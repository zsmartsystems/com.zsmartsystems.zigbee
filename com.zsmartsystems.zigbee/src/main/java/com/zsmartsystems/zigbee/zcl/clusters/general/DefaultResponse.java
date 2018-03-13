/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.general;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.ZclStatus;

/**
 * Default Response value object class.
 * <p>
 * The default response command is generated when a device receives a unicast
 * command, there is no other relevant response specified for the command, and
 * either an error results or the Disable default response bit of its Frame control field
 * is set to 0.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class DefaultResponse extends ZclCommand {
    /**
     * Command identifier command message field.
     */
    private Integer commandIdentifier;

    /**
     * Status code command message field.
     */
    private ZclStatus statusCode;

    /**
     * Default constructor.
     */
    public DefaultResponse() {
        genericCommand = true;
        commandId = 11;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Sets the cluster ID for <i>generic</i> commands. {@link DefaultResponse} is a <i>generic</i> command.
     * <p>
     * For commands that are not <i>generic</i>, this method will do nothing as the cluster ID is fixed.
     * To test if a command is <i>generic</i>, use the {@link #isGenericCommand} method.
     *
     * @param clusterId the cluster ID used for <i>generic</i> commands as an {@link Integer}
     */
    @Override
    public void setClusterId(Integer clusterId) {
        this.clusterId = clusterId;
    }

    /**
     * Gets Command identifier.
     *
     * @return the Command identifier
     */
    public Integer getCommandIdentifier() {
        return commandIdentifier;
    }

    /**
     * Sets Command identifier.
     *
     * @param commandIdentifier the Command identifier
     */
    public void setCommandIdentifier(final Integer commandIdentifier) {
        this.commandIdentifier = commandIdentifier;
    }

    /**
     * Gets Status code.
     *
     * @return the Status code
     */
    public ZclStatus getStatusCode() {
        return statusCode;
    }

    /**
     * Sets Status code.
     *
     * @param statusCode the Status code
     */
    public void setStatusCode(final ZclStatus statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(commandIdentifier, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(statusCode, ZclDataType.ZCL_STATUS);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        commandIdentifier = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        statusCode = (ZclStatus) deserializer.deserialize(ZclDataType.ZCL_STATUS);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(85);
        builder.append("DefaultResponse [");
        builder.append(super.toString());
        builder.append(", commandIdentifier=");
        builder.append(commandIdentifier);
        builder.append(", statusCode=");
        builder.append(statusCode);
        builder.append(']');
        return builder.toString();
    }

}
