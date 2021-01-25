/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.groups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for {@link ZigBeeGroupAddress}
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeGroupAddressTest {

    @Test
    public void testGroupAddress() {
        ZigBeeGroupAddress group1 = new ZigBeeGroupAddress(1);
        assertTrue(group1.isGroup());

        ZigBeeGroupAddress group2 = new ZigBeeGroupAddress(1);
        assertTrue(group1.equals(group2));
        assertEquals(0, group1.compareTo(group2));
        assertEquals(1, group1.getAddress());

        group2 = new ZigBeeGroupAddress();
        group2.setAddress(1);
        assertEquals(1, group1.getAddress());

        group2.setAddress(2);
        assertEquals(1, group1.compareTo(group2));

        System.out.println(group2);
    }
}
