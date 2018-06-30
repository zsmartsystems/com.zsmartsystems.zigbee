/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ash;

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
import java.util.Map;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspVersionRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ash.AshFrame.FrameType;
import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 *
 * @author Chris Jackson
 *
 */
public class AshFrameHandlerTest {

    private int[] getPacket(int[] data) {
        AshFrameHandler frameHandler = new AshFrameHandler(null, null);
        byte[] bytedata = new byte[data.length];
        int cnt = 0;
        for (int value : data) {
            bytedata[cnt++] = (byte) value;
        }
        ByteArrayInputStream stream = new ByteArrayInputStream(bytedata);
        ZigBeePort port = new TestPort(stream, null);

        Method privateMethod;
        try {
            Field field = frameHandler.getClass().getDeclaredField("port");
            field.setAccessible(true);
            field.set(frameHandler, port);

            privateMethod = AshFrameHandler.class.getDeclaredMethod("getPacket");
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
        AshFrameHandler frameHandler = new AshFrameHandler(null, null);
        frameHandler.start();

        assertTrue(frameHandler.isAlive());
        frameHandler.close();
        assertFalse(frameHandler.isAlive());
    }

    @Test
    public void testReceivePacketx() {
        int[] response = getPacket(
                new int[] { 0x44, 0x0E, 0xA1, 0x57, 0x54, 0x45, 0x15, 0x58, 0x94, 0x6C, 0x1B, 0x6E, 0x35, 0x27, 0xEA,
                        0x61, 0xE0, 0x60, 0x31, 0xFB, 0x04, 0xF3, 0xA7, 0x9D, 0x86, 0x86, 0xB0, 0x3E, 0x29, 0x7E });
        assertNotNull(response);
        assertEquals(29, response.length);
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

    @Test
    public void testAshFrameAck() {
        AshFrameAck frame;
        AshFrame inFrame;

        frame = new AshFrameAck(4);
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 132, 48, 252, 126 }, frame.getOutputBuffer()));

        inFrame = AshFrame.createFromInput(new int[] { 132, 48, 252 });
        assertTrue(inFrame instanceof AshFrameAck);
        assertEquals(4, inFrame.getAckNum());

        frame = new AshFrameAck(7);
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 135, 0, 159, 126 }, frame.getOutputBuffer()));

        inFrame = AshFrame.createFromInput(new int[] { 135, 0, 159 });
        assertTrue(inFrame instanceof AshFrameAck);
        assertEquals(7, inFrame.getAckNum());
    }

    @Test
    public void testAshFrameNak() {
        AshFrameNak frame;
        frame = new AshFrameNak(0);
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 255, 255, 126 }, frame.getOutputBuffer()));
    }

    @Test
    public void testAshFrameRst() {
        AshFrameRst frame;
        frame = new AshFrameRst();
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 26, 192, 56, 188, 126 }, frame.getOutputBuffer()));

        AshFrame inFrame = AshFrame.createFromInput(new int[] { 192, 56, 188 });
        assertTrue(inFrame instanceof AshFrameRst);
    }

    @Test
    public void testAshFrameData() {
        AshFrameData frame;
        EzspVersionRequest request = new EzspVersionRequest();
        request.setSequenceNumber(1);
        request.setDesiredProtocolVersion(4);
        frame = new AshFrameData(request);
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 0, 67, 33, 168, 80, 155, 152, 126 }, frame.getOutputBuffer()));

        request = new EzspVersionRequest();
        request.setSequenceNumber(2);
        request.setDesiredProtocolVersion(4);
        frame = new AshFrameData(request);
        frame.setAckNum(3);
        frame.setFrmNum(4);
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 67, 64, 33, 168, 80, 255, 254, 126 }, frame.getOutputBuffer()));

        request = new EzspVersionRequest();
        request.setSequenceNumber(3);
        request.setDesiredProtocolVersion(4);
        frame = new AshFrameData(request);
        frame.setAckNum(6);
        frame.setFrmNum(2);
        frame.setReTx();
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 46, 65, 33, 168, 80, 177, 236, 126 }, frame.getOutputBuffer()));

        AshFrame inFrame = AshFrame.createFromInput(new int[] { 46, 65, 33, 168, 80, 177, 236 });
        assertTrue(inFrame instanceof AshFrameData);
        assertEquals(6, inFrame.getAckNum());
        assertEquals(2, inFrame.getFrmNum());
        assertEquals(FrameType.DATA, inFrame.getFrameType());
    }

    @Test
    public void testGetCounters() {
        AshFrameHandler frameHandler = new AshFrameHandler(null, null);

        assertNotNull(frameHandler.getCounters());

        Map<String, Long> counters = frameHandler.getCounters();

        assertEquals(Long.valueOf(0), counters.get("ASH_RX_DAT"));
        assertEquals(Long.valueOf(0), counters.get("ASH_TX_DAT"));
        assertEquals(Long.valueOf(0), counters.get("ASH_RX_ACK"));
        assertEquals(Long.valueOf(0), counters.get("ASH_TX_ACK"));
        assertEquals(Long.valueOf(0), counters.get("ASH_RX_NAK"));
        assertEquals(Long.valueOf(0), counters.get("ASH_TX_NAK"));
        assertEquals(Long.valueOf(0), counters.get("ASH_RX_ERR"));
    }
}
