/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.util.Objects;

/**
 * Defines a unicast ZigBee device address - extends {@link ZigBeeAddress}.
 * <p>
 * The {@link ZigBeeDeviceAddress} is defined by the 16 bit network address
 * and the endpoint Id
 *
 * @author Chris Jackson
 */
public class ZigBeeDeviceAddress extends ZigBeeAddress {
    private int address;
    private int endpoint;

    /**
     * Constructor for ZDO ZigBee devices where only the address is defined
     *
     * @param address
     *            the network address
     *
     */
    public ZigBeeDeviceAddress(int address) {
        this.address = address;
        this.endpoint = 0;
    }

    /**
     * Constructor for standard ZigBee devices where the address and endpoint are defined
     *
     * @param address
     *            the network address
     * @param endpoint
     *            the endpoint number
     */
    public ZigBeeDeviceAddress(int address, int endpoint) {
        this.address = address;
        this.endpoint = endpoint;
    }

    public ZigBeeDeviceAddress(String address) {
        if (address.contains("/")) {
            String[] splits = address.split("/");
            if (splits.length > 2) {
                throw new IllegalArgumentException();
            }
            this.address = Integer.parseInt(splits[0]);
            this.endpoint = Integer.parseInt(splits[1]);
        } else {
            this.address = Integer.parseInt(address);
            this.endpoint = 0;
        }
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
        return false;
    }

    /**
     * Returns the endpoint number
     *
     * @return the endpoint number
     */
    public int getEndpoint() {
        return endpoint;
    }

    /**
     * Sets the endpoint number
     *
     * @param the endpoint number
     */
    public void setEndpoint(final int endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, endpoint);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!ZigBeeDeviceAddress.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final ZigBeeDeviceAddress other = (ZigBeeDeviceAddress) obj;

        return (other.getAddress() == getAddress() && other.getEndpoint() == getEndpoint());
    }

    @Override
    public int compareTo(Object that) {
        if (this == that) {
            return 0;
        }

        ZigBeeDeviceAddress thatAddr = (ZigBeeDeviceAddress) that;

        if (thatAddr.getAddress() == getAddress() && thatAddr.getEndpoint() == getEndpoint()) {
            return 0;
        }

        if (thatAddr.getAddress() == getAddress()) {
            return thatAddr.getEndpoint() - getEndpoint();
        }

        return thatAddr.getAddress() - getAddress();
    }

    @Override
    public String toString() {
        return address + "/" + endpoint;
    }

}
