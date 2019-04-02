/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.database;

import java.util.Calendar;

import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * This class provides a clean class to hold a data object for serialisation of a {@link ZclAttribute}.
 * 
 * @author Henning Sudbrock - initial contribution
 */
public class ZclAttributeDao {

    private ZclClusterType cluster;

    private int id;

    private String name;

    private ZclDataType dataType;

    private boolean mandatory;

    private boolean implemented;

    private boolean readable;

    private boolean writeable;

    private boolean reportable;

    private int minimumReportingPeriod;

    private int maximumReportingPeriod;

    private Object reportingChange;

    private int reportingTimeout;

    private Integer manufacturerCode;

    private Calendar lastReportTime;

    private Object lastValue;

    public ZclClusterType getCluster() {
        return cluster;
    }

    public void setCluster(ZclClusterType cluster) {
        this.cluster = cluster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZclDataType getDataType() {
        return dataType;
    }

    public void setDataType(ZclDataType dataType) {
        this.dataType = dataType;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public boolean isImplemented() {
        return implemented;
    }

    public void setImplemented(boolean implemented) {
        this.implemented = implemented;
    }

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    public boolean isWriteable() {
        return writeable;
    }

    public void setWriteable(boolean writeable) {
        this.writeable = writeable;
    }

    public boolean isReportable() {
        return reportable;
    }

    public void setReportable(boolean reportable) {
        this.reportable = reportable;
    }

    public int getMinimumReportingPeriod() {
        return minimumReportingPeriod;
    }

    public void setMinimumReportingPeriod(int minimumReportingPeriod) {
        this.minimumReportingPeriod = minimumReportingPeriod;
    }

    public int getMaximumReportingPeriod() {
        return maximumReportingPeriod;
    }

    public void setMaximumReportingPeriod(int maximumReportingPeriod) {
        this.maximumReportingPeriod = maximumReportingPeriod;
    }

    public Object getReportingChange() {
        return reportingChange;
    }

    public void setReportingChange(Object reportingChange) {
        this.reportingChange = reportingChange;
    }

    public int getReportingTimeout() {
        return reportingTimeout;
    }

    public void setReportingTimeout(int reportingTimeout) {
        this.reportingTimeout = reportingTimeout;
    }

    public Integer getManufacturerCode() {
        return manufacturerCode;
    }

    public void setManufacturerCode(Integer manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    public Calendar getLastReportTime() {
        return lastReportTime;
    }

    public void setLastReportTime(Calendar lastReportTime) {
        this.lastReportTime = lastReportTime;
    }

    public Object getLastValue() {
        return lastValue;
    }

    public void setLastValue(Object lastValue) {
        this.lastValue = lastValue;
    }

}
