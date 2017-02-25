package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;

import java.util.List;

/**
 * End Device Bind value object class.
 * <p>
 * The End_Device_Bind_req is generated from a Local Device wishing to perform
 * End Device Bind with a Remote Device. The End_Device_Bind_req is generated,
 * typically based on some user action like a button press. The destination addressing
 * on this command shall be unicast, and the destination address shall be that of the
 * ZigBee Coordinator.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class EndDeviceBind extends ZdoResponse {
    /**
     * BindingTarget command message field.
     */
    private Integer bindingTarget;

    /**
     * SrcAddress command message field.
     */
    private Long srcAddress;

    /**
     * SrcEndpoint command message field.
     */
    private Integer srcEndpoint;

    /**
     * ProfileID command message field.
     */
    private Integer profileId;

    /**
     * InClusterList command message field.
     */
    private List<Integer> inClusterList;

    /**
     * OutClusterList command message field.
     */
    private List<Integer> outClusterList;

    /**
     * Default constructor.
     */
    public EndDeviceBind() {
    }

    /**
     * Gets BindingTarget.
     *
     * @return the BindingTarget
     */
    public Integer getBindingTarget() {
        return bindingTarget;
    }

    /**
     * Sets BindingTarget.
     *
     * @param bindingTarget the BindingTarget
     */
    public void setBindingTarget(final Integer bindingTarget) {
        this.bindingTarget = bindingTarget;
    }

    /**
     * Gets SrcAddress.
     *
     * @return the SrcAddress
     */
    public Long getSrcAddress() {
        return srcAddress;
    }

    /**
     * Sets SrcAddress.
     *
     * @param srcAddress the SrcAddress
     */
    public void setSrcAddress(final Long srcAddress) {
        this.srcAddress = srcAddress;
    }

    /**
     * Gets SrcEndpoint.
     *
     * @return the SrcEndpoint
     */
    public Integer getSrcEndpoint() {
        return srcEndpoint;
    }

    /**
     * Sets SrcEndpoint.
     *
     * @param srcEndpoint the SrcEndpoint
     */
    public void setSrcEndpoint(final Integer srcEndpoint) {
        this.srcEndpoint = srcEndpoint;
    }

    /**
     * Gets ProfileID.
     *
     * @return the ProfileID
     */
    public Integer getProfileId() {
        return profileId;
    }

    /**
     * Sets ProfileID.
     *
     * @param profileId the ProfileID
     */
    public void setProfileId(final Integer profileId) {
        this.profileId = profileId;
    }

    /**
     * Gets InClusterList.
     *
     * @return the InClusterList
     */
    public List<Integer> getInClusterList() {
        return inClusterList;
    }

    /**
     * Sets InClusterList.
     *
     * @param inClusterList the InClusterList
     */
    public void setInClusterList(final List<Integer> inClusterList) {
        this.inClusterList = inClusterList;
    }

    /**
     * Gets OutClusterList.
     *
     * @return the OutClusterList
     */
    public List<Integer> getOutClusterList() {
        return outClusterList;
    }

    /**
     * Sets OutClusterList.
     *
     * @param outClusterList the OutClusterList
     */
    public void setOutClusterList(final List<Integer> outClusterList) {
        this.outClusterList = outClusterList;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(bindingTarget, ZclDataType.NWK_ADDRESS);
        serializer.serialize(srcAddress, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(srcEndpoint, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(profileId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(inClusterList, ZclDataType.N_X_CLUSTERID);
        serializer.serialize(outClusterList, ZclDataType.N_X_CLUSTERID);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        bindingTarget = (Integer) deserializer.deserialize(ZclDataType.NWK_ADDRESS);
        srcAddress = (Long) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        srcEndpoint = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        profileId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        inClusterList = (List<Integer>) deserializer.deserialize(ZclDataType.N_X_CLUSTERID);
        outClusterList = (List<Integer>) deserializer.deserialize(ZclDataType.N_X_CLUSTERID);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EndDeviceBind");
        builder.append(super.toString());
        builder.append(", bindingTarget=");
        builder.append(bindingTarget);
        builder.append(", srcAddress=");
        builder.append(srcAddress);
        builder.append(", srcEndpoint=");
        builder.append(srcEndpoint);
        builder.append(", profileId=");
        builder.append(profileId);
        builder.append(", inClusterList=");
        builder.append(inClusterList);
        builder.append(", outClusterList=");
        builder.append(outClusterList);
        return builder.toString();
    }

}
