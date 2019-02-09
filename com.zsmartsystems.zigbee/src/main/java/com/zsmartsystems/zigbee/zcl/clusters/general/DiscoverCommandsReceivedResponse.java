/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.general;

import java.util.List;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Discover Commands Received Response value object class.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * The Discover Commands Received Response is generated in response to a Discover Commands
 * Received command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T19:19:25Z")
public class DiscoverCommandsReceivedResponse extends ZclCommand {
    /**
     * Discovery Complete command message field.
     */
    private Boolean discoveryComplete;

    /**
     * Command Identifiers command message field.
     */
    private List<Integer> commandIdentifiers;

    /**
     * Default constructor.
     */
    public DiscoverCommandsReceivedResponse() {
        genericCommand = true;
        commandId = 18;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Sets the cluster ID for <i>generic</i> commands. {@link DiscoverCommandsReceivedResponse} is a <i>generic</i> command.
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
     * Gets Discovery Complete.
     *
     * @return the Discovery Complete
     */
    public Boolean getDiscoveryComplete() {
        return discoveryComplete;
    }

    /**
     * Sets Discovery Complete.
     *
     * @param discoveryComplete the Discovery Complete
     */
    public void setDiscoveryComplete(final Boolean discoveryComplete) {
        this.discoveryComplete = discoveryComplete;
    }

    /**
     * Gets Command Identifiers.
     *
     * @return the Command Identifiers
     */
    public List<Integer> getCommandIdentifiers() {
        return commandIdentifiers;
    }

    /**
     * Sets Command Identifiers.
     *
     * @param commandIdentifiers the Command Identifiers
     */
    public void setCommandIdentifiers(final List<Integer> commandIdentifiers) {
        this.commandIdentifiers = commandIdentifiers;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(discoveryComplete, ZclDataType.BOOLEAN);
        serializer.serialize(commandIdentifiers, ZclDataType.X_UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        discoveryComplete = (Boolean) deserializer.deserialize(ZclDataType.BOOLEAN);
        commandIdentifiers = (List<Integer>) deserializer.deserialize(ZclDataType.X_UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(110);
        builder.append("DiscoverCommandsReceivedResponse [");
        builder.append(super.toString());
        builder.append(", discoveryComplete=");
        builder.append(discoveryComplete);
        builder.append(", commandIdentifiers=");
        builder.append(commandIdentifiers);
        builder.append(']');
        return builder.toString();
    }

}
