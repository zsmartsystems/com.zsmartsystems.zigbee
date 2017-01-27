package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspNetworkScanType;

/**
 * This function will start a scan and process the response handlers until the scan is complete.
 * It will build a list of all responses
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
public class EzspStartScanRequest extends EzspFrameRequest {
    private List<EzspFrameResponse> responseList = new ArrayList<EzspFrameResponse>();

    /**
     * Indicates the type of scan to be performed. Possible values are:
     * EZSP_ENERGY_SCAN and EZSP_ACTIVE_SCAN. For each type, the respective
     * callback for reporting results is: energyScanResultHandler and
     * networkFoundHandler. The energy scan and active scan report errors and
     * completion via the scanCompleteHandler.
     */
    private EzspNetworkScanType scanType;

    /**
     * Bits set as 1 indicate that this particular channel should be scanned.
     * Bits set to 0 indicate that this particular channel should not be
     * scanned. For example, a channelMask value of 0x00000001 would indicate
     * that only channel 0 should be scanned. Valid channels range from 11 to 26
     * inclusive. This translates to a channel mask value of 0x07FFF800. As a
     * convenience, a value of 0 is reinterpreted as the mask for the current
     * channel.
     */
    private int channelMask;

    /**
     * Sets the exponent of the number of scan periods, where a scan period is
     * 960 symbols. The scan will occur for ((2^duration) + 1) scan periods.
     */
    private int duration;

    /**
     * Creates an EZSP <i>startScan</i> frame
     *
     * @param desiredProtocolVersion
     *            The EZSP version the Host wishes to use
     */
    public EzspStartScanRequest() {
        super(FRAME_ID_START_SCAN);
    }

    @Override
    public int[] getOutputBuffer() {
        initialiseOutputBuffer();
        outputUInt8(scanType.getKey());
        outputUInt32(channelMask);
        outputUInt8(duration);
        return Arrays.copyOfRange(buffer, 0, length);
    }

    @Override
    public boolean processResponse(EzspFrameResponse ezspResponse) {
        // Check the Frame ID
        switch (ezspResponse.getFrameId()) {
            case FRAME_ID_START_SCAN:
                // Initial response to our START_SCAN request
                // Only check the sequence number for the initial response
                if (getSequenceNumber() != ezspResponse.getSequenceNumber()) {
                    return false;
                }
                emberStatus = ezspResponse.getEmberStatus();
                if (emberStatus != EmberStatus.EMBER_SUCCESS) {
                    return true;
                }
                emberStatus = EmberStatus.EMBER_IN_PROGRESS;
                return false;
            case FRAME_ID_NETWORK_FOUND_HANDLER:
            case FRAME_ID_ENERGY_SCAN_RESULT_HANDLER:
                synchronized (responseList) {
                    responseList.add(ezspResponse);
                }
                return false;
            case FRAME_ID_SCAN_COMPLETE_HANDLER:
                // And finally the SCAN complete handler is sent
                emberStatus = ezspResponse.getEmberStatus();
                return true;
            default:
                return false;
        }
    }

    public void setScanType(EzspNetworkScanType scanType) {
        this.scanType = scanType;
    }

    public EzspNetworkScanType getScanType() {
        return scanType;
    }

    public void setChannelMask(int channelMask) {
        this.channelMask = channelMask;
    }

    public int getChannelMask() {
        return channelMask;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public EzspFrameResponse getResponse() {
        return responseList.get(0);
    }

    public int getQuietestChannel() {
        int channel = -1;
        int rssi = Integer.MAX_VALUE;

        synchronized (responseList) {
            for (EzspFrameResponse response : responseList) {
                if (response instanceof EzspEnergyScanResultHandlerResponse) {
                    EzspEnergyScanResultHandlerResponse energyResponse = (EzspEnergyScanResultHandlerResponse) response;
                    if (energyResponse.getMaxRssiValue() < rssi) {
                        rssi = energyResponse.getMaxRssiValue();
                        channel = energyResponse.getChannel();
                    }
                }
            }
            return channel;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("EzspStartScan [");
        result.append(emberStatus);
        result.append(": scanType=");
        result.append(scanType);
        result.append(", channelMask=");
        result.append(channelMask);
        result.append(", duration=");
        result.append(duration);
        result.append(". ");

        result.append(responseList.size());
        result.append(" results");

        result.append("]");
        return result.toString();
    }
}
