package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * IeeeAddressRequest.
 *
 * @author Tommi S.E. Laukkanen
 */
public class IeeeAddressRequest extends ZdoRequest {
    /**
     * The network address.
     */
    private int networkAddress;
    /**
     * The type.
     */
    private int type;
    /**
     * The started index.
     */
    private int startIndex;

    public IeeeAddressRequest() {
    }

    public IeeeAddressRequest(int networkAddress, int type, int startIndex) {
        this.networkAddress = networkAddress;
        this.type = type;
        this.startIndex = startIndex;
    }

    public int getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(int networkAddress) {
        this.networkAddress = networkAddress;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    @Override
    public String toString() {
        return "IEEE Address Request: networkAddress=" + networkAddress + ", type=" + type + ", startIndex="
                + startIndex;
    }
}
