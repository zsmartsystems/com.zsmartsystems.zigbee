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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class WriteAttributesStructuredCommand extends ZclCommand {
    /**
     * Status command message field.
     *
     * Status is only provided if the command was successful, and the
     * attribute selector records are not included for successfully
     * written attributes, in order to save bandwidth.
     */
    private ZclStatus status;

    /**
     * Attribute selectors command message field.
     *
     * Note that write attribute status records are not included for successfully
     * written attributes, in order to save bandwidth. In the case of successful
     * writing of all attributes, only a single  write attribute status record
     * SHALL be included in the command, with the status field set to SUCCESS and the
     * attribute identifier and selector fields omitted.
     */
    private Object attributeSelectors;

    /**
     * Default constructor.
     */
    public WriteAttributesStructuredCommand() {
        genericCommand = true;
        commandId = 15;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
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
     * Gets Status.
     *
     * Status is only provided if the command was successful, and the
     * attribute selector records are not included for successfully
     * written attributes, in order to save bandwidth.
     *
     * @return the Status
     */
    public ZclStatus getStatus() {
        return status;
    }

    /**
     * Sets Status.
     *
     * Status is only provided if the command was successful, and the
     * attribute selector records are not included for successfully
     * written attributes, in order to save bandwidth.
     *
     * @param status the Status
     */
    public void setStatus(final ZclStatus status) {
        this.status = status;
    }

    /**
     * Gets Attribute selectors.
     *
     * Note that write attribute status records are not included for successfully
     * written attributes, in order to save bandwidth. In the case of successful
     * writing of all attributes, only a single  write attribute status record
     * SHALL be included in the command, with the status field set to SUCCESS and the
     * attribute identifier and selector fields omitted.
     *
     * @return the Attribute selectors
     */
    public Object getAttributeSelectors() {
        return attributeSelectors;
    }

    /**
     * Sets Attribute selectors.
     *
     * Note that write attribute status records are not included for successfully
     * written attributes, in order to save bandwidth. In the case of successful
     * writing of all attributes, only a single  write attribute status record
     * SHALL be included in the command, with the status field set to SUCCESS and the
     * attribute identifier and selector fields omitted.
     *
     * @param attributeSelectors the Attribute selectors
     */
    public void setAttributeSelectors(final Object attributeSelectors) {
        this.attributeSelectors = attributeSelectors;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        if (status == ZclStatus.SUCCESS) {
            serializer.serialize(status, ZclDataType.ZCL_STATUS);
            return;
        }
        serializer.serialize(attributeSelectors, ZclDataType.N_X_ATTRIBUTE_SELECTOR);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        if (deserializer.getRemainingLength() == 1) {
            status = (ZclStatus) deserializer.deserialize(ZclDataType.ZCL_STATUS);
            return;
        }
        attributeSelectors = (Object) deserializer.deserialize(ZclDataType.N_X_ATTRIBUTE_SELECTOR);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(99);
        builder.append("WriteAttributesStructuredCommand [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(", attributeSelectors=");
        builder.append(attributeSelectors);
        builder.append(']');
        return builder.toString();
    }

}
