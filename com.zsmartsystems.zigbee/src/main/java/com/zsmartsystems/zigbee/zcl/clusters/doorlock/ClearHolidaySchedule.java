/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
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
 * Clear Holiday Schedule value object class.
 * <p>
 * Cluster: <b>Door Lock</b>. Command ID 0x13 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * Clear the holiday Schedule by specifying Holiday ID.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T05:31:31Z")
public class ClearHolidaySchedule extends ZclDoorLockCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0101;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x13;

    /**
     * Holiday Schedule ID command message field.
     */
    private Integer holidayScheduleId;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public ClearHolidaySchedule() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param holidayScheduleId {@link Integer} Holiday Schedule ID
     */
    public ClearHolidaySchedule(
            Integer holidayScheduleId) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.holidayScheduleId = holidayScheduleId;
    }

    /**
     * Gets Holiday Schedule ID.
     *
     * @return the Holiday Schedule ID
     */
    public Integer getHolidayScheduleId() {
        return holidayScheduleId;
    }

    /**
     * Sets Holiday Schedule ID.
     *
     * @param holidayScheduleId the Holiday Schedule ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setHolidayScheduleId(final Integer holidayScheduleId) {
        this.holidayScheduleId = holidayScheduleId;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(holidayScheduleId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        holidayScheduleId = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(60);
        builder.append("ClearHolidaySchedule [");
        builder.append(super.toString());
        builder.append(", holidayScheduleId=");
        builder.append(holidayScheduleId);
        builder.append(']');
        return builder.toString();
    }

}
