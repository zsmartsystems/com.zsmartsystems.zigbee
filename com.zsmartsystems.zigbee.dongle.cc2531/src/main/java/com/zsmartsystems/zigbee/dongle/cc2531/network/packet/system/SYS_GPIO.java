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
public class SYS_GPIO extends ZToolPacket /*implements IREQUEST,ISYSTEM*/ {
    public int Operation;
    public int Value;

    public SYS_GPIO() {

    }

    public SYS_GPIO(int num1, int num2) {
        this.Operation = num1;
        this.Value = num2;
        int[] framedata = new int[2];
        framedata[0] = this.Operation;
        framedata[1] = this.Value;
        super.buildPacket(new DoubleByte(ZToolCMD.SYS_GPIO), framedata);
    }

    public class OPERATION {
        public static final int SET_DIRECTION = 0;
        public static final int SET_INPUT_MODE = 1;
        public static final int SET = 2;
        public static final int CLEAR = 3;
        public static final int TOGGLE = 4;
        public static final int READ = 5;
    }

    public class VALUE {
        public static final int GPIO1 = 0x01;
        public static final int GPIO2 = 0x02;
        public static final int GPIO3 = 0x08;
        public static final int GPIO4 = 0x10;
    }

}
