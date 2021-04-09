/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.serial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.transport.ZigBeePort;
import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The default/reference Java serial port implementation using jSerialComm library
 *
 * @author Ziver Koc
 */
public class ZigBeeSerialPort implements ZigBeePort {
    /**
     * The logger.
     */
    private final static Logger logger = LoggerFactory.getLogger(ZigBeeSerialPort.class);

    /**
     * The default baud rate.
     */
    public static final int DEFAULT_BAUD_RATE = 38400;

    /**
     * The default flow control.
     */
    public static final FlowControl DEFAULT_FLOW_CONTROL = FlowControl.FLOWCONTROL_OUT_NONE;

    private final String portName;
    private final int baudRate;
    private final FlowControl flowControl;
    private SerialPort serialPort;
    private InputStream serialInputStream;
    private OutputStream serialOutputStream;


    /**
     * Constructor which sets port name to given value and baud rate to default.
     *
     * @param portName the port name
     */
    public ZigBeeSerialPort(String portName) {
        this(portName, DEFAULT_BAUD_RATE);
    }

    /**
     * Constructor setting port name and baud rate.
     *
     * @param portName the port name
     * @param baudRate the default baud rate
     */
    public ZigBeeSerialPort(String portName, int baudRate) {
        this(portName, baudRate, DEFAULT_FLOW_CONTROL);
    }

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
        return open(baudRate, flowControl);
    }

    @Override
    public boolean open(int baudRate, FlowControl flowControl) {
        try {
            openSerialPort(portName, baudRate, flowControl);

            return true;
        } catch (Exception e) {
            logger.error("Unable to open serial port: " + e.getMessage());
            close();
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

        serialPort = SerialPort.getCommPort(portName);
        serialPort.setBaudRate(baudRate);
        serialPort.setComPortTimeouts(
                SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);

        if (!serialPort.openPort()) {
            throw new RuntimeException("Error opening serial port: " + portName);
        }

        serialInputStream = serialPort.getInputStream();
        serialOutputStream = serialPort.getOutputStream();

        switch (flowControl) {
            case FLOWCONTROL_OUT_NONE:
                serialPort.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);
                break;
            case FLOWCONTROL_OUT_RTSCTS:
                serialPort.setFlowControl(
                        SerialPort.FLOW_CONTROL_RTS_ENABLED | SerialPort.FLOW_CONTROL_CTS_ENABLED);
                break;
            case FLOWCONTROL_OUT_XONOFF:
                serialPort.setFlowControl(
                        SerialPort.FLOW_CONTROL_XONXOFF_IN_ENABLED | SerialPort.FLOW_CONTROL_XONXOFF_OUT_ENABLED);
                break;
            default:
                break;
        }
    }

    @Override
    public void close() {
        if (serialPort == null)
            return;

        try {
            logger.info("Closing Serial port: '" + portName + "'");
            purgeRxBuffer();
            serialPort.closePort();

            serialInputStream = null;
            serialOutputStream = null;
            serialPort = null;
        } catch (Exception e) {
            logger.warn("Error closing portName portName: '" + portName + "'", e);
        }
    }

    @Override
    public void write(int value) {
        if (serialOutputStream == null)
            throw new RuntimeException("Unable to write, Serial port is not open.");

        try {
            serialOutputStream.write(value);
        } catch (IOException e) {
            logger.error("Was unable to write to serial port.", e);
        }
    }

    @Override
    public int read() {
        return read(0);
    }

    @Override
    public int read(int timeout) {
        if (serialInputStream == null)
            throw new RuntimeException("Unable to read, Serial port is not open.");

        try {
            if (serialPort.getReadTimeout() != timeout)
                serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, timeout, 0);

            return serialInputStream.read();
        } catch (IOException e) {
            logger.error("Was unable to read from serial port.", e);
        }
        return -1;
    }

    @Override
    public void purgeRxBuffer() {
        if (serialOutputStream == null)
            return;

        try {
            serialOutputStream.flush();
        } catch (IOException e) {
            logger.error("Was unable to flush serial data.", e);
        }
    }

    public boolean setDtr(boolean state) {
        if (state)
            return serialPort.setDTR();
        else
            return serialPort.clearDTR();
    }

    public boolean setRts(boolean state) {
        if (state)
            return serialPort.setRTS();
        else
            return serialPort.clearRTS();
    }
}