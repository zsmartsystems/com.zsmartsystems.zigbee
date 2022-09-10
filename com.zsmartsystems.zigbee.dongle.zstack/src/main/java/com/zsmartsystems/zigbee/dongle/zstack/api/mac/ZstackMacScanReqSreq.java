/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.mac;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameRequest;
import com.zsmartsystems.zigbee.dongle.zstack.api.rpc.ZstackRpcSreqErrorSrsp;

/**
 * Class to implement the Z-Stack command <b>MAC_SCAN_REQ</b>.
 * <p>
 * This command is used to send a request to the device to perform a network scan.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public class ZstackMacScanReqSreq extends ZstackFrameRequest {

    /**
     * This represents a bit-mask of channels to be scanned when starting the device: NONE = 0x00000000, ALL_CHANNELS = 0x07FFF800,
     * CHANNEL 11 = 0x00000800, CHANNEL 12 = 0x00001000, CHANNEL 13 = 0x00002000, CHANNEL 14 = 0x00004000, CHANNEL 15 = 0x00008000,
     * CHANNEL 16 = 0x00010000, CHANNEL 17 = 0x00020000, CHANNEL 18 = 0x00040000, CHANNEL_19 = 0x00080000, CHANNEL 20 = 0x00100000,
     * CHANNEL 21 = 0x00200000, CHANNEL 22 = 0x00400000, CHANNEL 23 = 0x00800000, CHANNEL 24 = 0x01000000 CHANNEL 25 = 0x02000000,
     * CHANNEL 26 = 0x04000000
     */
    private int scanChannels;

    /**
     * Specifies the scan type:
     */
    private int scanType;

    /**
     * Duration of the scan - The exponent used in the scan duration calculation.
     */
    private int scanDuration;

    /**
     * The channel page on which to perform the scan.
     */
    private int channelPage;

    /**
     * Key Source of this data frame.
     */
    private int keySource;

    /**
     * Security Level of this data frame:
     */
    private int securityLevel;

    /**
     * Key Id Mode of this data frame:
     */
    private int keyIdMode;

    /**
     * Key Index of this data frame.
     */
    private int keyIndex;

    /**
     * Request constructor
     */
    public ZstackMacScanReqSreq() {
        synchronousCommand = true;
    }

    /**
     * This represents a bit-mask of channels to be scanned when starting the device: NONE = 0x00000000, ALL_CHANNELS = 0x07FFF800,
     * CHANNEL 11 = 0x00000800, CHANNEL 12 = 0x00001000, CHANNEL 13 = 0x00002000, CHANNEL 14 = 0x00004000, CHANNEL 15 = 0x00008000,
     * CHANNEL 16 = 0x00010000, CHANNEL 17 = 0x00020000, CHANNEL 18 = 0x00040000, CHANNEL_19 = 0x00080000, CHANNEL 20 = 0x00100000,
     * CHANNEL 21 = 0x00200000, CHANNEL 22 = 0x00400000, CHANNEL 23 = 0x00800000, CHANNEL 24 = 0x01000000 CHANNEL 25 = 0x02000000,
     * CHANNEL 26 = 0x04000000
     *
     * @return the current scanChannels as {@link int}
     */
    public int getScanChannels() {
        return scanChannels;
    }

    /**
     * This represents a bit-mask of channels to be scanned when starting the device: NONE = 0x00000000, ALL_CHANNELS = 0x07FFF800,
     * CHANNEL 11 = 0x00000800, CHANNEL 12 = 0x00001000, CHANNEL 13 = 0x00002000, CHANNEL 14 = 0x00004000, CHANNEL 15 = 0x00008000,
     * CHANNEL 16 = 0x00010000, CHANNEL 17 = 0x00020000, CHANNEL 18 = 0x00040000, CHANNEL_19 = 0x00080000, CHANNEL 20 = 0x00100000,
     * CHANNEL 21 = 0x00200000, CHANNEL 22 = 0x00400000, CHANNEL 23 = 0x00800000, CHANNEL 24 = 0x01000000 CHANNEL 25 = 0x02000000,
     * CHANNEL 26 = 0x04000000
     *
     * @param scanChannels the ScanChannels to set as {@link int}
     */
    public void setScanChannels(int scanChannels) {
        this.scanChannels = scanChannels;
    }

    /**
     * Specifies the scan type:
     *
     * @return the current scanType as {@link int}
     */
    public int getScanType() {
        return scanType;
    }

    /**
     * Specifies the scan type:
     *
     * @param scanType the ScanType to set as {@link int}
     */
    public void setScanType(int scanType) {
        this.scanType = scanType;
    }

    /**
     * Duration of the scan - The exponent used in the scan duration calculation.
     *
     * @return the current scanDuration as {@link int}
     */
    public int getScanDuration() {
        return scanDuration;
    }

    /**
     * Duration of the scan - The exponent used in the scan duration calculation.
     *
     * @param scanDuration the ScanDuration to set as {@link int}
     */
    public void setScanDuration(int scanDuration) {
        this.scanDuration = scanDuration;
    }

    /**
     * The channel page on which to perform the scan.
     *
     * @return the current channelPage as {@link int}
     */
    public int getChannelPage() {
        return channelPage;
    }

    /**
     * The channel page on which to perform the scan.
     *
     * @param channelPage the ChannelPage to set as {@link int}
     */
    public void setChannelPage(int channelPage) {
        this.channelPage = channelPage;
    }

    /**
     * Key Source of this data frame.
     *
     * @return the current keySource as {@link int}
     */
    public int getKeySource() {
        return keySource;
    }

    /**
     * Key Source of this data frame.
     *
     * @param keySource the KeySource to set as {@link int}
     */
    public void setKeySource(int keySource) {
        this.keySource = keySource;
    }

    /**
     * Security Level of this data frame:
     *
     * @return the current securityLevel as {@link int}
     */
    public int getSecurityLevel() {
        return securityLevel;
    }

    /**
     * Security Level of this data frame:
     *
     * @param securityLevel the SecurityLevel to set as {@link int}
     */
    public void setSecurityLevel(int securityLevel) {
        this.securityLevel = securityLevel;
    }

    /**
     * Key Id Mode of this data frame:
     *
     * @return the current keyIdMode as {@link int}
     */
    public int getKeyIdMode() {
        return keyIdMode;
    }

    /**
     * Key Id Mode of this data frame:
     *
     * @param keyIdMode the KeyIdMode to set as {@link int}
     */
    public void setKeyIdMode(int keyIdMode) {
        this.keyIdMode = keyIdMode;
    }

    /**
     * Key Index of this data frame.
     *
     * @return the current keyIndex as {@link int}
     */
    public int getKeyIndex() {
        return keyIndex;
    }

    /**
     * Key Index of this data frame.
     *
     * @param keyIndex the KeyIndex to set as {@link int}
     */
    public void setKeyIndex(int keyIndex) {
        this.keyIndex = keyIndex;
    }

    @Override
    public boolean matchSreqError(ZstackRpcSreqErrorSrsp response) {
        return (((response.getReqCmd0() & 0x1F) == ZSTACK_MAC) && (response.getReqCmd1() == 0x0C));
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(ZSTACK_SREQ, ZSTACK_MAC, 0x0C);

        // Serialize the fields
        serializer.serializeUInt32(scanChannels);
        serializer.serializeUInt8(scanType);
        serializer.serializeUInt8(scanDuration);
        serializer.serializeUInt8(channelPage);
        serializer.serializeUInt8(keySource);
        serializer.serializeUInt8(securityLevel);
        serializer.serializeUInt8(keyIdMode);
        serializer.serializeUInt8(keyIndex);
        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(223);
        builder.append("ZstackMacScanReqSreq [scanChannels=");
        builder.append(scanChannels);
        builder.append(", scanType=");
        builder.append(scanType);
        builder.append(", scanDuration=");
        builder.append(scanDuration);
        builder.append(", channelPage=");
        builder.append(channelPage);
        builder.append(", keySource=");
        builder.append(keySource);
        builder.append(", securityLevel=");
        builder.append(securityLevel);
        builder.append(", keyIdMode=");
        builder.append(keyIdMode);
        builder.append(", keyIndex=");
        builder.append(keyIndex);
        builder.append(']');
        return builder.toString();
    }
}
