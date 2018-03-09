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

package com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.Integers;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress16;

/**
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @author Chris Jackson
 */
public class AF_DATA_REQUEST extends ZToolPacket/* implements IREQUEST,IAF */ {
    /// <name>TI.ZPI2.AF_DATA_REQUEST.ClusterID</name>
    /// <summary>specifies the cluster PROFILE_ID_HOME_AUTOMATION</summary>
    public DoubleByte ClusterID;
    /// <name>TI.ZPI2.AF_DATA_REQUEST.Data</name>
    /// <summary>Dynamic array, requires memory allocation. Variable length field of size 'Len' and is the transaction
    /// data frame</summary>
    public int[] Data;
    /// <name>TI.ZPI2.AF_DATA_REQUEST.DestEndpoint</name>
    /// <summary>specifies the endpoint of the device</summary>
    public int DestEndpoint;
    /// <name>TI.ZPI2.AF_DATA_REQUEST.DstAddr</name>
    /// <summary>the address of the destination device</summary>
    public ZToolAddress16 DstAddr;
    /// <name>TI.ZPI2.AF_DATA_REQUEST.Len</name>
    /// <summary>specifies the length of the TransactionData field</summary>
    public int Len;
    /// <name>TI.ZPI2.AF_DATA_REQUEST.Options</name>
    /// <summary>consists of the AF Tx Options bit fields; zero for none</summary>
    public int Options;
    /// <name>TI.ZPI2.AF_DATA_REQUEST.Radius</name>
    /// <summary>the number of hops allowed to deliver the message; usually use 7</summary>
    public int Radius;
    /// <name>TI.ZPI2.AF_DATA_REQUEST.SrcEndpoint</name>
    /// <summary>specifies the endpoint of the device</summary>
    public int SrcEndpoint;
    /// <name>TI.ZPI2.AF_DATA_REQUEST.TransID</name>
    /// <summary>specifies the transaction Id of the device</summary>
    public int TransID;

    /// <name>TI.ZPI2.AF_DATA_REQUEST</name>
    /// <summary>Constructor</summary>
    public AF_DATA_REQUEST() {
        this.Data = new int[0xff];
    }

    /**
     *
     * @param nwkDstAddress
     * @param dstEndPoint
     * @param srcEndPoint
     * @param clusterId
     * @param transId
     * @param bitmapOpt
     * @param radius
     * @param payload
     */
    public AF_DATA_REQUEST(int nwkDstAddress, short dstEndPoint, short srcEndPoint, int clusterId, int transId,
            byte bitmapOpt, byte radius, int[] payload) {

        // TODO Check compatibility with other Constructor

        if (payload.length > 128) {
            throw new IllegalArgumentException("Payload is too big, maximum is 128");
        }

        int[] framedata = new int[payload.length + 10];
        framedata[0] = Integers.getByteAsInteger(nwkDstAddress, 0);
        framedata[1] = Integers.getByteAsInteger(nwkDstAddress, 1);
        framedata[2] = dstEndPoint & 0xFF;
        framedata[3] = srcEndPoint & 0xFF;
        framedata[4] = Integers.getByteAsInteger(clusterId, 0);
        framedata[5] = Integers.getByteAsInteger(clusterId, 1);
        framedata[6] = transId & 0xFF;
        framedata[7] = bitmapOpt & 0xFF;
        framedata[8] = radius & 0xFF;
        framedata[9] = payload.length;
        for (int i = 0; i < payload.length; i++) {
            framedata[10 + i] = payload[i];
        }
        super.buildPacket(new DoubleByte(ZToolCMD.AF_DATA_REQUEST), framedata);
    }

}
