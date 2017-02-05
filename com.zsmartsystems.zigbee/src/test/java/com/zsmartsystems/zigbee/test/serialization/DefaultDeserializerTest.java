package com.zsmartsystems.zigbee.test.serialization;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 *
 * @author Chris Jackson
 *
 */
public class DefaultDeserializerTest {
    @Test
    public void testDeserialize_DATA_8_BIT() {
        int[] valIn = { 0x9 };
        int valOut = 0x9;
        testDeserialize(valIn, valOut, ZclDataType.DATA_8_BIT);
    }

    @Test
    public void testDeserialize_SIGNED_16_BIT_INTEGER() {
        int[] valIn = { 0x97, 0x03 };
        int valOut = 0x397;
        testDeserialize(valIn, valOut, ZclDataType.SIGNED_16_BIT_INTEGER);
    }

    @Test
    public void testDeserialize_IEEE_ADDRESS() {
        int[] valIn = { 0x56, 0x34, 0x12, 0x90, 0x78, 0x56, 0x34, 0x12 };
        IeeeAddress valOut = new IeeeAddress(Long.parseLong("1234567890123456", 16));
        testDeserialize(valIn, valOut, ZclDataType.IEEE_ADDRESS);
    }

    private void testDeserialize(int[] input, Object objectIn, ZclDataType type) {
        DefaultDeserializer deserializer = new DefaultDeserializer(input);
        Object objectOut = deserializer.readZigBeeType(type);
        assertEquals(objectIn, objectOut);
    }
}
