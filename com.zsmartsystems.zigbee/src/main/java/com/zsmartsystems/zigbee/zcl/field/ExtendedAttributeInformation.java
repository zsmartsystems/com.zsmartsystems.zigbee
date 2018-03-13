/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.field;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zcl.ZclListItemField;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Extended Attribute Information field.
 *
 * @author Chris Jackson
 */
public class ExtendedAttributeInformation implements ZclListItemField {
    /**
     * The attribute identifier.
     */
    private int attributeIdentifier;

    /**
     * The attribute data type.
     */
    private int attributeDataType;

    /**
     * True if the attribute can be read
     */
    private boolean readable;

    /**
     * True if the attribute can be written
     */
    private boolean writable;

    /**
     * True if the attribute provides reports
     */
    private boolean reportable;

    /**
     * @return the readable
     */
    public boolean isReadable() {
        return readable;
    }

    /**
     * @param readable the readable to set
     */
    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    /**
     * @return the writable
     */
    public boolean isWritable() {
        return writable;
    }

    /**
     * @param writable the writable to set
     */
    public void setWritable(boolean writable) {
        this.writable = writable;
    }

    /**
     * @return the reportable
     */
    public boolean isReportable() {
        return reportable;
    }

    /**
     * @param reportable the reportable to set
     */
    public void setReportable(boolean reportable) {
        this.reportable = reportable;
    }

    /**
     * Gets attribute data type.
     *
     * @return the attribute data type
     */
    public int getAttributeDataType() {
        return attributeDataType;
    }

    /**
     * Sets attribute data type.
     *
     * @param attributeDataType the attribute data type
     */
    public void setAttributeDataType(int attributeDataType) {
        this.attributeDataType = attributeDataType;
    }

    /**
     * Gets attribute identifier.
     *
     * @return the attribute identifier
     */
    public int getAttributeIdentifier() {
        return attributeIdentifier;
    }

    /**
     * Sets attribute identifier
     *
     * @param attributeIdentifier the attribute
     */
    public void setAttributeIdentifier(int attributeIdentifier) {
        this.attributeIdentifier = attributeIdentifier;
    }

    @Override
    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(attributeIdentifier, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.appendZigBeeType(attributeDataType, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZigBeeDeserializer deserializer) {
        attributeIdentifier = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        attributeDataType = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        return "Extended Attribute Information: attributeDataType=" + attributeDataType + ", attributeIdentifier="
                + attributeIdentifier + ", readable=" + readable + ", writable=" + writable + ", reportable";
    }
}
