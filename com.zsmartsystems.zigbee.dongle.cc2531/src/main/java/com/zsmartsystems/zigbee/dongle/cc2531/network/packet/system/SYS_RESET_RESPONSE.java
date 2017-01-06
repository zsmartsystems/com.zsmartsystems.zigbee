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
public class SYS_RESET_RESPONSE extends ZToolPacket /*implements IRESPONE,ISYSTEM*/ {
    /// <name>TI.ZPI2.SYS_RESET_RESPONSE.HwRev</name>
    /// <summary>Hardware revision</summary>
    public int HwRev;
    /// <name>TI.ZPI2.SYS_RESET_RESPONSE.MajorRel</name>
    /// <summary>Major release number</summary>
    public int MajorRel;
    /// <name>TI.ZPI2.SYS_RESET_RESPONSE.MinorRel</name>
    /// <summary>Minor release number</summary>
    public int MinorRel;
    /// <name>TI.ZPI2.SYS_RESET_RESPONSE.Product</name>
    /// <summary>Product PROFILE_ID_HOME_AUTOMATION</summary>
    public int Product;
    /// <name>TI.ZPI2.SYS_RESET_RESPONSE.Reason</name>
    /// <summary>Reset reason</summary>
    public int Reason;
    /// <name>TI.ZPI2.SYS_RESET_RESPONSE.TransportRev</name>
    /// <summary>Transport revision</summary>
    public int TransportRev;

    /// <name>TI.ZPI2.SYS_RESET_RESPONSE</name>
    /// <summary>Constructor</summary>
    public SYS_RESET_RESPONSE() {
    }

    /// <name>TI.ZPI2.SYS_RESET_RESPONSE</name>
    /// <summary>Constructor</summary>
    public SYS_RESET_RESPONSE(int[] framedata) {
        this.Reason = framedata[0];
        this.TransportRev = framedata[1];
        this.Product = framedata[2];
        this.MajorRel = framedata[3];
        this.MinorRel = framedata[4];
        this.HwRev = framedata[5];
        super.buildPacket(new DoubleByte(ZToolCMD.SYS_RESET_RESPONSE), framedata);
    }
}
