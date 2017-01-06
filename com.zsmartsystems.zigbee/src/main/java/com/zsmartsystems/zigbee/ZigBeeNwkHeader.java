package com.zsmartsystems.zigbee;

/**
 * Defines the ZigBee NWK frame header
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNwkHeader {
    /**
     * The type of destination address supplied by the DstAddr parameter.
     * This may have one of the following two values:
     * <ul>
     * <li>0x01=16-bit multicast group address ({@link #GROUP}).
     * <li>0x02=16-bit network address of a device or a 16-bit broadcast address ({@link #DEVICE}).
     * </ul>
     */
    private ZigBeeNwkAddressMode addressMode;

    /**
     * Destination address.
     */
    private int destinationAddress;

    private int sourceAddress;

    /**
     * The distance, in hops, that a frame will be allowed to travel through the network.
     */
    private int radius;

    /**
     * The distance, in hops, that a multicast frame will be relayed by nodes not a member of the group. A value of 0x07
     * is treated as infinity.
     */
    private int nonMemberRadius;

    /**
     * The DiscoverRoute parameter may be used to control route discovery operations for the transit of this frame.
     * <ul>
     * <li>false = suppress route discovery
     * <li>true = enable route discovery
     * </ul>
     */
    private boolean discoverRoute;

    /**
     * The SecurityEnable parameter may be used to enable NWK layer security processing for the current frame. If the
     * nwkSecurityLevel attribute of the NIB has a value of 0, meaning no security, then this parameter will be ignored.
     * Otherwise, a value of TRUE denotes that the security processing specified by the security level will be applied,
     * and a value of FALSE denotes that no security processing will be applied.
     */
    private boolean securityEnable;

    private int sequence;

    public int getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(int destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public int getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(int sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    /**
     * The distance, in hops, that a frame will be allowed to travel through the network.
     *
     * @return the radius for this frame
     */
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "ZigBeeNetworkHeader [destinationAddress=" + destinationAddress + ", sourceAddress=" + sourceAddress
                + ", radius=" + radius + ", sequence=" + sequence + "]";
    }
}
