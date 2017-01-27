package com.zsmartsystems.zigbee.zcl.clusters.rssilocation;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * Compact Location Data Notification Command value object class.
 * </p>
 * <p>
 * Cluster: <b>RSSI Location</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the RSSI Location cluster.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class CompactLocationDataNotificationCommand extends ZclCommand {
    /**
     * Default constructor.
     */
    public CompactLocationDataNotificationCommand() {
        genericCommand = false;
        clusterId = 11;
        commandId = 3;
        commandDirection = false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        return builder.toString();
    }

}
