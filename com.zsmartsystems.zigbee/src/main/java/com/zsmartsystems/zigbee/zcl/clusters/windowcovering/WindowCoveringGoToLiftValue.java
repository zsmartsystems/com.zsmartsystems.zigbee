/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.windowcovering;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Window Covering Go To Lift Value value object class.
 * <p>
 * Cluster: <b>Window Covering</b>. Command ID 0x04 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Window Covering cluster.
 * <p>
 * Goto the specified lift value
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class WindowCoveringGoToLiftValue extends ZclWindowCoveringCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0102;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x04;

    /**
     * Lift Value command message field.
     */
    private Integer liftValue;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public WindowCoveringGoToLiftValue() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param liftValue {@link Integer} Lift Value
     */
    public WindowCoveringGoToLiftValue(
            Integer liftValue) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.liftValue = liftValue;
    }

    /**
     * Gets Lift Value.
     *
     * @return the Lift Value
     */
    public Integer getLiftValue() {
        return liftValue;
    }

    /**
     * Sets Lift Value.
     *
     * @param liftValue the Lift Value
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setLiftValue(final Integer liftValue) {
        this.liftValue = liftValue;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(liftValue, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        liftValue = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(59);
        builder.append("WindowCoveringGoToLiftValue [");
        builder.append(super.toString());
        builder.append(", liftValue=");
        builder.append(liftValue);
        builder.append(']');
        return builder.toString();
    }

}
