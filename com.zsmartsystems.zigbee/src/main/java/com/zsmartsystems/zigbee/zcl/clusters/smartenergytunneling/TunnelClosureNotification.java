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
 * Tunnel Closure Notification value object class.
 * <p>
 * Cluster: <b>Smart Energy Tunneling</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Smart Energy Tunneling cluster.
 * <p>
 * TunnelClosureNotification is sent by the server to indicate that a tunnel has been closed
 * due to expiration of a CloseTunnelTimeout.
 * <p>
 * The command is sent by a server when a tunnel is closed due to expiration of
 * CloseTunnelTimeout. It is sent unicast to the client that had originally requested that
 * tunnel.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class TunnelClosureNotification extends ZclCommand {
    /**
     * Tunnel ID command message field.
     * <p>
     * The identifier of the tunnel that has been closed. It is the same number that has been
     * previously returned in the response to a RequestTunnel command. Valid numbers range
     * between 0..65535 and must correspond to a tunnel that was still active and maintained by
     * the server.
     */
    private Integer tunnelId;

    /**
     * Default constructor.
     */
    public TunnelClosureNotification() {
        genericCommand = false;
        clusterId = 0x0704;
        commandId = 6;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Tunnel ID.
     * <p>
     * The identifier of the tunnel that has been closed. It is the same number that has been
     * previously returned in the response to a RequestTunnel command. Valid numbers range
     * between 0..65535 and must correspond to a tunnel that was still active and maintained by
     * the server.
     *
     * @return the Tunnel ID
     */
    public Integer getTunnelId() {
        return tunnelId;
    }

    /**
     * Sets Tunnel ID.
     * <p>
     * The identifier of the tunnel that has been closed. It is the same number that has been
     * previously returned in the response to a RequestTunnel command. Valid numbers range
     * between 0..65535 and must correspond to a tunnel that was still active and maintained by
     * the server.
     *
     * @param tunnelId the Tunnel ID
     */
    public void setTunnelId(final Integer tunnelId) {
        this.tunnelId = tunnelId;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(tunnelId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        tunnelId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(56);
        builder.append("TunnelClosureNotification [");
        builder.append(super.toString());
        builder.append(", tunnelId=");
        builder.append(tunnelId);
        builder.append(']');
        return builder.toString();
    }

}
