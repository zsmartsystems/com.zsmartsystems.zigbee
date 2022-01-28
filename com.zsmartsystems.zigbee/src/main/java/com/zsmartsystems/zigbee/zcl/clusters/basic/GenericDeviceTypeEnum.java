/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.basic;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Generic Device Type value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum GenericDeviceTypeEnum {

    /**
     * Incandescent, 0, 0x0000
     */
    INCANDESCENT(0x0000),

    /**
     * Spotlight Halogen, 1, 0x0001
     */
    SPOTLIGHT_HALOGEN(0x0001),

    /**
     * Halogen bulb, 2, 0x0002
     */
    HALOGEN_BULB(0x0002),

    /**
     * CFL, 3, 0x0003
     */
    CFL(0x0003),

    /**
     * Linear Fluorescent, 4, 0x0004
     */
    LINEAR_FLUORESCENT(0x0004),

    /**
     * LED bulb, 5, 0x0005
     */
    LED_BULB(0x0005),

    /**
     * Spotlight LED, 6, 0x0006
     */
    SPOTLIGHT_LED(0x0006),

    /**
     * LED strip, 7, 0x0007
     */
    LED_STRIP(0x0007),

    /**
     * LED tube, 8, 0x0008
     */
    LED_TUBE(0x0008),

    /**
     * Generic indoor luminaire, 9, 0x0009
     */
    GENERIC_INDOOR_LUMINAIRE(0x0009),

    /**
     * Generic outdoor luminaire, 10, 0x000A
     */
    GENERIC_OUTDOOR_LUMINAIRE(0x000A),

    /**
     * Pendant luminaire, 11, 0x000B
     */
    PENDANT_LUMINAIRE(0x000B),

    /**
     * Floor standing luminaire, 12, 0x000C
     */
    FLOOR_STANDING_LUMINAIRE(0x000C),

    /**
     * Generic Controller, 224, 0x00E0
     */
    GENERIC_CONTROLLER(0x00E0),

    /**
     * Wall Switch, 225, 0x00E1
     */
    WALL_SWITCH(0x00E1),

    /**
     * Portable remote controller, 226, 0x00E2
     */
    PORTABLE_REMOTE_CONTROLLER(0x00E2),

    /**
     * Motion sensor / light sensor, 227, 0x00E3
     */
    MOTION_SENSOR_LIGHT_SENSOR(0x00E3),

    /**
     * Generic actuator, 240, 0x00F0
     */
    GENERIC_ACTUATOR(0x00F0),

    /**
     * Wall socket, 241, 0x00F1
     */
    WALL_SOCKET(0x00F1),

    /**
     * Gateway/Bridge, 242, 0x00F2
     */
    GATEWAY_BRIDGE(0x00F2),

    /**
     * Plug-in unit, 243, 0x00F3
     */
    PLUG_IN_UNIT(0x00F3),

    /**
     * Retrofit actuator, 244, 0x00F4
     */
    RETROFIT_ACTUATOR(0x00F4),

    /**
     * Unspecified, 255, 0x00FF
     */
    UNSPECIFIED(0x00FF);

    /**
     * A mapping between the integer code and its corresponding GenericDeviceTypeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, GenericDeviceTypeEnum> idMap;

    static {
        idMap = new HashMap<Integer, GenericDeviceTypeEnum>();
        for (GenericDeviceTypeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private GenericDeviceTypeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static GenericDeviceTypeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
