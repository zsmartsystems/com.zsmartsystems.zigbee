/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.identify.IdentifyCommand;
import com.zsmartsystems.zigbee.zcl.clusters.identify.IdentifyQueryCommand;
import com.zsmartsystems.zigbee.zcl.clusters.identify.IdentifyQueryResponse;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Identify</b> cluster implementation (<i>Cluster ID 0x0003</i>).
 * <p>
 * Attributes and commands to put a device into an Identification mode (e.g. flashing a light),
 * that indicates to an observer – e.g. an installer - which of several devices it is, also to
 * request any device that is identifying itself to respond to the initiator.
 * <p>
 * Note that this cluster cannot be disabled, and remains functional regardless of the setting
 * of the DeviceEnable attribute in the Basic cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-06T18:44:02Z")
public class ZclIdentifyCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0003;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Identify";

    // Attribute constants
    /**
     * The IdentifyTime attribute specifies the remaining length of time, in seconds, that
     * the device will continue to identify itself.
     * <p>
     * If this attribute is set to a value other than 0x0000 then the device shall enter its
     * identification procedure, in order to indicate to an observer which of several devices
     * it is. It is recommended that this procedure consists of flashing a light with a period of
     * 0.5 seconds. The IdentifyTime attribute shall be decremented every second.
     * <p>
     * If this attribute reaches or is set to the value 0x0000 then the device shall terminate
     * its identification procedure.
     */
    public static final int ATTR_IDENTIFYTIME = 0x0000;

    @Override
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentSkipListMap<>();

        return attributeMap;
    }

    @Override
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentSkipListMap<>();

        attributeMap.put(ATTR_IDENTIFYTIME, new ZclAttribute(this, ATTR_IDENTIFYTIME, "Identify Time", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, true, false));

        return attributeMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeServerCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();

        commandMap.put(0x0000, IdentifyQueryResponse.class);

        return commandMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();

        commandMap.put(0x0000, IdentifyCommand.class);
        commandMap.put(0x0001, IdentifyQueryCommand.class);

        return commandMap;
    }

    /**
     * Default constructor to create a Identify cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclIdentifyCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Set the <i>Identify Time</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The IdentifyTime attribute specifies the remaining length of time, in seconds, that
     * the device will continue to identify itself.
     * <p>
     * If this attribute is set to a value other than 0x0000 then the device shall enter its
     * identification procedure, in order to indicate to an observer which of several devices
     * it is. It is recommended that this procedure consists of flashing a light with a period of
     * 0.5 seconds. The IdentifyTime attribute shall be decremented every second.
     * <p>
     * If this attribute reaches or is set to the value 0x0000 then the device shall terminate
     * its identification procedure.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param identifyTime the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setIdentifyTime(final Integer value) {
        return write(serverAttributes.get(ATTR_IDENTIFYTIME), value);
    }

    /**
     * Get the <i>Identify Time</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The IdentifyTime attribute specifies the remaining length of time, in seconds, that
     * the device will continue to identify itself.
     * <p>
     * If this attribute is set to a value other than 0x0000 then the device shall enter its
     * identification procedure, in order to indicate to an observer which of several devices
     * it is. It is recommended that this procedure consists of flashing a light with a period of
     * 0.5 seconds. The IdentifyTime attribute shall be decremented every second.
     * <p>
     * If this attribute reaches or is set to the value 0x0000 then the device shall terminate
     * its identification procedure.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getIdentifyTimeAsync() {
        return read(serverAttributes.get(ATTR_IDENTIFYTIME));
    }

    /**
     * Synchronously get the <i>Identify Time</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The IdentifyTime attribute specifies the remaining length of time, in seconds, that
     * the device will continue to identify itself.
     * <p>
     * If this attribute is set to a value other than 0x0000 then the device shall enter its
     * identification procedure, in order to indicate to an observer which of several devices
     * it is. It is recommended that this procedure consists of flashing a light with a period of
     * 0.5 seconds. The IdentifyTime attribute shall be decremented every second.
     * <p>
     * If this attribute reaches or is set to the value 0x0000 then the device shall terminate
     * its identification procedure.
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
    public Integer getIdentifyTime(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_IDENTIFYTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_IDENTIFYTIME).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_IDENTIFYTIME));
    }

    /**
     * Set reporting for the <i>Identify Time</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The IdentifyTime attribute specifies the remaining length of time, in seconds, that
     * the device will continue to identify itself.
     * <p>
     * If this attribute is set to a value other than 0x0000 then the device shall enter its
     * identification procedure, in order to indicate to an observer which of several devices
     * it is. It is recommended that this procedure consists of flashing a light with a period of
     * 0.5 seconds. The IdentifyTime attribute shall be decremented every second.
     * <p>
     * If this attribute reaches or is set to the value 0x0000 then the device shall terminate
     * its identification procedure.
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
    public Future<CommandResult> setIdentifyTimeReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_IDENTIFYTIME), minInterval, maxInterval, reportableChange);
    }

    /**
     * The Identify Command
     * <p>
     * The identify command starts or stops the receiving device identifying itself.
     *
     * @param identifyTime {@link Integer} Identify Time
     * @return the {@link Future<CommandResult>} command result future
     */
    @Deprecated
    public Future<CommandResult> identifyCommand(Integer identifyTime) {
        IdentifyCommand command = new IdentifyCommand();

        // Set the fields
        command.setIdentifyTime(identifyTime);

        return sendCommand(command);
    }

    /**
     * The Identify Query Command
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    @Deprecated
    public Future<CommandResult> identifyQueryCommand() {
        return sendCommand(new IdentifyQueryCommand());
    }

    /**
     * The Identify Query Response
     * <p>
     * The identify query response command is generated in response to receiving an Identify
     * Query command in the case that the device is currently identifying itself.
     *
     * @param identifyTime {@link Integer} Identify Time
     * @return the {@link Future<CommandResult>} command result future
     */
    @Deprecated
    public Future<CommandResult> identifyQueryResponse(Integer identifyTime) {
        IdentifyQueryResponse command = new IdentifyQueryResponse();

        // Set the fields
        command.setIdentifyTime(identifyTime);

        return sendCommand(command);
    }
}
