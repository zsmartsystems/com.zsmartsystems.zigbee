/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.greenpower;
import javax.annotation.Generated;

import com.zsmartsystems.zigbee.serialization.ZigBeeSerializable;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Gp Pairing Configuration Group List structure implementation.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-12T12:02:05Z")
public class GpPairingConfigurationGroupList implements ZigBeeSerializable {
    /**
     * Sink Group structure field.
     */
    private Integer sinkGroup;

    /**
     * Alias structure field.
     */
    private Integer alias;


    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.
     */
    @Deprecated
    public GpPairingConfigurationGroupList() {
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param sinkGroup {@link Integer} Sink Group
     * @param alias {@link Integer} Alias
     */
    public GpPairingConfigurationGroupList(
            Integer sinkGroup,
            Integer alias) {

        this.sinkGroup = sinkGroup;
        this.alias = alias;
    }

    /**
     * Gets Sink Group.
     *
     * @return the Sink Group
     */
    public Integer getSinkGroup() {
        return sinkGroup;
    }

    /**
     * Sets Sink Group.
     *
     * @param sinkGroup the Sink Group
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSinkGroup(final Integer sinkGroup) {
        this.sinkGroup = sinkGroup;
    }

    /**
     * Gets Alias.
     *
     * @return the Alias
     */
    public Integer getAlias() {
        return alias;
    }

    /**
     * Sets Alias.
     *
     * @param alias the Alias
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setAlias(final Integer alias) {
        this.alias = alias;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(sinkGroup, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(alias, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        sinkGroup = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        alias = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(88);
        builder.append("GpPairingConfigurationGroupList [");
        builder.append(super.toString());
        builder.append(", sinkGroup=");
        builder.append(sinkGroup);
        builder.append(", alias=");
        builder.append(alias);
        builder.append(']');
        return builder.toString();
    }
}
