/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.protocol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclDataTypeTest {
    @Test
    public void getType() {
        assertEquals(ZclDataType.BOOLEAN, ZclDataType.getType(0x10));
        assertEquals(ZclDataType.CHARACTER_STRING, ZclDataType.getType(0x42));
        assertEquals(ZclDataType.DATA_8_BIT, ZclDataType.getType(0x08));
    }

    @Test
    public void getId() {
        assertEquals(0x10, ZclDataType.BOOLEAN.getId());
        assertEquals(0x42, ZclDataType.CHARACTER_STRING.getId());
        assertEquals(0x08, ZclDataType.DATA_8_BIT.getId());
    }

    @Test
    public void isAnalog() {
        ZclDataType type = ZclDataType.BOOLEAN;
        assertFalse(type.isAnalog());
        type = ZclDataType.UNSIGNED_16_BIT_INTEGER;
        assertTrue(type.isAnalog());
    }

}
