/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.demandresponseandloadcontrol;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Device Class value enumeration.
 * <p>
 * Although, for backwards compatibility, the Type cannot be changed, this 16-bit Integer
 * should be treated as if it were a 16-bit BitMap.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum DeviceClassBitmap {

    /**
     * Hvac Compressor Or Furnace
     */
    HVAC_COMPRESSOR_OR_FURNACE(0x0001),

    /**
     * Strip Heat Baseboard Heat
     */
    STRIP_HEAT_BASEBOARD_HEAT(0x0002),

    /**
     * Water Heater
     */
    WATER_HEATER(0x0004),

    /**
     * Pool Pump Spa Jacuzzi
     */
    POOL_PUMP_SPA_JACUZZI(0x0008),

    /**
     * Smart Appliances
     */
    SMART_APPLIANCES(0x0010),

    /**
     * Irrigation Pump
     */
    IRRIGATION_PUMP(0x0020),

    /**
     * Managed C And I Loads
     */
    MANAGED_C_AND_I_LOADS(0x0040),

    /**
     * Simple Misc Loads
     */
    SIMPLE_MISC_LOADS(0x0080),

    /**
     * Exterior Lighting
     */
    EXTERIOR_LIGHTING(0x0100),

    /**
     * Interior Lighting
     */
    INTERIOR_LIGHTING(0x0200),

    /**
     * Electric Vehicle
     */
    ELECTRIC_VEHICLE(0x0400),

    /**
     * Generation Systems
     */
    GENERATION_SYSTEMS(0x0800);

    /**
     * A mapping between the integer code and its corresponding DeviceClassBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, DeviceClassBitmap> idMap;

    static {
        idMap = new HashMap<Integer, DeviceClassBitmap>();
        for (DeviceClassBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private DeviceClassBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static DeviceClassBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
