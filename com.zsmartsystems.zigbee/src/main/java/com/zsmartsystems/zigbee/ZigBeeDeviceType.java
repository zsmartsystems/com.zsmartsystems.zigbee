/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * ZigBee Device Type value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum ZigBeeDeviceType {

    /**
     * On/Off Switch
     */
    ON_OFF_SWITCH(0x0000),

    /**
     * Level Control Switch
     */
    LEVEL_CONTROL_SWITCH(0x0001),

    /**
     * On/Off Output
     */
    ON_OFF_OUTPUT(0x0002),

    /**
     * Level Controllable Output
     */
    LEVEL_CONTROLLABLE_OUTPUT(0x0003),

    /**
     * Scene Selector
     */
    SCENE_SELECTOR(0x0004),

    /**
     * Configuration Tool
     */
    CONFIGURATION_TOOL(0x0005),

    /**
     * Remote Control
     */
    REMOTE_CONTROL(0x0006),

    /**
     * Combined Interface
     */
    COMBINED_INTERFACE(0x0007),

    /**
     * Range Extender
     */
    RANGE_EXTENDER(0x0008),

    /**
     * Mains Power Outlet
     */
    MAINS_POWER_OUTLET(0x0009),

    /**
     * Door Lock
     */
    DOOR_LOCK(0x000A),

    /**
     * Door Lock Controller
     */
    DOOR_LOCK_CONTROLLER(0x000B),

    /**
     * Simple Sensor
     */
    SIMPLE_SENSOR(0x000C),

    /**
     * Consumption Awareness Device
     */
    CONSUMPTION_AWARENESS_DEVICE(0x000D),

    /**
     * Home Gateway
     */
    HOME_GATEWAY(0x0050),

    /**
     * Smart Plug
     */
    SMART_PLUG(0x0051),

    /**
     * White Goods
     */
    WHITE_GOODS(0x0052),

    /**
     * Meter Interface
     */
    METER_INTERFACE(0x0053),

    /**
     * On/Off Light
     */
    ON_OFF_LIGHT(0x0100),

    /**
     * Dimmable Light
     */
    DIMMABLE_LIGHT(0x0101),

    /**
     * Color Dimmable Light
     */
    COLOR_DIMMABLE_LIGHT(0x0102),

    /**
     * On/Off Light Switch
     */
    ON_OFF_LIGHT_SWITCH(0x0103),

    /**
     * Dimmer Switch
     */
    DIMMER_SWITCH(0x0104),

    /**
     * Color Dimmer Switch
     */
    COLOR_DIMMER_SWITCH(0x0105),

    /**
     * Light Sensor
     */
    LIGHT_SENSOR(0x0106),

    /**
     * Occupancy Sensor
     */
    OCCUPANCY_SENSOR(0x0107),

    /**
     * Shade
     */
    SHADE(0x0200),

    /**
     * Shade Controller
     */
    SHADE_CONTROLLER(0x0201),

    /**
     * Window Covering Device
     */
    WINDOW_COVERING_DEVICE(0x0202),

    /**
     * Window Covering Controller
     */
    WINDOW_COVERING_CONTROLLER(0x0203),

    /**
     * Extended Color Light
     */
    EXTENDED_COLOR_LIGHT(0x0210),

    /**
     * Color Temperature Light
     */
    COLOR_TEMPERATURE_LIGHT(0x0220),

    /**
     * Heating/Cooling Unit
     */
    HEATING_COOLING_UNIT(0x0300),

    /**
     * Thermostat
     */
    THERMOSTAT(0x0301),

    /**
     * Temperature Sensor
     */
    TEMPERATURE_SENSOR(0x0302),

    /**
     * Pump
     */
    PUMP(0x0303),

    /**
     * Pump Controller
     */
    PUMP_CONTROLLER(0x0304),

    /**
     * Pressure Sensor
     */
    PRESSURE_SENSOR(0x0305),

    /**
     * Flow Sensor
     */
    FLOW_SENSOR(0x0306),

    /**
     * Mini Split AC
     */
    MINI_SPLIT_AC(0x0307),

    /**
     * IAS Control and Indicating Equipment
     */
    IAS_CONTROL_AND_INDICATING_EQUIPMENT(0x0400),

    /**
     * IAS Ancillary Control Equipment
     */
    IAS_ANCILLARY_CONTROL_EQUIPMENT(0x0401),

    /**
     * IAS Zone
     */
    IAS_ZONE(0x0402),

    /**
     * IAS Warning Device
     */
    IAS_WARNING_DEVICE(0x0403),

    /**
     * Energy Service Interface
     */
    ENERGY_SERVICE_INTERFACE(0x0500),

    /**
     * Metering Device
     */
    METERING_DEVICE(0x0501),

    /**
     * In-Home Display
     */
    IN_HOME_DISPLAY(0x0502),

    /**
     * Programmable Communicating Thermostat
     */
    PROGRAMMABLE_COMMUNICATING_THERMOSTAT(0x0503),

    /**
     * Load Control Device
     */
    LOAD_CONTROL_DEVICE(0x0504),

    /**
     * Smart Appliance
     */
    SMART_APPLIANCE(0x0505),

    /**
     * Prepayment Terminal
     */
    PREPAYMENT_TERMINAL(0x0506),

    /**
     * Physical Device
     */
    PHYSICAL_DEVICE(0x0507),

    /**
     * Remote Communications Device
     */
    REMOTE_COMMUNICATIONS_DEVICE(0x0508);

    /**
     * A mapping between the integer code and its corresponding ZigBeeDeviceType type to facilitate lookup by value.
     */
    private static Map<Integer, ZigBeeDeviceType> idMap;

    static {
        idMap = new HashMap<Integer, ZigBeeDeviceType>();
        for (ZigBeeDeviceType enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private ZigBeeDeviceType(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ZigBeeDeviceType getByValue(final int value) {
        return idMap.get(value);
    }
}
