/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.onoff;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Off With Effect Command value object class.
 * <p>
 * Cluster: <b>On/Off</b>. Command ID 0x40 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the On/Off cluster.
 * <p>
 * The Off With Effect command allows devices to be turned off using enhanced ways of fading.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class OffWithEffectCommand extends ZclOnOffCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0006;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x40;

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
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public OffWithEffectCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param effectIdentifier {@link Integer} Effect Identifier
     * @param effectVariant {@link Integer} Effect Variant
     */
    public OffWithEffectCommand(
            Integer effectIdentifier,
            Integer effectVariant) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.effectIdentifier = effectIdentifier;
        this.effectVariant = effectVariant;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
        effectIdentifier = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        effectVariant = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
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
