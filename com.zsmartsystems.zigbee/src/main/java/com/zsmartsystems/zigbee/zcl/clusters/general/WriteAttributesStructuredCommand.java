/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.general;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Write Attributes Structured Command value object class.
 * <p>
 * The write attributes structured command is generated when a device wishes to
 * change the values of one or more attributes located on another device. Each write
 * attribute record shall contain the identifier and the actual value of the attribute, or
 * element thereof, to be written.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class WriteAttributesStructuredCommand extends ZclCommand {
    /**
     * Attribute selectors command message field.
     */
    private Object attributeSelectors;

    /**
     * Default constructor.
     */
    public WriteAttributesStructuredCommand() {
        genericCommand = true;
        commandId = 15;
        commandDirection = true;
    }

    /**
     * Sets the cluster ID for <i>generic</i> commands. {@link WriteAttributesStructuredCommand} is a <i>generic</i> command.
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
     * Gets Attribute selectors.
     *
     * @return the Attribute selectors
     */
    public Object getAttributeSelectors() {
        return attributeSelectors;
    }

    /**
     * Sets Attribute selectors.
     *
     * @param attributeSelectors the Attribute selectors
     */
    public void setAttributeSelectors(final Object attributeSelectors) {
        this.attributeSelectors = attributeSelectors;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(attributeSelectors, ZclDataType.N_X_ATTRIBUTE_SELECTOR);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        attributeSelectors = (Object) deserializer.deserialize(ZclDataType.N_X_ATTRIBUTE_SELECTOR);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(73);
        builder.append("WriteAttributesStructuredCommand [");
        builder.append(super.toString());
        builder.append(", attributeSelectors=");
        builder.append(attributeSelectors);
        builder.append(']');
        return builder.toString();
    }

}
