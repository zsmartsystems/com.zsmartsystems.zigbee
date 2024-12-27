/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for the {@link ZigBeeUtcTime} class
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeUtcTimeTest {
    @Test
    public void test() {
        ZigBeeUtcTime time = ZigBeeUtcTime.ofEpochSecond(946684800);

        assertEquals(0, time.getZigBeeSecond());
        assertEquals(946684800, time.getEpochSecond());

        time = time.plus(100);
        assertEquals(100, time.getZigBeeSecond());

        time = time.minus(10);
        assertEquals(90, time.getZigBeeSecond());
        assertEquals(946684890, time.getEpochSecond());

        time = ZigBeeUtcTime.ofZigBeeSecond(20);
        assertEquals(20, time.getZigBeeSecond());
        assertEquals(946684820, time.getEpochSecond());

        time = ZigBeeUtcTime.ofZigBeeSecond(0x235A75A2);
        System.out.println(time.toString());

        time = new ZigBeeUtcTime();
        assertEquals(0xffffffff, time.getZigBeeSecond());
        System.out.println(time.toString());

        System.out.println(ZigBeeUtcTime.now().toString());
    }

    @Test
    public void compare() {
        ZigBeeUtcTime time1 = ZigBeeUtcTime.ofEpochSecond(946684800);
        ZigBeeUtcTime time2 = ZigBeeUtcTime.ofEpochSecond(946684801);
        ZigBeeUtcTime invalidTime = new ZigBeeUtcTime();

        assertFalse(time1.equals(time2));

        assertTrue(time1.isBefore(time2));
        assertFalse(time1.isBefore(invalidTime));
        assertTrue(time2.isAfter(time1));
        assertFalse(time2.isAfter(invalidTime));
        assertFalse(invalidTime.isBefore(time1));
        assertFalse(invalidTime.isAfter(time1));

        time2 = ZigBeeUtcTime.ofEpochSecond(946684800);
        assertTrue(time1.equals(time2));
    }

}
