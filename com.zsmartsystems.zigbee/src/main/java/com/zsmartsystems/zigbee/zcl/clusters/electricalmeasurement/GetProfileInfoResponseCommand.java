/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.electricalmeasurement;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Profile Info Response Command value object class.
 * <p>
 * Cluster: <b>Electrical Measurement</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Electrical Measurement cluster.
 * <p>
 * Returns the power profiling information requested in the GetProfileInfo command. The
 * power profiling information consists of a list of attributes which are profiled along with
 * the period used to profile them.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetProfileInfoResponseCommand extends ZclCommand {
    /**
     * Profile Count command message field.
     */
    private Integer profileCount;

    /**
     * Profile Interval Period command message field.
     */
    private Integer profileIntervalPeriod;

    /**
     * Max Number Of Intervals command message field.
     */
    private Integer maxNumberOfIntervals;

    /**
     * List Of Attributes command message field.
     */
    private Integer listOfAttributes;

    /**
     * Default constructor.
     */
    public GetProfileInfoResponseCommand() {
        genericCommand = false;
        clusterId = 0x0B04;
        commandId = 0;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Profile Count.
     *
     * @return the Profile Count
     */
    public Integer getProfileCount() {
        return profileCount;
    }

    /**
     * Sets Profile Count.
     *
     * @param profileCount the Profile Count
     */
    public void setProfileCount(final Integer profileCount) {
        this.profileCount = profileCount;
    }

    /**
     * Gets Profile Interval Period.
     *
     * @return the Profile Interval Period
     */
    public Integer getProfileIntervalPeriod() {
        return profileIntervalPeriod;
    }

    /**
     * Sets Profile Interval Period.
     *
     * @param profileIntervalPeriod the Profile Interval Period
     */
    public void setProfileIntervalPeriod(final Integer profileIntervalPeriod) {
        this.profileIntervalPeriod = profileIntervalPeriod;
    }

    /**
     * Gets Max Number Of Intervals.
     *
     * @return the Max Number Of Intervals
     */
    public Integer getMaxNumberOfIntervals() {
        return maxNumberOfIntervals;
    }

    /**
     * Sets Max Number Of Intervals.
     *
     * @param maxNumberOfIntervals the Max Number Of Intervals
     */
    public void setMaxNumberOfIntervals(final Integer maxNumberOfIntervals) {
        this.maxNumberOfIntervals = maxNumberOfIntervals;
    }

    /**
     * Gets List Of Attributes.
     *
     * @return the List Of Attributes
     */
    public Integer getListOfAttributes() {
        return listOfAttributes;
    }

    /**
     * Sets List Of Attributes.
     *
     * @param listOfAttributes the List Of Attributes
     */
    public void setListOfAttributes(final Integer listOfAttributes) {
        this.listOfAttributes = listOfAttributes;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(profileCount, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(profileIntervalPeriod, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(maxNumberOfIntervals, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(listOfAttributes, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        profileCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        profileIntervalPeriod = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        maxNumberOfIntervals = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        listOfAttributes = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(181);
        builder.append("GetProfileInfoResponseCommand [");
        builder.append(super.toString());
        builder.append(", profileCount=");
        builder.append(profileCount);
        builder.append(", profileIntervalPeriod=");
        builder.append(profileIntervalPeriod);
        builder.append(", maxNumberOfIntervals=");
        builder.append(maxNumberOfIntervals);
        builder.append(", listOfAttributes=");
        builder.append(listOfAttributes);
        builder.append(']');
        return builder.toString();
    }

}
