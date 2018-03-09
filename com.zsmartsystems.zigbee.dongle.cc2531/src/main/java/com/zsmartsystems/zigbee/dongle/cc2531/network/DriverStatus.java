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

package com.zsmartsystems.zigbee.dongle.cc2531.network;

/**
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi - ISTI-CNR
 */
public enum DriverStatus {

    /**
     * The driver has been created and it will start to initialize all the hardware resources
     * and the ZigBee network (i.e.: it will either join or create a network).
     */
    CREATED,

    /**
     * The driver has opened the hardware resources, and it is waiting for
     * the hardware to complete the reset process
     */
    HARDWARE_OPEN,

    /**
     * The driver has already initialized all the hardware resources, and it is waiting for
     * the hardware to complete the initialization process
     */
    HARDWARE_INITIALIZING,

    /**
     * The all the hardware resources have been initialized successfully, it will start to
     * initialize the ZigBee network
     */
    HARDWARE_READY,

    /**
     * The driver has already initialized the ZigBee network, and it is waiting for
     * the completion of process (i.e.: it joined to the network and it is waiting for
     * a network address)
     */
    NETWORK_INITIALIZING,

    /**
     * The driver successfully joined to or create the ZigBee network
     */
    NETWORK_READY,

    /**
     * The driver is closed, no resources is in use
     */
    CLOSED;
}
