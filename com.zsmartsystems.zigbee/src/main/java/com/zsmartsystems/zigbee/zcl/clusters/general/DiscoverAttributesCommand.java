/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.general;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Discover Attributes Command value object class.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * The discover attributes command is generated when a remote device wishes to discover the
 * identifiers and types of the attributes on a device which are supported within the cluster to
 * which this command is directed.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class DiscoverAttributesCommand extends ZclCommand {
    /**
     * Start Attribute Identifier command message field.
     * <p>
     * The start attribute identifier field is 16 bits in length and specifies the value of the
     * identifier at which to begin the attribute discovery.
     */
    private Integer startAttributeIdentifier;

    /**
     * Maximum Attribute Identifiers command message field.
     * <p>
     * The maximum attribute identifiers field is 8 bits in length and specifies the maximum
     * number of attribute identifiers that are to be returned in the resulting Discover
     * Attributes Response command.
     */
    private Integer maximumAttributeIdentifiers;

    /**
     * Default constructor.
     */
    public DiscoverAttributesCommand() {
        genericCommand = true;
        commandId = 12;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Sets the cluster ID for <i>generic</i> commands. {@link DiscoverAttributesCommand} is a <i>generic</i> command.
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
     * Gets Start Attribute Identifier.
     * <p>
     * The start attribute identifier field is 16 bits in length and specifies the value of the
     * identifier at which to begin the attribute discovery.
     *
     * @return the Start Attribute Identifier
     */
    public Integer getStartAttributeIdentifier() {
        return startAttributeIdentifier;
    }

    /**
     * Sets Start Attribute Identifier.
     * <p>
     * The start attribute identifier field is 16 bits in length and specifies the value of the
     * identifier at which to begin the attribute discovery.
     *
     * @param startAttributeIdentifier the Start Attribute Identifier
     */
    public void setStartAttributeIdentifier(final Integer startAttributeIdentifier) {
        this.startAttributeIdentifier = startAttributeIdentifier;
    }

    /**
     * Gets Maximum Attribute Identifiers.
     * <p>
     * The maximum attribute identifiers field is 8 bits in length and specifies the maximum
     * number of attribute identifiers that are to be returned in the resulting Discover
     * Attributes Response command.
     *
     * @return the Maximum Attribute Identifiers
     */
    public Integer getMaximumAttributeIdentifiers() {
        return maximumAttributeIdentifiers;
    }

    /**
     * Sets Maximum Attribute Identifiers.
     * <p>
     * The maximum attribute identifiers field is 8 bits in length and specifies the maximum
     * number of attribute identifiers that are to be returned in the resulting Discover
     * Attributes Response command.
     *
     * @param maximumAttributeIdentifiers the Maximum Attribute Identifiers
     */
    public void setMaximumAttributeIdentifiers(final Integer maximumAttributeIdentifiers) {
        this.maximumAttributeIdentifiers = maximumAttributeIdentifiers;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(startAttributeIdentifier, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(maximumAttributeIdentifiers, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        startAttributeIdentifier = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        maximumAttributeIdentifiers = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(119);
        builder.append("DiscoverAttributesCommand [");
        builder.append(super.toString());
        builder.append(", startAttributeIdentifier=");
        builder.append(startAttributeIdentifier);
        builder.append(", maximumAttributeIdentifiers=");
        builder.append(maximumAttributeIdentifiers);
        builder.append(']');
        return builder.toString();
    }

}
