/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeAddress;
import com.zsmartsystems.zigbee.ZigBeeBroadcastDestination;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;
import com.zsmartsystems.zigbee.zdo.command.UnbindResponse;

/**
 * Unbind Request value object class.
 * <p>
 * <p>
 * The Unbind_req is generated from a Local Device wishing to remove a Binding Table entry for
 * the source and destination addresses contained as parameters. The destination addressing
 * on this command shall be unicast only and the destination address must be that of the a Primary
 * binding table cache or the SrcAddress.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-08-28T10:02:45Z")
public class UnbindRequest extends ZdoRequest implements ZigBeeTransactionMatcher {
    /**
     * The ZDO cluster ID.
     */
    public static int CLUSTER_ID = 0x0022;

    /**
     * Src Address command message field.
     * <p>
     * The IEEE address for the source.
     */
    private IeeeAddress srcAddress;

    /**
     * Src Endpoint command message field.
     * <p>
     * The source endpoint for the binding entry.
     */
    private Integer srcEndpoint;

    /**
     * Bind Cluster command message field.
     * <p>
     * The identifier of the cluster on the source device that is bound to the destination.
     */
    private Integer bindCluster;

    /**
     * DST Addr Mode command message field.
     * <p>
     * The addressing mode for the destination address used in this command. This field can
     * take one of the non-reserved values from the following list: 0x00 = reserved 0x01 =
     * 16-bit group address for DstAddress and DstEndp not present 0x02 = reserved 0x03 =
     * 64-bit extended address for DstAddress and DstEndp present 0x04 – 0xff = reserved
     */
    private Integer dstAddrMode;

    /**
     * DST Address command message field.
     * <p>
     * The destination address for the binding entry.
     */
    private IeeeAddress dstAddress;

    /**
     * DST Endpoint command message field.
     * <p>
     * This field shall be present only if the DstAddrMode field has a value of 0x03 and, if
     * present, shall be the destination endpoint for the binding entry.
     */
    private Integer dstEndpoint;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.
     */
    @Deprecated
    public UnbindRequest() {
        clusterId = CLUSTER_ID;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param srcAddress {@link IeeeAddress} Src Address
     * @param srcEndpoint {@link Integer} Src Endpoint
     * @param bindCluster {@link Integer} Bind Cluster
     * @param dstAddrMode {@link Integer} DST Addr Mode
     * @param dstAddress {@link IeeeAddress} DST Address
     * @param dstEndpoint {@link Integer} DST Endpoint
     */
    public UnbindRequest(
            IeeeAddress srcAddress,
            Integer srcEndpoint,
            Integer bindCluster,
            Integer dstAddrMode,
            IeeeAddress dstAddress,
            Integer dstEndpoint) {

        clusterId = CLUSTER_ID;

        this.srcAddress = srcAddress;
        this.srcEndpoint = srcEndpoint;
        this.bindCluster = bindCluster;
        this.dstAddrMode = dstAddrMode;
        this.dstAddress = dstAddress;
        this.dstEndpoint = dstEndpoint;
    }

    /**
     * Gets Src Address.
     * <p>
     * The IEEE address for the source.
     *
     * @return the Src Address
     */
    public IeeeAddress getSrcAddress() {
        return srcAddress;
    }

    /**
     * Sets Src Address.
     * <p>
     * The IEEE address for the source.
     *
     * @param srcAddress the Src Address
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSrcAddress(final IeeeAddress srcAddress) {
        this.srcAddress = srcAddress;
    }

    /**
     * Gets Src Endpoint.
     * <p>
     * The source endpoint for the binding entry.
     *
     * @return the Src Endpoint
     */
    public Integer getSrcEndpoint() {
        return srcEndpoint;
    }

    /**
     * Sets Src Endpoint.
     * <p>
     * The source endpoint for the binding entry.
     *
     * @param srcEndpoint the Src Endpoint
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSrcEndpoint(final Integer srcEndpoint) {
        this.srcEndpoint = srcEndpoint;
    }

    /**
     * Gets Bind Cluster.
     * <p>
     * The identifier of the cluster on the source device that is bound to the destination.
     *
     * @return the Bind Cluster
     */
    public Integer getBindCluster() {
        return bindCluster;
    }

    /**
     * Sets Bind Cluster.
     * <p>
     * The identifier of the cluster on the source device that is bound to the destination.
     *
     * @param bindCluster the Bind Cluster
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setBindCluster(final Integer bindCluster) {
        this.bindCluster = bindCluster;
    }

    /**
     * Gets DST Addr Mode.
     * <p>
     * The addressing mode for the destination address used in this command. This field can
     * take one of the non-reserved values from the following list: 0x00 = reserved 0x01 =
     * 16-bit group address for DstAddress and DstEndp not present 0x02 = reserved 0x03 =
     * 64-bit extended address for DstAddress and DstEndp present 0x04 – 0xff = reserved
     *
     * @return the DST Addr Mode
     */
    public Integer getDstAddrMode() {
        return dstAddrMode;
    }

    /**
     * Sets DST Addr Mode.
     * <p>
     * The addressing mode for the destination address used in this command. This field can
     * take one of the non-reserved values from the following list: 0x00 = reserved 0x01 =
     * 16-bit group address for DstAddress and DstEndp not present 0x02 = reserved 0x03 =
     * 64-bit extended address for DstAddress and DstEndp present 0x04 – 0xff = reserved
     *
     * @param dstAddrMode the DST Addr Mode
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setDstAddrMode(final Integer dstAddrMode) {
        this.dstAddrMode = dstAddrMode;
    }

    /**
     * Gets DST Address.
     * <p>
     * The destination address for the binding entry.
     *
     * @return the DST Address
     */
    public IeeeAddress getDstAddress() {
        return dstAddress;
    }

    /**
     * Sets DST Address.
     * <p>
     * The destination address for the binding entry.
     *
     * @param dstAddress the DST Address
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setDstAddress(final IeeeAddress dstAddress) {
        this.dstAddress = dstAddress;
    }

    /**
     * Gets DST Endpoint.
     * <p>
     * This field shall be present only if the DstAddrMode field has a value of 0x03 and, if
     * present, shall be the destination endpoint for the binding entry.
     *
     * @return the DST Endpoint
     */
    public Integer getDstEndpoint() {
        return dstEndpoint;
    }

    /**
     * Sets DST Endpoint.
     * <p>
     * This field shall be present only if the DstAddrMode field has a value of 0x03 and, if
     * present, shall be the destination endpoint for the binding entry.
     *
     * @param dstEndpoint the DST Endpoint
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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

        ZigBeeAddress destinationAddress = ((ZdoRequest) request).getDestinationAddress();
        ZigBeeAddress sourceAddress = ((ZdoResponse) response).getSourceAddress();
        ZigBeeEndpointAddress localCoordinator = new ZigBeeEndpointAddress(0, 0);

        if(!ZigBeeBroadcastDestination.isBroadcast(destinationAddress.getAddress())) {
            if (!localCoordinator.equals(sourceAddress) && !destinationAddress.equals(sourceAddress)) {
                return false;
            }
        }
        return true;
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
