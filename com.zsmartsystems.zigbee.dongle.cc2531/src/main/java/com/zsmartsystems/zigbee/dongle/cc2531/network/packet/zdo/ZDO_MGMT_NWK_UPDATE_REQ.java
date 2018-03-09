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

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.Integers;

/**
 * This command is provided to allow updating of network configuration parameters or
 * to request information from devices on network conditions in the local operating
 * environment. Upon receipt, the remote device shall determine from the contents of
 * the ScanDuration parameter whether this request is an update to the ChannelMask and
 * NwkManagerAddr parameters, a channel change command, or a request to scan channels
 * and report the results.
 *
 * @author <a href="mailto:tommmi.s.e.laukkanen@gmail.com">Tommi S.E. Laukkanen</a>
 */
public class ZDO_MGMT_NWK_UPDATE_REQ extends ZToolPacket {
    private final int destinationAddress;
    private final int destinationAddressMode;
    private final int channelMask;
    private final int scanDuration;
    private final int scanCount;
    private final int networkManagerAddress;

    public static final int CHANNEL_MASK_NONE = 0x00000000;
    public static final int CHANNEL_MASK_ALL = 0x07FFF800;
    public static final int CHANNEL_MASK_11 = 0x00000800;
    public static final int CHANNEL_MASK_12 = 0x00001000;
    public static final int CHANNEL_MASK_13 = 0x00002000;
    public static final int CHANNEL_MASK_14 = 0x00004000;
    public static final int CHANNEL_MASK_15 = 0x00008000;
    public static final int CHANNEL_MASK_16 = 0x00010000;
    public static final int CHANNEL_MASK_17 = 0x00020000;
    public static final int CHANNEL_MASK_18 = 0x00040000;
    public static final int CHANNEL_MASK_19 = 0x00080000;
    public static final int CHANNEL_MASK_20 = 0x00100000;
    public static final int CHANNEL_MASK_21 = 0x00200000;
    public static final int CHANNEL_MASK_22 = 0x00400000;
    public static final int CHANNEL_MASK_23 = 0x00800000;
    public static final int CHANNEL_MASK_24 = 0x01000000;
    public static final int CHANNEL_MASK_25 = 0x02000000;
    public static final int CHANNEL_MASK_26 = 0x04000000;

    public ZDO_MGMT_NWK_UPDATE_REQ(final int destinationAddress, final int destinationAddressMode,
            final int channelMask, final int scanDuration, final int scanCount, final int networkManagerAddress) {

        this.destinationAddress = destinationAddress;
        this.destinationAddressMode = destinationAddressMode;
        this.channelMask = channelMask;
        this.scanDuration = scanDuration;
        this.scanCount = scanCount;
        this.networkManagerAddress = networkManagerAddress;

        int[] framedata = new int[11];

        framedata[0] = Integers.getByteAsInteger(this.destinationAddress, 0);
        framedata[1] = Integers.getByteAsInteger(this.destinationAddress, 1);

        framedata[2] = this.destinationAddressMode;

        framedata[3] = Integers.getByteAsInteger(this.channelMask, 0);
        framedata[4] = Integers.getByteAsInteger(this.channelMask, 1);
        framedata[5] = Integers.getByteAsInteger(this.channelMask, 2);
        framedata[6] = Integers.getByteAsInteger(this.channelMask, 3);

        framedata[7] = this.scanDuration;
        framedata[8] = this.scanCount;

        framedata[9] = Integers.getByteAsInteger(this.networkManagerAddress, 0);
        framedata[10] = Integers.getByteAsInteger(this.networkManagerAddress, 1);

        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_MGMT_NWK_UPDATE_REQ), framedata);
    }

    @Override
    public String toString() {
        return "ZDO_MGMT_NWK_UPDATE_REQ{destinationAddress=" + destinationAddress + ", destinationAddressMode="
                + destinationAddressMode + ", channelMask=" + channelMask + ", scanDuration=" + scanDuration
                + ", scanCount=" + scanCount + ", networkManagerAddress=" + networkManagerAddress + '}';
    }
}
