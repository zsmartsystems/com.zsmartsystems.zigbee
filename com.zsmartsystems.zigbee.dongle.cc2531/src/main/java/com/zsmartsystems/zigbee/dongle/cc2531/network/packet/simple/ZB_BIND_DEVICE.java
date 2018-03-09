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
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress64;

/**
 * This command is used to create or delete a 'binding' to another device on the network.
 * Once bound, an application can send messages to a device by referencing the commandId
 * for the binding. This command can also be issued with a NULL destination address (set
 * to all zeros). In that case, a binding will be established with another device that is
 * in the Allow Bind mode.
 * 
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 */
public class ZB_BIND_DEVICE extends ZToolPacket /* implements IREQUEST, ISIMPLEAPI */ {
    /// <name>TI.ZPI2.ZB_BIND_DEVICE.Action</name>
    /// <summary>CREATE or DELETE a binding entry.</summary>
    public int Action;
    /// <name>TI.ZPI2.ZB_BIND_DEVICE.CommandId</name>
    /// <summary>The Command identifier of packets for this binding.</summary>
    public DoubleByte CommandId;
    /// <name>TI.ZPI2.ZB_BIND_DEVICE.Destination</name>
    /// <summary>IEEE address of device to establish the binding with ( all zeros indicate NULL ).</summary>
    public ZToolAddress64 Destination;

    /// <name>TI.ZPI2.ZB_BIND_DEVICE</name>
    /// <summary>Constructor</summary>
    public ZB_BIND_DEVICE() {
    }

    public ZB_BIND_DEVICE(int bind_action_type1, DoubleByte num1, ZToolAddress64 num2) {
        this.Action = bind_action_type1;
        this.CommandId = num1;
        this.Destination = num2;
        int[] framedata = new int[11];
        framedata[0] = this.Action;
        framedata[1] = this.CommandId.getLsb();
        framedata[2] = this.CommandId.getMsb();
        byte[] bytes = Destination.getAddress();
        for (int i = 0; i < 8; i++) {
            framedata[i + 2] = bytes[7 - i];
        }
        super.buildPacket(new DoubleByte(ZToolCMD.ZB_BIND_DEVICE), framedata);
    }

    /// <name>TI.ZPI2.ZB_BIND_DEVICE.BIND_ACTION_TYPE</name>
    /// <summary>Reset type</summary>
    public class BIND_ACTION_TYPE {
        /// <name>TI.ZPI2.ZB_BIND_DEVICE.BIND_ACTION_TYPE.CREATE_BIND</name>
        /// <summary>Reset type</summary>
        public static final int CREATE_BIND = 1;
        /// <name>TI.ZPI2.ZB_BIND_DEVICE.BIND_ACTION_TYPE.DELETE_BIND</name>
        /// <summary>Reset type</summary>
        public static final int DELETE_BIND = 0;
    }

}
