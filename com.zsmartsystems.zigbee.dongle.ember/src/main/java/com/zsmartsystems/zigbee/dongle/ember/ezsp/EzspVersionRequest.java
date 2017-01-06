package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import java.util.Arrays;

/**
 * The command allows the Host to specify the desired EZSP version and must be
 * sent before any other command. The response provides information about the
 * firmware running on the NCP.
 *
 * @author Chris Jackson
 *
 */
public class EzspVersionRequest extends EzspFrameRequest {
    /**
     * The EZSP version the Host wishes to use.
     */
    private int desiredProtocolVersion;

    /**
     * Creates an EZSP <i>version</i> request frame
     * 
     */
    public EzspVersionRequest() {
        super(FRAME_ID_VERSION);
    }

    public int getDesiredProtocolVersion() {
        return desiredProtocolVersion;
    }

    public void setDesiredProtocolVersion(int desiredProtocolVersion) {
        this.desiredProtocolVersion = desiredProtocolVersion;
    }

    @Override
    public int[] getOutputBuffer() {
        initialiseOutputBuffer();
        outputUInt8(desiredProtocolVersion);
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

        response = ezspResponse;
        emberStatus = ezspResponse.getEmberStatus();

        return true;
    }

    @Override
    public String toString() {
        return "EzspVersion ["
                + emberStatus
                + ": desiredProtocolVersion="
                + desiredProtocolVersion
                + ((response != null) ? (", protocolVersion=" + ((EzspVersionResponse) response).getProtocolVersion()
                        + ", stackType=" + ((EzspVersionResponse) response).getStackType() + ", stackVersion=" + ((EzspVersionResponse) response)
                        .getStackVersion()) : "") + "]";
    }
}
