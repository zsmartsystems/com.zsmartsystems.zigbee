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
 * Deactivate use of mfglib test routines; restores the hardware to the state it was in prior to mfglibStart() and stops receiving
 * packets started by mfglibStart() at the same time.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 */
public class EzspMfgLibEndRequest extends EzspFrameRequest {
    public static int FRAME_ID = 0x84;


    /**
     * Serialiser used to serialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspMfgLibEndRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }


    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(serializer);

        // Serialize the fields
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(85);
        builder.append("EzspMfgLibEndRequest");
        return builder.toString();
    }
}
