/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.transport.ZigBeePort;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 * The default Java SE serial port implementation.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class SerialPortImpl implements ZigBeePort, SerialPortEventListener {
    /**
     * The logger.
     */
    private final static Logger logger = LoggerFactory.getLogger(SerialPortImpl.class);
    /**
     * The portName portName.
     */
    private jssc.SerialPort serialPort;
    /**
     * The portName portName input stream.
     */
    private InputStream inputStream;
    /**
     * The portName portName output stream.
     */
    private OutputStream outputStream;
    /**
     * The port identifier.
     */
    private final String portName;
    /**
     * The baud rate.
     */
    private final int baudRate;

    /**
     * Constructor setting port name and baud rate.
     * 
     * @param portName the port name
     * @param baudRate the baud rate
     */
    public SerialPortImpl(final String portName, final int baudRate) {
        this.portName = portName;
        this.baudRate = baudRate;
    }

    @Override
    public boolean open() {
        try {
            openSerialPort(portName, baudRate);

            return true;
        } catch (Exception e) {
            logger.warn("Unable to open serial port: " + e.getMessage());
            return false;
        }
    }

    /**
     * Opens serial port.
     * 
     * @param portName the port name
     * @param baudRate the baud rate
     */
    private void openSerialPort(String portName, int baudRate) {
        if (serialPort != null) {
            throw new RuntimeException("Serial port already open.");
        }

        serialPort = new jssc.SerialPort(portName);
        try {
            serialPort.openPort();
            serialPort.setParams(baudRate, 8, 1, 0);
            serialPort.setFlowControlMode(jssc.SerialPort.FLOWCONTROL_RTSCTS_OUT); // FLOWCONTROL_NONE);
        } catch (SerialPortException e) {
            logger.error("Error opening serial port.", e);
            throw new RuntimeException("Failed to open serial port: " + portName, e);
        }

        inputStream = new UnsignedByteSerialInputStream(serialPort);
        outputStream = new SerialOutputStream(serialPort);
    }

    @Override
    public void close() {
        try {
            if (serialPort != null) {
                try {
                    while (inputStream.available() > 0) {
                        try {
                            Thread.sleep(100);
                        } catch (final InterruptedException e) {
                            logger.warn("Interrupted while waiting input stream to flush.");
                        }
                    }
                } catch (Exception e) {
                    logger.trace("Exception in reading from serial port.", e);
                }
                inputStream.close();
                outputStream.flush();
                outputStream.close();
                serialPort.closePort();
                logger.info("Serial portName '" + serialPort.getPortName() + "' closed.");
                serialPort = null;
                inputStream = null;
                outputStream = null;
            }
        } catch (Exception e) {
            logger.warn("Error closing portName portName: '" + serialPort.getPortName() + "'", e);
        }
    }

    @Override
    public OutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR()) {
            try {
                if (inputStream.available() > 0) {
                    synchronized (this) {
                        this.notify();
                    }
                }
            } catch (IOException e) {
                logger.error("Error while handling serial event.", e);
            }
        }
    }
}
