/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.prepayment;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Change Payment Mode Response value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is send in response to the ChangePaymentMode Command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ChangePaymentModeResponse extends ZclCommand {
    /**
     * Friendly Credit command message field.
     */
    private Integer friendlyCredit;

    /**
     * Friendly Credit Calendar ID command message field.
     */
    private Integer friendlyCreditCalendarId;

    /**
     * Emergency Credit Limit command message field.
     */
    private Integer emergencyCreditLimit;

    /**
     * Emergency Credit Threshold command message field.
     */
    private Integer emergencyCreditThreshold;

    /**
     * Default constructor.
     */
    public ChangePaymentModeResponse() {
        genericCommand = false;
        clusterId = 0x0705;
        commandId = 2;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Friendly Credit.
     *
     * @return the Friendly Credit
     */
    public Integer getFriendlyCredit() {
        return friendlyCredit;
    }

    /**
     * Sets Friendly Credit.
     *
     * @param friendlyCredit the Friendly Credit
     */
    public void setFriendlyCredit(final Integer friendlyCredit) {
        this.friendlyCredit = friendlyCredit;
    }

    /**
     * Gets Friendly Credit Calendar ID.
     *
     * @return the Friendly Credit Calendar ID
     */
    public Integer getFriendlyCreditCalendarId() {
        return friendlyCreditCalendarId;
    }

    /**
     * Sets Friendly Credit Calendar ID.
     *
     * @param friendlyCreditCalendarId the Friendly Credit Calendar ID
     */
    public void setFriendlyCreditCalendarId(final Integer friendlyCreditCalendarId) {
        this.friendlyCreditCalendarId = friendlyCreditCalendarId;
    }

    /**
     * Gets Emergency Credit Limit.
     *
     * @return the Emergency Credit Limit
     */
    public Integer getEmergencyCreditLimit() {
        return emergencyCreditLimit;
    }

    /**
     * Sets Emergency Credit Limit.
     *
     * @param emergencyCreditLimit the Emergency Credit Limit
     */
    public void setEmergencyCreditLimit(final Integer emergencyCreditLimit) {
        this.emergencyCreditLimit = emergencyCreditLimit;
    }

    /**
     * Gets Emergency Credit Threshold.
     *
     * @return the Emergency Credit Threshold
     */
    public Integer getEmergencyCreditThreshold() {
        return emergencyCreditThreshold;
    }

    /**
     * Sets Emergency Credit Threshold.
     *
     * @param emergencyCreditThreshold the Emergency Credit Threshold
     */
    public void setEmergencyCreditThreshold(final Integer emergencyCreditThreshold) {
        this.emergencyCreditThreshold = emergencyCreditThreshold;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(friendlyCredit, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(friendlyCreditCalendarId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(emergencyCreditLimit, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(emergencyCreditThreshold, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        friendlyCredit = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        friendlyCreditCalendarId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        emergencyCreditLimit = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        emergencyCreditThreshold = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(190);
        builder.append("ChangePaymentModeResponse [");
        builder.append(super.toString());
        builder.append(", friendlyCredit=");
        builder.append(friendlyCredit);
        builder.append(", friendlyCreditCalendarId=");
        builder.append(friendlyCreditCalendarId);
        builder.append(", emergencyCreditLimit=");
        builder.append(emergencyCreditLimit);
        builder.append(", emergencyCreditThreshold=");
        builder.append(emergencyCreditThreshold);
        builder.append(']');
        return builder.toString();
    }

}
