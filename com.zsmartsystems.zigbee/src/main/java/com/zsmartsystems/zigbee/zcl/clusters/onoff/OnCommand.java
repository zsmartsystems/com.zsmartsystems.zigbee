package com.zsmartsystems.zigbee.zcl.clusters.onoff;

import com.zsmartsystems.zigbee.zcl.ZclCommand;

/**
 * On Command value object class.
 * <p>
 * Cluster: <b>On/Off</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the On/Off cluster.
 * <p>
 * Attributes and commands for switching devices between ‘On’ and ‘Off’ states.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class OnCommand extends ZclCommand {
    /**
     * Default constructor.
     */
    public OnCommand() {
        genericCommand = false;
        clusterId = 6;
        commandId = 1;
        commandDirection = true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        return builder.toString();
    }

}
