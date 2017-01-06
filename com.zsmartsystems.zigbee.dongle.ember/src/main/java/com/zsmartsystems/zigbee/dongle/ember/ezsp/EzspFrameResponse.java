package com.zsmartsystems.zigbee.dongle.ember.ezsp;

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

    /**
     * Constructor used to create a received frame
     * 
     * @param inputBuffer
     */
    EzspFrameResponse(int[] inputBuffer) {
        super(inputBuffer[2]);

        this.buffer = inputBuffer;

        this.sequenceNumber = inputBuffer[0];
        this.frameControl = inputBuffer[1];
        this.isResponse = (inputBuffer[1] & 0x80) != 0;

        position = 3;
    }
}
