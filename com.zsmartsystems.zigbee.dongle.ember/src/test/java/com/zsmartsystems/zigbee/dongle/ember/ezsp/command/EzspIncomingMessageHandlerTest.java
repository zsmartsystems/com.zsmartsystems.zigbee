/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.aps.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.dongle.ember.ZigBeeDongleEzsp;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberIncomingMessageType;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportReceive;

/**
 * @author Chris Jackson
 */
public class EzspIncomingMessageHandlerTest extends EzspFrameTest {

    private ZigBeeDongleEzsp getDongle() {
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);

        try {
            Field field = dongle.getClass().getDeclaredField("nwkAddress");
            field.setAccessible(true);
            field.set(dongle, 0);
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return dongle;
    }

    @Test
    public void testReceive1() {
        EzspFrame.setEzspVersion(4);
        EzspIncomingMessageHandler incomingMessageHandler = new EzspIncomingMessageHandler(
                getPacketData("00 94 45 00 00 01 00 00 00 00 00 00 00 00 58 FF 00 00 00 FF FF 01 00"));
        System.out.println(incomingMessageHandler);

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
        System.out.println(incomingMessageHandler);

        assertEquals(0x45, incomingMessageHandler.getFrameId());
        assertTrue(incomingMessageHandler.isResponse());
        assertEquals(EmberIncomingMessageType.EMBER_INCOMING_UNICAST, incomingMessageHandler.getType());
        ZigBeeTransportReceive transportReceiveMock = Mockito.mock(ZigBeeTransportReceive.class);
        ArgumentCaptor<ZigBeeApsFrame> apsFrame = ArgumentCaptor.forClass(ZigBeeApsFrame.class);

        Mockito.doNothing().when(transportReceiveMock).receiveCommand(apsFrame.capture());

        ZigBeeDongleEzsp dongle = getDongle();
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
        System.out.println(incomingMessageHandler);

        assertEquals(0x45, incomingMessageHandler.getFrameId());
        assertTrue(incomingMessageHandler.isResponse());
        assertEquals(EmberIncomingMessageType.EMBER_INCOMING_UNICAST, incomingMessageHandler.getType());
        assertTrue(Arrays.equals(getPacketData("00 00 00 00 00 40 8F CD AB 52 80 00 41 2A 80 00 00"),
                incomingMessageHandler.getMessageContents()));
    }
}
