package com.zsmartsystems.zigbee.zcl.clusters.basic;

import com.zsmartsystems.zigbee.zcl.ZclCommand;

/**
 * Reset to Factory Defaults Command value object class.
 * <p>
 * Cluster: <b>Basic</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Basic cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ResetToFactoryDefaultsCommand extends ZclCommand {
    /**
     * Default constructor.
     */
    public ResetToFactoryDefaultsCommand() {
        genericCommand = false;
        clusterId = 0;
        commandId = 0;
        commandDirection = true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ResetToFactoryDefaultsCommand [");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

}
