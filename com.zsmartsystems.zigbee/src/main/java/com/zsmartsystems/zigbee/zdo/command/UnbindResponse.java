package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;

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
        serializer.serialize(status, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        status = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UnbindResponse");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        return builder.toString();
    }

}
