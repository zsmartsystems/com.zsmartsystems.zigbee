package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * Get Zone ID Map Command value object class.
 * </p>
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * </p>
 * <p>
 * The IAS ACE cluster defines an interface to the functionality of any Ancillary
 * Control Equipment of the IAS system. Using this cluster, a ZigBee enabled ACE
 * device can access a IAS CIE device and manipulate the IAS system, on behalf of a
 * level-2 user.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class GetZoneIdMapCommand extends ZclCommand {
    /**
     * Default constructor setting the command type field.
     */
    public GetZoneIdMapCommand() {
        genericCommand = false;
        clusterId = 1281;
        commandId = 5;
        commandDirection = true;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public GetZoneIdMapCommand(final Map<Integer, Object> fields) {
        this();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        return builder.toString();
    }

}
