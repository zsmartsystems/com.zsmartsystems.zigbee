package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Management Leave Response value object class.
 * <p>
 * The Mgmt_Leave_rsp is generated in response to a Mgmt_Leave_req. If this
 * management command is not supported, a status of NOT_SUPPORTED shall be
 * returned. Otherwise, the Remote Device shall implement the following
 * processing.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ManagementLeaveResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public ManagementLeaveResponse() {
        clusterId = 0x8034;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ManagementLeaveResponse ");
        builder.append(super.toString());
        return builder.toString();
    }

}
