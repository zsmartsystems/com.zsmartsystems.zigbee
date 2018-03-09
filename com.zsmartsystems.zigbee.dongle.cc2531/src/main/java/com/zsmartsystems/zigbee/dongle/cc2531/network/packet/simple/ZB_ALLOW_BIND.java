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

/**
 * This command puts the device into the Allow Binding Mode for a given period of time.
 * This allows a peer device to establish a binding with this device (in the Allow Binding
 * Mode) by issuing the zb_BindDevice with a destination address of NULL.
 * 
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 */
public class ZB_ALLOW_BIND extends ZToolPacket /* implements IREQUEST,ISIMPLEAPI */ {
    /// <name>TI.ZPI2.ZB_ALLOW_BIND.Timeout</name>
    /// <summary>The number of seconds ( max. 64 ) for which the device will remain in the Allow Bind mode ( If 0, the
    /// device will turn off Allow Bind mode immediately. If 0xFF, the device will remain in the mode indefinitely.
    /// )</summary>
    public int Timeout;

    /// <name>TI.ZPI2.ZB_ALLOW_BIND</name>
    /// <summary>Constructor</summary>
    public ZB_ALLOW_BIND() {
    }

    /// <name>TI.ZPI2.ZB_ALLOW_BIND</name>
    /// <summary>Constructor</summary>
    public ZB_ALLOW_BIND(int num1) {
        this.Timeout = num1;
        int[] framedata = { num1 };
        super.buildPacket(new DoubleByte(ZToolCMD.ZB_ALLOW_BIND), framedata);
    }

}
