/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
   Copyright 2008-2013 CNR-ISTI, http://isti.cnr.it
   Institute of Information Science and Technologies
   of the Italian National Research Council

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
 * Requests the Node Descriptor.
 *
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @author Chris Jackson
 */
public class ZDO_NODE_DESC_REQ extends ZToolPacket /* implements IREQUEST,IZDO */ {
    /// <name>TI.ZPI1.ZDO_NODE_DESC_REQ.DstAddr</name>
    /// <summary>destination address</summary>
    private ZToolAddress16 DstAddr;
    /// <name>TI.ZPI1.ZDO_NODE_DESC_REQ.NWKAddrOfInterest</name>
    /// <summary>NWK address for the request</summary>
    private ZToolAddress16 NWKAddrOfInterest;

    /// <name>TI.ZPI1.ZDO_NODE_DESC_REQ</name>
    /// <summary>Constructor</summary>
    public ZDO_NODE_DESC_REQ() {
    }

    public ZDO_NODE_DESC_REQ(ZToolAddress16 num1, ZToolAddress16 num2) {
        this.DstAddr = num1;
        this.NWKAddrOfInterest = num2;

        int[] framedata = new int[4];
        framedata[0] = this.DstAddr.getLsb();
        framedata[1] = this.DstAddr.getMsb();
        framedata[2] = this.NWKAddrOfInterest.getLsb();
        framedata[3] = this.NWKAddrOfInterest.getMsb();
        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_NODE_DESC_REQ), framedata);
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
