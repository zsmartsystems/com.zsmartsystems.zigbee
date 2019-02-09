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
 * Get Alarm Response value object class.
 * <p>
 * Cluster: <b>Alarms</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Alarms cluster.
 * <p>
 * If there is at least one alarm record in the alarm table then the status field is set to SUCCESS.
 * The alarm code, cluster identifier and time stamp fields shall all be present and shall take
 * their values from the item in the alarm table that they are reporting.If there are no more
 * alarms logged in the alarm table then the status field is set to NOT_FOUND and the alarm code,
 * cluster identifier and time stamp fields shall be omitted.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetAlarmResponse extends ZclCommand {
    /**
     * Status command message field.
     */
    private Integer status;

    /**
     * Alarm Code command message field.
     */
    private Integer alarmCode;

    /**
     * Cluster Identifier command message field.
     */
    private Integer clusterIdentifier;

    /**
     * Timestamp command message field.
     */
    private Integer timestamp;

    /**
     * Default constructor.
     */
    public GetAlarmResponse() {
        genericCommand = false;
        clusterId = 0x0009;
        commandId = 1;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Status.
     *
     * @return the Status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets Status.
     *
     * @param status the Status
     */
    public void setStatus(final Integer status) {
        this.status = status;
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

    /**
     * Gets Timestamp.
     *
     * @return the Timestamp
     */
    public Integer getTimestamp() {
        return timestamp;
    }

    /**
     * Sets Timestamp.
     *
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
        final StringBuilder builder = new StringBuilder(140);
        builder.append("GetAlarmResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(", alarmCode=");
        builder.append(alarmCode);
        builder.append(", clusterIdentifier=");
        builder.append(clusterIdentifier);
        builder.append(", timestamp=");
        builder.append(timestamp);
        builder.append(']');
        return builder.toString();
    }

}
