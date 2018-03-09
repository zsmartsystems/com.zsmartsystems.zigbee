/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

/**
 * The status information provided through the {@link ZigBeeTransportFirmwareCallback} during a
 * {@link ZigBeeTransportFirmwareUpdate}.
 *
 * @author Chris Jackson
 *
 */
public enum ZigBeeTransportFirmwareStatus {
    /**
     * The update has started and the dongle is being initialised for the firmware update
     */
    FIRMWARE_UPDATE_STARTED,
    /**
     * The firmware is being transferred to the dongle
     */
    FIRMWARE_TRANSFER_STARTED,
    /**
     * The firmware has been transferred to the dongle and the final steps to execute the update are being implemented
     */
    FIRMWARE_TRANSFER_COMPLETE,
    /**
     * The update has completed
     */
    FIRMWARE_UPDATE_COMPLETE,
    /**
     * The update has been cancelled following a user request
     */
    FIRMWARE_UPDATE_CANCELLED,
    /**
     * The update failed
     */
    FIRMWARE_UPDATE_FAILED;
}
