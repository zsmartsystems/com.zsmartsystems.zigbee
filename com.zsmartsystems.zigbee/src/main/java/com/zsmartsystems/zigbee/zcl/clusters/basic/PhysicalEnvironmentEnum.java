/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T06:14:22Z")
public enum PhysicalEnvironmentEnum {

    /**
     * Unspecified, 0, 0x0000
     */
    UNSPECIFIED(0x0000),

    /**
     * Atrium Deprecated, 1, 0x0001
     */
    ATRIUM_DEPRECATED(0x0001),

    /**
     * Bar, 2, 0x0002
     */
    BAR(0x0002),

    /**
     * Courtyard, 3, 0x0003
     */
    COURTYARD(0x0003),

    /**
     * Bathroom, 4, 0x0004
     */
    BATHROOM(0x0004),

    /**
     * Bedroom, 5, 0x0005
     */
    BEDROOM(0x0005),

    /**
     * Billiard Room, 6, 0x0006
     */
    BILLIARD_ROOM(0x0006),

    /**
     * Utility Room, 7, 0x0007
     */
    UTILITY_ROOM(0x0007),

    /**
     * Cellar, 8, 0x0008
     */
    CELLAR(0x0008),

    /**
     * Storage Closet, 9, 0x0009
     */
    STORAGE_CLOSET(0x0009),

    /**
     * Theater, 10, 0x000A
     */
    THEATER(0x000A),

    /**
     * Office, 11, 0x000B
     */
    OFFICE(0x000B),

    /**
     * Deck, 12, 0x000C
     */
    DECK(0x000C),

    /**
     * Den, 13, 0x000D
     */
    DEN(0x000D),

    /**
     * Dining Room, 14, 0x000E
     */
    DINING_ROOM(0x000E),

    /**
     * Electrical Room, 15, 0x000F
     */
    ELECTRICAL_ROOM(0x000F),

    /**
     * Elevator, 16, 0x0010
     */
    ELEVATOR(0x0010),

    /**
     * Entry, 17, 0x0011
     */
    ENTRY(0x0011),

    /**
     * Family Room, 18, 0x0012
     */
    FAMILY_ROOM(0x0012),

    /**
     * Main Floor, 19, 0x0013
     */
    MAIN_FLOOR(0x0013),

    /**
     * Upstairs, 20, 0x0014
     */
    UPSTAIRS(0x0014),

    /**
     * Downstairs, 21, 0x0015
     */
    DOWNSTAIRS(0x0015),

    /**
     * Basement, 22, 0x0016
     */
    BASEMENT(0x0016),

    /**
     * Gallery, 23, 0x0017
     */
    GALLERY(0x0017),

    /**
     * Game Room, 24, 0x0018
     */
    GAME_ROOM(0x0018),

    /**
     * Garage, 25, 0x0019
     */
    GARAGE(0x0019),

    /**
     * Gym, 26, 0x001A
     */
    GYM(0x001A),

    /**
     * Hallway, 27, 0x001B
     */
    HALLWAY(0x001B),

    /**
     * House, 28, 0x001C
     */
    HOUSE(0x001C),

    /**
     * Kitchen, 29, 0x001D
     */
    KITCHEN(0x001D),

    /**
     * Laundry Room, 30, 0x001E
     */
    LAUNDRY_ROOM(0x001E),

    /**
     * Library, 31, 0x001F
     */
    LIBRARY(0x001F),

    /**
     * Master Bedroom, 32, 0x0020
     */
    MASTER_BEDROOM(0x0020),

    /**
     * Mud Room, 33, 0x0021
     */
    MUD_ROOM(0x0021),

    /**
     * Nursery, 34, 0x0022
     */
    NURSERY(0x0022),

    /**
     * Pantry, 35, 0x0023
     */
    PANTRY(0x0023),

    /**
     * Office 2, 36, 0x0024
     */
    OFFICE_2(0x0024),

    /**
     * Outside, 37, 0x0025
     */
    OUTSIDE(0x0025),

    /**
     * Pool, 38, 0x0026
     */
    POOL(0x0026),

    /**
     * Porch, 39, 0x0027
     */
    PORCH(0x0027),

    /**
     * Sewing Room, 40, 0x0028
     */
    SEWING_ROOM(0x0028),

    /**
     * Sitting Room, 41, 0x0029
     */
    SITTING_ROOM(0x0029),

    /**
     * Stairway, 42, 0x002A
     */
    STAIRWAY(0x002A),

    /**
     * Yard, 43, 0x002B
     */
    YARD(0x002B),

    /**
     * Attic, 44, 0x002C
     */
    ATTIC(0x002C),

    /**
     * Hot Tub, 45, 0x002D
     */
    HOT_TUB(0x002D),

    /**
     * Living Room, 46, 0x002E
     */
    LIVING_ROOM(0x002E),

    /**
     * Sauna, 47, 0x002F
     */
    SAUNA(0x002F),

    /**
     * Shop/Workshop, 48, 0x0030
     */
    SHOP_WORKSHOP(0x0030),

    /**
     * Guest Bedroom, 49, 0x0031
     */
    GUEST_BEDROOM(0x0031),

    /**
     * Guest Bath, 50, 0x0032
     */
    GUEST_BATH(0x0032),

    /**
     * Powder Room, 51, 0x0033
     */
    POWDER_ROOM(0x0033),

    /**
     * Back Yard, 52, 0x0034
     */
    BACK_YARD(0x0034),

    /**
     * Front Yard, 53, 0x0035
     */
    FRONT_YARD(0x0035),

    /**
     * Patio, 54, 0x0036
     */
    PATIO(0x0036),

    /**
     * Driveway, 55, 0x0037
     */
    DRIVEWAY(0x0037),

    /**
     * Sun Room, 56, 0x0038
     */
    SUN_ROOM(0x0038),

    /**
     * Living Room 2, 57, 0x0039
     */
    LIVING_ROOM_2(0x0039),

    /**
     * Spa, 58, 0x003A
     */
    SPA(0x003A),

    /**
     * Whirlpool, 59, 0x003B
     */
    WHIRLPOOL(0x003B),

    /**
     * Shed, 60, 0x003C
     */
    SHED(0x003C),

    /**
     * Equipment Storage, 61, 0x003D
     */
    EQUIPMENT_STORAGE(0x003D),

    /**
     * Hobby/Craft Room, 62, 0x003E
     */
    HOBBY_CRAFT_ROOM(0x003E),

    /**
     * Fountain, 63, 0x003F
     */
    FOUNTAIN(0x003F),

    /**
     * Pond, 64, 0x0040
     */
    POND(0x0040),

    /**
     * Reception Room, 65, 0x0041
     */
    RECEPTION_ROOM(0x0041),

    /**
     * Breakfast Room, 66, 0x0042
     */
    BREAKFAST_ROOM(0x0042),

    /**
     * Nook, 67, 0x0043
     */
    NOOK(0x0043),

    /**
     * Garden, 68, 0x0044
     */
    GARDEN(0x0044),

    /**
     * Balcony, 69, 0x0045
     */
    BALCONY(0x0045),

    /**
     * Panic Room, 70, 0x0046
     */
    PANIC_ROOM(0x0046),

    /**
     * Terrace, 71, 0x0047
     */
    TERRACE(0x0047),

    /**
     * Roof, 72, 0x0048
     */
    ROOF(0x0048),

    /**
     * Toilet, 73, 0x0049
     */
    TOILET(0x0049),

    /**
     * Toilet Main, 74, 0x004A
     */
    TOILET_MAIN(0x004A),

    /**
     * Outside Toilet, 75, 0x004B
     */
    OUTSIDE_TOILET(0x004B),

    /**
     * Shower room, 76, 0x004C
     */
    SHOWER_ROOM(0x004C),

    /**
     * Study, 77, 0x004D
     */
    STUDY(0x004D),

    /**
     * Front Garden, 78, 0x004E
     */
    FRONT_GARDEN(0x004E),

    /**
     * Back Garden, 79, 0x004F
     */
    BACK_GARDEN(0x004F),

    /**
     * Kettle, 80, 0x0050
     */
    KETTLE(0x0050),

    /**
     * Television, 81, 0x0051
     */
    TELEVISION(0x0051),

    /**
     * Stove, 82, 0x0052
     */
    STOVE(0x0052),

    /**
     * Microwave, 83, 0x0053
     */
    MICROWAVE(0x0053),

    /**
     * Toaster, 84, 0x0054
     */
    TOASTER(0x0054),

    /**
     * Vacuum, 85, 0x0055
     */
    VACUUM(0x0055),

    /**
     * Appliance, 86, 0x0056
     */
    APPLIANCE(0x0056),

    /**
     * Front Door, 87, 0x0057
     */
    FRONT_DOOR(0x0057),

    /**
     * Back Door, 88, 0x0058
     */
    BACK_DOOR(0x0058),

    /**
     * Fridge Door, 89, 0x0059
     */
    FRIDGE_DOOR(0x0059),

    /**
     * Medication Cabinet Door, 96, 0x0060
     */
    MEDICATION_CABINET_DOOR(0x0060),

    /**
     * Wardrobe Door, 97, 0x0061
     */
    WARDROBE_DOOR(0x0061),

    /**
     * Front Cupboard Door, 98, 0x0062
     */
    FRONT_CUPBOARD_DOOR(0x0062),

    /**
     * Other Door, 99, 0x0063
     */
    OTHER_DOOR(0x0063),

    /**
     * Waiting Room, 100, 0x0064
     */
    WAITING_ROOM(0x0064),

    /**
     * Triage Room, 101, 0x0065
     */
    TRIAGE_ROOM(0x0065),

    /**
     * Doctors Office, 102, 0x0066
     */
    DOCTORS_OFFICE(0x0066),

    /**
     * Patients Private Room, 103, 0x0067
     */
    PATIENTS_PRIVATE_ROOM(0x0067),

    /**
     * Consultation Room, 104, 0x0068
     */
    CONSULTATION_ROOM(0x0068),

    /**
     * Nurse Station, 105, 0x0069
     */
    NURSE_STATION(0x0069),

    /**
     * Ward, 106, 0x006A
     */
    WARD(0x006A),

    /**
     * Corridor, 107, 0x006B
     */
    CORRIDOR(0x006B),

    /**
     * Operating Theatre, 108, 0x006C
     */
    OPERATING_THEATRE(0x006C),

    /**
     * Dental Surgery Room, 109, 0x006D
     */
    DENTAL_SURGERY_ROOM(0x006D),

    /**
     * Medical Imaging Room, 110, 0x006E
     */
    MEDICAL_IMAGING_ROOM(0x006E),

    /**
     * Decontamination Room, 111, 0x006F
     */
    DECONTAMINATION_ROOM(0x006F),

    /**
     * Atrium, 112, 0x0070
     */
    ATRIUM(0x0070),

    /**
     * Mirror, 113, 0x0071
     */
    MIRROR(0x0071),

    /**
     * Unknown, 255, 0x00FF
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
