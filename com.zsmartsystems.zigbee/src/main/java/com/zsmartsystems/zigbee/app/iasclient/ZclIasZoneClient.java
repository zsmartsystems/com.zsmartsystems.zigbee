/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.iasclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.ZigBeeApplication;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclIasZoneCluster;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.EnrollResponseCodeEnum;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneEnrollRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneEnrollResponse;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneStateEnum;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneTypeEnum;

/**
 * Implements the a minimal IAS client side functionality. The IAS (Intruder Alarm Systems) server clusters require
 * support on the client side when enroling. Once an IAS device joins the network, it looks for a CIE (Control and
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
 * >
 * The detailed requirements for each commissioning method follow:
 * <p>
 * Trip-to-Pair
 * <ol>
 * <li>After an IAS Zone server is commissioned to a network, the IAS CIEMAY perform service discovery.
 * <li>If the IAS CIEdetermines it wants to enroll the IAS Zone server, it SHALL send a Write Attribute command on the
 * IAS Zone server’s IAS_CIE_Addressattribute with its IEEE address
 * <li>The IAS Zone server MAY configure a binding table entry for the IAS CIE’s address because all of its
 * communication will be directed to the IAS CIE.
 * <li>Upon a user input determined by the manufacturer (e.g., a button, change to device’s ZoneStatus attribute that
 * would result in a Zone Status Change Notification command) and the IAS Zone server’s ZoneStateattribute equal to 0x00
 * (unenrolled), the IAS Zone server SHALL send a Zone Enroll Request command.
 * <li>The IAS CIESHALL send a Zone Enroll Response command, which assigns the IAS Zone server’s ZoneIDattribute.
 * <li>The IAS Zone server SHALL change its ZoneStateattribute to 0x01 (enrolled).
 * </ol>
 * <p>
 * Auto-Enroll-Response
 * <ol>
 * <li>After an IAS Zone server is commissioned to a network, the IAS CIEMAY perform service discovery.
 * <li>If the IAS CIEdetermines it wants to enroll the IAS Zone server, it SHALL send a Write Attribute command
 * on the IAS Zone server’s CIE_IAS_Addressattribute with its IEEE address.
 * <li>The IAS Zone server MAY configure a binding table entry for the IAS CIE’s address because all of its
 * communication will be directed to the IAS CIE.
 * <li>The IAS CIESHALL send a Zone Enroll Response, which assigns the IAS Zone server’s ZoneIDattribute.
 * <li>The IAS Zone server SHALL change its ZoneStateattribute to 0x01 (enrolled).
 * </ol>
 * <p>
 * Auto-Enroll-Request
 * <ol>
 * <li>After an IAS Zone server is commissioned to a network, the IAS CIEMAY perform service discovery.
 * <li>If the IAS CIEdetermines it wants to enroll the IAS Zone server, it SHALL send a Write Attribute command on the
 * IAS Zone server’s IAS_CIE_Addressattribute with its IEEE address.
 * <li>The IAS Zone server MAY configure a binding table entry for the IAS CIE’s address because all of its
 * communication will be directed to the IAS CIE.
 * <li>The IAS Zone server SHALL send a Zone Enroll Request command.
 * <li>The IAS CIESHALL send a Zone Enroll Response command, which assigns the IAS Zone server’s ZoneIDattribute.
 * <li>The IAS Zone server SHALL change its ZoneStateattribute to 0x01 (enrolled).
 * </ol>
 *
 * @author Chris Jackson
 *
 */
public class ZclIasZoneClient implements ZigBeeApplication {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZclIasZoneClient.class);

    /**
     * The IAS cluster to which we're bound
     */
    private ZclIasZoneCluster iasZoneCluster;

    /**
     * The {@link IeeeAddress} of the CIE server. Normally this should be the local address of the coordinator.
     */
    private IeeeAddress ieeeAddress;

    /**
     * The IAS zone for this device
     */
    private int zone;

    /**
     * The zone type reported by the remote device during enrollment
     */
    private int zoneType;

    /**
     * Constructor
     */
    public ZclIasZoneClient(IeeeAddress ieeeAddress, int zone) {
        this.ieeeAddress = ieeeAddress;
        this.zone = zone;
    }

    /**
     * Gets the IAS zone for this device
     *
     * @return the IAS zone number
     */
    public int getZone() {
        return zone;
    }

    /**
     * Gets the IAS zone type for this device. This is provided by the remote device during enrollment.
     *
     * @return the IAS zone type as {@link ZoneTypeEnum}
     */
    public ZoneTypeEnum getZoneType() {
        return ZoneTypeEnum.getByValue(zoneType);
    }

    /**
     * Gets the IAS zone type for this device as an integer. This is provided by the remote device during enrollment.
     *
     * @return the IAS zone type
     */
    public int getZoneTypeId() {
        return zoneType;
    }

    @Override
    public ZigBeeStatus appStartup(ZclCluster cluster) {
        iasZoneCluster = (ZclIasZoneCluster) cluster;

        initialise();

        return ZigBeeStatus.SUCCESS;
    }

    private void initialise() {
        Integer currentState = iasZoneCluster.getZoneState(0);
        if (currentState != null) {
            ZoneStateEnum currentStateEnum = ZoneStateEnum.getByValue(currentState);
            logger.debug("{}: IAS CIE state is currently {}[{}]", iasZoneCluster.getZigBeeAddress(), currentStateEnum,
                    currentState);
        }

        IeeeAddress currentIeeeAddress = iasZoneCluster.getIasCieAddress(0);
        logger.debug("{}: IAS CIE address is currently {}", iasZoneCluster.getZigBeeAddress(), currentIeeeAddress);

        if (!ieeeAddress.equals(currentIeeeAddress)) {
            // Set the CIE address in the remote device. This is where the device will send its reports.
            iasZoneCluster.setIasCieAddress(ieeeAddress);

            currentIeeeAddress = iasZoneCluster.getIasCieAddress(0);
            logger.debug("{}: IAS CIE address is confirmed {}", iasZoneCluster.getZigBeeAddress(), currentIeeeAddress);
        }

        Integer currentZone = iasZoneCluster.getZoneId(0);
        if (currentZone == null) {
            logger.debug("{}: IAS CIE zone ID request failed", iasZoneCluster.getZigBeeAddress());
        } else {
            logger.debug("{}: IAS CIE zone ID is currently {}", iasZoneCluster.getZigBeeAddress(), currentZone);
        }

        Integer zoneType = iasZoneCluster.getZoneType(Long.MAX_VALUE);
        if (zoneType == null) {
            logger.debug("{}: IAS CIE zone type request failed", iasZoneCluster.getZigBeeAddress());
        } else {
            logger.debug("{}: IAS CIE zone type is 0x{}, {}", iasZoneCluster.getZigBeeAddress(),
                    String.format("%04X", zoneType), ZoneTypeEnum.getByValue(zoneType));
        }
    }

    @Override
    public void appShutdown() {
        // Nothing to do
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
     */
    private void handleZoneEnrollRequestCommand(ZoneEnrollRequestCommand command) {
        zoneType = command.getZoneType();
        iasZoneCluster.zoneEnrollResponse(EnrollResponseCodeEnum.SUCCESS.getKey(), zone);
    }

    @Override
    public void commandReceived(ZigBeeCommand command) {
        if (command instanceof ZoneEnrollRequestCommand) {
            handleZoneEnrollRequestCommand((ZoneEnrollRequestCommand) command);
            return;
        }
    }

}
