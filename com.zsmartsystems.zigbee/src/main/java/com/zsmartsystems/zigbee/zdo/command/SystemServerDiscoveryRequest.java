/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;
import javax.annotation.Generated;

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

@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class SystemServerDiscoveryRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public SystemServerDiscoveryRequest() {
        clusterId = 0x0015;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(31);
        builder.append("SystemServerDiscoveryRequest [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
