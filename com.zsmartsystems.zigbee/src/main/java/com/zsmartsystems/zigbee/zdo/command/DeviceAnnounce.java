package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Device Announce value object class.
 * <p>
 * The Device_annce is provided to enable ZigBee devices on the network to notify
 * other ZigBee devices that the device has joined or re-joined the network,
 * identifying the device's 64-bit IEEE address and new 16-bit NWK address, and
 * informing the Remote Devices of the capability of the ZigBee device. This
 * command shall be invoked for all ZigBee end devices upon join or rejoin. This
 * command may also be invoked by ZigBee routers upon join or rejoin as part of
 * NWK address conflict resolution. The destination addressing on this primitive is
 * broadcast to all devices for which macRxOnWhenIdle = TRUE.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class DeviceAnnounce extends ZdoResponse {
    /**
     * NWKAddrOfInterest command message field.
     */
    private Integer nwkAddrOfInterest;

    /**
     * IEEEAddr command message field.
     */
    private Long ieeeAddr;

    /**
     * Capability command message field.
     */
    private Integer capability;

    /**
     * Default constructor.
     */
    public DeviceAnnounce() {
    }

    /**
     * Gets NWKAddrOfInterest.
     *
     * @return the NWKAddrOfInterest
     */
    public Integer getNwkAddrOfInterest() {
        return nwkAddrOfInterest;
    }

    /**
     * Sets NWKAddrOfInterest.
     *
     * @param nwkAddrOfInterest the NWKAddrOfInterest
     */
    public void setNwkAddrOfInterest(final Integer nwkAddrOfInterest) {
        this.nwkAddrOfInterest = nwkAddrOfInterest;
    }

    /**
     * Gets IEEEAddr.
     *
     * @return the IEEEAddr
     */
    public Long getIeeeAddr() {
        return ieeeAddr;
    }

    /**
     * Sets IEEEAddr.
     *
     * @param ieeeAddr the IEEEAddr
     */
    public void setIeeeAddr(final Long ieeeAddr) {
        this.ieeeAddr = ieeeAddr;
    }

    /**
     * Gets Capability.
     *
     * @return the Capability
     */
    public Integer getCapability() {
        return capability;
    }

    /**
     * Sets Capability.
     *
     * @param capability the Capability
     */
    public void setCapability(final Integer capability) {
        this.capability = capability;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(nwkAddrOfInterest, ZclDataType.NWK_ADDRESS);
        serializer.serialize(ieeeAddr, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(capability, ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        nwkAddrOfInterest = (Integer) deserializer.deserialize(ZclDataType.NWK_ADDRESS);
        ieeeAddr = (Long) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        capability = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("DeviceAnnounce");
        builder.append(super.toString());
        builder.append(", nwkAddrOfInterest=");
        builder.append(nwkAddrOfInterest);
        builder.append(", ieeeAddr=");
        builder.append(ieeeAddr);
        builder.append(", capability=");
        builder.append(capability);
        return builder.toString();
    }

}
