package com.zsmartsystems.zigbee.dongle.cc2531;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoActiveEndpoint;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZDO_ACTIVE_EP_RSP_Test extends Cc2351TestPacket {

    @Test
    public void testReceive() {
        String packetString = "FE 08 45 85 00 00 00 00 00 02 02 01 C9";
        ZToolPacket data = getPacket(packetString);
        assertEquals(packetString, data.getPacketString());

        ZigBeeApsFrame apsFrame = ZdoActiveEndpoint.create(data);

        assertEquals(0x0000, apsFrame.getSourceAddress());
        assertEquals(0, apsFrame.getProfile());
        assertEquals(0, apsFrame.getDestinationEndpoint());
        assertTrue(Arrays.equals(getPacketData("00 00 00 00 02 02 01"), apsFrame.getPayload()));
    }

}
