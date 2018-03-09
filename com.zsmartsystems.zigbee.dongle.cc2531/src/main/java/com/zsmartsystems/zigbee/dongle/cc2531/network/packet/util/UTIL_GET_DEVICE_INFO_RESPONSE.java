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
package com.zsmartsystems.zigbee.dongle.cc2531.network.packet.util;

import java.util.Arrays;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ResponseStatus;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress16;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress64;

/**
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 */
public class UTIL_GET_DEVICE_INFO_RESPONSE extends ZToolPacket /* implements /*IRESPONSE; ISYSTEM */ {
    /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.AssocDevicesList</name>
    /// <summary>Dynamic array; Assoc Devices List</summary>
    private DoubleByte[] AssocDevicesList;
    /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DeviceState</name>
    /// <summary>Device Type</summary>
    private int DeviceState;
    /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DeviceType</name>
    /// <summary>Bitmap byte field indicating device type; where bits 0 to 2 indicate the capability for the device to
    /// operate as a coordinator; router; or end device; respectively</summary>
    private int DeviceType;
    /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.IEEEAddr</name>
    /// <summary>IEEE Address</summary>
    private ZToolAddress64 IEEEAddr;
    /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.NumAssocDevices</name>
    /// <summary>Number Assoc Devices</summary>
    private int NumAssocDevices;
    /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.ShortAddress</name>
    /// <summary>Short Address</summary>
    private ZToolAddress16 ShortAddress;
    /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.Status</name>
    /// <summary>The fail status is returned if the address value in the command message was not within the valid
    /// range.</summary>
    private int Status;

    /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE</name>
    /// <summary>Constructor</summary>
    public UTIL_GET_DEVICE_INFO_RESPONSE() {
        this.AssocDevicesList = new DoubleByte[0xff];
    }

    public UTIL_GET_DEVICE_INFO_RESPONSE(int[] framedata) {

        this.Status = framedata[0];
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            bytes[7 - i] = (byte) framedata[i + 1];
        }
        this.IEEEAddr = new ZToolAddress64(bytes);
        this.ShortAddress = new ZToolAddress16(framedata[9], framedata[10]);
        this.DeviceType = framedata[11];
        this.DeviceState = framedata[12];
        this.NumAssocDevices = framedata[13];
        // AssocDevicesList=new DoubleByte[(framedata.length-14)/2];//Actually more than NumAssocDevices
        AssocDevicesList = new DoubleByte[this.NumAssocDevices];
        for (int i = 0; i < this.AssocDevicesList.length; i++) {
            AssocDevicesList[i] = new DoubleByte(framedata[14 + (i * 2)], framedata[15 + (i * 2)]);
        }

        super.buildPacket(new DoubleByte(ZToolCMD.UTIL_GET_DEVICE_INFO_RESPONSE), framedata);
    }

    @Override
    public String toString() {
        return "UTIL_GET_DEVICE_INFO_RESPONSE{" + "AssocDevicesList=" + Arrays.toString(AssocDevicesList)
                + ", DeviceState=" + DeviceState + ", DeviceType=" + DeviceType + ", IEEEAddr=" + IEEEAddr
                + ", NumAssocDevices=" + NumAssocDevices + ", ShortAddress=" + ShortAddress + ", Status="
                + ResponseStatus.getStatus(Status) + '}';
    }
}
