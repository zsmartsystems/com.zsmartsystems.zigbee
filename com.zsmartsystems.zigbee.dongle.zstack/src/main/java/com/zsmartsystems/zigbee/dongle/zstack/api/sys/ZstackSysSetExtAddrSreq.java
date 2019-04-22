/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.sys;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameRequest;
import com.zsmartsystems.zigbee.dongle.zstack.api.rpc.ZstackRpcSreqErrorSrsp;

/**
 * Class to implement the Z-Stack command <b>SYS_SET_EXT_ADDR</b>.
 * <p>
 * This command is used to set the extended address of the device.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public class ZstackSysSetExtAddrSreq extends ZstackFrameRequest {

    /**
     * The device’s extended address.
     */
    private IeeeAddress extAddress;

    /**
     * Request constructor
     */
    public ZstackSysSetExtAddrSreq() {
        synchronousCommand = true;
    }

    /**
     * The device’s extended address.
     *
     * @return the current extAddress as {@link IeeeAddress}
     */
    public IeeeAddress getExtAddress() {
        return extAddress;
    }

    /**
     * The device’s extended address.
     *
     * @param extAddress the ExtAddress to set as {@link IeeeAddress}
     */
    public void setExtAddress(IeeeAddress extAddress) {
        this.extAddress = extAddress;
    }

    @Override
    public boolean matchSreqError(ZstackRpcSreqErrorSrsp response) {
        return (((response.getReqCmd0() & 0x1F) == ZSTACK_SYS) && (response.getReqCmd1() == 0x03));
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(ZSTACK_SREQ, ZSTACK_SYS, 0x03);

        // Serialize the fields
        serializer.serializeIeeeAddress(extAddress);
        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(51);
        builder.append("ZstackSysSetExtAddrSreq [extAddress=");
        builder.append(extAddress);
        builder.append(']');
        return builder.toString();
    }
}
