/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisNetworkLostEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSleepyDeviceAnnounceEvent;
import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisFrameHandlerTest {
    private String CR = "\r";
    private String LF = "\n";
    private String CRLF = "\r\n";

    private int[] getPacket(String packet) {
        TelegesisFrameHandler frameHandler = new TelegesisFrameHandler();
        ByteArrayInputStream stream = new ByteArrayInputStream(packet.getBytes());

        ZigBeePort port = new TestPort(stream, null);

        Method privateMethod;
        try {
            Field field = frameHandler.getClass().getDeclaredField("serialPort");
            field.setAccessible(true);
            field.set(frameHandler, port);

            privateMethod = TelegesisFrameHandler.class.getDeclaredMethod("getPacket");
            privateMethod.setAccessible(true);

            return (int[]) privateMethod.invoke(frameHandler);
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
                | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Test
    public void testReceivePacket_LongData() {
        int[] response = getPacket("BCAST:000D6F000005A666,12=123456789012345678" + CRLF);
        assertNotNull(response);
        assertEquals(44, response.length);
        assertEquals('B', response[0]);
    }

    @Test
    public void testReceivePacket_CRLF_End() {
        int[] response = getPacket("BCAST:000D6F000005A666,04=test" + CRLF);
        assertNotNull(response);
        assertEquals(30, response.length);
        assertEquals('B', response[0]);
    }

    @Test
    public void testReceivePacket_LF_Start() {
        int[] response = getPacket(LF + "BCAST:000D6F000005A666,04=test" + CRLF);
        assertNotNull(response);
        assertEquals(30, response.length);
        assertEquals('B', response[0]);
    }

    @Test
    public void testReceivePacket_CR_Start() {
        int[] response = getPacket(CR + "BCAST:000D6F000005A666,04=test" + CRLF);
        assertNotNull(response);
        assertEquals(30, response.length);
        assertEquals('B', response[0]);
    }

    @Test
    public void testReceivePacket_Equals() {
        int[] response = getPacket("+N=NOPAN" + CRLF);
        assertNotNull(response);
        assertEquals(8, response.length);
        assertEquals('+', response[0]);
    }

    @Test
    public void testRunning() {
        TelegesisFrameHandler frameHandler = new TelegesisFrameHandler();
        frameHandler.start(null);

        assertTrue(frameHandler.isAlive());
        frameHandler.close();
        assertFalse(frameHandler.isAlive());
    }

    @Test
    public void testReceivePacket_SReg_Binary_Issue() {
        int[] response = getPacket(CR + "ATS0A=0000" + CR + CR + LF + "ERROR:20" + CRLF);
        assertNotNull(response);
        assertEquals(10, response.length);
        assertEquals('A', response[0]);
    }

    @Ignore
    @Test
    public void testEventWait() {
        final TelegesisFrameHandler frameHandler = new TelegesisFrameHandler();

        final List<TelegesisEvent> eventCapture = new ArrayList<TelegesisEvent>();

        Thread waitThread = new Thread() {
            @Override
            public void run() {
                // Send the transaction and wait for the response
                eventCapture.add(frameHandler.eventWait(TelegesisSleepyDeviceAnnounceEvent.class));
                synchronized (eventCapture) {
                    eventCapture.notify();
                }
            }
        };

        waitThread.start();

        TelegesisEvent eventOk = new TelegesisSleepyDeviceAnnounceEvent();
        TelegesisEvent eventNOk = new TelegesisNetworkLostEvent();
        try {
            Method privateMethod;
            try {
                privateMethod = TelegesisFrameHandler.class.getDeclaredMethod("notifyEventReceived",
                        new Class[] { TelegesisEvent.class });
                privateMethod.setAccessible(true);

                privateMethod.invoke(frameHandler, eventNOk);
                privateMethod.invoke(frameHandler, eventOk);
            } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
                    | InvocationTargetException e) {
                e.printStackTrace();
            }

            synchronized (eventCapture) {
                eventCapture.wait(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(1, eventCapture.size());
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
