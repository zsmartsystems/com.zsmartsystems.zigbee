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
public class SYS_TEST_RF extends ZToolPacket /*implemtns IREQUEST,ISYSTEM*/ {
    public int TestMode;
    public int Frequency;
    public int TxPower;

    public SYS_TEST_RF() {

    }

    public SYS_TEST_RF(int num1, int num2, int num3) {
        this.TestMode = num1;
        this.Frequency = num2;
        this.TxPower = num3;
        int[] framedata = new int[3];
        framedata[0] = this.TestMode;
        framedata[1] = this.Frequency;
        framedata[2] = this.TxPower;
        super.buildPacket(new DoubleByte(ZToolCMD.SYS_TEST_RF), framedata);
    }

    public class TEST_MODE {
        public static final int UNMODULATED = 0;
        public static final int PSEUDO_RANDOM = 1;
        public static final int RECEIVE = 2;
    }

    public class TX_POWER {
        public static final int DBM_0_6 = 0xff;
        public static final int DBM_0_5 = 0xdf;
        public static final int DBM_0_3 = 0xbf;
        public static final int DBM_0_2 = 0x9f;
        public static final int DBM_MIN_0_1 = 0x7f;
        public static final int DBM_MIN_0_4 = 0x5f;
        public static final int DBM_MIN_0_9 = 0x3f;
        public static final int DBM_MIN_1_5 = 0x1f;
        public static final int DBM_MIN_2_7 = 0x1b;
        public static final int DBM_MIN_4_0 = 0x17;
        public static final int DBM_MIN_5_7 = 0x13;
        public static final int DBM_MIN_7_9 = 0x0f;
        public static final int DBM_MIN_10_8 = 0x0b;
        public static final int DBM_MIN_15_4 = 0x07;
        public static final int DBM_MIN_18_6 = 0x06;
        public static final int DBM_MIN_25_2 = 0x03;

    }

}
