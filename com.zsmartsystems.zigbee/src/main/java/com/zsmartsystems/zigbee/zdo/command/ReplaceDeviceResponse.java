/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;

/**
 * Replace Device Response value object class.
 * <p>
 * <p>
 * The Replace_Device_rsp is generated from a primary binding table cache device in response
 * to a Replace_Device_req and contains the status of the request. This command shall be
 * unicast to the requesting device. If the device receiving the Replace_Device_req is not a
 * primary binding table cache, a Status of NOT_SUPPORTED is returned. The primary binding
 * table cache shall search its binding table for entries whose source address and source
 * endpoint, or whose destination address and destination endpoint match OldAddress and
 * OldEndpoint, as described in the text for Replace_Device_req. It shall change these
 * entries to have NewAddress and possibly NewEndpoint. It shall then return a response of
 * SUCCESS.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T17:07:08Z")
public class ReplaceDeviceResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public ReplaceDeviceResponse() {
        clusterId = 0x8024;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(status, ZclDataType.ZDO_STATUS);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        status = (ZdoStatus) deserializer.deserialize(ZclDataType.ZDO_STATUS);
        if (status != ZdoStatus.SUCCESS) {
            // Don't read the full response if we have an error
            return;
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(50);
        builder.append("ReplaceDeviceResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(']');
        return builder.toString();
    }

}
