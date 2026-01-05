/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for MMO Hash function used for generating link keys from install codes for ZigBee 3 and Smart Energy.
 *
 * @author Chris Jackson
 *
 */
public class MmoHashTest {

    @Test
    public void testString() {
        MmoHash hash;

        hash = new MmoHash("11223344556677884AF7");
        assertEquals("4161:8FC0:C83B:0E14:A589:954B:16E3:1466", hash.toString());
        System.out.println(hash.toString());

        hash = new MmoHash(new int[] { 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x4A, 0xF7 });
        assertEquals("4161:8FC0:C83B:0E14:A589:954B:16E3:1466", hash.toString());
        System.out.println(hash.toString());

        hash = new MmoHash("1122 3344 5566 7788 4AF7");
        System.out.println(hash.toString());
        assertEquals("4161:8FC0:C83B:0E14:A589:954B:16E3:1466", hash.toString());

        hash = new MmoHash("1122:3344:5566:7788:4AF7");
        System.out.println(hash.toString());
        assertEquals("4161:8FC0:C83B:0E14:A589:954B:16E3:1466", hash.toString());

        hash = new MmoHash("83FED3407A939738c552");
        System.out.println(hash.toString());
        assertEquals("A833:A774:34F3:BFBD:7A7A:B979:4214:9287", hash.toString());

        hash = new MmoHash("83FE-D340-7A93-9738-C552");
        System.out.println(hash.toString());
        assertEquals("A833:A774:34F3:BFBD:7A7A:B979:4214:9287", hash.toString());
    }
}
