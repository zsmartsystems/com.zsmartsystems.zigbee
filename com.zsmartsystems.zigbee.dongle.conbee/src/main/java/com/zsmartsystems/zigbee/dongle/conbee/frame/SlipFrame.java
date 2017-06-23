package com.zsmartsystems.zigbee.dongle.conbee.frame;

import java.io.ByteArrayOutputStream;

public class SlipFrame {

    private final static int ESC = 0xDB;
    private final static int ESC_END = 0xDC;
    private final static int ESC_ESC = 0xDD;
    private final static int END = 0xC0;

    /**
     * Creates a SLIP frame
     *
     * @param _data the _data
     * @return the byte[]
     */
    public static byte[] createFrame(int[] payload) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream(payload.length * 2);

        for (int i = 0; i < payload.length; i++) {
            if (payload[i] == END) {
                bout.write((byte) ESC);
                bout.write((byte) ESC_END);
            } else if (payload[i] == ESC) {
                bout.write(ESC);
                bout.write(ESC_ESC);
            } else {
                bout.write(payload[i]);
            }
        }

        return bout.toByteArray();
    }

    /**
     * Parses a SLIP formatted frame and provides the data received.
     *
     * @param data the data
     * @return the byte[]
     */
    public static int[] parseFrame(byte[] payload) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream(payload.length);

        for (int i = 0; i < payload.length; i++) {
            // if we have and esc and another byte...
            if (payload[i] == ESC && i + 1 < payload.length) {
                i++;
                if (payload[i] == ESC_END) {
                    bout.write(END);
                } else if (payload[i] == ESC_ESC) {
                    bout.write(ESC);
                } else {
                    bout.write(payload[i]);
                }
            }
        }

        return new int[1];
    }
}