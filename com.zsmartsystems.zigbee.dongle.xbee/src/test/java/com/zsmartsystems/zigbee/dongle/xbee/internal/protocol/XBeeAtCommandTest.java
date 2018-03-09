/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class XBeeAtCommandTest {
    @Test
    public void test() {
        XBeeAtCommand command = new XBeeAtCommand();

        command.setFrameId(0);
        command.setAtCommand("AB");
        command.setParameterValue(new int[] { 0, 1, 2, 3, 4, 5 });
        System.out.println(command);
        assertTrue(Arrays.equals(new int[] { 0, 10, 8, 0, 65, 66, 0, 1, 2, 3, 4, 5, 101 }, command.serialize()));
    }

    @Test
    public void test2() {
        XBeeAtCommand command = new XBeeAtCommand();

        command.setFrameId(0x52);
        command.setAtCommand("NJ");
        System.out.println(command);

        assertTrue(Arrays.equals(new int[] { 0x00, 0x04, 0x08, 0x52, 0x4E, 0x4A, 0x0D }, command.serialize()));
    }
}
