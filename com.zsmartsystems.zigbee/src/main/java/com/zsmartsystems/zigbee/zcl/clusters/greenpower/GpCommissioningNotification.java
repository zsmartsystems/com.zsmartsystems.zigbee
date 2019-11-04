/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
 * Gp Commissioning Notification value object class.
 * <p>
 * Cluster: <b>Green Power</b>. Command ID 0x04 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Green Power cluster.
 * <p>
 * The GP Commissioning Notification command is used by the proxy in commissioning mode to
 * forward commissioning data to the sink(s).
 * <p>
 * On receipt of the GP Commissioning Notification command, a device is informed about a GPD
 * device seeking to manage a pairing. Also the device which received this frame is informed of
 * bidirectional commissioning capability of the sender.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-11-03T12:48:45Z")
public class GpCommissioningNotification extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0021;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x04;

    /**
     * Options command message field.
     */
    private Integer options;

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
     * Gpd Security Frame Counter command message field.
     */
    private Integer gpdSecurityFrameCounter;

    /**
     * Gpd Command ID command message field.
     */
    private Integer gpdCommandId;

    /**
     * Gpd Command Payload command message field.
     */
    private ByteArray gpdCommandPayload;

    /**
     * Gpp Short Address command message field.
     */
    private Integer gppShortAddress;

    /**
     * Gpp Link command message field.
     */
    private Integer gppLink;

    /**
     * Mic command message field.
     */
    private Integer mic;

    /**
     * Default constructor.
     */
    public GpCommissioningNotification() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
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
     */
    public void setOptions(final Integer options) {
        this.options = options;
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
     */
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
     */
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
     */
    public void setEndpoint(final Integer endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Gets Gpd Security Frame Counter.
     *
     * @return the Gpd Security Frame Counter
     */
    public Integer getGpdSecurityFrameCounter() {
        return gpdSecurityFrameCounter;
    }

    /**
     * Sets Gpd Security Frame Counter.
     *
     * @param gpdSecurityFrameCounter the Gpd Security Frame Counter
     */
    public void setGpdSecurityFrameCounter(final Integer gpdSecurityFrameCounter) {
        this.gpdSecurityFrameCounter = gpdSecurityFrameCounter;
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
     */
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
     */
    public void setGpdCommandPayload(final ByteArray gpdCommandPayload) {
        this.gpdCommandPayload = gpdCommandPayload;
    }

    /**
     * Gets Gpp Short Address.
     *
     * @return the Gpp Short Address
     */
    public Integer getGppShortAddress() {
        return gppShortAddress;
    }

    /**
     * Sets Gpp Short Address.
     *
     * @param gppShortAddress the Gpp Short Address
     */
    public void setGppShortAddress(final Integer gppShortAddress) {
        this.gppShortAddress = gppShortAddress;
    }

    /**
     * Gets Gpp Link.
     *
     * @return the Gpp Link
     */
    public Integer getGppLink() {
        return gppLink;
    }

    /**
     * Sets Gpp Link.
     *
     * @param gppLink the Gpp Link
     */
    public void setGppLink(final Integer gppLink) {
        this.gppLink = gppLink;
    }

    /**
     * Gets Mic.
     *
     * @return the Mic
     */
    public Integer getMic() {
        return mic;
    }

    /**
     * Sets Mic.
     *
     * @param mic the Mic
     */
    public void setMic(final Integer mic) {
        this.mic = mic;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(options, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(gpdSrcId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(gpdIeee, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(endpoint, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(gpdSecurityFrameCounter, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(gpdCommandId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(gpdCommandPayload, ZclDataType.OCTET_STRING);
        serializer.serialize(gppShortAddress, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(gppLink, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(mic, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        options = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        gpdSrcId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        gpdIeee = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        endpoint = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        gpdSecurityFrameCounter = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        gpdCommandId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        gpdCommandPayload = (ByteArray) deserializer.deserialize(ZclDataType.OCTET_STRING);
        gppShortAddress = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        gppLink = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        mic = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(337);
        builder.append("GpCommissioningNotification [");
        builder.append(super.toString());
        builder.append(", options=");
        builder.append(options);
        builder.append(", gpdSrcId=");
        builder.append(gpdSrcId);
        builder.append(", gpdIeee=");
        builder.append(gpdIeee);
        builder.append(", endpoint=");
        builder.append(endpoint);
        builder.append(", gpdSecurityFrameCounter=");
        builder.append(gpdSecurityFrameCounter);
        builder.append(", gpdCommandId=");
        builder.append(gpdCommandId);
        builder.append(", gpdCommandPayload=");
        builder.append(gpdCommandPayload);
        builder.append(", gppShortAddress=");
        builder.append(gppShortAddress);
        builder.append(", gppLink=");
        builder.append(gppLink);
        builder.append(", mic=");
        builder.append(mic);
        builder.append(']');
        return builder.toString();
    }

}
