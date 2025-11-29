/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.greenpower;

import javax.annotation.Generated;

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
 * This command is generated when the sink wishes to instruct the proxies to enter/exit
 * commissioning mode. The GP Proxy Commissioning Mode command is typically sent using
 * network-wide broadcast.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class GpProxyCommissioningMode extends ZclGreenPowerCommand {
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
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GpProxyCommissioningMode() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param options {@link Integer} Options
     * @param commissioningWindow {@link Integer} Commissioning Window
     * @param channel {@link Integer} Channel
     */
    public GpProxyCommissioningMode(
            Integer options,
            Integer commissioningWindow,
            Integer channel) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.options = options;
        this.commissioningWindow = commissioningWindow;
        this.channel = channel;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
        options = deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        commissioningWindow = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        channel = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
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
