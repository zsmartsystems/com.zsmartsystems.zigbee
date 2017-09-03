/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.descriptors;

import java.util.Objects;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor.LogicalType;

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
        return Objects.hash(extendedAddress, networkAddress, lqi);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!NeighborTable.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final NeighborTable other = (NeighborTable) obj;
        return (getExtendedAddress().equals(other.getExtendedAddress())
                && getNetworkAddress().equals(other.getNetworkAddress()) && getLqi().equals(other.getLqi())) ? true
                        : false;
    }

    @Override
    public String toString() {
        return "NeighborTable [extendedPanId=" + extendedPanId + ", extendedAddress=" + extendedAddress
                + ", networkAddress=" + networkAddress + ", deviceType=" + deviceType + ", rxOnWhenIdle=" + rxOnWhenIdle
                + ", relationship=" + relationship + ", permitJoining=" + permitJoining + ", depth=" + depth + ", lqi="
                + lqi + "]";
    }

}
