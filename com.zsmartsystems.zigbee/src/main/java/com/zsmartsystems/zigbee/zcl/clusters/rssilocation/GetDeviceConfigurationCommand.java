package com.zsmartsystems.zigbee.zcl.clusters.rssilocation;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Device Configuration Command value object class.
 * <p>
 * Cluster: <b>RSSI Location</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the RSSI Location cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class GetDeviceConfigurationCommand extends ZclCommand {
    /**
     * Target Address command message field.
     */
    private Long targetAddress;

    /**
     * Default constructor.
     */
    public GetDeviceConfigurationCommand() {
        genericCommand = false;
        clusterId = 11;
        commandId = 2;
        commandDirection = true;
    }

    /**
     * Gets Target Address.
     * @return the Target Address
     */
    public Long getTargetAddress() {
        return targetAddress;
    }

    /**
     * Sets Target Address.
     * @param targetAddress the Target Address
     */
    public void setTargetAddress(final Long targetAddress) {
        this.targetAddress = targetAddress;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(targetAddress, ZclDataType.IEEE_ADDRESS);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        targetAddress = (Long) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", targetAddress=");
        builder.append(targetAddress);
        return builder.toString();
    }

}
