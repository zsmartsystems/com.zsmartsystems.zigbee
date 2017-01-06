package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * <b>Flow measurement</b> cluster implementation (<i>Cluster ID 0x0404</i>).
 * <p>
 * The server cluster provides an interface to flow measurement functionality,
 * including configuration and provision of notifications of flow measurements.
 * </p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclFlowMeasurementCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0x0404;

    // Cluster Name
    public static final String CLUSTER_NAME = "Flow measurement";

    // Attribute constants
    public static final int ATTR_MEASUREDVALUE = 0x0000;
    public static final int ATTR_MINMEASUREDVALUE = 0x0001;
    public static final int ATTR_MAXMEASUREDVALUE = 0x0002;
    public static final int ATTR_TOLERANCE = 0x0003;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>(4);

        attributeMap.put(ATTR_MEASUREDVALUE, new ZclAttribute(0, "MeasuredValue", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_MINMEASUREDVALUE, new ZclAttribute(1, "MinMeasuredValue", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MAXMEASUREDVALUE, new ZclAttribute(2, "MaxMeasuredValue", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_TOLERANCE, new ZclAttribute(3, "Tolerance", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, true));

        return attributeMap;
    }

    /**
     * Default constructor.
     */
    public ZclFlowMeasurementCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {
        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * <p>
     * Get the <i>MeasuredValue</i> attribute.
     * <p>
     * <p>
     * MeasuredValue represents the flow in m3/h as follows:-
     * <br>
     * MeasuredValue = 10 x Flow
     * <br>
     * Where 0 m3/h <= Flow <= 6,553.4 m3
     * <br>
     * /h, corresponding to a MeasuredValue in the
     * range 0 to 0xfffe.
     * <br>
     * The maximum resolution this format allows is 0.1 m3/h.
     * <br>
     * A MeasuredValue of 0xffff indicates that the pressure measurement is invalid.
     * <br>
     * MeasuredValue is updated continuously as new measurements are made.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     * </p>
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
     * MeasuredValue represents the flow in m3/h as follows:-
     * <br>
     * MeasuredValue = 10 x Flow
     * <br>
     * Where 0 m3/h <= Flow <= 6,553.4 m3
     * <br>
     * /h, corresponding to a MeasuredValue in the
     * range 0 to 0xfffe.
     * <br>
     * The maximum resolution this format allows is 0.1 m3/h.
     * <br>
     * A MeasuredValue of 0xffff indicates that the pressure measurement is invalid.
     * <br>
     * MeasuredValue is updated continuously as new measurements are made.
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     * </p>
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
     * MeasuredValue represents the flow in m3/h as follows:-
     * <br>
     * MeasuredValue = 10 x Flow
     * <br>
     * Where 0 m3/h <= Flow <= 6,553.4 m3
     * <br>
     * /h, corresponding to a MeasuredValue in the
     * range 0 to 0xfffe.
     * <br>
     * The maximum resolution this format allows is 0.1 m3/h.
     * <br>
     * A MeasuredValue of 0xffff indicates that the pressure measurement is invalid.
     * <br>
     * MeasuredValue is updated continuously as new measurements are made.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     * </p>
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
     * that can be measured. A value of 0xffff means this attribute is not defined
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     * </p>
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
     * that can be measured. A value of 0xffff means this attribute is not defined
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     * </p>
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
     * that can be measured. A value of 0xffff means this attribute is not defined.
     * <br>
     * MaxMeasuredValue shall be greater than MinMeasuredValue.
     * <br>
     * MinMeasuredValue and MaxMeasuredValue define the range of the sensor
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     * </p>
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
     * that can be measured. A value of 0xffff means this attribute is not defined.
     * <br>
     * MaxMeasuredValue shall be greater than MinMeasuredValue.
     * <br>
     * MinMeasuredValue and MaxMeasuredValue define the range of the sensor
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     * </p>
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMaxMeasuredValue() {
        return (Integer) readSync(attributes.get(ATTR_MAXMEASUREDVALUE));
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
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
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
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTolerance() {
        return (Integer) readSync(attributes.get(ATTR_TOLERANCE));
    }


    /**
     * <p>
     * Configure reporting for the <i>Tolerance</i> attribute.
     * <p>
     * <p>
     * <br>
     * The Tolerance attribute indicates the magnitude of the possible error that is
     * associated with MeasuredValue . The true value is located in the range
     * (MeasuredValue – Tolerance) to (MeasuredValue + Tolerance).
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @param minInterval {@link int} minimum reporting period
     * @param maxInterval {@link int} maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> configToleranceReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return report(ATTR_TOLERANCE, minInterval, maxInterval, reportableChange);
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
