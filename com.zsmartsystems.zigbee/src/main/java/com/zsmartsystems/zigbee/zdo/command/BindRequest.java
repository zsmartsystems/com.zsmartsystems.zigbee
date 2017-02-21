package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Bind Request value object class.
 * <p>
 * The Bind_req is generated from a Local Device wishing to create a Binding Table
 * entry for the source and destination addresses contained as parameters. The
 * destination addressing on this command shall be unicast only, and the destination
 * address shall be that of a Primary binding table cache or to the SrcAddress itself.
 * The Binding Manager is optionally supported on the source device (unless that
 * device is also the ZigBee Coordinator) so that device shall issue a
 * NOT_SUPPORTED status to the Bind_req if not supported.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class BindRequest extends ZdoRequest {
    /**
     * SrcAddress command message field.
     */
    private Long srcAddress;

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
    private Long dstAddress;

    /**
     * DstEndpoint command message field.
     */
    private Integer dstEndpoint;

    /**
     * Default constructor.
     */
    public BindRequest() {
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
    public Long getDstAddress() {
        return dstAddress;
    }

    /**
     * Sets DstAddress.
     *
     * @param dstAddress the DstAddress
     */
    public void setDstAddress(final Long dstAddress) {
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
        serializer.serialize(srcAddress, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(srcEndpoint, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(clusterId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(dstAddrMode, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(dstAddress, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(dstEndpoint, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        srcAddress = (Long) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        srcEndpoint = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        clusterId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        dstAddrMode = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        dstAddress = (Long) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        dstEndpoint = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BindRequest");
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
