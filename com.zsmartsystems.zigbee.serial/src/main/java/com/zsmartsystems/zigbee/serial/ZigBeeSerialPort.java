/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.serial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.transport.ZigBeePort;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 * The default/reference Java serial port implementation using serial events to provide a non-blocking read call.
 *
 * @author Chris Jackson
 */
public class ZigBeeSerialPort implements ZigBeePort, SerialPortEventListener {
    /**
     * The logger.
     */
    private final static Logger logger = LoggerFactory.getLogger(ZigBeeSerialPort.class);

    /**
     * The portName portName.
     */
    private jssc.SerialPort serialPort;

    /**
     * The port identifier.
     */
    private final String portName;

    /**
     * The baud rate.
     */
    private final int baudRate;

    /**
     * True to enable RTS / CTS flow control
     */
    private final boolean flowControl;

    /**
     * The circular fifo queue for receive data
     */
    private int[] buffer = new int[512];

    /**
     * The receive buffer end pointer (where we put the newly received data)
     */
    private int end = 0;

    /**
     * The receive buffer start pointer (where we take the data to pass to the application)
     */
    private int start = 0;

    /**
     * The length of the receive buffer
     */
    private int maxLength = 512;

    /**
     * Constructor setting port name and baud rate.
     *
     * @param portName the port name
     * @param baudRate the baud rate
     * @param flowControl to use flow control
     */
    public ZigBeeSerialPort(String portName, int baudRate, boolean flowControl) {
        this.portName = portName;
        this.baudRate = baudRate;
        this.flowControl = flowControl;
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
            if (flowControl) {
                serialPort.setFlowControlMode(jssc.SerialPort.FLOWCONTROL_RTSCTS_OUT);
            } else {
                serialPort.setFlowControlMode(jssc.SerialPort.FLOWCONTROL_NONE);
            }
            serialPort.addEventListener(this);
        } catch (SerialPortException e) {
            logger.error("Error opening serial port.", e);
            throw new RuntimeException("Failed to open serial port: " + portName, e);
        }
    }

    @Override
    public void close() {
        try {
            if (serialPort != null) {
                synchronized (this) {
                    serialPort.removeEventListener();
                    serialPort.closePort();
                    serialPort = null;
                    this.notify();
                }

                logger.info("Serial port '" + portName + "' closed.");
            }
        } catch (Exception e) {
            logger.warn("Error closing serial port: '" + portName + "'", e);
        }
    }

    @Override
    public void write(int value) {
        if (serialPort == null) {
            return;
        }
        try {
            serialPort.writeInt(value);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int read() {
        return read(9999999);
    }

    @Override
    public int read(int timeout) {
        long endTime = System.currentTimeMillis() + timeout;

        try {
            while (System.currentTimeMillis() < endTime) {
                synchronized (this) {
                    if (serialPort == null) {
                        return -1;
                    }

                    if (start != end) {
                        int value = buffer[start++];
                        if (start >= maxLength) {
                            start = 0;
                        }
                        return value;
                    }

                    logger.debug("READ sleep");
                    wait(endTime - System.currentTimeMillis());
                    logger.debug("READ awake");
                }
            }
            return -1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR() & event.getEventValue() > 0) {
            try {
                int[] input = serialPort.readIntArray();
                if (input == null) {
                    return;
                }

                synchronized (this) {
                    for (int recv : input) {
                        buffer[end++] = recv;
                        if (end >= maxLength) {
                            end = 0;
                        }
                    }

                    this.notify();
                }
            } catch (SerialPortException e) {
                logger.error("Error while handling serial event.", e);
            }
        }
    }
}
