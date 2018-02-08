/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
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
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

/**
 * <b>Thermostat</b> cluster implementation (<i>Cluster ID 0x0201</i>).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
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
     * LocalTemperature represents the temperature in degrees Celsius, as measured locally.
     */
    public static final int ATTR_LOCALTEMPERATURE = 0x0000;
    /**
     * OutdoorTemperature represents the temperature in degrees Celsius, as measured locally.
     */
    public static final int ATTR_OUTDOORTEMPERATURE = 0x0001;
    /**
     * Occupancy specifies whether the heated/cooled space is occupied or not
     */
    public static final int ATTR_OCCUPANCY = 0x0002;
    /**
     * The MinHeatSetpointLimit attribute specifies the absolute minimum level that the heating setpoint MAY be
     * set to. This is a limitation imposed by the manufacturer.
     */
    public static final int ATTR_ABSMINHEATSETPOINTLIMIT = 0x0003;
    /**
     * The MaxHeatSetpointLimit attribute specifies the absolute maximum level that the heating setpoint MAY be
     * set to. This is a limitation imposed by the manufacturer.
     */
    public static final int ATTR_ABSMAXHEATSETPOINTLIMIT = 0x0004;
    /**
     * The MinCoolSetpointLimit attribute specifies the absolute minimum level that the cooling setpoint MAY be
     * set to. This is a limitation imposed by the manufacturer.
     */
    public static final int ATTR_ABSMINCOOLSETPOINTLIMIT = 0x0005;
    /**
     * The MaxCoolSetpointLimit attribute specifies the absolute maximum level that the cooling setpoint MAY be
     * set to. This is a limitation imposed by the manufacturer.
     */
    public static final int ATTR_ABSMAXCOOLSETPOINTLIMIT = 0x0006;
    /**
     * The PICoolingDemandattribute is 8 bits in length and specifies the level of cooling demanded by the PI
     * (proportional  integral) control loop in use by the thermostat (if any), in percent.  This value is 0 when the
     * thermostat is in “off” or “heating” mode.
     */
    public static final int ATTR_PICOOLINGDEMAND = 0x0007;
    /**
     * The PIHeatingDemand attribute is 8 bits in length and specifies the level of heating demanded by the PI
     * (proportional  integral) control loop in use by the thermostat (if any), in percent.  This value is 0 when the
     * thermostat is in “off” or “cooling” mode.
     */
    public static final int ATTR_PIHEATINGDEMAND = 0x0008;
    /**
     */
    public static final int ATTR_HVACSYSTEMTYPECONFIGURATION = 0x0009;
    /**
     */
    public static final int ATTR_LOCALTEMPERATURECALIBRATION = 0x0010;
    /**
     */
    public static final int ATTR_OCCUPIEDCOOLINGSETPOINT = 0x0011;
    /**
     */
    public static final int ATTR_OCCUPIEDHEATINGSETPOINT = 0x0012;
    /**
     */
    public static final int ATTR_UNOCCUPIEDCOOLINGSETPOINT = 0x0013;
    /**
     */
    public static final int ATTR_UNOCCUPIEDHEATINGSETPOINT = 0x0014;
    /**
     */
    public static final int ATTR_MINHEATSETPOINTLIMIT = 0x0015;
    /**
     */
    public static final int ATTR_MAXHEATSETPOINTLIMIT = 0x0016;
    /**
     */
    public static final int ATTR_MINCOOLSETPOINTLIMIT = 0x0017;
    /**
     */
    public static final int ATTR_MAXCOOLSETPOINTLIMIT = 0x0018;
    /**
     */
    public static final int ATTR_MINSETPOINTDEADBAND = 0x0019;
    /**
     */
    public static final int ATTR_REMOTESENSING = 0x001A;
    /**
     */
    public static final int ATTR_CONTROLSEQUENCEOFOPERATION = 0x001B;
    /**
     */
    public static final int ATTR_SYSTEMMODE = 0x001C;
    /**
     */
    public static final int ATTR_ALARMMASK = 0x001D;
    /**
     */
    public static final int ATTR_THERMOSTATRUNNINGMODE = 0x001E;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(25);

        attributeMap.put(ATTR_LOCALTEMPERATURE, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_LOCALTEMPERATURE, "LocalTemperature", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_OUTDOORTEMPERATURE, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_OUTDOORTEMPERATURE, "OutdoorTemperature", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_OCCUPANCY, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_OCCUPANCY, "Occupancy", ZclDataType.BITMAP_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_ABSMINHEATSETPOINTLIMIT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_ABSMINHEATSETPOINTLIMIT, "AbsMinHeatSetpointLimit", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ABSMAXHEATSETPOINTLIMIT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_ABSMAXHEATSETPOINTLIMIT, "AbsMaxHeatSetpointLimit", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ABSMINCOOLSETPOINTLIMIT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_ABSMINCOOLSETPOINTLIMIT, "AbsMinCoolSetpointLimit", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ABSMAXCOOLSETPOINTLIMIT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_ABSMAXCOOLSETPOINTLIMIT, "AbsMaxCoolSetpointLimit", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PICOOLINGDEMAND, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_PICOOLINGDEMAND, "PICoolingDemand", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_PIHEATINGDEMAND, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_PIHEATINGDEMAND, "PIHeatingDemand", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_HVACSYSTEMTYPECONFIGURATION, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_HVACSYSTEMTYPECONFIGURATION, "HVACSystemTypeConfiguration", ZclDataType.BITMAP_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_LOCALTEMPERATURECALIBRATION, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_LOCALTEMPERATURECALIBRATION, "LocalTemperatureCalibration", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_OCCUPIEDCOOLINGSETPOINT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_OCCUPIEDCOOLINGSETPOINT, "OccupiedCoolingSetpoint", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_OCCUPIEDHEATINGSETPOINT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_OCCUPIEDHEATINGSETPOINT, "OccupiedHeatingSetpoint", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_UNOCCUPIEDCOOLINGSETPOINT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_UNOCCUPIEDCOOLINGSETPOINT, "UnoccupiedCoolingSetpoint", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_UNOCCUPIEDHEATINGSETPOINT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_UNOCCUPIEDHEATINGSETPOINT, "UnoccupiedHeatingSetpoint", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MINHEATSETPOINTLIMIT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_MINHEATSETPOINTLIMIT, "MinHeatSetpointLimit", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MAXHEATSETPOINTLIMIT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_MAXHEATSETPOINTLIMIT, "MaxHeatSetpointLimit", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MINCOOLSETPOINTLIMIT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_MINCOOLSETPOINTLIMIT, "MinCoolSetpointLimit", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MAXCOOLSETPOINTLIMIT, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_MAXCOOLSETPOINTLIMIT, "MaxCoolSetpointLimit", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MINSETPOINTDEADBAND, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_MINSETPOINTDEADBAND, "MinSetpointDeadBand", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_REMOTESENSING, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_REMOTESENSING, "RemoteSensing", ZclDataType.BITMAP_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_CONTROLSEQUENCEOFOPERATION, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_CONTROLSEQUENCEOFOPERATION, "ControlSequenceOfOperation", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_SYSTEMMODE, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_SYSTEMMODE, "SystemMode", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_ALARMMASK, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_ALARMMASK, "AlarmMask", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_THERMOSTATRUNNINGMODE, new ZclAttribute(ZclClusterType.THERMOSTAT, ATTR_THERMOSTATRUNNINGMODE, "ThermostatRunningMode", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Thermostat cluster.
     *
     * @param zigbeeManager {@link ZigBeeNetworkManager}
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint}
     */
    public ZclThermostatCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeManager, zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * Get the <i>LocalTemperature</i> attribute [attribute ID <b>0</b>].
     * <p>
     * LocalTemperature represents the temperature in degrees Celsius, as measured locally.
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
     * Synchronously get the <i>LocalTemperature</i> attribute [attribute ID <b>0</b>].
     * <p>
     * LocalTemperature represents the temperature in degrees Celsius, as measured locally.
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
     * Set reporting for the <i>LocalTemperature</i> attribute [attribute ID <b>0</b>].
     * <p>
     * LocalTemperature represents the temperature in degrees Celsius, as measured locally.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval {@link int} minimum reporting period
     * @param maxInterval {@link int} maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setLocalTemperatureReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_LOCALTEMPERATURE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>OutdoorTemperature</i> attribute [attribute ID <b>1</b>].
     * <p>
     * OutdoorTemperature represents the temperature in degrees Celsius, as measured locally.
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
     * Synchronously get the <i>OutdoorTemperature</i> attribute [attribute ID <b>1</b>].
     * <p>
     * OutdoorTemperature represents the temperature in degrees Celsius, as measured locally.
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
     * Get the <i>Occupancy</i> attribute [attribute ID <b>2</b>].
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
     * Synchronously get the <i>Occupancy</i> attribute [attribute ID <b>2</b>].
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
     * Get the <i>AbsMinHeatSetpointLimit</i> attribute [attribute ID <b>3</b>].
     * <p>
     * The MinHeatSetpointLimit attribute specifies the absolute minimum level that the heating setpoint MAY be
     * set to. This is a limitation imposed by the manufacturer.
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
     * Synchronously get the <i>AbsMinHeatSetpointLimit</i> attribute [attribute ID <b>3</b>].
     * <p>
     * The MinHeatSetpointLimit attribute specifies the absolute minimum level that the heating setpoint MAY be
     * set to. This is a limitation imposed by the manufacturer.
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
     * Get the <i>AbsMaxHeatSetpointLimit</i> attribute [attribute ID <b>4</b>].
     * <p>
     * The MaxHeatSetpointLimit attribute specifies the absolute maximum level that the heating setpoint MAY be
     * set to. This is a limitation imposed by the manufacturer.
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
     * Synchronously get the <i>AbsMaxHeatSetpointLimit</i> attribute [attribute ID <b>4</b>].
     * <p>
     * The MaxHeatSetpointLimit attribute specifies the absolute maximum level that the heating setpoint MAY be
     * set to. This is a limitation imposed by the manufacturer.
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
     * Get the <i>AbsMinCoolSetpointLimit</i> attribute [attribute ID <b>5</b>].
     * <p>
     * The MinCoolSetpointLimit attribute specifies the absolute minimum level that the cooling setpoint MAY be
     * set to. This is a limitation imposed by the manufacturer.
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
     * Synchronously get the <i>AbsMinCoolSetpointLimit</i> attribute [attribute ID <b>5</b>].
     * <p>
     * The MinCoolSetpointLimit attribute specifies the absolute minimum level that the cooling setpoint MAY be
     * set to. This is a limitation imposed by the manufacturer.
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
     * Get the <i>AbsMaxCoolSetpointLimit</i> attribute [attribute ID <b>6</b>].
     * <p>
     * The MaxCoolSetpointLimit attribute specifies the absolute maximum level that the cooling setpoint MAY be
     * set to. This is a limitation imposed by the manufacturer.
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
     * Synchronously get the <i>AbsMaxCoolSetpointLimit</i> attribute [attribute ID <b>6</b>].
     * <p>
     * The MaxCoolSetpointLimit attribute specifies the absolute maximum level that the cooling setpoint MAY be
     * set to. This is a limitation imposed by the manufacturer.
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
     * Get the <i>PICoolingDemand</i> attribute [attribute ID <b>7</b>].
     * <p>
     * The PICoolingDemandattribute is 8 bits in length and specifies the level of cooling demanded by the PI
     * (proportional  integral) control loop in use by the thermostat (if any), in percent.  This value is 0 when the
     * thermostat is in “off” or “heating” mode.
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
     * Synchronously get the <i>PICoolingDemand</i> attribute [attribute ID <b>7</b>].
     * <p>
     * The PICoolingDemandattribute is 8 bits in length and specifies the level of cooling demanded by the PI
     * (proportional  integral) control loop in use by the thermostat (if any), in percent.  This value is 0 when the
     * thermostat is in “off” or “heating” mode.
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
     * Set reporting for the <i>PICoolingDemand</i> attribute [attribute ID <b>7</b>].
     * <p>
     * The PICoolingDemandattribute is 8 bits in length and specifies the level of cooling demanded by the PI
     * (proportional  integral) control loop in use by the thermostat (if any), in percent.  This value is 0 when the
     * thermostat is in “off” or “heating” mode.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param minInterval {@link int} minimum reporting period
     * @param maxInterval {@link int} maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPiCoolingDemandReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PICOOLINGDEMAND), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>PIHeatingDemand</i> attribute [attribute ID <b>8</b>].
     * <p>
     * The PIHeatingDemand attribute is 8 bits in length and specifies the level of heating demanded by the PI
     * (proportional  integral) control loop in use by the thermostat (if any), in percent.  This value is 0 when the
     * thermostat is in “off” or “cooling” mode.
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
     * Synchronously get the <i>PIHeatingDemand</i> attribute [attribute ID <b>8</b>].
     * <p>
     * The PIHeatingDemand attribute is 8 bits in length and specifies the level of heating demanded by the PI
     * (proportional  integral) control loop in use by the thermostat (if any), in percent.  This value is 0 when the
     * thermostat is in “off” or “cooling” mode.
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
     * Set reporting for the <i>PIHeatingDemand</i> attribute [attribute ID <b>8</b>].
     * <p>
     * The PIHeatingDemand attribute is 8 bits in length and specifies the level of heating demanded by the PI
     * (proportional  integral) control loop in use by the thermostat (if any), in percent.  This value is 0 when the
     * thermostat is in “off” or “cooling” mode.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param minInterval {@link int} minimum reporting period
     * @param maxInterval {@link int} maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPiHeatingDemandReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PIHEATINGDEMAND), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>HVACSystemTypeConfiguration</i> attribute [attribute ID <b>9</b>].
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
     * Synchronously get the <i>HVACSystemTypeConfiguration</i> attribute [attribute ID <b>9</b>].
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
     * Get the <i>LocalTemperatureCalibration</i> attribute [attribute ID <b>16</b>].
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
     * Synchronously get the <i>LocalTemperatureCalibration</i> attribute [attribute ID <b>16</b>].
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
     * Get the <i>OccupiedCoolingSetpoint</i> attribute [attribute ID <b>17</b>].
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
     * Synchronously get the <i>OccupiedCoolingSetpoint</i> attribute [attribute ID <b>17</b>].
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
     * Get the <i>OccupiedHeatingSetpoint</i> attribute [attribute ID <b>18</b>].
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
     * Synchronously get the <i>OccupiedHeatingSetpoint</i> attribute [attribute ID <b>18</b>].
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
     * Get the <i>UnoccupiedCoolingSetpoint</i> attribute [attribute ID <b>19</b>].
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
     * Synchronously get the <i>UnoccupiedCoolingSetpoint</i> attribute [attribute ID <b>19</b>].
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
     * Get the <i>UnoccupiedHeatingSetpoint</i> attribute [attribute ID <b>20</b>].
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
     * Synchronously get the <i>UnoccupiedHeatingSetpoint</i> attribute [attribute ID <b>20</b>].
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
     * Get the <i>MinHeatSetpointLimit</i> attribute [attribute ID <b>21</b>].
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
     * Synchronously get the <i>MinHeatSetpointLimit</i> attribute [attribute ID <b>21</b>].
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
     * Get the <i>MaxHeatSetpointLimit</i> attribute [attribute ID <b>22</b>].
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
     * Synchronously get the <i>MaxHeatSetpointLimit</i> attribute [attribute ID <b>22</b>].
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
     * Get the <i>MinCoolSetpointLimit</i> attribute [attribute ID <b>23</b>].
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
     * Synchronously get the <i>MinCoolSetpointLimit</i> attribute [attribute ID <b>23</b>].
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
     * Get the <i>MaxCoolSetpointLimit</i> attribute [attribute ID <b>24</b>].
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
     * Synchronously get the <i>MaxCoolSetpointLimit</i> attribute [attribute ID <b>24</b>].
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
     * Get the <i>MinSetpointDeadBand</i> attribute [attribute ID <b>25</b>].
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
     * Synchronously get the <i>MinSetpointDeadBand</i> attribute [attribute ID <b>25</b>].
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
     * Get the <i>RemoteSensing</i> attribute [attribute ID <b>26</b>].
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
     * Synchronously get the <i>RemoteSensing</i> attribute [attribute ID <b>26</b>].
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
     * Get the <i>ControlSequenceOfOperation</i> attribute [attribute ID <b>27</b>].
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
     * Synchronously get the <i>ControlSequenceOfOperation</i> attribute [attribute ID <b>27</b>].
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
     * Get the <i>SystemMode</i> attribute [attribute ID <b>28</b>].
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
     * Synchronously get the <i>SystemMode</i> attribute [attribute ID <b>28</b>].
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
     * Get the <i>AlarmMask</i> attribute [attribute ID <b>29</b>].
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
     * Synchronously get the <i>AlarmMask</i> attribute [attribute ID <b>29</b>].
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
     * Get the <i>ThermostatRunningMode</i> attribute [attribute ID <b>30</b>].
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
     * Synchronously get the <i>ThermostatRunningMode</i> attribute [attribute ID <b>30</b>].
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
     * The set weekly schedule command is used to update the thermostat weekly set point schedule from a management system.
     * If the thermostat already has a weekly set point schedule programmed then it SHOULD replace each daily set point set
     * as it receives the updates from the management system. For example if the thermostat has 4 set points for every day of
     * the week and is sent a Set Weekly Schedule command with one set point for Saturday then the thermostat SHOULD remove
     * all 4 set points for Saturday and replace those with the updated set point but leave all other days unchanged.
     * <br>
     * If the schedule is larger than what fits in one ZigBee frame or contains more than 10 transitions, the schedule SHALL
     * then be sent using multipleSet Weekly Schedule Commands.
     *
     * @param numberOfTransitions {@link Integer} Number of Transitions
     * @param dayOfWeek {@link Integer} Day of Week
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
        ClearWeeklySchedule command = new ClearWeeklySchedule();

        return send(command);
    }

    /**
     * The Get Relay Status Log
     * <p>
     * The Get Relay Status Log command is used to query the thermostat internal relay status log. This command has no payload.
     * <br>
     * The log storing order is First in First Out (FIFO) when the log is generated and stored into the Queue.
     * <br>
     * The first record in the log (i.e., the oldest) one, is the first to be replaced when there is a new record and there is
     * no more space in the log. Thus, the newest record will overwrite the oldest one if there is no space left.
     * <br>
     * The log storing order is Last In First Out (LIFO) when the log is being retrieved from the Queue by a client device.
     * Once the "Get Relay Status Log Response" frame is sent by the Server, the "Unread Entries" attribute
     * SHOULD be decremented to indicate the number of unread records that remain in the queue.
     * <br>
     * If the "Unread Entries"attribute reaches zero and the Client sends a new "Get Relay Status Log Request", the Server
     * MAY send one of the following items as a response:
     * <br>
     * i) resend the last Get Relay Status Log Response
     * or
     * ii) generate new log record at the time of request and send Get Relay Status Log Response with the new data
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRelayStatusLog() {
        GetRelayStatusLog command = new GetRelayStatusLog();

        return send(command);
    }

    /**
     * The Get Weekly Schedule Response
     *
     * @param numberOfTransitions {@link Integer} Number of Transitions
     * @param dayOfWeek {@link Integer} Day of Week
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
     * @param timeOfDay {@link Integer} Time of day
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

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0: // SETPOINT_RAISE_LOWER_COMMAND
                return new SetpointRaiseLowerCommand();
            case 1: // SET_WEEKLY_SCHEDULE
                return new SetWeeklySchedule();
            case 2: // GET_WEEKLY_SCHEDULE
                return new GetWeeklySchedule();
            case 3: // CLEAR_WEEKLY_SCHEDULE
                return new ClearWeeklySchedule();
            case 4: // GET_RELAY_STATUS_LOG
                return new GetRelayStatusLog();
            default:
                return null;
        }
    }

    @Override
    public ZclCommand getResponseFromId(int commandId) {
        switch (commandId) {
            case 0: // GET_WEEKLY_SCHEDULE_RESPONSE
                return new GetWeeklyScheduleResponse();
            case 1: // GET_RELAY_STATUS_LOG_RESPONSE
                return new GetRelayStatusLogResponse();
            default:
                return null;
        }
    }
}
