package com.zsmartsystems.zigbee;

/**
 * Defines a unicast ZigBee address - extends {@link ZigBeeAddress}
 *
 * @author Chris Jackson
 */
public class ZigBeeDeviceAddress extends ZigBeeAddress {
    final private int address;
    final private int endpoint;

    /**
     * Constructor
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
        String[] splits = address.split("/");
        if (splits.length != 2) {
            throw new IllegalArgumentException();
        }
        this.address = Integer.parseInt(splits[0]);
        this.endpoint = Integer.parseInt(splits[1]);
    }

    @Override
    public boolean isGroup() {
        return false;
    }

    /**
     * Sets the endpoint number
     *
     * @param endpoint
     *            number
     */
    // public void setEndpoint(int endpoint) {
    // this.endpoint = endpoint;
    // }

    /**
     * Returns the endpoint number
     *
     * @return the endpoint number
     */
    public int getEndpoint() {
        return endpoint;
    }

    /**
     * Returns the network address
     *
     * @return the network address
     */
    public int getAddress() {
        return address;
    }

    /**
     * Sets the network address
     *
     * @param address
     *            the network address
     */
    // public void setAddress(int address) {
    // this.address = address;
    // }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!ZigBeeDeviceAddress.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final ZigBeeDeviceAddress other = (ZigBeeDeviceAddress) obj;
        if (other.getAddress() == address && other.getEndpoint() == endpoint) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return address + "/" + endpoint;
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

        return 1;
    }
}
