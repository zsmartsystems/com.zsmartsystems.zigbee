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
import com.zsmartsystems.zigbee.zdo.command.NodeDescriptorResponse;

/**
 * Node Descriptor Request value object class.
 * <p>
 * <p>
 * The Node_Desc_req command is generated from a local device wishing to inquire as to the node
 * descriptor of a remote device. This command shall be unicast either to the remote device
 * itself or to an alternative device that contains the discovery information of the remote
 * device.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class NodeDescriptorRequest extends ZdoRequest implements ZigBeeTransactionMatcher {
    /**
     * The ZDO cluster ID.
     */
    public static int CLUSTER_ID = 0x0002;

    /**
     * NWK Addr Of Interest command message field.
     */
    private Integer nwkAddrOfInterest;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public NodeDescriptorRequest() {
        clusterId = CLUSTER_ID;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param nwkAddrOfInterest {@link Integer} NWK Addr Of Interest
     */
    public NodeDescriptorRequest(
            Integer nwkAddrOfInterest) {

        clusterId = CLUSTER_ID;

        this.nwkAddrOfInterest = nwkAddrOfInterest;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setNwkAddrOfInterest(final Integer nwkAddrOfInterest) {
        this.nwkAddrOfInterest = nwkAddrOfInterest;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(nwkAddrOfInterest, ZclDataType.NWK_ADDRESS);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        nwkAddrOfInterest = (Integer) deserializer.deserialize(ZclDataType.NWK_ADDRESS);
    }

    @Override
    public boolean isTransactionMatch(ZigBeeCommand request, ZigBeeCommand response) {
        if (!(response instanceof NodeDescriptorResponse)) {
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
        final StringBuilder builder = new StringBuilder(61);
        builder.append("NodeDescriptorRequest [");
        builder.append(super.toString());
        builder.append(", nwkAddrOfInterest=");
        builder.append(String.format("%04X", nwkAddrOfInterest));
        builder.append(']');
        return builder.toString();
    }

}
