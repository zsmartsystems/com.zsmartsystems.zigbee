/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspDeserializer;

/**
 * The EmberZNet Serial Protocol (EZSP) is the protocol used by a host
 * application processor to interact with the EmberZNet PRO stack running on a
 * Network CoProcessor (NCP).
 * <p>
 * Refer to UG100: EZSP Reference Guide
 * <p>
 * An EZSP Frame is made up as follows -:
 * <ul>
 * <li>Sequence : 1 byte sequence number
 * <li>Frame Control : 1 byte
 * <li>Legacy Frame ID : 1 byte
 * <li>Extended Frame Control : 1 byte
 * <li>Frame ID : 1 byte
 * <li>Parameters : variable length
 * </ul>
 * <p>
 * The Frame Control byte is as follows -:
 * <ul>
 * <li>bit 7 : 1 for Response
 * <li>bit 6 : networkIndex[1]
 * <li>bit 5 : networkIndex[0]
 * <li>bit 4 : callbackType[1]
 * <li>bit 3 : callbackType[0]
 * <li>bit 2 : callbackPending
 * <li>bit 1 : truncated
 * <li>bit 0 : overflow
 * </ul>
 *
 * @author Chris Jackson
 *
 */
public abstract class EzspFrameResponse extends EzspFrame {
    protected EzspDeserializer deserializer;

    private static final int EZSP_FC_CB_PENDING = 0x04;

    private boolean callbackPending = false;

    /**
     * Constructor used to create a received frame. The constructor reads the header fields from the incoming message.
     *
     * @param inputBuffer the input array to deserialize
     */
    protected EzspFrameResponse(int[] inputBuffer) {
        super();
        deserializer = new EzspDeserializer(inputBuffer);
        sequenceNumber = deserializer.deserializeUInt8();
        if (ezspVersion >= 8) {
            frameControl = deserializer.deserializeUInt16();
            frameId = deserializer.deserializeUInt16();
        } else {
            frameControl = deserializer.deserializeUInt8();
            frameId = deserializer.deserializeUInt8();
            if (frameId == EZSP_LEGACY_FRAME_ID) {
                deserializer.deserializeUInt8();
                frameId = deserializer.deserializeUInt8();
            }
        }

        networkId = (frameControl & EZSP_NETWORK_ID_MASK) >> EZSP_NETWORK_ID_SHIFT;

        isResponse = (frameControl & EZSP_FC_RESPONSE) != 0;
        callbackPending = (frameControl & EZSP_FC_CB_PENDING) != 0;
    }

    /**
     * Returns true if the frame control byte indicates that a callback is pending for this response frame
     *
     * @return true if a callback is pending
     */
    public boolean isCallbackPending() {
        return callbackPending;
    }

}
