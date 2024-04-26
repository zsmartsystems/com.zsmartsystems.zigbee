/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.prepayment;
import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.serialization.ZigBeeSerializable;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Debt Payload structure implementation.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-12T12:02:05Z")
public class DebtPayload implements ZigBeeSerializable {
    /**
     * Collection Time structure field.
     */
    private Calendar collectionTime;

    /**
     * Amount Collected structure field.
     */
    private Integer amountCollected;

    /**
     * Debt Type structure field.
     */
    private Integer debtType;

    /**
     * Outstanding Debt structure field.
     */
    private Integer outstandingDebt;


    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.
     */
    @Deprecated
    public DebtPayload() {
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param collectionTime {@link Calendar} Collection Time
     * @param amountCollected {@link Integer} Amount Collected
     * @param debtType {@link Integer} Debt Type
     * @param outstandingDebt {@link Integer} Outstanding Debt
     */
    public DebtPayload(
            Calendar collectionTime,
            Integer amountCollected,
            Integer debtType,
            Integer outstandingDebt) {

        this.collectionTime = collectionTime;
        this.amountCollected = amountCollected;
        this.debtType = debtType;
        this.outstandingDebt = outstandingDebt;
    }

    /**
     * Gets Collection Time.
     *
     * @return the Collection Time
     */
    public Calendar getCollectionTime() {
        return collectionTime;
    }

    /**
     * Sets Collection Time.
     *
     * @param collectionTime the Collection Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setCollectionTime(final Calendar collectionTime) {
        this.collectionTime = collectionTime;
    }

    /**
     * Gets Amount Collected.
     *
     * @return the Amount Collected
     */
    public Integer getAmountCollected() {
        return amountCollected;
    }

    /**
     * Sets Amount Collected.
     *
     * @param amountCollected the Amount Collected
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setAmountCollected(final Integer amountCollected) {
        this.amountCollected = amountCollected;
    }

    /**
     * Gets Debt Type.
     *
     * @return the Debt Type
     */
    public Integer getDebtType() {
        return debtType;
    }

    /**
     * Sets Debt Type.
     *
     * @param debtType the Debt Type
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setDebtType(final Integer debtType) {
        this.debtType = debtType;
    }

    /**
     * Gets Outstanding Debt.
     *
     * @return the Outstanding Debt
     */
    public Integer getOutstandingDebt() {
        return outstandingDebt;
    }

    /**
     * Sets Outstanding Debt.
     *
     * @param outstandingDebt the Outstanding Debt
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setOutstandingDebt(final Integer outstandingDebt) {
        this.outstandingDebt = outstandingDebt;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(collectionTime, ZclDataType.UTCTIME);
        serializer.serialize(amountCollected, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(debtType, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(outstandingDebt, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        collectionTime = deserializer.deserialize(ZclDataType.UTCTIME);
        amountCollected = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        debtType = deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        outstandingDebt = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(146);
        builder.append("DebtPayload [");
        builder.append(super.toString());
        builder.append(", collectionTime=");
        builder.append(collectionTime);
        builder.append(", amountCollected=");
        builder.append(amountCollected);
        builder.append(", debtType=");
        builder.append(debtType);
        builder.append(", outstandingDebt=");
        builder.append(outstandingDebt);
        builder.append(']');
        return builder.toString();
    }
}
