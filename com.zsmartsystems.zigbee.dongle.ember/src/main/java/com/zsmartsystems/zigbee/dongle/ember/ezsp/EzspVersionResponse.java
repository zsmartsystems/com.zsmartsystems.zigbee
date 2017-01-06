package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 * The command allows the Host to specify the desired EZSP version and must be
 * sent before any other command. The response provides information about the
 * firmware running on the NCP.
 *
 * @author Chris Jackson
 *
 */
public class EzspVersionResponse extends EzspFrameResponse {
    /**
     * The EZSP version the NCP is using.
     */
    private int protocolVersion;

    /**
     * The type of stack running on the NCP.
     */
    private int stackType;

    /**
     * The version number of the stack.
     */
    private int stackVersion;

    /**
     * Creates an EZSP <i>version</i> from a response frame
     * 
     * @param inputBuffer
     *            the received response data
     */
    public EzspVersionResponse(int[] inputBuffer) {
        super(inputBuffer);
        protocolVersion = inputUInt8();
        stackType = inputUInt8();
        stackVersion = inputUInt16();

        emberStatus = EmberStatus.EMBER_SUCCESS;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public int getStackType() {
        return stackType;
    }

    public void setStackType(int stackType) {
        this.stackType = stackType;
    }

    public int getStackVersion() {
        return stackVersion;
    }

    public void setStackVersion(int stackVersion) {
        this.stackVersion = stackVersion;
    }

    @Override
    public String toString() {
        return "EzspVersion RESPONSE [" + emberStatus + ": protocolVersion=" + protocolVersion + ", stackType="
                + stackType + ", stackVersion=" + stackVersion + "]";
    }
}
