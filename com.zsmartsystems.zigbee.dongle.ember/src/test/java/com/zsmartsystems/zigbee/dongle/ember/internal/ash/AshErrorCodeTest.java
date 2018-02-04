/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ash;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.internal.ash.AshErrorCode;

/**
 *
 * @author Chris Jackson
 *
 */
public class AshErrorCodeTest {
    @Test
    public void testErrorCodes() {
        assertEquals(AshErrorCode.RESET_UNKNOWN, AshErrorCode.getAshErrorCode(0));
        assertEquals(AshErrorCode.RESET_EXTERNAL, AshErrorCode.getAshErrorCode(1));
        assertEquals(AshErrorCode.RESET_POWER_ON, AshErrorCode.getAshErrorCode(2));
        assertEquals(AshErrorCode.RESET_WATCHDOG, AshErrorCode.getAshErrorCode(3));
        assertEquals(AshErrorCode.RESET_ASSERT, AshErrorCode.getAshErrorCode(6));
        assertEquals(AshErrorCode.RESET_BOOTLOADER, AshErrorCode.getAshErrorCode(9));
        assertEquals(AshErrorCode.RESET_SOFTWARE, AshErrorCode.getAshErrorCode(0x0b));
        assertEquals(AshErrorCode.RESET_TIMEOUT, AshErrorCode.getAshErrorCode(0x51));
        assertEquals(AshErrorCode.UNKNOWN, AshErrorCode.getAshErrorCode(-1));
    }
}
