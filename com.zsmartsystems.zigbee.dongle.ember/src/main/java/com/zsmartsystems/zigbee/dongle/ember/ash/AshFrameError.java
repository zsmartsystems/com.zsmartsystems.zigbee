package com.zsmartsystems.zigbee.dongle.ember.ash;

/**
 * ASH Frame Error
 * 
 * @author Chris Jackson
 *
 */
public class AshFrameError extends AshFrame {
    private final int version;
    private final int resetCode;

    /**
     * Constructor to create an ASH frame from a byte buffer.
     * 
     * @param buffer
     */
    public AshFrameError(int[] frameBuffer) {
        this.frameType = FrameType.ERROR;
        processHeader(frameBuffer);

        this.version = frameBuffer[1];
        this.resetCode = frameBuffer[2];
    }

    public int getVersion() {
        return version;
    }

    public int getResetCode() {
        return resetCode;
    }

    @Override
    public String toString() {
        return "AshFrameError [version=" + version + ", resetCode=" + resetCode + "]";
    }

}
