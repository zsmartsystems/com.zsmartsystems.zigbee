package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class XBeeAtCommandCommandTest {
    @Test
    public void test() {
        XBeeAtCommandCommand command = new XBeeAtCommandCommand();

        command.setFrameId(0);
        command.setAtCommand("AB");
        command.setParameterValue(new int[] { 0, 1, 2, 3, 4, 5 });
        System.out.println(command);
        assertTrue(Arrays.equals(new int[] { 126, 0, 10, 8, 0, 65, 66, 0, 1, 2, 3, 4, 5, 101 }, command.serialize()));
    }

    @Test
    public void test2() {
        XBeeAtCommandCommand command = new XBeeAtCommandCommand();

        command.setFrameId(0x52);
        command.setAtCommand("NJ");
        System.out.println(command);

        assertTrue(Arrays.equals(new int[] { 0x7E, 0x00, 0x04, 0x08, 0x52, 0x4E, 0x4A, 0x0D }, command.serialize()));
    }
}
