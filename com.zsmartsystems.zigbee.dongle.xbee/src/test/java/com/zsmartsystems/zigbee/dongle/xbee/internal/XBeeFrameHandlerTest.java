/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 *
 * @author Chris Jackson
 *
 */
public class XBeeFrameHandlerTest {
    private int[] getPacket(String packet) {
        String split[] = packet.split(" ");

        byte[] response = new byte[split.length];
        int cnt = 0;
        for (String val : split) {
            response[cnt++] = (byte) Integer.parseInt(val, 16);
        }

        XBeeFrameHandler frameHandler = new XBeeFrameHandler();
        ByteArrayInputStream stream = new ByteArrayInputStream(response);

        ZigBeePort port = new TestPort(stream, null);

        Method privateMethod;
        try {
            Field field = frameHandler.getClass().getDeclaredField("serialPort");
            field.setAccessible(true);
            field.set(frameHandler, port);

            privateMethod = XBeeFrameHandler.class.getDeclaredMethod("getPacket");
            privateMethod.setAccessible(true);

            return (int[]) privateMethod.invoke(frameHandler);
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
                | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Test
    public void testReceivePacket() {
        int[] response = getPacket("7E 00 02 8A 06 6F");
        assertNotNull(response);
        assertEquals(5, response.length);
        assertEquals(0x8A, response[2]);
    }

    @Test
    public void testReceivePacketBadChecksum() {
        int[] response = getPacket("7E 00 02 8A 06 6E");
        assertNull(response);
    }

    @Test
    public void testReceivePacketWithPreamble() {
        int[] response = getPacket("00 11 22 7E 00 02 8A 06 6F");
        assertNotNull(response);
        assertEquals(5, response.length);
        assertEquals(0x8A, response[2]);
    }

    @Test
    public void testReceivePacketTooLong() {
        int[] response = getPacket("7E FF 00 8A 06 6F");
        assertNull(response);
    }

    @Test
    public void testReceivePacketEscaped() {
        int[] response = getPacket("7E 00 02 23 7D 31 CB");
        assertNotNull(response);
        assertEquals(5, response.length);
        assertEquals(0x23, response[2]);
        assertEquals(0x11, response[3]);
    }

    class TestPort implements ZigBeePort {
        InputStream input;
        OutputStream output;

        TestPort(InputStream input, OutputStream output) {
            this.input = input;
            this.output = output;
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
    }
}
