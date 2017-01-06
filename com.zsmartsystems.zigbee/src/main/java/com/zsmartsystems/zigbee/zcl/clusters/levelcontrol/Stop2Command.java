package com.zsmartsystems.zigbee.zcl.clusters.levelcontrol;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * Stop 2 Command value object class.
 * </p>
 * <p>
 * Cluster: <b>Level Control</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Level Control cluster.
 * </p>
 * <p>
 * This cluster provides an interface for controlling a characteristic of a device that
 * can be set to a level, for example the brightness of a light, the degree of closure of
 * a door, or the power output of a heater.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class Stop2Command extends ZclCommand {
    /**
     * Default constructor setting the command type field.
     */
    public Stop2Command() {
        genericCommand = false;
        clusterId = 8;
        commandId = 7;
        commandDirection = true;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public Stop2Command(final Map<Integer, Object> fields) {
        this();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        return builder.toString();
    }

}
