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
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackConfigId;

/**
 * Class to implement the Z-Stack command <b>ZB_READ_CONFIGURATION</b>.
 * <p>
 * This command is used to get a configuration property from non-volatile memory.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public class ZstackZbReadConfigurationSreq extends ZstackFrameRequest {

    /**
     * Specifies the Identifier for the configuration property.
     */
    private ZstackConfigId configId;

    /**
     * Request constructor
     */
    public ZstackZbReadConfigurationSreq() {
        synchronousCommand = true;
    }

    /**
     * Specifies the Identifier for the configuration property.
     *
     * @return the current configId as {@link ZstackConfigId}
     */
    public ZstackConfigId getConfigId() {
        return configId;
    }

    /**
     * Specifies the Identifier for the configuration property.
     *
     * @param configId the ConfigId to set as {@link ZstackConfigId}
     */
    public void setConfigId(ZstackConfigId configId) {
        this.configId = configId;
    }

    @Override
    public boolean matchSreqError(ZstackRpcSreqErrorSrsp response) {
        return (((response.getReqCmd0() & 0x1F) == ZSTACK_SAPI) && (response.getReqCmd1() == 0x04));
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(ZSTACK_SREQ, ZSTACK_SAPI, 0x04);

        // Serialize the fields
        serializer.serializeUInt16(configId.getKey());
        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(57);
        builder.append("ZstackZbReadConfigurationSreq [configId=");
        builder.append(configId);
        builder.append(']');
        return builder.toString();
    }
}
