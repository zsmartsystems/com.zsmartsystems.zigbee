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

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Change Debt value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: The ChangeDebt command is send to the Metering Device to change the fuel or Non fuel
 * debt values.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ChangeDebt extends ZclCommand {
    /**
     * Issuer Event ID command message field.
     */
    private Integer issuerEventId;

    /**
     * Debt Label command message field.
     */
    private ByteArray debtLabel;

    /**
     * Debt Amount command message field.
     */
    private Integer debtAmount;

    /**
     * Debt Recovery Method command message field.
     */
    private Integer debtRecoveryMethod;

    /**
     * Debt Amount Type command message field.
     */
    private Integer debtAmountType;

    /**
     * Debt Recovery Start Time command message field.
     */
    private Calendar debtRecoveryStartTime;

    /**
     * Debt Recovery Collection Time command message field.
     */
    private Integer debtRecoveryCollectionTime;

    /**
     * Debt Recovery Frequency command message field.
     */
    private Integer debtRecoveryFrequency;

    /**
     * Debt Recovery Amount command message field.
     */
    private Integer debtRecoveryAmount;

    /**
     * Debt Recovery Balance Percentage command message field.
     */
    private Integer debtRecoveryBalancePercentage;

    /**
     * Default constructor.
     */
    public ChangeDebt() {
        genericCommand = false;
        clusterId = 0x0705;
        commandId = 2;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Issuer Event ID.
     *
     * @return the Issuer Event ID
     */
    public Integer getIssuerEventId() {
        return issuerEventId;
    }

    /**
     * Sets Issuer Event ID.
     *
     * @param issuerEventId the Issuer Event ID
     */
    public void setIssuerEventId(final Integer issuerEventId) {
        this.issuerEventId = issuerEventId;
    }

    /**
     * Gets Debt Label.
     *
     * @return the Debt Label
     */
    public ByteArray getDebtLabel() {
        return debtLabel;
    }

    /**
     * Sets Debt Label.
     *
     * @param debtLabel the Debt Label
     */
    public void setDebtLabel(final ByteArray debtLabel) {
        this.debtLabel = debtLabel;
    }

    /**
     * Gets Debt Amount.
     *
     * @return the Debt Amount
     */
    public Integer getDebtAmount() {
        return debtAmount;
    }

    /**
     * Sets Debt Amount.
     *
     * @param debtAmount the Debt Amount
     */
    public void setDebtAmount(final Integer debtAmount) {
        this.debtAmount = debtAmount;
    }

    /**
     * Gets Debt Recovery Method.
     *
     * @return the Debt Recovery Method
     */
    public Integer getDebtRecoveryMethod() {
        return debtRecoveryMethod;
    }

    /**
     * Sets Debt Recovery Method.
     *
     * @param debtRecoveryMethod the Debt Recovery Method
     */
    public void setDebtRecoveryMethod(final Integer debtRecoveryMethod) {
        this.debtRecoveryMethod = debtRecoveryMethod;
    }

    /**
     * Gets Debt Amount Type.
     *
     * @return the Debt Amount Type
     */
    public Integer getDebtAmountType() {
        return debtAmountType;
    }

    /**
     * Sets Debt Amount Type.
     *
     * @param debtAmountType the Debt Amount Type
     */
    public void setDebtAmountType(final Integer debtAmountType) {
        this.debtAmountType = debtAmountType;
    }

    /**
     * Gets Debt Recovery Start Time.
     *
     * @return the Debt Recovery Start Time
     */
    public Calendar getDebtRecoveryStartTime() {
        return debtRecoveryStartTime;
    }

    /**
     * Sets Debt Recovery Start Time.
     *
     * @param debtRecoveryStartTime the Debt Recovery Start Time
     */
    public void setDebtRecoveryStartTime(final Calendar debtRecoveryStartTime) {
        this.debtRecoveryStartTime = debtRecoveryStartTime;
    }

    /**
     * Gets Debt Recovery Collection Time.
     *
     * @return the Debt Recovery Collection Time
     */
    public Integer getDebtRecoveryCollectionTime() {
        return debtRecoveryCollectionTime;
    }

    /**
     * Sets Debt Recovery Collection Time.
     *
     * @param debtRecoveryCollectionTime the Debt Recovery Collection Time
     */
    public void setDebtRecoveryCollectionTime(final Integer debtRecoveryCollectionTime) {
        this.debtRecoveryCollectionTime = debtRecoveryCollectionTime;
    }

    /**
     * Gets Debt Recovery Frequency.
     *
     * @return the Debt Recovery Frequency
     */
    public Integer getDebtRecoveryFrequency() {
        return debtRecoveryFrequency;
    }

    /**
     * Sets Debt Recovery Frequency.
     *
     * @param debtRecoveryFrequency the Debt Recovery Frequency
     */
    public void setDebtRecoveryFrequency(final Integer debtRecoveryFrequency) {
        this.debtRecoveryFrequency = debtRecoveryFrequency;
    }

    /**
     * Gets Debt Recovery Amount.
     *
     * @return the Debt Recovery Amount
     */
    public Integer getDebtRecoveryAmount() {
        return debtRecoveryAmount;
    }

    /**
     * Sets Debt Recovery Amount.
     *
     * @param debtRecoveryAmount the Debt Recovery Amount
     */
    public void setDebtRecoveryAmount(final Integer debtRecoveryAmount) {
        this.debtRecoveryAmount = debtRecoveryAmount;
    }

    /**
     * Gets Debt Recovery Balance Percentage.
     *
     * @return the Debt Recovery Balance Percentage
     */
    public Integer getDebtRecoveryBalancePercentage() {
        return debtRecoveryBalancePercentage;
    }

    /**
     * Sets Debt Recovery Balance Percentage.
     *
     * @param debtRecoveryBalancePercentage the Debt Recovery Balance Percentage
     */
    public void setDebtRecoveryBalancePercentage(final Integer debtRecoveryBalancePercentage) {
        this.debtRecoveryBalancePercentage = debtRecoveryBalancePercentage;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(issuerEventId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(debtLabel, ZclDataType.OCTET_STRING);
        serializer.serialize(debtAmount, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(debtRecoveryMethod, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(debtAmountType, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(debtRecoveryStartTime, ZclDataType.UTCTIME);
        serializer.serialize(debtRecoveryCollectionTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(debtRecoveryFrequency, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(debtRecoveryAmount, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(debtRecoveryBalancePercentage, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        issuerEventId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        debtLabel = (ByteArray) deserializer.deserialize(ZclDataType.OCTET_STRING);
        debtAmount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        debtRecoveryMethod = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        debtAmountType = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        debtRecoveryStartTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        debtRecoveryCollectionTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        debtRecoveryFrequency = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        debtRecoveryAmount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        debtRecoveryBalancePercentage = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(392);
        builder.append("ChangeDebt [");
        builder.append(super.toString());
        builder.append(", issuerEventId=");
        builder.append(issuerEventId);
        builder.append(", debtLabel=");
        builder.append(debtLabel);
        builder.append(", debtAmount=");
        builder.append(debtAmount);
        builder.append(", debtRecoveryMethod=");
        builder.append(debtRecoveryMethod);
        builder.append(", debtAmountType=");
        builder.append(debtAmountType);
        builder.append(", debtRecoveryStartTime=");
        builder.append(debtRecoveryStartTime);
        builder.append(", debtRecoveryCollectionTime=");
        builder.append(debtRecoveryCollectionTime);
        builder.append(", debtRecoveryFrequency=");
        builder.append(debtRecoveryFrequency);
        builder.append(", debtRecoveryAmount=");
        builder.append(debtRecoveryAmount);
        builder.append(", debtRecoveryBalancePercentage=");
        builder.append(debtRecoveryBalancePercentage);
        builder.append(']');
        return builder.toString();
    }

}
