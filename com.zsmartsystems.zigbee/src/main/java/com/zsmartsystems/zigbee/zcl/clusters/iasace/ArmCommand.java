/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Arm Command value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command ID 0x00 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * On receipt of this command, the receiving device sets its arm mode according to the value of
 * the Arm Mode field. It is not guaranteed that an Arm command will succeed. Based on the current
 * state of the IAS CIE, and its related devices, the command can be rejected. The device shall
 * generate an Arm Response command to indicate the resulting armed state
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class ArmCommand extends ZclIasAceCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0501;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x00;

    /**
     * Arm Mode command message field.
     */
    private Integer armMode;

    /**
     * Arm/Disarm Code command message field.
     * <p>
     * The Arm/DisarmCode shall be a code entered into the ACE client (e.g., security keypad)
     * or system by the user upon arming/disarming. The server may validate the Arm/Disarm
     * Code received from the IAS ACE client in Arm command payload before arming or disarming
     * the system. If the client does not have the capability to input an Arm/Disarm Code (e.g.,
     * keyfob),or the system does not require one, the client shall a transmit a string with a
     * length of zero.
     * <p>
     * There is no minimum or maximum length to the Arm/Disarm Code; however, the Arm/Disarm
     * Code should be between four and eight alphanumeric characters in length.
     * <p>
     * The string encoding shall be UTF-8.
     */
    private String armDisarmCode;

    /**
     * Zone ID command message field.
     * <p>
     * Zone ID is the index of the Zone in the CIE's zone table. If none is programmed, the Zone ID
     * default value shall be indicated in this field.
     */
    private Integer zoneId;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public ArmCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param armMode {@link Integer} Arm Mode
     * @param armDisarmCode {@link String} Arm/Disarm Code
     * @param zoneId {@link Integer} Zone ID
     */
    public ArmCommand(
            Integer armMode,
            String armDisarmCode,
            Integer zoneId) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.armMode = armMode;
        this.armDisarmCode = armDisarmCode;
        this.zoneId = zoneId;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setArmMode(final Integer armMode) {
        this.armMode = armMode;
    }

    /**
     * Gets Arm/Disarm Code.
     * <p>
     * The Arm/DisarmCode shall be a code entered into the ACE client (e.g., security keypad)
     * or system by the user upon arming/disarming. The server may validate the Arm/Disarm
     * Code received from the IAS ACE client in Arm command payload before arming or disarming
     * the system. If the client does not have the capability to input an Arm/Disarm Code (e.g.,
     * keyfob),or the system does not require one, the client shall a transmit a string with a
     * length of zero.
     * <p>
     * There is no minimum or maximum length to the Arm/Disarm Code; however, the Arm/Disarm
     * Code should be between four and eight alphanumeric characters in length.
     * <p>
     * The string encoding shall be UTF-8.
     *
     * @return the Arm/Disarm Code
     */
    public String getArmDisarmCode() {
        return armDisarmCode;
    }

    /**
     * Sets Arm/Disarm Code.
     * <p>
     * The Arm/DisarmCode shall be a code entered into the ACE client (e.g., security keypad)
     * or system by the user upon arming/disarming. The server may validate the Arm/Disarm
     * Code received from the IAS ACE client in Arm command payload before arming or disarming
     * the system. If the client does not have the capability to input an Arm/Disarm Code (e.g.,
     * keyfob),or the system does not require one, the client shall a transmit a string with a
     * length of zero.
     * <p>
     * There is no minimum or maximum length to the Arm/Disarm Code; however, the Arm/Disarm
     * Code should be between four and eight alphanumeric characters in length.
     * <p>
     * The string encoding shall be UTF-8.
     *
     * @param armDisarmCode the Arm/Disarm Code
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setArmDisarmCode(final String armDisarmCode) {
        this.armDisarmCode = armDisarmCode;
    }

    /**
     * Gets Zone ID.
     * <p>
     * Zone ID is the index of the Zone in the CIE's zone table. If none is programmed, the Zone ID
     * default value shall be indicated in this field.
     *
     * @return the Zone ID
     */
    public Integer getZoneId() {
        return zoneId;
    }

    /**
     * Sets Zone ID.
     * <p>
     * Zone ID is the index of the Zone in the CIE's zone table. If none is programmed, the Zone ID
     * default value shall be indicated in this field.
     *
     * @param zoneId the Zone ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZoneId(final Integer zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(armMode, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(armDisarmCode, ZclDataType.CHARACTER_STRING);
        serializer.serialize(zoneId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        armMode = deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        armDisarmCode = deserializer.deserialize(ZclDataType.CHARACTER_STRING);
        zoneId = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(99);
        builder.append("ArmCommand [");
        builder.append(super.toString());
        builder.append(", armMode=");
        builder.append(armMode);
        builder.append(", armDisarmCode=");
        builder.append(armDisarmCode);
        builder.append(", zoneId=");
        builder.append(zoneId);
        builder.append(']');
        return builder.toString();
    }

}
