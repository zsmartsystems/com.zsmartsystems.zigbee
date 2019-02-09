/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.metering;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Set Uncontrolled Flow Threshold value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * This command is used to update the 'Uncontrolled Flow Rate' configuration data used by flow
 * meters.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class SetUncontrolledFlowThreshold extends ZclCommand {
    /**
     * Provider ID command message field.
     * <p>
     * An unsigned 32-bit field containing a unique identifier for the commodity provider to
     * whom this command relates.
     */
    private Integer providerId;

    /**
     * Issuer Event ID command message field.
     * <p>
     * Unique identifier generated by the commodity provider. When new information is
     * provided that replaces older information for the same time period, this field allows
     * devices to determine which information is newer. The value contained in this field is a
     * unique number managed by upstream servers or a UTC based time stamp (UTCTime data type)
     * identifying when the command was issued. Thus, newer information will have a value in
     * the Issuer Event ID field that is larger than older information.
     */
    private Integer issuerEventId;

    /**
     * Uncontrolled Flow Threshold command message field.
     * <p>
     * The threshold above which a flow meter (e.g. Gas or Water) shall detect an uncontrolled
     * flow. A value of 0x0000 indicates the feature in unused.
     */
    private Integer uncontrolledFlowThreshold;

    /**
     * Unit Of Measure command message field.
     * <p>
     * An enumeration indicating the unit of measure to be used in conjunction with the
     * Uncontrolled Flow Threshold attribute. The enumeration used for this field shall
     * match one of the UnitOfMeasure values using a pure binary format as defined in the
     * Metering cluster.
     */
    private Integer unitOfMeasure;

    /**
     * Multiplier command message field.
     * <p>
     * An unsigned 16-bit value indicating the multiplier, to be used in conjunction with the
     * Uncontrolled Flow Threshold and Divisor fields, to determine the true flow threshold
     * value. A value of 0x0000 is not allowed.
     */
    private Integer multiplier;

    /**
     * Divisor command message field.
     * <p>
     * An unsigned 16-bit value indicating the divisor, to be used in conjunction with the
     * Uncontrolled Flow Threshold and Multiplier fields, to determine the true flow
     * threshold value. A value of 0x0000 is not allowed.
     */
    private Integer divisor;

    /**
     * Stabilisation Period command message field.
     * <p>
     * An unsigned 8-bit value indicating the time given to allow the flow to stabilize. It is
     * defined in units of tenths of a second.
     */
    private Integer stabilisationPeriod;

    /**
     * Measurement Period command message field.
     * <p>
     * An unsigned 16-bit value indicating the period over which the flow is measured and
     * compared against the Uncontrolled Flow Threshold value. It is defined in units of 1
     * second.
     */
    private Integer measurementPeriod;

    /**
     * Default constructor.
     */
    public SetUncontrolledFlowThreshold() {
        genericCommand = false;
        clusterId = 0x0702;
        commandId = 14;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Provider ID.
     * <p>
     * An unsigned 32-bit field containing a unique identifier for the commodity provider to
     * whom this command relates.
     *
     * @return the Provider ID
     */
    public Integer getProviderId() {
        return providerId;
    }

    /**
     * Sets Provider ID.
     * <p>
     * An unsigned 32-bit field containing a unique identifier for the commodity provider to
     * whom this command relates.
     *
     * @param providerId the Provider ID
     */
    public void setProviderId(final Integer providerId) {
        this.providerId = providerId;
    }

    /**
     * Gets Issuer Event ID.
     * <p>
     * Unique identifier generated by the commodity provider. When new information is
     * provided that replaces older information for the same time period, this field allows
     * devices to determine which information is newer. The value contained in this field is a
     * unique number managed by upstream servers or a UTC based time stamp (UTCTime data type)
     * identifying when the command was issued. Thus, newer information will have a value in
     * the Issuer Event ID field that is larger than older information.
     *
     * @return the Issuer Event ID
     */
    public Integer getIssuerEventId() {
        return issuerEventId;
    }

    /**
     * Sets Issuer Event ID.
     * <p>
     * Unique identifier generated by the commodity provider. When new information is
     * provided that replaces older information for the same time period, this field allows
     * devices to determine which information is newer. The value contained in this field is a
     * unique number managed by upstream servers or a UTC based time stamp (UTCTime data type)
     * identifying when the command was issued. Thus, newer information will have a value in
     * the Issuer Event ID field that is larger than older information.
     *
     * @param issuerEventId the Issuer Event ID
     */
    public void setIssuerEventId(final Integer issuerEventId) {
        this.issuerEventId = issuerEventId;
    }

    /**
     * Gets Uncontrolled Flow Threshold.
     * <p>
     * The threshold above which a flow meter (e.g. Gas or Water) shall detect an uncontrolled
     * flow. A value of 0x0000 indicates the feature in unused.
     *
     * @return the Uncontrolled Flow Threshold
     */
    public Integer getUncontrolledFlowThreshold() {
        return uncontrolledFlowThreshold;
    }

    /**
     * Sets Uncontrolled Flow Threshold.
     * <p>
     * The threshold above which a flow meter (e.g. Gas or Water) shall detect an uncontrolled
     * flow. A value of 0x0000 indicates the feature in unused.
     *
     * @param uncontrolledFlowThreshold the Uncontrolled Flow Threshold
     */
    public void setUncontrolledFlowThreshold(final Integer uncontrolledFlowThreshold) {
        this.uncontrolledFlowThreshold = uncontrolledFlowThreshold;
    }

    /**
     * Gets Unit Of Measure.
     * <p>
     * An enumeration indicating the unit of measure to be used in conjunction with the
     * Uncontrolled Flow Threshold attribute. The enumeration used for this field shall
     * match one of the UnitOfMeasure values using a pure binary format as defined in the
     * Metering cluster.
     *
     * @return the Unit Of Measure
     */
    public Integer getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Sets Unit Of Measure.
     * <p>
     * An enumeration indicating the unit of measure to be used in conjunction with the
     * Uncontrolled Flow Threshold attribute. The enumeration used for this field shall
     * match one of the UnitOfMeasure values using a pure binary format as defined in the
     * Metering cluster.
     *
     * @param unitOfMeasure the Unit Of Measure
     */
    public void setUnitOfMeasure(final Integer unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * Gets Multiplier.
     * <p>
     * An unsigned 16-bit value indicating the multiplier, to be used in conjunction with the
     * Uncontrolled Flow Threshold and Divisor fields, to determine the true flow threshold
     * value. A value of 0x0000 is not allowed.
     *
     * @return the Multiplier
     */
    public Integer getMultiplier() {
        return multiplier;
    }

    /**
     * Sets Multiplier.
     * <p>
     * An unsigned 16-bit value indicating the multiplier, to be used in conjunction with the
     * Uncontrolled Flow Threshold and Divisor fields, to determine the true flow threshold
     * value. A value of 0x0000 is not allowed.
     *
     * @param multiplier the Multiplier
     */
    public void setMultiplier(final Integer multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * Gets Divisor.
     * <p>
     * An unsigned 16-bit value indicating the divisor, to be used in conjunction with the
     * Uncontrolled Flow Threshold and Multiplier fields, to determine the true flow
     * threshold value. A value of 0x0000 is not allowed.
     *
     * @return the Divisor
     */
    public Integer getDivisor() {
        return divisor;
    }

    /**
     * Sets Divisor.
     * <p>
     * An unsigned 16-bit value indicating the divisor, to be used in conjunction with the
     * Uncontrolled Flow Threshold and Multiplier fields, to determine the true flow
     * threshold value. A value of 0x0000 is not allowed.
     *
     * @param divisor the Divisor
     */
    public void setDivisor(final Integer divisor) {
        this.divisor = divisor;
    }

    /**
     * Gets Stabilisation Period.
     * <p>
     * An unsigned 8-bit value indicating the time given to allow the flow to stabilize. It is
     * defined in units of tenths of a second.
     *
     * @return the Stabilisation Period
     */
    public Integer getStabilisationPeriod() {
        return stabilisationPeriod;
    }

    /**
     * Sets Stabilisation Period.
     * <p>
     * An unsigned 8-bit value indicating the time given to allow the flow to stabilize. It is
     * defined in units of tenths of a second.
     *
     * @param stabilisationPeriod the Stabilisation Period
     */
    public void setStabilisationPeriod(final Integer stabilisationPeriod) {
        this.stabilisationPeriod = stabilisationPeriod;
    }

    /**
     * Gets Measurement Period.
     * <p>
     * An unsigned 16-bit value indicating the period over which the flow is measured and
     * compared against the Uncontrolled Flow Threshold value. It is defined in units of 1
     * second.
     *
     * @return the Measurement Period
     */
    public Integer getMeasurementPeriod() {
        return measurementPeriod;
    }

    /**
     * Sets Measurement Period.
     * <p>
     * An unsigned 16-bit value indicating the period over which the flow is measured and
     * compared against the Uncontrolled Flow Threshold value. It is defined in units of 1
     * second.
     *
     * @param measurementPeriod the Measurement Period
     */
    public void setMeasurementPeriod(final Integer measurementPeriod) {
        this.measurementPeriod = measurementPeriod;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(providerId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(issuerEventId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(uncontrolledFlowThreshold, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(unitOfMeasure, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(multiplier, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(divisor, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(stabilisationPeriod, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(measurementPeriod, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        providerId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        issuerEventId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        uncontrolledFlowThreshold = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        unitOfMeasure = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        multiplier = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        divisor = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        stabilisationPeriod = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        measurementPeriod = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(305);
        builder.append("SetUncontrolledFlowThreshold [");
        builder.append(super.toString());
        builder.append(", providerId=");
        builder.append(providerId);
        builder.append(", issuerEventId=");
        builder.append(issuerEventId);
        builder.append(", uncontrolledFlowThreshold=");
        builder.append(uncontrolledFlowThreshold);
        builder.append(", unitOfMeasure=");
        builder.append(unitOfMeasure);
        builder.append(", multiplier=");
        builder.append(multiplier);
        builder.append(", divisor=");
        builder.append(divisor);
        builder.append(", stabilisationPeriod=");
        builder.append(stabilisationPeriod);
        builder.append(", measurementPeriod=");
        builder.append(measurementPeriod);
        builder.append(']');
        return builder.toString();
    }

}
