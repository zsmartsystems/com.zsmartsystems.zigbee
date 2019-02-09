/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.onoff;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Off With Effect Command value object class.
 * <p>
 * Cluster: <b>On/Off</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the On/Off cluster.
 * <p>
 * The Off With Effect command allows devices to be turned off using enhanced ways of fading.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class OffWithEffectCommand extends ZclCommand {
    /**
     * Effect Identifier command message field.
     * <p>
     * The Effect Identifier field is 8-bits in length and specifies the fading effect to use
     * when switching the device off.
     */
    private Integer effectIdentifier;

    /**
     * Effect Variant command message field.
     * <p>
     * The Effect Variant field is 8-bits in length and is used to indicate which variant of the
     * effect, indicated in the Effect Identifier field, should be triggered. If a device does
     * not support the given variant, it shall use the default variant. This field is dependent
     * on the value of the Effect Identifier field.
     */
    private Integer effectVariant;

    /**
     * Default constructor.
     */
    public OffWithEffectCommand() {
        genericCommand = false;
        clusterId = 0x0006;
        commandId = 64;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Effect Identifier.
     * <p>
     * The Effect Identifier field is 8-bits in length and specifies the fading effect to use
     * when switching the device off.
     *
     * @return the Effect Identifier
     */
    public Integer getEffectIdentifier() {
        return effectIdentifier;
    }

    /**
     * Sets Effect Identifier.
     * <p>
     * The Effect Identifier field is 8-bits in length and specifies the fading effect to use
     * when switching the device off.
     *
     * @param effectIdentifier the Effect Identifier
     */
    public void setEffectIdentifier(final Integer effectIdentifier) {
        this.effectIdentifier = effectIdentifier;
    }

    /**
     * Gets Effect Variant.
     * <p>
     * The Effect Variant field is 8-bits in length and is used to indicate which variant of the
     * effect, indicated in the Effect Identifier field, should be triggered. If a device does
     * not support the given variant, it shall use the default variant. This field is dependent
     * on the value of the Effect Identifier field.
     *
     * @return the Effect Variant
     */
    public Integer getEffectVariant() {
        return effectVariant;
    }

    /**
     * Sets Effect Variant.
     * <p>
     * The Effect Variant field is 8-bits in length and is used to indicate which variant of the
     * effect, indicated in the Effect Identifier field, should be triggered. If a device does
     * not support the given variant, it shall use the default variant. This field is dependent
     * on the value of the Effect Identifier field.
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
