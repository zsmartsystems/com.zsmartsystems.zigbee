/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.CommandResponseMatcher;
import com.zsmartsystems.zigbee.zdo.command.NetworkAddressResponse;
import com.zsmartsystems.zigbee.IeeeAddress;

/**
 * Network Address Request value object class.
 * <p>
 * The NWK_addr_req is generated from a Local Device wishing to inquire as to the
 * 16-bit address of the Remote Device based on its known IEEE address. The
 * destination addressing on this command shall be unicast or broadcast to all
 * devices for which macRxOnWhenIdle = TRUE.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class NetworkAddressRequest extends ZdoRequest implements CommandResponseMatcher {
    /**
     * IEEEAddr command message field.
     */
    private IeeeAddress ieeeAddr;

    /**
     * RequestType command message field.
     *
     * Request type for this command:
     * 0x00 – Single device response
     * 0x01 – Extended response
     * 0x02-0xFF – reserved
     */
    private Integer requestType;

    /**
     * StartIndex command message field.
     */
    private Integer startIndex;

    /**
     * Default constructor.
     */
    public NetworkAddressRequest() {
        clusterId = 0x0000;
    }

    /**
     * Gets IEEEAddr.
     *
     * @return the IEEEAddr
     */
    public IeeeAddress getIeeeAddr() {
        return ieeeAddr;
    }

    /**
     * Sets IEEEAddr.
     *
     * @param ieeeAddr the IEEEAddr
     */
    public void setIeeeAddr(final IeeeAddress ieeeAddr) {
        this.ieeeAddr = ieeeAddr;
    }

    /**
     * Gets RequestType.
     * <p>
     * Request type for this command:
     * 0x00 – Single device response
     * 0x01 – Extended response
     * 0x02-0xFF – reserved
     *
     * @return the RequestType
     */
    public Integer getRequestType() {
        return requestType;
    }

    /**
     * Sets RequestType.
     * <p>
     * Request type for this command:
     * 0x00 – Single device response
     * 0x01 – Extended response
     * 0x02-0xFF – reserved
     *
     * @param requestType the RequestType
     */
    public void setRequestType(final Integer requestType) {
        this.requestType = requestType;
    }

    /**
     * Gets StartIndex.
     *
     * @return the StartIndex
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * Sets StartIndex.
     *
     * @param startIndex the StartIndex
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
    public boolean isMatch(ZigBeeCommand request, ZigBeeCommand response) {
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
