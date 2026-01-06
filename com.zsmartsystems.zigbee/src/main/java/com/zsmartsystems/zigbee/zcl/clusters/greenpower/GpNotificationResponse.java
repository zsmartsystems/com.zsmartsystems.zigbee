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
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Gp Notification Response value object class.
 * <p>
 * Cluster: <b>Green Power</b>. Command ID 0x00 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Green Power cluster.
 * <p>
 * This command is generated when the sink acknowledges the reception of full unicast GP
 * Notification command. The GP Notification Response command is sent in unicast to the
 * originating proxy.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class GpNotificationResponse extends ZclGreenPowerCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0021;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x00;

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
     * Gpd Security Frame Counter command message field.
     */
    private Integer gpdSecurityFrameCounter;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GpNotificationResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param options {@link Integer} Options
     * @param gpdSrcId {@link Integer} Gpd Src ID
     * @param gpdIeee {@link IeeeAddress} Gpd IEEE
     * @param gpdSecurityFrameCounter {@link Integer} Gpd Security Frame Counter
     */
    public GpNotificationResponse(
            Integer options,
            Integer gpdSrcId,
            IeeeAddress gpdIeee,
            Integer gpdSecurityFrameCounter) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.options = options;
        this.gpdSrcId = gpdSrcId;
        this.gpdIeee = gpdIeee;
        this.gpdSecurityFrameCounter = gpdSecurityFrameCounter;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGpdSecurityFrameCounter(final Integer gpdSecurityFrameCounter) {
        this.gpdSecurityFrameCounter = gpdSecurityFrameCounter;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(options, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(gpdSrcId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(gpdIeee, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(gpdSecurityFrameCounter, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        options = deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        gpdSrcId = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        gpdIeee = deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        gpdSecurityFrameCounter = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(150);
        builder.append("GpNotificationResponse [");
        builder.append(super.toString());
        builder.append(", options=");
        builder.append(options);
        builder.append(", gpdSrcId=");
        builder.append(gpdSrcId);
        builder.append(", gpdIeee=");
        builder.append(gpdIeee);
        builder.append(", gpdSecurityFrameCounter=");
        builder.append(gpdSecurityFrameCounter);
        builder.append(']');
        return builder.toString();
    }

}
