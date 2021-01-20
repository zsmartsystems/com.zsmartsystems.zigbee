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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum ZigBeeDeviceType {

    /**
     * On/Off Switch, 0, 0x0000
     */
    ON_OFF_SWITCH(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0000),

    /**
     * Level Control Switch, 1, 0x0001
     */
    LEVEL_CONTROL_SWITCH(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0001),

    /**
     * On/Off Output, 2, 0x0002
     */
    ON_OFF_OUTPUT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0002),

    /**
     * Level Controllable Output, 3, 0x0003
     */
    LEVEL_CONTROLLABLE_OUTPUT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0003),

    /**
     * Scene Selector, 4, 0x0004
     */
    SCENE_SELECTOR(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0004),

    /**
     * Configuration Tool, 5, 0x0005
     */
    CONFIGURATION_TOOL(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0005),

    /**
     * Remote Control, 6, 0x0006
     */
    REMOTE_CONTROL(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0006),

    /**
     * Combined Interface, 7, 0x0007
     */
    COMBINED_INTERFACE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0007),

    /**
     * Range Extender, 8, 0x0008
     */
    RANGE_EXTENDER(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0008),

    /**
     * Mains Power Outlet, 9, 0x0009
     */
    MAINS_POWER_OUTLET(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0009),

    /**
     * Door Lock, 10, 0x000A
     */
    DOOR_LOCK(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x000A),

    /**
     * Door Lock Controller, 11, 0x000B
     */
    DOOR_LOCK_CONTROLLER(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x000B),

    /**
     * Simple Sensor, 12, 0x000C
     */
    SIMPLE_SENSOR(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x000C),

    /**
     * Consumption Awareness Device, 13, 0x000D
     */
    CONSUMPTION_AWARENESS_DEVICE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x000D),

    /**
     * Data Collection Unit, 16, 0x0010
     */
    DATA_COLLECTION_UNIT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0010),

    /**
     * ZigBee SIM Card, 32, 0x0020
     */
    ZIGBEE_SIM_CARD(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0020),

    /**
     * ZigBee Mobile Terminal, 33, 0x0021
     */
    ZIGBEE_MOBILE_TERMINAL(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0021),

    /**
     * ZigBee Global Platform Card, 38, 0x0026
     */
    ZIGBEE_GLOBAL_PLATFORM_CARD(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0026),

    /**
     * Customer Handheld Device, 48, 0x0030
     */
    CUSTOMER_HANDHELD_DEVICE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0030),

    /**
     * Retail Associate Handheld Device, 49, 0x0031
     */
    RETAIL_ASSOCIATE_HANDHELD_DEVICE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0031),

    /**
     * Intelligent Shopping Cart, 50, 0x0032
     */
    INTELLIGENT_SHOPPING_CART(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0032),

    /**
     * Electronic Shelf Label, 51, 0x0033
     */
    ELECTRONIC_SHELF_LABEL(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0033),

    /**
     * Customer Information Point, 52, 0x0034
     */
    CUSTOMER_INFORMATION_POINT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0034),

    /**
     * Customer Card, 53, 0x0035
     */
    CUSTOMER_CARD(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0035),

    /**
     * Constructed BACnet Device, 74, 0x004A
     */
    CONSTRUCTED_BACNET_DEVICE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x004A),

    /**
     * BACnet Tunneled Device, 75, 0x004B
     */
    BACNET_TUNNELED_DEVICE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x004B),

    /**
     * Home Gateway, 80, 0x0050
     */
    HOME_GATEWAY(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0050),

    /**
     * Smart Plug, 81, 0x0051
     */
    SMART_PLUG(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0051),

    /**
     * White Goods, 82, 0x0052
     */
    WHITE_GOODS(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0052),

    /**
     * Meter Interface, 83, 0x0053
     */
    METER_INTERFACE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0053),

    /**
     * ZGP Proxy, 96, 0x0060
     */
    ZGP_PROXY(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0060),

    /**
     * ZGP Proxy Basic, 97, 0x0061
     */
    ZGP_PROXY_BASIC(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0061),

    /**
     * ZGP Target Plus, 98, 0x0062
     */
    ZGP_TARGET_PLUS(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0062),

    /**
     * ZGP Target, 99, 0x0063
     */
    ZGP_TARGET(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0063),

    /**
     * ZGP Commissioning Tool, 100, 0x0064
     */
    ZGP_COMMISSIONING_TOOL(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0064),

    /**
     * ZGP Combo, 101, 0x0065
     */
    ZGP_COMBO(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0065),

    /**
     * ZGP Combo Basic, 102, 0x0066
     */
    ZGP_COMBO_BASIC(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0066),

    /**
     * Environmental Sensor, 103, 0x0067
     */
    ENVIRONMENTAL_SENSOR(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0067),

    /**
     * On/Off Light, 256, 0x0100
     */
    ON_OFF_LIGHT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0100),

    /**
     * Dimmable Light, 257, 0x0101
     */
    DIMMABLE_LIGHT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0101),

    /**
     * Color Dimmable Light, 258, 0x0102
     */
    COLOR_DIMMABLE_LIGHT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0102),

    /**
     * On/Off Light Switch, 259, 0x0103
     */
    ON_OFF_LIGHT_SWITCH(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0103),

    /**
     * Dimmer Switch, 260, 0x0104
     */
    DIMMER_SWITCH(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0104),

    /**
     * Color Dimmer Switch, 261, 0x0105
     */
    COLOR_DIMMER_SWITCH(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0105),

    /**
     * Light Sensor, 262, 0x0106
     */
    LIGHT_SENSOR(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0106),

    /**
     * Occupancy Sensor, 263, 0x0107
     */
    OCCUPANCY_SENSOR(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0107),

    /**
     * On/Off Ballast, 264, 0x0108
     */
    ON_OFF_BALLAST(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0108),

    /**
     * Dimmable Ballast, 265, 0x0109
     */
    DIMMABLE_BALLAST(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0109),

    /**
     * On/off plug-in unit, 266, 0x010A
     */
    ON_OFF_PLUG_IN_UNIT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x010A),

    /**
     * Dimmable plug-in unit, 267, 0x010B
     */
    DIMMABLE_PLUG_IN_UNIT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x010B),

    /**
     * Color temperature light, 268, 0x010C
     */
    COLOR_TEMPERATURE_LIGHT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x010C),

    /**
     * Extended color light, 269, 0x010D
     */
    EXTENDED_COLOR_LIGHT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x010D),

    /**
     * Light level sensor, 270, 0x010E
     */
    LIGHT_LEVEL_SENSOR(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x010E),

    /**
     * ZigBee Access Point, 288, 0x0120
     */
    ZIGBEE_ACCESS_POINT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0120),

    /**
     * ZigBee Information node, 289, 0x0121
     */
    ZIGBEE_INFORMATION_NODE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0121),

    /**
     * ZigBee Information Terminal, 290, 0x0122
     */
    ZIGBEE_INFORMATION_TERMINAL(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0122),

    /**
     * Shade, 512, 0x0200
     */
    SHADE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0200),

    /**
     * Shade Controller, 513, 0x0201
     */
    SHADE_CONTROLLER(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0201),

    /**
     * Window Covering Device, 514, 0x0202
     */
    WINDOW_COVERING_DEVICE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0202),

    /**
     * Window Covering Controller, 515, 0x0203
     */
    WINDOW_COVERING_CONTROLLER(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0203),

    /**
     * Barrier Device, 516, 0x0204
     */
    BARRIER_DEVICE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0204),

    /**
     * Barrier Device Controller, 517, 0x0205
     */
    BARRIER_DEVICE_CONTROLLER(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0205),

    /**
     * Point of sale, 544, 0x0220
     */
    POINT_OF_SALE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0220),

    /**
     * Ticketing machine, 545, 0x0221
     */
    TICKETING_MACHINE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0221),

    /**
     * Pay controller, 546, 0x0222
     */
    PAY_CONTROLLER(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0222),

    /**
     * Billing unit, 547, 0x0223
     */
    BILLING_UNIT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0223),

    /**
     * Charging unit, 548, 0x0224
     */
    CHARGING_UNIT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0224),

    /**
     * Heating/Cooling Unit, 768, 0x0300
     */
    HEATING_COOLING_UNIT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0300),

    /**
     * Thermostat, 769, 0x0301
     */
    THERMOSTAT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0301),

    /**
     * Temperature Sensor, 770, 0x0302
     */
    TEMPERATURE_SENSOR(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0302),

    /**
     * Pump, 771, 0x0303
     */
    PUMP(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0303),

    /**
     * Pump Controller, 772, 0x0304
     */
    PUMP_CONTROLLER(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0304),

    /**
     * Pressure Sensor, 773, 0x0305
     */
    PRESSURE_SENSOR(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0305),

    /**
     * Flow Sensor, 774, 0x0306
     */
    FLOW_SENSOR(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0306),

    /**
     * Mini Split AC, 775, 0x0307
     */
    MINI_SPLIT_AC(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0307),

    /**
     * IAS Control and Indicating Equipment, 1024, 0x0400
     */
    IAS_CONTROL_AND_INDICATING_EQUIPMENT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0400),

    /**
     * IAS Ancillary Control Equipment, 1025, 0x0401
     */
    IAS_ANCILLARY_CONTROL_EQUIPMENT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0401),

    /**
     * IAS Zone, 1026, 0x0402
     */
    IAS_ZONE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0402),

    /**
     * IAS Warning Device, 1027, 0x0403
     */
    IAS_WARNING_DEVICE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0403),

    /**
     * Energy Service Interface, 1280, 0x0500
     */
    ENERGY_SERVICE_INTERFACE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0500),

    /**
     * Metering Device, 1281, 0x0501
     */
    METERING_DEVICE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0501),

    /**
     * In-Home Display, 1282, 0x0502
     */
    IN_HOME_DISPLAY(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0502),

    /**
     * Programmable Communicating Thermostat, 1283, 0x0503
     */
    PROGRAMMABLE_COMMUNICATING_THERMOSTAT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0503),

    /**
     * Load Control Device, 1284, 0x0504
     */
    LOAD_CONTROL_DEVICE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0504),

    /**
     * Smart Appliance, 1285, 0x0505
     */
    SMART_APPLIANCE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0505),

    /**
     * Prepayment Terminal, 1286, 0x0506
     */
    PREPAYMENT_TERMINAL(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0506),

    /**
     * Physical Device, 1287, 0x0507
     */
    PHYSICAL_DEVICE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0507),

    /**
     * Remote Communications Device, 1288, 0x0508
     */
    REMOTE_COMMUNICATIONS_DEVICE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0508),

    /**
     * ERL Interface, 1289, 0x0509
     */
    ERL_INTERFACE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0509),

    /**
     * Electric Vehicle Station Equipment, 1290, 0x050A
     */
    ELECTRIC_VEHICLE_STATION_EQUIPMENT(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x050A),

    /**
     * Color controller, 2048, 0x0800
     */
    COLOR_CONTROLLER(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0800),

    /**
     * Color scene controller, 2064, 0x0810
     */
    COLOR_SCENE_CONTROLLER(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0810),

    /**
     * Non-color controller, 2080, 0x0820
     */
    NON_COLOR_CONTROLLER(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0820),

    /**
     * Non-color scene controller, 2096, 0x0830
     */
    NON_COLOR_SCENE_CONTROLLER(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0830),

    /**
     * Control bridge, 2112, 0x0840
     */
    CONTROL_BRIDGE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0840),

    /**
     * On/off sensor, 2128, 0x0850
     */
    ON_OFF_SENSOR(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0850),

    /**
     * Generic Multifunction Healthcare Device, 3840, 0x0F00
     */
    GENERIC_MULTIFUNCTION_HEALTHCARE_DEVICE(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, 0x0F00),

    /**
     * ZLL On/Off Light, 0, 0x0000
     */
    ZLL_ON_OFF_LIGHT(ZigBeeProfileType.ZIGBEE_LIGHT_LINK, 0x0000),

    /**
     * ZLL On/Off Plug-in Unit, 16, 0x0010
     */
    ZLL_ON_OFF_PLUG_IN_UNIT(ZigBeeProfileType.ZIGBEE_LIGHT_LINK, 0x0010),

    /**
     * ZLL Dimmable Light, 256, 0x0100
     */
    ZLL_DIMMABLE_LIGHT(ZigBeeProfileType.ZIGBEE_LIGHT_LINK, 0x0100),

    /**
     * ZLL Dimmable Plug-in Unit, 272, 0x0110
     */
    ZLL_DIMMABLE_PLUG_IN_UNIT(ZigBeeProfileType.ZIGBEE_LIGHT_LINK, 0x0110),

    /**
     * ZLL Color Light, 512, 0x0200
     */
    ZLL_COLOR_LIGHT(ZigBeeProfileType.ZIGBEE_LIGHT_LINK, 0x0200),

    /**
     * ZLL Extended Color Light, 528, 0x0210
     */
    ZLL_EXTENDED_COLOR_LIGHT(ZigBeeProfileType.ZIGBEE_LIGHT_LINK, 0x0210),

    /**
     * ZLL Color Temperature Light, 544, 0x0220
     */
    ZLL_COLOR_TEMPERATURE_LIGHT(ZigBeeProfileType.ZIGBEE_LIGHT_LINK, 0x0220),

    /**
     * ZLL Color Controller, 2048, 0x0800
     */
    ZLL_COLOR_CONTROLLER(ZigBeeProfileType.ZIGBEE_LIGHT_LINK, 0x0800),

    /**
     * ZLL Color Scene Controller, 2064, 0x0810
     */
    ZLL_COLOR_SCENE_CONTROLLER(ZigBeeProfileType.ZIGBEE_LIGHT_LINK, 0x0810),

    /**
     * ZLL Non-color Controller, 2080, 0x0820
     */
    ZLL_NON_COLOR_CONTROLLER(ZigBeeProfileType.ZIGBEE_LIGHT_LINK, 0x0820),

    /**
     * ZLL Non-color Scene Controller, 2096, 0x0830
     */
    ZLL_NON_COLOR_SCENE_CONTROLLER(ZigBeeProfileType.ZIGBEE_LIGHT_LINK, 0x0830),

    /**
     * ZLL Control Bridge, 2112, 0x0840
     */
    ZLL_CONTROL_BRIDGE(ZigBeeProfileType.ZIGBEE_LIGHT_LINK, 0x0840),

    /**
     * ZLL On/Off Sensor, 2128, 0x0850
     */
    ZLL_ON_OFF_SENSOR(ZigBeeProfileType.ZIGBEE_LIGHT_LINK, 0x0850),

    /**
     * SEP Range Extender, 8, 0x0008
     */
    SEP_RANGE_EXTENDER(ZigBeeProfileType.ZIGBEE_SMART_ENERGY, 0x0008),

    /**
     * SEP Energy Service Interface, 1280, 0x0500
     */
    SEP_ENERGY_SERVICE_INTERFACE(ZigBeeProfileType.ZIGBEE_SMART_ENERGY, 0x0500),

    /**
     * SEP Metering Device, 1281, 0x0501
     */
    SEP_METERING_DEVICE(ZigBeeProfileType.ZIGBEE_SMART_ENERGY, 0x0501),

    /**
     * SEP In-Home Display, 1282, 0x0502
     */
    SEP_IN_HOME_DISPLAY(ZigBeeProfileType.ZIGBEE_SMART_ENERGY, 0x0502),

    /**
     * SEP Programmable Communicating Thermostat, 1283, 0x0503
     */
    SEP_PROGRAMMABLE_COMMUNICATING_THERMOSTAT(ZigBeeProfileType.ZIGBEE_SMART_ENERGY, 0x0503),

    /**
     * SEP Load Control Device, 1284, 0x0504
     */
    SEP_LOAD_CONTROL_DEVICE(ZigBeeProfileType.ZIGBEE_SMART_ENERGY, 0x0504),

    /**
     * SEP Smart Appliance, 1285, 0x0505
     */
    SEP_SMART_APPLIANCE(ZigBeeProfileType.ZIGBEE_SMART_ENERGY, 0x0505),

    /**
     * SEP Prepayment Terminal, 1286, 0x0506
     */
    SEP_PREPAYMENT_TERMINAL(ZigBeeProfileType.ZIGBEE_SMART_ENERGY, 0x0506),

    /**
     * SEP Physical Device, 1287, 0x0507
     */
    SEP_PHYSICAL_DEVICE(ZigBeeProfileType.ZIGBEE_SMART_ENERGY, 0x0507);

    /**
     * A mapping between the integer code and its corresponding ZigBeeDeviceType type to facilitate lookup by value.
     */
    private static Map<Integer, ZigBeeDeviceType> idMap;

    static {
        idMap = new HashMap<Integer, ZigBeeDeviceType>();
        for (ZigBeeDeviceType enumValue : values()) {
            idMap.put(enumValue.profilekey, enumValue);
        }
    }

    private final int key;
    private final int profilekey;

    private ZigBeeDeviceType(final ZigBeeProfileType profile, final int key) {
        this.key = key;
        this.profilekey = key & 0xffff + (profile.ordinal() << 16);
    }

    public int getKey() {
        return key;
    }

    public static ZigBeeDeviceType getByValue(final int value) {
        int id = value & 0xffff + (ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION.ordinal() << 16);
        return idMap.get(id);
    }

    public static ZigBeeDeviceType getByValue(final ZigBeeProfileType profile, final int value) {
        int id = value & 0xffff + (profile.ordinal() << 16);
        return idMap.get(id);
    }
}
