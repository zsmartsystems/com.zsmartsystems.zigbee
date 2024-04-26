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

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Emergency Credit Setup value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command ID 0x03 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is a method to set up the parameters for the emergency credit.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class EmergencyCreditSetup extends ZclPrepaymentCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0705;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x03;

    /**
     * Issuer Event ID command message field.
     */
    private Integer issuerEventId;

    /**
     * Start Time command message field.
     */
    private Calendar startTime;

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
    public EmergencyCreditSetup() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param startTime {@link Calendar} Start Time
     * @param emergencyCreditLimit {@link Integer} Emergency Credit Limit
     * @param emergencyCreditThreshold {@link Integer} Emergency Credit Threshold
     */
    public EmergencyCreditSetup(
            Integer issuerEventId,
            Calendar startTime,
            Integer emergencyCreditLimit,
            Integer emergencyCreditThreshold) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.issuerEventId = issuerEventId;
        this.startTime = startTime;
        this.emergencyCreditLimit = emergencyCreditLimit;
        this.emergencyCreditThreshold = emergencyCreditThreshold;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setStartTime(final Calendar startTime) {
        this.startTime = startTime;
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
        serializer.serialize(issuerEventId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(startTime, ZclDataType.UTCTIME);
        serializer.serialize(emergencyCreditLimit, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(emergencyCreditThreshold, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        issuerEventId = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        startTime = deserializer.deserialize(ZclDataType.UTCTIME);
        emergencyCreditLimit = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        emergencyCreditThreshold = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(169);
        builder.append("EmergencyCreditSetup [");
        builder.append(super.toString());
        builder.append(", issuerEventId=");
        builder.append(issuerEventId);
        builder.append(", startTime=");
        builder.append(startTime);
        builder.append(", emergencyCreditLimit=");
        builder.append(emergencyCreditLimit);
        builder.append(", emergencyCreditThreshold=");
        builder.append(emergencyCreditThreshold);
        builder.append(']');
        return builder.toString();
    }

}
