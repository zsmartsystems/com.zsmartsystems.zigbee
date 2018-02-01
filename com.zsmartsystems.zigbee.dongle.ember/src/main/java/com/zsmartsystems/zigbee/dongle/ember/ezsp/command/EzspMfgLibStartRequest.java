/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspSerializer;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspConfigId;

/**
 * Class to implement the Ember EZSP command <b>mfglibStart</b>.
 * <p>
 * Activate use of mfglib test routines and enables the radio receiver to report packets it receives to the mfgLibRxHandler()
 * callback. These packets will not be passed up with a CRC failure. All other mfglib functions will return an error until the mfglibStart() has
 * been called
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 */
public class EzspMfgLibStartRequest extends EzspFrameRequest {
    public static int FRAME_ID = 0x83;

    /**
     * true to generate a mfglibRxHandler callback when a packet is received.
     */
    private boolean rxCallback;

    /**
     * Serialiser used to serialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspMfgLibStartRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }

    /**
     * true to generate a mfglibRxHandler callback when a packet is received.
     * @return the current configuration
     */
    public boolean getRxCallback() {
        return rxCallback;
    }

    /**
     * true to generate a mfglibRxHandler callback when a packet is received.
     *
     * @param i_rxCallback
     */
    public void setRxCallback(boolean i_rxCallback) {
        this.rxCallback = i_rxCallback;
    }


    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(serializer);

        // Serialize the fields
        serializer.serializeBool(rxCallback);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(85);
        builder.append("EzspMfgLibStartRequest [rxCallback=");
        builder.append(rxCallback);
        builder.append(']');
        return builder.toString();
    }
}
