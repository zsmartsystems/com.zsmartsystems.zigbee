/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
 * Move Color Command value object class.
 * <p>
 * Cluster: <b>Color Control</b>. Command ID 0x08 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Color Control cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class MoveColorCommand extends ZclColorControlCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0300;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x08;

    /**
     * Rate X command message field.
     */
    private Integer rateX;

    /**
     * Rate Y command message field.
     */
    private Integer rateY;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public MoveColorCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param rateX {@link Integer} Rate X
     * @param rateY {@link Integer} Rate Y
     */
    public MoveColorCommand(
            Integer rateX,
            Integer rateY) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.rateX = rateX;
        this.rateY = rateY;
    }

    /**
     * Gets Rate X.
     *
     * @return the Rate X
     */
    public Integer getRateX() {
        return rateX;
    }

    /**
     * Sets Rate X.
     *
     * @param rateX the Rate X
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setRateX(final Integer rateX) {
        this.rateX = rateX;
    }

    /**
     * Gets Rate Y.
     *
     * @return the Rate Y
     */
    public Integer getRateY() {
        return rateY;
    }

    /**
     * Sets Rate Y.
     *
     * @param rateY the Rate Y
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setRateY(final Integer rateY) {
        this.rateY = rateY;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(rateX, ZclDataType.SIGNED_16_BIT_INTEGER);
        serializer.serialize(rateY, ZclDataType.SIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        rateX = (Integer) deserializer.deserialize(ZclDataType.SIGNED_16_BIT_INTEGER);
        rateY = (Integer) deserializer.deserialize(ZclDataType.SIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(69);
        builder.append("MoveColorCommand [");
        builder.append(super.toString());
        builder.append(", rateX=");
        builder.append(rateX);
        builder.append(", rateY=");
        builder.append(rateY);
        builder.append(']');
        return builder.toString();
    }

}
