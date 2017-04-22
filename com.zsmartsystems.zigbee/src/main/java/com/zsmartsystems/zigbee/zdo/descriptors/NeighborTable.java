package com.zsmartsystems.zigbee.zdo.descriptors;

import java.util.Objects;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor.LogicalType;

/**
 *
 * @author Chris Jackson
 *
 */
public class NeighborTable {

    private Long extendedPanId;

    private IeeeAddress extendedAddress;

    private Integer networkAddress;

    private LogicalType deviceType;

    private Integer rxOnWhenIdle;

    private NeighborRelationship relationship;

    private Integer permitJoining;

    private Integer depth;

    private Integer lqi = 0;

    public enum NeighborRelationship {
        PARENT,
        CHILD,
        SIBLING,
        UNKNOWN,
        PREVIOUS_CHILD
    }

    /**
     * Deserialise the contents of the structure.
     *
     * @param deserializer the {@link ZigBeeDeserializer} used to deserialize
     */
    public void deserialize(ZigBeeDeserializer deserializer) {
        // Deserialize the fields
        extendedPanId = ((IeeeAddress) deserializer.readZigBeeType(ZclDataType.IEEE_ADDRESS)).getLong();
        extendedAddress = (IeeeAddress) deserializer.readZigBeeType(ZclDataType.IEEE_ADDRESS);
        networkAddress = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);

        int temp = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        setDeviceType(temp & 0x03);
        rxOnWhenIdle = (temp & 0x0c) >> 2;
        setRelationship((temp & 0x70) >> 4);

        temp = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        permitJoining = (temp & 0x03);
        depth = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        lqi = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    public Long getExtendedPanId() {
        return extendedPanId;
    }

    public void setExtendedPanId(Long extendedPanId) {
        this.extendedPanId = extendedPanId;
    }

    public IeeeAddress getExtendedAddress() {
        return extendedAddress;
    }

    public void setExtendedAddress(IeeeAddress extendedAddress) {
        this.extendedAddress = extendedAddress;
    }

    public Integer getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(Integer networkAddress) {
        this.networkAddress = networkAddress;
    }

    public LogicalType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
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

    public Integer getRxOnWhenIdle() {
        return rxOnWhenIdle;
    }

    public void setRxOnWhenIdle(Integer rxOnWhenIdle) {
        this.rxOnWhenIdle = rxOnWhenIdle;
    }

    public NeighborRelationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Integer relationship) {
        switch (relationship) {
            case 0:
                this.relationship = NeighborRelationship.PARENT;
                break;
            case 1:
                this.relationship = NeighborRelationship.CHILD;
                break;
            case 2:
                this.relationship = NeighborRelationship.SIBLING;
                break;
            case 3:
                this.relationship = NeighborRelationship.UNKNOWN;
                break;
            case 4:
                this.relationship = NeighborRelationship.PREVIOUS_CHILD;
                break;
            default:
                this.relationship = NeighborRelationship.UNKNOWN;
                break;
        }
    }

    public Integer getPermitJoining() {
        return permitJoining;
    }

    public void setPermitJoining(Integer permitJoining) {
        this.permitJoining = permitJoining;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Integer getLqi() {
        return lqi;
    }

    public void setLqi(Integer lqi) {
        this.lqi = lqi;
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
        return "NeighborTable [extendedPanId=" + String.format("%08X", extendedPanId) + ", extendedAddress="
                + extendedAddress + ", networkAddress=" + networkAddress + ", deviceType=" + deviceType
                + ", rxOnWhenIdle=" + rxOnWhenIdle + ", relationship=" + relationship + ", permitJoining="
                + permitJoining + ", depth=" + depth + ", lqi=" + lqi + "]";
    }

}
