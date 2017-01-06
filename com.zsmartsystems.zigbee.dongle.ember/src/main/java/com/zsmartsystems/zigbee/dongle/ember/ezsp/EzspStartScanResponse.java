package com.zsmartsystems.zigbee.dongle.ember.ezsp;

/**
 * This function will start a scan.
 * 
 * EMBER_SUCCESS signals that the scan successfully started. Possible error
 * responses and their meanings: EMBER_MAC_SCANNING, we are already scanning;
 * EMBER_MAC_JOINED_NETWORK, we are currently joined to a network and cannot
 * begin a scan; EMBER_MAC_BAD_SCAN_DURATION, we have set a duration value that
 * is not 0..14 inclusive; EMBER_MAC_INCORRECT_SCAN_TYPE, we have requested an
 * undefined scanning type; EMBER_MAC_INVALID_CHANNEL_MASK, our channel mask did
 * not specify any valid channels.
 *
 * @author Chris Jackson
 *
 */
public class EzspStartScanResponse extends EzspFrameResponse {
    /**
     * Creates an EZSP <i>startScan</i> frame
     * 
     * @param desiredProtocolVersion
     *            The EZSP version the Host wishes to use
     */
    public EzspStartScanResponse(int[] inputBuffer) {
        super(inputBuffer);
        emberStatus = inputEmberStatus();
    }

    @Override
    public String toString() {
        return "EzspStartScan RESPONSE [" + emberStatus + "]";
    }
}
