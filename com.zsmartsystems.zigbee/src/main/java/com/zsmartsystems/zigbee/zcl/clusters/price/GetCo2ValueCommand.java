/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.price;

import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get CO2 Value Command value object class.
 * <p>
 * Cluster: <b>Price</b>. Command ID 0x09 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Price cluster.
 * <p>
 * This command initiates PublishCO2Value command(s) for scheduled CO2 conversion factor
 * updates. A server device shall be capable of storing at least two instances, current and (if
 * available) next instance to be activated in the future.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class GetCo2ValueCommand extends ZclPriceCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0700;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x09;

    /**
     * Earliest Start Time command message field.
     * <p>
     * UTCTime stamp indicating the earliest start time of values to be returned by the
     * corresponding PublishCO2Value command. The first returned PublishCO2Value command
     * shall be the instance which is active or becomes active at or after the stated
     * EarliestStartTime. If more than one instance is requested, the active and scheduled
     * instances shall be sent with ascending ordered StartTime.
     */
    private Calendar earliestStartTime;

    /**
     * Min . Issuer Event ID command message field.
     * <p>
     * A 32-bit integer representing the minimum Issuer Event ID of values to be returned by the
     * corresponding PublishCO2Value command. A value of 0xFFFFFFFF means not specified;
     * the server shall return values irrespective of the value of the Issuer Event ID.
     */
    private Integer minIssuerEventId;

    /**
     * Number Of Commands command message field.
     * <p>
     * An 8-bit Integer which represents the maximum number of PublishCO2Value commands that
     * the CLIENT is willing to receive in response to this command. A value of 0 would indicate
     * all available PublishCO2Value commands shall be returned.
     */
    private Integer numberOfCommands;

    /**
     * Tariff Type command message field.
     * <p>
     * An optional 8-bit bitmap identifying the type of tariff published in this command. The
     * least significant nibble represents an enumeration of the tariff type (Generation
     * Meters shall use the ‘Received’ Tariff). A value of 0xFF means not specified. If the
     * TariffType is not specified, the server shall return all C02 values regardless of
     * tariff type. The most significant nibble is reserved.
     */
    private Integer tariffType;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GetCo2ValueCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param earliestStartTime {@link Calendar} Earliest Start Time
     * @param minIssuerEventId {@link Integer} Min . Issuer Event ID
     * @param numberOfCommands {@link Integer} Number Of Commands
     * @param tariffType {@link Integer} Tariff Type
     */
    public GetCo2ValueCommand(
            Calendar earliestStartTime,
            Integer minIssuerEventId,
            Integer numberOfCommands,
            Integer tariffType) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.earliestStartTime = earliestStartTime;
        this.minIssuerEventId = minIssuerEventId;
        this.numberOfCommands = numberOfCommands;
        this.tariffType = tariffType;
    }

    /**
     * Gets Earliest Start Time.
     * <p>
     * UTCTime stamp indicating the earliest start time of values to be returned by the
     * corresponding PublishCO2Value command. The first returned PublishCO2Value command
     * shall be the instance which is active or becomes active at or after the stated
     * EarliestStartTime. If more than one instance is requested, the active and scheduled
     * instances shall be sent with ascending ordered StartTime.
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
     * corresponding PublishCO2Value command. The first returned PublishCO2Value command
     * shall be the instance which is active or becomes active at or after the stated
     * EarliestStartTime. If more than one instance is requested, the active and scheduled
     * instances shall be sent with ascending ordered StartTime.
     *
     * @param earliestStartTime the Earliest Start Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setEarliestStartTime(final Calendar earliestStartTime) {
        this.earliestStartTime = earliestStartTime;
    }

    /**
     * Gets Min . Issuer Event ID.
     * <p>
     * A 32-bit integer representing the minimum Issuer Event ID of values to be returned by the
     * corresponding PublishCO2Value command. A value of 0xFFFFFFFF means not specified;
     * the server shall return values irrespective of the value of the Issuer Event ID.
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
     * corresponding PublishCO2Value command. A value of 0xFFFFFFFF means not specified;
     * the server shall return values irrespective of the value of the Issuer Event ID.
     *
     * @param minIssuerEventId the Min . Issuer Event ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setMinIssuerEventId(final Integer minIssuerEventId) {
        this.minIssuerEventId = minIssuerEventId;
    }

    /**
     * Gets Number Of Commands.
     * <p>
     * An 8-bit Integer which represents the maximum number of PublishCO2Value commands that
     * the CLIENT is willing to receive in response to this command. A value of 0 would indicate
     * all available PublishCO2Value commands shall be returned.
     *
     * @return the Number Of Commands
     */
    public Integer getNumberOfCommands() {
        return numberOfCommands;
    }

    /**
     * Sets Number Of Commands.
     * <p>
     * An 8-bit Integer which represents the maximum number of PublishCO2Value commands that
     * the CLIENT is willing to receive in response to this command. A value of 0 would indicate
     * all available PublishCO2Value commands shall be returned.
     *
     * @param numberOfCommands the Number Of Commands
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setNumberOfCommands(final Integer numberOfCommands) {
        this.numberOfCommands = numberOfCommands;
    }

    /**
     * Gets Tariff Type.
     * <p>
     * An optional 8-bit bitmap identifying the type of tariff published in this command. The
     * least significant nibble represents an enumeration of the tariff type (Generation
     * Meters shall use the ‘Received’ Tariff). A value of 0xFF means not specified. If the
     * TariffType is not specified, the server shall return all C02 values regardless of
     * tariff type. The most significant nibble is reserved.
     *
     * @return the Tariff Type
     */
    public Integer getTariffType() {
        return tariffType;
    }

    /**
     * Sets Tariff Type.
     * <p>
     * An optional 8-bit bitmap identifying the type of tariff published in this command. The
     * least significant nibble represents an enumeration of the tariff type (Generation
     * Meters shall use the ‘Received’ Tariff). A value of 0xFF means not specified. If the
     * TariffType is not specified, the server shall return all C02 values regardless of
     * tariff type. The most significant nibble is reserved.
     *
     * @param tariffType the Tariff Type
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
        final StringBuilder builder = new StringBuilder(160);
        builder.append("GetCo2ValueCommand [");
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
