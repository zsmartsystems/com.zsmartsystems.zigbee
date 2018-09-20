/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.metering;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.field.ZigBeeUtcTime;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Change Supply value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command ID 0x0B is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * This command is sent from the Head-end or ESI to the Metering Device to instruct it to change
 * the status of the valve or load switch, i.e. the supply.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T21:19:38Z")
public class ChangeSupply extends ZclMeteringCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0702;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x0B;

    /**
     * Provider ID command message field.
     * <p>
     * An unsigned 32-bit field containing a unique identifier for the commodity provider to
     * whom this command relates.
     */
    private Integer providerId;

    /**
     * Issuer Event ID command message field.
     * <p>
     * Unique identifier generated by the commodity provider. When new information is
     * provided that replaces older information for the same time period, this field allows
     * devices to determine which information is newer. The value contained in this field is a
     * unique number managed by upstream servers or a UTC based time stamp (UTCTime data type)
     * identifying when the command was issued. Thus, newer information will have a value in
     * the Issuer Event ID field that is larger than older information.
     */
    private Integer issuerEventId;

    /**
     * Request Date Time command message field.
     * <p>
     * A UTC Time field to indicate the date and time at which the supply change was requested.
     */
    private ZigBeeUtcTime requestDateTime;

    /**
     * Implementation Date Time command message field.
     * <p>
     * A UTC Time field to indicate the date at which the supply change is to be applied. An
     * Implementation Date/Time of 0x00000000 shall indicate that the command should be
     * executed immediately. An Implementation Date/Time of 0xFFFFFFFF shall cause an
     * existing but pending Change Supply command with the same Provider ID and Issuer Event ID
     * to be cancelled (the status of the supply will not change but the Proposed Change Supply
     * Implementation Time attribute shall be reset to zero).
     */
    private ZigBeeUtcTime implementationDateTime;

    /**
     * Proposed Supply Status command message field.
     * <p>
     * An 8-bit enumeration field indicating the status of the energy supply controlled by the
     * Metering Device following implementation of this command.
     */
    private Integer proposedSupplyStatus;

    /**
     * Supply Control Bits command message field.
     * <p>
     * An 8-bit BitMap where the least significant nibble defines the Supply Control bits.
     */
    private Integer supplyControlBits;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public ChangeSupply() {
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
     * @param requestDateTime {@link ZigBeeUtcTime} Request Date Time
     * @param implementationDateTime {@link ZigBeeUtcTime} Implementation Date Time
     * @param proposedSupplyStatus {@link Integer} Proposed Supply Status
     * @param supplyControlBits {@link Integer} Supply Control Bits
     */
    public ChangeSupply(
            Integer providerId,
            Integer issuerEventId,
            ZigBeeUtcTime requestDateTime,
            ZigBeeUtcTime implementationDateTime,
            Integer proposedSupplyStatus,
            Integer supplyControlBits) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.providerId = providerId;
        this.issuerEventId = issuerEventId;
        this.requestDateTime = requestDateTime;
        this.implementationDateTime = implementationDateTime;
        this.proposedSupplyStatus = proposedSupplyStatus;
        this.supplyControlBits = supplyControlBits;
    }

    /**
     * Gets Provider ID.
     * <p>
     * An unsigned 32-bit field containing a unique identifier for the commodity provider to
     * whom this command relates.
     *
     * @return the Provider ID
     */
    public Integer getProviderId() {
        return providerId;
    }

    /**
     * Sets Provider ID.
     * <p>
     * An unsigned 32-bit field containing a unique identifier for the commodity provider to
     * whom this command relates.
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
     * identifying when the command was issued. Thus, newer information will have a value in
     * the Issuer Event ID field that is larger than older information.
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
     * identifying when the command was issued. Thus, newer information will have a value in
     * the Issuer Event ID field that is larger than older information.
     *
     * @param issuerEventId the Issuer Event ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setIssuerEventId(final Integer issuerEventId) {
        this.issuerEventId = issuerEventId;
    }

    /**
     * Gets Request Date Time.
     * <p>
     * A UTC Time field to indicate the date and time at which the supply change was requested.
     *
     * @return the Request Date Time
     */
    public ZigBeeUtcTime getRequestDateTime() {
        return requestDateTime;
    }

    /**
     * Sets Request Date Time.
     * <p>
     * A UTC Time field to indicate the date and time at which the supply change was requested.
     *
     * @param requestDateTime the Request Date Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setRequestDateTime(final ZigBeeUtcTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    /**
     * Gets Implementation Date Time.
     * <p>
     * A UTC Time field to indicate the date at which the supply change is to be applied. An
     * Implementation Date/Time of 0x00000000 shall indicate that the command should be
     * executed immediately. An Implementation Date/Time of 0xFFFFFFFF shall cause an
     * existing but pending Change Supply command with the same Provider ID and Issuer Event ID
     * to be cancelled (the status of the supply will not change but the Proposed Change Supply
     * Implementation Time attribute shall be reset to zero).
     *
     * @return the Implementation Date Time
     */
    public ZigBeeUtcTime getImplementationDateTime() {
        return implementationDateTime;
    }

    /**
     * Sets Implementation Date Time.
     * <p>
     * A UTC Time field to indicate the date at which the supply change is to be applied. An
     * Implementation Date/Time of 0x00000000 shall indicate that the command should be
     * executed immediately. An Implementation Date/Time of 0xFFFFFFFF shall cause an
     * existing but pending Change Supply command with the same Provider ID and Issuer Event ID
     * to be cancelled (the status of the supply will not change but the Proposed Change Supply
     * Implementation Time attribute shall be reset to zero).
     *
     * @param implementationDateTime the Implementation Date Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setImplementationDateTime(final ZigBeeUtcTime implementationDateTime) {
        this.implementationDateTime = implementationDateTime;
    }

    /**
     * Gets Proposed Supply Status.
     * <p>
     * An 8-bit enumeration field indicating the status of the energy supply controlled by the
     * Metering Device following implementation of this command.
     *
     * @return the Proposed Supply Status
     */
    public Integer getProposedSupplyStatus() {
        return proposedSupplyStatus;
    }

    /**
     * Sets Proposed Supply Status.
     * <p>
     * An 8-bit enumeration field indicating the status of the energy supply controlled by the
     * Metering Device following implementation of this command.
     *
     * @param proposedSupplyStatus the Proposed Supply Status
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setProposedSupplyStatus(final Integer proposedSupplyStatus) {
        this.proposedSupplyStatus = proposedSupplyStatus;
    }

    /**
     * Gets Supply Control Bits.
     * <p>
     * An 8-bit BitMap where the least significant nibble defines the Supply Control bits.
     *
     * @return the Supply Control Bits
     */
    public Integer getSupplyControlBits() {
        return supplyControlBits;
    }

    /**
     * Sets Supply Control Bits.
     * <p>
     * An 8-bit BitMap where the least significant nibble defines the Supply Control bits.
     *
     * @param supplyControlBits the Supply Control Bits
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSupplyControlBits(final Integer supplyControlBits) {
        this.supplyControlBits = supplyControlBits;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(providerId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(issuerEventId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(requestDateTime, ZclDataType.UTCTIME);
        serializer.serialize(implementationDateTime, ZclDataType.UTCTIME);
        serializer.serialize(proposedSupplyStatus, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(supplyControlBits, ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        providerId = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        issuerEventId = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        requestDateTime = deserializer.deserialize(ZclDataType.UTCTIME);
        implementationDateTime = deserializer.deserialize(ZclDataType.UTCTIME);
        proposedSupplyStatus = deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        supplyControlBits = deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(232);
        builder.append("ChangeSupply [");
        builder.append(super.toString());
        builder.append(", providerId=");
        builder.append(providerId);
        builder.append(", issuerEventId=");
        builder.append(issuerEventId);
        builder.append(", requestDateTime=");
        builder.append(requestDateTime);
        builder.append(", implementationDateTime=");
        builder.append(implementationDateTime);
        builder.append(", proposedSupplyStatus=");
        builder.append(proposedSupplyStatus);
        builder.append(", supplyControlBits=");
        builder.append(supplyControlBits);
        builder.append(']');
        return builder.toString();
    }

}
