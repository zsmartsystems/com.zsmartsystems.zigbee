package com.zsmartsystems.zigbee.zdo.command;

import java.util.Arrays;

import com.zsmartsystems.zigbee.zdo.ZdoCommand;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * @author Tommi S.E. Laukkanen
 */
public class ActiveEndpointsResponse extends ZdoCommand implements ZdoResponse {
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
     * Active endpoints.
     */
    private int[] activeEndpoints;

    public ActiveEndpointsResponse() {
    }

    public ActiveEndpointsResponse(int sourceAddress, int networkAddress, int status, int[] activeEndpoints) {
        this.sourceAddress = sourceAddress;
        this.networkAddress = networkAddress;
        this.status = status;
        this.activeEndpoints = activeEndpoints;
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

    public int[] getActiveEndpoints() {
        return activeEndpoints;
    }

    public void setActiveEndpoints(int[] activeEndpoints) {
        this.activeEndpoints = activeEndpoints;
    }

    @Override
    public String toString() {
        return "Active Endpoints Response " + "sourceAddress=" + sourceAddress + ", networkAddress=" + networkAddress
                + ", status=" + status + ", activeEndpoints=" + Arrays.toString(activeEndpoints);
    }
}
