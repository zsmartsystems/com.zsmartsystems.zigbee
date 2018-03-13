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
 * Active Endpoint Store Response value object class.
 * <p>
 * The Active_EP_store_rsp is provided to notify a Local Device of the request
 * status from a Primary Discovery Cache device. Included in the response is a status
 * code to notify the Local Device whether the request is successful (the Primary
 * Cache Device has space to store the discovery cache data for the Local Device),
 * the request is not supported (meaning the Remote Device is not a Primary
 * Discovery Cache device), or insufficient space exists.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */

@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class ActiveEndpointStoreResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public ActiveEndpointStoreResponse() {
        clusterId = 0x8019;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(30);
        builder.append("ActiveEndpointStoreResponse [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
