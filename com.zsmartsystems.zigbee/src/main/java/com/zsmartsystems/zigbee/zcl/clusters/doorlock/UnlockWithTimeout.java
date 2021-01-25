/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.doorlock;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Unlock With Timeout value object class.
 * <p>
 * Cluster: <b>Door Lock</b>. Command ID 0x03 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * This command causes the lock device to unlock the door with a timeout parameter. After the
 * time in seconds specified in the timeout field, the lock device will relock itself
 * automatically. This timeout parameter is only temporary for this message transition only
 * and overrides the default relock time as specified in the [Auto Relock Time attribute]
 * attribute. If the door lock device is not capable of or does not want to support temporary
 * Relock Timeout, it should not support this optional command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class UnlockWithTimeout extends ZclDoorLockCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0101;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x03;

    /**
     * Timeout In Seconds command message field.
     */
    private Integer timeoutInSeconds;

    /**
     * PIN command message field.
     */
    private String pin;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public UnlockWithTimeout() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param timeoutInSeconds {@link Integer} Timeout In Seconds
     * @param pin {@link String} PIN
     */
    public UnlockWithTimeout(
            Integer timeoutInSeconds,
            String pin) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.timeoutInSeconds = timeoutInSeconds;
        this.pin = pin;
    }

    /**
     * Gets Timeout In Seconds.
     *
     * @return the Timeout In Seconds
     */
    public Integer getTimeoutInSeconds() {
        return timeoutInSeconds;
    }

    /**
     * Sets Timeout In Seconds.
     *
     * @param timeoutInSeconds the Timeout In Seconds
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTimeoutInSeconds(final Integer timeoutInSeconds) {
        this.timeoutInSeconds = timeoutInSeconds;
    }

    /**
     * Gets PIN.
     *
     * @return the PIN
     */
    public String getPin() {
        return pin;
    }

    /**
     * Sets PIN.
     *
     * @param pin the PIN
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setPin(final String pin) {
        this.pin = pin;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(timeoutInSeconds, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(pin, ZclDataType.CHARACTER_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        timeoutInSeconds = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        pin = (String) deserializer.deserialize(ZclDataType.CHARACTER_STRING);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(79);
        builder.append("UnlockWithTimeout [");
        builder.append(super.toString());
        builder.append(", timeoutInSeconds=");
        builder.append(timeoutInSeconds);
        builder.append(", pin=");
        builder.append(pin);
        builder.append(']');
        return builder.toString();
    }

}
