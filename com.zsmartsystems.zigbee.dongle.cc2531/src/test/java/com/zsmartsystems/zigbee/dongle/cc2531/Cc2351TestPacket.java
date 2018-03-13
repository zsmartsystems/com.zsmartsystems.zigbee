/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.cc2531;

import static org.junit.Assert.assertFalse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacketStream;
import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 *
 * @author Chris Jackson
 *
 */
public class Cc2351TestPacket {
    protected int[] getPacketData(String stringData) {
        String split[] = stringData.split(" ");

        int[] response = new int[split.length];
        int cnt = 0;
        for (String val : split) {
            response[cnt++] = Integer.parseInt(val, 16);
        }

        return response;
    }

    protected ZToolPacket getPacket(String stringData) {
        int[] packet = getPacketData(stringData);

        byte[] byteArray = new byte[packet.length - 1];
        for (int c = 1; c < packet.length; c++) {
            byteArray[c - 1] = (byte) packet[c];
        }

        ByteArrayInputStream stream = new ByteArrayInputStream(byteArray);
        ZigBeePort port = new TestPort(stream, null);

        try {
            ZToolPacket ztoolPacket = new ZToolPacketStream(port).parsePacket();

            assertFalse(ztoolPacket.isError());

            return ztoolPacket;
        } catch (IOException e) {
            return null;
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
