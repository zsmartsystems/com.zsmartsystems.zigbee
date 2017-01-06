package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

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
    EzspFrameRequest(int frameId) {
        super(frameId);

        sequenceNumber = (int) sequence.getAndIncrement();
        if (sequenceNumber == 254) {
            sequence.set(1);
        }
    }

    protected void initialiseOutputBuffer() {
        // Output sequence number
        buffer[0] = sequenceNumber;

        // Output Frame Control Byte
        buffer[1] = 0;

        // Output Frame ID
        buffer[2] = frameId;

        length = 3;
    }

    @Override
    public int[] getOutputBuffer() {
        initialiseOutputBuffer();
        return Arrays.copyOfRange(buffer, 0, length);
    }

    public EzspFrameResponse getResponse() {
        return response;
    }

    public abstract boolean processResponse(EzspFrameResponse ezspResponse);

}
