/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.otaserver;

import java.util.HashMap;
import java.util.Map;

/**
 * The upgrade status of the client device.
 * <p>
 * The status indicates where the client device is at in terms of the download and upgrade process. The status helps
 * to indicate whether the client has completed the download process and whether it is ready to upgrade to the new
 * image. The status MAY be queried by the server via ZCL read attribute command. Hence, the server MAY not be able
 * to reliably query the status of ZED client since the device MAY have its radio off.
 *
 * @author Chris Jackson
 */
public enum ImageUpgradeStatus {
    UNKNOWN(0xFF),

    /**
     * Normal.
     * <p>
     * Normal status typically means the device has not participated in any download process. Additionally, the client
     * SHALL set its upgrade status back to Normal if the previous upgrade process was not successful.
     */
    NORMAL(0x00),
    /**
     * Download in progress.
     * <p>
     * Download in progress status is used from when the client device receives SUCCESS status in the Query Next Image
     * Response command from the server prior to when the device receives all the image data it needs.
     */
    DOWNLOAD_IN_PROGRESS(0x01),
    /**
     * Download complete.
     * <p>
     * Download complete status indicates the client has received all data blocks required and it has already verified
     * the OTA Upgrade Image signature (if applied) and has already written the image onto its additional memory space.
     * The status will be modified as soon as the client receives Upgrade End Response command from the server
     */
    DOWNLOAD_COMPLETE(0x02),
    /**
     * Waiting to upgrade.
     * <p>
     * Wait to upgrade status indicates that the client is told by the server to wait until another (upgrade) command is
     * sent from the server to indicate the client to upgrade its image.
     */
    WAITING_TO_UPGRADE(0x03),
    /**
     * Count down.
     * <p>
     * Count down status indicates that the server has notified the client to count down to when it SHALL upgrade its
     * image.
     */
    COUNT_DOWN(0x04),
    /**
     * Wait for more.
     * <p>
     * Wait for more (upgrade) image indicates that the client is still waiting to receive more OTA upgrade image files
     * from the server. This is true for a client device that is composed of multiple processors and each processor
     * requires different image. The client SHALL be in this state until it has received all necessary OTA upgrade
     * images, then it SHALL transition to Download complete state.
     */
    WAIT_FOR_MORE(0x05);

    /**
     * The status type value
     */
    private final int statusId;

    /**
     * Map containing the link of status type value to the enum
     */
    private static Map<Integer, ImageUpgradeStatus> map = null;

    private ImageUpgradeStatus(int stackId) {
        this.statusId = stackId;
    }

    /**
     * Get a {@link ImageUpgradeStatus} from an integer
     *
     * @param stackTypeValue integer value defining the status type
     * @return {@link ImageUpgradeStatus} or {@link #UNKNOWN} if the value could not be converted
     */
    public static ImageUpgradeStatus getStatus(int statusValue) {
        if (map == null) {
            map = new HashMap<Integer, ImageUpgradeStatus>();
            for (ImageUpgradeStatus stackType : values()) {
                map.put(stackType.statusId, stackType);
            }

        }
        if (map.get(statusValue) == null) {
            return UNKNOWN;
        }
        return map.get(statusValue);
    }

    /**
     * Gets the integer value for the status type
     *
     * @return int defining the status
     */
    public int getId() {
        return statusId;
    }
}