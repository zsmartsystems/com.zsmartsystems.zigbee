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
 * <b>On / Off Switch Configuration</b> cluster implementation (<i>Cluster ID 0x0007</i>).
 * <p>
 * Attributes and commands for configuring On/Off switching devices
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-10T11:55:03Z")
public class ZclOnOffSwitchConfigurationCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0007;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "On / Off Switch Configuration";

    // Attribute constants
    /**
     * The SwitchType attribute specifies the basic functionality of the On/Off switching
     * device.
     */
    public static final int ATTR_SWITCHTYPE = 0x0000;
    /**
     * The SwitchActions attribute is 8 bits in length and specifies the commands of the On/Off
     * cluster to be generated when the switch moves between its two states
     */
    public static final int ATTR_SWITCHACTIONS = 0x0010;

    @Override
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentSkipListMap<>();

        return attributeMap;
    }

    @Override
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentSkipListMap<>();

        attributeMap.put(ATTR_SWITCHTYPE, new ZclAttribute(this, ATTR_SWITCHTYPE, "Switch Type", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_SWITCHACTIONS, new ZclAttribute(this, ATTR_SWITCHACTIONS, "Switch Actions", ZclDataType.ENUMERATION_8_BIT, true, true, true, false));

        return attributeMap;
    }


    /**
     * Default constructor to create a On / Off Switch Configuration cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclOnOffSwitchConfigurationCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>Switch Type</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The SwitchType attribute specifies the basic functionality of the On/Off switching
     * device.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getSwitchTypeAsync() {
        return read(serverAttributes.get(ATTR_SWITCHTYPE));
    }

    /**
     * Synchronously get the <i>Switch Type</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The SwitchType attribute specifies the basic functionality of the On/Off switching
     * device.
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
    public Integer getSwitchType(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_SWITCHTYPE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_SWITCHTYPE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_SWITCHTYPE));
    }

    /**
     * Set reporting for the <i>Switch Type</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The SwitchType attribute specifies the basic functionality of the On/Off switching
     * device.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setSwitchTypeReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_SWITCHTYPE), minInterval, maxInterval);
    }

    /**
     * Set the <i>Switch Actions</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The SwitchActions attribute is 8 bits in length and specifies the commands of the On/Off
     * cluster to be generated when the switch moves between its two states
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param switchActions the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setSwitchActions(final Integer value) {
        return write(serverAttributes.get(ATTR_SWITCHACTIONS), value);
    }

    /**
     * Get the <i>Switch Actions</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The SwitchActions attribute is 8 bits in length and specifies the commands of the On/Off
     * cluster to be generated when the switch moves between its two states
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getSwitchActionsAsync() {
        return read(serverAttributes.get(ATTR_SWITCHACTIONS));
    }

    /**
     * Synchronously get the <i>Switch Actions</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The SwitchActions attribute is 8 bits in length and specifies the commands of the On/Off
     * cluster to be generated when the switch moves between its two states
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
    public Integer getSwitchActions(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_SWITCHACTIONS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_SWITCHACTIONS).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_SWITCHACTIONS));
    }

    /**
     * Set reporting for the <i>Switch Actions</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The SwitchActions attribute is 8 bits in length and specifies the commands of the On/Off
     * cluster to be generated when the switch moves between its two states
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setSwitchActionsReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_SWITCHACTIONS), minInterval, maxInterval);
    }
}
