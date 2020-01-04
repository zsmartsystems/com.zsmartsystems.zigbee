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

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Price Acknowledgement Command value object class.
 * <p>
 * Cluster: <b>Price</b>. Command ID 0x02 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Price cluster.
 * <p>
 * The PriceAcknowledgement command provides the ability to acknowledge a previously sent
 * PublishPrice command. It is mandatory for 1.1 and later devices. For SE 1.0 devices, the
 * command is optional.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-04-14T08:41:54Z")
public class PriceAcknowledgementCommand extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0700;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x02;

    /**
     * Provider ID command message field.
     * <p>
     * An unsigned 32 bit field containing a unique identifier for the commodity provider
     */
    private Integer providerId;

    /**
     * Issuer Event ID command message field.
     * <p>
     * Unique identifier generated by the commodity provider.
     */
    private Integer issuerEventId;

    /**
     * Price Ack Time command message field.
     * <p>
     * Time price acknowledgement generated.
     */
    private Calendar priceAckTime;

    /**
     * Control command message field.
     * <p>
     * Identifies the Price Control or Block Period Control options for the event.
     */
    private Integer control;

    /**
     * Default constructor.
     */
    public PriceAcknowledgementCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Provider ID.
     * <p>
     * An unsigned 32 bit field containing a unique identifier for the commodity provider
     *
     * @return the Provider ID
     */
    public Integer getProviderId() {
        return providerId;
    }

    /**
     * Sets Provider ID.
     * <p>
     * An unsigned 32 bit field containing a unique identifier for the commodity provider
     *
     * @param providerId the Provider ID
     */
    public void setProviderId(final Integer providerId) {
        this.providerId = providerId;
    }

    /**
     * Gets Issuer Event ID.
     * <p>
     * Unique identifier generated by the commodity provider.
     *
     * @return the Issuer Event ID
     */
    public Integer getIssuerEventId() {
        return issuerEventId;
    }

    /**
     * Sets Issuer Event ID.
     * <p>
     * Unique identifier generated by the commodity provider.
     *
     * @param issuerEventId the Issuer Event ID
     */
    public void setIssuerEventId(final Integer issuerEventId) {
        this.issuerEventId = issuerEventId;
    }

    /**
     * Gets Price Ack Time.
     * <p>
     * Time price acknowledgement generated.
     *
     * @return the Price Ack Time
     */
    public Calendar getPriceAckTime() {
        return priceAckTime;
    }

    /**
     * Sets Price Ack Time.
     * <p>
     * Time price acknowledgement generated.
     *
     * @param priceAckTime the Price Ack Time
     */
    public void setPriceAckTime(final Calendar priceAckTime) {
        this.priceAckTime = priceAckTime;
    }

    /**
     * Gets Control.
     * <p>
     * Identifies the Price Control or Block Period Control options for the event.
     *
     * @return the Control
     */
    public Integer getControl() {
        return control;
    }

    /**
     * Sets Control.
     * <p>
     * Identifies the Price Control or Block Period Control options for the event.
     *
     * @param control the Control
     */
    public void setControl(final Integer control) {
        this.control = control;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(providerId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(issuerEventId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(priceAckTime, ZclDataType.UTCTIME);
        serializer.serialize(control, ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        providerId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        issuerEventId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        priceAckTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        control = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(152);
        builder.append("PriceAcknowledgementCommand [");
        builder.append(super.toString());
        builder.append(", providerId=");
        builder.append(providerId);
        builder.append(", issuerEventId=");
        builder.append(issuerEventId);
        builder.append(", priceAckTime=");
        builder.append(priceAckTime);
        builder.append(", control=");
        builder.append(control);
        builder.append(']');
        return builder.toString();
    }

}
