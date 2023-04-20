/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.sys;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameRequest;
import com.zsmartsystems.zigbee.dongle.zstack.api.rpc.ZstackRpcSreqErrorSrsp;
import javax.annotation.Generated;

/**
 * Class to implement the Z-Stack command <b>SYS_PING</b>.
 * <p>
 * This command issues PING requests to verify if a device is active and check the capability of the device.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 */

@Generated(value = "com.zsmartsystems.zigbee.dongle.zstack.autocode.CommandGenerator", date = "Sun Mar 26 09:52:47 CEST 2023")
public class ZstackSysPingSreq extends ZstackFrameRequest {

    /**
     * Request constructor
     */
    public ZstackSysPingSreq() {
        synchronousCommand = true;
    }

    @Override
    public boolean matchSreqError(ZstackRpcSreqErrorSrsp response) {
        return (((response.getReqCmd0() & 0x1F) == ZSTACK_SYS) && (response.getReqCmd1() == 0x01));
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(ZSTACK_SREQ, ZSTACK_SYS, 0x01);

        // Serialize the fields
        return getPayload();
    }

    @Override
    public String toString() {
        return "ZstackSysPingSreq []";
    }
}
