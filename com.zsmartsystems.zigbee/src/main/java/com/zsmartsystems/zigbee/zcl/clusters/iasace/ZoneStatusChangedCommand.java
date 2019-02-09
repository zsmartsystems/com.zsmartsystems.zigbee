/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Zone Status Changed Command value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * This command updates ACE clients in the system of changes to zone status recorded by the ACE
 * server (e.g., IAS CIE device). An IAS ACE server should send a Zone Status Changed command
 * upon a change to an IAS Zone device’s ZoneStatus that it manages (i.e., IAS ACE server should
 * send a Zone Status Changed command upon receipt of a Zone Status Change Notification
 * command).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ZoneStatusChangedCommand extends ZclCommand {
    /**
     * Zone ID command message field.
     * <p>
     * The index of the Zone in the CIE’s zone table. If none is programmed, the ZoneID attribute
     * default value shall be indicated in this field.
     */
    private Integer zoneId;

    /**
     * Zone Status command message field.
     */
    private Integer zoneStatus;

    /**
     * Audible Notification command message field.
     */
    private Integer audibleNotification;

    /**
     * Zone Label command message field.
     * <p>
     * Provides the ZoneLabel stored in the IAS CIE. If none is programmed, the IAS ACE server
     * shall transmit a string with a length of zero. There is no minimum or maximum length to the
     * Zone Label field; however, the Zone Label should be between 16 to 24 alphanumeric
     * characters in length.
     */
    private String zoneLabel;

    /**
     * Default constructor.
     */
    public ZoneStatusChangedCommand() {
        genericCommand = false;
        clusterId = 0x0501;
        commandId = 3;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Zone ID.
     * <p>
     * The index of the Zone in the CIE’s zone table. If none is programmed, the ZoneID attribute
     * default value shall be indicated in this field.
     *
     * @return the Zone ID
     */
    public Integer getZoneId() {
        return zoneId;
    }

    /**
     * Sets Zone ID.
     * <p>
     * The index of the Zone in the CIE’s zone table. If none is programmed, the ZoneID attribute
     * default value shall be indicated in this field.
     *
     * @param zoneId the Zone ID
     */
    public void setZoneId(final Integer zoneId) {
        this.zoneId = zoneId;
    }

    /**
     * Gets Zone Status.
     *
     * @return the Zone Status
     */
    public Integer getZoneStatus() {
        return zoneStatus;
    }

    /**
     * Sets Zone Status.
     *
     * @param zoneStatus the Zone Status
     */
    public void setZoneStatus(final Integer zoneStatus) {
        this.zoneStatus = zoneStatus;
    }

    /**
     * Gets Audible Notification.
     *
     * @return the Audible Notification
     */
    public Integer getAudibleNotification() {
        return audibleNotification;
    }

    /**
     * Sets Audible Notification.
     *
     * @param audibleNotification the Audible Notification
     */
    public void setAudibleNotification(final Integer audibleNotification) {
        this.audibleNotification = audibleNotification;
    }

    /**
     * Gets Zone Label.
     * <p>
     * Provides the ZoneLabel stored in the IAS CIE. If none is programmed, the IAS ACE server
     * shall transmit a string with a length of zero. There is no minimum or maximum length to the
     * Zone Label field; however, the Zone Label should be between 16 to 24 alphanumeric
     * characters in length.
     *
     * @return the Zone Label
     */
    public String getZoneLabel() {
        return zoneLabel;
    }

    /**
     * Sets Zone Label.
     * <p>
     * Provides the ZoneLabel stored in the IAS CIE. If none is programmed, the IAS ACE server
     * shall transmit a string with a length of zero. There is no minimum or maximum length to the
     * Zone Label field; however, the Zone Label should be between 16 to 24 alphanumeric
     * characters in length.
     *
     * @param zoneLabel the Zone Label
     */
    public void setZoneLabel(final String zoneLabel) {
        this.zoneLabel = zoneLabel;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(zoneId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(zoneStatus, ZclDataType.ENUMERATION_16_BIT);
        serializer.serialize(audibleNotification, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(zoneLabel, ZclDataType.CHARACTER_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        zoneId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        zoneStatus = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_16_BIT);
        audibleNotification = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        zoneLabel = (String) deserializer.deserialize(ZclDataType.CHARACTER_STRING);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(151);
        builder.append("ZoneStatusChangedCommand [");
        builder.append(super.toString());
        builder.append(", zoneId=");
        builder.append(zoneId);
        builder.append(", zoneStatus=");
        builder.append(zoneStatus);
        builder.append(", audibleNotification=");
        builder.append(audibleNotification);
        builder.append(", zoneLabel=");
        builder.append(zoneLabel);
        builder.append(']');
        return builder.toString();
    }

}
