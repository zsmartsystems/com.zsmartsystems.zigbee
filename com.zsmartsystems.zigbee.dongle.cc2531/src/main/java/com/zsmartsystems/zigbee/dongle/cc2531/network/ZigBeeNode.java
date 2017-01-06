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

import com.zsmartsystems.zigbee.dongle.cc2531.network.impl.ZigBeeNodeDescriptor;
import com.zsmartsystems.zigbee.dongle.cc2531.network.impl.ZigBeeNodePowerDescriptor;

/**
 * This class represent a ZigBee node, it means a physical device that can communicate
 * using the ZigBee protocol.
 * <p>
 * Each physical may contain up 240 endpoints which are represented by the {@link ZigBeeEndpoint}
 * class. Each endpoint is identified by an <i>EndPoint</i> address, but shares either the:
 * <ul>
 * <li>64-bit 802.15.4 IEEE Address</li>
 * <li>16-bit ZigBee Network Address</li>
 * </ul>
 * <p>
 * The node descriptors are also available through the node. These provide basic information
 * about the device itself and are only available in the node (ie they are the same for all
 * endpoints).
 * <p>
 *
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:francesco.furfari@isti.cnr.it">Francesco Furfari</a>
 * @author <a href="mailto:chris@cd-jackson.com">Chris Jackson</a>
 */
public interface ZigBeeNode {
    /**
     * Gets the network address for this node
     * 
     * @return int representing the current network address linked to the node
     */
    int getNetworkAddress();

    /**
     * Gets the 64 bit IEEE address for this node
     * 
     * @return a {@link String} representing the IEEEAddress of the node
     */
    String getIeeeAddress();

    /**
     * Sets the {@link ZigBeeNodeDescriptor Node Descriptor}.
     * <p>
     * The node descriptor contains information about the capabilities of the ZigBee
     * node and is mandatory for each node.
     * 
     * @param descriptor
     */
    void setNodeDescriptor(ZigBeeNodeDescriptor descriptor);

    /**
     * Gets the {@link ZigBeeNodeDescriptor Node Descriptor}.
     * <p>
     * The node descriptor contains information about the capabilities of the ZigBee
     * node and is mandatory for each node.
     */
    ZigBeeNodeDescriptor getNodeDescriptor();

    /**
     * This sets the {@link ZigBeeNodePowerDescriptor Node Power Descriptor}.
     * <p>
     * The node power descriptor gives a dynamic indication of the power status of the
     * node and is mandatory for each node.
     * 
     * @param descriptor
     */
    void setPowerDescriptor(ZigBeeNodePowerDescriptor descriptor);

    /**
     * This gets the {@link ZigBeeNodePowerDescriptor Node Power Descriptor}.
     * <p>
     * The node power descriptor gives a dynamic indication of the power status of the
     * node and is mandatory for each node.
     */
    ZigBeeNodePowerDescriptor getPowerDescriptor();
}
