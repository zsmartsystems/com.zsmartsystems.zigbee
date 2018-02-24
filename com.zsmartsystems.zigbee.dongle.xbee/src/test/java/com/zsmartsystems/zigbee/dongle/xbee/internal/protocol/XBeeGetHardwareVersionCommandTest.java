package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class XBeeGetHardwareVersionCommandTest {
    @Test
    public void test() {
        XBeeGetHardwareVersionCommand command = new XBeeGetHardwareVersionCommand();

        command.setFrameId(0);
        System.out.println(command);
        assertTrue(Arrays.equals(new int[] { 126, 0, 4, 8, 0, 72, 86, 89 }, command.serialize()));
    }
}
