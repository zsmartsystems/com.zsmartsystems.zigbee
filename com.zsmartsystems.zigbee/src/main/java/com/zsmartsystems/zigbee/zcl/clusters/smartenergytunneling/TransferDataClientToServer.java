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
 * Transfer Data Client To Server value object class.
 * <p>
 * Cluster: <b>Smart Energy Tunneling</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Smart Energy Tunneling cluster.
 * <p>
 * Command that indicates (if received) that the client has sent data to the server. The data
 * itself is contained within the payload.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class TransferDataClientToServer extends ZclCommand {
    /**
     * Tunnel ID command message field.
     * <p>
     * A number between 0..65535 that uniquely identifies the tunnel that has been allocated
     * in the server triggered through the RequestTunnel command. This ID must be used to send
     * data through the tunnel or passed with any commands concerning that specific tunnel.
     */
    private Integer tunnelId;

    /**
     * Data command message field.
     * <p>
     * Octet containing the data to be transferred through the tunnel in the format of the
     * communication protocol for which the tunnel has been requested and opened. The payload
     * contains the assembled data exactly as it was sent by the client. Theoretically, its
     * length is solely limited through the fragmentation algorithm and the RX/TX transfer
     * buffer sizes within the communication partners. The content of the payload is up to the
     * application sending the data. It is neither guaranteed, that it contains a complete PDU
     * nor is any other assumption on its internal format made. This is left up to the
     * implementer of the specific protocol tunnel behavior.
     */
    private Integer data;

    /**
     * Default constructor.
     */
    public TransferDataClientToServer() {
        genericCommand = false;
        clusterId = 0x0704;
        commandId = 2;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Tunnel ID.
     * <p>
     * A number between 0..65535 that uniquely identifies the tunnel that has been allocated
     * in the server triggered through the RequestTunnel command. This ID must be used to send
     * data through the tunnel or passed with any commands concerning that specific tunnel.
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
     * in the server triggered through the RequestTunnel command. This ID must be used to send
     * data through the tunnel or passed with any commands concerning that specific tunnel.
     *
     * @param tunnelId the Tunnel ID
     */
    public void setTunnelId(final Integer tunnelId) {
        this.tunnelId = tunnelId;
    }

    /**
     * Gets Data.
     * <p>
     * Octet containing the data to be transferred through the tunnel in the format of the
     * communication protocol for which the tunnel has been requested and opened. The payload
     * contains the assembled data exactly as it was sent by the client. Theoretically, its
     * length is solely limited through the fragmentation algorithm and the RX/TX transfer
     * buffer sizes within the communication partners. The content of the payload is up to the
     * application sending the data. It is neither guaranteed, that it contains a complete PDU
     * nor is any other assumption on its internal format made. This is left up to the
     * implementer of the specific protocol tunnel behavior.
     *
     * @return the Data
     */
    public Integer getData() {
        return data;
    }

    /**
     * Sets Data.
     * <p>
     * Octet containing the data to be transferred through the tunnel in the format of the
     * communication protocol for which the tunnel has been requested and opened. The payload
     * contains the assembled data exactly as it was sent by the client. Theoretically, its
     * length is solely limited through the fragmentation algorithm and the RX/TX transfer
     * buffer sizes within the communication partners. The content of the payload is up to the
     * application sending the data. It is neither guaranteed, that it contains a complete PDU
     * nor is any other assumption on its internal format made. This is left up to the
     * implementer of the specific protocol tunnel behavior.
     *
     * @param data the Data
     */
    public void setData(final Integer data) {
        this.data = data;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(tunnelId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(data, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        tunnelId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        data = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(81);
        builder.append("TransferDataClientToServer [");
        builder.append(super.toString());
        builder.append(", tunnelId=");
        builder.append(tunnelId);
        builder.append(", data=");
        builder.append(data);
        builder.append(']');
        return builder.toString();
    }

}
