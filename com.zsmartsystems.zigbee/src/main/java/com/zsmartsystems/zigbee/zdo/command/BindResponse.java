package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Bind Response value object class.
 * <p>
 * The Bind_rsp is generated in response to a Bind_req. If the Bind_req is processed
 * and the Binding Table entry committed on the Remote Device, a Status of
 * SUCCESS is returned. If the Remote Device is not a Primary binding table cache
 * or the SrcAddress, a Status of NOT_SUPPORTED is returned. The supplied
 * endpoint shall be checked to determine whether it falls within the specified range.
 * If it does not, a Status of INVALID_EP shall be returned. If the Remote Device is
 * the Primary binding table cache or SrcAddress but does not have Binding Table
 * resources for the request, a Status of TABLE_FULL is returned.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class BindResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public BindResponse() {
        clusterId = 0x8021;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BindResponse");
        builder.append(super.toString());
        return builder.toString();
    }

}
