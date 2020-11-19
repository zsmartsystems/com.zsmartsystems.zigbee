/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.ZigBeeAddress;
import com.zsmartsystems.zigbee.ZigBeeBroadcastDestination;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;
import com.zsmartsystems.zigbee.zdo.command.ManagementBindResponse;

/**
 * Management Bind Request value object class.
 * <p>
 * <p>
 * The Mgmt_Bind_req is generated from a Local Device wishing to retrieve the contents of the
 * Binding Table from the Remote Device. The destination addressing on this command shall be
 * unicast only and the destination address must be that of a Primary binding table cache or
 * source device holding its own binding table.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-08-28T10:02:45Z")
public class ManagementBindRequest extends ZdoRequest implements ZigBeeTransactionMatcher {
    /**
     * The ZDO cluster ID.
     */
    public static int CLUSTER_ID = 0x0033;

    /**
     * Start Index command message field.
     */
    private Integer startIndex;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.
     */
    @Deprecated
    public ManagementBindRequest() {
        clusterId = CLUSTER_ID;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param startIndex {@link Integer} Start Index
     */
    public ManagementBindRequest(
            Integer startIndex) {

        clusterId = CLUSTER_ID;

        this.startIndex = startIndex;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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

        ZigBeeAddress destinationAddress = ((ZdoRequest) request).getDestinationAddress();
        ZigBeeAddress sourceAddress = ((ZdoResponse) response).getSourceAddress();
        ZigBeeEndpointAddress localCoordinator = new ZigBeeEndpointAddress(0, 0);

        if(!ZigBeeBroadcastDestination.isBroadcast(destinationAddress.getAddress())) {
            if (!localCoordinator.equals(sourceAddress) && !destinationAddress.equals(sourceAddress)) {
                return false;
            }
        }
        return true;
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
