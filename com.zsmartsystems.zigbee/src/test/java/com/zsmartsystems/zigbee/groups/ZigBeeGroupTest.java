/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.groups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;

/**
 * Tests for {@link ZigBeeGroup}
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeGroupTest {

    @Test
    public void testGroupAddress() {
        ZigBeeGroup group1 = new ZigBeeGroup(Mockito.mock(ZigBeeNetworkManager.class), 1);

        ZigBeeGroup group2 = new ZigBeeGroup(Mockito.mock(ZigBeeNetworkManager.class), 1);
        assertTrue(group1.equals(group2));
        assertEquals(0, group1.compareTo(group2));
        assertEquals(1, group1.getGroupId());
        assertEquals(1, group1.getAddress().getAddress());

        // Test that label doesn't matter
        group2 = new ZigBeeGroup(Mockito.mock(ZigBeeNetworkManager.class), 1, "Test Label");
        assertFalse(group1.equals(group2));
        assertEquals("Test Label", group2.getLabel());

        group2 = new ZigBeeGroup(Mockito.mock(ZigBeeNetworkManager.class), 1);
        assertEquals(1, group1.getGroupId());
        group2.setLabel("Test Label");
        assertEquals("Test Label", group2.getLabel());

        group2.setLabel("12345678901234567890");
        assertEquals("1234567890123456", group2.getLabel());

        group2.setGroupId(2);
        assertEquals(1, group1.compareTo(group2));

        System.out.println(group2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGroupIdTooLow() {
        ZigBeeGroup group = new ZigBeeGroup(Mockito.mock(ZigBeeNetworkManager.class), 1);

        group.setGroupId(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGroupIdTooHigh() {
        ZigBeeGroup group = new ZigBeeGroup(Mockito.mock(ZigBeeNetworkManager.class), 1);

        group.setGroupId(0xfff8);
    }

}
