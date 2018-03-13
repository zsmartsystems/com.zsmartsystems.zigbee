/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.rssilocation;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.IeeeAddress;

/**
 * Request Own Location Command value object class.
 * <p>
 * Cluster: <b>RSSI Location</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the RSSI Location cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class RequestOwnLocationCommand extends ZclCommand {
    /**
     * Requesting Address command message field.
     */
    private IeeeAddress requestingAddress;

    /**
     * Default constructor.
     */
    public RequestOwnLocationCommand() {
        genericCommand = false;
        clusterId = 11;
        commandId = 7;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
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
     */
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
