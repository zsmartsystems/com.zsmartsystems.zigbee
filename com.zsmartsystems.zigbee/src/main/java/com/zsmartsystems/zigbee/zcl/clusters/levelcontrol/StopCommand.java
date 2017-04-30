package com.zsmartsystems.zigbee.zcl.clusters.levelcontrol;

import com.zsmartsystems.zigbee.zcl.ZclCommand;

/**
 * Stop Command value object class.
 * <p>
 * Cluster: <b>Level Control</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Level Control cluster.
 * <p>
 * This cluster provides an interface for controlling a characteristic of a device that
 * can be set to a level, for example the brightness of a light, the degree of closure of
 * a door, or the power output of a heater.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class StopCommand extends ZclCommand {
    /**
     * Default constructor.
     */
    public StopCommand() {
        genericCommand = false;
        clusterId = 8;
        commandId = 3;
        commandDirection = true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("StopCommand [");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

}
