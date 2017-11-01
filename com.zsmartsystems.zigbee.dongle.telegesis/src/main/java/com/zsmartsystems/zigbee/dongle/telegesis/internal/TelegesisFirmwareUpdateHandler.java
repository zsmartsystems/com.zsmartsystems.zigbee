/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.transport.ZigBeePort;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportFirmwareCallback;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportFirmwareStatus;

/**
 * Firmware update handler for the Telegesis dongle
 *
 * @author Chris Jackson
 *
 */
public class TelegesisFirmwareUpdateHandler {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(TelegesisFrameHandler.class);

    private final File file;
    private final ZigBeePort serialPort;
    private final ZigBeeTransportFirmwareCallback callback;

    private final int TIMEOUT = 10000;
    private final int MENU_MAX_RETRIES = 3;
    private final int XMODEM_MAX_RETRIES = 10;
    private final int XMODEL_CRC_POLYNOMIAL = 0x1021;

    private final int CR = '\n';
    private final int SOH = 0x01;
    private final int EOT = 0x04;
    private final int ACK = 0x06;
    private final int NAK = 0x15;
    private final int CAN = 0x18;

    /**
     * Flag reflecting that parser has been closed and parser parserThread should exit.
     */
    private boolean closeHandler = false;

    public TelegesisFirmwareUpdateHandler(File file, ZigBeePort serialPort, ZigBeeTransportFirmwareCallback callback) {
        this.file = file;
        this.serialPort = serialPort;
        this.callback = callback;
    }

    /**
     * Starts the bootload.
     * The routine will open the serial port at 115200bd. It will send a CR up to 3 times waiting for the bootloader
     * prompt. It will then select the upload option, transfer the file, then run. The serial port will then be closed.
     */
    public void startBootload() {
        Thread firmwareThread = new Thread("TelegesisFirmwareUpdateHandler") {
            @Override
            public void run() {
                logger.debug("Telegesis bootloader: Starting.");
                if (serialPort.open(115200) == false) {
                    logger.debug("Telegesis bootloader: Failed to open serial port.");
                    callback.firmwareUpdateCallback(ZigBeeTransportFirmwareStatus.FIRMWARE_UPDATE_FAILED);
                    return;
                }

                logger.debug("Telegesis bootloader: Serial port opened.");

                // Wait for the bootload menu prompt
                if (getBlPrompt() == false) {
                    return;
                }
                logger.debug("Telegesis bootloader: Got bootloader prompt.");

                // Select option 1 to upload the file
                serialPort.write('1');

                // Short delay here to allow all bootloader menu data to be received so there's no confusion with acks.
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    // Eat me!
                }

                transferFile();

                logger.debug("Telegesis bootloader: Waiting for menu.");

                // Wait for the bootload menu prompt
                if (getBlPrompt() == false) {
                    return;
                }
                logger.debug("Telegesis bootloader: Got bootloader prompt.");

                logger.debug("Telegesis bootloader: Running firmware.");

                // Select 2 to run
                serialPort.write('2');

                // We're done
                callback.firmwareUpdateCallback(ZigBeeTransportFirmwareStatus.FIRMWARE_UPDATE_COMPLETE);

                logger.debug("Telegesis bootloader: Closing serial port. Done.");
                serialPort.close();
            }
        };

        firmwareThread.start();
    }

    /**
     * Waits for the Telegesis bootloader "BL >" response
     *
     * @return true if the prompt is found
     */
    private boolean getBlPrompt() {
        int[] matcher = new int[] { 'B', 'L', ' ', '>' };
        int matcherCount = 0;

        int retryCount = 0;
        while (!closeHandler && retryCount < MENU_MAX_RETRIES) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                // Eat me!
            }

            // Send CR to wake up the bootloader
            serialPort.write(CR);

            while (!closeHandler) {
                int val = serialPort.read();
                if (val == -1) {
                    continue;
                }
                if (val != matcher[matcherCount]) {
                    matcherCount = 0;
                    continue;
                }

                matcherCount++;
                if (matcherCount == matcher.length) {
                    return true;
                }
            }

            if (retryCount >= 3) {
                logger.debug("Telegesis bootloader: Unable to get bootloader prompt.");
                callback.firmwareUpdateCallback(ZigBeeTransportFirmwareStatus.FIRMWARE_UPDATE_FAILED);
                return false;
            }

            retryCount++;
        }

        return false;
    }

    /**
     * Transfers the file using the XModem protocol
     *
     * @return true if the transfer completed successfully
     */
    private boolean transferFile() {
        int retries;
        int response;
        int frame = 1;
        boolean done;

        // Clear all input in the input stream before starting the transfer
        try {
            InputStream fileStream;
            fileStream = new FileInputStream(file);

            logger.debug("Telegesis bootloader: Clearing input stream...");
            serialPort.purgeRxBuffer();

            logger.debug("Telegesis bootloader: Starting transfer.");
            while (!closeHandler) {
                retries = 0;

                do {
                    logger.debug("Telegesis bootloader: Transfer frame {}, attempt {}.", frame, retries);

                    // Send SOH
                    serialPort.write(SOH);

                    // Send frame count
                    serialPort.write(frame);
                    serialPort.write(255 - frame);

                    // Allow 10 retries
                    if (retries++ > XMODEM_MAX_RETRIES) {
                        fileStream.close();
                        return false;
                    }

                    // Output 128 bytes
                    byte[] data = new byte[128];
                    done = (fileStream.read(data) != 128);
                    int crc = 0;
                    for (int value : data) {
                        serialPort.write(value);
                        for (int i = 0; i < 8; i++) {
                            boolean bit = ((value >> (7 - i) & 1) == 1);
                            boolean c15 = ((crc >> 15 & 1) == 1);
                            crc <<= 1;
                            // If coefficient of bit and remainder polynomial = 1 xor crc with polynomial
                            if (c15 ^ bit) {
                                crc ^= XMODEL_CRC_POLYNOMIAL;
                            }
                        }
                    }
                    crc &= 0xffff;
                    serialPort.write((crc >> 8) & 0xff);
                    serialPort.write(crc & 0xff);

                    // Wait for the acknowledgment
                    response = getTransferResponse();
                    if (response != ACK) {
                        logger.debug("Telegesis bootloader: Response {}.", response);
                    }
                } while (response != ACK);

                if (done) {
                    logger.debug("Telegesis bootloader: Transfer complete.");
                    serialPort.write(EOT);
                    fileStream.close();
                    return true;
                }

                frame = (frame + 1) & 0xff;
            }
            fileStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Waits for a single byte response as part of the XModem protocol
     *
     * @return the value of the received data, or NAK on timeout
     */
    private int getTransferResponse() {
        long start = System.currentTimeMillis();
        while (!closeHandler) {
            int val = serialPort.read();
            if (val == -1) {
                if (System.currentTimeMillis() - start > TIMEOUT) {
                    return NAK;
                }
                continue;
            }

            return val;
        }

        return NAK;
    }

}
