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
 * This callback is called asynchronously by the CC2530-ZNP device when it has received a
 * packet from a remote device.
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @version $LastChangedRevision: 799 $ ($LastChangedDate: 2013-08-06 19:00:05 +0300 (Tue, 06 Aug 2013) $)
 */
public class ZB_RECEIVE_DATA_INDICATION extends ZToolPacket /*implements IRESPONSE_CALLBAC,ISIMPLEAPI*/ {
    /// <name>TI.ZPI2.ZB_RECEIVE_DATA_INDICATION.Command</name>
    /// <summary>The CommandId of the data packet.</summary>
    public DoubleByte Command;
    /// <name>TI.ZPI2.ZB_RECEIVE_DATA_INDICATION.Data</name>
    /// <summary>The received data packet payload.</summary>
    public int[] Data;
    /// <name>TI.ZPI2.ZB_RECEIVE_DATA_INDICATION.Len</name>
    /// <summary>The length of the data payload.</summary>
    public DoubleByte Len;
    /// <name>TI.ZPI2.ZB_RECEIVE_DATA_INDICATION.Source</name>
    /// <summary>The 16-bit address of the device that sent the data packet.</summary>
    public ZToolAddress16 Source;

    /// <name>TI.ZPI2.ZB_RECEIVE_DATA_INDICATION</name>
    /// <summary>Constructor</summary>
    public ZB_RECEIVE_DATA_INDICATION() {
        this.Data = new int[0xff];
    }

    /// <name>TI.ZPI2.ZB_RECEIVE_DATA_INDICATION</name>
    /// <summary>Constructor</summary>
    public ZB_RECEIVE_DATA_INDICATION(int[] framedata) {
        this.Source = new ZToolAddress16(framedata[1], framedata[0]);
        this.Command = new DoubleByte(framedata[3], framedata[2]);
        this.Len = new DoubleByte(framedata[5], framedata[4]);
        this.Data = new int[framedata.length - 6];
        for (int i = 0; i < this.Data.length; i++) {
            this.Data[i] = framedata[i + 6];
        }
            /*if (buffer1.Length > 0xff)
            {
                throw new Exception("Error creating object.");
            }
            this.Data = new byte[0xff];
            Array.Copy(buffer1, this.Data, buffer1.Length);*/
        super.buildPacket(new DoubleByte(ZToolCMD.ZB_RECEIVE_DATA_INDICATION), framedata);
    }

}
