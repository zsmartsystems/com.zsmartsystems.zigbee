/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeStackTypeTest {
    @Test
    public void testTypes() {
        assertEquals(ZigBeeStackType.ZIGBEE_2006, ZigBeeStackType.getByValue(0));
        assertEquals(ZigBeeStackType.ZIGBEE_2007, ZigBeeStackType.getByValue(1));
        assertEquals(ZigBeeStackType.ZIGBEE_PRO, ZigBeeStackType.getByValue(2));
    }
}
