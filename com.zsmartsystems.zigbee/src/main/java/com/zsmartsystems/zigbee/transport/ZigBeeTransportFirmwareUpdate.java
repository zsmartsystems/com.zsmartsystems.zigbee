/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

import java.io.InputStream;

/**
 * This interface is to be implemented by dongles that support firmware updating. It allows for device specific files
 * and protocols to be used to update the firmware of a dongle.
 *
 * @author Chris Jackson
 *
 */
public interface ZigBeeTransportFirmwareUpdate {

    /**
     * Implements the dongle firmware update. When the firmware is updated, the {@link ZigBeeTransportFirmwareCallback}
     * is called to notify the caller of updates.
     * <p>
     * Entry conditions are that the port should be closed. This means that any previous use of the transport interfaces
     * should first be stopped.
     *
     * @param firmware the device specific {@link InputStream} containing the dongle firmware
     * @param callback the {@link ZigBeeTransportFirmwareCallback} to be called when the update is complete
     * @return true if the firmware update was started.
     */
    public boolean updateFirmware(InputStream firmwareFile, ZigBeeTransportFirmwareCallback callback);

    /**
     * Cancels the firmware update. The implementation should make every effort to restore the dongle
     * to its previous operational state. The completion of the cancellation will be notified to the
     * {@link ZigBeeTransportFirmwareCallback} with state
     * {@link ZigBeeTransportFirmwareStatus#FIRMWARE_UPDATE_CANCELLED}.
     *
     * @return true if a firmware update is in progress and the cancellation is now started. False if there was no
     *         transfer in progress.
     */
    public boolean cancelUpdateFirmware();

    /**
     * Gets the current firmware version as a String. The format is not defined and can be implementation specific
     * so long as it is a constant format within an implementation.
     *
     * @return {@link String} defining the firmware version. If firmware version is not known, and empty string should
     *         be returned.
     */
    public String getFirmwareVersion();
}
