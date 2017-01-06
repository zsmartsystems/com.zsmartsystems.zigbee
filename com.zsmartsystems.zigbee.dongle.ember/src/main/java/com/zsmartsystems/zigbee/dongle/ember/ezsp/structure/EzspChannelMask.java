package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides a list of channel mask values used for channel scans. <br>
 * The 868MHz frequency band has only one channel and is used in Europe with a data rate of 20kbps. <br>
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
public enum EzspChannelMask {
    EZSP_CHANNEL_MASK_UNKNONW(0),
    EZSP_CHANNEL_MASK_ALL(0x07FFF800),
    EZSP_CHANNEL_MASK_CHAN11(0x00000800),
    EZSP_CHANNEL_MASK_CHAN12(0x00001000),
    EZSP_CHANNEL_MASK_CHAN13(0x00002000),
    EZSP_CHANNEL_MASK_CHAN14(0x00004000),
    EZSP_CHANNEL_MASK_CHAN15(0x00008000),
    EZSP_CHANNEL_MASK_CHAN16(0x00010000),
    EZSP_CHANNEL_MASK_CHAN17(0x00020000),
    EZSP_CHANNEL_MASK_CHAN18(0x00040000),
    EZSP_CHANNEL_MASK_CHAN19(0x00080000),
    EZSP_CHANNEL_MASK_CHAN20(0x00100000),
    EZSP_CHANNEL_MASK_CHAN21(0x00200000),
    EZSP_CHANNEL_MASK_CHAN22(0x00400000),
    EZSP_CHANNEL_MASK_CHAN23(0x00800000),
    EZSP_CHANNEL_MASK_CHAN24(0x01000000),
    EZSP_CHANNEL_MASK_CHAN25(0x02000000),
    EZSP_CHANNEL_MASK_CHAN26(0x04000000);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, EzspChannelMask> codeMapping;

    private int key;

    private EzspChannelMask(int key) {
        this.key = key;
    }

    private static void initMapping() {
        codeMapping = new HashMap<Integer, EzspChannelMask>();
        for (EzspChannelMask s : values()) {
            codeMapping.put(s.key, s);
        }
    }

    /**
     * Lookup function based on the EzspNetworkScanType type code. Returns null
     * if the code does not exist.
     *
     * @param i
     *            the code to lookup
     * @return enumeration value of the alarm type.
     */
    public static EzspChannelMask getEzspChannelMask(int i) {
        if (codeMapping == null) {
            initMapping();
        }

        if (codeMapping.get(i) == null) {
            return EZSP_CHANNEL_MASK_UNKNONW;
        }

        return codeMapping.get(i);
    }

    /**
     * @return the key
     */
    public int getKey() {
        return key;
    }
}
