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
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.EnhancedMoveHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.EnhancedMoveToHueAndSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.EnhancedMoveToHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.EnhancedStepHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveColorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveColorTemperatureCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToColorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToColorTemperatureCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToHueAndSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StepColorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StepColorTemperatureCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StepHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StepSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StopMoveStepCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Color Control</b> cluster implementation (<i>Cluster ID 0x0300</i>).
 * <p>
 * This cluster provides an interface for changing the color of a light. Color is specified
 * according to the Commission Internationale de l'Éclairage (CIE) specification CIE 1931
 * Color Space. Color control is carried out in terms of x,y values, as defined by this
 * specification.
 * <p>
 * Additionally, color may optionally be controlled in terms of color temperature, or as hue
 * and saturation values based on optionally variable RGB and W color points. It is recommended
 * that the hue and saturation are interpreted according to the HSV (aka HSB) color model.
 * <p>
 * Control over luminance is not included, as this is provided by means of the Level Control
 * cluster of the General library. It is recommended that the level provided by this cluster be
 * interpreted as representing a proportion of the maximum intensity achievable at the
 * current color.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-04-18T19:39:16Z")
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

    @Override
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<>(0);

        return attributeMap;
    }

    @Override
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<>(19);

        attributeMap.put(ATTR_CURRENTHUE, new ZclAttribute(this, ATTR_CURRENTHUE, "Current Hue", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_CURRENTSATURATION, new ZclAttribute(this, ATTR_CURRENTSATURATION, "Current Saturation", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_REMAININGTIME, new ZclAttribute(this, ATTR_REMAININGTIME, "Remaining Time", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_CURRENTX, new ZclAttribute(this, ATTR_CURRENTX, "Current X", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_CURRENTY, new ZclAttribute(this, ATTR_CURRENTY, "Current Y", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_DRIFTCOMPENSATION, new ZclAttribute(this, ATTR_DRIFTCOMPENSATION, "Drift Compensation", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_COMPENSATIONTEXT, new ZclAttribute(this, ATTR_COMPENSATIONTEXT, "Compensation Text", ZclDataType.CHARACTER_STRING, false, true, false, false));
        attributeMap.put(ATTR_COLORTEMPERATURE, new ZclAttribute(this, ATTR_COLORTEMPERATURE, "Color Temperature", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_COLORMODE, new ZclAttribute(this, ATTR_COLORMODE, "Color Mode", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_ENHANCEDCURRENTHUE, new ZclAttribute(this, ATTR_ENHANCEDCURRENTHUE, "Enhanced Current Hue", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_ENHANCEDCOLORMODE, new ZclAttribute(this, ATTR_ENHANCEDCOLORMODE, "Enhanced Color Mode", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_COLORLOOPACTIVE, new ZclAttribute(this, ATTR_COLORLOOPACTIVE, "Color Loop Active", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_COLORLOOPDIRECTION, new ZclAttribute(this, ATTR_COLORLOOPDIRECTION, "Color Loop Direction", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_COLORLOOPTIME, new ZclAttribute(this, ATTR_COLORLOOPTIME, "Color Loop Time", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_COLORLOOPSTARTHUE, new ZclAttribute(this, ATTR_COLORLOOPSTARTHUE, "Color Loop Start Hue", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_COLORLOOPSTOREDHUE, new ZclAttribute(this, ATTR_COLORLOOPSTOREDHUE, "Color Loop Stored Hue", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_COLORCAPABILITIES, new ZclAttribute(this, ATTR_COLORCAPABILITIES, "Color Capabilities", ZclDataType.BITMAP_16_BIT, false, true, false, false));
        attributeMap.put(ATTR_COLORTEMPERATUREMIN, new ZclAttribute(this, ATTR_COLORTEMPERATUREMIN, "Color Temperature Min", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_COLORTEMPERATUREMAX, new ZclAttribute(this, ATTR_COLORTEMPERATUREMAX, "Color Temperature Max", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));

        return attributeMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentHashMap<>(19);

        commandMap.put(0x0000, MoveToHueCommand.class);
        commandMap.put(0x0001, MoveHueCommand.class);
        commandMap.put(0x0002, StepHueCommand.class);
        commandMap.put(0x0003, MoveToSaturationCommand.class);
        commandMap.put(0x0004, MoveSaturationCommand.class);
        commandMap.put(0x0005, StepSaturationCommand.class);
        commandMap.put(0x0006, MoveToHueAndSaturationCommand.class);
        commandMap.put(0x0007, MoveToColorCommand.class);
        commandMap.put(0x0008, MoveColorCommand.class);
        commandMap.put(0x0009, StepColorCommand.class);
        commandMap.put(0x000A, MoveToColorTemperatureCommand.class);
        commandMap.put(0x0040, EnhancedMoveToHueCommand.class);
        commandMap.put(0x0041, EnhancedMoveHueCommand.class);
        commandMap.put(0x0042, EnhancedStepHueCommand.class);
        commandMap.put(0x0043, EnhancedMoveToHueAndSaturationCommand.class);
        commandMap.put(0x0044, ColorLoopSetCommand.class);
        commandMap.put(0x0047, StopMoveStepCommand.class);
        commandMap.put(0x004B, MoveColorTemperatureCommand.class);
        commandMap.put(0x004C, StepColorTemperatureCommand.class);

        return commandMap;
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getCurrentHueAsync() {
        return read(serverAttributes.get(ATTR_CURRENTHUE));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttributeValue(int attributeId, long refreshPeriod)}
     */
    @Deprecated
    public Integer getCurrentHue(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_CURRENTHUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_CURRENTHUE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_CURRENTHUE));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getCurrentSaturationAsync() {
        return read(serverAttributes.get(ATTR_CURRENTSATURATION));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttributeValue(int attributeId, long refreshPeriod)}
     */
    @Deprecated
    public Integer getCurrentSaturation(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_CURRENTSATURATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_CURRENTSATURATION).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_CURRENTSATURATION));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRemainingTimeAsync() {
        return read(serverAttributes.get(ATTR_REMAININGTIME));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttributeValue(int attributeId, long refreshPeriod)}
     */
    @Deprecated
    public Integer getRemainingTime(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_REMAININGTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_REMAININGTIME).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_REMAININGTIME));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getCurrentXAsync() {
        return read(serverAttributes.get(ATTR_CURRENTX));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttributeValue(int attributeId, long refreshPeriod)}
     */
    @Deprecated
    public Integer getCurrentX(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_CURRENTX).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_CURRENTX).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_CURRENTX));
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
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setCurrentXReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_CURRENTX), minInterval, maxInterval, reportableChange);
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getCurrentYAsync() {
        return read(serverAttributes.get(ATTR_CURRENTY));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttributeValue(int attributeId, long refreshPeriod)}
     */
    @Deprecated
    public Integer getCurrentY(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_CURRENTY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_CURRENTY).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_CURRENTY));
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
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setCurrentYReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_CURRENTY), minInterval, maxInterval, reportableChange);
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDriftCompensationAsync() {
        return read(serverAttributes.get(ATTR_DRIFTCOMPENSATION));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttributeValue(int attributeId, long refreshPeriod)}
     */
    @Deprecated
    public Integer getDriftCompensation(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DRIFTCOMPENSATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DRIFTCOMPENSATION).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DRIFTCOMPENSATION));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getCompensationTextAsync() {
        return read(serverAttributes.get(ATTR_COMPENSATIONTEXT));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttributeValue(int attributeId, long refreshPeriod)}
     */
    @Deprecated
    public String getCompensationText(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_COMPENSATIONTEXT).isLastValueCurrent(refreshPeriod)) {
            return (String) serverAttributes.get(ATTR_COMPENSATIONTEXT).getLastValue();
        }

        return (String) readSync(serverAttributes.get(ATTR_COMPENSATIONTEXT));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getColorTemperatureAsync() {
        return read(serverAttributes.get(ATTR_COLORTEMPERATURE));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttributeValue(int attributeId, long refreshPeriod)}
     */
    @Deprecated
    public Integer getColorTemperature(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_COLORTEMPERATURE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_COLORTEMPERATURE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_COLORTEMPERATURE));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getColorModeAsync() {
        return read(serverAttributes.get(ATTR_COLORMODE));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttributeValue(int attributeId, long refreshPeriod)}
     */
    @Deprecated
    public Integer getColorMode(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_COLORMODE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_COLORMODE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_COLORMODE));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getEnhancedCurrentHueAsync() {
        return read(serverAttributes.get(ATTR_ENHANCEDCURRENTHUE));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttributeValue(int attributeId, long refreshPeriod)}
     */
    @Deprecated
    public Integer getEnhancedCurrentHue(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ENHANCEDCURRENTHUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ENHANCEDCURRENTHUE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ENHANCEDCURRENTHUE));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getEnhancedColorModeAsync() {
        return read(serverAttributes.get(ATTR_ENHANCEDCOLORMODE));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttributeValue(int attributeId, long refreshPeriod)}
     */
    @Deprecated
    public Integer getEnhancedColorMode(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ENHANCEDCOLORMODE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ENHANCEDCOLORMODE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ENHANCEDCOLORMODE));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getColorLoopActiveAsync() {
        return read(serverAttributes.get(ATTR_COLORLOOPACTIVE));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttributeValue(int attributeId, long refreshPeriod)}
     */
    @Deprecated
    public Integer getColorLoopActive(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_COLORLOOPACTIVE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_COLORLOOPACTIVE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_COLORLOOPACTIVE));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getColorLoopDirectionAsync() {
        return read(serverAttributes.get(ATTR_COLORLOOPDIRECTION));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttributeValue(int attributeId, long refreshPeriod)}
     */
    @Deprecated
    public Integer getColorLoopDirection(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_COLORLOOPDIRECTION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_COLORLOOPDIRECTION).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_COLORLOOPDIRECTION));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getColorLoopTimeAsync() {
        return read(serverAttributes.get(ATTR_COLORLOOPTIME));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttributeValue(int attributeId, long refreshPeriod)}
     */
    @Deprecated
    public Integer getColorLoopTime(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_COLORLOOPTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_COLORLOOPTIME).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_COLORLOOPTIME));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getColorLoopStartHueAsync() {
        return read(serverAttributes.get(ATTR_COLORLOOPSTARTHUE));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttributeValue(int attributeId, long refreshPeriod)}
     */
    @Deprecated
    public Integer getColorLoopStartHue(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_COLORLOOPSTARTHUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_COLORLOOPSTARTHUE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_COLORLOOPSTARTHUE));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getColorLoopStoredHueAsync() {
        return read(serverAttributes.get(ATTR_COLORLOOPSTOREDHUE));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttributeValue(int attributeId, long refreshPeriod)}
     */
    @Deprecated
    public Integer getColorLoopStoredHue(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_COLORLOOPSTOREDHUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_COLORLOOPSTOREDHUE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_COLORLOOPSTOREDHUE));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getColorCapabilitiesAsync() {
        return read(serverAttributes.get(ATTR_COLORCAPABILITIES));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttributeValue(int attributeId, long refreshPeriod)}
     */
    @Deprecated
    public Integer getColorCapabilities(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_COLORCAPABILITIES).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_COLORCAPABILITIES).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_COLORCAPABILITIES));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getColorTemperatureMinAsync() {
        return read(serverAttributes.get(ATTR_COLORTEMPERATUREMIN));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttributeValue(int attributeId, long refreshPeriod)}
     */
    @Deprecated
    public Integer getColorTemperatureMin(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_COLORTEMPERATUREMIN).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_COLORTEMPERATUREMIN).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_COLORTEMPERATUREMIN));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getColorTemperatureMaxAsync() {
        return read(serverAttributes.get(ATTR_COLORTEMPERATUREMAX));
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttributeValue(int attributeId, long refreshPeriod)}
     */
    @Deprecated
    public Integer getColorTemperatureMax(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_COLORTEMPERATUREMAX).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_COLORTEMPERATUREMAX).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_COLORTEMPERATUREMAX));
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
     * <p>
     * On receipt of this command, a device shall set the value of the ColorMode attribute,
     * where implemented, to 0x02, and shall then move from its current color to the color given
     * by the Color Temperature Mireds field.
     * <p>
     * The movement shall be continuous, i.e., not a step function, and the time taken to move to
     * the new color shall be equal to the Transition Time field, in 1/10ths of a second.
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
     * <p>
     * The Enhanced Move to Hue command allows lamps to be moved in a smooth continuous
     * transition from their current hue to a target hue.
     * <p>
     * On receipt of this command, a device shall set the ColorMode attribute to 0x00 and set the
     * EnhancedColorMode attribute to the value 0x03. The device shall then move from its
     * current enhanced hue to the value given in the Enhanced Hue field.
     * <p>
     * The movement shall be continuous, i.e., not a step function, and the time taken to move to
     * the new en- hanced hue shall be equal to the Transition Time field.
     *
     * @param enhancedHue {@link Integer} Enhanced Hue
     * @param direction {@link Integer} Direction
     * @param transitionTime {@link Integer} Transition Time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> enhancedMoveToHueCommand(Integer enhancedHue, Integer direction, Integer transitionTime) {
        EnhancedMoveToHueCommand command = new EnhancedMoveToHueCommand();

        // Set the fields
        command.setEnhancedHue(enhancedHue);
        command.setDirection(direction);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Enhanced Move Hue Command
     * <p>
     * The Enhanced Move to Hue command allows lamps to be moved in a smooth continuous
     * transition from their current hue to a target hue.
     * <p>
     * On receipt of this command, a device shall set the ColorMode attribute to 0x00 and set the
     * EnhancedColorMode attribute to the value 0x03. The device shall then move from its
     * current enhanced hue in an up or down direction in a continuous fashion.
     *
     * @param moveMode {@link Integer} Move Mode
     * @param rate {@link Integer} Rate
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> enhancedMoveHueCommand(Integer moveMode, Integer rate) {
        EnhancedMoveHueCommand command = new EnhancedMoveHueCommand();

        // Set the fields
        command.setMoveMode(moveMode);
        command.setRate(rate);

        return send(command);
    }

    /**
     * The Enhanced Step Hue Command
     * <p>
     * The Enhanced Step Hue command allows lamps to be moved in a stepped transition from their
     * current hue to a target hue, resulting in a linear transition through XY space.
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
     * <p>
     * The Enhanced Move to Hue and Saturation command allows lamps to be moved in a smooth
     * continuous transition from their current hue to a target hue and from their current
     * saturation to a target saturation.
     *
     * @param enhancedHue {@link Integer} Enhanced Hue
     * @param saturation {@link Integer} Saturation
     * @param transitionTime {@link Integer} Transition Time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> enhancedMoveToHueAndSaturationCommand(Integer enhancedHue, Integer saturation, Integer transitionTime) {
        EnhancedMoveToHueAndSaturationCommand command = new EnhancedMoveToHueAndSaturationCommand();

        // Set the fields
        command.setEnhancedHue(enhancedHue);
        command.setSaturation(saturation);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Color Loop Set Command
     * <p>
     * The Color Loop Set command allows a color loop to be activated such that the color lamp
     * cycles through its range of hues.
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

    /**
     * The Stop Move Step Command
     * <p>
     * The Stop Move Step command is provided to allow Move to and Step commands to be stopped.
     * (Note this automatically provides symmetry to the Level Control cluster.)
     * <p>
     * Upon receipt of this command, any Move to, Move or Step command currently in process
     * shall be ter- minated. The values of the CurrentHue, EnhancedCurrentHue and
     * CurrentSaturation attributes shall be left at their present value upon receipt of the
     * Stop Move Step command, and the RemainingTime attribute shall be set to zero.
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> stopMoveStepCommand() {
        return send(new StopMoveStepCommand());
    }

    /**
     * The Move Color Temperature Command
     * <p>
     * The Move Color Temperature command allows the color temperature of a lamp to be moved at a
     * specified rate.
     *
     * @param moveMode {@link Integer} Move Mode
     * @param rate {@link Integer} Rate
     * @param colorTemperatureMinimum {@link Integer} Color Temperature Minimum
     * @param colorTemperatureMaximum {@link Integer} Color Temperature Maximum
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> moveColorTemperatureCommand(Integer moveMode, Integer rate, Integer colorTemperatureMinimum, Integer colorTemperatureMaximum) {
        MoveColorTemperatureCommand command = new MoveColorTemperatureCommand();

        // Set the fields
        command.setMoveMode(moveMode);
        command.setRate(rate);
        command.setColorTemperatureMinimum(colorTemperatureMinimum);
        command.setColorTemperatureMaximum(colorTemperatureMaximum);

        return send(command);
    }

    /**
     * The Step Color Temperature Command
     * <p>
     * The Step Color Temperature command allows the color temperature of a lamp to be stepped
     * with a specified step size.
     *
     * @param stepMode {@link Integer} Step Mode
     * @param stepSize {@link Integer} Step Size
     * @param transitionTime {@link Integer} Transition Time
     * @param colorTemperatureMinimum {@link Integer} Color Temperature Minimum
     * @param colorTemperatureMaximum {@link Integer} Color Temperature Maximum
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> stepColorTemperatureCommand(Integer stepMode, Integer stepSize, Integer transitionTime, Integer colorTemperatureMinimum, Integer colorTemperatureMaximum) {
        StepColorTemperatureCommand command = new StepColorTemperatureCommand();

        // Set the fields
        command.setStepMode(stepMode);
        command.setStepSize(stepSize);
        command.setTransitionTime(transitionTime);
        command.setColorTemperatureMinimum(colorTemperatureMinimum);
        command.setColorTemperatureMaximum(colorTemperatureMaximum);

        return send(command);
    }
}
