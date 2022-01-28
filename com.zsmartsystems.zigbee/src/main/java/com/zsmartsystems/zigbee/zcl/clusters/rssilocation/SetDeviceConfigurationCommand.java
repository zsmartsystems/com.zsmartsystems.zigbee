/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.rssilocation;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Set Device Configuration Command value object class.
 * <p>
 * Cluster: <b>RSSI Location</b>. Command ID 0x01 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the RSSI Location cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class SetDeviceConfigurationCommand extends ZclRssiLocationCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x000B;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x01;

    /**
     * Power command message field.
     */
    private Integer power;

    /**
     * Path Loss Exponent command message field.
     */
    private Integer pathLossExponent;

    /**
     * Calculation Period command message field.
     */
    private Integer calculationPeriod;

    /**
     * Number RSSI Measurements command message field.
     */
    private Integer numberRssiMeasurements;

    /**
     * Reporting Period command message field.
     */
    private Integer reportingPeriod;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public SetDeviceConfigurationCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param power {@link Integer} Power
     * @param pathLossExponent {@link Integer} Path Loss Exponent
     * @param calculationPeriod {@link Integer} Calculation Period
     * @param numberRssiMeasurements {@link Integer} Number RSSI Measurements
     * @param reportingPeriod {@link Integer} Reporting Period
     */
    public SetDeviceConfigurationCommand(
            Integer power,
            Integer pathLossExponent,
            Integer calculationPeriod,
            Integer numberRssiMeasurements,
            Integer reportingPeriod) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.power = power;
        this.pathLossExponent = pathLossExponent;
        this.calculationPeriod = calculationPeriod;
        this.numberRssiMeasurements = numberRssiMeasurements;
        this.reportingPeriod = reportingPeriod;
    }

    /**
     * Gets Power.
     *
     * @return the Power
     */
    public Integer getPower() {
        return power;
    }

    /**
     * Sets Power.
     *
     * @param power the Power
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setPower(final Integer power) {
        this.power = power;
    }

    /**
     * Gets Path Loss Exponent.
     *
     * @return the Path Loss Exponent
     */
    public Integer getPathLossExponent() {
        return pathLossExponent;
    }

    /**
     * Sets Path Loss Exponent.
     *
     * @param pathLossExponent the Path Loss Exponent
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setPathLossExponent(final Integer pathLossExponent) {
        this.pathLossExponent = pathLossExponent;
    }

    /**
     * Gets Calculation Period.
     *
     * @return the Calculation Period
     */
    public Integer getCalculationPeriod() {
        return calculationPeriod;
    }

    /**
     * Sets Calculation Period.
     *
     * @param calculationPeriod the Calculation Period
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setCalculationPeriod(final Integer calculationPeriod) {
        this.calculationPeriod = calculationPeriod;
    }

    /**
     * Gets Number RSSI Measurements.
     *
     * @return the Number RSSI Measurements
     */
    public Integer getNumberRssiMeasurements() {
        return numberRssiMeasurements;
    }

    /**
     * Sets Number RSSI Measurements.
     *
     * @param numberRssiMeasurements the Number RSSI Measurements
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setNumberRssiMeasurements(final Integer numberRssiMeasurements) {
        this.numberRssiMeasurements = numberRssiMeasurements;
    }

    /**
     * Gets Reporting Period.
     *
     * @return the Reporting Period
     */
    public Integer getReportingPeriod() {
        return reportingPeriod;
    }

    /**
     * Sets Reporting Period.
     *
     * @param reportingPeriod the Reporting Period
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setReportingPeriod(final Integer reportingPeriod) {
        this.reportingPeriod = reportingPeriod;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(power, ZclDataType.SIGNED_16_BIT_INTEGER);
        serializer.serialize(pathLossExponent, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(calculationPeriod, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(numberRssiMeasurements, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(reportingPeriod, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        power = (Integer) deserializer.deserialize(ZclDataType.SIGNED_16_BIT_INTEGER);
        pathLossExponent = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        calculationPeriod = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        numberRssiMeasurements = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        reportingPeriod = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(207);
        builder.append("SetDeviceConfigurationCommand [");
        builder.append(super.toString());
        builder.append(", power=");
        builder.append(power);
        builder.append(", pathLossExponent=");
        builder.append(pathLossExponent);
        builder.append(", calculationPeriod=");
        builder.append(calculationPeriod);
        builder.append(", numberRssiMeasurements=");
        builder.append(numberRssiMeasurements);
        builder.append(", reportingPeriod=");
        builder.append(reportingPeriod);
        builder.append(']');
        return builder.toString();
    }

}
