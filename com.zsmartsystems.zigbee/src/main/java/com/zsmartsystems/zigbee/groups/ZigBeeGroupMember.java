/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.groups;

import com.zsmartsystems.zigbee.IeeeAddress;

/**
 * Holds a group member comprising the node EUI address and endpoint ID.
 * <p>
 * EUI is used to ensure this is maintained correctly even when the network address may change
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeGroupMember {
    private IeeeAddress ieeeAddress;
    private int endpointId;

    public ZigBeeGroupMember(IeeeAddress ieeeAddress, int endpointId) {
        this.ieeeAddress = ieeeAddress;
        this.endpointId = endpointId;
    }

    /**
     * @return the {@link IeeeAddress}
     */
    public IeeeAddress getAddress() {
        return ieeeAddress;
    }

    /**
     * @param ieeeAddress the {@link IeeeAddress} to set
     */
    public void setAddress(IeeeAddress ieeeAddress) {
        this.ieeeAddress = ieeeAddress;
    }

    /**
     * @return the endpointId
     */
    public int getEndpointId() {
        return endpointId;
    }

    /**
     * @param endpointId the endpointId to set
     */
    public void setEndpointId(int endpointId) {
        this.endpointId = endpointId;
    }

    @Override
    public String toString() {
        return "ZigBeeGroupMember [ieeeAddress=" + ieeeAddress + ", endpointId=" + endpointId + "]";
    }
}
