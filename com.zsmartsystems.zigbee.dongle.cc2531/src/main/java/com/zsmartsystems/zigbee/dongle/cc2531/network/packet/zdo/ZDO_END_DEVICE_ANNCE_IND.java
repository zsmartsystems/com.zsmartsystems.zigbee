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
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress64;

/**
 * This callback indicates the ZDO End Device Announce, as long as the ZCD_NV_ZDO_DIRECT_CB
 * configuration item is set to TRUE. This can be caused by another device sending out the
 * END_DEVICE_ANNCE message to the network.
 * 
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 */
public class ZDO_END_DEVICE_ANNCE_IND extends ZToolPacket /* implements IRESPONSE_CALLBACK,IZDO */ {
    /// <name>TI.ZPI2.ZDO_END_DEVICE_ANNCE_IND.Capabilities</name>
    /// <summary>Capabilities</summary>
    public int Capabilities;
    /// <name>TI.ZPI2.ZDO_END_DEVICE_ANNCE_IND.IEEEAddr</name>
    /// <summary>64 bit IEEE address of source device</summary>
    public ZToolAddress64 IEEEAddr;
    /// <name>TI.ZPI2.ZDO_END_DEVICE_ANNCE_IND.NwkAddr</name>
    /// <summary>Network address</summary>
    public ZToolAddress16 NwkAddr;
    /// <name>TI.ZPI2.ZDO_END_DEVICE_ANNCE_IND.SrcAddr</name>
    /// <summary>Source address</summary>
    public ZToolAddress16 SrcAddr;

    /// <name>TI.ZPI2.ZDO_END_DEVICE_ANNCE_IND</name>
    /// <summary>Constructor</summary>
    public ZDO_END_DEVICE_ANNCE_IND() {
    }

    /// <name>TI.ZPI2.ZDO_END_DEVICE_ANNCE_IND</name>
    /// <summary>Constructor</summary>
    public ZDO_END_DEVICE_ANNCE_IND(int[] framedata) {
        this.SrcAddr = new ZToolAddress16(framedata[1], framedata[0]);
        this.NwkAddr = new ZToolAddress16(framedata[3], framedata[2]);
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) framedata[11 - i];
        }
        this.IEEEAddr = new ZToolAddress64(bytes);
        this.Capabilities = framedata[12];
        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_END_DEVICE_ANNCE_IND), framedata);
    }

}
