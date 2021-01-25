/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Close Tunnel value object class.
 * <p>
 * Cluster: <b>Smart Energy Tunneling</b>. Command ID 0x01 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Smart Energy Tunneling cluster.
 * <p>
 * Client command used to close the tunnel with the server. The parameter in the payload
 * specifies the tunnel identifier of the tunnel that has to be closed. The server leaves the
 * tunnel open and the assigned resources allocated until the client sends the CloseTunnel
 * command or the CloseTunnelTimeout fires.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class CloseTunnel extends ZclSmartEnergyTunnelingCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0704;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x01;

    /**
     * Tunnel ID command message field.
     * <p>
     * The identifier of the tunnel that shall be closed. It is the same number that has been
     * previously returned in the response to a RequestTunnel command. Valid numbers range
     * between 0..65535 and must correspond to a tunnel that is still active and maintained by
     * the server.
     */
    private Integer tunnelId;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public CloseTunnel() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param tunnelId {@link Integer} Tunnel ID
     */
    public CloseTunnel(
            Integer tunnelId) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.tunnelId = tunnelId;
    }

    /**
     * Gets Tunnel ID.
     * <p>
     * The identifier of the tunnel that shall be closed. It is the same number that has been
     * previously returned in the response to a RequestTunnel command. Valid numbers range
     * between 0..65535 and must correspond to a tunnel that is still active and maintained by
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
     * The identifier of the tunnel that shall be closed. It is the same number that has been
     * previously returned in the response to a RequestTunnel command. Valid numbers range
     * between 0..65535 and must correspond to a tunnel that is still active and maintained by
     * the server.
     *
     * @param tunnelId the Tunnel ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
        final StringBuilder builder = new StringBuilder(42);
        builder.append("CloseTunnel [");
        builder.append(super.toString());
        builder.append(", tunnelId=");
        builder.append(tunnelId);
        builder.append(']');
        return builder.toString();
    }

}
