/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
 * Change Payment Mode value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command ID 0x06 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is sent to a Metering Device to instruct it to change its mode of
 * operation. i.e. from Credit to Prepayment.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-12T12:33:13Z")
public class ChangePaymentMode extends ZclPrepaymentCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0705;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x06;

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
     * Proposed Payment Control Configuration command message field.
     */
    private Integer proposedPaymentControlConfiguration;

    /**
     * Cut Off Value command message field.
     */
    private Integer cutOffValue;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.
     */
    @Deprecated
    public ChangePaymentMode() {
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
     * @param proposedPaymentControlConfiguration {@link Integer} Proposed Payment Control Configuration
     * @param cutOffValue {@link Integer} Cut Off Value
     */
    public ChangePaymentMode(
            Integer providerId,
            Integer issuerEventId,
            Calendar implementationDateTime,
            Integer proposedPaymentControlConfiguration,
            Integer cutOffValue) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.providerId = providerId;
        this.issuerEventId = issuerEventId;
        this.implementationDateTime = implementationDateTime;
        this.proposedPaymentControlConfiguration = proposedPaymentControlConfiguration;
        this.cutOffValue = cutOffValue;
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
     * Gets Proposed Payment Control Configuration.
     *
     * @return the Proposed Payment Control Configuration
     */
    public Integer getProposedPaymentControlConfiguration() {
        return proposedPaymentControlConfiguration;
    }

    /**
     * Sets Proposed Payment Control Configuration.
     *
     * @param proposedPaymentControlConfiguration the Proposed Payment Control Configuration
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setProposedPaymentControlConfiguration(final Integer proposedPaymentControlConfiguration) {
        this.proposedPaymentControlConfiguration = proposedPaymentControlConfiguration;
    }

    /**
     * Gets Cut Off Value.
     *
     * @return the Cut Off Value
     */
    public Integer getCutOffValue() {
        return cutOffValue;
    }

    /**
     * Sets Cut Off Value.
     *
     * @param cutOffValue the Cut Off Value
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setCutOffValue(final Integer cutOffValue) {
        this.cutOffValue = cutOffValue;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(providerId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(issuerEventId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(implementationDateTime, ZclDataType.UTCTIME);
        serializer.serialize(proposedPaymentControlConfiguration, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(cutOffValue, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        providerId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        issuerEventId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        implementationDateTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        proposedPaymentControlConfiguration = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        cutOffValue = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(211);
        builder.append("ChangePaymentMode [");
        builder.append(super.toString());
        builder.append(", providerId=");
        builder.append(providerId);
        builder.append(", issuerEventId=");
        builder.append(issuerEventId);
        builder.append(", implementationDateTime=");
        builder.append(implementationDateTime);
        builder.append(", proposedPaymentControlConfiguration=");
        builder.append(proposedPaymentControlConfiguration);
        builder.append(", cutOffValue=");
        builder.append(cutOffValue);
        builder.append(']');
        return builder.toString();
    }

}
