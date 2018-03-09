/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;
import javax.annotation.Generated;
import com.zsmartsystems.zigbee.IeeeAddress;

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

@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class DeviceAnnounce extends ZdoResponse {
    /**
     * NWKAddrOfInterest command message field.
     */
    private Integer nwkAddrOfInterest;

    /**
     * IEEEAddr command message field.
     */
    private IeeeAddress ieeeAddr;

    /**
     * Capability command message field.
     */
    private Integer capability;

    /**
     * Default constructor.
     */
    public DeviceAnnounce() {
        clusterId = 0x0013;
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
    public IeeeAddress getIeeeAddr() {
        return ieeeAddr;
    }

    /**
     * Sets IEEEAddr.
     *
     * @param ieeeAddr the IEEEAddr
     */
    public void setIeeeAddr(final IeeeAddress ieeeAddr) {
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
        super.serialize(serializer);

        serializer.serialize(nwkAddrOfInterest, ZclDataType.NWK_ADDRESS);
        serializer.serialize(ieeeAddr, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(capability, ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        nwkAddrOfInterest = (Integer) deserializer.deserialize(ZclDataType.NWK_ADDRESS);
        ieeeAddr = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        capability = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(112);
        builder.append("DeviceAnnounce [");
        builder.append(super.toString());
        builder.append(", nwkAddrOfInterest=");
        builder.append(nwkAddrOfInterest);
        builder.append(", ieeeAddr=");
        builder.append(ieeeAddr);
        builder.append(", capability=");
        builder.append(capability);
        builder.append(']');
        return builder.toString();
    }

}
