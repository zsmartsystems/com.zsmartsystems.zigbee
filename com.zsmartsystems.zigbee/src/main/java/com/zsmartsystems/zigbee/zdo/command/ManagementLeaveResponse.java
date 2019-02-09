/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;

/**
 * Management Leave Response value object class.
 * <p>
 * <p>
 * The Mgmt_Leave_rsp is generated in response to a Mgmt_Leave_req. If this management
 * command is not supported, a status of NOT_SUPPORTED shall be returned. Otherwise, the
 * Remote Device shall implement the following processing. <br> Upon receipt of and after
 * support for the Mgmt_Leave_req has been verified, the Remote Device shall execute the
 * NLME-LEAVE.request to disassociate from the currently associated network. The
 * Mgmt_Leave_rsp shall contain the same status that was contained in the NLME-LEAVE.confirm
 * primitive. <br> Once a device has disassociated, it may execute pre-programmed logic to
 * perform NLME-NETWORK-DISCOVERY and NLME-JOIN to join/re-join a network.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T17:07:08Z")
public class ManagementLeaveResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public ManagementLeaveResponse() {
        clusterId = 0x8034;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(status, ZclDataType.ZDO_STATUS);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        status = (ZdoStatus) deserializer.deserialize(ZclDataType.ZDO_STATUS);
        if (status != ZdoStatus.SUCCESS) {
            // Don't read the full response if we have an error
            return;
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(52);
        builder.append("ManagementLeaveResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(']');
        return builder.toString();
    }

}
