package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Management Permit Joining Response value object class.
 * <p>
 * The Mgmt_Permit_Joining_rsp is generated in response to a unicast
 * Mgmt_Permit_Joining_req. In the description which follows, note that no
 * response shall be sent if the Mgmt_Permit_Joining_req was received as a
 * broadcast to all routers. If this management command is not permitted by the
 * requesting device, a status of INVALID_REQUEST shall be returned. Upon
 * receipt and after support for Mgmt_Permit_Joining_req has been verified, the
 * Remote Device shall execute the NLME-PERMIT-JOINING.request. The
 * Mgmt_Permit-Joining_rsp shall contain the same status that was contained in the
 * NLME-PERMIT-JOINING.confirm primitive.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ManagementPermitJoiningResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public ManagementPermitJoiningResponse() {
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ManagementPermitJoiningResponse");
        builder.append(super.toString());
        return builder.toString();
    }

}
