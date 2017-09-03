/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Bind Register Response value object class.
 * <p>
 * The Bind_Register_rsp is generated from a primary binding table cache device in
 * response to a Bind_Register_req and contains the status of the request. This
 * command shall be unicast to the requesting device.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class BindRegisterResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public BindRegisterResponse() {
        clusterId = 0x8023;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BindRegisterResponse [");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

}
