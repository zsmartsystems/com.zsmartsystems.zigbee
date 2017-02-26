package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
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
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ManagementPermitJoiningResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public ManagementPermitJoiningResponse() {
        clusterId = 0x8036;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(status, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        status = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ManagementPermitJoiningResponse");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        return builder.toString();
    }

}
