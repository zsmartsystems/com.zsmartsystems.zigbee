package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Arm Command value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * The IAS ACE cluster defines an interface to the functionality of any Ancillary
 * Control Equipment of the IAS system. Using this cluster, a ZigBee enabled ACE
 * device can access a IAS CIE device and manipulate the IAS system, on behalf of a
 * level-2 user.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ArmCommand extends ZclCommand {
    /**
     * Arm Mode command message field.
     */
    private Integer armMode;

    /**
     * Default constructor.
     */
    public ArmCommand() {
        genericCommand = false;
        clusterId = 1281;
        commandId = 0;
        commandDirection = true;
    }

    /**
     * Gets Arm Mode.
     *
     * @return the Arm Mode
     */
    public Integer getArmMode() {
        return armMode;
    }

    /**
     * Sets Arm Mode.
     *
     * @param armMode the Arm Mode
     */
    public void setArmMode(final Integer armMode) {
        this.armMode = armMode;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(armMode, ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        armMode = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", armMode=");
        builder.append(armMode);
        return builder.toString();
    }

}
