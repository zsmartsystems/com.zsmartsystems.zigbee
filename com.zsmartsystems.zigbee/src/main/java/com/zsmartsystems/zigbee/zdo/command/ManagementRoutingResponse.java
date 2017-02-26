package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Management Routing Response value object class.
 * <p>
 * The Mgmt_Rtg_rsp is generated in response to an Mgmt_Rtg_req. If this
 * management command is not supported, a status of NOT_SUPPORTED shall be
 * returned and all parameter fields after the Status field shall be omitted. Otherwise,
 * the Remote Device shall implement the following processing.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ManagementRoutingResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public ManagementRoutingResponse() {
        clusterId = 0x8032;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ManagementRoutingResponse");
        builder.append(super.toString());
        return builder.toString();
    }

}
