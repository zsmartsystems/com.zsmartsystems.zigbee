/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.iasclient;

import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.ZigBeeApplication;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclCommandListener;
import com.zsmartsystems.zigbee.zcl.clusters.ZclIasZoneCluster;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.EnrollResponseCodeEnum;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneEnrollRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneEnrollResponse;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneStateEnum;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneTypeEnum;

/**
 * Implements the a minimal IAS client side functionality. The IAS (Intruder Alarm Systems) server clusters require
 * support on the client side when enrolling. Once an IAS device joins the network, it looks for a CIE (Control and
 * Indicating Equipment) which is implemented here.
 * <p>
 * There are three methods for enrolling IAS Zone server to an IAS CIE (i.e., IAS Zone client):
 * <ul>
 * <li>Trip-to-pair
 * <li>Auto-Enroll-Response
 * <li>Auto-Enroll-Request
 * </ul>
 * <p>
 * IAS Zone clients SHALL support either:
 * <ul>
 * <li>Trip-to-pair AND Auto-Enroll-Response, OR
 * <li>Auto-Enroll-Request
 * </ul>
 * <p>
 * An IAS Zone client MAY support all enrollment methods. The Trip-to-Pair enrollment method is primarily intended to be
 * used when there is a desire for an explicit enrollment method (e.g., when a GUI wizard or other commissioning tool is
 * used by a user or installer to add multiple IAS Zone servers in an orderly fashion, assign names to them, configure
 * them in the system).
 * <p>
 * The detailed requirements for each commissioning method follow:
 * <p>
 * <b>Trip-to-Pair</b>
 * <ol>
 * <li>After an IAS Zone server is commissioned to a network, the IAS CIE MAY perform service discovery.
 * <li>If the IAS CIE determines it wants to enroll the IAS Zone server, it SHALL send a Write Attribute command on the
 * IAS Zone server’s IAS_CIE_Address attribute with its IEEE address
 * <li>The IAS Zone server MAY configure a binding table entry for the IAS CIE’s address because all of its
 * communication will be directed to the IAS CIE.
 * <li>Upon a user input determined by the manufacturer (e.g., a button, change to device’s ZoneStatus attribute that
 * would result in a Zone Status Change Notification command) and the IAS Zone server’s ZoneState attribute equal to
 * 0x00 (unenrolled), the IAS Zone server SHALL send a Zone Enroll Request command.
 * <li>The IAS CIE SHALL send a Zone Enroll Response command, which assigns the IAS Zone server’s ZoneID attribute.
 * <li>The IAS Zone server SHALL change its ZoneState attribute to 0x01 (enrolled).
 * </ol>
 * <p>
 * <b>Auto-Enroll-Response</b>
 * <ol>
 * <li>After an IAS Zone server is commissioned to a network, the IAS CIE MAY perform service discovery.
 * <li>If the IAS CIE determines it wants to enroll the IAS Zone server, it SHALL send a Write Attribute command
 * on the IAS Zone server’s CIE_IAS_Address attribute with its IEEE address.
 * <li>The IAS Zone server MAY configure a binding table entry for the IAS CIE’s address because all of its
 * communication will be directed to the IAS CIE.
 * <li>The IAS CIE SHALL send a Zone Enroll Response, which assigns the IAS Zone server’s ZoneID attribute.
 * <li>The IAS Zone server SHALL change its ZoneState attribute to 0x01 (enrolled).
 * </ol>
 * <p>
 * <b>Auto-Enroll-Request</b>
 * <ol>
 * <li>After an IAS Zone server is commissioned to a network, the IAS CIE MAY perform service discovery.
 * <li>If the IAS CIE determines it wants to enroll the IAS Zone server, it SHALL send a Write Attribute command on the
 * IAS Zone server’s IAS_CIE_Address attribute with its IEEE address.
 * <li>The IAS Zone server MAY configure a binding table entry for the IAS CIE’s address because all of its
 * communication will be directed to the IAS CIE.
 * <li>The IAS Zone server SHALL send a Zone Enroll Request command.
 * <li>The IAS CIE SHALL send a Zone Enroll Response command, which assigns the IAS Zone server’s ZoneID attribute.
 * <li>The IAS Zone server SHALL change its ZoneState attribute to 0x01 (enrolled).
 * </ol>
 * <p>
 * <img src="./doc-files/ZclIasZoneClient.png" width="100%">
 *
 * @author Chris Jackson
 *
 */
public class ZclIasZoneClient implements ZigBeeApplication, ZclCommandListener {
    /**
     * The default number of milliseconds to wait for a {@link ZoneEnrollRequestCommand} before sending the
     * {@link ZoneEnrollResponse}
     */
    private static final long DEFAULT_AUTO_ENROLL_DELAY = 2000;

    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZclIasZoneClient.class);

    /**
     * The {@link ZigBeeNetworkManager}
     */
    private final ZigBeeNetworkManager networkManager;

    /**
     * The IAS cluster to which we're bound
     */
    private ZclIasZoneCluster iasZoneCluster;

    /**
     * The {@link IeeeAddress} of the CIE server. Normally this should be the local address of the coordinator.
     */
    private IeeeAddress ieeeAddress;

    /**
     * The time to wait for a {@link ZoneEnrollRequestCommand} before sending the {@link ZoneEnrollResponse}
     */
    private long autoEnrollDelay = DEFAULT_AUTO_ENROLL_DELAY;

    /**
     * The auto enrollment task being run
     */
    private ScheduledFuture<?> autoEnrollmentTask;

    /**
     * The IAS zone ID for this device
     */
    private int zoneId;

    /**
     * The zone type reported by the remote device during enrolment
     */
    private Integer zoneType;

    /**
     * Constructor
     *
     * @param networkManager the {@link ZigBeeNetworkManager} this belongs to
     * @param ieeeAddress the {@link IeeeAddress} of the CIE
     * @param zoneId the zone ID to use for this device
     */
    public ZclIasZoneClient(ZigBeeNetworkManager networkManager, IeeeAddress ieeeAddress, int zoneId) {
        this.networkManager = networkManager;
        this.ieeeAddress = ieeeAddress;
        this.zoneId = zoneId;
    }

    /**
     * Gets the IAS zone Id for this device
     *
     * @return the IAS zone ID number
     */
    public int getZoneId() {
        return zoneId;
    }

    /**
     * Gets the IAS zone type for this device. This is provided by the remote device during enrollment.
     *
     * @return the IAS zone type as {@link ZoneTypeEnum} or null if the zone type is unknown
     */
    public ZoneTypeEnum getZoneType() {
        if (zoneType == null) {
            return null;
        }
        return ZoneTypeEnum.getByValue(zoneType);
    }

    /**
     * Gets the IAS zone type for this device as an integer. This is provided by the remote device during enrollment.
     *
     * @return the IAS zone type
     */
    public Integer getZoneTypeId() {
        return zoneType;
    }

    @Override
    public ZigBeeStatus appStartup(ZclCluster cluster) {
        iasZoneCluster = (ZclIasZoneCluster) cluster;
        iasZoneCluster.addCommandListener(this);

        initialise();

        return ZigBeeStatus.SUCCESS;
    }

    private void initialise() {
        Integer currentState = (Integer) iasZoneCluster.getAttribute(ZclIasZoneCluster.ATTR_ZONESTATE).readValue(0);
        if (currentState != null) {
            ZoneStateEnum currentStateEnum = ZoneStateEnum.getByValue(currentState);
            logger.debug("{}: IAS CIE state is currently {}[{}]", iasZoneCluster.getZigBeeAddress(), currentStateEnum,
                    currentState);
            if (currentStateEnum == ZoneStateEnum.ENROLLED) {
                logger.debug("{}: IAS CIE is already enrolled", iasZoneCluster.getZigBeeAddress());
                return;
            }
        } else {
            logger.debug("{}: IAS CIE failed to get state", iasZoneCluster.getZigBeeAddress());
        }

        ZclAttribute cieAddressAttribute = iasZoneCluster.getAttribute(ZclIasZoneCluster.ATTR_IASCIEADDRESS);
        IeeeAddress currentIeeeAddress = (IeeeAddress) cieAddressAttribute.readValue(0);
        logger.debug("{}: IAS CIE address is currently {}", iasZoneCluster.getZigBeeAddress(), currentIeeeAddress);

        if (!ieeeAddress.equals(currentIeeeAddress)) {
            // Set the CIE address in the remote device. This is where the device will send its reports.
            cieAddressAttribute.writeValue(ieeeAddress);

            currentIeeeAddress = (IeeeAddress) cieAddressAttribute.readValue(0);
            if (ieeeAddress.equals(currentIeeeAddress)) {
                logger.debug("{}: IAS CIE address is confirmed {}", iasZoneCluster.getZigBeeAddress(),
                        currentIeeeAddress);
            } else {
                logger.warn("{}: IAS CIE address is NOT confirmed {}", iasZoneCluster.getZigBeeAddress(),
                        currentIeeeAddress);
            }
        }

        Integer currentZone = (Integer) iasZoneCluster.getAttribute(ZclIasZoneCluster.ATTR_ZONEID).readValue(0);
        if (currentZone == null) {
            logger.debug("{}: IAS CIE zone ID request failed", iasZoneCluster.getZigBeeAddress());
        } else {
            logger.debug("{}: IAS CIE zone ID is currently {}", iasZoneCluster.getZigBeeAddress(), currentZone);
        }

        zoneType = (Integer) iasZoneCluster.getAttribute(ZclIasZoneCluster.ATTR_ZONETYPE).readValue(Long.MAX_VALUE);
        if (zoneType == null) {
            logger.debug("{}: IAS CIE zone type request failed", iasZoneCluster.getZigBeeAddress());
        } else {
            logger.debug("{}: IAS CIE zone type is 0x{}, {}", iasZoneCluster.getZigBeeAddress(),
                    String.format("%04X", zoneType), ZoneTypeEnum.getByValue(zoneType));
        }

        // Start the auto-enroll timer
        final Runnable runnableTask = new AutoEnrollmentTask();
        autoEnrollmentTask = networkManager.scheduleTask(runnableTask, autoEnrollDelay);
    }

    @Override
    public void appShutdown() {
        if (autoEnrollmentTask != null) {
            autoEnrollmentTask.cancel(true);
        }
    }

    @Override
    public int getClusterId() {
        return ZclIasZoneCluster.CLUSTER_ID;
    }

    /**
     * Handle the received {@link ZoneEnrollRequestCommand} and send the {@link ZoneEnrollResponse}. This will register
     * the zone number specified in the constructor {@link #ZigBeeIasCieApp}.
     *
     * @param command the received {@link ZoneEnrollRequestCommand}
     * @param the {@link ZclCommand} to send as the response
     */
    private boolean handleZoneEnrollRequestCommand(ZoneEnrollRequestCommand command) {
        if (autoEnrollmentTask != null) {
            autoEnrollmentTask.cancel(true);
        }

        zoneType = command.getZoneType();
        iasZoneCluster.zoneEnrollResponse(EnrollResponseCodeEnum.SUCCESS.getKey(), zoneId);
        return true;
    }

    /**
     * Sets the auto enrollment delay in milliseconds. This is the time that the client will wait for the server to send
     * a {@link ZoneEnrollRequestCommand} before sending the {@link ZoneEnrollResponse} to automatically enroll the
     * device.
     *
     * @param autoEnrollDelay the autoEnrollDelay to set in milliseconds
     */
    public void setAutoEnrollDelay(long autoEnrollDelay) {
        this.autoEnrollDelay = autoEnrollDelay;
    }

    @Override
    public boolean commandReceived(ZclCommand command) {
        if (command instanceof ZoneEnrollRequestCommand) {
            return handleZoneEnrollRequestCommand((ZoneEnrollRequestCommand) command);
        }

        return false;
    }

    private class AutoEnrollmentTask implements Runnable {
        @Override
        public void run() {
            iasZoneCluster.zoneEnrollResponse(EnrollResponseCodeEnum.SUCCESS.getKey(), zoneId);
        }
    }
}
