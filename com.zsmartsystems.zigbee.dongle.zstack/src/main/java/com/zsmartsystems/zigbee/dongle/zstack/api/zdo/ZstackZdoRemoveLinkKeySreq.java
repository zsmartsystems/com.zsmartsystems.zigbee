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
import javax.annotation.Generated;

/**
 * Class to implement the Z-Stack command <b>ZDO_REMOVE_LINK_KEY</b>.
 * <p>
 * This command removes the application link key of a given device.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 */

@Generated(value = "com.zsmartsystems.zigbee.dongle.zstack.autocode.CommandGenerator", date = "Sun Mar 26 09:52:47 CEST 2023")
public class ZstackZdoRemoveLinkKeySreq extends ZstackFrameRequest {

    /**
     * Specifies the IEEE address of the pair device of the link key
     */
    private IeeeAddress ieeeAddr;

    /**
     * Request constructor
     */
    public ZstackZdoRemoveLinkKeySreq() {
        synchronousCommand = true;
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

    @Override
    public boolean matchSreqError(ZstackRpcSreqErrorSrsp response) {
        return (((response.getReqCmd0() & 0x1F) == ZSTACK_ZDO) && (response.getReqCmd1() == 0x24));
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(ZSTACK_SREQ, ZSTACK_ZDO, 0x24);

        // Serialize the fields
        serializer.serializeIeeeAddress(ieeeAddr);
        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(54);
        builder.append("ZstackZdoRemoveLinkKeySreq [ieeeAddr=");
        builder.append(ieeeAddr);
        builder.append(']');
        return builder.toString();
    }
}
