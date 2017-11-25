/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ash.v3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrame;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrame.FrameType;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameAck;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameData;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameHandlerTest;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameNak;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameRst;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameRstAck;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspVersionRequest;
import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 *
 * @author Chris Jackson
 *
 */
public class AshFrameHandlerV3Test extends AshFrameHandlerTest {

    @Test
    public void testReceivePacket() {
        int[] response = getPacket(new AshFrameHandlerV3(null), new int[] { 0x11, 0x7E, 0x01, 0x02, 0x03, 0x04, 0x7E });
        assertNotNull(response);
        assertEquals(5, response.length);
        assertEquals(0x7E, response[0]);
        assertEquals(0x01, response[1]);
    }

    @Test
    public void testReceivePacketFlagStart() {
        int[] response = getPacket(new AshFrameHandlerV3(null), new int[] { 0x7E, 0x01, 0x02, 0x03, 0x04, 0x7E });
        assertNotNull(response);
        assertEquals(5, response.length);
        assertEquals(0x7E, response[0]);
        assertEquals(0x01, response[1]);
    }

    @Test
    public void testReceivePacketIgnoreXonXoff() {
        int[] response = getPacket(new AshFrameHandlerV3(null),
                new int[] { 0x7E, 0x01, 0x02, 0x11, 0x03, 0x13, 0x04, 0x7E });
        assertNotNull(response);
        assertEquals(5, response.length);
        assertEquals(0x7E, response[0]);
        assertEquals(0x01, response[1]);
    }

    @Test
    public void testRunning() {
        AshFrameHandler frameHandler = new AshFrameHandlerV3(null);
        frameHandler.start(null);

        assertTrue(frameHandler.isAlive());
        frameHandler.close();
        assertFalse(frameHandler.isAlive());
    }

    @Test
    public void testAshFrameAck() {
        AshFrameHandler handler = new AshFrameHandlerV3(null);
        AshFrameAck frame;
        AshFrame inFrame;

        frame = new AshFrameAck();
        frame.setAckNum(4);
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 126, 0, 132, 0, 39, 99, 192 }, getOutputBuffer(handler, frame)));

        inFrame = createAshFrame(handler, new int[] { 126, 0, 132, 0, 39, 99, 192 });
        assertTrue(inFrame instanceof AshFrameAck);
        assertEquals(4, inFrame.getAckNum());

        frame = new AshFrameAck();
        frame.setAckNum(7);
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 126, 0, 135, 0, 98, 32, 0 }, getOutputBuffer(handler, frame)));

        inFrame = createAshFrame(handler, new int[] { 126, 0, 135, 0, 98, 32, 0 });
        assertTrue(inFrame instanceof AshFrameAck);
        assertEquals(7, inFrame.getAckNum());
    }

    @Test
    public void testAshFrameNak() {
        AshFrameHandler handler = new AshFrameHandlerV3(null);
        AshFrameNak frame;
        AshFrame inFrame;

        frame = new AshFrameNak();
        frame.setAckNum(0);
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 126, 0, 192, 0, 230, 107, 192 }, getOutputBuffer(handler, frame)));

        inFrame = createAshFrame(handler, new int[] { 126, 0, 192, 0, 230, 107, 192 });
        assertTrue(inFrame instanceof AshFrameNak);
        assertEquals(0, inFrame.getAckNum());
    }

    @Test
    public void testAshFrameRst() {
        AshFrameHandler handler = new AshFrameHandlerV3(null);
        AshFrameRst frame;
        frame = new AshFrameRst();
        System.out.println(frame);
        assertTrue(
                Arrays.equals(new int[] { 0x7E, 0x00, 0x08, 0x00, 0x69, 0x86, 0x00 }, getOutputBuffer(handler, frame)));

        AshFrame inFrame = createAshFrame(handler, new int[] { 0x7E, 0x00, 0x08, 0x00, 0x69, 0x86, 0x00 });
        assertTrue(inFrame instanceof AshFrameRst);
    }

    @Test
    public void testAshFrameRstAck() {
        AshFrameHandler handler = new AshFrameHandlerV3(null);
        AshFrameRstAck frame;
        frame = new AshFrameRstAck();
        System.out.println(frame);
        assertTrue(
                Arrays.equals(new int[] { 0x7E, 0x00, 0x49, 0x00, 0x47, 0x6B, 0xC0 }, getOutputBuffer(handler, frame)));

        AshFrame inFrame = createAshFrame(handler, new int[] { 0x7E, 0x00, 0x49, 0x00, 0x47, 0x6B, 0xC0 });
        assertTrue(inFrame instanceof AshFrameRstAck);
    }

    @Test
    public void testAshFrameData() {
        AshFrameHandler handler = new AshFrameHandlerV3(null);
        AshFrameData frame;
        EzspVersionRequest request = new EzspVersionRequest();
        request.setSequenceNumber(1);
        request.setDesiredProtocolVersion(4);
        frame = new AshFrameData();
        frame.setData(request);
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 126, 0, 128, 4, 1, 0, 0, 4, 69, 11, 0 }, getOutputBuffer(handler, frame)));

        request = new EzspVersionRequest();
        request.setSequenceNumber(2);
        request.setDesiredProtocolVersion(4);
        frame = new AshFrameData();
        frame.setData(request);
        frame.setAckNum(3);
        frame.setFrmNum(4);
        System.out.println(frame);
        assertTrue(
                Arrays.equals(new int[] { 126, 0, 163, 4, 2, 0, 0, 4, 37, 47, 64 }, getOutputBuffer(handler, frame)));

        AshFrame inFrame = createAshFrame(handler, new int[] { 126, 0, 150, 4, 3, 0, 0, 4, 47, 6, 128 });
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
        public boolean open(int baudRate, FlowControl flowControl) {
            return false;
        }

        @Override
        public void purgeRxBuffer() {
        }
    }
}
