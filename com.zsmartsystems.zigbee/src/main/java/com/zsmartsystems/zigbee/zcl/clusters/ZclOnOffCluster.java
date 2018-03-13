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
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OffWithEffectCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnWithRecallGlobalSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnWithTimedOffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.ToggleCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import javax.annotation.Generated;

/**
 * <b>On/Off</b> cluster implementation (<i>Cluster ID 0x0006</i>).
 * <p>
 * Attributes and commands for switching devices between ‘On’ and ‘Off’ states.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class ZclOnOffCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0006;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "On/Off";

    // Attribute constants
    /**
     * The OnOff attribute has the following values: 0 = Off, 1 = On
     */
    public static final int ATTR_ONOFF = 0x0000;
    /**
     */
    public static final int ATTR_GLOBALSCENECONTROL = 0x4000;
    /**
     */
    public static final int ATTR_OFFTIME = 0x4001;
    /**
     */
    public static final int ATTR_OFFWAITTIME = 0x4002;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(4);

        attributeMap.put(ATTR_ONOFF, new ZclAttribute(ZclClusterType.ON_OFF, ATTR_ONOFF, "OnOff", ZclDataType.BOOLEAN, true, true, false, true));
        attributeMap.put(ATTR_GLOBALSCENECONTROL, new ZclAttribute(ZclClusterType.ON_OFF, ATTR_GLOBALSCENECONTROL, "GlobalSceneControl", ZclDataType.BOOLEAN, false, true, true, true));
        attributeMap.put(ATTR_OFFTIME, new ZclAttribute(ZclClusterType.ON_OFF, ATTR_OFFTIME, "OffTime", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_OFFWAITTIME, new ZclAttribute(ZclClusterType.ON_OFF, ATTR_OFFWAITTIME, "OffWaitTime", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, true));

        return attributeMap;
    }

    /**
     * Default constructor to create a On/Off cluster.
     *
     * @param zigbeeManager {@link ZigBeeNetworkManager}
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint}
     */
    public ZclOnOffCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeManager, zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>OnOff</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The OnOff attribute has the following values: 0 = Off, 1 = On
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getOnOffAsync() {
        return read(attributes.get(ATTR_ONOFF));
    }

    /**
     * Synchronously get the <i>OnOff</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The OnOff attribute has the following values: 0 = Off, 1 = On
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
    public Boolean getOnOff(final long refreshPeriod) {
        if (attributes.get(ATTR_ONOFF).isLastValueCurrent(refreshPeriod)) {
            return (Boolean) attributes.get(ATTR_ONOFF).getLastValue();
        }

        return (Boolean) readSync(attributes.get(ATTR_ONOFF));
    }

    /**
     * Set reporting for the <i>OnOff</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The OnOff attribute has the following values: 0 = Off, 1 = On
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval {@link int} minimum reporting period
     * @param maxInterval {@link int} maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setOnOffReporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_ONOFF), minInterval, maxInterval);
    }

    /**
     * Set the <i>GlobalSceneControl</i> attribute [attribute ID <b>16384</b>].
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is 
     *
     * @param globalSceneControl the {@link Boolean} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setGlobalSceneControl(final Object value) {
        return write(attributes.get(ATTR_GLOBALSCENECONTROL), value);
    }

    /**
     * Get the <i>GlobalSceneControl</i> attribute [attribute ID <b>16384</b>].
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is 
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getGlobalSceneControlAsync() {
        return read(attributes.get(ATTR_GLOBALSCENECONTROL));
    }

    /**
     * Synchronously get the <i>GlobalSceneControl</i> attribute [attribute ID <b>16384</b>].
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
     * The implementation of this attribute by a device is 
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Boolean} attribute value, or null on error
     */
    public Boolean getGlobalSceneControl(final long refreshPeriod) {
        if (attributes.get(ATTR_GLOBALSCENECONTROL).isLastValueCurrent(refreshPeriod)) {
            return (Boolean) attributes.get(ATTR_GLOBALSCENECONTROL).getLastValue();
        }

        return (Boolean) readSync(attributes.get(ATTR_GLOBALSCENECONTROL));
    }

    /**
     * Set reporting for the <i>GlobalSceneControl</i> attribute [attribute ID <b>16384</b>].
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is 
     *
     * @param minInterval {@link int} minimum reporting period
     * @param maxInterval {@link int} maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setGlobalSceneControlReporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_GLOBALSCENECONTROL), minInterval, maxInterval);
    }

    /**
     * Set the <i>OffTime</i> attribute [attribute ID <b>16385</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is 
     *
     * @param offTime the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setOffTime(final Object value) {
        return write(attributes.get(ATTR_OFFTIME), value);
    }

    /**
     * Get the <i>OffTime</i> attribute [attribute ID <b>16385</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is 
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getOffTimeAsync() {
        return read(attributes.get(ATTR_OFFTIME));
    }

    /**
     * Synchronously get the <i>OffTime</i> attribute [attribute ID <b>16385</b>].
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
     * The implementation of this attribute by a device is 
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getOffTime(final long refreshPeriod) {
        if (attributes.get(ATTR_OFFTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_OFFTIME).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_OFFTIME));
    }

    /**
     * Set reporting for the <i>OffTime</i> attribute [attribute ID <b>16385</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is 
     *
     * @param minInterval {@link int} minimum reporting period
     * @param maxInterval {@link int} maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setOffTimeReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_OFFTIME), minInterval, maxInterval, reportableChange);
    }

    /**
     * Set the <i>OffWaitTime</i> attribute [attribute ID <b>16386</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is 
     *
     * @param offWaitTime the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setOffWaitTime(final Object value) {
        return write(attributes.get(ATTR_OFFWAITTIME), value);
    }

    /**
     * Get the <i>OffWaitTime</i> attribute [attribute ID <b>16386</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is 
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getOffWaitTimeAsync() {
        return read(attributes.get(ATTR_OFFWAITTIME));
    }

    /**
     * Synchronously get the <i>OffWaitTime</i> attribute [attribute ID <b>16386</b>].
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
     * The implementation of this attribute by a device is 
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getOffWaitTime(final long refreshPeriod) {
        if (attributes.get(ATTR_OFFWAITTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_OFFWAITTIME).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_OFFWAITTIME));
    }

    /**
     * Set reporting for the <i>OffWaitTime</i> attribute [attribute ID <b>16386</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is 
     *
     * @param minInterval {@link int} minimum reporting period
     * @param maxInterval {@link int} maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setOffWaitTimeReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_OFFWAITTIME), minInterval, maxInterval, reportableChange);
    }

    /**
     * The Off Command
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> offCommand() {
        OffCommand command = new OffCommand();

        return send(command);
    }

    /**
     * The On Command
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> onCommand() {
        OnCommand command = new OnCommand();

        return send(command);
    }

    /**
     * The Toggle Command
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> toggleCommand() {
        ToggleCommand command = new ToggleCommand();

        return send(command);
    }

    /**
     * The Off With Effect Command
     * <p>
     * The Off With Effect command allows devices to be turned off using enhanced ways of fading.
     *
     * @param effectIdentifier {@link Integer} Effect Identifier
     * @param effectVariant {@link Integer} Effect Variant
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> offWithEffectCommand(Integer effectIdentifier, Integer effectVariant) {
        OffWithEffectCommand command = new OffWithEffectCommand();

        // Set the fields
        command.setEffectIdentifier(effectIdentifier);
        command.setEffectVariant(effectVariant);

        return send(command);
    }

    /**
     * The On With Recall Global Scene Command
     * <p>
     * The On With Recall Global Scene command allows the recall of the settings when the device was turned off.
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> onWithRecallGlobalSceneCommand() {
        OnWithRecallGlobalSceneCommand command = new OnWithRecallGlobalSceneCommand();

        return send(command);
    }

    /**
     * The On With Timed Off Command
     * <p>
     * The On With Timed Off command allows devices to be turned on for a specific duration
     * with a guarded off duration so that SHOULD the device be subsequently switched off,
     * further On With Timed Off commands, received during this time, are prevented from
     * turning the devices back on. Note that the device can be periodically re-kicked by
     * subsequent On With Timed Off commands, e.g., from an on/off sensor.
     *
     * @param onOffControl {@link Integer} On Off Control
     * @param onTime {@link Integer} On Time
     * @param offWaitTime {@link Integer} Off Wait Time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> onWithTimedOffCommand(Integer onOffControl, Integer onTime, Integer offWaitTime) {
        OnWithTimedOffCommand command = new OnWithTimedOffCommand();

        // Set the fields
        command.setOnOffControl(onOffControl);
        command.setOnTime(onTime);
        command.setOffWaitTime(offWaitTime);

        return send(command);
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0: // OFF_COMMAND
                return new OffCommand();
            case 1: // ON_COMMAND
                return new OnCommand();
            case 2: // TOGGLE_COMMAND
                return new ToggleCommand();
            case 64: // OFF_WITH_EFFECT_COMMAND
                return new OffWithEffectCommand();
            case 65: // ON_WITH_RECALL_GLOBAL_SCENE_COMMAND
                return new OnWithRecallGlobalSceneCommand();
            case 66: // ON_WITH_TIMED_OFF_COMMAND
                return new OnWithTimedOffCommand();
            default:
                return null;
        }
    }
}
