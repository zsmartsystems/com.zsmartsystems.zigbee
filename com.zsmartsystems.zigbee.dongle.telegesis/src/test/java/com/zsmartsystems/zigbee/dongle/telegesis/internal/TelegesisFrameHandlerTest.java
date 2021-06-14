/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
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
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.dongle.telegesis.ZigBeeDongleTelegesis;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisNetworkLostEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSetRegisterBitCommand;
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
        TelegesisFrameHandler frameHandler = new TelegesisFrameHandler(null);
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
        TelegesisFrameHandler frameHandler = new TelegesisFrameHandler(null);
        frameHandler.start(null);

        assertTrue(frameHandler.isAlive());
        frameHandler.close();
        assertFalse(frameHandler.isAlive());
    }

    @Ignore
    @Test
    public void testRetriesReset() {
        // Initialise mock
        ZigBeeDongleTelegesis dongle = Mockito.mock(ZigBeeDongleTelegesis.class);
        TelegesisFrameHandler frameHandler = new TelegesisFrameHandler(dongle);
        frameHandler.setCommandTimeout(500);

        // Accept calls and monitor the time, when the call has been made
        ZigBeePort serialPort = Mockito.mock(ZigBeePort.class);

        // Repeat whenever processed[0] == false, only give response if respond[0] == true
        boolean[] processed = new boolean[] { false };
        boolean[] respond = new boolean[] { false };
        int[] call = new int[] { 0 };
        String response = "OK" + CRLF;
        Mockito.when(serialPort.read()).thenAnswer(invocationCall -> {
            if (!respond[0] || processed[0]) {
                return -1;
            }
            processed[0] |= call[0] % response.length() == response.length() - 1;
            return (int) response.getBytes()[call[0]++ % response.length()];
        });

        frameHandler.start(serialPort);

        // Repeat the task four times, with timeouts after every second. Retries should be reset after each round
        for (int i = 0; i < 3; i++) {
            respond[0] = false;
            sendCommandAndWait(frameHandler, processed, respond);
            respond[0] = true;
            sendCommandAndWait(frameHandler, processed, respond);
        }

        Mockito.verify(dongle, Mockito.times(0)).notifyStateUpdate(false);

        assertTrue(frameHandler.isAlive());
        frameHandler.close();
        assertFalse(frameHandler.isAlive());
    }

    @Test
    public void testRetriesExceeded() {
        // Initialise mock
        ZigBeeDongleTelegesis dongle = Mockito.mock(ZigBeeDongleTelegesis.class);
        TelegesisFrameHandler frameHandler = new TelegesisFrameHandler(dongle);
        frameHandler.setCommandTimeout(50);

        // Accept calls and monitor the time, when the call has been made
        ZigBeePort serialPort = Mockito.mock(ZigBeePort.class);

        // Repeat whenever processed[0] == false, delay the response till given time int waitTime[0] has passed
        boolean[] processed = new boolean[] { false };
        Mockito.when(serialPort.read()).thenAnswer(invocationCall -> -1);
        Mockito.doAnswer(mi -> processed[0] = true).when(dongle).notifyStateUpdate(false);

        frameHandler.start(serialPort);

        // Repeat the task four times, with timeouts after every second. Retries should NOT be reset
        for (int i = 0; i < 3; i++) {
            sendCommandAndWait(frameHandler, processed, new boolean[1]);
        }

        Mockito.verify(dongle, Mockito.atLeast(1)).notifyStateUpdate(false);

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
        final TelegesisFrameHandler frameHandler = new TelegesisFrameHandler(null);

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

    /**
     * Helper Method to send a command to the frameHandler and wait, till the getPacket() method may have read it
     *
     * @param frameHandler the frame handler to send the command to
     * @param processed holder for the boolean to check, whether the message is already processed
     * @param resond holder to pause message responses until message is sent
     */
    private void sendCommandAndWait(TelegesisFrameHandler frameHandler, boolean[] processed, boolean[] respond) {
        TelegesisSetRegisterBitCommand command = new TelegesisSetRegisterBitCommand();
        command.setBit(0);
        command.setRegister(0);
        command.setPassword("password");
        command.setState(false);

        // We need to make sure the response is not send before the message is send
        boolean tempResponse = respond[0];
        respond[0] = false;
        processed[0] = false;
        frameHandler.queueFrame(command);
        // Now the response may be send
        respond[0] = tempResponse;
        try {
            Field sentCommandField = TelegesisFrameHandler.class.getDeclaredField("sentCommand");
            sentCommandField.setAccessible(true);
            long time = System.currentTimeMillis();
            while (!processed[0] && sentCommandField.get(frameHandler) != null
                    && System.currentTimeMillis() - time < 2000) {
                Thread.yield();
            }
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
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
        public void write(int[] bytes) {
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
