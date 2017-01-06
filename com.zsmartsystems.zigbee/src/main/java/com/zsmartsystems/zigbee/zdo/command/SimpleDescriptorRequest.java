package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * SimpleDescriptorRequest
 * 
 * @author Tommi S.E. Laukkanen
 */
public class SimpleDescriptorRequest extends ZdoRequest {
    /**
     * Endpoint.
     */
    private int endpoint;

    public SimpleDescriptorRequest() {
    }

    public SimpleDescriptorRequest(int destinationAddress, int endpoint) {
        this.destinationAddress = destinationAddress;
        this.endpoint = endpoint;
    }

    public int getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(int endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String toString() {
        return "Simple Descriptor Request " + "destinationAddress=" + destinationAddress + ", endpoint=" + endpoint;
    }
}
