package com.zsmartsystems.zigbee.zcl.clusters.alarms;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;


/**
 * <p>
 * Alarm Command value object class.
 * <p>
 * Cluster: <b>Alarms</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Alarms cluster.
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
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class AlarmCommand extends ZclCommand {
    /**
     * Alarm code command message field.
     */
    private Integer alarmCode;

    /**
     * Cluster identifier command message field.
     */
    private Integer clusterIdentifier;

    /**
     * Default constructor.
     */
    public AlarmCommand() {
        genericCommand = false;
        clusterId = 9;
        commandId = 0;
        commandDirection = false;
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

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(alarmCode, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(clusterIdentifier, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        alarmCode = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        clusterIdentifier = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", alarmCode=");
        builder.append(alarmCode);
        builder.append(", clusterIdentifier=");
        builder.append(clusterIdentifier);
        return builder.toString();
    }

}
