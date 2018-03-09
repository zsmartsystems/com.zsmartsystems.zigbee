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
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 */
public class ZDO_END_DEVICE_ANNCE extends ZToolPacket /* implements IREQUEST,IZDO */ {
    /// <name>TI.ZPI1.ZDO_END_DEVICE_ANNCE.Capabilities</name>
    /// <summary>MAC capabilities</summary>
    public int Capabilities;
    /// <name>TI.ZPI1.ZDO_END_DEVICE_ANNCE.DevAddr</name>
    /// <summary>Device network address.</summary>
    public ZToolAddress16 nwkAddr;
    /// <name>TI.ZPI1.ZDO_END_DEVICE_ANNCE.DeviceAddress</name>
    /// <summary>The 64 bit IEEE Address of the device you want to announce</summary>
    public ZToolAddress64 IEEEAddress;

    /// <name>TI.ZPI1.ZDO_END_DEVICE_ANNCE</name>
    /// <summary>Constructor</summary>
    public ZDO_END_DEVICE_ANNCE() {
    }

    public ZDO_END_DEVICE_ANNCE(ZToolAddress16 num1, ZToolAddress64 num2, int capability_info1) {
        this.nwkAddr = num1;
        this.IEEEAddress = num2;
        this.Capabilities = capability_info1;

        int[] framedata = new int[11];
        framedata[0] = this.nwkAddr.getLsb();
        framedata[1] = this.nwkAddr.getMsb();
        byte[] bytes = this.IEEEAddress.getAddress();
        for (int i = 0; i < 8; i++) {
            framedata[i + 2] = bytes[7 - i];
        }
        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_END_DEVICE_ANNCE), framedata);
    }

    /// <name>TI.ZPI1.ZDO_END_DEVICE_ANNCE.CAPABILITY_INFO</name>
    /// <summary>Capability Information bitfield</summary>
    public class CAPABILITY_INFO {
        /// <name>TI.ZPI1.ZDO_END_DEVICE_ANNCE.CAPABILITY_INFO.ALTER_PAN_COORD</name>
        /// <summary>Capability Information bitfield</summary>
        public static final int ALTER_PAN_COORD = 1;
        /// <name>TI.ZPI1.ZDO_END_DEVICE_ANNCE.CAPABILITY_INFO.DEVICE_TYPE</name>
        /// <summary>Capability Information bitfield</summary>
        public static final int DEVICE_TYPE = 2;
        /// <name>TI.ZPI1.ZDO_END_DEVICE_ANNCE.CAPABILITY_INFO.NONE</name>
        /// <summary>Capability Information bitfield</summary>
        public static final int NONE = 0;
        /// <name>TI.ZPI1.ZDO_END_DEVICE_ANNCE.CAPABILITY_INFO.POWER_SOURCE</name>
        /// <summary>Capability Information bitfield</summary>
        public static final int POWER_SOURCE = 4;
        /// <name>TI.ZPI1.ZDO_END_DEVICE_ANNCE.CAPABILITY_INFO.RECEIVER_ON_WHEN_IDLE</name>
        /// <summary>Capability Information bitfield</summary>
        public static final int RECEIVER_ON_WHEN_IDLE = 8;
        /// <name>TI.ZPI1.ZDO_END_DEVICE_ANNCE.CAPABILITY_INFO.SECURITY_CAPABILITY</name>
        /// <summary>Capability Information bitfield</summary>
        public static final int SECURITY_CAPABILITY = 0x40;
    }

}
