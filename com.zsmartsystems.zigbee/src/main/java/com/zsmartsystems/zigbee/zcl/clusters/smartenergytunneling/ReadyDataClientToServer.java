/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
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
 * Ready Data Client To Server value object class.
 * <p>
 * Cluster: <b>Smart Energy Tunneling</b>. Command ID 0x05 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Smart Energy Tunneling cluster.
 * <p>
 * The ReadyData command is generated - after a receiver had to stop the dataflow using the
 * AckTransferData(0) command - to indicate that the device is now ready to continue receiving
 * data. The parameter NumberOfOctetsLeft gives a hint on how much space is left for the next
 * data transfer. The ReadyData command is only issued if flow control is enabled.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class ReadyDataClientToServer extends ZclSmartEnergyTunnelingCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0704;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x05;

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
     * Number Of Octets Left command message field.
     * <p>
     * Indicates the number of octets that may be received by the initiator of this command
     * (receiver). It is most likely the remaining size of the buffer holding the data that is
     * sent over TransferData. As an example: A value of 150 indicates that the next
     * TransferData command must not contain more than 150 bytes of payload or data will get
     * lost. The value must be larger than 0. As for its exact value, it is up to the implementer of
     * the cluster to decide what flow control algorithm shall be applied.
     */
    private Integer numberOfOctetsLeft;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public ReadyDataClientToServer() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param tunnelId {@link Integer} Tunnel ID
     * @param numberOfOctetsLeft {@link Integer} Number Of Octets Left
     */
    public ReadyDataClientToServer(
            Integer tunnelId,
            Integer numberOfOctetsLeft) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.tunnelId = tunnelId;
        this.numberOfOctetsLeft = numberOfOctetsLeft;
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
     * Gets Number Of Octets Left.
     * <p>
     * Indicates the number of octets that may be received by the initiator of this command
     * (receiver). It is most likely the remaining size of the buffer holding the data that is
     * sent over TransferData. As an example: A value of 150 indicates that the next
     * TransferData command must not contain more than 150 bytes of payload or data will get
     * lost. The value must be larger than 0. As for its exact value, it is up to the implementer of
     * the cluster to decide what flow control algorithm shall be applied.
     *
     * @return the Number Of Octets Left
     */
    public Integer getNumberOfOctetsLeft() {
        return numberOfOctetsLeft;
    }

    /**
     * Sets Number Of Octets Left.
     * <p>
     * Indicates the number of octets that may be received by the initiator of this command
     * (receiver). It is most likely the remaining size of the buffer holding the data that is
     * sent over TransferData. As an example: A value of 150 indicates that the next
     * TransferData command must not contain more than 150 bytes of payload or data will get
     * lost. The value must be larger than 0. As for its exact value, it is up to the implementer of
     * the cluster to decide what flow control algorithm shall be applied.
     *
     * @param numberOfOctetsLeft the Number Of Octets Left
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setNumberOfOctetsLeft(final Integer numberOfOctetsLeft) {
        this.numberOfOctetsLeft = numberOfOctetsLeft;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(tunnelId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(numberOfOctetsLeft, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        tunnelId = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        numberOfOctetsLeft = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(92);
        builder.append("ReadyDataClientToServer [");
        builder.append(super.toString());
        builder.append(", tunnelId=");
        builder.append(tunnelId);
        builder.append(", numberOfOctetsLeft=");
        builder.append(numberOfOctetsLeft);
        builder.append(']');
        return builder.toString();
    }

}
