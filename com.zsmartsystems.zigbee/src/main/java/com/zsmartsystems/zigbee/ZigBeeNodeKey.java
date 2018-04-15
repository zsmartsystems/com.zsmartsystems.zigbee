/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

/**
 * A class providing a key for a specific node address
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNodeKey {
    private IeeeAddress address;
    private ZigBeeKey key;

    /**
     * Constructor taking the {@link IeeeAddress} of the node and the {@link ZigBeeKey}
     * 
     * @param address the node {@link IeeeAddress}
     * @param key the {@link ZigBeeKey}
     */
    public ZigBeeNodeKey(IeeeAddress address, ZigBeeKey key) {
        this.address = address;
        this.key = key;
    }

    /**
     * @return the {@link IeeeAddress} of the node
     */
    public IeeeAddress getAddress() {
        return address;
    }

    /**
     * @param address the {@link IeeeAddress} to set
     */
    public void setAddress(IeeeAddress address) {
        this.address = address;
    }

    /**
     * @return the {@link ZigBeeKey}
     */
    public ZigBeeKey getKey() {
        return key;
    }

    /**
     * @param key the {@link ZigBeeKey} to set
     */
    public void setKey(ZigBeeKey key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "ZigBeeNodeKey [address=" + address + ", key=" + key + "]";
    }

}
