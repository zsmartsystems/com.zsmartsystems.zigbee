package com.zsmartsystems.zigbee.dongle.ember.ezsp;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspFrameTest {
    protected int[] getPacketData(String stringData) {
        String split[] = stringData.split(" ");

        int[] response = new int[split.length];
        int cnt = 0;
        for (String val : split) {
            response[cnt++] = Integer.parseInt(val, 16);
        }

        return response;
    }
}
