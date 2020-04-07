/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-04-07T19:42:14Z")
public enum GenericDeviceTypeEnum {

    /**
     * Incandescent
     */
    INCANDESCENT(0x0000),

    /**
     * Spotlight Halogen
     */
    SPOTLIGHT_HALOGEN(0x0001),

    /**
     * Halogen bulb
     */
    HALOGEN_BULB(0x0002),

    /**
     * CFL
     */
    CFL(0x0003),

    /**
     * Linear Fluorescent
     */
    LINEAR_FLUORESCENT(0x0004),

    /**
     * LED bulb
     */
    LED_BULB(0x0005),

    /**
     * Spotlight LED
     */
    SPOTLIGHT_LED(0x0006),

    /**
     * LED strip
     */
    LED_STRIP(0x0007),

    /**
     * LED tube
     */
    LED_TUBE(0x0008),

    /**
     * Generic indoor luminaire
     */
    GENERIC_INDOOR_LUMINAIRE(0x0009),

    /**
     * Generic outdoor luminaire
     */
    GENERIC_OUTDOOR_LUMINAIRE(0x000A),

    /**
     * Pendant luminaire
     */
    PENDANT_LUMINAIRE(0x000B),

    /**
     * Floor standing luminaire
     */
    FLOOR_STANDING_LUMINAIRE(0x000C),

    /**
     * Generic Controller
     */
    GENERIC_CONTROLLER(0x00E0),

    /**
     * Wall Switch
     */
    WALL_SWITCH(0x00E1),

    /**
     * Portable remote controller
     */
    PORTABLE_REMOTE_CONTROLLER(0x00E2),

    /**
     * Motion sensor / light sensor
     */
    MOTION_SENSOR_LIGHT_SENSOR(0x00E3),

    /**
     * Generic actuator
     */
    GENERIC_ACTUATOR(0x00F0),

    /**
     * Wall socket
     */
    WALL_SOCKET(0x00F1),

    /**
     * Gateway/Bridge
     */
    GATEWAY_BRIDGE(0x00F2),

    /**
     * Plug-in unit
     */
    PLUG_IN_UNIT(0x00F3),

    /**
     * Retrofit actuator
     */
    RETROFIT_ACTUATOR(0x00F4),

    /**
     * Unspecified
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
