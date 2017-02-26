package com.zsmartsystems.zigbee;

/**
 * Defines an abstract ZigBee address. All addresses must provide a 16 bit network address.
 * <p>
 * Addresses in the range 0x0000 to 0xfff7 are used for node addresses while
 * 0xfff8 to 0xffff are used for broadcast addresses.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public abstract class ZigBeeAddress implements java.lang.Comparable {
    /**
     * Gets the network address for this address.
     *
     * @return network address as {@link int}
     */
    public abstract int getAddress();

    /**
     * Sets the network address for this address
     * 
     * @param address the network address as {@link int}
     */
    public abstract void setAddress(final int address);

    /**
     * Check whether this address is ZigBee group.
     *
     * @return TRUE if this is ZigBee group.
     */
    public abstract boolean isGroup();
}
