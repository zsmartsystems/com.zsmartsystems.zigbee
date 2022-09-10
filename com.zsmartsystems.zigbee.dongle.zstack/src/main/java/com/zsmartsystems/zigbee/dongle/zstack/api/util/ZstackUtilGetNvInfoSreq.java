/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.util;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameRequest;
import com.zsmartsystems.zigbee.dongle.zstack.api.rpc.ZstackRpcSreqErrorSrsp;

/**
 * Class to implement the Z-Stack command <b>UTIL_GET_NV_INFO</b>.
 * <p>
 * This command is used to read a block of parameters from non-volatile storage of the target device.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public class ZstackUtilGetNvInfoSreq extends ZstackFrameRequest {

    /**
     * Request constructor
     */
    public ZstackUtilGetNvInfoSreq() {
        synchronousCommand = true;
    }

    @Override
    public boolean matchSreqError(ZstackRpcSreqErrorSrsp response) {
        return (((response.getReqCmd0() & 0x1F) == ZSTACK_UTIL) && (response.getReqCmd1() == 0x01));
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(ZSTACK_SREQ, ZSTACK_UTIL, 0x01);

        // Serialize the fields
        return getPayload();
    }

    @Override
    public String toString() {
        return "ZstackUtilGetNvInfoSreq []";
    }
}
