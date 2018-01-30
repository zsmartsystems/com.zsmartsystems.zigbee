/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.internal.ChannelScanCacheRecord;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zdo.command.ManagementNetworkUpdateNotify;

/**
 *
 * @author Chris Jackson
 *
 */
public class ChannelScanCacheRecordTest {
    @Test
    public void testConstructor() {
        int[] packet = { 0x00, 0x00, 0x00, 0xF8, 0xFF, 0x07, 0x1F, 0x00, 0x08, 0x00, 0x10, 0xAE, 0xAF, 0xD6, 0xAC, 0xB7,
                0xD5, 0xB0, 0xB2, 0xB6, 0xB6, 0xA7, 0xAF, 0xA9, 0xA8, 0xAA, 0xA5 };

        ManagementNetworkUpdateNotify networkUpdate = new ManagementNetworkUpdateNotify();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        networkUpdate.deserialize(fieldDeserializer);

        ChannelScanCacheRecord cacheRecord = new ChannelScanCacheRecord(networkUpdate);
        System.out.println(cacheRecord);

        assertEquals(16, cacheRecord.getScanRecord().size());
    }
}
