package com.zsmartsystems.zigbee.zdo.descriptors;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 *
 * @author Chris Jackson
 *
 */
public class RoutingTable {

    private Integer destinationAddress;

    private DiscoveryState status;

    private boolean memoryConstrained;

    private boolean manyToOne;

    private boolean routeRecordRequired;

    private Integer nextHopAddress;

    public enum DiscoveryState {
        ACTIVE,
        DISCOVERY_UNDERWAY,
        DISCOVERY_FAILED,
        INACTIVE,
        VALIDATION_UNDERWAY
    }

    /**
     * Deserialise the contents of the structure.
     *
     * @param deserializer the {@link ZigBeeDeserializer} used to deserialize
     */
    public void deserialize(ZigBeeDeserializer deserializer) {
        // Deserialize the fields
        destinationAddress = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        int temp = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        switch (temp & 0x07) {
            case 0:
                status = DiscoveryState.ACTIVE;
                break;
            case 1:
                status = DiscoveryState.DISCOVERY_UNDERWAY;
                break;
            case 2:
                status = DiscoveryState.DISCOVERY_FAILED;
                break;
            case 3:
                status = DiscoveryState.INACTIVE;
                break;
            case 4:
                status = DiscoveryState.VALIDATION_UNDERWAY;
                break;
        }

        memoryConstrained = (temp & 0x08) != 0;
        manyToOne = (temp & 0x10) != 0;
        routeRecordRequired = (temp & 0x20) != 0;
        nextHopAddress = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    public Integer getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(Integer destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public DiscoveryState getStatus() {
        return status;
    }

    public void setStatus(DiscoveryState status) {
        this.status = status;
    }

    public boolean isMemoryConstrained() {
        return memoryConstrained;
    }

    public void setMemoryConstrained(boolean memoryConstrained) {
        this.memoryConstrained = memoryConstrained;
    }

    public boolean isManyToOne() {
        return manyToOne;
    }

    public void setManyToOne(boolean manyToOne) {
        this.manyToOne = manyToOne;
    }

    public boolean isRouteRecordRequired() {
        return routeRecordRequired;
    }

    public void setRouteRecordRequired(boolean routeRecordRequired) {
        this.routeRecordRequired = routeRecordRequired;
    }

    public Integer getNextHopAddress() {
        return nextHopAddress;
    }

    public void setNextHopAddress(Integer nextHopAddress) {
        this.nextHopAddress = nextHopAddress;
    }

    @Override
    public String toString() {
        return "RoutingTable [destinationAddress=" + destinationAddress + ", status=" + status + ", memoryConstrained="
                + memoryConstrained + ", manyToOne=" + manyToOne + ", routeRecordRequired=" + routeRecordRequired
                + ", nextHopAddress=" + nextHopAddress + "]";
    }

}
