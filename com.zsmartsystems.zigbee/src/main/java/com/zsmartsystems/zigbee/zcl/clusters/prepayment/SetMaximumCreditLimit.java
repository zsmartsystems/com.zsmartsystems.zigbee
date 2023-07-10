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
 * Set Maximum Credit Limit value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command ID 0x0B is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is sent from a client to the Prepayment server to set the maximum credit
 * level allowed in the meter.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class SetMaximumCreditLimit extends ZclPrepaymentCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0705;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x0B;

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
     * Maximum Credit Level command message field.
     */
    private Integer maximumCreditLevel;

    /**
     * Maximum Credit Per Top Up command message field.
     */
    private Integer maximumCreditPerTopUp;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public SetMaximumCreditLimit() {
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
     * @param maximumCreditLevel {@link Integer} Maximum Credit Level
     * @param maximumCreditPerTopUp {@link Integer} Maximum Credit Per Top Up
     */
    public SetMaximumCreditLimit(
            Integer providerId,
            Integer issuerEventId,
            Calendar implementationDateTime,
            Integer maximumCreditLevel,
            Integer maximumCreditPerTopUp) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.providerId = providerId;
        this.issuerEventId = issuerEventId;
        this.implementationDateTime = implementationDateTime;
        this.maximumCreditLevel = maximumCreditLevel;
        this.maximumCreditPerTopUp = maximumCreditPerTopUp;
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
     * Gets Maximum Credit Level.
     *
     * @return the Maximum Credit Level
     */
    public Integer getMaximumCreditLevel() {
        return maximumCreditLevel;
    }

    /**
     * Sets Maximum Credit Level.
     *
     * @param maximumCreditLevel the Maximum Credit Level
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setMaximumCreditLevel(final Integer maximumCreditLevel) {
        this.maximumCreditLevel = maximumCreditLevel;
    }

    /**
     * Gets Maximum Credit Per Top Up.
     *
     * @return the Maximum Credit Per Top Up
     */
    public Integer getMaximumCreditPerTopUp() {
        return maximumCreditPerTopUp;
    }

    /**
     * Sets Maximum Credit Per Top Up.
     *
     * @param maximumCreditPerTopUp the Maximum Credit Per Top Up
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setMaximumCreditPerTopUp(final Integer maximumCreditPerTopUp) {
        this.maximumCreditPerTopUp = maximumCreditPerTopUp;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(providerId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(issuerEventId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(implementationDateTime, ZclDataType.UTCTIME);
        serializer.serialize(maximumCreditLevel, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(maximumCreditPerTopUp, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        providerId = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        issuerEventId = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        implementationDateTime = deserializer.deserialize(ZclDataType.UTCTIME);
        maximumCreditLevel = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        maximumCreditPerTopUp = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(208);
        builder.append("SetMaximumCreditLimit [");
        builder.append(super.toString());
        builder.append(", providerId=");
        builder.append(providerId);
        builder.append(", issuerEventId=");
        builder.append(issuerEventId);
        builder.append(", implementationDateTime=");
        builder.append(implementationDateTime);
        builder.append(", maximumCreditLevel=");
        builder.append(maximumCreditLevel);
        builder.append(", maximumCreditPerTopUp=");
        builder.append(maximumCreditPerTopUp);
        builder.append(']');
        return builder.toString();
    }

}
