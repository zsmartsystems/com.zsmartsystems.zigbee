/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;
import javax.annotation.Generated;

/**
 * Extended Active Endpoint Response value object class.
 * <p>
 * The Extended_Active_EP_rsp is generated by a remote device in response to an
 * Extended_Active_EP_req directed to the remote device. This command shall be
 * unicast to the originator of the Extended_Active_EP_req command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */

@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class ExtendedActiveEndpointResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public ExtendedActiveEndpointResponse() {
        clusterId = 0x801E;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(33);
        builder.append("ExtendedActiveEndpointResponse [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
