package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * @author Tommi S.E. Laukkanen
 */
public class ActiveEndpointsRequest extends ZdoRequest {
    /**
     * Network address of interest.
     */
    private int networkAddressOfInterest;

    public ActiveEndpointsRequest() {
    }

    public ActiveEndpointsRequest(int destinationAddress, int networkAddressOfInterest) {
        this.destinationAddress = destinationAddress;
        this.networkAddressOfInterest = networkAddressOfInterest;
    }

    public int getNetworkAddressOfInterest() {
        return networkAddressOfInterest;
    }

    public void setNetworkAddressOfInterest(int networkAddressOfInterest) {
        this.networkAddressOfInterest = networkAddressOfInterest;
    }

    @Override
    public String toString() {
        return "Active Endpoints Request " + "destinationAddress=" + destinationAddress + ", networkAddressOfInterest="
                + networkAddressOfInterest;
    }
}
