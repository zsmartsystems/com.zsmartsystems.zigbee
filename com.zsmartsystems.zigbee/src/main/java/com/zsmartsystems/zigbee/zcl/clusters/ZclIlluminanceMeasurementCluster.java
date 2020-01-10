/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
 * <b>Illuminance Measurement</b> cluster implementation (<i>Cluster ID 0x0400</i>).
 * <p>
 * The cluster provides an interface to illuminance measurement functionality, including
 * configuration and provision of notifications of illuminance measurements.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-10-04T18:21:10Z")
public class ZclIlluminanceMeasurementCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0400;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Illuminance Measurement";

    // Attribute constants
    /**
     * MeasuredValue represents the Illuminance in Lux (symbol lx) as follows:-
     * <p>
     * MeasuredValue = 10,000 x log10 Illuminance + 1
     * <p>
     * Where 1 lx <= Illuminance <=3.576 Mlx, corresponding to a MeasuredValue in the range 1 to
     * 0xfffe.
     * <p>
     * The following special values of MeasuredValue apply. <li>0x0000 indicates a value of
     * Illuminance that is too low to be measured.</li> <li>0xffff indicates that the
     * Illuminance measurement is invalid.</li>
     */
    public static final int ATTR_MEASUREDVALUE = 0x0000;
    /**
     * The MinMeasuredValue attribute indicates the minimum value of MeasuredValue that can
     * be measured. A value of 0xffff indicates that this attribute is not defined.
     */
    public static final int ATTR_MINMEASUREDVALUE = 0x0001;
    /**
     * The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue that can
     * be measured. A value of 0xffff indicates that this attribute is not defined.
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
     * The LightSensorType attribute specifies the electronic type of the light sensor.
     */
    public static final int ATTR_LIGHTSENSORTYPE = 0x0004;

    @Override
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentSkipListMap<>();

        return attributeMap;
    }

    @Override
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentSkipListMap<>();

        attributeMap.put(ATTR_MEASUREDVALUE, new ZclAttribute(this, ATTR_MEASUREDVALUE, "Measured Value", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_MINMEASUREDVALUE, new ZclAttribute(this, ATTR_MINMEASUREDVALUE, "Min Measured Value", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MAXMEASUREDVALUE, new ZclAttribute(this, ATTR_MAXMEASUREDVALUE, "Max Measured Value", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_TOLERANCE, new ZclAttribute(this, ATTR_TOLERANCE, "Tolerance", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_LIGHTSENSORTYPE, new ZclAttribute(this, ATTR_LIGHTSENSORTYPE, "Light Sensor Type", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Illuminance Measurement cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclIlluminanceMeasurementCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>Measured Value</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * MeasuredValue represents the Illuminance in Lux (symbol lx) as follows:-
     * <p>
     * MeasuredValue = 10,000 x log10 Illuminance + 1
     * <p>
     * Where 1 lx <= Illuminance <=3.576 Mlx, corresponding to a MeasuredValue in the range 1 to
     * 0xfffe.
     * <p>
     * The following special values of MeasuredValue apply. <li>0x0000 indicates a value of
     * Illuminance that is too low to be measured.</li> <li>0xffff indicates that the
     * Illuminance measurement is invalid.</li>
     * <p>
     * The attribute is of type {@link Integer}.
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
     * MeasuredValue represents the Illuminance in Lux (symbol lx) as follows:-
     * <p>
     * MeasuredValue = 10,000 x log10 Illuminance + 1
     * <p>
     * Where 1 lx <= Illuminance <=3.576 Mlx, corresponding to a MeasuredValue in the range 1 to
     * 0xfffe.
     * <p>
     * The following special values of MeasuredValue apply. <li>0x0000 indicates a value of
     * Illuminance that is too low to be measured.</li> <li>0xffff indicates that the
     * Illuminance measurement is invalid.</li>
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
    public Integer getMeasuredValue(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MEASUREDVALUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MEASUREDVALUE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MEASUREDVALUE));
    }

    /**
     * Set reporting for the <i>Measured Value</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * MeasuredValue represents the Illuminance in Lux (symbol lx) as follows:-
     * <p>
     * MeasuredValue = 10,000 x log10 Illuminance + 1
     * <p>
     * Where 1 lx <= Illuminance <=3.576 Mlx, corresponding to a MeasuredValue in the range 1 to
     * 0xfffe.
     * <p>
     * The following special values of MeasuredValue apply. <li>0x0000 indicates a value of
     * Illuminance that is too low to be measured.</li> <li>0xffff indicates that the
     * Illuminance measurement is invalid.</li>
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
    public Future<CommandResult> setMeasuredValueReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MEASUREDVALUE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Min Measured Value</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The MinMeasuredValue attribute indicates the minimum value of MeasuredValue that can
     * be measured. A value of 0xffff indicates that this attribute is not defined.
     * <p>
     * The attribute is of type {@link Integer}.
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
     * be measured. A value of 0xffff indicates that this attribute is not defined.
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
    public Integer getMinMeasuredValue(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MINMEASUREDVALUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MINMEASUREDVALUE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MINMEASUREDVALUE));
    }

    /**
     * Set reporting for the <i>Min Measured Value</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The MinMeasuredValue attribute indicates the minimum value of MeasuredValue that can
     * be measured. A value of 0xffff indicates that this attribute is not defined.
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
    public Future<CommandResult> setMinMeasuredValueReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MINMEASUREDVALUE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Max Measured Value</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue that can
     * be measured. A value of 0xffff indicates that this attribute is not defined.
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
     * be measured. A value of 0xffff indicates that this attribute is not defined.
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getMaxMeasuredValue(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MAXMEASUREDVALUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MAXMEASUREDVALUE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MAXMEASUREDVALUE));
    }

    /**
     * Set reporting for the <i>Max Measured Value</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue that can
     * be measured. A value of 0xffff indicates that this attribute is not defined.
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
     * The attribute is of type {@link Integer}.
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
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getTolerance(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_TOLERANCE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_TOLERANCE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_TOLERANCE));
    }

    /**
     * Get the <i>Light Sensor Type</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The LightSensorType attribute specifies the electronic type of the light sensor.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getLightSensorTypeAsync() {
        return read(serverAttributes.get(ATTR_LIGHTSENSORTYPE));
    }

    /**
     * Synchronously get the <i>Light Sensor Type</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The LightSensorType attribute specifies the electronic type of the light sensor.
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
    public Integer getLightSensorType(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_LIGHTSENSORTYPE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_LIGHTSENSORTYPE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_LIGHTSENSORTYPE));
    }
}
