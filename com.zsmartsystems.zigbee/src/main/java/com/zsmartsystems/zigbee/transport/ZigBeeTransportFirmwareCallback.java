/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

/**
 * A callback to notify a caller of the progress or completion of a {@link ZigBeeTransportFirmwareUpdate}
 *
 * @author Chris Jackson
 *
 */
public interface ZigBeeTransportFirmwareCallback {
    /**
     * Called when the firmware update status changes
     * 
     * @param status the updated {@link ZigBeeTransportFirmwareStatus} of the firmware update
     */
    public void firmwareUpdateCallback(ZigBeeTransportFirmwareStatus status);
}
