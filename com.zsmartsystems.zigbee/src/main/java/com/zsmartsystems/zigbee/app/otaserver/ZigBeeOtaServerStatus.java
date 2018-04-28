/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.otaserver;

/**
 * An enumeration defining the {@link ZclOtaUpgradeServer} status provided to the {@link ZigBeeStatusCallback}
 *
 * @author Chris Jackson
 *
 */
public enum ZigBeeOtaServerStatus {
    /**
     * The OTA server is uninitialized. No firmware is currently set.
     */
    OTA_UNINITIALISED,

    /**
     * The OTA server is waiting for the client to request an update
     */
    OTA_WAITING,

    /**
     * The OTA server is currently progressing a transfer
     */
    OTA_TRANSFER_IN_PROGRESS,

    /**
     * The OTA transfer is complete. The firmware has not been executed.
     */
    OTA_TRANSFER_COMPLETE,

    /**
     * The transfer is complete, and the server has told the client to execute the upgrade with a delay
     */
    OTA_UPGRADE_WAITING,

    /**
     * The OTA upgrade is complete, and the device firmware is restarting.
     */
    OTA_UPGRADE_FIRMWARE_RESTARTING,

    /**
     * The OTA upgrade is complete and an acknowledgement was received from the device to confirm the firmware version
     * is consistent with the new file.
     */
    OTA_UPGRADE_COMPLETE,

    /**
     * The OTA upgrade was cancelled by user request. A new transfer can be started by setting a new OTA firmware
     */
    OTA_CANCELLED,

    /**
     * The OTA upgrade failed. This may be caused due to a timeout or unexpected response, or the firmware version was
     * inconsistent on completion.
     */
    OTA_UPGRADE_FAILED;
}
