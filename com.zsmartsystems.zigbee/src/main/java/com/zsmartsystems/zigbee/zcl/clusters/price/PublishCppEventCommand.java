/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
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
 * Publish Cpp Event Command value object class.
 * <p>
 * Cluster: <b>Price</b>. Command ID 0x0B is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Price cluster.
 * <p>
 * The PublishCPPEvent command is sent from an ESI to its Price clients to notify them of a
 * Critical Peak Pricing (CPP) event. <br> When the PublishCPPEvent command is received, the
 * IHD or Meter shall act in one of two ways: 1. It shall notify the consumer that there is a CPP
 * event that requires acknowledgment. The acknowledgement shall be either to accept the
 * CPPEvent or reject the CPPEvent (in which case it shall send the CPPEventResponse command,
 * with the CPPAuth parameter set to Accepted or Rejected). It is recommended that the CPP event
 * is ignored until a consumer either accepts or rejects the event. 2. The CPPAuth parameter is
 * set to “Forced”, in which case the CPPEvent has been accepted.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class PublishCppEventCommand extends ZclPriceCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0700;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x0B;

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
     * value in the Issuer Event ID field that is larger than older information. A start
     * date/time of 0x00000000 shall indicate that the command should be executed
     * immediately. A start date/time of 0xFFFFFFFF shall cause an existing PublishCPPEvent
     * command with the same Provider ID and Issuer Event ID to be cancelled (note that, in
     * markets where permanently active price information is required for billing purposes,
     * it is recommended that a replacement/superseding PublishCPPEvent command is used in
     * place of this cancellation mechanism). Duration in Minutes: Defines the duration of
     * the CPP event.
     */
    private Integer issuerEventId;

    /**
     * Start Time command message field.
     */
    private Calendar startTime;

    /**
     * Duration In Minutes command message field.
     */
    private Integer durationInMinutes;

    /**
     * Tariff Type command message field.
     * <p>
     * An 8-bit bitmap identifying the type of tariff published in this command. The least
     * significant nibble represents an enumeration of the tariff type (Generation Meters
     * shall use the ‘Received’ Tariff). The most significant nibble is reserved.
     */
    private Integer tariffType;

    /**
     * Cpp Price Tier command message field.
     * <p>
     * An 8-bit enumeration identifying the price tier associated with this CPP event. The
     * price(s) contained in the active price matrix for that price tier will override the
     * normal pricing scheme. Prices ‘CPP1’ and ‘CPP2’ are reserved for this purposes.
     */
    private Integer cppPriceTier;

    /**
     * Cpp Auth command message field.
     * <p>
     * An 8-bit enumeration identifying the status of the CPP event:
     */
    private Integer cppAuth;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public PublishCppEventCommand() {
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
     * @param startTime {@link Calendar} Start Time
     * @param durationInMinutes {@link Integer} Duration In Minutes
     * @param tariffType {@link Integer} Tariff Type
     * @param cppPriceTier {@link Integer} Cpp Price Tier
     * @param cppAuth {@link Integer} Cpp Auth
     */
    public PublishCppEventCommand(
            Integer providerId,
            Integer issuerEventId,
            Calendar startTime,
            Integer durationInMinutes,
            Integer tariffType,
            Integer cppPriceTier,
            Integer cppAuth) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.providerId = providerId;
        this.issuerEventId = issuerEventId;
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
        this.tariffType = tariffType;
        this.cppPriceTier = cppPriceTier;
        this.cppAuth = cppAuth;
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
     * value in the Issuer Event ID field that is larger than older information. A start
     * date/time of 0x00000000 shall indicate that the command should be executed
     * immediately. A start date/time of 0xFFFFFFFF shall cause an existing PublishCPPEvent
     * command with the same Provider ID and Issuer Event ID to be cancelled (note that, in
     * markets where permanently active price information is required for billing purposes,
     * it is recommended that a replacement/superseding PublishCPPEvent command is used in
     * place of this cancellation mechanism). Duration in Minutes: Defines the duration of
     * the CPP event.
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
     * value in the Issuer Event ID field that is larger than older information. A start
     * date/time of 0x00000000 shall indicate that the command should be executed
     * immediately. A start date/time of 0xFFFFFFFF shall cause an existing PublishCPPEvent
     * command with the same Provider ID and Issuer Event ID to be cancelled (note that, in
     * markets where permanently active price information is required for billing purposes,
     * it is recommended that a replacement/superseding PublishCPPEvent command is used in
     * place of this cancellation mechanism). Duration in Minutes: Defines the duration of
     * the CPP event.
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
     * Gets Duration In Minutes.
     *
     * @return the Duration In Minutes
     */
    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    /**
     * Sets Duration In Minutes.
     *
     * @param durationInMinutes the Duration In Minutes
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setDurationInMinutes(final Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    /**
     * Gets Tariff Type.
     * <p>
     * An 8-bit bitmap identifying the type of tariff published in this command. The least
     * significant nibble represents an enumeration of the tariff type (Generation Meters
     * shall use the ‘Received’ Tariff). The most significant nibble is reserved.
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
     * significant nibble represents an enumeration of the tariff type (Generation Meters
     * shall use the ‘Received’ Tariff). The most significant nibble is reserved.
     *
     * @param tariffType the Tariff Type
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTariffType(final Integer tariffType) {
        this.tariffType = tariffType;
    }

    /**
     * Gets Cpp Price Tier.
     * <p>
     * An 8-bit enumeration identifying the price tier associated with this CPP event. The
     * price(s) contained in the active price matrix for that price tier will override the
     * normal pricing scheme. Prices ‘CPP1’ and ‘CPP2’ are reserved for this purposes.
     *
     * @return the Cpp Price Tier
     */
    public Integer getCppPriceTier() {
        return cppPriceTier;
    }

    /**
     * Sets Cpp Price Tier.
     * <p>
     * An 8-bit enumeration identifying the price tier associated with this CPP event. The
     * price(s) contained in the active price matrix for that price tier will override the
     * normal pricing scheme. Prices ‘CPP1’ and ‘CPP2’ are reserved for this purposes.
     *
     * @param cppPriceTier the Cpp Price Tier
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setCppPriceTier(final Integer cppPriceTier) {
        this.cppPriceTier = cppPriceTier;
    }

    /**
     * Gets Cpp Auth.
     * <p>
     * An 8-bit enumeration identifying the status of the CPP event:
     *
     * @return the Cpp Auth
     */
    public Integer getCppAuth() {
        return cppAuth;
    }

    /**
     * Sets Cpp Auth.
     * <p>
     * An 8-bit enumeration identifying the status of the CPP event:
     *
     * @param cppAuth the Cpp Auth
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setCppAuth(final Integer cppAuth) {
        this.cppAuth = cppAuth;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(providerId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(issuerEventId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(startTime, ZclDataType.UTCTIME);
        serializer.serialize(durationInMinutes, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(tariffType, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(cppPriceTier, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(cppAuth, ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        providerId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        issuerEventId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        startTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        durationInMinutes = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        tariffType = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        cppPriceTier = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        cppAuth = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(243);
        builder.append("PublishCppEventCommand [");
        builder.append(super.toString());
        builder.append(", providerId=");
        builder.append(providerId);
        builder.append(", issuerEventId=");
        builder.append(issuerEventId);
        builder.append(", startTime=");
        builder.append(startTime);
        builder.append(", durationInMinutes=");
        builder.append(durationInMinutes);
        builder.append(", tariffType=");
        builder.append(tariffType);
        builder.append(", cppPriceTier=");
        builder.append(cppPriceTier);
        builder.append(", cppAuth=");
        builder.append(cppAuth);
        builder.append(']');
        return builder.toString();
    }

}
