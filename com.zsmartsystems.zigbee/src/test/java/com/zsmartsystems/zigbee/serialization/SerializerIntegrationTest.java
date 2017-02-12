package com.zsmartsystems.zigbee.serialization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.serialization.DefaultSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * This performs a simple set of tests to make sure the serialiser/deserializer can read back what it writes.
 *
 * @author Chris Jackson
 *
 */
public class SerializerIntegrationTest {
    @Test
    public void testDeserialize_BITMAP_8_BIT() {
        int valIn = 0x91;
        testSerializer(valIn, ZclDataType.BITMAP_8_BIT);
    }

    @Test
    public void testDeserialize_BITMAP_16_BIT() {
        int valIn = 0x9119;
        testSerializer(valIn, ZclDataType.BITMAP_16_BIT);
    }

    @Test
    public void testDeserialize_ENUMERATION_8_BIT() {
        int valIn = 0x91;
        testSerializer(valIn, ZclDataType.ENUMERATION_8_BIT);
    }

    @Test
    public void testDeserialize_ENUMERATION_16_BIT() {
        int valIn = 0x9119;
        testSerializer(valIn, ZclDataType.ENUMERATION_16_BIT);
    }

    @Test
    public void testSerialize_IEEE_ADDRESS() {
        IeeeAddress valIn = new IeeeAddress(Long.parseLong("1234567890123456", 16));
        testSerializer(valIn, ZclDataType.IEEE_ADDRESS);
    }

    @Test
    public void testDeserialize_BOOLEAN() {
        boolean valIn = true;
        testSerializer(valIn, ZclDataType.BOOLEAN);
        valIn = false;
        testSerializer(valIn, ZclDataType.BOOLEAN);
    }

    @Test
    public void testDeserialize_DATA_8_BIT() {
        int valIn = 0x9;
        testSerializer(valIn, ZclDataType.DATA_8_BIT);
    }

    @Test
    public void testDeserialize_SIGNED_8_BIT_INTEGER() {
        int valIn = -23;
        testSerializer(valIn, ZclDataType.SIGNED_8_BIT_INTEGER);
    }

    @Test
    public void testDeserialize_UNSIGNED_8_BIT_INTEGER() {
        int valIn = 0x98;
        testSerializer(valIn, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Test
    public void testSerialize_SIGNED_16_BIT_INTEGER() {
        int valIn = -23456;
        testSerializer(valIn, ZclDataType.SIGNED_16_BIT_INTEGER);
    }

    @Test
    public void testSerialize_UNSIGNED_16_BIT_INTEGER() {
        int valIn = 0x9971;
        testSerializer(valIn, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Test
    public void testSerialize_SIGNED_32_BIT_INTEGER() {
        int valIn = -2345;
        testSerializer(valIn, ZclDataType.SIGNED_32_BIT_INTEGER);
    }

    @Test
    public void testSerialize_UNSIGNED_32_BIT_INTEGER() {
        int valIn = 0xE3970456;
        testSerializer(valIn, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Test
    public void testSerialize_CHARACTER_STRING() {
        String valIn = "Hello World";
        testSerializer(valIn, ZclDataType.CHARACTER_STRING);
    }

    private void testSerializer(Object objectIn, ZclDataType type) {
        DefaultSerializer serializer = new DefaultSerializer();
        serializer.appendZigBeeType(objectIn, type);
        int[] buffer = serializer.getPayload();
        int size = buffer.length;
        DefaultDeserializer deserializer = new DefaultDeserializer(buffer);
        assertEquals(size, deserializer.getSize());
        assertEquals(objectIn, deserializer.readZigBeeType(type));
        assertEquals(size, deserializer.getPosition());
        assertTrue(deserializer.endOfStream());
    }
}
