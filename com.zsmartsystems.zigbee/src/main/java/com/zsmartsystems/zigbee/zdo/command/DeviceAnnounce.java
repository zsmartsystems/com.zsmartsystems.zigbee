package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoCommand;

/**
 * DeviceAnnounce.
 * 
 * @author Tommi S.E. Laukkanen
 */
public class DeviceAnnounce extends ZdoCommand {
    /**
     * Source address.
     */
    private int sourceAddress;
    /**
     * Device IEEE address.
     */
    private long ieeeAddress;
    /**
     * Device network address.
     */
    private int networkAddress;
    /**
     * Device capabilities.
     */
    private int capabilities;

    public DeviceAnnounce() {
    }

    public DeviceAnnounce(int sourceAddress, long ieeeAddress, int networkAddress, int capabilities) {
        this.sourceAddress = sourceAddress;
        this.ieeeAddress = ieeeAddress;
        this.networkAddress = networkAddress;
        this.capabilities = capabilities;
    }

    public int getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(int sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public long getIeeeAddress() {
        return ieeeAddress;
    }

    public void setIeeeAddress(long ieeeAddress) {
        this.ieeeAddress = ieeeAddress;
    }

    public int getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(int networkAddress) {
        this.networkAddress = networkAddress;
    }

    public int getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(int capabilities) {
        this.capabilities = capabilities;
    }

    @Override
    public String toString() {
        return "Device Announce " + "sourceAddress=" + sourceAddress + ", ieeeAddress=" + ieeeAddress
                + ", networkAddress=" + networkAddress + ", capabilities=" + capabilities;
    }
}
