/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
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

/**
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 */
public class ZB_APP_REGISTER_REQUEST extends ZToolPacket /* implements IREQUEST,ISIMPLEAPI */ {
    /// <name>TI.ZPI2.ZB_APP_REGISTER_REQUEST.AppEndPoint</name>
    /// <summary>The endpoint of the device on which this application should reside ( value 1 through 240 ). This should
    /// be set to same value for all devices in this network. </summary>
    public int AppEndPoint;
    /// <name>TI.ZPI2.ZB_APP_REGISTER_REQUEST.AppProfileID</name>
    /// <summary>The profile id of the application. This should be set to same value for all devices in this
    /// network.</summary>
    public DoubleByte AppProfileID;
    /// <name>TI.ZPI2.ZB_APP_REGISTER_REQUEST.DeviceId</name>
    /// <summary>The device description id for this endpoint</summary>
    public DoubleByte DeviceId;
    /// <name>TI.ZPI2.ZB_APP_REGISTER_REQUEST.DeviceVersion</name>
    /// <summary>The version of the device description</summary>
    public int DeviceVersion;
    /// <name>TI.ZPI2.ZB_APP_REGISTER_REQUEST.InputCommandsList</name>
    /// <summary>List of input commands that are processed by this device application</summary>
    public DoubleByte[] InputCommandsList;
    /// <name>TI.ZPI2.ZB_APP_REGISTER_REQUEST.InputCommandsNum</name>
    /// <summary>The number of Input commands Ids following in the InputCommandsList</summary>
    public int InputCommandsNum;
    /// <name>TI.ZPI2.ZB_APP_REGISTER_REQUEST.OutputCommandsList</name>
    /// <summary>List of output commands that are generated by this device application</summary>
    public DoubleByte[] OutputCommandsList;
    /// <name>TI.ZPI2.ZB_APP_REGISTER_REQUEST.OutputCommandsNum</name>
    /// <summary>The number of Output command Ids in the OutputCommandsList</summary>
    public int OutputCommandsNum;
    /// <name>TI.ZPI2.ZB_APP_REGISTER_REQUEST.Unused</name>
    /// <summary>Unused parameter</summary>
    public int Unused;

    /// <name>TI.ZPI2.ZB_APP_REGISTER_REQUEST</name>
    /// <summary>Constructor</summary>
    public ZB_APP_REGISTER_REQUEST() {
        this.InputCommandsList = new DoubleByte[0xff];
        this.OutputCommandsList = new DoubleByte[0xff];
    }

    /// <name>TI.ZPI2.ZB_APP_REGISTER_REQUEST</name>
    /// <summary>Constructor</summary>
    public ZB_APP_REGISTER_REQUEST(int num1, DoubleByte num2, DoubleByte num3, int num4, int num5, int num6,
            DoubleByte[] numArray1, int num7, DoubleByte[] numArray2) {
        this.AppEndPoint = num1;
        this.AppProfileID = num2;
        this.DeviceId = num3;
        this.DeviceVersion = num4;
        this.Unused = num5;
        this.InputCommandsNum = num6;
        this.InputCommandsList = new DoubleByte[numArray1.length];
        this.InputCommandsList = numArray1;
        /*
         * if (numArray1.Length > 0xff)
         * {
         * throw new Exception("Error creating object.");
         * }
         * this.InputCommandsList = new ushort[0xff];
         * Array.Copy(numArray1, this.InputCommandsList, numArray1.Length);
         */
        this.OutputCommandsNum = num7;
        this.OutputCommandsList = new DoubleByte[numArray2.length];
        this.OutputCommandsList = numArray2;
        /*
         * if (numArray2.Length > 0xff)
         * {
         * throw new Exception("Error creating object.");
         * }
         * this.OutputCommandsList = new ushort[0xff];
         * Array.Copy(numArray2, this.OutputCommandsList, numArray2.Length);
         */
        int[] framedata = new int[9 + this.InputCommandsList.length * 2 + this.OutputCommandsList.length * 2];
        framedata[0] = this.AppEndPoint;
        framedata[1] = this.AppProfileID.getLsb();
        framedata[2] = this.AppProfileID.getMsb();
        framedata[3] = this.DeviceId.getLsb();
        framedata[4] = this.DeviceId.getMsb();
        framedata[5] = this.DeviceVersion;
        framedata[6] = this.Unused;
        framedata[7] = this.InputCommandsNum;
        for (int i = 0; i < this.InputCommandsList.length; i++) {
            framedata[(i * 2) + 8] = this.InputCommandsList[i].getLsb();
            framedata[(i * 2) + 9] = this.InputCommandsList[i].getMsb();
        }
        framedata[((this.InputCommandsList.length) * 2) + 8] = this.OutputCommandsNum;
        for (int i = 0; i < this.OutputCommandsList.length; i++) {
            framedata[(i * 2) + ((this.InputCommandsList.length) * 2) + 9] = this.OutputCommandsList[i].getLsb();
            framedata[(i * 2) + ((this.InputCommandsList.length) * 2) + 10] = this.OutputCommandsList[i].getMsb();
        }
        super.buildPacket(new DoubleByte(ZToolCMD.ZB_APP_REGISTER_REQUEST), framedata);

    }
}
