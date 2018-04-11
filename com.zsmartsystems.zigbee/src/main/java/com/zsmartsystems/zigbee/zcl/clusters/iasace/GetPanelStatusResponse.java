/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Get Panel Status Response value object class.
 * <p>
 * This command updates requesting IAS ACE clients in the system of changes to the security panel status recorded by
 * the ACE server (e.g., IAS CIE device).
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * The IAS ACE cluster defines an interface to the functionality of any Ancillary
 * Control Equipment of the IAS system. Using this cluster, a ZigBee enabled ACE
 * device can access a IAS CIE device and manipulate the IAS system, on behalf of a
 * level-2 user.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-04-10T07:28:44Z")
public class GetPanelStatusResponse extends ZclCommand {
    /**
     * Panel Status command message field.
     * <p>
     * Defines the current status of the alarm panel.
     */
    private Integer panelStatus;

    /**
     * Seconds Remaining command message field.
     * <p>
     * Indicates the number of seconds remaining for  the server to be in the state indicated in the PanelStatus parameter.
     * The SecondsRemaining parameter SHALL be provided if the PanelStatus parameter has a value of 0x04 (Exit delay) or 0x05 (Entry delay).
     * <p>
     * The default value SHALL be 0x00.
     */
    private Integer secondsRemaining;

    /**
     * Audible Notification command message field.
     * <p>
     * Provide the ACE client with information on which type of audible notification it SHOULD make for the zone status change. This field is useful
     * for telling the ACE client to play a standard chime or other audio indication or to mute and not sound an audible notification at all. This
     * field also allows manufacturers to create additional audible alert types (e.g., dog barking, windchimes, conga drums) to enable users to
     * customise their system.
     */
    private Integer audibleNotification;

    /**
     * Alarm Status command message field.
     * <p>
     * Provides the ACE client with information on the type of alarm the panel is in if its Panel Status field indicates it is “in alarm.” This field
     * MAY be useful for ACE clients to display or otherwise initiate notification for users.
     */
    private Integer alarmStatus;

    /**
     * Default constructor.
     */
    public GetPanelStatusResponse() {
        genericCommand = false;
        clusterId = 1281;
        commandId = 5;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Panel Status.
     *
     * Defines the current status of the alarm panel.
     *
     * @return the Panel Status
     */
    public Integer getPanelStatus() {
        return panelStatus;
    }

    /**
     * Sets Panel Status.
     *
     * Defines the current status of the alarm panel.
     *
     * @param panelStatus the Panel Status
     */
    public void setPanelStatus(final Integer panelStatus) {
        this.panelStatus = panelStatus;
    }

    /**
     * Gets Seconds Remaining.
     *
     * Indicates the number of seconds remaining for  the server to be in the state indicated in the PanelStatus parameter.
     * The SecondsRemaining parameter SHALL be provided if the PanelStatus parameter has a value of 0x04 (Exit delay) or 0x05 (Entry delay).
     * <p>
     * The default value SHALL be 0x00.
     *
     * @return the Seconds Remaining
     */
    public Integer getSecondsRemaining() {
        return secondsRemaining;
    }

    /**
     * Sets Seconds Remaining.
     *
     * Indicates the number of seconds remaining for  the server to be in the state indicated in the PanelStatus parameter.
     * The SecondsRemaining parameter SHALL be provided if the PanelStatus parameter has a value of 0x04 (Exit delay) or 0x05 (Entry delay).
     * <p>
     * The default value SHALL be 0x00.
     *
     * @param secondsRemaining the Seconds Remaining
     */
    public void setSecondsRemaining(final Integer secondsRemaining) {
        this.secondsRemaining = secondsRemaining;
    }

    /**
     * Gets Audible Notification.
     *
     * Provide the ACE client with information on which type of audible notification it SHOULD make for the zone status change. This field is useful for telling the ACE client to play a standard chime or other audio indication or to mute and not sound an audible notification at all. This field also allows manufacturers to create additional audible alert types (e.g., dog barking, windchimes, conga drums) to enable users to customise their system.
     *
     * @return the Audible Notification
     */
    public Integer getAudibleNotification() {
        return audibleNotification;
    }

    /**
     * Sets Audible Notification.
     *
     * Provide the ACE client with information on which type of audible notification it SHOULD make for the zone status change. This field is useful
     * for telling the ACE client to play a standard chime or other audio indication or to mute and not sound an audible notification at all. This
     * field also allows manufacturers to create additional audible alert types (e.g., dog barking, windchimes, conga drums) to enable users to
     * customise their system.
     *
     * @param audibleNotification the Audible Notification
     */
    public void setAudibleNotification(final Integer audibleNotification) {
        this.audibleNotification = audibleNotification;
    }

    /**
     * Gets Alarm Status.
     *
     * Provides the ACE client with information on the type of alarm the panel is in if its Panel Status field indicates it is “in alarm.” This field MAY be useful for ACE clients to display or otherwise initiate notification for users.
     *
     * @return the Alarm Status
     */
    public Integer getAlarmStatus() {
        return alarmStatus;
    }

    /**
     * Sets Alarm Status.
     *
     * Provides the ACE client with information on the type of alarm the panel is in if its Panel Status field indicates it is “in alarm.” This field
     * MAY be useful for ACE clients to display or otherwise initiate notification for users.
     *
     * @param alarmStatus the Alarm Status
     */
    public void setAlarmStatus(final Integer alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(panelStatus, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(secondsRemaining, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(audibleNotification, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(alarmStatus, ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        panelStatus = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        secondsRemaining = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        audibleNotification = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        alarmStatus = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(162);
        builder.append("GetPanelStatusResponse [");
        builder.append(super.toString());
        builder.append(", panelStatus=");
        builder.append(panelStatus);
        builder.append(", secondsRemaining=");
        builder.append(secondsRemaining);
        builder.append(", audibleNotification=");
        builder.append(audibleNotification);
        builder.append(", alarmStatus=");
        builder.append(alarmStatus);
        builder.append(']');
        return builder.toString();
    }

}
