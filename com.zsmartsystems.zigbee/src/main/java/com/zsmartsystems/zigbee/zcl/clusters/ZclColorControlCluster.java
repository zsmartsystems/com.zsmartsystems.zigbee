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
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.ColorLoopSetCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.EnhancedMoveToHueAndSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.EnhancedMoveToHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.EnhancedStepHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveColorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToColorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToColorTemperatureCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToHueAndSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StepColorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StepHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StepSaturationCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

/**
 * <b>Color control</b> cluster implementation (<i>Cluster ID 0x0300</i>).
 * <p>
 * This cluster provides an interface for changing the color of a light. Color is
 * specified according to the Commission Internationale de l'Éclairage (CIE)
 * specification CIE 1931 Color Space, [B4]. Color control is carried out in terms of
 * x,y values, as defined by this specification.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclColorControlCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0300;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Color control";

    // Attribute constants
    /**
     * The CurrentHue attribute contains the current hue value of the light. It is updated
     * as fast as practical during commands that change the hue.
     * <p>
     * The hue in degrees shall be related to the CurrentHue attribute by the relationship
     * Hue = CurrentHue x 360 / 254 (CurrentHue in the range 0 - 254 inclusive)
     * <p>
     * If this attribute is implemented then the CurrentSaturation and ColorMode
     * attributes shall also be implemented.
     */
    public static final int ATTR_CURRENTHUE = 0x0000;
    /**
     * The CurrentSaturation attribute holds the current saturation value of the light. It is
     * updated as fast as practical during commands that change the saturation.
     * The saturation shall be related to the CurrentSaturation attribute by the
     * relationship
     * Saturation = CurrentSaturation/254 (CurrentSaturation in the range 0 - 254 inclusive)
     * If this attribute is implemented then the CurrentHue and ColorMode attributes
     * shall also be implemented.
     */
    public static final int ATTR_CURRENTSATURATION = 0x0001;
    /**
     * The RemainingTime attribute holds the time remaining, in 1/10ths of a second,
     * until the currently active command will be complete.
     */
    public static final int ATTR_REMAININGTIME = 0x0002;
    /**
     * The CurrentX attribute contains the current value of the normalized chromaticity
     * value x, as defined in the CIE xyY Color Space. It is updated as fast as practical
     * during commands that change the color.
     * <p>
     * The value of x shall be related to the CurrentX attribute by the relationship
     * <p>
     * x = CurrentX / 65535 (CurrentX in the range 0 to 65279 inclusive)
     */
    public static final int ATTR_CURRENTX = 0x0003;
    /**
     * The CurrentY attribute contains the current value of the normalized chromaticity
     * value y, as defined in the CIE xyY Color Space. It is updated as fast as practical
     * during commands that change the color.
     * <p>
     * The value of y shall be related to the CurrentY attribute by the relationship
     * <p>
     * y = CurrentY / 65535 (CurrentY in the range 0 to 65279 inclusive)
     */
    public static final int ATTR_CURRENTY = 0x0004;
    /**
     * The DriftCompensation attribute indicates what mechanism, if any, is in use for
     * compensation for color/intensity drift over time.
     */
    public static final int ATTR_DRIFTCOMPENSATION = 0x0005;
    /**
     * The CompensationText attribute holds a textual indication of what mechanism, if
     * any, is in use to compensate for color/intensity drift over time.
     */
    public static final int ATTR_COMPENSATIONTEXT = 0x0006;
    /**
     * The ColorTemperature attribute contains a scaled inverse of the current value of
     * the color temperature. It is updated as fast as practical during commands that
     * change the color.
     * <p>
     * The color temperature value in Kelvins shall be related to the ColorTemperature
     * attribute by the relationship
     * <p>
     * Color temperature = 1,000,000 / ColorTemperature (ColorTemperature in the
     * range 1 to 65279 inclusive, giving a color temperature range from 1,000,000
     * Kelvins to 15.32 Kelvins).
     * <p>
     * The value ColorTemperature = 0 indicates an undefined value. The value
     * ColorTemperature = 65535 indicates an invalid value.
     */
    public static final int ATTR_COLORTEMPERATURE = 0x0007;
    /**
     * The ColorMode attribute indicates which attributes are currently determining the color of the device.
     * If either the CurrentHue or CurrentSaturation attribute is implemented, this attribute SHALL also be
     * implemented, otherwise it is optional. The value of the ColorMode attribute cannot be written directly
     * - it is set upon reception of another command in to the appropriate mode for that command.
     */
    public static final int ATTR_COLORMODE = 0x0008;
    /**
     * The EnhancedCurrentHueattribute represents non-equidistant steps along the CIE 1931 color
     * triangle, and it provides 16-bits precision. The upper 8 bits of this attribute SHALL be
     * used as an index in the implementation specific XY lookup table to provide the non-equidistance
     * steps (see the ZLL test specification for an example).  The lower 8 bits SHALL be used to
     * interpolate between these steps in a linear way in order to provide color zoom for the user.
     */
    public static final int ATTR_ENHANCEDCURRENTHUE = 0x4000;
    /**
     * The EnhancedColorModeattribute specifies which attributes are currently determining the color of the device.
     * To provide compatibility with standard ZCL, the original ColorModeattribute SHALLindicate ‘CurrentHueand CurrentSaturation’
     * when the light uses the EnhancedCurrentHueattribute.
     */
    public static final int ATTR_ENHANCEDCOLORMODE = 0x4001;
    /**
     * The ColorLoopActive attribute specifies the current active status of the color loop.
     * If this attribute has the value 0x00, the color loop SHALLnot be active. If this attribute
     * has the value 0x01, the color loop SHALL be active. All other values (0x02 – 0xff) are reserved.
     */
    public static final int ATTR_COLORLOOPACTIVE = 0x4002;
    /**
     * The ColorLoopDirection attribute specifies the current direction of the color loop.
     * If this attribute has the value 0x00, the EnhancedCurrentHue attribute SHALL be decremented.
     * If this attribute has the value 0x01, the EnhancedCurrentHue attribute SHALL be incremented.
     * All other values (0x02 – 0xff) are reserved.
     */
    public static final int ATTR_COLORLOOPDIRECTION = 0x4003;
    /**
     * The ColorLoopTime attribute specifies the number of seconds it SHALL take to perform a full
     * color loop, i.e.,to cycle all values of the EnhancedCurrentHue attribute (between 0x0000 and 0xffff).
     */
    public static final int ATTR_COLORLOOPTIME = 0x4004;
    /**
     * The ColorLoopStartEnhancedHueattribute specifies the value of the EnhancedCurrentHue attribute
     * from which the color loop SHALL be started.
     */
    public static final int ATTR_COLORLOOPSTARTHUE = 0x4005;
    /**
     * The ColorLoopStoredEnhancedHue attribute specifies the value of the EnhancedCurrentHue attribute
     * before the color loop was started. Once the color loop is complete, the EnhancedCurrentHue
     * attribute SHALL be restored to this value.
     */
    public static final int ATTR_COLORLOOPSTOREDHUE = 0x4006;
    /**
     * The ColorCapabilitiesattribute specifies the color capabilities of the device supporting the
     * color control cluster.
     * <p>
     * Note:The support of the CurrentXand CurrentYattributes is mandatory regardless of color capabilities.
     */
    public static final int ATTR_COLORCAPABILITIES = 0x400A;
    /**
     * The ColorTempPhysicalMinMiredsattribute indicates the minimum mired value
     * supported by the hardware. ColorTempPhysicalMinMiredscorresponds to the maximum
     * color temperature in kelvins supported by the hardware.
     * ColorTempPhysicalMinMireds ≤ ColorTemperatureMireds
     */
    public static final int ATTR_COLORTEMPERATUREMIN = 0x400B;
    /**
     * The ColorTempPhysicalMaxMiredsattribute indicates the maximum mired value
     * supported by the hard-ware. ColorTempPhysicalMaxMiredscorresponds to the minimum
     * color temperature in kelvins supported by the hardware.
     * ColorTemperatureMireds ≤ ColorTempPhysicalMaxMireds.
     */
    public static final int ATTR_COLORTEMPERATUREMAX = 0x400C;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(19);

        attributeMap.put(ATTR_CURRENTHUE, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_CURRENTHUE, "CurrentHue", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_CURRENTSATURATION, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_CURRENTSATURATION, "CurrentSaturation", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_REMAININGTIME, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_REMAININGTIME, "RemainingTime", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_CURRENTX, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_CURRENTX, "CurrentX", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_CURRENTY, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_CURRENTY, "CurrentY", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_DRIFTCOMPENSATION, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_DRIFTCOMPENSATION, "DriftCompensation", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_COMPENSATIONTEXT, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COMPENSATIONTEXT, "CompensationText", ZclDataType.CHARACTER_STRING, false, true, false, false));
        attributeMap.put(ATTR_COLORTEMPERATURE, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORTEMPERATURE, "ColorTemperature", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_COLORMODE, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORMODE, "ColorMode", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_ENHANCEDCURRENTHUE, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_ENHANCEDCURRENTHUE, "EnhancedCurrentHue", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_ENHANCEDCOLORMODE, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_ENHANCEDCOLORMODE, "EnhancedColorMode", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_COLORLOOPACTIVE, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORLOOPACTIVE, "ColorLoopActive", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_COLORLOOPDIRECTION, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORLOOPDIRECTION, "ColorLoopDirection", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_COLORLOOPTIME, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORLOOPTIME, "ColorLoopTime", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_COLORLOOPSTARTHUE, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORLOOPSTARTHUE, "ColorLoopStartHue", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_COLORLOOPSTOREDHUE, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORLOOPSTOREDHUE, "ColorLoopStoredHue", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_COLORCAPABILITIES, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORCAPABILITIES, "ColorCapabilities", ZclDataType.BITMAP_16_BIT, false, true, false, false));
        attributeMap.put(ATTR_COLORTEMPERATUREMIN, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORTEMPERATUREMIN, "ColorTemperatureMin", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_COLORTEMPERATUREMAX, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORTEMPERATUREMAX, "ColorTemperatureMax", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Color control cluster.
     *
     * @param zigbeeManager {@link ZigBeeNetworkManager}
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint}
     */
    public ZclColorControlCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeManager, zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * Get the <i>CurrentHue</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The CurrentHue attribute contains the current hue value of the light. It is updated
     * as fast as practical during commands that change the hue.
     * <p>
     * The hue in degrees shall be related to the CurrentHue attribute by the relationship
     * Hue = CurrentHue x 360 / 254 (CurrentHue in the range 0 - 254 inclusive)
     * <p>
     * If this attribute is implemented then the CurrentSaturation and ColorMode
     * attributes shall also be implemented.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentHueAsync() {
        return read(attributes.get(ATTR_CURRENTHUE));
    }


    /**
     * Synchronously get the <i>CurrentHue</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The CurrentHue attribute contains the current hue value of the light. It is updated
     * as fast as practical during commands that change the hue.
     * <p>
     * The hue in degrees shall be related to the CurrentHue attribute by the relationship
     * Hue = CurrentHue x 360 / 254 (CurrentHue in the range 0 - 254 inclusive)
     * <p>
     * If this attribute is implemented then the CurrentSaturation and ColorMode
     * attributes shall also be implemented.
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
    public Integer getCurrentHue(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_CURRENTHUE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_CURRENTHUE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_CURRENTHUE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTHUE));
    }


    /**
     * Set reporting for the <i>CurrentHue</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The CurrentHue attribute contains the current hue value of the light. It is updated
     * as fast as practical during commands that change the hue.
     * <p>
     * The hue in degrees shall be related to the CurrentHue attribute by the relationship
     * Hue = CurrentHue x 360 / 254 (CurrentHue in the range 0 - 254 inclusive)
     * <p>
     * If this attribute is implemented then the CurrentSaturation and ColorMode
     * attributes shall also be implemented.
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
    public Future<CommandResult> setCurrentHueReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTHUE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>CurrentSaturation</i> attribute [attribute ID <b>1</b>].
     * <p>
     * The CurrentSaturation attribute holds the current saturation value of the light. It is
     * updated as fast as practical during commands that change the saturation.
     * The saturation shall be related to the CurrentSaturation attribute by the
     * relationship
     * Saturation = CurrentSaturation/254 (CurrentSaturation in the range 0 - 254 inclusive)
     * If this attribute is implemented then the CurrentHue and ColorMode attributes
     * shall also be implemented.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentSaturationAsync() {
        return read(attributes.get(ATTR_CURRENTSATURATION));
    }


    /**
     * Synchronously get the <i>CurrentSaturation</i> attribute [attribute ID <b>1</b>].
     * <p>
     * The CurrentSaturation attribute holds the current saturation value of the light. It is
     * updated as fast as practical during commands that change the saturation.
     * The saturation shall be related to the CurrentSaturation attribute by the
     * relationship
     * Saturation = CurrentSaturation/254 (CurrentSaturation in the range 0 - 254 inclusive)
     * If this attribute is implemented then the CurrentHue and ColorMode attributes
     * shall also be implemented.
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
    public Integer getCurrentSaturation(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_CURRENTSATURATION).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_CURRENTSATURATION).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_CURRENTSATURATION).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTSATURATION));
    }


    /**
     * Set reporting for the <i>CurrentSaturation</i> attribute [attribute ID <b>1</b>].
     * <p>
     * The CurrentSaturation attribute holds the current saturation value of the light. It is
     * updated as fast as practical during commands that change the saturation.
     * The saturation shall be related to the CurrentSaturation attribute by the
     * relationship
     * Saturation = CurrentSaturation/254 (CurrentSaturation in the range 0 - 254 inclusive)
     * If this attribute is implemented then the CurrentHue and ColorMode attributes
     * shall also be implemented.
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
    public Future<CommandResult> setCurrentSaturationReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTSATURATION), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RemainingTime</i> attribute [attribute ID <b>2</b>].
     * <p>
     * The RemainingTime attribute holds the time remaining, in 1/10ths of a second,
     * until the currently active command will be complete.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRemainingTimeAsync() {
        return read(attributes.get(ATTR_REMAININGTIME));
    }


    /**
     * Synchronously get the <i>RemainingTime</i> attribute [attribute ID <b>2</b>].
     * <p>
     * The RemainingTime attribute holds the time remaining, in 1/10ths of a second,
     * until the currently active command will be complete.
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
    public Integer getRemainingTime(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_REMAININGTIME).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_REMAININGTIME).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_REMAININGTIME).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_REMAININGTIME));
    }

    /**
     * Get the <i>CurrentX</i> attribute [attribute ID <b>3</b>].
     * <p>
     * The CurrentX attribute contains the current value of the normalized chromaticity
     * value x, as defined in the CIE xyY Color Space. It is updated as fast as practical
     * during commands that change the color.
     * <p>
     * The value of x shall be related to the CurrentX attribute by the relationship
     * <p>
     * x = CurrentX / 65535 (CurrentX in the range 0 to 65279 inclusive)
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentXAsync() {
        return read(attributes.get(ATTR_CURRENTX));
    }


    /**
     * Synchronously get the <i>CurrentX</i> attribute [attribute ID <b>3</b>].
     * <p>
     * The CurrentX attribute contains the current value of the normalized chromaticity
     * value x, as defined in the CIE xyY Color Space. It is updated as fast as practical
     * during commands that change the color.
     * <p>
     * The value of x shall be related to the CurrentX attribute by the relationship
     * <p>
     * x = CurrentX / 65535 (CurrentX in the range 0 to 65279 inclusive)
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
    public Integer getCurrentX(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_CURRENTX).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_CURRENTX).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_CURRENTX).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTX));
    }


    /**
     * Set reporting for the <i>CurrentX</i> attribute [attribute ID <b>3</b>].
     * <p>
     * The CurrentX attribute contains the current value of the normalized chromaticity
     * value x, as defined in the CIE xyY Color Space. It is updated as fast as practical
     * during commands that change the color.
     * <p>
     * The value of x shall be related to the CurrentX attribute by the relationship
     * <p>
     * x = CurrentX / 65535 (CurrentX in the range 0 to 65279 inclusive)
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
    public Future<CommandResult> setCurrentXReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTX), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>CurrentY</i> attribute [attribute ID <b>4</b>].
     * <p>
     * The CurrentY attribute contains the current value of the normalized chromaticity
     * value y, as defined in the CIE xyY Color Space. It is updated as fast as practical
     * during commands that change the color.
     * <p>
     * The value of y shall be related to the CurrentY attribute by the relationship
     * <p>
     * y = CurrentY / 65535 (CurrentY in the range 0 to 65279 inclusive)
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentYAsync() {
        return read(attributes.get(ATTR_CURRENTY));
    }


    /**
     * Synchronously get the <i>CurrentY</i> attribute [attribute ID <b>4</b>].
     * <p>
     * The CurrentY attribute contains the current value of the normalized chromaticity
     * value y, as defined in the CIE xyY Color Space. It is updated as fast as practical
     * during commands that change the color.
     * <p>
     * The value of y shall be related to the CurrentY attribute by the relationship
     * <p>
     * y = CurrentY / 65535 (CurrentY in the range 0 to 65279 inclusive)
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
    public Integer getCurrentY(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_CURRENTY).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_CURRENTY).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_CURRENTY).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTY));
    }


    /**
     * Set reporting for the <i>CurrentY</i> attribute [attribute ID <b>4</b>].
     * <p>
     * The CurrentY attribute contains the current value of the normalized chromaticity
     * value y, as defined in the CIE xyY Color Space. It is updated as fast as practical
     * during commands that change the color.
     * <p>
     * The value of y shall be related to the CurrentY attribute by the relationship
     * <p>
     * y = CurrentY / 65535 (CurrentY in the range 0 to 65279 inclusive)
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
    public Future<CommandResult> setCurrentYReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTY), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>DriftCompensation</i> attribute [attribute ID <b>5</b>].
     * <p>
     * The DriftCompensation attribute indicates what mechanism, if any, is in use for
     * compensation for color/intensity drift over time.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDriftCompensationAsync() {
        return read(attributes.get(ATTR_DRIFTCOMPENSATION));
    }


    /**
     * Synchronously get the <i>DriftCompensation</i> attribute [attribute ID <b>5</b>].
     * <p>
     * The DriftCompensation attribute indicates what mechanism, if any, is in use for
     * compensation for color/intensity drift over time.
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
    public Integer getDriftCompensation(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_DRIFTCOMPENSATION).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_DRIFTCOMPENSATION).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_DRIFTCOMPENSATION).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_DRIFTCOMPENSATION));
    }

    /**
     * Get the <i>CompensationText</i> attribute [attribute ID <b>6</b>].
     * <p>
     * The CompensationText attribute holds a textual indication of what mechanism, if
     * any, is in use to compensate for color/intensity drift over time.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCompensationTextAsync() {
        return read(attributes.get(ATTR_COMPENSATIONTEXT));
    }


    /**
     * Synchronously get the <i>CompensationText</i> attribute [attribute ID <b>6</b>].
     * <p>
     * The CompensationText attribute holds a textual indication of what mechanism, if
     * any, is in use to compensate for color/intensity drift over time.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link String} attribute value, or null on error
     */
    public String getCompensationText(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_COMPENSATIONTEXT).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_COMPENSATIONTEXT).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (String) attributes.get(ATTR_COMPENSATIONTEXT).getLastValue();
            }
        }

        return (String) readSync(attributes.get(ATTR_COMPENSATIONTEXT));
    }

    /**
     * Get the <i>ColorTemperature</i> attribute [attribute ID <b>7</b>].
     * <p>
     * The ColorTemperature attribute contains a scaled inverse of the current value of
     * the color temperature. It is updated as fast as practical during commands that
     * change the color.
     * <p>
     * The color temperature value in Kelvins shall be related to the ColorTemperature
     * attribute by the relationship
     * <p>
     * Color temperature = 1,000,000 / ColorTemperature (ColorTemperature in the
     * range 1 to 65279 inclusive, giving a color temperature range from 1,000,000
     * Kelvins to 15.32 Kelvins).
     * <p>
     * The value ColorTemperature = 0 indicates an undefined value. The value
     * ColorTemperature = 65535 indicates an invalid value.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getColorTemperatureAsync() {
        return read(attributes.get(ATTR_COLORTEMPERATURE));
    }


    /**
     * Synchronously get the <i>ColorTemperature</i> attribute [attribute ID <b>7</b>].
     * <p>
     * The ColorTemperature attribute contains a scaled inverse of the current value of
     * the color temperature. It is updated as fast as practical during commands that
     * change the color.
     * <p>
     * The color temperature value in Kelvins shall be related to the ColorTemperature
     * attribute by the relationship
     * <p>
     * Color temperature = 1,000,000 / ColorTemperature (ColorTemperature in the
     * range 1 to 65279 inclusive, giving a color temperature range from 1,000,000
     * Kelvins to 15.32 Kelvins).
     * <p>
     * The value ColorTemperature = 0 indicates an undefined value. The value
     * ColorTemperature = 65535 indicates an invalid value.
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
    public Integer getColorTemperature(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_COLORTEMPERATURE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_COLORTEMPERATURE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_COLORTEMPERATURE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_COLORTEMPERATURE));
    }


    /**
     * Set reporting for the <i>ColorTemperature</i> attribute [attribute ID <b>7</b>].
     * <p>
     * The ColorTemperature attribute contains a scaled inverse of the current value of
     * the color temperature. It is updated as fast as practical during commands that
     * change the color.
     * <p>
     * The color temperature value in Kelvins shall be related to the ColorTemperature
     * attribute by the relationship
     * <p>
     * Color temperature = 1,000,000 / ColorTemperature (ColorTemperature in the
     * range 1 to 65279 inclusive, giving a color temperature range from 1,000,000
     * Kelvins to 15.32 Kelvins).
     * <p>
     * The value ColorTemperature = 0 indicates an undefined value. The value
     * ColorTemperature = 65535 indicates an invalid value.
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
    public Future<CommandResult> setColorTemperatureReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_COLORTEMPERATURE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>ColorMode</i> attribute [attribute ID <b>8</b>].
     * <p>
     * The ColorMode attribute indicates which attributes are currently determining the color of the device.
     * If either the CurrentHue or CurrentSaturation attribute is implemented, this attribute SHALL also be
     * implemented, otherwise it is optional. The value of the ColorMode attribute cannot be written directly
     * - it is set upon reception of another command in to the appropriate mode for that command.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getColorModeAsync() {
        return read(attributes.get(ATTR_COLORMODE));
    }


    /**
     * Synchronously get the <i>ColorMode</i> attribute [attribute ID <b>8</b>].
     * <p>
     * The ColorMode attribute indicates which attributes are currently determining the color of the device.
     * If either the CurrentHue or CurrentSaturation attribute is implemented, this attribute SHALL also be
     * implemented, otherwise it is optional. The value of the ColorMode attribute cannot be written directly
     * - it is set upon reception of another command in to the appropriate mode for that command.
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
    public Integer getColorMode(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_COLORMODE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_COLORMODE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_COLORMODE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_COLORMODE));
    }

    /**
     * Get the <i>EnhancedCurrentHue</i> attribute [attribute ID <b>16384</b>].
     * <p>
     * The EnhancedCurrentHueattribute represents non-equidistant steps along the CIE 1931 color
     * triangle, and it provides 16-bits precision. The upper 8 bits of this attribute SHALL be
     * used as an index in the implementation specific XY lookup table to provide the non-equidistance
     * steps (see the ZLL test specification for an example).  The lower 8 bits SHALL be used to
     * interpolate between these steps in a linear way in order to provide color zoom for the user.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getEnhancedCurrentHueAsync() {
        return read(attributes.get(ATTR_ENHANCEDCURRENTHUE));
    }


    /**
     * Synchronously get the <i>EnhancedCurrentHue</i> attribute [attribute ID <b>16384</b>].
     * <p>
     * The EnhancedCurrentHueattribute represents non-equidistant steps along the CIE 1931 color
     * triangle, and it provides 16-bits precision. The upper 8 bits of this attribute SHALL be
     * used as an index in the implementation specific XY lookup table to provide the non-equidistance
     * steps (see the ZLL test specification for an example).  The lower 8 bits SHALL be used to
     * interpolate between these steps in a linear way in order to provide color zoom for the user.
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
    public Integer getEnhancedCurrentHue(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_ENHANCEDCURRENTHUE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_ENHANCEDCURRENTHUE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_ENHANCEDCURRENTHUE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_ENHANCEDCURRENTHUE));
    }


    /**
     * Set reporting for the <i>EnhancedCurrentHue</i> attribute [attribute ID <b>16384</b>].
     * <p>
     * The EnhancedCurrentHueattribute represents non-equidistant steps along the CIE 1931 color
     * triangle, and it provides 16-bits precision. The upper 8 bits of this attribute SHALL be
     * used as an index in the implementation specific XY lookup table to provide the non-equidistance
     * steps (see the ZLL test specification for an example).  The lower 8 bits SHALL be used to
     * interpolate between these steps in a linear way in order to provide color zoom for the user.
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
    public Future<CommandResult> setEnhancedCurrentHueReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_ENHANCEDCURRENTHUE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>EnhancedColorMode</i> attribute [attribute ID <b>16385</b>].
     * <p>
     * The EnhancedColorModeattribute specifies which attributes are currently determining the color of the device.
     * To provide compatibility with standard ZCL, the original ColorModeattribute SHALLindicate ‘CurrentHueand CurrentSaturation’
     * when the light uses the EnhancedCurrentHueattribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getEnhancedColorModeAsync() {
        return read(attributes.get(ATTR_ENHANCEDCOLORMODE));
    }


    /**
     * Synchronously get the <i>EnhancedColorMode</i> attribute [attribute ID <b>16385</b>].
     * <p>
     * The EnhancedColorModeattribute specifies which attributes are currently determining the color of the device.
     * To provide compatibility with standard ZCL, the original ColorModeattribute SHALLindicate ‘CurrentHueand CurrentSaturation’
     * when the light uses the EnhancedCurrentHueattribute.
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
    public Integer getEnhancedColorMode(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_ENHANCEDCOLORMODE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_ENHANCEDCOLORMODE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_ENHANCEDCOLORMODE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_ENHANCEDCOLORMODE));
    }

    /**
     * Get the <i>ColorLoopActive</i> attribute [attribute ID <b>16386</b>].
     * <p>
     * The ColorLoopActive attribute specifies the current active status of the color loop.
     * If this attribute has the value 0x00, the color loop SHALLnot be active. If this attribute
     * has the value 0x01, the color loop SHALL be active. All other values (0x02 – 0xff) are reserved.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getColorLoopActiveAsync() {
        return read(attributes.get(ATTR_COLORLOOPACTIVE));
    }


    /**
     * Synchronously get the <i>ColorLoopActive</i> attribute [attribute ID <b>16386</b>].
     * <p>
     * The ColorLoopActive attribute specifies the current active status of the color loop.
     * If this attribute has the value 0x00, the color loop SHALLnot be active. If this attribute
     * has the value 0x01, the color loop SHALL be active. All other values (0x02 – 0xff) are reserved.
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
    public Integer getColorLoopActive(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_COLORLOOPACTIVE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_COLORLOOPACTIVE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_COLORLOOPACTIVE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_COLORLOOPACTIVE));
    }

    /**
     * Get the <i>ColorLoopDirection</i> attribute [attribute ID <b>16387</b>].
     * <p>
     * The ColorLoopDirection attribute specifies the current direction of the color loop.
     * If this attribute has the value 0x00, the EnhancedCurrentHue attribute SHALL be decremented.
     * If this attribute has the value 0x01, the EnhancedCurrentHue attribute SHALL be incremented.
     * All other values (0x02 – 0xff) are reserved.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getColorLoopDirectionAsync() {
        return read(attributes.get(ATTR_COLORLOOPDIRECTION));
    }


    /**
     * Synchronously get the <i>ColorLoopDirection</i> attribute [attribute ID <b>16387</b>].
     * <p>
     * The ColorLoopDirection attribute specifies the current direction of the color loop.
     * If this attribute has the value 0x00, the EnhancedCurrentHue attribute SHALL be decremented.
     * If this attribute has the value 0x01, the EnhancedCurrentHue attribute SHALL be incremented.
     * All other values (0x02 – 0xff) are reserved.
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
    public Integer getColorLoopDirection(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_COLORLOOPDIRECTION).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_COLORLOOPDIRECTION).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_COLORLOOPDIRECTION).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_COLORLOOPDIRECTION));
    }

    /**
     * Get the <i>ColorLoopTime</i> attribute [attribute ID <b>16388</b>].
     * <p>
     * The ColorLoopTime attribute specifies the number of seconds it SHALL take to perform a full
     * color loop, i.e.,to cycle all values of the EnhancedCurrentHue attribute (between 0x0000 and 0xffff).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getColorLoopTimeAsync() {
        return read(attributes.get(ATTR_COLORLOOPTIME));
    }


    /**
     * Synchronously get the <i>ColorLoopTime</i> attribute [attribute ID <b>16388</b>].
     * <p>
     * The ColorLoopTime attribute specifies the number of seconds it SHALL take to perform a full
     * color loop, i.e.,to cycle all values of the EnhancedCurrentHue attribute (between 0x0000 and 0xffff).
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
    public Integer getColorLoopTime(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_COLORLOOPTIME).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_COLORLOOPTIME).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_COLORLOOPTIME).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_COLORLOOPTIME));
    }

    /**
     * Get the <i>ColorLoopStartHue</i> attribute [attribute ID <b>16389</b>].
     * <p>
     * The ColorLoopStartEnhancedHueattribute specifies the value of the EnhancedCurrentHue attribute
     * from which the color loop SHALL be started.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getColorLoopStartHueAsync() {
        return read(attributes.get(ATTR_COLORLOOPSTARTHUE));
    }


    /**
     * Synchronously get the <i>ColorLoopStartHue</i> attribute [attribute ID <b>16389</b>].
     * <p>
     * The ColorLoopStartEnhancedHueattribute specifies the value of the EnhancedCurrentHue attribute
     * from which the color loop SHALL be started.
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
    public Integer getColorLoopStartHue(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_COLORLOOPSTARTHUE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_COLORLOOPSTARTHUE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_COLORLOOPSTARTHUE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_COLORLOOPSTARTHUE));
    }

    /**
     * Get the <i>ColorLoopStoredHue</i> attribute [attribute ID <b>16390</b>].
     * <p>
     * The ColorLoopStoredEnhancedHue attribute specifies the value of the EnhancedCurrentHue attribute
     * before the color loop was started. Once the color loop is complete, the EnhancedCurrentHue
     * attribute SHALL be restored to this value.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getColorLoopStoredHueAsync() {
        return read(attributes.get(ATTR_COLORLOOPSTOREDHUE));
    }


    /**
     * Synchronously get the <i>ColorLoopStoredHue</i> attribute [attribute ID <b>16390</b>].
     * <p>
     * The ColorLoopStoredEnhancedHue attribute specifies the value of the EnhancedCurrentHue attribute
     * before the color loop was started. Once the color loop is complete, the EnhancedCurrentHue
     * attribute SHALL be restored to this value.
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
    public Integer getColorLoopStoredHue(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_COLORLOOPSTOREDHUE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_COLORLOOPSTOREDHUE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_COLORLOOPSTOREDHUE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_COLORLOOPSTOREDHUE));
    }

    /**
     * Get the <i>ColorCapabilities</i> attribute [attribute ID <b>16394</b>].
     * <p>
     * The ColorCapabilitiesattribute specifies the color capabilities of the device supporting the
     * color control cluster.
     * <p>
     * Note:The support of the CurrentXand CurrentYattributes is mandatory regardless of color capabilities.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getColorCapabilitiesAsync() {
        return read(attributes.get(ATTR_COLORCAPABILITIES));
    }


    /**
     * Synchronously get the <i>ColorCapabilities</i> attribute [attribute ID <b>16394</b>].
     * <p>
     * The ColorCapabilitiesattribute specifies the color capabilities of the device supporting the
     * color control cluster.
     * <p>
     * Note:The support of the CurrentXand CurrentYattributes is mandatory regardless of color capabilities.
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
    public Integer getColorCapabilities(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_COLORCAPABILITIES).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_COLORCAPABILITIES).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_COLORCAPABILITIES).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_COLORCAPABILITIES));
    }

    /**
     * Get the <i>ColorTemperatureMin</i> attribute [attribute ID <b>16395</b>].
     * <p>
     * The ColorTempPhysicalMinMiredsattribute indicates the minimum mired value
     * supported by the hardware. ColorTempPhysicalMinMiredscorresponds to the maximum
     * color temperature in kelvins supported by the hardware.
     * ColorTempPhysicalMinMireds ≤ ColorTemperatureMireds
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getColorTemperatureMinAsync() {
        return read(attributes.get(ATTR_COLORTEMPERATUREMIN));
    }


    /**
     * Synchronously get the <i>ColorTemperatureMin</i> attribute [attribute ID <b>16395</b>].
     * <p>
     * The ColorTempPhysicalMinMiredsattribute indicates the minimum mired value
     * supported by the hardware. ColorTempPhysicalMinMiredscorresponds to the maximum
     * color temperature in kelvins supported by the hardware.
     * ColorTempPhysicalMinMireds ≤ ColorTemperatureMireds
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
    public Integer getColorTemperatureMin(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_COLORTEMPERATUREMIN).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_COLORTEMPERATUREMIN).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_COLORTEMPERATUREMIN).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_COLORTEMPERATUREMIN));
    }

    /**
     * Get the <i>ColorTemperatureMax</i> attribute [attribute ID <b>16396</b>].
     * <p>
     * The ColorTempPhysicalMaxMiredsattribute indicates the maximum mired value
     * supported by the hard-ware. ColorTempPhysicalMaxMiredscorresponds to the minimum
     * color temperature in kelvins supported by the hardware.
     * ColorTemperatureMireds ≤ ColorTempPhysicalMaxMireds.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getColorTemperatureMaxAsync() {
        return read(attributes.get(ATTR_COLORTEMPERATUREMAX));
    }


    /**
     * Synchronously get the <i>ColorTemperatureMax</i> attribute [attribute ID <b>16396</b>].
     * <p>
     * The ColorTempPhysicalMaxMiredsattribute indicates the maximum mired value
     * supported by the hard-ware. ColorTempPhysicalMaxMiredscorresponds to the minimum
     * color temperature in kelvins supported by the hardware.
     * ColorTemperatureMireds ≤ ColorTempPhysicalMaxMireds.
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
    public Integer getColorTemperatureMax(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_COLORTEMPERATUREMAX).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_COLORTEMPERATUREMAX).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_COLORTEMPERATUREMAX).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_COLORTEMPERATUREMAX));
    }

    /**
     * The Move to Hue Command
     *
     * @param hue {@link Integer} Hue
     * @param direction {@link Integer} Direction
     * @param transitionTime {@link Integer} Transition time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> moveToHueCommand(Integer hue, Integer direction, Integer transitionTime) {
        MoveToHueCommand command = new MoveToHueCommand();

        // Set the fields
        command.setHue(hue);
        command.setDirection(direction);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Move Hue Command
     *
     * @param moveMode {@link Integer} Move mode
     * @param rate {@link Integer} Rate
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> moveHueCommand(Integer moveMode, Integer rate) {
        MoveHueCommand command = new MoveHueCommand();

        // Set the fields
        command.setMoveMode(moveMode);
        command.setRate(rate);

        return send(command);
    }

    /**
     * The Step Hue Command
     *
     * @param stepMode {@link Integer} Step mode
     * @param stepSize {@link Integer} Step size
     * @param transitionTime {@link Integer} Transition time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> stepHueCommand(Integer stepMode, Integer stepSize, Integer transitionTime) {
        StepHueCommand command = new StepHueCommand();

        // Set the fields
        command.setStepMode(stepMode);
        command.setStepSize(stepSize);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Move to Saturation Command
     *
     * @param saturation {@link Integer} Saturation
     * @param transitionTime {@link Integer} Transition time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> moveToSaturationCommand(Integer saturation, Integer transitionTime) {
        MoveToSaturationCommand command = new MoveToSaturationCommand();

        // Set the fields
        command.setSaturation(saturation);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Move Saturation Command
     *
     * @param moveMode {@link Integer} Move mode
     * @param rate {@link Integer} Rate
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> moveSaturationCommand(Integer moveMode, Integer rate) {
        MoveSaturationCommand command = new MoveSaturationCommand();

        // Set the fields
        command.setMoveMode(moveMode);
        command.setRate(rate);

        return send(command);
    }

    /**
     * The Step Saturation Command
     *
     * @param stepMode {@link Integer} Step mode
     * @param stepSize {@link Integer} Step size
     * @param transitionTime {@link Integer} Transition time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> stepSaturationCommand(Integer stepMode, Integer stepSize, Integer transitionTime) {
        StepSaturationCommand command = new StepSaturationCommand();

        // Set the fields
        command.setStepMode(stepMode);
        command.setStepSize(stepSize);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Move to Hue and Saturation Command
     *
     * @param hue {@link Integer} Hue
     * @param saturation {@link Integer} Saturation
     * @param transitionTime {@link Integer} Transition time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> moveToHueAndSaturationCommand(Integer hue, Integer saturation, Integer transitionTime) {
        MoveToHueAndSaturationCommand command = new MoveToHueAndSaturationCommand();

        // Set the fields
        command.setHue(hue);
        command.setSaturation(saturation);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Move to Color Command
     *
     * @param colorX {@link Integer} ColorX
     * @param colorY {@link Integer} ColorY
     * @param transitionTime {@link Integer} Transition time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> moveToColorCommand(Integer colorX, Integer colorY, Integer transitionTime) {
        MoveToColorCommand command = new MoveToColorCommand();

        // Set the fields
        command.setColorX(colorX);
        command.setColorY(colorY);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Move Color Command
     *
     * @param rateX {@link Integer} RateX
     * @param rateY {@link Integer} RateY
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> moveColorCommand(Integer rateX, Integer rateY) {
        MoveColorCommand command = new MoveColorCommand();

        // Set the fields
        command.setRateX(rateX);
        command.setRateY(rateY);

        return send(command);
    }

    /**
     * The Step Color Command
     *
     * @param stepX {@link Integer} StepX
     * @param stepY {@link Integer} StepY
     * @param transitionTime {@link Integer} Transition time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> stepColorCommand(Integer stepX, Integer stepY, Integer transitionTime) {
        StepColorCommand command = new StepColorCommand();

        // Set the fields
        command.setStepX(stepX);
        command.setStepY(stepY);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Move to Color Temperature Command
     *
     * @param colorTemperature {@link Integer} Color Temperature
     * @param transitionTime {@link Integer} Transition time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> moveToColorTemperatureCommand(Integer colorTemperature, Integer transitionTime) {
        MoveToColorTemperatureCommand command = new MoveToColorTemperatureCommand();

        // Set the fields
        command.setColorTemperature(colorTemperature);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Enhanced Move To Hue Command
     *
     * @param hue {@link Integer} Hue
     * @param direction {@link Integer} Direction
     * @param transitionTime {@link Integer} Transition time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> enhancedMoveToHueCommand(Integer hue, Integer direction, Integer transitionTime) {
        EnhancedMoveToHueCommand command = new EnhancedMoveToHueCommand();

        // Set the fields
        command.setHue(hue);
        command.setDirection(direction);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Enhanced Step Hue Command
     *
     * @param stepMode {@link Integer} Step Mode
     * @param stepSize {@link Integer} Step Size
     * @param transitionTime {@link Integer} Transition time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> enhancedStepHueCommand(Integer stepMode, Integer stepSize, Integer transitionTime) {
        EnhancedStepHueCommand command = new EnhancedStepHueCommand();

        // Set the fields
        command.setStepMode(stepMode);
        command.setStepSize(stepSize);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Enhanced Move To Hue and Saturation Command
     *
     * @param hue {@link Integer} Hue
     * @param saturation {@link Integer} Saturation
     * @param transitionTime {@link Integer} Transition time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> enhancedMoveToHueAndSaturationCommand(Integer hue, Integer saturation, Integer transitionTime) {
        EnhancedMoveToHueAndSaturationCommand command = new EnhancedMoveToHueAndSaturationCommand();

        // Set the fields
        command.setHue(hue);
        command.setSaturation(saturation);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Color Loop Set Command
     *
     * @param updateFlags {@link Integer} Update Flags
     * @param action {@link Integer} Action
     * @param direction {@link Integer} Direction
     * @param transitionTime {@link Integer} Transition time
     * @param startHue {@link Integer} Start Hue
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> colorLoopSetCommand(Integer updateFlags, Integer action, Integer direction, Integer transitionTime, Integer startHue) {
        ColorLoopSetCommand command = new ColorLoopSetCommand();

        // Set the fields
        command.setUpdateFlags(updateFlags);
        command.setAction(action);
        command.setDirection(direction);
        command.setTransitionTime(transitionTime);
        command.setStartHue(startHue);

        return send(command);
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0: // MOVE_TO_HUE_COMMAND
                return new MoveToHueCommand();
            case 1: // MOVE_HUE_COMMAND
                return new MoveHueCommand();
            case 2: // STEP_HUE_COMMAND
                return new StepHueCommand();
            case 3: // MOVE_TO_SATURATION_COMMAND
                return new MoveToSaturationCommand();
            case 4: // MOVE_SATURATION_COMMAND
                return new MoveSaturationCommand();
            case 5: // STEP_SATURATION_COMMAND
                return new StepSaturationCommand();
            case 6: // MOVE_TO_HUE_AND_SATURATION_COMMAND
                return new MoveToHueAndSaturationCommand();
            case 7: // MOVE_TO_COLOR_COMMAND
                return new MoveToColorCommand();
            case 8: // MOVE_COLOR_COMMAND
                return new MoveColorCommand();
            case 9: // STEP_COLOR_COMMAND
                return new StepColorCommand();
            case 10: // MOVE_TO_COLOR_TEMPERATURE_COMMAND
                return new MoveToColorTemperatureCommand();
            case 64: // ENHANCED_MOVE_TO_HUE_COMMAND
                return new EnhancedMoveToHueCommand();
            case 65: // ENHANCED_STEP_HUE_COMMAND
                return new EnhancedStepHueCommand();
            case 66: // ENHANCED_MOVE_TO_HUE_AND_SATURATION_COMMAND
                return new EnhancedMoveToHueAndSaturationCommand();
            case 67: // COLOR_LOOP_SET_COMMAND
                return new ColorLoopSetCommand();
            default:
                return null;
        }
    }
}
