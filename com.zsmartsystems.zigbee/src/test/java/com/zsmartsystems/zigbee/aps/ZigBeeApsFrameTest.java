/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.aps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeApsFrameTest {

    @Test
    public void testToString() {
        ZigBeeApsFrame frame = new ZigBeeApsFrame();

        assertNotNull(frame.toString());
    }

    @Test
    public void testSecurityEnable() {
        ZigBeeApsFrame frame = new ZigBeeApsFrame();

        frame.setSecurityEnabled(true);
        assertTrue(frame.getSecurityEnabled());

        frame.setSecurityEnabled(false);
        assertFalse(frame.getSecurityEnabled());

        System.out.println(frame);
    }

    @Test
    public void testRadius() {
        ZigBeeApsFrame frame = new ZigBeeApsFrame();

        frame.setRadius(4);
        assertEquals(4, frame.getRadius());
    }

    @Test
    public void testNonMemberRadius() {
        ZigBeeApsFrame frame = new ZigBeeApsFrame();

        frame.setNonMemberRadius(4);
        assertEquals(4, frame.getNonMemberRadius());
    }

    @Test
    public void testGroupAddress() {
        ZigBeeApsFrame frame = new ZigBeeApsFrame();
        frame.setApsCounter(-1);
        assertEquals(-1, frame.getApsCounter());
        frame.setGroupAddress(1);
        System.out.println(frame);
        assertEquals(1, frame.getGroupAddress());
    }

    @Test
    public void testFragment() {
        ZigBeeApsFrame frame = new ZigBeeApsFrame();

        frame.setFragmentBase(0);
        frame.setFragmentTotal(2);
        frame.setFragmentOutstanding(1);
        assertEquals(0, frame.getFragmentBase());
        assertEquals(1, frame.getFragmentOutstanding());
        frame.setFragmentOutstanding(-1);
        assertEquals(1, frame.getFragmentOutstanding());
        frame.setFragmentOutstanding(2);
        assertEquals(2, frame.getFragmentOutstanding());
        frame.setFragmentOutstanding(3);
        assertEquals(2, frame.getFragmentOutstanding());
    }

}
