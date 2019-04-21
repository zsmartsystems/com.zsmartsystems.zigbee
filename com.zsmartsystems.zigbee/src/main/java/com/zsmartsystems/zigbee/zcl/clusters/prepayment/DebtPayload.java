/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
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
     */
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
     */
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
     */
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
     */
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
        collectionTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        amountCollected = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        debtType = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        outstandingDebt = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
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
