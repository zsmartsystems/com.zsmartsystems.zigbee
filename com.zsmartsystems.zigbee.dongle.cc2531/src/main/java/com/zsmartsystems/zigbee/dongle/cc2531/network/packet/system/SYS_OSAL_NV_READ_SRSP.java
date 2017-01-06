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

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ResponseStatus;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;

import java.util.Arrays;

/**
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @version $LastChangedRevision: 799 $ ($LastChangedDate: 2013-08-06 19:00:05 +0300 (Tue, 06 Aug 2013) $)
 */
public class SYS_OSAL_NV_READ_SRSP extends ZToolPacket /*implements IRESPONSE,ISYSTEM*/ {
    /// <name>TI.ZPI2.SYS_OSAL_NV_READ_SRSP.Len</name>
    /// <summary>Length of value.</summary>
    public int Len;
    /// <name>TI.ZPI2.SYS_OSAL_NV_READ_SRSP.Status</name>
    /// <summary>The fail status is returned if the address value in the command message was not within the valid range.</summary>
    public int Status;
    /// <name>TI.ZPI2.SYS_OSAL_NV_READ_SRSP.Value</name>
    /// <summary>Dynamic array, contains the data read from the target.</summary>
    public int[] Value;

    /// <name>TI.ZPI2.SYS_OSAL_NV_READ_SRSP</name>
    /// <summary>Constructor</summary>
    public SYS_OSAL_NV_READ_SRSP() {
        this.Value = new int[0xff];
    }

    public SYS_OSAL_NV_READ_SRSP(int[] framedata) {
        this.Status = framedata[0];
        this.Len = framedata[1];
        this.Value = new int[framedata.length - 2];
        for (int i = 0; i < this.Value.length; i++) {
            this.Value[i] = framedata[i + 2];
        }
            /*if (buffer1.Length > 0xff)
            {
                throw new Exception("Error creating object.");
            }
            this.Value = new byte[0xff];
            Array.Copy(buffer1, this.Value, buffer1.Length);*/
        super.buildPacket(new DoubleByte(ZToolCMD.SYS_OSAL_NV_READ_SRSP), framedata);
    }

    @Override
    public String toString() {
        return "SYS_OSAL_NV_READ_SRSP{" +
                "Len=" + Len +
                ", Status=" + ResponseStatus.getStatus(Status) +
                ", Value=" + Arrays.toString(Value) +
                '}';
    }
}
