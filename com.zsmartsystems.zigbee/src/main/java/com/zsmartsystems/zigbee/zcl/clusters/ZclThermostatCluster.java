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
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.ClearWeeklySchedule;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.GetRelayStatusLog;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.GetRelayStatusLogResponse;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.GetWeeklySchedule;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.GetWeeklyScheduleResponse;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.SetWeeklySchedule;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.SetpointRaiseLowerCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Thermostat</b> cluster implementation (<i>Cluster ID 0x0201</i>).
 * <p>
 * This cluster provides an interface to the functionality of a thermostat.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-26T20:57:36Z")
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

    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<>(25);

        attributeMap.put(ATTR_LOCALTEMPERATURE, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_LOCALTEMPERATURE, "Local Temperature", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_OUTDOORTEMPERATURE, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_OUTDOORTEMPERATURE, "Outdoor Temperature", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_OCCUPANCY, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_OCCUPANCY, "Occupancy", ZclDataType.BITMAP_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_ABSMINHEATSETPOINTLIMIT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_ABSMINHEATSETPOINTLIMIT, "Abs Min Heat Setpoint Limit", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ABSMAXHEATSETPOINTLIMIT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_ABSMAXHEATSETPOINTLIMIT, "Abs Max Heat Setpoint Limit", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ABSMINCOOLSETPOINTLIMIT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_ABSMINCOOLSETPOINTLIMIT, "Abs Min Cool Setpoint Limit", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ABSMAXCOOLSETPOINTLIMIT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_ABSMAXCOOLSETPOINTLIMIT, "Abs Max Cool Setpoint Limit", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PICOOLINGDEMAND, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_PICOOLINGDEMAND, "Pi Cooling Demand", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_PIHEATINGDEMAND, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_PIHEATINGDEMAND, "Pi Heating Demand", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_HVACSYSTEMTYPECONFIGURATION, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_HVACSYSTEMTYPECONFIGURATION, "Hvac System Type Configuration", ZclDataType.BITMAP_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_LOCALTEMPERATURECALIBRATION, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_LOCALTEMPERATURECALIBRATION, "Local Temperature Calibration", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_OCCUPIEDCOOLINGSETPOINT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_OCCUPIEDCOOLINGSETPOINT, "Occupied Cooling Setpoint", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_OCCUPIEDHEATINGSETPOINT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_OCCUPIEDHEATINGSETPOINT, "Occupied Heating Setpoint", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_UNOCCUPIEDCOOLINGSETPOINT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_UNOCCUPIEDCOOLINGSETPOINT, "Unoccupied Cooling Setpoint", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_UNOCCUPIEDHEATINGSETPOINT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_UNOCCUPIEDHEATINGSETPOINT, "Unoccupied Heating Setpoint", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MINHEATSETPOINTLIMIT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_MINHEATSETPOINTLIMIT, "Min Heat Setpoint Limit", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MAXHEATSETPOINTLIMIT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_MAXHEATSETPOINTLIMIT, "Max Heat Setpoint Limit", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MINCOOLSETPOINTLIMIT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_MINCOOLSETPOINTLIMIT, "Min Cool Setpoint Limit", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MAXCOOLSETPOINTLIMIT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_MAXCOOLSETPOINTLIMIT, "Max Cool Setpoint Limit", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MINSETPOINTDEADBAND, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_MINSETPOINTDEADBAND, "Min Setpoint Dead Band", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_REMOTESENSING, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_REMOTESENSING, "Remote Sensing", ZclDataType.BITMAP_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_CONTROLSEQUENCEOFOPERATION, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_CONTROLSEQUENCEOFOPERATION, "Control Sequence Of Operation", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_SYSTEMMODE, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_SYSTEMMODE, "System Mode", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_ALARMMASK, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_ALARMMASK, "Alarm Mask", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_THERMOSTATRUNNINGMODE, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_THERMOSTATRUNNINGMODE, "Thermostat Running Mode", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));

        return attributeMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeServerCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentHashMap<>(2);

        commandMap.put(0x0000, GetWeeklyScheduleResponse.class);
        commandMap.put(0x0001, GetRelayStatusLogResponse.class);

        return commandMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentHashMap<>(5);

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
     */
    public Future<CommandResult> getLocalTemperatureAsync() {
        return read(attributes.get(ATTR_LOCALTEMPERATURE));
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
     */
    public Integer getLocalTemperature(final long refreshPeriod) {
        if (attributes.get(ATTR_LOCALTEMPERATURE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_LOCALTEMPERATURE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_LOCALTEMPERATURE));
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
     */
    public Future<CommandResult> setLocalTemperatureReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_LOCALTEMPERATURE), minInterval, maxInterval, reportableChange);
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
     */
    public Future<CommandResult> getOutdoorTemperatureAsync() {
        return read(attributes.get(ATTR_OUTDOORTEMPERATURE));
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
     */
    public Integer getOutdoorTemperature(final long refreshPeriod) {
        if (attributes.get(ATTR_OUTDOORTEMPERATURE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_OUTDOORTEMPERATURE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_OUTDOORTEMPERATURE));
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
     */
    public Future<CommandResult> getOccupancyAsync() {
        return read(attributes.get(ATTR_OCCUPANCY));
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
     */
    public Integer getOccupancy(final long refreshPeriod) {
        if (attributes.get(ATTR_OCCUPANCY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_OCCUPANCY).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_OCCUPANCY));
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
     */
    public Future<CommandResult> getAbsMinHeatSetpointLimitAsync() {
        return read(attributes.get(ATTR_ABSMINHEATSETPOINTLIMIT));
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
     */
    public Integer getAbsMinHeatSetpointLimit(final long refreshPeriod) {
        if (attributes.get(ATTR_ABSMINHEATSETPOINTLIMIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ABSMINHEATSETPOINTLIMIT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ABSMINHEATSETPOINTLIMIT));
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
     */
    public Future<CommandResult> getAbsMaxHeatSetpointLimitAsync() {
        return read(attributes.get(ATTR_ABSMAXHEATSETPOINTLIMIT));
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
     */
    public Integer getAbsMaxHeatSetpointLimit(final long refreshPeriod) {
        if (attributes.get(ATTR_ABSMAXHEATSETPOINTLIMIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ABSMAXHEATSETPOINTLIMIT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ABSMAXHEATSETPOINTLIMIT));
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
     */
    public Future<CommandResult> getAbsMinCoolSetpointLimitAsync() {
        return read(attributes.get(ATTR_ABSMINCOOLSETPOINTLIMIT));
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
     */
    public Integer getAbsMinCoolSetpointLimit(final long refreshPeriod) {
        if (attributes.get(ATTR_ABSMINCOOLSETPOINTLIMIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ABSMINCOOLSETPOINTLIMIT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ABSMINCOOLSETPOINTLIMIT));
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
     */
    public Future<CommandResult> getAbsMaxCoolSetpointLimitAsync() {
        return read(attributes.get(ATTR_ABSMAXCOOLSETPOINTLIMIT));
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
     */
    public Integer getAbsMaxCoolSetpointLimit(final long refreshPeriod) {
        if (attributes.get(ATTR_ABSMAXCOOLSETPOINTLIMIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ABSMAXCOOLSETPOINTLIMIT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ABSMAXCOOLSETPOINTLIMIT));
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
     */
    public Future<CommandResult> getPiCoolingDemandAsync() {
        return read(attributes.get(ATTR_PICOOLINGDEMAND));
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
     */
    public Integer getPiCoolingDemand(final long refreshPeriod) {
        if (attributes.get(ATTR_PICOOLINGDEMAND).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PICOOLINGDEMAND).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PICOOLINGDEMAND));
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
     */
    public Future<CommandResult> getPiHeatingDemandAsync() {
        return read(attributes.get(ATTR_PIHEATINGDEMAND));
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
     */
    public Integer getPiHeatingDemand(final long refreshPeriod) {
        if (attributes.get(ATTR_PIHEATINGDEMAND).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PIHEATINGDEMAND).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PIHEATINGDEMAND));
    }

    /**
     * Get the <i>Hvac System Type Configuration</i> attribute [attribute ID <b>0x0009</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getHvacSystemTypeConfigurationAsync() {
        return read(attributes.get(ATTR_HVACSYSTEMTYPECONFIGURATION));
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
     */
    public Integer getHvacSystemTypeConfiguration(final long refreshPeriod) {
        if (attributes.get(ATTR_HVACSYSTEMTYPECONFIGURATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_HVACSYSTEMTYPECONFIGURATION).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_HVACSYSTEMTYPECONFIGURATION));
    }

    /**
     * Get the <i>Local Temperature Calibration</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLocalTemperatureCalibrationAsync() {
        return read(attributes.get(ATTR_LOCALTEMPERATURECALIBRATION));
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
     */
    public Integer getLocalTemperatureCalibration(final long refreshPeriod) {
        if (attributes.get(ATTR_LOCALTEMPERATURECALIBRATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_LOCALTEMPERATURECALIBRATION).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_LOCALTEMPERATURECALIBRATION));
    }

    /**
     * Get the <i>Occupied Cooling Setpoint</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getOccupiedCoolingSetpointAsync() {
        return read(attributes.get(ATTR_OCCUPIEDCOOLINGSETPOINT));
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
     */
    public Integer getOccupiedCoolingSetpoint(final long refreshPeriod) {
        if (attributes.get(ATTR_OCCUPIEDCOOLINGSETPOINT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_OCCUPIEDCOOLINGSETPOINT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_OCCUPIEDCOOLINGSETPOINT));
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
     */
    public Future<CommandResult> setOccupiedCoolingSetpointReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_OCCUPIEDCOOLINGSETPOINT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Occupied Heating Setpoint</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getOccupiedHeatingSetpointAsync() {
        return read(attributes.get(ATTR_OCCUPIEDHEATINGSETPOINT));
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
     */
    public Integer getOccupiedHeatingSetpoint(final long refreshPeriod) {
        if (attributes.get(ATTR_OCCUPIEDHEATINGSETPOINT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_OCCUPIEDHEATINGSETPOINT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_OCCUPIEDHEATINGSETPOINT));
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
     */
    public Future<CommandResult> setOccupiedHeatingSetpointReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_OCCUPIEDHEATINGSETPOINT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Unoccupied Cooling Setpoint</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getUnoccupiedCoolingSetpointAsync() {
        return read(attributes.get(ATTR_UNOCCUPIEDCOOLINGSETPOINT));
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
     */
    public Integer getUnoccupiedCoolingSetpoint(final long refreshPeriod) {
        if (attributes.get(ATTR_UNOCCUPIEDCOOLINGSETPOINT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_UNOCCUPIEDCOOLINGSETPOINT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_UNOCCUPIEDCOOLINGSETPOINT));
    }

    /**
     * Get the <i>Unoccupied Heating Setpoint</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getUnoccupiedHeatingSetpointAsync() {
        return read(attributes.get(ATTR_UNOCCUPIEDHEATINGSETPOINT));
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
     */
    public Integer getUnoccupiedHeatingSetpoint(final long refreshPeriod) {
        if (attributes.get(ATTR_UNOCCUPIEDHEATINGSETPOINT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_UNOCCUPIEDHEATINGSETPOINT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_UNOCCUPIEDHEATINGSETPOINT));
    }

    /**
     * Get the <i>Min Heat Setpoint Limit</i> attribute [attribute ID <b>0x0015</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMinHeatSetpointLimitAsync() {
        return read(attributes.get(ATTR_MINHEATSETPOINTLIMIT));
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
     */
    public Integer getMinHeatSetpointLimit(final long refreshPeriod) {
        if (attributes.get(ATTR_MINHEATSETPOINTLIMIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MINHEATSETPOINTLIMIT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MINHEATSETPOINTLIMIT));
    }

    /**
     * Get the <i>Max Heat Setpoint Limit</i> attribute [attribute ID <b>0x0016</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMaxHeatSetpointLimitAsync() {
        return read(attributes.get(ATTR_MAXHEATSETPOINTLIMIT));
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
     */
    public Integer getMaxHeatSetpointLimit(final long refreshPeriod) {
        if (attributes.get(ATTR_MAXHEATSETPOINTLIMIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MAXHEATSETPOINTLIMIT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MAXHEATSETPOINTLIMIT));
    }

    /**
     * Get the <i>Min Cool Setpoint Limit</i> attribute [attribute ID <b>0x0017</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMinCoolSetpointLimitAsync() {
        return read(attributes.get(ATTR_MINCOOLSETPOINTLIMIT));
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
     */
    public Integer getMinCoolSetpointLimit(final long refreshPeriod) {
        if (attributes.get(ATTR_MINCOOLSETPOINTLIMIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MINCOOLSETPOINTLIMIT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MINCOOLSETPOINTLIMIT));
    }

    /**
     * Get the <i>Max Cool Setpoint Limit</i> attribute [attribute ID <b>0x0018</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMaxCoolSetpointLimitAsync() {
        return read(attributes.get(ATTR_MAXCOOLSETPOINTLIMIT));
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
     */
    public Integer getMaxCoolSetpointLimit(final long refreshPeriod) {
        if (attributes.get(ATTR_MAXCOOLSETPOINTLIMIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MAXCOOLSETPOINTLIMIT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MAXCOOLSETPOINTLIMIT));
    }

    /**
     * Get the <i>Min Setpoint Dead Band</i> attribute [attribute ID <b>0x0019</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMinSetpointDeadBandAsync() {
        return read(attributes.get(ATTR_MINSETPOINTDEADBAND));
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
     */
    public Integer getMinSetpointDeadBand(final long refreshPeriod) {
        if (attributes.get(ATTR_MINSETPOINTDEADBAND).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MINSETPOINTDEADBAND).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MINSETPOINTDEADBAND));
    }

    /**
     * Get the <i>Remote Sensing</i> attribute [attribute ID <b>0x001A</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRemoteSensingAsync() {
        return read(attributes.get(ATTR_REMOTESENSING));
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
     */
    public Integer getRemoteSensing(final long refreshPeriod) {
        if (attributes.get(ATTR_REMOTESENSING).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_REMOTESENSING).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_REMOTESENSING));
    }

    /**
     * Get the <i>Control Sequence Of Operation</i> attribute [attribute ID <b>0x001B</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getControlSequenceOfOperationAsync() {
        return read(attributes.get(ATTR_CONTROLSEQUENCEOFOPERATION));
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
     */
    public Integer getControlSequenceOfOperation(final long refreshPeriod) {
        if (attributes.get(ATTR_CONTROLSEQUENCEOFOPERATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CONTROLSEQUENCEOFOPERATION).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CONTROLSEQUENCEOFOPERATION));
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
     */
    public Future<CommandResult> setControlSequenceOfOperationReporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_CONTROLSEQUENCEOFOPERATION), minInterval, maxInterval);
    }

    /**
     * Get the <i>System Mode</i> attribute [attribute ID <b>0x001C</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getSystemModeAsync() {
        return read(attributes.get(ATTR_SYSTEMMODE));
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
     */
    public Integer getSystemMode(final long refreshPeriod) {
        if (attributes.get(ATTR_SYSTEMMODE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_SYSTEMMODE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_SYSTEMMODE));
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
     */
    public Future<CommandResult> setSystemModeReporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_SYSTEMMODE), minInterval, maxInterval);
    }

    /**
     * Get the <i>Alarm Mask</i> attribute [attribute ID <b>0x001D</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getAlarmMaskAsync() {
        return read(attributes.get(ATTR_ALARMMASK));
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
     */
    public Integer getAlarmMask(final long refreshPeriod) {
        if (attributes.get(ATTR_ALARMMASK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ALARMMASK).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ALARMMASK));
    }

    /**
     * Get the <i>Thermostat Running Mode</i> attribute [attribute ID <b>0x001E</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getThermostatRunningModeAsync() {
        return read(attributes.get(ATTR_THERMOSTATRUNNINGMODE));
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
     */
    public Integer getThermostatRunningMode(final long refreshPeriod) {
        if (attributes.get(ATTR_THERMOSTATRUNNINGMODE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_THERMOSTATRUNNINGMODE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_THERMOSTATRUNNINGMODE));
    }

    /**
     * The Setpoint Raise/Lower Command
     *
     * @param mode {@link Integer} Mode
     * @param amount {@link Integer} Amount
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setpointRaiseLowerCommand(Integer mode, Integer amount) {
        SetpointRaiseLowerCommand command = new SetpointRaiseLowerCommand();

        // Set the fields
        command.setMode(mode);
        command.setAmount(amount);

        return send(command);
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
     */
    public Future<CommandResult> setWeeklySchedule(Integer numberOfTransitions, Integer dayOfWeek, Integer mode, Integer transition, Integer heatSet, Integer coolSet) {
        SetWeeklySchedule command = new SetWeeklySchedule();

        // Set the fields
        command.setNumberOfTransitions(numberOfTransitions);
        command.setDayOfWeek(dayOfWeek);
        command.setMode(mode);
        command.setTransition(transition);
        command.setHeatSet(heatSet);
        command.setCoolSet(coolSet);

        return send(command);
    }

    /**
     * The Get Weekly Schedule
     *
     * @param daysToReturn {@link Integer} Days To Return
     * @param modeToReturn {@link Integer} Mode To Return
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getWeeklySchedule(Integer daysToReturn, Integer modeToReturn) {
        GetWeeklySchedule command = new GetWeeklySchedule();

        // Set the fields
        command.setDaysToReturn(daysToReturn);
        command.setModeToReturn(modeToReturn);

        return send(command);
    }

    /**
     * The Clear Weekly Schedule
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> clearWeeklySchedule() {
        return send(new ClearWeeklySchedule());
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
     */
    public Future<CommandResult> getRelayStatusLog() {
        return send(new GetRelayStatusLog());
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
     */
    public Future<CommandResult> getWeeklyScheduleResponse(Integer numberOfTransitions, Integer dayOfWeek, Integer mode, Integer transition, Integer heatSet, Integer coolSet) {
        GetWeeklyScheduleResponse command = new GetWeeklyScheduleResponse();

        // Set the fields
        command.setNumberOfTransitions(numberOfTransitions);
        command.setDayOfWeek(dayOfWeek);
        command.setMode(mode);
        command.setTransition(transition);
        command.setHeatSet(heatSet);
        command.setCoolSet(coolSet);

        return send(command);
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
     */
    public Future<CommandResult> getRelayStatusLogResponse(Integer timeOfDay, Integer relayStatus, Integer localTemperature, Integer humidity, Integer setpoint, Integer unreadEntries) {
        GetRelayStatusLogResponse command = new GetRelayStatusLogResponse();

        // Set the fields
        command.setTimeOfDay(timeOfDay);
        command.setRelayStatus(relayStatus);
        command.setLocalTemperature(localTemperature);
        command.setHumidity(humidity);
        command.setSetpoint(setpoint);
        command.setUnreadEntries(unreadEntries);

        return send(command);
    }
}
