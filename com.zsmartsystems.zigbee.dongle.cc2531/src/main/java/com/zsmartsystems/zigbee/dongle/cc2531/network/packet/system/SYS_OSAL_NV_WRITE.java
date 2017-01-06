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
public class SYS_OSAL_NV_WRITE extends ZToolPacket /*implements IREQUEST,ISYSTEM*/ {
    /// <name>TI.ZPI2.SYS_OSAL_NV_WRITE.Id</name>
    /// <summary>indicates the PROFILE_ID_HOME_AUTOMATION of the NV item to be written.</summary>
    public DoubleByte Id;
    /// <name>TI.ZPI2.SYS_OSAL_NV_WRITE.Len</name>
    /// <summary>Number of bytes in item (up to 250)</summary>
    public int Len;
    /// <name>TI.ZPI2.SYS_OSAL_NV_WRITE.Offset</name>
    /// <summary>Memory offset into item (up to 250)</summary>
    public int Offset;
    /// <name>TI.ZPI2.SYS_OSAL_NV_WRITE.Value</name>
    /// <summary>Dynamic array, requires memory allocation.  Contains the data value that is to be written to the location.</summary>
    public int[] Value;

    /// <name>TI.ZPI2.SYS_OSAL_NV_WRITE</name>
    /// <summary>Constructor</summary>
    public SYS_OSAL_NV_WRITE() {
        this.Value = new int[0xff];
    }

    /// <name>TI.ZPI2.SYS_OSAL_NV_WRITE</name>
    /// <summary>Constructor</summary>
    public SYS_OSAL_NV_WRITE(DoubleByte num1, int num2, int num3, int[] buffer1) {
        this.Id = num1;
        this.Offset = num2;
        this.Len = num3;
        this.Value = new int[buffer1.length];
        this.Value = buffer1;
        /*if (buffer1.Length > 0xff)
        {
        throw new Exception("Error creating object.");
        }
        this.Value = new byte[0xff];
        Array.Copy(buffer1, this.Value, buffer1.Length);*/
        int[] framedata = new int[buffer1.length + 4];
        framedata[0] = this.Id.getLsb();
        framedata[1] = this.Id.getMsb();
        framedata[2] = this.Offset;
        framedata[3] = this.Len;
        for (int i = 4; i < framedata.length; i++) {
            framedata[i] = this.Value[i - 4];
        }
        super.buildPacket(new DoubleByte(ZToolCMD.SYS_OSAL_NV_WRITE), framedata);
    }
}
