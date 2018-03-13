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

package com.zsmartsystems.zigbee.dongle.cc2531.network.packet.system;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ResponseStatus;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;

/**
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @version $LastChangedRevision: 799 $ ($LastChangedDate: 2013-08-06 19:00:05 +0300 (Tue, 06 Aug 2013) $)
 */
public class SYS_RPC_ERROR extends ZToolPacket /*implements IRESPONSE, ISYSTEM*/ {
    /// <name>TI.ZPI2.SYS_RPC_ERROR.ErrCmd0</name>
    /// <summary>Command byte 0 of the message causing an error.</summary>
    public int ErrCmd0;
    /// <name>TI.ZPI2.SYS_RPC_ERROR.ErrCmd1</name>
    /// <summary>Command byte 1 of the message causing an error.</summary>
    public int ErrCmd1;
    /// <name>TI.ZPI2.SYS_RPC_ERROR.Status</name>
    /// <summary>Status</summary>
    public int Status;

    /// <name>TI.ZPI2.SYS_RPC_ERROR</name>
    /// <summary>Constructor</summary>
    public SYS_RPC_ERROR() {
    }

    /// <name>TI.ZPI2.SYS_RPC_ERROR</name>
    /// <summary>Constructor</summary>
    public SYS_RPC_ERROR(int num1, int num2, int num3) {
        this.Status = num1;
        this.ErrCmd0 = num2;
        this.ErrCmd1 = num3;
        int[] framedata = {num1, num2, num3};
        super.buildPacket(new DoubleByte(ZToolCMD.SYS_RPC_ERROR), framedata);
    }

    public SYS_RPC_ERROR(int[] framedata) {
        this.Status = framedata[0];
        this.ErrCmd0 = framedata[1];
        this.ErrCmd1 = framedata[3];
        super.buildPacket(new DoubleByte(ZToolCMD.SYS_RPC_ERROR), framedata);
    }

    @Override
    public String toString() {
        return "SYS_RPC_ERROR{" +
                "ErrCmd0=" + ErrCmd0 +
                ", ErrCmd1=" + ErrCmd1 +
                ", Status=" + ResponseStatus.getStatus(Status) +
                '}';
    }
}
