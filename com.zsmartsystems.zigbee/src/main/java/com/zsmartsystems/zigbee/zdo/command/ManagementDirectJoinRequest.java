package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;
import com.zsmartsystems.zigbee.IeeeAddress;

/**
 * Management Direct Join Request value object class.
 * <p>
 * The Mgmt_Direct_Join_req is generated from a Local Device requesting that a
 * Remote Device permit a device designated by DeviceAddress to join the network
 * directly. The Mgmt_Direct_Join_req is generated by a management application
 * which directs the request to a Remote Device where the NLME-DIRECTJOIN.request
 * is to be executed using the parameter supplied by
 * Mgmt_Direct_Join_req.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ManagementDirectJoinRequest extends ZdoRequest {
    /**
     * DeviceAddress command message field.
     */
    private IeeeAddress deviceAddress;

    /**
     * CapabilityInformation command message field.
     */
    private Integer capabilityInformation;

    /**
     * Default constructor.
     */
    public ManagementDirectJoinRequest() {
        clusterId = 0x0035;
    }

    /**
     * Gets DeviceAddress.
     *
     * @return the DeviceAddress
     */
    public IeeeAddress getDeviceAddress() {
        return deviceAddress;
    }

    /**
     * Sets DeviceAddress.
     *
     * @param deviceAddress the DeviceAddress
     */
    public void setDeviceAddress(final IeeeAddress deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    /**
     * Gets CapabilityInformation.
     *
     * @return the CapabilityInformation
     */
    public Integer getCapabilityInformation() {
        return capabilityInformation;
    }

    /**
     * Sets CapabilityInformation.
     *
     * @param capabilityInformation the CapabilityInformation
     */
    public void setCapabilityInformation(final Integer capabilityInformation) {
        this.capabilityInformation = capabilityInformation;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(deviceAddress, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(capabilityInformation, ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        deviceAddress = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        capabilityInformation = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ManagementDirectJoinRequest");
        builder.append(super.toString());
        builder.append(", deviceAddress=");
        builder.append(deviceAddress);
        builder.append(", capabilityInformation=");
        builder.append(capabilityInformation);
        return builder.toString();
    }

}
