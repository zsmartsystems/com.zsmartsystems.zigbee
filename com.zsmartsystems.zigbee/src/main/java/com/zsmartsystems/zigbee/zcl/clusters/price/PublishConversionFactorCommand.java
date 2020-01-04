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
 * Publish Conversion Factor Command value object class.
 * <p>
 * Cluster: <b>Price</b>. Command ID 0x02 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Price cluster.
 * <p>
 * The PublishConversionFactor command is sent in response to a GetConversionFactor command
 * or if a new conversion factor is available. Clients shall be capable of storing at least two
 * instances of the Conversion Factor, the currently active one and the next one.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-04-14T08:41:54Z")
public class PublishConversionFactorCommand extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0700;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x02;

    /**
     * Issuer Event ID command message field.
     * <p>
     * Unique identifier generated by the commodity provider.
     */
    private Integer issuerEventId;

    /**
     * Start Time command message field.
     * <p>
     * A UTCTime field to denote the time at which the value becomes valid. The value remains
     * valid until replaced by a newer one.
     */
    private Calendar startTime;

    /**
     * Conversion Factor command message field.
     * <p>
     * See Price Cluster Commodity attributes.
     */
    private Integer conversionFactor;

    /**
     * Conversion Factor Trailing Digit command message field.
     * <p>
     * See Price Cluster Commodity attributes.
     */
    private Integer conversionFactorTrailingDigit;

    /**
     * Default constructor.
     */
    public PublishConversionFactorCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
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
     * Gets Start Time.
     * <p>
     * A UTCTime field to denote the time at which the value becomes valid. The value remains
     * valid until replaced by a newer one.
     *
     * @return the Start Time
     */
    public Calendar getStartTime() {
        return startTime;
    }

    /**
     * Sets Start Time.
     * <p>
     * A UTCTime field to denote the time at which the value becomes valid. The value remains
     * valid until replaced by a newer one.
     *
     * @param startTime the Start Time
     */
    public void setStartTime(final Calendar startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets Conversion Factor.
     * <p>
     * See Price Cluster Commodity attributes.
     *
     * @return the Conversion Factor
     */
    public Integer getConversionFactor() {
        return conversionFactor;
    }

    /**
     * Sets Conversion Factor.
     * <p>
     * See Price Cluster Commodity attributes.
     *
     * @param conversionFactor the Conversion Factor
     */
    public void setConversionFactor(final Integer conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    /**
     * Gets Conversion Factor Trailing Digit.
     * <p>
     * See Price Cluster Commodity attributes.
     *
     * @return the Conversion Factor Trailing Digit
     */
    public Integer getConversionFactorTrailingDigit() {
        return conversionFactorTrailingDigit;
    }

    /**
     * Sets Conversion Factor Trailing Digit.
     * <p>
     * See Price Cluster Commodity attributes.
     *
     * @param conversionFactorTrailingDigit the Conversion Factor Trailing Digit
     */
    public void setConversionFactorTrailingDigit(final Integer conversionFactorTrailingDigit) {
        this.conversionFactorTrailingDigit = conversionFactorTrailingDigit;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(issuerEventId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(startTime, ZclDataType.UTCTIME);
        serializer.serialize(conversionFactor, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(conversionFactorTrailingDigit, ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        issuerEventId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        startTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        conversionFactor = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        conversionFactorTrailingDigit = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(180);
        builder.append("PublishConversionFactorCommand [");
        builder.append(super.toString());
        builder.append(", issuerEventId=");
        builder.append(issuerEventId);
        builder.append(", startTime=");
        builder.append(startTime);
        builder.append(", conversionFactor=");
        builder.append(conversionFactor);
        builder.append(", conversionFactorTrailingDigit=");
        builder.append(conversionFactorTrailingDigit);
        builder.append(']');
        return builder.toString();
    }

}
