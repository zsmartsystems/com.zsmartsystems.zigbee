package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * NodeDescriptorRequest.
 *
 * @author Tommi S.E. Laukkanen
 */
public class NodeDescriptorRequest extends ZdoRequest {
    /**
     * Network address of interest.
     */
    private int networkAddressOfInterest;

    public NodeDescriptorRequest() {
    }

    public NodeDescriptorRequest(int destinationAddress, int networkAddressOfInterest) {
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
        return "Node Descriptor Request: destinationAddress=" + destinationAddress + ", networkAddressOfInterest="
                + networkAddressOfInterest;
    }
}
