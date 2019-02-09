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
 * Request Tunnel Response value object class.
 * <p>
 * Cluster: <b>Smart Energy Tunneling</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Smart Energy Tunneling cluster.
 * <p>
 * RequestTunnelResponse is sent by the server in response to a RequestTunnel command
 * previously received from the client. The response contains the status of the RequestTunnel
 * command and a tunnel identifier corresponding to the tunnel that has been set-up in the
 * server in case of success.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class RequestTunnelResponse extends ZclCommand {
    /**
     * Tunnel ID command message field.
     * <p>
     * A number between 0..65535 that uniquely identifies the tunnel that has been allocated
     * in the server triggered through the RequestTunnel command. This ID must now be used to
     * send data through this tunnel (TunnelID, TransferData) and is also required to close
     * the tunnel again (CloseTunnel). If the command has failed, the TunnelStatus contains
     * the reason of the error and the TunnelID is set to 0xFFFF.
     */
    private Integer tunnelId;

    /**
     * Tunnel Status command message field.
     * <p>
     * The TunnelStatus parameter indicates the server’s internal status after the
     * execution of a RequestTunnel command.
     */
    private Integer tunnelStatus;

    /**
     * Maximum Incoming Transfer Size command message field.
     * <p>
     * A value that defines the size, in octets, of the maximum data packet that can be
     * transferred to the server in the payload of a single TransferData command.
     */
    private Integer maximumIncomingTransferSize;

    /**
     * Default constructor.
     */
    public RequestTunnelResponse() {
        genericCommand = false;
        clusterId = 0x0704;
        commandId = 0;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Tunnel ID.
     * <p>
     * A number between 0..65535 that uniquely identifies the tunnel that has been allocated
     * in the server triggered through the RequestTunnel command. This ID must now be used to
     * send data through this tunnel (TunnelID, TransferData) and is also required to close
     * the tunnel again (CloseTunnel). If the command has failed, the TunnelStatus contains
     * the reason of the error and the TunnelID is set to 0xFFFF.
     *
     * @return the Tunnel ID
     */
    public Integer getTunnelId() {
        return tunnelId;
    }

    /**
     * Sets Tunnel ID.
     * <p>
     * A number between 0..65535 that uniquely identifies the tunnel that has been allocated
     * in the server triggered through the RequestTunnel command. This ID must now be used to
     * send data through this tunnel (TunnelID, TransferData) and is also required to close
     * the tunnel again (CloseTunnel). If the command has failed, the TunnelStatus contains
     * the reason of the error and the TunnelID is set to 0xFFFF.
     *
     * @param tunnelId the Tunnel ID
     */
    public void setTunnelId(final Integer tunnelId) {
        this.tunnelId = tunnelId;
    }

    /**
     * Gets Tunnel Status.
     * <p>
     * The TunnelStatus parameter indicates the server’s internal status after the
     * execution of a RequestTunnel command.
     *
     * @return the Tunnel Status
     */
    public Integer getTunnelStatus() {
        return tunnelStatus;
    }

    /**
     * Sets Tunnel Status.
     * <p>
     * The TunnelStatus parameter indicates the server’s internal status after the
     * execution of a RequestTunnel command.
     *
     * @param tunnelStatus the Tunnel Status
     */
    public void setTunnelStatus(final Integer tunnelStatus) {
        this.tunnelStatus = tunnelStatus;
    }

    /**
     * Gets Maximum Incoming Transfer Size.
     * <p>
     * A value that defines the size, in octets, of the maximum data packet that can be
     * transferred to the server in the payload of a single TransferData command.
     *
     * @return the Maximum Incoming Transfer Size
     */
    public Integer getMaximumIncomingTransferSize() {
        return maximumIncomingTransferSize;
    }

    /**
     * Sets Maximum Incoming Transfer Size.
     * <p>
     * A value that defines the size, in octets, of the maximum data packet that can be
     * transferred to the server in the payload of a single TransferData command.
     *
     * @param maximumIncomingTransferSize the Maximum Incoming Transfer Size
     */
    public void setMaximumIncomingTransferSize(final Integer maximumIncomingTransferSize) {
        this.maximumIncomingTransferSize = maximumIncomingTransferSize;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(tunnelId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(tunnelStatus, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(maximumIncomingTransferSize, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        tunnelId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        tunnelStatus = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        maximumIncomingTransferSize = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(131);
        builder.append("RequestTunnelResponse [");
        builder.append(super.toString());
        builder.append(", tunnelId=");
        builder.append(tunnelId);
        builder.append(", tunnelStatus=");
        builder.append(tunnelStatus);
        builder.append(", maximumIncomingTransferSize=");
        builder.append(maximumIncomingTransferSize);
        builder.append(']');
        return builder.toString();
    }

}
