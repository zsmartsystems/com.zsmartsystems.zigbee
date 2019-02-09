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
 * Remove Node Cache value object class.
 * <p>
 * <p>
 * The Remove_node_cache_rsp is provided to notify a Local Device of the request status from a
 * Primary Discovery Cache device. Included in the response is a status code to notify the Local
 * Device whether the request is successful (the Primary Cache Device has removed the
 * discovery cache data for the indicated device of interest), or the request is not supported
 * (meaning the Remote Device is not a Primary Discovery Cache device).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class RemoveNodeCache extends ZdoRequest {
    /**
     * Default constructor.
     */
    public RemoveNodeCache() {
        clusterId = 0x801B;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(18);
        builder.append("RemoveNodeCache [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
