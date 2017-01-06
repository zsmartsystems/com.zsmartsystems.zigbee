package com.zsmartsystems.zigbee.dongle.ember.ash;

/**
 * ASH Frame Error
 * 
 * @author Chris Jackson
 *
 */
public class AshFrameAck extends AshFrame {
    /**
     * Constructor to create an ASH ACK frame.
     * 
     * @param buffer
     */
    public AshFrameAck(int ackNum) {
        this.frameType = FrameType.ACK;
        this.ackNum = ackNum;
    }

    public AshFrameAck(int[] frameBuffer) {
        this.frameType = FrameType.ACK;
        processHeader(frameBuffer);
    }

    @Override
    public String toString() {
        return "AshFrameAck [ackNum=" + ackNum + "]";
    }
}
