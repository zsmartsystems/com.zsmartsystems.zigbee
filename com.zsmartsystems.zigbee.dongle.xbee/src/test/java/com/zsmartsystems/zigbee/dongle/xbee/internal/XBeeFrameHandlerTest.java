/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Ignore;
import org.junit.Test;

import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 *
 * @author Chris Jackson
 *
 */
public class XBeeFrameHandlerTest {
    private String CR = "\r";
    private String LF = "\n";
    private String CRLF = "\r\n";

    private int[] getPacket(String packet) {
        XBeeFrameHandler frameHandler = new XBeeFrameHandler();
        ByteArrayInputStream stream = new ByteArrayInputStream(packet.getBytes());

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

    @Ignore
    @Test
    public void testReceivePacket_LongData() {
        int[] response = getPacket("BCAST:000D6F000005A666,12=123456789012345678" + CRLF);
        assertNotNull(response);
        assertEquals(44, response.length);
        assertEquals('B', response[0]);
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
