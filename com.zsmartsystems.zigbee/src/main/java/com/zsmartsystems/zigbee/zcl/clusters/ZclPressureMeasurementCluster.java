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
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

/**
 * <b>Pressure measurement</b> cluster implementation (<i>Cluster ID 0x0403</i>).
 * <p>
 * The cluster provides an interface to pressure measurement functionality,
 * including configuration and provision of notifications of pressure measurements.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclPressureMeasurementCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0403;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Pressure measurement";

    // Attribute constants
    /**
     * MeasuredValue represents the pressure in kPa as follows:-
     * <p>
     * MeasuredValue = 10 x Pressure
     * <p>
     * Where -3276.7 kPa <= Pressure <= 3276.7 kPa, corresponding to a
     * MeasuredValue in the range 0x8001 to 0x7fff.
     * <p>
     * Note:- The maximum resolution this format allows is 0.1 kPa.
     * <p>
     * A MeasuredValue of 0x8000 indicates that the pressure measurement is invalid.
     * MeasuredValue is updated continuously as new measurements are made.
     */
    public static final int ATTR_MEASUREDVALUE = 0x0000;
    /**
     * The MinMeasuredValue attribute indicates the minimum value of MeasuredValue
     * that can be measured. A value of 0x8000 means this attribute is not defined.
     */
    public static final int ATTR_MINMEASUREDVALUE = 0x0001;
    /**
     * The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue
     * that can be measured. A value of 0x8000 means this attribute is not defined.
     * <p>
     * MaxMeasuredValue shall be greater than MinMeasuredValue.
     * <p>
     * MinMeasuredValue and MaxMeasuredValue define the range of the sensor.
     */
    public static final int ATTR_MAXMEASUREDVALUE = 0x0002;
    /**
     * The Tolerance attribute indicates the magnitude of the possible error that is
     * associated with MeasuredValue . The true value is located in the range
     * (MeasuredValue – Tolerance) to (MeasuredValue + Tolerance).
     */
    public static final int ATTR_TOLERANCE = 0x0003;
    /**
     */
    public static final int ATTR_SCALEDVALUE = 0x0010;
    /**
     */
    public static final int ATTR_MINSCALEDVALUE = 0x0011;
    /**
     */
    public static final int ATTR_MAXSCALEDVALUE = 0x0012;
    /**
     */
    public static final int ATTR_SCALEDTOLERANCE = 0x0013;
    /**
     */
    public static final int ATTR_SCALE = 0x0014;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(9);

        attributeMap.put(ATTR_MEASUREDVALUE, new ZclAttribute(ZclClusterType.PRESSURE_MEASUREMENT, ATTR_MEASUREDVALUE, "MeasuredValue", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_MINMEASUREDVALUE, new ZclAttribute(ZclClusterType.PRESSURE_MEASUREMENT, ATTR_MINMEASUREDVALUE, "MinMeasuredValue", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MAXMEASUREDVALUE, new ZclAttribute(ZclClusterType.PRESSURE_MEASUREMENT, ATTR_MAXMEASUREDVALUE, "MaxMeasuredValue", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_TOLERANCE, new ZclAttribute(ZclClusterType.PRESSURE_MEASUREMENT, ATTR_TOLERANCE, "Tolerance", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_SCALEDVALUE, new ZclAttribute(ZclClusterType.PRESSURE_MEASUREMENT, ATTR_SCALEDVALUE, "ScaledValue", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_MINSCALEDVALUE, new ZclAttribute(ZclClusterType.PRESSURE_MEASUREMENT, ATTR_MINSCALEDVALUE, "MinScaledValue", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MAXSCALEDVALUE, new ZclAttribute(ZclClusterType.PRESSURE_MEASUREMENT, ATTR_MAXSCALEDVALUE, "MaxScaledValue", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_SCALEDTOLERANCE, new ZclAttribute(ZclClusterType.PRESSURE_MEASUREMENT, ATTR_SCALEDTOLERANCE, "ScaledTolerance", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_SCALE, new ZclAttribute(ZclClusterType.PRESSURE_MEASUREMENT, ATTR_SCALE, "Scale", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Pressure measurement cluster.
     *
     * @param zigbeeManager {@link ZigBeeNetworkManager}
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint}
     */
    public ZclPressureMeasurementCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeManager, zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * Get the <i>MeasuredValue</i> attribute [attribute ID <b>0</b>].
     * <p>
     * MeasuredValue represents the pressure in kPa as follows:-
     * <p>
     * MeasuredValue = 10 x Pressure
     * <p>
     * Where -3276.7 kPa <= Pressure <= 3276.7 kPa, corresponding to a
     * MeasuredValue in the range 0x8001 to 0x7fff.
     * <p>
     * Note:- The maximum resolution this format allows is 0.1 kPa.
     * <p>
     * A MeasuredValue of 0x8000 indicates that the pressure measurement is invalid.
     * MeasuredValue is updated continuously as new measurements are made.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMeasuredValueAsync() {
        return read(attributes.get(ATTR_MEASUREDVALUE));
    }


    /**
     * Synchronously get the <i>MeasuredValue</i> attribute [attribute ID <b>0</b>].
     * <p>
     * MeasuredValue represents the pressure in kPa as follows:-
     * <p>
     * MeasuredValue = 10 x Pressure
     * <p>
     * Where -3276.7 kPa <= Pressure <= 3276.7 kPa, corresponding to a
     * MeasuredValue in the range 0x8001 to 0x7fff.
     * <p>
     * Note:- The maximum resolution this format allows is 0.1 kPa.
     * <p>
     * A MeasuredValue of 0x8000 indicates that the pressure measurement is invalid.
     * MeasuredValue is updated continuously as new measurements are made.
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
    public Integer getMeasuredValue(final long refreshPeriod) {
        if (attributes.get(ATTR_MEASUREDVALUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MEASUREDVALUE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MEASUREDVALUE));
    }


    /**
     * Set reporting for the <i>MeasuredValue</i> attribute [attribute ID <b>0</b>].
     * <p>
     * MeasuredValue represents the pressure in kPa as follows:-
     * <p>
     * MeasuredValue = 10 x Pressure
     * <p>
     * Where -3276.7 kPa <= Pressure <= 3276.7 kPa, corresponding to a
     * MeasuredValue in the range 0x8001 to 0x7fff.
     * <p>
     * Note:- The maximum resolution this format allows is 0.1 kPa.
     * <p>
     * A MeasuredValue of 0x8000 indicates that the pressure measurement is invalid.
     * MeasuredValue is updated continuously as new measurements are made.
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
    public Future<CommandResult> setMeasuredValueReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_MEASUREDVALUE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>MinMeasuredValue</i> attribute [attribute ID <b>1</b>].
     * <p>
     * The MinMeasuredValue attribute indicates the minimum value of MeasuredValue
     * that can be measured. A value of 0x8000 means this attribute is not defined.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMinMeasuredValueAsync() {
        return read(attributes.get(ATTR_MINMEASUREDVALUE));
    }


    /**
     * Synchronously get the <i>MinMeasuredValue</i> attribute [attribute ID <b>1</b>].
     * <p>
     * The MinMeasuredValue attribute indicates the minimum value of MeasuredValue
     * that can be measured. A value of 0x8000 means this attribute is not defined.
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
    public Integer getMinMeasuredValue(final long refreshPeriod) {
        if (attributes.get(ATTR_MINMEASUREDVALUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MINMEASUREDVALUE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MINMEASUREDVALUE));
    }

    /**
     * Get the <i>MaxMeasuredValue</i> attribute [attribute ID <b>2</b>].
     * <p>
     * The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue
     * that can be measured. A value of 0x8000 means this attribute is not defined.
     * <p>
     * MaxMeasuredValue shall be greater than MinMeasuredValue.
     * <p>
     * MinMeasuredValue and MaxMeasuredValue define the range of the sensor.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMaxMeasuredValueAsync() {
        return read(attributes.get(ATTR_MAXMEASUREDVALUE));
    }


    /**
     * Synchronously get the <i>MaxMeasuredValue</i> attribute [attribute ID <b>2</b>].
     * <p>
     * The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue
     * that can be measured. A value of 0x8000 means this attribute is not defined.
     * <p>
     * MaxMeasuredValue shall be greater than MinMeasuredValue.
     * <p>
     * MinMeasuredValue and MaxMeasuredValue define the range of the sensor.
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
    public Integer getMaxMeasuredValue(final long refreshPeriod) {
        if (attributes.get(ATTR_MAXMEASUREDVALUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MAXMEASUREDVALUE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MAXMEASUREDVALUE));
    }


    /**
     * Set reporting for the <i>MaxMeasuredValue</i> attribute [attribute ID <b>2</b>].
     * <p>
     * The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue
     * that can be measured. A value of 0x8000 means this attribute is not defined.
     * <p>
     * MaxMeasuredValue shall be greater than MinMeasuredValue.
     * <p>
     * MinMeasuredValue and MaxMeasuredValue define the range of the sensor.
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
    public Future<CommandResult> setMaxMeasuredValueReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_MAXMEASUREDVALUE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Tolerance</i> attribute [attribute ID <b>3</b>].
     * <p>
     * The Tolerance attribute indicates the magnitude of the possible error that is
     * associated with MeasuredValue . The true value is located in the range
     * (MeasuredValue – Tolerance) to (MeasuredValue + Tolerance).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getToleranceAsync() {
        return read(attributes.get(ATTR_TOLERANCE));
    }


    /**
     * Synchronously get the <i>Tolerance</i> attribute [attribute ID <b>3</b>].
     * <p>
     * The Tolerance attribute indicates the magnitude of the possible error that is
     * associated with MeasuredValue . The true value is located in the range
     * (MeasuredValue – Tolerance) to (MeasuredValue + Tolerance).
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
    public Integer getTolerance(final long refreshPeriod) {
        if (attributes.get(ATTR_TOLERANCE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TOLERANCE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TOLERANCE));
    }

    /**
     * Get the <i>ScaledValue</i> attribute [attribute ID <b>16</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getScaledValueAsync() {
        return read(attributes.get(ATTR_SCALEDVALUE));
    }


    /**
     * Synchronously get the <i>ScaledValue</i> attribute [attribute ID <b>16</b>].
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
    public Integer getScaledValue(final long refreshPeriod) {
        if (attributes.get(ATTR_SCALEDVALUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_SCALEDVALUE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_SCALEDVALUE));
    }


    /**
     * Set reporting for the <i>ScaledValue</i> attribute [attribute ID <b>16</b>].
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
    public Future<CommandResult> setScaledValueReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_SCALEDVALUE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>MinScaledValue</i> attribute [attribute ID <b>17</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMinScaledValueAsync() {
        return read(attributes.get(ATTR_MINSCALEDVALUE));
    }


    /**
     * Synchronously get the <i>MinScaledValue</i> attribute [attribute ID <b>17</b>].
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
    public Integer getMinScaledValue(final long refreshPeriod) {
        if (attributes.get(ATTR_MINSCALEDVALUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MINSCALEDVALUE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MINSCALEDVALUE));
    }

    /**
     * Get the <i>MaxScaledValue</i> attribute [attribute ID <b>18</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMaxScaledValueAsync() {
        return read(attributes.get(ATTR_MAXSCALEDVALUE));
    }


    /**
     * Synchronously get the <i>MaxScaledValue</i> attribute [attribute ID <b>18</b>].
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
    public Integer getMaxScaledValue(final long refreshPeriod) {
        if (attributes.get(ATTR_MAXSCALEDVALUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MAXSCALEDVALUE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MAXSCALEDVALUE));
    }

    /**
     * Get the <i>ScaledTolerance</i> attribute [attribute ID <b>19</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getScaledToleranceAsync() {
        return read(attributes.get(ATTR_SCALEDTOLERANCE));
    }


    /**
     * Synchronously get the <i>ScaledTolerance</i> attribute [attribute ID <b>19</b>].
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
    public Integer getScaledTolerance(final long refreshPeriod) {
        if (attributes.get(ATTR_SCALEDTOLERANCE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_SCALEDTOLERANCE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_SCALEDTOLERANCE));
    }


    /**
     * Set reporting for the <i>ScaledTolerance</i> attribute [attribute ID <b>19</b>].
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
    public Future<CommandResult> setScaledToleranceReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_SCALEDTOLERANCE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Scale</i> attribute [attribute ID <b>20</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getScaleAsync() {
        return read(attributes.get(ATTR_SCALE));
    }


    /**
     * Synchronously get the <i>Scale</i> attribute [attribute ID <b>20</b>].
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
    public Integer getScale(final long refreshPeriod) {
        if (attributes.get(ATTR_SCALE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_SCALE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_SCALE));
    }
}
