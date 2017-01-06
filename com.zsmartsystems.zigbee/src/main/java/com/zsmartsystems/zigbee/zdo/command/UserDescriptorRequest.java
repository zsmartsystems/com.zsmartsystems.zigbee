package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * UserDescriptorRequest.
 * 
 * @author Tommi S.E. Laukkanen
 */
public class UserDescriptorRequest extends ZdoRequest {
    /**
     * Network address of interest.
     */
    private int networkAddressOfInterest;

    public UserDescriptorRequest() {
    }

    public UserDescriptorRequest(int destinationAddress, int networkAddressOfInterest) {
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
        return "User Descriptor Request " + "destinationAddress=" + destinationAddress + ", networkAddressOfInterest="
                + networkAddressOfInterest;
    }
}
