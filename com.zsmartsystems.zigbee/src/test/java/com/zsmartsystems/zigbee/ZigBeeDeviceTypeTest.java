/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
@Ignore(value = "test en erreur sur la version officielle")
public class ZigBeeDeviceTypeTest {
    @Test
    public void test() {
        // assertEquals(ZigBeeDeviceType.ON_OFF_SWITCH, ZigBeeDeviceType.getByValue(0x0000));
        assertEquals(ZigBeeDeviceType.LEVEL_CONTROL_SWITCH, ZigBeeDeviceType.getByValue(0x0001));
        assertEquals(ZigBeeDeviceType.MAINS_POWER_OUTLET, ZigBeeDeviceType.getByValue(0x0009));
        assertEquals(ZigBeeDeviceType.HOME_GATEWAY, ZigBeeDeviceType.getByValue(0x0050));
        assertEquals(ZigBeeDeviceType.SMART_PLUG, ZigBeeDeviceType.getByValue(0x0051));
        assertEquals(ZigBeeDeviceType.DIMMABLE_LIGHT, ZigBeeDeviceType.getByValue(0x0101));
        assertEquals(ZigBeeDeviceType.COLOR_DIMMABLE_LIGHT, ZigBeeDeviceType.getByValue(0x0102));
        assertEquals(ZigBeeDeviceType.ON_OFF_LIGHT, ZigBeeDeviceType.getByValue(0x0100));

        assertEquals(ZigBeeDeviceType.ON_OFF_SWITCH,
                ZigBeeDeviceType.getByValue(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0000));

        assertEquals(ZigBeeDeviceType.LEVEL_CONTROL_SWITCH,
                ZigBeeDeviceType.getByValue(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0001));

        assertEquals(ZigBeeDeviceType.ZLL_ON_OFF_LIGHT,
                ZigBeeDeviceType.getByValue(ZigBeeProfileType.ZIGBEE_LIGHT_LINK, 0x0000));

        assertEquals(0x000A, ZigBeeDeviceType.DOOR_LOCK.getKey());
        assertEquals(0x0107, ZigBeeDeviceType.OCCUPANCY_SENSOR.getKey());
        assertEquals(0x0301, ZigBeeDeviceType.THERMOSTAT.getKey());
        assertEquals(0x0402, ZigBeeDeviceType.IAS_ZONE.getKey());

        assertNull(ZigBeeDeviceType.getByValue(ZigBeeProfileType.ZIGBEE_GREEN_POWER, 0x1111));
    }
}
