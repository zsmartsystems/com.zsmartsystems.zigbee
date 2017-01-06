package com.zsmartsystems.zigbee.dongle.ember.ash;

/**
 * ASH Frame Error
 * 
 * @author Chris Jackson
 *
 */
public class AshFrameRst extends AshFrame {
    /**
     * Constructor to create an ASH Reset frame.
     * 
     * @param buffer
     */
    public AshFrameRst() {
        this.frameType = FrameType.RST;
    }

    public AshFrameRst(int[] frameBuffer) {
        this();
    }

    public int[] getOutputBuffer() {
        int[] rstFrame = super.getOutputBuffer();

        int[] outFrame = new int[rstFrame.length + 1];
        outFrame[0] = 0x1A;
        for (int cnt = 0; cnt < rstFrame.length; cnt++) {
            outFrame[cnt + 1] = rstFrame[cnt];
        }

        return outFrame;
    }

    @Override
    public String toString() {
        return "AshFrameRst []";
    }
}
