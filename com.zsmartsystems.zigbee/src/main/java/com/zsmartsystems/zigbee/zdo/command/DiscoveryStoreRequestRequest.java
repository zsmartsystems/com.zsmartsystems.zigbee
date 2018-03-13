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
 * Discovery Store Request Request value object class.
 * <p>
 * The Discovery_store_req is provided to enable ZigBee end devices on the
 * network to request storage of their discovery cache information on a Primary
 * Discovery Cache device. Included in the request is the amount of storage space
 * the Local Device requires.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */

@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class DiscoveryStoreRequestRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public DiscoveryStoreRequestRequest() {
        clusterId = 0x0016;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(31);
        builder.append("DiscoveryStoreRequestRequest [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
