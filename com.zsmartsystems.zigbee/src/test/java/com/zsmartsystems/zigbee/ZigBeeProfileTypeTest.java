/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeProfileTypeTest {
    @Test
    public void testTypes() {
        assertEquals(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZigBeeProfileType.getByValue(0x0104));
        assertEquals(ZigBeeProfileType.ZIGBEE_LIGHT_LINK, ZigBeeProfileType.getByValue(0xC05E));
        assertNull(ZigBeeProfileType.getByValue(0));
    }
}
