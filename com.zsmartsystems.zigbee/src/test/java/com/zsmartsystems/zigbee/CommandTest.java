package com.zsmartsystems.zigbee;

/**
 *
 * @author Chris Jackson
 *
 */
public class CommandTest {
    protected int[] getPacketData(String stringData) {
        String split[] = stringData.trim().split(" ");

        int[] response = new int[split.length];
        int cnt = 0;
        for (String val : split) {
            response[cnt++] = Integer.parseInt(val, 16);
        }

        return response;
    }
}
