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
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Credit Adjustment value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: The CreditAdjustment command is sent to update the accounting base for the
 * Prepayment meter.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class CreditAdjustment extends ZclCommand {
    /**
     * Issuer Event ID command message field.
     */
    private Integer issuerEventId;

    /**
     * Start Time command message field.
     */
    private Calendar startTime;

    /**
     * Credit Adjustment Type command message field.
     */
    private Integer creditAdjustmentType;

    /**
     * Credit Adjustment Value command message field.
     */
    private Integer creditAdjustmentValue;

    /**
     * Default constructor.
     */
    public CreditAdjustment() {
        genericCommand = false;
        clusterId = 0x0705;
        commandId = 5;
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
     * Gets Start Time.
     *
     * @return the Start Time
     */
    public Calendar getStartTime() {
        return startTime;
    }

    /**
     * Sets Start Time.
     *
     * @param startTime the Start Time
     */
    public void setStartTime(final Calendar startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets Credit Adjustment Type.
     *
     * @return the Credit Adjustment Type
     */
    public Integer getCreditAdjustmentType() {
        return creditAdjustmentType;
    }

    /**
     * Sets Credit Adjustment Type.
     *
     * @param creditAdjustmentType the Credit Adjustment Type
     */
    public void setCreditAdjustmentType(final Integer creditAdjustmentType) {
        this.creditAdjustmentType = creditAdjustmentType;
    }

    /**
     * Gets Credit Adjustment Value.
     *
     * @return the Credit Adjustment Value
     */
    public Integer getCreditAdjustmentValue() {
        return creditAdjustmentValue;
    }

    /**
     * Sets Credit Adjustment Value.
     *
     * @param creditAdjustmentValue the Credit Adjustment Value
     */
    public void setCreditAdjustmentValue(final Integer creditAdjustmentValue) {
        this.creditAdjustmentValue = creditAdjustmentValue;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(issuerEventId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(startTime, ZclDataType.UTCTIME);
        serializer.serialize(creditAdjustmentType, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(creditAdjustmentValue, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        issuerEventId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        startTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        creditAdjustmentType = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        creditAdjustmentValue = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(162);
        builder.append("CreditAdjustment [");
        builder.append(super.toString());
        builder.append(", issuerEventId=");
        builder.append(issuerEventId);
        builder.append(", startTime=");
        builder.append(startTime);
        builder.append(", creditAdjustmentType=");
        builder.append(creditAdjustmentType);
        builder.append(", creditAdjustmentValue=");
        builder.append(creditAdjustmentValue);
        builder.append(']');
        return builder.toString();
    }

}
