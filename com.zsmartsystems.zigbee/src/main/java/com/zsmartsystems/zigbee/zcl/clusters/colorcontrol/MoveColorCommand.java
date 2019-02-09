/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
 * Move Color Command value object class.
 * <p>
 * Cluster: <b>Color Control</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Color Control cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class MoveColorCommand extends ZclCommand {
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
     */
    public MoveColorCommand() {
        genericCommand = false;
        clusterId = 0x0300;
        commandId = 8;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
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
     */
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
     */
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
