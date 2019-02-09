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
 * Get Sampled Data value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * This command is used to request sampled data from the server. Note that it is the
 * responsibility of the client to ensure that it does not request more samples than can be held
 * in a single command payload.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetSampledData extends ZclCommand {
    /**
     * Sample ID command message field.
     * <p>
     * Unique identifier allocated to this Sampling session. This field allows devices to
     * match response data with the appropriate request.
     */
    private Integer sampleId;

    /**
     * Earliest Sample Time command message field.
     * <p>
     * A UTC Timestamp indicating the earliest time of a sample to be returned. Samples with a
     * timestamp equal to or greater than the specified EarliestSampleTime shall be
     * returned.
     */
    private Calendar earliestSampleTime;

    /**
     * Sample Type command message field.
     * <p>
     * An 8 bit enumeration that identifies the required type of sampled data.
     */
    private Integer sampleType;

    /**
     * Number Of Samples command message field.
     * <p>
     * Represents the number of samples being requested, This value cannot exceed the size
     * stipulated in the MaxNumberofSamples field in the StartSampling command. If more
     * samples are requested than can be delivered, the GetSampledDataResponse command will
     * return the number of samples equal to the MaxNumberofSamples field. If fewer samples
     * are available for the time period, only those available are returned.
     */
    private Integer numberOfSamples;

    /**
     * Default constructor.
     */
    public GetSampledData() {
        genericCommand = false;
        clusterId = 0x0702;
        commandId = 8;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Sample ID.
     * <p>
     * Unique identifier allocated to this Sampling session. This field allows devices to
     * match response data with the appropriate request.
     *
     * @return the Sample ID
     */
    public Integer getSampleId() {
        return sampleId;
    }

    /**
     * Sets Sample ID.
     * <p>
     * Unique identifier allocated to this Sampling session. This field allows devices to
     * match response data with the appropriate request.
     *
     * @param sampleId the Sample ID
     */
    public void setSampleId(final Integer sampleId) {
        this.sampleId = sampleId;
    }

    /**
     * Gets Earliest Sample Time.
     * <p>
     * A UTC Timestamp indicating the earliest time of a sample to be returned. Samples with a
     * timestamp equal to or greater than the specified EarliestSampleTime shall be
     * returned.
     *
     * @return the Earliest Sample Time
     */
    public Calendar getEarliestSampleTime() {
        return earliestSampleTime;
    }

    /**
     * Sets Earliest Sample Time.
     * <p>
     * A UTC Timestamp indicating the earliest time of a sample to be returned. Samples with a
     * timestamp equal to or greater than the specified EarliestSampleTime shall be
     * returned.
     *
     * @param earliestSampleTime the Earliest Sample Time
     */
    public void setEarliestSampleTime(final Calendar earliestSampleTime) {
        this.earliestSampleTime = earliestSampleTime;
    }

    /**
     * Gets Sample Type.
     * <p>
     * An 8 bit enumeration that identifies the required type of sampled data.
     *
     * @return the Sample Type
     */
    public Integer getSampleType() {
        return sampleType;
    }

    /**
     * Sets Sample Type.
     * <p>
     * An 8 bit enumeration that identifies the required type of sampled data.
     *
     * @param sampleType the Sample Type
     */
    public void setSampleType(final Integer sampleType) {
        this.sampleType = sampleType;
    }

    /**
     * Gets Number Of Samples.
     * <p>
     * Represents the number of samples being requested, This value cannot exceed the size
     * stipulated in the MaxNumberofSamples field in the StartSampling command. If more
     * samples are requested than can be delivered, the GetSampledDataResponse command will
     * return the number of samples equal to the MaxNumberofSamples field. If fewer samples
     * are available for the time period, only those available are returned.
     *
     * @return the Number Of Samples
     */
    public Integer getNumberOfSamples() {
        return numberOfSamples;
    }

    /**
     * Sets Number Of Samples.
     * <p>
     * Represents the number of samples being requested, This value cannot exceed the size
     * stipulated in the MaxNumberofSamples field in the StartSampling command. If more
     * samples are requested than can be delivered, the GetSampledDataResponse command will
     * return the number of samples equal to the MaxNumberofSamples field. If fewer samples
     * are available for the time period, only those available are returned.
     *
     * @param numberOfSamples the Number Of Samples
     */
    public void setNumberOfSamples(final Integer numberOfSamples) {
        this.numberOfSamples = numberOfSamples;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(sampleId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(earliestSampleTime, ZclDataType.UTCTIME);
        serializer.serialize(sampleType, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(numberOfSamples, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        sampleId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        earliestSampleTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        sampleType = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        numberOfSamples = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(148);
        builder.append("GetSampledData [");
        builder.append(super.toString());
        builder.append(", sampleId=");
        builder.append(sampleId);
        builder.append(", earliestSampleTime=");
        builder.append(earliestSampleTime);
        builder.append(", sampleType=");
        builder.append(sampleType);
        builder.append(", numberOfSamples=");
        builder.append(numberOfSamples);
        builder.append(']');
        return builder.toString();
    }

}
