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
 * <b>Illuminance measurement</b> cluster implementation (<i>Cluster ID 0x0400</i>).
 * <p>
 * The cluster provides an interface to illuminance measurement functionality,
 * including configuration and provision of notifications of illuminance
 * measurements.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclIlluminanceMeasurementCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0400;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Illuminance measurement";

    // Attribute constants
    /**
     * MeasuredValue represents the Illuminance in Lux (symbol lx) as follows:-
     * <p>
     * MeasuredValue = 10,000 x log10 Illuminance + 1
     * <p>
     * Where 1 lx <= Illuminance <=3.576 Mlx, corresponding to a MeasuredValue in
     * the range 1 to 0xfffe.
     * <p>
     * The following special values of MeasuredValue apply.
     * <li>0x0000 indicates a value of Illuminance that is too low to be measured.</li>
     * <li>0xffff indicates that the Illuminance measurement is invalid.</li>
     */
    public static final int ATTR_MEASUREDVALUE = 0x0000;
    /**
     * The MinMeasuredValue attribute indicates the minimum value of MeasuredValue
     * that can be measured. A value of 0xffff indicates that this attribute is not defined.
     */
    public static final int ATTR_MINMEASUREDVALUE = 0x0001;
    /**
     * The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue
     * that can be measured. A value of 0xffff indicates that this attribute is not defined.
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
     * The LightSensorType attribute specifies the electronic type of the light sensor.
     */
    public static final int ATTR_LIGHTSENSORTYPE = 0x0004;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(5);

        attributeMap.put(ATTR_MEASUREDVALUE, new ZclAttribute(ZclClusterType.ILLUMINANCE_MEASUREMENT, ATTR_MEASUREDVALUE, "MeasuredValue", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_MINMEASUREDVALUE, new ZclAttribute(ZclClusterType.ILLUMINANCE_MEASUREMENT, ATTR_MINMEASUREDVALUE, "MinMeasuredValue", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MAXMEASUREDVALUE, new ZclAttribute(ZclClusterType.ILLUMINANCE_MEASUREMENT, ATTR_MAXMEASUREDVALUE, "MaxMeasuredValue", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_TOLERANCE, new ZclAttribute(ZclClusterType.ILLUMINANCE_MEASUREMENT, ATTR_TOLERANCE, "Tolerance", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_LIGHTSENSORTYPE, new ZclAttribute(ZclClusterType.ILLUMINANCE_MEASUREMENT, ATTR_LIGHTSENSORTYPE, "LightSensorType", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Illuminance measurement cluster.
     *
     * @param zigbeeManager {@link ZigBeeNetworkManager}
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint}
     */
    public ZclIlluminanceMeasurementCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeManager, zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * Get the <i>MeasuredValue</i> attribute [attribute ID <b>0</b>].
     * <p>
     * MeasuredValue represents the Illuminance in Lux (symbol lx) as follows:-
     * <p>
     * MeasuredValue = 10,000 x log10 Illuminance + 1
     * <p>
     * Where 1 lx <= Illuminance <=3.576 Mlx, corresponding to a MeasuredValue in
     * the range 1 to 0xfffe.
     * <p>
     * The following special values of MeasuredValue apply.
     * <li>0x0000 indicates a value of Illuminance that is too low to be measured.</li>
     * <li>0xffff indicates that the Illuminance measurement is invalid.</li>
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
     * MeasuredValue represents the Illuminance in Lux (symbol lx) as follows:-
     * <p>
     * MeasuredValue = 10,000 x log10 Illuminance + 1
     * <p>
     * Where 1 lx <= Illuminance <=3.576 Mlx, corresponding to a MeasuredValue in
     * the range 1 to 0xfffe.
     * <p>
     * The following special values of MeasuredValue apply.
     * <li>0x0000 indicates a value of Illuminance that is too low to be measured.</li>
     * <li>0xffff indicates that the Illuminance measurement is invalid.</li>
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
     * MeasuredValue represents the Illuminance in Lux (symbol lx) as follows:-
     * <p>
     * MeasuredValue = 10,000 x log10 Illuminance + 1
     * <p>
     * Where 1 lx <= Illuminance <=3.576 Mlx, corresponding to a MeasuredValue in
     * the range 1 to 0xfffe.
     * <p>
     * The following special values of MeasuredValue apply.
     * <li>0x0000 indicates a value of Illuminance that is too low to be measured.</li>
     * <li>0xffff indicates that the Illuminance measurement is invalid.</li>
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
     * that can be measured. A value of 0xffff indicates that this attribute is not defined.
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
     * that can be measured. A value of 0xffff indicates that this attribute is not defined.
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
     * that can be measured. A value of 0xffff indicates that this attribute is not defined.
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
     * that can be measured. A value of 0xffff indicates that this attribute is not defined.
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
     * Set reporting for the <i>Tolerance</i> attribute [attribute ID <b>3</b>].
     * <p>
     * The Tolerance attribute indicates the magnitude of the possible error that is
     * associated with MeasuredValue . The true value is located in the range
     * (MeasuredValue – Tolerance) to (MeasuredValue + Tolerance).
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
    public Future<CommandResult> setToleranceReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_TOLERANCE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>LightSensorType</i> attribute [attribute ID <b>4</b>].
     * <p>
     * The LightSensorType attribute specifies the electronic type of the light sensor.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLightSensorTypeAsync() {
        return read(attributes.get(ATTR_LIGHTSENSORTYPE));
    }


    /**
     * Synchronously get the <i>LightSensorType</i> attribute [attribute ID <b>4</b>].
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
     */
    public Integer getLightSensorType(final long refreshPeriod) {
        if (attributes.get(ATTR_LIGHTSENSORTYPE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_LIGHTSENSORTYPE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_LIGHTSENSORTYPE));
    }
}
