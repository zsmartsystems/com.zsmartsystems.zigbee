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
 */
public class ZDO_USER_DESC_CONF extends ZToolPacket /* implements IRESPONSE_CALLBACK,IZDO */ {
    /// <name>TI.ZPI1.ZDO_USER_DESC_CONF.SrcAddress</name>
    /// <summary>Source address of the message</summary>
    public ZToolAddress16 SrcAddress;
    /// <name>TI.ZPI1.ZDO_USER_DESC_CONF.Status</name>
    /// <summary>this field indicates status of the request</summary>
    public int Status;
    public ZToolAddress16 nwkAddr;

    /// <name>TI.ZPI1.ZDO_USER_DESC_CONF</name>
    /// <summary>Constructor</summary>
    public ZDO_USER_DESC_CONF() {
    }

    public ZDO_USER_DESC_CONF(int[] framedata) {
        this.SrcAddress = new ZToolAddress16(framedata[1], framedata[0]);
        this.Status = framedata[2];
        if (framedata.length == 5) {
            this.nwkAddr = new ZToolAddress16(framedata[4], framedata[3]);
        } else {
            this.nwkAddr = new ZToolAddress16();
        }
        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_USER_DESC_CONF), framedata);
    }

}
