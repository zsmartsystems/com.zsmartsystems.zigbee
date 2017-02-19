package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class UserDescriptorResponse extends ZdoResponse {
    /**
     * Network address.
     */
    public int networkAddress;
    /**
     * Descriptor
     */
    private String descriptor;

    public UserDescriptorResponse() {
    }

    public UserDescriptorResponse(int sourceAddress, int networkAddress, int status, String descriptor) {
        this.sourceAddress = sourceAddress;
        this.networkAddress = networkAddress;
        this.status = status;
        this.descriptor = descriptor;
    }

    public int getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(int networkAddress) {
        this.networkAddress = networkAddress;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public String toString() {
        return "Active Endpoints Response: sourceAddress=" + sourceAddress + ", networkAddress=" + networkAddress
                + ", status=" + status + ", descriptor='" + descriptor + "'";
    }
}
