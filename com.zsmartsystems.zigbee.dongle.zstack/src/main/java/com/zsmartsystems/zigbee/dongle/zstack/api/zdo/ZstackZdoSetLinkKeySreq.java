/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.zdo;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameRequest;
import com.zsmartsystems.zigbee.dongle.zstack.api.rpc.ZstackRpcSreqErrorSrsp;
import com.zsmartsystems.zigbee.security.ZigBeeKey;

/**
 * Class to implement the Z-Stack command <b>ZDO_SET_LINK_KEY</b>.
 * <p>
 * This command sets the application link key for a given device.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public class ZstackZdoSetLinkKeySreq extends ZstackFrameRequest {

    /**
     * Specifies the short address of the pair device of the link key.
     */
    private int shortAddr;

    /**
     * Specifies the IEEE address of the pair device of the link key
     */
    private IeeeAddress ieeeAddr;

    /**
     * 128 bit link key data of the device.
     */
    private ZigBeeKey linkKeyData;

    /**
     * Request constructor
     */
    public ZstackZdoSetLinkKeySreq() {
        synchronousCommand = true;
    }

    /**
     * Specifies the short address of the pair device of the link key.
     *
     * @return the current shortAddr as {@link int}
     */
    public int getShortAddr() {
        return shortAddr;
    }

    /**
     * Specifies the short address of the pair device of the link key.
     *
     * @param shortAddr the ShortAddr to set as {@link int}
     */
    public void setShortAddr(int shortAddr) {
        this.shortAddr = shortAddr;
    }

    /**
     * Specifies the IEEE address of the pair device of the link key
     *
     * @return the current ieeeAddr as {@link IeeeAddress}
     */
    public IeeeAddress getIeeeAddr() {
        return ieeeAddr;
    }

    /**
     * Specifies the IEEE address of the pair device of the link key
     *
     * @param ieeeAddr the IeeeAddr to set as {@link IeeeAddress}
     */
    public void setIeeeAddr(IeeeAddress ieeeAddr) {
        this.ieeeAddr = ieeeAddr;
    }

    /**
     * 128 bit link key data of the device.
     *
     * @return the current linkKeyData as {@link ZigBeeKey}
     */
    public ZigBeeKey getLinkKeyData() {
        return linkKeyData;
    }

    /**
     * 128 bit link key data of the device.
     *
     * @param linkKeyData the LinkKeyData to set as {@link ZigBeeKey}
     */
    public void setLinkKeyData(ZigBeeKey linkKeyData) {
        this.linkKeyData = linkKeyData;
    }

    @Override
    public boolean matchSreqError(ZstackRpcSreqErrorSrsp response) {
        return (((response.getReqCmd0() & 0x1F) == ZSTACK_ZDO) && (response.getReqCmd1() == 0x23));
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(ZSTACK_SREQ, ZSTACK_ZDO, 0x23);

        // Serialize the fields
        serializer.serializeUInt16(shortAddr);
        serializer.serializeIeeeAddress(ieeeAddr);
        serializer.serializeZigBeeKey(linkKeyData);
        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(101);
        builder.append("ZstackZdoSetLinkKeySreq [shortAddr=");
        builder.append(String.format("%04X", shortAddr));
        builder.append(", ieeeAddr=");
        builder.append(ieeeAddr);
        builder.append(", linkKeyData=");
        builder.append(linkKeyData);
        builder.append(']');
        return builder.toString();
    }
}
