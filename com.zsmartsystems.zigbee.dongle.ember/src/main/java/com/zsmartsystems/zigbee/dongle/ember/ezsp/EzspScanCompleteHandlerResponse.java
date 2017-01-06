package com.zsmartsystems.zigbee.dongle.ember.ezsp;

/**
 * Returns the status of the current scan of type EZSP_ENERGY_SCAN or
 * EZSP_ACTIVE_SCAN. EMBER_SUCCESS signals that the scan has completed. Other
 * error conditions signify a failure to scan on the channel specified.
 * 
 * @author Chris Jackson
 *
 */
public class EzspScanCompleteHandlerResponse extends EzspFrameResponse {

    /**
     * The channel on which the current error occurred. Undefined for the case
     * of EMBER_SUCCESS.
     */
    private int channel;

    /**
     * Creates an EZSP <i>invalidCommand</i> frame
     * 
     * @param customFrame
     *            The EZSP echo buffer
     */
    public EzspScanCompleteHandlerResponse(int[] inputBuffer) {
        super(inputBuffer);
        channel = inputUInt8();
        emberStatus = inputEmberStatus();
    }

    public int getChannel() {
        return channel;
    }

    @Override
    public String toString() {
        return "EzspScanCompleteHandler [" + emberStatus + ": channel=" + channel + "]";
    }
}
