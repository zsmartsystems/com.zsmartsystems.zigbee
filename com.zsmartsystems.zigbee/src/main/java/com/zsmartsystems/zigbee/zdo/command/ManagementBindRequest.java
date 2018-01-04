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
import com.zsmartsystems.zigbee.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.zdo.command.ManagementBindResponse;

/**
 * Management Bind Request value object class.
 * <p>
 * The Mgmt_Bind_req is generated from a Local Device wishing to retrieve the
 * contents of the Binding Table from the Remote Device. The destination
 * addressing on this command shall be unicast only and the destination address
 * must be that of a Primary binding table cache or source device holding its own
 * binding table.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ManagementBindRequest extends ZdoRequest implements ZigBeeTransactionMatcher {
    /**
     * StartIndex command message field.
     */
    private Integer startIndex;

    /**
     * Default constructor.
     */
    public ManagementBindRequest() {
        clusterId = 0x0033;
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

        serializer.serialize(startIndex, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        startIndex = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public boolean isTransactionMatch(ZigBeeCommand request, ZigBeeCommand response) {
        if (!(response instanceof ManagementBindResponse)) {
            return false;
        }

        return ((ZdoRequest) request).getDestinationAddress().equals(((ManagementBindResponse) response).getSourceAddress());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(54);
        builder.append("ManagementBindRequest [");
        builder.append(super.toString());
        builder.append(", startIndex=");
        builder.append(startIndex);
        builder.append(']');
        return builder.toString();
    }

}
