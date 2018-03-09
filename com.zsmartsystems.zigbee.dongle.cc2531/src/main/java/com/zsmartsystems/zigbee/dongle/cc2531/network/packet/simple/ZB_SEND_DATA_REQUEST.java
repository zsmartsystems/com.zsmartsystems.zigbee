/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
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

package com.zsmartsystems.zigbee.dongle.cc2531.network.packet.simple;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress16;

/**
 * This command initiates transmission of data to another device in the network.
 * This command can only be issued after the application processor has registered its
 * application using the ZB_APP_REGISTER_REQUEST and the device has successfully created
 * or joined a network.
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @version $LastChangedRevision: 799 $ ($LastChangedDate: 2013-08-06 19:00:05 +0300 (Tue, 06 Aug 2013) $)
 */
public class ZB_SEND_DATA_REQUEST extends ZToolPacket /*implements IREQUEST,ISIMPLEAPI*/ {
    /// <name>TI.ZPI2.ZB_SEND_DATA_REQUEST.CommandId</name>
    /// <summary>Command identifier of the data packet.</summary>
    public DoubleByte CommandId;
    /// <name>TI.ZPI2.ZB_SEND_DATA_REQUEST.Destination</name>
    /// <summary>Short address of destination device ( 0xFFFE indicates NULL address and a binding must have been established previously for this command identifier. Broadcast addresses include 0xFFFF for all devices, 0xFFFC for all routers/coordinator devices and 0xFFFD for all devices with receiver turned ON ).</summary>
    public ZToolAddress16 Destination;
    /// <name>TI.ZPI2.ZB_SEND_DATA_REQUEST.Handle</name>
    /// <summary>Identifier for this data send operation.</summary>
    public int Handle;
    /// <name>TI.ZPI2.ZB_SEND_DATA_REQUEST.PayloadLen</name>
    /// <summary>Size in bytes of the data packet payload.</summary>
    public int PayloadLen;
    /// <name>TI.ZPI2.ZB_SEND_DATA_REQUEST.PayloadValue</name>
    /// <summary>Packet payload.</summary>
    public int[] PayloadValue;
    /// <name>TI.ZPI2.ZB_SEND_DATA_REQUEST.Radius</name>
    /// <summary>Max number of hops the data packet can travel ( set to 0 to use default value ).</summary>
    public int Radius;
    /// <name>TI.ZPI2.ZB_SEND_DATA_REQUEST.TxOptions</name>
    /// <summary>Bit mask of transmission options ( 0x10 for end-to-end acknowledgements and retransmissions ).</summary>
    public int TxOptions;

    /// <name>TI.ZPI2.ZB_SEND_DATA_REQUEST</name>
    /// <summary>Constructor</summary>
    public ZB_SEND_DATA_REQUEST() {
        this.PayloadValue = new int[0xff];
    }

    /**
     * Create the ZB_SEND_DATA_REQUEST packet.
     * <p>
     * Address can be one of the following -:
     * <ul>
     * <li>0 - 0xFFF7: 16-bit short address of the destination device</li>
     * <li>0xFFFC: Group of all routers and coordinator</li>
     * <li>0xFFFD: Group of all devices with receiver turned on</li>
     * <li>0xFFFE: This is the binding address and should be used when a binding entry has
     * been previously created for this particular CommandId. The destination address
     * will be determined from the binding table by the CC2530-ZNP
     * <li>0xFFFF: Broadcast group of all devices in the network</li>
     * </ul>
     * @param destination The destination address of the data packet.
     * @param commandId The command ID to send with the message.
     * @param handle A handle used to identify the send data request.
     *              The corresponding ZB_SEND_DATA_CONFIRM will have the same handle value.
     *              This can be useful if the application wishes to match up
     *              ZB_SEND_DATA_REQUESTs with ZB_SEND_DATA_CONFIRMs
     * @param txoptions_type1
     * @param radius The max number of hops the packet can travel through before it is dropped.
     * @param payloadLen Specifies the size of the Data buffer in bytes.
     * @param buffer1 Data. Without any security (99 bytes), with NWK security (81 bytes), with NWK and APS security (64 bytes).
     */
    public ZB_SEND_DATA_REQUEST(ZToolAddress16 destination, DoubleByte commandId, int handle, int txoptions_type1, int radius, int payloadLen, int[] buffer1) {
        this.Destination = destination;
        this.CommandId = commandId;
        this.Handle = handle;
        this.TxOptions = txoptions_type1;
        this.Radius = radius;
        this.PayloadLen = payloadLen;
        this.PayloadValue = new int[buffer1.length];
        this.PayloadValue = buffer1;
            /*if (buffer1.Length > 0xff)
            {
                throw new Exception("Error creating object.");
            }
            this.PayloadValue = new byte[0xff];
            Array.Copy(buffer1, this.PayloadValue, buffer1.Length);*/
        int[] framedata = new int[this.PayloadValue.length + 8];
        framedata[0] = this.Destination.getLsb();
        framedata[1] = this.Destination.getMsb();
        framedata[2] = this.CommandId.getLsb();
        framedata[3] = this.CommandId.getMsb();
        framedata[4] = this.Handle;
        framedata[5] = this.TxOptions;
        framedata[6] = this.Radius;
        framedata[7] = this.PayloadLen;
        for (int i = 0; i < this.PayloadValue.length; i++) {
            framedata[i + 8] = this.PayloadValue[i];
        }
        super.buildPacket(new DoubleByte(ZToolCMD.ZB_SEND_DATA_REQUEST), framedata);

    }

    /// <name>TI.ZPI2.ZB_SEND_DATA_REQUEST.TXOPTIONS_TYPE</name>
    /// <summary>Reset type</summary>
    public class TXOPTIONS_TYPE {
        /// <name>TI.ZPI2.ZB_SEND_DATA_REQUEST.TXOPTIONS_TYPE.END-TO-END-ACK</name>
        /// <summary>Reset type</summary>
        public static final int END_TO_END_ACK = 0x10;
        /// <name>TI.ZPI2.ZB_SEND_DATA_REQUEST.TXOPTIONS_TYPE.NONE</name>
        /// <summary>Reset type</summary>
        public static final int NONE = 0;
    }

}
