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
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OffWithEffectCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnWithRecallGlobalSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnWithTimedOffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.ToggleCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>On/Off</b> cluster implementation (<i>Cluster ID 0x0006</i>).
 * <p>
 * Attributes and commands for switching devices between ‘On’ and ‘Off’ states.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
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
     * In order to support the use case where the user gets back the last setting of the devices
     * (e.g. level settings for lamps), a global scene is introduced which is stored when the
     * devices are turned off and recalled when the devices are turned on. The global scene is
     * defined as the scene that is stored with group identifier 0 and scene identifier 0.
     * <p>
     * The GlobalSceneControl attribute is defined in order to prevent a second off command
     * storing the all-devices-off situation as a global scene, and to prevent a second on
     * command destroying the current settings by going back to the global scene.
     * <p>
     * The GlobalSceneControl attribute shall be set to TRUE after the reception of a command
     * which causes the OnOff attribute to be set to TRUE, such as a standard On command, a Move to
     * level (with on/off) command, a Recall scene command or a On with recall global scene
     * command.
     * <p>
     * The GlobalSceneControl attribute is set to FALSE after reception of a Off with effect
     * command.
     */
    public static final int ATTR_GLOBALSCENECONTROL = 0x4000;
    /**
     * The OnTime attribute specifies the length of time (in 1/10ths second) that the “on”
     * state shall be maintained before automatically transitioning to the “off” state when
     * using the On with timed off command. If this attribute is set to 0x0000 or 0xffff, the
     * device shall remain in its current state.
     */
    public static final int ATTR_ONTIME = 0x4001;
    /**
     * The OffWaitTime attribute specifies the length of time (in 1/10ths second) that the
     * “off” state shall be guarded to prevent an on command turning the device back to its “on”
     * state (e.g., when leaving a room, the lights are turned off but an occupancy sensor
     * detects the leaving person and attempts to turn the lights back on). If this attribute is
     * set to 0x0000, the device shall remain in its current state.
     */
    public static final int ATTR_OFFWAITTIME = 0x4002;

    // Attribute initialisation
    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(4);

        attributeMap.put(ATTR_ONOFF, new ZclAttribute(ZclClusterType.ON_OFF, ATTR_ONOFF, "On Off", ZclDataType.BOOLEAN, true, true, false, true));
        attributeMap.put(ATTR_GLOBALSCENECONTROL, new ZclAttribute(ZclClusterType.ON_OFF, ATTR_GLOBALSCENECONTROL, "Global Scene Control", ZclDataType.BOOLEAN, true, true, false, false));
        attributeMap.put(ATTR_ONTIME, new ZclAttribute(ZclClusterType.ON_OFF, ATTR_ONTIME, "On Time", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, true, false));
        attributeMap.put(ATTR_OFFWAITTIME, new ZclAttribute(ZclClusterType.ON_OFF, ATTR_OFFWAITTIME, "Off Wait Time", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, true, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a On/Off cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclOnOffCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>On Off</i> attribute [attribute ID <b>0x0000</b>].
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
     * Synchronously get the <i>On Off</i> attribute [attribute ID <b>0x0000</b>].
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
     * Set reporting for the <i>On Off</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The OnOff attribute has the following values: 0 = Off, 1 = On
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setOnOffReporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_ONOFF), minInterval, maxInterval);
    }

    /**
     * Get the <i>Global Scene Control</i> attribute [attribute ID <b>0x4000</b>].
     * <p>
     * In order to support the use case where the user gets back the last setting of the devices
     * (e.g. level settings for lamps), a global scene is introduced which is stored when the
     * devices are turned off and recalled when the devices are turned on. The global scene is
     * defined as the scene that is stored with group identifier 0 and scene identifier 0.
     * <p>
     * The GlobalSceneControl attribute is defined in order to prevent a second off command
     * storing the all-devices-off situation as a global scene, and to prevent a second on
     * command destroying the current settings by going back to the global scene.
     * <p>
     * The GlobalSceneControl attribute shall be set to TRUE after the reception of a command
     * which causes the OnOff attribute to be set to TRUE, such as a standard On command, a Move to
     * level (with on/off) command, a Recall scene command or a On with recall global scene
     * command.
     * <p>
     * The GlobalSceneControl attribute is set to FALSE after reception of a Off with effect
     * command.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getGlobalSceneControlAsync() {
        return read(attributes.get(ATTR_GLOBALSCENECONTROL));
    }

    /**
     * Synchronously get the <i>Global Scene Control</i> attribute [attribute ID <b>0x4000</b>].
     * <p>
     * In order to support the use case where the user gets back the last setting of the devices
     * (e.g. level settings for lamps), a global scene is introduced which is stored when the
     * devices are turned off and recalled when the devices are turned on. The global scene is
     * defined as the scene that is stored with group identifier 0 and scene identifier 0.
     * <p>
     * The GlobalSceneControl attribute is defined in order to prevent a second off command
     * storing the all-devices-off situation as a global scene, and to prevent a second on
     * command destroying the current settings by going back to the global scene.
     * <p>
     * The GlobalSceneControl attribute shall be set to TRUE after the reception of a command
     * which causes the OnOff attribute to be set to TRUE, such as a standard On command, a Move to
     * level (with on/off) command, a Recall scene command or a On with recall global scene
     * command.
     * <p>
     * The GlobalSceneControl attribute is set to FALSE after reception of a Off with effect
     * command.
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
    public Boolean getGlobalSceneControl(final long refreshPeriod) {
        if (attributes.get(ATTR_GLOBALSCENECONTROL).isLastValueCurrent(refreshPeriod)) {
            return (Boolean) attributes.get(ATTR_GLOBALSCENECONTROL).getLastValue();
        }

        return (Boolean) readSync(attributes.get(ATTR_GLOBALSCENECONTROL));
    }

    /**
     * Set reporting for the <i>Global Scene Control</i> attribute [attribute ID <b>0x4000</b>].
     * <p>
     * In order to support the use case where the user gets back the last setting of the devices
     * (e.g. level settings for lamps), a global scene is introduced which is stored when the
     * devices are turned off and recalled when the devices are turned on. The global scene is
     * defined as the scene that is stored with group identifier 0 and scene identifier 0.
     * <p>
     * The GlobalSceneControl attribute is defined in order to prevent a second off command
     * storing the all-devices-off situation as a global scene, and to prevent a second on
     * command destroying the current settings by going back to the global scene.
     * <p>
     * The GlobalSceneControl attribute shall be set to TRUE after the reception of a command
     * which causes the OnOff attribute to be set to TRUE, such as a standard On command, a Move to
     * level (with on/off) command, a Recall scene command or a On with recall global scene
     * command.
     * <p>
     * The GlobalSceneControl attribute is set to FALSE after reception of a Off with effect
     * command.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setGlobalSceneControlReporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_GLOBALSCENECONTROL), minInterval, maxInterval);
    }

    /**
     * Set the <i>On Time</i> attribute [attribute ID <b>0x4001</b>].
     * <p>
     * The OnTime attribute specifies the length of time (in 1/10ths second) that the “on”
     * state shall be maintained before automatically transitioning to the “off” state when
     * using the On with timed off command. If this attribute is set to 0x0000 or 0xffff, the
     * device shall remain in its current state.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param onTime the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setOnTime(final Integer value) {
        return write(attributes.get(ATTR_ONTIME), value);
    }

    /**
     * Get the <i>On Time</i> attribute [attribute ID <b>0x4001</b>].
     * <p>
     * The OnTime attribute specifies the length of time (in 1/10ths second) that the “on”
     * state shall be maintained before automatically transitioning to the “off” state when
     * using the On with timed off command. If this attribute is set to 0x0000 or 0xffff, the
     * device shall remain in its current state.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getOnTimeAsync() {
        return read(attributes.get(ATTR_ONTIME));
    }

    /**
     * Synchronously get the <i>On Time</i> attribute [attribute ID <b>0x4001</b>].
     * <p>
     * The OnTime attribute specifies the length of time (in 1/10ths second) that the “on”
     * state shall be maintained before automatically transitioning to the “off” state when
     * using the On with timed off command. If this attribute is set to 0x0000 or 0xffff, the
     * device shall remain in its current state.
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
    public Integer getOnTime(final long refreshPeriod) {
        if (attributes.get(ATTR_ONTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ONTIME).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ONTIME));
    }

    /**
     * Set reporting for the <i>On Time</i> attribute [attribute ID <b>0x4001</b>].
     * <p>
     * The OnTime attribute specifies the length of time (in 1/10ths second) that the “on”
     * state shall be maintained before automatically transitioning to the “off” state when
     * using the On with timed off command. If this attribute is set to 0x0000 or 0xffff, the
     * device shall remain in its current state.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setOnTimeReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_ONTIME), minInterval, maxInterval, reportableChange);
    }

    /**
     * Set the <i>Off Wait Time</i> attribute [attribute ID <b>0x4002</b>].
     * <p>
     * The OffWaitTime attribute specifies the length of time (in 1/10ths second) that the
     * “off” state shall be guarded to prevent an on command turning the device back to its “on”
     * state (e.g., when leaving a room, the lights are turned off but an occupancy sensor
     * detects the leaving person and attempts to turn the lights back on). If this attribute is
     * set to 0x0000, the device shall remain in its current state.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param offWaitTime the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setOffWaitTime(final Integer value) {
        return write(attributes.get(ATTR_OFFWAITTIME), value);
    }

    /**
     * Get the <i>Off Wait Time</i> attribute [attribute ID <b>0x4002</b>].
     * <p>
     * The OffWaitTime attribute specifies the length of time (in 1/10ths second) that the
     * “off” state shall be guarded to prevent an on command turning the device back to its “on”
     * state (e.g., when leaving a room, the lights are turned off but an occupancy sensor
     * detects the leaving person and attempts to turn the lights back on). If this attribute is
     * set to 0x0000, the device shall remain in its current state.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getOffWaitTimeAsync() {
        return read(attributes.get(ATTR_OFFWAITTIME));
    }

    /**
     * Synchronously get the <i>Off Wait Time</i> attribute [attribute ID <b>0x4002</b>].
     * <p>
     * The OffWaitTime attribute specifies the length of time (in 1/10ths second) that the
     * “off” state shall be guarded to prevent an on command turning the device back to its “on”
     * state (e.g., when leaving a room, the lights are turned off but an occupancy sensor
     * detects the leaving person and attempts to turn the lights back on). If this attribute is
     * set to 0x0000, the device shall remain in its current state.
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
    public Integer getOffWaitTime(final long refreshPeriod) {
        if (attributes.get(ATTR_OFFWAITTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_OFFWAITTIME).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_OFFWAITTIME));
    }

    /**
     * Set reporting for the <i>Off Wait Time</i> attribute [attribute ID <b>0x4002</b>].
     * <p>
     * The OffWaitTime attribute specifies the length of time (in 1/10ths second) that the
     * “off” state shall be guarded to prevent an on command turning the device back to its “on”
     * state (e.g., when leaving a room, the lights are turned off but an occupancy sensor
     * detects the leaving person and attempts to turn the lights back on). If this attribute is
     * set to 0x0000, the device shall remain in its current state.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setOffWaitTimeReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_OFFWAITTIME), minInterval, maxInterval, reportableChange);
    }

    /**
     * The Off Command
     * <p>
     * On receipt of this command, a device shall enter its ‘Off’ state. This state is device
     * dependent, but it is recommended that it is used for power off or similar functions. On
     * receipt of the Off command, the OnTime attribute shall be set to 0x0000.
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> offCommand() {
        return send(new OffCommand());
    }

    /**
     * The On Command
     * <p>
     * On receipt of this command, a device shall enter its ‘On’ state. This state is device
     * dependent, but it is recommended that it is used for power on or similar functions. On
     * receipt of the On command, if the value of the OnTime attribute is equal to 0x0000, the
     * device shall set the OffWaitTime attribute to 0x0000.
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> onCommand() {
        return send(new OnCommand());
    }

    /**
     * The Toggle Command
     * <p>
     * On receipt of this command, if a device is in its ‘Off’ state it shall enter its ‘On’ state.
     * Otherwise, if it is in its ‘On’ state it shall enter its ‘Off’ state. On receipt of the
     * Toggle command, if the value of the OnOff attribute is equal to 0x00 and if the value of the
     * OnTime attribute is equal to 0x0000, the device shall set the OffWaitTime attribute to
     * 0x0000. If the value of the OnOff attribute is equal to 0x01, the OnTime attribute shall
     * be set to 0x0000.
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> toggleCommand() {
        return send(new ToggleCommand());
    }

    /**
     * The Off With Effect Command
     * <p>
     * The Off With Effect command allows devices to be turned off using enhanced ways of
     * fading.
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
     * The On With Recall Global Scene command allows the recall of the settings when the device
     * was turned off.
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> onWithRecallGlobalSceneCommand() {
        return send(new OnWithRecallGlobalSceneCommand());
    }

    /**
     * The On With Timed Off Command
     * <p>
     * The On With Timed Off command allows devices to be turned on for a specific duration with a
     * guarded off duration so that should the device be subsequently switched off, further On
     * With Timed Off commands, received during this time, are prevented from turning the
     * devices back on. Note that the device can be periodically re-kicked by subsequent On
     * With Timed Off commands, e.g., from an on/off sensor.
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
    public ZclCommand getResponseFromId(int commandId) {
        switch (commandId) {
            default:
                return null;
        }
    }
}
