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
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress16;

/**
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 */
public class ZDO_IEEE_ADDR_REQ extends ZToolPacket /* implements IREQUEST,IZDo */ {
    /// <name>TI.ZPI1.ZDO_IEEE_ADDR_REQ.ReqType</name>
    /// <summary>Request type</summary>
    // public int ReqType;
    /// <name>TI.ZPI1.ZDO_IEEE_ADDR_REQ.ShortAddr</name>
    /// <summary>short address of the destination device</summary>
    // public ZToolAddress16 ShortAddr;
    /// <name>TI.ZPI1.ZDO_IEEE_ADDR_REQ.StartIndex</name>
    /// <summary>Starting index into the list of children. This is used to get more of the list if the list is to large
    /// for one message.</summary>
    // public int StartIndex;

    /// <name>TI.ZPI1.ZDO_IEEE_ADDR_REQ</name>
    /// <summary>Constructor</summary>
    public ZDO_IEEE_ADDR_REQ() {
    }

    public short getShortAddress() {

        return (short) (packet[PAYLOAD_START_INDEX + 0] + (packet[PAYLOAD_START_INDEX + 1] << 8));
    }

    public REQ_TYPE getRequestType() {
        return REQ_TYPE.fromValue(packet[PAYLOAD_START_INDEX + 2]);
    }

    public int getStartIndex() {
        return super.packet[PAYLOAD_START_INDEX + 3];
    }

    /**
     *
     * @param num1
     * @param req_type
     * @param startIdx
     */
    public ZDO_IEEE_ADDR_REQ(ZToolAddress16 num1, int req_type, int startIdx) {
        int[] framedata = new int[4];
        framedata[0] = num1.getLsb();
        framedata[1] = num1.getMsb();
        framedata[2] = req_type;
        framedata[3] = startIdx;
        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_IEEE_ADDR_REQ), framedata);
    }

    /// <name>TI.ZPI1.ZDO_IEEE_ADDR_REQ.REQ_TYPE</name>
    /// <summary>Request type</summary>
    public enum REQ_TYPE {
        EXTENDED(1),
        SINGLE_DEVICE_RESPONSE(0);

        private int value;

        private REQ_TYPE(int v) {
            value = v;
        }

        public int getValue() {
            return value;
        }

        public static REQ_TYPE fromValue(int v) {
            REQ_TYPE[] values = REQ_TYPE.values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].value == v) {
                    return values[i];
                }
            }
            return null;
        }

        /// <name>TI.ZPI1.ZDO_IEEE_ADDR_REQ.REQ_TYPE.EXTENDED</name>
        /// <summary>Request type</summary>
        // public static final int EXTENDED = 1;
        /// <name>TI.ZPI1.ZDO_IEEE_ADDR_REQ.REQ_TYPE.SINGLE_DEVICE_RESPONSE</name>
        /// <summary>Request type</summary>
        // public static final int SINGLE_DEVICE_RESPONSE = 0;
    }

}
