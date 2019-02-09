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
 * Active Endpoint Store Request value object class.
 * <p>
 * <p>
 * The Active_EP_store_req is provided to enable ZigBee end devices on the network to request
 * storage of their list of Active Endpoints on a Primary Discovery Cache device which has
 * previously received a SUCCESS status from a Discovery_store_req to the same Primary
 * Discovery Cache device. Included in this request is the count of Active Endpoints the Local
 * Device wishes to cache and the endpoint list itself.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ActiveEndpointStoreRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public ActiveEndpointStoreRequest() {
        clusterId = 0x0019;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(29);
        builder.append("ActiveEndpointStoreRequest [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
