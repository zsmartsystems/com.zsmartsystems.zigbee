/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.groups;

import java.util.Objects;

import com.zsmartsystems.zigbee.ZigBeeAddress;

/**
 * ZigBee group address
 *
 * @author Chris Jackson
 */
public class ZigBeeGroupAddress extends ZigBeeAddress {
    /**
     * The address / group ID.
     */
    private int address;

    /**
     * Default constructor.
     */
    public ZigBeeGroupAddress() {
    }

    /**
     * Constructor which sets group ID.
     *
     * @param address the address or group ID
     */
    public ZigBeeGroupAddress(final int address) {
        this.address = address;
    }

    @Override
    public int getAddress() {
        return address;
    }

    @Override
    public void setAddress(final int address) {
        this.address = address;
    }

    @Override
    public boolean isGroup() {
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public int compareTo(ZigBeeAddress that) {
        if (this == that) {
            return 0;
        }

        ZigBeeGroupAddress thatAddr = (ZigBeeGroupAddress) that;

        if (thatAddr.getAddress() == getAddress()) {
            return 0;
        }

        return (thatAddr.getAddress() < getAddress()) ? -1 : 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!ZigBeeGroupAddress.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final ZigBeeGroupAddress other = (ZigBeeGroupAddress) obj;
        return other.getAddress() == getAddress();
    }

    @Override
    public String toString() {
        return "ZigBeeGroupAddress [groupId=" + address + "]";
    }
}