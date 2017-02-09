package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * ManagementLqiRequest.
 *
 * @author Tommi S.E. Laukkanen
 */
public class ManagementLqiRequest extends ZdoRequest {
    /**
     * The started index.
     */
    private int startIndex;

    public ManagementLqiRequest() {
    }

    public ManagementLqiRequest(int networkAddress, int type, int startIndex) {
        this.destinationAddress = networkAddress;
        this.startIndex = startIndex;
    }

    public int getNetworkAddress() {
        return destinationAddress;
    }

    public void setNetworkAddress(int networkAddress) {
        this.destinationAddress = networkAddress;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    @Override
    public String toString() {
        return "Management LQI Request: networkAddress=" + destinationAddress + ", startIndex=" + startIndex;
    }

}
