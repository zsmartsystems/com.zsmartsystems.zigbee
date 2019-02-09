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
 * Bind Response value object class.
 * <p>
 * <p>
 * The Bind_rsp is generated in response to a Bind_req. If the Bind_req is processed and the
 * Binding Table entry committed on the Remote Device, a Status of SUCCESS is returned. If the
 * Remote Device is not a Primary binding table cache or the SrcAddress, a Status of
 * NOT_SUPPORTED is returned. The supplied endpoint shall be checked to determine whether it
 * falls within the specified range. If it does not, a Status of INVALID_EP shall be returned. If
 * the Remote Device is the Primary binding table cache or SrcAddress but does not have Binding
 * Table resources for the request, a Status of TABLE_FULL is returned.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T17:07:08Z")
public class BindResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public BindResponse() {
        clusterId = 0x8021;
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
        final StringBuilder builder = new StringBuilder(41);
        builder.append("BindResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(']');
        return builder.toString();
    }

}
