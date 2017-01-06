package com.zsmartsystems.zigbee.zcl.clusters.iaszone;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * Zone Enroll Request Command value object class.
 * </p>
 * <p>
 * Cluster: <b>IAS Zone</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS Zone cluster.
 * </p>
 * <p>
 * The IAS Zone cluster defines an interface to the functionality of an IAS security
 * zone device. IAS Zone supports up to two alarm types per zone, low battery
 * reports and supervision of the IAS network.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class ZoneEnrollRequestCommand extends ZclCommand {
    /**
     * Zone Type command message field.
     */
    private Integer zoneType;

    /**
     * Manufacturer Code command message field.
     */
    private Integer manufacturerCode;

    /**
     * Default constructor setting the command type field.
     */
    public ZoneEnrollRequestCommand() {
        genericCommand = false;
        clusterId = 1280;
        commandId = 1;
        commandDirection = false;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public ZoneEnrollRequestCommand(final Map<Integer, Object> fields) {
        this();
        zoneType = (Integer) fields.get(0);
        manufacturerCode = (Integer) fields.get(1);
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
     * Gets Manufacturer Code.
     * @return the Manufacturer Code
     */
    public Integer getManufacturerCode() {
        return manufacturerCode;
    }

    /**
     * Sets Manufacturer Code.
     * @param manufacturerCode the Manufacturer Code
     */
    public void setManufacturerCode(final Integer manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(zoneType, ZclDataType.ENUMERATION_16_BIT);
        serializer.serialize(manufacturerCode, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        zoneType = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_16_BIT);
        manufacturerCode = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", ");
        builder.append("zoneType = ");
        builder.append(zoneType);
        builder.append(", ");
        builder.append("manufacturerCode = ");
        builder.append(manufacturerCode);
        return builder.toString();
    }

}
