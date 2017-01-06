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
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress64;

/**
 * This message will request the device to send a "Network Address Request".
 * This message sends a broadcast message looking for a 16 bit address with a known
 * 64 bit IEEE address.
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @version $LastChangedRevision: 799 $ ($LastChangedDate: 2013-08-06 19:00:05 +0300 (Tue, 06 Aug 2013) $)
 */
public class ZDO_NWK_ADDR_REQ extends ZToolPacket /*implements IREQUEST,IZDO*/ {
    /// <name>TI.ZPI1.ZDO_NWK_ADDR_REQ.IEEEAddress</name>
    /// <summary>64 bit address bait</summary>
    public ZToolAddress64 IEEEAddress;
    /// <name>TI.ZPI1.ZDO_NWK_ADDR_REQ.ReqType</name>
    /// <summary>Request type</summary>
    public int ReqType;
    /// <name>TI.ZPI1.ZDO_NWK_ADDR_REQ.StartIndex</name>
    /// <summary>Starting index into the list of children.  This is used to get more of the list if the list is to large for one message.</summary>
    public int StartIndex;

    /// <name>TI.ZPI1.ZDO_NWK_ADDR_REQ</name>
    /// <summary>Constructor</summary>
    public ZDO_NWK_ADDR_REQ() {
    }

    public ZDO_NWK_ADDR_REQ(ZToolAddress64 num1, int req_type1, int num2) {
        this.IEEEAddress = num1;
        this.ReqType = req_type1;
        this.StartIndex = num2;

        int[] framedata = new int[10];
        for (int i = 0; i < 8; i++) {
            framedata[i] = this.IEEEAddress.getAddress()[7 - i];
        }
        framedata[8] = this.ReqType;
        framedata[9] = this.StartIndex;

        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_NWK_ADDR_REQ), framedata);
    }

    /// <name>TI.ZPI1.ZDO_NWK_ADDR_REQ.REQ_TYPE</name>
    /// <summary>Request type</summary>
    public class REQ_TYPE {
        /// <name>TI.ZPI1.ZDO_NWK_ADDR_REQ.REQ_TYPE.EXTENDED</name>
        /// <summary>Request type</summary>
        public static final int EXTENDED = 1;
        /// <name>TI.ZPI1.ZDO_NWK_ADDR_REQ.REQ_TYPE.SINGLE_DEVICE_RESPONSE</name>
        /// <summary>Request type</summary>
        public static final int SINGLE_DEVICE_RESPONSE = 0;
    }
}
