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

import java.util.List;
import com.zsmartsystems.zigbee.zcl.field.ExtendedAttributeInformation;

/**
 * Discover Attributes Extended Response value object class.
 * <p>
 * The Discover Attributes Extended Response command is generated in response to a Discover Attributes
 * Extended command.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class DiscoverAttributesExtendedResponse extends ZclCommand {
    /**
     * Discovery complete command message field.
     */
    private Boolean discoveryComplete;

    /**
     * Attribute Information command message field.
     */
    private List<ExtendedAttributeInformation> attributeInformation;

    /**
     * Default constructor.
     */
    public DiscoverAttributesExtendedResponse() {
        genericCommand = true;
        commandId = 22;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Sets the cluster ID for <i>generic</i> commands. {@link DiscoverAttributesExtendedResponse} is a <i>generic</i> command.
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
     * Gets Discovery complete.
     *
     * @return the Discovery complete
     */
    public Boolean getDiscoveryComplete() {
        return discoveryComplete;
    }

    /**
     * Sets Discovery complete.
     *
     * @param discoveryComplete the Discovery complete
     */
    public void setDiscoveryComplete(final Boolean discoveryComplete) {
        this.discoveryComplete = discoveryComplete;
    }

    /**
     * Gets Attribute Information.
     *
     * @return the Attribute Information
     */
    public List<ExtendedAttributeInformation> getAttributeInformation() {
        return attributeInformation;
    }

    /**
     * Sets Attribute Information.
     *
     * @param attributeInformation the Attribute Information
     */
    public void setAttributeInformation(final List<ExtendedAttributeInformation> attributeInformation) {
        this.attributeInformation = attributeInformation;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(discoveryComplete, ZclDataType.BOOLEAN);
        serializer.serialize(attributeInformation, ZclDataType.N_X_EXTENDED_ATTRIBUTE_INFORMATION);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        discoveryComplete = (Boolean) deserializer.deserialize(ZclDataType.BOOLEAN);
        attributeInformation = (List<ExtendedAttributeInformation>) deserializer.deserialize(ZclDataType.N_X_EXTENDED_ATTRIBUTE_INFORMATION);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(114);
        builder.append("DiscoverAttributesExtendedResponse [");
        builder.append(super.toString());
        builder.append(", discoveryComplete=");
        builder.append(discoveryComplete);
        builder.append(", attributeInformation=");
        builder.append(attributeInformation);
        builder.append(']');
        return builder.toString();
    }

}
