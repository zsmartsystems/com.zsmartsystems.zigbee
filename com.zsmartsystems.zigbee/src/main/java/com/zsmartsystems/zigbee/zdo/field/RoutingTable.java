/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.field;

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
        UNKNOWN,
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
            default:
                status = DiscoveryState.UNKNOWN;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((destinationAddress == null) ? 0 : destinationAddress.hashCode());
        result = prime * result + (manyToOne ? 1231 : 1237);
        result = prime * result + (memoryConstrained ? 1231 : 1237);
        result = prime * result + ((nextHopAddress == null) ? 0 : nextHopAddress.hashCode());
        result = prime * result + (routeRecordRequired ? 1231 : 1237);
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RoutingTable other = (RoutingTable) obj;
        if (destinationAddress == null) {
            if (other.destinationAddress != null) {
                return false;
            }
        } else if (!destinationAddress.equals(other.destinationAddress)) {
            return false;
        }
        if (manyToOne != other.manyToOne) {
            return false;
        }
        if (memoryConstrained != other.memoryConstrained) {
            return false;
        }
        if (nextHopAddress == null) {
            if (other.nextHopAddress != null) {
                return false;
            }
        } else if (!nextHopAddress.equals(other.nextHopAddress)) {
            return false;
        }
        if (routeRecordRequired != other.routeRecordRequired) {
            return false;
        }
        if (status != other.status) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RoutingTable [destinationAddress=" + destinationAddress + ", status=" + status + ", memoryConstrained="
                + memoryConstrained + ", manyToOne=" + manyToOne + ", routeRecordRequired=" + routeRecordRequired
                + ", nextHopAddress=" + nextHopAddress + "]";
    }

}
