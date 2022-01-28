/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspSerializer;

/**
 * Class to implement the Ember EZSP command <b>energyScanRequest</b>.
 * <p>
 * Sends a ZDO energy scan request. This request may only be sent by the current network manager
 * and must be unicast, not broadcast. See ezsp-utils.h for related macros
 * emberSetNetworkManagerRequest() and emberChangeChannelRequest()
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspEnergyScanRequestRequest extends EzspFrameRequest {
    public static final int FRAME_ID = 0x9C;

    /**
     * The network address of the node to perform the scan.
     * <p>
     * EZSP type is <i>EmberNodeId</i> - Java type is {@link int}
     */
    private int target;

    /**
     * A mask of the channels to be scanned.
     * <p>
     * EZSP type is <i>uint32_t</i> - Java type is {@link int}
     */
    private int scanChannels;

    /**
     * How long to scan on each channel. Allowed values are 0..5, with the scan times as specified by
     * 802.15.4 (0 = 31ms, 1 = 46ms, 2 = 77ms, 3 = 138ms, 4 = 261ms, 5 = 507ms).
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int scanDuration;

    /**
     * The number of scans to be performed on each channel (1..8).
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     */
    private int scanCount;

    /**
     * Serialiser used to serialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspEnergyScanRequestRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }

    /**
     * The network address of the node to perform the scan.
     * <p>
     * EZSP type is <i>EmberNodeId</i> - Java type is {@link int}
     *
     * @return the current target as {@link int}
     */
    public int getTarget() {
        return target;
    }

    /**
     * The network address of the node to perform the scan.
     *
     * @param target the target to set as {@link int}
     */
    public void setTarget(int target) {
        this.target = target;
    }

    /**
     * A mask of the channels to be scanned.
     * <p>
     * EZSP type is <i>uint32_t</i> - Java type is {@link int}
     *
     * @return the current scanChannels as {@link int}
     */
    public int getScanChannels() {
        return scanChannels;
    }

    /**
     * A mask of the channels to be scanned.
     *
     * @param scanChannels the scanChannels to set as {@link int}
     */
    public void setScanChannels(int scanChannels) {
        this.scanChannels = scanChannels;
    }

    /**
     * How long to scan on each channel. Allowed values are 0..5, with the scan times as specified by
     * 802.15.4 (0 = 31ms, 1 = 46ms, 2 = 77ms, 3 = 138ms, 4 = 261ms, 5 = 507ms).
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current scanDuration as {@link int}
     */
    public int getScanDuration() {
        return scanDuration;
    }

    /**
     * How long to scan on each channel. Allowed values are 0..5, with the scan times as specified by
     * 802.15.4 (0 = 31ms, 1 = 46ms, 2 = 77ms, 3 = 138ms, 4 = 261ms, 5 = 507ms).
     *
     * @param scanDuration the scanDuration to set as {@link int}
     */
    public void setScanDuration(int scanDuration) {
        this.scanDuration = scanDuration;
    }

    /**
     * The number of scans to be performed on each channel (1..8).
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     *
     * @return the current scanCount as {@link int}
     */
    public int getScanCount() {
        return scanCount;
    }

    /**
     * The number of scans to be performed on each channel (1..8).
     *
     * @param scanCount the scanCount to set as {@link int}
     */
    public void setScanCount(int scanCount) {
        this.scanCount = scanCount;
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(serializer);

        // Serialize the fields
        serializer.serializeUInt16(target);
        serializer.serializeUInt32(scanChannels);
        serializer.serializeUInt8(scanDuration);
        serializer.serializeUInt16(scanCount);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(156);
        builder.append("EzspEnergyScanRequestRequest [networkId=");
        builder.append(networkId);
        builder.append(", target=");
        builder.append(String.format("%04X", target));
        builder.append(", scanChannels=");
        builder.append(String.format("%08X", scanChannels));
        builder.append(", scanDuration=");
        builder.append(scanDuration);
        builder.append(", scanCount=");
        builder.append(scanCount);
        builder.append(']');
        return builder.toString();
    }
}
