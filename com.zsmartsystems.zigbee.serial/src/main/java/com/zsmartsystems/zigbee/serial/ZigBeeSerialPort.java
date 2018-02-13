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
    private final FlowControl flowControl;

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
     * Synchronisation object for buffer queue manipulation
     */
    private Object bufferSynchronisationObject = new Object();

    /**
     * Constructor setting port name and baud rate.
     *
     * @param portName the port name
     * @param baudRate the baud rate
     * @param flowControl to use flow control
     */
    public ZigBeeSerialPort(String portName, int baudRate, FlowControl flowControl) {
        this.portName = portName;
        this.baudRate = baudRate;
        this.flowControl = flowControl;
    }

    @Override
    public boolean open() {
        return open(baudRate);
    }

    @Override
    public boolean open(int baudRate) {
        try {
            openSerialPort(portName, baudRate, flowControl);

            return true;
        } catch (Exception e) {
            logger.warn("Unable to open serial port: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean open(int baudRate, FlowControl flowControl) {
        try {
            openSerialPort(portName, baudRate, flowControl);

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
     * @param flowControl the flow control option
     */
    private void openSerialPort(String portName, int baudRate, FlowControl flowControl) {
        if (serialPort != null) {
            throw new RuntimeException("Serial port already open.");
        }

        logger.debug("Opening port {} at {} baud with {}.", portName, baudRate, flowControl);

        serialPort = new jssc.SerialPort(portName);
        try {
            serialPort.openPort();
            serialPort.setParams(baudRate, 8, 1, 0);
            switch (flowControl) {
                case FLOWCONTROL_OUT_NONE:
                    serialPort.setFlowControlMode(jssc.SerialPort.FLOWCONTROL_NONE);
                    break;
                case FLOWCONTROL_OUT_RTSCTS:
                    serialPort.setFlowControlMode(
                            jssc.SerialPort.FLOWCONTROL_RTSCTS_IN | jssc.SerialPort.FLOWCONTROL_RTSCTS_OUT);
                    break;
                case FLOWCONTROL_OUT_XONOFF:
                    serialPort.setFlowControlMode(jssc.SerialPort.FLOWCONTROL_XONXOFF_OUT);
                    break;
                default:
                    break;
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
                synchronized (bufferSynchronisationObject) {
                    if (start != end) {
                        int value = buffer[start++];
                        if (start >= maxLength) {
                            start = 0;
                        }
                        return value;
                    }
                }

                synchronized (this) {
                    if (serialPort == null) {
                        return -1;
                    }

                    wait(endTime - System.currentTimeMillis());
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

                synchronized (bufferSynchronisationObject) {
                    for (int recv : input) {
                        buffer[end++] = recv;
                        if (end >= maxLength) {
                            end = 0;
                        }
                    }
                }

                synchronized (this) {
                    this.notify();
                }
            } catch (SerialPortException e) {
                logger.error("Error while handling serial event.", e);
            }
        }
    }

    @Override
    public void purgeRxBuffer() {
        synchronized (bufferSynchronisationObject) {
            start = 0;
            end = 0;
        }
    }
}