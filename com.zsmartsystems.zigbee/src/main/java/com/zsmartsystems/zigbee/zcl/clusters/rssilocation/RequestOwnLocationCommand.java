/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.rssilocation;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Request Own Location Command value object class.
 * <p>
 * Cluster: <b>RSSI Location</b>. Command ID 0x07 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the RSSI Location cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class RequestOwnLocationCommand extends ZclRssiLocationCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x000B;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x07;

    /**
     * Requesting Address command message field.
     */
    private IeeeAddress requestingAddress;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public RequestOwnLocationCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param requestingAddress {@link IeeeAddress} Requesting Address
     */
    public RequestOwnLocationCommand(
            IeeeAddress requestingAddress) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.requestingAddress = requestingAddress;
    }

    /**
     * Gets Requesting Address.
     *
     * @return the Requesting Address
     */
    public IeeeAddress getRequestingAddress() {
        return requestingAddress;
    }

    /**
     * Sets Requesting Address.
     *
     * @param requestingAddress the Requesting Address
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setRequestingAddress(final IeeeAddress requestingAddress) {
        this.requestingAddress = requestingAddress;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(requestingAddress, ZclDataType.IEEE_ADDRESS);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        requestingAddress = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(65);
        builder.append("RequestOwnLocationCommand [");
        builder.append(super.toString());
        builder.append(", requestingAddress=");
        builder.append(requestingAddress);
        builder.append(']');
        return builder.toString();
    }

}
