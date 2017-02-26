package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsOption;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberOutgoingMessageType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.serialization.DefaultSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zdo.command.ManagementPermitJoiningRequest;

/**
 * @author Chris Jackson
 */
public class EzspSendUnicastTest extends EzspFrameTest {

    @Test
    public void testReceive1() {
        EzspSendUnicastResponse unicastResponse = new EzspSendUnicastResponse(getPacketData("02 80 34 00 9E"));

        assertEquals(0x34, unicastResponse.getFrameId());
        assertTrue(unicastResponse.isResponse());
        assertEquals(EmberStatus.EMBER_SUCCESS, unicastResponse.getStatus());
    }

    @Test
    public void testSendPermitJoining() {
        ManagementPermitJoiningRequest permitJoining = new ManagementPermitJoiningRequest();

        permitJoining.setDestinationAddress(new ZigBeeDeviceAddress(0x401C));
        permitJoining.setSourceAddress(new ZigBeeDeviceAddress(0));
        permitJoining.setTcSignificance(false);
        permitJoining.setPermitDuration(60);

        DefaultSerializer serializer = new DefaultSerializer();
        ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);
        permitJoining.serialize(fieldSerializer);
        int[] payload = serializer.getPayload();

        EzspSendUnicastRequest emberUnicast = new EzspSendUnicastRequest();
        EmberApsFrame apsFrame = new EmberApsFrame();
        apsFrame.setClusterId(permitJoining.getClusterId());
        apsFrame.setProfileId(0);
        apsFrame.setSourceEndpoint(1);
        apsFrame.setDestinationEndpoint(0);
        apsFrame.setSequence(0x88);
        // apsFrame.setOptions(EmberApsOption.EMBER_APS_OPTION_RETRY | EMBER_APS_OPTION_ENABLE_ADDRESS_DISCOVERY
        // | EMBER_APS_OPTION_ENABLE_ROUTE_DISCOVERY);
        apsFrame.setOptions(EmberApsOption.EMBER_APS_OPTION_RETRY);
        apsFrame.setGroupId(0xffff);

        emberUnicast.setMessageTag(0x99);
        emberUnicast.setSequenceNumber(0xaa);
        emberUnicast.setType(EmberOutgoingMessageType.EMBER_OUTGOING_DIRECT);
        emberUnicast.setApsFrame(apsFrame);
        emberUnicast.setIndexOrDestination(permitJoining.getDestinationAddress().getAddress());
        emberUnicast.setMessageContents(payload);

        int[] messageToSend = emberUnicast.serialize();

        System.out.println(emberUnicast.toString());
        System.out.println(Arrays.toString(messageToSend));

        String out = "";
        for (int c : messageToSend) {
            out += String.format("%02X ", c);
        }
        System.out.println(out);

        // assertTrue(Arrays.equals(getPacketData("02 00 02 01 04 01 00 00 00 03 03 00 00 01 00 06 00 00 00 01 00 06
        // 00"),
        // messageToSend));
    }
}
