/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.price;

import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Publish Credit Payment Command value object class.
 * <p>
 * Cluster: <b>Price</b>. Command ID 0x0C is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Price cluster.
 * <p>
 * The PublishCreditPayment command is used to update the credit payment information when
 * available. <br> Nested and overlapping PublishCreditPayment commands are not allowed. In
 * the case of overlapping credit payments, the payment with the newer Issuer Event ID takes
 * priority over all nested and overlapping payments. All existing payments that overlap,
 * even partially, should be removed.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-12T12:33:13Z")
public class PublishCreditPaymentCommand extends ZclPriceCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0700;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x0C;

    /**
     * Provider ID command message field.
     * <p>
     * An unsigned 32-bit field containing a unique identifier for the commodity provider.
     * This field allows differentiation in deregulated markets where multiple commodity
     * providers may be available.
     */
    private Integer providerId;

    /**
     * Issuer Event ID command message field.
     * <p>
     * Unique identifier generated by the commodity provider. When new information is
     * provided that replaces older information for the same time period, this field allows
     * devices to determine which information is newer. The value contained in this field is a
     * unique number managed by upstream servers or a UTC based time stamp (UTCTime data type)
     * identifying when the Publish command was issued. Thus, newer information will have a
     * value in the Issuer Event ID field that is larger than older information.
     */
    private Integer issuerEventId;

    /**
     * Credit Payment Due Date command message field.
     * <p>
     * A UTCTime field containing the time that the next credit payment is due.
     */
    private Calendar creditPaymentDueDate;

    /**
     * Credit Payment Overdue Amount command message field.
     * <p>
     * An unsigned 32-bit field denoting the current amount this is overdue from the consumer.
     * This field should be provided in the same currency as used in the Price cluster.
     */
    private Integer creditPaymentOverdueAmount;

    /**
     * Credit Payment Status command message field.
     * <p>
     * An 8-bit enumeration identifying the current credit payment status.
     */
    private Integer creditPaymentStatus;

    /**
     * Credit Payment command message field.
     * <p>
     * An unsigned 32-bit field denoting the last credit payment. This field should be
     * provided in the same currency as used in the Price cluster.
     */
    private Integer creditPayment;

    /**
     * Credit Payment Date command message field.
     * <p>
     * A UTCTime field containing the time at which the last credit payment was made.
     */
    private Calendar creditPaymentDate;

    /**
     * Credit Payment Ref command message field.
     * <p>
     * An string of between 0-20 octets used to denote the last credit payment reference used by
     * the energy supplier.
     */
    private ByteArray creditPaymentRef;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.
     */
    @Deprecated
    public PublishCreditPaymentCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param providerId {@link Integer} Provider ID
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param creditPaymentDueDate {@link Calendar} Credit Payment Due Date
     * @param creditPaymentOverdueAmount {@link Integer} Credit Payment Overdue Amount
     * @param creditPaymentStatus {@link Integer} Credit Payment Status
     * @param creditPayment {@link Integer} Credit Payment
     * @param creditPaymentDate {@link Calendar} Credit Payment Date
     * @param creditPaymentRef {@link ByteArray} Credit Payment Ref
     */
    public PublishCreditPaymentCommand(
            Integer providerId,
            Integer issuerEventId,
            Calendar creditPaymentDueDate,
            Integer creditPaymentOverdueAmount,
            Integer creditPaymentStatus,
            Integer creditPayment,
            Calendar creditPaymentDate,
            ByteArray creditPaymentRef) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.providerId = providerId;
        this.issuerEventId = issuerEventId;
        this.creditPaymentDueDate = creditPaymentDueDate;
        this.creditPaymentOverdueAmount = creditPaymentOverdueAmount;
        this.creditPaymentStatus = creditPaymentStatus;
        this.creditPayment = creditPayment;
        this.creditPaymentDate = creditPaymentDate;
        this.creditPaymentRef = creditPaymentRef;
    }

    /**
     * Gets Provider ID.
     * <p>
     * An unsigned 32-bit field containing a unique identifier for the commodity provider.
     * This field allows differentiation in deregulated markets where multiple commodity
     * providers may be available.
     *
     * @return the Provider ID
     */
    public Integer getProviderId() {
        return providerId;
    }

    /**
     * Sets Provider ID.
     * <p>
     * An unsigned 32-bit field containing a unique identifier for the commodity provider.
     * This field allows differentiation in deregulated markets where multiple commodity
     * providers may be available.
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
     * <p>
     * Unique identifier generated by the commodity provider. When new information is
     * provided that replaces older information for the same time period, this field allows
     * devices to determine which information is newer. The value contained in this field is a
     * unique number managed by upstream servers or a UTC based time stamp (UTCTime data type)
     * identifying when the Publish command was issued. Thus, newer information will have a
     * value in the Issuer Event ID field that is larger than older information.
     *
     * @return the Issuer Event ID
     */
    public Integer getIssuerEventId() {
        return issuerEventId;
    }

    /**
     * Sets Issuer Event ID.
     * <p>
     * Unique identifier generated by the commodity provider. When new information is
     * provided that replaces older information for the same time period, this field allows
     * devices to determine which information is newer. The value contained in this field is a
     * unique number managed by upstream servers or a UTC based time stamp (UTCTime data type)
     * identifying when the Publish command was issued. Thus, newer information will have a
     * value in the Issuer Event ID field that is larger than older information.
     *
     * @param issuerEventId the Issuer Event ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setIssuerEventId(final Integer issuerEventId) {
        this.issuerEventId = issuerEventId;
    }

    /**
     * Gets Credit Payment Due Date.
     * <p>
     * A UTCTime field containing the time that the next credit payment is due.
     *
     * @return the Credit Payment Due Date
     */
    public Calendar getCreditPaymentDueDate() {
        return creditPaymentDueDate;
    }

    /**
     * Sets Credit Payment Due Date.
     * <p>
     * A UTCTime field containing the time that the next credit payment is due.
     *
     * @param creditPaymentDueDate the Credit Payment Due Date
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setCreditPaymentDueDate(final Calendar creditPaymentDueDate) {
        this.creditPaymentDueDate = creditPaymentDueDate;
    }

    /**
     * Gets Credit Payment Overdue Amount.
     * <p>
     * An unsigned 32-bit field denoting the current amount this is overdue from the consumer.
     * This field should be provided in the same currency as used in the Price cluster.
     *
     * @return the Credit Payment Overdue Amount
     */
    public Integer getCreditPaymentOverdueAmount() {
        return creditPaymentOverdueAmount;
    }

    /**
     * Sets Credit Payment Overdue Amount.
     * <p>
     * An unsigned 32-bit field denoting the current amount this is overdue from the consumer.
     * This field should be provided in the same currency as used in the Price cluster.
     *
     * @param creditPaymentOverdueAmount the Credit Payment Overdue Amount
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setCreditPaymentOverdueAmount(final Integer creditPaymentOverdueAmount) {
        this.creditPaymentOverdueAmount = creditPaymentOverdueAmount;
    }

    /**
     * Gets Credit Payment Status.
     * <p>
     * An 8-bit enumeration identifying the current credit payment status.
     *
     * @return the Credit Payment Status
     */
    public Integer getCreditPaymentStatus() {
        return creditPaymentStatus;
    }

    /**
     * Sets Credit Payment Status.
     * <p>
     * An 8-bit enumeration identifying the current credit payment status.
     *
     * @param creditPaymentStatus the Credit Payment Status
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setCreditPaymentStatus(final Integer creditPaymentStatus) {
        this.creditPaymentStatus = creditPaymentStatus;
    }

    /**
     * Gets Credit Payment.
     * <p>
     * An unsigned 32-bit field denoting the last credit payment. This field should be
     * provided in the same currency as used in the Price cluster.
     *
     * @return the Credit Payment
     */
    public Integer getCreditPayment() {
        return creditPayment;
    }

    /**
     * Sets Credit Payment.
     * <p>
     * An unsigned 32-bit field denoting the last credit payment. This field should be
     * provided in the same currency as used in the Price cluster.
     *
     * @param creditPayment the Credit Payment
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setCreditPayment(final Integer creditPayment) {
        this.creditPayment = creditPayment;
    }

    /**
     * Gets Credit Payment Date.
     * <p>
     * A UTCTime field containing the time at which the last credit payment was made.
     *
     * @return the Credit Payment Date
     */
    public Calendar getCreditPaymentDate() {
        return creditPaymentDate;
    }

    /**
     * Sets Credit Payment Date.
     * <p>
     * A UTCTime field containing the time at which the last credit payment was made.
     *
     * @param creditPaymentDate the Credit Payment Date
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setCreditPaymentDate(final Calendar creditPaymentDate) {
        this.creditPaymentDate = creditPaymentDate;
    }

    /**
     * Gets Credit Payment Ref.
     * <p>
     * An string of between 0-20 octets used to denote the last credit payment reference used by
     * the energy supplier.
     *
     * @return the Credit Payment Ref
     */
    public ByteArray getCreditPaymentRef() {
        return creditPaymentRef;
    }

    /**
     * Sets Credit Payment Ref.
     * <p>
     * An string of between 0-20 octets used to denote the last credit payment reference used by
     * the energy supplier.
     *
     * @param creditPaymentRef the Credit Payment Ref
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setCreditPaymentRef(final ByteArray creditPaymentRef) {
        this.creditPaymentRef = creditPaymentRef;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(providerId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(issuerEventId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(creditPaymentDueDate, ZclDataType.UTCTIME);
        serializer.serialize(creditPaymentOverdueAmount, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(creditPaymentStatus, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(creditPayment, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(creditPaymentDate, ZclDataType.UTCTIME);
        serializer.serialize(creditPaymentRef, ZclDataType.OCTET_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        providerId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        issuerEventId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        creditPaymentDueDate = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        creditPaymentOverdueAmount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        creditPaymentStatus = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        creditPayment = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        creditPaymentDate = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        creditPaymentRef = (ByteArray) deserializer.deserialize(ZclDataType.OCTET_STRING);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(324);
        builder.append("PublishCreditPaymentCommand [");
        builder.append(super.toString());
        builder.append(", providerId=");
        builder.append(providerId);
        builder.append(", issuerEventId=");
        builder.append(issuerEventId);
        builder.append(", creditPaymentDueDate=");
        builder.append(creditPaymentDueDate);
        builder.append(", creditPaymentOverdueAmount=");
        builder.append(creditPaymentOverdueAmount);
        builder.append(", creditPaymentStatus=");
        builder.append(creditPaymentStatus);
        builder.append(", creditPayment=");
        builder.append(creditPayment);
        builder.append(", creditPaymentDate=");
        builder.append(creditPaymentDate);
        builder.append(", creditPaymentRef=");
        builder.append(creditPaymentRef);
        builder.append(']');
        return builder.toString();
    }

}
