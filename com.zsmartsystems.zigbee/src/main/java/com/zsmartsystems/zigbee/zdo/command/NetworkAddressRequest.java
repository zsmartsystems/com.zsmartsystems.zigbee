/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;
import com.zsmartsystems.zigbee.zdo.command.NetworkAddressResponse;

/**
 * Network Address Request value object class.
 * <p>
 * <p>
 * The NWK_addr_req is generated from a Local Device wishing to inquire as to the 16-bit address
 * of the Remote Device based on its known IEEE address. The destination addressing on this
 * command shall be unicast or broadcast to all devices for which macRxOnWhenIdle = TRUE.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class NetworkAddressRequest extends ZdoRequest implements ZigBeeTransactionMatcher {
    /**
     * IEEE Addr command message field.
     */
    private IeeeAddress ieeeAddr;

    /**
     * Request Type command message field.
     * <p>
     * Request type for this command: 0x00 – Single device response 0x01 – Extended response
     * 0x02-0xFF – reserved
     */
    private Integer requestType;

    /**
     * Start Index command message field.
     */
    private Integer startIndex;

    /**
     * Default constructor.
     */
    public NetworkAddressRequest() {
        clusterId = 0x0000;
    }

    /**
     * Gets IEEE Addr.
     *
     * @return the IEEE Addr
     */
    public IeeeAddress getIeeeAddr() {
        return ieeeAddr;
    }

    /**
     * Sets IEEE Addr.
     *
     * @param ieeeAddr the IEEE Addr
     */
    public void setIeeeAddr(final IeeeAddress ieeeAddr) {
        this.ieeeAddr = ieeeAddr;
    }

    /**
     * Gets Request Type.
     * <p>
     * Request type for this command: 0x00 – Single device response 0x01 – Extended response
     * 0x02-0xFF – reserved
     *
     * @return the Request Type
     */
    public Integer getRequestType() {
        return requestType;
    }

    /**
     * Sets Request Type.
     * <p>
     * Request type for this command: 0x00 – Single device response 0x01 – Extended response
     * 0x02-0xFF – reserved
     *
     * @param requestType the Request Type
     */
    public void setRequestType(final Integer requestType) {
        this.requestType = requestType;
    }

    /**
     * Gets Start Index.
     *
     * @return the Start Index
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * Sets Start Index.
     *
     * @param startIndex the Start Index
     */
    public void setStartIndex(final Integer startIndex) {
        this.startIndex = startIndex;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(ieeeAddr, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(requestType, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(startIndex, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        ieeeAddr = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        requestType = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        startIndex = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public boolean isTransactionMatch(ZigBeeCommand request, ZigBeeCommand response) {
        if (!(response instanceof NetworkAddressResponse)) {
            return false;
        }

        return (((NetworkAddressRequest) request).getIeeeAddr()
                .equals(((NetworkAddressResponse) response).getIeeeAddrRemoteDev()));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(113);
        builder.append("NetworkAddressRequest [");
        builder.append(super.toString());
        builder.append(", ieeeAddr=");
        builder.append(ieeeAddr);
        builder.append(", requestType=");
        builder.append(requestType);
        builder.append(", startIndex=");
        builder.append(startIndex);
        builder.append(']');
        return builder.toString();
    }

}
