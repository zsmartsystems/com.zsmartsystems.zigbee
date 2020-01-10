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
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Publish Consolidated Bill Command value object class.
 * <p>
 * Cluster: <b>Price</b>. Command ID 0x0A is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Price cluster.
 * <p>
 * The PublishConsolidatedBill command is used to make consolidated billing information
 * from previous billing periods available to other end devices. This command is issued in
 * response to a GetConsolidatedBill command or if new billing information is available.
 * Nested and overlapping PublishConsolidatedBill commands are not allowed. In the case of
 * overlapping consolidated bills, the bill with the newer IssuerEventID takes priority over
 * all nested and overlapping bills. All existing bills that overlap, even partially, should
 * be removed. <br> Note however that there may be separate consolidated bills for consumption
 * delivered and received.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-12T12:33:13Z")
public class PublishConsolidatedBillCommand extends ZclPriceCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0700;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x0A;

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
     * Billing Period Start Time command message field.
     * <p>
     * A UTCTime field containing the start time of the related billing period. A start
     * date/time of 0x00000000 shall indicate that the command should be executed
     * immediately. A start date/time of 0xFFFFFFFF shall cause an existing
     * PublishConsolidatedBill command with the same Provider ID and Issuer Event ID to be
     * cancelled (note that, in markets where permanently active price information is
     * required for billing purposes, it is recommended that a replacement/superseding
     * PublishConsolidatedBill command is used in place of this cancellation mechanism).
     */
    private Calendar billingPeriodStartTime;

    /**
     * Billing Period Duration command message field.
     * <p>
     * An unsigned 24-bit field denoting the duration of the related billing period. The
     * duration units are defined by the Billing Period Duration Type field.
     */
    private Integer billingPeriodDuration;

    /**
     * Billing Period Duration Type command message field.
     * <p>
     * An 8-bit bitmap where the least significant nibble is an enumerated sub-field
     * indicating the time base used for the duration, and the most significant nibble is an
     * enumerated sub-field providing duration control.
     */
    private Integer billingPeriodDurationType;

    /**
     * Tariff Type command message field.
     * <p>
     * An 8-bit bitmap identifying the type of tariff published in this command. The least
     * significant nibble represents an enumeration of the tariff type as detailed in
     */
    private Integer tariffType;

    /**
     * Consolidated Bill command message field.
     * <p>
     * An unsigned 32-bit field containing the consolidated bill value for the stated billing
     * period. The Consolidated Bill field should be provided in the same currency as used in
     * the Price cluster.
     */
    private Integer consolidatedBill;

    /**
     * Currency command message field.
     * <p>
     * An unsigned 16-bit field containing identifying information concerning the local
     * unit of currency used in the Consolidated Bill field. The value of the currency field
     * should match the values defined by ISO 4217.
     */
    private Integer currency;

    /**
     * Bill Trailing Digit command message field.
     * <p>
     * An 8-bit field used to determine where the decimal point is located in the Consolidated
     * Bill field. The most significant nibble contains the Trailing Digit sub field which
     * indicates the number of digits to the right of the decimal point.
     */
    private Integer billTrailingDigit;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.
     */
    @Deprecated
    public PublishConsolidatedBillCommand() {
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
     * @param billingPeriodStartTime {@link Calendar} Billing Period Start Time
     * @param billingPeriodDuration {@link Integer} Billing Period Duration
     * @param billingPeriodDurationType {@link Integer} Billing Period Duration Type
     * @param tariffType {@link Integer} Tariff Type
     * @param consolidatedBill {@link Integer} Consolidated Bill
     * @param currency {@link Integer} Currency
     * @param billTrailingDigit {@link Integer} Bill Trailing Digit
     */
    public PublishConsolidatedBillCommand(
            Integer providerId,
            Integer issuerEventId,
            Calendar billingPeriodStartTime,
            Integer billingPeriodDuration,
            Integer billingPeriodDurationType,
            Integer tariffType,
            Integer consolidatedBill,
            Integer currency,
            Integer billTrailingDigit) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.providerId = providerId;
        this.issuerEventId = issuerEventId;
        this.billingPeriodStartTime = billingPeriodStartTime;
        this.billingPeriodDuration = billingPeriodDuration;
        this.billingPeriodDurationType = billingPeriodDurationType;
        this.tariffType = tariffType;
        this.consolidatedBill = consolidatedBill;
        this.currency = currency;
        this.billTrailingDigit = billTrailingDigit;
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
     * Gets Billing Period Start Time.
     * <p>
     * A UTCTime field containing the start time of the related billing period. A start
     * date/time of 0x00000000 shall indicate that the command should be executed
     * immediately. A start date/time of 0xFFFFFFFF shall cause an existing
     * PublishConsolidatedBill command with the same Provider ID and Issuer Event ID to be
     * cancelled (note that, in markets where permanently active price information is
     * required for billing purposes, it is recommended that a replacement/superseding
     * PublishConsolidatedBill command is used in place of this cancellation mechanism).
     *
     * @return the Billing Period Start Time
     */
    public Calendar getBillingPeriodStartTime() {
        return billingPeriodStartTime;
    }

    /**
     * Sets Billing Period Start Time.
     * <p>
     * A UTCTime field containing the start time of the related billing period. A start
     * date/time of 0x00000000 shall indicate that the command should be executed
     * immediately. A start date/time of 0xFFFFFFFF shall cause an existing
     * PublishConsolidatedBill command with the same Provider ID and Issuer Event ID to be
     * cancelled (note that, in markets where permanently active price information is
     * required for billing purposes, it is recommended that a replacement/superseding
     * PublishConsolidatedBill command is used in place of this cancellation mechanism).
     *
     * @param billingPeriodStartTime the Billing Period Start Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setBillingPeriodStartTime(final Calendar billingPeriodStartTime) {
        this.billingPeriodStartTime = billingPeriodStartTime;
    }

    /**
     * Gets Billing Period Duration.
     * <p>
     * An unsigned 24-bit field denoting the duration of the related billing period. The
     * duration units are defined by the Billing Period Duration Type field.
     *
     * @return the Billing Period Duration
     */
    public Integer getBillingPeriodDuration() {
        return billingPeriodDuration;
    }

    /**
     * Sets Billing Period Duration.
     * <p>
     * An unsigned 24-bit field denoting the duration of the related billing period. The
     * duration units are defined by the Billing Period Duration Type field.
     *
     * @param billingPeriodDuration the Billing Period Duration
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setBillingPeriodDuration(final Integer billingPeriodDuration) {
        this.billingPeriodDuration = billingPeriodDuration;
    }

    /**
     * Gets Billing Period Duration Type.
     * <p>
     * An 8-bit bitmap where the least significant nibble is an enumerated sub-field
     * indicating the time base used for the duration, and the most significant nibble is an
     * enumerated sub-field providing duration control.
     *
     * @return the Billing Period Duration Type
     */
    public Integer getBillingPeriodDurationType() {
        return billingPeriodDurationType;
    }

    /**
     * Sets Billing Period Duration Type.
     * <p>
     * An 8-bit bitmap where the least significant nibble is an enumerated sub-field
     * indicating the time base used for the duration, and the most significant nibble is an
     * enumerated sub-field providing duration control.
     *
     * @param billingPeriodDurationType the Billing Period Duration Type
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setBillingPeriodDurationType(final Integer billingPeriodDurationType) {
        this.billingPeriodDurationType = billingPeriodDurationType;
    }

    /**
     * Gets Tariff Type.
     * <p>
     * An 8-bit bitmap identifying the type of tariff published in this command. The least
     * significant nibble represents an enumeration of the tariff type as detailed in
     *
     * @return the Tariff Type
     */
    public Integer getTariffType() {
        return tariffType;
    }

    /**
     * Sets Tariff Type.
     * <p>
     * An 8-bit bitmap identifying the type of tariff published in this command. The least
     * significant nibble represents an enumeration of the tariff type as detailed in
     *
     * @param tariffType the Tariff Type
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTariffType(final Integer tariffType) {
        this.tariffType = tariffType;
    }

    /**
     * Gets Consolidated Bill.
     * <p>
     * An unsigned 32-bit field containing the consolidated bill value for the stated billing
     * period. The Consolidated Bill field should be provided in the same currency as used in
     * the Price cluster.
     *
     * @return the Consolidated Bill
     */
    public Integer getConsolidatedBill() {
        return consolidatedBill;
    }

    /**
     * Sets Consolidated Bill.
     * <p>
     * An unsigned 32-bit field containing the consolidated bill value for the stated billing
     * period. The Consolidated Bill field should be provided in the same currency as used in
     * the Price cluster.
     *
     * @param consolidatedBill the Consolidated Bill
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setConsolidatedBill(final Integer consolidatedBill) {
        this.consolidatedBill = consolidatedBill;
    }

    /**
     * Gets Currency.
     * <p>
     * An unsigned 16-bit field containing identifying information concerning the local
     * unit of currency used in the Consolidated Bill field. The value of the currency field
     * should match the values defined by ISO 4217.
     *
     * @return the Currency
     */
    public Integer getCurrency() {
        return currency;
    }

    /**
     * Sets Currency.
     * <p>
     * An unsigned 16-bit field containing identifying information concerning the local
     * unit of currency used in the Consolidated Bill field. The value of the currency field
     * should match the values defined by ISO 4217.
     *
     * @param currency the Currency
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setCurrency(final Integer currency) {
        this.currency = currency;
    }

    /**
     * Gets Bill Trailing Digit.
     * <p>
     * An 8-bit field used to determine where the decimal point is located in the Consolidated
     * Bill field. The most significant nibble contains the Trailing Digit sub field which
     * indicates the number of digits to the right of the decimal point.
     *
     * @return the Bill Trailing Digit
     */
    public Integer getBillTrailingDigit() {
        return billTrailingDigit;
    }

    /**
     * Sets Bill Trailing Digit.
     * <p>
     * An 8-bit field used to determine where the decimal point is located in the Consolidated
     * Bill field. The most significant nibble contains the Trailing Digit sub field which
     * indicates the number of digits to the right of the decimal point.
     *
     * @param billTrailingDigit the Bill Trailing Digit
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setBillTrailingDigit(final Integer billTrailingDigit) {
        this.billTrailingDigit = billTrailingDigit;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(providerId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(issuerEventId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(billingPeriodStartTime, ZclDataType.UTCTIME);
        serializer.serialize(billingPeriodDuration, ZclDataType.UNSIGNED_24_BIT_INTEGER);
        serializer.serialize(billingPeriodDurationType, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(tariffType, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(consolidatedBill, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(currency, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(billTrailingDigit, ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        providerId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        issuerEventId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        billingPeriodStartTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        billingPeriodDuration = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_24_BIT_INTEGER);
        billingPeriodDurationType = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        tariffType = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        consolidatedBill = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        currency = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        billTrailingDigit = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(355);
        builder.append("PublishConsolidatedBillCommand [");
        builder.append(super.toString());
        builder.append(", providerId=");
        builder.append(providerId);
        builder.append(", issuerEventId=");
        builder.append(issuerEventId);
        builder.append(", billingPeriodStartTime=");
        builder.append(billingPeriodStartTime);
        builder.append(", billingPeriodDuration=");
        builder.append(billingPeriodDuration);
        builder.append(", billingPeriodDurationType=");
        builder.append(billingPeriodDurationType);
        builder.append(", tariffType=");
        builder.append(tariffType);
        builder.append(", consolidatedBill=");
        builder.append(consolidatedBill);
        builder.append(", currency=");
        builder.append(currency);
        builder.append(", billTrailingDigit=");
        builder.append(billTrailingDigit);
        builder.append(']');
        return builder.toString();
    }

}
