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
 * This command is generated to request a Management Leave Request for the target device and is used to remove
 * devices from the network.
 *
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @author Chris Jackson
 */
public class ZDO_MGMT_LEAVE_REQ extends ZToolPacket /* implements IREQUEST,IZDO */ {
    /// <name>TI.ZPI1.ZDO_MGMT_LEAVE_REQ.DeviceAddress</name>
    /// <summary>The 64 bit IEEE Address of the device you want to leave.</summary>
    private ZToolAddress64 DeviceAddress;
    /// <name>TI.ZPI1.ZDO_MGMT_LEAVE_REQ.DstAddr</name>
    /// <summary>Destination network address.</summary>
    private ZToolAddress16 DstAddr;
    /// <name>TI.ZPI1.ZDO_MGMT_LEAVE_REQ.RemoveChildren</name>
    /// <summary>This field has a value of 1 if the device being asked to leave the network is also being asked to
    /// remove its child devices, if any. Otherwise it has a value of 0. Currently, the stack profile of Home Control
    /// specifies that this field should always be set to 0</summary>
    private int RemoveChildren_Rejoin;

    /// <name>TI.ZPI1.ZDO_MGMT_LEAVE_REQ</name>
    /// <summary>Constructor</summary>
    public ZDO_MGMT_LEAVE_REQ() {
    }

    public ZDO_MGMT_LEAVE_REQ(ZToolAddress16 num1, ZToolAddress64 num2, int flag1) {
        this.DstAddr = num1;
        this.DeviceAddress = num2;
        this.RemoveChildren_Rejoin = flag1;

        int[] framedata = new int[11];
        framedata[0] = this.DstAddr.getLsb();
        framedata[1] = this.DstAddr.getMsb();
        for (int i = 0; i < 8; i++) {
            framedata[2 + i] = this.DeviceAddress.getAddress()[7 - i];
        }
        framedata[10] = this.RemoveChildren_Rejoin;
        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_MGMT_LEAVE_REQ), framedata);
    }

    public ZToolAddress64 getDeviceAddress() {
        return DeviceAddress;
    }

    public void setDeviceAddress(ZToolAddress64 deviceAddress) {
        DeviceAddress = deviceAddress;
    }

    public ZToolAddress16 getDstAddr() {
        return DstAddr;
    }

    public void setDstAddr(ZToolAddress16 dstAddr) {
        DstAddr = dstAddr;
    }

    public int getRemoveChildrenRejoin() {
        return RemoveChildren_Rejoin;
    }

    public void setRemoveChildrenRejoin(int removeChildren_Rejoin) {
        RemoveChildren_Rejoin = removeChildren_Rejoin;
    }

}
