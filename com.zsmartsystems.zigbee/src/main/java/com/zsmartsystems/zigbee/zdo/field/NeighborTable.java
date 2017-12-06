/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.field;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.LogicalType;

/**
 * Class representing the ZigBee neighbor table
 *
 * @author Chris Jackson
 *
 */
public class NeighborTable {

    private ExtendedPanId extendedPanId;

    private IeeeAddress extendedAddress;

    private Integer networkAddress;

    private LogicalType deviceType;

    private NeighborTableRxState rxOnWhenIdle;

    private NeighborTableRelationship relationship;

    private NeighborTableJoining permitJoining;

    private Integer depth;

    private Integer lqi = 0;

    public enum NeighborTableRelationship {
        PARENT,
        CHILD,
        SIBLING,
        UNKNOWN,
        PREVIOUS_CHILD
    }

    public enum NeighborTableJoining {
        ENABLED,
        DISABLED,
        UNKNOWN
    }

    public enum NeighborTableRxState {
        RX_OFF,
        RX_ON,
        UNKNOWN
    }

    /**
     * Deserialise the contents of the structure.
     *
     * @param deserializer the {@link ZigBeeDeserializer} used to deserialize
     */
    public void deserialize(ZigBeeDeserializer deserializer) {
        // Deserialize the fields
        extendedPanId = (ExtendedPanId) deserializer.readZigBeeType(ZclDataType.EXTENDED_PANID);
        extendedAddress = (IeeeAddress) deserializer.readZigBeeType(ZclDataType.IEEE_ADDRESS);
        networkAddress = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);

        int temp = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        setDeviceType(temp & 0x03);
        setRxOnWhenIdle((temp & 0x0c) >> 2);
        setRelationship((temp & 0x70) >> 4);

        temp = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        setPermitJoining(temp & 0x03);
        depth = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        lqi = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    public ExtendedPanId getExtendedPanId() {
        return extendedPanId;
    }

    public IeeeAddress getExtendedAddress() {
        return extendedAddress;
    }

    public Integer getNetworkAddress() {
        return networkAddress;
    }

    public LogicalType getDeviceType() {
        return deviceType;
    }

    private void setDeviceType(Integer deviceType) {
        switch (deviceType) {
            case 0:
                this.deviceType = LogicalType.COORDINATOR;
                break;
            case 1:
                this.deviceType = LogicalType.ROUTER;
                break;
            case 2:
                this.deviceType = LogicalType.END_DEVICE;
                break;
            default:
                this.deviceType = LogicalType.UNKNOWN;
                break;

        }
    }

    public NeighborTableRxState getRxOnWhenIdle() {
        return rxOnWhenIdle;
    }

    public void setRxOnWhenIdle(Integer rxOnWhenIdle) {
        switch (rxOnWhenIdle) {
            case 0:
                this.rxOnWhenIdle = NeighborTableRxState.RX_OFF;
                break;
            case 1:
                this.rxOnWhenIdle = NeighborTableRxState.RX_ON;
                break;
            default:
                this.rxOnWhenIdle = NeighborTableRxState.UNKNOWN;
                break;

        }
    }

    public NeighborTableRelationship getRelationship() {
        return relationship;
    }

    private void setRelationship(Integer relationship) {
        switch (relationship) {
            case 0:
                this.relationship = NeighborTableRelationship.PARENT;
                break;
            case 1:
                this.relationship = NeighborTableRelationship.CHILD;
                break;
            case 2:
                this.relationship = NeighborTableRelationship.SIBLING;
                break;
            case 3:
                this.relationship = NeighborTableRelationship.UNKNOWN;
                break;
            case 4:
                this.relationship = NeighborTableRelationship.PREVIOUS_CHILD;
                break;
            default:
                this.relationship = NeighborTableRelationship.UNKNOWN;
                break;
        }
    }

    public NeighborTableJoining getPermitJoining() {
        return permitJoining;
    }

    private void setPermitJoining(Integer permitJoining) {
        switch (permitJoining) {
            case 0:
                this.permitJoining = NeighborTableJoining.DISABLED;
                break;
            case 1:
                this.permitJoining = NeighborTableJoining.ENABLED;
                break;
            default:
                this.permitJoining = NeighborTableJoining.UNKNOWN;
                break;
        }
    }

    public Integer getDepth() {
        return depth;
    }

    public Integer getLqi() {
        return lqi;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((depth == null) ? 0 : depth.hashCode());
        result = prime * result + ((deviceType == null) ? 0 : deviceType.hashCode());
        result = prime * result + ((extendedAddress == null) ? 0 : extendedAddress.hashCode());
        result = prime * result + ((extendedPanId == null) ? 0 : extendedPanId.hashCode());
        result = prime * result + ((lqi == null) ? 0 : lqi.hashCode());
        result = prime * result + ((networkAddress == null) ? 0 : networkAddress.hashCode());
        result = prime * result + ((permitJoining == null) ? 0 : permitJoining.hashCode());
        result = prime * result + ((relationship == null) ? 0 : relationship.hashCode());
        result = prime * result + ((rxOnWhenIdle == null) ? 0 : rxOnWhenIdle.hashCode());
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
        NeighborTable other = (NeighborTable) obj;
        if (depth == null) {
            if (other.depth != null) {
                return false;
            }
        } else if (!depth.equals(other.depth)) {
            return false;
        }
        if (deviceType != other.deviceType) {
            return false;
        }
        if (extendedAddress == null) {
            if (other.extendedAddress != null) {
                return false;
            }
        } else if (!extendedAddress.equals(other.extendedAddress)) {
            return false;
        }
        if (extendedPanId == null) {
            if (other.extendedPanId != null) {
                return false;
            }
        } else if (!extendedPanId.equals(other.extendedPanId)) {
            return false;
        }
        if (lqi == null) {
            if (other.lqi != null) {
                return false;
            }
        } else if (!lqi.equals(other.lqi)) {
            return false;
        }
        if (networkAddress == null) {
            if (other.networkAddress != null) {
                return false;
            }
        } else if (!networkAddress.equals(other.networkAddress)) {
            return false;
        }
        if (permitJoining != other.permitJoining) {
            return false;
        }
        if (relationship != other.relationship) {
            return false;
        }
        if (rxOnWhenIdle != other.rxOnWhenIdle) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NeighborTable [extendedPanId=" + extendedPanId + ", extendedAddress=" + extendedAddress
                + ", networkAddress=" + networkAddress + ", deviceType=" + deviceType + ", rxOnWhenIdle=" + rxOnWhenIdle
                + ", relationship=" + relationship + ", permitJoining=" + permitJoining + ", depth=" + depth + ", lqi="
                + lqi + "]";
    }

}
