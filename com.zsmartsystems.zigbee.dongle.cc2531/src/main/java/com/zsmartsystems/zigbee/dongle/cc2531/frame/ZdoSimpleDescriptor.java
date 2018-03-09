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
public class ZdoSimpleDescriptor extends TiDongleReceivePacket {

    public static ZigBeeApsFrame create(ZToolPacket packet) {
        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setCluster(ZdoCommandType.SIMPLE_DESCRIPTOR_RESPONSE.getClusterId());
        apsFrame.setDestinationEndpoint(0);
        apsFrame.setSourceAddress(packet.getPacket()[4] + (packet.getPacket()[5] << 8));
        apsFrame.setSourceEndpoint(0);
        apsFrame.setProfile(0);
        apsFrame.setPayload(Arrays.copyOfRange(packet.getPacket(), 5, packet.getPacket().length - 1));

        return apsFrame;
    }
}
