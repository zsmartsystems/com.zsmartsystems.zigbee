/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.colorcontrol;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Move To Color Temperature Command value object class.
 * <p>
 * Cluster: <b>Color Control</b>. Command ID 0x0A is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Color Control cluster.
 * <p>
 * On receipt of this command, a device shall set the value of the ColorMode attribute, where
 * implemented, to 0x02, and shall then move from its current color to the color given by the
 * Color Temperature Mireds field.
 * <p>
 * The movement shall be continuous, i.e., not a step function, and the time taken to move to the
 * new color shall be equal to the Transition Time field, in 1/10ths of a second.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-06T18:45:28Z")
public class MoveToColorTemperatureCommand extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0300;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x0A;

    /**
     * Color Temperature command message field.
     */
    private Integer colorTemperature;

    /**
     * Transition Time command message field.
     */
    private Integer transitionTime;

    /**
     * Default constructor.
     */
    public MoveToColorTemperatureCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Color Temperature.
     *
     * @return the Color Temperature
     */
    public Integer getColorTemperature() {
        return colorTemperature;
    }

    /**
     * Sets Color Temperature.
     *
     * @param colorTemperature the Color Temperature
     * @return the MoveToColorTemperatureCommand command
     */
    public MoveToColorTemperatureCommand setColorTemperature(final Integer colorTemperature) {
        this.colorTemperature = colorTemperature;
        return this;
    }

    /**
     * Gets Transition Time.
     *
     * @return the Transition Time
     */
    public Integer getTransitionTime() {
        return transitionTime;
    }

    /**
     * Sets Transition Time.
     *
     * @param transitionTime the Transition Time
     * @return the MoveToColorTemperatureCommand command
     */
    public MoveToColorTemperatureCommand setTransitionTime(final Integer transitionTime) {
        this.transitionTime = transitionTime;
        return this;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(colorTemperature, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(transitionTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        colorTemperature = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        transitionTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(102);
        builder.append("MoveToColorTemperatureCommand [");
        builder.append(super.toString());
        builder.append(", colorTemperature=");
        builder.append(colorTemperature);
        builder.append(", transitionTime=");
        builder.append(transitionTime);
        builder.append(']');
        return builder.toString();
    }

}
