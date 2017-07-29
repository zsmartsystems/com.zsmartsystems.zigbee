package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeFrameResponse extends ConBeeFrame {
    protected ConBeeStatus status;

    public ConBeeFrameResponse(int[] response) {
        super(response);
    }

    /**
     * @return the state
     */
    public ConBeeStatus getStatus() {
        return status;
    }

}
