/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.Future;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Meter Identification</b> cluster implementation (<i>Cluster ID 0x0B01</i>).
 * <p>
 * This cluster provides attributes and commands for determining advanced information about
 * utility metering device.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-11-19T09:19:31Z")
public class ZclMeterIdentificationCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0B01;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Meter Identification";

    // Attribute constants
    /**
     * This attribute defines the meter manufacturer name, decided by manufacturer.
     */
    public static final int ATTR_COMPANYNAME = 0x0000;
    /**
     * This attribute defines the Meter installation features, decided by manufacturer.
     */
    public static final int ATTR_METERTYPEID = 0x0001;
    /**
     * This attribute defines the Meter Simple Metering information certification type,
     * decided by manufacturer.
     */
    public static final int ATTR_DATAQUALITYID = 0x0004;
    public static final int ATTR_CUSTOMERNAME = 0x0005;
    /**
     * This attribute defines the meter model name, decided by manufacturer.
     */
    public static final int ATTR_MODEL = 0x0006;
    /**
     * This attribute defines the meter part number, decided by manufacturer.
     */
    public static final int ATTR_PARTNUMBER = 0x0007;
    /**
     * This attribute defines the meter revision code, decided by manufacturer.
     */
    public static final int ATTR_PRODUCTREVISION = 0x0008;
    /**
     * This attribute defines the meter software revision code, decided by manufacturer.
     */
    public static final int ATTR_SOFTWAREREVISION = 0x000A;
    public static final int ATTR_UTILITYNAME = 0x000B;
    /**
     * This attribute is the unique identification ID of the premise connection point. It is
     * also a contractual information known by the clients and indicated in the bill.
     */
    public static final int ATTR_POD = 0x000C;
    /**
     * This attribute represents the InstantaneousDemand that can be distributed to the
     * customer (e.g., 3.3KW power) without any risk of overload. The Available Power shall
     * use the same formatting conventions as the one used in the simple metering cluster
     * formatting attribute set for the InstantaneousDemand attribute, i.e., the
     * UnitOfMeasure and DemandFormatting.
     */
    public static final int ATTR_AVAILABLEPOWER = 0x000D;
    /**
     * This attribute represents a threshold of InstantaneousDemand distributed to the
     * customer (e.g., 4.191KW) that will lead to an imminent risk of overload. The
     * PowerThreshold shall use the same formatting conventions as the one used in the
     * AvailablePower attributes and therefore in the simple metering cluster formatting
     * attribute set for the InstantaneousDemand attribute, i.e., the UnitOfMeasure and
     * DemandFormatting.
     */
    public static final int ATTR_POWERTHRESHOLD = 0x000E;

    @Override
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentSkipListMap<>();

        return attributeMap;
    }

    @Override
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentSkipListMap<>();

        attributeMap.put(ATTR_COMPANYNAME, new ZclAttribute(this, ATTR_COMPANYNAME, "Company Name", ZclDataType.CHARACTER_STRING, true, true, false, false));
        attributeMap.put(ATTR_METERTYPEID, new ZclAttribute(this, ATTR_METERTYPEID, "Meter Type ID", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DATAQUALITYID, new ZclAttribute(this, ATTR_DATAQUALITYID, "Data Quality ID", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_CUSTOMERNAME, new ZclAttribute(this, ATTR_CUSTOMERNAME, "Customer Name", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_MODEL, new ZclAttribute(this, ATTR_MODEL, "Model", ZclDataType.CHARACTER_STRING, false, true, false, false));
        attributeMap.put(ATTR_PARTNUMBER, new ZclAttribute(this, ATTR_PARTNUMBER, "Part Number", ZclDataType.CHARACTER_STRING, false, true, false, false));
        attributeMap.put(ATTR_PRODUCTREVISION, new ZclAttribute(this, ATTR_PRODUCTREVISION, "Product Revision", ZclDataType.CHARACTER_STRING, false, true, false, false));
        attributeMap.put(ATTR_SOFTWAREREVISION, new ZclAttribute(this, ATTR_SOFTWAREREVISION, "Software Revision", ZclDataType.CHARACTER_STRING, false, true, false, false));
        attributeMap.put(ATTR_UTILITYNAME, new ZclAttribute(this, ATTR_UTILITYNAME, "Utility Name", ZclDataType.CHARACTER_STRING, false, true, false, false));
        attributeMap.put(ATTR_POD, new ZclAttribute(this, ATTR_POD, "POD", ZclDataType.CHARACTER_STRING, true, true, false, false));
        attributeMap.put(ATTR_AVAILABLEPOWER, new ZclAttribute(this, ATTR_AVAILABLEPOWER, "Available Power", ZclDataType.SIGNED_24_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_POWERTHRESHOLD, new ZclAttribute(this, ATTR_POWERTHRESHOLD, "Power Threshold", ZclDataType.SIGNED_24_BIT_INTEGER, true, true, false, false));

        return attributeMap;
    }


    /**
     * Default constructor to create a Meter Identification cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclMeterIdentificationCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>Company Name</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * This attribute defines the meter manufacturer name, decided by manufacturer.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getCompanyNameAsync() {
        return read(serverAttributes.get(ATTR_COMPANYNAME));
    }

    /**
     * Synchronously get the <i>Company Name</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * This attribute defines the meter manufacturer name, decided by manufacturer.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link String} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public String getCompanyName(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_COMPANYNAME).isLastValueCurrent(refreshPeriod)) {
            return (String) serverAttributes.get(ATTR_COMPANYNAME).getLastValue();
        }

        return (String) readSync(serverAttributes.get(ATTR_COMPANYNAME));
    }

    /**
     * Set reporting for the <i>Company Name</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * This attribute defines the meter manufacturer name, decided by manufacturer.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setCompanyNameReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_COMPANYNAME), minInterval, maxInterval);
    }

    /**
     * Get the <i>Meter Type ID</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * This attribute defines the Meter installation features, decided by manufacturer.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMeterTypeIdAsync() {
        return read(serverAttributes.get(ATTR_METERTYPEID));
    }

    /**
     * Synchronously get the <i>Meter Type ID</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * This attribute defines the Meter installation features, decided by manufacturer.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getMeterTypeId(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_METERTYPEID).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_METERTYPEID).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_METERTYPEID));
    }

    /**
     * Set reporting for the <i>Meter Type ID</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * This attribute defines the Meter installation features, decided by manufacturer.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setMeterTypeIdReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_METERTYPEID), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Data Quality ID</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * This attribute defines the Meter Simple Metering information certification type,
     * decided by manufacturer.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDataQualityIdAsync() {
        return read(serverAttributes.get(ATTR_DATAQUALITYID));
    }

    /**
     * Synchronously get the <i>Data Quality ID</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * This attribute defines the Meter Simple Metering information certification type,
     * decided by manufacturer.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getDataQualityId(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DATAQUALITYID).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DATAQUALITYID).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DATAQUALITYID));
    }

    /**
     * Set reporting for the <i>Data Quality ID</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * This attribute defines the Meter Simple Metering information certification type,
     * decided by manufacturer.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setDataQualityIdReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_DATAQUALITYID), minInterval, maxInterval, reportableChange);
    }

    /**
     * Set the <i>Customer Name</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param customerName the {@link String} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setCustomerName(final String value) {
        return write(serverAttributes.get(ATTR_CUSTOMERNAME), value);
    }

    /**
     * Get the <i>Customer Name</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getCustomerNameAsync() {
        return read(serverAttributes.get(ATTR_CUSTOMERNAME));
    }

    /**
     * Synchronously get the <i>Customer Name</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link String} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public String getCustomerName(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_CUSTOMERNAME).isLastValueCurrent(refreshPeriod)) {
            return (String) serverAttributes.get(ATTR_CUSTOMERNAME).getLastValue();
        }

        return (String) readSync(serverAttributes.get(ATTR_CUSTOMERNAME));
    }

    /**
     * Get the <i>Model</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * This attribute defines the meter model name, decided by manufacturer.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getModelAsync() {
        return read(serverAttributes.get(ATTR_MODEL));
    }

    /**
     * Synchronously get the <i>Model</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * This attribute defines the meter model name, decided by manufacturer.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link String} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public String getModel(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MODEL).isLastValueCurrent(refreshPeriod)) {
            return (String) serverAttributes.get(ATTR_MODEL).getLastValue();
        }

        return (String) readSync(serverAttributes.get(ATTR_MODEL));
    }

    /**
     * Get the <i>Part Number</i> attribute [attribute ID <b>0x0007</b>].
     * <p>
     * This attribute defines the meter part number, decided by manufacturer.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPartNumberAsync() {
        return read(serverAttributes.get(ATTR_PARTNUMBER));
    }

    /**
     * Synchronously get the <i>Part Number</i> attribute [attribute ID <b>0x0007</b>].
     * <p>
     * This attribute defines the meter part number, decided by manufacturer.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link String} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public String getPartNumber(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_PARTNUMBER).isLastValueCurrent(refreshPeriod)) {
            return (String) serverAttributes.get(ATTR_PARTNUMBER).getLastValue();
        }

        return (String) readSync(serverAttributes.get(ATTR_PARTNUMBER));
    }

    /**
     * Get the <i>Product Revision</i> attribute [attribute ID <b>0x0008</b>].
     * <p>
     * This attribute defines the meter revision code, decided by manufacturer.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getProductRevisionAsync() {
        return read(serverAttributes.get(ATTR_PRODUCTREVISION));
    }

    /**
     * Synchronously get the <i>Product Revision</i> attribute [attribute ID <b>0x0008</b>].
     * <p>
     * This attribute defines the meter revision code, decided by manufacturer.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link String} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public String getProductRevision(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_PRODUCTREVISION).isLastValueCurrent(refreshPeriod)) {
            return (String) serverAttributes.get(ATTR_PRODUCTREVISION).getLastValue();
        }

        return (String) readSync(serverAttributes.get(ATTR_PRODUCTREVISION));
    }

    /**
     * Get the <i>Software Revision</i> attribute [attribute ID <b>0x000A</b>].
     * <p>
     * This attribute defines the meter software revision code, decided by manufacturer.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getSoftwareRevisionAsync() {
        return read(serverAttributes.get(ATTR_SOFTWAREREVISION));
    }

    /**
     * Synchronously get the <i>Software Revision</i> attribute [attribute ID <b>0x000A</b>].
     * <p>
     * This attribute defines the meter software revision code, decided by manufacturer.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link String} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public String getSoftwareRevision(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_SOFTWAREREVISION).isLastValueCurrent(refreshPeriod)) {
            return (String) serverAttributes.get(ATTR_SOFTWAREREVISION).getLastValue();
        }

        return (String) readSync(serverAttributes.get(ATTR_SOFTWAREREVISION));
    }

    /**
     * Get the <i>Utility Name</i> attribute [attribute ID <b>0x000B</b>].
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getUtilityNameAsync() {
        return read(serverAttributes.get(ATTR_UTILITYNAME));
    }

    /**
     * Synchronously get the <i>Utility Name</i> attribute [attribute ID <b>0x000B</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link String} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public String getUtilityName(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_UTILITYNAME).isLastValueCurrent(refreshPeriod)) {
            return (String) serverAttributes.get(ATTR_UTILITYNAME).getLastValue();
        }

        return (String) readSync(serverAttributes.get(ATTR_UTILITYNAME));
    }

    /**
     * Get the <i>POD</i> attribute [attribute ID <b>0x000C</b>].
     * <p>
     * This attribute is the unique identification ID of the premise connection point. It is
     * also a contractual information known by the clients and indicated in the bill.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPodAsync() {
        return read(serverAttributes.get(ATTR_POD));
    }

    /**
     * Synchronously get the <i>POD</i> attribute [attribute ID <b>0x000C</b>].
     * <p>
     * This attribute is the unique identification ID of the premise connection point. It is
     * also a contractual information known by the clients and indicated in the bill.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link String} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public String getPod(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_POD).isLastValueCurrent(refreshPeriod)) {
            return (String) serverAttributes.get(ATTR_POD).getLastValue();
        }

        return (String) readSync(serverAttributes.get(ATTR_POD));
    }

    /**
     * Set reporting for the <i>POD</i> attribute [attribute ID <b>0x000C</b>].
     * <p>
     * This attribute is the unique identification ID of the premise connection point. It is
     * also a contractual information known by the clients and indicated in the bill.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setPodReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_POD), minInterval, maxInterval);
    }

    /**
     * Get the <i>Available Power</i> attribute [attribute ID <b>0x000D</b>].
     * <p>
     * This attribute represents the InstantaneousDemand that can be distributed to the
     * customer (e.g., 3.3KW power) without any risk of overload. The Available Power shall
     * use the same formatting conventions as the one used in the simple metering cluster
     * formatting attribute set for the InstantaneousDemand attribute, i.e., the
     * UnitOfMeasure and DemandFormatting.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAvailablePowerAsync() {
        return read(serverAttributes.get(ATTR_AVAILABLEPOWER));
    }

    /**
     * Synchronously get the <i>Available Power</i> attribute [attribute ID <b>0x000D</b>].
     * <p>
     * This attribute represents the InstantaneousDemand that can be distributed to the
     * customer (e.g., 3.3KW power) without any risk of overload. The Available Power shall
     * use the same formatting conventions as the one used in the simple metering cluster
     * formatting attribute set for the InstantaneousDemand attribute, i.e., the
     * UnitOfMeasure and DemandFormatting.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getAvailablePower(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_AVAILABLEPOWER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_AVAILABLEPOWER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_AVAILABLEPOWER));
    }

    /**
     * Set reporting for the <i>Available Power</i> attribute [attribute ID <b>0x000D</b>].
     * <p>
     * This attribute represents the InstantaneousDemand that can be distributed to the
     * customer (e.g., 3.3KW power) without any risk of overload. The Available Power shall
     * use the same formatting conventions as the one used in the simple metering cluster
     * formatting attribute set for the InstantaneousDemand attribute, i.e., the
     * UnitOfMeasure and DemandFormatting.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setAvailablePowerReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_AVAILABLEPOWER), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Power Threshold</i> attribute [attribute ID <b>0x000E</b>].
     * <p>
     * This attribute represents a threshold of InstantaneousDemand distributed to the
     * customer (e.g., 4.191KW) that will lead to an imminent risk of overload. The
     * PowerThreshold shall use the same formatting conventions as the one used in the
     * AvailablePower attributes and therefore in the simple metering cluster formatting
     * attribute set for the InstantaneousDemand attribute, i.e., the UnitOfMeasure and
     * DemandFormatting.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPowerThresholdAsync() {
        return read(serverAttributes.get(ATTR_POWERTHRESHOLD));
    }

    /**
     * Synchronously get the <i>Power Threshold</i> attribute [attribute ID <b>0x000E</b>].
     * <p>
     * This attribute represents a threshold of InstantaneousDemand distributed to the
     * customer (e.g., 4.191KW) that will lead to an imminent risk of overload. The
     * PowerThreshold shall use the same formatting conventions as the one used in the
     * AvailablePower attributes and therefore in the simple metering cluster formatting
     * attribute set for the InstantaneousDemand attribute, i.e., the UnitOfMeasure and
     * DemandFormatting.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getPowerThreshold(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_POWERTHRESHOLD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_POWERTHRESHOLD).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_POWERTHRESHOLD));
    }

    /**
     * Set reporting for the <i>Power Threshold</i> attribute [attribute ID <b>0x000E</b>].
     * <p>
     * This attribute represents a threshold of InstantaneousDemand distributed to the
     * customer (e.g., 4.191KW) that will lead to an imminent risk of overload. The
     * PowerThreshold shall use the same formatting conventions as the one used in the
     * AvailablePower attributes and therefore in the simple metering cluster formatting
     * attribute set for the InstantaneousDemand attribute, i.e., the UnitOfMeasure and
     * DemandFormatting.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setPowerThresholdReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_POWERTHRESHOLD), minInterval, maxInterval, reportableChange);
    }
}
