package com.zsmartsystems.zigbee.dongle.ember.ezsp;

/**
 * Reports the result of an energy scan for a single channel. The scan is not
 * complete until the scanCompleteHandler callback is called.
 * 
 * @author Chris Jackson
 *
 */
public class EzspEnergyScanResultHandlerResponse extends EzspFrameResponse {

    /**
     * The 802.15.4 channel number that was scanned.
     */
    private int channel;

    /**
     * The maximum RSSI value found on the channel.
     */
    private int maxRssiValue;

    /**
     * Creates an EZSP <i>energyScanResultHandler</i> frame
     * 
     * @param customFrame
     *            The EZSP echo buffer
     */
    public EzspEnergyScanResultHandlerResponse(int[] inputBuffer) {
        super(inputBuffer);

        channel = inputUInt8();
        maxRssiValue = inputInt8s();
    }

    public int getChannel() {
        return channel;
    }

    public int getMaxRssiValue() {
        return maxRssiValue;
    }

    @Override
    public String toString() {
        return "EzspEnergyScanResultHandler [channel=" + channel + ", maxRssiValue=" + maxRssiValue + "]";
    }
}
