package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.IeeeAddress;

/**
 * Unbind Request value object class.
 * <p>
 * The Unbind_req is generated from a Local Device wishing to remove a Binding
 * Table entry for the source and destination addresses contained as parameters. The
 * destination addressing on this command shall be unicast only and the destination
 * address must be that of the a Primary binding table cache or the SrcAddress.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class UnbindRequest extends ZdoRequest {
    /**
     * SrcAddress command message field.
     */
    private IeeeAddress srcAddress;

    /**
     * SrcEndpoint command message field.
     */
    private Integer srcEndpoint;

    /**
     * ClusterID command message field.
     */
    private Integer clusterId;

    /**
     * DstAddrMode command message field.
     */
    private Integer dstAddrMode;

    /**
     * DstAddress command message field.
     */
    private IeeeAddress dstAddress;

    /**
     * DstEndpoint command message field.
     */
    private Integer dstEndpoint;

    /**
     * Default constructor.
     */
    public UnbindRequest() {
        clusterId = 0x0022;
    }

    /**
     * Gets SrcAddress.
     *
     * @return the SrcAddress
     */
    public IeeeAddress getSrcAddress() {
        return srcAddress;
    }

    /**
     * Sets SrcAddress.
     *
     * @param srcAddress the SrcAddress
     */
    public void setSrcAddress(final IeeeAddress srcAddress) {
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
     * Gets ClusterID.
     *
     * @return the ClusterID
     */
    public Integer getClusterId() {
        return clusterId;
    }

    /**
     * Sets ClusterID.
     *
     * @param clusterId the ClusterID
     */
    public void setClusterId(final Integer clusterId) {
        this.clusterId = clusterId;
    }

    /**
     * Gets DstAddrMode.
     *
     * @return the DstAddrMode
     */
    public Integer getDstAddrMode() {
        return dstAddrMode;
    }

    /**
     * Sets DstAddrMode.
     *
     * @param dstAddrMode the DstAddrMode
     */
    public void setDstAddrMode(final Integer dstAddrMode) {
        this.dstAddrMode = dstAddrMode;
    }

    /**
     * Gets DstAddress.
     *
     * @return the DstAddress
     */
    public IeeeAddress getDstAddress() {
        return dstAddress;
    }

    /**
     * Sets DstAddress.
     *
     * @param dstAddress the DstAddress
     */
    public void setDstAddress(final IeeeAddress dstAddress) {
        this.dstAddress = dstAddress;
    }

    /**
     * Gets DstEndpoint.
     *
     * @return the DstEndpoint
     */
    public Integer getDstEndpoint() {
        return dstEndpoint;
    }

    /**
     * Sets DstEndpoint.
     *
     * @param dstEndpoint the DstEndpoint
     */
    public void setDstEndpoint(final Integer dstEndpoint) {
        this.dstEndpoint = dstEndpoint;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(srcAddress, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(srcEndpoint, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(clusterId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(dstAddrMode, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(dstAddress, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(dstEndpoint, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        srcAddress = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        srcEndpoint = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        clusterId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        dstAddrMode = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        dstAddress = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        dstEndpoint = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UnbindRequest");
        builder.append(super.toString());
        builder.append(", srcAddress=");
        builder.append(srcAddress);
        builder.append(", srcEndpoint=");
        builder.append(srcEndpoint);
        builder.append(", clusterId=");
        builder.append(clusterId);
        builder.append(", dstAddrMode=");
        builder.append(dstAddrMode);
        builder.append(", dstAddress=");
        builder.append(dstAddress);
        builder.append(", dstEndpoint=");
        builder.append(dstEndpoint);
        return builder.toString();
    }

}
