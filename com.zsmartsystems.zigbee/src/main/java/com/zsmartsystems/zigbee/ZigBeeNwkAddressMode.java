package com.zsmartsystems.zigbee;

/**
 * The type of destination address supplied by the DstAddr parameter.
 * This may have one of the following two values:
 * <ul>
 * <li>0x01=16-bit multicast group address ({@link #GROUP}).
 * <li>0x02=16-bit network address of a device or a 16-bit broadcast address ({@link #DEVICE}).
 * </ul>
 *
 * @author Chris Jackson
 *
 */
public enum ZigBeeNwkAddressMode {
    /**
     * Address is multicast group address
     */
    GROUP,
    /**
     * Address is 16-bit network address of a device or a 16-bit broadcast address
     */
    DEVICE
}
