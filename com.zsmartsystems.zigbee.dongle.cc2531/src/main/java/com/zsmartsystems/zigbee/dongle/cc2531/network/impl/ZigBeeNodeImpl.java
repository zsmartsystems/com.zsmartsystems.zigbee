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

package com.zsmartsystems.zigbee.dongle.cc2531.network.impl;

import com.zsmartsystems.zigbee.dongle.cc2531.network.ZigBeeNode;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.IEEEAddress;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress64;

/**
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:francesco.furfari@isti.cnr.it">Francesco Furfari</a>
 * @version $LastChangedRevision: 799 $ ($LastChangedDate: 2013-08-06 19:00:05 +0300 (Tue, 06 Aug 2013) $)
 * @since 0.1.0
 */
public class ZigBeeNodeImpl implements ZigBeeNode {
    /**
     * The network address.
     */
    private int networkAddress;
    /**
     * The IEEE address.
     */
    private String ieeeAddress;
    /**
     * The pan.
     */
    private short pan;

    /*
     * The basic node descriptor
     */
    private ZigBeeNodeDescriptor nodeDescriptor;
    
    /*
     * The node power descriptor
     */
    private ZigBeeNodePowerDescriptor powerDescriptor;

    /**
     * Default constructor.
     */
    public ZigBeeNodeImpl() {
    }

    /**
     * @param nwk
     * @param ieee
     * @param pan
     * @since 0.6.0 - Revision 67
     */
    public ZigBeeNodeImpl(int nwk, String ieee, short pan) {
        this.networkAddress = nwk;
        this.ieeeAddress = ieee;
        this.pan = pan;
        IEEEAddress.fromColonNotation(ieee); //Only for checking the IEEE format
    }

    public ZigBeeNodeImpl(int nwk, ZToolAddress64 ieee, short pan) {
        this.ieeeAddress = IEEEAddress.toString(ieee.getLong());
        setNetworkAddress(nwk);
        this.pan = pan;
    }

    public String getIeeeAddress() {
        return ieeeAddress;
    }

    public void setIeeeAddress(String ieeeAddress) {
        this.ieeeAddress = ieeeAddress;
    }

    public void setPan(short pan) {
        this.pan = pan;
    }

    /**
     * @param nwk the new network address
     * @since 0.6.0 - Revision 74
     */
    public void setNetworkAddress(int nwk) {
        networkAddress = nwk;
    }

    public int getNetworkAddress() {
        return networkAddress;
    }


    public short getPan() {
        return pan;
    }

    public String toString() {
        return "#" + networkAddress + " (" + ieeeAddress + ")";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof ZigBeeNode) {
            ZigBeeNode node = (ZigBeeNode) obj;
            return networkAddress == node.getNetworkAddress() && ieeeAddress.equals(node.getIeeeAddress());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return ieeeAddress.hashCode();
    }

	@Override
	public void setNodeDescriptor(ZigBeeNodeDescriptor descriptor) {
		nodeDescriptor = descriptor;
	}

	@Override
	public ZigBeeNodeDescriptor getNodeDescriptor() {
		return nodeDescriptor;
	}

	@Override
	public void setPowerDescriptor(ZigBeeNodePowerDescriptor descriptor) {
		powerDescriptor = descriptor;
	}

	@Override
	public ZigBeeNodePowerDescriptor getPowerDescriptor() {
		return powerDescriptor;
	}

}
