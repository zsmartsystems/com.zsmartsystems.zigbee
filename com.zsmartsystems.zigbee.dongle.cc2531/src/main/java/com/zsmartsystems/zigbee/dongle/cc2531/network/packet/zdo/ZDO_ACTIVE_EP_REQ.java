/*
   Copyright 2008-2013 CNR-ISTI, http://isti.cnr.it
   Institute of Information Science and Technologies 
   of the Italian National Research Council 

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
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.Integers;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress16;

/**
 * Processes the ACTIVE_EP_REQ packet to get the active endpoints from a node.
 * 
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @version $LastChangedRevision: 799 $ ($LastChangedDate: 2013-08-06 19:00:05 +0300 (Tue, 06 Aug 2013) $)
 */
public class ZDO_ACTIVE_EP_REQ extends ZToolPacket /*implements IREQUEST,IZDO*/ {
    /// <name>TI.ZPI1.ZDO_ACTIVE_EP_REQ.DstAddr</name>
    /// <summary>destination address</summary>

    /// <name>TI.ZPI1.ZDO_ACTIVE_EP_REQ.NWKAddrOfInterest</name>
    /// <summary>NWK address for the request</summary>

    /// <name>TI.ZPI1.ZDO_ACTIVE_EP_REQ</name>
    /// <summary>Constructor</summary>
    public ZDO_ACTIVE_EP_REQ() {
    }

    public ZDO_ACTIVE_EP_REQ(ZToolAddress16 dstAddr, ZToolAddress16 nwkAddrOfInterest) {
        int[] framedata = new int[4];
        framedata[0] = dstAddr.getLsb();
        framedata[1] = dstAddr.getMsb();
        framedata[2] = nwkAddrOfInterest.getLsb();
        framedata[3] = nwkAddrOfInterest.getMsb();

        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_ACTIVE_EP_REQ), framedata);
    }

    public ZDO_ACTIVE_EP_REQ(short nwkAddress) {
        int[] framedata = new int[4];

        framedata[0] = Integers.getByteAsInteger(nwkAddress, 0);
        framedata[1] = Integers.getByteAsInteger(nwkAddress, 1);
        framedata[2] = framedata[0];
        framedata[3] = framedata[1];

        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_ACTIVE_EP_REQ), framedata);
    }

    public ZDO_ACTIVE_EP_REQ(int nwkAddress) {
        int[] framedata = new int[4];

        framedata[0] = Integers.getByteAsInteger((short) nwkAddress, 0);
        framedata[1] = Integers.getByteAsInteger((short) nwkAddress, 1);
        framedata[2] = framedata[0];
        framedata[3] = framedata[1];

        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_ACTIVE_EP_REQ), framedata);
    }

}
