/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.metering;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Mirror Removed value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * The Mirror Removed Command allows the ESI to inform a sleepy Metering Device mirroring
 * support has been removed or halted.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class MirrorRemoved extends ZclCommand {
    /**
     * Removed Endpoint ID command message field.
     * <p>
     * 16 Bit Unsigned Integer indicating the End Point ID previously containing the Metering
     * Devices meter data.
     */
    private Integer removedEndpointId;

    /**
     * Default constructor.
     */
    public MirrorRemoved() {
        genericCommand = false;
        clusterId = 0x0702;
        commandId = 2;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
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
     */
    public void setRemovedEndpointId(final Integer removedEndpointId) {
        this.removedEndpointId = removedEndpointId;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(removedEndpointId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        removedEndpointId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
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
