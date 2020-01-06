/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.greenpower;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-06T18:45:28Z")
public class GpResponse extends ZclCommand {
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
     */
    public GpResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
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
     * @return the GpResponse command
     */
    public GpResponse setOptions(final Integer options) {
        this.options = options;
        return this;
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
     * @return the GpResponse command
     */
    public GpResponse setTempMasterShortAddress(final Integer tempMasterShortAddress) {
        this.tempMasterShortAddress = tempMasterShortAddress;
        return this;
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
     * @return the GpResponse command
     */
    public GpResponse setTempMasterTxChannel(final Integer tempMasterTxChannel) {
        this.tempMasterTxChannel = tempMasterTxChannel;
        return this;
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
     * @return the GpResponse command
     */
    public GpResponse setGpdSrcId(final Integer gpdSrcId) {
        this.gpdSrcId = gpdSrcId;
        return this;
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
     * @return the GpResponse command
     */
    public GpResponse setGpdIeee(final IeeeAddress gpdIeee) {
        this.gpdIeee = gpdIeee;
        return this;
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
     * @return the GpResponse command
     */
    public GpResponse setEndpoint(final Integer endpoint) {
        this.endpoint = endpoint;
        return this;
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
     * @return the GpResponse command
     */
    public GpResponse setGpdCommandId(final Integer gpdCommandId) {
        this.gpdCommandId = gpdCommandId;
        return this;
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
     * @return the GpResponse command
     */
    public GpResponse setGpdCommandPayload(final ByteArray gpdCommandPayload) {
        this.gpdCommandPayload = gpdCommandPayload;
        return this;
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
        options = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        tempMasterShortAddress = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        tempMasterTxChannel = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        gpdSrcId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        gpdIeee = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        endpoint = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        gpdCommandId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        gpdCommandPayload = (ByteArray) deserializer.deserialize(ZclDataType.OCTET_STRING);
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
