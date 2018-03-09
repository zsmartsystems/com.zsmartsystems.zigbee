/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
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
    public void testNormalizeBoolean() {
        ZclAttributeNormalizer normalizer = new ZclAttributeNormalizer();

        assertEquals(Boolean.TRUE, normalizer.normalizeZclData(ZclDataType.BOOLEAN, Integer.valueOf(1)));
        assertEquals(Boolean.TRUE, normalizer.normalizeZclData(ZclDataType.BOOLEAN, Integer.valueOf(100)));
        assertEquals(Boolean.FALSE, normalizer.normalizeZclData(ZclDataType.BOOLEAN, Integer.valueOf(0)));
    }
}
