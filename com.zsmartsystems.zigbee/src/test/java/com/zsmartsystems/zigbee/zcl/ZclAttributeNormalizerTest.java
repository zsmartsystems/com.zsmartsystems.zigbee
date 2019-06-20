/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclAttributeNormalizerTest {
    @Test
    public void testNormalizeBOOLEAN() {
        ZclAttributeNormalizer normalizer = new ZclAttributeNormalizer();

        assertEquals(Boolean.TRUE, normalizer.normalizeZclData(ZclDataType.BOOLEAN, Integer.valueOf(1)));
        assertEquals(Boolean.TRUE, normalizer.normalizeZclData(ZclDataType.BOOLEAN, Integer.valueOf(100)));
        assertEquals(Boolean.FALSE, normalizer.normalizeZclData(ZclDataType.BOOLEAN, Integer.valueOf(0)));
    }

    @Test
    public void testNormalizeUNSIGNED_8_BIT_INTEGER() {
        ZclAttributeNormalizer normalizer = new ZclAttributeNormalizer();

        assertEquals(Integer.valueOf(123), normalizer.normalizeZclData(ZclDataType.UNSIGNED_8_BIT_INTEGER, "123"));
        assertEquals(Integer.valueOf(0), normalizer.normalizeZclData(ZclDataType.UNSIGNED_8_BIT_INTEGER,
                String.valueOf(new char[] { 1, 2, 3 })));
        assertEquals(Integer.valueOf(123),
                normalizer.normalizeZclData(ZclDataType.UNSIGNED_8_BIT_INTEGER, Double.valueOf(123)));
    }

    @Test
    public void testNormalizeSIGNED_8_BIT_INTEGER() {
        ZclAttributeNormalizer normalizer = new ZclAttributeNormalizer();

        assertEquals(Integer.valueOf(123),
                normalizer.normalizeZclData(ZclDataType.SIGNED_8_BIT_INTEGER, Double.valueOf(123)));
    }

    @Test
    public void testNormalizeUNSIGNED_16_BIT_INTEGER() {
        ZclAttributeNormalizer normalizer = new ZclAttributeNormalizer();

        assertEquals(Integer.valueOf(123),
                normalizer.normalizeZclData(ZclDataType.UNSIGNED_16_BIT_INTEGER, Double.valueOf(123)));
    }

    @Test
    public void testNormalizeSIGNED_16_BIT_INTEGER() {
        ZclAttributeNormalizer normalizer = new ZclAttributeNormalizer();

        assertEquals(Integer.valueOf(123),
                normalizer.normalizeZclData(ZclDataType.SIGNED_16_BIT_INTEGER, Double.valueOf(123)));
    }
}
