/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.commissioning;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Restart Device Command value object class.
 * <p>
 * Cluster: <b>Commissioning</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Commissioning cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class RestartDeviceCommand extends ZclCommand {
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
     */
    public RestartDeviceCommand() {
        genericCommand = false;
        clusterId = 21;
        commandId = 0;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
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
     */
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
     */
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
     */
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
