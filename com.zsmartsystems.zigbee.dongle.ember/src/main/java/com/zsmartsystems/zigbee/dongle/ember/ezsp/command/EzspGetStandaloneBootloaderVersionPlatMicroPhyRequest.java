/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspSerializer;

/**
 * Class to implement the Ember EZSP command <b>getStandaloneBootloaderVersionPlatMicroPhy</b>.
 * <p>
 * Detects if the standalone bootloader is installed, and if so returns the installed version.
 * If not return 0xffff. A returned version of 0x1234 would indicate version 1.2 build 34. Also
 * return the node's version of PLAT, MICRO and PHY.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspGetStandaloneBootloaderVersionPlatMicroPhyRequest extends EzspFrameRequest {
    public static final int FRAME_ID = 0x91;

    /**
     * Serialiser used to serialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspGetStandaloneBootloaderVersionPlatMicroPhyRequest() {
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
        final StringBuilder builder = new StringBuilder(81);
        builder.append("EzspGetStandaloneBootloaderVersionPlatMicroPhyRequest [networkId=");
        builder.append(networkId);
        builder.append(']');
        return builder.toString();
    }
}
