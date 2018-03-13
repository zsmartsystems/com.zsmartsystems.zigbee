/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides a list of channel mask values used for channel scans.
 * <p>
 * The 868MHz frequency band has only one channel and is used in Europe with a data rate of 20kbps.
 * <p>
 * The 915MHz frequency band band has 10 channels ranging from channel-1 to channel-10. It delivers data rate of 40 Kbps
 * and used in Americas.
 * The 2.4GHz frequency band is used worldwide and has total of 16 channels from channel-11 to channel-26 delivering a
 * data rate of 250kbps.
 * <ol>
 * <li>channel-0 868 MHz
 * <li>channel-1 906 MHz
 * <li>channel-2 908 MHz
 * <li>channel-3 910 MHz
 * <li>channel-4 912 MHz
 * <li>channel-5 914 MHz
 * <li>channel-6 916 MHz
 * <li>channel-7 918 MHz
 * <li>channel-8 920 MHz
 * <li>channel-9 922 MHz
 * <li>channel-10 924 MHz
 * <li>channel-11 2405 MHz
 * <li>channel-12 2410 MHz
 * <li>channel-13 2415 MHz
 * <li>channel-14 2420 MHz
 * <li>channel-15 2425 MHz
 * <li>channel-16 2430 MHz
 * <li>channel-17 2435 MHz
 * <li>channel-18 2440 MHz
 * <li>channel-19 2445 MHz
 * <li>channel-20 2450 MHz
 * <li>channel-21 2455 MHz
 * <li>channel-22 2460 MHz
 * <li>channel-23 2465 MHz
 * <li>channel-24 2470 MHz
 * <li>channel-25 2475 MHz
 * <li>channel-26 2480 MHz
 * </ol>
 *
 * @author Chris Jackson
 *
 */
public enum ZigBeeChannel {
    UNKNOWN(-1, 0),
    CHANNEL_00(0, 0x00000001),
    CHANNEL_01(1, 0x00000002),
    CHANNEL_02(2, 0x00000004),
    CHANNEL_03(3, 0x00000008),
    CHANNEL_04(4, 0x00000010),
    CHANNEL_05(5, 0x00000020),
    CHANNEL_06(6, 0x00000040),
    CHANNEL_07(7, 0x00000080),
    CHANNEL_08(8, 0x00000100),
    CHANNEL_09(9, 0x00000200),
    CHANNEL_10(10, 0x00000400),
    CHANNEL_11(11, 0x00000800),
    CHANNEL_12(12, 0x00001000),
    CHANNEL_13(13, 0x00002000),
    CHANNEL_14(14, 0x00004000),
    CHANNEL_15(15, 0x00008000),
    CHANNEL_16(16, 0x00010000),
    CHANNEL_17(17, 0x00020000),
    CHANNEL_18(18, 0x00040000),
    CHANNEL_19(19, 0x00080000),
    CHANNEL_20(20, 0x00100000),
    CHANNEL_21(21, 0x00200000),
    CHANNEL_22(22, 0x00400000),
    CHANNEL_23(23, 0x00800000),
    CHANNEL_24(24, 0x01000000),
    CHANNEL_25(25, 0x02000000),
    CHANNEL_26(26, 0x04000000);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, ZigBeeChannel> codeMapping;

    private int channel;
    private int mask;

    private ZigBeeChannel(int channel, int mask) {
        this.channel = channel;
        this.mask = mask;
    }

    private static void initMapping() {
        codeMapping = new HashMap<Integer, ZigBeeChannel>();
        for (ZigBeeChannel s : values()) {
            codeMapping.put(s.channel, s);
        }
    }

    /**
     * @return the channel
     */
    public int getChannel() {
        return channel;
    }

    /**
     * @return the channel mask
     */
    public int getMask() {
        return mask;
    }

    /**
     * Lookup function based on the ZigBeeChannel channel number. Returns null
     * if the channel does not exist.
     *
     * @param channel the channel to lookup
     * @return enumeration value of the {@link ZigBeeChannel}.
     */
    public static ZigBeeChannel create(int channel) {
        if (codeMapping == null) {
            initMapping();
        }

        if (codeMapping.get(channel) == null) {
            return UNKNOWN;
        }

        return codeMapping.get(channel);
    }
}
