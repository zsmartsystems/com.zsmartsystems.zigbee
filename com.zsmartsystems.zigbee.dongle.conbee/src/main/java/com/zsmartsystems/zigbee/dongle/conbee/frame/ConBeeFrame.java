package com.zsmartsystems.zigbee.dongle.conbee.frame;

import java.util.Arrays;

/**
 *
 * @author Chris Jackson
 *
 */
public abstract class ConBeeFrame {
    protected int sequence;

    protected final static int DEVICE_STATE = 0x07;
    protected final static int READ_PARAMETER = 0x0A;
    protected final static int WRITE_PARAMETER = 0x0B;
    protected final static int CHANGE_NETWORK_STATE = 0x08;
    protected final static int APS_DATA_INDICATION = 0x17;
    protected final static int APS_DATA_REQUEST = 0x12;
    protected final static int APS_DATA_CONFIRM = 0x04;

    protected final int[] buffer;
    protected int length = 0;

    public ConBeeFrame() {
        buffer = new int[129];
    }

    public ConBeeFrame(final int[] buffer) {
        this.buffer = buffer;
    }

    public int[] getOutputBuffer() {
        length = 0;
        return null;
    };

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

    protected int deserializeInt8() {
        return buffer[length++];
    }

    protected int deserializeUInt8() {
        return buffer[length++];
    }

    protected ConBeeStatus deserializeStatus() {
        return ConBeeStatus.values()[buffer[length++]];
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

    protected int deserializeUInt16() {
        return buffer[length++] + (buffer[length++] << 8);
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

    private static int getChecksum(final int[] frame, int length) {
        int crc = 0x0;

        for (int cnt = 0; cnt < length; cnt++) {
            crc += frame[cnt];
        }
        return (~crc + 1) & 0xffff;
    }

    public static ConBeeFrame create(final int[] buffer) {
        // Check the checksum
        int checksum = getChecksum(buffer, buffer.length - 2);
        if (checksum != (buffer[buffer.length - 2] + (buffer[buffer.length - 1] << 8))) {
            return null;
        }
        switch (buffer[0]) {
            case DEVICE_STATE:
                return new ConBeeDeviceStateResponse(buffer);
            case READ_PARAMETER:
                return new ConBeeReadParameterResponse(buffer);
        }
        return null;
    }
}
