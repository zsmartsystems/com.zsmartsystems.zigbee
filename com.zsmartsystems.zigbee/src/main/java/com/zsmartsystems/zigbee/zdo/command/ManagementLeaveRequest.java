package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;
import com.zsmartsystems.zigbee.IeeeAddress;

/**
 * Management Leave Request value object class.
 * <p>
 * The Mgmt_Leave_req is generated from a Local Device requesting that a Remote
 * Device leave the network or to request that another device leave the network. The
 * Mgmt_Leave_req is generated by a management application which directs the
 * request to a Remote Device where the NLME-LEAVE.request is to be executed
 * using the parameter supplied by Mgmt_Leave_req.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ManagementLeaveRequest extends ZdoRequest {
    /**
     * DeviceAddress command message field.
     */
    private IeeeAddress deviceAddress;

    /**
     * RemoveChildren command message field.
     */
    private Boolean removeChildren;

    /**
     * Rejoin command message field.
     */
    private Boolean rejoin;

    /**
     * Default constructor.
     */
    public ManagementLeaveRequest() {
        clusterId = 0x0034;
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
     * Gets RemoveChildren.
     *
     * @return the RemoveChildren
     */
    public Boolean getRemoveChildren() {
        return removeChildren;
    }

    /**
     * Sets RemoveChildren.
     *
     * @param removeChildren the RemoveChildren
     */
    public void setRemoveChildren(final Boolean removeChildren) {
        this.removeChildren = removeChildren;
    }

    /**
     * Gets Rejoin.
     *
     * @return the Rejoin
     */
    public Boolean getRejoin() {
        return rejoin;
    }

    /**
     * Sets Rejoin.
     *
     * @param rejoin the Rejoin
     */
    public void setRejoin(final Boolean rejoin) {
        this.rejoin = rejoin;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(deviceAddress, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(removeChildren, ZclDataType.BOOLEAN);
        serializer.serialize(rejoin, ZclDataType.BOOLEAN);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        deviceAddress = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        removeChildren = (Boolean) deserializer.deserialize(ZclDataType.BOOLEAN);
        rejoin = (Boolean) deserializer.deserialize(ZclDataType.BOOLEAN);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ManagementLeaveRequest");
        builder.append(super.toString());
        builder.append(", deviceAddress=");
        builder.append(deviceAddress);
        builder.append(", removeChildren=");
        builder.append(removeChildren);
        builder.append(", rejoin=");
        builder.append(rejoin);
        return builder.toString();
    }

}
