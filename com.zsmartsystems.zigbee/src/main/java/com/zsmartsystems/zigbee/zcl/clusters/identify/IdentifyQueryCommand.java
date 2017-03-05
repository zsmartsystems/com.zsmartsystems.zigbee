package com.zsmartsystems.zigbee.zcl.clusters.identify;

import com.zsmartsystems.zigbee.zcl.ZclCommand;

/**
 * Identify Query Command value object class.
 * <p>
 * Cluster: <b>Identify</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Identify cluster.
 * <p>
 * Attributes and commands to put a device into an Identification mode (e.g. flashing
 * a light), that indicates to an observer – e.g. an installer - which of several devices
 * it is, also to request any device that is identifying itself to respond to the initiator.
 * <p>
 * Note that this cluster cannot be disabled, and remains functional regardless of the
 * setting of the DeviceEnable attribute in the Basic cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class IdentifyQueryCommand extends ZclCommand {
    /**
     * Default constructor.
     */
    public IdentifyQueryCommand() {
        genericCommand = false;
        clusterId = 3;
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
