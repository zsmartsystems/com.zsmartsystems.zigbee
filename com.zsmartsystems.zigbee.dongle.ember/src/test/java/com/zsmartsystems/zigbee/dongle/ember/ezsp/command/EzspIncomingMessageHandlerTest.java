package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ZigBeeApsHeader;
import com.zsmartsystems.zigbee.ZigBeeNwkHeader;
import com.zsmartsystems.zigbee.ZigBeeTransportReceive;
import com.zsmartsystems.zigbee.dongle.ember.ZigBeeDongleEzsp;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberIncomingMessageType;

/**
 * @author Chris Jackson
 */
public class EzspIncomingMessageHandlerTest extends EzspFrameTest {

    @Test
    public void testReceive1() {
        EzspIncomingMessageHandler incomingMessageHandler = new EzspIncomingMessageHandler(
                getPacketData("00 94 45 00 00 01 00 00 00 00 00 00 00 00 58 FF 00 00 00 FF FF 01 00"));

        assertEquals(0x45, incomingMessageHandler.getFrameId());
        assertTrue(incomingMessageHandler.isResponse());
        assertEquals(EmberIncomingMessageType.EMBER_INCOMING_UNICAST, incomingMessageHandler.getType());
    }

    @Test
    public void testReceive2() {
        // This tests a number of stages - not just this class
        // We process the received frame, make sure the dongle sends it to the networkManager
        EzspIncomingMessageHandler incomingMessageHandler = new EzspIncomingMessageHandler(getPacketData(
                "01 90 45 00 00 00 01 80 00 00 40 00 00 00 EE FF 00 00 00 FF FF 0C 00 81 F0 F0 00 20 00 00 00 00 00 01"));

        assertEquals(0x45, incomingMessageHandler.getFrameId());
        assertTrue(incomingMessageHandler.isResponse());
        assertEquals(EmberIncomingMessageType.EMBER_INCOMING_UNICAST, incomingMessageHandler.getType());
        System.out.println(incomingMessageHandler);
        ZigBeeTransportReceive transportReceiveMock = Mockito.mock(ZigBeeTransportReceive.class);
        ArgumentCaptor<ZigBeeNwkHeader> nwkHeader = ArgumentCaptor.forClass(ZigBeeNwkHeader.class);
        ArgumentCaptor<ZigBeeApsHeader> apsHeader = ArgumentCaptor.forClass(ZigBeeApsHeader.class);
        ArgumentCaptor<int[]> payload = ArgumentCaptor.forClass(int[].class);

        Mockito.doNothing().when(transportReceiveMock).receiveZclCommand(nwkHeader.capture(), apsHeader.capture(),
                payload.capture());

        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);
        dongle.setZigBeeTransportReceive(transportReceiveMock);

        dongle.handlePacket(incomingMessageHandler);

        assertEquals(1, nwkHeader.getAllValues().size());
        assertEquals(1, apsHeader.getAllValues().size());
        assertEquals(1, payload.getAllValues().size());

        assertEquals(0, nwkHeader.getValue().getSourceAddress());
        assertEquals(0, nwkHeader.getValue().getDestinationAddress());

        assertEquals(0, apsHeader.getValue().getProfile());
        assertEquals(238, apsHeader.getValue().getApsCounter());
        assertEquals(0x8001, apsHeader.getValue().getCluster());
        assertEquals(0, apsHeader.getValue().getSourceEndpoint());
        assertEquals(0, apsHeader.getValue().getDestinationEndpoint());

        assertTrue(Arrays.equals(payload.getValue(), getPacketData("00 81 F0 F0 00 20 00 00 00 00 00 01")));
    }

    @Test
    public void testReceive3() {
        EzspIncomingMessageHandler incomingMessageHandler = new EzspIncomingMessageHandler(getPacketData(
                "01 90 45 00 00 00 02 80 00 00 40 00 00 00 44 FF 00 00 00 FF FF 11 00 00 00 00 00 40 8F CD AB 52 80 00 41 2A 80 00 00"));
        assertEquals(0x45, incomingMessageHandler.getFrameId());
        assertTrue(incomingMessageHandler.isResponse());
        assertEquals(EmberIncomingMessageType.EMBER_INCOMING_UNICAST, incomingMessageHandler.getType());
        assertTrue(Arrays.equals(getPacketData("00 00 00 00 00 40 8F CD AB 52 80 00 41 2A 80 00 00"),
                incomingMessageHandler.getMessageContents()));
    }
}
