package com.zsmartsystems.zigbee.serialization;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.serialization.DefaultSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 *
 * @author Chris Jackson
 *
 */
public class DefaultSerializerTest {
    @Test
    public void testSerialize_null() {
        boolean caught = false;
        try {
            testSerializedData(null, null, ZclDataType.DATA_8_BIT);
        } catch (IllegalArgumentException e) {
            caught = true;
        }

        assertTrue(caught);
    }

    @Test
    public void testSerialize_DATA_8_BIT() {
        int valIn = 0x9;
        int[] valOut = { 0x9 };
        testSerializedData(valIn, valOut, ZclDataType.DATA_8_BIT);
    }

    @Test
    public void testSerialize_SIGNED_16_BIT_INTEGER() {
        int valIn = 0x397;
        int[] valOut = { 0x97, 0x03 };
        testSerializedData(valIn, valOut, ZclDataType.SIGNED_16_BIT_INTEGER);
    }

    @Test
    public void testSerialize_IEEE_ADDRESS() {
        IeeeAddress valIn = new IeeeAddress(Long.parseLong("1234567890123456", 16));
        int[] valOut = { 0x56, 0x34, 0x12, 0x90, 0x78, 0x56, 0x34, 0x12 };
        testSerializedData(valIn, valOut, ZclDataType.IEEE_ADDRESS);
    }

    private void testSerializedData(Object object, int[] output, ZclDataType type) {
        DefaultSerializer serializer = new DefaultSerializer();
        serializer.appendZigBeeType(object, type);
        int[] data = serializer.getPayload();
        assertTrue(Arrays.equals(output, data));
    }
}
