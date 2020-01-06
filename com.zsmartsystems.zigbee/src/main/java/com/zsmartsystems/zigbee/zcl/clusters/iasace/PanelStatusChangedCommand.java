/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
 * Panel Status Changed Command value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command ID 0x04 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * This command updates ACE clients in the system of changes to panel status recorded by the ACE
 * server (e.g., IAS CIE device).Sending the Panel Status Changed command (vs.the Get Panel
 * Status and Get Panel Status Response method) is generally useful only when there are IAS ACE
 * clients that data poll within the retry timeout of the network (e.g., less than 7.68
 * seconds). <br> An IAS ACE server shall send a Panel Status Changed command upon a change to the
 * IAS CIE’s panel status (e.g., Disarmed to Arming Away/Stay/Night, Arming Away/Stay/Night
 * to Armed, Armed to Disarmed) as defined in the Panel Status field. <br> When Panel Status is
 * Arming Away/Stay/Night, an IAS ACE server should send Panel Status Changed commands every
 * second in order to update the Seconds Remaining. In some markets (e.g., North America), the
 * final 10 seconds of the Arming Away/Stay/Night sequence requires a separate audible
 * notification (e.g., a double tone).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-06T18:45:28Z")
public class PanelStatusChangedCommand extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0501;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x04;

    /**
     * Panel Status command message field.
     * <p>
     * Indicates the number of seconds remaining for the server to be in the state indicated in
     * the PanelStatus parameter. The SecondsRemaining parameter shall be provided if the
     * PanelStatus parameter has a value of 0x04 (Exit delay) or 0x05 (Entry delay).
     * <p>
     * The default value shall be 0x00.
     */
    private Integer panelStatus;

    /**
     * Seconds Remaining command message field.
     */
    private Integer secondsRemaining;

    /**
     * Audible Notification command message field.
     */
    private Integer audibleNotification;

    /**
     * Alarm Status command message field.
     */
    private Integer alarmStatus;

    /**
     * Default constructor.
     */
    public PanelStatusChangedCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Panel Status.
     * <p>
     * Indicates the number of seconds remaining for the server to be in the state indicated in
     * the PanelStatus parameter. The SecondsRemaining parameter shall be provided if the
     * PanelStatus parameter has a value of 0x04 (Exit delay) or 0x05 (Entry delay).
     * <p>
     * The default value shall be 0x00.
     *
     * @return the Panel Status
     */
    public Integer getPanelStatus() {
        return panelStatus;
    }

    /**
     * Sets Panel Status.
     * <p>
     * Indicates the number of seconds remaining for the server to be in the state indicated in
     * the PanelStatus parameter. The SecondsRemaining parameter shall be provided if the
     * PanelStatus parameter has a value of 0x04 (Exit delay) or 0x05 (Entry delay).
     * <p>
     * The default value shall be 0x00.
     *
     * @param panelStatus the Panel Status
     * @return the PanelStatusChangedCommand command
     */
    public PanelStatusChangedCommand setPanelStatus(final Integer panelStatus) {
        this.panelStatus = panelStatus;
        return this;
    }

    /**
     * Gets Seconds Remaining.
     *
     * @return the Seconds Remaining
     */
    public Integer getSecondsRemaining() {
        return secondsRemaining;
    }

    /**
     * Sets Seconds Remaining.
     *
     * @param secondsRemaining the Seconds Remaining
     * @return the PanelStatusChangedCommand command
     */
    public PanelStatusChangedCommand setSecondsRemaining(final Integer secondsRemaining) {
        this.secondsRemaining = secondsRemaining;
        return this;
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
     * @return the PanelStatusChangedCommand command
     */
    public PanelStatusChangedCommand setAudibleNotification(final Integer audibleNotification) {
        this.audibleNotification = audibleNotification;
        return this;
    }

    /**
     * Gets Alarm Status.
     *
     * @return the Alarm Status
     */
    public Integer getAlarmStatus() {
        return alarmStatus;
    }

    /**
     * Sets Alarm Status.
     *
     * @param alarmStatus the Alarm Status
     * @return the PanelStatusChangedCommand command
     */
    public PanelStatusChangedCommand setAlarmStatus(final Integer alarmStatus) {
        this.alarmStatus = alarmStatus;
        return this;
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
        final StringBuilder builder = new StringBuilder(165);
        builder.append("PanelStatusChangedCommand [");
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
