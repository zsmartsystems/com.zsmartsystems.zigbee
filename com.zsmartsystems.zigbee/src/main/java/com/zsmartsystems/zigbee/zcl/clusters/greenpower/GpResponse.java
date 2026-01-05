/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.greenpower;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Gp Response value object class.
 * <p>
 * Cluster: <b>Green Power</b>. Command ID 0x06 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Green Power cluster.
 * <p>
 * This command is generated when sink requests to send any information to a specific GPD with Rx
 * capability.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class GpResponse extends ZclGreenPowerCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0021;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x06;

    /**
     * Options command message field.
     */
    private Integer options;

    /**
     * Temp Master Short Address command message field.
     */
    private Integer tempMasterShortAddress;

    /**
     * Temp Master Tx Channel command message field.
     */
    private Integer tempMasterTxChannel;

    /**
     * Gpd Src ID command message field.
     */
    private Integer gpdSrcId;

    /**
     * Gpd IEEE command message field.
     */
    private IeeeAddress gpdIeee;

    /**
     * Endpoint command message field.
     */
    private Integer endpoint;

    /**
     * Gpd Command ID command message field.
     */
    private Integer gpdCommandId;

    /**
     * Gpd Command Payload command message field.
     */
    private ByteArray gpdCommandPayload;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GpResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param options {@link Integer} Options
     * @param tempMasterShortAddress {@link Integer} Temp Master Short Address
     * @param tempMasterTxChannel {@link Integer} Temp Master Tx Channel
     * @param gpdSrcId {@link Integer} Gpd Src ID
     * @param gpdIeee {@link IeeeAddress} Gpd IEEE
     * @param endpoint {@link Integer} Endpoint
     * @param gpdCommandId {@link Integer} Gpd Command ID
     * @param gpdCommandPayload {@link ByteArray} Gpd Command Payload
     */
    public GpResponse(
            Integer options,
            Integer tempMasterShortAddress,
            Integer tempMasterTxChannel,
            Integer gpdSrcId,
            IeeeAddress gpdIeee,
            Integer endpoint,
            Integer gpdCommandId,
            ByteArray gpdCommandPayload) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.options = options;
        this.tempMasterShortAddress = tempMasterShortAddress;
        this.tempMasterTxChannel = tempMasterTxChannel;
        this.gpdSrcId = gpdSrcId;
        this.gpdIeee = gpdIeee;
        this.endpoint = endpoint;
        this.gpdCommandId = gpdCommandId;
        this.gpdCommandPayload = gpdCommandPayload;
    }

    /**
     * Gets Options.
     *
     * @return the Options
     */
    public Integer getOptions() {
        return options;
    }

    /**
     * Sets Options.
     *
     * @param options the Options
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setOptions(final Integer options) {
        this.options = options;
    }

    /**
     * Gets Temp Master Short Address.
     *
     * @return the Temp Master Short Address
     */
    public Integer getTempMasterShortAddress() {
        return tempMasterShortAddress;
    }

    /**
     * Sets Temp Master Short Address.
     *
     * @param tempMasterShortAddress the Temp Master Short Address
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTempMasterShortAddress(final Integer tempMasterShortAddress) {
        this.tempMasterShortAddress = tempMasterShortAddress;
    }

    /**
     * Gets Temp Master Tx Channel.
     *
     * @return the Temp Master Tx Channel
     */
    public Integer getTempMasterTxChannel() {
        return tempMasterTxChannel;
    }

    /**
     * Sets Temp Master Tx Channel.
     *
     * @param tempMasterTxChannel the Temp Master Tx Channel
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTempMasterTxChannel(final Integer tempMasterTxChannel) {
        this.tempMasterTxChannel = tempMasterTxChannel;
    }

    /**
     * Gets Gpd Src ID.
     *
     * @return the Gpd Src ID
     */
    public Integer getGpdSrcId() {
        return gpdSrcId;
    }

    /**
     * Sets Gpd Src ID.
     *
     * @param gpdSrcId the Gpd Src ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGpdSrcId(final Integer gpdSrcId) {
        this.gpdSrcId = gpdSrcId;
    }

    /**
     * Gets Gpd IEEE.
     *
     * @return the Gpd IEEE
     */
    public IeeeAddress getGpdIeee() {
        return gpdIeee;
    }

    /**
     * Sets Gpd IEEE.
     *
     * @param gpdIeee the Gpd IEEE
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGpdIeee(final IeeeAddress gpdIeee) {
        this.gpdIeee = gpdIeee;
    }

    /**
     * Gets Endpoint.
     *
     * @return the Endpoint
     */
    public Integer getEndpoint() {
        return endpoint;
    }

    /**
     * Sets Endpoint.
     *
     * @param endpoint the Endpoint
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setEndpoint(final Integer endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Gets Gpd Command ID.
     *
     * @return the Gpd Command ID
     */
    public Integer getGpdCommandId() {
        return gpdCommandId;
    }

    /**
     * Sets Gpd Command ID.
     *
     * @param gpdCommandId the Gpd Command ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGpdCommandId(final Integer gpdCommandId) {
        this.gpdCommandId = gpdCommandId;
    }

    /**
     * Gets Gpd Command Payload.
     *
     * @return the Gpd Command Payload
     */
    public ByteArray getGpdCommandPayload() {
        return gpdCommandPayload;
    }

    /**
     * Sets Gpd Command Payload.
     *
     * @param gpdCommandPayload the Gpd Command Payload
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGpdCommandPayload(final ByteArray gpdCommandPayload) {
        this.gpdCommandPayload = gpdCommandPayload;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(options, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(tempMasterShortAddress, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(tempMasterTxChannel, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(gpdSrcId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(gpdIeee, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(endpoint, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(gpdCommandId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(gpdCommandPayload, ZclDataType.OCTET_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        options = deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        tempMasterShortAddress = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        tempMasterTxChannel = deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        gpdSrcId = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        gpdIeee = deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        endpoint = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        gpdCommandId = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        gpdCommandPayload = deserializer.deserialize(ZclDataType.OCTET_STRING);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(273);
        builder.append("GpResponse [");
        builder.append(super.toString());
        builder.append(", options=");
        builder.append(options);
        builder.append(", tempMasterShortAddress=");
        builder.append(tempMasterShortAddress);
        builder.append(", tempMasterTxChannel=");
        builder.append(tempMasterTxChannel);
        builder.append(", gpdSrcId=");
        builder.append(gpdSrcId);
        builder.append(", gpdIeee=");
        builder.append(gpdIeee);
        builder.append(", endpoint=");
        builder.append(endpoint);
        builder.append(", gpdCommandId=");
        builder.append(gpdCommandId);
        builder.append(", gpdCommandPayload=");
        builder.append(gpdCommandPayload);
        builder.append(']');
        return builder.toString();
    }

}
