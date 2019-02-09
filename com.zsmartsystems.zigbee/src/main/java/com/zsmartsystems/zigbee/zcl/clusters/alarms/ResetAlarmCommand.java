/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.alarms;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Reset Alarm Command value object class.
 * <p>
 * Cluster: <b>Alarms</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Alarms cluster.
 * <p>
 * This command resets a specific alarm. This is needed for some alarms that do not reset
 * automatically. If the alarm condition being reset was in fact still active then a new
 * notification will be generated and, where implemented, a new record added to the alarm log.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ResetAlarmCommand extends ZclCommand {
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
     */
    public ResetAlarmCommand() {
        genericCommand = false;
        clusterId = 0x0009;
        commandId = 0;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
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
     */
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
