/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.thermostat;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Weekly Schedule Response value object class.
 * <p>
 * Cluster: <b>Thermostat</b>. Command ID 0x00 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Thermostat cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-06T18:45:28Z")
public class GetWeeklyScheduleResponse extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0201;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x00;

    /**
     * Number Of Transitions command message field.
     */
    private Integer numberOfTransitions;

    /**
     * Day Of Week command message field.
     */
    private Integer dayOfWeek;

    /**
     * Mode command message field.
     */
    private Integer mode;

    /**
     * Transition command message field.
     */
    private Integer transition;

    /**
     * Heat Set command message field.
     */
    private Integer heatSet;

    /**
     * Cool Set command message field.
     */
    private Integer coolSet;

    /**
     * Default constructor.
     */
    public GetWeeklyScheduleResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Number Of Transitions.
     *
     * @return the Number Of Transitions
     */
    public Integer getNumberOfTransitions() {
        return numberOfTransitions;
    }

    /**
     * Sets Number Of Transitions.
     *
     * @param numberOfTransitions the Number Of Transitions
     * @return the GetWeeklyScheduleResponse command
     */
    public GetWeeklyScheduleResponse setNumberOfTransitions(final Integer numberOfTransitions) {
        this.numberOfTransitions = numberOfTransitions;
        return this;
    }

    /**
     * Gets Day Of Week.
     *
     * @return the Day Of Week
     */
    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Sets Day Of Week.
     *
     * @param dayOfWeek the Day Of Week
     * @return the GetWeeklyScheduleResponse command
     */
    public GetWeeklyScheduleResponse setDayOfWeek(final Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    /**
     * Gets Mode.
     *
     * @return the Mode
     */
    public Integer getMode() {
        return mode;
    }

    /**
     * Sets Mode.
     *
     * @param mode the Mode
     * @return the GetWeeklyScheduleResponse command
     */
    public GetWeeklyScheduleResponse setMode(final Integer mode) {
        this.mode = mode;
        return this;
    }

    /**
     * Gets Transition.
     *
     * @return the Transition
     */
    public Integer getTransition() {
        return transition;
    }

    /**
     * Sets Transition.
     *
     * @param transition the Transition
     * @return the GetWeeklyScheduleResponse command
     */
    public GetWeeklyScheduleResponse setTransition(final Integer transition) {
        this.transition = transition;
        return this;
    }

    /**
     * Gets Heat Set.
     *
     * @return the Heat Set
     */
    public Integer getHeatSet() {
        return heatSet;
    }

    /**
     * Sets Heat Set.
     *
     * @param heatSet the Heat Set
     * @return the GetWeeklyScheduleResponse command
     */
    public GetWeeklyScheduleResponse setHeatSet(final Integer heatSet) {
        this.heatSet = heatSet;
        return this;
    }

    /**
     * Gets Cool Set.
     *
     * @return the Cool Set
     */
    public Integer getCoolSet() {
        return coolSet;
    }

    /**
     * Sets Cool Set.
     *
     * @param coolSet the Cool Set
     * @return the GetWeeklyScheduleResponse command
     */
    public GetWeeklyScheduleResponse setCoolSet(final Integer coolSet) {
        this.coolSet = coolSet;
        return this;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(numberOfTransitions, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(dayOfWeek, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(mode, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(transition, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(heatSet, ZclDataType.SIGNED_16_BIT_INTEGER);
        serializer.serialize(coolSet, ZclDataType.SIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        numberOfTransitions = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        dayOfWeek = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        mode = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        transition = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        heatSet = (Integer) deserializer.deserialize(ZclDataType.SIGNED_16_BIT_INTEGER);
        coolSet = (Integer) deserializer.deserialize(ZclDataType.SIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(204);
        builder.append("GetWeeklyScheduleResponse [");
        builder.append(super.toString());
        builder.append(", numberOfTransitions=");
        builder.append(numberOfTransitions);
        builder.append(", dayOfWeek=");
        builder.append(dayOfWeek);
        builder.append(", mode=");
        builder.append(mode);
        builder.append(", transition=");
        builder.append(transition);
        builder.append(", heatSet=");
        builder.append(heatSet);
        builder.append(", coolSet=");
        builder.append(coolSet);
        builder.append(']');
        return builder.toString();
    }

}
