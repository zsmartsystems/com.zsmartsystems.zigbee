package com.zsmartsystems.zigbee.dongle.cc2531.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoEndDeviceAnnounce;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;

public class ZDO_END_DEVICE_ANNCE_IND extends Cc2351TestPacket {

    @Test
    public void testReceive() {
        ZToolPacket data = getPacket("FE 0D 45 C1 2A 2F 2A 2F F9 41 F6 02 00 4B 12 00 00 9C");

        ZigBeeApsFrame apsFrame = ZdoEndDeviceAnnounce.create(data);

        assertEquals(0x2f2a, apsFrame.getSourceAddress());
        assertEquals(0, apsFrame.getProfile());
        assertEquals(0, apsFrame.getDestinationEndpoint());
        assertTrue(Arrays.equals(getPacketData("2F 2A 2F F9 41 F6 02 00 4B 12 00 00"), apsFrame.getPayload()));
    }

}
