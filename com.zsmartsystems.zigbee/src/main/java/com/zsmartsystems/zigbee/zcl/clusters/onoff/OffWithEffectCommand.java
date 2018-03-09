/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.onoff;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Off With Effect Command value object class.
 * <p>
 * The Off With Effect command allows devices to be turned off using enhanced ways of fading.
 * <p>
 * Cluster: <b>On/Off</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the On/Off cluster.
 * <p>
 * Attributes and commands for switching devices between ‘On’ and ‘Off’ states.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class OffWithEffectCommand extends ZclCommand {
    /**
     * Effect Identifier command message field.
     */
    private Integer effectIdentifier;

    /**
     * Effect Variant command message field.
     */
    private Integer effectVariant;

    /**
     * Default constructor.
     */
    public OffWithEffectCommand() {
        genericCommand = false;
        clusterId = 6;
        commandId = 64;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Effect Identifier.
     *
     * @return the Effect Identifier
     */
    public Integer getEffectIdentifier() {
        return effectIdentifier;
    }

    /**
     * Sets Effect Identifier.
     *
     * @param effectIdentifier the Effect Identifier
     */
    public void setEffectIdentifier(final Integer effectIdentifier) {
        this.effectIdentifier = effectIdentifier;
    }

    /**
     * Gets Effect Variant.
     *
     * @return the Effect Variant
     */
    public Integer getEffectVariant() {
        return effectVariant;
    }

    /**
     * Sets Effect Variant.
     *
     * @param effectVariant the Effect Variant
     */
    public void setEffectVariant(final Integer effectVariant) {
        this.effectVariant = effectVariant;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(effectIdentifier, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(effectVariant, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        effectIdentifier = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        effectVariant = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(92);
        builder.append("OffWithEffectCommand [");
        builder.append(super.toString());
        builder.append(", effectIdentifier=");
        builder.append(effectIdentifier);
        builder.append(", effectVariant=");
        builder.append(effectVariant);
        builder.append(']');
        return builder.toString();
    }

}
