/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.spi;

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

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetConfigurationValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspVersionRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspVersionResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.EzspFrameHandler;
import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 * Tests for the low level frame handler to manage the SPI transport layer
 *
 * @author Chris Jackson
 *
 */
public class SpiFrameHandlerTest {
    private List<Integer> portOutData;

    private ArgumentCaptor<Boolean> stateCaptor;
    private ArgumentCaptor<EzspFrame> responseCaptor;

    private int[] getPacket(int[] data) {
        byte[] bytedata = new byte[data.length];
        int cnt = 0;
        for (int value : data) {
            bytedata[cnt++] = (byte) value;
        }
        ByteArrayInputStream stream = new ByteArrayInputStream(bytedata);
        SpiFrameHandler frameHandler = new SpiFrameHandler(null, new TestPort(stream, null));

        Method privateMethod;
        try {
            privateMethod = SpiFrameHandler.class.getDeclaredMethod("getPacket");
            privateMethod.setAccessible(true);

            return (int[]) privateMethod.invoke(frameHandler);
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void sendEzspFrame(SpiFrameHandler handler, EzspFrameRequest ezspFrame) {
        Method privateMethod;
        try {
            Field field = handler.getClass().getDeclaredField("stateConnected");
            field.setAccessible(true);
            field.set(handler, true);

            privateMethod = SpiFrameHandler.class.getDeclaredMethod("queueFrame", EzspFrameRequest.class);
            privateMethod.setAccessible(true);

            privateMethod.invoke(handler, ezspFrame);
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
                | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void pollCallback(SpiFrameHandler handler) {
        Method privateMethod;
        try {
            Field field = handler.getClass().getDeclaredField("stateConnected");
            field.setAccessible(true);
            field.set(handler, true);

            privateMethod = SpiFrameHandler.class.getDeclaredMethod("pollCallback");
            privateMethod.setAccessible(true);

            privateMethod.invoke(handler);
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
                | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private SpiFrameHandler processSpiCommand(int[] lastFrame, int[] data) {
        EzspFrameHandler receiveHandler = Mockito.mock(EzspFrameHandler.class);
        stateCaptor = ArgumentCaptor.forClass(Boolean.class);
        responseCaptor = ArgumentCaptor.forClass(EzspFrame.class);

        Mockito.doNothing().when(receiveHandler).handleLinkStateChange(stateCaptor.capture());
        Mockito.doNothing().when(receiveHandler).handlePacket(responseCaptor.capture());

        SpiFrameHandler frameHandler = new SpiFrameHandler(receiveHandler, new TestPort(null, null));
        frameHandler.start();

        Method privateMethod;
        try {
            Field field = frameHandler.getClass().getDeclaredField("lastFrameSent");
            field.setAccessible(true);
            field.set(frameHandler, lastFrame);

            privateMethod = SpiFrameHandler.class.getDeclaredMethod("processSpiCommand", int[].class);
            privateMethod.setAccessible(true);

            privateMethod.invoke(frameHandler, data);
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
                | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return frameHandler;
    }

    @Test
    public void testReceivePacket() {
        int[] response = getPacket(new int[] { 0x01, 0x02, 0x03, 0x04, 0xA7 });
        assertNotNull(response);
        assertEquals(4, response.length);
        assertEquals(0x01, response[0]);
    }

    @Test
    public void testReceivePacketMissingTerminator() {
        int[] response = getPacket(new int[] { 0x03, 0x00, 0xA7 });
        assertNotNull(response);
        assertEquals(2, response.length);
        assertEquals(0x03, response[0]);
        assertEquals(0x00, response[1]);
    }

    @Test
    public void testReceivePacketVersion() {
        int[] response = getPacket(new int[] { 0x82, 0xA7 });
        assertNotNull(response);
        assertEquals(1, response.length);
        assertEquals(0x82, response[0]);
    }

    @Test
    public void testReceivePacketNoData() {
        int[] response = getPacket(new int[] { 0xFF, 0xFF, 0xA7, 0x01, 0x02, 0x03, 0x04, 0xA7 });
        assertNotNull(response);
        assertEquals(4, response.length);
        assertEquals(0x01, response[0]);
    }

    @Test
    public void testReceiveWithTerminatorInData() {
        int[] response = getPacket(new int[] { 0xFE, 0x03, 0xA7, 0x00, 0xA7, 0xA7 });
        assertNotNull(response);
        assertEquals(5, response.length);
        assertEquals(0xFE, response[0]);
        assertEquals(0x03, response[1]);
        assertEquals(0xA7, response[2]);
    }

    @Test
    public void testRunning() {
        SpiFrameHandler frameHandler = new SpiFrameHandler(null, null);
        frameHandler.start();

        assertTrue(frameHandler.isAlive());
        frameHandler.close();
        assertFalse(frameHandler.isAlive());
    }

    @Test
    public void testReceiveEzsp() {
        processSpiCommand(new int[] { 0xFE }, new int[] { 0xFE, 0x07, 0x04, 0x80, 0x00, 0x04, 0x02, 0x50, 0x58 });
        System.out.println(responseCaptor.getValue());
        assertTrue(responseCaptor.getValue() instanceof EzspVersionResponse);

        processSpiCommand(new int[] { 0xFE }, new int[] { 0xFE, 0x06, 0x02, 0x80, 0x52, 0x00, 0x08, 0x00 });
        System.out.println(responseCaptor.getValue());
        assertTrue(responseCaptor.getValue() instanceof EzspGetConfigurationValueResponse);
    }

    @Test
    public void testReceiveErrors() {
        SpiFrameHandler handler;

        // Oversize frame
        handler = processSpiCommand(new int[] { 0x00 }, new int[] { 0x01, 0x00 });
        assertEquals(1, handler.getSpiErrors());

        // Missing terminator
        handler = processSpiCommand(new int[] { 0x00 }, new int[] { 0x03, 0x00 });
        assertEquals(1, handler.getSpiErrors());

        // Unsupported command
        handler = processSpiCommand(new int[] { 0x00 }, new int[] { 0x04, 0x00 });
        assertEquals(1, handler.getSpiErrors());

        // Aborted transaction
        handler = processSpiCommand(new int[] { 0x00 }, new int[] { 0x02, 0x00 });
        assertEquals(1, handler.getSpiErrors());

        // Invalid command
        handler = processSpiCommand(new int[] { 0x00 }, new int[] { 0xFF });
        assertEquals(1, handler.getSpiErrors());
    }

    @Test
    public void testReceiveVersion() {
        portOutData = new ArrayList<>();

        // If we process the version, then we send the status request
        processSpiCommand(new int[] { 0x0A }, new int[] { 0x82 });
        assertEquals(2, portOutData.size());
        assertEquals(Integer.valueOf(0x0B), portOutData.get(0));
        assertEquals(Integer.valueOf(0xA7), portOutData.get(1));
    }

    @Test
    public void testReceiveStatus() {
        portOutData = new ArrayList<>();

        // If we process the status and it's not ready, then we send the status request again
        processSpiCommand(new int[] { 0x0B }, new int[] { 0xC0 });
        assertEquals(2, portOutData.size());
        assertEquals(Integer.valueOf(0x0B), portOutData.get(0));
        assertEquals(Integer.valueOf(0xA7), portOutData.get(1));

        // If we process the status and it is ready, then we get the online notification
        processSpiCommand(new int[] { 0x0B }, new int[] { 0xC1 });
        assertTrue(stateCaptor.getValue());
    }

    @Test
    public void testConnect() {
        portOutData = new ArrayList<>();
        SpiFrameHandler handler = new SpiFrameHandler(null, new TestPort(null, null));

        handler.connect();

        // Verify the version command is sent
        assertEquals(2, portOutData.size());
        assertEquals(Integer.valueOf(0x0A), portOutData.get(0));
        assertEquals(Integer.valueOf(0xA7), portOutData.get(1));
    }

    @Test
    public void testSendEzspFrame() {
        portOutData = new ArrayList<>();
        SpiFrameHandler handler = new SpiFrameHandler(null, new TestPort(null, null));

        EzspVersionRequest command = new EzspVersionRequest();
        command.setDesiredProtocolVersion(4);
        command.setSequenceNumber(1);
        sendEzspFrame(handler, command);
        assertEquals(7, portOutData.size());
        assertEquals(Integer.valueOf(0xFE), portOutData.get(0));
        assertEquals(Integer.valueOf(0x04), portOutData.get(1));
        assertEquals(Integer.valueOf(0x01), portOutData.get(2));
        assertEquals(Integer.valueOf(0x00), portOutData.get(3));
        assertEquals(Integer.valueOf(0x00), portOutData.get(4));
        assertEquals(Integer.valueOf(0x04), portOutData.get(5));
        assertEquals(Integer.valueOf(0xA7), portOutData.get(6));
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
            portOutData.add(value);
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
