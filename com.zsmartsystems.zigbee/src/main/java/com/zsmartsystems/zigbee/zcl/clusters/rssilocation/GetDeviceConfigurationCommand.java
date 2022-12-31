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
 * Get Device Configuration Command value object class.
 * <p>
 * Cluster: <b>RSSI Location</b>. Command ID 0x02 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the RSSI Location cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class GetDeviceConfigurationCommand extends ZclRssiLocationCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x000B;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x02;

    /**
     * Target Address command message field.
     */
    private IeeeAddress targetAddress;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GetDeviceConfigurationCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param targetAddress {@link IeeeAddress} Target Address
     */
    public GetDeviceConfigurationCommand(
            IeeeAddress targetAddress) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.targetAddress = targetAddress;
    }

    /**
     * Gets Target Address.
     *
     * @return the Target Address
     */
    public IeeeAddress getTargetAddress() {
        return targetAddress;
    }

    /**
     * Sets Target Address.
     *
     * @param targetAddress the Target Address
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTargetAddress(final IeeeAddress targetAddress) {
        this.targetAddress = targetAddress;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(targetAddress, ZclDataType.IEEE_ADDRESS);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        targetAddress = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(65);
        builder.append("GetDeviceConfigurationCommand [");
        builder.append(super.toString());
        builder.append(", targetAddress=");
        builder.append(targetAddress);
        builder.append(']');
        return builder.toString();
    }

}
