/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Extended Active Endpoint Request value object class.
 * <p>
 * <p>
 * The Extended_Active_EP_req command is generated from a local device wishing to acquire the
 * list of endpoints on a remote device with simple descriptors. This command shall be unicast
 * either to the remote device itself or to an alternative device that contains the discovery
 * information of the remote device. The Extended_Active_EP_req is used for devices which
 * support more active endpoints than can be returned by a single Active_EP_req. <br> The
 * NWKAddrOfInterest field shall contain the network address of the remote device for which
 * the active endpoint list is required. The StartIndex field shall be set in the request to
 * enable retrieval of lists of active endpoints from devices whose list exceeds the size of a
 * single ASDU and where fragmentation is not supported.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ExtendedActiveEndpointRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public ExtendedActiveEndpointRequest() {
        clusterId = 0x001E;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(32);
        builder.append("ExtendedActiveEndpointRequest [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
