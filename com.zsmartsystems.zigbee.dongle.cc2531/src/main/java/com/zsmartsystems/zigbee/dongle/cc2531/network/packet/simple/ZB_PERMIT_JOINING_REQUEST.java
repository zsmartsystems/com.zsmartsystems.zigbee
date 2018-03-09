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

package com.zsmartsystems.zigbee.dongle.cc2531.network.packet.simple;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress16;

/**
 * This command is used to control the joining permissions and thus allow or disallow new
 * devices from joining the network.
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @version $LastChangedRevision: 799 $ ($LastChangedDate: 2013-08-06 19:00:05 +0300 (Tue, 06 Aug 2013) $)
 */
public class ZB_PERMIT_JOINING_REQUEST extends ZToolPacket/* implements IREQUEST,ISIMPLEAPI*/ {
    /// <name>TI.ZPI2.ZB_PERMIT_JOINING_REQUEST.Destination</name>
    /// <summary>Short adddress of the device on which to control the joining permissions. This is usually the local device address or the special broadcast address that denotes all routers and coordinator ( 0xFFFC ).</summary>
    public ZToolAddress16 Destination;
    /// <name>TI.ZPI2.ZB_PERMIT_JOINING_REQUEST.Timeout</name>
    /// <summary>The amount of time in seconds for which the joining permissions should be turned on. If set to 0x00, the device will turn off the joining permissions indefinitely. If it is set to 0xFF, the joining permissions will be turned on indefinitely.</summary>
    public int Timeout;

    /// <name>TI.ZPI2.ZB_PERMIT_JOINING_REQUEST</name>
    /// <summary>Constructor</summary>
    public ZB_PERMIT_JOINING_REQUEST() {
    }

    /// <name>TI.ZPI2.ZB_PERMIT_JOINING_REQUEST</name>
    /// <summary>Constructor</summary>
    public ZB_PERMIT_JOINING_REQUEST(ZToolAddress16 num1, int num2) {
        this.Destination = num1;
        this.Timeout = num2;
        int[] framedata = new int[3];
        framedata[0] = this.Destination.getLsb();
        framedata[1] = this.Destination.getMsb();
        framedata[2] = this.Timeout;
        super.buildPacket(new DoubleByte(ZToolCMD.ZB_PERMIT_JOINING_REQUEST), framedata);
    }

}
