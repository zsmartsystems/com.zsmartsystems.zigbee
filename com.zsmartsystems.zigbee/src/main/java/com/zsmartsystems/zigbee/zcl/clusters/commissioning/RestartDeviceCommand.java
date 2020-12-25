/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.commissioning;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Restart Device Command value object class.
 * <p>
 * Cluster: <b>Commissioning</b>. Command ID 0x00 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Commissioning cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class RestartDeviceCommand extends ZclCommissioningCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0015;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x00;

    /**
     * Option command message field.
     */
    private Integer option;

    /**
     * Delay command message field.
     */
    private Integer delay;

    /**
     * Jitter command message field.
     */
    private Integer jitter;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public RestartDeviceCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param option {@link Integer} Option
     * @param delay {@link Integer} Delay
     * @param jitter {@link Integer} Jitter
     */
    public RestartDeviceCommand(
            Integer option,
            Integer delay,
            Integer jitter) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.option = option;
        this.delay = delay;
        this.jitter = jitter;
    }

    /**
     * Gets Option.
     *
     * @return the Option
     */
    public Integer getOption() {
        return option;
    }

    /**
     * Sets Option.
     *
     * @param option the Option
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setOption(final Integer option) {
        this.option = option;
    }

    /**
     * Gets Delay.
     *
     * @return the Delay
     */
    public Integer getDelay() {
        return delay;
    }

    /**
     * Sets Delay.
     *
     * @param delay the Delay
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setDelay(final Integer delay) {
        this.delay = delay;
    }

    /**
     * Gets Jitter.
     *
     * @return the Jitter
     */
    public Integer getJitter() {
        return jitter;
    }

    /**
     * Sets Jitter.
     *
     * @param jitter the Jitter
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setJitter(final Integer jitter) {
        this.jitter = jitter;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(option, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(delay, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(jitter, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        option = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        delay = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        jitter = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(100);
        builder.append("RestartDeviceCommand [");
        builder.append(super.toString());
        builder.append(", option=");
        builder.append(option);
        builder.append(", delay=");
        builder.append(delay);
        builder.append(", jitter=");
        builder.append(jitter);
        builder.append(']');
        return builder.toString();
    }

}
