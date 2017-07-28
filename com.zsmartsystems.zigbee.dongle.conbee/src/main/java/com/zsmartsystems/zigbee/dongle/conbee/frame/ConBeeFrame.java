package com.zsmartsystems.zigbee.dongle.conbee.frame;

import java.util.Arrays;

/**
 *
 * @author Chris Jackson
 *
 */
public abstract class ConBeeFrame {
    protected int sequence;

    private int[] buffer = new int[129];
    private int length;

    public abstract int[] getOutputBuffer();

    protected int[] copyOutputBuffer() {
        // Add the CRC
        int crc = getChecksum(buffer, length);
        buffer[length++] = crc & 0xFF;
        buffer[length++] = (crc >> 8) & 0xFF;

        return Arrays.copyOfRange(buffer, 0, length);
    }

    /**
     * Adds a uint8_t into the output stream
     *
     * @param val
     */
    public void serializeUInt8(int val) {
        buffer[length++] = val & 0xFF;
    }

    /**
     * Adds a uint16_t into the output stream
     *
     * @param val
     */
    public void serializeUInt16(int val) {
        buffer[length++] = val & 0xFF;
        buffer[length++] = (val >> 8) & 0xFF;
    }

    /**
     * Adds a uint32_t into the output stream
     *
     * @param val
     */
    public void serializeUInt32(int val) {
        buffer[length++] = val & 0xFF;
        buffer[length++] = (val >> 8) & 0xFF;
        buffer[length++] = (val >> 16) & 0xFF;
        buffer[length++] = (val >> 24) & 0xFF;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getSequence() {
        return sequence;
    }

    private int getChecksum(final int[] frame, int length) {
        int crc = 0x0;

        for (int cnt = 0; cnt < length; cnt++) {
            crc += frame[cnt];
        }
        return (~crc + 1) & 0xffff;
    }
}
