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

import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberApsFrame;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberApsOption;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberOutgoingMessageType;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.serialization.DefaultSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zdo.command.ManagementPermitJoiningRequest;

/**
 * @author Chris Jackson
 */
public class EzspSendUnicastTest extends EzspFrameTest {

    @Test
    public void testReceive1() {
        EzspFrame.setEzspVersion(4);
        EzspSendUnicastResponse unicastResponse = new EzspSendUnicastResponse(getPacketData("02 80 34 00 9E"));

        assertEquals(0x34, unicastResponse.getFrameId());
        assertTrue(unicastResponse.isResponse());
        assertEquals(EmberStatus.EMBER_SUCCESS, unicastResponse.getStatus());
    }

    @Test
    public void testSendPermitJoining() {
        EzspFrame.setEzspVersion(4);
        ManagementPermitJoiningRequest permitJoining = new ManagementPermitJoiningRequest();

        permitJoining.setDestinationAddress(new ZigBeeEndpointAddress(0x401C));
        permitJoining.setSourceAddress(new ZigBeeEndpointAddress(0));
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
        apsFrame.addOptions(EmberApsOption.EMBER_APS_OPTION_RETRY);
        apsFrame.addOptions(EmberApsOption.EMBER_APS_OPTION_ENABLE_ADDRESS_DISCOVERY);
        apsFrame.addOptions(EmberApsOption.EMBER_APS_OPTION_ENABLE_ROUTE_DISCOVERY);
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
    }
}
