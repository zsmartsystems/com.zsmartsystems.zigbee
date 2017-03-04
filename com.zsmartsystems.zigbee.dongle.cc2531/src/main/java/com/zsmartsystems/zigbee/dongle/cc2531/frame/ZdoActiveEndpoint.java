package com.zsmartsystems.zigbee.dongle.cc2531.frame;

import java.util.Arrays;

import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.zdo.ZdoCommandType;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZdoActiveEndpoint extends TiDongleReceivePacket {

    public static ZigBeeApsFrame create(ZToolPacket packet) {
        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setCluster(ZdoCommandType.ACTIVE_ENDPOINTS_RESPONSE.getClusterId());
        apsFrame.setDestinationEndpoint(0);
        apsFrame.setSourceAddress(packet.getPacket()[4] + (packet.getPacket()[5] << 8));
        apsFrame.setSourceEndpoint(0);
        apsFrame.setProfile(0);
        apsFrame.setPayload(Arrays.copyOfRange(packet.getPacket(), 5, packet.getPacket().length - 1));

        return apsFrame;
    }
}
