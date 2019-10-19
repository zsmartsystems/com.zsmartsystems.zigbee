/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import java.util.Calendar;
import java.util.concurrent.Future;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.database.ZclAttributeDao;
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
    private ZclCluster cluster;

    /**
     * The attribute identifier field is 16-bits in length and shall contain the
     * identifier of the attribute that the reporting configuration details
     * apply to.
     */
    private int id;

    /**
     * Stores the name of this attribute;
     */
    private String name;

    /**
     * Defines the ZigBee data type.
     */
    private ZclDataType dataType;

    /**
     * Defines if this attribute is mandatory to be implemented
     */
    private boolean mandatory;

    /**
     * Defines if the attribute is implemented by the device
     */
    private boolean implemented;

    /**
     * True if this attribute is readable
     */
    private boolean readable;

    /**
     * True if this attribute is writable
     */
    private boolean writable;

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
     * The manufacturer code of this attribute. If null, the attribute is not manufacturer-specific.
     */
    private Integer manufacturerCode;

    /**
     * Records the last time a report was received
     */
    private Calendar lastReportTime;

    /**
     * Records the last value received
     */
    private Object lastValue;

    /**
     * Default constructor
     */
    public ZclAttribute() {
    }

    /**
     * Constructor used to set the static information (for non-manufacturer-specific attribute)
     *
     * @param cluster the {@link ZclCluster} to which the attribute belongs
     * @param id the attribute ID
     * @param name the human readable name
     * @param dataType the {@link ZclDataType} for this attribute
     * @param mandatory true if this is defined as mandatory in the ZCL specification
     * @param readable true if this is defined as readable in the ZCL specification
     * @param writable true if this is defined as writable in the ZCL specification
     * @param reportable true if this is defined as reportable in the ZCL specification
     */
    public ZclAttribute(final ZclCluster cluster, final int id, final String name, final ZclDataType dataType,
            final boolean mandatory, final boolean readable, final boolean writable, final boolean reportable) {
        this.cluster = cluster;
        this.id = id;
        this.name = name;
        this.dataType = dataType;
        this.mandatory = mandatory;
        this.readable = readable;
        this.writable = writable;
        this.reportable = reportable;
    }

    /**
     * Constructor used to set the static information (for manufacturer-specific attribute)
     *
     * @param cluster the {@link ZclCluster} to which the attribute belongs
     * @param id the attribute ID
     * @param name the human readable name
     * @param dataType the {@link ZclDataType} for this attribute
     * @param mandatory true if this is defined as mandatory in the ZCL specification
     * @param readable true if this is defined as readable in the ZCL specification
     * @param writable true if this is defined as writable in the ZCL specification
     * @param reportable true if this is defined as reportable in the ZCL specification
     * @param manufacturerCode the code for the manufacturer specific cluster, for ex. 0x1234
     */
    public ZclAttribute(final ZclCluster cluster, final int id, final String name, final ZclDataType dataType,
            final boolean mandatory, final boolean readable, final boolean writable, final boolean reportable,
            final int manufacturerCode) {
        this.cluster = cluster;
        this.id = id;
        this.name = name;
        this.dataType = dataType;
        this.mandatory = mandatory;
        this.readable = readable;
        this.writable = writable;
        this.reportable = reportable;
        this.manufacturerCode = manufacturerCode;
    }

    /**
     * Returns the value of the attribute from the remote attribute. If the current value is newer than refreshPeriod
     * (in milliseconds) then the current value will be returned, otherwise the value will be requested from the remote
     * device.
     *
     * @param refreshPeriod the number of milliseconds to consider the value current
     * @return an Object with the attribute value, or null on error
     */
    public Object readValue(long refreshPeriod) {
        if (isLastValueCurrent(refreshPeriod)) {
            return getLastValue();
        }

        return cluster.readAttributeValue(id);
    }

    /**
     * Write the attribute value to the remote cluster.
     *
     * @param value the value to set (as {@link Object})
     * @return command future {@link CommandResult}
     */
    public Future<CommandResult> writeValue(Object value) {
        return cluster.writeAttribute(id, dataType, value);
    }

    /**
     * Sets the attribute value to the attribute.
     *
     * @param value the value to set (as {@link Object})
     */
    public void setValue(Object value) {
        lastValue = value;
    }

    /**
     * Gets the {@link ZclClusterType} to which this attribute belongs
     *
     * @return the {@link ZclClusterType} for this attribute
     */
    public ZclClusterType getCluster() {
        return ZclClusterType.getValueById(cluster.getClusterId());
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
     * mandatory as required by the ZigBee standard.
     * <p>
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
        return writable;
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
     * Sets the attributed as implemented or not.
     *
     * @param implemented true if the attribute is implemented
     */
    public void setImplemented(boolean implemented) {
        this.implemented = implemented;
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
     * Gets the reportable change field.
     * <p>
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
     * Gets the reporting timeout in seconds.
     * <p>
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
     * @return whether this is a manufacturer-specific attribute
     */
    public boolean isManufacturerSpecific() {
        return manufacturerCode != null;
    }

    /**
     * @return the manufacturer code of this attribute (null for attributes that are not manufacturer-specific)
     */
    public Integer getManufacturerCode() {
        return manufacturerCode;
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
     * Configures the reporting for the specified attribute ID for analog attributes.
     * <p>
     * <b>minInterval</b>:
     * The minimum reporting interval field is 16 bits in length and shall contain the
     * minimum interval, in seconds, between issuing reports of the specified attribute.
     * If minInterval is set to 0x0000, then there is no minimum limit, unless one is
     * imposed by the specification of the cluster using this reporting mechanism or by
     * the applicable profile.
     * <p>
     * <b>maxInterval</b>:
     * The maximum reporting interval field is 16 bits in length and shall contain the
     * maximum interval, in seconds, between issuing reports of the specified attribute.
     * If maxInterval is set to 0xffff, then the device shall not issue reports for the specified
     * attribute, and the configuration information for that attribute need not be
     * maintained.
     * <p>
     * <b>reportableChange</b>:
     * The reportable change field shall contain the minimum change to the attribute that
     * will result in a report being issued. This field is of variable length. For attributes
     * with 'analog' data type the field has the same data type as the attribute. The sign (if any) of the reportable
     * change field is ignored.
     *
     * @param minInterval the minimum reporting interval
     * @param maxInterval the maximum reporting interval
     * @param reportableChange the minimum change required to report an update
     * @return command future {@link CommandResult}
     */
    public Future<CommandResult> setReporting(final int minInterval, final int maxInterval,
            final Object reportableChange) {
        return cluster.setReporting(id, minInterval, maxInterval, reportableChange);
    }

    /**
     * Configures the reporting for the specified attribute ID for discrete attributes.
     * <p>
     * <b>minInterval</b>:
     * The minimum reporting interval field is 16 bits in length and shall contain the
     * minimum interval, in seconds, between issuing reports of the specified attribute.
     * If minInterval is set to 0x0000, then there is no minimum limit, unless one is
     * imposed by the specification of the cluster using this reporting mechanism or by
     * the applicable profile.
     * <p>
     * <b>maxInterval</b>:
     * The maximum reporting interval field is 16 bits in length and shall contain the
     * maximum interval, in seconds, between issuing reports of the specified attribute.
     * If maxInterval is set to 0xffff, then the device shall not issue reports for the specified
     * attribute, and the configuration information for that attribute need not be
     * maintained.
     *
     * @param minInterval the minimum reporting interval
     * @param maxInterval the maximum reporting interval
     * @return command future {@link CommandResult}
     */
    public Future<CommandResult> setReporting(final int minInterval, final int maxInterval) {
        return cluster.setReporting(id, minInterval, maxInterval);
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
     * @param attributeValue the attribute value to be updated {@link Object}
     */
    public void updateValue(Object attributeValue) {
        lastValue = attributeValue;
        lastReportTime = Calendar.getInstance();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(80);

        builder.append("ZclAttribute [cluster=");
        builder.append(cluster.getClusterName());
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
        builder.append(", implemented=");
        builder.append(implemented);
        builder.append(']');

        return builder.toString();
    }

    /**
     * Sets the state of the attribute from a {@link ZclAttributeDao} which has been restored from a persisted state.
     *
     * @param dao the {@link ZclAttributeDao} to restore
     */
    public void setDao(ZclCluster cluster, ZclAttributeDao dao) {
        this.cluster = cluster;
        id = dao.getId();
        name = dao.getName();
        dataType = dao.getDataType();
        mandatory = dao.isMandatory();
        implemented = dao.isImplemented();
        writable = dao.isWritable();
        readable = dao.isReadable();
        reportable = dao.isReportable();
        lastValue = dao.getLastValue();
        lastReportTime = dao.getLastReportTime();
        minimumReportingPeriod = dao.getMinimumReportingPeriod();
        maximumReportingPeriod = dao.getMaximumReportingPeriod();
        reportingChange = dao.getReportingChange();
        reportingTimeout = dao.getReportingTimeout();
    }

    /**
     * Returns a Data Acquisition Object for this attribute. This is a clean class recording the state of the primary
     * fields of the attribute for persistence purposes.
     *
     * @return the {@link ZclAttributeDao} from this {@link ZclAttribute}
     */
    public ZclAttributeDao getDao() {
        ZclAttributeDao dao = new ZclAttributeDao();

        dao.setId(id);
        dao.setDataType(dataType);
        dao.setName(name);
        dao.setMandatory(mandatory);
        dao.setImplemented(implemented);
        dao.setMinimumReportingPeriod(minimumReportingPeriod);
        dao.setMaximumReportingPeriod(maximumReportingPeriod);
        dao.setReadable(readable);
        dao.setWritable(writable);
        dao.setReportable(reportable);
        dao.setReportingChange(reportingChange);
        dao.setReportingTimeout(reportingTimeout);
        dao.setLastValue(lastValue);
        dao.setLastReportTime(lastReportTime);

        return dao;
    }

}
