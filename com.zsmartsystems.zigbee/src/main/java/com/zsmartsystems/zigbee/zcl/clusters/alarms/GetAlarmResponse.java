package com.zsmartsystems.zigbee.zcl.clusters.alarms;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * Get Alarm Response value object class.
 * </p>
 * <p>
 * Cluster: <b>Alarms</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Alarms cluster.
 * </p>
 * <p>
 * Attributes and commands for sending alarm notifications and configuring alarm
 * functionality.
 * <br>
 * Alarm conditions and their respective alarm codes are described in individual
 * clusters, along with an alarm mask field. Where not masked, alarm notifications
 * are reported to subscribed targets using binding.
 * <br>
 * Where an alarm table is implemented, all alarms, masked or otherwise, are
 * recorded and may be retrieved on demand.
 * <br>
 * Alarms may either reset automatically when the conditions that cause are no
 * longer active, or may need to be explicitly reset.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class GetAlarmResponse extends ZclCommand {
    /**
     * Status command message field.
     */
    private Integer status;

    /**
     * Alarm code command message field.
     */
    private Integer alarmCode;

    /**
     * Cluster identifier command message field.
     */
    private Integer clusterIdentifier;

    /**
     * Timestamp command message field.
     */
    private Integer timestamp;

    /**
     * Default constructor setting the command type field.
     */
    public GetAlarmResponse() {
        genericCommand = false;
        clusterId = 9;
        commandId = 1;
        commandDirection = false;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public GetAlarmResponse(final Map<Integer, Object> fields) {
        this();
        status = (Integer) fields.get(0);
        alarmCode = (Integer) fields.get(1);
        clusterIdentifier = (Integer) fields.get(2);
        timestamp = (Integer) fields.get(3);
    }

    /**
     * Gets Status.
     * @return the Status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets Status.
     * @param status the Status
     */
    public void setStatus(final Integer status) {
        this.status = status;
    }

    /**
     * Gets Alarm code.
     * @return the Alarm code
     */
    public Integer getAlarmCode() {
        return alarmCode;
    }

    /**
     * Sets Alarm code.
     * @param alarmCode the Alarm code
     */
    public void setAlarmCode(final Integer alarmCode) {
        this.alarmCode = alarmCode;
    }

    /**
     * Gets Cluster identifier.
     * @return the Cluster identifier
     */
    public Integer getClusterIdentifier() {
        return clusterIdentifier;
    }

    /**
     * Sets Cluster identifier.
     * @param clusterIdentifier the Cluster identifier
     */
    public void setClusterIdentifier(final Integer clusterIdentifier) {
        this.clusterIdentifier = clusterIdentifier;
    }

    /**
     * Gets Timestamp.
     * @return the Timestamp
     */
    public Integer getTimestamp() {
        return timestamp;
    }

    /**
     * Sets Timestamp.
     * @param timestamp the Timestamp
     */
    public void setTimestamp(final Integer timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(status, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(alarmCode, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(clusterIdentifier, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(timestamp, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        status = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        alarmCode = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        clusterIdentifier = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        timestamp = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", ");
        builder.append("status = ");
        builder.append(status);
        builder.append(", ");
        builder.append("alarmCode = ");
        builder.append(alarmCode);
        builder.append(", ");
        builder.append("clusterIdentifier = ");
        builder.append(clusterIdentifier);
        builder.append(", ");
        builder.append("timestamp = ");
        builder.append(timestamp);
        return builder.toString();
    }

}
