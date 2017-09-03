package com.zsmartsystems.zigbee.dongle.ember.ash;

/**
 * ASH Error Frame
 *
 * @author Chris Jackson
 *
 */
public class AshFrameError extends AshFrame {
    private final int version;
    private final int errorCode;

    /**
     * Constructor to create an ASH frame from a byte buffer.
     *
     * @param buffer
     */
    public AshFrameError(int[] frameBuffer) {
        this.frameType = FrameType.ERROR;
        processHeader(frameBuffer);

        this.version = frameBuffer[1];
        this.errorCode = frameBuffer[2];
    }

    public int getVersion() {
        return version;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("AshFrameError [version=");
        builder.append(version);
        builder.append(". errorCode=");
        builder.append(errorCode);
        AshErrorCode ashError = AshErrorCode.getAshErrorCode(errorCode);
        builder.append(ashError.getDescription());
        builder.append("]");
        return builder.toString();
    }

}
