/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ash.v2;

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
import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrame;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrame.FrameType;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameAck;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameData;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameNak;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameRst;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspVersionRequest;
import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 *
 * @author Chris Jackson
 *
 */
public class AshFrameHandlerV2Test {
    @Test
    public void TestPacket_DataStuffed() {
        int[] buffer = new int[] { 0x7d, 0x3a, 0x43, 0xa1, 0xfa, 0x54, 0x0a, 0x15, 0xc9, 0x89 };

        AshFrameHandlerV2 frameHandler = new AshFrameHandlerV2(null);
        final AshFrame packet = createAshFrame(frameHandler, buffer);
        // final AshFrame packet = AshFrame.createFromInput(buffer);
        assertNotNull(packet);
        assertTrue(packet instanceof AshFrameData);

        AshFrameData dataPacket = (AshFrameData) packet;
        assertEquals(1, dataPacket.getFrmNum());
        assertEquals(2, dataPacket.getAckNum());
        assertEquals(true, dataPacket.getReTx());
    }

    private int[] getPacket(int[] data) {
        AshFrameHandler frameHandler = new AshFrameHandlerV2(null);
        byte[] bytedata = new byte[data.length];
        int cnt = 0;
        for (int value : data) {
            bytedata[cnt++] = (byte) value;
        }
        ByteArrayInputStream stream = new ByteArrayInputStream(bytedata);
        ZigBeePort port = new TestPort(stream, null);

        Method privateMethod;
        try {
            Field field = frameHandler.getClass().getSuperclass().getDeclaredField("port");
            field.setAccessible(true);
            field.set(frameHandler, port);

            privateMethod = AshFrameHandler.class.getDeclaredMethod("getPacket");
            privateMethod.setAccessible(true);

            return (int[]) privateMethod.invoke(frameHandler);
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
                | InvocationTargetException | NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    private AshFrame getAshFrame(AshFrameHandler handler, FrameType frameType) {
        Method privateMethod;
        try {
            privateMethod = AshFrameHandler.class.getDeclaredMethod("getAshFrame", FrameType.class);
            privateMethod.setAccessible(true);

            return (AshFrame) privateMethod.invoke(handler, frameType);
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
                | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    private int[] getOutputBuffer(AshFrameHandler handler, AshFrame frame) {
        Method privateMethod;
        try {
            privateMethod = AshFrameHandler.class.getDeclaredMethod("getOutputBuffer", AshFrame.class);
            privateMethod.setAccessible(true);

            return (int[]) privateMethod.invoke(handler, frame);
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
                | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    private AshFrame createAshFrame(AshFrameHandler handler, int[] data) {
        Method privateMethod;
        try {
            privateMethod = AshFrameHandler.class.getDeclaredMethod("createAshFrame", int[].class);
            privateMethod.setAccessible(true);

            return (AshFrame) privateMethod.invoke(handler, data);
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
                | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Test
    public void testReceivePacket() {
        int[] response = getPacket(new int[] { 0x01, 0x02, 0x03, 0x04, 0x7E });
        assertNotNull(response);
        assertEquals(4, response.length);
        assertEquals(0x01, response[0]);
    }

    @Test
    public void testReceivePacket_FlagStart() {
        int[] response = getPacket(new int[] { 0x7E, 0x01, 0x02, 0x03, 0x04, 0x7E });
        assertNotNull(response);
        assertEquals(4, response.length);
        assertEquals(0x01, response[0]);
    }

    @Test
    public void testReceivePacket_IgnoreXonXoff() {
        int[] response = getPacket(new int[] { 0x7E, 0x01, 0x02, 0x11, 0x03, 0x13, 0x04, 0x7E });
        assertNotNull(response);
        assertEquals(4, response.length);
        assertEquals(0x01, response[0]);
    }

    @Test
    public void testReceivePacket_Cancel() {
        int[] response = getPacket(new int[] { 0x7E, 0x01, 0x02, 0x1A, 0x03, 0x04, 0x05, 0x06, 0x07, 0x7E });
        assertNotNull(response);
        assertEquals(5, response.length);
        assertEquals(0x03, response[0]);
    }

    @Test
    public void testReceivePacket_Substitute() {
        int[] response = getPacket(new int[] { 0x7E, 0x01, 0x02, 0x18, 0x03, 0x04, 0x7E, 0x05, 0x06, 0x07, 0x7E });
        assertNotNull(response);
        assertEquals(3, response.length);
        assertEquals(0x05, response[0]);
    }

    @Test
    public void testRunning() {
        AshFrameHandler frameHandler = new AshFrameHandlerV2(null);
        frameHandler.start(null);

        assertTrue(frameHandler.isAlive());
        frameHandler.close();
        assertFalse(frameHandler.isAlive());
    }

    @Test
    public void testAshFrameAck() {
        AshFrameHandler handler = new AshFrameHandlerV2(null);
        AshFrameAck frame;
        AshFrame inFrame;

        frame = (AshFrameAck) getAshFrame(handler, FrameType.ACK);
        frame.setAckNum(4);
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 132, 48, 252, 126 }, getOutputBuffer(handler, frame)));

        inFrame = createAshFrame(handler, new int[] { 132, 48, 252 });
        assertTrue(inFrame instanceof AshFrameAck);
        assertEquals(4, inFrame.getAckNum());

        frame = (AshFrameAck) getAshFrame(handler, FrameType.ACK);
        frame.setAckNum(7);
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 135, 0, 159, 126 }, getOutputBuffer(handler, frame)));

        inFrame = createAshFrame(handler, new int[] { 135, 0, 159 });
        assertTrue(inFrame instanceof AshFrameAck);
        assertEquals(7, inFrame.getAckNum());
    }

    @Test
    public void testAshFrameNak() {
        AshFrameHandler handler = new AshFrameHandlerV2(null);
        AshFrameNak frame;
        frame = (AshFrameNak) getAshFrame(handler, FrameType.NAK);
        frame.setAckNum(0);
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 255, 255, 126 }, getOutputBuffer(handler, frame)));
    }

    @Test
    public void testAshFrameRst() {
        AshFrameHandler handler = new AshFrameHandlerV2(null);
        AshFrameRst frame;
        frame = (AshFrameRst) getAshFrame(handler, FrameType.RST);
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 26, 192, 56, 188, 126 }, getOutputBuffer(handler, frame)));

        AshFrame inFrame = createAshFrame(handler, new int[] { 192, 56, 188 });
        assertTrue(inFrame instanceof AshFrameRst);
    }

    @Test
    public void testAshFrameData() {
        AshFrameHandler handler = new AshFrameHandlerV2(null);
        AshFrameData frame;
        EzspVersionRequest request = new EzspVersionRequest();
        request.setSequenceNumber(1);
        request.setDesiredProtocolVersion(4);
        frame = (AshFrameData) getAshFrame(handler, FrameType.DATA);
        frame.setData(request);
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 0, 67, 33, 168, 80, 155, 152, 126 }, getOutputBuffer(handler, frame)));

        request = new EzspVersionRequest();
        request.setSequenceNumber(2);
        request.setDesiredProtocolVersion(4);
        frame = (AshFrameData) getAshFrame(handler, FrameType.DATA);
        frame.setData(request);
        frame.setAckNum(3);
        frame.setFrmNum(4);
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 67, 64, 33, 168, 80, 255, 254, 126 }, getOutputBuffer(handler, frame)));

        request = new EzspVersionRequest();
        request.setSequenceNumber(3);
        request.setDesiredProtocolVersion(4);
        frame = (AshFrameData) getAshFrame(handler, FrameType.DATA);
        frame.setData(request);
        frame.setAckNum(6);
        frame.setFrmNum(2);
        frame.setReTx();
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 46, 65, 33, 168, 80, 177, 236, 126 }, getOutputBuffer(handler, frame)));

        AshFrame inFrame = createAshFrame(handler, new int[] { 46, 65, 33, 168, 80, 177, 236 });
        assertTrue(inFrame instanceof AshFrameData);
        assertEquals(6, inFrame.getAckNum());
        assertEquals(2, inFrame.getFrmNum());
        assertEquals(FrameType.DATA, inFrame.getFrameType());
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
        public void purgeRxBuffer() {
        }
    }
}
