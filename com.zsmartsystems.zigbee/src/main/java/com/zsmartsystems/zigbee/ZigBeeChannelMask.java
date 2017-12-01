/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeChannelMask {
    /**
     * All currently defined ZigBee channels in all bands
     */
    public static int CHANNEL_MASK_ALL = 0x07FFFFFF;
    /**
     * All currently defined ZigBee channels in the 2.4GHz band
     */
    public static int CHANNEL_MASK_2GHZ = 0x07FFF800;

    /**
     * The channel mask
     */
    private int channelMask = CHANNEL_MASK_2GHZ;

    /**
     * Constructor creating a mask with no channels set
     */
    public ZigBeeChannelMask() {
        this.channelMask = 0;
    }

    /**
     * Constructor taking a channel mask
     *
     * @param channelMask bitmask of the channels
     */
    public ZigBeeChannelMask(int channelMask) {
        this.channelMask = channelMask;
    }

    /**
     * Gets the channel bitmask of the channels in this channel mask
     *
     * @return int containing the channel bitmask
     */
    public int getChannelMask() {
        return channelMask;
    }

    /**
     * Gets the channels in this channel mask
     *
     * return the {@link List} of {@link ZigBeeChannel}s in this mask
     */
    public List<ZigBeeChannel> getChannels() {
        List<ZigBeeChannel> channels = new ArrayList<ZigBeeChannel>();

        for (int channelCnt = 0; channelCnt < 32; channelCnt++) {
            if ((channelMask & 1 << channelCnt) == 0) {
                continue;
            }

            ZigBeeChannel channel = ZigBeeChannel.create(channelCnt);
            if (channel != ZigBeeChannel.UNKNOWN) {
                channels.add(channel);
            }
        }

        return channels;
    }

    /**
     * Adds a new channel to the channel mask
     *
     * @param channel the channel to add to the mask
     */
    public void addChannel(int channel) {
        if (channel < 0 || channel > 27) {
            return;
        }
        channelMask |= 1 << channel;
    }

    /**
     * Adds a new channel to the channel mask
     *
     * @param channel {@link ZigBeeChannel} to add to the mask
     */
    public void addChannel(ZigBeeChannel channel) {
        channelMask |= channel.getMask();
    }

    /**
     * Tests of the specified channel number is included in the bitmask
     *
     * @param channel the channel number
     * @return true if the mask contains the channel
     */
    public boolean containsChannel(int channel) {
        return ((channelMask & (1 << channel)) != 0);
    }

    /**
     * Tests of the specified channel number is included in the bitmask
     *
     * @param channel the {@link ZigBeeChannel} number
     * @return true if the mask contains the channel
     */
    public boolean containsChannel(ZigBeeChannel channel) {
        return ((channelMask & channel.getMask()) != 0);
    }
}
