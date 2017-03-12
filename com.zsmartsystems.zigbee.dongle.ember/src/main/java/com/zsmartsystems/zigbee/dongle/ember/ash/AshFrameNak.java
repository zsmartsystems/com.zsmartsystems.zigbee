package com.zsmartsystems.zigbee.dongle.ember.ash;

/**
 * ASH NAK Frame
 *
 * @author Chris Jackson
 *
 */
public class AshFrameNak extends AshFrame {
    /**
     * Constructor to create an ASH NAK frame.
     *
     * @param buffer
     */
    public AshFrameNak(int ackNum) {
        this.frameType = FrameType.NAK;
        this.ackNum = ackNum;
    }

    public AshFrameNak(int[] frameBuffer) {
        this.frameType = FrameType.NAK;
        processHeader(frameBuffer);
    }

    @Override
    public String toString() {
        return "AshFrameNak [ackNum=" + ackNum + "]";
    }
}
