package com.zsmartsystems.zigbee.dongle.cc2531.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoIeeeAddress;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;

public class ZDO_IEEE_ADDR_RSP extends Cc2351TestPacket {

    @Test
    public void testReceive() {
        ZToolPacket data = getPacket("FE 11 45 81 00 14 D4 F1 02 00 4B 12 00 00 00 00 02 8F 22 2A 2F 15");

        ZigBeeApsFrame apsFrame = ZdoIeeeAddress.create(data);

        assertEquals(0x0000, apsFrame.getSourceAddress());
        assertEquals(0, apsFrame.getProfile());
        assertEquals(0, apsFrame.getDestinationEndpoint());
        assertTrue(Arrays.equals(getPacketData("00 00 14 D4 F1 02 00 4B 12 00 00 00 02 00 8F 22 2A 2F"),
                apsFrame.getPayload()));
    }

}
