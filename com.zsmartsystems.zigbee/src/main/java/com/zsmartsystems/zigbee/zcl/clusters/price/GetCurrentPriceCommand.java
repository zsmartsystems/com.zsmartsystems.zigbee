/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.price;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Current Price Command value object class.
 * <p>
 * Cluster: <b>Price</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Price cluster.
 * <p>
 * This command initiates a PublishPrice command for the current time. On receipt of this
 * command, the device shall send a PublishPrice command for the currently scheduled time.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetCurrentPriceCommand extends ZclCommand {
    /**
     * Command Options command message field.
     */
    private Integer commandOptions;

    /**
     * Default constructor.
     */
    public GetCurrentPriceCommand() {
        genericCommand = false;
        clusterId = 0x0700;
        commandId = 0;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Command Options.
     *
     * @return the Command Options
     */
    public Integer getCommandOptions() {
        return commandOptions;
    }

    /**
     * Sets Command Options.
     *
     * @param commandOptions the Command Options
     */
    public void setCommandOptions(final Integer commandOptions) {
        this.commandOptions = commandOptions;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(commandOptions, ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        commandOptions = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(59);
        builder.append("GetCurrentPriceCommand [");
        builder.append(super.toString());
        builder.append(", commandOptions=");
        builder.append(commandOptions);
        builder.append(']');
        return builder.toString();
    }

}
