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

/**
 * Class to implement the Z-Stack command <b>SYS_ZDIAGS_CLEAR_STATS</b>.
 * <p>
 * This command is used to clear the statistics table. To clear data in NV (including the Boot Counter) the clearNV flag shall be set
 * to TRUE.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public class ZstackSysZdiagsClearStatsSreq extends ZstackFrameRequest {

    /**
     * TRUE – Clears statistics in NV memory including Boot Counter. FALSE – Clears statistics in RAM only. Boot Counter is preserved.
     */
    private boolean clearNv;

    /**
     * Request constructor
     */
    public ZstackSysZdiagsClearStatsSreq() {
        synchronousCommand = true;
    }

    /**
     * TRUE – Clears statistics in NV memory including Boot Counter. FALSE – Clears statistics in RAM only. Boot Counter is preserved.
     *
     * @return the current clearNv as {@link boolean}
     */
    public boolean getClearNV() {
        return clearNv;
    }

    /**
     * TRUE – Clears statistics in NV memory including Boot Counter. FALSE – Clears statistics in RAM only. Boot Counter is preserved.
     *
     * @param clearNv the clearNV to set as {@link boolean}
     */
    public void setClearNV(boolean clearNv) {
        this.clearNv = clearNv;
    }

    @Override
    public boolean matchSreqError(ZstackRpcSreqErrorSrsp response) {
        return (((response.getReqCmd0() & 0x1F) == ZSTACK_SYS) && (response.getReqCmd1() == 0x18));
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(ZSTACK_SREQ, ZSTACK_SYS, 0x18);

        // Serialize the fields
        serializer.serializeBoolean(clearNv);
        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(57);
        builder.append("ZstackSysZdiagsClearStatsSreq [clearNv=");
        builder.append(clearNv);
        builder.append(']');
        return builder.toString();
    }
}
