/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.Future;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.ClearWeeklySchedule;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.GetRelayStatusLog;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.GetRelayStatusLogResponse;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.GetWeeklySchedule;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.GetWeeklyScheduleResponse;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.SetWeeklySchedule;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.SetpointRaiseLowerCommand;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.ZclThermostatCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Thermostat</b> cluster implementation (<i>Cluster ID 0x0201</i>).
 * <p>
 * This cluster provides an interface to the functionality of a thermostat.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-06-25T09:06:14Z")
public class ZclThermostatCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0201;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Thermostat";

    // Attribute constants
    /**
     * LocalTemperature represents the temperature in degrees Celsius, as measured
     * locally.
     */
    public static final int ATTR_LOCALTEMPERATURE = 0x0000;
    /**
     * OutdoorTemperature represents the temperature in degrees Celsius, as measured
     * locally.
     */
    public static final int ATTR_OUTDOORTEMPERATURE = 0x0001;
    /**
     * Occupancy specifies whether the heated/cooled space is occupied or not
     */
    public static final int ATTR_OCCUPANCY = 0x0002;
    /**
     * The MinHeatSetpointLimit attribute specifies the absolute minimum level that the
     * heating setpoint may be set to. This is a limitation imposed by the manufacturer.
     */
    public static final int ATTR_ABSMINHEATSETPOINTLIMIT = 0x0003;
    /**
     * The MaxHeatSetpointLimit attribute specifies the absolute maximum level that the
     * heating setpoint may be set to. This is a limitation imposed by the manufacturer.
     */
    public static final int ATTR_ABSMAXHEATSETPOINTLIMIT = 0x0004;
    /**
     * The MinCoolSetpointLimit attribute specifies the absolute minimum level that the
     * cooling setpoint may be set to. This is a limitation imposed by the manufacturer.
     */
    public static final int ATTR_ABSMINCOOLSETPOINTLIMIT = 0x0005;
    /**
     * The MaxCoolSetpointLimit attribute specifies the absolute maximum level that the
     * cooling setpoint may be set to. This is a limitation imposed by the manufacturer.
     */
    public static final int ATTR_ABSMAXCOOLSETPOINTLIMIT = 0x0006;
    /**
     * The PICoolingDemandattribute is 8 bits in length and specifies the level of cooling
     * demanded by the PI (proportional integral) control loop in use by the thermostat (if
     * any), in percent. This value is 0 when the thermostat is in “off” or “heating” mode.
     */
    public static final int ATTR_PICOOLINGDEMAND = 0x0007;
    /**
     * The PIHeatingDemand attribute is 8 bits in length and specifies the level of heating
     * demanded by the PI (proportional integral) control loop in use by the thermostat (if
     * any), in percent. This value is 0 when the thermostat is in “off” or “cooling” mode.
     */
    public static final int ATTR_PIHEATINGDEMAND = 0x0008;
    public static final int ATTR_HVACSYSTEMTYPECONFIGURATION = 0x0009;
    public static final int ATTR_LOCALTEMPERATURECALIBRATION = 0x0010;
    public static final int ATTR_OCCUPIEDCOOLINGSETPOINT = 0x0011;
    public static final int ATTR_OCCUPIEDHEATINGSETPOINT = 0x0012;
    public static final int ATTR_UNOCCUPIEDCOOLINGSETPOINT = 0x0013;
    public static final int ATTR_UNOCCUPIEDHEATINGSETPOINT = 0x0014;
    public static final int ATTR_MINHEATSETPOINTLIMIT = 0x0015;
    public static final int ATTR_MAXHEATSETPOINTLIMIT = 0x0016;
    public static final int ATTR_MINCOOLSETPOINTLIMIT = 0x0017;
    public static final int ATTR_MAXCOOLSETPOINTLIMIT = 0x0018;
    public static final int ATTR_MINSETPOINTDEADBAND = 0x0019;
    public static final int ATTR_REMOTESENSING = 0x001A;
    public static final int ATTR_CONTROLSEQUENCEOFOPERATION = 0x001B;
    public static final int ATTR_SYSTEMMODE = 0x001C;
    public static final int ATTR_ALARMMASK = 0x001D;
    public static final int ATTR_THERMOSTATRUNNINGMODE = 0x001E;
    public static final int ATTR_STARTOFWEEK = 0x0020;
    public static final int ATTR_NUMBEROFWEEKLYTRANSITIONS = 0x0021;
    public static final int ATTR_NUMBEROFDAILYTRANSITIONS = 0x0022;
    public static final int ATTR_TEMPERATURESETPOINTHOLD = 0x0023;
    public static final int ATTR_TEMPERATURESETPOINTHOLDDURATION = 0x0024;
    public static final int ATTR_THERMOSTATPROGRAMMINGOPERATIONMODE = 0x0025;
    public static final int ATTR_THERMOSTATRUNNINGSTATE = 0x0029;
    public static final int ATTR_SETPOINTCHANGESOURCE = 0x0030;
    public static final int ATTR_SETPOINTCHANGEAMOUNT = 0x0031;
    public static final int ATTR_SETPOINTCHANGESOURCETIMESTAMP = 0x0032;
    public static final int ATTR_OCCUPIEDSETBACK = 0x0034;
    public static final int ATTR_OCCUPIEDSETBACKMIN = 0x0035;
    public static final int ATTR_OCCUPIEDSETBACKMAX = 0x0036;
    public static final int ATTR_UNOCCUPIEDSETBACK = 0x0037;
    public static final int ATTR_UNOCCUPIEDSETBACKMIN = 0x0038;
    public static final int ATTR_UNOCCUPIEDSETBACKMAX = 0x0039;
    public static final int ATTR_EMERGENCYHEATDELTA = 0x003A;
    public static final int ATTR_ACTYPE = 0x0040;
    public static final int ATTR_ACCAPACITY = 0x0041;
    public static final int ATTR_ACREFRIGERANTTYPE = 0x0042;
    public static final int ATTR_ACCOMPRESSORTYPE = 0x0043;
    public static final int ATTR_ACERRORCODE = 0x0044;
    public static final int ATTR_ACLOUVERPOSITION = 0x0045;
    public static final int ATTR_ACCOILTEMPERATURE = 0x0046;
    public static final int ATTR_ACCAPACITYFORMAT = 0x0047;

    @Override
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        Map<Integer, ZclAttribute> attributeMap = super.initializeClientAttributes();

        return attributeMap;
    }

    @Override
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        Map<Integer, ZclAttribute> attributeMap = super.initializeServerAttributes();

        attributeMap.put(ATTR_LOCALTEMPERATURE, new ZclAttribute(this, ATTR_LOCALTEMPERATURE, "Local Temperature", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_OUTDOORTEMPERATURE, new ZclAttribute(this, ATTR_OUTDOORTEMPERATURE, "Outdoor Temperature", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_OCCUPANCY, new ZclAttribute(this, ATTR_OCCUPANCY, "Occupancy", ZclDataType.BITMAP_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_ABSMINHEATSETPOINTLIMIT, new ZclAttribute(this, ATTR_ABSMINHEATSETPOINTLIMIT, "Abs Min Heat Setpoint Limit", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ABSMAXHEATSETPOINTLIMIT, new ZclAttribute(this, ATTR_ABSMAXHEATSETPOINTLIMIT, "Abs Max Heat Setpoint Limit", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ABSMINCOOLSETPOINTLIMIT, new ZclAttribute(this, ATTR_ABSMINCOOLSETPOINTLIMIT, "Abs Min Cool Setpoint Limit", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ABSMAXCOOLSETPOINTLIMIT, new ZclAttribute(this, ATTR_ABSMAXCOOLSETPOINTLIMIT, "Abs Max Cool Setpoint Limit", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PICOOLINGDEMAND, new ZclAttribute(this, ATTR_PICOOLINGDEMAND, "Pi Cooling Demand", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_PIHEATINGDEMAND, new ZclAttribute(this, ATTR_PIHEATINGDEMAND, "Pi Heating Demand", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_HVACSYSTEMTYPECONFIGURATION, new ZclAttribute(this, ATTR_HVACSYSTEMTYPECONFIGURATION, "Hvac System Type Configuration", ZclDataType.BITMAP_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_LOCALTEMPERATURECALIBRATION, new ZclAttribute(this, ATTR_LOCALTEMPERATURECALIBRATION, "Local Temperature Calibration", ZclDataType.SIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_OCCUPIEDCOOLINGSETPOINT, new ZclAttribute(this, ATTR_OCCUPIEDCOOLINGSETPOINT, "Occupied Cooling Setpoint", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, true, false));
        attributeMap.put(ATTR_OCCUPIEDHEATINGSETPOINT, new ZclAttribute(this, ATTR_OCCUPIEDHEATINGSETPOINT, "Occupied Heating Setpoint", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, true, false));
        attributeMap.put(ATTR_UNOCCUPIEDCOOLINGSETPOINT, new ZclAttribute(this, ATTR_UNOCCUPIEDCOOLINGSETPOINT, "Unoccupied Cooling Setpoint", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_UNOCCUPIEDHEATINGSETPOINT, new ZclAttribute(this, ATTR_UNOCCUPIEDHEATINGSETPOINT, "Unoccupied Heating Setpoint", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_MINHEATSETPOINTLIMIT, new ZclAttribute(this, ATTR_MINHEATSETPOINTLIMIT, "Min Heat Setpoint Limit", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_MAXHEATSETPOINTLIMIT, new ZclAttribute(this, ATTR_MAXHEATSETPOINTLIMIT, "Max Heat Setpoint Limit", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_MINCOOLSETPOINTLIMIT, new ZclAttribute(this, ATTR_MINCOOLSETPOINTLIMIT, "Min Cool Setpoint Limit", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_MAXCOOLSETPOINTLIMIT, new ZclAttribute(this, ATTR_MAXCOOLSETPOINTLIMIT, "Max Cool Setpoint Limit", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_MINSETPOINTDEADBAND, new ZclAttribute(this, ATTR_MINSETPOINTDEADBAND, "Min Setpoint Dead Band", ZclDataType.SIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_REMOTESENSING, new ZclAttribute(this, ATTR_REMOTESENSING, "Remote Sensing", ZclDataType.BITMAP_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_CONTROLSEQUENCEOFOPERATION, new ZclAttribute(this, ATTR_CONTROLSEQUENCEOFOPERATION, "Control Sequence Of Operation", ZclDataType.ENUMERATION_8_BIT, true, true, true, false));
        attributeMap.put(ATTR_SYSTEMMODE, new ZclAttribute(this, ATTR_SYSTEMMODE, "System Mode", ZclDataType.ENUMERATION_8_BIT, true, true, true, false));
        attributeMap.put(ATTR_ALARMMASK, new ZclAttribute(this, ATTR_ALARMMASK, "Alarm Mask", ZclDataType.BITMAP_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_THERMOSTATRUNNINGMODE, new ZclAttribute(this, ATTR_THERMOSTATRUNNINGMODE, "Thermostat Running Mode", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_STARTOFWEEK, new ZclAttribute(this, ATTR_STARTOFWEEK, "Start Of Week", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_NUMBEROFWEEKLYTRANSITIONS, new ZclAttribute(this, ATTR_NUMBEROFWEEKLYTRANSITIONS, "Number Of Weekly Transitions", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_NUMBEROFDAILYTRANSITIONS, new ZclAttribute(this, ATTR_NUMBEROFDAILYTRANSITIONS, "Number Of Daily Transitions", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TEMPERATURESETPOINTHOLD, new ZclAttribute(this, ATTR_TEMPERATURESETPOINTHOLD, "Temperature Setpoint Hold", ZclDataType.ENUMERATION_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_TEMPERATURESETPOINTHOLDDURATION, new ZclAttribute(this, ATTR_TEMPERATURESETPOINTHOLDDURATION, "Temperature Setpoint Hold Duration", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_THERMOSTATPROGRAMMINGOPERATIONMODE, new ZclAttribute(this, ATTR_THERMOSTATPROGRAMMINGOPERATIONMODE, "Thermostat Programming Operation Mode", ZclDataType.BITMAP_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_THERMOSTATRUNNINGSTATE, new ZclAttribute(this, ATTR_THERMOSTATRUNNINGSTATE, "Thermostat Running State", ZclDataType.BITMAP_16_BIT, false, true, false, false));
        attributeMap.put(ATTR_SETPOINTCHANGESOURCE, new ZclAttribute(this, ATTR_SETPOINTCHANGESOURCE, "Setpoint Change Source", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_SETPOINTCHANGEAMOUNT, new ZclAttribute(this, ATTR_SETPOINTCHANGEAMOUNT, "Setpoint Change Amount", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_SETPOINTCHANGESOURCETIMESTAMP, new ZclAttribute(this, ATTR_SETPOINTCHANGESOURCETIMESTAMP, "Setpoint Change Source Timestamp", ZclDataType.UTCTIME, false, true, false, false));
        attributeMap.put(ATTR_OCCUPIEDSETBACK, new ZclAttribute(this, ATTR_OCCUPIEDSETBACK, "Occupied Setback", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_OCCUPIEDSETBACKMIN, new ZclAttribute(this, ATTR_OCCUPIEDSETBACKMIN, "Occupied Setback Min", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_OCCUPIEDSETBACKMAX, new ZclAttribute(this, ATTR_OCCUPIEDSETBACKMAX, "Occupied Setback Max", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_UNOCCUPIEDSETBACK, new ZclAttribute(this, ATTR_UNOCCUPIEDSETBACK, "Unoccupied Setback", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_UNOCCUPIEDSETBACKMIN, new ZclAttribute(this, ATTR_UNOCCUPIEDSETBACKMIN, "Unoccupied Setback Min", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_UNOCCUPIEDSETBACKMAX, new ZclAttribute(this, ATTR_UNOCCUPIEDSETBACKMAX, "Unoccupied Setback Max", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_EMERGENCYHEATDELTA, new ZclAttribute(this, ATTR_EMERGENCYHEATDELTA, "Emergency Heat Delta", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_ACTYPE, new ZclAttribute(this, ATTR_ACTYPE, "AC Type", ZclDataType.ENUMERATION_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_ACCAPACITY, new ZclAttribute(this, ATTR_ACCAPACITY, "AC Capacity", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_ACREFRIGERANTTYPE, new ZclAttribute(this, ATTR_ACREFRIGERANTTYPE, "AC Refrigerant Type", ZclDataType.ENUMERATION_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_ACCOMPRESSORTYPE, new ZclAttribute(this, ATTR_ACCOMPRESSORTYPE, "AC Compressor Type", ZclDataType.ENUMERATION_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_ACERRORCODE, new ZclAttribute(this, ATTR_ACERRORCODE, "AC Error Code", ZclDataType.BITMAP_32_BIT, false, true, true, false));
        attributeMap.put(ATTR_ACLOUVERPOSITION, new ZclAttribute(this, ATTR_ACLOUVERPOSITION, "AC Louver Position", ZclDataType.ENUMERATION_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_ACCOILTEMPERATURE, new ZclAttribute(this, ATTR_ACCOILTEMPERATURE, "AC Coil Temperature", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ACCAPACITYFORMAT, new ZclAttribute(this, ATTR_ACCAPACITYFORMAT, "AC Capacity Format", ZclDataType.ENUMERATION_8_BIT, false, true, true, false));

        return attributeMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeServerCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();

        commandMap.put(0x0000, GetWeeklyScheduleResponse.class);
        commandMap.put(0x0001, GetRelayStatusLogResponse.class);

        return commandMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();

        commandMap.put(0x0000, SetpointRaiseLowerCommand.class);
        commandMap.put(0x0001, SetWeeklySchedule.class);
        commandMap.put(0x0002, GetWeeklySchedule.class);
        commandMap.put(0x0003, ClearWeeklySchedule.class);
        commandMap.put(0x0004, GetRelayStatusLog.class);

        return commandMap;
    }

    /**
     * Default constructor to create a Thermostat cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclThermostatCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Sends a {@link ZclThermostatCommand} and returns the {@link Future} to the result which will complete when the remote
     * device response is received, or the request times out.
     *
     * @param command the {@link ZclThermostatCommand} to send
     * @return the command result future
     */
    public Future<CommandResult> sendCommand(ZclThermostatCommand command) {
        return super.sendCommand(command);
    }

    /**
     * Sends a response to the command. This method sets all the common elements of the response based on the command -
     * eg transactionId, direction, address...
     *
     * @param command the {@link ZclThermostatCommand} to which the response is being sent
     * @param response the {@link ZclThermostatCommand} to send
     */
    public Future<CommandResult> sendResponse(ZclThermostatCommand command, ZclThermostatCommand response) {
        return super.sendResponse(command, response);
    }

    /**
     * Get the <i>Local Temperature</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * LocalTemperature represents the temperature in degrees Celsius, as measured
     * locally.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getLocalTemperatureAsync() {
        return read(serverAttributes.get(ATTR_LOCALTEMPERATURE));
    }

    /**
     * Synchronously get the <i>Local Temperature</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * LocalTemperature represents the temperature in degrees Celsius, as measured
     * locally.
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
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getLocalTemperature(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_LOCALTEMPERATURE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_LOCALTEMPERATURE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_LOCALTEMPERATURE));
    }

    /**
     * Set reporting for the <i>Local Temperature</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * LocalTemperature represents the temperature in degrees Celsius, as measured
     * locally.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setLocalTemperatureReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_LOCALTEMPERATURE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Outdoor Temperature</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * OutdoorTemperature represents the temperature in degrees Celsius, as measured
     * locally.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getOutdoorTemperatureAsync() {
        return read(serverAttributes.get(ATTR_OUTDOORTEMPERATURE));
    }

    /**
     * Synchronously get the <i>Outdoor Temperature</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * OutdoorTemperature represents the temperature in degrees Celsius, as measured
     * locally.
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getOutdoorTemperature(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_OUTDOORTEMPERATURE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_OUTDOORTEMPERATURE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_OUTDOORTEMPERATURE));
    }

    /**
     * Get the <i>Occupancy</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * Occupancy specifies whether the heated/cooled space is occupied or not
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getOccupancyAsync() {
        return read(serverAttributes.get(ATTR_OCCUPANCY));
    }

    /**
     * Synchronously get the <i>Occupancy</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * Occupancy specifies whether the heated/cooled space is occupied or not
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getOccupancy(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_OCCUPANCY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_OCCUPANCY).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_OCCUPANCY));
    }

    /**
     * Get the <i>Abs Min Heat Setpoint Limit</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The MinHeatSetpointLimit attribute specifies the absolute minimum level that the
     * heating setpoint may be set to. This is a limitation imposed by the manufacturer.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAbsMinHeatSetpointLimitAsync() {
        return read(serverAttributes.get(ATTR_ABSMINHEATSETPOINTLIMIT));
    }

    /**
     * Synchronously get the <i>Abs Min Heat Setpoint Limit</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The MinHeatSetpointLimit attribute specifies the absolute minimum level that the
     * heating setpoint may be set to. This is a limitation imposed by the manufacturer.
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getAbsMinHeatSetpointLimit(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ABSMINHEATSETPOINTLIMIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ABSMINHEATSETPOINTLIMIT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ABSMINHEATSETPOINTLIMIT));
    }

    /**
     * Get the <i>Abs Max Heat Setpoint Limit</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The MaxHeatSetpointLimit attribute specifies the absolute maximum level that the
     * heating setpoint may be set to. This is a limitation imposed by the manufacturer.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAbsMaxHeatSetpointLimitAsync() {
        return read(serverAttributes.get(ATTR_ABSMAXHEATSETPOINTLIMIT));
    }

    /**
     * Synchronously get the <i>Abs Max Heat Setpoint Limit</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The MaxHeatSetpointLimit attribute specifies the absolute maximum level that the
     * heating setpoint may be set to. This is a limitation imposed by the manufacturer.
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getAbsMaxHeatSetpointLimit(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ABSMAXHEATSETPOINTLIMIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ABSMAXHEATSETPOINTLIMIT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ABSMAXHEATSETPOINTLIMIT));
    }

    /**
     * Get the <i>Abs Min Cool Setpoint Limit</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The MinCoolSetpointLimit attribute specifies the absolute minimum level that the
     * cooling setpoint may be set to. This is a limitation imposed by the manufacturer.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAbsMinCoolSetpointLimitAsync() {
        return read(serverAttributes.get(ATTR_ABSMINCOOLSETPOINTLIMIT));
    }

    /**
     * Synchronously get the <i>Abs Min Cool Setpoint Limit</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The MinCoolSetpointLimit attribute specifies the absolute minimum level that the
     * cooling setpoint may be set to. This is a limitation imposed by the manufacturer.
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getAbsMinCoolSetpointLimit(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ABSMINCOOLSETPOINTLIMIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ABSMINCOOLSETPOINTLIMIT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ABSMINCOOLSETPOINTLIMIT));
    }

    /**
     * Get the <i>Abs Max Cool Setpoint Limit</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * The MaxCoolSetpointLimit attribute specifies the absolute maximum level that the
     * cooling setpoint may be set to. This is a limitation imposed by the manufacturer.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAbsMaxCoolSetpointLimitAsync() {
        return read(serverAttributes.get(ATTR_ABSMAXCOOLSETPOINTLIMIT));
    }

    /**
     * Synchronously get the <i>Abs Max Cool Setpoint Limit</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * The MaxCoolSetpointLimit attribute specifies the absolute maximum level that the
     * cooling setpoint may be set to. This is a limitation imposed by the manufacturer.
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getAbsMaxCoolSetpointLimit(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ABSMAXCOOLSETPOINTLIMIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ABSMAXCOOLSETPOINTLIMIT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ABSMAXCOOLSETPOINTLIMIT));
    }

    /**
     * Get the <i>Pi Cooling Demand</i> attribute [attribute ID <b>0x0007</b>].
     * <p>
     * The PICoolingDemandattribute is 8 bits in length and specifies the level of cooling
     * demanded by the PI (proportional integral) control loop in use by the thermostat (if
     * any), in percent. This value is 0 when the thermostat is in “off” or “heating” mode.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPiCoolingDemandAsync() {
        return read(serverAttributes.get(ATTR_PICOOLINGDEMAND));
    }

    /**
     * Synchronously get the <i>Pi Cooling Demand</i> attribute [attribute ID <b>0x0007</b>].
     * <p>
     * The PICoolingDemandattribute is 8 bits in length and specifies the level of cooling
     * demanded by the PI (proportional integral) control loop in use by the thermostat (if
     * any), in percent. This value is 0 when the thermostat is in “off” or “heating” mode.
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getPiCoolingDemand(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_PICOOLINGDEMAND).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_PICOOLINGDEMAND).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_PICOOLINGDEMAND));
    }

    /**
     * Get the <i>Pi Heating Demand</i> attribute [attribute ID <b>0x0008</b>].
     * <p>
     * The PIHeatingDemand attribute is 8 bits in length and specifies the level of heating
     * demanded by the PI (proportional integral) control loop in use by the thermostat (if
     * any), in percent. This value is 0 when the thermostat is in “off” or “cooling” mode.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPiHeatingDemandAsync() {
        return read(serverAttributes.get(ATTR_PIHEATINGDEMAND));
    }

    /**
     * Synchronously get the <i>Pi Heating Demand</i> attribute [attribute ID <b>0x0008</b>].
     * <p>
     * The PIHeatingDemand attribute is 8 bits in length and specifies the level of heating
     * demanded by the PI (proportional integral) control loop in use by the thermostat (if
     * any), in percent. This value is 0 when the thermostat is in “off” or “cooling” mode.
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getPiHeatingDemand(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_PIHEATINGDEMAND).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_PIHEATINGDEMAND).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_PIHEATINGDEMAND));
    }

    /**
     * Set the <i>Hvac System Type Configuration</i> attribute [attribute ID <b>0x0009</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param hvacSystemTypeConfiguration the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setHvacSystemTypeConfiguration(final Integer value) {
        return write(serverAttributes.get(ATTR_HVACSYSTEMTYPECONFIGURATION), value);
    }

    /**
     * Get the <i>Hvac System Type Configuration</i> attribute [attribute ID <b>0x0009</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getHvacSystemTypeConfigurationAsync() {
        return read(serverAttributes.get(ATTR_HVACSYSTEMTYPECONFIGURATION));
    }

    /**
     * Synchronously get the <i>Hvac System Type Configuration</i> attribute [attribute ID <b>0x0009</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getHvacSystemTypeConfiguration(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_HVACSYSTEMTYPECONFIGURATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_HVACSYSTEMTYPECONFIGURATION).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_HVACSYSTEMTYPECONFIGURATION));
    }

    /**
     * Set the <i>Local Temperature Calibration</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param localTemperatureCalibration the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setLocalTemperatureCalibration(final Integer value) {
        return write(serverAttributes.get(ATTR_LOCALTEMPERATURECALIBRATION), value);
    }

    /**
     * Get the <i>Local Temperature Calibration</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getLocalTemperatureCalibrationAsync() {
        return read(serverAttributes.get(ATTR_LOCALTEMPERATURECALIBRATION));
    }

    /**
     * Synchronously get the <i>Local Temperature Calibration</i> attribute [attribute ID <b>0x0010</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getLocalTemperatureCalibration(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_LOCALTEMPERATURECALIBRATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_LOCALTEMPERATURECALIBRATION).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_LOCALTEMPERATURECALIBRATION));
    }

    /**
     * Set the <i>Occupied Cooling Setpoint</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param occupiedCoolingSetpoint the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setOccupiedCoolingSetpoint(final Integer value) {
        return write(serverAttributes.get(ATTR_OCCUPIEDCOOLINGSETPOINT), value);
    }

    /**
     * Get the <i>Occupied Cooling Setpoint</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getOccupiedCoolingSetpointAsync() {
        return read(serverAttributes.get(ATTR_OCCUPIEDCOOLINGSETPOINT));
    }

    /**
     * Synchronously get the <i>Occupied Cooling Setpoint</i> attribute [attribute ID <b>0x0011</b>].
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
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getOccupiedCoolingSetpoint(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_OCCUPIEDCOOLINGSETPOINT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_OCCUPIEDCOOLINGSETPOINT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_OCCUPIEDCOOLINGSETPOINT));
    }

    /**
     * Set reporting for the <i>Occupied Cooling Setpoint</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setOccupiedCoolingSetpointReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_OCCUPIEDCOOLINGSETPOINT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Set the <i>Occupied Heating Setpoint</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param occupiedHeatingSetpoint the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setOccupiedHeatingSetpoint(final Integer value) {
        return write(serverAttributes.get(ATTR_OCCUPIEDHEATINGSETPOINT), value);
    }

    /**
     * Get the <i>Occupied Heating Setpoint</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getOccupiedHeatingSetpointAsync() {
        return read(serverAttributes.get(ATTR_OCCUPIEDHEATINGSETPOINT));
    }

    /**
     * Synchronously get the <i>Occupied Heating Setpoint</i> attribute [attribute ID <b>0x0012</b>].
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
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getOccupiedHeatingSetpoint(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_OCCUPIEDHEATINGSETPOINT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_OCCUPIEDHEATINGSETPOINT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_OCCUPIEDHEATINGSETPOINT));
    }

    /**
     * Set reporting for the <i>Occupied Heating Setpoint</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setOccupiedHeatingSetpointReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_OCCUPIEDHEATINGSETPOINT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Set the <i>Unoccupied Cooling Setpoint</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param unoccupiedCoolingSetpoint the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setUnoccupiedCoolingSetpoint(final Integer value) {
        return write(serverAttributes.get(ATTR_UNOCCUPIEDCOOLINGSETPOINT), value);
    }

    /**
     * Get the <i>Unoccupied Cooling Setpoint</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getUnoccupiedCoolingSetpointAsync() {
        return read(serverAttributes.get(ATTR_UNOCCUPIEDCOOLINGSETPOINT));
    }

    /**
     * Synchronously get the <i>Unoccupied Cooling Setpoint</i> attribute [attribute ID <b>0x0013</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getUnoccupiedCoolingSetpoint(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_UNOCCUPIEDCOOLINGSETPOINT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_UNOCCUPIEDCOOLINGSETPOINT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_UNOCCUPIEDCOOLINGSETPOINT));
    }

    /**
     * Set the <i>Unoccupied Heating Setpoint</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param unoccupiedHeatingSetpoint the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setUnoccupiedHeatingSetpoint(final Integer value) {
        return write(serverAttributes.get(ATTR_UNOCCUPIEDHEATINGSETPOINT), value);
    }

    /**
     * Get the <i>Unoccupied Heating Setpoint</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getUnoccupiedHeatingSetpointAsync() {
        return read(serverAttributes.get(ATTR_UNOCCUPIEDHEATINGSETPOINT));
    }

    /**
     * Synchronously get the <i>Unoccupied Heating Setpoint</i> attribute [attribute ID <b>0x0014</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getUnoccupiedHeatingSetpoint(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_UNOCCUPIEDHEATINGSETPOINT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_UNOCCUPIEDHEATINGSETPOINT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_UNOCCUPIEDHEATINGSETPOINT));
    }

    /**
     * Set the <i>Min Heat Setpoint Limit</i> attribute [attribute ID <b>0x0015</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param minHeatSetpointLimit the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setMinHeatSetpointLimit(final Integer value) {
        return write(serverAttributes.get(ATTR_MINHEATSETPOINTLIMIT), value);
    }

    /**
     * Get the <i>Min Heat Setpoint Limit</i> attribute [attribute ID <b>0x0015</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMinHeatSetpointLimitAsync() {
        return read(serverAttributes.get(ATTR_MINHEATSETPOINTLIMIT));
    }

    /**
     * Synchronously get the <i>Min Heat Setpoint Limit</i> attribute [attribute ID <b>0x0015</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getMinHeatSetpointLimit(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MINHEATSETPOINTLIMIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MINHEATSETPOINTLIMIT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MINHEATSETPOINTLIMIT));
    }

    /**
     * Set the <i>Max Heat Setpoint Limit</i> attribute [attribute ID <b>0x0016</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param maxHeatSetpointLimit the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setMaxHeatSetpointLimit(final Integer value) {
        return write(serverAttributes.get(ATTR_MAXHEATSETPOINTLIMIT), value);
    }

    /**
     * Get the <i>Max Heat Setpoint Limit</i> attribute [attribute ID <b>0x0016</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMaxHeatSetpointLimitAsync() {
        return read(serverAttributes.get(ATTR_MAXHEATSETPOINTLIMIT));
    }

    /**
     * Synchronously get the <i>Max Heat Setpoint Limit</i> attribute [attribute ID <b>0x0016</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getMaxHeatSetpointLimit(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MAXHEATSETPOINTLIMIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MAXHEATSETPOINTLIMIT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MAXHEATSETPOINTLIMIT));
    }

    /**
     * Set the <i>Min Cool Setpoint Limit</i> attribute [attribute ID <b>0x0017</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param minCoolSetpointLimit the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setMinCoolSetpointLimit(final Integer value) {
        return write(serverAttributes.get(ATTR_MINCOOLSETPOINTLIMIT), value);
    }

    /**
     * Get the <i>Min Cool Setpoint Limit</i> attribute [attribute ID <b>0x0017</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMinCoolSetpointLimitAsync() {
        return read(serverAttributes.get(ATTR_MINCOOLSETPOINTLIMIT));
    }

    /**
     * Synchronously get the <i>Min Cool Setpoint Limit</i> attribute [attribute ID <b>0x0017</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getMinCoolSetpointLimit(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MINCOOLSETPOINTLIMIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MINCOOLSETPOINTLIMIT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MINCOOLSETPOINTLIMIT));
    }

    /**
     * Set the <i>Max Cool Setpoint Limit</i> attribute [attribute ID <b>0x0018</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param maxCoolSetpointLimit the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setMaxCoolSetpointLimit(final Integer value) {
        return write(serverAttributes.get(ATTR_MAXCOOLSETPOINTLIMIT), value);
    }

    /**
     * Get the <i>Max Cool Setpoint Limit</i> attribute [attribute ID <b>0x0018</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMaxCoolSetpointLimitAsync() {
        return read(serverAttributes.get(ATTR_MAXCOOLSETPOINTLIMIT));
    }

    /**
     * Synchronously get the <i>Max Cool Setpoint Limit</i> attribute [attribute ID <b>0x0018</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getMaxCoolSetpointLimit(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MAXCOOLSETPOINTLIMIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MAXCOOLSETPOINTLIMIT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MAXCOOLSETPOINTLIMIT));
    }

    /**
     * Set the <i>Min Setpoint Dead Band</i> attribute [attribute ID <b>0x0019</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param minSetpointDeadBand the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setMinSetpointDeadBand(final Integer value) {
        return write(serverAttributes.get(ATTR_MINSETPOINTDEADBAND), value);
    }

    /**
     * Get the <i>Min Setpoint Dead Band</i> attribute [attribute ID <b>0x0019</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMinSetpointDeadBandAsync() {
        return read(serverAttributes.get(ATTR_MINSETPOINTDEADBAND));
    }

    /**
     * Synchronously get the <i>Min Setpoint Dead Band</i> attribute [attribute ID <b>0x0019</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getMinSetpointDeadBand(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MINSETPOINTDEADBAND).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MINSETPOINTDEADBAND).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MINSETPOINTDEADBAND));
    }

    /**
     * Set the <i>Remote Sensing</i> attribute [attribute ID <b>0x001A</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param remoteSensing the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setRemoteSensing(final Integer value) {
        return write(serverAttributes.get(ATTR_REMOTESENSING), value);
    }

    /**
     * Get the <i>Remote Sensing</i> attribute [attribute ID <b>0x001A</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRemoteSensingAsync() {
        return read(serverAttributes.get(ATTR_REMOTESENSING));
    }

    /**
     * Synchronously get the <i>Remote Sensing</i> attribute [attribute ID <b>0x001A</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getRemoteSensing(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_REMOTESENSING).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_REMOTESENSING).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_REMOTESENSING));
    }

    /**
     * Set the <i>Control Sequence Of Operation</i> attribute [attribute ID <b>0x001B</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param controlSequenceOfOperation the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setControlSequenceOfOperation(final Integer value) {
        return write(serverAttributes.get(ATTR_CONTROLSEQUENCEOFOPERATION), value);
    }

    /**
     * Get the <i>Control Sequence Of Operation</i> attribute [attribute ID <b>0x001B</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getControlSequenceOfOperationAsync() {
        return read(serverAttributes.get(ATTR_CONTROLSEQUENCEOFOPERATION));
    }

    /**
     * Synchronously get the <i>Control Sequence Of Operation</i> attribute [attribute ID <b>0x001B</b>].
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
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getControlSequenceOfOperation(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_CONTROLSEQUENCEOFOPERATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_CONTROLSEQUENCEOFOPERATION).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_CONTROLSEQUENCEOFOPERATION));
    }

    /**
     * Set reporting for the <i>Control Sequence Of Operation</i> attribute [attribute ID <b>0x001B</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setControlSequenceOfOperationReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_CONTROLSEQUENCEOFOPERATION), minInterval, maxInterval);
    }

    /**
     * Set the <i>System Mode</i> attribute [attribute ID <b>0x001C</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param systemMode the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setSystemMode(final Integer value) {
        return write(serverAttributes.get(ATTR_SYSTEMMODE), value);
    }

    /**
     * Get the <i>System Mode</i> attribute [attribute ID <b>0x001C</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getSystemModeAsync() {
        return read(serverAttributes.get(ATTR_SYSTEMMODE));
    }

    /**
     * Synchronously get the <i>System Mode</i> attribute [attribute ID <b>0x001C</b>].
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
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getSystemMode(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_SYSTEMMODE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_SYSTEMMODE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_SYSTEMMODE));
    }

    /**
     * Set reporting for the <i>System Mode</i> attribute [attribute ID <b>0x001C</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setSystemModeReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_SYSTEMMODE), minInterval, maxInterval);
    }

    /**
     * Get the <i>Alarm Mask</i> attribute [attribute ID <b>0x001D</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAlarmMaskAsync() {
        return read(serverAttributes.get(ATTR_ALARMMASK));
    }

    /**
     * Synchronously get the <i>Alarm Mask</i> attribute [attribute ID <b>0x001D</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getAlarmMask(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ALARMMASK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ALARMMASK).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ALARMMASK));
    }

    /**
     * Get the <i>Thermostat Running Mode</i> attribute [attribute ID <b>0x001E</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getThermostatRunningModeAsync() {
        return read(serverAttributes.get(ATTR_THERMOSTATRUNNINGMODE));
    }

    /**
     * Synchronously get the <i>Thermostat Running Mode</i> attribute [attribute ID <b>0x001E</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getThermostatRunningMode(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_THERMOSTATRUNNINGMODE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_THERMOSTATRUNNINGMODE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_THERMOSTATRUNNINGMODE));
    }

    /**
     * Get the <i>Start Of Week</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getStartOfWeekAsync() {
        return read(serverAttributes.get(ATTR_STARTOFWEEK));
    }

    /**
     * Synchronously get the <i>Start Of Week</i> attribute [attribute ID <b>0x0020</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getStartOfWeek(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_STARTOFWEEK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_STARTOFWEEK).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_STARTOFWEEK));
    }

    /**
     * Get the <i>Number Of Weekly Transitions</i> attribute [attribute ID <b>0x0021</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getNumberOfWeeklyTransitionsAsync() {
        return read(serverAttributes.get(ATTR_NUMBEROFWEEKLYTRANSITIONS));
    }

    /**
     * Synchronously get the <i>Number Of Weekly Transitions</i> attribute [attribute ID <b>0x0021</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getNumberOfWeeklyTransitions(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_NUMBEROFWEEKLYTRANSITIONS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_NUMBEROFWEEKLYTRANSITIONS).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_NUMBEROFWEEKLYTRANSITIONS));
    }

    /**
     * Get the <i>Number Of Daily Transitions</i> attribute [attribute ID <b>0x0022</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getNumberOfDailyTransitionsAsync() {
        return read(serverAttributes.get(ATTR_NUMBEROFDAILYTRANSITIONS));
    }

    /**
     * Synchronously get the <i>Number Of Daily Transitions</i> attribute [attribute ID <b>0x0022</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getNumberOfDailyTransitions(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_NUMBEROFDAILYTRANSITIONS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_NUMBEROFDAILYTRANSITIONS).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_NUMBEROFDAILYTRANSITIONS));
    }

    /**
     * Set the <i>Temperature Setpoint Hold</i> attribute [attribute ID <b>0x0023</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param temperatureSetpointHold the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setTemperatureSetpointHold(final Integer value) {
        return write(serverAttributes.get(ATTR_TEMPERATURESETPOINTHOLD), value);
    }

    /**
     * Get the <i>Temperature Setpoint Hold</i> attribute [attribute ID <b>0x0023</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getTemperatureSetpointHoldAsync() {
        return read(serverAttributes.get(ATTR_TEMPERATURESETPOINTHOLD));
    }

    /**
     * Synchronously get the <i>Temperature Setpoint Hold</i> attribute [attribute ID <b>0x0023</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getTemperatureSetpointHold(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_TEMPERATURESETPOINTHOLD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_TEMPERATURESETPOINTHOLD).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_TEMPERATURESETPOINTHOLD));
    }

    /**
     * Set the <i>Temperature Setpoint Hold Duration</i> attribute [attribute ID <b>0x0024</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param temperatureSetpointHoldDuration the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setTemperatureSetpointHoldDuration(final Integer value) {
        return write(serverAttributes.get(ATTR_TEMPERATURESETPOINTHOLDDURATION), value);
    }

    /**
     * Get the <i>Temperature Setpoint Hold Duration</i> attribute [attribute ID <b>0x0024</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getTemperatureSetpointHoldDurationAsync() {
        return read(serverAttributes.get(ATTR_TEMPERATURESETPOINTHOLDDURATION));
    }

    /**
     * Synchronously get the <i>Temperature Setpoint Hold Duration</i> attribute [attribute ID <b>0x0024</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getTemperatureSetpointHoldDuration(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_TEMPERATURESETPOINTHOLDDURATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_TEMPERATURESETPOINTHOLDDURATION).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_TEMPERATURESETPOINTHOLDDURATION));
    }

    /**
     * Set the <i>Thermostat Programming Operation Mode</i> attribute [attribute ID <b>0x0025</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param thermostatProgrammingOperationMode the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setThermostatProgrammingOperationMode(final Integer value) {
        return write(serverAttributes.get(ATTR_THERMOSTATPROGRAMMINGOPERATIONMODE), value);
    }

    /**
     * Get the <i>Thermostat Programming Operation Mode</i> attribute [attribute ID <b>0x0025</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getThermostatProgrammingOperationModeAsync() {
        return read(serverAttributes.get(ATTR_THERMOSTATPROGRAMMINGOPERATIONMODE));
    }

    /**
     * Synchronously get the <i>Thermostat Programming Operation Mode</i> attribute [attribute ID <b>0x0025</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getThermostatProgrammingOperationMode(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_THERMOSTATPROGRAMMINGOPERATIONMODE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_THERMOSTATPROGRAMMINGOPERATIONMODE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_THERMOSTATPROGRAMMINGOPERATIONMODE));
    }

    /**
     * Get the <i>Thermostat Running State</i> attribute [attribute ID <b>0x0029</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getThermostatRunningStateAsync() {
        return read(serverAttributes.get(ATTR_THERMOSTATRUNNINGSTATE));
    }

    /**
     * Synchronously get the <i>Thermostat Running State</i> attribute [attribute ID <b>0x0029</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getThermostatRunningState(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_THERMOSTATRUNNINGSTATE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_THERMOSTATRUNNINGSTATE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_THERMOSTATRUNNINGSTATE));
    }

    /**
     * Get the <i>Setpoint Change Source</i> attribute [attribute ID <b>0x0030</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getSetpointChangeSourceAsync() {
        return read(serverAttributes.get(ATTR_SETPOINTCHANGESOURCE));
    }

    /**
     * Synchronously get the <i>Setpoint Change Source</i> attribute [attribute ID <b>0x0030</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getSetpointChangeSource(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_SETPOINTCHANGESOURCE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_SETPOINTCHANGESOURCE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_SETPOINTCHANGESOURCE));
    }

    /**
     * Get the <i>Setpoint Change Amount</i> attribute [attribute ID <b>0x0031</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getSetpointChangeAmountAsync() {
        return read(serverAttributes.get(ATTR_SETPOINTCHANGEAMOUNT));
    }

    /**
     * Synchronously get the <i>Setpoint Change Amount</i> attribute [attribute ID <b>0x0031</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getSetpointChangeAmount(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_SETPOINTCHANGEAMOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_SETPOINTCHANGEAMOUNT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_SETPOINTCHANGEAMOUNT));
    }

    /**
     * Get the <i>Setpoint Change Source Timestamp</i> attribute [attribute ID <b>0x0032</b>].
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getSetpointChangeSourceTimestampAsync() {
        return read(serverAttributes.get(ATTR_SETPOINTCHANGESOURCETIMESTAMP));
    }

    /**
     * Synchronously get the <i>Setpoint Change Source Timestamp</i> attribute [attribute ID <b>0x0032</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Calendar getSetpointChangeSourceTimestamp(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_SETPOINTCHANGESOURCETIMESTAMP).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) serverAttributes.get(ATTR_SETPOINTCHANGESOURCETIMESTAMP).getLastValue();
        }

        return (Calendar) readSync(serverAttributes.get(ATTR_SETPOINTCHANGESOURCETIMESTAMP));
    }

    /**
     * Set the <i>Occupied Setback</i> attribute [attribute ID <b>0x0034</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param occupiedSetback the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setOccupiedSetback(final Integer value) {
        return write(serverAttributes.get(ATTR_OCCUPIEDSETBACK), value);
    }

    /**
     * Get the <i>Occupied Setback</i> attribute [attribute ID <b>0x0034</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getOccupiedSetbackAsync() {
        return read(serverAttributes.get(ATTR_OCCUPIEDSETBACK));
    }

    /**
     * Synchronously get the <i>Occupied Setback</i> attribute [attribute ID <b>0x0034</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getOccupiedSetback(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_OCCUPIEDSETBACK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_OCCUPIEDSETBACK).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_OCCUPIEDSETBACK));
    }

    /**
     * Get the <i>Occupied Setback Min</i> attribute [attribute ID <b>0x0035</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getOccupiedSetbackMinAsync() {
        return read(serverAttributes.get(ATTR_OCCUPIEDSETBACKMIN));
    }

    /**
     * Synchronously get the <i>Occupied Setback Min</i> attribute [attribute ID <b>0x0035</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getOccupiedSetbackMin(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_OCCUPIEDSETBACKMIN).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_OCCUPIEDSETBACKMIN).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_OCCUPIEDSETBACKMIN));
    }

    /**
     * Get the <i>Occupied Setback Max</i> attribute [attribute ID <b>0x0036</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getOccupiedSetbackMaxAsync() {
        return read(serverAttributes.get(ATTR_OCCUPIEDSETBACKMAX));
    }

    /**
     * Synchronously get the <i>Occupied Setback Max</i> attribute [attribute ID <b>0x0036</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getOccupiedSetbackMax(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_OCCUPIEDSETBACKMAX).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_OCCUPIEDSETBACKMAX).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_OCCUPIEDSETBACKMAX));
    }

    /**
     * Set the <i>Unoccupied Setback</i> attribute [attribute ID <b>0x0037</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param unoccupiedSetback the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setUnoccupiedSetback(final Integer value) {
        return write(serverAttributes.get(ATTR_UNOCCUPIEDSETBACK), value);
    }

    /**
     * Get the <i>Unoccupied Setback</i> attribute [attribute ID <b>0x0037</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getUnoccupiedSetbackAsync() {
        return read(serverAttributes.get(ATTR_UNOCCUPIEDSETBACK));
    }

    /**
     * Synchronously get the <i>Unoccupied Setback</i> attribute [attribute ID <b>0x0037</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getUnoccupiedSetback(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_UNOCCUPIEDSETBACK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_UNOCCUPIEDSETBACK).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_UNOCCUPIEDSETBACK));
    }

    /**
     * Get the <i>Unoccupied Setback Min</i> attribute [attribute ID <b>0x0038</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getUnoccupiedSetbackMinAsync() {
        return read(serverAttributes.get(ATTR_UNOCCUPIEDSETBACKMIN));
    }

    /**
     * Synchronously get the <i>Unoccupied Setback Min</i> attribute [attribute ID <b>0x0038</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getUnoccupiedSetbackMin(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_UNOCCUPIEDSETBACKMIN).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_UNOCCUPIEDSETBACKMIN).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_UNOCCUPIEDSETBACKMIN));
    }

    /**
     * Get the <i>Unoccupied Setback Max</i> attribute [attribute ID <b>0x0039</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getUnoccupiedSetbackMaxAsync() {
        return read(serverAttributes.get(ATTR_UNOCCUPIEDSETBACKMAX));
    }

    /**
     * Synchronously get the <i>Unoccupied Setback Max</i> attribute [attribute ID <b>0x0039</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getUnoccupiedSetbackMax(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_UNOCCUPIEDSETBACKMAX).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_UNOCCUPIEDSETBACKMAX).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_UNOCCUPIEDSETBACKMAX));
    }

    /**
     * Set the <i>Emergency Heat Delta</i> attribute [attribute ID <b>0x003A</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param emergencyHeatDelta the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setEmergencyHeatDelta(final Integer value) {
        return write(serverAttributes.get(ATTR_EMERGENCYHEATDELTA), value);
    }

    /**
     * Get the <i>Emergency Heat Delta</i> attribute [attribute ID <b>0x003A</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getEmergencyHeatDeltaAsync() {
        return read(serverAttributes.get(ATTR_EMERGENCYHEATDELTA));
    }

    /**
     * Synchronously get the <i>Emergency Heat Delta</i> attribute [attribute ID <b>0x003A</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getEmergencyHeatDelta(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_EMERGENCYHEATDELTA).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_EMERGENCYHEATDELTA).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_EMERGENCYHEATDELTA));
    }

    /**
     * Set the <i>AC Type</i> attribute [attribute ID <b>0x0040</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param acType the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setAcType(final Integer value) {
        return write(serverAttributes.get(ATTR_ACTYPE), value);
    }

    /**
     * Get the <i>AC Type</i> attribute [attribute ID <b>0x0040</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcTypeAsync() {
        return read(serverAttributes.get(ATTR_ACTYPE));
    }

    /**
     * Synchronously get the <i>AC Type</i> attribute [attribute ID <b>0x0040</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getAcType(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACTYPE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACTYPE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACTYPE));
    }

    /**
     * Set the <i>AC Capacity</i> attribute [attribute ID <b>0x0041</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param acCapacity the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setAcCapacity(final Integer value) {
        return write(serverAttributes.get(ATTR_ACCAPACITY), value);
    }

    /**
     * Get the <i>AC Capacity</i> attribute [attribute ID <b>0x0041</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcCapacityAsync() {
        return read(serverAttributes.get(ATTR_ACCAPACITY));
    }

    /**
     * Synchronously get the <i>AC Capacity</i> attribute [attribute ID <b>0x0041</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getAcCapacity(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACCAPACITY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACCAPACITY).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACCAPACITY));
    }

    /**
     * Set the <i>AC Refrigerant Type</i> attribute [attribute ID <b>0x0042</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param acRefrigerantType the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setAcRefrigerantType(final Integer value) {
        return write(serverAttributes.get(ATTR_ACREFRIGERANTTYPE), value);
    }

    /**
     * Get the <i>AC Refrigerant Type</i> attribute [attribute ID <b>0x0042</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcRefrigerantTypeAsync() {
        return read(serverAttributes.get(ATTR_ACREFRIGERANTTYPE));
    }

    /**
     * Synchronously get the <i>AC Refrigerant Type</i> attribute [attribute ID <b>0x0042</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getAcRefrigerantType(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACREFRIGERANTTYPE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACREFRIGERANTTYPE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACREFRIGERANTTYPE));
    }

    /**
     * Set the <i>AC Compressor Type</i> attribute [attribute ID <b>0x0043</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param acCompressorType the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setAcCompressorType(final Integer value) {
        return write(serverAttributes.get(ATTR_ACCOMPRESSORTYPE), value);
    }

    /**
     * Get the <i>AC Compressor Type</i> attribute [attribute ID <b>0x0043</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcCompressorTypeAsync() {
        return read(serverAttributes.get(ATTR_ACCOMPRESSORTYPE));
    }

    /**
     * Synchronously get the <i>AC Compressor Type</i> attribute [attribute ID <b>0x0043</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getAcCompressorType(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACCOMPRESSORTYPE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACCOMPRESSORTYPE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACCOMPRESSORTYPE));
    }

    /**
     * Set the <i>AC Error Code</i> attribute [attribute ID <b>0x0044</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param acErrorCode the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setAcErrorCode(final Integer value) {
        return write(serverAttributes.get(ATTR_ACERRORCODE), value);
    }

    /**
     * Get the <i>AC Error Code</i> attribute [attribute ID <b>0x0044</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcErrorCodeAsync() {
        return read(serverAttributes.get(ATTR_ACERRORCODE));
    }

    /**
     * Synchronously get the <i>AC Error Code</i> attribute [attribute ID <b>0x0044</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getAcErrorCode(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACERRORCODE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACERRORCODE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACERRORCODE));
    }

    /**
     * Set the <i>AC Louver Position</i> attribute [attribute ID <b>0x0045</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param acLouverPosition the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setAcLouverPosition(final Integer value) {
        return write(serverAttributes.get(ATTR_ACLOUVERPOSITION), value);
    }

    /**
     * Get the <i>AC Louver Position</i> attribute [attribute ID <b>0x0045</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcLouverPositionAsync() {
        return read(serverAttributes.get(ATTR_ACLOUVERPOSITION));
    }

    /**
     * Synchronously get the <i>AC Louver Position</i> attribute [attribute ID <b>0x0045</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getAcLouverPosition(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACLOUVERPOSITION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACLOUVERPOSITION).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACLOUVERPOSITION));
    }

    /**
     * Get the <i>AC Coil Temperature</i> attribute [attribute ID <b>0x0046</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcCoilTemperatureAsync() {
        return read(serverAttributes.get(ATTR_ACCOILTEMPERATURE));
    }

    /**
     * Synchronously get the <i>AC Coil Temperature</i> attribute [attribute ID <b>0x0046</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getAcCoilTemperature(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACCOILTEMPERATURE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACCOILTEMPERATURE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACCOILTEMPERATURE));
    }

    /**
     * Set the <i>AC Capacity Format</i> attribute [attribute ID <b>0x0047</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param acCapacityFormat the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setAcCapacityFormat(final Integer value) {
        return write(serverAttributes.get(ATTR_ACCAPACITYFORMAT), value);
    }

    /**
     * Get the <i>AC Capacity Format</i> attribute [attribute ID <b>0x0047</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcCapacityFormatAsync() {
        return read(serverAttributes.get(ATTR_ACCAPACITYFORMAT));
    }

    /**
     * Synchronously get the <i>AC Capacity Format</i> attribute [attribute ID <b>0x0047</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getAcCapacityFormat(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACCAPACITYFORMAT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACCAPACITYFORMAT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACCAPACITYFORMAT));
    }

    /**
     * The Setpoint Raise/Lower Command
     *
     * @param mode {@link Integer} Mode
     * @param amount {@link Integer} Amount
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.setpointRaiseLowerCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new setpointRaiseLowerCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> setpointRaiseLowerCommand(Integer mode, Integer amount) {
        SetpointRaiseLowerCommand command = new SetpointRaiseLowerCommand();

        // Set the fields
        command.setMode(mode);
        command.setAmount(amount);

        return sendCommand(command);
    }

    /**
     * The Set Weekly Schedule
     * <p>
     * The set weekly schedule command is used to update the thermostat weekly set point
     * schedule from a management system. If the thermostat already has a weekly set point
     * schedule programmed then it should replace each daily set point set as it receives the
     * updates from the management system. For example if the thermostat has 4 set points for
     * every day of the week and is sent a Set Weekly Schedule command with one set point for
     * Saturday then the thermostat should remove all 4 set points for Saturday and replace
     * those with the updated set point but leave all other days unchanged. <br> If the schedule
     * is larger than what fits in one ZigBee frame or contains more than 10 transitions, the
     * schedule shall then be sent using multipleSet Weekly Schedule Commands.
     *
     * @param numberOfTransitions {@link Integer} Number Of Transitions
     * @param dayOfWeek {@link Integer} Day Of Week
     * @param mode {@link Integer} Mode
     * @param transition {@link Integer} Transition
     * @param heatSet {@link Integer} Heat Set
     * @param coolSet {@link Integer} Cool Set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.setWeeklySchedule(parameters ...)</code>
     * with <code>cluster.sendCommand(new setWeeklySchedule(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> setWeeklySchedule(Integer numberOfTransitions, Integer dayOfWeek, Integer mode, Integer transition, Integer heatSet, Integer coolSet) {
        SetWeeklySchedule command = new SetWeeklySchedule();

        // Set the fields
        command.setNumberOfTransitions(numberOfTransitions);
        command.setDayOfWeek(dayOfWeek);
        command.setMode(mode);
        command.setTransition(transition);
        command.setHeatSet(heatSet);
        command.setCoolSet(coolSet);

        return sendCommand(command);
    }

    /**
     * The Get Weekly Schedule
     *
     * @param daysToReturn {@link Integer} Days To Return
     * @param modeToReturn {@link Integer} Mode To Return
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getWeeklySchedule(parameters ...)</code>
     * with <code>cluster.sendCommand(new getWeeklySchedule(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getWeeklySchedule(Integer daysToReturn, Integer modeToReturn) {
        GetWeeklySchedule command = new GetWeeklySchedule();

        // Set the fields
        command.setDaysToReturn(daysToReturn);
        command.setModeToReturn(modeToReturn);

        return sendCommand(command);
    }

    /**
     * The Clear Weekly Schedule
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.clearWeeklySchedule(parameters ...)</code>
     * with <code>cluster.sendCommand(new clearWeeklySchedule(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> clearWeeklySchedule() {
        return sendCommand(new ClearWeeklySchedule());
    }

    /**
     * The Get Relay Status Log
     * <p>
     * The Get Relay Status Log command is used to query the thermostat internal relay status
     * log. This command has no payload. <br> The log storing order is First in First Out (FIFO)
     * when the log is generated and stored into the Queue. <br> The first record in the log
     * (i.e., the oldest) one, is the first to be replaced when there is a new record and there is
     * no more space in the log. Thus, the newest record will overwrite the oldest one if there is
     * no space left. <br> The log storing order is Last In First Out (LIFO) when the log is being
     * retrieved from the Queue by a client device. Once the "Get Relay Status Log Response"
     * frame is sent by the Server, the "Unread Entries" attribute should be decremented to
     * indicate the number of unread records that remain in the queue. <br> If the "Unread
     * Entries"attribute reaches zero and the Client sends a new "Get Relay Status Log
     * Request", the Server may send one of the following items as a response: <br> i) resend the
     * last Get Relay Status Log Response or ii) generate new log record at the time of request
     * and send Get Relay Status Log Response with the new data
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getRelayStatusLog(parameters ...)</code>
     * with <code>cluster.sendCommand(new getRelayStatusLog(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getRelayStatusLog() {
        return sendCommand(new GetRelayStatusLog());
    }

    /**
     * The Get Weekly Schedule Response
     *
     * @param numberOfTransitions {@link Integer} Number Of Transitions
     * @param dayOfWeek {@link Integer} Day Of Week
     * @param mode {@link Integer} Mode
     * @param transition {@link Integer} Transition
     * @param heatSet {@link Integer} Heat Set
     * @param coolSet {@link Integer} Cool Set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getWeeklyScheduleResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new getWeeklyScheduleResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getWeeklyScheduleResponse(Integer numberOfTransitions, Integer dayOfWeek, Integer mode, Integer transition, Integer heatSet, Integer coolSet) {
        GetWeeklyScheduleResponse command = new GetWeeklyScheduleResponse();

        // Set the fields
        command.setNumberOfTransitions(numberOfTransitions);
        command.setDayOfWeek(dayOfWeek);
        command.setMode(mode);
        command.setTransition(transition);
        command.setHeatSet(heatSet);
        command.setCoolSet(coolSet);

        return sendCommand(command);
    }

    /**
     * The Get Relay Status Log Response
     *
     * @param timeOfDay {@link Integer} Time Of Day
     * @param relayStatus {@link Integer} Relay Status
     * @param localTemperature {@link Integer} Local Temperature
     * @param humidity {@link Integer} Humidity
     * @param setpoint {@link Integer} Setpoint
     * @param unreadEntries {@link Integer} Unread Entries
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getRelayStatusLogResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new getRelayStatusLogResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getRelayStatusLogResponse(Integer timeOfDay, Integer relayStatus, Integer localTemperature, Integer humidity, Integer setpoint, Integer unreadEntries) {
        GetRelayStatusLogResponse command = new GetRelayStatusLogResponse();

        // Set the fields
        command.setTimeOfDay(timeOfDay);
        command.setRelayStatus(relayStatus);
        command.setLocalTemperature(localTemperature);
        command.setHumidity(humidity);
        command.setSetpoint(setpoint);
        command.setUnreadEntries(unreadEntries);

        return sendCommand(command);
    }
}
