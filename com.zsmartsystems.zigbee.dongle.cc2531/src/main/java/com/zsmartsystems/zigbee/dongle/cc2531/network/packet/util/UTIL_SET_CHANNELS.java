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

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ByteUtils;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;

/**
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @version $LastChangedRevision: 799 $ ($LastChangedDate: 2013-08-06 19:00:05 +0300 (Tue, 06 Aug 2013) $)
 * @since 0.6.0
 */
public class UTIL_SET_CHANNELS extends ZToolPacket/* implements IREQUEST,ISYSTEM*/ {
    /// <name>TI.ZPI1.SYS_SET_CHANNELS.Channels</name>
    /// <summary>Channels.</summary>
    public int Channels;

    /// <name>TI.ZPI1.SYS_SET_CHANNELS</name>
    /// <summary>Constructor</summary>
    public UTIL_SET_CHANNELS() {
    }

    public UTIL_SET_CHANNELS(int zigbee_channels1) {
        this.Channels = zigbee_channels1;

        int[] framedata = new int[]{0, 0, 0, 0};
        int[] bytes = ByteUtils.convertLongtoMultiByte(this.Channels);////////////WARNING!
        int j = 0;
        for (int i = bytes.length - 1; i >= 0; i--) {
            framedata[j] = bytes[i];
            j++;
        }
        super.buildPacket(new DoubleByte(ZToolCMD.UTIL_SET_CHANNELS), framedata);
    }

    public UTIL_SET_CHANNELS(int[] framedata) {

        int[] bytes = new int[4];
        bytes[3] = framedata[0];
        bytes[2] = framedata[1];
        bytes[1] = framedata[2];
        bytes[0] = framedata[3];
        this.Channels = ByteUtils.convertMultiByteToInt(bytes);////////////WARNING!
        super.buildPacket(new DoubleByte(ZToolCMD.UTIL_SET_CHANNELS), framedata);
    }

    /// <name>TI.ZPI1.SYS_SET_CHANNELS.ZIGBEE_CHANNELS</name>
    /// <summary>Channels bitfield</summary>
    public class ZIGBEE_CHANNELS {
        /// <name>TI.ZPI1.SYS_SET_CHANNELS.ZIGBEE_CHANNELS.ALL_CHANNELS</name>
        /// <summary>Channels bitfield</summary>
        public static final int ALL_CHANNELS = 0x7fff800;
        /// <name>TI.ZPI1.SYS_SET_CHANNELS.ZIGBEE_CHANNELS.CHNL_0x00000800</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00000800 = 0x800;
        /// <name>TI.ZPI1.SYS_SET_CHANNELS.ZIGBEE_CHANNELS.CHNL_0x00001000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00001000 = 0x1000;
        /// <name>TI.ZPI1.SYS_SET_CHANNELS.ZIGBEE_CHANNELS.CHNL_0x00002000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00002000 = 0x2000;
        /// <name>TI.ZPI1.SYS_SET_CHANNELS.ZIGBEE_CHANNELS.CHNL_0x00004000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00004000 = 0x4000;
        /// <name>TI.ZPI1.SYS_SET_CHANNELS.ZIGBEE_CHANNELS.CHNL_0x00008000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00008000 = 0x8000;
        /// <name>TI.ZPI1.SYS_SET_CHANNELS.ZIGBEE_CHANNELS.CHNL_0x00010000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00010000 = 0x10000;
        /// <name>TI.ZPI1.SYS_SET_CHANNELS.ZIGBEE_CHANNELS.CHNL_0x00020000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00020000 = 0x20000;
        /// <name>TI.ZPI1.SYS_SET_CHANNELS.ZIGBEE_CHANNELS.CHNL_0x00040000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00040000 = 0x40000;
        /// <name>TI.ZPI1.SYS_SET_CHANNELS.ZIGBEE_CHANNELS.CHNL_0x00080000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00080000 = 0x80000;
        /// <name>TI.ZPI1.SYS_SET_CHANNELS.ZIGBEE_CHANNELS.CHNL_0x00100000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00100000 = 0x100000;
        /// <name>TI.ZPI1.SYS_SET_CHANNELS.ZIGBEE_CHANNELS.CHNL_0x00200000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00200000 = 0x200000;
        /// <name>TI.ZPI1.SYS_SET_CHANNELS.ZIGBEE_CHANNELS.CHNL_0x00400000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00400000 = 0x400000;
        /// <name>TI.ZPI1.SYS_SET_CHANNELS.ZIGBEE_CHANNELS.CHNL_0x00800000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x00800000 = 0x800000;
        /// <name>TI.ZPI1.SYS_SET_CHANNELS.ZIGBEE_CHANNELS.CHNL_0x01000000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x01000000 = 0x1000000;
        /// <name>TI.ZPI1.SYS_SET_CHANNELS.ZIGBEE_CHANNELS.CHNL_0x02000000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x02000000 = 0x2000000;
        /// <name>TI.ZPI1.SYS_SET_CHANNELS.ZIGBEE_CHANNELS.CHNL_0x04000000</name>
        /// <summary>Channels bitfield</summary>
        public static final int CHNL_0x04000000 = 0x4000000;
        /// <name>TI.ZPI1.SYS_SET_CHANNELS.ZIGBEE_CHANNELS.NONE</name>
        /// <summary>Channels bitfield</summary>
        public static final int NONE = 0;
    }
}
