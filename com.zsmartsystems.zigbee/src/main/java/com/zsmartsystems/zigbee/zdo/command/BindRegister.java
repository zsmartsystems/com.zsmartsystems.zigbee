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
 * Bind Register value object class.
 * <p>
 * The Bind_Register_req is generated from a Local Device and sent to a primary
 * binding table cache device to register that the local device wishes to hold its own
 * binding table entries. The destination addressing mode for this request is unicast.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */

@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class BindRegister extends ZdoResponse {
    /**
     * Default constructor.
     */
    public BindRegister() {
        clusterId = 0x0023;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(15);
        builder.append("BindRegister [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
