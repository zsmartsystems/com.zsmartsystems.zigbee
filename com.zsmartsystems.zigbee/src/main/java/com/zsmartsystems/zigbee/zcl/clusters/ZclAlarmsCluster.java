/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.AlarmCommand;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.GetAlarmCommand;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.GetAlarmResponse;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.ResetAlarmCommand;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.ResetAlarmLogCommand;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.ResetAllAlarmsCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Alarms</b> cluster implementation (<i>Cluster ID 0x0009</i>).
 * <p>
 * Attributes and commands for sending alarm notifications and configuring alarm
 * functionality.
 * <p>
 * Alarm conditions and their respective alarm codes are described in individual clusters,
 * along with an alarm mask field. Where not masked, alarm notifications are reported to
 * subscribed targets using binding.
 * <p>
 * Where an alarm table is implemented, all alarms, masked or otherwise, are recorded and may be
 * retrieved on demand.
 * <p>
 * Alarms may either reset automatically when the conditions that cause are no longer active,
 * or may need to be explicitly reset.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ZclAlarmsCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0009;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Alarms";

    // Attribute constants
    /**
     * The AlarmCount attribute is 16-bits in length and specifies the number of entries
     * currently in the alarm table. This attribute shall be specified in the range 0x00 to the
     * maximum defined in the profile using this cluster.
     * <p>
     * If alarm logging is not implemented this attribute shall always take the value 0x00.
     */
    public static final int ATTR_ALARMCOUNT = 0x0000;

    // Attribute initialisation
    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(1);

        attributeMap.put(ATTR_ALARMCOUNT, new ZclAttribute(ZclClusterType.ALARMS, ATTR_ALARMCOUNT, "Alarm Count", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Alarms cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclAlarmsCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>Alarm Count</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The AlarmCount attribute is 16-bits in length and specifies the number of entries
     * currently in the alarm table. This attribute shall be specified in the range 0x00 to the
     * maximum defined in the profile using this cluster.
     * <p>
     * If alarm logging is not implemented this attribute shall always take the value 0x00.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getAlarmCountAsync() {
        return read(attributes.get(ATTR_ALARMCOUNT));
    }

    /**
     * Synchronously get the <i>Alarm Count</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The AlarmCount attribute is 16-bits in length and specifies the number of entries
     * currently in the alarm table. This attribute shall be specified in the range 0x00 to the
     * maximum defined in the profile using this cluster.
     * <p>
     * If alarm logging is not implemented this attribute shall always take the value 0x00.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getAlarmCount(final long refreshPeriod) {
        if (attributes.get(ATTR_ALARMCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ALARMCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ALARMCOUNT));
    }

    /**
     * The Reset Alarm Command
     * <p>
     * This command resets a specific alarm. This is needed for some alarms that do not reset
     * automatically. If the alarm condition being reset was in fact still active then a new
     * notification will be generated and, where implemented, a new record added to the alarm
     * log.
     *
     * @param alarmCode {@link Integer} Alarm Code
     * @param clusterIdentifier {@link Integer} Cluster Identifier
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> resetAlarmCommand(Integer alarmCode, Integer clusterIdentifier) {
        ResetAlarmCommand command = new ResetAlarmCommand();

        // Set the fields
        command.setAlarmCode(alarmCode);
        command.setClusterIdentifier(clusterIdentifier);

        return send(command);
    }

    /**
     * The Reset All Alarms Command
     * <p>
     * This command resets all alarms. Any alarm conditions that were in fact still active will
     * cause a new notification to be generated and, where implemented, a new record added to
     * the alarm log.
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> resetAllAlarmsCommand() {
        return send(new ResetAllAlarmsCommand());
    }

    /**
     * The Get Alarm Command
     * <p>
     * This command causes the alarm with the earliest generated alarm entry in the alarm table
     * to be reported in a get alarm response command. This command enables the reading of
     * logged alarm conditions from the alarm table. Once an alarm condition has been reported
     * the corresponding entry in the table is removed.
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getAlarmCommand() {
        return send(new GetAlarmCommand());
    }

    /**
     * The Reset Alarm Log Command
     * <p>
     * This command causes the alarm table to be cleared.
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> resetAlarmLogCommand() {
        return send(new ResetAlarmLogCommand());
    }

    /**
     * The Alarm Command
     * <p>
     * The alarm command signals an alarm situation on the sending device. <br> An alarm
     * command is generated when a cluster which has alarm functionality detects an alarm
     * condition, e.g., an attribute has taken on a value that is outside a ‘safe’ range. The
     * details are given by individual cluster specifications.
     *
     * @param alarmCode {@link Integer} Alarm Code
     * @param clusterIdentifier {@link Integer} Cluster Identifier
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> alarmCommand(Integer alarmCode, Integer clusterIdentifier) {
        AlarmCommand command = new AlarmCommand();

        // Set the fields
        command.setAlarmCode(alarmCode);
        command.setClusterIdentifier(clusterIdentifier);

        return send(command);
    }

    /**
     * The Get Alarm Response
     * <p>
     * If there is at least one alarm record in the alarm table then the status field is set to
     * SUCCESS. The alarm code, cluster identifier and time stamp fields shall all be present
     * and shall take their values from the item in the alarm table that they are reporting.If
     * there are no more alarms logged in the alarm table then the status field is set to
     * NOT_FOUND and the alarm code, cluster identifier and time stamp fields shall be
     * omitted.
     *
     * @param status {@link Integer} Status
     * @param alarmCode {@link Integer} Alarm Code
     * @param clusterIdentifier {@link Integer} Cluster Identifier
     * @param timestamp {@link Integer} Timestamp
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getAlarmResponse(Integer status, Integer alarmCode, Integer clusterIdentifier, Integer timestamp) {
        GetAlarmResponse command = new GetAlarmResponse();

        // Set the fields
        command.setStatus(status);
        command.setAlarmCode(alarmCode);
        command.setClusterIdentifier(clusterIdentifier);
        command.setTimestamp(timestamp);

        return send(command);
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0x00: // RESET_ALARM_COMMAND
                return new ResetAlarmCommand();
            case 0x01: // RESET_ALL_ALARMS_COMMAND
                return new ResetAllAlarmsCommand();
            case 0x02: // GET_ALARM_COMMAND
                return new GetAlarmCommand();
            case 0x03: // RESET_ALARM_LOG_COMMAND
                return new ResetAlarmLogCommand();
            default:
                return null;
        }
    }

    @Override
    public ZclCommand getResponseFromId(int commandId) {
        switch (commandId) {
            case 0x00: // ALARM_COMMAND
                return new AlarmCommand();
            case 0x01: // GET_ALARM_RESPONSE
                return new GetAlarmResponse();
            default:
                return null;
        }
    }
}
