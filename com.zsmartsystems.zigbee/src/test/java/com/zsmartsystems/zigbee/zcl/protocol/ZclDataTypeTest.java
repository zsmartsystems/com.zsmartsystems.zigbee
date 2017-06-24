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
    public void getLabel() {
        ZclDataType type = ZclDataType.BOOLEAN;
        assertEquals("Boolean", type.getLabel());
    }

    @Test
    public void isAnalog() {
        ZclDataType type = ZclDataType.BOOLEAN;
        assertFalse(type.isAnalog());
        type = ZclDataType.UNSIGNED_16_BIT_INTEGER;
        assertTrue(type.isAnalog());
    }

}
