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
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Pressure Measurement</b> cluster implementation (<i>Cluster ID 0x0403</i>).
 * <p>
 * The cluster provides an interface to pressure measurement functionality, including
 * configuration and provision of notifications of pressure measurements.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-26T21:33:25Z")
public class ZclPressureMeasurementCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0403;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Pressure Measurement";

    // Attribute constants
    /**
     * MeasuredValue represents the pressure in kPa as follows:-
     * <p>
     * MeasuredValue = 10 x Pressure
     * <p>
     * Where -3276.7 kPa <= Pressure <= 3276.7 kPa, corresponding to a MeasuredValue in the
     * range 0x8001 to 0x7fff.
     * <p>
     * Note:- The maximum resolution this format allows is 0.1 kPa.
     * <p>
     * A MeasuredValue of 0x8000 indicates that the pressure measurement is invalid.
     * MeasuredValue is updated continuously as new measurements are made.
     */
    public static final int ATTR_MEASUREDVALUE = 0x0000;
    /**
     * The MinMeasuredValue attribute indicates the minimum value of MeasuredValue that can
     * be measured. A value of 0x8000 means this attribute is not defined.
     */
    public static final int ATTR_MINMEASUREDVALUE = 0x0001;
    /**
     * The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue that can
     * be measured. A value of 0x8000 means this attribute is not defined.
     * <p>
     * MaxMeasuredValue shall be greater than MinMeasuredValue.
     * <p>
     * MinMeasuredValue and MaxMeasuredValue define the range of the sensor.
     */
    public static final int ATTR_MAXMEASUREDVALUE = 0x0002;
    /**
     * The Tolerance attribute indicates the magnitude of the possible error that is
     * associated with MeasuredValue . The true value is located in the range (MeasuredValue –
     * Tolerance) to (MeasuredValue + Tolerance).
     */
    public static final int ATTR_TOLERANCE = 0x0003;
    /**
     * ScaledValue represents the pressure in Pascals as follows: ScaledValue = 10Scale x
     * Pressure in Pa
     */
    public static final int ATTR_SCALEDVALUE = 0x0010;
    /**
     * The MinScaledValue attribute indicates the minimum value of ScaledValue that can be
     * measured. A value of 0x8000 means this attribute is not defined
     */
    public static final int ATTR_MINSCALEDVALUE = 0x0011;
    /**
     * The MaxScaledValue attribute indicates the maximum value of ScaledValue that can be
     * measured. A value of 0x8000 means this attribute is not defined.
     */
    public static final int ATTR_MAXSCALEDVALUE = 0x0012;
    /**
     * The ScaledTolerance attribute indicates the magnitude of the possible error that is
     * associated with ScaledValue. The true value is located in the range (ScaledValue –
     * ScaledTolerance) to (ScaledValue + ScaledTolerance).
     */
    public static final int ATTR_SCALEDTOLERANCE = 0x0013;
    /**
     * The Scale attribute indicates the base 10 exponent used to obtain ScaledValue.
     */
    public static final int ATTR_SCALE = 0x0014;

    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<>(9);

        attributeMap.put(ATTR_MEASUREDVALUE, new ZclAttribute(ZclClusterType.PRESSURE_MEASUREMENT, ATTR_MEASUREDVALUE, "Measured Value", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_MINMEASUREDVALUE, new ZclAttribute(ZclClusterType.PRESSURE_MEASUREMENT, ATTR_MINMEASUREDVALUE, "Min Measured Value", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MAXMEASUREDVALUE, new ZclAttribute(ZclClusterType.PRESSURE_MEASUREMENT, ATTR_MAXMEASUREDVALUE, "Max Measured Value", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_TOLERANCE, new ZclAttribute(ZclClusterType.PRESSURE_MEASUREMENT, ATTR_TOLERANCE, "Tolerance", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_SCALEDVALUE, new ZclAttribute(ZclClusterType.PRESSURE_MEASUREMENT, ATTR_SCALEDVALUE, "Scaled Value", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_MINSCALEDVALUE, new ZclAttribute(ZclClusterType.PRESSURE_MEASUREMENT, ATTR_MINSCALEDVALUE, "Min Scaled Value", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MAXSCALEDVALUE, new ZclAttribute(ZclClusterType.PRESSURE_MEASUREMENT, ATTR_MAXSCALEDVALUE, "Max Scaled Value", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_SCALEDTOLERANCE, new ZclAttribute(ZclClusterType.PRESSURE_MEASUREMENT, ATTR_SCALEDTOLERANCE, "Scaled Tolerance", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_SCALE, new ZclAttribute(ZclClusterType.PRESSURE_MEASUREMENT, ATTR_SCALE, "Scale", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Pressure Measurement cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclPressureMeasurementCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>Measured Value</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * MeasuredValue represents the pressure in kPa as follows:-
     * <p>
     * MeasuredValue = 10 x Pressure
     * <p>
     * Where -3276.7 kPa <= Pressure <= 3276.7 kPa, corresponding to a MeasuredValue in the
     * range 0x8001 to 0x7fff.
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
     * Synchronously get the <i>Measured Value</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * MeasuredValue represents the pressure in kPa as follows:-
     * <p>
     * MeasuredValue = 10 x Pressure
     * <p>
     * Where -3276.7 kPa <= Pressure <= 3276.7 kPa, corresponding to a MeasuredValue in the
     * range 0x8001 to 0x7fff.
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
     * Set reporting for the <i>Measured Value</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * MeasuredValue represents the pressure in kPa as follows:-
     * <p>
     * MeasuredValue = 10 x Pressure
     * <p>
     * Where -3276.7 kPa <= Pressure <= 3276.7 kPa, corresponding to a MeasuredValue in the
     * range 0x8001 to 0x7fff.
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
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMeasuredValueReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_MEASUREDVALUE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Min Measured Value</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The MinMeasuredValue attribute indicates the minimum value of MeasuredValue that can
     * be measured. A value of 0x8000 means this attribute is not defined.
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
     * Synchronously get the <i>Min Measured Value</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The MinMeasuredValue attribute indicates the minimum value of MeasuredValue that can
     * be measured. A value of 0x8000 means this attribute is not defined.
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
     * Set reporting for the <i>Min Measured Value</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The MinMeasuredValue attribute indicates the minimum value of MeasuredValue that can
     * be measured. A value of 0x8000 means this attribute is not defined.
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
    public Future<CommandResult> setMinMeasuredValueReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_MINMEASUREDVALUE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Max Measured Value</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue that can
     * be measured. A value of 0x8000 means this attribute is not defined.
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
     * Synchronously get the <i>Max Measured Value</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue that can
     * be measured. A value of 0x8000 means this attribute is not defined.
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
     * Set reporting for the <i>Max Measured Value</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue that can
     * be measured. A value of 0x8000 means this attribute is not defined.
     * <p>
     * MaxMeasuredValue shall be greater than MinMeasuredValue.
     * <p>
     * MinMeasuredValue and MaxMeasuredValue define the range of the sensor.
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
    public Future<CommandResult> setMaxMeasuredValueReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_MAXMEASUREDVALUE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Tolerance</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The Tolerance attribute indicates the magnitude of the possible error that is
     * associated with MeasuredValue . The true value is located in the range (MeasuredValue –
     * Tolerance) to (MeasuredValue + Tolerance).
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
     * Synchronously get the <i>Tolerance</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The Tolerance attribute indicates the magnitude of the possible error that is
     * associated with MeasuredValue . The true value is located in the range (MeasuredValue –
     * Tolerance) to (MeasuredValue + Tolerance).
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
     * Get the <i>Scaled Value</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * ScaledValue represents the pressure in Pascals as follows: ScaledValue = 10Scale x
     * Pressure in Pa
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
     * Synchronously get the <i>Scaled Value</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * ScaledValue represents the pressure in Pascals as follows: ScaledValue = 10Scale x
     * Pressure in Pa
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
     * Get the <i>Min Scaled Value</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The MinScaledValue attribute indicates the minimum value of ScaledValue that can be
     * measured. A value of 0x8000 means this attribute is not defined
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
     * Synchronously get the <i>Min Scaled Value</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The MinScaledValue attribute indicates the minimum value of ScaledValue that can be
     * measured. A value of 0x8000 means this attribute is not defined
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
     * Get the <i>Max Scaled Value</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The MaxScaledValue attribute indicates the maximum value of ScaledValue that can be
     * measured. A value of 0x8000 means this attribute is not defined.
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
     * Synchronously get the <i>Max Scaled Value</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The MaxScaledValue attribute indicates the maximum value of ScaledValue that can be
     * measured. A value of 0x8000 means this attribute is not defined.
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
     * Get the <i>Scaled Tolerance</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The ScaledTolerance attribute indicates the magnitude of the possible error that is
     * associated with ScaledValue. The true value is located in the range (ScaledValue –
     * ScaledTolerance) to (ScaledValue + ScaledTolerance).
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
     * Synchronously get the <i>Scaled Tolerance</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The ScaledTolerance attribute indicates the magnitude of the possible error that is
     * associated with ScaledValue. The true value is located in the range (ScaledValue –
     * ScaledTolerance) to (ScaledValue + ScaledTolerance).
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
     * Get the <i>Scale</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The Scale attribute indicates the base 10 exponent used to obtain ScaledValue.
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
     * Synchronously get the <i>Scale</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The Scale attribute indicates the base 10 exponent used to obtain ScaledValue.
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
