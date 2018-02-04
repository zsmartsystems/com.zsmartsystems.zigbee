/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.dongle.ember.ZigBeeDongleEzsp;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberIncomingMessageType;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportReceive;

/**
 * @author Chris Jackson
 */
public class EzspIncomingMessageHandlerTest extends EzspFrameTest {

    @Test
    public void testReceive1() {
        EzspFrame.setEzspVersion(4);
        EzspIncomingMessageHandler incomingMessageHandler = new EzspIncomingMessageHandler(
                getPacketData("00 94 45 00 00 01 00 00 00 00 00 00 00 00 58 FF 00 00 00 FF FF 01 00"));

        assertEquals(0x45, incomingMessageHandler.getFrameId());
        assertTrue(incomingMessageHandler.isResponse());
        assertEquals(EmberIncomingMessageType.EMBER_INCOMING_UNICAST, incomingMessageHandler.getType());
    }

    @Test
    public void testReceive2() {
        EzspFrame.setEzspVersion(4);
        // This tests a number of stages - not just this class
        // We process the received frame, make sure the dongle sends it to the networkManager
        EzspIncomingMessageHandler incomingMessageHandler = new EzspIncomingMessageHandler(getPacketData(
                "01 90 45 00 00 00 01 80 00 00 40 00 00 00 EE FF 00 00 00 FF FF 0C 00 81 F0 F0 00 20 00 00 00 00 00 01"));

        assertEquals(0x45, incomingMessageHandler.getFrameId());
        assertTrue(incomingMessageHandler.isResponse());
        assertEquals(EmberIncomingMessageType.EMBER_INCOMING_UNICAST, incomingMessageHandler.getType());
        System.out.println(incomingMessageHandler);
        ZigBeeTransportReceive transportReceiveMock = Mockito.mock(ZigBeeTransportReceive.class);
        ArgumentCaptor<ZigBeeApsFrame> apsFrame = ArgumentCaptor.forClass(ZigBeeApsFrame.class);

        Mockito.doNothing().when(transportReceiveMock).receiveCommand(apsFrame.capture());

        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);
        dongle.setZigBeeTransportReceive(transportReceiveMock);

        dongle.handlePacket(incomingMessageHandler);

        assertEquals(1, apsFrame.getAllValues().size());

        assertEquals(0, apsFrame.getValue().getSourceAddress());
        assertEquals(0, apsFrame.getValue().getDestinationAddress());

        assertEquals(0, apsFrame.getValue().getProfile());
        assertEquals(238, apsFrame.getValue().getApsCounter());
        assertEquals(0x8001, apsFrame.getValue().getCluster());
        assertEquals(0, apsFrame.getValue().getSourceEndpoint());
        assertEquals(0, apsFrame.getValue().getDestinationEndpoint());

        assertTrue(
                Arrays.equals(apsFrame.getValue().getPayload(), getPacketData("00 81 F0 F0 00 20 00 00 00 00 00 01")));
    }

    @Test
    public void testReceive3() {
        EzspFrame.setEzspVersion(4);

        EzspIncomingMessageHandler incomingMessageHandler = new EzspIncomingMessageHandler(getPacketData(
                "01 90 45 00 00 00 02 80 00 00 40 00 00 00 44 FF 00 00 00 FF FF 11 00 00 00 00 00 40 8F CD AB 52 80 00 41 2A 80 00 00"));
        assertEquals(0x45, incomingMessageHandler.getFrameId());
        assertTrue(incomingMessageHandler.isResponse());
        assertEquals(EmberIncomingMessageType.EMBER_INCOMING_UNICAST, incomingMessageHandler.getType());
        assertTrue(Arrays.equals(getPacketData("00 00 00 00 00 40 8F CD AB 52 80 00 41 2A 80 00 00"),
                incomingMessageHandler.getMessageContents()));
    }
}
