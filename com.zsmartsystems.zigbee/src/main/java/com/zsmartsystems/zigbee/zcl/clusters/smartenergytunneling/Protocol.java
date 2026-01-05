/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling;
import javax.annotation.Generated;

import com.zsmartsystems.zigbee.serialization.ZigBeeSerializable;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Protocol structure implementation.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-12T12:02:05Z")
public class Protocol implements ZigBeeSerializable {
    /**
     * Manufacturer Code structure field.
     */
    private Integer manufacturerCode;

    /**
     * Protocol ID structure field.
     */
    private Integer protocolId;


    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.
     */
    @Deprecated
    public Protocol() {
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param manufacturerCode {@link Integer} Manufacturer Code
     * @param protocolId {@link Integer} Protocol ID
     */
    public Protocol(
            Integer manufacturerCode,
            Integer protocolId) {

        this.manufacturerCode = manufacturerCode;
        this.protocolId = protocolId;
    }

    /**
     * Gets Manufacturer Code.
     *
     * @return the Manufacturer Code
     */
    public Integer getManufacturerCode() {
        return manufacturerCode;
    }

    /**
     * Sets Manufacturer Code.
     *
     * @param manufacturerCode the Manufacturer Code
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setManufacturerCode(final Integer manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    /**
     * Gets Protocol ID.
     *
     * @return the Protocol ID
     */
    public Integer getProtocolId() {
        return protocolId;
    }

    /**
     * Sets Protocol ID.
     *
     * @param protocolId the Protocol ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setProtocolId(final Integer protocolId) {
        this.protocolId = protocolId;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(manufacturerCode, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(protocolId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        manufacturerCode = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        protocolId = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(77);
        builder.append("Protocol [");
        builder.append(super.toString());
        builder.append(", manufacturerCode=");
        builder.append(manufacturerCode);
        builder.append(", protocolId=");
        builder.append(protocolId);
        builder.append(']');
        return builder.toString();
    }
}
