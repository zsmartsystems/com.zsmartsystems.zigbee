package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * Arm Response value object class.
 * </p>
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * </p>
 * <p>
 * The IAS ACE cluster defines an interface to the functionality of any Ancillary
 * Control Equipment of the IAS system. Using this cluster, a ZigBee enabled ACE
 * device can access a IAS CIE device and manipulate the IAS system, on behalf of a
 * level-2 user.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class ArmResponse extends ZclCommand {
    /**
     * Arm Notification command message field.
     */
    private Integer armNotification;

    /**
     * Default constructor.
     */
    public ArmResponse() {
        genericCommand = false;
        clusterId = 1281;
        commandId = 0;
        commandDirection = false;
    }

    /**
     * Gets Arm Notification.
     * @return the Arm Notification
     */
    public Integer getArmNotification() {
        return armNotification;
    }

    /**
     * Sets Arm Notification.
     * @param armNotification the Arm Notification
     */
    public void setArmNotification(final Integer armNotification) {
        this.armNotification = armNotification;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(armNotification, ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        armNotification = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", ");
        builder.append("armNotification = ");
        builder.append(armNotification);
        return builder.toString();
    }

}
