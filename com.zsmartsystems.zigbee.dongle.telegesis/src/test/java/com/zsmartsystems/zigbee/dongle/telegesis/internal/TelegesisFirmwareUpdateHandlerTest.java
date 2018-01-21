/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisFirmwareUpdateHandlerTest {
    private final int CR = '\n';
    private final String sACK = "\u0006";
    private final String sNAK = "\u0015";

    private final int SOH = 0x01;
    private final int EOT = 0x04;
    private final int ACK = 0x06;
    private final int NAK = 0x15;
    private final int CAN = 0x18;

    private boolean getBlPrompt(String packet) {
        ByteArrayInputStream stream = new ByteArrayInputStream(packet.getBytes());
        ZigBeePort port = new TestPort(stream);
        TelegesisFirmwareUpdateHandler firmwareHandler = new TelegesisFirmwareUpdateHandler(null, null, port, null);

        Method privateMethod;
        try {
            privateMethod = TelegesisFirmwareUpdateHandler.class.getDeclaredMethod("getBlPrompt");
            privateMethod.setAccessible(true);

            return (boolean) privateMethod.invoke(firmwareHandler);
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }

        return false;
    }

    private int getTransferResponse(String packet) {
        ByteArrayInputStream stream = new ByteArrayInputStream(packet.getBytes());
        ZigBeePort port = new TestPort(stream);
        TelegesisFirmwareUpdateHandler firmwareHandler = new TelegesisFirmwareUpdateHandler(null, null, port, null);

        Method privateMethod;
        try {
            privateMethod = TelegesisFirmwareUpdateHandler.class.getDeclaredMethod("getTransferResponse");
            privateMethod.setAccessible(true);

            return (int) privateMethod.invoke(firmwareHandler);
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Test
    public void testGetBlPrompt() {
        boolean response = getBlPrompt(CR + "BL >");
        assertEquals(true, response);
    }

    @Test
    public void testGetTransferResponse() {
        int response = getTransferResponse(sNAK);
        assertEquals(NAK, response);
        response = getTransferResponse(sACK);
        assertEquals(ACK, response);
    }

    @Test
    public void testTransfer() {
        File file = new File("./src/test/resource/xmodem.txt");
        InputStream firmwareStream;
        try {
            firmwareStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            firmwareStream = null;
        }

        assertNotNull(firmwareStream);
        ByteArrayInputStream inStream = new ByteArrayInputStream(new byte[] { ACK, ACK, ACK, ACK });

        TestPort port = new TestPort(inStream);
        TelegesisFirmwareUpdateHandler firmwareHandler = new TelegesisFirmwareUpdateHandler(null, firmwareStream, port,
                null);

        boolean returnVal = false;
        Method privateMethod;
        try {
            privateMethod = TelegesisFirmwareUpdateHandler.class.getDeclaredMethod("transferFile");
            privateMethod.setAccessible(true);

            returnVal = (boolean) privateMethod.invoke(firmwareHandler);
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
            returnVal = false;
        }

        assertTrue(returnVal);
        byte[] sentData = port.getOutput();

        // Expecting 3 packets, plus the EOT
        assertEquals(133 * 3 + 1, sentData.length);
        assertEquals(SOH, sentData[0]);
        assertEquals(1, sentData[1]);
        assertEquals(254, sentData[2] & 0xff);
        assertEquals(113, sentData[131]);
        assertEquals(15, sentData[132]);
        assertEquals(SOH, sentData[133]);
        assertEquals(2, sentData[134]);
        assertEquals(253, sentData[135] & 0xff);
        assertEquals(113, sentData[264]);
        assertEquals(15, sentData[265]);
        assertEquals(SOH, sentData[266]);
        assertEquals(3, sentData[267]);
        assertEquals(252, sentData[268] & 0xff);
        assertEquals(EOT, sentData[399]);
    }

    class TestPort implements ZigBeePort {
        InputStream input;
        byte[] output = new byte[1024];
        int cnt = 0;

        TestPort(InputStream input) {
            this.input = input;
        }

        @Override
        public boolean open() {
            return true;
        }

        @Override
        public void close() {
        }

        @Override
        public void write(int value) {
            output[cnt++] = (byte) value;
        }

        @Override
        public int read(int timeout) {
            return read();
        }

        @Override
        public int read() {
            try {
                return input.read();
            } catch (IOException e) {
                return -1;
            }
        }

        @Override
        public boolean open(int baudRate) {
            return false;
        }

        @Override
        public boolean open(int baudRate, FlowControl flowControl) {
            return false;
        }

        @Override
        public void purgeRxBuffer() {
        }

        public byte[] getOutput() {
            return Arrays.copyOfRange(output, 0, cnt);
        }
    }
}
