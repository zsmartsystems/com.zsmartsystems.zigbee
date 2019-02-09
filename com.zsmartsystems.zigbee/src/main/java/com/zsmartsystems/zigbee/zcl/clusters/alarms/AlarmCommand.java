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
 * Alarm Command value object class.
 * <p>
 * Cluster: <b>Alarms</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Alarms cluster.
 * <p>
 * The alarm command signals an alarm situation on the sending device. <br> An alarm command is
 * generated when a cluster which has alarm functionality detects an alarm condition, e.g., an
 * attribute has taken on a value that is outside a ‘safe’ range. The details are given by
 * individual cluster specifications.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class AlarmCommand extends ZclCommand {
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
    public AlarmCommand() {
        genericCommand = false;
        clusterId = 0x0009;
        commandId = 0;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
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
        final StringBuilder builder = new StringBuilder(81);
        builder.append("AlarmCommand [");
        builder.append(super.toString());
        builder.append(", alarmCode=");
        builder.append(alarmCode);
        builder.append(", clusterIdentifier=");
        builder.append(clusterIdentifier);
        builder.append(']');
        return builder.toString();
    }

}
