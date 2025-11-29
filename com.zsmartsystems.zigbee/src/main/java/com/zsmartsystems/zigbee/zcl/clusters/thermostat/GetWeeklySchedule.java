/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.thermostat;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Weekly Schedule value object class.
 * <p>
 * Cluster: <b>Thermostat</b>. Command ID 0x02 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Thermostat cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class GetWeeklySchedule extends ZclThermostatCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0201;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x02;

    /**
     * Days To Return command message field.
     */
    private Integer daysToReturn;

    /**
     * Mode To Return command message field.
     */
    private Integer modeToReturn;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GetWeeklySchedule() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param daysToReturn {@link Integer} Days To Return
     * @param modeToReturn {@link Integer} Mode To Return
     */
    public GetWeeklySchedule(
            Integer daysToReturn,
            Integer modeToReturn) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.daysToReturn = daysToReturn;
        this.modeToReturn = modeToReturn;
    }

    /**
     * Gets Days To Return.
     *
     * @return the Days To Return
     */
    public Integer getDaysToReturn() {
        return daysToReturn;
    }

    /**
     * Sets Days To Return.
     *
     * @param daysToReturn the Days To Return
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setDaysToReturn(final Integer daysToReturn) {
        this.daysToReturn = daysToReturn;
    }

    /**
     * Gets Mode To Return.
     *
     * @return the Mode To Return
     */
    public Integer getModeToReturn() {
        return modeToReturn;
    }

    /**
     * Sets Mode To Return.
     *
     * @param modeToReturn the Mode To Return
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setModeToReturn(final Integer modeToReturn) {
        this.modeToReturn = modeToReturn;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(daysToReturn, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(modeToReturn, ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        daysToReturn = deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        modeToReturn = deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(84);
        builder.append("GetWeeklySchedule [");
        builder.append(super.toString());
        builder.append(", daysToReturn=");
        builder.append(daysToReturn);
        builder.append(", modeToReturn=");
        builder.append(modeToReturn);
        builder.append(']');
        return builder.toString();
    }

}
