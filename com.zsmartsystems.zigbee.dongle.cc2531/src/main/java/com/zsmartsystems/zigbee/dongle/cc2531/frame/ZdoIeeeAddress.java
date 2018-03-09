/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
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
public class ZdoIeeeAddress extends TiDongleReceivePacket {

    public static ZigBeeApsFrame create(ZToolPacket packet) {
        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setCluster(ZdoCommandType.IEEE_ADDRESS_RESPONSE.getClusterId());
        apsFrame.setDestinationEndpoint(0);
        apsFrame.setSourceAddress(packet.getPacket()[13] + (packet.getPacket()[14] << 8));
        apsFrame.setSourceEndpoint(0);
        apsFrame.setProfile(0);
        int temp[] = Arrays.copyOfRange(packet.getPacket(), 3, packet.getPacket().length - 1);
        int a = temp[12];
        temp[12] = temp[13];
        temp[13] = a;
        temp[0] = 0;
        apsFrame.setPayload(temp);

        return apsFrame;
    }
}
