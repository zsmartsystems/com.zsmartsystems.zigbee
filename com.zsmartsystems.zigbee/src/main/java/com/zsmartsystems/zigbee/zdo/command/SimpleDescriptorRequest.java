/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;
import com.zsmartsystems.zigbee.zdo.command.SimpleDescriptorResponse;

/**
 * Simple Descriptor Request value object class.
 * <p>
 * <p>
 * The Simple_Desc_req command is generated from a local device wishing to inquire as to the
 * simple descriptor of a remote device on a specified endpoint. This command shall be unicast
 * either to the remote device itself or to an alternative device that contains the discovery
 * information of the remote device.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class SimpleDescriptorRequest extends ZdoRequest implements ZigBeeTransactionMatcher {
    /**
     * NWK Addr Of Interest command message field.
     */
    private Integer nwkAddrOfInterest;

    /**
     * Endpoint command message field.
     */
    private Integer endpoint;

    /**
     * Default constructor.
     */
    public SimpleDescriptorRequest() {
        clusterId = 0x0004;
    }

    /**
     * Gets NWK Addr Of Interest.
     *
     * @return the NWK Addr Of Interest
     */
    public Integer getNwkAddrOfInterest() {
        return nwkAddrOfInterest;
    }

    /**
     * Sets NWK Addr Of Interest.
     *
     * @param nwkAddrOfInterest the NWK Addr Of Interest
     */
    public void setNwkAddrOfInterest(final Integer nwkAddrOfInterest) {
        this.nwkAddrOfInterest = nwkAddrOfInterest;
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

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(nwkAddrOfInterest, ZclDataType.NWK_ADDRESS);
        serializer.serialize(endpoint, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        nwkAddrOfInterest = (Integer) deserializer.deserialize(ZclDataType.NWK_ADDRESS);
        endpoint = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public boolean isTransactionMatch(ZigBeeCommand request, ZigBeeCommand response) {
        if (!(response instanceof SimpleDescriptorResponse)) {
            return false;
        }

        return (((SimpleDescriptorRequest) request).getEndpoint()
                .equals(((SimpleDescriptorResponse) response).getSimpleDescriptor().getEndpoint()))
                && (((SimpleDescriptorRequest) request).getNwkAddrOfInterest()
                .equals(((SimpleDescriptorResponse) response).getNwkAddrOfInterest()));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(91);
        builder.append("SimpleDescriptorRequest [");
        builder.append(super.toString());
        builder.append(", nwkAddrOfInterest=");
        builder.append(nwkAddrOfInterest);
        builder.append(", endpoint=");
        builder.append(endpoint);
        builder.append(']');
        return builder.toString();
    }

}
