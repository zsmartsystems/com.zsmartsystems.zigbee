/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaswd.SquawkCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaswd.StartWarningCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

/**
 * <b>IAS WD</b> cluster implementation (<i>Cluster ID 0x0502</i>).
 * <p>
 * The IAS WD cluster provides an interface to the functionality of any Warning
 * Device equipment of the IAS system. Using this cluster, a ZigBee enabled CIE
 * device can access a ZigBee enabled IAS WD device and issue alarm warning
 * indications (siren, strobe lighting, etc.) when a system alarm condition is detected.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclIasWdCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0502;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "IAS WD";

    // Attribute constants
    /**
     * The MaxDuration attribute specifies the maximum time in seconds that the siren
     * will sound continuously, regardless of start/stop commands.
     */
    public static final int ATTR_MAXDURATION = 0x0000;
    /**
     */
    public static final int ATTR_ZONETYPE = 0x0001;
    /**
     */
    public static final int ATTR_ZONESTATUS = 0x0002;
    /**
     */
    public static final int ATTR_IAS_CIE_ADDRESS = 0x0010;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(4);

        attributeMap.put(ATTR_MAXDURATION, new ZclAttribute(ZclClusterType.IAS_WD, ATTR_MAXDURATION, "MaxDuration", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, true, false));
        attributeMap.put(ATTR_ZONETYPE, new ZclAttribute(ZclClusterType.IAS_WD, ATTR_ZONETYPE, "ZoneType", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_ZONESTATUS, new ZclAttribute(ZclClusterType.IAS_WD, ATTR_ZONESTATUS, "ZoneStatus", ZclDataType.BITMAP_16_BIT, true, true, false, false));
        attributeMap.put(ATTR_IAS_CIE_ADDRESS, new ZclAttribute(ZclClusterType.IAS_WD, ATTR_IAS_CIE_ADDRESS, "IAS_CIE_Address", ZclDataType.IEEE_ADDRESS, true, true, true, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a IAS WD cluster.
     *
     * @param zigbeeManager {@link ZigBeeNetworkManager}
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint}
     */
    public ZclIasWdCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeManager, zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }



    /**
     * Set the <i>MaxDuration</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The MaxDuration attribute specifies the maximum time in seconds that the siren
     * will sound continuously, regardless of start/stop commands.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param maxDuration the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMaxDuration(final Object value) {
        return write(attributes.get(ATTR_MAXDURATION), value);
    }

    /**
     * Get the <i>MaxDuration</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The MaxDuration attribute specifies the maximum time in seconds that the siren
     * will sound continuously, regardless of start/stop commands.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMaxDurationAsync() {
        return read(attributes.get(ATTR_MAXDURATION));
    }


    /**
     * Synchronously get the <i>MaxDuration</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The MaxDuration attribute specifies the maximum time in seconds that the siren
     * will sound continuously, regardless of start/stop commands.
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
    public Integer getMaxDuration(final long refreshPeriod) {
        if (attributes.get(ATTR_MAXDURATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MAXDURATION).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MAXDURATION));
    }

    /**
     * Get the <i>ZoneType</i> attribute [attribute ID <b>1</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getZoneTypeAsync() {
        return read(attributes.get(ATTR_ZONETYPE));
    }


    /**
     * Synchronously get the <i>ZoneType</i> attribute [attribute ID <b>1</b>].
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
    public Integer getZoneType(final long refreshPeriod) {
        if (attributes.get(ATTR_ZONETYPE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ZONETYPE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ZONETYPE));
    }

    /**
     * Get the <i>ZoneStatus</i> attribute [attribute ID <b>2</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getZoneStatusAsync() {
        return read(attributes.get(ATTR_ZONESTATUS));
    }


    /**
     * Synchronously get the <i>ZoneStatus</i> attribute [attribute ID <b>2</b>].
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
    public Integer getZoneStatus(final long refreshPeriod) {
        if (attributes.get(ATTR_ZONESTATUS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ZONESTATUS).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ZONESTATUS));
    }


    /**
     * Set the <i>IAS_CIE_Address</i> attribute [attribute ID <b>16</b>].
     * <p>
     * The attribute is of type {@link IeeeAddress}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param iasCieAddress the {@link IeeeAddress} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setIasCieAddress(final Object value) {
        return write(attributes.get(ATTR_IAS_CIE_ADDRESS), value);
    }

    /**
     * Get the <i>IAS_CIE_Address</i> attribute [attribute ID <b>16</b>].
     * <p>
     * The attribute is of type {@link IeeeAddress}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getIasCieAddressAsync() {
        return read(attributes.get(ATTR_IAS_CIE_ADDRESS));
    }


    /**
     * Synchronously get the <i>IAS_CIE_Address</i> attribute [attribute ID <b>16</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link IeeeAddress}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link IeeeAddress} attribute value, or null on error
     */
    public IeeeAddress getIasCieAddress(final long refreshPeriod) {
        if (attributes.get(ATTR_IAS_CIE_ADDRESS).isLastValueCurrent(refreshPeriod)) {
            return (IeeeAddress) attributes.get(ATTR_IAS_CIE_ADDRESS).getLastValue();
        }

        return (IeeeAddress) readSync(attributes.get(ATTR_IAS_CIE_ADDRESS));
    }

    /**
     * The Start Warning Command
     * <p>
     * This command starts the WD operation. The WD alerts the surrounding area by
     * audible (siren) and visual (strobe) signals.
     * <br>
     * A Start Warning command shall always terminate the effect of any previous
     * command that is still current.
     *
     * @param header {@link Integer} Header
     * @param warningDuration {@link Integer} Warning duration
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> startWarningCommand(Integer header, Integer warningDuration) {
        StartWarningCommand command = new StartWarningCommand();

        // Set the fields
        command.setHeader(header);
        command.setWarningDuration(warningDuration);

        return send(command);
    }

    /**
     * The Squawk Command
     *
     * @param header {@link Integer} Header
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> squawkCommand(Integer header) {
        SquawkCommand command = new SquawkCommand();

        // Set the fields
        command.setHeader(header);

        return send(command);
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0: // START_WARNING_COMMAND
                return new StartWarningCommand();
            case 2: // SQUAWK_COMMAND
                return new SquawkCommand();
            default:
                return null;
        }
    }
}
