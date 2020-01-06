/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-06T18:45:28Z")
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
     * @return the Protocol command
     */
    public Protocol setManufacturerCode(final Integer manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
        return this;
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
     * @return the Protocol command
     */
    public Protocol setProtocolId(final Integer protocolId) {
        this.protocolId = protocolId;
        return this;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(manufacturerCode, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(protocolId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        manufacturerCode = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        protocolId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
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
