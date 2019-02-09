/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iaszone;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Zone Status Change Notification Command value object class.
 * <p>
 * Cluster: <b>IAS Zone</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS Zone cluster.
 * <p>
 * The Zone Status Change Notification command is generated when a change takes place in one or
 * more bits of the ZoneStatus attribute.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ZoneStatusChangeNotificationCommand extends ZclCommand {
    /**
     * Zone Status command message field.
     * <p>
     * The Zone Status field shall be the current value of the ZoneStatus attribute.
     */
    private Integer zoneStatus;

    /**
     * Extended Status command message field.
     * <p>
     * The Extended Status field is reserved for additional status information and shall be
     * set to zero.
     */
    private Integer extendedStatus;

    /**
     * Zone ID command message field.
     * <p>
     * Zone ID is the index of the Zone in the CIE's zone table.
     */
    private Integer zoneId;

    /**
     * Delay command message field.
     * <p>
     * The Delay field is defined as the amount of time, in quarter-seconds, from the moment
     * when a change takes place in one or more bits of the Zone Status attribute and the
     * successful transmission of the Zone Status Change Notification. This is designed to
     * help congested networks or offline servers quantify the amount of time from when an
     * event was detected and when it could be reported to the client.
     */
    private Integer delay;

    /**
     * Default constructor.
     */
    public ZoneStatusChangeNotificationCommand() {
        genericCommand = false;
        clusterId = 0x0500;
        commandId = 0;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Zone Status.
     * <p>
     * The Zone Status field shall be the current value of the ZoneStatus attribute.
     *
     * @return the Zone Status
     */
    public Integer getZoneStatus() {
        return zoneStatus;
    }

    /**
     * Sets Zone Status.
     * <p>
     * The Zone Status field shall be the current value of the ZoneStatus attribute.
     *
     * @param zoneStatus the Zone Status
     */
    public void setZoneStatus(final Integer zoneStatus) {
        this.zoneStatus = zoneStatus;
    }

    /**
     * Gets Extended Status.
     * <p>
     * The Extended Status field is reserved for additional status information and shall be
     * set to zero.
     *
     * @return the Extended Status
     */
    public Integer getExtendedStatus() {
        return extendedStatus;
    }

    /**
     * Sets Extended Status.
     * <p>
     * The Extended Status field is reserved for additional status information and shall be
     * set to zero.
     *
     * @param extendedStatus the Extended Status
     */
    public void setExtendedStatus(final Integer extendedStatus) {
        this.extendedStatus = extendedStatus;
    }

    /**
     * Gets Zone ID.
     * <p>
     * Zone ID is the index of the Zone in the CIE's zone table.
     *
     * @return the Zone ID
     */
    public Integer getZoneId() {
        return zoneId;
    }

    /**
     * Sets Zone ID.
     * <p>
     * Zone ID is the index of the Zone in the CIE's zone table.
     *
     * @param zoneId the Zone ID
     */
    public void setZoneId(final Integer zoneId) {
        this.zoneId = zoneId;
    }

    /**
     * Gets Delay.
     * <p>
     * The Delay field is defined as the amount of time, in quarter-seconds, from the moment
     * when a change takes place in one or more bits of the Zone Status attribute and the
     * successful transmission of the Zone Status Change Notification. This is designed to
     * help congested networks or offline servers quantify the amount of time from when an
     * event was detected and when it could be reported to the client.
     *
     * @return the Delay
     */
    public Integer getDelay() {
        return delay;
    }

    /**
     * Sets Delay.
     * <p>
     * The Delay field is defined as the amount of time, in quarter-seconds, from the moment
     * when a change takes place in one or more bits of the Zone Status attribute and the
     * successful transmission of the Zone Status Change Notification. This is designed to
     * help congested networks or offline servers quantify the amount of time from when an
     * event was detected and when it could be reported to the client.
     *
     * @param delay the Delay
     */
    public void setDelay(final Integer delay) {
        this.delay = delay;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(zoneStatus, ZclDataType.ENUMERATION_16_BIT);
        serializer.serialize(extendedStatus, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(zoneId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(delay, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        zoneStatus = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_16_BIT);
        extendedStatus = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        zoneId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        delay = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(153);
        builder.append("ZoneStatusChangeNotificationCommand [");
        builder.append(super.toString());
        builder.append(", zoneStatus=");
        builder.append(zoneStatus);
        builder.append(", extendedStatus=");
        builder.append(extendedStatus);
        builder.append(", zoneId=");
        builder.append(zoneId);
        builder.append(", delay=");
        builder.append(delay);
        builder.append(']');
        return builder.toString();
    }

}
