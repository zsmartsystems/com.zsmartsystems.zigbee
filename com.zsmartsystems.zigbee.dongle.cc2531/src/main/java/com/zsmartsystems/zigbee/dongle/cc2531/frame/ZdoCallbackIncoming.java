package com.zsmartsystems.zigbee.dongle.cc2531.frame;

import java.util.Arrays;

import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZdoCallbackIncoming extends TiDongleReceivePacket {

    public static ZigBeeApsFrame create(ZToolPacket packet) {
        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setCluster(packet.getPacket()[7] + (packet.getPacket()[8] << 8));
        apsFrame.setDestinationAddress(packet.getPacket()[11] + (packet.getPacket()[12] << 8));
        apsFrame.setDestinationEndpoint(0);
        apsFrame.setSourceAddress(packet.getPacket()[4] + (packet.getPacket()[5] << 8));
        apsFrame.setSourceEndpoint(0);
        apsFrame.setProfile(0);
        apsFrame.setApsCounter(packet.getPacket()[10]);
        apsFrame.setPayload(Arrays.copyOfRange(packet.getPacket(), 12, packet.getPacket().length - 1));

        return apsFrame;
    }
}
