/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

import java.io.File;

/**
 * This interface is to be implemented by dongles that support firmware updating. It allows for device specific files
 * and protocols to be used to update the firmware of a dongle.
 *
 * @author Chris Jackson
 *
 */
public interface ZigBeeTransportFirmwareUpdate {

    /**
     * Implements the dongle firmware update. When the firmware is updated, the {@link ZigBeeTransportFirmwareCallback} is
     * called to notify the caller.
     * 
     * @param firmwareFile the device specific {@link File} containing the dongle firmware
     * @param callback the {@link ZigBeeTransportFirmwareCallback} to be called when the update is complete
     * @return
     */
    public boolean updateFirmware(File firmwareFile, ZigBeeTransportFirmwareCallback callback);
}
