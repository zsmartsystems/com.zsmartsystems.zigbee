/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.colorcontrol;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Move To Color Command value object class.
 * <p>
 * Cluster: <b>Color Control</b>. Command ID 0x07 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Color Control cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class MoveToColorCommand extends ZclColorControlCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0300;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x07;

    /**
     * Color X command message field.
     */
    private Integer colorX;

    /**
     * Color Y command message field.
     */
    private Integer colorY;

    /**
     * Transition Time command message field.
     */
    private Integer transitionTime;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public MoveToColorCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param colorX {@link Integer} Color X
     * @param colorY {@link Integer} Color Y
     * @param transitionTime {@link Integer} Transition Time
     */
    public MoveToColorCommand(
            Integer colorX,
            Integer colorY,
            Integer transitionTime) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.colorX = colorX;
        this.colorY = colorY;
        this.transitionTime = transitionTime;
    }

    /**
     * Gets Color X.
     *
     * @return the Color X
     */
    public Integer getColorX() {
        return colorX;
    }

    /**
     * Sets Color X.
     *
     * @param colorX the Color X
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setColorX(final Integer colorX) {
        this.colorX = colorX;
    }

    /**
     * Gets Color Y.
     *
     * @return the Color Y
     */
    public Integer getColorY() {
        return colorY;
    }

    /**
     * Sets Color Y.
     *
     * @param colorY the Color Y
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setColorY(final Integer colorY) {
        this.colorY = colorY;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTransitionTime(final Integer transitionTime) {
        this.transitionTime = transitionTime;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(colorX, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(colorY, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(transitionTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        colorX = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        colorY = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        transitionTime = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(107);
        builder.append("MoveToColorCommand [");
        builder.append(super.toString());
        builder.append(", colorX=");
        builder.append(colorX);
        builder.append(", colorY=");
        builder.append(colorY);
        builder.append(", transitionTime=");
        builder.append(transitionTime);
        builder.append(']');
        return builder.toString();
    }

}
