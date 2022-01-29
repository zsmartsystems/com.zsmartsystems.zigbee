/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.database;

import java.util.Calendar;

import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * This class provides a clean class to hold a data object for serialisation of a {@link ZclAttribute}
 *
 * @author Chris Jackson
 *
 */
public class ZclAttributeDao {
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
     * The manufacturer code of the attribute, in case the attribute is 
     * manufacturer-specific. Otherwise, this field is null.
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
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the dataType
     */
    public ZclDataType getDataType() {
        return dataType;
    }

    /**
     * @param dataType the dataType to set
     */
    public void setDataType(ZclDataType dataType) {
        this.dataType = dataType;
    }

    /**
     * @return the mandatory
     */
    public boolean isMandatory() {
        return mandatory;
    }

    /**
     * @param mandatory the mandatory to set
     */
    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    /**
     * @return the implemented
     */
    public boolean isImplemented() {
        return implemented;
    }

    /**
     * @param implemented the implemented to set
     */
    public void setImplemented(boolean implemented) {
        this.implemented = implemented;
    }

    /**
     * @return the readable
     */
    public boolean isReadable() {
        return readable;
    }

    /**
     * @param readable the readable to set
     */
    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    /**
     * @return the writable flag
     */
    public boolean isWritable() {
        return writable;
    }

    /**
     * @param writable the writable to set
     */
    public void setWritable(boolean writable) {
        this.writable = writable;
    }

    /**
     * @return the reportable
     */
    public boolean isReportable() {
        return reportable;
    }

    /**
     * @param reportable the reportable to set
     */
    public void setReportable(boolean reportable) {
        this.reportable = reportable;
    }

    /**
     * @return the minimumReportingPeriod
     */
    public int getMinimumReportingPeriod() {
        return minimumReportingPeriod;
    }

    /**
     * @param minimumReportingPeriod the minimumReportingPeriod to set
     */
    public void setMinimumReportingPeriod(int minimumReportingPeriod) {
        this.minimumReportingPeriod = minimumReportingPeriod;
    }

    /**
     * @return the maximumReportingPeriod
     */
    public int getMaximumReportingPeriod() {
        return maximumReportingPeriod;
    }

    /**
     * @param maximumReportingPeriod the maximumReportingPeriod to set
     */
    public void setMaximumReportingPeriod(int maximumReportingPeriod) {
        this.maximumReportingPeriod = maximumReportingPeriod;
    }

    /**
     * @return the reportingChange
     */
    public Object getReportingChange() {
        return reportingChange;
    }

    /**
     * @param reportingChange the reportingChange to set
     */
    public void setReportingChange(Object reportingChange) {
        this.reportingChange = reportingChange;
    }

    /**
     * @return the reportingTimeout
     */
    public int getReportingTimeout() {
        return reportingTimeout;
    }

    /**
     * @param reportingTimeout the reportingTimeout to set
     */
    public void setReportingTimeout(int reportingTimeout) {
        this.reportingTimeout = reportingTimeout;
    }

    /**
     * @return the manufacturer code (or null, if attribute is not manufacturer-specific)
     */
    public Integer getManufacturerCode() {
        return manufacturerCode;
    }

    /**
     * @param manufacturerCode the manufacturer code to set (or null, if attribute is not manufacturer-specific)
     */
    public void setManufacturerCode(Integer manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    /**
     * @return the lastReportTime
     */
    public Calendar getLastReportTime() {
        return lastReportTime;
    }

    /**
     * @param lastReportTime the lastReportTime to set
     */
    public void setLastReportTime(Calendar lastReportTime) {
        this.lastReportTime = lastReportTime;
    }

    /**
     * @return the lastValue
     */
    public Object getLastValue() {
        return lastValue;
    }

    /**
     * @param lastValue the lastValue to set
     */
    public void setLastValue(Object lastValue) {
        this.lastValue = lastValue;
    }

}
