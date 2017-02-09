package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoCommand;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * User Descriptor Configuration.
 *
 * @author Tommi S.E. Laukkanen
 */
public class UserDescriptorConfiguration extends ZdoCommand implements ZdoResponse {
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

    public UserDescriptorConfiguration() {
    }

    public UserDescriptorConfiguration(int sourceAddress, int networkAddress, int status) {
        this.sourceAddress = sourceAddress;
        this.networkAddress = networkAddress;
        this.status = status;
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

    @Override
    public String toString() {
        return "User Descriptor Configuration: sourceAddress=" + sourceAddress + ", networkAddress=" + networkAddress
                + ", status=" + status;
    }
}
