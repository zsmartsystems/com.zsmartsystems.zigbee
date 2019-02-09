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
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Top Up Payload structure implementation.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class TopUpPayload implements ZigBeeSerializable {
    /**
     * Top Up Code structure field.
     */
    private ByteArray topUpCode;

    /**
     * Top Up Amount structure field.
     */
    private Integer topUpAmount;

    /**
     * Top Up Time structure field.
     */
    private Calendar topUpTime;



    /**
     * Gets Top Up Code.
     *
     * @return the Top Up Code
     */
    public ByteArray getTopUpCode() {
        return topUpCode;
    }

    /**
     * Sets Top Up Code.
     *
     * @param topUpCode the Top Up Code
     */
    public void setTopUpCode(final ByteArray topUpCode) {
        this.topUpCode = topUpCode;
    }

    /**
     * Gets Top Up Amount.
     *
     * @return the Top Up Amount
     */
    public Integer getTopUpAmount() {
        return topUpAmount;
    }

    /**
     * Sets Top Up Amount.
     *
     * @param topUpAmount the Top Up Amount
     */
    public void setTopUpAmount(final Integer topUpAmount) {
        this.topUpAmount = topUpAmount;
    }

    /**
     * Gets Top Up Time.
     *
     * @return the Top Up Time
     */
    public Calendar getTopUpTime() {
        return topUpTime;
    }

    /**
     * Sets Top Up Time.
     *
     * @param topUpTime the Top Up Time
     */
    public void setTopUpTime(final Calendar topUpTime) {
        this.topUpTime = topUpTime;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(topUpCode, ZclDataType.OCTET_STRING);
        serializer.serialize(topUpAmount, ZclDataType.SIGNED_32_BIT_INTEGER);
        serializer.serialize(topUpTime, ZclDataType.UTCTIME);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        topUpCode = (ByteArray) deserializer.deserialize(ZclDataType.OCTET_STRING);
        topUpAmount = (Integer) deserializer.deserialize(ZclDataType.SIGNED_32_BIT_INTEGER);
        topUpTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(104);
        builder.append("TopUpPayload [");
        builder.append(super.toString());
        builder.append(", topUpCode=");
        builder.append(topUpCode);
        builder.append(", topUpAmount=");
        builder.append(topUpAmount);
        builder.append(", topUpTime=");
        builder.append(topUpTime);
        builder.append(']');
        return builder.toString();
    }
}
