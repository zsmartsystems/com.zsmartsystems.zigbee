/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ByteArrayTest {
    @Test
    public void testSetting() {
        ByteArray array = new ByteArray(new byte[] { 1, 2, 3, 4, 5 });
        System.out.println(array);

        assertTrue(Arrays.equals(new byte[] { 1, 2, 3, 4, 5 }, array.get()));
        assertEquals(5, array.size());

        array.set(new byte[] { 9, 8, 7, 6, 5, 4, 3, 2, 1 });
        assertTrue(Arrays.equals(new byte[] { 9, 8, 7, 6, 5, 4, 3, 2, 1 }, array.get()));

        array.set(new int[] { 1, 2, 3, 4, 5 });
        assertTrue(Arrays.equals(new byte[] { 1, 2, 3, 4, 5 }, array.get()));

        array = new ByteArray(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
        assertTrue(Arrays.equals(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, array.get()));

        array = new ByteArray(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 1, 7);
        assertEquals(new ByteArray(new byte[] { 2, 3, 4, 5, 6, 7, 8 }), array);

        assertTrue(Arrays.equals(new int[] { 2, 3, 4, 5, 6, 7, 8 }, array.getAsIntArray()));
    }
}
