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

package com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ResponseStatus;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress16;

/**
 * Processes the Node Descriptor response. Only a single Node Descriptor is
 * available for each node.
 * <p>
 * The node descriptor contains information about the capabilities of the ZigBee
 * node and is mandatory for each node. There shall be only one node descriptor
 * in a node.
 * 
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @author <a href="mailto:chris@cd-jackson.com">Chris Jackson</a>
 */
public class ZDO_NODE_DESC_RSP extends ZToolPacket /*implements IRESPONSE_CALLBACK,IZDO*/ {
    /**
     * Node Flags assigned for APS. For V1.0 all bits are reserved
     */
    public int APSFlags;
    /**
     * Indicates size of maximum NPDU. This field is used as a high level indication for api
     */
    public int BufferSize;
    /**
     * Capability flags stored for the MAC
     */
    public int Capabilities;
    /**
     * Indicates if complex descriptor is available for the node
     */
    public boolean ComplexDescriptorAvailable;
    /**
     * Specifies a manufacturer code that is allocated by ZigBee Alliance, relating to the manufacturer to the device
     */
    public DoubleByte ManufacturerCode;
    /**
     * Node type
     */
    public int NodeType;
    /**
     * Device's short address of this Node descriptor
     */
    public ZToolAddress16 nwkAddr;
    /**
     * Specifies the system server capability
     */
    public int ServerMask;
    /**
     * the message's source network address.
     */
    public ZToolAddress16 SrcAddress;
    /**
     * this field indicates either SUCCESS or FAILURE.
     */
    public int Status;
    /**
     * Indicates maximum size of Transfer up to 0x7fff (This field is reserved in version 1.0 and shall be set to zero ).
     */
    public DoubleByte TransferSize;
    /**
     * Indicates if user descriptor is available for the node
     */
    public boolean UserDescriptorAvailable;
    public int FreqBand;

    /**
     * Constructor
     */
    public ZDO_NODE_DESC_RSP() {
    }

    public ZDO_NODE_DESC_RSP(int[] framedata) {
        this.SrcAddress = new ZToolAddress16(framedata[1], framedata[0]);
        this.Status = framedata[2];
        this.nwkAddr = new ZToolAddress16(framedata[4], framedata[3]);
        this.NodeType = framedata[5] & (0x07);///Experimental
        this.ComplexDescriptorAvailable = ((framedata[5] & (0x08)) >>> 3) == 1;
        this.UserDescriptorAvailable = ((framedata[5] & (16)) >>> 4) == 1;
        this.APSFlags = framedata[6] & (0x0F);
        this.FreqBand = framedata[6] & (0xF0) >>> 4;
        this.Capabilities = framedata[10];
        this.ManufacturerCode = new DoubleByte(framedata[12], framedata[11]);
        this.BufferSize = framedata[13];
        this.TransferSize = new DoubleByte(framedata[15], framedata[14]);
        this.ServerMask = new DoubleByte(framedata[17], framedata[16]).get16BitValue();
        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_NODE_DESC_RSP), framedata);
    }

    /**
     * Capability Information bitfield
     */
    public class CAPABILITY_INFO {
        /**
         * Capability Information bitfield
         */
        public static final int ALLOCATE_ADDRESS = 0x80;
        /**
         * Capability Information bitfield
         */
        public static final int ALTER_PAN_COORD = 1;
        /**
         * Capability Information bitfield
         */
        public static final int DEVICE_TYPE = 2;
        /**
         * Capability Information bitfield
         */
        public static final int NONE = 0;
        /**
         * Capability Information bitfield
         */
        public static final int POWER_SOURCE = 4;
        /**
         * Capability Information bitfield
         */
        public static final int RECEIVER_ON_WHEN_IDLE = 8;
        /**
         * Capability Information bitfield</summary>
         */
        public static final int SECURITY_CAPABILITY = 0x40;
    }


    /**
     * Type of Node
     */
    public class NODE_TYPE {
        /**
         * Type of Node
         */
        public static final int COORDINATOR = 0;
        /**
         * Type of Node
         */
        public static final int END_DEVICE = 2;
        /*
         * Type of Node
         */
        public static final int ROUTER = 1;
    }

    /**
     * Capabilities bitfield
     */
    public class SERVER_CAPABILITY {
        /**
         * Capabilities bitfield
         */
        public static final int BACKUP_TRUST_CENTER = 2;
        /**
         * Capabilities bitfield
         */
        public static final int BAK_BIND_TABLE_CACHE = 8;
        /**
         * Capabilities bitfield
         */
        public static final int BAK_DISC_CACHE = 50;
        /**
         * Capabilities bitfield
         */
        public static final int NONE = 0;
        /**
         * Capabilities bitfield
         */
        public static final int PRIM_BIND_TABLE_CACHE = 4;
        /**
         * Capabilities bitfield
         */
        public static final int PRIM_DISC_CACHE = 0x16;
        /**
         * Capabilities bitfield
         */
        public static final int PRIM_TRUST_CENTER = 1;
    }

    @Override
    public String toString() {
        return "ZDO_NODE_DESC_RSP{" +
                "APSFlags=" + APSFlags +
                ", BufferSize=" + BufferSize +
                ", Capabilities=" + String.format("0x%02X", Capabilities) +
                ", ComplexDescriptorAvailable=" + ComplexDescriptorAvailable +
                ", ManufacturerCode=" + ManufacturerCode +
                ", NodeType=" + NodeType +
                ", nwkAddr=" + nwkAddr +
                ", ServerMask=" + String.format("0x%02X", ServerMask) +
                ", SrcAddress=" + SrcAddress +
                ", Status=" + ResponseStatus.getStatus(Status) +
                ", TransferSize=" + TransferSize +
                ", UserDescriptorAvailable=" + UserDescriptorAvailable +
                ", FreqBand=" + FreqBand +
                '}';
    }
}
