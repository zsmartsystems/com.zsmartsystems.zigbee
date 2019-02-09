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
 * Request Mirror Response value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * The Request Mirror Response Command allows the ESI to inform a sleepy Metering Device it has
 * the ability to store and mirror its data.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class RequestMirrorResponse extends ZclCommand {
    /**
     * Endpoint ID command message field.
     * <p>
     * 16 Bit Unsigned Integer indicating the End Point ID to contain the Metering Devices
     * meter data. Valid End Point ID values are 0x0001 to 0x00F0. If the ESI is able to mirror the
     * Metering Device data, the low byte of the unsigned 16 bit integer shall be used to contain
     * the eight bit EndPoint ID. If the ESI is unable to mirror the Metering Device data,
     * EndPoint ID shall be returned as 0xFFFF. All other EndPoint ID values are reserved. If
     * valid, the Metering device shall use the EndPoint ID to forward its metered data.
     */
    private Integer endpointId;

    /**
     * Default constructor.
     */
    public RequestMirrorResponse() {
        genericCommand = false;
        clusterId = 0x0702;
        commandId = 1;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Endpoint ID.
     * <p>
     * 16 Bit Unsigned Integer indicating the End Point ID to contain the Metering Devices
     * meter data. Valid End Point ID values are 0x0001 to 0x00F0. If the ESI is able to mirror the
     * Metering Device data, the low byte of the unsigned 16 bit integer shall be used to contain
     * the eight bit EndPoint ID. If the ESI is unable to mirror the Metering Device data,
     * EndPoint ID shall be returned as 0xFFFF. All other EndPoint ID values are reserved. If
     * valid, the Metering device shall use the EndPoint ID to forward its metered data.
     *
     * @return the Endpoint ID
     */
    public Integer getEndpointId() {
        return endpointId;
    }

    /**
     * Sets Endpoint ID.
     * <p>
     * 16 Bit Unsigned Integer indicating the End Point ID to contain the Metering Devices
     * meter data. Valid End Point ID values are 0x0001 to 0x00F0. If the ESI is able to mirror the
     * Metering Device data, the low byte of the unsigned 16 bit integer shall be used to contain
     * the eight bit EndPoint ID. If the ESI is unable to mirror the Metering Device data,
     * EndPoint ID shall be returned as 0xFFFF. All other EndPoint ID values are reserved. If
     * valid, the Metering device shall use the EndPoint ID to forward its metered data.
     *
     * @param endpointId the Endpoint ID
     */
    public void setEndpointId(final Integer endpointId) {
        this.endpointId = endpointId;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(endpointId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        endpointId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(54);
        builder.append("RequestMirrorResponse [");
        builder.append(super.toString());
        builder.append(", endpointId=");
        builder.append(endpointId);
        builder.append(']');
        return builder.toString();
    }

}
