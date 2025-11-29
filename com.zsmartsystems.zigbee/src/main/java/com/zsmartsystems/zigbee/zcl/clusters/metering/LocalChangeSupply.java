/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.metering;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Local Change Supply value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command ID 0x0C is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * This command is a simplified version of the ChangeSupply command, intended to be sent from an
 * IHD to a meter as the consequence of a user action on the IHD. Its purpose is to provide a local
 * disconnection/reconnection button on the IHD in addition to the one on the meter.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class LocalChangeSupply extends ZclMeteringCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0702;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x0C;

    /**
     * Proposed Supply Status command message field.
     * <p>
     * An 8-bit enumeration field indicating the status of the energy supply controlled by the
     * Metering Device following implementation of this command.
     */
    private Integer proposedSupplyStatus;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public LocalChangeSupply() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param proposedSupplyStatus {@link Integer} Proposed Supply Status
     */
    public LocalChangeSupply(
            Integer proposedSupplyStatus) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.proposedSupplyStatus = proposedSupplyStatus;
    }

    /**
     * Gets Proposed Supply Status.
     * <p>
     * An 8-bit enumeration field indicating the status of the energy supply controlled by the
     * Metering Device following implementation of this command.
     *
     * @return the Proposed Supply Status
     */
    public Integer getProposedSupplyStatus() {
        return proposedSupplyStatus;
    }

    /**
     * Sets Proposed Supply Status.
     * <p>
     * An 8-bit enumeration field indicating the status of the energy supply controlled by the
     * Metering Device following implementation of this command.
     *
     * @param proposedSupplyStatus the Proposed Supply Status
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setProposedSupplyStatus(final Integer proposedSupplyStatus) {
        this.proposedSupplyStatus = proposedSupplyStatus;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(proposedSupplyStatus, ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        proposedSupplyStatus = deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(60);
        builder.append("LocalChangeSupply [");
        builder.append(super.toString());
        builder.append(", proposedSupplyStatus=");
        builder.append(proposedSupplyStatus);
        builder.append(']');
        return builder.toString();
    }

}
