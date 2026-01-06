/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.metering;

import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Request Fast Poll Mode Response value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command ID 0x03 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * This command is generated when the client command Request Fast Poll Mode is received.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class RequestFastPollModeResponse extends ZclMeteringCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0702;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x03;

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
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public RequestFastPollModeResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param appliedUpdatePeriod {@link Integer} Applied Update Period
     * @param fastPollModeEndtime {@link Calendar} Fast Poll Mode Endtime
     */
    public RequestFastPollModeResponse(
            Integer appliedUpdatePeriod,
            Calendar fastPollModeEndtime) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.appliedUpdatePeriod = appliedUpdatePeriod;
        this.fastPollModeEndtime = fastPollModeEndtime;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
        appliedUpdatePeriod = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        fastPollModeEndtime = deserializer.deserialize(ZclDataType.UTCTIME);
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
