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
public class SYS_ADC_READ extends ZToolPacket /*implements IREQUEST,ISYSTEM*/ {
    /// <name>TI.ZPI2.SYS_ADC_READ.Channel</name>
    /// <summary>Channel:  0x00-0x07 = AIN0 - AIN7; 0x0E = temp; 0x0F = voltage</summary>
    public int Channel;
    /// <name>TI.ZPI2.SYS_ADC_READ.Resolution</name>
    /// <summary>Resolution:  1 = 8-bit; 2 = 10-bit; 3 = 12-bit; 4 = 14-bit</summary>
    public int Resolution;

    /// <name>TI.ZPI2.SYS_ADC_READ</name>
    /// <summary>Constructor</summary>
    public SYS_ADC_READ() {
    }

    /// <name>TI.ZPI2.SYS_ADC_READ</name>
    /// <summary>Constructor</summary>
    public SYS_ADC_READ(int num1, int num2) {
        this.Channel = num1;
        this.Resolution = num2;
        int[] framedata = {num1, num2};
        super.buildPacket(new DoubleByte(ZToolCMD.SYS_ADC_READ), framedata);
    }

    public class CHANNEL {
        public static final int AIN0 = 0x06;
        public static final int AIN1 = 0x07;
        public static final int AIN0_1 = 0x0B;
        public static final int TEMPERATURE = 0x0E;
        public static final int VOLTAGE = 0x0F;
    }

    public class RESOLUTION {
        public static final int BITS7 = 0x00;
        public static final int BITS9 = 0x01;
        public static final int BITS10 = 0x02;
        public static final int BITS12 = 0x03;
    }
}
