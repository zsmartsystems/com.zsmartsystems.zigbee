package com.zsmartsystems.zigbee.zdo.command;

import java.util.Arrays;

import com.zsmartsystems.zigbee.zdo.ZdoCommand;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * ManagementLqiResponse.
 *
 * @author Tommi S.E. Laukkanen
 */
public class ManagementLqiResponse extends ZdoCommand implements ZdoResponse {
    /**
     * The status.
     */
    private int status;
    /**
     * Network address.
     */
    private int sourceAddress;
    /**
     * Start index.
     */
    public int startIndex;
    /**
     * Number of neighbors.
     */
    public int numberOfNeighbors;
    /**
     * Neighbors.
     */
    public Neighbor[] neighbors;

    public ManagementLqiResponse() {
    }

    public ManagementLqiResponse(int status, int sourceAddress, int startIndex, int numberOfNeighbors,
            Neighbor[] associatedDeviceList) {
        this.status = status;
        this.sourceAddress = sourceAddress;
        this.startIndex = startIndex;
        this.numberOfNeighbors = numberOfNeighbors;
        this.neighbors = associatedDeviceList;
    }

    @Override
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(int sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getNumberOfNeighbors() {
        return numberOfNeighbors;
    }

    public void setNumberOfNeighbors(int numberOfNeighbors) {
        this.numberOfNeighbors = numberOfNeighbors;
    }

    public Neighbor[] getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Neighbor[] neighbors) {
        this.neighbors = neighbors;
    }

    @Override
    public String toString() {
        return "Management LQI Response: status=" + status + ", sourceAddress=" + sourceAddress + ", startIndex="
                + startIndex + ", numberOfNeighbors=" + numberOfNeighbors + ", neighbors=" + Arrays.toString(neighbors);
    }

    public static class Neighbor {
        public int depth;
        public long extendedPanId;
        public long ieeeAddress;
        public int networkAddress;
        public int relationshipRxOnWhenIdleDeviceType;
        public int permitJoining;
        public int lqi;

        public Neighbor(int depth, long extendedPanId, long ieeeAddress, int networkAddress,
                int relationshipRxOnWhenIdleDeviceType, int permitJoining, int lqi) {
            this.depth = depth;
            this.extendedPanId = extendedPanId;
            this.ieeeAddress = ieeeAddress;
            this.networkAddress = networkAddress;
            this.relationshipRxOnWhenIdleDeviceType = relationshipRxOnWhenIdleDeviceType;
            this.permitJoining = permitJoining;
            this.lqi = lqi;
        }

        public int getDepth() {
            return depth;
        }

        public long getExtendedPanId() {
            return extendedPanId;
        }

        public long getIeeeAddress() {
            return ieeeAddress;
        }

        public int getNetworkAddress() {
            return networkAddress;
        }

        public int getRelationshipRxOnWhenIdleDeviceType() {
            return relationshipRxOnWhenIdleDeviceType;
        }

        public int getPermitJoining() {
            return permitJoining;
        }

        public int getLqi() {
            return lqi;
        }
    }
}
