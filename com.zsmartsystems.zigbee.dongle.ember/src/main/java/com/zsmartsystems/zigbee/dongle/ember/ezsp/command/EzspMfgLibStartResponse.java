/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspStatus;

/**
 * Class to implement the Ember EZSP command <b>setConfigurationValue</b>.
 * <p>
 * Activate use of mfglib test routines and enables the radio receiver to report packets it receives to the mfgLibRxHandler()
 * callback. These packets will not be passed up with a CRC failure. All other mfglib functions will return an error until the mfglibStart() has
 * been called
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 */
public class EzspMfgLibStartResponse extends EzspFrameResponse {
    public static int FRAME_ID = 0x83;

    /**
     * An EmberStatus value indicating success or the reason for failure.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EzspStatus}
     */
    private EmberStatus status;

    /**
     * Response and Handler constructor
     */
    public EzspMfgLibStartResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        status = deserializer.deserializeEmberStatus();
    }

    /**
     * An EmberStatus value indicating success or the reason for failure.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EzspStatus}
     *
     * @return the current status as {@link EzspStatus}
     */
    public EmberStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(61);
        builder.append("EzspMfgLibStartResponse [status=");
        builder.append(status);
        builder.append(']');
        return builder.toString();
    }
}
