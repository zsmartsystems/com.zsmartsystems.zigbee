/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.onoff;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * On With Timed Off Command value object class.
 * <p>
 * The On With Timed Off command allows devices to be turned on for a specific duration
 * with a guarded off duration so that SHOULD the device be subsequently switched off,
 * further On With Timed Off commands, received during this time, are prevented from
 * turning the devices back on. Note that the device can be periodically re-kicked by
 * subsequent On With Timed Off commands, e.g., from an on/off sensor.
 * <p>
 * Cluster: <b>On/Off</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the On/Off cluster.
 * <p>
 * Attributes and commands for switching devices between ‘On’ and ‘Off’ states.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class OnWithTimedOffCommand extends ZclCommand {
    /**
     * On Off Control command message field.
     */
    private Integer onOffControl;

    /**
     * On Time command message field.
     */
    private Integer onTime;

    /**
     * Off Wait Time command message field.
     */
    private Integer offWaitTime;

    /**
     * Default constructor.
     */
    public OnWithTimedOffCommand() {
        genericCommand = false;
        clusterId = 6;
        commandId = 66;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets On Off Control.
     *
     * @return the On Off Control
     */
    public Integer getOnOffControl() {
        return onOffControl;
    }

    /**
     * Sets On Off Control.
     *
     * @param onOffControl the On Off Control
     */
    public void setOnOffControl(final Integer onOffControl) {
        this.onOffControl = onOffControl;
    }

    /**
     * Gets On Time.
     *
     * @return the On Time
     */
    public Integer getOnTime() {
        return onTime;
    }

    /**
     * Sets On Time.
     *
     * @param onTime the On Time
     */
    public void setOnTime(final Integer onTime) {
        this.onTime = onTime;
    }

    /**
     * Gets Off Wait Time.
     *
     * @return the Off Wait Time
     */
    public Integer getOffWaitTime() {
        return offWaitTime;
    }

    /**
     * Sets Off Wait Time.
     *
     * @param offWaitTime the Off Wait Time
     */
    public void setOffWaitTime(final Integer offWaitTime) {
        this.offWaitTime = offWaitTime;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(onOffControl, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(onTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(offWaitTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        onOffControl = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        onTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        offWaitTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(113);
        builder.append("OnWithTimedOffCommand [");
        builder.append(super.toString());
        builder.append(", onOffControl=");
        builder.append(onOffControl);
        builder.append(", onTime=");
        builder.append(onTime);
        builder.append(", offWaitTime=");
        builder.append(offWaitTime);
        builder.append(']');
        return builder.toString();
    }

}
