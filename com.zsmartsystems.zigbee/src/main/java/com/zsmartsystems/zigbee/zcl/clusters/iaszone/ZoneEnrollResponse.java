package com.zsmartsystems.zigbee.zcl.clusters.iaszone;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;


/**
 * <p>
 * Zone Enroll Response value object class.
 * <p>
 * Cluster: <b>IAS Zone</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the IAS Zone cluster.
 * <p>
 * The IAS Zone cluster defines an interface to the functionality of an IAS security
 * zone device. IAS Zone supports up to two alarm types per zone, low battery
 * reports and supervision of the IAS network.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZoneEnrollResponse extends ZclCommand {
    /**
     * Enroll response code command message field.
     */
    private Integer enrollResponseCode;

    /**
     * Zone ID command message field.
     */
    private Integer zoneId;

    /**
     * Default constructor.
     */
    public ZoneEnrollResponse() {
        genericCommand = false;
        clusterId = 1280;
        commandId = 0;
        commandDirection = true;
    }

    /**
     * Gets Enroll response code.
     * @return the Enroll response code
     */
    public Integer getEnrollResponseCode() {
        return enrollResponseCode;
    }

    /**
     * Sets Enroll response code.
     * @param enrollResponseCode the Enroll response code
     */
    public void setEnrollResponseCode(final Integer enrollResponseCode) {
        this.enrollResponseCode = enrollResponseCode;
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

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(enrollResponseCode, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(zoneId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        enrollResponseCode = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        zoneId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", enrollResponseCode=");
        builder.append(enrollResponseCode);
        builder.append(", zoneId=");
        builder.append(zoneId);
        return builder.toString();
    }

}
