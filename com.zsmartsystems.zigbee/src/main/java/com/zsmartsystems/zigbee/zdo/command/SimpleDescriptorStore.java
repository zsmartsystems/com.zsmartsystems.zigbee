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
 * Simple Descriptor Store value object class.
 * <p>
 * <p>
 * The Simple_desc_store_req is provided to enable ZigBee end devices on the network to
 * request storage of their list of Simple Descriptors on a Primary Discovery Cache device
 * which has previously received a SUCCESS status from a Discovery_store_req to the same
 * Primary Discovery Cache device. Note that each Simple Descriptor for every active endpoint
 * on the Local Device must be individually uploaded to the Primary Discovery Cache device via
 * this command to enable cached discovery. Included in this request is the length of the Simple
 * Descriptor the Local Device wishes to cache and the Simple Descriptor itself. The endpoint
 * is a field within the Simple Descriptor and is accessed by the Remote Device to manage the
 * discovery cache information for the Local Device.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class SimpleDescriptorStore extends ZdoRequest {
    /**
     * Default constructor.
     */
    public SimpleDescriptorStore() {
        clusterId = 0x001A;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(24);
        builder.append("SimpleDescriptorStore [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
