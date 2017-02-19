package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * User Descriptor Configuration.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class UserDescriptorConfiguration extends ZdoResponse {

    /**
     * Network address.
     */
    public int networkAddress;

    public UserDescriptorConfiguration() {
    }

    public UserDescriptorConfiguration(int sourceAddress, int networkAddress, int status) {
        this.sourceAddress = sourceAddress;
        this.networkAddress = networkAddress;
        this.status = status;
    }

    public int getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(int networkAddress) {
        this.networkAddress = networkAddress;
    }

    @Override
    public String toString() {
        return "User Descriptor Configuration: sourceAddress=" + sourceAddress + ", networkAddress=" + networkAddress
                + ", status=" + status;
    }
}
