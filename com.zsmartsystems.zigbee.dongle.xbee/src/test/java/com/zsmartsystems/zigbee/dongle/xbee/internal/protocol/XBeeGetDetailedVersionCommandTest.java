/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
public class XBeeGetDetailedVersionCommandTest {
    @Test
    public void test() {
        XBeeGetDetailedVersionCommand command = new XBeeGetDetailedVersionCommand();

        command.setFrameId(0);
        System.out.println(command);
        assertTrue(Arrays.equals(new int[] { 0, 4, 8, 0, 86, 76, 85 }, command.serialize()));
    }
}
