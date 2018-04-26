/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.pollcontrol;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Check In Response value object class.
 * <p>
 * Cluster: <b>Poll Control</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Poll Control cluster.
 * <p>
 * The Check-in Response is sent in response to the receipt of a Check-in command. The Check-in Response is used by the Poll Control Client to
 * indicate whether it would like the device implementing the Poll Control Cluster Server to go into a fast poll mode and for how long. If the Poll
 * Control Cluster Client indicates that it would like the device to go into a fast poll mode, it is responsible for telling the device to stop
 * fast polling when it is done sending messages to the fast polling device.
 * <br>
 * If the Poll Control Server receives a Check-In Response from a client for which there is no binding (unbound), it SHOULD respond with a
 * Default Response with a status value indicating ACTION_DENIED.
 * <br>
 * If the Poll Control Server receives a Check-In Response from a client for which there is a binding (bound) with an invalid fast poll interval
 * it SHOULD respond with a Default Response with status INVALID_VALUE.
 * <br>
 * If the Poll Control Server receives a Check-In Response from a bound client after temporary fast poll mode is completed it SHOULD respond
 * with a Default Response with a status value indicating TIMEOUT.
 * <br>
 * In all of the above cases, the Server SHALL respond with a Default Response not equal to ZCL_SUCCESS.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-04-26T19:23:24Z")
public class CheckInResponse extends ZclCommand {
    /**
     * Start Fast Polling command message field.
     */
    private Boolean startFastPolling;

    /**
     * Fast Poll Timeout command message field.
     */
    private Integer fastPollTimeout;

    /**
     * Default constructor.
     */
    public CheckInResponse() {
        genericCommand = false;
        clusterId = 32;
        commandId = 0;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Start Fast Polling.
     *
     * @return the Start Fast Polling
     */
    public Boolean getStartFastPolling() {
        return startFastPolling;
    }

    /**
     * Sets Start Fast Polling.
     *
     * @param startFastPolling the Start Fast Polling
     */
    public void setStartFastPolling(final Boolean startFastPolling) {
        this.startFastPolling = startFastPolling;
    }

    /**
     * Gets Fast Poll Timeout.
     *
     * @return the Fast Poll Timeout
     */
    public Integer getFastPollTimeout() {
        return fastPollTimeout;
    }

    /**
     * Sets Fast Poll Timeout.
     *
     * @param fastPollTimeout the Fast Poll Timeout
     */
    public void setFastPollTimeout(final Integer fastPollTimeout) {
        this.fastPollTimeout = fastPollTimeout;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(startFastPolling, ZclDataType.BOOLEAN);
        serializer.serialize(fastPollTimeout, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        startFastPolling = (Boolean) deserializer.deserialize(ZclDataType.BOOLEAN);
        fastPollTimeout = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(89);
        builder.append("CheckInResponse [");
        builder.append(super.toString());
        builder.append(", startFastPolling=");
        builder.append(startFastPolling);
        builder.append(", fastPollTimeout=");
        builder.append(fastPollTimeout);
        builder.append(']');
        return builder.toString();
    }

}
