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
 * Get Sampled Data Response value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * FIXME: This command is used to send the requested sample data to the client. It is generated in
 * response to a GetSampledData command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetSampledDataResponse extends ZclCommand {
    /**
     * Sample ID command message field.
     * <p>
     * Unique identifier allocated to this Sampling session. This field allows devices to
     * match response data with the appropriate request.
     */
    private Integer sampleId;

    /**
     * Sample Start Time command message field.
     * <p>
     * A UTC Time field to denote the time of the first sample returned in this response.
     */
    private Calendar sampleStartTime;

    /**
     * Sample Type command message field.
     * <p>
     * An 8 bit enumeration that identifies the type of data being sampled.
     */
    private Integer sampleType;

    /**
     * Sample Request Interval command message field.
     * <p>
     * An unsigned 16-bit field representing the interval or time in seconds between samples.
     */
    private Integer sampleRequestInterval;

    /**
     * Number Of Samples command message field.
     * <p>
     * Represents the number of samples being requested, This value cannot exceed the size
     * stipulated in the MaxNumberofSamples field in the StartSampling command. If more
     * samples are requested than can be delivered, the GetSampleDataResponse command will
     * return the number of samples equal to MaxNumberofSamples field. If fewer samples are
     * available for the time period, only those available shall be returned.
     */
    private Integer numberOfSamples;

    /**
     * Samples command message field.
     * <p>
     * Series of data samples captured using the interval specified by the
     * SampleRequestInterval field in the StartSampling command. Each sample contains the
     * change in the relevant data since the previous sample. Data is organised in a
     * chronological order, the oldest sample is transmitted first and the most recent sample
     * is transmitted last. Invalid samples should be marked as 0xFFFFFF.
     */
    private Integer samples;

    /**
     * Default constructor.
     */
    public GetSampledDataResponse() {
        genericCommand = false;
        clusterId = 0x0702;
        commandId = 7;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
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
     * Gets Sample Start Time.
     * <p>
     * A UTC Time field to denote the time of the first sample returned in this response.
     *
     * @return the Sample Start Time
     */
    public Calendar getSampleStartTime() {
        return sampleStartTime;
    }

    /**
     * Sets Sample Start Time.
     * <p>
     * A UTC Time field to denote the time of the first sample returned in this response.
     *
     * @param sampleStartTime the Sample Start Time
     */
    public void setSampleStartTime(final Calendar sampleStartTime) {
        this.sampleStartTime = sampleStartTime;
    }

    /**
     * Gets Sample Type.
     * <p>
     * An 8 bit enumeration that identifies the type of data being sampled.
     *
     * @return the Sample Type
     */
    public Integer getSampleType() {
        return sampleType;
    }

    /**
     * Sets Sample Type.
     * <p>
     * An 8 bit enumeration that identifies the type of data being sampled.
     *
     * @param sampleType the Sample Type
     */
    public void setSampleType(final Integer sampleType) {
        this.sampleType = sampleType;
    }

    /**
     * Gets Sample Request Interval.
     * <p>
     * An unsigned 16-bit field representing the interval or time in seconds between samples.
     *
     * @return the Sample Request Interval
     */
    public Integer getSampleRequestInterval() {
        return sampleRequestInterval;
    }

    /**
     * Sets Sample Request Interval.
     * <p>
     * An unsigned 16-bit field representing the interval or time in seconds between samples.
     *
     * @param sampleRequestInterval the Sample Request Interval
     */
    public void setSampleRequestInterval(final Integer sampleRequestInterval) {
        this.sampleRequestInterval = sampleRequestInterval;
    }

    /**
     * Gets Number Of Samples.
     * <p>
     * Represents the number of samples being requested, This value cannot exceed the size
     * stipulated in the MaxNumberofSamples field in the StartSampling command. If more
     * samples are requested than can be delivered, the GetSampleDataResponse command will
     * return the number of samples equal to MaxNumberofSamples field. If fewer samples are
     * available for the time period, only those available shall be returned.
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
     * samples are requested than can be delivered, the GetSampleDataResponse command will
     * return the number of samples equal to MaxNumberofSamples field. If fewer samples are
     * available for the time period, only those available shall be returned.
     *
     * @param numberOfSamples the Number Of Samples
     */
    public void setNumberOfSamples(final Integer numberOfSamples) {
        this.numberOfSamples = numberOfSamples;
    }

    /**
     * Gets Samples.
     * <p>
     * Series of data samples captured using the interval specified by the
     * SampleRequestInterval field in the StartSampling command. Each sample contains the
     * change in the relevant data since the previous sample. Data is organised in a
     * chronological order, the oldest sample is transmitted first and the most recent sample
     * is transmitted last. Invalid samples should be marked as 0xFFFFFF.
     *
     * @return the Samples
     */
    public Integer getSamples() {
        return samples;
    }

    /**
     * Sets Samples.
     * <p>
     * Series of data samples captured using the interval specified by the
     * SampleRequestInterval field in the StartSampling command. Each sample contains the
     * change in the relevant data since the previous sample. Data is organised in a
     * chronological order, the oldest sample is transmitted first and the most recent sample
     * is transmitted last. Invalid samples should be marked as 0xFFFFFF.
     *
     * @param samples the Samples
     */
    public void setSamples(final Integer samples) {
        this.samples = samples;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(sampleId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(sampleStartTime, ZclDataType.UTCTIME);
        serializer.serialize(sampleType, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(sampleRequestInterval, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(numberOfSamples, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(samples, ZclDataType.UNSIGNED_24_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        sampleId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        sampleStartTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        sampleType = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        sampleRequestInterval = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        numberOfSamples = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        samples = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_24_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(221);
        builder.append("GetSampledDataResponse [");
        builder.append(super.toString());
        builder.append(", sampleId=");
        builder.append(sampleId);
        builder.append(", sampleStartTime=");
        builder.append(sampleStartTime);
        builder.append(", sampleType=");
        builder.append(sampleType);
        builder.append(", sampleRequestInterval=");
        builder.append(sampleRequestInterval);
        builder.append(", numberOfSamples=");
        builder.append(numberOfSamples);
        builder.append(", samples=");
        builder.append(samples);
        builder.append(']');
        return builder.toString();
    }

}
