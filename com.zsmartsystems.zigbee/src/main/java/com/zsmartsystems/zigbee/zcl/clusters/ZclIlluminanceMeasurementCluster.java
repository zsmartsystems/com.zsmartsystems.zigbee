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
 * <b>Illuminance measurement</b> cluster implementation (<i>Cluster ID 0x0400</i>).
 * <p>
 * The cluster provides an interface to illuminance measurement functionality,
 * including configuration and provision of notifications of illuminance
 * measurements.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclIlluminanceMeasurementCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0x0400;

    // Cluster Name
    public static final String CLUSTER_NAME = "Illuminance measurement";

    // Attribute constants
    public static final int ATTR_MEASUREDVALUE = 0x0000;
    public static final int ATTR_MINMEASUREDVALUE = 0x0001;
    public static final int ATTR_MAXMEASUREDVALUE = 0x0002;
    public static final int ATTR_TOLERANCE = 0x0003;
    public static final int ATTR_LIGHTSENSORTYPE = 0x0004;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>(5);

        attributeMap.put(ATTR_MEASUREDVALUE, new ZclAttribute(0, "MeasuredValue", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_MINMEASUREDVALUE, new ZclAttribute(1, "MinMeasuredValue", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MAXMEASUREDVALUE, new ZclAttribute(2, "MaxMeasuredValue", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_TOLERANCE, new ZclAttribute(3, "Tolerance", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_LIGHTSENSORTYPE, new ZclAttribute(4, "LightSensorType", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor.
     */
    public ZclIlluminanceMeasurementCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {
        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * <p>
     * Get the <i>MeasuredValue</i> attribute.
     * <p>
     * <p>
     * MeasuredValue represents the Illuminance in Lux (symbol lx) as follows:-
     * <br>
     * MeasuredValue = 10,000 x log10 Illuminance + 1
     * <br>
     * Where 1 lx <= Illuminance <=3.576 Mlx, corresponding to a MeasuredValue in
     * the range 1 to 0xfffe.
     * <br>
     * The following special values of MeasuredValue apply.
     * <li>0x0000 indicates a value of Illuminance that is too low to be measured.</li>
     * <li>0xffff indicates that the Illuminance measurement is invalid.</li>
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
     * MeasuredValue represents the Illuminance in Lux (symbol lx) as follows:-
     * <br>
     * MeasuredValue = 10,000 x log10 Illuminance + 1
     * <br>
     * Where 1 lx <= Illuminance <=3.576 Mlx, corresponding to a MeasuredValue in
     * the range 1 to 0xfffe.
     * <br>
     * The following special values of MeasuredValue apply.
     * <li>0x0000 indicates a value of Illuminance that is too low to be measured.</li>
     * <li>0xffff indicates that the Illuminance measurement is invalid.</li>
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
     * MeasuredValue represents the Illuminance in Lux (symbol lx) as follows:-
     * <br>
     * MeasuredValue = 10,000 x log10 Illuminance + 1
     * <br>
     * Where 1 lx <= Illuminance <=3.576 Mlx, corresponding to a MeasuredValue in
     * the range 1 to 0xfffe.
     * <br>
     * The following special values of MeasuredValue apply.
     * <li>0x0000 indicates a value of Illuminance that is too low to be measured.</li>
     * <li>0xffff indicates that the Illuminance measurement is invalid.</li>
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
     * that can be measured. A value of 0xffff indicates that this attribute is not defined.
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
     * that can be measured. A value of 0xffff indicates that this attribute is not defined.
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
     * that can be measured. A value of 0xffff indicates that this attribute is not defined.
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
     * that can be measured. A value of 0xffff indicates that this attribute is not defined.
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
     * Configure reporting for the <i>Tolerance</i> attribute.
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
     * @param minInterval {@link int} minimum reporting period
     * @param maxInterval {@link int} maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> configToleranceReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return report(ATTR_TOLERANCE, minInterval, maxInterval, reportableChange);
    }

    /**
     * <p>
     * Get the <i>LightSensorType</i> attribute.
     * <p>
     * <p>
     * <br>
     * The LightSensorType attribute specifies the electronic type of the light sensor.
     * <p>
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
     * <p>
     * Synchronously get the <i>LightSensorType</i> attribute.
     * <p>
     * <p>
     * <br>
     * The LightSensorType attribute specifies the electronic type of the light sensor.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getLightSensorType() {
        return (Integer) readSync(attributes.get(ATTR_LIGHTSENSORTYPE));
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
