/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import java.util.concurrent.atomic.AtomicLong;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspSerializer;

/**
 * The EmberZNet Serial Protocol (EZSP) is the protocol used by a host
 * application processor to interact with the EmberZNet PRO stack running on a
 * Network CoProcessor (NCP).
 *
 * UG100: EZSP Reference Guide
 *
 * An EZSP Frame is made up as follows -:
 * <ul>
 * <li>Sequence : 1 byte sequence number
 * <li>Frame Control: 1 byte
 * <li>Frame ID : 1 byte
 * <li>Parameters : variable length
 * </ul>
 *
 * @author Chris Jackson
 *
 */
public abstract class EzspFrameRequest extends EzspFrame {
    private final static AtomicLong sequence = new AtomicLong(1);

    protected EzspFrameResponse response = null;

    /**
     * Constructor used to create an outgoing frame
     *
     * @param frameId
     */
    protected EzspFrameRequest() {
        sequenceNumber = (int) sequence.getAndIncrement();
        if (sequenceNumber == 254) {
            sequence.set(1);
        }
    }

    protected void serializeHeader(final EzspSerializer serializer) {
        // Output sequence number
        serializer.serializeUInt8(sequenceNumber);

        // Output Frame Control Byte
        serializer.serializeUInt8(0);
        
        if( ezspVersion > 4 ) {
        	serializer.serializeUInt8(0xFF);
        	serializer.serializeUInt8(0x00);        	
        }

        // Output Frame ID
        serializer.serializeUInt8(frameId);
    }

    public int[] serialize() {
        EzspSerializer serializer = new EzspSerializer();
        serializeHeader(serializer);
        return serializer.getPayload();
    }
}
