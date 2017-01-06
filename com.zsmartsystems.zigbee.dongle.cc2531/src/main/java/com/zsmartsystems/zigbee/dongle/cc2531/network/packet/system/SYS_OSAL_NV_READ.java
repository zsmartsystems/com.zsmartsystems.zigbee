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
 * @version $LastChangedRevision: 799 $ ($LastChangedDate: 2013-08-06 19:00:05 +0300 (Tue, 06 Aug 2013) $)
 */
public class SYS_OSAL_NV_READ extends ZToolPacket /*implements IREQUEST, ISYSTEM*/ {
    /// <name>TI.ZPI2.SYS_OSAL_NV_READ.Id</name>
    /// <summary>Attribute PROFILE_ID_HOME_AUTOMATION</summary>
    public DoubleByte Id;
    /// <name>TI.ZPI2.SYS_OSAL_NV_READ.Offset</name>
    /// <summary>Memory offset into item (up to 250)</summary>
    public int Offset;

    /// <name>TI.ZPI2.SYS_OSAL_NV_READ</name>
    /// <summary>Constructor</summary>
    public SYS_OSAL_NV_READ() {
    }

    /// <name>TI.ZPI2.SYS_OSAL_NV_READ</name>
    /// <summary>Constructor</summary>
    public SYS_OSAL_NV_READ(DoubleByte num1, int num2) {
        this.Id = num1;
        this.Offset = num2;
        int[] framedata = new int[3];
        framedata[0] = this.Id.getLsb();
        framedata[1] = this.Id.getMsb();
        framedata[2] = this.Offset;
        super.buildPacket(new DoubleByte(ZToolCMD.SYS_OSAL_NV_READ), framedata);
    }

    public class ID {
        public static final int ZP_NV_APP_ITEM_1 = 0x0300;
        public static final int ZP_NV_APP_ITEM_2 = 0x0301;
        public static final int ZP_NV_APP_ITEM_3 = 0x0302;
        public static final int ZP_NV_APP_ITEM_4 = 0x0303;
        public static final int ZP_NV_APP_ITEM_5 = 0x0304;
        public static final int ZP_NV_APP_ITEM_6 = 0x0305;
    }

}
