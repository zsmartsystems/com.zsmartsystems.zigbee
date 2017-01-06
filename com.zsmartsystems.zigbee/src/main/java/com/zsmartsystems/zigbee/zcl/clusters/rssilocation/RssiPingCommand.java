package com.zsmartsystems.zigbee.zcl.clusters.rssilocation;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * RSSI Ping Command value object class.
 * </p>
 * <p>
 * Cluster: <b>RSSI Location</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the RSSI Location cluster.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class RssiPingCommand extends ZclCommand {
    /**
     * Location Type command message field.
     */
    private Integer locationType;

    /**
     * Default constructor setting the command type field.
     */
    public RssiPingCommand() {
        genericCommand = false;
        clusterId = 11;
        commandId = 4;
        commandDirection = false;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public RssiPingCommand(final Map<Integer, Object> fields) {
        this();
        locationType = (Integer) fields.get(0);
    }

    /**
     * Gets Location Type.
     * @return the Location Type
     */
    public Integer getLocationType() {
        return locationType;
    }

    /**
     * Sets Location Type.
     * @param locationType the Location Type
     */
    public void setLocationType(final Integer locationType) {
        this.locationType = locationType;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(locationType, ZclDataType.DATA_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        locationType = (Integer) deserializer.deserialize(ZclDataType.DATA_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", ");
        builder.append("locationType = ");
        builder.append(locationType);
        return builder.toString();
    }

}
