/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.Integers;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress16;

/**
 * Requests the Power Descriptor for a node.
 *
 * @author Chris Jackson
 */
public class ZDO_POWER_DESC_REQ extends ZToolPacket {
    /// <name>TI.ZPI1.ZDO_POWER_DESC_REQ.DstAddr</name>
    /// <summary>destination address</summary>
    private ZToolAddress16 DstAddr;
    /// <name>TI.ZPI1.ZDO_POWER_DESC_REQ.NWKAddrOfInterest</name>
    /// <summary>NWK address for the request</summary>
    private ZToolAddress16 NWKAddrOfInterest;

    /// <name>TI.ZPI1.ZDO_POWER_DESC_REQ</name>
    /// <summary>Constructor</summary>
    public ZDO_POWER_DESC_REQ() {
    }

    public ZDO_POWER_DESC_REQ(int destination) {
        // TODO Check compatibility with other Constructor
        int[] framedata = new int[4];
        framedata[0] = Integers.getByteAsInteger(destination, 0);
        framedata[1] = Integers.getByteAsInteger(destination, 1);
        framedata[2] = framedata[0];
        framedata[3] = framedata[1];
        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_POWER_DESC_REQ), framedata);
    }

    public ZToolAddress16 getDstAddr() {
        return DstAddr;
    }

    public void setDstAddr(ZToolAddress16 dstAddr) {
        DstAddr = dstAddr;
    }

    public ZToolAddress16 getNWKAddrOfInterest() {
        return NWKAddrOfInterest;
    }

    public void setNWKAddrOfInterest(ZToolAddress16 nWKAddrOfInterest) {
        NWKAddrOfInterest = nWKAddrOfInterest;
    }
}
