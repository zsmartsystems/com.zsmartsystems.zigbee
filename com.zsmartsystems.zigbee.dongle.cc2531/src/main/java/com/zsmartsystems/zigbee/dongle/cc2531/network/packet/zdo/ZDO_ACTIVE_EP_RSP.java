/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
   Copyright 2008-2013 CNR-ISTI, http://isti.cnr.it
   Institute of Information Science and Technologies
   of the Italian National Research Council

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

import java.util.Arrays;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ResponseStatus;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress16;

/**
 * Processes the ACTIVE_EP_RSP packet.
 * <p>
 * On receipt of the ACTIVE_EP_RSP command, the recipient is either notified of
 * the active endpoints of the remote device indicated in the original
 * ACTIVE_EP_REQ command or notified of an error. If the Active_EP_rsp command
 * is received with a Status of SUCCESS, the ActiveEPCount field indicates the
 * number of entries in the ActiveEPList field. Otherwise, the Status field
 * indicates the error and the ActiveEPList field is not included.
 *
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 */
public class ZDO_ACTIVE_EP_RSP extends ZToolPacket /* implements IRESPONSE_CALLBACK,IZDO */ {
    /// <name>TI.ZPI1.ZDO_ACTIVE_EP_RSP.ActiveEndpointCount</name>
    /// <summary>Number of active endpoint in the list</summary>
    public int ActiveEndpointCount;
    /// <name>TI.ZPI1.ZDO_ACTIVE_EP_RSP.ActiveEndpointList</name>
    /// <summary>Array of active endpoints on this device</summary>
    public int[] ActiveEndpointList;
    /// <name>TI.ZPI1.ZDO_ACTIVE_EP_RSP.NWKAddrOfInterest</name>
    /// <summary>Device's short address that this response describes.</summary>
    public ZToolAddress16 nwkAddr;
    /// <name>TI.ZPI1.ZDO_ACTIVE_EP_RSP.SrcAddress</name>
    /// <summary>the message's source network address</summary>
    public ZToolAddress16 SrcAddress;
    /// <name>TI.ZPI1.ZDO_ACTIVE_EP_RSP.Status</name>
    /// <summary>this field indicates either SUCCESS or FAILURE</summary>
    public int Status;
    private short[] list;

    /// <name>TI.ZPI1.ZDO_ACTIVE_EP_RSP</name>
    /// <summary>Constructor</summary>
    public ZDO_ACTIVE_EP_RSP() {
        this.ActiveEndpointList = new int[0xff];
    }

    public ZDO_ACTIVE_EP_RSP(int[] framedata) {
        this.SrcAddress = new ZToolAddress16(framedata[1], framedata[0]);
        this.Status = framedata[2];
        this.nwkAddr = new ZToolAddress16(framedata[4], framedata[3]);

        this.ActiveEndpointCount = framedata[5];
        this.ActiveEndpointList = new int[this.ActiveEndpointCount];
        for (int i = 0; i < this.ActiveEndpointList.length; i++) {
            this.ActiveEndpointList[i] = framedata[i + 6];
        }

        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_ACTIVE_EP_RSP), framedata);
    }

    public short[] getActiveEndPointList() {
        if (list == null) {
            list = new short[super.packet[ZToolPacket.PAYLOAD_START_INDEX + 5]];
            for (int i = 0; i < list.length; i++) {
                list[i] = (short) super.packet[ZToolPacket.PAYLOAD_START_INDEX + 6 + i];
            }
        }
        return list;
    }

    @Override
    public String toString() {
        return "ZDO_ACTIVE_EP_RSP{" + "ActiveEndpointCount=" + ActiveEndpointCount + ", ActiveEndpointList="
                + Arrays.toString(ActiveEndpointList) + ", nwkAddr=" + nwkAddr + ", SrcAddress=" + SrcAddress
                + ", Status=" + ResponseStatus.getStatus(Status) + '}';
    }
}
