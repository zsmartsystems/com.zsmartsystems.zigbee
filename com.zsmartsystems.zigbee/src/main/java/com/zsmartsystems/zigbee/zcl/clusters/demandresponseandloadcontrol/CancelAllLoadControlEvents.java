/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.demandresponseandloadcontrol;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Cancel All Load Control Events value object class.
 * <p>
 * Cluster: <b>Demand Response And Load Control</b>. Command ID 0x02 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Demand Response And Load Control cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-06T18:45:28Z")
public class CancelAllLoadControlEvents extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0701;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x02;

    /**
     * Cancel Control command message field.
     */
    private Integer cancelControl;

    /**
     * Default constructor.
     */
    public CancelAllLoadControlEvents() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Cancel Control.
     *
     * @return the Cancel Control
     */
    public Integer getCancelControl() {
        return cancelControl;
    }

    /**
     * Sets Cancel Control.
     *
     * @param cancelControl the Cancel Control
     * @return the CancelAllLoadControlEvents command
     */
    public CancelAllLoadControlEvents setCancelControl(final Integer cancelControl) {
        this.cancelControl = cancelControl;
        return this;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(cancelControl, ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        cancelControl = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(62);
        builder.append("CancelAllLoadControlEvents [");
        builder.append(super.toString());
        builder.append(", cancelControl=");
        builder.append(cancelControl);
        builder.append(']');
        return builder.toString();
    }

}
