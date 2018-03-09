/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
   Copyright 2008-2013 ITACA-TSB, http://www.tsb.upv.es/
   Instituto Tecnologico de Aplicaciones de Comunicacion
   Avanzadas - Grupo Tecnologias para la Salud y el
   Bienestar (TSB)


   See the NOTICE file distributed with this work for additional
   information regarding copyright ownership

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress16;

/**
 * This command is generated to set the Permit Join for the destination device.
 *
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @author Chris Jackson
 */
public class ZDO_MGMT_PERMIT_JOIN_REQ extends ZToolPacket /* implements IREQUEST,IZDO */ {
    /// <name>TI.ZPI1.ZDO_MGMT_PERMIT_JOIN_REQ.AddrMode</name>
    /// <summary>Destination address type: 0x02 - Address 16 bit, 0x0F - Broadcast.</summary>
    private byte AddrMode;
    /// <name>TI.ZPI1.ZDO_MGMT_PERMIT_JOIN_REQ.DstAddr</name>
    /// <summary>Destination network address.</summary>
    private ZToolAddress16 DstAddr;
    /// <name>TI.ZPI1.ZDO_MGMT_PERMIT_JOIN_REQ.Duration</name>
    /// <summary>The duration to permit joining. 0 = join disabled. 0xff = join enabled. 0x01-0xfe = number of seconds
    /// to permit joining</summary>
    private int Duration;
    /// <name>TI.ZPI1.ZDO_MGMT_PERMIT_JOIN_REQ.TCSignificance</name>
    /// <summary>Trust Center Significance</summary>
    private boolean TCSignificance;

    /// <name>TI.ZPI1.ZDO_MGMT_PERMIT_JOIN_REQ</name>
    /// <summary>Constructor</summary>
    public ZDO_MGMT_PERMIT_JOIN_REQ() {
    }

    /// <name>TI.ZPI1.ZDO_MGMT_PERMIT_JOIN_REQ</name>
    /// <summary>Constructor</summary>
    public ZDO_MGMT_PERMIT_JOIN_REQ(byte AddrMode, ZToolAddress16 DstAddr, int Duration, Boolean TCSignificance) {
        this.AddrMode = AddrMode;
        this.DstAddr = DstAddr;
        this.Duration = Duration;
        this.TCSignificance = TCSignificance;

        int[] framedata = new int[5];
        framedata[0] = this.AddrMode;
        framedata[1] = this.DstAddr.getLsb();
        framedata[2] = this.DstAddr.getMsb();
        framedata[3] = this.Duration;
        framedata[4] = this.TCSignificance ? 1 : 0;
        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_MGMT_PERMIT_JOIN_REQ), framedata);
    }

    public byte getAddrMode() {
        return AddrMode;
    }

    public void setAddrMode(byte addrMode) {
        AddrMode = addrMode;
    }

    public ZToolAddress16 getDstAddr() {
        return DstAddr;
    }

    public void setDstAddr(ZToolAddress16 dstAddr) {
        DstAddr = dstAddr;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public boolean getTCSignificance() {
        return TCSignificance;
    }

    public void setTCSignificance(boolean tCSignificance) {
        TCSignificance = tCSignificance;
    }
}
