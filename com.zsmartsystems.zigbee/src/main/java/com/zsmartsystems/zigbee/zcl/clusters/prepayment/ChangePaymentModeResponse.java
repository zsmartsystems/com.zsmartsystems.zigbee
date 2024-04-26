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
 * Change Payment Mode Response value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command ID 0x02 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is send in response to the ChangePaymentMode Command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class ChangePaymentModeResponse extends ZclPrepaymentCommand {
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
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public ChangePaymentModeResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param friendlyCredit {@link Integer} Friendly Credit
     * @param friendlyCreditCalendarId {@link Integer} Friendly Credit Calendar ID
     * @param emergencyCreditLimit {@link Integer} Emergency Credit Limit
     * @param emergencyCreditThreshold {@link Integer} Emergency Credit Threshold
     */
    public ChangePaymentModeResponse(
            Integer friendlyCredit,
            Integer friendlyCreditCalendarId,
            Integer emergencyCreditLimit,
            Integer emergencyCreditThreshold) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.friendlyCredit = friendlyCredit;
        this.friendlyCreditCalendarId = friendlyCreditCalendarId;
        this.emergencyCreditLimit = emergencyCreditLimit;
        this.emergencyCreditThreshold = emergencyCreditThreshold;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
        friendlyCredit = deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        friendlyCreditCalendarId = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        emergencyCreditLimit = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        emergencyCreditThreshold = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
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
