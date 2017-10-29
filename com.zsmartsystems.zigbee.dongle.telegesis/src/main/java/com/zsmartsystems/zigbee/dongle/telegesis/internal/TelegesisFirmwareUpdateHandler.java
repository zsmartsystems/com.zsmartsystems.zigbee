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
    private final int MAX_RETRIES = 10;

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

    public void startBootload() {
        Thread firmwareThread = new Thread("TelegesisFirmwareUpdateHandler") {
            @Override
            public void run() {
                logger.debug("Telegesis bootloader: Starting");
                if (serialPort.open(115200) == false) {
                    logger.debug("Telegesis bootloader: Failed to open serial port");
                    callback.firmwareUpdateCallback(ZigBeeTransportFirmwareStatus.FIRMWARE_UPDATE_FAILED);
                    return;
                }

                int retryCount = 0;
                while (retryCount++ < 3) {
                    // Send CR to wake up the bootloader
                    serialPort.write(CR);

                    // Then wait for the prompt
                    if (getBlPrompt() == false) {
                        // continue;
                    }
                }
                if (retryCount >= 3) {
                    callback.firmwareUpdateCallback(ZigBeeTransportFirmwareStatus.FIRMWARE_UPDATE_FAILED);
                    return;
                }

                // Select option 1 to upload the file
                serialPort.write('1');

                transferFile();

                // File transfer complete wait for the prompt
                retryCount = 0;
                while (retryCount++ < 3) {
                    // Send CR to wake up the bootloader
                    serialPort.write(CR);

                    // Then wait for the prompt
                    if (getBlPrompt() == false) {
                        // continue;
                    }
                }
                if (retryCount >= 3) {
                    callback.firmwareUpdateCallback(ZigBeeTransportFirmwareStatus.FIRMWARE_UPDATE_FAILED);
                    return;
                }

                // Select 2 to run
                serialPort.write('2');

                // We're done
                callback.firmwareUpdateCallback(ZigBeeTransportFirmwareStatus.FIRMWARE_UPDATE_COMPLETE);

                serialPort.close();
            }
        };

        firmwareThread.start();
    }

    private boolean getBlPrompt() {
        int[] matcher = new int[] { 'B', 'L', ' ', '>' };
        int matcherCount = 0;

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

        return false;
    }

    private boolean transferFile() {
        int retries;
        int frame = 1;
        boolean done;

        InputStream fileStream = null;
        try {
            fileStream = new FileInputStream(file);

            while (!closeHandler) {
                // Send SOH
                serialPort.write(SOH);

                // Send frame count
                serialPort.write(frame);
                serialPort.write(255 - frame);

                retries = 0;
                do {
                    // Allow 10 retries
                    if (retries > MAX_RETRIES) {
                        fileStream.close();
                        return false;
                    }

                    // Output 128 bytes
                    byte[] data = new byte[128];
                    done = (fileStream.read(data) != 128);
                    int csum = 0;
                    for (int value : data) {
                        csum += value;
                        serialPort.write(value);
                    }
                    serialPort.write(csum & 0xff);

                    // Wait for the acknowledgment
                } while (getTransferResponse() != ACK);

                if (done) {
                    serialPort.write(EOT);
                    fileStream.close();
                    return true;
                }

                frame = (frame + 1) & 0xff;
            }
        } catch (IOException e) {
            logger.debug("Error reading Telegesis dongle firmware file:", e);
        }
        if (fileStream != null) {
            try {
                fileStream.close();
            } catch (IOException e) {
                logger.debug("Error closing Telegesis dongle firmware file:", e);
            }
        }
        return false;
    }

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
