/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
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
     * The update has started
     */
    FIRMWARE_UPDATE_STARTED,
    /**
     * The update failed
     */
    FIRMWARE_UPDATE_FAILED,
    /**
     * The update has completed
     */
    FIRMWARE_UPDATE_COMPLETE;
}
