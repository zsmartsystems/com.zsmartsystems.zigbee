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
 * Emergency Credit Setup value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is a method to set up the parameters for the emergency credit.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class EmergencyCreditSetup extends ZclCommand {
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
     */
    public EmergencyCreditSetup() {
        genericCommand = false;
        clusterId = 0x0705;
        commandId = 3;
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
        serializer.serialize(issuerEventId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(startTime, ZclDataType.UTCTIME);
        serializer.serialize(emergencyCreditLimit, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(emergencyCreditThreshold, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        issuerEventId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        startTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        emergencyCreditLimit = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        emergencyCreditThreshold = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
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
