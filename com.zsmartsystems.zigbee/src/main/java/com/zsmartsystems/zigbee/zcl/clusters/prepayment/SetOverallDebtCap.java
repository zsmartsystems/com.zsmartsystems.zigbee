/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
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
 * Set Overall Debt Cap value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command ID 0x0C is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is sent from a client to the Prepayment server to set the overall debt cap
 * allowed in the meter.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class SetOverallDebtCap extends ZclPrepaymentCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0705;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x0C;

    /**
     * Provider ID command message field.
     */
    private Integer providerId;

    /**
     * Issuer Event ID command message field.
     */
    private Integer issuerEventId;

    /**
     * Implementation Date Time command message field.
     */
    private Calendar implementationDateTime;

    /**
     * Overall Debt Cap command message field.
     */
    private Integer overallDebtCap;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public SetOverallDebtCap() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param providerId {@link Integer} Provider ID
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param implementationDateTime {@link Calendar} Implementation Date Time
     * @param overallDebtCap {@link Integer} Overall Debt Cap
     */
    public SetOverallDebtCap(
            Integer providerId,
            Integer issuerEventId,
            Calendar implementationDateTime,
            Integer overallDebtCap) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.providerId = providerId;
        this.issuerEventId = issuerEventId;
        this.implementationDateTime = implementationDateTime;
        this.overallDebtCap = overallDebtCap;
    }

    /**
     * Gets Provider ID.
     *
     * @return the Provider ID
     */
    public Integer getProviderId() {
        return providerId;
    }

    /**
     * Sets Provider ID.
     *
     * @param providerId the Provider ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setProviderId(final Integer providerId) {
        this.providerId = providerId;
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
     * Gets Implementation Date Time.
     *
     * @return the Implementation Date Time
     */
    public Calendar getImplementationDateTime() {
        return implementationDateTime;
    }

    /**
     * Sets Implementation Date Time.
     *
     * @param implementationDateTime the Implementation Date Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setImplementationDateTime(final Calendar implementationDateTime) {
        this.implementationDateTime = implementationDateTime;
    }

    /**
     * Gets Overall Debt Cap.
     *
     * @return the Overall Debt Cap
     */
    public Integer getOverallDebtCap() {
        return overallDebtCap;
    }

    /**
     * Sets Overall Debt Cap.
     *
     * @param overallDebtCap the Overall Debt Cap
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setOverallDebtCap(final Integer overallDebtCap) {
        this.overallDebtCap = overallDebtCap;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(providerId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(issuerEventId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(implementationDateTime, ZclDataType.UTCTIME);
        serializer.serialize(overallDebtCap, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        providerId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        issuerEventId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        implementationDateTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        overallDebtCap = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(159);
        builder.append("SetOverallDebtCap [");
        builder.append(super.toString());
        builder.append(", providerId=");
        builder.append(providerId);
        builder.append(", issuerEventId=");
        builder.append(issuerEventId);
        builder.append(", implementationDateTime=");
        builder.append(implementationDateTime);
        builder.append(", overallDebtCap=");
        builder.append(overallDebtCap);
        builder.append(']');
        return builder.toString();
    }

}
