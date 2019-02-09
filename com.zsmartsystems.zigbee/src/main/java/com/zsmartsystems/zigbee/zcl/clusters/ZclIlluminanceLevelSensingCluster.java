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
 * <b>Illuminance Level Sensing</b> cluster implementation (<i>Cluster ID 0x0401</i>).
 * <p>
 * The cluster provides an interface to illuminance level sensing functionality, including
 * configuration and provision of notifications of whether the illuminance is within, above
 * or below a target band.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ZclIlluminanceLevelSensingCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0401;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Illuminance Level Sensing";

    // Attribute constants
    /**
     * The LevelStatus attribute indicates whether the measured illuminance is above,
     * below, or within a band around IlluminanceTargetLevel .
     */
    public static final int ATTR_LEVELSTATUS = 0x0000;
    /**
     * The LightSensorType attribute specifies the electronic type of the light sensor.
     */
    public static final int ATTR_LIGHTSENSORTYPE = 0x0001;
    /**
     * The IlluminanceTargetLevel attribute specifies the target illuminance level. This
     * target level is taken as the centre of a 'dead band', which must be sufficient in width,
     * with hysteresis bands at both top and bottom, to provide reliable notifications
     * without 'chatter'. Such a dead band and hysteresis bands must be provided by any
     * implementation of this cluster. (N.B. Manufacturer specific attributes may be
     * provided to configure these).
     * <p>
     * IlluminanceTargetLevel represents illuminance in Lux (symbol lx) as follows:
     * <p>
     * IlluminanceTargetLevel = 10,000 x log10 Illuminance
     * <p>
     * Where 1 lx <= Illuminance <=3.576 Mlx, corresponding to a MeasuredValue in the range 0 to
     * 0xfffe.
     * <p>
     * A value of 0xffff indicates that this attribute is not valid.
     */
    public static final int ATTR_ILLUMINANCETARGETLEVEL = 0x0010;

    // Attribute initialisation
    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(3);

        attributeMap.put(ATTR_LEVELSTATUS, new ZclAttribute(ZclClusterType.ILLUMINANCE_LEVEL_SENSING, ATTR_LEVELSTATUS, "Level Status", ZclDataType.ENUMERATION_8_BIT, true, true, false, true));
        attributeMap.put(ATTR_LIGHTSENSORTYPE, new ZclAttribute(ZclClusterType.ILLUMINANCE_LEVEL_SENSING, ATTR_LIGHTSENSORTYPE, "Light Sensor Type", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_ILLUMINANCETARGETLEVEL, new ZclAttribute(ZclClusterType.ILLUMINANCE_LEVEL_SENSING, ATTR_ILLUMINANCETARGETLEVEL, "Illuminance Target Level", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Illuminance Level Sensing cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclIlluminanceLevelSensingCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>Level Status</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The LevelStatus attribute indicates whether the measured illuminance is above,
     * below, or within a band around IlluminanceTargetLevel .
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLevelStatusAsync() {
        return read(attributes.get(ATTR_LEVELSTATUS));
    }

    /**
     * Synchronously get the <i>Level Status</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The LevelStatus attribute indicates whether the measured illuminance is above,
     * below, or within a band around IlluminanceTargetLevel .
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
    public Integer getLevelStatus(final long refreshPeriod) {
        if (attributes.get(ATTR_LEVELSTATUS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_LEVELSTATUS).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_LEVELSTATUS));
    }

    /**
     * Set reporting for the <i>Level Status</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The LevelStatus attribute indicates whether the measured illuminance is above,
     * below, or within a band around IlluminanceTargetLevel .
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setLevelStatusReporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_LEVELSTATUS), minInterval, maxInterval);
    }

    /**
     * Get the <i>Light Sensor Type</i> attribute [attribute ID <b>0x0001</b>].
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
     * Synchronously get the <i>Light Sensor Type</i> attribute [attribute ID <b>0x0001</b>].
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

    /**
     * Get the <i>Illuminance Target Level</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The IlluminanceTargetLevel attribute specifies the target illuminance level. This
     * target level is taken as the centre of a 'dead band', which must be sufficient in width,
     * with hysteresis bands at both top and bottom, to provide reliable notifications
     * without 'chatter'. Such a dead band and hysteresis bands must be provided by any
     * implementation of this cluster. (N.B. Manufacturer specific attributes may be
     * provided to configure these).
     * <p>
     * IlluminanceTargetLevel represents illuminance in Lux (symbol lx) as follows:
     * <p>
     * IlluminanceTargetLevel = 10,000 x log10 Illuminance
     * <p>
     * Where 1 lx <= Illuminance <=3.576 Mlx, corresponding to a MeasuredValue in the range 0 to
     * 0xfffe.
     * <p>
     * A value of 0xffff indicates that this attribute is not valid.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getIlluminanceTargetLevelAsync() {
        return read(attributes.get(ATTR_ILLUMINANCETARGETLEVEL));
    }

    /**
     * Synchronously get the <i>Illuminance Target Level</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The IlluminanceTargetLevel attribute specifies the target illuminance level. This
     * target level is taken as the centre of a 'dead band', which must be sufficient in width,
     * with hysteresis bands at both top and bottom, to provide reliable notifications
     * without 'chatter'. Such a dead band and hysteresis bands must be provided by any
     * implementation of this cluster. (N.B. Manufacturer specific attributes may be
     * provided to configure these).
     * <p>
     * IlluminanceTargetLevel represents illuminance in Lux (symbol lx) as follows:
     * <p>
     * IlluminanceTargetLevel = 10,000 x log10 Illuminance
     * <p>
     * Where 1 lx <= Illuminance <=3.576 Mlx, corresponding to a MeasuredValue in the range 0 to
     * 0xfffe.
     * <p>
     * A value of 0xffff indicates that this attribute is not valid.
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
    public Integer getIlluminanceTargetLevel(final long refreshPeriod) {
        if (attributes.get(ATTR_ILLUMINANCETARGETLEVEL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ILLUMINANCETARGETLEVEL).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ILLUMINANCETARGETLEVEL));
    }
}
