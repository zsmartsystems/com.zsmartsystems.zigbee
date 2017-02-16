package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * IeeeAddressRequest.
 *
 * @author Tommi S.E. Laukkanen
 */
public class IeeeAddressRequest extends ZdoRequest {

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
        this.destinationAddress = networkAddress;
        this.type = type;
        this.startIndex = startIndex;
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
        return "IEEE Address Request: networkAddress=" + destinationAddress + ", type=" + type + ", startIndex="
                + startIndex;
    }
}
