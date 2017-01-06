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
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress16;

/**
 * This command is generated to request an End Device Bind with the destination device.
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @version $LastChangedRevision: 799 $ ($LastChangedDate: 2013-08-06 19:00:05 +0300 (Tue, 06 Aug 2013) $)
 */
public class ZDO_END_DEVICE_BIND_REQ extends ZToolPacket /*implements IREQUEST,IZDo*/ {
    /// <name>TI.ZPI1.ZDO_END_DEVICE_BIND_REQ.DstAddr</name>
    /// <summary>destination address</summary>
    public ZToolAddress16 DstAddr;
    /// <name>TI.ZPI1.ZDO_END_DEVICE_BIND_REQ.Endpoint</name>
    /// <summary>Device's Endpoint</summary>
    public int Endpoint;
    /// <name>TI.ZPI1.ZDO_END_DEVICE_BIND_REQ.InClusterList</name>
    /// <summary>Array of input cluster IDs - NumInClusters long</summary>
    public DoubleByte[] InClusterList;
    /// <name>TI.ZPI1.ZDO_END_DEVICE_BIND_REQ.LocalCoordinator</name>
    /// <summary>Local coordinator's short address</summary>
    public DoubleByte LocalCoordinator;
    /// <name>TI.ZPI1.ZDO_END_DEVICE_BIND_REQ.NumInClusters</name>
    /// <summary>Number of ClusterIds in the InClusterList</summary>
    public int NumInClusters;
    /// <name>TI.ZPI1.ZDO_END_DEVICE_BIND_REQ.NumOutClusters</name>
    /// <summary>Number of ClusterIds in the OutClusterList</summary>
    public int NumOutClusters;
    /// <name>TI.ZPI1.ZDO_END_DEVICE_BIND_REQ.OutClusterList</name>
    /// <summary>Array of output cluster IDs - NumOutClusters long</summary>
    public DoubleByte[] OutClusterList;
    /// <name>TI.ZPI1.ZDO_END_DEVICE_BIND_REQ.ProfileID</name>
    /// <summary>Profile PROFILE_ID_HOME_AUTOMATION to match</summary>
    public DoubleByte ProfileID;

    /// <name>TI.ZPI1.ZDO_END_DEVICE_BIND_REQ</name>
    /// <summary>Constructor</summary>
    public ZDO_END_DEVICE_BIND_REQ() {
        this.InClusterList = new DoubleByte[15];
        this.OutClusterList = new DoubleByte[15];
    }

    public ZDO_END_DEVICE_BIND_REQ(ZToolAddress16 num1, DoubleByte num2, int num3, DoubleByte num4, int num5, DoubleByte[] numArray1, int num6, DoubleByte[] numArray2, int security_suite1) {
        this.DstAddr = num1;
        this.LocalCoordinator = num2;
        this.Endpoint = num3;
        this.ProfileID = num4;
        this.NumInClusters = num5;
        this.InClusterList = new DoubleByte[numArray1.length];
        this.InClusterList = numArray1;
        /*if (numArray1.Length > 15)
        {
        throw new Exception("Error creating object.");
        }
        this.InClusterList = new ushort[15];
        Array.Copy(numArray1, this.InClusterList, numArray1.Length);*/
        this.NumOutClusters = num6;
        this.OutClusterList = new DoubleByte[numArray1.length];
        this.OutClusterList = numArray1;
        /*if (numArray2.Length > 15)
        {
        throw new Exception("Error creating object.");
        }
        this.OutClusterList = new ushort[15];
        Array.Copy(numArray2, this.OutClusterList, numArray2.Length);*/

        int[] framedata = new int[9 + this.InClusterList.length * 2 + this.OutClusterList.length * 2];
        framedata[0] = this.DstAddr.getLsb();
        framedata[1] = this.DstAddr.getMsb();
        framedata[2] = this.LocalCoordinator.getLsb();
        framedata[3] = this.LocalCoordinator.getMsb();
        framedata[4] = this.Endpoint;
        framedata[5] = this.ProfileID.getLsb();
        framedata[6] = this.ProfileID.getMsb();
        framedata[7] = this.NumInClusters;
        for (int i = 0; i < this.InClusterList.length; i++) {
            framedata[(i * 2) + 8] = this.InClusterList[i].getLsb();
            framedata[(i * 2) + 9] = this.InClusterList[i].getMsb();
        }
        framedata[((this.InClusterList.length) * 2) + 8] = this.NumOutClusters;
        for (int i = 0; i < this.OutClusterList.length; i++) {
            framedata[(i * 2) + ((this.InClusterList.length) * 2) + 9] = this.OutClusterList[i].getLsb();
            framedata[(i * 2) + ((this.InClusterList.length) * 2) + 10] = this.OutClusterList[i].getMsb();
        }
        super.buildPacket(new DoubleByte(ZToolCMD.ZDO_END_DEVICE_BIND_REQ), framedata);
    }

}
