/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal;

import org.junit.Test;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ZigBeeChannel;
import com.zsmartsystems.zigbee.ZigBeeChannelMask;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zdo.command.ManagementNetworkUpdateNotify;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNetworkChannelManagerTest {
    private int[] packet1 = { 0x00, 0x00, 0x00, 0xF8, 0xFF, 0x07, 0x1F, 0x00, 0x08, 0x00, 0x10, 0xAE, 0xAF, 0xD6, 0xAC,
            0xB7, 0xD5, 0xB0, 0xB2, 0xB6, 0xB6, 0xA7, 0xAF, 0xA9, 0xA8, 0xAA, 0xA5 };
    private int[] packet2 = { 0x00, 0x00, 0x00, 0xF8, 0xFF, 0x07, 0x1F, 0x00, 0x08, 0x00, 0x10, 0xAC, 0xAC, 0xD9, 0xA1,
            0xB6, 0xD0, 0xA0, 0xB6, 0xB0, 0xB1, 0xA1, 0xB1, 0xA8, 0xA0, 0xA5, 0xAA };

    private ManagementNetworkUpdateNotify getNotifyCommand(int[] packet) {
        ManagementNetworkUpdateNotify networkUpdate = new ManagementNetworkUpdateNotify();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        networkUpdate.deserialize(fieldDeserializer);

        return networkUpdate;
    }

    @Test
    public void testScan() {
        ZigBeeChannelMask channelMask = new ZigBeeChannelMask(ZigBeeChannelMask.CHANNEL_MASK_2GHZ);
        channelMask.removeChannel(ZigBeeChannel.CHANNEL_12);
        ZigBeeNetworkChannelManager channelManager = new ZigBeeNetworkChannelManager(
                Mockito.mock(ZigBeeNetworkManager.class));
        channelManager.setChannelMask(channelMask);
        channelManager.commandReceived(getNotifyCommand(packet1));
        channelManager.commandReceived(getNotifyCommand(packet2));

        channelManager.getCurrentBestChannel();
    }
}
