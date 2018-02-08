/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
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
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

/**
 * <b>On/off Switch Configuration</b> cluster implementation (<i>Cluster ID 0x0007</i>).
 * <p>
 * Attributes and commands for configuring On/Off switching devices
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclOnOffSwitchConfigurationCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0007;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "On/off Switch Configuration";

    // Attribute constants
    /**
     * The SwitchTypeattribute  specifies  the  basic  functionality  of  the  On/Off  switching  device.
     */
    public static final int ATTR_SWITCHTYPE = 0x0000;
    /**
     * The SwitchActions attribute is 8 bits in length and specifies the commands of the On/Off cluster
     * to be generated when the switch moves between its two states
     */
    public static final int ATTR_SWITCHACTIONS = 0x0010;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(2);

        attributeMap.put(ATTR_SWITCHTYPE, new ZclAttribute(ZclClusterType.ON_OFF_SWITCH_CONFIGURATION, ATTR_SWITCHTYPE, "SwitchType", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_SWITCHACTIONS, new ZclAttribute(ZclClusterType.ON_OFF_SWITCH_CONFIGURATION, ATTR_SWITCHACTIONS, "SwitchActions", ZclDataType.ENUMERATION_8_BIT, true, true, true, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a On/off Switch Configuration cluster.
     *
     * @param zigbeeManager {@link ZigBeeNetworkManager}
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint}
     */
    public ZclOnOffSwitchConfigurationCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeManager, zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * Get the <i>SwitchType</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The SwitchTypeattribute  specifies  the  basic  functionality  of  the  On/Off  switching  device.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getSwitchTypeAsync() {
        return read(attributes.get(ATTR_SWITCHTYPE));
    }


    /**
     * Synchronously get the <i>SwitchType</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The SwitchTypeattribute  specifies  the  basic  functionality  of  the  On/Off  switching  device.
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
    public Integer getSwitchType(final long refreshPeriod) {
        if (attributes.get(ATTR_SWITCHTYPE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_SWITCHTYPE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_SWITCHTYPE));
    }


    /**
     * Set the <i>SwitchActions</i> attribute [attribute ID <b>16</b>].
     * <p>
     * The SwitchActions attribute is 8 bits in length and specifies the commands of the On/Off cluster
     * to be generated when the switch moves between its two states
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param switchActions the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setSwitchActions(final Object value) {
        return write(attributes.get(ATTR_SWITCHACTIONS), value);
    }

    /**
     * Get the <i>SwitchActions</i> attribute [attribute ID <b>16</b>].
     * <p>
     * The SwitchActions attribute is 8 bits in length and specifies the commands of the On/Off cluster
     * to be generated when the switch moves between its two states
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getSwitchActionsAsync() {
        return read(attributes.get(ATTR_SWITCHACTIONS));
    }


    /**
     * Synchronously get the <i>SwitchActions</i> attribute [attribute ID <b>16</b>].
     * <p>
     * The SwitchActions attribute is 8 bits in length and specifies the commands of the On/Off cluster
     * to be generated when the switch moves between its two states
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
    public Integer getSwitchActions(final long refreshPeriod) {
        if (attributes.get(ATTR_SWITCHACTIONS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_SWITCHACTIONS).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_SWITCHACTIONS));
    }
}
