/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
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
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress64;

/**
 * This command is generated to request a Bind.
 *
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @author Chris Jackson
 */
public class ZDO_BIND_REQ extends ZToolPacket /* implements IREQUEST,IZDO */ {

    public ZDO_BIND_REQ(ZToolAddress16 nwkDst, ZToolAddress64 ieeeSrc, int epSrc, DoubleByte cluster,
            int addressingMode, ZToolAddress64 ieeeDst, int epDst) {

        int[] framedata;
        if (addressingMode == 3) {
            framedata = new int[23];
        } else {
            framedata = new int[16];
        }
        framedata[0] = nwkDst.getLsb();
        framedata[1] = nwkDst.getMsb();
        byte[] bytes = ieeeSrc.getAddress();
        for (int i = 0; i < 8; i++) {
            framedata[i + 2] = bytes[7 - i] & 0xFF;
        }
        framedata[10] = epSrc;
        framedata[11] = cluster.getLsb();
        framedata[12] = cluster.getMsb();
        framedata[13] = addressingMode;
        bytes = ieeeDst.getAddress();
        if (addressingMode == 3) {
            for (int i = 0; i < 8; i++) {
                framedata[i + 14] = bytes[7 - i] & 0xFF;
            }
            framedata[22] = epDst;
        } else {
            framedata[14] = bytes[7];
            framedata[15] = bytes[6];
        }

        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_BIND_REQ), framedata);
    }

    public ZDO_BIND_REQ(short nwkDstAdr, short clusterId, long bindSrcAdr, byte bindSrcEP, long bindDstAdr,
            byte bindDstEP) {
        int[] framedata = new int[23];
        framedata[0] = Integers.getByteAsInteger(nwkDstAdr, 0);
        framedata[1] = Integers.getByteAsInteger(nwkDstAdr, 1);
        for (int i = 0; i < 8; i++) {
            framedata[i + 2] = Integers.getByteAsInteger(bindSrcAdr, i);
        }
        framedata[10] = bindSrcEP & 0xFF;
        framedata[11] = Integers.getByteAsInteger(clusterId, 0);
        framedata[12] = Integers.getByteAsInteger(clusterId, 1);
        framedata[13] = ADDRESS_MODE.ADDRESS_64_BIT;
        for (int i = 0; i < 8; i++) {
            framedata[i + 14] = Integers.getByteAsInteger(bindDstAdr, i);
        }
        framedata[22] = bindDstEP & 0xFF;
        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_BIND_REQ), framedata);
    }

    /// <name>TI.ZPI1.ZDO_BIND_REQ.ADDRESS_MODE</name>
    /// <summary>Specified the format of the coordinator address</summary>
    private class ADDRESS_MODE {
        /// <name>TI.ZPI1.ZDO_BIND_REQ.ADDRESS_MODE.ADDRESS_16_BIT</name>
        /// <summary>Specified the format of the coordinator address</summary>
        // private static final int ADDRESS_16_BIT = 2;
        /// <name>TI.ZPI1.ZDO_BIND_REQ.ADDRESS_MODE.ADDRESS_64_BIT</name>
        /// <summary>Specified the format of the coordinator address</summary>
        private static final int ADDRESS_64_BIT = 3;
        /// <name>TI.ZPI1.ZDO_BIND_REQ.ADDRESS_MODE.ADDRESS_NOT_PRESENT</name>
        /// <summary>Specified the format of the coordinator address</summary>
        // private static final int ADDRESS_NOT_PRESENT = 0;
        /// <name>TI.ZPI1.ZDO_BIND_REQ.ADDRESS_MODE.BROADCAST</name>
        /// <summary>Specified the format of the coordinator address</summary>
        // private static final int BROADCAST = 15;
        /// <name>TI.ZPI1.ZDO_BIND_REQ.ADDRESS_MODE.GROUP_ADDRESS</name>
        /// <summary>Specified the format of the coordinator address</summary>
        // private static final int GROUP_ADDRESS = 1;
    }

}
