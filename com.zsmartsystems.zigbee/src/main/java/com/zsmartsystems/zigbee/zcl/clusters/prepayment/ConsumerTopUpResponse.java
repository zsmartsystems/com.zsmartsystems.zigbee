/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.prepayment;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Consumer Top Up Response value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command ID 0x03 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is send in response to the ConsumerTopUp Command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class ConsumerTopUpResponse extends ZclPrepaymentCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0705;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x03;

    /**
     * Result Type command message field.
     */
    private Integer resultType;

    /**
     * Top Up Value command message field.
     */
    private Integer topUpValue;

    /**
     * Source Of Top Up command message field.
     */
    private Integer sourceOfTopUp;

    /**
     * Credit Remaining command message field.
     */
    private Integer creditRemaining;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public ConsumerTopUpResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param resultType {@link Integer} Result Type
     * @param topUpValue {@link Integer} Top Up Value
     * @param sourceOfTopUp {@link Integer} Source Of Top Up
     * @param creditRemaining {@link Integer} Credit Remaining
     */
    public ConsumerTopUpResponse(
            Integer resultType,
            Integer topUpValue,
            Integer sourceOfTopUp,
            Integer creditRemaining) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.resultType = resultType;
        this.topUpValue = topUpValue;
        this.sourceOfTopUp = sourceOfTopUp;
        this.creditRemaining = creditRemaining;
    }

    /**
     * Gets Result Type.
     *
     * @return the Result Type
     */
    public Integer getResultType() {
        return resultType;
    }

    /**
     * Sets Result Type.
     *
     * @param resultType the Result Type
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setResultType(final Integer resultType) {
        this.resultType = resultType;
    }

    /**
     * Gets Top Up Value.
     *
     * @return the Top Up Value
     */
    public Integer getTopUpValue() {
        return topUpValue;
    }

    /**
     * Sets Top Up Value.
     *
     * @param topUpValue the Top Up Value
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTopUpValue(final Integer topUpValue) {
        this.topUpValue = topUpValue;
    }

    /**
     * Gets Source Of Top Up.
     *
     * @return the Source Of Top Up
     */
    public Integer getSourceOfTopUp() {
        return sourceOfTopUp;
    }

    /**
     * Sets Source Of Top Up.
     *
     * @param sourceOfTopUp the Source Of Top Up
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSourceOfTopUp(final Integer sourceOfTopUp) {
        this.sourceOfTopUp = sourceOfTopUp;
    }

    /**
     * Gets Credit Remaining.
     *
     * @return the Credit Remaining
     */
    public Integer getCreditRemaining() {
        return creditRemaining;
    }

    /**
     * Sets Credit Remaining.
     *
     * @param creditRemaining the Credit Remaining
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setCreditRemaining(final Integer creditRemaining) {
        this.creditRemaining = creditRemaining;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(resultType, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(topUpValue, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(sourceOfTopUp, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(creditRemaining, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        resultType = deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        topUpValue = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        sourceOfTopUp = deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        creditRemaining = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(152);
        builder.append("ConsumerTopUpResponse [");
        builder.append(super.toString());
        builder.append(", resultType=");
        builder.append(resultType);
        builder.append(", topUpValue=");
        builder.append(topUpValue);
        builder.append(", sourceOfTopUp=");
        builder.append(sourceOfTopUp);
        builder.append(", creditRemaining=");
        builder.append(creditRemaining);
        builder.append(']');
        return builder.toString();
    }

}
