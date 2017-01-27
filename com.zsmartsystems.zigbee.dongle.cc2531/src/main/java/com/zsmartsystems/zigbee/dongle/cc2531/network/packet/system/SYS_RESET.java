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

package com.zsmartsystems.zigbee.dongle.cc2531.network.packet.system;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;

/**
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 */
public class SYS_RESET extends ZToolPacket /* implements IREQUEST,ISYSTEM */ {
    /// <name>TI.ZPI1.SYS_RESET.Type</name>
    /// <summary>requests a target device reset (0) or port bootloader reset (1). If the target device does not support
    /// port bootloading, bootloader reset commands are ignored and no response is sent from the target.</summary>
    private int Type;

    public SYS_RESET(int reset_type1) {
        this.Type = reset_type1;

        int[] framedata = new int[1];
        framedata[0] = this.Type;

        super.buildPacket(new DoubleByte(ZToolCMD.SYS_RESET), framedata);
    }

    /// <name>TI.ZPI1.SYS_RESET.RESET_TYPE</name>
    /// <summary>Reset type</summary>
    public class RESET_TYPE {
        /// <name>TI.ZPI1.SYS_RESET.RESET_TYPE.SERIAL_BOOTLOADER</name>
        /// <summary>Reset type</summary>
        public static final int SERIAL_BOOTLOADER = 1;
        /// <name>TI.ZPI1.SYS_RESET.RESET_TYPE.TARGET_DEVICE</name>
        /// <summary>Reset type</summary>
        public static final int TARGET_DEVICE = 0;
    }
}
