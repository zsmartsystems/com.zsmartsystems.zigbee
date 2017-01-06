package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberZigbeeNetwork;

/**
 * Reports the result of an energy scan for a single channel. The scan is not
 * complete until the scanCompleteHandler callback is called.
 * 
 * @author Chris Jackson
 *
 */
public class EzspNetworkFoundHandlerResponse extends EzspFrameResponse {

    /**
     * The parameters associated with the network found.
     */
    private EmberZigbeeNetwork networkFound;

    /**
     * The link quality from the node that generated this beacon.
     */
    private int lastHopLqi;

    /**
     * The energy level (in units of dBm) observed during the reception.
     */
    private int lastHopRssi;

    /**
     * Creates an EZSP <i>networkFoundHandler</i> frame
     * 
     * @param customFrame
     *            The EZSP echo buffer
     */
    public EzspNetworkFoundHandlerResponse(int[] inputBuffer) {
        super(inputBuffer);

        networkFound = (EmberZigbeeNetwork) inputStructure(EmberZigbeeNetwork.class);
        lastHopLqi = inputUInt8();
        lastHopRssi = inputInt8s();
    }

    /**
     * @return the networkFound
     */
    public EmberZigbeeNetwork getNetworkFound() {
        return networkFound;
    }

    /**
     * @param networkFound the networkFound to set
     */
    public void setNetworkFound(EmberZigbeeNetwork networkFound) {
        this.networkFound = networkFound;
    }

    /**
     * @return the lastHopLqi
     */
    public int getLastHopLqi() {
        return lastHopLqi;
    }

    /**
     * @param lastHopLqi the lastHopLqi to set
     */
    public void setLastHopLqi(int lastHopLqi) {
        this.lastHopLqi = lastHopLqi;
    }

    /**
     * @return the lastHopRssi
     */
    public int getLastHopRssi() {
        return lastHopRssi;
    }

    /**
     * @param lastHopRssi the lastHopRssi to set
     */
    public void setLastHopRssi(int lastHopRssi) {
        this.lastHopRssi = lastHopRssi;
    }

    @Override
    public String toString() {
        return "EzspNetworkFoundHandlerResponse [networkFound=" + networkFound + ", lastHopLqi=" + lastHopLqi
                + ", lastHopRssi=" + lastHopRssi + "]";
    }

}
