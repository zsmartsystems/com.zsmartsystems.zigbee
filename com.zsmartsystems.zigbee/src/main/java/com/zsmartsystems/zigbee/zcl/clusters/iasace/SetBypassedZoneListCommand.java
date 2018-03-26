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
 * Set Bypassed Zone List Command value object class.
 * <p>
 * Sets the list of bypassed zoneson the IAS ACE client. This command can be sent either as a response to the
 * GetBypassedZoneList command or unsolicited when the list of bypassed zones changes on the ACE server.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * The IAS ACE cluster defines an interface to the functionality of any Ancillary
 * Control Equipment of the IAS system. Using this cluster, a ZigBee enabled ACE
 * device can access a IAS CIE device and manipulate the IAS system, on behalf of a
 * level-2 user.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-26T17:34:00Z")
public class SetBypassedZoneListCommand extends ZclCommand {
    /**
     * Zone ID command message field.
     * <p>
     * Zone ID is the index of the Zone in the CIE's zone table and is an array of Zone IDs for each zone that is bypassed
     * where X is equal to the value of the Number of Zones field. There is no order imposed by the numbering of the Zone ID
     * field in this command payload. IAS ACE servers SHOULD provide the array of Zone IDs in ascending order.
     */
    private List<Integer> zoneId;

    /**
     * Default constructor.
     */
    public SetBypassedZoneListCommand() {
        genericCommand = false;
        clusterId = 1281;
        commandId = 6;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Zone ID.
     *
     * Zone ID is the index of the Zone in the CIE's zone table and is an array of Zone IDs for each zone that is bypassed
     * where X is equal to the value of the Number of Zones field. There is no order imposed by the numbering of the Zone ID
     * field in this command payload. IAS ACE servers SHOULD provide the array of Zone IDs in ascending order.
     *
     * @return the Zone ID
     */
    public List<Integer> getZoneId() {
        return zoneId;
    }

    /**
     * Sets Zone ID.
     *
     * Zone ID is the index of the Zone in the CIE's zone table and is an array of Zone IDs for each zone that is bypassed
     * where X is equal to the value of the Number of Zones field. There is no order imposed by the numbering of the Zone ID
     * field in this command payload. IAS ACE servers SHOULD provide the array of Zone IDs in ascending order.
     *
     * @param zoneId the Zone ID
     */
    public void setZoneId(final List<Integer> zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(zoneId, ZclDataType.N_X_UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        zoneId = (List<Integer>) deserializer.deserialize(ZclDataType.N_X_UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(55);
        builder.append("SetBypassedZoneListCommand [");
        builder.append(super.toString());
        builder.append(", zoneId=");
        builder.append(zoneId);
        builder.append(']');
        return builder.toString();
    }

}
