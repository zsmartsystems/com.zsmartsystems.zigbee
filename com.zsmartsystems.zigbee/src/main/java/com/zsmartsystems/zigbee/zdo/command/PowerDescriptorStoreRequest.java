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
 * Power Descriptor Store Request value object class.
 * <p>
 * The Power_Desc_store_req is provided to enable ZigBee end devices on the
 * network to request storage of their Power Descriptor on a Primary Discovery
 * Cache device which has previously received a SUCCESS status from a
 * Discovery_store_req to the same Primary Discovery Cache device. Included in
 * this request is the Power Descriptor the Local Device wishes to cache.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */

@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class PowerDescriptorStoreRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public PowerDescriptorStoreRequest() {
        clusterId = 0x0018;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(30);
        builder.append("PowerDescriptorStoreRequest [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
