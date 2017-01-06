package com.zsmartsystems.zigbee.zcl.clusters.onoff;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * Toggle Command value object class.
 * </p>
 * <p>
 * Cluster: <b>On/Off</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the On/Off cluster.
 * </p>
 * <p>
 * Attributes and commands for switching devices between ‘On’ and ‘Off’ states.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class ToggleCommand extends ZclCommand {
    /**
     * Default constructor setting the command type field.
     */
    public ToggleCommand() {
        genericCommand = false;
        clusterId = 6;
        commandId = 2;
        commandDirection = true;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public ToggleCommand(final Map<Integer, Object> fields) {
        this();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        return builder.toString();
    }

}
