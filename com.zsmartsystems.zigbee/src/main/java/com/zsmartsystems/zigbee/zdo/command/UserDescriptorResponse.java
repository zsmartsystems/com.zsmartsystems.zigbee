package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoCommand;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * @author Tommi S.E. Laukkanen
 */
public class UserDescriptorResponse extends ZdoCommand implements ZdoResponse {
    /**
     * Source address.
     */
    public int sourceAddress;
    /**
     * Network address.
     */
    public int networkAddress;
    /**
     * Status.
     */
    public int status;
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

    @Override
    public int getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(int sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public int getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(int networkAddress) {
        this.networkAddress = networkAddress;
    }

    @Override
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
