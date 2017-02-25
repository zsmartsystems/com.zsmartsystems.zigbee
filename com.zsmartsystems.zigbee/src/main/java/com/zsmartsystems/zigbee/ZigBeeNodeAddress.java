package com.zsmartsystems.zigbee;

/**
 * Defines a ZigBee node address - extends {@link ZigBeeAddress}.
 * <p>
 * The {@link ZigBeeNodeAddress} is defined by the 16 bit network address.
 * This is normally used for ZDO.
 *
 * @author Chris Jackson
 */
public class ZigBeeNodeAddress extends ZigBeeAddress {
    final private int address;

    /**
     * Constructor
     *
     * @param address
     *            the network address
     */
    public ZigBeeNodeAddress(int address) {
        this.address = address;
    }

    @Override
    public boolean isGroup() {
        return false;
    }

    /**
     * Returns the network address
     *
     * @return the network address
     */
    public int getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!ZigBeeNodeAddress.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final ZigBeeNodeAddress other = (ZigBeeNodeAddress) obj;
        if (other.getAddress() == address) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Object that) {
        if (this == that) {
            return 0;
        }

        ZigBeeNodeAddress thatAddr = (ZigBeeNodeAddress) that;

        if (thatAddr.getAddress() == getAddress()) {
            return 0;
        }

        return 1;
    }

    @Override
    public String toString() {
        return String.format("%04X/0", address);
    }

}
