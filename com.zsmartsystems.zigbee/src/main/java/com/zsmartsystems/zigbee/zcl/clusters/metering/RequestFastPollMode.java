/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.metering;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Request Fast Poll Mode value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command ID 0x03 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * The Request Fast Poll Mode command is generated when the metering client wishes to receive
 * near real-time updates of InstantaneousDemand.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class RequestFastPollMode extends ZclMeteringCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0702;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x03;

    /**
     * Fast Poll Update Period command message field.
     * <p>
     * Desired fast poll period not to be less than the FastPollUpdatePeriod attribute.
     */
    private Integer fastPollUpdatePeriod;

    /**
     * Duration command message field.
     * <p>
     * Desired duration for the server to remain in fast poll mode not to exceed 15 minutes.
     */
    private Integer duration;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public RequestFastPollMode() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param fastPollUpdatePeriod {@link Integer} Fast Poll Update Period
     * @param duration {@link Integer} Duration
     */
    public RequestFastPollMode(
            Integer fastPollUpdatePeriod,
            Integer duration) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.fastPollUpdatePeriod = fastPollUpdatePeriod;
        this.duration = duration;
    }

    /**
     * Gets Fast Poll Update Period.
     * <p>
     * Desired fast poll period not to be less than the FastPollUpdatePeriod attribute.
     *
     * @return the Fast Poll Update Period
     */
    public Integer getFastPollUpdatePeriod() {
        return fastPollUpdatePeriod;
    }

    /**
     * Sets Fast Poll Update Period.
     * <p>
     * Desired fast poll period not to be less than the FastPollUpdatePeriod attribute.
     *
     * @param fastPollUpdatePeriod the Fast Poll Update Period
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setFastPollUpdatePeriod(final Integer fastPollUpdatePeriod) {
        this.fastPollUpdatePeriod = fastPollUpdatePeriod;
    }

    /**
     * Gets Duration.
     * <p>
     * Desired duration for the server to remain in fast poll mode not to exceed 15 minutes.
     *
     * @return the Duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Sets Duration.
     * <p>
     * Desired duration for the server to remain in fast poll mode not to exceed 15 minutes.
     *
     * @param duration the Duration
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setDuration(final Integer duration) {
        this.duration = duration;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(fastPollUpdatePeriod, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(duration, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        fastPollUpdatePeriod = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        duration = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(90);
        builder.append("RequestFastPollMode [");
        builder.append(super.toString());
        builder.append(", fastPollUpdatePeriod=");
        builder.append(fastPollUpdatePeriod);
        builder.append(", duration=");
        builder.append(duration);
        builder.append(']');
        return builder.toString();
    }

}
