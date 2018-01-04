/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ash;

/**
 * ASH Frame Handling: Asynchronous Serial Host (ASH) protocol. The ASH protocol
 * is a data-link layer protocol below EZSP and above the serial device (or
 * UART) driver.
 *
 * @author Chris Jackson
 *
 */
public class AshFrame {
    protected int frmNum;
    protected int ackNum;
    protected boolean reTx;
    protected boolean nRdy;

    protected FrameType frameType;
    protected int[] dataBuffer;

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

    public enum FrameType {
        DATA,
        ACK,
        NAK,
        RST,
        RSTACK
    }

    public static String frameToString(int[] inputBuffer) {
        if (inputBuffer == null || inputBuffer.length == 4) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < inputBuffer.length - 2; i++) {
            result.append(String.format("%02X ", inputBuffer[i]));
        }
        return result.toString();
    }

    public FrameType getFrameType() {
        return frameType;
    }

    public static String frameToString(int[] inputBuffer, int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(String.format("%02X ", inputBuffer[i]));
        }
        return result.toString();
    }

    public boolean isReTransmit() {
        return reTx;
    }

    public int[] getDataBuffer() {
        return dataBuffer;
    }
}
