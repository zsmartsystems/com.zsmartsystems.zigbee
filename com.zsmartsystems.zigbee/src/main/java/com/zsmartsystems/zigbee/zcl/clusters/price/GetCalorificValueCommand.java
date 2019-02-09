/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.price;

import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Calorific Value Command value object class.
 * <p>
 * Cluster: <b>Price</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Price cluster.
 * <p>
 * This command initiates a PublishCalorificValue command(s) for scheduled calorific value
 * updates. A server device shall be capable of storing at least two instances, the current and
 * (if available) next instance to be activated in the future. <br> A ZCL Default response with
 * status NOT_FOUND shall be returned if there are no conversion factor updates available
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetCalorificValueCommand extends ZclCommand {
    /**
     * Earliest Start Time command message field.
     * <p>
     * UTCTime stamp indicating the earliest start time of values to be returned by the
     * corresponding PublishCalorificValue command. The first returned
     * PublishCalorificValue command shall be the instance which is active or becomes active
     * at or after the stated Earliest Start Time. If more than one instance is requested, the
     * active and scheduled instances shall be sent with ascending ordered Start Time.
     */
    private Calendar earliestStartTime;

    /**
     * Min . Issuer Event ID command message field.
     * <p>
     * A 32-bit integer representing the minimum Issuer Event ID of values to be returned by the
     * corresponding PublishCalorificValue command. A value of 0xFFFFFFFF means not
     * specified; the server shall return values irrespective of the value of the Issuer Event
     * ID.
     */
    private Integer minIssuerEventId;

    /**
     * Number Of Commands command message field.
     * <p>
     * An 8-bit Integer which represents the maximum number of PublishCalorificValue
     * commands that the CLIENT is willing to receive in response to this command. A value of 0
     * would indicate all available PublishCalorificValue commands shall be returned.
     */
    private Integer numberOfCommands;

    /**
     * Default constructor.
     */
    public GetCalorificValueCommand() {
        genericCommand = false;
        clusterId = 0x0700;
        commandId = 5;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Earliest Start Time.
     * <p>
     * UTCTime stamp indicating the earliest start time of values to be returned by the
     * corresponding PublishCalorificValue command. The first returned
     * PublishCalorificValue command shall be the instance which is active or becomes active
     * at or after the stated Earliest Start Time. If more than one instance is requested, the
     * active and scheduled instances shall be sent with ascending ordered Start Time.
     *
     * @return the Earliest Start Time
     */
    public Calendar getEarliestStartTime() {
        return earliestStartTime;
    }

    /**
     * Sets Earliest Start Time.
     * <p>
     * UTCTime stamp indicating the earliest start time of values to be returned by the
     * corresponding PublishCalorificValue command. The first returned
     * PublishCalorificValue command shall be the instance which is active or becomes active
     * at or after the stated Earliest Start Time. If more than one instance is requested, the
     * active and scheduled instances shall be sent with ascending ordered Start Time.
     *
     * @param earliestStartTime the Earliest Start Time
     */
    public void setEarliestStartTime(final Calendar earliestStartTime) {
        this.earliestStartTime = earliestStartTime;
    }

    /**
     * Gets Min . Issuer Event ID.
     * <p>
     * A 32-bit integer representing the minimum Issuer Event ID of values to be returned by the
     * corresponding PublishCalorificValue command. A value of 0xFFFFFFFF means not
     * specified; the server shall return values irrespective of the value of the Issuer Event
     * ID.
     *
     * @return the Min . Issuer Event ID
     */
    public Integer getMinIssuerEventId() {
        return minIssuerEventId;
    }

    /**
     * Sets Min . Issuer Event ID.
     * <p>
     * A 32-bit integer representing the minimum Issuer Event ID of values to be returned by the
     * corresponding PublishCalorificValue command. A value of 0xFFFFFFFF means not
     * specified; the server shall return values irrespective of the value of the Issuer Event
     * ID.
     *
     * @param minIssuerEventId the Min . Issuer Event ID
     */
    public void setMinIssuerEventId(final Integer minIssuerEventId) {
        this.minIssuerEventId = minIssuerEventId;
    }

    /**
     * Gets Number Of Commands.
     * <p>
     * An 8-bit Integer which represents the maximum number of PublishCalorificValue
     * commands that the CLIENT is willing to receive in response to this command. A value of 0
     * would indicate all available PublishCalorificValue commands shall be returned.
     *
     * @return the Number Of Commands
     */
    public Integer getNumberOfCommands() {
        return numberOfCommands;
    }

    /**
     * Sets Number Of Commands.
     * <p>
     * An 8-bit Integer which represents the maximum number of PublishCalorificValue
     * commands that the CLIENT is willing to receive in response to this command. A value of 0
     * would indicate all available PublishCalorificValue commands shall be returned.
     *
     * @param numberOfCommands the Number Of Commands
     */
    public void setNumberOfCommands(final Integer numberOfCommands) {
        this.numberOfCommands = numberOfCommands;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(earliestStartTime, ZclDataType.UTCTIME);
        serializer.serialize(minIssuerEventId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(numberOfCommands, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        earliestStartTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        minIssuerEventId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        numberOfCommands = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(136);
        builder.append("GetCalorificValueCommand [");
        builder.append(super.toString());
        builder.append(", earliestStartTime=");
        builder.append(earliestStartTime);
        builder.append(", minIssuerEventId=");
        builder.append(minIssuerEventId);
        builder.append(", numberOfCommands=");
        builder.append(numberOfCommands);
        builder.append(']');
        return builder.toString();
    }

}
