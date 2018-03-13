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

package com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ResponseStatus;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;

/**
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 */
public class AF_REGISTER_SRSP extends ZToolPacket /* implements IRESPONSE, IAF */ {
    /// <name>TI.ZPI1.AF_REGISTER_RESPONSE.Status</name>
    /// <summary>Status</summary>
    public int Status;

    /// <name>TI.ZPI1.AF_REGISTER_RESPONSE</name>
    /// <summary>Constructor</summary>
    public AF_REGISTER_SRSP() {
    }

    public AF_REGISTER_SRSP(int[] framedata) {
        this.Status = framedata[0];
        super.buildPacket(new DoubleByte(ZToolCMD.AF_REGISTER_SRSP), framedata);
    }

    /// <name>TI.ZPI1.AF_REGISTER_SRSP.AF_STATUS</name>
    /// <summary>Status code for AF responses</summary>
    public class AF_STATUS {
        /// <name>TI.ZPI1.AF_REGISTER_SRSP.AF_STATUS.FAILED</name>
        /// <summary>Status code for AF responses</summary>
        public static final int FAILED = 1;
        /// <name>TI.ZPI1.AF_REGISTER_SRSP.AF_STATUS.INVALID_PARAMETER</name>
        /// <summary>Status code for AF responses</summary>
        public static final int INVALID_PARAMETER = 3;
        /// <name>TI.ZPI1.AF_REGISTER_SRSP.AF_STATUS.MEM_FAIL</name>
        /// <summary>Status code for AF responses</summary>
        public static final int MEM_FAIL = 2;
        /// <name>TI.ZPI1.AF_REGISTER_SRSP.AF_STATUS.SUCCESS</name>
        /// <summary>Status code for AF responses</summary>
        public static final int SUCCESS = 0;
    }

    public int getStatus() {
        return this.Status;
    }

    @Override
    public String toString() {
        return "AF_REGISTER_SRSP(Status=" + ResponseStatus.getStatus(Status) + ')';
    }
}
