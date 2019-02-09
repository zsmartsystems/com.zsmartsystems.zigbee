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
import com.zsmartsystems.zigbee.zdo.command.ManagementLqiResponse;

/**
 * Management LQI Request value object class.
 * <p>
 * <p>
 * The Mgmt_Lqi_req is generated from a Local Device wishing to obtain a neighbor list for the
 * Remote Device along with associated LQI values to each neighbor. The destination
 * addressing on this command shall be unicast only and the destination address must be that of a
 * ZigBee Coordinator or ZigBee Router.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ManagementLqiRequest extends ZdoRequest implements ZigBeeTransactionMatcher {
    /**
     * Start Index command message field.
     */
    private Integer startIndex;

    /**
     * Default constructor.
     */
    public ManagementLqiRequest() {
        clusterId = 0x0031;
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

        serializer.serialize(startIndex, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        startIndex = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public boolean isTransactionMatch(ZigBeeCommand request, ZigBeeCommand response) {
        if (!(response instanceof ManagementLqiResponse)) {
            return false;
        }

        return (((ManagementLqiRequest) request).getDestinationAddress()
                .equals(((ManagementLqiResponse) response).getSourceAddress()));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(53);
        builder.append("ManagementLqiRequest [");
        builder.append(super.toString());
        builder.append(", startIndex=");
        builder.append(startIndex);
        builder.append(']');
        return builder.toString();
    }

}
