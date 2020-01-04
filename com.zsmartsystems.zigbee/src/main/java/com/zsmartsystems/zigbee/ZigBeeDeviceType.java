/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-11-14T15:24:57Z")
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
     * Data Collection Unit
     */
    DATA_COLLECTION_UNIT(0x0010),

    /**
     * ZigBee SIM Card
     */
    ZIGBEE_SIM_CARD(0x0020),

    /**
     * ZigBee Mobile Terminal
     */
    ZIGBEE_MOBILE_TERMINAL(0x0021),

    /**
     * ZigBee Global Platform Card
     */
    ZIGBEE_GLOBAL_PLATFORM_CARD(0x0026),

    /**
     * Customer Handheld Device
     */
    CUSTOMER_HANDHELD_DEVICE(0x0030),

    /**
     * Retail Associate Handheld Device
     */
    RETAIL_ASSOCIATE_HANDHELD_DEVICE(0x0031),

    /**
     * Intelligent Shopping Cart
     */
    INTELLIGENT_SHOPPING_CART(0x0032),

    /**
     * Electronic Shelf Label
     */
    ELECTRONIC_SHELF_LABEL(0x0033),

    /**
     * Customer Information Point
     */
    CUSTOMER_INFORMATION_POINT(0x0034),

    /**
     * Customer Card
     */
    CUSTOMER_CARD(0x0035),

    /**
     * Constructed BACnet Device
     */
    CONSTRUCTED_BACNET_DEVICE(0x004A),

    /**
     * BACnet Tunneled Device
     */
    BACNET_TUNNELED_DEVICE(0x004B),

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
     * ZGP Proxy
     */
    ZGP_PROXY(0x0060),

    /**
     * ZGP Proxy Basic
     */
    ZGP_PROXY_BASIC(0x0061),

    /**
     * ZGP Target Plus
     */
    ZGP_TARGET_PLUS(0x0062),

    /**
     * ZGP Target
     */
    ZGP_TARGET(0x0063),

    /**
     * ZGP Commissioning Tool
     */
    ZGP_COMMISSIONING_TOOL(0x0064),

    /**
     * ZGP Combo
     */
    ZGP_COMBO(0x0065),

    /**
     * ZGP Combo Basic
     */
    ZGP_COMBO_BASIC(0x0066),

    /**
     * Environmental Sensor
     */
    ENVIRONMENTAL_SENSOR(0x0067),

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
     * On/Off Ballast
     */
    ON_OFF_BALLAST(0x0108),

    /**
     * Dimmable Ballast
     */
    DIMMABLE_BALLAST(0x0109),

    /**
     * On/off plug-in unit
     */
    ON_OFF_PLUG_IN_UNIT(0x010A),

    /**
     * Dimmable plug-in unit
     */
    DIMMABLE_PLUG_IN_UNIT(0x010B),

    /**
     * Color temperature light
     */
    COLOR_TEMPERATURE_LIGHT(0x010C),

    /**
     * Extended color light
     */
    EXTENDED_COLOR_LIGHT(0x010D),

    /**
     * Light level sensor
     */
    LIGHT_LEVEL_SENSOR(0x010E),

    /**
     * ZigBee Access Point
     */
    ZIGBEE_ACCESS_POINT(0x0120),

    /**
     * ZigBee Information node
     */
    ZIGBEE_INFORMATION_NODE(0x0121),

    /**
     * ZigBee Information Terminal
     */
    ZIGBEE_INFORMATION_TERMINAL(0x0122),

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
     * Barrier Device
     */
    BARRIER_DEVICE(0x0204),

    /**
     * Barrier Device Controller
     */
    BARRIER_DEVICE_CONTROLLER(0x0205),

    /**
     * Point of sale
     */
    POINT_OF_SALE(0x0220),

    /**
     * Ticketing machine
     */
    TICKETING_MACHINE(0x0221),

    /**
     * Pay controller
     */
    PAY_CONTROLLER(0x0222),

    /**
     * Billing unit
     */
    BILLING_UNIT(0x0223),

    /**
     * Charging unit
     */
    CHARGING_UNIT(0x0224),

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
    REMOTE_COMMUNICATIONS_DEVICE(0x0508),

    /**
     * ERL Interface
     */
    ERL_INTERFACE(0x0509),

    /**
     * Electric Vehicle Station Equipment
     */
    ELECTRIC_VEHICLE_STATION_EQUIPMENT(0x050A),

    /**
     * Color controller
     */
    COLOR_CONTROLLER(0x0800),

    /**
     * Color scene controller
     */
    COLOR_SCENE_CONTROLLER(0x0810),

    /**
     * Non-color controller
     */
    NON_COLOR_CONTROLLER(0x0820),

    /**
     * Non-color scene controller
     */
    NON_COLOR_SCENE_CONTROLLER(0x0830),

    /**
     * Control bridge
     */
    CONTROL_BRIDGE(0x0840),

    /**
     * On/off sensor
     */
    ON_OFF_SENSOR(0x0850),

    /**
     * Generic Multifunction Healthcare Device
     */
    GENERIC_MULTIFUNCTION_HEALTHCARE_DEVICE(0x0F00);

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
