/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.field;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zcl.ZclListItemField;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Write Attribute Status Record field.
 *
 * @author Chris Jackson
 */
public class WriteAttributeStatusRecord implements ZclListItemField {
    /**
     * The status.
     */
    private ZclStatus status;

    /**
     * The attribute identifier.
     */
    private int attributeIdentifier;

    /**
     * Gets attribute identifier.
     *
     * @return the attribute identifier
     */
    public int getAttributeIdentifier() {
        return attributeIdentifier;
    }

    /**
     * Sets attribute identifier.
     *
     * @param attributeIdentifier the attribute identifier
     */
    public void setAttributeIdentifier(int attributeIdentifier) {
        this.attributeIdentifier = attributeIdentifier;
    }

    /**
     * Gets the {@link ZclStatus}.
     *
     * @return the {@link ZclStatus}
     */
    public ZclStatus getStatus() {
        return status;
    }

    /**
     * Sets status
     *
     * @param status the {@link ZclStatus}
     */
    public void setStatus(ZclStatus status) {
        this.status = status;
    }

    @Override
    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(status, ZclDataType.ZCL_STATUS);
        serializer.appendZigBeeType(attributeIdentifier, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZigBeeDeserializer deserializer) {
        status = (ZclStatus) deserializer.readZigBeeType(ZclDataType.ZCL_STATUS);
        if (status != ZclStatus.SUCCESS) {
            attributeIdentifier = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
    }

    @Override
    public String toString() {
        return "WriteAttributeStatusRecord [status=" + status + ", attributeIdentifier=" + attributeIdentifier + "]";
    }
}
