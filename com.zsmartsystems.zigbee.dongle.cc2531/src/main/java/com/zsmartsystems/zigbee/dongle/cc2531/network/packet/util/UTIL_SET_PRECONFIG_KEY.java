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
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;

/**
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @version $LastChangedRevision: 799 $ ($LastChangedDate: 2013-08-06 19:00:05 +0300 (Tue, 06 Aug 2013) $)
 * @since 0.6.0
 */
public class UTIL_SET_PRECONFIG_KEY extends ZToolPacket /*implements IREQUEST,ISYSTEM*/ {
    /// <name>TI.ZPI1.SYS_SET_PRECONFIG_KEY.PreConfigKey</name>
    /// <summary>PreConfigKey.</summary>
    public int[] PreConfigKey;

    /// <name>TI.ZPI1.SYS_SET_PRECONFIG_KEY</name>
    /// <summary>Constructor</summary>
    public UTIL_SET_PRECONFIG_KEY() {
        this.PreConfigKey = new int[0x10];
    }

    /// <name>TI.ZPI1.SYS_SET_PRECONFIG_KEY</name>
    /// <summary>Constructor</summary>
    public UTIL_SET_PRECONFIG_KEY(int[] buffer1) {
        this.PreConfigKey = new int[0x10];
        this.PreConfigKey = buffer1;
        /*if (buffer1.Length > 0x10)
        {
        throw new Exception("Error creating object.");
        }
        this.PreConfigKey = new byte[0x10];
        Array.Copy(buffer1, this.PreConfigKey, buffer1.Length);*/

        int[] framedata = new int[buffer1.length];

        for (int i = 0; i < buffer1.length; i++) {
            framedata[i] = buffer1[(buffer1.length - 1) - i];
        }

        super.buildPacket(new DoubleByte(ZToolCMD.UTIL_SET_PRECONFIG_KEY), framedata);
    }
}
