package com.zsmartsystems.zigbee.zcl.clusters.basic;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * Reset to Factory Defaults Command value object class.
 * </p>
 * <p>
 * Cluster: <b>Basic</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Basic cluster.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class ResetToFactoryDefaultsCommand extends ZclCommand {
    /**
     * Default constructor setting the command type field.
     */
    public ResetToFactoryDefaultsCommand() {
        genericCommand = false;
        clusterId = 0;
        commandId = 0;
        commandDirection = true;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public ResetToFactoryDefaultsCommand(final Map<Integer, Object> fields) {
        this();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        return builder.toString();
    }

}
