/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.identify;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Identify Command value object class.
 * <p>
 * Cluster: <b>Identify</b>. Command ID 0x00 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Identify cluster.
 * <p>
 * The identify command starts or stops the receiving device identifying itself.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-06T18:45:28Z")
public class IdentifyCommand extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0003;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x00;

    /**
     * Identify Time command message field.
     */
    private Integer identifyTime;

    /**
     * Default constructor.
     */
    public IdentifyCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Identify Time.
     *
     * @return the Identify Time
     */
    public Integer getIdentifyTime() {
        return identifyTime;
    }

    /**
     * Sets Identify Time.
     *
     * @param identifyTime the Identify Time
     * @return the IdentifyCommand command
     */
    public IdentifyCommand setIdentifyTime(final Integer identifyTime) {
        this.identifyTime = identifyTime;
        return this;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(identifyTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        identifyTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(50);
        builder.append("IdentifyCommand [");
        builder.append(super.toString());
        builder.append(", identifyTime=");
        builder.append(identifyTime);
        builder.append(']');
        return builder.toString();
    }

}
