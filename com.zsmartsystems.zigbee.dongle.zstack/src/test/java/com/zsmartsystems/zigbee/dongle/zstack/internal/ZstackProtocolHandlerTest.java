/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.internal;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 * Tests for {@link ZstackProtocolHandler}
 *
 * @author Chris Jackson
 *
 */
public class ZstackProtocolHandlerTest {
    @Test
    public void testReceivePacket() throws Exception {
        int[] response = getPacket(new int[] { 0xFE, 0x02, 0x61, 0x01, 0x12, 0x34, 0x44 });
        assertTrue(Arrays.equals(response, new int[] { 0x61, 0x01, 0x12, 0x34 }));
    }

    @Test
    public void testReceivePacketLeadingRubbish() throws Exception {
        int[] response = getPacket(new int[] { 0x00, 0x12, 0xFE, 0x02, 0x61, 0x01, 0x12, 0x34, 0x44 });
        assertTrue(Arrays.equals(response, new int[] { 0x61, 0x01, 0x12, 0x34 }));
    }

    private int[] getPacket(int[] data) throws Exception {
        ZstackProtocolHandler handler = new ZstackProtocolHandler();
        byte[] bytedata = new byte[data.length];
        int cnt = 0;
        for (int value : data) {
            bytedata[cnt++] = (byte) value;
        }
        ByteArrayInputStream stream = new ByteArrayInputStream(bytedata);
        ZigBeePort port = new TestPort(stream, null);

        TestUtilities.setField(ZstackProtocolHandler.class, handler, "port", port);
        return (int[]) TestUtilities.invokeMethod(ZstackProtocolHandler.class, handler, "getPacket");
    }

    private class TestPort implements ZigBeePort {
        InputStream input;
        List<Integer> outputData = new ArrayList<>();

        TestPort(InputStream input, OutputStream output) {
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
            outputData.add(value);
        }

        @Override
        public void write(int[] bytes) {
            for (int b: bytes) {
                outputData.add(b);
            }
        }

        @Override
        public int read(int timeout) {
            return read();
        }

        @Override
        public int read() {
            if (input == null) {
                return -1;
            }
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
