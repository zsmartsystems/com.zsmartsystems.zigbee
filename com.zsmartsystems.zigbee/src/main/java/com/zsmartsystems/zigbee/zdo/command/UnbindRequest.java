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
import com.zsmartsystems.zigbee.zdo.ZdoRequest;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.zdo.command.UnbindResponse;
import javax.annotation.Generated;
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

@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class UnbindRequest extends ZdoRequest implements ZigBeeTransactionMatcher {
    /**
     * SrcAddress command message field.
     *
     * The IEEE address for the source.
     */
    private IeeeAddress srcAddress;

    /**
     * SrcEndpoint command message field.
     *
     * The source endpoint for the binding entry.
     */
    private Integer srcEndpoint;

    /**
     * BindCluster command message field.
     *
     * The identifier of the cluster on the source device that is bound to the destination.
     */
    private Integer bindCluster;

    /**
     * DstAddrMode command message field.
     *
     * The addressing mode for the destination address used in this command. This field
     * can take one of the non-reserved values from the following list:
     * 0x00 = reserved
     * 0x01 = 16-bit group address for DstAddress and DstEndp not present
     * 0x02 = reserved
     * 0x03 = 64-bit extended address for DstAddress and DstEndp present
     * 0x04 – 0xff = reserved
     */
    private Integer dstAddrMode;

    /**
     * DstAddress command message field.
     *
     * The destination address for the binding entry.
     */
    private IeeeAddress dstAddress;

    /**
     * DstEndpoint command message field.
     *
     * This field shall be present only if the DstAddrMode field has a value of 0x03 and,
     * if present, shall be the destination endpoint for the binding entry.
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
     * <p>
     * The IEEE address for the source.
     *
     * @return the SrcAddress
     */
    public IeeeAddress getSrcAddress() {
        return srcAddress;
    }

    /**
     * Sets SrcAddress.
     * <p>
     * The IEEE address for the source.
     *
     * @param srcAddress the SrcAddress
     */
    public void setSrcAddress(final IeeeAddress srcAddress) {
        this.srcAddress = srcAddress;
    }

    /**
     * Gets SrcEndpoint.
     * <p>
     * The source endpoint for the binding entry.
     *
     * @return the SrcEndpoint
     */
    public Integer getSrcEndpoint() {
        return srcEndpoint;
    }

    /**
     * Sets SrcEndpoint.
     * <p>
     * The source endpoint for the binding entry.
     *
     * @param srcEndpoint the SrcEndpoint
     */
    public void setSrcEndpoint(final Integer srcEndpoint) {
        this.srcEndpoint = srcEndpoint;
    }

    /**
     * Gets BindCluster.
     * <p>
     * The identifier of the cluster on the source device that is bound to the destination.
     *
     * @return the BindCluster
     */
    public Integer getBindCluster() {
        return bindCluster;
    }

    /**
     * Sets BindCluster.
     * <p>
     * The identifier of the cluster on the source device that is bound to the destination.
     *
     * @param bindCluster the BindCluster
     */
    public void setBindCluster(final Integer bindCluster) {
        this.bindCluster = bindCluster;
    }

    /**
     * Gets DstAddrMode.
     * <p>
     * The addressing mode for the destination address used in this command. This field
     * can take one of the non-reserved values from the following list:
     * 0x00 = reserved
     * 0x01 = 16-bit group address for DstAddress and DstEndp not present
     * 0x02 = reserved
     * 0x03 = 64-bit extended address for DstAddress and DstEndp present
     * 0x04 – 0xff = reserved
     *
     * @return the DstAddrMode
     */
    public Integer getDstAddrMode() {
        return dstAddrMode;
    }

    /**
     * Sets DstAddrMode.
     * <p>
     * The addressing mode for the destination address used in this command. This field
     * can take one of the non-reserved values from the following list:
     * 0x00 = reserved
     * 0x01 = 16-bit group address for DstAddress and DstEndp not present
     * 0x02 = reserved
     * 0x03 = 64-bit extended address for DstAddress and DstEndp present
     * 0x04 – 0xff = reserved
     *
     * @param dstAddrMode the DstAddrMode
     */
    public void setDstAddrMode(final Integer dstAddrMode) {
        this.dstAddrMode = dstAddrMode;
    }

    /**
     * Gets DstAddress.
     * <p>
     * The destination address for the binding entry.
     *
     * @return the DstAddress
     */
    public IeeeAddress getDstAddress() {
        return dstAddress;
    }

    /**
     * Sets DstAddress.
     * <p>
     * The destination address for the binding entry.
     *
     * @param dstAddress the DstAddress
     */
    public void setDstAddress(final IeeeAddress dstAddress) {
        this.dstAddress = dstAddress;
    }

    /**
     * Gets DstEndpoint.
     * <p>
     * This field shall be present only if the DstAddrMode field has a value of 0x03 and,
     * if present, shall be the destination endpoint for the binding entry.
     *
     * @return the DstEndpoint
     */
    public Integer getDstEndpoint() {
        return dstEndpoint;
    }

    /**
     * Sets DstEndpoint.
     * <p>
     * This field shall be present only if the DstAddrMode field has a value of 0x03 and,
     * if present, shall be the destination endpoint for the binding entry.
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
        serializer.serialize(bindCluster, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(dstAddrMode, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(dstAddress, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(dstEndpoint, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        srcAddress = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        srcEndpoint = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        bindCluster = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        dstAddrMode = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        dstAddress = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        dstEndpoint = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public boolean isTransactionMatch(ZigBeeCommand request, ZigBeeCommand response) {
        if (!(response instanceof UnbindResponse)) {
            return false;
        }

        return ((ZdoRequest) request).getDestinationAddress().equals(((UnbindResponse) response).getSourceAddress());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("UnbindRequest [");
        builder.append(super.toString());
        builder.append(", srcAddress=");
        builder.append(srcAddress);
        builder.append(", srcEndpoint=");
        builder.append(srcEndpoint);
        builder.append(", bindCluster=");
        builder.append(bindCluster);
        builder.append(", dstAddrMode=");
        builder.append(dstAddrMode);
        builder.append(", dstAddress=");
        builder.append(dstAddress);
        builder.append(", dstEndpoint=");
        builder.append(dstEndpoint);
        builder.append(']');
        return builder.toString();
    }

}
