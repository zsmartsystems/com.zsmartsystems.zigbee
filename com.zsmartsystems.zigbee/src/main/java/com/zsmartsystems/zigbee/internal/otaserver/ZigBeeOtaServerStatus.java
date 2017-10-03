/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal.otaserver;

import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.UpgradeEndResponse;

/**
 * An enumeration defining the {@link ZigBeeOtaServer} status provided by the {@link ZigBeeStatusCallback}
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
     * The OTA upgrade is complete.
     */
    OTA_UPGRADE_COMPLETE,
    /**
     * The OTA upgrade failed. This is caused when the {@link UpgradeEndResponse} is not acked.
     */
    OTA_UPGRADE_FAILED;

}
