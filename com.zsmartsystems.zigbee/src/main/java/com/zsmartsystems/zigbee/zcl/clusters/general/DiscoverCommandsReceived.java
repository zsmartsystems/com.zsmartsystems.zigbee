/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.general;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Discover Commands Received value object class.
 * <p>
 * Cluster: <b>General</b>. Command ID 0x11 is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * The Discover Commands Received command is generated when a remote device wishes to discover
 * the optional and mandatory commands the cluster to which this command is sent can process.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class DiscoverCommandsReceived extends ZclGeneralCommand {
    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x11;

    /**
     * Start Command Identifier command message field.
     */
    private Integer startCommandIdentifier;

    /**
     * Maximum Command Identifiers command message field.
     */
    private Integer maximumCommandIdentifiers;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public DiscoverCommandsReceived() {
        commandId = COMMAND_ID;
        genericCommand = true;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param startCommandIdentifier {@link Integer} Start Command Identifier
     * @param maximumCommandIdentifiers {@link Integer} Maximum Command Identifiers
     */
    public DiscoverCommandsReceived(
            Integer startCommandIdentifier,
            Integer maximumCommandIdentifiers) {

        commandId = COMMAND_ID;
        genericCommand = true;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.startCommandIdentifier = startCommandIdentifier;
        this.maximumCommandIdentifiers = maximumCommandIdentifiers;
    }

    /**
     * Sets the cluster ID for <i>generic</i> commands. {@link DiscoverCommandsReceived} is a <i>generic</i> command.
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
     * Gets Start Command Identifier.
     *
     * @return the Start Command Identifier
     */
    public Integer getStartCommandIdentifier() {
        return startCommandIdentifier;
    }

    /**
     * Sets Start Command Identifier.
     *
     * @param startCommandIdentifier the Start Command Identifier
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setStartCommandIdentifier(final Integer startCommandIdentifier) {
        this.startCommandIdentifier = startCommandIdentifier;
    }

    /**
     * Gets Maximum Command Identifiers.
     *
     * @return the Maximum Command Identifiers
     */
    public Integer getMaximumCommandIdentifiers() {
        return maximumCommandIdentifiers;
    }

    /**
     * Sets Maximum Command Identifiers.
     *
     * @param maximumCommandIdentifiers the Maximum Command Identifiers
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setMaximumCommandIdentifiers(final Integer maximumCommandIdentifiers) {
        this.maximumCommandIdentifiers = maximumCommandIdentifiers;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(startCommandIdentifier, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(maximumCommandIdentifiers, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        startCommandIdentifier = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        maximumCommandIdentifiers = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(114);
        builder.append("DiscoverCommandsReceived [");
        builder.append(super.toString());
        builder.append(", startCommandIdentifier=");
        builder.append(startCommandIdentifier);
        builder.append(", maximumCommandIdentifiers=");
        builder.append(maximumCommandIdentifiers);
        builder.append(']');
        return builder.toString();
    }

}
