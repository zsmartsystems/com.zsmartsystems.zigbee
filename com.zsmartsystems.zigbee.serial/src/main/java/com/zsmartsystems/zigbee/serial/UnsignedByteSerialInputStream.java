package com.zsmartsystems.zigbee.serial;

import jssc.SerialPort;

import java.io.IOException;

/**
 * Unsigned byte serial input stream.
 *
 * @author Allan Lang
 *
 * Attribution: https://github.com/allanlang/xbee-api-jssc/
 *
 */
public class UnsignedByteSerialInputStream extends SerialInputStream {

    /**
     * Constructor for setting the seria port.
     * @param sp the serial port.
     */
    public UnsignedByteSerialInputStream(SerialPort sp) {
        super(sp);
    }

    @Override
    public int read(int timeout) throws IOException {
        return (super.read(timeout) & 0xff);
    }

    @Override
    public int read(byte[] buf, int offset, int length) throws IOException {
        int bytesRead = super.read(buf, offset, length);
        unsignArray(buf, bytesRead);
        return bytesRead;
    }

    @Override
    public int blockingRead(byte[] buf, int offset, int length, int timeout)
            throws IOException {
        int bytesRead = super.blockingRead(buf, offset, length, timeout);
        unsignArray(buf, bytesRead);
        return bytesRead;
    }

    /**
     * Unsign array.
     * @param inputArray the input array
     * @param length the array length
     */
    private void unsignArray(byte[] inputArray, int length) {
        if (length > 0) {
            byte[] unsignedBytes = new byte[length];
            for (int i = 0; i < length; i++) {
                int s = inputArray[i] & 0xf;
                unsignedBytes[i] = (byte) s;
            }
            System.arraycopy(unsignedBytes, 0, inputArray, 0, length);
        }
    }

}

