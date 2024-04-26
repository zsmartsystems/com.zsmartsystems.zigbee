/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.metering;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Mirror Removed value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command ID 0x02 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * The Mirror Removed Command allows the ESI to inform a sleepy Metering Device mirroring
 * support has been removed or halted.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class MirrorRemoved extends ZclMeteringCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0702;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x02;

    /**
     * Removed Endpoint ID command message field.
     * <p>
     * 16 Bit Unsigned Integer indicating the End Point ID previously containing the Metering
     * Devices meter data.
     */
    private Integer removedEndpointId;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public MirrorRemoved() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param removedEndpointId {@link Integer} Removed Endpoint ID
     */
    public MirrorRemoved(
            Integer removedEndpointId) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.removedEndpointId = removedEndpointId;
    }

    /**
     * Gets Removed Endpoint ID.
     * <p>
     * 16 Bit Unsigned Integer indicating the End Point ID previously containing the Metering
     * Devices meter data.
     *
     * @return the Removed Endpoint ID
     */
    public Integer getRemovedEndpointId() {
        return removedEndpointId;
    }

    /**
     * Sets Removed Endpoint ID.
     * <p>
     * 16 Bit Unsigned Integer indicating the End Point ID previously containing the Metering
     * Devices meter data.
     *
     * @param removedEndpointId the Removed Endpoint ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setRemovedEndpointId(final Integer removedEndpointId) {
        this.removedEndpointId = removedEndpointId;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(removedEndpointId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        removedEndpointId = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(53);
        builder.append("MirrorRemoved [");
        builder.append(super.toString());
        builder.append(", removedEndpointId=");
        builder.append(removedEndpointId);
        builder.append(']');
        return builder.toString();
    }

}
