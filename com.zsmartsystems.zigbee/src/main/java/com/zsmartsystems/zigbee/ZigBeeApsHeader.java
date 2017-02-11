package com.zsmartsystems.zigbee;

/**
 * Defines the ZigBee APS frame header.
 * <p>
 * Note that note all APS header fields may be present in this class. The class is passed from the framework to the
 * hardware drivers where it is processed accordingly. Should data be missing that is required by a hardware
 * implemention, it will be added.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeApsHeader {

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

    public int getDestinationEndpoint() {
        return destinationEndpoint;
    }

    public void setDestinationEndpoint(int destinationEndpoint) {
        this.destinationEndpoint = destinationEndpoint;
    }

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public int getSourceEndpoint() {
        return sourceEndpoint;
    }

    public void setSourceEndpoint(int sourceEndpoint) {
        this.sourceEndpoint = sourceEndpoint;
    }

    public int getGroupAddress() {
        return groupAddress;
    }

    public void setGroupAddress(int groupAddress) {
        this.groupAddress = groupAddress;
    }

    @Override
    public String toString() {
        return "ZigBeeApsHeader [destinationEndpoint=" + destinationEndpoint + ", cluster=" + cluster + ", profile="
                + profile + ", sourceEndpoint=" + sourceEndpoint + ", groupAddress=" + groupAddress + "]";
    }
}
