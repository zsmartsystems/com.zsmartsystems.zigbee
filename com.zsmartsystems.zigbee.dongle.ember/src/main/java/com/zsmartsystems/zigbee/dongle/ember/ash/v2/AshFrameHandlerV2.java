/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ash.v2;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.ember.EzspFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrame;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrame.FrameType;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameAck;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameData;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameNak;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameRst;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameRstAck;

/**
 * Implements the ASHv2 protocol defined in the document
 * UG101: UART GATEWAY PROTOCOL REFERENCE: FOR THE EMBER® EZSP NETWORK CO-PROCESSOR
 *
 * @author Chris Jackson
 *
 */
public class AshFrameHandlerV2 extends AshFrameHandler {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(AshFrameHandlerV2.class);

    private final int ASH_CANCEL_BYTE = 0x1A;
    private final int ASH_FLAG_BYTE = 0x7E;
    private final int ASH_SUBSTITUTE_BYTE = 0x18;
    private final int ASH_XON_BYTE = 0x11;
    private final int ASH_OFF_BYTE = 0x13;
    private final int ASH_TIMEOUT = -1;

    private final int ASH_MAX_LENGTH = 131;

    public AshFrameHandlerV2(EzspFrameHandler frameHandler) {
        super(frameHandler);
    }

    @Override
    protected int[] getPacket() throws IOException {
        int[] inputBuffer = new int[ASH_MAX_LENGTH];
        int inputCount = 0;
        boolean inputError = false;

        while (!closeHandler) {
            int val = port.read();
            logger.trace("ASH RX: " + String.format("%02X", val));
            switch (val) {
                case ASH_CANCEL_BYTE:
                    // Cancel Byte: Terminates a frame in progress. A Cancel Byte causes all data received since the
                    // previous Flag Byte to be ignored. Note that as a special case, RST and RSTACK frames are preceded
                    // by Cancel Bytes to ignore any link startup noise.
                    inputCount = 0;
                    inputError = false;
                    break;
                case ASH_FLAG_BYTE:
                    // Flag Byte: Marks the end of a frame.When a Flag Byte is received, the data received since the
                    // last Flag Byte or Cancel Byte is tested to see whether it is a valid frame.
                    if (!inputError && inputCount != 0) {
                        return Arrays.copyOfRange(inputBuffer, 0, inputCount);
                    }
                    inputCount = 0;
                    inputError = false;
                    break;
                case ASH_SUBSTITUTE_BYTE:
                    // Substitute Byte: Replaces a byte received with a low-level communication error (e.g., framing
                    // error) from the UART.When a Substitute Byte is processed, the data between the previous and the
                    // next Flag Bytes is ignored.
                    inputError = true;
                    break;
                case ASH_XON_BYTE:
                    // XON: Resume transmissionUsed in XON/XOFF flow control. Always ignored if received by the NCP.
                    break;
                case ASH_OFF_BYTE:
                    // XOFF: Stop transmissionUsed in XON/XOFF flow control. Always ignored if received by the NCP.
                    break;
                case ASH_TIMEOUT:
                    break;
                default:
                    if (inputCount >= ASH_MAX_LENGTH) {
                        inputCount = 0;
                        inputError = true;
                    }
                    inputBuffer[inputCount++] = val;
                    break;
            }
        }

        return null;
    }

    private void dataRandomise(int[] buffer, int start, int length) {
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
    @Override
    protected int[] getOutputBuffer(AshFrame frame) {
        int frmNum = frame.getFrmNum();
        if (frmNum > 7 || frmNum < 0) {
            logger.debug("Invalid frmNum {}. Assuming 0", frmNum);
            frmNum = 0;
        }
        int ackNum = frame.getAckNum();
        if (ackNum > 7 || ackNum < 0) {
            logger.debug("Invalid ackNum {}. Assuming 0", ackNum);
            ackNum = 0;
        }

        int outputData[] = new int[250];
        int outputPos = 0;

        switch (frame.getFrameType()) {
            case ACK:
                outputData[outputPos++] = 0x80 + ackNum;
                break;
            case DATA:
                outputData[outputPos++] = (frmNum << 4) + ackNum + (frame.isReTransmit() ? 0x08 : 0x00);
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
        int[] dataBuffer = frame.getDataBuffer();
        if (dataBuffer != null) {
            for (int cnt = 0; cnt < dataBuffer.length; cnt++) {
                outputData[outputPos++] = dataBuffer[cnt];
            }
            if (frame.getFrameType() == FrameType.DATA) {
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

        if (frame.getFrameType() == FrameType.RST) {
            // When sending a reset we add a clear buffer command
            int[] outFrame = new int[stuffedOutputPos + 1];
            outFrame[0] = 0x1A;
            for (int cnt = 0; cnt < stuffedOutputPos; cnt++) {
                outFrame[cnt + 1] = stuffedOutputData[cnt];
            }
            return outFrame;
        }

        return Arrays.copyOfRange(stuffedOutputData, 0, stuffedOutputPos);
    }

    @Override
    protected AshFrame createAshFrame(int[] buffer) {
        // A frame must be at least 3 bytes long
        if (buffer.length < 3) {
            return null;
        }

        // Remove byte stuffing
        int[] unstuffedData = new int[131];
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
        int[] frameBuffer = Arrays.copyOfRange(unstuffedData, 0, outLength);

        // Check CRC
        if (checkCRC(unstuffedData, outLength) != 0) {
            return null;
        }

        FrameType frameType = null;
        if ((unstuffedData[0] & 0x80) == 0) {
            frameType = FrameType.DATA;
        } else if ((unstuffedData[0] & 0x60) == 0x00) {
            frameType = FrameType.ACK;
        } else if ((unstuffedData[0] & 0x60) == 0x20) {
            frameType = FrameType.NAK;
        } else if (unstuffedData[0] == 0xC0) {
            frameType = FrameType.RST;
        } else if (unstuffedData[0] == 0xC1) {
            frameType = FrameType.RSTACK;
        } else {
            logger.debug("Unknown ASH frame type {}", unstuffedData[0]);
        }

        if (frameType == null) {
            logger.debug("Invalid ASH frame type {}", String.format("%02X", unstuffedData[0]));
            return null;
        }

        switch (frameType) {
            case ACK:
                AshFrameAck ackFrame = new AshFrameAck();
                ackFrame.setAckNum((unstuffedData[0] & 0x07));
                return ackFrame;
            case DATA:
                dataRandomise(frameBuffer, 1, frameBuffer.length);
                AshFrameData dataFrame = new AshFrameData();
                dataFrame.setAckNum(unstuffedData[0] & 0x07);
                dataFrame.setFrmNum((unstuffedData[0] & 0x70) >> 4);
                if ((unstuffedData[0] & 0x08) != 0) {
                    dataFrame.setReTx();
                }
                dataFrame.setData(Arrays.copyOfRange(frameBuffer, 1, frameBuffer.length - 2));
                return dataFrame;
            case NAK:
                AshFrameNak nakFrame = new AshFrameNak();
                nakFrame.setAckNum((unstuffedData[0] & 0x07));
                return nakFrame;
            case RST:
                return new AshFrameRst();
            case RSTACK:
                AshFrameRstAck rstAckFrame = new AshFrameRstAck();
                rstAckFrame.setVersion(unstuffedData[1]);
                rstAckFrame.setResetCode(unstuffedData[2]);
                return rstAckFrame;
            default:
                break;
        }
        return null;
    }

    private int checkCRC(int[] buffer, int length) {
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

}
