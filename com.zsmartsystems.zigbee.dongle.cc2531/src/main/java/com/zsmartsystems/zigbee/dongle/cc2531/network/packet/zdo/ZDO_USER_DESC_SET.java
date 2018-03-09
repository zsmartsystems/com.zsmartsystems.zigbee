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
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @author Chris Jackson
 */
public class ZDO_USER_DESC_SET extends ZToolPacket /* implements IREQUEST,IZDO */ {
    /// <name>TI.ZPI1.ZDO_USER_DESC_SET.DescLen</name>
    /// <summary>Length, in bytes, of the user descriptor.</summary>
    private int DescLen;
    /// <name>TI.ZPI1.ZDO_USER_DESC_SET.Descriptor</name>
    /// <summary>User descriptor array (can be up to 15 bytes).</summary>
    private int[] Descriptor;
    /// <name>TI.ZPI1.ZDO_USER_DESC_SET.DstAddr</name>
    /// <summary>Destination network address.</summary>
    private ZToolAddress16 DstAddr;
    /// <name>TI.ZPI1.ZDO_USER_DESC_SET.NWKAddrOfInterest</name>
    /// <summary>NWK address for the request.</summary>
    private ZToolAddress16 nwkAddr;

    /// <name>TI.ZPI1.ZDO_USER_DESC_SET</name>
    /// <summary>Constructor</summary>
    public ZDO_USER_DESC_SET() {
        this.Descriptor = new int[15];
    }

    public ZDO_USER_DESC_SET(ZToolAddress16 num1, ZToolAddress16 num2, int num3, int[] buffer1) {
        this.DstAddr = num1;
        this.nwkAddr = num2;
        this.DescLen = num3;
        this.Descriptor = new int[buffer1.length];
        this.Descriptor = buffer1;
        /*
         * if (buffer1.Length > 15)
         * {
         * throw new Exception("Error creating object.");
         * }
         * this.Descriptor = new byte[15];
         * Array.Copy(buffer1, this.Descriptor, buffer1.Length);
         */

        int[] framedata = new int[5 + this.Descriptor.length];
        framedata[0] = this.DstAddr.getLsb();
        framedata[1] = this.DstAddr.getMsb();
        framedata[2] = this.nwkAddr.getLsb();
        framedata[3] = this.nwkAddr.getMsb();
        framedata[4] = this.DescLen;
        for (int i = 0; i < this.Descriptor.length; i++) {
            framedata[i + 5] = this.Descriptor[i];
        }
        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_USER_DESC_SET), framedata);
    }

    public int getDescLen() {
        return DescLen;
    }

    public void setDescLen(int descLen) {
        DescLen = descLen;
    }

    public int[] getDescriptor() {
        return Descriptor;
    }

    public void setDescriptor(int[] descriptor) {
        Descriptor = descriptor;
    }

    public ZToolAddress16 getDstAddr() {
        return DstAddr;
    }

    public void setDstAddr(ZToolAddress16 dstAddr) {
        DstAddr = dstAddr;
    }

    public ZToolAddress16 getNwkAddr() {
        return nwkAddr;
    }

    public void setNwkAddr(ZToolAddress16 nwkAddr) {
        this.nwkAddr = nwkAddr;
    }

}
