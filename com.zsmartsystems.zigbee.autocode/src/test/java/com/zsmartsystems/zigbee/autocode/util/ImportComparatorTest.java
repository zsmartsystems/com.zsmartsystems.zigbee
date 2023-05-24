/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

/**
*
* @author Victor Toni
*
*/
public class ImportComparatorTest {

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
    public void testOrder() {
        Comparator<String> importComparator = new ImportComparator(
                "java",
                "org"
        );

        List<String> importList = new ArrayList<>(EXPECTED);
        assertEquals(EXPECTED, importList);

        Collections.shuffle(importList);
        assertNotEquals(EXPECTED, importList);

        Collections.sort(importList, importComparator);
        assertEquals(EXPECTED, importList);
    }

}
