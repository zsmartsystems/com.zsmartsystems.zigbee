/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Transfer Data Server To Client value object class.
 * <p>
 * Cluster: <b>Smart Energy Tunneling</b>. Command ID 0x01 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Smart Energy Tunneling cluster.
 * <p>
 * Command that transfers data from server to the client. The data itself has to be placed within
 * the payload.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class TransferDataServerToClient extends ZclSmartEnergyTunnelingCommand {
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
     * A number between 0..65535 that uniquely identifies the tunnel that has been allocated
     * in the server triggered through the RequestTunnel command. This ID must now be used to
     * send data through this tunnel (TunnelID, TransferData) and is also required to close
     * the tunnel again (CloseTunnel). If the command has failed, the TunnelStatus contains
     * the reason of the error and the TunnelID is set to 0xFFFF.
     */
    private Integer tunnelId;

    /**
     * Data command message field.
     * <p>
     * Octets containing the data to be transferred through the tunnel in the format of the
     * communication protocol for which the tunnel has been requested and opened. The payload
     * containing the assembled data exactly as it has been sent away by the client.
     * Theoretically, its length is solely limited through the fragmentation algorithm and
     * the RX/TX transfer buffer sizes within the communication partners. The content of the
     * payload is up to the application sending the data. It is not guaranteed that it contains a
     * complete PDU, nor is any assumption to be made on its internal format (which is left up to
     * the implementer of the specific tunnel protocol).
     */
    private ByteArray data;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public TransferDataServerToClient() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param tunnelId {@link Integer} Tunnel ID
     * @param data {@link ByteArray} Data
     */
    public TransferDataServerToClient(
            Integer tunnelId,
            ByteArray data) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.tunnelId = tunnelId;
        this.data = data;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTunnelId(final Integer tunnelId) {
        this.tunnelId = tunnelId;
    }

    /**
     * Gets Data.
     * <p>
     * Octets containing the data to be transferred through the tunnel in the format of the
     * communication protocol for which the tunnel has been requested and opened. The payload
     * containing the assembled data exactly as it has been sent away by the client.
     * Theoretically, its length is solely limited through the fragmentation algorithm and
     * the RX/TX transfer buffer sizes within the communication partners. The content of the
     * payload is up to the application sending the data. It is not guaranteed that it contains a
     * complete PDU, nor is any assumption to be made on its internal format (which is left up to
     * the implementer of the specific tunnel protocol).
     *
     * @return the Data
     */
    public ByteArray getData() {
        return data;
    }

    /**
     * Sets Data.
     * <p>
     * Octets containing the data to be transferred through the tunnel in the format of the
     * communication protocol for which the tunnel has been requested and opened. The payload
     * containing the assembled data exactly as it has been sent away by the client.
     * Theoretically, its length is solely limited through the fragmentation algorithm and
     * the RX/TX transfer buffer sizes within the communication partners. The content of the
     * payload is up to the application sending the data. It is not guaranteed that it contains a
     * complete PDU, nor is any assumption to be made on its internal format (which is left up to
     * the implementer of the specific tunnel protocol).
     *
     * @param data the Data
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setData(final ByteArray data) {
        this.data = data;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(tunnelId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(data, ZclDataType.OCTET_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        tunnelId = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        data = deserializer.deserialize(ZclDataType.OCTET_STRING);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(81);
        builder.append("TransferDataServerToClient [");
        builder.append(super.toString());
        builder.append(", tunnelId=");
        builder.append(tunnelId);
        builder.append(", data=");
        builder.append(data);
        builder.append(']');
        return builder.toString();
    }

}
