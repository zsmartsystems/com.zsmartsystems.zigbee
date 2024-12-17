/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.af;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameResponse;

/**
 * Class to implement the Z-Stack command <b>AF_INCOMING_MSG</b>.
 * <p>
 * This callback message is in response to incoming data to any of the registered endpoints on this device.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public class ZstackAfIncomingMsgAreq extends ZstackFrameResponse {

    /**
     * Specifies the group ID of the device.
     */
    private int groupId;

    /**
     * Specifies the cluster ID.
     */
    private int clusterId;

    /**
     * Specifies the ZigBee network address of the source device sending the message.
     */
    private int srcAddr;

    /**
     * Specifies the source endpoint of the message.
     */
    private int srcEndpoint;

    /**
     * Specifies the destination endpoint of the message.
     */
    private int destEndpoint;

    /**
     * Specifies if the message was a broadcast or not.
     */
    private boolean wasBroadcast;

    /**
     * Indicates the link quality measured during reception.
     */
    private int linkQuality;

    /**
     * Specifies if the security is used or not.
     */
    private boolean securityUse;

    /**
     * Specifies the timestamp of the message.
     */
    private int timeStamp;

    /**
     * Specifies transaction sequence number of the message.
     */
    private int seqNumber;

    /**
     * Contains 0 to 99 bytes of data. Without any security (99 bytes), with NWK security (81 bytes), with NWK and APS security (64
     * bytes).
     */
    private int[] data;

    /**
     * Response and Handler constructor
     */
    public ZstackAfIncomingMsgAreq(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        groupId = deserializer.deserializeUInt16();
        clusterId = deserializer.deserializeUInt16();
        srcAddr = deserializer.deserializeUInt16();
        srcEndpoint = deserializer.deserializeUInt8();
        destEndpoint = deserializer.deserializeUInt8();
        wasBroadcast = deserializer.deserializeBoolean();
        linkQuality = deserializer.deserializeUInt8();
        securityUse = deserializer.deserializeBoolean();
        timeStamp = deserializer.deserializeUInt32();
        seqNumber = deserializer.deserializeUInt8();
        int len = deserializer.deserializeUInt8();
        data = deserializer.deserializeUInt8Array(len);
    }

    /**
     * Specifies the group ID of the device.
     *
     * @return the current groupId as {@link int}
     */
    public int getGroupId() {
        return groupId;
    }

    /**
     * Specifies the group ID of the device.
     *
     * @param groupId the GroupId to set as {@link int}
     */
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    /**
     * Specifies the cluster ID.
     *
     * @return the current clusterId as {@link int}
     */
    public int getClusterId() {
        return clusterId;
    }

    /**
     * Specifies the cluster ID.
     *
     * @param clusterId the ClusterId to set as {@link int}
     */
    public void setClusterId(int clusterId) {
        this.clusterId = clusterId;
    }

    /**
     * Specifies the ZigBee network address of the source device sending the message.
     *
     * @return the current srcAddr as {@link int}
     */
    public int getSrcAddr() {
        return srcAddr;
    }

    /**
     * Specifies the ZigBee network address of the source device sending the message.
     *
     * @param srcAddr the SrcAddr to set as {@link int}
     */
    public void setSrcAddr(int srcAddr) {
        this.srcAddr = srcAddr;
    }

    /**
     * Specifies the source endpoint of the message.
     *
     * @return the current srcEndpoint as {@link int}
     */
    public int getSrcEndpoint() {
        return srcEndpoint;
    }

    /**
     * Specifies the source endpoint of the message.
     *
     * @param srcEndpoint the SrcEndpoint to set as {@link int}
     */
    public void setSrcEndpoint(int srcEndpoint) {
        this.srcEndpoint = srcEndpoint;
    }

    /**
     * Specifies the destination endpoint of the message.
     *
     * @return the current destEndpoint as {@link int}
     */
    public int getDestEndpoint() {
        return destEndpoint;
    }

    /**
     * Specifies the destination endpoint of the message.
     *
     * @param destEndpoint the DestEndpoint to set as {@link int}
     */
    public void setDestEndpoint(int destEndpoint) {
        this.destEndpoint = destEndpoint;
    }

    /**
     * Specifies if the message was a broadcast or not.
     *
     * @return the current wasBroadcast as {@link boolean}
     */
    public boolean getWasBroadcast() {
        return wasBroadcast;
    }

    /**
     * Specifies if the message was a broadcast or not.
     *
     * @param wasBroadcast the WasBroadcast to set as {@link boolean}
     */
    public void setWasBroadcast(boolean wasBroadcast) {
        this.wasBroadcast = wasBroadcast;
    }

    /**
     * Indicates the link quality measured during reception.
     *
     * @return the current linkQuality as {@link int}
     */
    public int getLinkQuality() {
        return linkQuality;
    }

    /**
     * Indicates the link quality measured during reception.
     *
     * @param linkQuality the LinkQuality to set as {@link int}
     */
    public void setLinkQuality(int linkQuality) {
        this.linkQuality = linkQuality;
    }

    /**
     * Specifies if the security is used or not.
     *
     * @return the current securityUse as {@link boolean}
     */
    public boolean getSecurityUse() {
        return securityUse;
    }

    /**
     * Specifies if the security is used or not.
     *
     * @param securityUse the SecurityUse to set as {@link boolean}
     */
    public void setSecurityUse(boolean securityUse) {
        this.securityUse = securityUse;
    }

    /**
     * Specifies the timestamp of the message.
     *
     * @return the current timeStamp as {@link int}
     */
    public int getTimeStamp() {
        return timeStamp;
    }

    /**
     * Specifies the timestamp of the message.
     *
     * @param timeStamp the TimeStamp to set as {@link int}
     */
    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Specifies transaction sequence number of the message.
     *
     * @return the current seqNumber as {@link int}
     */
    public int getSeqNumber() {
        return seqNumber;
    }

    /**
     * Specifies transaction sequence number of the message.
     *
     * @param seqNumber the SeqNumber to set as {@link int}
     */
    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }

    /**
     * Contains 0 to 99 bytes of data. Without any security (99 bytes), with NWK security (81 bytes), with NWK and APS security (64
     * bytes).
     *
     * @return the current data as {@link int[]}
     */
    public int[] getData() {
        return data;
    }

    /**
     * Contains 0 to 99 bytes of data. Without any security (99 bytes), with NWK security (81 bytes), with NWK and APS security (64
     * bytes).
     *
     * @param data the Data to set as {@link int[]}
     */
    public void setData(int[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(326);
        builder.append("ZstackAfIncomingMsgAreq [groupId=");
        builder.append(String.format("%04X", groupId));
        builder.append(", clusterId=");
        builder.append(String.format("%04X", clusterId));
        builder.append(", srcAddr=");
        builder.append(String.format("%04X", srcAddr));
        builder.append(", srcEndpoint=");
        builder.append(String.format("%02X", srcEndpoint));
        builder.append(", destEndpoint=");
        builder.append(String.format("%02X", destEndpoint));
        builder.append(", wasBroadcast=");
        builder.append(wasBroadcast);
        builder.append(", linkQuality=");
        builder.append(linkQuality);
        builder.append(", securityUse=");
        builder.append(securityUse);
        builder.append(", timeStamp=");
        builder.append(String.format("%08X", timeStamp));
        builder.append(", seqNumber=");
        builder.append(String.format("%02X", seqNumber));
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