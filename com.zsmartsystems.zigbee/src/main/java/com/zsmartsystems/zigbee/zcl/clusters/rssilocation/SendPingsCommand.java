package com.zsmartsystems.zigbee.zcl.clusters.rssilocation;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * Send Pings Command value object class.
 * </p>
 * <p>
 * Cluster: <b>RSSI Location</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the RSSI Location cluster.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class SendPingsCommand extends ZclCommand {
    /**
     * Target Address command message field.
     */
    private Long targetAddress;

    /**
     * Number RSSI Measurements command message field.
     */
    private Integer numberRssiMeasurements;

    /**
     * Calculation Period command message field.
     */
    private Integer calculationPeriod;

    /**
     * Default constructor setting the command type field.
     */
    public SendPingsCommand() {
        genericCommand = false;
        clusterId = 11;
        commandId = 5;
        commandDirection = true;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public SendPingsCommand(final Map<Integer, Object> fields) {
        this();
        targetAddress = (Long) fields.get(0);
        numberRssiMeasurements = (Integer) fields.get(1);
        calculationPeriod = (Integer) fields.get(2);
    }

    /**
     * Gets Target Address.
     * @return the Target Address
     */
    public Long getTargetAddress() {
        return targetAddress;
    }

    /**
     * Sets Target Address.
     * @param targetAddress the Target Address
     */
    public void setTargetAddress(final Long targetAddress) {
        this.targetAddress = targetAddress;
    }

    /**
     * Gets Number RSSI Measurements.
     * @return the Number RSSI Measurements
     */
    public Integer getNumberRssiMeasurements() {
        return numberRssiMeasurements;
    }

    /**
     * Sets Number RSSI Measurements.
     * @param numberRssiMeasurements the Number RSSI Measurements
     */
    public void setNumberRssiMeasurements(final Integer numberRssiMeasurements) {
        this.numberRssiMeasurements = numberRssiMeasurements;
    }

    /**
     * Gets Calculation Period.
     * @return the Calculation Period
     */
    public Integer getCalculationPeriod() {
        return calculationPeriod;
    }

    /**
     * Sets Calculation Period.
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
        targetAddress = (Long) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        numberRssiMeasurements = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        calculationPeriod = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", ");
        builder.append("targetAddress = ");
        builder.append(targetAddress);
        builder.append(", ");
        builder.append("numberRssiMeasurements = ");
        builder.append(numberRssiMeasurements);
        builder.append(", ");
        builder.append("calculationPeriod = ");
        builder.append(calculationPeriod);
        return builder.toString();
    }

}
