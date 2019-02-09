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
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.windowcovering.WindowCoveringDownClose;
import com.zsmartsystems.zigbee.zcl.clusters.windowcovering.WindowCoveringGoToLiftPercentage;
import com.zsmartsystems.zigbee.zcl.clusters.windowcovering.WindowCoveringGoToLiftValue;
import com.zsmartsystems.zigbee.zcl.clusters.windowcovering.WindowCoveringGoToTiltPercentage;
import com.zsmartsystems.zigbee.zcl.clusters.windowcovering.WindowCoveringGoToTiltValue;
import com.zsmartsystems.zigbee.zcl.clusters.windowcovering.WindowCoveringStop;
import com.zsmartsystems.zigbee.zcl.clusters.windowcovering.WindowCoveringUpOpen;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Window Covering</b> cluster implementation (<i>Cluster ID 0x0102</i>).
 * <p>
 * Provides an interface for controlling and adjusting automatic window coverings.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T17:03:43Z")
public class ZclWindowCoveringCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0102;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Window Covering";

    // Attribute constants
    /**
     * The WindowCoveringType attribute identifies the type of window covering being
     * controlled by this endpoint.
     */
    public static final int ATTR_WINDOWCOVERINGTYPE = 0x0000;
    /**
     * The PhysicalClosedLimitLift attribute identifies the maximum possible encoder
     * position possible (in centi- meters) to position the height of the window covering –
     * this is ignored if the device is running in Open Loop Control.
     */
    public static final int ATTR_PHYSICALCLOSEDLIMITLIFT = 0x0001;
    /**
     * The PhysicalClosedLimitTilt attribute identifies the maximum possible encoder
     * position possible (tenth of a degrees) to position the angle of the window covering –
     * this is ignored if the device is running in Open Loop Control.
     */
    public static final int ATTR_PHYSICALCLOSEDLIMITTILT = 0x0002;
    /**
     * The CurrentPositionLift attribute identifies the actual position (in centimeters)
     * of the window covering from the top of the shade if Closed Loop Control is enabled. This
     * attribute is ignored if the device is running in Open Loop Control.
     */
    public static final int ATTR_CURRENTPOSITIONLIFT = 0x0003;
    /**
     * The CurrentPositionTilt attribute identifies the actual tilt position (in tenth of an
     * degree) of the window covering from Open if Closed Loop Control is enabled. This
     * attribute is ignored if the device is running in Open Loop Control.
     */
    public static final int ATTR_CURRENTPOSITIONTILT = 0x0004;
    /**
     * The NumberOfActuationsLift attribute identifies the total number of lift actuations
     * applied to the Window Covering since the device was installed.
     */
    public static final int ATTR_NUMBEROFACTUATIONSLIFT = 0x0005;
    /**
     * The NumberOfActuationsTilt attribute identifies the total number of tilt actuations
     * applied to the Window Covering since the device was installed.
     */
    public static final int ATTR_NUMBEROFACTUATIONSTILT = 0x0006;
    /**
     * The ConfigStatus attribute makes configuration and status information available. To
     * change settings, devices shall write to the Mode attribute of the Window Covering
     * Settings Attribute Set. The behavior causing the setting or clearing of each bit is
     * vendor specific.
     */
    public static final int ATTR_CONFIGSTATUS = 0x0007;
    /**
     * The CurrentPositionLiftPercentage attribute identifies the actual position as a
     * percentage between the InstalledOpenLimitLift attribute and the
     * InstalledClosedLimitLift58attribute of the window covering from the up/open
     * position if Closed Loop Control is enabled. If the device is running in Open Loop Control
     * or the device only supports Tilt actions, this attribute is not required as an attribute
     * but has a special interpretation when received as part of a scene command.
     */
    public static final int ATTR_CURRENTPOSITIONLIFTPERCENTAGE = 0x0008;
    /**
     * The CurrentPositionTiltPercentage attribute identifies the actual position as a
     * percentage between the InstalledOpenLimitTilt attribute and the
     * InstalledClosedLimitTilt59attribute of the window covering from the up/open
     * position if Closed Loop Control is enabled. If the device is running in Open Loop Control
     * or the device only support Lift actions, this attribute is not required as an attribute
     * but has a special interpretation when received as part of a scene command.
     */
    public static final int ATTR_CURRENTPOSITIONTILTPERCENTAGE = 0x0009;
    /**
     * The InstalledOpenLimitLift attribute identifies the Open Limit for Lifting the
     * Window Covering whether position (in centimeters) is encoded or timed. This attribute
     * is ignored if the device is running in Open Loop Control or only supports Tilt actions.
     */
    public static final int ATTR_INSTALLEDOPENLIMITLIFT = 0x0010;
    /**
     * The InstalledClosedLimitLift attribute identifies the Closed Limit for Lifting the
     * Window Covering whether position (in centimeters) is encoded or timed. This attribute
     * is ignored if the device is running in Open Loop Control or only supports Tilt actions.
     */
    public static final int ATTR_INSTALLEDCLOSEDLIMITLIFT = 0x0011;
    /**
     * The InstalledOpenLimitTilt attribute identifies the Open Limit for Tilting the
     * Window Covering whether position (in tenth of a degree) is encoded or timed. This
     * attribute is ignored if the device is running in Open Loop Control or only supports Lift
     * actions.
     */
    public static final int ATTR_INSTALLEDOPENLIMITTILT = 0x0012;
    /**
     * The InstalledClosedLimitTilt attribute identifies the Closed Limit for Tilting the
     * Window Covering whether position (in tenth of a degree) is encoded or timed. This
     * attribute is ignored if the device is running in Open Loop Control or only supports Lift
     * actions.
     */
    public static final int ATTR_INSTALLEDCLOSEDLIMITTILT = 0x0013;
    /**
     * The VelocityLift attribute identifies the velocity (in centimeters per second)
     * associated with Lifting the Window Covering.
     */
    public static final int ATTR_VELOCITYLIFT = 0x0014;
    /**
     * The AccelerationTimeLift attribute identifies any ramp up times to reaching the
     * velocity setting (in tenth of a second) for positioning the Window Covering.
     */
    public static final int ATTR_ACCELERATIONTIMELIFT = 0x0015;
    /**
     * The DecelerationTimeLift attribute identifies any ramp down times associated with
     * stopping the positioning (in tenth of a second) of the Window Covering.
     */
    public static final int ATTR_DECELERATIONTIMELIFT = 0x0016;
    /**
     * The Mode attribute allows configuration of the Window Covering, such as: reversing the
     * motor direction, placing the Window Covering into calibration mode, placing the motor
     * into maintenance mode, disabling the ZigBee network, and disabling status LEDs.
     */
    public static final int ATTR_MODE = 0x0017;
    /**
     * Identifies the number of Intermediate Setpoints supported by the Window Covering for
     * Lift and then iden- tifies the position settings for those Intermediate Setpoints if
     * Closed Loop Control is supported. This is a comma delimited ASCII character string. For
     * example: “2,0x0013, 0x0030”
     */
    public static final int ATTR_INTERMEDIATESETPOINTSLIFT = 0x0018;
    /**
     * Identifies the number of Intermediate Setpoints supported by the Window Covering for
     * Tilt and then iden- tifies the position settings for those Intermediate Setpoints if
     * Closed Loop Control is supported. This is a comma delimited ASCII character string. For
     * example: “2,0x0013, 0x0030”
     */
    public static final int ATTR_INTERMEDIATESETPOINTSTILT = 0x0019;

    // Attribute initialisation
    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(20);

        attributeMap.put(ATTR_WINDOWCOVERINGTYPE, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_WINDOWCOVERINGTYPE, "Window Covering Type", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_PHYSICALCLOSEDLIMITLIFT, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_PHYSICALCLOSEDLIMITLIFT, "Physical Closed Limit - Lift", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PHYSICALCLOSEDLIMITTILT, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_PHYSICALCLOSEDLIMITTILT, "Physical Closed Limit - Tilt", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_CURRENTPOSITIONLIFT, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_CURRENTPOSITIONLIFT, "Current Position - Lift", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_CURRENTPOSITIONTILT, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_CURRENTPOSITIONTILT, "Current Position - Tilt", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NUMBEROFACTUATIONSLIFT, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_NUMBEROFACTUATIONSLIFT, "Number Of Actuations - Lift", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NUMBEROFACTUATIONSTILT, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_NUMBEROFACTUATIONSTILT, "Number Of Actuations - Tilt", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_CONFIGSTATUS, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_CONFIGSTATUS, "Config Status", ZclDataType.BITMAP_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_CURRENTPOSITIONLIFTPERCENTAGE, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_CURRENTPOSITIONLIFTPERCENTAGE, "Current Position Lift Percentage", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_CURRENTPOSITIONTILTPERCENTAGE, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_CURRENTPOSITIONTILTPERCENTAGE, "Current Position Tilt Percentage", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_INSTALLEDOPENLIMITLIFT, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_INSTALLEDOPENLIMITLIFT, "Installed Open Limit - Lift", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_INSTALLEDCLOSEDLIMITLIFT, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_INSTALLEDCLOSEDLIMITLIFT, "Installed Closed Limit - Lift", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_INSTALLEDOPENLIMITTILT, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_INSTALLEDOPENLIMITTILT, "Installed Open Limit - Tilt", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_INSTALLEDCLOSEDLIMITTILT, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_INSTALLEDCLOSEDLIMITTILT, "Installed Closed Limit - Tilt", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_VELOCITYLIFT, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_VELOCITYLIFT, "Velocity - Lift", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_ACCELERATIONTIMELIFT, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_ACCELERATIONTIMELIFT, "Acceleration Time - Lift", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_DECELERATIONTIMELIFT, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_DECELERATIONTIMELIFT, "Deceleration Time - Lift", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_MODE, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_MODE, "Mode", ZclDataType.BITMAP_8_BIT, false, true, true, true));
        attributeMap.put(ATTR_INTERMEDIATESETPOINTSLIFT, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_INTERMEDIATESETPOINTSLIFT, "Intermediate Setpoints - Lift", ZclDataType.OCTET_STRING, false, true, true, true));
        attributeMap.put(ATTR_INTERMEDIATESETPOINTSTILT, new ZclAttribute(ZclClusterType.WINDOW_COVERING, ATTR_INTERMEDIATESETPOINTSTILT, "Intermediate Setpoints - Tilt", ZclDataType.OCTET_STRING, false, true, true, true));

        return attributeMap;
    }

    /**
     * Default constructor to create a Window Covering cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclWindowCoveringCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>Window Covering Type</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The WindowCoveringType attribute identifies the type of window covering being
     * controlled by this endpoint.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getWindowCoveringTypeAsync() {
        return read(attributes.get(ATTR_WINDOWCOVERINGTYPE));
    }

    /**
     * Synchronously get the <i>Window Covering Type</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The WindowCoveringType attribute identifies the type of window covering being
     * controlled by this endpoint.
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
    public Integer getWindowCoveringType(final long refreshPeriod) {
        if (attributes.get(ATTR_WINDOWCOVERINGTYPE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_WINDOWCOVERINGTYPE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_WINDOWCOVERINGTYPE));
    }

    /**
     * Set reporting for the <i>Window Covering Type</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The WindowCoveringType attribute identifies the type of window covering being
     * controlled by this endpoint.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setWindowCoveringTypeReporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_WINDOWCOVERINGTYPE), minInterval, maxInterval);
    }

    /**
     * Get the <i>Physical Closed Limit - Lift</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The PhysicalClosedLimitLift attribute identifies the maximum possible encoder
     * position possible (in centi- meters) to position the height of the window covering –
     * this is ignored if the device is running in Open Loop Control.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPhysicalClosedLimitLiftAsync() {
        return read(attributes.get(ATTR_PHYSICALCLOSEDLIMITLIFT));
    }

    /**
     * Synchronously get the <i>Physical Closed Limit - Lift</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The PhysicalClosedLimitLift attribute identifies the maximum possible encoder
     * position possible (in centi- meters) to position the height of the window covering –
     * this is ignored if the device is running in Open Loop Control.
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
    public Integer getPhysicalClosedLimitLift(final long refreshPeriod) {
        if (attributes.get(ATTR_PHYSICALCLOSEDLIMITLIFT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PHYSICALCLOSEDLIMITLIFT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PHYSICALCLOSEDLIMITLIFT));
    }

    /**
     * Set reporting for the <i>Physical Closed Limit - Lift</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The PhysicalClosedLimitLift attribute identifies the maximum possible encoder
     * position possible (in centi- meters) to position the height of the window covering –
     * this is ignored if the device is running in Open Loop Control.
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
    public Future<CommandResult> setPhysicalClosedLimitLiftReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PHYSICALCLOSEDLIMITLIFT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Physical Closed Limit - Tilt</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The PhysicalClosedLimitTilt attribute identifies the maximum possible encoder
     * position possible (tenth of a degrees) to position the angle of the window covering –
     * this is ignored if the device is running in Open Loop Control.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPhysicalClosedLimitTiltAsync() {
        return read(attributes.get(ATTR_PHYSICALCLOSEDLIMITTILT));
    }

    /**
     * Synchronously get the <i>Physical Closed Limit - Tilt</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The PhysicalClosedLimitTilt attribute identifies the maximum possible encoder
     * position possible (tenth of a degrees) to position the angle of the window covering –
     * this is ignored if the device is running in Open Loop Control.
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
    public Integer getPhysicalClosedLimitTilt(final long refreshPeriod) {
        if (attributes.get(ATTR_PHYSICALCLOSEDLIMITTILT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PHYSICALCLOSEDLIMITTILT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PHYSICALCLOSEDLIMITTILT));
    }

    /**
     * Set reporting for the <i>Physical Closed Limit - Tilt</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The PhysicalClosedLimitTilt attribute identifies the maximum possible encoder
     * position possible (tenth of a degrees) to position the angle of the window covering –
     * this is ignored if the device is running in Open Loop Control.
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
    public Future<CommandResult> setPhysicalClosedLimitTiltReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PHYSICALCLOSEDLIMITTILT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Current Position - Lift</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The CurrentPositionLift attribute identifies the actual position (in centimeters)
     * of the window covering from the top of the shade if Closed Loop Control is enabled. This
     * attribute is ignored if the device is running in Open Loop Control.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentPositionLiftAsync() {
        return read(attributes.get(ATTR_CURRENTPOSITIONLIFT));
    }

    /**
     * Synchronously get the <i>Current Position - Lift</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The CurrentPositionLift attribute identifies the actual position (in centimeters)
     * of the window covering from the top of the shade if Closed Loop Control is enabled. This
     * attribute is ignored if the device is running in Open Loop Control.
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
    public Integer getCurrentPositionLift(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENTPOSITIONLIFT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENTPOSITIONLIFT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTPOSITIONLIFT));
    }

    /**
     * Set reporting for the <i>Current Position - Lift</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The CurrentPositionLift attribute identifies the actual position (in centimeters)
     * of the window covering from the top of the shade if Closed Loop Control is enabled. This
     * attribute is ignored if the device is running in Open Loop Control.
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
    public Future<CommandResult> setCurrentPositionLiftReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTPOSITIONLIFT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Current Position - Tilt</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The CurrentPositionTilt attribute identifies the actual tilt position (in tenth of an
     * degree) of the window covering from Open if Closed Loop Control is enabled. This
     * attribute is ignored if the device is running in Open Loop Control.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentPositionTiltAsync() {
        return read(attributes.get(ATTR_CURRENTPOSITIONTILT));
    }

    /**
     * Synchronously get the <i>Current Position - Tilt</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The CurrentPositionTilt attribute identifies the actual tilt position (in tenth of an
     * degree) of the window covering from Open if Closed Loop Control is enabled. This
     * attribute is ignored if the device is running in Open Loop Control.
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
    public Integer getCurrentPositionTilt(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENTPOSITIONTILT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENTPOSITIONTILT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTPOSITIONTILT));
    }

    /**
     * Set reporting for the <i>Current Position - Tilt</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The CurrentPositionTilt attribute identifies the actual tilt position (in tenth of an
     * degree) of the window covering from Open if Closed Loop Control is enabled. This
     * attribute is ignored if the device is running in Open Loop Control.
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
    public Future<CommandResult> setCurrentPositionTiltReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTPOSITIONTILT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Number Of Actuations - Lift</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The NumberOfActuationsLift attribute identifies the total number of lift actuations
     * applied to the Window Covering since the device was installed.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getNumberOfActuationsLiftAsync() {
        return read(attributes.get(ATTR_NUMBEROFACTUATIONSLIFT));
    }

    /**
     * Synchronously get the <i>Number Of Actuations - Lift</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The NumberOfActuationsLift attribute identifies the total number of lift actuations
     * applied to the Window Covering since the device was installed.
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
    public Integer getNumberOfActuationsLift(final long refreshPeriod) {
        if (attributes.get(ATTR_NUMBEROFACTUATIONSLIFT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_NUMBEROFACTUATIONSLIFT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_NUMBEROFACTUATIONSLIFT));
    }

    /**
     * Set reporting for the <i>Number Of Actuations - Lift</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The NumberOfActuationsLift attribute identifies the total number of lift actuations
     * applied to the Window Covering since the device was installed.
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
    public Future<CommandResult> setNumberOfActuationsLiftReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_NUMBEROFACTUATIONSLIFT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Number Of Actuations - Tilt</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * The NumberOfActuationsTilt attribute identifies the total number of tilt actuations
     * applied to the Window Covering since the device was installed.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getNumberOfActuationsTiltAsync() {
        return read(attributes.get(ATTR_NUMBEROFACTUATIONSTILT));
    }

    /**
     * Synchronously get the <i>Number Of Actuations - Tilt</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * The NumberOfActuationsTilt attribute identifies the total number of tilt actuations
     * applied to the Window Covering since the device was installed.
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
    public Integer getNumberOfActuationsTilt(final long refreshPeriod) {
        if (attributes.get(ATTR_NUMBEROFACTUATIONSTILT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_NUMBEROFACTUATIONSTILT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_NUMBEROFACTUATIONSTILT));
    }

    /**
     * Set reporting for the <i>Number Of Actuations - Tilt</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * The NumberOfActuationsTilt attribute identifies the total number of tilt actuations
     * applied to the Window Covering since the device was installed.
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
    public Future<CommandResult> setNumberOfActuationsTiltReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_NUMBEROFACTUATIONSTILT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Config Status</i> attribute [attribute ID <b>0x0007</b>].
     * <p>
     * The ConfigStatus attribute makes configuration and status information available. To
     * change settings, devices shall write to the Mode attribute of the Window Covering
     * Settings Attribute Set. The behavior causing the setting or clearing of each bit is
     * vendor specific.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getConfigStatusAsync() {
        return read(attributes.get(ATTR_CONFIGSTATUS));
    }

    /**
     * Synchronously get the <i>Config Status</i> attribute [attribute ID <b>0x0007</b>].
     * <p>
     * The ConfigStatus attribute makes configuration and status information available. To
     * change settings, devices shall write to the Mode attribute of the Window Covering
     * Settings Attribute Set. The behavior causing the setting or clearing of each bit is
     * vendor specific.
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
    public Integer getConfigStatus(final long refreshPeriod) {
        if (attributes.get(ATTR_CONFIGSTATUS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CONFIGSTATUS).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CONFIGSTATUS));
    }

    /**
     * Set reporting for the <i>Config Status</i> attribute [attribute ID <b>0x0007</b>].
     * <p>
     * The ConfigStatus attribute makes configuration and status information available. To
     * change settings, devices shall write to the Mode attribute of the Window Covering
     * Settings Attribute Set. The behavior causing the setting or clearing of each bit is
     * vendor specific.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setConfigStatusReporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_CONFIGSTATUS), minInterval, maxInterval);
    }

    /**
     * Get the <i>Current Position Lift Percentage</i> attribute [attribute ID <b>0x0008</b>].
     * <p>
     * The CurrentPositionLiftPercentage attribute identifies the actual position as a
     * percentage between the InstalledOpenLimitLift attribute and the
     * InstalledClosedLimitLift58attribute of the window covering from the up/open
     * position if Closed Loop Control is enabled. If the device is running in Open Loop Control
     * or the device only supports Tilt actions, this attribute is not required as an attribute
     * but has a special interpretation when received as part of a scene command.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentPositionLiftPercentageAsync() {
        return read(attributes.get(ATTR_CURRENTPOSITIONLIFTPERCENTAGE));
    }

    /**
     * Synchronously get the <i>Current Position Lift Percentage</i> attribute [attribute ID <b>0x0008</b>].
     * <p>
     * The CurrentPositionLiftPercentage attribute identifies the actual position as a
     * percentage between the InstalledOpenLimitLift attribute and the
     * InstalledClosedLimitLift58attribute of the window covering from the up/open
     * position if Closed Loop Control is enabled. If the device is running in Open Loop Control
     * or the device only supports Tilt actions, this attribute is not required as an attribute
     * but has a special interpretation when received as part of a scene command.
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
    public Integer getCurrentPositionLiftPercentage(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENTPOSITIONLIFTPERCENTAGE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENTPOSITIONLIFTPERCENTAGE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTPOSITIONLIFTPERCENTAGE));
    }

    /**
     * Set reporting for the <i>Current Position Lift Percentage</i> attribute [attribute ID <b>0x0008</b>].
     * <p>
     * The CurrentPositionLiftPercentage attribute identifies the actual position as a
     * percentage between the InstalledOpenLimitLift attribute and the
     * InstalledClosedLimitLift58attribute of the window covering from the up/open
     * position if Closed Loop Control is enabled. If the device is running in Open Loop Control
     * or the device only supports Tilt actions, this attribute is not required as an attribute
     * but has a special interpretation when received as part of a scene command.
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
    public Future<CommandResult> setCurrentPositionLiftPercentageReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTPOSITIONLIFTPERCENTAGE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Current Position Tilt Percentage</i> attribute [attribute ID <b>0x0009</b>].
     * <p>
     * The CurrentPositionTiltPercentage attribute identifies the actual position as a
     * percentage between the InstalledOpenLimitTilt attribute and the
     * InstalledClosedLimitTilt59attribute of the window covering from the up/open
     * position if Closed Loop Control is enabled. If the device is running in Open Loop Control
     * or the device only support Lift actions, this attribute is not required as an attribute
     * but has a special interpretation when received as part of a scene command.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentPositionTiltPercentageAsync() {
        return read(attributes.get(ATTR_CURRENTPOSITIONTILTPERCENTAGE));
    }

    /**
     * Synchronously get the <i>Current Position Tilt Percentage</i> attribute [attribute ID <b>0x0009</b>].
     * <p>
     * The CurrentPositionTiltPercentage attribute identifies the actual position as a
     * percentage between the InstalledOpenLimitTilt attribute and the
     * InstalledClosedLimitTilt59attribute of the window covering from the up/open
     * position if Closed Loop Control is enabled. If the device is running in Open Loop Control
     * or the device only support Lift actions, this attribute is not required as an attribute
     * but has a special interpretation when received as part of a scene command.
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
    public Integer getCurrentPositionTiltPercentage(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENTPOSITIONTILTPERCENTAGE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENTPOSITIONTILTPERCENTAGE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTPOSITIONTILTPERCENTAGE));
    }

    /**
     * Set reporting for the <i>Current Position Tilt Percentage</i> attribute [attribute ID <b>0x0009</b>].
     * <p>
     * The CurrentPositionTiltPercentage attribute identifies the actual position as a
     * percentage between the InstalledOpenLimitTilt attribute and the
     * InstalledClosedLimitTilt59attribute of the window covering from the up/open
     * position if Closed Loop Control is enabled. If the device is running in Open Loop Control
     * or the device only support Lift actions, this attribute is not required as an attribute
     * but has a special interpretation when received as part of a scene command.
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
    public Future<CommandResult> setCurrentPositionTiltPercentageReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTPOSITIONTILTPERCENTAGE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Installed Open Limit - Lift</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The InstalledOpenLimitLift attribute identifies the Open Limit for Lifting the
     * Window Covering whether position (in centimeters) is encoded or timed. This attribute
     * is ignored if the device is running in Open Loop Control or only supports Tilt actions.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getInstalledOpenLimitLiftAsync() {
        return read(attributes.get(ATTR_INSTALLEDOPENLIMITLIFT));
    }

    /**
     * Synchronously get the <i>Installed Open Limit - Lift</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The InstalledOpenLimitLift attribute identifies the Open Limit for Lifting the
     * Window Covering whether position (in centimeters) is encoded or timed. This attribute
     * is ignored if the device is running in Open Loop Control or only supports Tilt actions.
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
    public Integer getInstalledOpenLimitLift(final long refreshPeriod) {
        if (attributes.get(ATTR_INSTALLEDOPENLIMITLIFT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_INSTALLEDOPENLIMITLIFT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_INSTALLEDOPENLIMITLIFT));
    }

    /**
     * Set reporting for the <i>Installed Open Limit - Lift</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The InstalledOpenLimitLift attribute identifies the Open Limit for Lifting the
     * Window Covering whether position (in centimeters) is encoded or timed. This attribute
     * is ignored if the device is running in Open Loop Control or only supports Tilt actions.
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
    public Future<CommandResult> setInstalledOpenLimitLiftReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_INSTALLEDOPENLIMITLIFT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Installed Closed Limit - Lift</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The InstalledClosedLimitLift attribute identifies the Closed Limit for Lifting the
     * Window Covering whether position (in centimeters) is encoded or timed. This attribute
     * is ignored if the device is running in Open Loop Control or only supports Tilt actions.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getInstalledClosedLimitLiftAsync() {
        return read(attributes.get(ATTR_INSTALLEDCLOSEDLIMITLIFT));
    }

    /**
     * Synchronously get the <i>Installed Closed Limit - Lift</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The InstalledClosedLimitLift attribute identifies the Closed Limit for Lifting the
     * Window Covering whether position (in centimeters) is encoded or timed. This attribute
     * is ignored if the device is running in Open Loop Control or only supports Tilt actions.
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
    public Integer getInstalledClosedLimitLift(final long refreshPeriod) {
        if (attributes.get(ATTR_INSTALLEDCLOSEDLIMITLIFT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_INSTALLEDCLOSEDLIMITLIFT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_INSTALLEDCLOSEDLIMITLIFT));
    }

    /**
     * Set reporting for the <i>Installed Closed Limit - Lift</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The InstalledClosedLimitLift attribute identifies the Closed Limit for Lifting the
     * Window Covering whether position (in centimeters) is encoded or timed. This attribute
     * is ignored if the device is running in Open Loop Control or only supports Tilt actions.
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
    public Future<CommandResult> setInstalledClosedLimitLiftReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_INSTALLEDCLOSEDLIMITLIFT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Installed Open Limit - Tilt</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The InstalledOpenLimitTilt attribute identifies the Open Limit for Tilting the
     * Window Covering whether position (in tenth of a degree) is encoded or timed. This
     * attribute is ignored if the device is running in Open Loop Control or only supports Lift
     * actions.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getInstalledOpenLimitTiltAsync() {
        return read(attributes.get(ATTR_INSTALLEDOPENLIMITTILT));
    }

    /**
     * Synchronously get the <i>Installed Open Limit - Tilt</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The InstalledOpenLimitTilt attribute identifies the Open Limit for Tilting the
     * Window Covering whether position (in tenth of a degree) is encoded or timed. This
     * attribute is ignored if the device is running in Open Loop Control or only supports Lift
     * actions.
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
    public Integer getInstalledOpenLimitTilt(final long refreshPeriod) {
        if (attributes.get(ATTR_INSTALLEDOPENLIMITTILT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_INSTALLEDOPENLIMITTILT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_INSTALLEDOPENLIMITTILT));
    }

    /**
     * Set reporting for the <i>Installed Open Limit - Tilt</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The InstalledOpenLimitTilt attribute identifies the Open Limit for Tilting the
     * Window Covering whether position (in tenth of a degree) is encoded or timed. This
     * attribute is ignored if the device is running in Open Loop Control or only supports Lift
     * actions.
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
    public Future<CommandResult> setInstalledOpenLimitTiltReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_INSTALLEDOPENLIMITTILT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Installed Closed Limit - Tilt</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The InstalledClosedLimitTilt attribute identifies the Closed Limit for Tilting the
     * Window Covering whether position (in tenth of a degree) is encoded or timed. This
     * attribute is ignored if the device is running in Open Loop Control or only supports Lift
     * actions.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getInstalledClosedLimitTiltAsync() {
        return read(attributes.get(ATTR_INSTALLEDCLOSEDLIMITTILT));
    }

    /**
     * Synchronously get the <i>Installed Closed Limit - Tilt</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The InstalledClosedLimitTilt attribute identifies the Closed Limit for Tilting the
     * Window Covering whether position (in tenth of a degree) is encoded or timed. This
     * attribute is ignored if the device is running in Open Loop Control or only supports Lift
     * actions.
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
    public Integer getInstalledClosedLimitTilt(final long refreshPeriod) {
        if (attributes.get(ATTR_INSTALLEDCLOSEDLIMITTILT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_INSTALLEDCLOSEDLIMITTILT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_INSTALLEDCLOSEDLIMITTILT));
    }

    /**
     * Set reporting for the <i>Installed Closed Limit - Tilt</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The InstalledClosedLimitTilt attribute identifies the Closed Limit for Tilting the
     * Window Covering whether position (in tenth of a degree) is encoded or timed. This
     * attribute is ignored if the device is running in Open Loop Control or only supports Lift
     * actions.
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
    public Future<CommandResult> setInstalledClosedLimitTiltReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_INSTALLEDCLOSEDLIMITTILT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Set the <i>Velocity - Lift</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The VelocityLift attribute identifies the velocity (in centimeters per second)
     * associated with Lifting the Window Covering.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param velocityLift the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setVelocityLift(final Integer value) {
        return write(attributes.get(ATTR_VELOCITYLIFT), value);
    }

    /**
     * Get the <i>Velocity - Lift</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The VelocityLift attribute identifies the velocity (in centimeters per second)
     * associated with Lifting the Window Covering.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getVelocityLiftAsync() {
        return read(attributes.get(ATTR_VELOCITYLIFT));
    }

    /**
     * Synchronously get the <i>Velocity - Lift</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The VelocityLift attribute identifies the velocity (in centimeters per second)
     * associated with Lifting the Window Covering.
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
    public Integer getVelocityLift(final long refreshPeriod) {
        if (attributes.get(ATTR_VELOCITYLIFT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_VELOCITYLIFT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_VELOCITYLIFT));
    }

    /**
     * Set the <i>Acceleration Time - Lift</i> attribute [attribute ID <b>0x0015</b>].
     * <p>
     * The AccelerationTimeLift attribute identifies any ramp up times to reaching the
     * velocity setting (in tenth of a second) for positioning the Window Covering.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param accelerationTimeLift the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setAccelerationTimeLift(final Integer value) {
        return write(attributes.get(ATTR_ACCELERATIONTIMELIFT), value);
    }

    /**
     * Get the <i>Acceleration Time - Lift</i> attribute [attribute ID <b>0x0015</b>].
     * <p>
     * The AccelerationTimeLift attribute identifies any ramp up times to reaching the
     * velocity setting (in tenth of a second) for positioning the Window Covering.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getAccelerationTimeLiftAsync() {
        return read(attributes.get(ATTR_ACCELERATIONTIMELIFT));
    }

    /**
     * Synchronously get the <i>Acceleration Time - Lift</i> attribute [attribute ID <b>0x0015</b>].
     * <p>
     * The AccelerationTimeLift attribute identifies any ramp up times to reaching the
     * velocity setting (in tenth of a second) for positioning the Window Covering.
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
    public Integer getAccelerationTimeLift(final long refreshPeriod) {
        if (attributes.get(ATTR_ACCELERATIONTIMELIFT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ACCELERATIONTIMELIFT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ACCELERATIONTIMELIFT));
    }

    /**
     * Set the <i>Deceleration Time - Lift</i> attribute [attribute ID <b>0x0016</b>].
     * <p>
     * The DecelerationTimeLift attribute identifies any ramp down times associated with
     * stopping the positioning (in tenth of a second) of the Window Covering.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param decelerationTimeLift the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDecelerationTimeLift(final Integer value) {
        return write(attributes.get(ATTR_DECELERATIONTIMELIFT), value);
    }

    /**
     * Get the <i>Deceleration Time - Lift</i> attribute [attribute ID <b>0x0016</b>].
     * <p>
     * The DecelerationTimeLift attribute identifies any ramp down times associated with
     * stopping the positioning (in tenth of a second) of the Window Covering.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDecelerationTimeLiftAsync() {
        return read(attributes.get(ATTR_DECELERATIONTIMELIFT));
    }

    /**
     * Synchronously get the <i>Deceleration Time - Lift</i> attribute [attribute ID <b>0x0016</b>].
     * <p>
     * The DecelerationTimeLift attribute identifies any ramp down times associated with
     * stopping the positioning (in tenth of a second) of the Window Covering.
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
    public Integer getDecelerationTimeLift(final long refreshPeriod) {
        if (attributes.get(ATTR_DECELERATIONTIMELIFT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DECELERATIONTIMELIFT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DECELERATIONTIMELIFT));
    }

    /**
     * Set the <i>Mode</i> attribute [attribute ID <b>0x0017</b>].
     * <p>
     * The Mode attribute allows configuration of the Window Covering, such as: reversing the
     * motor direction, placing the Window Covering into calibration mode, placing the motor
     * into maintenance mode, disabling the ZigBee network, and disabling status LEDs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param mode the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMode(final Integer value) {
        return write(attributes.get(ATTR_MODE), value);
    }

    /**
     * Get the <i>Mode</i> attribute [attribute ID <b>0x0017</b>].
     * <p>
     * The Mode attribute allows configuration of the Window Covering, such as: reversing the
     * motor direction, placing the Window Covering into calibration mode, placing the motor
     * into maintenance mode, disabling the ZigBee network, and disabling status LEDs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getModeAsync() {
        return read(attributes.get(ATTR_MODE));
    }

    /**
     * Synchronously get the <i>Mode</i> attribute [attribute ID <b>0x0017</b>].
     * <p>
     * The Mode attribute allows configuration of the Window Covering, such as: reversing the
     * motor direction, placing the Window Covering into calibration mode, placing the motor
     * into maintenance mode, disabling the ZigBee network, and disabling status LEDs.
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
    public Integer getMode(final long refreshPeriod) {
        if (attributes.get(ATTR_MODE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MODE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MODE));
    }

    /**
     * Set the <i>Intermediate Setpoints - Lift</i> attribute [attribute ID <b>0x0018</b>].
     * <p>
     * Identifies the number of Intermediate Setpoints supported by the Window Covering for
     * Lift and then iden- tifies the position settings for those Intermediate Setpoints if
     * Closed Loop Control is supported. This is a comma delimited ASCII character string. For
     * example: “2,0x0013, 0x0030”
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param intermediateSetpointsLift the {@link ByteArray} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setIntermediateSetpointsLift(final ByteArray value) {
        return write(attributes.get(ATTR_INTERMEDIATESETPOINTSLIFT), value);
    }

    /**
     * Get the <i>Intermediate Setpoints - Lift</i> attribute [attribute ID <b>0x0018</b>].
     * <p>
     * Identifies the number of Intermediate Setpoints supported by the Window Covering for
     * Lift and then iden- tifies the position settings for those Intermediate Setpoints if
     * Closed Loop Control is supported. This is a comma delimited ASCII character string. For
     * example: “2,0x0013, 0x0030”
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getIntermediateSetpointsLiftAsync() {
        return read(attributes.get(ATTR_INTERMEDIATESETPOINTSLIFT));
    }

    /**
     * Synchronously get the <i>Intermediate Setpoints - Lift</i> attribute [attribute ID <b>0x0018</b>].
     * <p>
     * Identifies the number of Intermediate Setpoints supported by the Window Covering for
     * Lift and then iden- tifies the position settings for those Intermediate Setpoints if
     * Closed Loop Control is supported. This is a comma delimited ASCII character string. For
     * example: “2,0x0013, 0x0030”
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link ByteArray} attribute value, or null on error
     */
    public ByteArray getIntermediateSetpointsLift(final long refreshPeriod) {
        if (attributes.get(ATTR_INTERMEDIATESETPOINTSLIFT).isLastValueCurrent(refreshPeriod)) {
            return (ByteArray) attributes.get(ATTR_INTERMEDIATESETPOINTSLIFT).getLastValue();
        }

        return (ByteArray) readSync(attributes.get(ATTR_INTERMEDIATESETPOINTSLIFT));
    }

    /**
     * Set the <i>Intermediate Setpoints - Tilt</i> attribute [attribute ID <b>0x0019</b>].
     * <p>
     * Identifies the number of Intermediate Setpoints supported by the Window Covering for
     * Tilt and then iden- tifies the position settings for those Intermediate Setpoints if
     * Closed Loop Control is supported. This is a comma delimited ASCII character string. For
     * example: “2,0x0013, 0x0030”
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param intermediateSetpointsTilt the {@link ByteArray} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setIntermediateSetpointsTilt(final ByteArray value) {
        return write(attributes.get(ATTR_INTERMEDIATESETPOINTSTILT), value);
    }

    /**
     * Get the <i>Intermediate Setpoints - Tilt</i> attribute [attribute ID <b>0x0019</b>].
     * <p>
     * Identifies the number of Intermediate Setpoints supported by the Window Covering for
     * Tilt and then iden- tifies the position settings for those Intermediate Setpoints if
     * Closed Loop Control is supported. This is a comma delimited ASCII character string. For
     * example: “2,0x0013, 0x0030”
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getIntermediateSetpointsTiltAsync() {
        return read(attributes.get(ATTR_INTERMEDIATESETPOINTSTILT));
    }

    /**
     * Synchronously get the <i>Intermediate Setpoints - Tilt</i> attribute [attribute ID <b>0x0019</b>].
     * <p>
     * Identifies the number of Intermediate Setpoints supported by the Window Covering for
     * Tilt and then iden- tifies the position settings for those Intermediate Setpoints if
     * Closed Loop Control is supported. This is a comma delimited ASCII character string. For
     * example: “2,0x0013, 0x0030”
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link ByteArray} attribute value, or null on error
     */
    public ByteArray getIntermediateSetpointsTilt(final long refreshPeriod) {
        if (attributes.get(ATTR_INTERMEDIATESETPOINTSTILT).isLastValueCurrent(refreshPeriod)) {
            return (ByteArray) attributes.get(ATTR_INTERMEDIATESETPOINTSTILT).getLastValue();
        }

        return (ByteArray) readSync(attributes.get(ATTR_INTERMEDIATESETPOINTSTILT));
    }

    /**
     * The Window Covering Up Open
     * <p>
     * Moves window covering to InstalledOpenLimit
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> windowCoveringUpOpen() {
        return send(new WindowCoveringUpOpen());
    }

    /**
     * The Window Covering Down Close
     * <p>
     * Moves window covering to InstalledClosedLimit
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> windowCoveringDownClose() {
        return send(new WindowCoveringDownClose());
    }

    /**
     * The Window Covering Stop
     * <p>
     * Stop any adjustment of window covering
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> windowCoveringStop() {
        return send(new WindowCoveringStop());
    }

    /**
     * The Window Covering Go To Lift Value
     * <p>
     * Goto the specified lift value
     *
     * @param liftValue {@link Integer} Lift Value
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> windowCoveringGoToLiftValue(Integer liftValue) {
        WindowCoveringGoToLiftValue command = new WindowCoveringGoToLiftValue();

        // Set the fields
        command.setLiftValue(liftValue);

        return send(command);
    }

    /**
     * The Window Covering Go To Lift Percentage
     * <p>
     * Goto the specified lift percentage
     *
     * @param percentageLiftValue {@link Integer} Percentage Lift Value
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> windowCoveringGoToLiftPercentage(Integer percentageLiftValue) {
        WindowCoveringGoToLiftPercentage command = new WindowCoveringGoToLiftPercentage();

        // Set the fields
        command.setPercentageLiftValue(percentageLiftValue);

        return send(command);
    }

    /**
     * The Window Covering Go To Tilt Value
     * <p>
     * Goto the specified tilt value
     *
     * @param tiltValue {@link Integer} Tilt Value
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> windowCoveringGoToTiltValue(Integer tiltValue) {
        WindowCoveringGoToTiltValue command = new WindowCoveringGoToTiltValue();

        // Set the fields
        command.setTiltValue(tiltValue);

        return send(command);
    }

    /**
     * The Window Covering Go To Tilt Percentage
     * <p>
     * Goto the specified tilt percentage
     *
     * @param percentageTiltValue {@link Integer} Percentage Tilt Value
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> windowCoveringGoToTiltPercentage(Integer percentageTiltValue) {
        WindowCoveringGoToTiltPercentage command = new WindowCoveringGoToTiltPercentage();

        // Set the fields
        command.setPercentageTiltValue(percentageTiltValue);

        return send(command);
    }

    @Override
    public ZclCommand getResponseFromId(int commandId) {
        switch (commandId) {
            default:
                return null;
        }
    }
}
