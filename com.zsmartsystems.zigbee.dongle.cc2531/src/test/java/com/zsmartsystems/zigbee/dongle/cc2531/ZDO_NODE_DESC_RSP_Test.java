package com.zsmartsystems.zigbee.dongle.cc2531;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoNodeDescriptor;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZDO_NODE_DESC_RSP_Test extends Cc2351TestPacket {

    @Test
    public void testReceive() {
        ZToolPacket data = getPacket("FE 12 45 82 00 00 00 00 00 00 40 0F 00 00 50 A0 00 01 00 A0 00 00 CB");

        ZigBeeApsFrame apsFrame = ZdoNodeDescriptor.create(data);

        assertEquals(0x0000, apsFrame.getSourceAddress());
        assertEquals(0, apsFrame.getProfile());
        assertEquals(0, apsFrame.getDestinationEndpoint());
        assertTrue(Arrays.equals(getPacketData("00 00 00 00 00 40 0F 00 00 50 A0 00 01 00 A0 00 00"),
                apsFrame.getPayload()));
    }

}
