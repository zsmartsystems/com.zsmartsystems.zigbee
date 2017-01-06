/*
   Copyright 2008-2013 CNR-ISTI, http://isti.cnr.it
   Institute of Information Science and Technologies 
   of the Italian National Research Council 

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.*;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress16;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress64;

import java.util.Arrays;

/**
 * This command will request a devices IEEE 64-bit address.
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @version $LastChangedRevision: 799 $ ($LastChangedDate: 2013-08-06 19:00:05 +0300 (Tue, 06 Aug 2013) $)
 * @since 0.1.0
 */
public class ZDO_IEEE_ADDR_RSP extends ZToolPacket /*implements IRESPONSE_CALLBACK_IZDO*/ {
    private static final Logger logger = LoggerFactory.getLogger(ZDO_IEEE_ADDR_RSP.class);
    /// <name>TI.ZPI1.ZDO_IEEE_ADDR_RSP.AssocDevList</name>
    /// <summary>Dynamic array, array of 16 bit short addresses - list of network address for associated devices.  This list can be a partial list if the entire list doesn't fit into a packet.  If it is a partial list, the starting index is StartIndex.</summary>
    public ZToolAddress16[] AssocDevList;
    public short[] assocDevList;
    /// <name>TI.ZPI1.ZDO_IEEE_ADDR_RSP.IEEEAddr</name>
    /// <summary>64 bit IEEE address of source device</summary>
    public ZToolAddress64 IEEEAddr;
    public ZToolAddress16 nwkAddr;
    /// <name>TI.ZPI1.ZDO_IEEE_ADDR_RSP.NumAssocDev</name>
    /// <summary>the number of associated devices</summary>
    public int NumAssocDev;
    /// <name>TI.ZPI1.ZDO_IEEE_ADDR_RSP.SrcAddress</name>
    /// <summary>Source address, size is dependent on SrcAddrMode</summary>
    public ZToolAddress64 SrcAddress;
    /// <name>TI.ZPI1.ZDO_IEEE_ADDR_RSP.SrcAddrMode</name>
    /// <summary>indicates that the SrcAddr is either 16 bits or 64 bits</summary>
    public int SrcAddrMode;
    /// <name>TI.ZPI1.ZDO_IEEE_ADDR_RSP.StartIndex</name>
    /// <summary>Starting index into the list of associated devices for this report.</summary>
    public int StartIndex;
    /// <name>TI.ZPI1.ZDO_IEEE_ADDR_RSP.Status</name>
    /// <summary>this field indicates either SUCCESS or FAILURE</summary>
    public int Status;

    /// <name>TI.ZPI1.ZDO_IEEE_ADDR_RSP</name>
    /// <summary>Constructor</summary>
    public ZDO_IEEE_ADDR_RSP() {
        this.AssocDevList = new ZToolAddress16[0xff];
    }

    public ZDO_IEEE_ADDR_RSP(int[] framedata) {
        this.Status = framedata[0];
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) framedata[8 - i];
        }
        this.IEEEAddr = new ZToolAddress64(bytes);
        this.nwkAddr = new ZToolAddress16(framedata[10], framedata[9]);
        this.StartIndex = framedata[11];
        this.NumAssocDev = framedata[12];
        this.AssocDevList = new ZToolAddress16[this.NumAssocDev];
        this.assocDevList = new short[this.NumAssocDev];
        for (int i = 0; i < this.AssocDevList.length; i++) {
            this.AssocDevList[i] = new ZToolAddress16(framedata[14 + (i * 2)], framedata[13 + (i * 2)]);
        }
        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_IEEE_ADDR_RSP), framedata);
    }

    public ZToolAddress64 getIeeeAddress() {
        return IEEEAddr;
    }

    public int[] getAssociatedNodesList() {
        int[] values = new int[AssocDevList.length];
        for (int i = 0; i < AssocDevList.length; i++) {
            logger.trace("Associated node - msb: " + AssocDevList[i].getMsb() + " lsb: " + AssocDevList[i].getLsb());
            values[i] = ((int) AssocDevList[i].get16BitValue() & 0xFFFF);
        }
        return values;
    }

    public int getStartIndex() {
        return StartIndex;
    }

    public int getAssociatedNodeCount() {
        return NumAssocDev;
    }

    @Override
    public String toString() {
        return "ZDO_IEEE_ADDR_RSP{" +
                "AssocDevList=" + Arrays.toString(AssocDevList) +
                ", assocDevList=" + Arrays.toString(assocDevList) +
                ", IEEEAddr=" + IEEEAddr +
                ", nwkAddr=" + nwkAddr +
                ", NumAssocDev=" + NumAssocDev +
                ", SrcAddress=" + SrcAddress +
                ", SrcAddrMode=" + SrcAddrMode +
                ", StartIndex=" + StartIndex +
                ", Status=" + ResponseStatus.getStatus(Status) +
                '}';
    }
}
