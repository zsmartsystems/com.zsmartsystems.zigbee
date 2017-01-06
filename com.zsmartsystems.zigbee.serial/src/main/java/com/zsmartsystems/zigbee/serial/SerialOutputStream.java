package com.zsmartsystems.zigbee.serial;

import jssc.SerialPort;
import jssc.SerialPortException;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Class that wraps a {@link SerialPort} to provide
 * {@link OutputStream} functionality.
 * <br>
 * It is instantiated by passing the constructor a {@link SerialPort}
 * instance.  Do not create multiple streams for the
 * same serial port unless you implement your own
 * synchronization.
 *
 * @author Charles Hache <chalz@member.fsf.org>
 *
 * Attribution: https://github.com/therealchalz/java-simple-serial-connector
 *
 */
public class SerialOutputStream extends OutputStream {
    /**
     * The serial port.
     */
    private final SerialPort serialPort;

    /** Instantiates a SerialOutputStream for the given {@link SerialPort}
     * Do not create multiple streams for the same serial port
     * unless you implement your own synchronization.
     * @param sp The serial port to stream.
     */
    public SerialOutputStream(SerialPort sp) {
        serialPort = sp;
    }

    @Override
    public void write(int b) throws IOException {
        try {
            serialPort.writeInt(b);
        } catch (SerialPortException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);

    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        byte[] buffer = new byte[len];
        System.arraycopy(b, off, buffer, 0, len);
        try {
            serialPort.writeBytes(buffer);
        } catch (SerialPortException e) {
            throw new IOException(e);
        }
    }
}