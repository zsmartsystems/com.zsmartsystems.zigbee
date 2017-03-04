package com.zsmartsystems.zigbee.zdo.descriptors;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 *
 * @author Chris Jackson
 *
 */
public class NeighborTable {

    private Long extendedPanId;

    private Integer extendedAddress;

    private Integer networkAddress;

    private Integer deviceType;

    private Integer rxOnWhenIdle;

    private Integer relationship;

    private Integer permitJoining;

    private Integer depth;

    private Integer lqi;

    /**
     * Deserialise the contents of the structure.
     *
     * @param deserializer the {@link ZigBeeDeserializer} used to deserialize
     */
    public void deserialize(ZigBeeDeserializer deserializer) {
        // Deserialize the fields
        extendedPanId = ((IeeeAddress) deserializer.readZigBeeType(ZclDataType.IEEE_ADDRESS)).getLong();
        extendedAddress = (int) deserializer.readZigBeeType(ZclDataType.IEEE_ADDRESS);
        networkAddress = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);

        int temp = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        deviceType = temp & 0x03;
        rxOnWhenIdle = (temp & 0x0c) >> 2;
        relationship = (temp & 0x70) >> 4;

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

    public Integer getExtendedAddress() {
        return extendedAddress;
    }

    public void setExtendedAddress(Integer extendedAddress) {
        this.extendedAddress = extendedAddress;
    }

    public Integer getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(Integer networkAddress) {
        this.networkAddress = networkAddress;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getRxOnWhenIdle() {
        return rxOnWhenIdle;
    }

    public void setRxOnWhenIdle(Integer rxOnWhenIdle) {
        this.rxOnWhenIdle = rxOnWhenIdle;
    }

    public Integer getRelationship() {
        return relationship;
    }

    public void setRelationship(Integer relationship) {
        this.relationship = relationship;
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
    public String toString() {
        return "NeighborTable [extendedPanId=" + extendedPanId + ", extendedAddress=" + extendedAddress
                + ", networkAddress=" + networkAddress + ", deviceType=" + deviceType + ", rxOnWhenIdle=" + rxOnWhenIdle
                + ", relationship=" + relationship + ", permitJoining=" + permitJoining + ", depth=" + depth + ", lqi="
                + lqi + "]";
    }

}
