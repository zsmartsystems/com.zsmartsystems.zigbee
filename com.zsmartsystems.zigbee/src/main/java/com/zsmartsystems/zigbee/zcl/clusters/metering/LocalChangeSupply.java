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
 * Local Change Supply value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * This command is a simplified version of the ChangeSupply command, intended to be sent from an
 * IHD to a meter as the consequence of a user action on the IHD. Its purpose is to provide a local
 * disconnection/reconnection button on the IHD in addition to the one on the meter.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class LocalChangeSupply extends ZclCommand {
    /**
     * Proposed Supply Status command message field.
     * <p>
     * An 8-bit enumeration field indicating the status of the energy supply controlled by the
     * Metering Device following implementation of this command.
     */
    private Integer proposedSupplyStatus;

    /**
     * Default constructor.
     */
    public LocalChangeSupply() {
        genericCommand = false;
        clusterId = 0x0702;
        commandId = 12;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
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
     */
    public void setProposedSupplyStatus(final Integer proposedSupplyStatus) {
        this.proposedSupplyStatus = proposedSupplyStatus;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(proposedSupplyStatus, ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        proposedSupplyStatus = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
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
