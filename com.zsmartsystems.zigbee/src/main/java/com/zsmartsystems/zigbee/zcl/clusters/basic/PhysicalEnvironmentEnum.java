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
 * Physical Environment value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-09-28T13:35:09Z")
public enum PhysicalEnvironmentEnum {

    /**
     * Unspecified
     */
    UNSPECIFIED(0x0000),

    /**
     * Atrium
     */
    ATRIUM(0x0001),

    /**
     * Bar
     */
    BAR(0x0002),

    /**
     * Courtyard
     */
    COURTYARD(0x0003),

    /**
     * Bathroom
     */
    BATHROOM(0x0004),

    /**
     * Bedroom
     */
    BEDROOM(0x0005),

    /**
     * Billiard Room
     */
    BILLIARD_ROOM(0x0006),

    /**
     * Utility Room
     */
    UTILITY_ROOM(0x0007),

    /**
     * Cellar
     */
    CELLAR(0x0008),

    /**
     * Storage Closet
     */
    STORAGE_CLOSET(0x0009),

    /**
     * Theater
     */
    THEATER(0x000A),

    /**
     * Office
     */
    OFFICE(0x000B),

    /**
     * Deck
     */
    DECK(0x000C),

    /**
     * Den
     */
    DEN(0x000D),

    /**
     * Dining Room
     */
    DINING_ROOM(0x000E),

    /**
     * Electrical Room
     */
    ELECTRICAL_ROOM(0x000F),

    /**
     * Elevator
     */
    ELEVATOR(0x0010),

    /**
     * Entry
     */
    ENTRY(0x0011),

    /**
     * Family Room
     */
    FAMILY_ROOM(0x0012),

    /**
     * Main Floor
     */
    MAIN_FLOOR(0x0013),

    /**
     * Upstairs
     */
    UPSTAIRS(0x0014),

    /**
     * Downstairs
     */
    DOWNSTAIRS(0x0015),

    /**
     * Basement
     */
    BASEMENT(0x0016),

    /**
     * Gallery
     */
    GALLERY(0x0017),

    /**
     * Game Room
     */
    GAME_ROOM(0x0018),

    /**
     * Garage
     */
    GARAGE(0x0019),

    /**
     * Gym
     */
    GYM(0x001A),

    /**
     * Hallway
     */
    HALLWAY(0x001B),

    /**
     * House
     */
    HOUSE(0x001C),

    /**
     * Kitchen
     */
    KITCHEN(0x001D),

    /**
     * Laundry Room
     */
    LAUNDRY_ROOM(0x001E),

    /**
     * Library
     */
    LIBRARY(0x001F),

    /**
     * Master Bedroom
     */
    MASTER_BEDROOM(0x0020),

    /**
     * Mud Room
     */
    MUD_ROOM(0x0021),

    /**
     * Nursery
     */
    NURSERY(0x0022),

    /**
     * Pantry
     */
    PANTRY(0x0023),

    /**
     * Office 2
     */
    OFFICE_2(0x0024),

    /**
     * Outside
     */
    OUTSIDE(0x0025),

    /**
     * Pool
     */
    POOL(0x0026),

    /**
     * Porch
     */
    PORCH(0x0027),

    /**
     * Sewing Room
     */
    SEWING_ROOM(0x0028),

    /**
     * Sitting Room
     */
    SITTING_ROOM(0x0029),

    /**
     * Stairway
     */
    STAIRWAY(0x002A),

    /**
     * Yard
     */
    YARD(0x002B),

    /**
     * Attic
     */
    ATTIC(0x002C),

    /**
     * Hot Tub
     */
    HOT_TUB(0x002D),

    /**
     * Living Room
     */
    LIVING_ROOM(0x002E),

    /**
     * Sauna
     */
    SAUNA(0x002F),

    /**
     * Shop/Workshop
     */
    SHOP_WORKSHOP(0x0030),

    /**
     * Guest Bedroom
     */
    GUEST_BEDROOM(0x0031),

    /**
     * Guest Bath
     */
    GUEST_BATH(0x0032),

    /**
     * Powder Room
     */
    POWDER_ROOM(0x0033),

    /**
     * Back Yard
     */
    BACK_YARD(0x0034),

    /**
     * Front Yard
     */
    FRONT_YARD(0x0035),

    /**
     * Patio
     */
    PATIO(0x0036),

    /**
     * Driveway
     */
    DRIVEWAY(0x0037),

    /**
     * Sun Room
     */
    SUN_ROOM(0x0038),

    /**
     * Living Room 2
     */
    LIVING_ROOM_2(0x0039),

    /**
     * Spa
     */
    SPA(0x003A),

    /**
     * Whirlpool
     */
    WHIRLPOOL(0x003B),

    /**
     * Shed
     */
    SHED(0x003C),

    /**
     * Equipment Storage
     */
    EQUIPMENT_STORAGE(0x003D),

    /**
     * Hobby/Craft Room
     */
    HOBBY_CRAFT_ROOM(0x003E),

    /**
     * Fountain
     */
    FOUNTAIN(0x003F),

    /**
     * Pond
     */
    POND(0x0040),

    /**
     * Reception Room
     */
    RECEPTION_ROOM(0x0041),

    /**
     * Breakfast Room
     */
    BREAKFAST_ROOM(0x0042),

    /**
     * Nook
     */
    NOOK(0x0043),

    /**
     * Garden
     */
    GARDEN(0x0044),

    /**
     * Balcony
     */
    BALCONY(0x0045),

    /**
     * Panic Room
     */
    PANIC_ROOM(0x0046),

    /**
     * Terrace
     */
    TERRACE(0x0047),

    /**
     * Roof
     */
    ROOF(0x0048),

    /**
     * Toilet
     */
    TOILET(0x0049),

    /**
     * Toilet Main
     */
    TOILET_MAIN(0x004A),

    /**
     * Outside Toilet
     */
    OUTSIDE_TOILET(0x004B),

    /**
     * Shower room
     */
    SHOWER_ROOM(0x004C),

    /**
     * Study
     */
    STUDY(0x004D),

    /**
     * Front Garden
     */
    FRONT_GARDEN(0x004E),

    /**
     * Back Garden
     */
    BACK_GARDEN(0x004F),

    /**
     * Kettle
     */
    KETTLE(0x0050),

    /**
     * Television
     */
    TELEVISION(0x0051),

    /**
     * Stove
     */
    STOVE(0x0052),

    /**
     * Microwave
     */
    MICROWAVE(0x0053),

    /**
     * Toaster
     */
    TOASTER(0x0054),

    /**
     * Vacuum
     */
    VACUUM(0x0055),

    /**
     * Appliance
     */
    APPLIANCE(0x0056),

    /**
     * Front Door
     */
    FRONT_DOOR(0x0057),

    /**
     * Back Door
     */
    BACK_DOOR(0x0058),

    /**
     * Fridge Door
     */
    FRIDGE_DOOR(0x0059),

    /**
     * Medication Cabinet Door
     */
    MEDICATION_CABINET_DOOR(0x0060),

    /**
     * Wardrobe Door
     */
    WARDROBE_DOOR(0x0061),

    /**
     * Front Cupboard Door
     */
    FRONT_CUPBOARD_DOOR(0x0062),

    /**
     * Other Door
     */
    OTHER_DOOR(0x0063),

    /**
     * Waiting Room
     */
    WAITING_ROOM(0x0064),

    /**
     * Triage Room
     */
    TRIAGE_ROOM(0x0065),

    /**
     * Doctors Office
     */
    DOCTORS_OFFICE(0x0066),

    /**
     * Patients Private Room
     */
    PATIENTS_PRIVATE_ROOM(0x0067),

    /**
     * Consultation Room
     */
    CONSULTATION_ROOM(0x0068),

    /**
     * Nurse Station
     */
    NURSE_STATION(0x0069),

    /**
     * Ward
     */
    WARD(0x006A),

    /**
     * Corridor
     */
    CORRIDOR(0x006B),

    /**
     * Operating Theatre
     */
    OPERATING_THEATRE(0x006C),

    /**
     * Dental Surgery Room
     */
    DENTAL_SURGERY_ROOM(0x006D),

    /**
     * Medical Imaging Room
     */
    MEDICAL_IMAGING_ROOM(0x006E),

    /**
     * Decontamination Room
     */
    DECONTAMINATION_ROOM(0x006F),

    /**
     * Unknown
     */
    UNKNOWN(0x00FF);

    /**
     * A mapping between the integer code and its corresponding PhysicalEnvironmentEnum type to facilitate lookup by value.
     */
    private static Map<Integer, PhysicalEnvironmentEnum> idMap;

    static {
        idMap = new HashMap<Integer, PhysicalEnvironmentEnum>();
        for (PhysicalEnvironmentEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private PhysicalEnvironmentEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static PhysicalEnvironmentEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
