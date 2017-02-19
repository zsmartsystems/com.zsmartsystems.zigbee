package com.zsmartsystems.zigbee.zcl.clusters.commissioning;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Reset Startup Parameters Command value object class.
 * <p>
 * Cluster: <b>Commissioning</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Commissioning cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ResetStartupParametersCommand extends ZclCommand {
    /**
     * Option command message field.
     */
    private Integer option;

    /**
     * Index command message field.
     */
    private Integer index;

    /**
     * Default constructor.
     */
    public ResetStartupParametersCommand() {
        genericCommand = false;
        clusterId = 21;
        commandId = 3;
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
     * Gets Index.
     * @return the Index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * Sets Index.
     * @param index the Index
     */
    public void setIndex(final Integer index) {
        this.index = index;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(option, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(index, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        option = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        index = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", option=");
        builder.append(option);
        builder.append(", index=");
        builder.append(index);
        return builder.toString();
    }

}
