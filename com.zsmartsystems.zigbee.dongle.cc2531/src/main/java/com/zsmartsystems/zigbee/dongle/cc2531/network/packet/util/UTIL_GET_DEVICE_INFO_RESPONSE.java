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

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.*;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress16;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress64;

import java.util.Arrays;

/**
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @version $LastChangedRevision: 799 $ ($LastChangedDate: 2013-08-06 19:00:05 +0300 (Tue, 06 Aug 2013) $)
 * @since 0.6.0
 */
public class UTIL_GET_DEVICE_INFO_RESPONSE extends ZToolPacket /*implements /*IRESPONSE; ISYSTEM*/ {
    /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.AssocDevicesList</name>
    /// <summary>Dynamic array; Assoc Devices List</summary>
    public DoubleByte[] AssocDevicesList;
    /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DeviceState</name>
    /// <summary>Device Type</summary>
    public int DeviceState;
    /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DeviceType</name>
    /// <summary>Bitmap byte field indicating device type; where bits 0 to 2 indicate the capability for the device to operate as a coordinator; router; or end device; respectively</summary>
    public int DeviceType;
    /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.IEEEAddr</name>
    /// <summary>IEEE Address</summary>
    public ZToolAddress64 IEEEAddr;
    /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.NumAssocDevices</name>
    /// <summary>Number Assoc Devices</summary>
    public int NumAssocDevices;
    /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.ShortAddress</name>
    /// <summary>Short Address</summary>
    public ZToolAddress16 ShortAddress;
    /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.Status</name>
    /// <summary>The fail status is returned if the address value in the command message was not within the valid range.</summary>
    public int Status;

    /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE</name>
    /// <summary>Constructor</summary>
    public UTIL_GET_DEVICE_INFO_RESPONSE() {
        this.AssocDevicesList = new DoubleByte[0xff];
    }

    public UTIL_GET_DEVICE_INFO_RESPONSE(int cmd_status1, ZToolAddress64 num1, ZToolAddress16 num2, int device_type1, int device_state1, int num3, DoubleByte[] numArray1) {
        this.Status = cmd_status1;
        this.IEEEAddr = num1;
        this.ShortAddress = num2;
        this.DeviceType = device_type1;
        this.DeviceState = device_state1;
        this.NumAssocDevices = num3;
        this.AssocDevicesList = numArray1;
        /*if (numArray1.length > 0xff)
        {
        throw new Exception("Error creating object.");
        }
        this.AssocDevicesList = new DoubleByte[0xff];
        Array.Copy(numArray1; this.AssocDevicesList; numArray1.length);*/
        int[] framedata = new int[14 + (numArray1.length * 2)];
        framedata[0] = this.Status;
        for (int i = 0; i < 8; i++) {
            framedata[i + 1] = this.IEEEAddr.getAddress()[7 - i];
        }
        framedata[9] = this.ShortAddress.getLsb();
        framedata[10] = this.ShortAddress.getMsb();
        framedata[11] = this.DeviceType;
        framedata[12] = this.DeviceState;
        framedata[13] = this.NumAssocDevices;
        for (int i = 0; i < numArray1.length; i++) {
            framedata[14 + (i * 2)] = numArray1[i].getLsb();
            framedata[14 + (i * 2) + 1] = numArray1[i].getMsb();
        }

        super.buildPacket(new DoubleByte(ZToolCMD.UTIL_GET_DEVICE_INFO_RESPONSE), framedata);
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
        //AssocDevicesList=new DoubleByte[(framedata.length-14)/2];//Actually more than NumAssocDevices
        AssocDevicesList = new DoubleByte[this.NumAssocDevices];
        for (int i = 0; i < this.AssocDevicesList.length; i++) {
            AssocDevicesList[i] = new DoubleByte(framedata[14 + (i * 2)], framedata[15 + (i * 2)]);
        }

        super.buildPacket(new DoubleByte(ZToolCMD.UTIL_GET_DEVICE_INFO_RESPONSE), framedata);
    }


    /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DEVICE_STATE</name>
    /// <summary>Device state</summary>
    public class DEVICE_STATE {
        /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DEVICE_STATE.DEV_COORD_STARTING</name>
        /// <summary>Device state</summary>
        public static final int DEV_COORD_STARTING = 8;
        /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DEVICE_STATE.DEV_END_DEVICE</name>
        /// <summary>Device state</summary>
        public static final int DEV_END_DEVICE = 6;
        /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DEVICE_STATE.DEV_END_DEVICE_UNAUTH</name>
        /// <summary>Device state</summary>
        public static final int DEV_END_DEVICE_UNAUTH = 5;
        /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DEVICE_STATE.DEV_HOLD</name>
        /// <summary>Device state</summary>
        public static final int DEV_HOLD = 0;
        /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DEVICE_STATE.DEV_INIT</name>
        /// <summary>Device state</summary>
        public static final int DEV_INIT = 1;
        /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DEVICE_STATE.DEV_NWK_DISC</name>
        /// <summary>Device state</summary>
        public static final int DEV_NWK_DISC = 2;
        /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DEVICE_STATE.DEV_NWK_JOINING</name>
        /// <summary>Device state</summary>
        public static final int DEV_NWK_JOINING = 3;
        /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DEVICE_STATE.DEV_NWK_ORPHAN</name>
        /// <summary>Device state</summary>
        public static final int DEV_NWK_ORPHAN = 10;
        /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DEVICE_STATE.DEV_NWK_REJOIN</name>
        /// <summary>Device state</summary>
        public static final int DEV_NWK_REJOIN = 4;
        /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DEVICE_STATE.DEV_ROUTER</name>
        /// <summary>Device state</summary>
        public static final int DEV_ROUTER = 7;
        /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DEVICE_STATE.DEV_ZB_COORD</name>
        /// <summary>Device state</summary>
        public static final int DEV_ZB_COORD = 9;
    }

    /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DEVICE_TYPE</name>
    /// <summary>Device type bitfield</summary>
    public class DEVICE_TYPE {
        /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DEVICE_TYPE.COORDINATOR</name>
        /// <summary>Device type bitfield</summary>
        public static final int COORDINATOR = 1;
        /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DEVICE_TYPE.END_DEVICE</name>
        /// <summary>Device type bitfield</summary>
        public static final int END_DEVICE = 4;
        /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DEVICE_TYPE.NONE</name>
        /// <summary>Device type bitfield</summary>
        public static final int NONE = 0;
        /// <name>TI.ZPI1.SYS_GET_DEVICE_INFO_RESPONSE.DEVICE_TYPE.ROUTER</name>
        /// <summary>Device type bitfield</summary>
        public static final int ROUTER = 2;
    }

    @Override
    public String toString() {
        return "UTIL_GET_DEVICE_INFO_RESPONSE{" +
                "AssocDevicesList=" + Arrays.toString(AssocDevicesList) +
                ", DeviceState=" + DeviceState +
                ", DeviceType=" + DeviceType +
                ", IEEEAddr=" + IEEEAddr +
                ", NumAssocDevices=" + NumAssocDevices +
                ", ShortAddress=" + ShortAddress +
                ", Status=" + ResponseStatus.getStatus(Status) +
                '}';
    }
}
