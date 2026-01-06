/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.Future;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>PM2.5 Measurement</b> cluster implementation (<i>Cluster ID 0x042A</i>).
 * <p>
 * The cluster provides an interface for measurement of Particulate Matter 2.5 microns or less
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-07-16T20:26:44Z")
public class ZclPm25MeasurementCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x042A;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "PM2.5 Measurement";

    // Attribute constants
    /**
     * MeasuredValue represents the concentration as a fraction of 1 (one).
     * <p>
     * A value of NaN indicates that the concentration measurement is unknown or outside the
     * valid range.
     * <p>
     * MinMeasuredValue and MaxMeasuredValue define the valid range for MeasuredValue.
     * <p>
     * MeasuredValue is updated continuously as new measurements are made.
     */
    public static final int ATTR_MEASUREDVALUE = 0x0000;
    /**
     * The MinMeasuredValue attribute indicates the minimum value of MeasuredValue that can
     * be measured. A value of NaN means this attribute is not defined.
     */
    public static final int ATTR_MINMEASUREDVALUE = 0x0001;
    /**
     * The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue that can
     * be measured. A value of NaN means this attribute is not defined.
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

    @Override
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        Map<Integer, ZclAttribute> attributeMap = super.initializeClientAttributes();

        return attributeMap;
    }

    @Override
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        Map<Integer, ZclAttribute> attributeMap = super.initializeServerAttributes();

        attributeMap.put(ATTR_MEASUREDVALUE, new ZclAttribute(this, ATTR_MEASUREDVALUE, "Measured Value", ZclDataType.FLOAT_32_BIT, true, true, false, true));
        attributeMap.put(ATTR_MINMEASUREDVALUE, new ZclAttribute(this, ATTR_MINMEASUREDVALUE, "Min Measured Value", ZclDataType.FLOAT_32_BIT, true, true, false, false));
        attributeMap.put(ATTR_MAXMEASUREDVALUE, new ZclAttribute(this, ATTR_MAXMEASUREDVALUE, "Max Measured Value", ZclDataType.FLOAT_32_BIT, true, true, false, true));
        attributeMap.put(ATTR_TOLERANCE, new ZclAttribute(this, ATTR_TOLERANCE, "Tolerance", ZclDataType.FLOAT_32_BIT, false, true, false, false));

        return attributeMap;
    }


    /**
     * Default constructor to create a PM2.5 Measurement cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclPm25MeasurementCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>Measured Value</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * MeasuredValue represents the concentration as a fraction of 1 (one).
     * <p>
     * A value of NaN indicates that the concentration measurement is unknown or outside the
     * valid range.
     * <p>
     * MinMeasuredValue and MaxMeasuredValue define the valid range for MeasuredValue.
     * <p>
     * MeasuredValue is updated continuously as new measurements are made.
     * <p>
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMeasuredValueAsync() {
        return read(serverAttributes.get(ATTR_MEASUREDVALUE));
    }

    /**
     * Synchronously get the <i>Measured Value</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * MeasuredValue represents the concentration as a fraction of 1 (one).
     * <p>
     * A value of NaN indicates that the concentration measurement is unknown or outside the
     * valid range.
     * <p>
     * MinMeasuredValue and MaxMeasuredValue define the valid range for MeasuredValue.
     * <p>
     * MeasuredValue is updated continuously as new measurements are made.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Double} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Double getMeasuredValue(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MEASUREDVALUE).isLastValueCurrent(refreshPeriod)) {
            return (Double) serverAttributes.get(ATTR_MEASUREDVALUE).getLastValue();
        }

        return (Double) readSync(serverAttributes.get(ATTR_MEASUREDVALUE));
    }

    /**
     * Set reporting for the <i>Measured Value</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * MeasuredValue represents the concentration as a fraction of 1 (one).
     * <p>
     * A value of NaN indicates that the concentration measurement is unknown or outside the
     * valid range.
     * <p>
     * MinMeasuredValue and MaxMeasuredValue define the valid range for MeasuredValue.
     * <p>
     * MeasuredValue is updated continuously as new measurements are made.
     * <p>
     * The attribute is of type {@link Double}.
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
    public Future<CommandResult> setMeasuredValueReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MEASUREDVALUE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Min Measured Value</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The MinMeasuredValue attribute indicates the minimum value of MeasuredValue that can
     * be measured. A value of NaN means this attribute is not defined.
     * <p>
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMinMeasuredValueAsync() {
        return read(serverAttributes.get(ATTR_MINMEASUREDVALUE));
    }

    /**
     * Synchronously get the <i>Min Measured Value</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The MinMeasuredValue attribute indicates the minimum value of MeasuredValue that can
     * be measured. A value of NaN means this attribute is not defined.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Double} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Double getMinMeasuredValue(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MINMEASUREDVALUE).isLastValueCurrent(refreshPeriod)) {
            return (Double) serverAttributes.get(ATTR_MINMEASUREDVALUE).getLastValue();
        }

        return (Double) readSync(serverAttributes.get(ATTR_MINMEASUREDVALUE));
    }

    /**
     * Set reporting for the <i>Min Measured Value</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The MinMeasuredValue attribute indicates the minimum value of MeasuredValue that can
     * be measured. A value of NaN means this attribute is not defined.
     * <p>
     * The attribute is of type {@link Double}.
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
    public Future<CommandResult> setMinMeasuredValueReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MINMEASUREDVALUE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Max Measured Value</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue that can
     * be measured. A value of NaN means this attribute is not defined.
     * <p>
     * MaxMeasuredValue shall be greater than MinMeasuredValue.
     * <p>
     * MinMeasuredValue and MaxMeasuredValue define the range of the sensor.
     * <p>
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMaxMeasuredValueAsync() {
        return read(serverAttributes.get(ATTR_MAXMEASUREDVALUE));
    }

    /**
     * Synchronously get the <i>Max Measured Value</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue that can
     * be measured. A value of NaN means this attribute is not defined.
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
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Double} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Double getMaxMeasuredValue(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MAXMEASUREDVALUE).isLastValueCurrent(refreshPeriod)) {
            return (Double) serverAttributes.get(ATTR_MAXMEASUREDVALUE).getLastValue();
        }

        return (Double) readSync(serverAttributes.get(ATTR_MAXMEASUREDVALUE));
    }

    /**
     * Set reporting for the <i>Max Measured Value</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue that can
     * be measured. A value of NaN means this attribute is not defined.
     * <p>
     * MaxMeasuredValue shall be greater than MinMeasuredValue.
     * <p>
     * MinMeasuredValue and MaxMeasuredValue define the range of the sensor.
     * <p>
     * The attribute is of type {@link Double}.
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
    public Future<CommandResult> setMaxMeasuredValueReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MAXMEASUREDVALUE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Tolerance</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The Tolerance attribute indicates the magnitude of the possible error that is
     * associated with MeasuredValue . The true value is located in the range (MeasuredValue –
     * Tolerance) to (MeasuredValue + Tolerance).
     * <p>
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getToleranceAsync() {
        return read(serverAttributes.get(ATTR_TOLERANCE));
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
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Double} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Double getTolerance(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_TOLERANCE).isLastValueCurrent(refreshPeriod)) {
            return (Double) serverAttributes.get(ATTR_TOLERANCE).getLastValue();
        }

        return (Double) readSync(serverAttributes.get(ATTR_TOLERANCE));
    }
}
