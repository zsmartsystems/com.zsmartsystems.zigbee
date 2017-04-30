package com.zsmartsystems.zigbee.zcl.clusters.iaswd;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Start Warning Command value object class.
 * <p>
 * This command starts the WD operation. The WD alerts the surrounding area by
 * audible (siren) and visual (strobe) signals.
 * <br>
 * A Start Warning command shall always terminate the effect of any previous
 * command that is still current.
 * <p>
 * Cluster: <b>IAS WD</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the IAS WD cluster.
 * <p>
 * The IAS WD cluster provides an interface to the functionality of any Warning
 * Device equipment of the IAS system. Using this cluster, a ZigBee enabled CIE
 * device can access a ZigBee enabled IAS WD device and issue alarm warning
 * indications (siren, strobe lighting, etc.) when a system alarm condition is detected.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class StartWarningCommand extends ZclCommand {
    /**
     * Header command message field.
     */
    private Integer header;

    /**
     * Warning duration command message field.
     */
    private Integer warningDuration;

    /**
     * Default constructor.
     */
    public StartWarningCommand() {
        genericCommand = false;
        clusterId = 1282;
        commandId = 0;
        commandDirection = true;
    }

    /**
     * Gets Header.
     *
     * @return the Header
     */
    public Integer getHeader() {
        return header;
    }

    /**
     * Sets Header.
     *
     * @param header the Header
     */
    public void setHeader(final Integer header) {
        this.header = header;
    }

    /**
     * Gets Warning duration.
     *
     * @return the Warning duration
     */
    public Integer getWarningDuration() {
        return warningDuration;
    }

    /**
     * Sets Warning duration.
     *
     * @param warningDuration the Warning duration
     */
    public void setWarningDuration(final Integer warningDuration) {
        this.warningDuration = warningDuration;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(header, ZclDataType.DATA_8_BIT);
        serializer.serialize(warningDuration, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        header = (Integer) deserializer.deserialize(ZclDataType.DATA_8_BIT);
        warningDuration = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("StartWarningCommand [");
        builder.append(super.toString());
        builder.append(", header=");
        builder.append(header);
        builder.append(", warningDuration=");
        builder.append(warningDuration);
        builder.append("]");
        return builder.toString();
    }

}
