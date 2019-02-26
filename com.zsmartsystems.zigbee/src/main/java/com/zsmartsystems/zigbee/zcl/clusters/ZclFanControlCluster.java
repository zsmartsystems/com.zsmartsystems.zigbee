/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Fan Control</b> cluster implementation (<i>Cluster ID 0x0202</i>).
 * <p>
 * This cluster specifies an interface to control the speed of a fan as part of a heating / cooling
 * system.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-26T21:33:25Z")
public class ZclFanControlCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0202;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Fan Control";

    // Attribute constants
    /**
     * The FanMode attribute is an 8-bit value that specifies the current speed of the fan.
     */
    public static final int ATTR_FANMODE = 0x0000;
    /**
     * The FanModeSequence attribute is an 8-bit value that specifies the possible fan speeds
     * that the thermostat can set.
     */
    public static final int ATTR_FANMODESEQUENCE = 0x0001;

    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<>(2);

        attributeMap.put(ATTR_FANMODE, new ZclAttribute(ZclClusterType.FAN_CONTROL, ATTR_FANMODE, "Fan Mode", ZclDataType.ENUMERATION_8_BIT, false, true, true, true));
        attributeMap.put(ATTR_FANMODESEQUENCE, new ZclAttribute(ZclClusterType.FAN_CONTROL, ATTR_FANMODESEQUENCE, "Fan Mode Sequence", ZclDataType.ENUMERATION_8_BIT, false, true, true, true));

        return attributeMap;
    }

    /**
     * Default constructor to create a Fan Control cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclFanControlCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Set the <i>Fan Mode</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The FanMode attribute is an 8-bit value that specifies the current speed of the fan.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param fanMode the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setFanMode(final Integer value) {
        return write(attributes.get(ATTR_FANMODE), value);
    }

    /**
     * Get the <i>Fan Mode</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The FanMode attribute is an 8-bit value that specifies the current speed of the fan.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getFanModeAsync() {
        return read(attributes.get(ATTR_FANMODE));
    }

    /**
     * Synchronously get the <i>Fan Mode</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The FanMode attribute is an 8-bit value that specifies the current speed of the fan.
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
    public Integer getFanMode(final long refreshPeriod) {
        if (attributes.get(ATTR_FANMODE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_FANMODE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_FANMODE));
    }

    /**
     * Set the <i>Fan Mode Sequence</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The FanModeSequence attribute is an 8-bit value that specifies the possible fan speeds
     * that the thermostat can set.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param fanModeSequence the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setFanModeSequence(final Integer value) {
        return write(attributes.get(ATTR_FANMODESEQUENCE), value);
    }

    /**
     * Get the <i>Fan Mode Sequence</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The FanModeSequence attribute is an 8-bit value that specifies the possible fan speeds
     * that the thermostat can set.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getFanModeSequenceAsync() {
        return read(attributes.get(ATTR_FANMODESEQUENCE));
    }

    /**
     * Synchronously get the <i>Fan Mode Sequence</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The FanModeSequence attribute is an 8-bit value that specifies the possible fan speeds
     * that the thermostat can set.
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
    public Integer getFanModeSequence(final long refreshPeriod) {
        if (attributes.get(ATTR_FANMODESEQUENCE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_FANMODESEQUENCE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_FANMODESEQUENCE));
    }
}
