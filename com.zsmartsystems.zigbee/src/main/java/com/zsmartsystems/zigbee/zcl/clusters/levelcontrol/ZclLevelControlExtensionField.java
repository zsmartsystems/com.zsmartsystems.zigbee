/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.levelcontrol;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zcl.clusters.ZclLevelControlCluster;
import com.zsmartsystems.zigbee.zcl.field.ExtensionFieldSet;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Level Control</b> cluster {@link ExtensionFieldSet} implementation for use with scenes.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-20T21:27:13Z")
public class ZclLevelControlExtensionField extends ExtensionFieldSet {

    /**
     * The CurrentLevel attribute represents the current level of this device. The meaning of
     * 'level' is device dependent. Value is between 0 and 254.
     */
    private Integer currentLevel;

    /**
     * The CurrentFrequency attribute represents the frequency that the devices is at
     * CurrentLevel. A CurrentFrequency of 0 is unknown.
     */
    private Integer currentFrequency;

    /**
     * Default constructor to create a Level Control {@link ExtensionFieldSet}.
     *
     * @param currentLevel {@link Integer} Current Level
     * @param currentFrequency {@link Integer} Current Frequency
     */
    public ZclLevelControlExtensionField(
            Integer currentLevel,
            Integer currentFrequency) {
        clusterId = ZclLevelControlCluster.CLUSTER_ID;

        this.currentLevel = currentLevel;
        this.currentFrequency = currentFrequency;
    }

    /**
     * The CurrentLevel attribute represents the current level of this device. The meaning of
     * 'level' is device dependent. Value is between 0 and 254.
     *
     * @return the Current Level
     */
    public Integer getCurrentLevel() {
        return currentLevel;
    }

    /**
     * The CurrentFrequency attribute represents the frequency that the devices is at
     * CurrentLevel. A CurrentFrequency of 0 is unknown.
     *
     * @return the Current Frequency
     */
    public Integer getCurrentFrequency() {
        return currentFrequency;
    }

    @Override
    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(clusterId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.appendZigBeeType(3, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.appendZigBeeType(currentLevel, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.appendZigBeeType(currentFrequency, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZigBeeDeserializer deserializer) {
        clusterId = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        int size = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        if (size >= 1) {
            currentLevel = (Integer) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        }
        if (size >= 3) {
            currentFrequency = (Integer) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(100);
        builder.append("ZclLevelControlExtensionField [clusterId=");
        builder.append(clusterId);
        builder.append(", currentLevel=");
        builder.append(currentLevel);
        builder.append(", currentFrequency=");
        builder.append(currentFrequency);
        builder.append(']');
        return builder.toString();
    }
}
