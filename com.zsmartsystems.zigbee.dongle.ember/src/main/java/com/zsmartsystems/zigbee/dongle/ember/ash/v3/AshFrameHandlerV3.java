/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ash.v3;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.ember.EzspFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrame;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameAck;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameData;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameNak;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameRst;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameRstAck;

/**
 * Implements the ASHv3 protocol defined in the document
 * UG115: ASHv3 Protocol Reference
 *
 * @author Chris Jackson
 *
 */
public class AshFrameHandlerV3 extends AshFrameHandler {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(AshFrameHandlerV3.class);

    private final int ASH_FLAG_START = 0x7E;
    private final int ASH_FLAG_ESCAPE = 0x7D;

    private final int ASH_CTRL_RST = (0 << 6);
    private final int ASH_CTRL_RSTACK = (1 << 6);
    private final int ASH_CTRL_ACK = (2 << 6);
    private final int ASH_CTRL_NAK = (3 << 6);

    private final int ASH_FLAG_BYTE = 0x7E;
    private final int ASH_XON_BYTE = 0x11;
    private final int ASH_OFF_BYTE = 0x13;
    private final int ASH_TIMEOUT = -1;

    private final int ASH_MAX_LENGTH = 131;

    public AshFrameHandlerV3(EzspFrameHandler frameHandler) {
        super(frameHandler);
    }

    @Override
    protected int[] getPacket() throws IOException {
        int[] inputBuffer = new int[ASH_MAX_LENGTH];
        int inputCount = 0;
        boolean inputError = false;

        while (!close) {
            int val = port.read();
            logger.trace("ASH RX: " + String.format("%02X", val));
            switch (val) {
                case ASH_FLAG_BYTE:
                    // Flag Byte: Marks the start and end of a frame. When a Flag Byte is received, the data received
                    // since the last Flag Byte or Cancel Byte is tested to see whether it is a valid frame.
                    if (!inputError && inputCount != 0) {
                        return Arrays.copyOfRange(inputBuffer, 0, inputCount);
                    }
                    inputBuffer[0] = ASH_FLAG_BYTE;
                    inputCount = 1;
                    inputError = false;
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

    @Override
    protected AshFrame createAshFrame(int[] buffer) {
        if (buffer[0] != ASH_FLAG_START) {
            return null;
        }

        int control = buffer[2];
        int length = buffer[3];

        if ((buffer[1] & 0x80) != 0) {
            control = escapeValue(control);
        }

        if ((buffer[1] & 0x40) != 0) {
            length = escapeValue(length);
        }

        int[] unstuffedData = new int[length + 7];
        unstuffedData[0] = ASH_FLAG_START;
        int unstuffedPos = 1;
        for (int cnt = 1; cnt < buffer.length; cnt++) {
            if (buffer[cnt] == ASH_FLAG_ESCAPE) {
                cnt++;
                unstuffedData[unstuffedPos++] = escapeValue(buffer[cnt]);
            } else {
                unstuffedData[unstuffedPos++] = buffer[cnt];
            }
        }

        int crc = checkCRC(unstuffedData, unstuffedData.length - 3);

        if (unstuffedData[unstuffedData.length - 3] != ((crc >> 8) & 0xEF)
                || unstuffedData[unstuffedData.length - 2] != (crc & 0xEF)
                || unstuffedData[unstuffedData.length - 1] != (((crc & 0x1000) != 0) ? 0x80 : 0x00)
                        + (((crc & 0x0010) != 0) ? 0x40 : 0x00)) {
            logger.debug("ASHv3 CRC error");
            return null;
        }

        AshFrame frame = null;
        switch (control & 0xC0) {
            case ASH_CTRL_RST:
                return new AshFrameRst();
            case ASH_CTRL_RSTACK:
                return new AshFrameRstAck();
            case ASH_CTRL_ACK:
                if (length == 0) {
                    frame = new AshFrameAck();
                } else {
                    frame = new AshFrameData();
                }
                break;
            case ASH_CTRL_NAK:
                if (length == 0) {
                    frame = new AshFrameNak();
                } else {
                    frame = new AshFrameData();
                }
                break;
        }

        if (frame != null) {
            frame.setAckNum(control & 0x07);
            frame.setFrmNum((control & 0x38) >> 3);
        }

        return frame;
    }

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

        int outputData[] = new int[ASH_MAX_LENGTH];
        outputData[0] = 0x7E;
        outputData[1] = 0x00;
        outputData[2] = (frmNum << 3) + ackNum;

        int outputPos = 3;

        switch (frame.getFrameType()) {
            case ACK:
                outputData[2] |= ASH_CTRL_ACK;
                outputData[outputPos++] = 0x00;
                break;
            case DATA:
                outputData[2] |= ASH_CTRL_ACK;
                AshFrameData dataFrame = (AshFrameData) frame;
                outputData[outputPos++] = dataFrame.getDataBuffer().length;
                for (int value : frame.getDataBuffer()) {
                    outputData[outputPos++] = value;
                }
                break;
            case NAK:
                outputData[2] |= ASH_CTRL_NAK;
                outputData[outputPos++] = 0x00;
                break;
            case RST:
                outputData[2] |= 0x08;
                outputData[outputPos++] = 0x00;
                break;
            case RSTACK:
                outputData[2] |= 0x49;
                outputData[outputPos++] = 0x00;
                break;
            default:
                break;
        }

        // Handle the header escape
        if (requiresEscape(outputData[2])) {
            outputData[1] |= 80;
            outputData[2] = escapeValue(outputData[2]);
        }
        if (requiresEscape(outputData[3])) {
            outputData[1] |= 40;
            outputData[3] = escapeValue(outputData[3]);
        }

        // Add the CRC
        int crc = checkCRC(outputData, outputPos);
        outputData[outputPos++] = (crc >> 8) & 0xEF;
        outputData[outputPos++] = crc & 0xEF;
        outputData[outputPos++] = (((crc & 0x1000) != 0) ? 0x80 : 0x00) + (((crc & 0x0010) != 0) ? 0x40 : 0x00);

        // Do byte stuffing
        int[] stuffedData = new int[ASH_MAX_LENGTH];
        stuffedData[0] = ASH_FLAG_START;
        int stuffedPos = 1;
        for (int cnt = 1; cnt < outputPos; cnt++) {
            if (requiresEscape(outputData[cnt])) {
                stuffedData[stuffedPos++] = ASH_FLAG_ESCAPE;
                stuffedData[stuffedPos++] = escapeValue(outputData[cnt]);
            } else {
                stuffedData[stuffedPos++] = outputData[cnt];
            }
        }

        return Arrays.copyOfRange(stuffedData, 0, stuffedPos);
    }

    private boolean requiresEscape(int value) {
        switch (value) {
            case 0x11:
            case 0x13:
            case 0x7D:
            case 0x7E:
            case 0xF8:
                return true;
            default:
                return false;
        }
    }

    private int escapeValue(int value) {
        return value ^ (1 << 5);
    }

    private int checkCRC(int[] buffer, int length) {
        int crc = 0x0000; // initial value
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
