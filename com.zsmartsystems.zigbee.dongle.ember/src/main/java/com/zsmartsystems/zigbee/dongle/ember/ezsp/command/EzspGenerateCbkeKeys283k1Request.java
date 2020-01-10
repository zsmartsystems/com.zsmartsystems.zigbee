/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspSerializer;

/**
 * Class to implement the Ember EZSP command <b>generateCbkeKeys283k1</b>.
 * <p>
 * This call starts the generation of the ECC 283k1 curve Ephemeral Public/Private key pair.
 * When complete it stores the private key. The results are returned via
 * ezspGenerateCbkeKeys283k1Handler().
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspGenerateCbkeKeys283k1Request extends EzspFrameRequest {
    public static final int FRAME_ID = 0xE8;

    /**
     * Serialiser used to serialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspGenerateCbkeKeys283k1Request() {
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
        return "EzspGenerateCbkeKeys283k1Request []";
    }
}
