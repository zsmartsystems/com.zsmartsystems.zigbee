/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import java.util.Calendar;

import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Defines a Cluster Library Attribute
 *
 * @author Chris Jackson
 *
 */
public class ZclAttribute {
    /**
     *
     */
    private final ZclClusterType cluster;

    /**
     * The attribute identifier field is 16-bits in length and shall contain the
     * identifier of the attribute that the reporting configuration details
     * apply to.
     */
    private final int id;

    /**
     * Stores the name of this attribute;
     */
    private final String name;

    /**
     * Defines the ZigBee data type.
     */
    private final ZclDataType dataType;

    /**
     * Defines if this attribute is mandatory to be implemented
     */
    private final boolean mandatory;

    /**
     * Defines if the attribute is implemented by the device
     */
    private boolean implemented;

    /**
     * True if this attribute is readable
     */
    private boolean readable;

    /**
     * True if this attribute is writeable
     */
    private boolean writeable;

    /**
     * True if this attribute is reportable
     */
    private boolean reportable;

    /**
     * The minimum reporting interval field is 16-bits in length and shall
     * contain the minimum interval, in seconds, between issuing reports for the
     * attribute specified in the attribute identifier field. If the minimum
     * reporting interval has not been configured, this field shall contain the
     * value 0xffff.
     */
    private int minimumReportingPeriod;

    /**
     * The maximum reporting interval field is 16-bits in length and shall
     * contain the maximum interval, in seconds, between issuing reports for the
     * attribute specified in the attribute identifier field. If the maximum
     * reporting interval has not been configured, this field shall contain the
     * value 0xffff.
     */
    private int maximumReportingPeriod;

    /**
     * The reportable change field shall contain the minimum change to the
     * attribute that will result in a report being issued. For attributes with
     * 'analog' data type the field has the same data type as the attribute. If
     * the reportable change has not been configured, this field shall contain
     * the invalid value for the relevant data type
     */
    private Object reportingChange;

    /**
     * The timeout period field is 16-bits in length and shall contain the
     * maximum expected time, in seconds, between received reports for the
     * attribute specified in the attribute identifier field. If the timeout
     * period has not been configured, this field shall contain the value
     * 0xffff.
     */
    private int reportingTimeout;

    /**
     * Records the last time a report was received
     */
    private Calendar lastReportTime;

    /**
     * Records the last value received
     */
    private Object lastValue;

    /**
     * Constructor used to set the static information
     *
     * @param cluster
     * @param id
     * @param dataType
     * @param mandatory
     * @param readable
     * @param writeable
     * @param reportable
     */
    public ZclAttribute(final ZclClusterType cluster, final int id, final String name, final ZclDataType dataType,
            final boolean mandatory, final boolean readable, final boolean writeable, final boolean reportable) {
        this.cluster = cluster;
        this.id = id;
        this.name = name;
        this.dataType = dataType;
        this.mandatory = mandatory;
        this.readable = readable;
        this.writeable = writeable;
        this.reportable = reportable;
    }

    /**
     * Gets the {@link ZclClusterType} to which this attribute belongs
     *
     * @return the {@link ZclClusterType} for this attribute
     */
    public ZclClusterType getCluster() {
        return cluster;
    }

    /**
     * Gets the attribute ID
     *
     * @return the attribute ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns true if the implementation of this attribute in the cluster is
     * mandatory as required by the ZigBee standard. <br>
     * Note that this does not necessarily mean that the attribute is actually
     * implemented in any device if it does not conform to the standard.
     *
     * @return true if the attribute must be implemented
     */
    public boolean isMandatory() {
        return mandatory;
    }

    /**
     * Returns true if this attribute is supported by this device
     *
     * @return true if the attribute is supported
     */
    public boolean isImplemented() {
        return implemented;
    }

    /**
     * Returns true if this attribute is readable
     *
     * @return true if the attribute is readable
     */
    public boolean isReadable() {
        return readable;
    }

    /**
     * Returns true if this attribute is writable
     *
     * @return true if the attribute is writable
     */
    public boolean isWritable() {
        return writeable;
    }

    /**
     * Returns true if this attribute is reportable
     *
     * @return true if the attribute is reportable
     */
    public boolean isReportable() {
        return reportable;
    }

    /**
     * Gets the {@link ZigBeeType} of this attribute
     *
     * @return the {@link ZigBeeType} of this attribute
     */
    public ZclDataType getDataType() {
        return dataType;
    }

    /**
     * Gets the minimum reporting interval in seconds. <br>
     * The minimum reporting interval field is 16-bits in length and shall
     * contain the minimum interval, in seconds, between issuing reports for the
     * attribute specified in the attribute identifier field. If the minimum
     * reporting interval has not been configured, this field shall contain the
     * value 0xffff.
     *
     * @return minimum reporting period in seconds
     */
    public int getMinimumReportingPeriod() {
        return minimumReportingPeriod;
    }

    /**
     * Gets the maximum reporting interval in seconds. <br>
     * The maximum reporting interval field is 16-bits in length and shall
     * contain the maximum interval, in seconds, between issuing reports for the
     * attribute specified in the attribute identifier field. If the maximum
     * reporting interval has not been configured, this field shall contain the
     * value 0xffff.
     *
     * @return maximum reporting period in seconds
     */
    public int getMaximumReportingPeriod() {
        return maximumReportingPeriod;
    }

    /**
     * Gets the reportable change field. <br>
     * The reportable change field shall contain the minimum change to the
     * attribute that will result in a report being issued. For attributes with
     * 'analog' data type the field has the same data type as the attribute. If
     * the reportable change has not been configured, this field shall contain
     * the invalid value for the relevant data type
     *
     * @return
     */
    public Object getReportingChange() {
        return reportingChange;
    }

    /**
     * Gets the reporting timeout in seconds. <br>
     * The timeout period field is 16-bits in length and shall contain the
     * maximum expected time, in seconds, between received reports for the
     * attribute specified in the attribute identifier field. If the timeout
     * period has not been configured, this field shall contain the value
     * 0xffff.
     *
     * @return
     */
    public int getReportingTimeout() {
        return reportingTimeout;
    }

    /**
     * Gets the last reported value of this attribute
     *
     * @return the last value, or null if no update has been received
     */
    public Object getLastValue() {
        return lastValue;
    }

    /**
     * Gets the last report time of this attribute
     *
     * @return the time of the last report, or null if not reports have been
     *         received
     */
    public Calendar getLastReportTime() {
        return lastReportTime;
    }

    /**
     * Checks if the last value received for the attribute is still current.
     * If the last update time is more recent than the allowedAge then this will return true. allowedAge is defined in
     * milliseconds.
     *
     * @param allowedAge the number of milliseconds to consider the value current
     * @return true if the last value can be considered current
     */
    public boolean isLastValueCurrent(long allowedAge) {
        if (lastReportTime == null) {
            return false;
        }

        long refreshTime = Calendar.getInstance().getTimeInMillis() - allowedAge;
        if (refreshTime < 0) {
            return true;
        }
        return getLastReportTime().getTimeInMillis() > refreshTime;
    }

    /**
     * Gets the name of this attribute
     *
     * @return the name as {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the attribute value This will also record the time of the last update
     *
     * @param attributeValue
     *            the attribute value to be updated {@link Object}
     */
    public void updateValue(Object attributeValue) {
        lastValue = attributeValue;
        lastReportTime = Calendar.getInstance();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(80);

        builder.append("ZclAttribute [cluster=");
        builder.append(cluster);
        builder.append(", id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", dataType=");
        builder.append(dataType);
        builder.append(", lastValue=");
        builder.append(lastValue);
        if (lastReportTime != null) {
            builder.append(", lastReportTime=");
            builder.append(lastReportTime.getTime());
        }
        builder.append(']');

        return builder.toString();
    }
}
