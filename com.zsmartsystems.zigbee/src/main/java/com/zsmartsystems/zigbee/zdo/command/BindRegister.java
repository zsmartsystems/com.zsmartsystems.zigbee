package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Bind Register value object class.
 * <p>
 * The Bind_Register_req is generated from a Local Device and sent to a primary
 * binding table cache device to register that the local device wishes to hold its own
 * binding table entries. The destination addressing mode for this request is unicast.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class BindRegister extends ZdoResponse {
    /**
     * Default constructor.
     */
    public BindRegister() {
        clusterId = 0x0023;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BindRegister ");
        builder.append(super.toString());
        return builder.toString();
    }

}
