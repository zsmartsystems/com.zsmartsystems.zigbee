/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import java.util.List;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Set Bypassed Zone List Command value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command ID 0x06 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * Sets the list of bypassed zones on the IAS ACE client. This command can be sent either as a
 * response to the GetBypassedZoneList command or unsolicited when the list of bypassed zones
 * changes on the ACE server.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class SetBypassedZoneListCommand extends ZclIasAceCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0501;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x06;

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
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public SetBypassedZoneListCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param zoneId {@link List<Integer>} Zone ID
     */
    public SetBypassedZoneListCommand(
            List<Integer> zoneId) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.zoneId = zoneId;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZoneId(final List<Integer> zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(zoneId, ZclDataType.N_X_UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        zoneId = deserializer.deserialize(ZclDataType.N_X_UNSIGNED_8_BIT_INTEGER);
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
