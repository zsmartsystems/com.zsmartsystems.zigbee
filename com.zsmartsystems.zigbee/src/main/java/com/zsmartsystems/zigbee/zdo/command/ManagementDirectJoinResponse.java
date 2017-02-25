package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Management Direct Join Response value object class.
 * <p>
 * The Mgmt_Direct_Join_rsp is generated in response to a Mgmt_Direct_Join_req.
 * If this management command is not supported, a status of NOT_SUPPORTED
 * shall be returned. Otherwise, the Remote Device shall implement the following
 * processing.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ManagementDirectJoinResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public ManagementDirectJoinResponse() {
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ManagementDirectJoinResponse");
        builder.append(super.toString());
        return builder.toString();
    }

}
