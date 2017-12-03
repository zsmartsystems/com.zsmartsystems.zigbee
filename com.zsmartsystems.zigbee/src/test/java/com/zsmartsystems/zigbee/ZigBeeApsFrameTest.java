/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

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

        frame.setSecurityEnable(true);
        assertTrue(frame.isSecurityEnable());

        frame.setSecurityEnable(false);
        assertFalse(frame.isSecurityEnable());

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
        System.out.println(frame);

        frame.setGroupAddress(1);
        assertEquals(1, frame.getGroupAddress());
    }

}
