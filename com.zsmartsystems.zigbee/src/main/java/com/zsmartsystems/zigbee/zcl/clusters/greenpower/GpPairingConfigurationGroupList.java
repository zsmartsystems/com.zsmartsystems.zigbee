/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-07-04T21:54:11Z")
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
     */
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
     */
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
        sinkGroup = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        alias = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
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
