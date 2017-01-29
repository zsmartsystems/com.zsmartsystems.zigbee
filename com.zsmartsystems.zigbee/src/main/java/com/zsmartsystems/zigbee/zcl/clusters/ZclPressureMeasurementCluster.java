package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.HashMap;
import java.util.Map;
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
    // Cluster ID
    public static final int CLUSTER_ID = 0x0403;

    // Cluster Name
    public static final String CLUSTER_NAME = "Pressure measurement";

    // Attribute constants
    public static final int ATTR_MEASUREDVALUE = 0x0000;
    public static final int ATTR_MINMEASUREDVALUE = 0x0001;
    public static final int ATTR_MAXMEASUREDVALUE = 0x0002;
    public static final int ATTR_TOLERANCE = 0x0003;
    public static final int ATTR_SCALEDVALUE = 0x0010;
    public static final int ATTR_MINSCALEDVALUE = 0x0011;
    public static final int ATTR_MAXSCALEDVALUE = 0x0012;
    public static final int ATTR_SCALEDTOLERANCE = 0x0013;
    public static final int ATTR_SCALE = 0x0014;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>(9);

        attributeMap.put(ATTR_MEASUREDVALUE, new ZclAttribute(0, "MeasuredValue", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_MINMEASUREDVALUE, new ZclAttribute(1, "MinMeasuredValue", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MAXMEASUREDVALUE, new ZclAttribute(2, "MaxMeasuredValue", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_TOLERANCE, new ZclAttribute(3, "Tolerance", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_SCALEDVALUE, new ZclAttribute(16, "ScaledValue", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_MINSCALEDVALUE, new ZclAttribute(17, "MinScaledValue", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MAXSCALEDVALUE, new ZclAttribute(18, "MaxScaledValue", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_SCALEDTOLERANCE, new ZclAttribute(19, "ScaledTolerance", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_SCALE, new ZclAttribute(20, "Scale", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor.
     */
    public ZclPressureMeasurementCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {
        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * <p>
     * Get the <i>MeasuredValue</i> attribute.
     * <p>
     * <p>
     * MeasuredValue represents the pressure in kPa as follows:-
     * <br>
     * MeasuredValue = 10 x Pressure
     * <br>
     * Where -3276.7 kPa <= Pressure <= 3276.7 kPa, corresponding to a
     * MeasuredValue in the range 0x8001 to 0x7fff.
     * <br>
     * Note:- The maximum resolution this format allows is 0.1 kPa.
     * <br>
     * A MeasuredValue of 0x8000 indicates that the pressure measurement is invalid.
     * MeasuredValue is updated continuously as new measurements are made.
     * <p>
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
     * <p>
     * Synchronously get the <i>MeasuredValue</i> attribute.
     * <p>
     * <p>
     * MeasuredValue represents the pressure in kPa as follows:-
     * <br>
     * MeasuredValue = 10 x Pressure
     * <br>
     * Where -3276.7 kPa <= Pressure <= 3276.7 kPa, corresponding to a
     * MeasuredValue in the range 0x8001 to 0x7fff.
     * <br>
     * Note:- The maximum resolution this format allows is 0.1 kPa.
     * <br>
     * A MeasuredValue of 0x8000 indicates that the pressure measurement is invalid.
     * MeasuredValue is updated continuously as new measurements are made.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMeasuredValue() {
        return (Integer) readSync(attributes.get(ATTR_MEASUREDVALUE));
    }


    /**
     * <p>
     * Configure reporting for the <i>MeasuredValue</i> attribute.
     * <p>
     * <p>
     * MeasuredValue represents the pressure in kPa as follows:-
     * <br>
     * MeasuredValue = 10 x Pressure
     * <br>
     * Where -3276.7 kPa <= Pressure <= 3276.7 kPa, corresponding to a
     * MeasuredValue in the range 0x8001 to 0x7fff.
     * <br>
     * Note:- The maximum resolution this format allows is 0.1 kPa.
     * <br>
     * A MeasuredValue of 0x8000 indicates that the pressure measurement is invalid.
     * MeasuredValue is updated continuously as new measurements are made.
     * <p>
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
    public Future<CommandResult> configMeasuredValueReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return report(ATTR_MEASUREDVALUE, minInterval, maxInterval, reportableChange);
    }

    /**
     * <p>
     * Get the <i>MinMeasuredValue</i> attribute.
     * <p>
     * <p>
     * <br>
     * The MinMeasuredValue attribute indicates the minimum value of MeasuredValue
     * that can be measured. A value of 0x8000 means this attribute is not defined.
     * <p>
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
     * <p>
     * Synchronously get the <i>MinMeasuredValue</i> attribute.
     * <p>
     * <p>
     * <br>
     * The MinMeasuredValue attribute indicates the minimum value of MeasuredValue
     * that can be measured. A value of 0x8000 means this attribute is not defined.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMinMeasuredValue() {
        return (Integer) readSync(attributes.get(ATTR_MINMEASUREDVALUE));
    }

    /**
     * <p>
     * Get the <i>MaxMeasuredValue</i> attribute.
     * <p>
     * <p>
     * <br>
     * The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue
     * that can be measured. A value of 0x8000 means this attribute is not defined.
     * <br>
     * MaxMeasuredValue shall be greater than MinMeasuredValue.
     * <br>
     * MinMeasuredValue and MaxMeasuredValue define the range of the sensor.
     * <p>
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
     * <p>
     * Synchronously get the <i>MaxMeasuredValue</i> attribute.
     * <p>
     * <p>
     * <br>
     * The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue
     * that can be measured. A value of 0x8000 means this attribute is not defined.
     * <br>
     * MaxMeasuredValue shall be greater than MinMeasuredValue.
     * <br>
     * MinMeasuredValue and MaxMeasuredValue define the range of the sensor.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMaxMeasuredValue() {
        return (Integer) readSync(attributes.get(ATTR_MAXMEASUREDVALUE));
    }


    /**
     * <p>
     * Configure reporting for the <i>MaxMeasuredValue</i> attribute.
     * <p>
     * <p>
     * <br>
     * The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue
     * that can be measured. A value of 0x8000 means this attribute is not defined.
     * <br>
     * MaxMeasuredValue shall be greater than MinMeasuredValue.
     * <br>
     * MinMeasuredValue and MaxMeasuredValue define the range of the sensor.
     * <p>
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
    public Future<CommandResult> configMaxMeasuredValueReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return report(ATTR_MAXMEASUREDVALUE, minInterval, maxInterval, reportableChange);
    }

    /**
     * <p>
     * Get the <i>Tolerance</i> attribute.
     * <p>
     * <p>
     * <br>
     * The Tolerance attribute indicates the magnitude of the possible error that is
     * associated with MeasuredValue . The true value is located in the range
     * (MeasuredValue – Tolerance) to (MeasuredValue + Tolerance).
     * <p>
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
     * <p>
     * Synchronously get the <i>Tolerance</i> attribute.
     * <p>
     * <p>
     * <br>
     * The Tolerance attribute indicates the magnitude of the possible error that is
     * associated with MeasuredValue . The true value is located in the range
     * (MeasuredValue – Tolerance) to (MeasuredValue + Tolerance).
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTolerance() {
        return (Integer) readSync(attributes.get(ATTR_TOLERANCE));
    }

    /**
     * <p>
     * Get the <i>ScaledValue</i> attribute.
     * <p>
     * <p>
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
     * <p>
     * Synchronously get the <i>ScaledValue</i> attribute.
     * <p>
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getScaledValue() {
        return (Integer) readSync(attributes.get(ATTR_SCALEDVALUE));
    }


    /**
     * <p>
     * Configure reporting for the <i>ScaledValue</i> attribute.
     * <p>
     * <p>
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
    public Future<CommandResult> configScaledValueReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return report(ATTR_SCALEDVALUE, minInterval, maxInterval, reportableChange);
    }

    /**
     * <p>
     * Get the <i>MinScaledValue</i> attribute.
     * <p>
     * <p>
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
     * <p>
     * Synchronously get the <i>MinScaledValue</i> attribute.
     * <p>
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMinScaledValue() {
        return (Integer) readSync(attributes.get(ATTR_MINSCALEDVALUE));
    }

    /**
     * <p>
     * Get the <i>MaxScaledValue</i> attribute.
     * <p>
     * <p>
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
     * <p>
     * Synchronously get the <i>MaxScaledValue</i> attribute.
     * <p>
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMaxScaledValue() {
        return (Integer) readSync(attributes.get(ATTR_MAXSCALEDVALUE));
    }

    /**
     * <p>
     * Get the <i>ScaledTolerance</i> attribute.
     * <p>
     * <p>
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
     * <p>
     * Synchronously get the <i>ScaledTolerance</i> attribute.
     * <p>
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getScaledTolerance() {
        return (Integer) readSync(attributes.get(ATTR_SCALEDTOLERANCE));
    }


    /**
     * <p>
     * Configure reporting for the <i>ScaledTolerance</i> attribute.
     * <p>
     * <p>
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
    public Future<CommandResult> configScaledToleranceReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return report(ATTR_SCALEDTOLERANCE, minInterval, maxInterval, reportableChange);
    }

    /**
     * <p>
     * Get the <i>Scale</i> attribute.
     * <p>
     * <p>
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
     * <p>
     * Synchronously get the <i>Scale</i> attribute.
     * <p>
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getScale() {
        return (Integer) readSync(attributes.get(ATTR_SCALE));
    }

    /**
     * Add a binding for this cluster to the local node
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> bind() {
        return bind();
    }
}
