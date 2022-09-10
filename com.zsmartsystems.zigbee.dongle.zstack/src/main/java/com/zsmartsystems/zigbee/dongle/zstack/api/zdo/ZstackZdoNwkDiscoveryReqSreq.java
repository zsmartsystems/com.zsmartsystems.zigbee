/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.zdo;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameRequest;
import com.zsmartsystems.zigbee.dongle.zstack.api.rpc.ZstackRpcSreqErrorSrsp;

/**
 * Class to implement the Z-Stack command <b>ZDO_NWK_DISCOVERY_REQ</b>.
 * <p>
 * This command is used to initiate a network discovery (active scan).
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public class ZstackZdoNwkDiscoveryReqSreq extends ZstackFrameRequest {

    /**
     * Bit mask for channels to scan.
     */
    private int scanChannels;

    /**
     * A value used to calculate the length of time to spend scanning each channel
     */
    private int scanDuration;

    /**
     * Request constructor
     */
    public ZstackZdoNwkDiscoveryReqSreq() {
        synchronousCommand = true;
    }

    /**
     * Bit mask for channels to scan.
     *
     * @return the current scanChannels as {@link int}
     */
    public int getScanChannels() {
        return scanChannels;
    }

    /**
     * Bit mask for channels to scan.
     *
     * @param scanChannels the ScanChannels to set as {@link int}
     */
    public void setScanChannels(int scanChannels) {
        this.scanChannels = scanChannels;
    }

    /**
     * A value used to calculate the length of time to spend scanning each channel
     *
     * @return the current scanDuration as {@link int}
     */
    public int getScanDuration() {
        return scanDuration;
    }

    /**
     * A value used to calculate the length of time to spend scanning each channel
     *
     * @param scanDuration the ScanDuration to set as {@link int}
     */
    public void setScanDuration(int scanDuration) {
        this.scanDuration = scanDuration;
    }

    @Override
    public boolean matchSreqError(ZstackRpcSreqErrorSrsp response) {
        return (((response.getReqCmd0() & 0x1F) == ZSTACK_ZDO) && (response.getReqCmd1() == 0x26));
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(ZSTACK_SREQ, ZSTACK_ZDO, 0x26);

        // Serialize the fields
        serializer.serializeUInt32(scanChannels);
        serializer.serializeUInt8(scanDuration);
        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(81);
        builder.append("ZstackZdoNwkDiscoveryReqSreq [scanChannels=");
        builder.append(scanChannels);
        builder.append(", scanDuration=");
        builder.append(scanDuration);
        builder.append(']');
        return builder.toString();
    }
}
