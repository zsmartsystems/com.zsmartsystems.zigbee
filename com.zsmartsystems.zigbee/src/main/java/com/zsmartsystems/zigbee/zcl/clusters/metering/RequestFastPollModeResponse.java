/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.metering;

import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Request Fast Poll Mode Response value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * This command is generated when the client command Request Fast Poll Mode is received.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class RequestFastPollModeResponse extends ZclCommand {
    /**
     * Applied Update Period command message field.
     * <p>
     * The period at which metering data shall be updated. This may be different than the
     * requested fast poll. If the Request Fast Poll Rate is less than Fast Poll Update Period
     * Attribute, it shall use the Fast Poll Update Period Attribute. Otherwise, the Applied
     * Update Period shall be greater than or equal to the minimum Fast Poll Update Period
     * Attribute and less than or equal to the Requested Fast Poll Rate.
     */
    private Integer appliedUpdatePeriod;

    /**
     * Fast Poll Mode Endtime command message field.
     * <p>
     * UTC time that indicates when the metering server will terminate fast poll mode and
     * resume updating at the rate specified by DefaultUpdatePeriod. For example, one or more
     * metering clients may request fast poll mode while the metering server is already in fast
     * poll mode. The intent is that the fast poll mode will not be extended since this scenario
     * would make it possible to be in fast poll mode longer than 15 minutes.
     */
    private Calendar fastPollModeEndtime;

    /**
     * Default constructor.
     */
    public RequestFastPollModeResponse() {
        genericCommand = false;
        clusterId = 0x0702;
        commandId = 3;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Applied Update Period.
     * <p>
     * The period at which metering data shall be updated. This may be different than the
     * requested fast poll. If the Request Fast Poll Rate is less than Fast Poll Update Period
     * Attribute, it shall use the Fast Poll Update Period Attribute. Otherwise, the Applied
     * Update Period shall be greater than or equal to the minimum Fast Poll Update Period
     * Attribute and less than or equal to the Requested Fast Poll Rate.
     *
     * @return the Applied Update Period
     */
    public Integer getAppliedUpdatePeriod() {
        return appliedUpdatePeriod;
    }

    /**
     * Sets Applied Update Period.
     * <p>
     * The period at which metering data shall be updated. This may be different than the
     * requested fast poll. If the Request Fast Poll Rate is less than Fast Poll Update Period
     * Attribute, it shall use the Fast Poll Update Period Attribute. Otherwise, the Applied
     * Update Period shall be greater than or equal to the minimum Fast Poll Update Period
     * Attribute and less than or equal to the Requested Fast Poll Rate.
     *
     * @param appliedUpdatePeriod the Applied Update Period
     */
    public void setAppliedUpdatePeriod(final Integer appliedUpdatePeriod) {
        this.appliedUpdatePeriod = appliedUpdatePeriod;
    }

    /**
     * Gets Fast Poll Mode Endtime.
     * <p>
     * UTC time that indicates when the metering server will terminate fast poll mode and
     * resume updating at the rate specified by DefaultUpdatePeriod. For example, one or more
     * metering clients may request fast poll mode while the metering server is already in fast
     * poll mode. The intent is that the fast poll mode will not be extended since this scenario
     * would make it possible to be in fast poll mode longer than 15 minutes.
     *
     * @return the Fast Poll Mode Endtime
     */
    public Calendar getFastPollModeEndtime() {
        return fastPollModeEndtime;
    }

    /**
     * Sets Fast Poll Mode Endtime.
     * <p>
     * UTC time that indicates when the metering server will terminate fast poll mode and
     * resume updating at the rate specified by DefaultUpdatePeriod. For example, one or more
     * metering clients may request fast poll mode while the metering server is already in fast
     * poll mode. The intent is that the fast poll mode will not be extended since this scenario
     * would make it possible to be in fast poll mode longer than 15 minutes.
     *
     * @param fastPollModeEndtime the Fast Poll Mode Endtime
     */
    public void setFastPollModeEndtime(final Calendar fastPollModeEndtime) {
        this.fastPollModeEndtime = fastPollModeEndtime;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(appliedUpdatePeriod, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(fastPollModeEndtime, ZclDataType.UTCTIME);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        appliedUpdatePeriod = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        fastPollModeEndtime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(108);
        builder.append("RequestFastPollModeResponse [");
        builder.append(super.toString());
        builder.append(", appliedUpdatePeriod=");
        builder.append(appliedUpdatePeriod);
        builder.append(", fastPollModeEndtime=");
        builder.append(fastPollModeEndtime);
        builder.append(']');
        return builder.toString();
    }

}
