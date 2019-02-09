/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.price;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Price Matrix Command value object class.
 * <p>
 * Cluster: <b>Price</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Price cluster.
 * <p>
 * This command initiates a PublishPriceMatrix command for the scheduled Price Matrix
 * updates. A server device shall be capable of storing at least two instances, current and next
 * instance to be activated in the future. <br> A ZCL Default response with status NOT_FOUND
 * shall be returned if there are no Price Matrix updates available.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetPriceMatrixCommand extends ZclCommand {
    /**
     * Issuer Tariff ID command message field.
     * <p>
     * IssuerTariffID indicates the tariff to which the requested Price Matrix belongs.
     */
    private Integer issuerTariffId;

    /**
     * Default constructor.
     */
    public GetPriceMatrixCommand() {
        genericCommand = false;
        clusterId = 0x0700;
        commandId = 7;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Issuer Tariff ID.
     * <p>
     * IssuerTariffID indicates the tariff to which the requested Price Matrix belongs.
     *
     * @return the Issuer Tariff ID
     */
    public Integer getIssuerTariffId() {
        return issuerTariffId;
    }

    /**
     * Sets Issuer Tariff ID.
     * <p>
     * IssuerTariffID indicates the tariff to which the requested Price Matrix belongs.
     *
     * @param issuerTariffId the Issuer Tariff ID
     */
    public void setIssuerTariffId(final Integer issuerTariffId) {
        this.issuerTariffId = issuerTariffId;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(issuerTariffId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        issuerTariffId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(58);
        builder.append("GetPriceMatrixCommand [");
        builder.append(super.toString());
        builder.append(", issuerTariffId=");
        builder.append(issuerTariffId);
        builder.append(']');
        return builder.toString();
    }

}
