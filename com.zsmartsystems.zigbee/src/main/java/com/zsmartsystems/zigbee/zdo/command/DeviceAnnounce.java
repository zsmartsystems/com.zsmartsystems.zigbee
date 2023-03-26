/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Device Announce value object class.
 * <p>
 * <p>
 * The Device_annce is provided to enable ZigBee devices on the network to notify other ZigBee
 * devices that the device has joined or re-joined the network, identifying the device's
 * 64-bit IEEE address and new 16-bit NWK address, and informing the Remote Devices of the
 * capability of the ZigBee device. This command shall be invoked for all ZigBee end devices
 * upon join or rejoin. This command may also be invoked by ZigBee routers upon join or rejoin as
 * part of NWK address conflict resolution. The destination addressing on this primitive is
 * broadcast to all devices for which macRxOnWhenIdle = TRUE.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class DeviceAnnounce extends ZdoRequest {
    /**
     * The ZDO cluster ID.
     */
    public static int CLUSTER_ID = 0x0013;

    /**
     * NWK Addr Of Interest command message field.
     */
    private Integer nwkAddrOfInterest;

    /**
     * IEEE Addr command message field.
     */
    private IeeeAddress ieeeAddr;

    /**
     * Capability command message field.
     */
    private Integer capability;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public DeviceAnnounce() {
        clusterId = CLUSTER_ID;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param nwkAddrOfInterest {@link Integer} NWK Addr Of Interest
     * @param ieeeAddr {@link IeeeAddress} IEEE Addr
     * @param capability {@link Integer} Capability
     */
    public DeviceAnnounce(
            Integer nwkAddrOfInterest,
            IeeeAddress ieeeAddr,
            Integer capability) {

        clusterId = CLUSTER_ID;

        this.nwkAddrOfInterest = nwkAddrOfInterest;
        this.ieeeAddr = ieeeAddr;
        this.capability = capability;
    }

    /**
     * Gets NWK Addr Of Interest.
     *
     * @return the NWK Addr Of Interest
     */
    public Integer getNwkAddrOfInterest() {
        return nwkAddrOfInterest;
    }

    /**
     * Sets NWK Addr Of Interest.
     *
     * @param nwkAddrOfInterest the NWK Addr Of Interest
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setNwkAddrOfInterest(final Integer nwkAddrOfInterest) {
        this.nwkAddrOfInterest = nwkAddrOfInterest;
    }

    /**
     * Gets IEEE Addr.
     *
     * @return the IEEE Addr
     */
    public IeeeAddress getIeeeAddr() {
        return ieeeAddr;
    }

    /**
     * Sets IEEE Addr.
     *
     * @param ieeeAddr the IEEE Addr
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
        builder.append(String.format("%04X", nwkAddrOfInterest));
        builder.append(", ieeeAddr=");
        builder.append(ieeeAddr);
        builder.append(", capability=");
        builder.append(String.format("%02X", capability));
        builder.append(']');
        return builder.toString();
    }

}
