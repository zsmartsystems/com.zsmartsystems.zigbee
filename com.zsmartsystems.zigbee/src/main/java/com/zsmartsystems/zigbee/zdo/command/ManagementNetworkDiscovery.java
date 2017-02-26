package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Management Network Discovery value object class.
 * <p>
 * The Mgmt_NWK_Disc_req is generated from a Local Device requesting that the
 * Remote Device execute a Scan to report back networks in the vicinity of the Local
 * Device. The destination addressing on this command shall be unicast.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ManagementNetworkDiscovery extends ZdoResponse {
    /**
     * ScanChannels command message field.
     */
    private Integer scanChannels;

    /**
     * ScanDuration command message field.
     */
    private Integer scanDuration;

    /**
     * StartIndex command message field.
     */
    private Integer startIndex;

    /**
     * Default constructor.
     */
    public ManagementNetworkDiscovery() {
        clusterId = 0x0030;
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
     * Gets StartIndex.
     *
     * @return the StartIndex
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * Sets StartIndex.
     *
     * @param startIndex the StartIndex
     */
    public void setStartIndex(final Integer startIndex) {
        this.startIndex = startIndex;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(scanChannels, ZclDataType.BITMAP_32_BIT);
        serializer.serialize(scanDuration, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(startIndex, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        scanChannels = (Integer) deserializer.deserialize(ZclDataType.BITMAP_32_BIT);
        scanDuration = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        startIndex = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ManagementNetworkDiscovery");
        builder.append(super.toString());
        builder.append(", scanChannels=");
        builder.append(scanChannels);
        builder.append(", scanDuration=");
        builder.append(scanDuration);
        builder.append(", startIndex=");
        builder.append(startIndex);
        return builder.toString();
    }

}
