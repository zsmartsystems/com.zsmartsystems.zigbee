/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.aps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.transport.ZigBeeTransportProgressState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;

/**
 *
 * @author Chris Jackson
 *
 */
public class ApsDataEntityTest {
    public static final int FRAGMENTATION_LENGTH = 65;
    private static int TIMEOUT = 5000;

    @Test
    public void duplicateRemoval() {
        ApsDataEntity aps = new ApsDataEntity(Mockito.mock(ZigBeeTransportTransmit.class));
        aps.setDuplicateTimeWindow(Long.MAX_VALUE);

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        ZigBeeApsFrame response;
        apsFrame.setApsCounter(1);
        apsFrame.setAckRequest(true);
        apsFrame.setSourceAddress(1);

        response = aps.receive(apsFrame);
        assertEquals(apsFrame, response);

        // Duplicate fails
        response = aps.receive(apsFrame);
        assertNull(response);

        // Non-duplicate within time window passes
        apsFrame.setApsCounter(2);
        response = aps.receive(apsFrame);
        assertEquals(apsFrame, response);

        // -1 counter value is allowed - indicates no counter available and will always pass
        apsFrame.setApsCounter(-1);
        response = aps.receive(apsFrame);
        assertEquals(apsFrame, response);
        response = aps.receive(apsFrame);
        assertEquals(apsFrame, response);
    }

    @Test
    public void sendUnfragmented() {
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        ApsDataEntity aps = new ApsDataEntity(transport);

        aps.setFragmentationWindow(2);

        ArgumentCaptor<ZigBeeApsFrame> mockedFrameCaptor = ArgumentCaptor.forClass(ZigBeeApsFrame.class);

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setApsCounter(1);
        apsFrame.setAckRequest(true);
        apsFrame.setPayload(createData(0, 30));

        aps.send(0, apsFrame);

        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(1)).sendCommand(ArgumentMatchers.anyInt(),
                mockedFrameCaptor.capture());
        assertTrue(mockedFrameCaptor.getValue() instanceof ZigBeeApsFrame);
    }

    @Test
    public void sendFragments() {
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        ApsDataEntity aps = new ApsDataEntity(transport);

        aps.setFragmentationWindow(2);
        aps.setFragmentationLength(65);

        ArgumentCaptor<ZigBeeApsFrame> mockedFrameCaptor = ArgumentCaptor.forClass(ZigBeeApsFrame.class);
        Mockito.doNothing().when(transport).sendCommand(ArgumentMatchers.anyInt(), mockedFrameCaptor.capture());

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setApsCounter(1);
        apsFrame.setAckRequest(true);
        apsFrame.setPayload(createData(0, 150));

        aps.send(0, apsFrame);

        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(2)).sendCommand(ArgumentMatchers.anyInt(),
                ArgumentMatchers.any(ZigBeeApsFrame.class));

        assertFalse(aps.receiveCommandState(0, ZigBeeTransportProgressState.RX_ACK));
        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(3)).sendCommand(ArgumentMatchers.anyInt(),
                ArgumentMatchers.any(ZigBeeApsFrame.class));

        assertFalse(aps.receiveCommandState(0, ZigBeeTransportProgressState.RX_ACK));
        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(3)).sendCommand(ArgumentMatchers.anyInt(),
                ArgumentMatchers.any(ZigBeeApsFrame.class));

        assertTrue(aps.receiveCommandState(0, ZigBeeTransportProgressState.RX_ACK));
        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(3)).sendCommand(ArgumentMatchers.anyInt(),
                ArgumentMatchers.any(ZigBeeApsFrame.class));

        assertTrue(mockedFrameCaptor.getAllValues().get(0) instanceof ZigBeeApsFrameFragment);
        assertTrue(mockedFrameCaptor.getAllValues().get(1) instanceof ZigBeeApsFrameFragment);
        assertTrue(mockedFrameCaptor.getAllValues().get(2) instanceof ZigBeeApsFrameFragment);

        ZigBeeApsFrameFragment fragment0 = (ZigBeeApsFrameFragment) mockedFrameCaptor.getAllValues().get(0);
        ZigBeeApsFrameFragment fragment1 = (ZigBeeApsFrameFragment) mockedFrameCaptor.getAllValues().get(1);
        ZigBeeApsFrameFragment fragment2 = (ZigBeeApsFrameFragment) mockedFrameCaptor.getAllValues().get(2);

        System.out.println(fragment0);
        System.out.println(fragment1);
        System.out.println(fragment2);

        assertEquals(0x00, fragment0.getPayload()[0]);
        assertEquals(0x41, fragment1.getPayload()[0]);
        assertEquals(0x82, fragment2.getPayload()[0]);

        assertEquals(65, fragment0.getPayload().length);
        assertEquals(65, fragment1.getPayload().length);
        assertEquals(20, fragment2.getPayload().length);
    }

    @Test
    public void sendFragmentsFail() {
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        ApsDataEntity aps = new ApsDataEntity(transport);

        aps.setFragmentationWindow(2);
        aps.setFragmentationLength(65);

        ArgumentCaptor<ZigBeeApsFrame> mockedFrameCaptor = ArgumentCaptor.forClass(ZigBeeApsFrame.class);
        Mockito.doNothing().when(transport).sendCommand(ArgumentMatchers.anyInt(), mockedFrameCaptor.capture());

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setApsCounter(1);
        apsFrame.setAckRequest(true);
        apsFrame.setPayload(createData(0, 150));

        aps.send(0, apsFrame);

        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(2)).sendCommand(ArgumentMatchers.anyInt(),
                ArgumentMatchers.any(ZigBeeApsFrame.class));

        assertFalse(aps.receiveCommandState(0, ZigBeeTransportProgressState.RX_ACK));
        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(3)).sendCommand(ArgumentMatchers.anyInt(),
                ArgumentMatchers.any(ZigBeeApsFrame.class));

        assertTrue(aps.receiveCommandState(0, ZigBeeTransportProgressState.RX_NAK));
        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(3)).sendCommand(ArgumentMatchers.anyInt(),
                ArgumentMatchers.any(ZigBeeApsFrame.class));
    }

    @Test
    public void receiveFragments() {
        ApsDataEntity aps = new ApsDataEntity(Mockito.mock(ZigBeeTransportTransmit.class));
        aps.setDuplicateTimeWindow(Long.MIN_VALUE);

        ZigBeeApsFrameFragment fragment;

        fragment = new ZigBeeApsFrameFragment(0);
        fragment.setApsCounter(1);
        fragment.setSourceAddress(1);
        fragment.setFragmentTotal(3);
        fragment.setPayload(createData(0, 64));
        assertNull(aps.receive(fragment));

        fragment = new ZigBeeApsFrameFragment(1);
        fragment.setApsCounter(1);
        fragment.setSourceAddress(1);
        fragment.setPayload(createData(64, 64));
        assertNull(aps.receive(fragment));

        fragment = new ZigBeeApsFrameFragment(2);
        fragment.setApsCounter(1);
        fragment.setSourceAddress(1);
        fragment.setPayload(createData(128, 12));
        ZigBeeApsFrame apsFrame = aps.receive(fragment);

        assertNotNull(apsFrame);
        System.out.println(apsFrame);
    }

    @Test
    public void receiveFragmentsFail() {
        ApsDataEntity aps = new ApsDataEntity(Mockito.mock(ZigBeeTransportTransmit.class));
        aps.setDuplicateTimeWindow(Long.MIN_VALUE);

        ZigBeeApsFrameFragment fragment;

        fragment = new ZigBeeApsFrameFragment(0);
        fragment.setApsCounter(1);
        fragment.setSourceAddress(1);
        fragment.setFragmentTotal(3);
        fragment.setPayload(createData(0, 64));
        assertNull(aps.receive(fragment));

        fragment = new ZigBeeApsFrameFragment(2);
        fragment.setApsCounter(1);
        fragment.setSourceAddress(1);
        fragment.setPayload(createData(128, 12));
        assertNull(aps.receive(fragment));

        fragment = new ZigBeeApsFrameFragment(1);
        fragment.setApsCounter(2);
        fragment.setSourceAddress(1);
        fragment.setPayload(createData(0, 64));
        assertNull(aps.receive(fragment));
    }

    @Test
    public void receiveCommandState() {
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        ApsDataEntity aps = new ApsDataEntity(transport);

        assertTrue(aps.receiveCommandState(0, ZigBeeTransportProgressState.RX_ACK));
    }

    @Test
    public void shouldExpectOneFrameReceivedForPayloadShorterThanFragmentationLength() {
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        ApsDataEntity aps = new ApsDataEntity(transport);

        aps.setFragmentationLength(FRAGMENTATION_LENGTH);

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setApsCounter(1);
        apsFrame.setAckRequest(true);
        apsFrame.setPayload(createData(0, FRAGMENTATION_LENGTH-1));

        aps.send(0, apsFrame);

        assertTrue(aps.receiveCommandState(0, ZigBeeTransportProgressState.TX_ACK));
        assertTrue(aps.receiveCommandState(0, ZigBeeTransportProgressState.RX_ACK));
    }

    @Test
    public void shouldExpectOneFrameReceivedForPayloadEqualToFragmentationLength() {
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        ApsDataEntity aps = new ApsDataEntity(transport);

        aps.setFragmentationLength(FRAGMENTATION_LENGTH);

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setApsCounter(1);
        apsFrame.setAckRequest(true);
        apsFrame.setPayload(createData(0, FRAGMENTATION_LENGTH));

        aps.send(0, apsFrame);

        assertTrue(aps.receiveCommandState(0, ZigBeeTransportProgressState.TX_ACK));
        assertTrue(aps.receiveCommandState(0, ZigBeeTransportProgressState.RX_ACK));
    }

    @Test
    public void shouldExpectTwoFramesReceivedForPayloadFittingInTwoFragments() {
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        ApsDataEntity aps = new ApsDataEntity(transport);

        aps.setFragmentationLength(FRAGMENTATION_LENGTH);

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setApsCounter(1);
        apsFrame.setAckRequest(true);
        apsFrame.setPayload(createData(0, FRAGMENTATION_LENGTH+1));

        aps.send(0, apsFrame);

        assertFalse(aps.receiveCommandState(0, ZigBeeTransportProgressState.TX_ACK));
        assertFalse(aps.receiveCommandState(0, ZigBeeTransportProgressState.RX_ACK));

        assertTrue(aps.receiveCommandState(0, ZigBeeTransportProgressState.TX_ACK));
        assertTrue(aps.receiveCommandState(0, ZigBeeTransportProgressState.RX_ACK));
    }

    @Test
    public void shouldExpectThreeFramesReceivedForPayloadFittingInThreeFragments() {
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        ApsDataEntity aps = new ApsDataEntity(transport);

        aps.setFragmentationLength(FRAGMENTATION_LENGTH);

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setApsCounter(1);
        apsFrame.setAckRequest(true);
        apsFrame.setPayload(createData(0, FRAGMENTATION_LENGTH*2+1));

        aps.send(0, apsFrame);

        assertFalse(aps.receiveCommandState(0, ZigBeeTransportProgressState.TX_ACK));
        assertFalse(aps.receiveCommandState(0, ZigBeeTransportProgressState.RX_ACK));

        assertFalse(aps.receiveCommandState(0, ZigBeeTransportProgressState.TX_ACK));
        assertFalse(aps.receiveCommandState(0, ZigBeeTransportProgressState.RX_ACK));

        assertTrue(aps.receiveCommandState(0, ZigBeeTransportProgressState.TX_ACK));
        assertTrue(aps.receiveCommandState(0, ZigBeeTransportProgressState.RX_ACK));
    }

    @Test
    public void shouldExpectTwoFramesTransmittedForPayloadFittingInTwoFragmentsWithNoApsAcks() {
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        ApsDataEntity aps = new ApsDataEntity(transport);

        aps.setFragmentationLength(FRAGMENTATION_LENGTH);

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setApsCounter(1);
        apsFrame.setAckRequest(false);
        apsFrame.setPayload(createData(0, FRAGMENTATION_LENGTH+1));

        aps.send(0, apsFrame);

        assertFalse(aps.receiveCommandState(0, ZigBeeTransportProgressState.TX_ACK));

        assertTrue(aps.receiveCommandState(0, ZigBeeTransportProgressState.TX_ACK));
    }

    @Test
    public void shouldExpectTwoFramesTransmittedForPayloadFittingInTwoFragmentsWithNoApsAcksButAcksReceived() {
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        ApsDataEntity aps = new ApsDataEntity(transport);

        aps.setFragmentationLength(FRAGMENTATION_LENGTH);

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setApsCounter(1);
        apsFrame.setAckRequest(false);
        apsFrame.setPayload(createData(0, FRAGMENTATION_LENGTH+1));

        aps.send(0, apsFrame);

        assertFalse(aps.receiveCommandState(0, ZigBeeTransportProgressState.TX_ACK));

        // A misbehaving receiver or dongle not supporting disabling APS ACK disabling cause us to receive RX ACK
        assertFalse(aps.receiveCommandState(0, ZigBeeTransportProgressState.RX_ACK));

        assertTrue(aps.receiveCommandState(0, ZigBeeTransportProgressState.TX_ACK));
    }

    private int[] createData(int start, int length) {
        int[] data = new int[length];

        for (int cnt = 0; cnt < length; cnt++) {
            data[cnt] = (cnt + start) & 0xFF;
        }

        return data;
    }
}
