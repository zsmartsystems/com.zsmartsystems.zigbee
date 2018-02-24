package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class XBeeGetDetailedVersionCommandTest {
    @Test
    public void test() {
        XBeeGetDetailedVersionCommand command = new XBeeGetDetailedVersionCommand();

        command.setFrameId(0);
        System.out.println(command);
        assertTrue(Arrays.equals(new int[] { 126, 0, 4, 8, 0, 86, 76, 85 }, command.serialize()));
    }
}
