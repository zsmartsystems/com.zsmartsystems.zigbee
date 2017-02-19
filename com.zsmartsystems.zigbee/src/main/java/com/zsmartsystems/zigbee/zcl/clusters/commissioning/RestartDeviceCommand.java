package com.zsmartsystems.zigbee.zcl.clusters.commissioning;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Restart Device Command value object class.
 * <p>
 * Cluster: <b>Commissioning</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Commissioning cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class RestartDeviceCommand extends ZclCommand {
    /**
     * Option command message field.
     */
    private Integer option;

    /**
     * Delay command message field.
     */
    private Integer delay;

    /**
     * Jitter command message field.
     */
    private Integer jitter;

    /**
     * Default constructor.
     */
    public RestartDeviceCommand() {
        genericCommand = false;
        clusterId = 21;
        commandId = 0;
        commandDirection = true;
    }

    /**
     * Gets Option.
     * @return the Option
     */
    public Integer getOption() {
        return option;
    }

    /**
     * Sets Option.
     * @param option the Option
     */
    public void setOption(final Integer option) {
        this.option = option;
    }

    /**
     * Gets Delay.
     * @return the Delay
     */
    public Integer getDelay() {
        return delay;
    }

    /**
     * Sets Delay.
     * @param delay the Delay
     */
    public void setDelay(final Integer delay) {
        this.delay = delay;
    }

    /**
     * Gets Jitter.
     * @return the Jitter
     */
    public Integer getJitter() {
        return jitter;
    }

    /**
     * Sets Jitter.
     * @param jitter the Jitter
     */
    public void setJitter(final Integer jitter) {
        this.jitter = jitter;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(option, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(delay, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(jitter, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        option = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        delay = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        jitter = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", option=");
        builder.append(option);
        builder.append(", delay=");
        builder.append(delay);
        builder.append(", jitter=");
        builder.append(jitter);
        return builder.toString();
    }

}
