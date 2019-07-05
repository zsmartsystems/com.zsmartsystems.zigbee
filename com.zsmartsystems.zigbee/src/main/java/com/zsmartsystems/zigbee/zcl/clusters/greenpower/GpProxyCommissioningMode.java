/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.greenpower;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Gp Proxy Commissioning Mode value object class.
 * <p>
 * Cluster: <b>Green Power</b>. Command ID 0x02 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Green Power cluster.
 * <p>
 * From GPS to GPPs in the whole network to indicate commissioning mode.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-07-04T21:54:11Z")
public class GpProxyCommissioningMode extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0021;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x02;

    /**
     * Options command message field.
     */
    private Integer options;

    /**
     * Commissioning Window command message field.
     */
    private Integer commissioningWindow;

    /**
     * Channel command message field.
     */
    private Integer channel;

    /**
     * Default constructor.
     */
    public GpProxyCommissioningMode() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Options.
     *
     * @return the Options
     */
    public Integer getOptions() {
        return options;
    }

    /**
     * Sets Options.
     *
     * @param options the Options
     */
    public void setOptions(final Integer options) {
        this.options = options;
    }

    /**
     * Gets Commissioning Window.
     *
     * @return the Commissioning Window
     */
    public Integer getCommissioningWindow() {
        return commissioningWindow;
    }

    /**
     * Sets Commissioning Window.
     *
     * @param commissioningWindow the Commissioning Window
     */
    public void setCommissioningWindow(final Integer commissioningWindow) {
        this.commissioningWindow = commissioningWindow;
    }

    /**
     * Gets Channel.
     *
     * @return the Channel
     */
    public Integer getChannel() {
        return channel;
    }

    /**
     * Sets Channel.
     *
     * @param channel the Channel
     */
    public void setChannel(final Integer channel) {
        this.channel = channel;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(options, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(commissioningWindow, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(channel, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        options = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        commissioningWindow = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        channel = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(120);
        builder.append("GpProxyCommissioningMode [");
        builder.append(super.toString());
        builder.append(", options=");
        builder.append(options);
        builder.append(", commissioningWindow=");
        builder.append(commissioningWindow);
        builder.append(", channel=");
        builder.append(channel);
        builder.append(']');
        return builder.toString();
    }

}
