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
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Ack Transfer Data Client To Server value object class.
 * <p>
 * Cluster: <b>Smart Energy Tunneling</b>. Command ID 0x04 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Smart Energy Tunneling cluster.
 * <p>
 * Command sent in response to each TransferData command in case - and only in case - flow control
 * has been requested by the client in the TunnelRequest command and is supported by both tunnel
 * endpoints. The response payload indicates the number of octets that may still be received by
 * the receiver.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class AckTransferDataClientToServer extends ZclSmartEnergyTunnelingCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0704;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x04;

    /**
     * Tunnel ID command message field.
     * <p>
     * A number between 0..65535 that uniquely identifies the tunnel that has been allocated
     * in the server triggered through the RequestTunnel command. This ID must be used for the
     * data transfer through the tunnel or passed with any commands concerning that specific
     * tunnel.
     */
    private Integer tunnelId;

    /**
     * Number Of Bytes Left command message field.
     * <p>
     * Indicates the number of bytes that may still be received by the initiator of this command
     * (receiver). It is most likely the remaining size of the buffer holding the data that is
     * sent over TransferData. As an example: A value of 150 indicates that the next
     * TransferData command must not contain more than 150 bytes of payload or data will get
     * lost. A value of 0 indicates that there is no more space left in the receiver and the sender
     * should completely stop sending data. After the reception of a ReadyData command, the
     * sender may continue its data transfer.
     */
    private Integer numberOfBytesLeft;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public AckTransferDataClientToServer() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param tunnelId {@link Integer} Tunnel ID
     * @param numberOfBytesLeft {@link Integer} Number Of Bytes Left
     */
    public AckTransferDataClientToServer(
            Integer tunnelId,
            Integer numberOfBytesLeft) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.tunnelId = tunnelId;
        this.numberOfBytesLeft = numberOfBytesLeft;
    }

    /**
     * Gets Tunnel ID.
     * <p>
     * A number between 0..65535 that uniquely identifies the tunnel that has been allocated
     * in the server triggered through the RequestTunnel command. This ID must be used for the
     * data transfer through the tunnel or passed with any commands concerning that specific
     * tunnel.
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
     * in the server triggered through the RequestTunnel command. This ID must be used for the
     * data transfer through the tunnel or passed with any commands concerning that specific
     * tunnel.
     *
     * @param tunnelId the Tunnel ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTunnelId(final Integer tunnelId) {
        this.tunnelId = tunnelId;
    }

    /**
     * Gets Number Of Bytes Left.
     * <p>
     * Indicates the number of bytes that may still be received by the initiator of this command
     * (receiver). It is most likely the remaining size of the buffer holding the data that is
     * sent over TransferData. As an example: A value of 150 indicates that the next
     * TransferData command must not contain more than 150 bytes of payload or data will get
     * lost. A value of 0 indicates that there is no more space left in the receiver and the sender
     * should completely stop sending data. After the reception of a ReadyData command, the
     * sender may continue its data transfer.
     *
     * @return the Number Of Bytes Left
     */
    public Integer getNumberOfBytesLeft() {
        return numberOfBytesLeft;
    }

    /**
     * Sets Number Of Bytes Left.
     * <p>
     * Indicates the number of bytes that may still be received by the initiator of this command
     * (receiver). It is most likely the remaining size of the buffer holding the data that is
     * sent over TransferData. As an example: A value of 150 indicates that the next
     * TransferData command must not contain more than 150 bytes of payload or data will get
     * lost. A value of 0 indicates that there is no more space left in the receiver and the sender
     * should completely stop sending data. After the reception of a ReadyData command, the
     * sender may continue its data transfer.
     *
     * @param numberOfBytesLeft the Number Of Bytes Left
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setNumberOfBytesLeft(final Integer numberOfBytesLeft) {
        this.numberOfBytesLeft = numberOfBytesLeft;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(tunnelId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(numberOfBytesLeft, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        tunnelId = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        numberOfBytesLeft = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(97);
        builder.append("AckTransferDataClientToServer [");
        builder.append(super.toString());
        builder.append(", tunnelId=");
        builder.append(tunnelId);
        builder.append(", numberOfBytesLeft=");
        builder.append(numberOfBytesLeft);
        builder.append(']');
        return builder.toString();
    }

}
