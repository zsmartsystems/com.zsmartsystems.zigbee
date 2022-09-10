/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.af;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameRequest;
import com.zsmartsystems.zigbee.dongle.zstack.api.af.AfDataOptions;
import com.zsmartsystems.zigbee.dongle.zstack.api.rpc.ZstackRpcSreqErrorSrsp;
import java.util.HashSet;
import java.util.Set;

/**
 * Class to implement the Z-Stack command <b>AF_DATA_REQUEST</b>.
 * <p>
 * This command is used by the App processor to build and send a message through AF layer.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public class ZstackAfDataRequestSreq extends ZstackFrameRequest {

    /**
     * Short address of the destination device.
     */
    private int dstAddr;

    /**
     * Endpoint of the destination device.
     */
    private int destEndpoint;

    /**
     * Endpoint of the source device.
     */
    private int srcEndpoint;

    /**
     * Specifies the cluster ID.
     */
    private int clusterId;

    /**
     * Specifies the transaction sequence number of the message. The corresponding AF_DATA_CONFIRM will have the same TransID. This
     * can be useful if the application wishes to match up AF_DATA_REQUESTs with AF_DATA_CONFIRMs.
     */
    private int transId;

    /**
     * The transmit options field is organized as a bitmask. The following enumerates the values for the various supported bitmasks.
     * For example, a value of 0x10 means that bit 4 is set.
     * <p>
     * Parameter allows multiple options so implemented as a {@link Set}.
     */
    private Set<AfDataOptions> options = new HashSet<>();

    /**
     * Specifies the list of Input Cluster Ids ( 2bytes each ).
     */
    private int radius;

    /**
     * 0-99 bytes data. Without any security (99 bytes), with NWK security (81 bytes), with NWK and APS security (64 bytes).
     */
    private int[] data;

    /**
     * Request constructor
     */
    public ZstackAfDataRequestSreq() {
        synchronousCommand = true;
    }

    /**
     * Short address of the destination device.
     *
     * @return the current dstAddr as {@link int}
     */
    public int getDstAddr() {
        return dstAddr;
    }

    /**
     * Short address of the destination device.
     *
     * @param dstAddr the DstAddr to set as {@link int}
     */
    public void setDstAddr(int dstAddr) {
        this.dstAddr = dstAddr;
    }

    /**
     * Endpoint of the destination device.
     *
     * @return the current destEndpoint as {@link int}
     */
    public int getDestEndpoint() {
        return destEndpoint;
    }

    /**
     * Endpoint of the destination device.
     *
     * @param destEndpoint the DestEndpoint to set as {@link int}
     */
    public void setDestEndpoint(int destEndpoint) {
        this.destEndpoint = destEndpoint;
    }

    /**
     * Endpoint of the source device.
     *
     * @return the current srcEndpoint as {@link int}
     */
    public int getSrcEndpoint() {
        return srcEndpoint;
    }

    /**
     * Endpoint of the source device.
     *
     * @param srcEndpoint the SrcEndpoint to set as {@link int}
     */
    public void setSrcEndpoint(int srcEndpoint) {
        this.srcEndpoint = srcEndpoint;
    }

    /**
     * Specifies the cluster ID.
     *
     * @return the current clusterId as {@link int}
     */
    public int getClusterID() {
        return clusterId;
    }

    /**
     * Specifies the cluster ID.
     *
     * @param clusterId the ClusterID to set as {@link int}
     */
    public void setClusterID(int clusterId) {
        this.clusterId = clusterId;
    }

    /**
     * Specifies the transaction sequence number of the message. The corresponding AF_DATA_CONFIRM will have the same TransID. This
     * can be useful if the application wishes to match up AF_DATA_REQUESTs with AF_DATA_CONFIRMs.
     *
     * @return the current transId as {@link int}
     */
    public int getTransID() {
        return transId;
    }

    /**
     * Specifies the transaction sequence number of the message. The corresponding AF_DATA_CONFIRM will have the same TransID. This
     * can be useful if the application wishes to match up AF_DATA_REQUESTs with AF_DATA_CONFIRMs.
     *
     * @param transId the TransID to set as {@link int}
     */
    public void setTransID(int transId) {
        this.transId = transId;
    }

    /**
     * The transmit options field is organized as a bitmask. The following enumerates the values for the various supported bitmasks.
     * For example, a value of 0x10 means that bit 4 is set.
     *
     * @return the current options as {@link Set} of {@link AfDataOptions}
     */
    public Set<AfDataOptions> getOptions() {
        return options;
    }

    /**
     * The transmit options field is organized as a bitmask. The following enumerates the values for the various supported bitmasks.
     * For example, a value of 0x10 means that bit 4 is set.
     *
     * @param options the Options to add to the {@link Set} as {@link AfDataOptions}
     */
    public void addOptions(AfDataOptions options) {
        this.options.add(options);
    }

    /**
     * The transmit options field is organized as a bitmask. The following enumerates the values for the various supported bitmasks.
     * For example, a value of 0x10 means that bit 4 is set.
     *
     * @param options the Options to remove to the {@link Set} as {@link AfDataOptions}
     */
    public void removeOptions(AfDataOptions options) {
        this.options.remove(options);
    }

    /**
     * Specifies the list of Input Cluster Ids ( 2bytes each ).
     *
     * @return the current radius as {@link int}
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Specifies the list of Input Cluster Ids ( 2bytes each ).
     *
     * @param radius the Radius to set as {@link int}
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * 0-99 bytes data. Without any security (99 bytes), with NWK security (81 bytes), with NWK and APS security (64 bytes).
     *
     * @return the current data as {@link int[]}
     */
    public int[] getData() {
        return data;
    }

    /**
     * 0-99 bytes data. Without any security (99 bytes), with NWK security (81 bytes), with NWK and APS security (64 bytes).
     *
     * @param data the Data to set as {@link int[]}
     */
    public void setData(int[] data) {
        this.data = data;
    }

    @Override
    public boolean matchSreqError(ZstackRpcSreqErrorSrsp response) {
        return (((response.getReqCmd0() & 0x1F) == ZSTACK_AF) && (response.getReqCmd1() == 0x01));
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(ZSTACK_SREQ, ZSTACK_AF, 0x01);

        // Serialize the fields
        serializer.serializeUInt16(dstAddr);
        serializer.serializeUInt8(destEndpoint);
        serializer.serializeUInt8(srcEndpoint);
        serializer.serializeUInt16(clusterId);
        serializer.serializeUInt8(transId);
        int tmpOptions = 0;
        for (AfDataOptions value : options) {
            tmpOptions += value.getKey();
        }
        serializer.serializeUInt8(tmpOptions);
        serializer.serializeUInt8(radius);
        serializer.serializeUInt8(data.length);
        serializer.serializeUInt8Array(data);
        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(251);
        builder.append("ZstackAfDataRequestSreq [dstAddr=");
        builder.append(String.format("%04X", dstAddr));
        builder.append(", destEndpoint=");
        builder.append(String.format("%02X", destEndpoint));
        builder.append(", srcEndpoint=");
        builder.append(String.format("%02X", srcEndpoint));
        builder.append(", clusterId=");
        builder.append(String.format("%04X", clusterId));
        builder.append(", transId=");
        builder.append(String.format("%02X", transId));
        builder.append(", options=");
        builder.append(options);
        builder.append(", radius=");
        builder.append(radius);
        builder.append(", data=");
        for (int c = 0; c < data.length; c++) {
            if (c > 0) {
                builder.append(' ');
            }
            builder.append(String.format("%02X", data[c]));
        }
        builder.append(']');
        return builder.toString();
    }
}
