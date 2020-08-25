/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.zdo.command.ManagementLeaveResponse;

/**
 * Management Leave Request value object class.
 * <p>
 * <p>
 * The Mgmt_Leave_req is generated from a Local Device requesting that a Remote Device leave
 * the network or to request that another device leave the network. The Mgmt_Leave_req is
 * generated by a management application which directs the request to a Remote Device where the
 * NLME-LEAVE.request is to be executed using the parameter supplied by Mgmt_Leave_req.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-08-25T13:51:20Z")
public class ManagementLeaveRequest extends ZdoRequest implements ZigBeeTransactionMatcher {
    /**
     * The ZDO cluster ID.
     */
    public static int CLUSTER_ID = 0x0034;

    /**
     * Device Address command message field.
     */
    private IeeeAddress deviceAddress;

    /**
     * Remove Children_Rejoin command message field.
     */
    private Boolean removeChildrenRejoin;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.
     */
    @Deprecated
    public ManagementLeaveRequest() {
        clusterId = CLUSTER_ID;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param deviceAddress {@link IeeeAddress} Device Address
     * @param removeChildrenRejoin {@link Boolean} Remove Children_Rejoin
     */
    public ManagementLeaveRequest(
            IeeeAddress deviceAddress,
            Boolean removeChildrenRejoin) {

        clusterId = CLUSTER_ID;

        this.deviceAddress = deviceAddress;
        this.removeChildrenRejoin = removeChildrenRejoin;
    }

    /**
     * Gets Device Address.
     *
     * @return the Device Address
     */
    public IeeeAddress getDeviceAddress() {
        return deviceAddress;
    }

    /**
     * Sets Device Address.
     *
     * @param deviceAddress the Device Address
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setDeviceAddress(final IeeeAddress deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    /**
     * Gets Remove Children_Rejoin.
     *
     * @return the Remove Children_Rejoin
     */
    public Boolean getRemoveChildrenRejoin() {
        return removeChildrenRejoin;
    }

    /**
     * Sets Remove Children_Rejoin.
     *
     * @param removeChildrenRejoin the Remove Children_Rejoin
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setRemoveChildrenRejoin(final Boolean removeChildrenRejoin) {
        this.removeChildrenRejoin = removeChildrenRejoin;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(deviceAddress, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(removeChildrenRejoin, ZclDataType.BOOLEAN);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        deviceAddress = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        removeChildrenRejoin = (Boolean) deserializer.deserialize(ZclDataType.BOOLEAN);
    }

    @Override
    public boolean isTransactionMatch(ZigBeeCommand request, ZigBeeCommand response) {
        return (response instanceof ManagementLeaveResponse)
                && ((ZdoRequest) request).getDestinationAddress().equals(((ManagementLeaveResponse) response).getSourceAddress());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(98);
        builder.append("ManagementLeaveRequest [");
        builder.append(super.toString());
        builder.append(", deviceAddress=");
        builder.append(deviceAddress);
        builder.append(", removeChildrenRejoin=");
        builder.append(removeChildrenRejoin);
        builder.append(']');
        return builder.toString();
    }

}
