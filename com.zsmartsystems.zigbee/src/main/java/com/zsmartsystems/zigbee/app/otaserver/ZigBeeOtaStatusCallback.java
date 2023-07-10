/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.otaserver;

import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.QueryNextImageCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.QueryNextImageResponse;

/**
 * This interface defines status callback updates for Over The Air update progress
 *
 * @author Chris Jackson
 */
public interface ZigBeeOtaStatusCallback {

    /**
     * Provides a callback following the status change of the OTA server
     *
     * @param status the updated {@link ZigBeeOtaServerStatus}
     * @param percent the current percent complete of the transfer
     */
    void otaStatusUpdate(ZigBeeOtaServerStatus status, int percent);

    /**
     * Notifies the listener that a {@link QueryNextImageCommand} has been received from the device that has not been
     * handled by the server as the server has no file waiting to be transferred.
     * <p>
     * If the listener has a new {@link ZigBeeOtaFile} matching the request to transfer, then it may be returned from
     * this method. If no handlers return a file reference, the server will send a {@link QueryNextImageResponse} with
     * status {@link ZclStatus#NO_IMAGE_AVAILABLE};
     * <p>
     * Implementations of this method MUST return quickly. The server will only wait for a few hundred milliseconds
     * before sending a response to the device. If handlers do not respond within this time, it will be assumed there is
     * no new file to send. If a handler needs to perform processing to retrieve the file, it should immediately return
     * null and perform the processing separately before updating the firmware, or preparing the firmware asynchronously
     * so that it is available for the next request from the device.
     *
     * @param command {@link QueryNextImageCommand} received from the device
     * @return {@link ZigBeeOtaFile} if there is a file to transfer, otherwise null
     */
    default ZigBeeOtaFile otaIncomingRequest(QueryNextImageCommand command) {
        return null;
    }
}
