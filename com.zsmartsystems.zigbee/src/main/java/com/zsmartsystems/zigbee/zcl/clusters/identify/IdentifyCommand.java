package com.zsmartsystems.zigbee.zcl.clusters.identify;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;


/**
 * <p>
 * Identify Command value object class.
 * <p>
 * The identify command starts or stops the receiving device identifying itself.
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
public class IdentifyCommand extends ZclCommand {
    /**
     * Identify Time command message field.
     */
    private Integer identifyTime;

    /**
     * Default constructor.
     */
    public IdentifyCommand() {
        genericCommand = false;
        clusterId = 3;
        commandId = 0;
        commandDirection = true;
    }

    /**
     * Gets Identify Time.
     * @return the Identify Time
     */
    public Integer getIdentifyTime() {
        return identifyTime;
    }

    /**
     * Sets Identify Time.
     * @param identifyTime the Identify Time
     */
    public void setIdentifyTime(final Integer identifyTime) {
        this.identifyTime = identifyTime;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(identifyTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        identifyTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", identifyTime=");
        builder.append(identifyTime);
        return builder.toString();
    }

}
