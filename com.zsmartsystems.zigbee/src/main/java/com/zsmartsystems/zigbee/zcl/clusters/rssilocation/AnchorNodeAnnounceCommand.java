package com.zsmartsystems.zigbee.zcl.clusters.rssilocation;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * Anchor Node Announce Command value object class.
 * </p>
 * <p>
 * Cluster: <b>RSSI Location</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the RSSI Location cluster.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class AnchorNodeAnnounceCommand extends ZclCommand {
    /**
     * Anchor Node Address command message field.
     */
    private Long anchorNodeAddress;

    /**
     * Coordinate 1 command message field.
     */
    private Integer coordinate1;

    /**
     * Coordinate 2 command message field.
     */
    private Integer coordinate2;

    /**
     * Coordinate 3 command message field.
     */
    private Integer coordinate3;

    /**
     * Default constructor.
     */
    public AnchorNodeAnnounceCommand() {
        genericCommand = false;
        clusterId = 11;
        commandId = 6;
        commandDirection = true;
    }

    /**
     * Gets Anchor Node Address.
     * @return the Anchor Node Address
     */
    public Long getAnchorNodeAddress() {
        return anchorNodeAddress;
    }

    /**
     * Sets Anchor Node Address.
     * @param anchorNodeAddress the Anchor Node Address
     */
    public void setAnchorNodeAddress(final Long anchorNodeAddress) {
        this.anchorNodeAddress = anchorNodeAddress;
    }

    /**
     * Gets Coordinate 1.
     * @return the Coordinate 1
     */
    public Integer getCoordinate1() {
        return coordinate1;
    }

    /**
     * Sets Coordinate 1.
     * @param coordinate1 the Coordinate 1
     */
    public void setCoordinate1(final Integer coordinate1) {
        this.coordinate1 = coordinate1;
    }

    /**
     * Gets Coordinate 2.
     * @return the Coordinate 2
     */
    public Integer getCoordinate2() {
        return coordinate2;
    }

    /**
     * Sets Coordinate 2.
     * @param coordinate2 the Coordinate 2
     */
    public void setCoordinate2(final Integer coordinate2) {
        this.coordinate2 = coordinate2;
    }

    /**
     * Gets Coordinate 3.
     * @return the Coordinate 3
     */
    public Integer getCoordinate3() {
        return coordinate3;
    }

    /**
     * Sets Coordinate 3.
     * @param coordinate3 the Coordinate 3
     */
    public void setCoordinate3(final Integer coordinate3) {
        this.coordinate3 = coordinate3;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(anchorNodeAddress, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(coordinate1, ZclDataType.SIGNED_16_BIT_INTEGER);
        serializer.serialize(coordinate2, ZclDataType.SIGNED_16_BIT_INTEGER);
        serializer.serialize(coordinate3, ZclDataType.SIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        anchorNodeAddress = (Long) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        coordinate1 = (Integer) deserializer.deserialize(ZclDataType.SIGNED_16_BIT_INTEGER);
        coordinate2 = (Integer) deserializer.deserialize(ZclDataType.SIGNED_16_BIT_INTEGER);
        coordinate3 = (Integer) deserializer.deserialize(ZclDataType.SIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", ");
        builder.append("anchorNodeAddress = ");
        builder.append(anchorNodeAddress);
        builder.append(", ");
        builder.append("coordinate1 = ");
        builder.append(coordinate1);
        builder.append(", ");
        builder.append("coordinate2 = ");
        builder.append(coordinate2);
        builder.append(", ");
        builder.append("coordinate3 = ");
        builder.append(coordinate3);
        return builder.toString();
    }

}
