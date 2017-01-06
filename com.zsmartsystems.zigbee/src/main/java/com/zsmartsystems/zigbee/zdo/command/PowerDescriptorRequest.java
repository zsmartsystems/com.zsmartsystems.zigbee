package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * 
 * @author Chris Jackson
 *
 */
public class PowerDescriptorRequest extends ZdoRequest {
    /**
     * Network address of interest.
     */
    private int networkAddressOfInterest;

    public PowerDescriptorRequest() {
    }

    public PowerDescriptorRequest(int destinationAddress, int networkAddressOfInterest) {
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
        return "Power Descriptor Request " + "destinationAddress=" + destinationAddress + ", networkAddressOfInterest="
                + networkAddressOfInterest;
    }
}
