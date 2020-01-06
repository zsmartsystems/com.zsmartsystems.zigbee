/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
 * Cluster: <b>Prepayment</b>. Command ID 0x02 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is send in response to the ChangePaymentMode Command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-06T18:45:28Z")
public class ChangePaymentModeResponse extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0705;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x02;

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
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
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
     * @return the ChangePaymentModeResponse command
     */
    public ChangePaymentModeResponse setFriendlyCredit(final Integer friendlyCredit) {
        this.friendlyCredit = friendlyCredit;
        return this;
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
     * @return the ChangePaymentModeResponse command
     */
    public ChangePaymentModeResponse setFriendlyCreditCalendarId(final Integer friendlyCreditCalendarId) {
        this.friendlyCreditCalendarId = friendlyCreditCalendarId;
        return this;
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
     * @return the ChangePaymentModeResponse command
     */
    public ChangePaymentModeResponse setEmergencyCreditLimit(final Integer emergencyCreditLimit) {
        this.emergencyCreditLimit = emergencyCreditLimit;
        return this;
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
     * @return the ChangePaymentModeResponse command
     */
    public ChangePaymentModeResponse setEmergencyCreditThreshold(final Integer emergencyCreditThreshold) {
        this.emergencyCreditThreshold = emergencyCreditThreshold;
        return this;
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
