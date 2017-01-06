package com.zsmartsystems.zigbee;

/**
 * Defines an abstract ZigBee address
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public abstract class ZigBeeAddress implements java.lang.Comparable {
    /**
     * Check whether this destination is ZigBee group.
     *
     * @return TRUE if this is ZigBee group.
     */
    public abstract boolean isGroup();
}
