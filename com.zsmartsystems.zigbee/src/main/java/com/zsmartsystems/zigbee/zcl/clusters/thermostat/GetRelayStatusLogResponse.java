/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.thermostat;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Relay Status Log Response value object class.
 * <p>
 * Cluster: <b>Thermostat</b>. Command ID 0x01 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Thermostat cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-10T12:07:00Z")
public class GetRelayStatusLogResponse extends ZclThermostatCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0201;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x01;

    /**
     * Time Of Day command message field.
     */
    private Integer timeOfDay;

    /**
     * Relay Status command message field.
     */
    private Integer relayStatus;

    /**
     * Local Temperature command message field.
     */
    private Integer localTemperature;

    /**
     * Humidity command message field.
     */
    private Integer humidity;

    /**
     * Setpoint command message field.
     */
    private Integer setpoint;

    /**
     * Unread Entries command message field.
     */
    private Integer unreadEntries;

    /**
     * Default constructor.
     */
    public GetRelayStatusLogResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Time Of Day.
     *
     * @return the Time Of Day
     */
    public Integer getTimeOfDay() {
        return timeOfDay;
    }

    /**
     * Sets Time Of Day.
     *
     * @param timeOfDay the Time Of Day
     */
    public void setTimeOfDay(final Integer timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    /**
     * Gets Relay Status.
     *
     * @return the Relay Status
     */
    public Integer getRelayStatus() {
        return relayStatus;
    }

    /**
     * Sets Relay Status.
     *
     * @param relayStatus the Relay Status
     */
    public void setRelayStatus(final Integer relayStatus) {
        this.relayStatus = relayStatus;
    }

    /**
     * Gets Local Temperature.
     *
     * @return the Local Temperature
     */
    public Integer getLocalTemperature() {
        return localTemperature;
    }

    /**
     * Sets Local Temperature.
     *
     * @param localTemperature the Local Temperature
     */
    public void setLocalTemperature(final Integer localTemperature) {
        this.localTemperature = localTemperature;
    }

    /**
     * Gets Humidity.
     *
     * @return the Humidity
     */
    public Integer getHumidity() {
        return humidity;
    }

    /**
     * Sets Humidity.
     *
     * @param humidity the Humidity
     */
    public void setHumidity(final Integer humidity) {
        this.humidity = humidity;
    }

    /**
     * Gets Setpoint.
     *
     * @return the Setpoint
     */
    public Integer getSetpoint() {
        return setpoint;
    }

    /**
     * Sets Setpoint.
     *
     * @param setpoint the Setpoint
     */
    public void setSetpoint(final Integer setpoint) {
        this.setpoint = setpoint;
    }

    /**
     * Gets Unread Entries.
     *
     * @return the Unread Entries
     */
    public Integer getUnreadEntries() {
        return unreadEntries;
    }

    /**
     * Sets Unread Entries.
     *
     * @param unreadEntries the Unread Entries
     */
    public void setUnreadEntries(final Integer unreadEntries) {
        this.unreadEntries = unreadEntries;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(timeOfDay, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(relayStatus, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(localTemperature, ZclDataType.SIGNED_16_BIT_INTEGER);
        serializer.serialize(humidity, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(setpoint, ZclDataType.SIGNED_16_BIT_INTEGER);
        serializer.serialize(unreadEntries, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        timeOfDay = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        relayStatus = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        localTemperature = (Integer) deserializer.deserialize(ZclDataType.SIGNED_16_BIT_INTEGER);
        humidity = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        setpoint = (Integer) deserializer.deserialize(ZclDataType.SIGNED_16_BIT_INTEGER);
        unreadEntries = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(213);
        builder.append("GetRelayStatusLogResponse [");
        builder.append(super.toString());
        builder.append(", timeOfDay=");
        builder.append(timeOfDay);
        builder.append(", relayStatus=");
        builder.append(relayStatus);
        builder.append(", localTemperature=");
        builder.append(localTemperature);
        builder.append(", humidity=");
        builder.append(humidity);
        builder.append(", setpoint=");
        builder.append(setpoint);
        builder.append(", unreadEntries=");
        builder.append(unreadEntries);
        builder.append(']');
        return builder.toString();
    }

}
