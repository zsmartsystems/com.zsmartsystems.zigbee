/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

/**
 * Defines the APS layer frame along with some network layer elements that may be needed by the application.
 * <p>
 * The APS frame format is composed of an APS header and an APS payload. The fields of the APS header appear in a fixed
 * order, however, the addressing fields may not be included in all frames.
 * <p>
 * Note that not all APS header fields may be present in this class. The class is passed from the framework to the
 * hardware drivers where it is processed accordingly. Should data be missing that is required by a hardware
 * implemention, it will be added.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeApsFrame {
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

    /**
     * Destination address.
     */
    private IeeeAddress destinationIeeeAddress;

    /**
     * Source address
     */
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
    // private boolean discoverRoute;

    /**
     * The SecurityEnable parameter may be used to enable NWK layer security processing for the current frame. If the
     * nwkSecurityLevel attribute of the NIB has a value of 0, meaning no security, then this parameter will be ignored.
     * Otherwise, a value of TRUE denotes that the security processing specified by the security level will be applied,
     * and a value of FALSE denotes that no security processing will be applied.
     */
    private boolean securityEnable;

    /**
     * The destination endpoint field is 8-bits in length and specifies the endpoint of the final recipient of the
     * frame. This field shall be included in the frame only if the delivery mode sub-field of the frame control field
     * is set to 0b00 (normal unicast delivery), 0b01 (indirect delivery where the indirect address mode sub-field of
     * the frame control field is also set to 0), or 0b10 (broadcast delivery). In the case of broadcast delivery, the
     * frame shall be delivered to the destination endpoint specified within the range 0x01-0xf0 or to all active
     * endpoints if specified as 0xff.
     * <p>
     * A destination endpoint value of 0x00 addresses the frame to the ZigBee device object (ZDO), resident in each
     * device. A destination endpoint value of 0x01-0xf0 addresses the frame to an application operating on that
     * endpoint. A destination endpoint value of 0xff addresses the frame to all active endpoints except endpoint 0x00.
     * All other endpoints (0xf1-0xfe) are reserved.
     */
    private int destinationEndpoint;

    /**
     * The cluster identifier field is 16 bits in length and specifies the identifier of the cluster to which the frame
     * relates and which shall be made available for filtering and interpretation of messages at each device that takes
     * delivery of the frame.
     * <p>
     * This field shall be present only for data or acknowledgement frames.
     */
    private int cluster;

    /**
     * The profile identifier is two octets in length and specifies the ZigBee profile identifier for which the frame is
     * intended and shall be used during the filtering of messages at each device that takes delivery of the frame.
     * <p>
     * This field shall be present only for data or acknowledgement frames.
     */
    private int profile;

    /**
     * The source endpoint field is eight-bits in length and specifies the endpoint of the initial originator of the
     * frame. A source endpoint value of 0x00 indicates that the frame originated from the ZigBee device object (ZDO)
     * resident in each device. A source endpoint value of 0x01-0xf0 indicates that the frame originated from an
     * application operating on that endpoint. All other endpoints (0xf1-0xff) are reserved.
     */
    private int sourceEndpoint;

    /**
     * The group address field is 16 bits in length and will only be present if the delivery mode sub-field of the frame
     * control has a value of 0b11. In this case, the destination endpoint shall not be present.
     */
    private int groupAddress;

    /**
     * This field is eight bits in length and is used to prevent the reception of duplicate frames. This value shall be
     * incremented by one for each new transmission.
     */
    private int apsCounter;

    private int sequence;

    /**
     * The APS payload.
     * <p>
     * This is defined as the application payload as defined in the ZigBee standard. This could include a ZCL cluster
     * starting with the ZCL header, or a ZDO frame etc.
     */
    private int[] payload;

    public int getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(int destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public IeeeAddress getDestinationIeeeAddress() {
        return destinationIeeeAddress;
    }

    public void setDestinationIeeeAddress(IeeeAddress destinationIeeeAddress) {
        this.destinationIeeeAddress = destinationIeeeAddress;
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

    public boolean isSecurityEnable() {
        return securityEnable;
    }

    public void setSecurityEnable(boolean securityEnable) {
        this.securityEnable = securityEnable;
    }

    // public boolean isDiscoverRoute() {
    // return discoverRoute;
    // }

    // public void setDiscoverRoute(boolean discoverRoute) {
    // this.discoverRoute = discoverRoute;
    // }

    public int getNonMemberRadius() {
        return nonMemberRadius;
    }

    public void setNonMemberRadius(int nonMemberRadius) {
        this.nonMemberRadius = nonMemberRadius;
    }

    public ZigBeeNwkAddressMode getAddressMode() {
        return addressMode;
    }

    public void setAddressMode(ZigBeeNwkAddressMode addressMode) {
        this.addressMode = addressMode;
    }

    public int getDestinationEndpoint() {
        return destinationEndpoint;
    }

    public void setDestinationEndpoint(final int destinationEndpoint) {
        this.destinationEndpoint = destinationEndpoint;
    }

    public int getCluster() {
        return cluster;
    }

    public void setCluster(final int cluster) {
        this.cluster = cluster;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(final int profile) {
        this.profile = profile;
    }

    public int getSourceEndpoint() {
        return sourceEndpoint;
    }

    public void setSourceEndpoint(final int sourceEndpoint) {
        this.sourceEndpoint = sourceEndpoint;
    }

    public int getGroupAddress() {
        return groupAddress;
    }

    public void setGroupAddress(final int groupAddress) {
        this.groupAddress = groupAddress;
    }

    public int getApsCounter() {
        return apsCounter;
    }

    public void setApsCounter(final int apsCounter) {
        this.apsCounter = apsCounter;
    }

    public void setPayload(int[] payload) {
        this.payload = payload;
    }

    public int[] getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ZigBeeApsFrame [sourceAddress=");
        builder.append(sourceAddress);
        builder.append("/");
        builder.append(sourceEndpoint);
        builder.append(", destinationAddress=");
        builder.append(destinationAddress);
        builder.append("/");
        builder.append(destinationEndpoint);
        builder.append(", profile=");
        builder.append(String.format("%04X", profile));
        builder.append(", cluster=");
        builder.append(cluster);
        builder.append(", addressMode=");
        builder.append(addressMode);
        builder.append(", radius=");
        builder.append(radius);
        builder.append(", sequence=");
        builder.append(sequence);
        builder.append(", payload=");
        if (payload != null) {
            for (int c = 0; c < payload.length; c++) {
                if (c != 0) {
                    builder.append(" ");
                }
                builder.append(String.format("%02X", payload[c]));
            }
        }
        builder.append("]");
        return builder.toString();
    }

}
