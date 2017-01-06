package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import java.util.Arrays;

/**
 * Tells the stack to allow other nodes to join the network with this node as
 * their parent. Joining is initially disabled by default.
 *
 * @author Chris Jackson
 *
 */
public class EzspPermitJoiningRequest extends EzspFrameRequest {
    /**
     * A value of 0x00 disables joining. A value of 0xFF enables joining. Any
     * other value enables joining for that number of seconds.
     */
    private int duration;

    /**
     * Creates an EZSP <i>version</i> request frame
     * 
     */
    public EzspPermitJoiningRequest() {
        super(FRAME_ID_PERMIT_JOINING);
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public int[] getOutputBuffer() {
        initialiseOutputBuffer();
        outputUInt8(duration);
        return Arrays.copyOfRange(buffer, 0, length);
    }

    @Override
    public boolean processResponse(EzspFrameResponse ezspResponse) {
        if (getSequenceNumber() != ezspResponse.getSequenceNumber()) {
            return false;
        }
        if (getFrameId() != ezspResponse.getFrameId()) {
            return false;
        }

        emberStatus = ezspResponse.getEmberStatus();

        return true;
    }

    @Override
    public String toString() {
        return "EzspPermitJoining [" + emberStatus + ": duration=" + duration + "]";
    }
}
