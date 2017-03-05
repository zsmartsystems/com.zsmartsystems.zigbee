package com.zsmartsystems.zigbee.dongle.cc2531.frame;

import com.zsmartsystems.zigbee.ZigBeeApsHeader;

public class TiDongleReceivePacket {
    protected ZigBeeApsHeader apsHeader = new ZigBeeApsHeader();
    protected int[] payload = null;

    public ZigBeeApsHeader getApsHeader() {
        return apsHeader;
    }

    public int[] getPayload() {
        return payload;
    }
}
