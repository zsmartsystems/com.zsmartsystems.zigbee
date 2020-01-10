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
 * Publish Tariff Information Command value object class.
 * <p>
 * Cluster: <b>Price</b>. Command ID 0x04 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Price cluster.
 * <p>
 * The PublishTariffInformation command is sent in response to a GetTariffInformation
 * command or if new tariff information is available (including Price Matrix and Block
 * Thresholds). Clients should be capable of storing at least two instances of the Tariff
 * Information, the currently active and the next one. Note that there may be separate tariff
 * information for consumption delivered and received. <br> Note that the payload for this
 * command could be up to 61 bytes in length, therefore fragmentation may be required. <br> If
 * the CLIENT is unable to store this PublishTariffInformation command, the device should
 * respond using a ZCL Default Response with a status of INSUFFICIENT_SPACE.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-12T12:33:13Z")
public class PublishTariffInformationCommand extends ZclPriceCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0700;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x04;

    /**
     * Provider ID command message field.
     * <p>
     * A unique identifier for the commodity supplier. The ProviderID in this command will
     * always be the one stored as the attribute except for the case where a change of supplier is
     * pending and the new supplier wishes to publish its tariff information in advance.
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
     * Issuer Tariff ID command message field.
     * <p>
     * Unique identifier generated by the commodity supplier.
     */
    private Integer issuerTariffId;

    /**
     * Start Time command message field.
     * <p>
     * A UTCTime field to denote the time at which the price signal becomes valid. A start
     * date/time of 0x00000000 shall indicate that the command should be executed
     * immediately. An 8-bit bitmap identifying the type of tariff published in this command.
     * The least significant nibble represents an enumeration of the tariff type as detailed
     * in Table D-108 (Generation Meters shall use the ‘Received’ Tariff), the most
     * significant nibble represents an enumeration specifying the charging scheme
     */
    private Calendar startTime;

    /**
     * Tariff Type command message field.
     */
    private Integer tariffType;

    /**
     * Tariff Label command message field.
     * <p>
     * The format and use of this field is the same as for the TariffLabel attribute or
     * ReceivedTariffLabel attribute (depending on TariffType). The format and use of this
     * field is the same as for the NumberofPriceTiersInUse attribute or
     * ReceivedNumberofPriceTiersInUse attribute (depending on TariffType/Charging
     * Scheme). The format and use of this field is the same as for the
     * NumberofBlockThresholdsInUse attribute or
     * ReceivedNumberofBlockThresholdsInUse attribute (depending on
     * TariffType/Charging Scheme).
     */
    private ByteArray tariffLabel;

    /**
     * Number Of Price Tiers command message field.
     */
    private Integer numberOfPriceTiers;

    /**
     * Number Of Block Thresholds command message field.
     */
    private Integer numberOfBlockThresholds;

    /**
     * Unit Of Measure command message field.
     * <p>
     * The format and use of this field is the same as for the Unit of Measure attribute.
     */
    private Integer unitOfMeasure;

    /**
     * Currency command message field.
     * <p>
     * The format and use of this field is the same as for the Currency attribute.
     */
    private Integer currency;

    /**
     * Price Trailing Digit command message field.
     * <p>
     * The format and use of this field is the same as for the PriceTrailingDigit attribute.
     */
    private Integer priceTrailingDigit;

    /**
     * Standing Charge command message field.
     * <p>
     * The format and use of this field is the same as for the StandingCharge attribute. A value
     * of 0xFFFFFFFF indicates the field is not used. When publishing Received tariffs
     * (according to TariffType) this field should be set to 0xFFFFFFFF.
     */
    private Integer standingCharge;

    /**
     * Tier Block Mode command message field.
     * <p>
     * The format and use of this field is the same as for the TierBlockMode attribute or
     * ReceivedTierBlockMode attribute (depending on TariffType ). In case of TOU or Block
     * Charging only, this field is not used and shall be set to 0xFF. For combined Block/TOU
     * charging, this field is mandatory and must be set to a valid value.
     */
    private Integer tierBlockMode;

    /**
     * Block Threshold Multiplier command message field.
     * <p>
     * BlockThresholdMultiplier provides a value to be multiplied against Threshold
     * parameter(s). If present, this attribute must be applied to all Block Threshold values
     * to derive values that can be compared against the
     * CurrentBlockPeriodConsumptionDelivered attribute within the Metering cluster.
     * This parameter must be used in conjunction with the BlockThresholdDivisor
     * parameter(s). In case no multiplier is defined, this field shall be set to 1.
     */
    private Integer blockThresholdMultiplier;

    /**
     * Block Threshold Divisor command message field.
     * <p>
     * BlockThresholdDivisor provides a value to divide the result of applying the
     * ThresholdMultiplier attribute to Block Threshold values to derive values that can be
     * compared against the CurrentBlockPeriodConsumptionDelivered attribute within the
     * Metering cluster. This attribute must be used in conjunction with the
     * BlockThresholdMultiplier parameter(s). In case no divisor is defined, this field
     * shall be set to 1.
     */
    private Integer blockThresholdDivisor;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.
     */
    @Deprecated
    public PublishTariffInformationCommand() {
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
     * @param issuerTariffId {@link Integer} Issuer Tariff ID
     * @param startTime {@link Calendar} Start Time
     * @param tariffType {@link Integer} Tariff Type
     * @param tariffLabel {@link ByteArray} Tariff Label
     * @param numberOfPriceTiers {@link Integer} Number Of Price Tiers
     * @param numberOfBlockThresholds {@link Integer} Number Of Block Thresholds
     * @param unitOfMeasure {@link Integer} Unit Of Measure
     * @param currency {@link Integer} Currency
     * @param priceTrailingDigit {@link Integer} Price Trailing Digit
     * @param standingCharge {@link Integer} Standing Charge
     * @param tierBlockMode {@link Integer} Tier Block Mode
     * @param blockThresholdMultiplier {@link Integer} Block Threshold Multiplier
     * @param blockThresholdDivisor {@link Integer} Block Threshold Divisor
     */
    public PublishTariffInformationCommand(
            Integer providerId,
            Integer issuerEventId,
            Integer issuerTariffId,
            Calendar startTime,
            Integer tariffType,
            ByteArray tariffLabel,
            Integer numberOfPriceTiers,
            Integer numberOfBlockThresholds,
            Integer unitOfMeasure,
            Integer currency,
            Integer priceTrailingDigit,
            Integer standingCharge,
            Integer tierBlockMode,
            Integer blockThresholdMultiplier,
            Integer blockThresholdDivisor) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.providerId = providerId;
        this.issuerEventId = issuerEventId;
        this.issuerTariffId = issuerTariffId;
        this.startTime = startTime;
        this.tariffType = tariffType;
        this.tariffLabel = tariffLabel;
        this.numberOfPriceTiers = numberOfPriceTiers;
        this.numberOfBlockThresholds = numberOfBlockThresholds;
        this.unitOfMeasure = unitOfMeasure;
        this.currency = currency;
        this.priceTrailingDigit = priceTrailingDigit;
        this.standingCharge = standingCharge;
        this.tierBlockMode = tierBlockMode;
        this.blockThresholdMultiplier = blockThresholdMultiplier;
        this.blockThresholdDivisor = blockThresholdDivisor;
    }

    /**
     * Gets Provider ID.
     * <p>
     * A unique identifier for the commodity supplier. The ProviderID in this command will
     * always be the one stored as the attribute except for the case where a change of supplier is
     * pending and the new supplier wishes to publish its tariff information in advance.
     *
     * @return the Provider ID
     */
    public Integer getProviderId() {
        return providerId;
    }

    /**
     * Sets Provider ID.
     * <p>
     * A unique identifier for the commodity supplier. The ProviderID in this command will
     * always be the one stored as the attribute except for the case where a change of supplier is
     * pending and the new supplier wishes to publish its tariff information in advance.
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
     * Gets Issuer Tariff ID.
     * <p>
     * Unique identifier generated by the commodity supplier.
     *
     * @return the Issuer Tariff ID
     */
    public Integer getIssuerTariffId() {
        return issuerTariffId;
    }

    /**
     * Sets Issuer Tariff ID.
     * <p>
     * Unique identifier generated by the commodity supplier.
     *
     * @param issuerTariffId the Issuer Tariff ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setIssuerTariffId(final Integer issuerTariffId) {
        this.issuerTariffId = issuerTariffId;
    }

    /**
     * Gets Start Time.
     * <p>
     * A UTCTime field to denote the time at which the price signal becomes valid. A start
     * date/time of 0x00000000 shall indicate that the command should be executed
     * immediately. An 8-bit bitmap identifying the type of tariff published in this command.
     * The least significant nibble represents an enumeration of the tariff type as detailed
     * in Table D-108 (Generation Meters shall use the ‘Received’ Tariff), the most
     * significant nibble represents an enumeration specifying the charging scheme
     *
     * @return the Start Time
     */
    public Calendar getStartTime() {
        return startTime;
    }

    /**
     * Sets Start Time.
     * <p>
     * A UTCTime field to denote the time at which the price signal becomes valid. A start
     * date/time of 0x00000000 shall indicate that the command should be executed
     * immediately. An 8-bit bitmap identifying the type of tariff published in this command.
     * The least significant nibble represents an enumeration of the tariff type as detailed
     * in Table D-108 (Generation Meters shall use the ‘Received’ Tariff), the most
     * significant nibble represents an enumeration specifying the charging scheme
     *
     * @param startTime the Start Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setStartTime(final Calendar startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets Tariff Type.
     *
     * @return the Tariff Type
     */
    public Integer getTariffType() {
        return tariffType;
    }

    /**
     * Sets Tariff Type.
     *
     * @param tariffType the Tariff Type
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTariffType(final Integer tariffType) {
        this.tariffType = tariffType;
    }

    /**
     * Gets Tariff Label.
     * <p>
     * The format and use of this field is the same as for the TariffLabel attribute or
     * ReceivedTariffLabel attribute (depending on TariffType). The format and use of this
     * field is the same as for the NumberofPriceTiersInUse attribute or
     * ReceivedNumberofPriceTiersInUse attribute (depending on TariffType/Charging
     * Scheme). The format and use of this field is the same as for the
     * NumberofBlockThresholdsInUse attribute or
     * ReceivedNumberofBlockThresholdsInUse attribute (depending on
     * TariffType/Charging Scheme).
     *
     * @return the Tariff Label
     */
    public ByteArray getTariffLabel() {
        return tariffLabel;
    }

    /**
     * Sets Tariff Label.
     * <p>
     * The format and use of this field is the same as for the TariffLabel attribute or
     * ReceivedTariffLabel attribute (depending on TariffType). The format and use of this
     * field is the same as for the NumberofPriceTiersInUse attribute or
     * ReceivedNumberofPriceTiersInUse attribute (depending on TariffType/Charging
     * Scheme). The format and use of this field is the same as for the
     * NumberofBlockThresholdsInUse attribute or
     * ReceivedNumberofBlockThresholdsInUse attribute (depending on
     * TariffType/Charging Scheme).
     *
     * @param tariffLabel the Tariff Label
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTariffLabel(final ByteArray tariffLabel) {
        this.tariffLabel = tariffLabel;
    }

    /**
     * Gets Number Of Price Tiers.
     *
     * @return the Number Of Price Tiers
     */
    public Integer getNumberOfPriceTiers() {
        return numberOfPriceTiers;
    }

    /**
     * Sets Number Of Price Tiers.
     *
     * @param numberOfPriceTiers the Number Of Price Tiers
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setNumberOfPriceTiers(final Integer numberOfPriceTiers) {
        this.numberOfPriceTiers = numberOfPriceTiers;
    }

    /**
     * Gets Number Of Block Thresholds.
     *
     * @return the Number Of Block Thresholds
     */
    public Integer getNumberOfBlockThresholds() {
        return numberOfBlockThresholds;
    }

    /**
     * Sets Number Of Block Thresholds.
     *
     * @param numberOfBlockThresholds the Number Of Block Thresholds
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setNumberOfBlockThresholds(final Integer numberOfBlockThresholds) {
        this.numberOfBlockThresholds = numberOfBlockThresholds;
    }

    /**
     * Gets Unit Of Measure.
     * <p>
     * The format and use of this field is the same as for the Unit of Measure attribute.
     *
     * @return the Unit Of Measure
     */
    public Integer getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Sets Unit Of Measure.
     * <p>
     * The format and use of this field is the same as for the Unit of Measure attribute.
     *
     * @param unitOfMeasure the Unit Of Measure
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setUnitOfMeasure(final Integer unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * Gets Currency.
     * <p>
     * The format and use of this field is the same as for the Currency attribute.
     *
     * @return the Currency
     */
    public Integer getCurrency() {
        return currency;
    }

    /**
     * Sets Currency.
     * <p>
     * The format and use of this field is the same as for the Currency attribute.
     *
     * @param currency the Currency
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setCurrency(final Integer currency) {
        this.currency = currency;
    }

    /**
     * Gets Price Trailing Digit.
     * <p>
     * The format and use of this field is the same as for the PriceTrailingDigit attribute.
     *
     * @return the Price Trailing Digit
     */
    public Integer getPriceTrailingDigit() {
        return priceTrailingDigit;
    }

    /**
     * Sets Price Trailing Digit.
     * <p>
     * The format and use of this field is the same as for the PriceTrailingDigit attribute.
     *
     * @param priceTrailingDigit the Price Trailing Digit
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setPriceTrailingDigit(final Integer priceTrailingDigit) {
        this.priceTrailingDigit = priceTrailingDigit;
    }

    /**
     * Gets Standing Charge.
     * <p>
     * The format and use of this field is the same as for the StandingCharge attribute. A value
     * of 0xFFFFFFFF indicates the field is not used. When publishing Received tariffs
     * (according to TariffType) this field should be set to 0xFFFFFFFF.
     *
     * @return the Standing Charge
     */
    public Integer getStandingCharge() {
        return standingCharge;
    }

    /**
     * Sets Standing Charge.
     * <p>
     * The format and use of this field is the same as for the StandingCharge attribute. A value
     * of 0xFFFFFFFF indicates the field is not used. When publishing Received tariffs
     * (according to TariffType) this field should be set to 0xFFFFFFFF.
     *
     * @param standingCharge the Standing Charge
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setStandingCharge(final Integer standingCharge) {
        this.standingCharge = standingCharge;
    }

    /**
     * Gets Tier Block Mode.
     * <p>
     * The format and use of this field is the same as for the TierBlockMode attribute or
     * ReceivedTierBlockMode attribute (depending on TariffType ). In case of TOU or Block
     * Charging only, this field is not used and shall be set to 0xFF. For combined Block/TOU
     * charging, this field is mandatory and must be set to a valid value.
     *
     * @return the Tier Block Mode
     */
    public Integer getTierBlockMode() {
        return tierBlockMode;
    }

    /**
     * Sets Tier Block Mode.
     * <p>
     * The format and use of this field is the same as for the TierBlockMode attribute or
     * ReceivedTierBlockMode attribute (depending on TariffType ). In case of TOU or Block
     * Charging only, this field is not used and shall be set to 0xFF. For combined Block/TOU
     * charging, this field is mandatory and must be set to a valid value.
     *
     * @param tierBlockMode the Tier Block Mode
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTierBlockMode(final Integer tierBlockMode) {
        this.tierBlockMode = tierBlockMode;
    }

    /**
     * Gets Block Threshold Multiplier.
     * <p>
     * BlockThresholdMultiplier provides a value to be multiplied against Threshold
     * parameter(s). If present, this attribute must be applied to all Block Threshold values
     * to derive values that can be compared against the
     * CurrentBlockPeriodConsumptionDelivered attribute within the Metering cluster.
     * This parameter must be used in conjunction with the BlockThresholdDivisor
     * parameter(s). In case no multiplier is defined, this field shall be set to 1.
     *
     * @return the Block Threshold Multiplier
     */
    public Integer getBlockThresholdMultiplier() {
        return blockThresholdMultiplier;
    }

    /**
     * Sets Block Threshold Multiplier.
     * <p>
     * BlockThresholdMultiplier provides a value to be multiplied against Threshold
     * parameter(s). If present, this attribute must be applied to all Block Threshold values
     * to derive values that can be compared against the
     * CurrentBlockPeriodConsumptionDelivered attribute within the Metering cluster.
     * This parameter must be used in conjunction with the BlockThresholdDivisor
     * parameter(s). In case no multiplier is defined, this field shall be set to 1.
     *
     * @param blockThresholdMultiplier the Block Threshold Multiplier
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setBlockThresholdMultiplier(final Integer blockThresholdMultiplier) {
        this.blockThresholdMultiplier = blockThresholdMultiplier;
    }

    /**
     * Gets Block Threshold Divisor.
     * <p>
     * BlockThresholdDivisor provides a value to divide the result of applying the
     * ThresholdMultiplier attribute to Block Threshold values to derive values that can be
     * compared against the CurrentBlockPeriodConsumptionDelivered attribute within the
     * Metering cluster. This attribute must be used in conjunction with the
     * BlockThresholdMultiplier parameter(s). In case no divisor is defined, this field
     * shall be set to 1.
     *
     * @return the Block Threshold Divisor
     */
    public Integer getBlockThresholdDivisor() {
        return blockThresholdDivisor;
    }

    /**
     * Sets Block Threshold Divisor.
     * <p>
     * BlockThresholdDivisor provides a value to divide the result of applying the
     * ThresholdMultiplier attribute to Block Threshold values to derive values that can be
     * compared against the CurrentBlockPeriodConsumptionDelivered attribute within the
     * Metering cluster. This attribute must be used in conjunction with the
     * BlockThresholdMultiplier parameter(s). In case no divisor is defined, this field
     * shall be set to 1.
     *
     * @param blockThresholdDivisor the Block Threshold Divisor
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setBlockThresholdDivisor(final Integer blockThresholdDivisor) {
        this.blockThresholdDivisor = blockThresholdDivisor;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(providerId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(issuerEventId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(issuerTariffId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(startTime, ZclDataType.UTCTIME);
        serializer.serialize(tariffType, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(tariffLabel, ZclDataType.OCTET_STRING);
        serializer.serialize(numberOfPriceTiers, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(numberOfBlockThresholds, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(unitOfMeasure, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(currency, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(priceTrailingDigit, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(standingCharge, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(tierBlockMode, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(blockThresholdMultiplier, ZclDataType.UNSIGNED_24_BIT_INTEGER);
        serializer.serialize(blockThresholdDivisor, ZclDataType.UNSIGNED_24_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        providerId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        issuerEventId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        issuerTariffId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        startTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        tariffType = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        tariffLabel = (ByteArray) deserializer.deserialize(ZclDataType.OCTET_STRING);
        numberOfPriceTiers = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        numberOfBlockThresholds = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        unitOfMeasure = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        currency = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        priceTrailingDigit = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        standingCharge = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        tierBlockMode = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        blockThresholdMultiplier = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_24_BIT_INTEGER);
        blockThresholdDivisor = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_24_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(553);
        builder.append("PublishTariffInformationCommand [");
        builder.append(super.toString());
        builder.append(", providerId=");
        builder.append(providerId);
        builder.append(", issuerEventId=");
        builder.append(issuerEventId);
        builder.append(", issuerTariffId=");
        builder.append(issuerTariffId);
        builder.append(", startTime=");
        builder.append(startTime);
        builder.append(", tariffType=");
        builder.append(tariffType);
        builder.append(", tariffLabel=");
        builder.append(tariffLabel);
        builder.append(", numberOfPriceTiers=");
        builder.append(numberOfPriceTiers);
        builder.append(", numberOfBlockThresholds=");
        builder.append(numberOfBlockThresholds);
        builder.append(", unitOfMeasure=");
        builder.append(unitOfMeasure);
        builder.append(", currency=");
        builder.append(currency);
        builder.append(", priceTrailingDigit=");
        builder.append(priceTrailingDigit);
        builder.append(", standingCharge=");
        builder.append(standingCharge);
        builder.append(", tierBlockMode=");
        builder.append(tierBlockMode);
        builder.append(", blockThresholdMultiplier=");
        builder.append(blockThresholdMultiplier);
        builder.append(", blockThresholdDivisor=");
        builder.append(blockThresholdDivisor);
        builder.append(']');
        return builder.toString();
    }

}
