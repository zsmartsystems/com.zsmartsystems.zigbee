/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.sapi;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameRequest;
import com.zsmartsystems.zigbee.dongle.zstack.api.rpc.ZstackRpcSreqErrorSrsp;

/**
 * Class to implement the Z-Stack command <b>ZB_GET_DEVICE_INFO</b>.
 * <p>
 * This command retrieves a Device Information Property.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public class ZstackZbGetDeviceInfoSreq extends ZstackFrameRequest {

    /**
     * The Identifier for the device information.
     */
    private int param;

    /**
     * Request constructor
     */
    public ZstackZbGetDeviceInfoSreq() {
        synchronousCommand = true;
    }

    /**
     * The Identifier for the device information.
     *
     * @return the current param as {@link int}
     */
    public int getParam() {
        return param;
    }

    /**
     * The Identifier for the device information.
     *
     * @param param the Param to set as {@link int}
     */
    public void setParam(int param) {
        this.param = param;
    }

    @Override
    public boolean matchSreqError(ZstackRpcSreqErrorSrsp response) {
        return (((response.getReqCmd0() & 0x1F) == ZSTACK_SAPI) && (response.getReqCmd1() == 0x06));
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(ZSTACK_SREQ, ZSTACK_SAPI, 0x06);

        // Serialize the fields
        serializer.serializeUInt8(param);
        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(53);
        builder.append("ZstackZbGetDeviceInfoSreq [param=");
        builder.append(param);
        builder.append(']');
        return builder.toString();
    }
}
