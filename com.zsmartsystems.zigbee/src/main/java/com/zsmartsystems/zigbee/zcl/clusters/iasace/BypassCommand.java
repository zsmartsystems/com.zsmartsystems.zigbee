/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import java.util.List;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Bypass Command value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * Provides IAS ACE clients with a method to send zone bypass requests to the IAS ACE server.
 * Bypassed zones may be faulted or in alarm but will not trigger the security system to go into
 * alarm. For example, a user MAYwish to allow certain windows in his premises protected by an
 * IAS Zone server to be left open while the user leaves the premises. The user could bypass the
 * IAS Zone server protecting the window on his IAS ACE client (e.g., security keypad), and if
 * the IAS ACE server indicates that zone is successfully by-passed, arm his security system
 * while he is away.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T19:19:25Z")
public class BypassCommand extends ZclCommand {
    /**
     * Number Of Zones command message field.
     */
    private Integer numberOfZones;

    /**
     * Zone IDs command message field.
     */
    private List<Integer> zoneIds;

    /**
     * Arm/Disarm Code command message field.
     * <p>
     * The Arm/DisarmCode shall be a code entered into the ACE client (e.g., security keypad)
     * or system by the user upon arming/disarming. The server may validate the Arm/Disarm
     * Code received from the IAS ACE client in Arm command payload before arming or disarming
     * the system. If the client does not have the capability to input an Arm/Disarm Code (e.g.,
     * keyfob),or the system does not require one, the client shall a transmit a string with a
     * length of zero.
     */
    private String armDisarmCode;

    /**
     * Default constructor.
     */
    public BypassCommand() {
        genericCommand = false;
        clusterId = 0x0501;
        commandId = 1;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Number Of Zones.
     *
     * @return the Number Of Zones
     */
    public Integer getNumberOfZones() {
        return numberOfZones;
    }

    /**
     * Sets Number Of Zones.
     *
     * @param numberOfZones the Number Of Zones
     */
    public void setNumberOfZones(final Integer numberOfZones) {
        this.numberOfZones = numberOfZones;
    }

    /**
     * Gets Zone IDs.
     *
     * @return the Zone IDs
     */
    public List<Integer> getZoneIds() {
        return zoneIds;
    }

    /**
     * Sets Zone IDs.
     *
     * @param zoneIds the Zone IDs
     */
    public void setZoneIds(final List<Integer> zoneIds) {
        this.zoneIds = zoneIds;
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
     *
     * @param armDisarmCode the Arm/Disarm Code
     */
    public void setArmDisarmCode(final String armDisarmCode) {
        this.armDisarmCode = armDisarmCode;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(numberOfZones, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(zoneIds, ZclDataType.N_X_UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(armDisarmCode, ZclDataType.CHARACTER_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        numberOfZones = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        zoneIds = (List<Integer>) deserializer.deserialize(ZclDataType.N_X_UNSIGNED_8_BIT_INTEGER);
        armDisarmCode = (String) deserializer.deserialize(ZclDataType.CHARACTER_STRING);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(109);
        builder.append("BypassCommand [");
        builder.append(super.toString());
        builder.append(", numberOfZones=");
        builder.append(numberOfZones);
        builder.append(", zoneIds=");
        builder.append(zoneIds);
        builder.append(", armDisarmCode=");
        builder.append(armDisarmCode);
        builder.append(']');
        return builder.toString();
    }

}
