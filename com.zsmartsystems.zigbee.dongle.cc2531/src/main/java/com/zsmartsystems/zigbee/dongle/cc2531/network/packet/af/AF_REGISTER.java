/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
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

package com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.Integers;

/**
 * This command enables the host processor to register an application's endpoint
 * description (and its simple descriptor). Multiple endpoints may be registered
 * with the AF by making multiple calls to AF_REGISTER. This could be useful in
 * the case where the device needs to support multiple application profiles, where
 * each AF_REGISTER call would register a unique endpoint description per application
 * profile.
 *
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 */
public class AF_REGISTER extends ZToolPacket /* implements IREQUEST, IAF */ {
    /// <name>TI.ZPI2.AF_REGISTER.AppDeviceId</name>
    /// <summary>specifies the device description id for this endpoint</summary>
    public DoubleByte AppDeviceId;
    /// <name>TI.ZPI2.AF_REGISTER.AppDevVer</name>
    /// <summary>specifies the version of the device description</summary>
    public int AppDevVer;
    /// <name>TI.ZPI2.AF_REGISTER.AppInClusterList</name>
    /// <summary>Dynamic array, requires memory allocation. variable length field of size 'AppNumInClusters' and is the
    /// list of Input Cluster Ids</summary>
    public DoubleByte[] AppInClusterList;
    /// <name>TI.ZPI2.AF_REGISTER.AppNumInClusters</name>
    /// <summary>the number of Input cluster Ids following in the AppInClusterList</summary>
    public int AppNumInClusters;
    /// <name>TI.ZPI2.AF_REGISTER.AppNumOutClusters</name>
    /// <summary>specifies the number of Output cluster Ids following in the AppOutClusterList</summary>
    public int AppNumOutClusters;
    /// <name>TI.ZPI2.AF_REGISTER.AppOutClusterList</name>
    /// <summary>Dynamic array, requires memory allocation. Variable length field of size 'AppNumOutClusters' and is the
    /// list of Output Cluster Ids</summary>
    public DoubleByte[] AppOutClusterList;
    /// <name>TI.ZPI2.AF_REGISTER.AppProfID</name>
    /// <summary>specifies the profile id of the application</summary>
    public DoubleByte AppProfID;
    /// <name>TI.ZPI2.AF_REGISTER.EndPoint</name>
    /// <summary>specifies the endpoint of the device</summary>
    public int EndPoint;
    /// <name>TI.ZPI2.AF_REGISTER.LatencyReq</name>
    /// <summary>Latency</summary>
    // public int LatencyReq;

    /// <name>TI.ZPI2.AF_REGISTER</name>
    /// <summary>Constructor</summary>
    public AF_REGISTER() {
        this.AppInClusterList = new DoubleByte[0xff];
        this.AppOutClusterList = new DoubleByte[0xff];
    }

    /**
     * Creates the AF_REGISTER packet
     *
     * @param EndPoint Specifies the endpoint of this simple descriptor.
     * @param AppProfID Specifies the profile id of the application
     * @param AppDeviceId Specifies the device description id for this endpoint
     * @param AppDevVer Specifies the device version number
     * @param AppNumInClusters the number of Input cluster Ids following in the AppInClusterList
     * @param AppInClusterList Specifies the list of Input Cluster Ids
     * @param AppNumOutClusters Specifies the number of Output cluster Ids following in the AppOutClusterList
     * @param AppOutClusterList Specifies the list of Output Cluster Ids
     */
    public AF_REGISTER(int EndPoint, DoubleByte AppProfID, DoubleByte AppDeviceId, int AppDevVer, int AppNumInClusters,
            DoubleByte[] AppInClusterList, int AppNumOutClusters, DoubleByte[] AppOutClusterList) {
        this.EndPoint = EndPoint;
        this.AppProfID = AppProfID;
        this.AppDeviceId = AppDeviceId;
        this.AppDevVer = AppDevVer;

        this.AppNumInClusters = AppNumInClusters;
        this.AppInClusterList = new DoubleByte[AppInClusterList.length];
        this.AppInClusterList = AppInClusterList;
        // if (numArray1.Length > 0xff)
        // {
        // throw new Exception("Error creating object.");
        // }
        // this.AppInClusterList = new ushort[0xff];
        // Array.Copy(numArray1, this.AppInClusterList, numArray1.Length);
        this.AppNumOutClusters = AppNumOutClusters;
        this.AppOutClusterList = new DoubleByte[AppOutClusterList.length];
        this.AppOutClusterList = AppOutClusterList;
        // if (numArray2.Length > 0xff)
        // {
        // throw new Exception("Error creating object.");
        // }
        // this.AppOutClusterList = new ushort[0xff];
        // Array.Copy(numArray2, this.AppOutClusterList, numArray2.Length);

        int[] framedata = new int[9 + this.AppInClusterList.length * 2 + this.AppOutClusterList.length * 2];
        framedata[0] = this.EndPoint;
        framedata[1] = this.AppProfID.getLsb();
        framedata[2] = this.AppProfID.getMsb();
        framedata[3] = this.AppDeviceId.getLsb();
        framedata[4] = this.AppDeviceId.getMsb();
        framedata[5] = this.AppDevVer;
        framedata[6] = 0;
        framedata[7] = this.AppNumInClusters;
        for (int i = 0; i < this.AppInClusterList.length; i++) {
            framedata[(i * 2) + 8] = this.AppInClusterList[i].getLsb();
            framedata[(i * 2) + 9] = this.AppInClusterList[i].getMsb();
        }
        framedata[((this.AppInClusterList.length) * 2) + 8] = this.AppNumOutClusters;
        for (int i = 0; i < this.AppOutClusterList.length; i++) {
            framedata[(i * 2) + ((this.AppInClusterList.length) * 2) + 9] = this.AppOutClusterList[i].getLsb();
            framedata[(i * 2) + ((this.AppInClusterList.length) * 2) + 10] = this.AppOutClusterList[i].getMsb();
        }
        super.buildPacket(new DoubleByte(ZToolCMD.AF_REGISTER), framedata);
    }

    public AF_REGISTER(int endpointId, int profileId, short deviceId, byte deviceVersion, int[] inputs, int[] outputs) {

        // TODO Check compatibility with other Constructor

        int[] framedata = new int[9 + inputs.length * 2 + outputs.length * 2];
        int j;
        framedata[0] = 0xFF & endpointId;
        framedata[1] = Integers.getByteAsInteger(profileId, 0);
        framedata[2] = Integers.getByteAsInteger(profileId, 1);
        framedata[3] = Integers.getByteAsInteger(deviceId, 0);
        framedata[4] = Integers.getByteAsInteger(deviceId, 1);
        framedata[5] = 0xFF & deviceVersion;
        framedata[6] = 0; // No latency
        framedata[7] = inputs.length;
        j = 8;
        for (int i = 0; i < inputs.length; i++) {
            framedata[j] = Integers.getByteAsInteger(inputs[i], 0);
            j++;
            framedata[j] = Integers.getByteAsInteger(inputs[i], 1);
            j++;
        }
        framedata[j] = outputs.length;
        j++;

        for (int i = 0; i < outputs.length; i++) {
            framedata[j] = Integers.getByteAsInteger(outputs[i], 0);
            j++;
            framedata[j] = Integers.getByteAsInteger(outputs[i], 1);
            j++;
        }
        super.buildPacket(new DoubleByte(ZToolCMD.AF_REGISTER), framedata);
    }

    /// <name>TI.ZPI2.AF_REGISTER.LATENCY_TYPE</name>
    /// <summary>Latency type</summary>
    /*
     * public class LATENCY_TYPE {
     * /// <name>TI.ZPI2.AF_REGISTER.LATENCY_TYPE.FAST_BEACONS</name>
     * /// <summary>Latency type</summary>
     * public static final int FAST_BEACONS = 1;
     * /// <name>TI.ZPI2.AF_REGISTER.LATENCY_TYPE.NO_LATENCY_REQS</name>
     * /// <summary>Latency type</summary>
     * public static final int NO_LATENCY_REQS = 0;
     * /// <name>TI.ZPI2.AF_REGISTER.LATENCY_TYPE.SLOW_BEACONS</name>
     * /// <summary>Latency type</summary>
     * public static final int SLOW_BEACONS = 2;
     * }
     */
}
