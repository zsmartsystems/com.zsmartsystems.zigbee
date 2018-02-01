/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspDeserializer;

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
public abstract class EzspFrameResponse extends EzspFrame {
    protected EzspDeserializer deserializer;

    /**
     * Constructor used to create a received frame. The constructor reads the header fields from the incoming message.
     *
     * @param inputBuffer
     */
    protected EzspFrameResponse(int[] inputBuffer) {
        super();
        deserializer = new EzspDeserializer(inputBuffer);

        sequenceNumber = deserializer.deserializeUInt8();
        frameControl = deserializer.deserializeUInt8();
        frameId = deserializer.deserializeUInt8();
        if (frameId == 0xFF) {
        	deserializer.deserializeUInt8();
        	frameId = deserializer.deserializeUInt8();
        }
        isResponse = (frameControl & 0x80) != 0;
    }

    /*
     * protected boolean initialiseEzspResponse(EzspDeserializer deserializer, int[] inputBuffer) {
     * buffer = inputBuffer;
     * position = 0;
     *
     * // Check the sequence number
     * if (inputBuffer[0] != sequenceNumber) {
     * return false;
     * }
     *
     * // Make sure this is a response
     * if ((inputBuffer[1] & 0x80) == 0) {
     * return false;
     * }
     *
     * position = 3;
     *
     * // Default the status to success. This can be overridden if the response
     * // provides a status
     * emberStatus = EmberStatus.EMBER_SUCCESS;
     *
     * return true;
     * }
     */
    /*
     * protected boolean initialEzspResponse(EzspFrame response) {
     * // Make sure this is a response
     * if (!response.isResponse()) {
     * return false;
     * }
     * 
     * // Check the sequence number
     * if (response.getSequenceNumber() != sequenceNumber) {
     * return false;
     * }
     * 
     * // Default the status to success. This can be overridden if the response
     * // provides a status
     * emberStatus = EmberStatus.EMBER_SUCCESS;
     * 
     * return true;
     * }
     */

}
