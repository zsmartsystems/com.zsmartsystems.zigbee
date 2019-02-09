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
 * Get Tariff Information Command value object class.
 * <p>
 * Cluster: <b>Price</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Price cluster.
 * <p>
 * This command initiates PublishTariffInformation command(s) for scheduled tariff
 * updates. A server device shall be capable of storing at least two instances, current and the
 * next instance to be activated in the future. <br> One or more PublishTariffInformation
 * commands are sent in response to this command. To obtain the complete tariff details,
 * further GetPriceMatrix and GetBlockThesholds commands must be sent using the start time
 * and IssuerTariffID obtained from the appropriate PublishTariffInformation command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetTariffInformationCommand extends ZclCommand {
    /**
     * Earliest Start Time command message field.
     * <p>
     * UTCTime stamp indicating the earliest start time of tariffs to be returned by the
     * corresponding PublishTariffInformation command. The first returned
     * PublishTariffInformation command shall be the instance which is active or becomes
     * active at or after the stated EarliestStartTime. If more than one command is requested,
     * the active and scheduled commands shall be sent with ascending ordered StartTime.
     */
    private Calendar earliestStartTime;

    /**
     * Min . Issuer Event ID command message field.
     * <p>
     * A 32-bit integer representing the minimum Issuer Event ID of tariffs to be returned by
     * the corresponding PublishTariffInformation command. A value of 0xFFFFFFFF means not
     * specified; the server shall return tariffs irrespective of the value of the Issuer
     * Event ID.
     */
    private Integer minIssuerEventId;

    /**
     * Number Of Commands command message field.
     * <p>
     * An 8-bit integer which represents the maximum number of PublishTariffInformation
     * commands that the CLIENT is willing to receive in response to this command. A value of 0
     * would indicate all available PublishTariffInformation commands shall be returned.
     */
    private Integer numberOfCommands;

    /**
     * Tariff Type command message field.
     * <p>
     * An 8-bit bitmap identifying the type of tariff published in this command. The least
     * significant nibble represents an enumeration of the tariff type (Generation Meters
     * shall use the ‘Received’ Tariff.). The most significant nibble is reserved.
     */
    private Integer tariffType;

    /**
     * Default constructor.
     */
    public GetTariffInformationCommand() {
        genericCommand = false;
        clusterId = 0x0700;
        commandId = 6;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Earliest Start Time.
     * <p>
     * UTCTime stamp indicating the earliest start time of tariffs to be returned by the
     * corresponding PublishTariffInformation command. The first returned
     * PublishTariffInformation command shall be the instance which is active or becomes
     * active at or after the stated EarliestStartTime. If more than one command is requested,
     * the active and scheduled commands shall be sent with ascending ordered StartTime.
     *
     * @return the Earliest Start Time
     */
    public Calendar getEarliestStartTime() {
        return earliestStartTime;
    }

    /**
     * Sets Earliest Start Time.
     * <p>
     * UTCTime stamp indicating the earliest start time of tariffs to be returned by the
     * corresponding PublishTariffInformation command. The first returned
     * PublishTariffInformation command shall be the instance which is active or becomes
     * active at or after the stated EarliestStartTime. If more than one command is requested,
     * the active and scheduled commands shall be sent with ascending ordered StartTime.
     *
     * @param earliestStartTime the Earliest Start Time
     */
    public void setEarliestStartTime(final Calendar earliestStartTime) {
        this.earliestStartTime = earliestStartTime;
    }

    /**
     * Gets Min . Issuer Event ID.
     * <p>
     * A 32-bit integer representing the minimum Issuer Event ID of tariffs to be returned by
     * the corresponding PublishTariffInformation command. A value of 0xFFFFFFFF means not
     * specified; the server shall return tariffs irrespective of the value of the Issuer
     * Event ID.
     *
     * @return the Min . Issuer Event ID
     */
    public Integer getMinIssuerEventId() {
        return minIssuerEventId;
    }

    /**
     * Sets Min . Issuer Event ID.
     * <p>
     * A 32-bit integer representing the minimum Issuer Event ID of tariffs to be returned by
     * the corresponding PublishTariffInformation command. A value of 0xFFFFFFFF means not
     * specified; the server shall return tariffs irrespective of the value of the Issuer
     * Event ID.
     *
     * @param minIssuerEventId the Min . Issuer Event ID
     */
    public void setMinIssuerEventId(final Integer minIssuerEventId) {
        this.minIssuerEventId = minIssuerEventId;
    }

    /**
     * Gets Number Of Commands.
     * <p>
     * An 8-bit integer which represents the maximum number of PublishTariffInformation
     * commands that the CLIENT is willing to receive in response to this command. A value of 0
     * would indicate all available PublishTariffInformation commands shall be returned.
     *
     * @return the Number Of Commands
     */
    public Integer getNumberOfCommands() {
        return numberOfCommands;
    }

    /**
     * Sets Number Of Commands.
     * <p>
     * An 8-bit integer which represents the maximum number of PublishTariffInformation
     * commands that the CLIENT is willing to receive in response to this command. A value of 0
     * would indicate all available PublishTariffInformation commands shall be returned.
     *
     * @param numberOfCommands the Number Of Commands
     */
    public void setNumberOfCommands(final Integer numberOfCommands) {
        this.numberOfCommands = numberOfCommands;
    }

    /**
     * Gets Tariff Type.
     * <p>
     * An 8-bit bitmap identifying the type of tariff published in this command. The least
     * significant nibble represents an enumeration of the tariff type (Generation Meters
     * shall use the ‘Received’ Tariff.). The most significant nibble is reserved.
     *
     * @return the Tariff Type
     */
    public Integer getTariffType() {
        return tariffType;
    }

    /**
     * Sets Tariff Type.
     * <p>
     * An 8-bit bitmap identifying the type of tariff published in this command. The least
     * significant nibble represents an enumeration of the tariff type (Generation Meters
     * shall use the ‘Received’ Tariff.). The most significant nibble is reserved.
     *
     * @param tariffType the Tariff Type
     */
    public void setTariffType(final Integer tariffType) {
        this.tariffType = tariffType;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(earliestStartTime, ZclDataType.UTCTIME);
        serializer.serialize(minIssuerEventId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(numberOfCommands, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(tariffType, ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        earliestStartTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        minIssuerEventId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        numberOfCommands = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        tariffType = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(169);
        builder.append("GetTariffInformationCommand [");
        builder.append(super.toString());
        builder.append(", earliestStartTime=");
        builder.append(earliestStartTime);
        builder.append(", minIssuerEventId=");
        builder.append(minIssuerEventId);
        builder.append(", numberOfCommands=");
        builder.append(numberOfCommands);
        builder.append(", tariffType=");
        builder.append(tariffType);
        builder.append(']');
        return builder.toString();
    }

}
