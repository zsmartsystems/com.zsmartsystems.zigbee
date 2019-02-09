/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.rssilocation;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Send Pings Command value object class.
 * <p>
 * Cluster: <b>RSSI Location</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the RSSI Location cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class SendPingsCommand extends ZclCommand {
    /**
     * Target Address command message field.
     */
    private IeeeAddress targetAddress;

    /**
     * Number RSSI Measurements command message field.
     */
    private Integer numberRssiMeasurements;

    /**
     * Calculation Period command message field.
     */
    private Integer calculationPeriod;

    /**
     * Default constructor.
     */
    public SendPingsCommand() {
        genericCommand = false;
        clusterId = 0x000B;
        commandId = 5;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Target Address.
     *
     * @return the Target Address
     */
    public IeeeAddress getTargetAddress() {
        return targetAddress;
    }

    /**
     * Sets Target Address.
     *
     * @param targetAddress the Target Address
     */
    public void setTargetAddress(final IeeeAddress targetAddress) {
        this.targetAddress = targetAddress;
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
     */
    public void setNumberRssiMeasurements(final Integer numberRssiMeasurements) {
        this.numberRssiMeasurements = numberRssiMeasurements;
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
     */
    public void setCalculationPeriod(final Integer calculationPeriod) {
        this.calculationPeriod = calculationPeriod;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(targetAddress, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(numberRssiMeasurements, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(calculationPeriod, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        targetAddress = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        numberRssiMeasurements = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        calculationPeriod = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(131);
        builder.append("SendPingsCommand [");
        builder.append(super.toString());
        builder.append(", targetAddress=");
        builder.append(targetAddress);
        builder.append(", numberRssiMeasurements=");
        builder.append(numberRssiMeasurements);
        builder.append(", calculationPeriod=");
        builder.append(calculationPeriod);
        builder.append(']');
        return builder.toString();
    }

}
