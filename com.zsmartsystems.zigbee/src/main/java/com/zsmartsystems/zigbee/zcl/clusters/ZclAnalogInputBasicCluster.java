/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
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
 * <b>Analog Input (Basic)</b> cluster implementation (<i>Cluster ID 0x000C</i>).
 * <p>
 * The Analog Input (Basic) cluster provides an interface for reading the value of an analog
 * measurement and accessing various characteristics of that measurement. The cluster is
 * typically used to implement a sensor that measures an analog physical quantity.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T20:27:57Z")
public class ZclAnalogInputBasicCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x000C;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Analog Input (Basic)";

    // Attribute constants
    /**
     * The Description attribute, of type Character string, may be used to hold a description
     * of the usage of the input, output or value, as appropriate to the cluster. The character
     * set used shall be ASCII, and the at- tribute shall contain a maximum of 16 characters,
     * which shall be printable but are otherwise unrestricted.
     */
    public static final int ATTR_DESCRIPTION = 0x001C;
    /**
     * The MaxPresentValue attribute, of type Single precision, indicates the highest value
     * that can be reliably obtained for the PresentValue attribute of an Analog Input
     * cluster, or which can reliably be used for the PresentValue attribute of an Analog
     * Output or Analog Value cluster.
     */
    public static final int ATTR_MAXPRESENTVALUE = 0x0041;
    /**
     * The MinPresentValue attribute, of type Single precision, indicates the lowest value
     * that can be reliably ob- tained for the PresentValue attribute of an Analog Input
     * cluster, or which can reliably be used for the PresentValue attribute of an Analog
     * Output or Analog Value cluster.
     */
    public static final int ATTR_MINPRESENTVALUE = 0x0045;
    public static final int ATTR_OUTOFSERVICE = 0x0051;
    /**
     * The PresentValue attribute indicates the current value of the input, output or value,
     * as appropriate for the cluster. For Analog clusters it is of type single precision, for
     * Binary clusters it is of type Boolean, and for multistate clusters it is of type Unsigned
     * 16-bit integer. The PresentValue attribute of an input cluster shall be writable when
     * OutOfService is TRUE. When the PriorityArray attribute is implemented, writing to
     * PresentValue shall be equivalent to writing to element 16 of PriorityArray, i.e., with
     * a priority of 16.
     */
    public static final int ATTR_PRESENTVALUE = 0x0055;
    /**
     * The Reliability attribute, of type 8-bit enumeration, provides an indication of
     * whether the PresentValueor the operation of the physical input, output or value in
     * question (as appropriate for the cluster) is “reliable” as far as can be determined and,
     * if not, why not. The Reliability attribute may have any of the following values:
     * <p>
     * NO-FAULT-DETECTED (0) OVER-RANGE (2) UNDER-RANGE (3) OPEN-LOOP (4) SHORTED-LOOP (5)
     * UNRELIABLE-OTHER (7) PROCESS-ERROR (8) MULTI-STATE-FAULT (9) CONFIGURATION-ERROR
     * (10)
     */
    public static final int ATTR_RELIABILITY = 0x0067;
    /**
     * This attribute, of type Single precision, indicates the smallest recognizable change
     * to PresentValue.
     */
    public static final int ATTR_RESOLUTION = 0x006A;
    /**
     * This attribute, of type bitmap, represents four Boolean flags that indicate the
     * general “health” of the analog sensor. Three of the flags are associated with the values
     * of other optional attributes of this cluster. A more detailed status could be
     * determined by reading the optional attributes (if supported) that are linked to these
     * flags. The relationship between individual flags is not defined.
     * <p>
     * The four flags are Bit 0 = IN_ALARM, Bit 1 = FAULT, Bit 2 = OVERRIDDEN, Bit 3 = OUT OF SERVICE
     * <p>
     * where:
     * <p>
     * IN_ALARM -Logical FALSE (0) if the EventStateattribute has a value of NORMAL,
     * otherwise logical TRUE (1). This bit is always 0 unless the cluster implementing the
     * EventState attribute is implemented on the same endpoint.
     * <p>
     * FAULT -Logical TRUE (1) if the Reliability attribute is present and does not have a value
     * of NO FAULT DETECTED, otherwise logical FALSE (0).
     * <p>
     * OVERRIDDEN -Logical TRUE (1) if the cluster has been overridden by some mechanism local
     * to the device. Otherwise, the value is logical FALSE (0). In this context, for an input
     * cluster, “overridden” is taken to mean that the PresentValue and
     * Reliability(optional) attributes are no longer tracking changes to the physical
     * input. For an Output cluster, “overridden” is taken to mean that the physical output is
     * no longer tracking changes to the PresentValue attribute and the Reliability
     * attribute is no longer a reflection of the physical output. For a Value cluster,
     * “overridden” is taken to mean that the PresentValue attribute is not writeable.
     * <p>
     * OUT OF SERVICE -Logical TRUE (1) if the OutOfService attribute has a value of TRUE,
     * otherwise logical FALSE (0).
     */
    public static final int ATTR_STATUSFLAGS = 0x006F;
    /**
     * The EngineeringUnits attribute indicates the physical units associated with the
     * value of the PresentValue attribute of an Analog cluster.
     * <p>
     * Values 0x0000 to 0x00fe are reserved for the list of engineering units with
     * corresponding values specified in Clause 21 of the BACnet standard. 0x00ff represents
     * 'other'. Values 0x0100 to 0xffff are available for proprietary use.
     * <p>
     * If the ApplicationType attribute is implemented, and is set to a value with a defined
     * physical unit, the physical unit defined in ApplicationType takes priority over
     * EngineeringUnits.
     * <p>
     * This attribute is defined to be Read Only, but a vendor can decide to allow this to be
     * written to if ApplicationType is also supported. If this attribute is written to, how
     * the device handles invalid units (e.g., changing Deg F to Cubic Feet per Minute), any
     * local display or other vendor-specific operation (upon the change) is a local matter.
     */
    public static final int ATTR_ENGINEERINGUNITS = 0x0075;
    /**
     * The ApplicationType attribute is an unsigned 32 bit integer that indicates the
     * specific application usage for this cluster. (Note: This attribute has no BACnet
     * equivalent). ApplicationType is subdivided into Group, Type and an Index number, as
     * follows.
     * <p>
     * Group = Bits 24-31 An indication of the cluster this attribute is part of.
     * <p>
     * Type = Bits 16-23 For Analog clusters, the physical quantity that the Present Value
     * attribute of the cluster represents. For Binary and Multistate clusters, the
     * application usage domain.
     * <p>
     * Index = Bits 0-15The specific application usage of the cluster.
     */
    public static final int ATTR_APPLICATIONTYPE = 0x0100;

    @Override
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        Map<Integer, ZclAttribute> attributeMap = super.initializeClientAttributes();

        return attributeMap;
    }

    @Override
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        Map<Integer, ZclAttribute> attributeMap = super.initializeServerAttributes();

        attributeMap.put(ATTR_DESCRIPTION, new ZclAttribute(this, ATTR_DESCRIPTION, "Description", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_MAXPRESENTVALUE, new ZclAttribute(this, ATTR_MAXPRESENTVALUE, "Max Present Value", ZclDataType.FLOAT_32_BIT, false, true, true, false));
        attributeMap.put(ATTR_MINPRESENTVALUE, new ZclAttribute(this, ATTR_MINPRESENTVALUE, "Min Present Value", ZclDataType.FLOAT_32_BIT, false, true, true, false));
        attributeMap.put(ATTR_OUTOFSERVICE, new ZclAttribute(this, ATTR_OUTOFSERVICE, "Out Of Service", ZclDataType.BOOLEAN, false, true, true, false));
        attributeMap.put(ATTR_PRESENTVALUE, new ZclAttribute(this, ATTR_PRESENTVALUE, "Present Value", ZclDataType.FLOAT_32_BIT, false, true, true, false));
        attributeMap.put(ATTR_RELIABILITY, new ZclAttribute(this, ATTR_RELIABILITY, "Reliability", ZclDataType.ENUMERATION_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_RESOLUTION, new ZclAttribute(this, ATTR_RESOLUTION, "Resolution", ZclDataType.FLOAT_32_BIT, false, true, true, false));
        attributeMap.put(ATTR_STATUSFLAGS, new ZclAttribute(this, ATTR_STATUSFLAGS, "Status Flags", ZclDataType.BITMAP_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_ENGINEERINGUNITS, new ZclAttribute(this, ATTR_ENGINEERINGUNITS, "Engineering Units", ZclDataType.ENUMERATION_32_BIT, false, true, true, false));
        attributeMap.put(ATTR_APPLICATIONTYPE, new ZclAttribute(this, ATTR_APPLICATIONTYPE, "Application Type", ZclDataType.SIGNED_32_BIT_INTEGER, false, true, true, false));

        return attributeMap;
    }


    /**
     * Default constructor to create a Analog Input (Basic) cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclAnalogInputBasicCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Set the <i>Description</i> attribute [attribute ID <b>0x001C</b>].
     * <p>
     * The Description attribute, of type Character string, may be used to hold a description
     * of the usage of the input, output or value, as appropriate to the cluster. The character
     * set used shall be ASCII, and the at- tribute shall contain a maximum of 16 characters,
     * which shall be printable but are otherwise unrestricted.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param description the {@link String} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setDescription(final String description) {
        return write(serverAttributes.get(ATTR_DESCRIPTION), description);
    }

    /**
     * Get the <i>Description</i> attribute [attribute ID <b>0x001C</b>].
     * <p>
     * The Description attribute, of type Character string, may be used to hold a description
     * of the usage of the input, output or value, as appropriate to the cluster. The character
     * set used shall be ASCII, and the at- tribute shall contain a maximum of 16 characters,
     * which shall be printable but are otherwise unrestricted.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDescriptionAsync() {
        return read(serverAttributes.get(ATTR_DESCRIPTION));
    }

    /**
     * Synchronously get the <i>Description</i> attribute [attribute ID <b>0x001C</b>].
     * <p>
     * The Description attribute, of type Character string, may be used to hold a description
     * of the usage of the input, output or value, as appropriate to the cluster. The character
     * set used shall be ASCII, and the at- tribute shall contain a maximum of 16 characters,
     * which shall be printable but are otherwise unrestricted.
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
    public String getDescription(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DESCRIPTION).isLastValueCurrent(refreshPeriod)) {
            return (String) serverAttributes.get(ATTR_DESCRIPTION).getLastValue();
        }

        return (String) readSync(serverAttributes.get(ATTR_DESCRIPTION));
    }

    /**
     * Set the <i>Max Present Value</i> attribute [attribute ID <b>0x0041</b>].
     * <p>
     * The MaxPresentValue attribute, of type Single precision, indicates the highest value
     * that can be reliably obtained for the PresentValue attribute of an Analog Input
     * cluster, or which can reliably be used for the PresentValue attribute of an Analog
     * Output or Analog Value cluster.
     * <p>
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param maxPresentValue the {@link Double} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setMaxPresentValue(final Double maxPresentValue) {
        return write(serverAttributes.get(ATTR_MAXPRESENTVALUE), maxPresentValue);
    }

    /**
     * Get the <i>Max Present Value</i> attribute [attribute ID <b>0x0041</b>].
     * <p>
     * The MaxPresentValue attribute, of type Single precision, indicates the highest value
     * that can be reliably obtained for the PresentValue attribute of an Analog Input
     * cluster, or which can reliably be used for the PresentValue attribute of an Analog
     * Output or Analog Value cluster.
     * <p>
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMaxPresentValueAsync() {
        return read(serverAttributes.get(ATTR_MAXPRESENTVALUE));
    }

    /**
     * Synchronously get the <i>Max Present Value</i> attribute [attribute ID <b>0x0041</b>].
     * <p>
     * The MaxPresentValue attribute, of type Single precision, indicates the highest value
     * that can be reliably obtained for the PresentValue attribute of an Analog Input
     * cluster, or which can reliably be used for the PresentValue attribute of an Analog
     * Output or Analog Value cluster.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Double} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Double getMaxPresentValue(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MAXPRESENTVALUE).isLastValueCurrent(refreshPeriod)) {
            return (Double) serverAttributes.get(ATTR_MAXPRESENTVALUE).getLastValue();
        }

        return (Double) readSync(serverAttributes.get(ATTR_MAXPRESENTVALUE));
    }

    /**
     * Set the <i>Min Present Value</i> attribute [attribute ID <b>0x0045</b>].
     * <p>
     * The MinPresentValue attribute, of type Single precision, indicates the lowest value
     * that can be reliably ob- tained for the PresentValue attribute of an Analog Input
     * cluster, or which can reliably be used for the PresentValue attribute of an Analog
     * Output or Analog Value cluster.
     * <p>
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param minPresentValue the {@link Double} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setMinPresentValue(final Double minPresentValue) {
        return write(serverAttributes.get(ATTR_MINPRESENTVALUE), minPresentValue);
    }

    /**
     * Get the <i>Min Present Value</i> attribute [attribute ID <b>0x0045</b>].
     * <p>
     * The MinPresentValue attribute, of type Single precision, indicates the lowest value
     * that can be reliably ob- tained for the PresentValue attribute of an Analog Input
     * cluster, or which can reliably be used for the PresentValue attribute of an Analog
     * Output or Analog Value cluster.
     * <p>
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMinPresentValueAsync() {
        return read(serverAttributes.get(ATTR_MINPRESENTVALUE));
    }

    /**
     * Synchronously get the <i>Min Present Value</i> attribute [attribute ID <b>0x0045</b>].
     * <p>
     * The MinPresentValue attribute, of type Single precision, indicates the lowest value
     * that can be reliably ob- tained for the PresentValue attribute of an Analog Input
     * cluster, or which can reliably be used for the PresentValue attribute of an Analog
     * Output or Analog Value cluster.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Double} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Double getMinPresentValue(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MINPRESENTVALUE).isLastValueCurrent(refreshPeriod)) {
            return (Double) serverAttributes.get(ATTR_MINPRESENTVALUE).getLastValue();
        }

        return (Double) readSync(serverAttributes.get(ATTR_MINPRESENTVALUE));
    }

    /**
     * Set the <i>Out Of Service</i> attribute [attribute ID <b>0x0051</b>].
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param outOfService the {@link Boolean} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setOutOfService(final Boolean outOfService) {
        return write(serverAttributes.get(ATTR_OUTOFSERVICE), outOfService);
    }

    /**
     * Get the <i>Out Of Service</i> attribute [attribute ID <b>0x0051</b>].
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getOutOfServiceAsync() {
        return read(serverAttributes.get(ATTR_OUTOFSERVICE));
    }

    /**
     * Synchronously get the <i>Out Of Service</i> attribute [attribute ID <b>0x0051</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Boolean} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Boolean getOutOfService(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_OUTOFSERVICE).isLastValueCurrent(refreshPeriod)) {
            return (Boolean) serverAttributes.get(ATTR_OUTOFSERVICE).getLastValue();
        }

        return (Boolean) readSync(serverAttributes.get(ATTR_OUTOFSERVICE));
    }

    /**
     * Set the <i>Present Value</i> attribute [attribute ID <b>0x0055</b>].
     * <p>
     * The PresentValue attribute indicates the current value of the input, output or value,
     * as appropriate for the cluster. For Analog clusters it is of type single precision, for
     * Binary clusters it is of type Boolean, and for multistate clusters it is of type Unsigned
     * 16-bit integer. The PresentValue attribute of an input cluster shall be writable when
     * OutOfService is TRUE. When the PriorityArray attribute is implemented, writing to
     * PresentValue shall be equivalent to writing to element 16 of PriorityArray, i.e., with
     * a priority of 16.
     * <p>
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param presentValue the {@link Double} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setPresentValue(final Double presentValue) {
        return write(serverAttributes.get(ATTR_PRESENTVALUE), presentValue);
    }

    /**
     * Get the <i>Present Value</i> attribute [attribute ID <b>0x0055</b>].
     * <p>
     * The PresentValue attribute indicates the current value of the input, output or value,
     * as appropriate for the cluster. For Analog clusters it is of type single precision, for
     * Binary clusters it is of type Boolean, and for multistate clusters it is of type Unsigned
     * 16-bit integer. The PresentValue attribute of an input cluster shall be writable when
     * OutOfService is TRUE. When the PriorityArray attribute is implemented, writing to
     * PresentValue shall be equivalent to writing to element 16 of PriorityArray, i.e., with
     * a priority of 16.
     * <p>
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPresentValueAsync() {
        return read(serverAttributes.get(ATTR_PRESENTVALUE));
    }

    /**
     * Synchronously get the <i>Present Value</i> attribute [attribute ID <b>0x0055</b>].
     * <p>
     * The PresentValue attribute indicates the current value of the input, output or value,
     * as appropriate for the cluster. For Analog clusters it is of type single precision, for
     * Binary clusters it is of type Boolean, and for multistate clusters it is of type Unsigned
     * 16-bit integer. The PresentValue attribute of an input cluster shall be writable when
     * OutOfService is TRUE. When the PriorityArray attribute is implemented, writing to
     * PresentValue shall be equivalent to writing to element 16 of PriorityArray, i.e., with
     * a priority of 16.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Double} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Double getPresentValue(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_PRESENTVALUE).isLastValueCurrent(refreshPeriod)) {
            return (Double) serverAttributes.get(ATTR_PRESENTVALUE).getLastValue();
        }

        return (Double) readSync(serverAttributes.get(ATTR_PRESENTVALUE));
    }

    /**
     * Set the <i>Reliability</i> attribute [attribute ID <b>0x0067</b>].
     * <p>
     * The Reliability attribute, of type 8-bit enumeration, provides an indication of
     * whether the PresentValueor the operation of the physical input, output or value in
     * question (as appropriate for the cluster) is “reliable” as far as can be determined and,
     * if not, why not. The Reliability attribute may have any of the following values:
     * <p>
     * NO-FAULT-DETECTED (0) OVER-RANGE (2) UNDER-RANGE (3) OPEN-LOOP (4) SHORTED-LOOP (5)
     * UNRELIABLE-OTHER (7) PROCESS-ERROR (8) MULTI-STATE-FAULT (9) CONFIGURATION-ERROR
     * (10)
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param reliability the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setReliability(final Integer reliability) {
        return write(serverAttributes.get(ATTR_RELIABILITY), reliability);
    }

    /**
     * Get the <i>Reliability</i> attribute [attribute ID <b>0x0067</b>].
     * <p>
     * The Reliability attribute, of type 8-bit enumeration, provides an indication of
     * whether the PresentValueor the operation of the physical input, output or value in
     * question (as appropriate for the cluster) is “reliable” as far as can be determined and,
     * if not, why not. The Reliability attribute may have any of the following values:
     * <p>
     * NO-FAULT-DETECTED (0) OVER-RANGE (2) UNDER-RANGE (3) OPEN-LOOP (4) SHORTED-LOOP (5)
     * UNRELIABLE-OTHER (7) PROCESS-ERROR (8) MULTI-STATE-FAULT (9) CONFIGURATION-ERROR
     * (10)
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getReliabilityAsync() {
        return read(serverAttributes.get(ATTR_RELIABILITY));
    }

    /**
     * Synchronously get the <i>Reliability</i> attribute [attribute ID <b>0x0067</b>].
     * <p>
     * The Reliability attribute, of type 8-bit enumeration, provides an indication of
     * whether the PresentValueor the operation of the physical input, output or value in
     * question (as appropriate for the cluster) is “reliable” as far as can be determined and,
     * if not, why not. The Reliability attribute may have any of the following values:
     * <p>
     * NO-FAULT-DETECTED (0) OVER-RANGE (2) UNDER-RANGE (3) OPEN-LOOP (4) SHORTED-LOOP (5)
     * UNRELIABLE-OTHER (7) PROCESS-ERROR (8) MULTI-STATE-FAULT (9) CONFIGURATION-ERROR
     * (10)
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getReliability(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RELIABILITY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RELIABILITY).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RELIABILITY));
    }

    /**
     * Set the <i>Resolution</i> attribute [attribute ID <b>0x006A</b>].
     * <p>
     * This attribute, of type Single precision, indicates the smallest recognizable change
     * to PresentValue.
     * <p>
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param resolution the {@link Double} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setResolution(final Double resolution) {
        return write(serverAttributes.get(ATTR_RESOLUTION), resolution);
    }

    /**
     * Get the <i>Resolution</i> attribute [attribute ID <b>0x006A</b>].
     * <p>
     * This attribute, of type Single precision, indicates the smallest recognizable change
     * to PresentValue.
     * <p>
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getResolutionAsync() {
        return read(serverAttributes.get(ATTR_RESOLUTION));
    }

    /**
     * Synchronously get the <i>Resolution</i> attribute [attribute ID <b>0x006A</b>].
     * <p>
     * This attribute, of type Single precision, indicates the smallest recognizable change
     * to PresentValue.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Double}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Double} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Double getResolution(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RESOLUTION).isLastValueCurrent(refreshPeriod)) {
            return (Double) serverAttributes.get(ATTR_RESOLUTION).getLastValue();
        }

        return (Double) readSync(serverAttributes.get(ATTR_RESOLUTION));
    }

    /**
     * Set the <i>Status Flags</i> attribute [attribute ID <b>0x006F</b>].
     * <p>
     * This attribute, of type bitmap, represents four Boolean flags that indicate the
     * general “health” of the analog sensor. Three of the flags are associated with the values
     * of other optional attributes of this cluster. A more detailed status could be
     * determined by reading the optional attributes (if supported) that are linked to these
     * flags. The relationship between individual flags is not defined.
     * <p>
     * The four flags are Bit 0 = IN_ALARM, Bit 1 = FAULT, Bit 2 = OVERRIDDEN, Bit 3 = OUT OF SERVICE
     * <p>
     * where:
     * <p>
     * IN_ALARM -Logical FALSE (0) if the EventStateattribute has a value of NORMAL,
     * otherwise logical TRUE (1). This bit is always 0 unless the cluster implementing the
     * EventState attribute is implemented on the same endpoint.
     * <p>
     * FAULT -Logical TRUE (1) if the Reliability attribute is present and does not have a value
     * of NO FAULT DETECTED, otherwise logical FALSE (0).
     * <p>
     * OVERRIDDEN -Logical TRUE (1) if the cluster has been overridden by some mechanism local
     * to the device. Otherwise, the value is logical FALSE (0). In this context, for an input
     * cluster, “overridden” is taken to mean that the PresentValue and
     * Reliability(optional) attributes are no longer tracking changes to the physical
     * input. For an Output cluster, “overridden” is taken to mean that the physical output is
     * no longer tracking changes to the PresentValue attribute and the Reliability
     * attribute is no longer a reflection of the physical output. For a Value cluster,
     * “overridden” is taken to mean that the PresentValue attribute is not writeable.
     * <p>
     * OUT OF SERVICE -Logical TRUE (1) if the OutOfService attribute has a value of TRUE,
     * otherwise logical FALSE (0).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param statusFlags the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setStatusFlags(final Integer statusFlags) {
        return write(serverAttributes.get(ATTR_STATUSFLAGS), statusFlags);
    }

    /**
     * Get the <i>Status Flags</i> attribute [attribute ID <b>0x006F</b>].
     * <p>
     * This attribute, of type bitmap, represents four Boolean flags that indicate the
     * general “health” of the analog sensor. Three of the flags are associated with the values
     * of other optional attributes of this cluster. A more detailed status could be
     * determined by reading the optional attributes (if supported) that are linked to these
     * flags. The relationship between individual flags is not defined.
     * <p>
     * The four flags are Bit 0 = IN_ALARM, Bit 1 = FAULT, Bit 2 = OVERRIDDEN, Bit 3 = OUT OF SERVICE
     * <p>
     * where:
     * <p>
     * IN_ALARM -Logical FALSE (0) if the EventStateattribute has a value of NORMAL,
     * otherwise logical TRUE (1). This bit is always 0 unless the cluster implementing the
     * EventState attribute is implemented on the same endpoint.
     * <p>
     * FAULT -Logical TRUE (1) if the Reliability attribute is present and does not have a value
     * of NO FAULT DETECTED, otherwise logical FALSE (0).
     * <p>
     * OVERRIDDEN -Logical TRUE (1) if the cluster has been overridden by some mechanism local
     * to the device. Otherwise, the value is logical FALSE (0). In this context, for an input
     * cluster, “overridden” is taken to mean that the PresentValue and
     * Reliability(optional) attributes are no longer tracking changes to the physical
     * input. For an Output cluster, “overridden” is taken to mean that the physical output is
     * no longer tracking changes to the PresentValue attribute and the Reliability
     * attribute is no longer a reflection of the physical output. For a Value cluster,
     * “overridden” is taken to mean that the PresentValue attribute is not writeable.
     * <p>
     * OUT OF SERVICE -Logical TRUE (1) if the OutOfService attribute has a value of TRUE,
     * otherwise logical FALSE (0).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getStatusFlagsAsync() {
        return read(serverAttributes.get(ATTR_STATUSFLAGS));
    }

    /**
     * Synchronously get the <i>Status Flags</i> attribute [attribute ID <b>0x006F</b>].
     * <p>
     * This attribute, of type bitmap, represents four Boolean flags that indicate the
     * general “health” of the analog sensor. Three of the flags are associated with the values
     * of other optional attributes of this cluster. A more detailed status could be
     * determined by reading the optional attributes (if supported) that are linked to these
     * flags. The relationship between individual flags is not defined.
     * <p>
     * The four flags are Bit 0 = IN_ALARM, Bit 1 = FAULT, Bit 2 = OVERRIDDEN, Bit 3 = OUT OF SERVICE
     * <p>
     * where:
     * <p>
     * IN_ALARM -Logical FALSE (0) if the EventStateattribute has a value of NORMAL,
     * otherwise logical TRUE (1). This bit is always 0 unless the cluster implementing the
     * EventState attribute is implemented on the same endpoint.
     * <p>
     * FAULT -Logical TRUE (1) if the Reliability attribute is present and does not have a value
     * of NO FAULT DETECTED, otherwise logical FALSE (0).
     * <p>
     * OVERRIDDEN -Logical TRUE (1) if the cluster has been overridden by some mechanism local
     * to the device. Otherwise, the value is logical FALSE (0). In this context, for an input
     * cluster, “overridden” is taken to mean that the PresentValue and
     * Reliability(optional) attributes are no longer tracking changes to the physical
     * input. For an Output cluster, “overridden” is taken to mean that the physical output is
     * no longer tracking changes to the PresentValue attribute and the Reliability
     * attribute is no longer a reflection of the physical output. For a Value cluster,
     * “overridden” is taken to mean that the PresentValue attribute is not writeable.
     * <p>
     * OUT OF SERVICE -Logical TRUE (1) if the OutOfService attribute has a value of TRUE,
     * otherwise logical FALSE (0).
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getStatusFlags(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_STATUSFLAGS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_STATUSFLAGS).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_STATUSFLAGS));
    }

    /**
     * Set the <i>Engineering Units</i> attribute [attribute ID <b>0x0075</b>].
     * <p>
     * The EngineeringUnits attribute indicates the physical units associated with the
     * value of the PresentValue attribute of an Analog cluster.
     * <p>
     * Values 0x0000 to 0x00fe are reserved for the list of engineering units with
     * corresponding values specified in Clause 21 of the BACnet standard. 0x00ff represents
     * 'other'. Values 0x0100 to 0xffff are available for proprietary use.
     * <p>
     * If the ApplicationType attribute is implemented, and is set to a value with a defined
     * physical unit, the physical unit defined in ApplicationType takes priority over
     * EngineeringUnits.
     * <p>
     * This attribute is defined to be Read Only, but a vendor can decide to allow this to be
     * written to if ApplicationType is also supported. If this attribute is written to, how
     * the device handles invalid units (e.g., changing Deg F to Cubic Feet per Minute), any
     * local display or other vendor-specific operation (upon the change) is a local matter.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param engineeringUnits the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setEngineeringUnits(final Integer engineeringUnits) {
        return write(serverAttributes.get(ATTR_ENGINEERINGUNITS), engineeringUnits);
    }

    /**
     * Get the <i>Engineering Units</i> attribute [attribute ID <b>0x0075</b>].
     * <p>
     * The EngineeringUnits attribute indicates the physical units associated with the
     * value of the PresentValue attribute of an Analog cluster.
     * <p>
     * Values 0x0000 to 0x00fe are reserved for the list of engineering units with
     * corresponding values specified in Clause 21 of the BACnet standard. 0x00ff represents
     * 'other'. Values 0x0100 to 0xffff are available for proprietary use.
     * <p>
     * If the ApplicationType attribute is implemented, and is set to a value with a defined
     * physical unit, the physical unit defined in ApplicationType takes priority over
     * EngineeringUnits.
     * <p>
     * This attribute is defined to be Read Only, but a vendor can decide to allow this to be
     * written to if ApplicationType is also supported. If this attribute is written to, how
     * the device handles invalid units (e.g., changing Deg F to Cubic Feet per Minute), any
     * local display or other vendor-specific operation (upon the change) is a local matter.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getEngineeringUnitsAsync() {
        return read(serverAttributes.get(ATTR_ENGINEERINGUNITS));
    }

    /**
     * Synchronously get the <i>Engineering Units</i> attribute [attribute ID <b>0x0075</b>].
     * <p>
     * The EngineeringUnits attribute indicates the physical units associated with the
     * value of the PresentValue attribute of an Analog cluster.
     * <p>
     * Values 0x0000 to 0x00fe are reserved for the list of engineering units with
     * corresponding values specified in Clause 21 of the BACnet standard. 0x00ff represents
     * 'other'. Values 0x0100 to 0xffff are available for proprietary use.
     * <p>
     * If the ApplicationType attribute is implemented, and is set to a value with a defined
     * physical unit, the physical unit defined in ApplicationType takes priority over
     * EngineeringUnits.
     * <p>
     * This attribute is defined to be Read Only, but a vendor can decide to allow this to be
     * written to if ApplicationType is also supported. If this attribute is written to, how
     * the device handles invalid units (e.g., changing Deg F to Cubic Feet per Minute), any
     * local display or other vendor-specific operation (upon the change) is a local matter.
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getEngineeringUnits(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ENGINEERINGUNITS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ENGINEERINGUNITS).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ENGINEERINGUNITS));
    }

    /**
     * Set the <i>Application Type</i> attribute [attribute ID <b>0x0100</b>].
     * <p>
     * The ApplicationType attribute is an unsigned 32 bit integer that indicates the
     * specific application usage for this cluster. (Note: This attribute has no BACnet
     * equivalent). ApplicationType is subdivided into Group, Type and an Index number, as
     * follows.
     * <p>
     * Group = Bits 24-31 An indication of the cluster this attribute is part of.
     * <p>
     * Type = Bits 16-23 For Analog clusters, the physical quantity that the Present Value
     * attribute of the cluster represents. For Binary and Multistate clusters, the
     * application usage domain.
     * <p>
     * Index = Bits 0-15The specific application usage of the cluster.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param applicationType the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setApplicationType(final Integer applicationType) {
        return write(serverAttributes.get(ATTR_APPLICATIONTYPE), applicationType);
    }

    /**
     * Get the <i>Application Type</i> attribute [attribute ID <b>0x0100</b>].
     * <p>
     * The ApplicationType attribute is an unsigned 32 bit integer that indicates the
     * specific application usage for this cluster. (Note: This attribute has no BACnet
     * equivalent). ApplicationType is subdivided into Group, Type and an Index number, as
     * follows.
     * <p>
     * Group = Bits 24-31 An indication of the cluster this attribute is part of.
     * <p>
     * Type = Bits 16-23 For Analog clusters, the physical quantity that the Present Value
     * attribute of the cluster represents. For Binary and Multistate clusters, the
     * application usage domain.
     * <p>
     * Index = Bits 0-15The specific application usage of the cluster.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getApplicationTypeAsync() {
        return read(serverAttributes.get(ATTR_APPLICATIONTYPE));
    }

    /**
     * Synchronously get the <i>Application Type</i> attribute [attribute ID <b>0x0100</b>].
     * <p>
     * The ApplicationType attribute is an unsigned 32 bit integer that indicates the
     * specific application usage for this cluster. (Note: This attribute has no BACnet
     * equivalent). ApplicationType is subdivided into Group, Type and an Index number, as
     * follows.
     * <p>
     * Group = Bits 24-31 An indication of the cluster this attribute is part of.
     * <p>
     * Type = Bits 16-23 For Analog clusters, the physical quantity that the Present Value
     * attribute of the cluster represents. For Binary and Multistate clusters, the
     * application usage domain.
     * <p>
     * Index = Bits 0-15The specific application usage of the cluster.
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getApplicationType(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_APPLICATIONTYPE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_APPLICATIONTYPE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_APPLICATIONTYPE));
    }
}
