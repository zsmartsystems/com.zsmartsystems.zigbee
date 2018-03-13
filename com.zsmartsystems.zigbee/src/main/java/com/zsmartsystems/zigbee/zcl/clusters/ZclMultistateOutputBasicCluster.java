/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import javax.annotation.Generated;

/**
 * <b>Multistate Output (Basic)</b> cluster implementation (<i>Cluster ID 0x0013</i>).
 * <p>
 * The Multistate Output (Basic) cluster provides an interface for setting the value of an output
 * that can take one of a number of discrete values, and accessing characteristics of that value.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class ZclMultistateOutputBasicCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0013;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Multistate Output (Basic)";

    // Attribute constants
    /**
     * This  attribute, of type Array of Character strings, holds descriptions of all possible
     * states of a multistate PresentValue.  The number of descriptions matches the number of states
     * defined in the NumberOfStates property. The PresentValue, interpreted as an integer, serves as
     * an index into the array. If the size of this array is changed, the NumberOfStates property SHALL
     * also be changed to the same value. The character set used SHALL be ASCII, and the attribute
     * SHALL contain a maximum of 16 characters, which SHALL be printable but are otherwise unrestricted.
     */
    public static final int ATTR_STATETEXT = 0x000E;
    /**
     * The Description attribute, of type Character string, MAY be used to hold a description
     * of the usage of the input, output or value, as appropriate to the cluster. The character
     * set used SHALL be ASCII, and the attribute SHALL contain a maximum of 16 characters,
     * which SHALL be printable but are otherwise unrestricted.
     */
    public static final int ATTR_DESCRIPTION = 0x001C;
    /**
     * This attribute, of type Unsigned 16-bit integer, defines the number of states that a multistate
     * PresentValue MAY have. The NumberOfStates property SHALL always have a value greater than zero.
     * If the value of this property is changed, the size of the StateText array, if present, SHALL also
     * be changed to the same value. The states are numbered consecutively, starting with 1.
     */
    public static final int ATTR_NUMBEROFSTATES = 0x004A;
    /**
     * The OutOfService attribute, of type Boolean, indicates whether (TRUE) or not (FALSE) the physical
     * input, output or value that the cluster represents is not in service. For an Input cluster, when
     * OutOfService is TRUE the PresentValue attribute is decoupled from the physical input and  will
     * not track changes to the  physical input. For an Output cluster, when OutOfService is TRUE the
     * PresentValue attribute is decoupled from the physical output, so changes to PresentValue will not
     * affect the physical output. For a Value cluster, when OutOfService is TRUE the PresentValue attribute
     * MAY be written to freely by software local to the device that the cluster resides on.
     */
    public static final int ATTR_OUTOFSERVICE = 0x0051;
    /**
     * The PresentValue attribute indicates the current value of the input, output or
     * value, as appropriate  for the cluster. For Analog clusters it is of type single precision, for Binary
     * clusters it is of type  Boolean, and for multistate clusters it is of type Unsigned 16-bit integer. The
     * PresentValue attribute of an input cluster SHALL be writable when OutOfService is TRUE. When the PriorityArray
     * attribute is implemented, writing to PresentValue SHALL be equivalent to writing to element 16 of PriorityArray,
     * i.e., with a priority of 16.
     */
    public static final int ATTR_PRESENTVALUE = 0x0055;
    /**
     * The Reliability attribute, of type 8-bit enumeration, provides an indication of whether
     * the PresentValueor the operation of the physical input, output or value in question (as
     * appropriate for the cluster) is “reliable” as far as can be determined and, if not, why
     * not. The Reliability attribute MAY have any of the following values:
     * <p>
     * NO-FAULT-DETECTED (0)
     * OVER-RANGE (2)
     * UNDER-RANGE (3)
     * OPEN-LOOP (4)
     * SHORTED-LOOP (5)
     * UNRELIABLE-OTHER (7)
     * PROCESS-ERROR (8)
     * MULTI-STATE-FAULT (9)
     * CONFIGURATION-ERROR (10)
     */
    public static final int ATTR_RELIABILITY = 0x0067;
    /**
     */
    public static final int ATTR_RELINGUISHDEFAULT = 0x0068;
    /**
     * This attribute, of type bitmap, represents four Boolean flags that indicate the general “health”
     * of the analog sensor. Three of the flags are associated with the values of other optional attributes
     * of this cluster. A more detailed status could be determined by reading the optional attributes (if
     * supported) that are linked to these flags. The relationship between individual flags is not defined.
     * <p>
     * The four flags are Bit 0 = IN_ALARM, Bit 1 = FAULT, Bit 2 = OVERRIDDEN, Bit 3 = OUT OF SERVICE
     * <p>
     * where:
     * <p>
     * IN_ALARM -Logical FALSE (0) if the EventStateattribute has a value of NORMAL, otherwise logical TRUE (1).
     * This bit is always 0 unless the cluster implementing the EventState attribute is implemented on the same
     * endpoint.
     * <p>
     * FAULT -Logical TRUE (1) if the Reliability attribute is present and does not have a value of NO FAULT DETECTED,
     * otherwise logical FALSE (0).
     * <p>
     * OVERRIDDEN -Logical TRUE (1) if the cluster has been overridden by some  mechanism local to the device.
     * Otherwise, the value is logical FALSE (0). In this context, for an input cluster, “overridden” is taken
     * to mean that the PresentValue and Reliability(optional) attributes are no longer tracking changes to the
     * physical input. For an Output cluster, “overridden” is taken to mean that the physical output is no longer
     * tracking changes to the PresentValue attribute and the Reliability attribute is no longer a reflection of
     * the physical output. For a Value cluster, “overridden” is taken to mean that the PresentValue attribute is
     * not writeable.
     * <p>
     * OUT OF SERVICE -Logical TRUE (1) if the OutOfService attribute has a value of TRUE, otherwise
     * logical FALSE (0).
     */
    public static final int ATTR_STATUSFLAGS = 0x006F;
    /**
     * The ApplicationType attribute is an unsigned 32 bit integer that indicates the specific
     * application usage for this cluster. (Note: This attribute has no BACnet equivalent).
     * ApplicationType is subdivided into Group, Type and an Index number, as follows.
     * <p>
     * Group = Bits 24 -31 An indication of the cluster this attribute is part of.
     * <p>
     * Type = Bits 16 -23 For Analog clusters, the physical quantity that the Present Value attribute
     * of the cluster represents. For Binary and Multistate clusters, the application usage domain.
     * <p>
     * Index = Bits 0 -15 The specific application usage of the cluster.
     */
    public static final int ATTR_APPLICATIONTYPE = 0x0100;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(9);

        attributeMap.put(ATTR_STATETEXT, new ZclAttribute(ZclClusterType.MULTISTATE_OUTPUT__BASIC, ATTR_STATETEXT, "StateText", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_DESCRIPTION, new ZclAttribute(ZclClusterType.MULTISTATE_OUTPUT__BASIC, ATTR_DESCRIPTION, "Description", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_NUMBEROFSTATES, new ZclAttribute(ZclClusterType.MULTISTATE_OUTPUT__BASIC, ATTR_NUMBEROFSTATES, "NumberOfStates", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, true, false));
        attributeMap.put(ATTR_OUTOFSERVICE, new ZclAttribute(ZclClusterType.MULTISTATE_OUTPUT__BASIC, ATTR_OUTOFSERVICE, "OutOfService", ZclDataType.BOOLEAN, true, true, true, false));
        attributeMap.put(ATTR_PRESENTVALUE, new ZclAttribute(ZclClusterType.MULTISTATE_OUTPUT__BASIC, ATTR_PRESENTVALUE, "PresentValue", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, true, false));
        attributeMap.put(ATTR_RELIABILITY, new ZclAttribute(ZclClusterType.MULTISTATE_OUTPUT__BASIC, ATTR_RELIABILITY, "Reliability", ZclDataType.ENUMERATION_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_RELINGUISHDEFAULT, new ZclAttribute(ZclClusterType.MULTISTATE_OUTPUT__BASIC, ATTR_RELINGUISHDEFAULT, "RelinguishDefault", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_STATUSFLAGS, new ZclAttribute(ZclClusterType.MULTISTATE_OUTPUT__BASIC, ATTR_STATUSFLAGS, "StatusFlags", ZclDataType.BITMAP_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_APPLICATIONTYPE, new ZclAttribute(ZclClusterType.MULTISTATE_OUTPUT__BASIC, ATTR_APPLICATIONTYPE, "ApplicationType", ZclDataType.SIGNED_32_BIT_INTEGER, false, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Multistate Output (Basic) cluster.
     *
     * @param zigbeeManager {@link ZigBeeNetworkManager}
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint}
     */
    public ZclMultistateOutputBasicCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeManager, zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Set the <i>StateText</i> attribute [attribute ID <b>14</b>].
     * <p>
     * This  attribute, of type Array of Character strings, holds descriptions of all possible
     * states of a multistate PresentValue.  The number of descriptions matches the number of states
     * defined in the NumberOfStates property. The PresentValue, interpreted as an integer, serves as
     * an index into the array. If the size of this array is changed, the NumberOfStates property SHALL
     * also be changed to the same value. The character set used SHALL be ASCII, and the attribute
     * SHALL contain a maximum of 16 characters, which SHALL be printable but are otherwise unrestricted.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param stateText the {@link String} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setStateText(final Object value) {
        return write(attributes.get(ATTR_STATETEXT), value);
    }

    /**
     * Get the <i>StateText</i> attribute [attribute ID <b>14</b>].
     * <p>
     * This  attribute, of type Array of Character strings, holds descriptions of all possible
     * states of a multistate PresentValue.  The number of descriptions matches the number of states
     * defined in the NumberOfStates property. The PresentValue, interpreted as an integer, serves as
     * an index into the array. If the size of this array is changed, the NumberOfStates property SHALL
     * also be changed to the same value. The character set used SHALL be ASCII, and the attribute
     * SHALL contain a maximum of 16 characters, which SHALL be printable but are otherwise unrestricted.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getStateTextAsync() {
        return read(attributes.get(ATTR_STATETEXT));
    }

    /**
     * Synchronously get the <i>StateText</i> attribute [attribute ID <b>14</b>].
     * <p>
     * This  attribute, of type Array of Character strings, holds descriptions of all possible
     * states of a multistate PresentValue.  The number of descriptions matches the number of states
     * defined in the NumberOfStates property. The PresentValue, interpreted as an integer, serves as
     * an index into the array. If the size of this array is changed, the NumberOfStates property SHALL
     * also be changed to the same value. The character set used SHALL be ASCII, and the attribute
     * SHALL contain a maximum of 16 characters, which SHALL be printable but are otherwise unrestricted.
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
     */
    public String getStateText(final long refreshPeriod) {
        if (attributes.get(ATTR_STATETEXT).isLastValueCurrent(refreshPeriod)) {
            return (String) attributes.get(ATTR_STATETEXT).getLastValue();
        }

        return (String) readSync(attributes.get(ATTR_STATETEXT));
    }

    /**
     * Set the <i>Description</i> attribute [attribute ID <b>28</b>].
     * <p>
     * The Description attribute, of type Character string, MAY be used to hold a description
     * of the usage of the input, output or value, as appropriate to the cluster. The character
     * set used SHALL be ASCII, and the attribute SHALL contain a maximum of 16 characters,
     * which SHALL be printable but are otherwise unrestricted.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param description the {@link String} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDescription(final Object value) {
        return write(attributes.get(ATTR_DESCRIPTION), value);
    }

    /**
     * Get the <i>Description</i> attribute [attribute ID <b>28</b>].
     * <p>
     * The Description attribute, of type Character string, MAY be used to hold a description
     * of the usage of the input, output or value, as appropriate to the cluster. The character
     * set used SHALL be ASCII, and the attribute SHALL contain a maximum of 16 characters,
     * which SHALL be printable but are otherwise unrestricted.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDescriptionAsync() {
        return read(attributes.get(ATTR_DESCRIPTION));
    }

    /**
     * Synchronously get the <i>Description</i> attribute [attribute ID <b>28</b>].
     * <p>
     * The Description attribute, of type Character string, MAY be used to hold a description
     * of the usage of the input, output or value, as appropriate to the cluster. The character
     * set used SHALL be ASCII, and the attribute SHALL contain a maximum of 16 characters,
     * which SHALL be printable but are otherwise unrestricted.
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
     */
    public String getDescription(final long refreshPeriod) {
        if (attributes.get(ATTR_DESCRIPTION).isLastValueCurrent(refreshPeriod)) {
            return (String) attributes.get(ATTR_DESCRIPTION).getLastValue();
        }

        return (String) readSync(attributes.get(ATTR_DESCRIPTION));
    }

    /**
     * Set the <i>NumberOfStates</i> attribute [attribute ID <b>74</b>].
     * <p>
     * This attribute, of type Unsigned 16-bit integer, defines the number of states that a multistate
     * PresentValue MAY have. The NumberOfStates property SHALL always have a value greater than zero.
     * If the value of this property is changed, the size of the StateText array, if present, SHALL also
     * be changed to the same value. The states are numbered consecutively, starting with 1.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param numberOfStates the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setNumberOfStates(final Object value) {
        return write(attributes.get(ATTR_NUMBEROFSTATES), value);
    }

    /**
     * Get the <i>NumberOfStates</i> attribute [attribute ID <b>74</b>].
     * <p>
     * This attribute, of type Unsigned 16-bit integer, defines the number of states that a multistate
     * PresentValue MAY have. The NumberOfStates property SHALL always have a value greater than zero.
     * If the value of this property is changed, the size of the StateText array, if present, SHALL also
     * be changed to the same value. The states are numbered consecutively, starting with 1.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getNumberOfStatesAsync() {
        return read(attributes.get(ATTR_NUMBEROFSTATES));
    }

    /**
     * Synchronously get the <i>NumberOfStates</i> attribute [attribute ID <b>74</b>].
     * <p>
     * This attribute, of type Unsigned 16-bit integer, defines the number of states that a multistate
     * PresentValue MAY have. The NumberOfStates property SHALL always have a value greater than zero.
     * If the value of this property is changed, the size of the StateText array, if present, SHALL also
     * be changed to the same value. The states are numbered consecutively, starting with 1.
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
     */
    public Integer getNumberOfStates(final long refreshPeriod) {
        if (attributes.get(ATTR_NUMBEROFSTATES).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_NUMBEROFSTATES).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_NUMBEROFSTATES));
    }

    /**
     * Set the <i>OutOfService</i> attribute [attribute ID <b>81</b>].
     * <p>
     * The OutOfService attribute, of type Boolean, indicates whether (TRUE) or not (FALSE) the physical
     * input, output or value that the cluster represents is not in service. For an Input cluster, when
     * OutOfService is TRUE the PresentValue attribute is decoupled from the physical input and  will
     * not track changes to the  physical input. For an Output cluster, when OutOfService is TRUE the
     * PresentValue attribute is decoupled from the physical output, so changes to PresentValue will not
     * affect the physical output. For a Value cluster, when OutOfService is TRUE the PresentValue attribute
     * MAY be written to freely by software local to the device that the cluster resides on.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param outOfService the {@link Boolean} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setOutOfService(final Object value) {
        return write(attributes.get(ATTR_OUTOFSERVICE), value);
    }

    /**
     * Get the <i>OutOfService</i> attribute [attribute ID <b>81</b>].
     * <p>
     * The OutOfService attribute, of type Boolean, indicates whether (TRUE) or not (FALSE) the physical
     * input, output or value that the cluster represents is not in service. For an Input cluster, when
     * OutOfService is TRUE the PresentValue attribute is decoupled from the physical input and  will
     * not track changes to the  physical input. For an Output cluster, when OutOfService is TRUE the
     * PresentValue attribute is decoupled from the physical output, so changes to PresentValue will not
     * affect the physical output. For a Value cluster, when OutOfService is TRUE the PresentValue attribute
     * MAY be written to freely by software local to the device that the cluster resides on.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getOutOfServiceAsync() {
        return read(attributes.get(ATTR_OUTOFSERVICE));
    }

    /**
     * Synchronously get the <i>OutOfService</i> attribute [attribute ID <b>81</b>].
     * <p>
     * The OutOfService attribute, of type Boolean, indicates whether (TRUE) or not (FALSE) the physical
     * input, output or value that the cluster represents is not in service. For an Input cluster, when
     * OutOfService is TRUE the PresentValue attribute is decoupled from the physical input and  will
     * not track changes to the  physical input. For an Output cluster, when OutOfService is TRUE the
     * PresentValue attribute is decoupled from the physical output, so changes to PresentValue will not
     * affect the physical output. For a Value cluster, when OutOfService is TRUE the PresentValue attribute
     * MAY be written to freely by software local to the device that the cluster resides on.
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
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Boolean} attribute value, or null on error
     */
    public Boolean getOutOfService(final long refreshPeriod) {
        if (attributes.get(ATTR_OUTOFSERVICE).isLastValueCurrent(refreshPeriod)) {
            return (Boolean) attributes.get(ATTR_OUTOFSERVICE).getLastValue();
        }

        return (Boolean) readSync(attributes.get(ATTR_OUTOFSERVICE));
    }

    /**
     * Set the <i>PresentValue</i> attribute [attribute ID <b>85</b>].
     * <p>
     * The PresentValue attribute indicates the current value of the input, output or
     * value, as appropriate  for the cluster. For Analog clusters it is of type single precision, for Binary
     * clusters it is of type  Boolean, and for multistate clusters it is of type Unsigned 16-bit integer. The
     * PresentValue attribute of an input cluster SHALL be writable when OutOfService is TRUE. When the PriorityArray
     * attribute is implemented, writing to PresentValue SHALL be equivalent to writing to element 16 of PriorityArray,
     * i.e., with a priority of 16.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param presentValue the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPresentValue(final Object value) {
        return write(attributes.get(ATTR_PRESENTVALUE), value);
    }

    /**
     * Get the <i>PresentValue</i> attribute [attribute ID <b>85</b>].
     * <p>
     * The PresentValue attribute indicates the current value of the input, output or
     * value, as appropriate  for the cluster. For Analog clusters it is of type single precision, for Binary
     * clusters it is of type  Boolean, and for multistate clusters it is of type Unsigned 16-bit integer. The
     * PresentValue attribute of an input cluster SHALL be writable when OutOfService is TRUE. When the PriorityArray
     * attribute is implemented, writing to PresentValue SHALL be equivalent to writing to element 16 of PriorityArray,
     * i.e., with a priority of 16.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPresentValueAsync() {
        return read(attributes.get(ATTR_PRESENTVALUE));
    }

    /**
     * Synchronously get the <i>PresentValue</i> attribute [attribute ID <b>85</b>].
     * <p>
     * The PresentValue attribute indicates the current value of the input, output or
     * value, as appropriate  for the cluster. For Analog clusters it is of type single precision, for Binary
     * clusters it is of type  Boolean, and for multistate clusters it is of type Unsigned 16-bit integer. The
     * PresentValue attribute of an input cluster SHALL be writable when OutOfService is TRUE. When the PriorityArray
     * attribute is implemented, writing to PresentValue SHALL be equivalent to writing to element 16 of PriorityArray,
     * i.e., with a priority of 16.
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
     */
    public Integer getPresentValue(final long refreshPeriod) {
        if (attributes.get(ATTR_PRESENTVALUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PRESENTVALUE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PRESENTVALUE));
    }

    /**
     * Set the <i>Reliability</i> attribute [attribute ID <b>103</b>].
     * <p>
     * The Reliability attribute, of type 8-bit enumeration, provides an indication of whether
     * the PresentValueor the operation of the physical input, output or value in question (as
     * appropriate for the cluster) is “reliable” as far as can be determined and, if not, why
     * not. The Reliability attribute MAY have any of the following values:
     * <p>
     * NO-FAULT-DETECTED (0)
     * OVER-RANGE (2)
     * UNDER-RANGE (3)
     * OPEN-LOOP (4)
     * SHORTED-LOOP (5)
     * UNRELIABLE-OTHER (7)
     * PROCESS-ERROR (8)
     * MULTI-STATE-FAULT (9)
     * CONFIGURATION-ERROR (10)
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param reliability the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setReliability(final Object value) {
        return write(attributes.get(ATTR_RELIABILITY), value);
    }

    /**
     * Get the <i>Reliability</i> attribute [attribute ID <b>103</b>].
     * <p>
     * The Reliability attribute, of type 8-bit enumeration, provides an indication of whether
     * the PresentValueor the operation of the physical input, output or value in question (as
     * appropriate for the cluster) is “reliable” as far as can be determined and, if not, why
     * not. The Reliability attribute MAY have any of the following values:
     * <p>
     * NO-FAULT-DETECTED (0)
     * OVER-RANGE (2)
     * UNDER-RANGE (3)
     * OPEN-LOOP (4)
     * SHORTED-LOOP (5)
     * UNRELIABLE-OTHER (7)
     * PROCESS-ERROR (8)
     * MULTI-STATE-FAULT (9)
     * CONFIGURATION-ERROR (10)
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReliabilityAsync() {
        return read(attributes.get(ATTR_RELIABILITY));
    }

    /**
     * Synchronously get the <i>Reliability</i> attribute [attribute ID <b>103</b>].
     * <p>
     * The Reliability attribute, of type 8-bit enumeration, provides an indication of whether
     * the PresentValueor the operation of the physical input, output or value in question (as
     * appropriate for the cluster) is “reliable” as far as can be determined and, if not, why
     * not. The Reliability attribute MAY have any of the following values:
     * <p>
     * NO-FAULT-DETECTED (0)
     * OVER-RANGE (2)
     * UNDER-RANGE (3)
     * OPEN-LOOP (4)
     * SHORTED-LOOP (5)
     * UNRELIABLE-OTHER (7)
     * PROCESS-ERROR (8)
     * MULTI-STATE-FAULT (9)
     * CONFIGURATION-ERROR (10)
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
     */
    public Integer getReliability(final long refreshPeriod) {
        if (attributes.get(ATTR_RELIABILITY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RELIABILITY).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RELIABILITY));
    }

    /**
     * Set the <i>RelinguishDefault</i> attribute [attribute ID <b>104</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param relinguishDefault the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setRelinguishDefault(final Object value) {
        return write(attributes.get(ATTR_RELINGUISHDEFAULT), value);
    }

    /**
     * Get the <i>RelinguishDefault</i> attribute [attribute ID <b>104</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRelinguishDefaultAsync() {
        return read(attributes.get(ATTR_RELINGUISHDEFAULT));
    }

    /**
     * Synchronously get the <i>RelinguishDefault</i> attribute [attribute ID <b>104</b>].
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
     */
    public Integer getRelinguishDefault(final long refreshPeriod) {
        if (attributes.get(ATTR_RELINGUISHDEFAULT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RELINGUISHDEFAULT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RELINGUISHDEFAULT));
    }

    /**
     * Get the <i>StatusFlags</i> attribute [attribute ID <b>111</b>].
     * <p>
     * This attribute, of type bitmap, represents four Boolean flags that indicate the general “health”
     * of the analog sensor. Three of the flags are associated with the values of other optional attributes
     * of this cluster. A more detailed status could be determined by reading the optional attributes (if
     * supported) that are linked to these flags. The relationship between individual flags is not defined.
     * <p>
     * The four flags are Bit 0 = IN_ALARM, Bit 1 = FAULT, Bit 2 = OVERRIDDEN, Bit 3 = OUT OF SERVICE
     * <p>
     * where:
     * <p>
     * IN_ALARM -Logical FALSE (0) if the EventStateattribute has a value of NORMAL, otherwise logical TRUE (1).
     * This bit is always 0 unless the cluster implementing the EventState attribute is implemented on the same
     * endpoint.
     * <p>
     * FAULT -Logical TRUE (1) if the Reliability attribute is present and does not have a value of NO FAULT DETECTED,
     * otherwise logical FALSE (0).
     * <p>
     * OVERRIDDEN -Logical TRUE (1) if the cluster has been overridden by some  mechanism local to the device.
     * Otherwise, the value is logical FALSE (0). In this context, for an input cluster, “overridden” is taken
     * to mean that the PresentValue and Reliability(optional) attributes are no longer tracking changes to the
     * physical input. For an Output cluster, “overridden” is taken to mean that the physical output is no longer
     * tracking changes to the PresentValue attribute and the Reliability attribute is no longer a reflection of
     * the physical output. For a Value cluster, “overridden” is taken to mean that the PresentValue attribute is
     * not writeable.
     * <p>
     * OUT OF SERVICE -Logical TRUE (1) if the OutOfService attribute has a value of TRUE, otherwise
     * logical FALSE (0).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getStatusFlagsAsync() {
        return read(attributes.get(ATTR_STATUSFLAGS));
    }

    /**
     * Synchronously get the <i>StatusFlags</i> attribute [attribute ID <b>111</b>].
     * <p>
     * This attribute, of type bitmap, represents four Boolean flags that indicate the general “health”
     * of the analog sensor. Three of the flags are associated with the values of other optional attributes
     * of this cluster. A more detailed status could be determined by reading the optional attributes (if
     * supported) that are linked to these flags. The relationship between individual flags is not defined.
     * <p>
     * The four flags are Bit 0 = IN_ALARM, Bit 1 = FAULT, Bit 2 = OVERRIDDEN, Bit 3 = OUT OF SERVICE
     * <p>
     * where:
     * <p>
     * IN_ALARM -Logical FALSE (0) if the EventStateattribute has a value of NORMAL, otherwise logical TRUE (1).
     * This bit is always 0 unless the cluster implementing the EventState attribute is implemented on the same
     * endpoint.
     * <p>
     * FAULT -Logical TRUE (1) if the Reliability attribute is present and does not have a value of NO FAULT DETECTED,
     * otherwise logical FALSE (0).
     * <p>
     * OVERRIDDEN -Logical TRUE (1) if the cluster has been overridden by some  mechanism local to the device.
     * Otherwise, the value is logical FALSE (0). In this context, for an input cluster, “overridden” is taken
     * to mean that the PresentValue and Reliability(optional) attributes are no longer tracking changes to the
     * physical input. For an Output cluster, “overridden” is taken to mean that the physical output is no longer
     * tracking changes to the PresentValue attribute and the Reliability attribute is no longer a reflection of
     * the physical output. For a Value cluster, “overridden” is taken to mean that the PresentValue attribute is
     * not writeable.
     * <p>
     * OUT OF SERVICE -Logical TRUE (1) if the OutOfService attribute has a value of TRUE, otherwise
     * logical FALSE (0).
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
     */
    public Integer getStatusFlags(final long refreshPeriod) {
        if (attributes.get(ATTR_STATUSFLAGS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_STATUSFLAGS).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_STATUSFLAGS));
    }

    /**
     * Get the <i>ApplicationType</i> attribute [attribute ID <b>256</b>].
     * <p>
     * The ApplicationType attribute is an unsigned 32 bit integer that indicates the specific
     * application usage for this cluster. (Note: This attribute has no BACnet equivalent).
     * ApplicationType is subdivided into Group, Type and an Index number, as follows.
     * <p>
     * Group = Bits 24 -31 An indication of the cluster this attribute is part of.
     * <p>
     * Type = Bits 16 -23 For Analog clusters, the physical quantity that the Present Value attribute
     * of the cluster represents. For Binary and Multistate clusters, the application usage domain.
     * <p>
     * Index = Bits 0 -15 The specific application usage of the cluster.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getApplicationTypeAsync() {
        return read(attributes.get(ATTR_APPLICATIONTYPE));
    }

    /**
     * Synchronously get the <i>ApplicationType</i> attribute [attribute ID <b>256</b>].
     * <p>
     * The ApplicationType attribute is an unsigned 32 bit integer that indicates the specific
     * application usage for this cluster. (Note: This attribute has no BACnet equivalent).
     * ApplicationType is subdivided into Group, Type and an Index number, as follows.
     * <p>
     * Group = Bits 24 -31 An indication of the cluster this attribute is part of.
     * <p>
     * Type = Bits 16 -23 For Analog clusters, the physical quantity that the Present Value attribute
     * of the cluster represents. For Binary and Multistate clusters, the application usage domain.
     * <p>
     * Index = Bits 0 -15 The specific application usage of the cluster.
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
     */
    public Integer getApplicationType(final long refreshPeriod) {
        if (attributes.get(ATTR_APPLICATIONTYPE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_APPLICATIONTYPE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_APPLICATIONTYPE));
    }
}
