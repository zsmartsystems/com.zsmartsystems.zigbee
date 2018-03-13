/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

import java.util.List;

/**
 * Bypass Command value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * The IAS ACE cluster defines an interface to the functionality of any Ancillary
 * Control Equipment of the IAS system. Using this cluster, a ZigBee enabled ACE
 * device can access a IAS CIE device and manipulate the IAS system, on behalf of a
 * level-2 user.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class BypassCommand extends ZclCommand {
    /**
     * Number of Zones command message field.
     */
    private Integer numberOfZones;

    /**
     * Zone IDs command message field.
     */
    private List<Integer> zoneIDs;

    /**
     * Default constructor.
     */
    public BypassCommand() {
        genericCommand = false;
        clusterId = 1281;
        commandId = 1;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Number of Zones.
     *
     * @return the Number of Zones
     */
    public Integer getNumberOfZones() {
        return numberOfZones;
    }

    /**
     * Sets Number of Zones.
     *
     * @param numberOfZones the Number of Zones
     */
    public void setNumberOfZones(final Integer numberOfZones) {
        this.numberOfZones = numberOfZones;
    }

    /**
     * Gets Zone IDs.
     *
     * @return the Zone IDs
     */
    public List<Integer> getZoneIDs() {
        return zoneIDs;
    }

    /**
     * Sets Zone IDs.
     *
     * @param zoneIDs the Zone IDs
     */
    public void setZoneIDs(final List<Integer> zoneIDs) {
        this.zoneIDs = zoneIDs;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(numberOfZones, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(zoneIDs, ZclDataType.N_X_UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        numberOfZones = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        zoneIDs = (List<Integer>) deserializer.deserialize(ZclDataType.N_X_UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(76);
        builder.append("BypassCommand [");
        builder.append(super.toString());
        builder.append(", numberOfZones=");
        builder.append(numberOfZones);
        builder.append(", zoneIDs=");
        builder.append(zoneIDs);
        builder.append(']');
        return builder.toString();
    }

}
