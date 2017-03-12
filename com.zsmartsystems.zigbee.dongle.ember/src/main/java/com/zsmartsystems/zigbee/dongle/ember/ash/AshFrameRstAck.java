package com.zsmartsystems.zigbee.dongle.ember.ash;

/**
 * ASH Reset ACK Frame
 *
 * @author Chris Jackson
 *
 */
public class AshFrameRstAck extends AshFrame {
    private final int version;
    private final int resetCode;
    private final ErrorCode errorCode;

    /**
     * Constructor to create an ASH frame from a byte buffer.
     *
     * @param buffer
     */
    public AshFrameRstAck(int[] frameBuffer) {
        this.frameType = FrameType.RSTACK;

        this.version = frameBuffer[1];
        this.resetCode = frameBuffer[2];
        this.errorCode = ErrorCode.getErrorCode(this.resetCode);
    }

    public int getVersion() {
        return version;
    }

    public int getResetCode() {
        return resetCode;
    }

    public ErrorCode getResetType() {
        return errorCode;
    }

    @Override
    public String toString() {
        return "AshFrameRstAck [version=" + version + ", resetCode=" + resetCode + " (" + errorCode + ")]";
    }
}
