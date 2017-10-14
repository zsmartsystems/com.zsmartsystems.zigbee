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
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneEnrollRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneEnrollResponse;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneStatusChangeNotificationCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

/**
 * <b>IAS Zone</b> cluster implementation (<i>Cluster ID 0x0500</i>).
 * <p>
 * The IAS Zone cluster defines an interface to the functionality of an IAS security
 * zone device. IAS Zone supports up to two alarm types per zone, low battery
 * reports and supervision of the IAS network.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclIasZoneCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0x0500;

    // Cluster Name
    public static final String CLUSTER_NAME = "IAS Zone";

    // Attribute constants
    public static final int ATTR_ZONESTATE = 0x0000;
    public static final int ATTR_ZONETYPE = 0x0001;
    public static final int ATTR_ZONESTATUS = 0x0002;
    public static final int ATTR_IAS_CIE_ADDRESS = 0x0010;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(4);

        attributeMap.put(ATTR_ZONESTATE, new ZclAttribute(ZclClusterType.IAS_ZONE, ATTR_ZONESTATE, "ZoneState", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_ZONETYPE, new ZclAttribute(ZclClusterType.IAS_ZONE, ATTR_ZONETYPE, "ZoneType", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_ZONESTATUS, new ZclAttribute(ZclClusterType.IAS_ZONE, ATTR_ZONESTATUS, "ZoneStatus", ZclDataType.BITMAP_16_BIT, true, true, false, false));
        attributeMap.put(ATTR_IAS_CIE_ADDRESS, new ZclAttribute(ZclClusterType.IAS_ZONE, ATTR_IAS_CIE_ADDRESS, "IAS_CIE_Address", ZclDataType.IEEE_ADDRESS, true, true, true, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a IAS Zone cluster.
     *
     * @param zigbeeManager {@link ZigBeeNetworkManager}
     * @param zigbeeEndpoint the {@link ZigBeeDevice}
     */
    public ZclIasZoneCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeManager, zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * Get the <i>ZoneState</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getZoneStateAsync() {
        return read(attributes.get(ATTR_ZONESTATE));
    }


    /**
     * Synchronously get the <i>ZoneState</i> attribute [attribute ID <b>0</b>].
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
    public Integer getZoneState(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_ZONESTATE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_ZONESTATE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_ZONESTATE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_ZONESTATE));
    }

    /**
     * Get the <i>ZoneType</i> attribute [attribute ID <b>1</b>].
     * <p>
     * The Zone Type dictates the meaning of Alarm1 and Alarm2 bits of the ZoneStatus attribute
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
     * The Zone Type dictates the meaning of Alarm1 and Alarm2 bits of the ZoneStatus attribute
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
        if(refreshPeriod > 0 && attributes.get(ATTR_ZONETYPE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_ZONETYPE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_ZONETYPE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_ZONETYPE));
    }

    /**
     * Get the <i>ZoneStatus</i> attribute [attribute ID <b>2</b>].
     * <p>
     * The ZoneStatus attribute is a bit map.
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
     * The ZoneStatus attribute is a bit map.
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
        if(refreshPeriod > 0 && attributes.get(ATTR_ZONESTATUS).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_ZONESTATUS).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_ZONESTATUS).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_ZONESTATUS));
    }


    /**
     * Set the <i>IAS_CIE_Address</i> attribute [attribute ID <b>16</b>].
     * <p>
     * The IAS_CIE_Address attribute specifies the address that commands generated by
     * <p>
     * the server shall be sent to. All commands received by the server must also come
     * from this address.
     * <p>
     * It is up to the zone's specific implementation to permit or deny change (write) of
     * this attribute at specific times. Also, it is up to the zone's specific implementation
     * to implement some auto-detect for the CIE (example: by requesting the ZigBee
     * cluster discovery service to locate a Zone Server cluster.) or require the
     * intervention of a CT in order to configure this attribute during installation.
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
     * The IAS_CIE_Address attribute specifies the address that commands generated by
     * <p>
     * the server shall be sent to. All commands received by the server must also come
     * from this address.
     * <p>
     * It is up to the zone's specific implementation to permit or deny change (write) of
     * this attribute at specific times. Also, it is up to the zone's specific implementation
     * to implement some auto-detect for the CIE (example: by requesting the ZigBee
     * cluster discovery service to locate a Zone Server cluster.) or require the
     * intervention of a CT in order to configure this attribute during installation.
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
     * The IAS_CIE_Address attribute specifies the address that commands generated by
     * <p>
     * the server shall be sent to. All commands received by the server must also come
     * from this address.
     * <p>
     * It is up to the zone's specific implementation to permit or deny change (write) of
     * this attribute at specific times. Also, it is up to the zone's specific implementation
     * to implement some auto-detect for the CIE (example: by requesting the ZigBee
     * cluster discovery service to locate a Zone Server cluster.) or require the
     * intervention of a CT in order to configure this attribute during installation.
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
        if(refreshPeriod > 0 && attributes.get(ATTR_IAS_CIE_ADDRESS).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_IAS_CIE_ADDRESS).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (IeeeAddress) attributes.get(ATTR_IAS_CIE_ADDRESS).getLastValue();
            }
        }

        return (IeeeAddress) readSync(attributes.get(ATTR_IAS_CIE_ADDRESS));
    }

    /**
     * The Zone Enroll Response
     *
     * @param enrollResponseCode {@link Integer} Enroll response code
     * @param zoneId {@link Integer} Zone ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> zoneEnrollResponse(Integer enrollResponseCode, Integer zoneId) {
        ZoneEnrollResponse command = new ZoneEnrollResponse();

        // Set the fields
        command.setEnrollResponseCode(enrollResponseCode);
        command.setZoneId(zoneId);

        return send(command);
    }

    /**
     * The Zone Status Change Notification Command
     *
     * @param zoneStatus {@link Integer} Zone Status
     * @param extendedStatus {@link Integer} Extended Status
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> zoneStatusChangeNotificationCommand(Integer zoneStatus, Integer extendedStatus) {
        ZoneStatusChangeNotificationCommand command = new ZoneStatusChangeNotificationCommand();

        // Set the fields
        command.setZoneStatus(zoneStatus);
        command.setExtendedStatus(extendedStatus);

        return send(command);
    }

    /**
     * The Zone Enroll Request Command
     *
     * @param zoneType {@link Integer} Zone Type
     * @param manufacturerCode {@link Integer} Manufacturer Code
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> zoneEnrollRequestCommand(Integer zoneType, Integer manufacturerCode) {
        ZoneEnrollRequestCommand command = new ZoneEnrollRequestCommand();

        // Set the fields
        command.setZoneType(zoneType);
        command.setManufacturerCode(manufacturerCode);

        return send(command);
    }

    /**
     * Add a binding for this cluster to the local node
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> bind() {
        return bind();
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0: // ZONE_ENROLL_RESPONSE
                return new ZoneEnrollResponse();
            default:
                return null;
        }
    }

    @Override
    public ZclCommand getResponseFromId(int commandId) {
        switch (commandId) {
            case 0: // ZONE_STATUS_CHANGE_NOTIFICATION_COMMAND
                return new ZoneStatusChangeNotificationCommand();
            case 1: // ZONE_ENROLL_REQUEST_COMMAND
                return new ZoneEnrollRequestCommand();
            default:
                return null;
        }
    }
}
