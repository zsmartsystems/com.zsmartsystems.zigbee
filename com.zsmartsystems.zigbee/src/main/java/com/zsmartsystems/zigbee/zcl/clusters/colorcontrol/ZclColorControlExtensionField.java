/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.colorcontrol;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zcl.clusters.ZclColorControlCluster;
import com.zsmartsystems.zigbee.zcl.field.ExtensionFieldSet;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Color Control</b> cluster {@link ExtensionFieldSet} implementation for use with scenes.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-20T21:27:13Z")
public class ZclColorControlExtensionField extends ExtensionFieldSet {

    /**
     * The CurrentX attribute contains the current value of the normalized chromaticity
     * value x, as defined in the CIE xyY Color Space. It is updated as fast as practical during
     * commands that change the color.
     * <p>
     * The value of x shall be related to the CurrentX attribute by the relationship
     * <p>
     * x = CurrentX / 65535 (CurrentX in the range 0 to 65279 inclusive)
     */
    private Integer currentX;

    /**
     * The CurrentY attribute contains the current value of the normalized chromaticity
     * value y, as defined in the CIE xyY Color Space. It is updated as fast as practical during
     * commands that change the color.
     * <p>
     * The value of y shall be related to the CurrentY attribute by the relationship
     * <p>
     * y = CurrentY / 65535 (CurrentY in the range 0 to 65279 inclusive)
     */
    private Integer currentY;

    /**
     * The EnhancedCurrentHueattribute represents non-equidistant steps along the CIE
     * 1931 color triangle, and it provides 16-bits precision. The upper 8 bits of this
     * attribute shall be used as an index in the implementation specific XY lookup table to
     * provide the non-equidistance steps (see the ZLL test specification for an example).
     * The lower 8 bits shall be used to interpolate between these steps in a linear way in order
     * to provide color zoom for the user.
     */
    private Integer enhancedCurrentHue;

    /**
     * The CurrentSaturation attribute holds the current saturation value of the light. It is
     * updated as fast as practical during commands that change the saturation. The
     * saturation shall be related to the CurrentSaturation attribute by the relationship
     * Saturation = CurrentSaturation/254 (CurrentSaturation in the range 0 - 254
     * inclusive) If this attribute is implemented then the CurrentHue and ColorMode
     * attributes shall also be implemented.
     */
    private Integer currentSaturation;

    /**
     * The ColorLoopActive attribute specifies the current active status of the color loop.
     * If this attribute has the value 0x00, the color loop SHALLnot be active. If this
     * attribute has the value 0x01, the color loop shall be active. All other values (0x02 –
     * 0xff) are reserved.
     */
    private Integer colorLoopActive;

    /**
     * The ColorLoopDirection attribute specifies the current direction of the color loop.
     * If this attribute has the value 0x00, the EnhancedCurrentHue attribute shall be
     * decremented. If this attribute has the value 0x01, the EnhancedCurrentHue attribute
     * shall be incremented. All other values (0x02 – 0xff) are reserved.
     */
    private Integer colorLoopDirection;

    /**
     * The ColorLoopTime attribute specifies the number of seconds it shall take to perform a
     * full color loop, i.e.,to cycle all values of the EnhancedCurrentHue attribute
     * (between 0x0000 and 0xffff).
     */
    private Integer colorLoopTime;

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
    private Integer colorTemperature;

    /**
     * Default constructor to create a Color Control {@link ExtensionFieldSet}.
     *
     * @param currentX {@link Integer} Current X
     * @param currentY {@link Integer} Current Y
     * @param enhancedCurrentHue {@link Integer} Enhanced Current Hue
     * @param currentSaturation {@link Integer} Current Saturation
     * @param colorLoopActive {@link Integer} Color Loop Active
     * @param colorLoopDirection {@link Integer} Color Loop Direction
     * @param colorLoopTime {@link Integer} Color Loop Time
     * @param colorTemperature {@link Integer} Color Temperature
     */
    public ZclColorControlExtensionField(
            Integer currentX,
            Integer currentY,
            Integer enhancedCurrentHue,
            Integer currentSaturation,
            Integer colorLoopActive,
            Integer colorLoopDirection,
            Integer colorLoopTime,
            Integer colorTemperature) {
        clusterId = ZclColorControlCluster.CLUSTER_ID;

        this.currentX = currentX;
        this.currentY = currentY;
        this.enhancedCurrentHue = enhancedCurrentHue;
        this.currentSaturation = currentSaturation;
        this.colorLoopActive = colorLoopActive;
        this.colorLoopDirection = colorLoopDirection;
        this.colorLoopTime = colorLoopTime;
        this.colorTemperature = colorTemperature;
    }

    /**
     * The CurrentX attribute contains the current value of the normalized chromaticity
     * value x, as defined in the CIE xyY Color Space. It is updated as fast as practical during
     * commands that change the color.
     * <p>
     * The value of x shall be related to the CurrentX attribute by the relationship
     * <p>
     * x = CurrentX / 65535 (CurrentX in the range 0 to 65279 inclusive)
     *
     * @return the Current X
     */
    public Integer getCurrentX() {
        return currentX;
    }

    /**
     * The CurrentY attribute contains the current value of the normalized chromaticity
     * value y, as defined in the CIE xyY Color Space. It is updated as fast as practical during
     * commands that change the color.
     * <p>
     * The value of y shall be related to the CurrentY attribute by the relationship
     * <p>
     * y = CurrentY / 65535 (CurrentY in the range 0 to 65279 inclusive)
     *
     * @return the Current Y
     */
    public Integer getCurrentY() {
        return currentY;
    }

    /**
     * The EnhancedCurrentHueattribute represents non-equidistant steps along the CIE
     * 1931 color triangle, and it provides 16-bits precision. The upper 8 bits of this
     * attribute shall be used as an index in the implementation specific XY lookup table to
     * provide the non-equidistance steps (see the ZLL test specification for an example).
     * The lower 8 bits shall be used to interpolate between these steps in a linear way in order
     * to provide color zoom for the user.
     *
     * @return the Enhanced Current Hue
     */
    public Integer getEnhancedCurrentHue() {
        return enhancedCurrentHue;
    }

    /**
     * The CurrentSaturation attribute holds the current saturation value of the light. It is
     * updated as fast as practical during commands that change the saturation. The
     * saturation shall be related to the CurrentSaturation attribute by the relationship
     * Saturation = CurrentSaturation/254 (CurrentSaturation in the range 0 - 254
     * inclusive) If this attribute is implemented then the CurrentHue and ColorMode
     * attributes shall also be implemented.
     *
     * @return the Current Saturation
     */
    public Integer getCurrentSaturation() {
        return currentSaturation;
    }

    /**
     * The ColorLoopActive attribute specifies the current active status of the color loop.
     * If this attribute has the value 0x00, the color loop SHALLnot be active. If this
     * attribute has the value 0x01, the color loop shall be active. All other values (0x02 –
     * 0xff) are reserved.
     *
     * @return the Color Loop Active
     */
    public Integer getColorLoopActive() {
        return colorLoopActive;
    }

    /**
     * The ColorLoopDirection attribute specifies the current direction of the color loop.
     * If this attribute has the value 0x00, the EnhancedCurrentHue attribute shall be
     * decremented. If this attribute has the value 0x01, the EnhancedCurrentHue attribute
     * shall be incremented. All other values (0x02 – 0xff) are reserved.
     *
     * @return the Color Loop Direction
     */
    public Integer getColorLoopDirection() {
        return colorLoopDirection;
    }

    /**
     * The ColorLoopTime attribute specifies the number of seconds it shall take to perform a
     * full color loop, i.e.,to cycle all values of the EnhancedCurrentHue attribute
     * (between 0x0000 and 0xffff).
     *
     * @return the Color Loop Time
     */
    public Integer getColorLoopTime() {
        return colorLoopTime;
    }

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
     *
     * @return the Color Temperature
     */
    public Integer getColorTemperature() {
        return colorTemperature;
    }

    @Override
    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(clusterId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.appendZigBeeType(13, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.appendZigBeeType(currentX, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.appendZigBeeType(currentY, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.appendZigBeeType(enhancedCurrentHue, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.appendZigBeeType(currentSaturation, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.appendZigBeeType(colorLoopActive, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.appendZigBeeType(colorLoopDirection, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.appendZigBeeType(colorLoopTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.appendZigBeeType(colorTemperature, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZigBeeDeserializer deserializer) {
        clusterId = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        int size = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        if (size >= 2) {
            currentX = (Integer) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
        if (size >= 4) {
            currentY = (Integer) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
        if (size >= 6) {
            enhancedCurrentHue = (Integer) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
        if (size >= 7) {
            currentSaturation = (Integer) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        }
        if (size >= 8) {
            colorLoopActive = (Integer) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        }
        if (size >= 9) {
            colorLoopDirection = (Integer) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        }
        if (size >= 11) {
            colorLoopTime = (Integer) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
        if (size >= 13) {
            colorTemperature = (Integer) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(100);
        builder.append("ZclColorControlExtensionField [clusterId=");
        builder.append(clusterId);
        builder.append(", currentX=");
        builder.append(currentX);
        builder.append(", currentY=");
        builder.append(currentY);
        builder.append(", enhancedCurrentHue=");
        builder.append(enhancedCurrentHue);
        builder.append(", currentSaturation=");
        builder.append(currentSaturation);
        builder.append(", colorLoopActive=");
        builder.append(colorLoopActive);
        builder.append(", colorLoopDirection=");
        builder.append(colorLoopDirection);
        builder.append(", colorLoopTime=");
        builder.append(colorLoopTime);
        builder.append(", colorTemperature=");
        builder.append(colorTemperature);
        builder.append(']');
        return builder.toString();
    }
}
