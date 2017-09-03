package com.zsmartsystems.zigbee.dongle.ember.ash;

/**
 * ASH Reset ACK Frame.
 * Informs the Host that the NCP has reset and the reason for the reset.
 *
 * @author Chris Jackson
 *
 */
public class AshFrameRstAck extends AshFrame {
    private final int version;
    private final int resetCode;
    private final AshErrorCode errorCode;

    /**
     * Constructor to create an ASH frame from a byte buffer.
     *
     * @param buffer
     */
    public AshFrameRstAck(int[] frameBuffer) {
        this.frameType = FrameType.RSTACK;

        this.version = frameBuffer[1];
        this.resetCode = frameBuffer[2];
        this.errorCode = AshErrorCode.getAshErrorCode(this.resetCode);
    }

    public int getVersion() {
        return version;
    }

    public int getResetCode() {
        return resetCode;
    }

    public AshErrorCode getResetType() {
        return errorCode;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("AshFrameRstAck [version=");
        builder.append(version);
        builder.append(". resetCode=");
        builder.append(resetCode);
        AshErrorCode ashError = AshErrorCode.getAshErrorCode(resetCode);
        builder.append(ashError.getDescription());
        builder.append("]");
        return builder.toString();
    }
}
