/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ash;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ASH Frame Handling: Asynchronous Serial Host (ASH) protocol. The ASH protocol
 * is a data-link layer protocol below EZSP and above the serial device (or
 * UART) driver.
 * <p>
 * UG101: UART GATEWAY PROTOCOL REFERENCE: FOR THE EMBER® EZSP NETWORK CO-PROCESSOR
 *
 * @author Chris Jackson
 *
 */
public class AshFrame {
    private final static Logger logger = LoggerFactory.getLogger(AshFrame.class);

    protected int frmNum;
    protected int ackNum;
    protected boolean reTx;
    protected boolean nRdy;

    protected FrameType frameType;
    protected int[] dataBuffer;

    protected AshFrame() {
    }

    /**
     * Returns the data to be sent to the NCP
     * <ol>
     * <li>The Control Byte is added before the Data Field. The frmNum field is set to the last frame transmitted plus
     * one, and the ackNum field is set to the number of the next frame expected to be received. The reTx flag is clear.
     * <li>The Data Field is exclusive-OR’ed with a pseudo-random sequence (see Data randomization).
     * <li>The two-byte CRC of the Control Byte plus the Data Field is computed and appended after the Data Field.
     * <li>The frame is byte stuffed to remove reserved byte values (see Reserved bytes and byte stuffing).
     * <li>A Flag Byte is added after the CRC.
     * </ol>
     *
     * @param frmNum
     * @param ackNum
     * @return integer array of data to be sent
     */
    public int[] getOutputBuffer() {
        if (frmNum > 7 || frmNum < 0) {
            logger.debug("Invalid frmNum {}. Assuming 0", frmNum);
            frmNum = 0;
        }
        if (ackNum > 7 || ackNum < 0) {
            logger.debug("Invalid ackNum {}. Assuming 0", ackNum);
            ackNum = 0;
        }

        int outputData[] = new int[250];
        int outputPos = 0;

        switch (frameType) {
            case ACK:
                outputData[outputPos++] = 0x80 + ackNum;
                break;
            case DATA:
                outputData[outputPos++] = (frmNum << 4) + ackNum + (reTx ? 0x08 : 0x00);
                break;
            case ERROR:
                break;
            case NAK:
                break;
            case RST:
                outputData[outputPos++] = 0xC0;
                break;
            case RSTACK:
                break;
            default:
                break;
        }
        if (dataBuffer != null) {
            for (int cnt = 0; cnt < dataBuffer.length; cnt++) {
                outputData[outputPos++] = dataBuffer[cnt];
            }
            if (frameType == FrameType.DATA) {
                dataRandomise(outputData, 1, dataBuffer.length + 1);
            }
        }

        int crc = checkCRC(outputData, outputPos);
        outputData[outputPos++] = (crc >> 8) & 0xFF;
        outputData[outputPos++] = crc & 0xFF;

        int[] stuffedOutputData = new int[250];
        int stuffedOutputPos = 0;
        for (int cnt = 0; cnt < outputPos; cnt++) {
            switch (outputData[cnt]) {
                case 0x7E:
                    stuffedOutputData[stuffedOutputPos++] = 0x7D;
                    stuffedOutputData[stuffedOutputPos++] = 0x5E;
                    break;
                case 0x7D:
                    stuffedOutputData[stuffedOutputPos++] = 0x7D;
                    stuffedOutputData[stuffedOutputPos++] = 0x5D;
                    break;
                case 0x11:
                    stuffedOutputData[stuffedOutputPos++] = 0x7D;
                    stuffedOutputData[stuffedOutputPos++] = 0x31;
                    break;
                case 0x13:
                    stuffedOutputData[stuffedOutputPos++] = 0x7D;
                    stuffedOutputData[stuffedOutputPos++] = 0x33;
                    break;
                case 0x18:
                    stuffedOutputData[stuffedOutputPos++] = 0x7D;
                    stuffedOutputData[stuffedOutputPos++] = 0x38;
                    break;
                case 0x1A:
                    stuffedOutputData[stuffedOutputPos++] = 0x7D;
                    stuffedOutputData[stuffedOutputPos++] = 0x3A;
                    break;
                default:
                    stuffedOutputData[stuffedOutputPos++] = outputData[cnt];
                    break;
            }
        }
        stuffedOutputData[stuffedOutputPos++] = 0x7E;

        return Arrays.copyOfRange(stuffedOutputData, 0, stuffedOutputPos);
    }

    protected void processHeader(int[] inputBuffer) {
        switch (frameType) {
            case DATA:
                ackNum = (inputBuffer[0] & 0x07);
                frmNum = (inputBuffer[0] & 0x70) >> 4;
                reTx = (inputBuffer[0] & 0x08) != 0;
                break;
            case ACK:
            case NAK:
                nRdy = (inputBuffer[0] & 0x08) != 0;
                ackNum = (inputBuffer[0] & 0x07);
                break;
            default:
                break;
        }
    }

    public static AshFrame createFromInput(int[] buffer) {
        // A frame must be at least 3 bytes long
        if (buffer.length < 3) {
            return null;
        }

        // Remove byte stuffing
        int[] unstuffedData = new int[buffer.length];
        int outLength = 0;
        boolean escape = false;
        for (int data : buffer) {
            if (escape) {
                escape = false;
                if ((data & 0x20) == 0) {
                    data = (byte) (data + 0x20);
                } else {
                    data = (byte) (data & 0xDF);
                }
            } else if (data == 0x7D) {
                escape = true;
                continue;
            }
            unstuffedData[outLength++] = data;
        }

        // Check CRC
        if (checkCRC(unstuffedData, outLength) != 0) {
            return null;
        }

        FrameType frameType = getFrameType(unstuffedData);
        if (frameType == null) {
            logger.debug("Invalid ASH frame type {}", String.format("%02X", unstuffedData[0]));
            return null;
        }

        int[] frameBuffer = Arrays.copyOfRange(unstuffedData, 0, outLength);
        switch (frameType) {
            case ACK:
                return new AshFrameAck(frameBuffer);
            case DATA:
                dataRandomise(frameBuffer, 1, frameBuffer.length);
                return new AshFrameData(frameBuffer);
            case ERROR:
                return new AshFrameError(frameBuffer);
            case NAK:
                return new AshFrameNak(unstuffedData);
            case RST:
                return new AshFrameRst();
            case RSTACK:
                return new AshFrameRstAck(frameBuffer);
            default:
                break;
        }
        return null;
    }

    private static void dataRandomise(int[] buffer, int start, int length) {
        // Randomise the data
        int rand = 0x42;
        for (int cnt = start; cnt < length; cnt++) {
            buffer[cnt] = buffer[cnt] ^ rand;

            if ((rand & 0x01) == 0) {
                rand = rand >> 1;
            } else {
                rand = (rand >> 1) ^ 0xb8;
            }
        }
    }

    private static FrameType getFrameType(int[] buffer) {
        if (buffer == null) {
            return null;
        }

        if ((buffer[0] & 0x80) == 0) {
            return FrameType.DATA;
        } else if ((buffer[0] & 0x60) == 0x00) {
            return FrameType.ACK;
        } else if ((buffer[0] & 0x60) == 0x20) {
            return FrameType.NAK;
        } else if (buffer[0] == 0xC0) {
            return FrameType.RST;
        } else if (buffer[0] == 0xC1) {
            return FrameType.RSTACK;
        } else if (buffer[0] == 0xC2) {
            return FrameType.ERROR;
        }

        return null;
    }

    public int getFrmNum() {
        return frmNum;
    }

    public void setFrmNum(int frmNum) {
        this.frmNum = frmNum;
    }

    public int getAckNum() {
        return ackNum;
    }

    public void setAckNum(int ackNum) {
        this.ackNum = ackNum;
    }

    private static int checkCRC(int[] buffer, int length) {
        int crc = 0xFFFF; // initial value
        int polynomial = 0x1021; // 0001 0000 0010 0001 (0, 5, 12)

        for (int cnt = 0; cnt < length; cnt++) {
            for (int i = 0; i < 8; i++) {
                boolean bit = ((buffer[cnt] >> (7 - i) & 1) == 1);
                boolean c15 = ((crc >> 15 & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit) {
                    crc ^= polynomial;
                }
            }
        }

        crc &= 0xffff;

        return crc;
    }

    enum FrameType {
        DATA,
        ACK,
        NAK,
        RST,
        RSTACK,
        ERROR
    }

    public FrameType getFrameType() {
        return frameType;
    }
}
