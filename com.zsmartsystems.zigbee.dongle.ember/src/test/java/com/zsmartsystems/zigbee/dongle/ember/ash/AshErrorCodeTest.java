package com.zsmartsystems.zigbee.dongle.ember.ash;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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
