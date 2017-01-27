package com.zsmartsystems.zigbee.zcl.clusters.doorlock;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * Unlock Door Command value object class.
 * </p>
 * <p>
 * Cluster: <b>Door Lock</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class UnlockDoorCommand extends ZclCommand {
    /**
     * Pin code command message field.
     */
    private String pinCode;

    /**
     * Default constructor.
     */
    public UnlockDoorCommand() {
        genericCommand = false;
        clusterId = 257;
        commandId = 1;
        commandDirection = true;
    }

    /**
     * Gets Pin code.
     * @return the Pin code
     */
    public String getPinCode() {
        return pinCode;
    }

    /**
     * Sets Pin code.
     * @param pinCode the Pin code
     */
    public void setPinCode(final String pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(pinCode, ZclDataType.OCTET_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        pinCode = (String) deserializer.deserialize(ZclDataType.OCTET_STRING);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", ");
        builder.append("pinCode = ");
        builder.append(pinCode);
        return builder.toString();
    }

}
