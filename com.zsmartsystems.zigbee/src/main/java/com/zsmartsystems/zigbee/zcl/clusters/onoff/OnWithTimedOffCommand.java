/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.onoff;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * On With Timed Off Command value object class.
 * <p>
 * Cluster: <b>On/Off</b>. Command ID 0x42 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the On/Off cluster.
 * <p>
 * The On With Timed Off command allows devices to be turned on for a specific duration with a
 * guarded off duration so that should the device be subsequently switched off, further On With
 * Timed Off commands, received during this time, are prevented from turning the devices back
 * on. Note that the device can be periodically re-kicked by subsequent On With Timed Off
 * commands, e.g., from an on/off sensor.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class OnWithTimedOffCommand extends ZclOnOffCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0006;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x42;

    /**
     * On Off Control command message field.
     * <p>
     * The On/Off Control field is 8-bits in length and contains information on how the device
     * is to be operated.
     */
    private Integer onOffControl;

    /**
     * On Time command message field.
     * <p>
     * The On Time field is 16 bits in length and specifies the length of time (in 1/10ths second)
     * that the device is to remain “on”, i.e., with its OnOffattribute equal to 0x01, before
     * automatically turning “off”. This field shall be specified in the range
     * 0x0000–0xfffe. The Off Wait Time field is 16 bits in length and specifies the length of
     * time (in 1/10ths second) that the device shall remain “off”, i.e., with its
     * OnOffattribute equal to 0x00, and guarded to prevent an on command turning the device
     * back “on”. This field shall be specified in the range 0x0000–0xfffe.
     */
    private Integer onTime;

    /**
     * Off Wait Time command message field.
     */
    private Integer offWaitTime;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public OnWithTimedOffCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param onOffControl {@link Integer} On Off Control
     * @param onTime {@link Integer} On Time
     * @param offWaitTime {@link Integer} Off Wait Time
     */
    public OnWithTimedOffCommand(
            Integer onOffControl,
            Integer onTime,
            Integer offWaitTime) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.onOffControl = onOffControl;
        this.onTime = onTime;
        this.offWaitTime = offWaitTime;
    }

    /**
     * Gets On Off Control.
     * <p>
     * The On/Off Control field is 8-bits in length and contains information on how the device
     * is to be operated.
     *
     * @return the On Off Control
     */
    public Integer getOnOffControl() {
        return onOffControl;
    }

    /**
     * Sets On Off Control.
     * <p>
     * The On/Off Control field is 8-bits in length and contains information on how the device
     * is to be operated.
     *
     * @param onOffControl the On Off Control
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setOnOffControl(final Integer onOffControl) {
        this.onOffControl = onOffControl;
    }

    /**
     * Gets On Time.
     * <p>
     * The On Time field is 16 bits in length and specifies the length of time (in 1/10ths second)
     * that the device is to remain “on”, i.e., with its OnOffattribute equal to 0x01, before
     * automatically turning “off”. This field shall be specified in the range
     * 0x0000–0xfffe. The Off Wait Time field is 16 bits in length and specifies the length of
     * time (in 1/10ths second) that the device shall remain “off”, i.e., with its
     * OnOffattribute equal to 0x00, and guarded to prevent an on command turning the device
     * back “on”. This field shall be specified in the range 0x0000–0xfffe.
     *
     * @return the On Time
     */
    public Integer getOnTime() {
        return onTime;
    }

    /**
     * Sets On Time.
     * <p>
     * The On Time field is 16 bits in length and specifies the length of time (in 1/10ths second)
     * that the device is to remain “on”, i.e., with its OnOffattribute equal to 0x01, before
     * automatically turning “off”. This field shall be specified in the range
     * 0x0000–0xfffe. The Off Wait Time field is 16 bits in length and specifies the length of
     * time (in 1/10ths second) that the device shall remain “off”, i.e., with its
     * OnOffattribute equal to 0x00, and guarded to prevent an on command turning the device
     * back “on”. This field shall be specified in the range 0x0000–0xfffe.
     *
     * @param onTime the On Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
