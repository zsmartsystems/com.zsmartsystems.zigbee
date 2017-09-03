/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Match Descriptor Request value object class.
 * <p>
 * The Match_Desc_req command is generated from a local device wishing to find
 * remote devices supporting a specific simple descriptor match criterion. This
 * command shall either be broadcast to all devices for which macRxOnWhenIdle =
 * TRUE, or unicast. If the command is unicast, it shall be directed either to the
 * remote device itself or to an alternative device that contains the discovery
 * information of the remote device.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class MatchDescriptorRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public MatchDescriptorRequest() {
        clusterId = 0x0006;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("MatchDescriptorRequest [");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

}
