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

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;

/**
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @version $LastChangedRevision: 799 $ ($LastChangedDate: 2013-08-06 19:00:05 +0300 (Tue, 06 Aug 2013) $)
 */
public class ZB_FIND_DEVICE_CONFIRM extends ZToolPacket /*implements IRESPONSE_CALLBACK,ISIMPLEAPI*/ {
    /// <name>TI.ZPI2.ZB_FIND_DEVICE_CONFIRM.Result</name>
    /// <summary>The 64-bit IEEE address of device that was searched for.</summary>
    public int[] Result;
    /// <name>TI.ZPI2.ZB_FIND_DEVICE_CONFIRM.SearchKey</name>
    /// <summary>The 16-bit short address of the device that was found.</summary>
    public DoubleByte SearchKey;
    /// <name>TI.ZPI2.ZB_FIND_DEVICE_CONFIRM.SearchType</name>
    /// <summary>The type of search that was performed.</summary>
    public int SearchType;

    /// <name>TI.ZPI2.ZB_FIND_DEVICE_CONFIRM</name>
    /// <summary>Constructor</summary>
    public ZB_FIND_DEVICE_CONFIRM() {
    }

    /// <name>TI.ZPI2.ZB_FIND_DEVICE_CONFIRM</name>
    /// <summary>Constructor</summary>
    public ZB_FIND_DEVICE_CONFIRM(int[] framedata) {
        this.SearchType = framedata[0];
        this.SearchKey = new DoubleByte(framedata[2], framedata[1]);
        this.Result = new int[8];
        for (int i = 0; i < 8; i++) {
            this.Result[i] = framedata[i + 3];
        }
    }

}
