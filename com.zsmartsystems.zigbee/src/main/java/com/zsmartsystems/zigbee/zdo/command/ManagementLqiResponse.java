package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Management LQI Response value object class.
 * <p>
 * The Mgmt_Lqi_rsp is generated in response to an Mgmt_Lqi_req. If this
 * management command is not supported, a status of NOT_SUPPORTED shall be
 * returned and all parameter fields after the Status field shall be omitted. Otherwise,
 * the Remote Device shall implement the following processing.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ManagementLqiResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public ManagementLqiResponse() {
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ManagementLqiResponse");
        builder.append(super.toString());
        return builder.toString();
    }

}
