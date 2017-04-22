package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;

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
        final StringBuilder builder = new StringBuilder();
        builder.append("ManagementLeaveResponse");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        return builder.toString();
    }

}
