package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsOption;
import com.zsmartsystems.zigbee.serialization.DefaultSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zdo.command.ManagementPermitJoiningRequest;

/**
 * @author Chris Jackson
 */
public class EzspSendBroadcastTest extends EzspFrameTest {

    @Test
    public void testSendPermitJoining() {
        ManagementPermitJoiningRequest permitJoining = new ManagementPermitJoiningRequest();

        permitJoining.setDestinationAddress(new ZigBeeDeviceAddress(0xFFFC));
        permitJoining.setSourceAddress(new ZigBeeDeviceAddress(0));
        permitJoining.setTcSignificance(true);
        permitJoining.setPermitDuration(255);

        DefaultSerializer serializer = new DefaultSerializer();
        ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);
        permitJoining.serialize(fieldSerializer);
        int[] payload = serializer.getPayload();

        EzspSendBroadcastRequest emberBroadcast = new EzspSendBroadcastRequest();
        EmberApsFrame apsFrame = new EmberApsFrame();
        apsFrame.setClusterId(permitJoining.getClusterId());
        apsFrame.setProfileId(0);
        apsFrame.setSourceEndpoint(0);
        apsFrame.setDestinationEndpoint(0);
        apsFrame.setSequence(5);
        apsFrame.addOptions(EmberApsOption.EMBER_APS_OPTION_RETRY);
        apsFrame.addOptions(EmberApsOption.EMBER_APS_OPTION_ENABLE_ADDRESS_DISCOVERY);
        apsFrame.addOptions(EmberApsOption.EMBER_APS_OPTION_ENABLE_ROUTE_DISCOVERY);
        apsFrame.setGroupId(0);

        emberBroadcast.setMessageTag(5);
        emberBroadcast.setSequenceNumber(5);
        emberBroadcast.setApsFrame(apsFrame);
        emberBroadcast.setDestination(permitJoining.getDestinationAddress().getAddress());
        emberBroadcast.setMessageContents(payload);
        emberBroadcast.setRadius(31);

        int[] messageToSend = emberBroadcast.serialize();

        String out = "";
        for (int c : messageToSend) {
            out += String.format("%02X ", c);
        }
        System.out.println(out);

        assertTrue(Arrays.equals(getPacketData("05 00 36 FC FF 00 00 36 00 00 00 40 11 00 00 05 1F 05 03 00 FF 01"),
                messageToSend));
    }
}
