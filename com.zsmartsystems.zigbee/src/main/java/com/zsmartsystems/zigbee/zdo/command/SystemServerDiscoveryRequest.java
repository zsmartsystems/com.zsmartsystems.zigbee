package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * System Server Discovery Request value object class.
 * <p>
 * The System_Server_Discovery_req is generated from a Local Device wishing to
 * discover the location of a particular system server or servers as indicated by the
 * ServerMask parameter. The destination addressing on this request is "broadcast to
 * all devices for which macRxOnWhenIdle = TRUE".
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class SystemServerDiscoveryRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public SystemServerDiscoveryRequest() {
        clusterId = 0x0015;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("SystemServerDiscoveryRequest ");
        builder.append(super.toString());
        return builder.toString();
    }

}
