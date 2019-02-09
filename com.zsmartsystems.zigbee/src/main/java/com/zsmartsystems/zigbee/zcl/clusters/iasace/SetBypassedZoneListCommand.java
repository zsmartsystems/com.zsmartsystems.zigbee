/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import java.util.List;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Set Bypassed Zone List Command value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * Sets the list of bypassed zones on the IAS ACE client. This command can be sent either as a
 * response to the GetBypassedZoneList command or unsolicited when the list of bypassed zones
 * changes on the ACE server.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T19:19:25Z")
public class SetBypassedZoneListCommand extends ZclCommand {
    /**
     * Zone ID command message field.
     * <p>
     * Zone ID is the index of the Zone in the CIE's zone table and is an array of Zone IDs for each
     * zone that is bypassed where X is equal to the value of the Number of Zones field. There is no
     * order imposed by the numbering of the Zone ID field in this command payload. IAS ACE
     * servers should provide the array of Zone IDs in ascending order.
     */
    private List<Integer> zoneId;

    /**
     * Default constructor.
     */
    public SetBypassedZoneListCommand() {
        genericCommand = false;
        clusterId = 0x0501;
        commandId = 6;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Zone ID.
     * <p>
     * Zone ID is the index of the Zone in the CIE's zone table and is an array of Zone IDs for each
     * zone that is bypassed where X is equal to the value of the Number of Zones field. There is no
     * order imposed by the numbering of the Zone ID field in this command payload. IAS ACE
     * servers should provide the array of Zone IDs in ascending order.
     *
     * @return the Zone ID
     */
    public List<Integer> getZoneId() {
        return zoneId;
    }

    /**
     * Sets Zone ID.
     * <p>
     * Zone ID is the index of the Zone in the CIE's zone table and is an array of Zone IDs for each
     * zone that is bypassed where X is equal to the value of the Number of Zones field. There is no
     * order imposed by the numbering of the Zone ID field in this command payload. IAS ACE
     * servers should provide the array of Zone IDs in ascending order.
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
