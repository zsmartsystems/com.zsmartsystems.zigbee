/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.alarms;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Reset Alarm Command value object class.
 * <p>
 * Cluster: <b>Alarms</b>. Command ID 0x00 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Alarms cluster.
 * <p>
 * This command resets a specific alarm. This is needed for some alarms that do not reset
 * automatically. If the alarm condition being reset was in fact still active then a new
 * notification will be generated and, where implemented, a new record added to the alarm log.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-12T12:33:13Z")
public class ResetAlarmCommand extends ZclAlarmsCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0009;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x00;

    /**
     * Alarm Code command message field.
     */
    private Integer alarmCode;

    /**
     * Cluster Identifier command message field.
     */
    private Integer clusterIdentifier;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.
     */
    @Deprecated
    public ResetAlarmCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param alarmCode {@link Integer} Alarm Code
     * @param clusterIdentifier {@link Integer} Cluster Identifier
     */
    public ResetAlarmCommand(
            Integer alarmCode,
            Integer clusterIdentifier) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.alarmCode = alarmCode;
        this.clusterIdentifier = clusterIdentifier;
    }

    /**
     * Gets Alarm Code.
     *
     * @return the Alarm Code
     */
    public Integer getAlarmCode() {
        return alarmCode;
    }

    /**
     * Sets Alarm Code.
     *
     * @param alarmCode the Alarm Code
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setAlarmCode(final Integer alarmCode) {
        this.alarmCode = alarmCode;
    }

    /**
     * Gets Cluster Identifier.
     *
     * @return the Cluster Identifier
     */
    public Integer getClusterIdentifier() {
        return clusterIdentifier;
    }

    /**
     * Sets Cluster Identifier.
     *
     * @param clusterIdentifier the Cluster Identifier
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
        final StringBuilder builder = new StringBuilder(86);
        builder.append("ResetAlarmCommand [");
        builder.append(super.toString());
        builder.append(", alarmCode=");
        builder.append(alarmCode);
        builder.append(", clusterIdentifier=");
        builder.append(clusterIdentifier);
        builder.append(']');
        return builder.toString();
    }

}
