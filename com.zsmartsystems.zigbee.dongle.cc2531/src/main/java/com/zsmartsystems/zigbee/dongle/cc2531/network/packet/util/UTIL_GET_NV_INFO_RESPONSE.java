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

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ResponseStatus;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ByteUtils;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress64;

import java.util.Arrays;

/**
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @version $LastChangedRevision: 799 $ ($LastChangedDate: 2013-08-06 19:00:05 +0300 (Tue, 06 Aug 2013) $)
 * @since 0.6.0
 */
public class UTIL_GET_NV_INFO_RESPONSE extends ZToolPacket /*implements IREQUEST, ISYSTEM*/ {
    /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.IEEEAddress</name>
    /// <summary>IEEE Address</summary>
    public ZToolAddress64 IEEEAddress;
    /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.PanID</name>
    /// <summary>Pan PROFILE_ID_HOME_AUTOMATION</summary>
    public int PanID;
    /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.PreConfigKey</name>
    /// <summary>PreConfigKey</summary>
    public int[] PreConfigKey;
    /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ScanChannels</name>
    /// <summary>Scan Channels</summary>
    public int ScanChannels;
    /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.SecurityMode</name>
    /// <summary>This specifies the network messaging security mode, zero disables security</summary>
    public int SecurityMode;
    /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.Status</name>
    /// <summary>Status</summary>
    public int Status;

    /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE</name>
    /// <summary>Constructor</summary>
    public UTIL_GET_NV_INFO_RESPONSE() {
        this.PreConfigKey = new int[0x10];
    }

    public UTIL_GET_NV_INFO_RESPONSE(int cmd_status1, ZToolAddress64 num1, int zigbee_channels1, int num2, int num3, int[] buffer1) {
        this.Status = cmd_status1;
        this.IEEEAddress = num1;
        this.ScanChannels = zigbee_channels1;
        this.PanID = num2;
        this.SecurityMode = num3;
        this.PreConfigKey = new int[buffer1.length];
        this.PreConfigKey = buffer1;
        /*if (buffer1.length > 0x10) {
        throw new Exception("Error creating object.");
        }
        this.PreConfigKey = new byte[0x10];
        Array.Copy(buffer1, this.PreConfigKey, buffer1.Length);*/
        int[] framedata = new int[15 + buffer1.length];
        framedata[0] = this.Status;
        for (int i = 0; i < 8; i++) {
            framedata[i + 1] = this.IEEEAddress.getAddress()[7 - i];
        }
        int[] bytes = new int[4];
        bytes = ByteUtils.convertLongtoMultiByte(this.ScanChannels);////////////WARNING!
        framedata[9] = bytes[3];
        framedata[10] = bytes[2];
        framedata[11] = bytes[1];
        framedata[12] = bytes[0];
        framedata[13] = this.PanID;
        framedata[14] = this.SecurityMode;

        for (int i = 0; i < buffer1.length; i++) {
            framedata[i + 15] = buffer1[(buffer1.length - 1) - i];
        }

        super.buildPacket(new DoubleByte(ZToolCMD.UTIL_GET_NV_INFO_RESPONSE), framedata);
    }

    public UTIL_GET_NV_INFO_RESPONSE(int[] framedata) {

        this.Status = framedata[0];
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            bytes[7 - i] = (byte) framedata[i + 1];
        }
        this.IEEEAddress = new ZToolAddress64(bytes);
        int[] bytes2 = new int[4];

        bytes2[3] = framedata[9];
        bytes2[2] = framedata[10];
        bytes2[1] = framedata[11];
        bytes2[0] = framedata[12];
        this.ScanChannels = ByteUtils.convertMultiByteToInt(bytes2);////////////WARNING!
        this.PanID = framedata[13];
        this.SecurityMode = framedata[14];

        this.PreConfigKey = new int[0x10];
        for (int i = 0; i < this.PreConfigKey.length; i++) {
            this.PreConfigKey[(this.PreConfigKey.length - 1) - i] = framedata[i + 15];
        }

        super.buildPacket(new DoubleByte(ZToolCMD.UTIL_GET_NV_INFO_RESPONSE), framedata);
    }

    /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ZIGBEE_CHANNELS</name>
    /// <summary>Channels bitfield</summary>
    public class ZIGBEE_CHANNELS {
        /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ZIGBEE_CHANNELS.ALL_CHANNELS</name>
        /// <summary>Channels bitfield</summary>
        public static final int ALL_CHANNELS = 0x7fff800;
        /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ZIGBEE_CHANNELS.CHNL_0x00000800</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00000800 = 0x800;
        /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ZIGBEE_CHANNELS.CHNL_0x00001000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00001000 = 0x1000;
        /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ZIGBEE_CHANNELS.CHNL_0x00002000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00002000 = 0x2000;
        /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ZIGBEE_CHANNELS.CHNL_0x00004000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00004000 = 0x4000;
        /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ZIGBEE_CHANNELS.CHNL_0x00008000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00008000 = 0x8000;
        /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ZIGBEE_CHANNELS.CHNL_0x00010000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00010000 = 0x10000;
        /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ZIGBEE_CHANNELS.CHNL_0x00020000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00020000 = 0x20000;
        /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ZIGBEE_CHANNELS.CHNL_0x00040000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00040000 = 0x40000;
        /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ZIGBEE_CHANNELS.CHNL_0x00080000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00080000 = 0x80000;
        /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ZIGBEE_CHANNELS.CHNL_0x00100000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00100000 = 0x100000;
        /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ZIGBEE_CHANNELS.CHNL_0x00200000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00200000 = 0x200000;
        /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ZIGBEE_CHANNELS.CHNL_0x00400000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00400000 = 0x400000;
        /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ZIGBEE_CHANNELS.CHNL_0x00800000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00800000 = 0x800000;
        /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ZIGBEE_CHANNELS.CHNL_0x01000000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x01000000 = 0x1000000;
        /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ZIGBEE_CHANNELS.CHNL_0x02000000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x02000000 = 0x2000000;
        /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ZIGBEE_CHANNELS.CHNL_0x04000000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x04000000 = 0x4000000;
        /// <name>TI.ZPI1.SYS_GET_NV_INFO_RESPONSE.ZIGBEE_CHANNELS.NONE</name>
        /// <summary>Channels bitfield</summary>
        public static final int NONE = 0;
    }

    @Override
    public String toString() {
        return "UTIL_GET_NV_INFO_RESPONSE{" +
                "IEEEAddress=" + IEEEAddress +
                ", PanID=" + PanID +
                ", PreConfigKey=" + Arrays.toString(PreConfigKey) +
                ", ScanChannels=" + ScanChannels +
                ", SecurityMode=" + SecurityMode +
                ", Status=" + ResponseStatus.getStatus(Status) +
                '}';
    }
}
