/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Supported Tunnel Protocols value object class.
 * <p>
 * Cluster: <b>Smart Energy Tunneling</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Smart Energy Tunneling cluster.
 * <p>
 * Get Supported Tunnel Protocols is the client command used to determine the Tunnel protocols
 * supported on another device.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetSupportedTunnelProtocols extends ZclCommand {
    /**
     * Protocol Offset command message field.
     * <p>
     * Where there are more protocols supported than can be returned in a single Supported
     * Tunnel Protocols Response command, this field allows an offset to be specified on
     * subsequent Get Supported Tunnel Protocols commands. An offset of zero (0x00) should be
     * used for an initial (or only) Get Supported Tunnel Protocols command (indicating that
     * the returned list of protocols should commence with first available protocol). As a
     * further example, if 10 protocols had previously been returned, the next Get Supported
     * Tunnel Protocols command should use an offset of 10 (0x0A) to indicate the 11th
     * available protocol should be the first returned in the next response.
     */
    private Integer protocolOffset;

    /**
     * Default constructor.
     */
    public GetSupportedTunnelProtocols() {
        genericCommand = false;
        clusterId = 0x0704;
        commandId = 6;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Protocol Offset.
     * <p>
     * Where there are more protocols supported than can be returned in a single Supported
     * Tunnel Protocols Response command, this field allows an offset to be specified on
     * subsequent Get Supported Tunnel Protocols commands. An offset of zero (0x00) should be
     * used for an initial (or only) Get Supported Tunnel Protocols command (indicating that
     * the returned list of protocols should commence with first available protocol). As a
     * further example, if 10 protocols had previously been returned, the next Get Supported
     * Tunnel Protocols command should use an offset of 10 (0x0A) to indicate the 11th
     * available protocol should be the first returned in the next response.
     *
     * @return the Protocol Offset
     */
    public Integer getProtocolOffset() {
        return protocolOffset;
    }

    /**
     * Sets Protocol Offset.
     * <p>
     * Where there are more protocols supported than can be returned in a single Supported
     * Tunnel Protocols Response command, this field allows an offset to be specified on
     * subsequent Get Supported Tunnel Protocols commands. An offset of zero (0x00) should be
     * used for an initial (or only) Get Supported Tunnel Protocols command (indicating that
     * the returned list of protocols should commence with first available protocol). As a
     * further example, if 10 protocols had previously been returned, the next Get Supported
     * Tunnel Protocols command should use an offset of 10 (0x0A) to indicate the 11th
     * available protocol should be the first returned in the next response.
     *
     * @param protocolOffset the Protocol Offset
     */
    public void setProtocolOffset(final Integer protocolOffset) {
        this.protocolOffset = protocolOffset;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(protocolOffset, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        protocolOffset = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(64);
        builder.append("GetSupportedTunnelProtocols [");
        builder.append(super.toString());
        builder.append(", protocolOffset=");
        builder.append(protocolOffset);
        builder.append(']');
        return builder.toString();
    }

}
