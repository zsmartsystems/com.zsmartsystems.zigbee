package com.zsmartsystems.zigbee.zcl.clusters.alarms;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * Reset Alarm Log Command value object class.
 * </p>
 * <p>
 * Cluster: <b>Alarms</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Alarms cluster.
 * </p>
 * <p>
 * Attributes and commands for sending alarm notifications and configuring alarm
 * functionality.
 * <br>
 * Alarm conditions and their respective alarm codes are described in individual
 * clusters, along with an alarm mask field. Where not masked, alarm notifications
 * are reported to subscribed targets using binding.
 * <br>
 * Where an alarm table is implemented, all alarms, masked or otherwise, are
 * recorded and may be retrieved on demand.
 * <br>
 * Alarms may either reset automatically when the conditions that cause are no
 * longer active, or may need to be explicitly reset.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class ResetAlarmLogCommand extends ZclCommand {
    /**
     * Default constructor setting the command type field.
     */
    public ResetAlarmLogCommand() {
        genericCommand = false;
        clusterId = 9;
        commandId = 3;
        commandDirection = true;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public ResetAlarmLogCommand(final Map<Integer, Object> fields) {
        this();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        return builder.toString();
    }

}
