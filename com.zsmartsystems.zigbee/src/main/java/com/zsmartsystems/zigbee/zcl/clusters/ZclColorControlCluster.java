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

/**
 * <b>Color Control</b> cluster implementation (<i>Cluster ID 0x0300</i>).
 * <p>
 * This cluster provides an interface for changing the color of a light. Color is specified
 * according to the Commission Internationale de l'Éclairage (CIE) specification CIE 1931
 * Color Space, [B4]. Color control is carried out in terms of x,y values, as defined by this
 * specification.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:39:00Z")
public class ZclColorControlCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0300;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Color Control";

    // Attribute constants
    /**
     * The CurrentHue attribute contains the current hue value of the light. It is updated as
     * fast as practical during commands that change the hue.
     * <p>
     * The hue in degrees shall be related to the CurrentHue attribute by the relationship Hue =
     * CurrentHue x 360 / 254 (CurrentHue in the range 0 - 254 inclusive)
     * <p>
     * If this attribute is implemented then the CurrentSaturation and ColorMode attributes
     * shall also be implemented.
     */
    public static final int ATTR_CURRENTHUE = 0x0000;
    /**
     * The CurrentSaturation attribute holds the current saturation value of the light. It is
     * updated as fast as practical during commands that change the saturation. The
     * saturation shall be related to the CurrentSaturation attribute by the relationship
     * Saturation = CurrentSaturation/254 (CurrentSaturation in the range 0 - 254
     * inclusive) If this attribute is implemented then the CurrentHue and ColorMode
     * attributes shall also be implemented.
     */
    public static final int ATTR_CURRENTSATURATION = 0x0001;
    /**
     * The RemainingTime attribute holds the time remaining, in 1/10ths of a second, until the
     * currently active command will be complete.
     */
    public static final int ATTR_REMAININGTIME = 0x0002;
    /**
     * The CurrentX attribute contains the current value of the normalized chromaticity
     * value x, as defined in the CIE xyY Color Space. It is updated as fast as practical during
     * commands that change the color.
     * <p>
     * The value of x shall be related to the CurrentX attribute by the relationship
     * <p>
     * x = CurrentX / 65535 (CurrentX in the range 0 to 65279 inclusive)
     */
    public static final int ATTR_CURRENTX = 0x0003;
    /**
     * The CurrentY attribute contains the current value of the normalized chromaticity
     * value y, as defined in the CIE xyY Color Space. It is updated as fast as practical during
     * commands that change the color.
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
     * The CompensationText attribute holds a textual indication of what mechanism, if any,
     * is in use to compensate for color/intensity drift over time.
     */
    public static final int ATTR_COMPENSATIONTEXT = 0x0006;
    /**
     * The ColorTemperature attribute contains a scaled inverse of the current value of the
     * color temperature. It is updated as fast as practical during commands that change the
     * color.
     * <p>
     * The color temperature value in Kelvins shall be related to the ColorTemperature
     * attribute by the relationship
     * <p>
     * Color temperature = 1,000,000 / ColorTemperature (ColorTemperature in the range 1 to
     * 65279 inclusive, giving a color temperature range from 1,000,000 Kelvins to 15.32
     * Kelvins).
     * <p>
     * The value ColorTemperature = 0 indicates an undefined value. The value
     * ColorTemperature = 65535 indicates an invalid value.
     */
    public static final int ATTR_COLORTEMPERATURE = 0x0007;
    /**
     * The ColorMode attribute indicates which attributes are currently determining the
     * color of the device. If either the CurrentHue or CurrentSaturation attribute is
     * implemented, this attribute shall also be implemented, otherwise it is optional. The
     * value of the ColorMode attribute cannot be written directly - it is set upon reception of
     * another command in to the appropriate mode for that command.
     */
    public static final int ATTR_COLORMODE = 0x0008;
    /**
     * The EnhancedCurrentHueattribute represents non-equidistant steps along the CIE
     * 1931 color triangle, and it provides 16-bits precision. The upper 8 bits of this
     * attribute shall be used as an index in the implementation specific XY lookup table to
     * provide the non-equidistance steps (see the ZLL test specification for an example).
     * The lower 8 bits shall be used to interpolate between these steps in a linear way in order
     * to provide color zoom for the user.
     */
    public static final int ATTR_ENHANCEDCURRENTHUE = 0x4000;
    /**
     * The EnhancedColorModeattribute specifies which attributes are currently
     * determining the color of the device. To provide compatibility with standard ZCL, the
     * original ColorModeattribute SHALLindicate ‘CurrentHueand CurrentSaturation’
     * when the light uses the EnhancedCurrentHueattribute.
     */
    public static final int ATTR_ENHANCEDCOLORMODE = 0x4001;
    /**
     * The ColorLoopActive attribute specifies the current active status of the color loop.
     * If this attribute has the value 0x00, the color loop SHALLnot be active. If this
     * attribute has the value 0x01, the color loop shall be active. All other values (0x02 –
     * 0xff) are reserved.
     */
    public static final int ATTR_COLORLOOPACTIVE = 0x4002;
    /**
     * The ColorLoopDirection attribute specifies the current direction of the color loop.
     * If this attribute has the value 0x00, the EnhancedCurrentHue attribute shall be
     * decremented. If this attribute has the value 0x01, the EnhancedCurrentHue attribute
     * shall be incremented. All other values (0x02 – 0xff) are reserved.
     */
    public static final int ATTR_COLORLOOPDIRECTION = 0x4003;
    /**
     * The ColorLoopTime attribute specifies the number of seconds it shall take to perform a
     * full color loop, i.e.,to cycle all values of the EnhancedCurrentHue attribute
     * (between 0x0000 and 0xffff).
     */
    public static final int ATTR_COLORLOOPTIME = 0x4004;
    /**
     * The ColorLoopStartEnhancedHueattribute specifies the value of the
     * EnhancedCurrentHue attribute from which the color loop shall be started.
     */
    public static final int ATTR_COLORLOOPSTARTHUE = 0x4005;
    /**
     * The ColorLoopStoredEnhancedHue attribute specifies the value of the
     * EnhancedCurrentHue attribute before the color loop was started. Once the color loop is
     * complete, the EnhancedCurrentHue attribute shall be restored to this value.
     */
    public static final int ATTR_COLORLOOPSTOREDHUE = 0x4006;
    /**
     * The ColorCapabilitiesattribute specifies the color capabilities of the device
     * supporting the color control cluster.
     * <p>
     * Note:The support of the CurrentXand CurrentYattributes is mandatory regardless of
     * color capabilities.
     */
    public static final int ATTR_COLORCAPABILITIES = 0x400A;
    /**
     * The ColorTempPhysicalMinMiredsattribute indicates the minimum mired value
     * supported by the hardware. ColorTempPhysicalMinMiredscorresponds to the maximum
     * color temperature in kelvins supported by the hardware. ColorTempPhysicalMinMireds
     * ≤ ColorTemperatureMireds
     */
    public static final int ATTR_COLORTEMPERATUREMIN = 0x400B;
    /**
     * The ColorTempPhysicalMaxMiredsattribute indicates the maximum mired value
     * supported by the hard-ware. ColorTempPhysicalMaxMiredscorresponds to the minimum
     * color temperature in kelvins supported by the hardware. ColorTemperatureMireds ≤
     * ColorTempPhysicalMaxMireds.
     */
    public static final int ATTR_COLORTEMPERATUREMAX = 0x400C;

    // Attribute initialisation
    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(19);

        attributeMap.put(ATTR_CURRENTHUE, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_CURRENTHUE, "Current Hue", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_CURRENTSATURATION, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_CURRENTSATURATION, "Current Saturation", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_REMAININGTIME, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_REMAININGTIME, "Remaining Time", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_CURRENTX, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_CURRENTX, "Current X", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_CURRENTY, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_CURRENTY, "Current Y", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_DRIFTCOMPENSATION, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_DRIFTCOMPENSATION, "Drift Compensation", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_COMPENSATIONTEXT, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COMPENSATIONTEXT, "Compensation Text", ZclDataType.CHARACTER_STRING, false, true, false, false));
        attributeMap.put(ATTR_COLORTEMPERATURE, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORTEMPERATURE, "Color Temperature", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_COLORMODE, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORMODE, "Color Mode", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_ENHANCEDCURRENTHUE, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_ENHANCEDCURRENTHUE, "Enhanced Current Hue", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_ENHANCEDCOLORMODE, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_ENHANCEDCOLORMODE, "Enhanced Color Mode", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_COLORLOOPACTIVE, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORLOOPACTIVE, "Color Loop Active", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_COLORLOOPDIRECTION, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORLOOPDIRECTION, "Color Loop Direction", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_COLORLOOPTIME, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORLOOPTIME, "Color Loop Time", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_COLORLOOPSTARTHUE, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORLOOPSTARTHUE, "Color Loop Start Hue", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_COLORLOOPSTOREDHUE, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORLOOPSTOREDHUE, "Color Loop Stored Hue", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_COLORCAPABILITIES, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORCAPABILITIES, "Color Capabilities", ZclDataType.BITMAP_16_BIT, false, true, false, false));
        attributeMap.put(ATTR_COLORTEMPERATUREMIN, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORTEMPERATUREMIN, "Color Temperature Min", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_COLORTEMPERATUREMAX, new ZclAttribute(ZclClusterType.COLOR_CONTROL, ATTR_COLORTEMPERATUREMAX, "Color Temperature Max", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Color Control cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclColorControlCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>Current Hue</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The CurrentHue attribute contains the current hue value of the light. It is updated as
     * fast as practical during commands that change the hue.
     * <p>
     * The hue in degrees shall be related to the CurrentHue attribute by the relationship Hue =
     * CurrentHue x 360 / 254 (CurrentHue in the range 0 - 254 inclusive)
     * <p>
     * If this attribute is implemented then the CurrentSaturation and ColorMode attributes
     * shall also be implemented.
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
     * Synchronously get the <i>Current Hue</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The CurrentHue attribute contains the current hue value of the light. It is updated as
     * fast as practical during commands that change the hue.
     * <p>
     * The hue in degrees shall be related to the CurrentHue attribute by the relationship Hue =
     * CurrentHue x 360 / 254 (CurrentHue in the range 0 - 254 inclusive)
     * <p>
     * If this attribute is implemented then the CurrentSaturation and ColorMode attributes
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
    public Integer getCurrentHue(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENTHUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENTHUE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTHUE));
    }

    /**
     * Get the <i>Current Saturation</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The CurrentSaturation attribute holds the current saturation value of the light. It is
     * updated as fast as practical during commands that change the saturation. The
     * saturation shall be related to the CurrentSaturation attribute by the relationship
     * Saturation = CurrentSaturation/254 (CurrentSaturation in the range 0 - 254
     * inclusive) If this attribute is implemented then the CurrentHue and ColorMode
     * attributes shall also be implemented.
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
     * Synchronously get the <i>Current Saturation</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The CurrentSaturation attribute holds the current saturation value of the light. It is
     * updated as fast as practical during commands that change the saturation. The
     * saturation shall be related to the CurrentSaturation attribute by the relationship
     * Saturation = CurrentSaturation/254 (CurrentSaturation in the range 0 - 254
     * inclusive) If this attribute is implemented then the CurrentHue and ColorMode
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
    public Integer getCurrentSaturation(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENTSATURATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENTSATURATION).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTSATURATION));
    }

    /**
     * Get the <i>Remaining Time</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The RemainingTime attribute holds the time remaining, in 1/10ths of a second, until the
     * currently active command will be complete.
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
     * Synchronously get the <i>Remaining Time</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The RemainingTime attribute holds the time remaining, in 1/10ths of a second, until the
     * currently active command will be complete.
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
        if (attributes.get(ATTR_REMAININGTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_REMAININGTIME).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_REMAININGTIME));
    }

    /**
     * Get the <i>Current X</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The CurrentX attribute contains the current value of the normalized chromaticity
     * value x, as defined in the CIE xyY Color Space. It is updated as fast as practical during
     * commands that change the color.
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
     * Synchronously get the <i>Current X</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The CurrentX attribute contains the current value of the normalized chromaticity
     * value x, as defined in the CIE xyY Color Space. It is updated as fast as practical during
     * commands that change the color.
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
        if (attributes.get(ATTR_CURRENTX).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENTX).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTX));
    }

    /**
     * Set reporting for the <i>Current X</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The CurrentX attribute contains the current value of the normalized chromaticity
     * value x, as defined in the CIE xyY Color Space. It is updated as fast as practical during
     * commands that change the color.
     * <p>
     * The value of x shall be related to the CurrentX attribute by the relationship
     * <p>
     * x = CurrentX / 65535 (CurrentX in the range 0 to 65279 inclusive)
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
    public Future<CommandResult> setCurrentXReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTX), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Current Y</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The CurrentY attribute contains the current value of the normalized chromaticity
     * value y, as defined in the CIE xyY Color Space. It is updated as fast as practical during
     * commands that change the color.
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
     * Synchronously get the <i>Current Y</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The CurrentY attribute contains the current value of the normalized chromaticity
     * value y, as defined in the CIE xyY Color Space. It is updated as fast as practical during
     * commands that change the color.
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
        if (attributes.get(ATTR_CURRENTY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENTY).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTY));
    }

    /**
     * Set reporting for the <i>Current Y</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The CurrentY attribute contains the current value of the normalized chromaticity
     * value y, as defined in the CIE xyY Color Space. It is updated as fast as practical during
     * commands that change the color.
     * <p>
     * The value of y shall be related to the CurrentY attribute by the relationship
     * <p>
     * y = CurrentY / 65535 (CurrentY in the range 0 to 65279 inclusive)
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
    public Future<CommandResult> setCurrentYReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTY), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Drift Compensation</i> attribute [attribute ID <b>0x0005</b>].
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
     * Synchronously get the <i>Drift Compensation</i> attribute [attribute ID <b>0x0005</b>].
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
        if (attributes.get(ATTR_DRIFTCOMPENSATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DRIFTCOMPENSATION).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DRIFTCOMPENSATION));
    }

    /**
     * Get the <i>Compensation Text</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * The CompensationText attribute holds a textual indication of what mechanism, if any,
     * is in use to compensate for color/intensity drift over time.
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
     * Synchronously get the <i>Compensation Text</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * The CompensationText attribute holds a textual indication of what mechanism, if any,
     * is in use to compensate for color/intensity drift over time.
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
        if (attributes.get(ATTR_COMPENSATIONTEXT).isLastValueCurrent(refreshPeriod)) {
            return (String) attributes.get(ATTR_COMPENSATIONTEXT).getLastValue();
        }

        return (String) readSync(attributes.get(ATTR_COMPENSATIONTEXT));
    }

    /**
     * Get the <i>Color Temperature</i> attribute [attribute ID <b>0x0007</b>].
     * <p>
     * The ColorTemperature attribute contains a scaled inverse of the current value of the
     * color temperature. It is updated as fast as practical during commands that change the
     * color.
     * <p>
     * The color temperature value in Kelvins shall be related to the ColorTemperature
     * attribute by the relationship
     * <p>
     * Color temperature = 1,000,000 / ColorTemperature (ColorTemperature in the range 1 to
     * 65279 inclusive, giving a color temperature range from 1,000,000 Kelvins to 15.32
     * Kelvins).
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
     * Synchronously get the <i>Color Temperature</i> attribute [attribute ID <b>0x0007</b>].
     * <p>
     * The ColorTemperature attribute contains a scaled inverse of the current value of the
     * color temperature. It is updated as fast as practical during commands that change the
     * color.
     * <p>
     * The color temperature value in Kelvins shall be related to the ColorTemperature
     * attribute by the relationship
     * <p>
     * Color temperature = 1,000,000 / ColorTemperature (ColorTemperature in the range 1 to
     * 65279 inclusive, giving a color temperature range from 1,000,000 Kelvins to 15.32
     * Kelvins).
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
        if (attributes.get(ATTR_COLORTEMPERATURE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_COLORTEMPERATURE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_COLORTEMPERATURE));
    }

    /**
     * Get the <i>Color Mode</i> attribute [attribute ID <b>0x0008</b>].
     * <p>
     * The ColorMode attribute indicates which attributes are currently determining the
     * color of the device. If either the CurrentHue or CurrentSaturation attribute is
     * implemented, this attribute shall also be implemented, otherwise it is optional. The
     * value of the ColorMode attribute cannot be written directly - it is set upon reception of
     * another command in to the appropriate mode for that command.
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
     * Synchronously get the <i>Color Mode</i> attribute [attribute ID <b>0x0008</b>].
     * <p>
     * The ColorMode attribute indicates which attributes are currently determining the
     * color of the device. If either the CurrentHue or CurrentSaturation attribute is
     * implemented, this attribute shall also be implemented, otherwise it is optional. The
     * value of the ColorMode attribute cannot be written directly - it is set upon reception of
     * another command in to the appropriate mode for that command.
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
        if (attributes.get(ATTR_COLORMODE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_COLORMODE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_COLORMODE));
    }

    /**
     * Get the <i>Enhanced Current Hue</i> attribute [attribute ID <b>0x4000</b>].
     * <p>
     * The EnhancedCurrentHueattribute represents non-equidistant steps along the CIE
     * 1931 color triangle, and it provides 16-bits precision. The upper 8 bits of this
     * attribute shall be used as an index in the implementation specific XY lookup table to
     * provide the non-equidistance steps (see the ZLL test specification for an example).
     * The lower 8 bits shall be used to interpolate between these steps in a linear way in order
     * to provide color zoom for the user.
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
     * Synchronously get the <i>Enhanced Current Hue</i> attribute [attribute ID <b>0x4000</b>].
     * <p>
     * The EnhancedCurrentHueattribute represents non-equidistant steps along the CIE
     * 1931 color triangle, and it provides 16-bits precision. The upper 8 bits of this
     * attribute shall be used as an index in the implementation specific XY lookup table to
     * provide the non-equidistance steps (see the ZLL test specification for an example).
     * The lower 8 bits shall be used to interpolate between these steps in a linear way in order
     * to provide color zoom for the user.
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
        if (attributes.get(ATTR_ENHANCEDCURRENTHUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ENHANCEDCURRENTHUE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ENHANCEDCURRENTHUE));
    }

    /**
     * Get the <i>Enhanced Color Mode</i> attribute [attribute ID <b>0x4001</b>].
     * <p>
     * The EnhancedColorModeattribute specifies which attributes are currently
     * determining the color of the device. To provide compatibility with standard ZCL, the
     * original ColorModeattribute SHALLindicate ‘CurrentHueand CurrentSaturation’
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
     * Synchronously get the <i>Enhanced Color Mode</i> attribute [attribute ID <b>0x4001</b>].
     * <p>
     * The EnhancedColorModeattribute specifies which attributes are currently
     * determining the color of the device. To provide compatibility with standard ZCL, the
     * original ColorModeattribute SHALLindicate ‘CurrentHueand CurrentSaturation’
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
        if (attributes.get(ATTR_ENHANCEDCOLORMODE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ENHANCEDCOLORMODE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ENHANCEDCOLORMODE));
    }

    /**
     * Get the <i>Color Loop Active</i> attribute [attribute ID <b>0x4002</b>].
     * <p>
     * The ColorLoopActive attribute specifies the current active status of the color loop.
     * If this attribute has the value 0x00, the color loop SHALLnot be active. If this
     * attribute has the value 0x01, the color loop shall be active. All other values (0x02 –
     * 0xff) are reserved.
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
     * Synchronously get the <i>Color Loop Active</i> attribute [attribute ID <b>0x4002</b>].
     * <p>
     * The ColorLoopActive attribute specifies the current active status of the color loop.
     * If this attribute has the value 0x00, the color loop SHALLnot be active. If this
     * attribute has the value 0x01, the color loop shall be active. All other values (0x02 –
     * 0xff) are reserved.
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
        if (attributes.get(ATTR_COLORLOOPACTIVE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_COLORLOOPACTIVE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_COLORLOOPACTIVE));
    }

    /**
     * Get the <i>Color Loop Direction</i> attribute [attribute ID <b>0x4003</b>].
     * <p>
     * The ColorLoopDirection attribute specifies the current direction of the color loop.
     * If this attribute has the value 0x00, the EnhancedCurrentHue attribute shall be
     * decremented. If this attribute has the value 0x01, the EnhancedCurrentHue attribute
     * shall be incremented. All other values (0x02 – 0xff) are reserved.
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
     * Synchronously get the <i>Color Loop Direction</i> attribute [attribute ID <b>0x4003</b>].
     * <p>
     * The ColorLoopDirection attribute specifies the current direction of the color loop.
     * If this attribute has the value 0x00, the EnhancedCurrentHue attribute shall be
     * decremented. If this attribute has the value 0x01, the EnhancedCurrentHue attribute
     * shall be incremented. All other values (0x02 – 0xff) are reserved.
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
        if (attributes.get(ATTR_COLORLOOPDIRECTION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_COLORLOOPDIRECTION).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_COLORLOOPDIRECTION));
    }

    /**
     * Get the <i>Color Loop Time</i> attribute [attribute ID <b>0x4004</b>].
     * <p>
     * The ColorLoopTime attribute specifies the number of seconds it shall take to perform a
     * full color loop, i.e.,to cycle all values of the EnhancedCurrentHue attribute
     * (between 0x0000 and 0xffff).
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
     * Synchronously get the <i>Color Loop Time</i> attribute [attribute ID <b>0x4004</b>].
     * <p>
     * The ColorLoopTime attribute specifies the number of seconds it shall take to perform a
     * full color loop, i.e.,to cycle all values of the EnhancedCurrentHue attribute
     * (between 0x0000 and 0xffff).
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
        if (attributes.get(ATTR_COLORLOOPTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_COLORLOOPTIME).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_COLORLOOPTIME));
    }

    /**
     * Get the <i>Color Loop Start Hue</i> attribute [attribute ID <b>0x4005</b>].
     * <p>
     * The ColorLoopStartEnhancedHueattribute specifies the value of the
     * EnhancedCurrentHue attribute from which the color loop shall be started.
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
     * Synchronously get the <i>Color Loop Start Hue</i> attribute [attribute ID <b>0x4005</b>].
     * <p>
     * The ColorLoopStartEnhancedHueattribute specifies the value of the
     * EnhancedCurrentHue attribute from which the color loop shall be started.
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
        if (attributes.get(ATTR_COLORLOOPSTARTHUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_COLORLOOPSTARTHUE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_COLORLOOPSTARTHUE));
    }

    /**
     * Get the <i>Color Loop Stored Hue</i> attribute [attribute ID <b>0x4006</b>].
     * <p>
     * The ColorLoopStoredEnhancedHue attribute specifies the value of the
     * EnhancedCurrentHue attribute before the color loop was started. Once the color loop is
     * complete, the EnhancedCurrentHue attribute shall be restored to this value.
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
     * Synchronously get the <i>Color Loop Stored Hue</i> attribute [attribute ID <b>0x4006</b>].
     * <p>
     * The ColorLoopStoredEnhancedHue attribute specifies the value of the
     * EnhancedCurrentHue attribute before the color loop was started. Once the color loop is
     * complete, the EnhancedCurrentHue attribute shall be restored to this value.
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
        if (attributes.get(ATTR_COLORLOOPSTOREDHUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_COLORLOOPSTOREDHUE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_COLORLOOPSTOREDHUE));
    }

    /**
     * Get the <i>Color Capabilities</i> attribute [attribute ID <b>0x400A</b>].
     * <p>
     * The ColorCapabilitiesattribute specifies the color capabilities of the device
     * supporting the color control cluster.
     * <p>
     * Note:The support of the CurrentXand CurrentYattributes is mandatory regardless of
     * color capabilities.
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
     * Synchronously get the <i>Color Capabilities</i> attribute [attribute ID <b>0x400A</b>].
     * <p>
     * The ColorCapabilitiesattribute specifies the color capabilities of the device
     * supporting the color control cluster.
     * <p>
     * Note:The support of the CurrentXand CurrentYattributes is mandatory regardless of
     * color capabilities.
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
        if (attributes.get(ATTR_COLORCAPABILITIES).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_COLORCAPABILITIES).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_COLORCAPABILITIES));
    }

    /**
     * Get the <i>Color Temperature Min</i> attribute [attribute ID <b>0x400B</b>].
     * <p>
     * The ColorTempPhysicalMinMiredsattribute indicates the minimum mired value
     * supported by the hardware. ColorTempPhysicalMinMiredscorresponds to the maximum
     * color temperature in kelvins supported by the hardware. ColorTempPhysicalMinMireds
     * ≤ ColorTemperatureMireds
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
     * Synchronously get the <i>Color Temperature Min</i> attribute [attribute ID <b>0x400B</b>].
     * <p>
     * The ColorTempPhysicalMinMiredsattribute indicates the minimum mired value
     * supported by the hardware. ColorTempPhysicalMinMiredscorresponds to the maximum
     * color temperature in kelvins supported by the hardware. ColorTempPhysicalMinMireds
     * ≤ ColorTemperatureMireds
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
        if (attributes.get(ATTR_COLORTEMPERATUREMIN).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_COLORTEMPERATUREMIN).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_COLORTEMPERATUREMIN));
    }

    /**
     * Get the <i>Color Temperature Max</i> attribute [attribute ID <b>0x400C</b>].
     * <p>
     * The ColorTempPhysicalMaxMiredsattribute indicates the maximum mired value
     * supported by the hard-ware. ColorTempPhysicalMaxMiredscorresponds to the minimum
     * color temperature in kelvins supported by the hardware. ColorTemperatureMireds ≤
     * ColorTempPhysicalMaxMireds.
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
     * Synchronously get the <i>Color Temperature Max</i> attribute [attribute ID <b>0x400C</b>].
     * <p>
     * The ColorTempPhysicalMaxMiredsattribute indicates the maximum mired value
     * supported by the hard-ware. ColorTempPhysicalMaxMiredscorresponds to the minimum
     * color temperature in kelvins supported by the hardware. ColorTemperatureMireds ≤
     * ColorTempPhysicalMaxMireds.
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
        if (attributes.get(ATTR_COLORTEMPERATUREMAX).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_COLORTEMPERATUREMAX).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_COLORTEMPERATUREMAX));
    }

    /**
     * The Move To Hue Command
     *
     * @param hue {@link Integer} Hue
     * @param direction {@link Integer} Direction
     * @param transitionTime {@link Integer} Transition Time
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
     * @param moveMode {@link Integer} Move Mode
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
     * @param stepMode {@link Integer} Step Mode
     * @param stepSize {@link Integer} Step Size
     * @param transitionTime {@link Integer} Transition Time
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
     * The Move To Saturation Command
     *
     * @param saturation {@link Integer} Saturation
     * @param transitionTime {@link Integer} Transition Time
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
     * @param moveMode {@link Integer} Move Mode
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
     * @param stepMode {@link Integer} Step Mode
     * @param stepSize {@link Integer} Step Size
     * @param transitionTime {@link Integer} Transition Time
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
     * The Move To Hue And Saturation Command
     *
     * @param hue {@link Integer} Hue
     * @param saturation {@link Integer} Saturation
     * @param transitionTime {@link Integer} Transition Time
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
     * The Move To Color Command
     *
     * @param colorX {@link Integer} Color X
     * @param colorY {@link Integer} Color Y
     * @param transitionTime {@link Integer} Transition Time
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
     * @param rateX {@link Integer} Rate X
     * @param rateY {@link Integer} Rate Y
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
     * @param stepX {@link Integer} Step X
     * @param stepY {@link Integer} Step Y
     * @param transitionTime {@link Integer} Transition Time
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
     * The Move To Color Temperature Command
     *
     * @param colorTemperature {@link Integer} Color Temperature
     * @param transitionTime {@link Integer} Transition Time
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
     * @param transitionTime {@link Integer} Transition Time
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
     * @param transitionTime {@link Integer} Transition Time
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
     * The Enhanced Move To Hue And Saturation Command
     *
     * @param hue {@link Integer} Hue
     * @param saturation {@link Integer} Saturation
     * @param transitionTime {@link Integer} Transition Time
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
     * @param transitionTime {@link Integer} Transition Time
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
    public ZclCommand getResponseFromId(int commandId) {
        switch (commandId) {
            default:
                return null;
        }
    }
}
