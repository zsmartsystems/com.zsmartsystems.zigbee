package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;

/**
 * Unbind Response value object class.
 * <p>
 * The Unbind_rsp is generated in response to an Unbind_req. If the Unbind_req is
 * processed and the corresponding Binding Table entry is removed from the Remote
 * Device, a Status of SUCCESS is returned. If the Remote Device is not the ZigBee
 * Coordinator or the SrcAddress, a Status of NOT_SUPPORTED is returned. The
 * supplied endpoint shall be checked to determine whether it falls within the
 * specified range. If it does not, a Status of INVALID_EP shall be returned If the
 * Remote Device is the ZigBee Coordinator or SrcAddress but does not have a
 * Binding Table entry corresponding to the parameters received in the request, a
 * Status of NO_ENTRY is returned.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class UnbindResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public UnbindResponse() {
        clusterId = 0x8022;
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
        builder.append("UnbindResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append("]");
        return builder.toString();
    }

}
