package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Network Update Request value object class.
 * <p>
 * This command is provided to allow updating of network configuration parameters
 * or to request information from devices on network conditions in the local
 * operating environment. The destination addressing on this primitive shall be
 * unicast or broadcast to all devices for which macRxOnWhenIdle = TRUE.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class NetworkUpdateRequest extends ZdoRequest {
    /**
     * ScanChannels command message field.
     */
    private Integer scanChannels;

    /**
     * ScanDuration command message field.
     */
    private Integer scanDuration;

    /**
     * ScanCount command message field.
     */
    private Integer scanCount;

    /**
     * nwkUpdateId command message field.
     */
    private Integer nwkUpdateId;

    /**
     * nwkManagerAddr command message field.
     */
    private Integer nwkManagerAddr;

    /**
     * Default constructor.
     */
    public NetworkUpdateRequest() {
    }

    /**
     * Gets ScanChannels.
     *
     * @return the ScanChannels
     */
    public Integer getScanChannels() {
        return scanChannels;
    }

    /**
     * Sets ScanChannels.
     *
     * @param scanChannels the ScanChannels
     */
    public void setScanChannels(final Integer scanChannels) {
        this.scanChannels = scanChannels;
    }

    /**
     * Gets ScanDuration.
     *
     * @return the ScanDuration
     */
    public Integer getScanDuration() {
        return scanDuration;
    }

    /**
     * Sets ScanDuration.
     *
     * @param scanDuration the ScanDuration
     */
    public void setScanDuration(final Integer scanDuration) {
        this.scanDuration = scanDuration;
    }

    /**
     * Gets ScanCount.
     *
     * @return the ScanCount
     */
    public Integer getScanCount() {
        return scanCount;
    }

    /**
     * Sets ScanCount.
     *
     * @param scanCount the ScanCount
     */
    public void setScanCount(final Integer scanCount) {
        this.scanCount = scanCount;
    }

    /**
     * Gets nwkUpdateId.
     *
     * @return the nwkUpdateId
     */
    public Integer getNwkUpdateId() {
        return nwkUpdateId;
    }

    /**
     * Sets nwkUpdateId.
     *
     * @param nwkUpdateId the nwkUpdateId
     */
    public void setNwkUpdateId(final Integer nwkUpdateId) {
        this.nwkUpdateId = nwkUpdateId;
    }

    /**
     * Gets nwkManagerAddr.
     *
     * @return the nwkManagerAddr
     */
    public Integer getNwkManagerAddr() {
        return nwkManagerAddr;
    }

    /**
     * Sets nwkManagerAddr.
     *
     * @param nwkManagerAddr the nwkManagerAddr
     */
    public void setNwkManagerAddr(final Integer nwkManagerAddr) {
        this.nwkManagerAddr = nwkManagerAddr;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(scanChannels, ZclDataType.BITMAP_32_BIT);
        serializer.serialize(scanDuration, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(scanCount, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(nwkUpdateId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(nwkManagerAddr, ZclDataType.NWK_ADDRESS);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        scanChannels = (Integer) deserializer.deserialize(ZclDataType.BITMAP_32_BIT);
        scanDuration = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        scanCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        nwkUpdateId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        nwkManagerAddr = (Integer) deserializer.deserialize(ZclDataType.NWK_ADDRESS);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("NetworkUpdateRequest");
        builder.append(super.toString());
        builder.append(", scanChannels=");
        builder.append(scanChannels);
        builder.append(", scanDuration=");
        builder.append(scanDuration);
        builder.append(", scanCount=");
        builder.append(scanCount);
        builder.append(", nwkUpdateId=");
        builder.append(nwkUpdateId);
        builder.append(", nwkManagerAddr=");
        builder.append(nwkManagerAddr);
        return builder.toString();
    }

}
