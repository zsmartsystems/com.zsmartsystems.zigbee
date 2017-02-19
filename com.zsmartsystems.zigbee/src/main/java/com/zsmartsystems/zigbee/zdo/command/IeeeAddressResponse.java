package com.zsmartsystems.zigbee.zdo.command;

import java.util.Arrays;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Returns the IEEE address of a node, given a 16-bit short address.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class IeeeAddressResponse extends ZdoResponse {
    /**
     * The source address mode.
     */
    private int sourceAddressMode;
    /**
     * IEEE address.
     */
    private IeeeAddress ieeeAddress;
    /**
     * Start index.
     */
    public int startIndex;
    /**
     * Number of associated devices.
     */
    public int numberOfAssociatedDevices;
    /**
     * Associated device list.
     */
    public int[] associatedDeviceList;

    public IeeeAddressResponse() {
    }

    public IeeeAddressResponse(int status, int sourceAddressMode, IeeeAddress ieeeAddress, int networkAddress,
            int startIndex, int numberOfAssociatedDevices, int[] associatedDeviceList) {
        this.status = status;
        this.sourceAddressMode = sourceAddressMode;
        this.ieeeAddress = ieeeAddress;
        this.sourceAddress = networkAddress;
        this.startIndex = startIndex;
        this.numberOfAssociatedDevices = numberOfAssociatedDevices;
        this.associatedDeviceList = associatedDeviceList;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSourceAddressMode() {
        return sourceAddressMode;
    }

    public void setSourceAddressMode(int sourceAddressMode) {
        this.sourceAddressMode = sourceAddressMode;
    }

    public IeeeAddress getIeeeAddress() {
        return ieeeAddress;
    }

    public void setIeeeAddress(IeeeAddress ieeeAddress) {
        this.ieeeAddress = ieeeAddress;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getNumberOfAssociatedDevices() {
        return numberOfAssociatedDevices;
    }

    public void setNumberOfAssociatedDevices(int numberOfAssociatedDevices) {
        this.numberOfAssociatedDevices = numberOfAssociatedDevices;
    }

    public int[] getAssociatedDeviceList() {
        return associatedDeviceList;
    }

    public void setAssociatedDeviceList(int[] associatedDeviceList) {
        this.associatedDeviceList = associatedDeviceList;
    }

    @Override
    public String toString() {
        return "IEEE Address Response: status=" + status + ", sourceAddressMode=" + sourceAddressMode + ", ieeeAddress="
                + ieeeAddress + ", networkAddress=" + sourceAddress + ", startIndex=" + startIndex
                + ", numberOfAssociatedDevices=" + numberOfAssociatedDevices + ", associatedDeviceList="
                + Arrays.toString(associatedDeviceList);
    }
}
