package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * Get Zone Information Response value object class.
 * </p>
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * </p>
 * <p>
 * The IAS ACE cluster defines an interface to the functionality of any Ancillary
 * Control Equipment of the IAS system. Using this cluster, a ZigBee enabled ACE
 * device can access a IAS CIE device and manipulate the IAS system, on behalf of a
 * level-2 user.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class GetZoneInformationResponse extends ZclCommand {
    /**
     * Zone ID command message field.
     */
    private Integer zoneId;

    /**
     * Zone Type command message field.
     */
    private Integer zoneType;

    /**
     * IEEE address command message field.
     */
    private Long ieeeAddress;

    /**
     * Default constructor setting the command type field.
     */
    public GetZoneInformationResponse() {
        genericCommand = false;
        clusterId = 1281;
        commandId = 2;
        commandDirection = false;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public GetZoneInformationResponse(final Map<Integer, Object> fields) {
        this();
        zoneId = (Integer) fields.get(0);
        zoneType = (Integer) fields.get(1);
        ieeeAddress = (Long) fields.get(2);
    }

    /**
     * Gets Zone ID.
     * @return the Zone ID
     */
    public Integer getZoneId() {
        return zoneId;
    }

    /**
     * Sets Zone ID.
     * @param zoneId the Zone ID
     */
    public void setZoneId(final Integer zoneId) {
        this.zoneId = zoneId;
    }

    /**
     * Gets Zone Type.
     * @return the Zone Type
     */
    public Integer getZoneType() {
        return zoneType;
    }

    /**
     * Sets Zone Type.
     * @param zoneType the Zone Type
     */
    public void setZoneType(final Integer zoneType) {
        this.zoneType = zoneType;
    }

    /**
     * Gets IEEE address.
     * @return the IEEE address
     */
    public Long getIeeeAddress() {
        return ieeeAddress;
    }

    /**
     * Sets IEEE address.
     * @param ieeeAddress the IEEE address
     */
    public void setIeeeAddress(final Long ieeeAddress) {
        this.ieeeAddress = ieeeAddress;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(zoneId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(zoneType, ZclDataType.ENUMERATION_16_BIT);
        serializer.serialize(ieeeAddress, ZclDataType.IEEE_ADDRESS);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        zoneId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        zoneType = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_16_BIT);
        ieeeAddress = (Long) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", ");
        builder.append("zoneId = ");
        builder.append(zoneId);
        builder.append(", ");
        builder.append("zoneType = ");
        builder.append(zoneType);
        builder.append(", ");
        builder.append("ieeeAddress = ");
        builder.append(ieeeAddress);
        return builder.toString();
    }

}
