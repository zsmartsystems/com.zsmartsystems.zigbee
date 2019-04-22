/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api;

import com.zsmartsystems.zigbee.dongle.zstack.internal.serializer.ZstackDeserializer;

/**
 *
 * @author Chris Jackson
 *
 */
public abstract class ZstackFrameResponse extends ZstackCommand {
    protected ZstackDeserializer deserializer;

    public ZstackFrameResponse(int[] inputBuffer) {
        deserializer = new ZstackDeserializer(inputBuffer);

        // Skip the command ID
        deserializer.deserializeUInt16();
    }

}
