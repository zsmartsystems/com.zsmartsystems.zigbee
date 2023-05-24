/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 *
 * @author Victor Toni
 *
 */
public class ImportSetTest {

    private static final List<String> EXPECTED = Arrays.asList(
            "java.util.HashMap",
            "java.util.HashSet",
            "java.util.List",
            "javax.annotation.Generated",
            "org.slf4j.Logger",
            "org.slf4j.LoggerFactory",
            "com.zsmartsystems.zigbee.console",
            "com.zsmartsystems.zigbee.zcl",
            "com.zsmartsystems.zigbee.zdo",
            "com.zsmartsystems.zigbee.zdo.field"
    );

    @Test
    public void testAdd() {
        ImportSet importClasses = new ImportSet();
        assertEquals(0, importClasses.size());
        assertTrue(importClasses.isEmpty());

        for (int i = 0; i < EXPECTED.size(); i++) {
            String importClass = EXPECTED.get(i);
            assertEquals(i, importClasses.size());
            assertFalse(importClasses.contains(importClass));

            importClasses.add(EXPECTED.get(i));
            assertEquals(i + 1, importClasses.size());
            assertTrue(importClasses.contains(importClass));
            assertFalse(importClasses.isEmpty());
        }

        // duplicates are ignored
        for (int i = 0; i < EXPECTED.size(); i++) {
            importClasses.add(EXPECTED.get(i));
            assertEquals(EXPECTED.size(), importClasses.size());
            assertFalse(importClasses.isEmpty());
        }
    }

    @Test
    public void testOrder() {
        ImportSet importClasses = new ImportSet();
        assertEquals(0, importClasses.size());
        assertTrue(importClasses.isEmpty());
        
        List<String> shuffledImportList = new ArrayList<>(EXPECTED);
        Collections.shuffle(shuffledImportList);
        assertNotEquals(EXPECTED, shuffledImportList);

        shuffledImportList.forEach(importClasses::add);
        List<String> actual1 = importClasses.stream().collect(Collectors.toList());
        assertEquals(EXPECTED, actual1);

        List<String> actual2 = importClasses.stream().collect(Collectors.toList());
        assertEquals(EXPECTED, actual2);
    }

    @Test
    public void testClear() {
        ImportSet importClasses = new ImportSet();
        assertEquals(
                0,
                importClasses.size()
        );

        EXPECTED.forEach(importClasses::add);

        assertEquals(
                EXPECTED.size(),
                importClasses.size()
        );

        importClasses.clear();
        assertEquals(
                0,
                importClasses.size()
        );
    }

    @Test
    public void testFilter() {
        assertFilter(s -> s.startsWith("java"));
        assertFilter(s -> s.startsWith("java."));
        assertFilter(s -> s.startsWith("com."));
    }

    private void assertFilter(Predicate<String> predicate) {
        ImportSet importClasses = new ImportSet();
        EXPECTED.forEach(importClasses::add);

        assertNotEquals(
                EXPECTED.size(),
                EXPECTED.stream().filter(predicate).count()
        );

        assertEquals(
                EXPECTED.stream().filter(predicate).count(),
                importClasses.stream().filter(predicate).count()
        );
    }
}
