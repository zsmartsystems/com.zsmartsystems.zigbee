package com.zsmartsystems.zigbee.dongle.cc2531.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoNodeDescriptor;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;

public class ZDO_POWER_DESC_RSP extends Cc2351TestPacket {

    @Test
    public void testReceive() {
        ZToolPacket data = getPacket("FE 07 45 83 00 00 00 00 00 10 C1 10");

        ZigBeeApsFrame apsFrame = ZdoNodeDescriptor.create(data);

        assertEquals(0x0000, apsFrame.getSourceAddress());
        assertEquals(0, apsFrame.getProfile());
        assertEquals(0, apsFrame.getDestinationEndpoint());
        assertTrue(Arrays.equals(getPacketData("00 00 00 00 10 C1"), apsFrame.getPayload()));
    }

}
