/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
import java.util.Map;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspVersionRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspVersionResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.EzspFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.transaction.EzspSingleResponseTransaction;
import com.zsmartsystems.zigbee.dongle.ember.internal.transaction.EzspTransaction;
import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 *
 * @author Chris Jackson
 *
 */
public class AshFrameHandlerTest {
    private static int TIMEOUT = 5000;

    private int[] getPacket(int[] data) {
        AshFrameHandler frameHandler = new AshFrameHandler(null);
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
        EzspFrameHandler ezspHandler = Mockito.mock(EzspFrameHandler.class);
        AshFrameHandler frameHandler = new AshFrameHandler(ezspHandler);
        frameHandler.start(Mockito.mock(ZigBeePort.class));

        assertTrue(frameHandler.isAlive());
        frameHandler.close();
        assertFalse(frameHandler.isAlive());
        Mockito.verify(ezspHandler, Mockito.times(1)).handleLinkStateChange(false);

        // No further transmissions are allowed
        frameHandler.queueFrame(Mockito.mock(EzspFrameRequest.class));
        assertNull(frameHandler.sendEzspRequestAsync(Mockito.mock(EzspTransaction.class)));
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

        public List<Integer> getOutputData() {
            return outputData;
        }
    }

    @Test
    public void testGetCounters() {
        AshFrameHandler frameHandler = new AshFrameHandler(null);

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

    @Test
    public void testTimeout() throws NoSuchFieldException, SecurityException, Exception {
        ZigBeePort port = new TestPort(null, null);

        EzspFrameHandler ezspHandler = Mockito.mock(EzspFrameHandler.class);
        AshFrameHandler frameHandler = new AshFrameHandler(ezspHandler);

        frameHandler.start(port);

        TestUtilities.setField(AshFrameHandler.class, frameHandler, "T_RX_ACK_MIN", 0);
        TestUtilities.setField(AshFrameHandler.class, frameHandler, "T_RX_ACK_INIT", 0);
        TestUtilities.setField(AshFrameHandler.class, frameHandler, "T_RX_ACK_MAX", 0);
        TestUtilities.setField(AshFrameHandler.class, frameHandler, "receiveTimeout", 0);
        TestUtilities.setField(AshFrameHandler.class, frameHandler, "stateConnected", true);

        ArgumentCaptor<Boolean> stateCapture = ArgumentCaptor.forClass(Boolean.class);

        EzspVersionRequest request = new EzspVersionRequest();
        request.setSequenceNumber(3);
        request.setDesiredProtocolVersion(4);

        EzspTransaction<EzspVersionResponse> transaction = frameHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction<>(request, EzspVersionResponse.class));

        assertNull(transaction.getResponse());

        Mockito.verify(ezspHandler, Mockito.timeout(TIMEOUT).times(1)).handleLinkStateChange(stateCapture.capture());
        assertFalse(stateCapture.getValue());
    }

    @Test
    public void testErrorToOffline() {
        int[] data = new int[] { 0xC2, 0x02, 0x52, 0x98, 0xDE, 0x7E, 0xC1, 0x02, 0x0B, 0x0A, 0x52, 0x7E, 0xC2, 0x02,
                0x52, 0x98, 0xDE, 0x7E };

        byte[] bytedata = new byte[data.length];
        int cnt = 0;
        for (int value : data) {
            bytedata[cnt++] = (byte) value;
        }
        ByteArrayInputStream stream = new ByteArrayInputStream(bytedata);
        ZigBeePort port = new TestPort(stream, null);
        ArgumentCaptor<Boolean> stateCapture = ArgumentCaptor.forClass(Boolean.class);

        EzspFrameHandler ezspHandler = Mockito.mock(EzspFrameHandler.class);
        AshFrameHandler frameHandler = new AshFrameHandler(ezspHandler);

        frameHandler.start(port);
        frameHandler.connect();
        Mockito.verify(ezspHandler, Mockito.timeout(TIMEOUT).times(1)).handleLinkStateChange(stateCapture.capture());
    }

    @Test
    public void handleReset() throws Exception {
        // PoR reset is ignored
        // Software Reset V1 ignored
        // Software Reset V2 goes online and state to connected
        // PoR reset while connected goes offline
        int[] dataCorrupt = new int[] { 0xC1, 0x02, 0x02, 0x9B, 0x7C, 0x7E };
        int[] dataPOR = new int[] { 0xC1, 0x02, 0x02, 0x9B, 0x7B, 0x7E };
        int[] dataSWV1 = new int[] { 0xC1, 0x01, 0x0B, 0x5F, 0x01, 0x7E };
        int[] dataSWV2 = new int[] { 0xC1, 0x02, 0x0B, 0x0A, 0x52, 0x7E };

        byte[] bytedata = new byte[dataPOR.length * 5];
        int cnt = 0;
        for (int value : dataCorrupt) {
            bytedata[cnt++] = (byte) value;
        }
        for (int value : dataPOR) {
            bytedata[cnt++] = (byte) value;
        }
        for (int value : dataSWV1) {
            bytedata[cnt++] = (byte) value;
        }
        for (int value : dataSWV2) {
            bytedata[cnt++] = (byte) value;
        }
        for (int value : dataPOR) {
            bytedata[cnt++] = (byte) value;
        }
        ByteArrayInputStream stream = new ByteArrayInputStream(bytedata);
        ZigBeePort port = new TestPort(stream, null);
        ArgumentCaptor<Boolean> stateCapture = ArgumentCaptor.forClass(Boolean.class);

        EzspFrameHandler ezspHandler = Mockito.mock(EzspFrameHandler.class);
        AshFrameHandler frameHandler = new AshFrameHandler(ezspHandler);

        frameHandler.start(port);

        Mockito.verify(ezspHandler, Mockito.timeout(TIMEOUT)).handleLinkStateChange(stateCapture.capture());
    }
}
