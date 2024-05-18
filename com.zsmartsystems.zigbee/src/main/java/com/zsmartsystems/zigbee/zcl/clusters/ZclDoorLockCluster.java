/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.Future;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.ClearAllPinCodes;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.ClearAllPinCodesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.ClearAllRfidCodes;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.ClearAllRfidCodesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.ClearHolidaySchedule;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.ClearHolidayScheduleResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.ClearPinCode;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.ClearPinCodeResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.ClearRfidCode;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.ClearRfidCodeResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.ClearWeekDaySchedule;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.ClearWeekDayScheduleResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.ClearYearDaySchedule;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.ClearYearDayScheduleResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.GetHolidaySchedule;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.GetHolidayScheduleResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.GetLogRecord;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.GetLogRecordResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.GetPinCode;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.GetPinCodeResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.GetRfidCode;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.GetRfidCodeResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.GetUserStatus;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.GetUserStatusResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.GetUserType;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.GetUserTypeResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.GetWeekDaySchedule;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.GetWeekDayScheduleResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.GetYearDaySchedule;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.LockDoorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.LockDoorResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.OperationEventNotification;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.ProgrammingEventNotification;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.SetHolidaySchedule;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.SetHolidayScheduleResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.SetPinCode;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.SetPinCodeResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.SetRfidCode;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.SetRfidCodeResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.SetUserStatus;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.SetUserStatusResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.SetUserType;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.SetUserTypeResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.SetWeekDaySchedule;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.SetWeekDayScheduleResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.SetYearDaySchedule;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.SetYearDayScheduleResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.Toggle;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.ToggleResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.UnlockDoorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.UnlockDoorResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.UnlockWithTimeout;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.UnlockWithTimeoutResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.ZclDoorLockCommand;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Door Lock</b> cluster implementation (<i>Cluster ID 0x0101</i>).
 * <p>
 * The door lock cluster provides an interface to a generic way to secure a door. The physical
 * object that provides the locking functionality is abstracted from the cluster. The cluster
 * has a small list of mandatory attributes and functions and a list of optional features.
 * <p>
 * Generally the door lock itself implements the server side of this cluster. The attributes
 * and commands listed in this cluster were developed to be implemented by a door lock which has
 * the ability to keep track of multiple users and schedules.
 * <p>
 * The door lock cluster provides several alarms which can be sent when there is a critical state
 * on the door lock. The alarms available for the door lock cluster are listed in the section
 * below outlining the alarm mask at- tribute. The Alarm cluster is used to generate the actual
 * alarms.
 * <p>
 * The event mechanism in the door lock centers on the transmission of two commands
 * autonomously generated by the server and sent to a bound device. The assumption is that the
 * binding mechanism will be used to commission the server to send these commands.
 * <p>
 * Door locks have the ability to require the use of APS encryption for sending and receiving of
 * all cluster messages. The Security Level attribute is used to specify the type of encryption
 * required by the door lock.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T05:41:29Z")
public class ZclDoorLockCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0101;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Door Lock";

    // Attribute constants
    /**
     * Provides the current lock state
     */
    public static final int ATTR_LOCKSTATE = 0x0000;
    /**
     * Defines the type of lock
     */
    public static final int ATTR_LOCKTYPE = 0x0001;
    /**
     * Boolean indicating the enabled/disabled state of the lock
     */
    public static final int ATTR_ACTUATORENABLED = 0x0002;
    /**
     * The current state of the door lock
     */
    public static final int ATTR_DOORSTATE = 0x0003;
    /**
     * This attribute holds the number of door open events that have occurred since it was last
     * zeroed.
     */
    public static final int ATTR_DOOROPENEVENTS = 0x0004;
    /**
     * This attribute holds the number of door closed events that have occurred since it was
     * last zeroed.
     */
    public static final int ATTR_DOORCLOSEDEVENTS = 0x0005;
    /**
     * This attribute holds the number of minutes the door has been open since the last time it
     * transitioned from closed to open.
     */
    public static final int ATTR_OPENPERIOD = 0x0006;
    /**
     * The number of available log records.
     */
    public static final int ATTR_NUMLOCKRECORDSSUPPORTED = 0x0010;
    /**
     * Number of total users supported by the lock. This value is equal to the higher one of [# of
     * PIN Users Supported] and [# of RFID Users Supported]
     */
    public static final int ATTR_NUMTOTALUSERSSUPPORTED = 0x0011;
    /**
     * The number of PIN users supported.
     */
    public static final int ATTR_NUMPINUSERSSUPPORTED = 0x0012;
    /**
     * The number of RFID users supported.
     */
    public static final int ATTR_NUMRFIDUSERSSUPPORTED = 0x0013;
    /**
     * The number of configurable week day schedule supported per user.
     */
    public static final int ATTR_NUMWEEKDAYSCHEDULESSUPPORTEDPERUSER = 0x0014;
    /**
     * The number of configurable year day schedule supported per user
     */
    public static final int ATTR_NUMYEARDAYSCHEDULESSUPPORTEDPERUSER = 0x0015;
    /**
     * The number of holiday schedules supported for the entire door lock device.
     */
    public static final int ATTR_NUMHOLIDAYSCHEDULESSUPPORTEDPERUSER = 0x0016;
    /**
     * An 8 bit value indicates the maximum length in bytes of a PIN Code on this device. The
     * default is set to 8 since most lock manufacturers currently allow PIN Codes of 8 bytes or
     * less.
     */
    public static final int ATTR_MAXPINLENGTH = 0x0017;
    /**
     * An 8 bit value indicates the minimum length in bytes of a PIN Code on this device. The
     * default is set to 4 since most lock manufacturers do not support PIN Codes that are
     * shorter than 4 bytes.
     */
    public static final int ATTR_MINPINLENGTH = 0x0018;
    /**
     * An 8 bit value indicates the maximum length in bytes of a RFID Code on this device. The
     * value depends on the RFID code range specified by the manufacturer, if media
     * anti-collision identifiers (UID) are used as RFID code, a value of 20 (equals 10 Byte ISO
     * 14443A UID) is recommended.
     */
    public static final int ATTR_MAXRFIDCODELENGTH = 0x0019;
    /**
     * An 8 bit value indicates the minimum length in bytes of a RFID Code on this device. The
     * value depends on the RFID code range specified by the manufacturer, if media
     * anti-collision identifiers (UID) are used as RFID code, a value of 8 (equals 4 Byte ISO
     * 14443A UID) is recommended.
     */
    public static final int ATTR_MINRFIDCODELENGTH = 0x001A;
    /**
     * Enable/disable event logging. When event logging is enabled, all event messages are
     * stored on the lock for retrieval. Logging events can be but not limited to Tamper Alarm,
     * Lock, Unlock, Autolock, User Code Added, User Code Deleted, Schedule Added, and
     * Schedule Deleted. For a full detail of all the possible alarms and events, please refer
     * to the full list in the Alarm and Event Masks Attribute Set.
     */
    public static final int ATTR_ENABLELOGGING = 0x0020;
    /**
     * Modifies the language for the on-screen or audible user interface using three bytes
     * from ISO-639-1. It consists of one byte of length and two bytes for the language code. For
     * example if the language is set to English, the value would be "02 65 6E" for the language
     * code "en"
     */
    public static final int ATTR_LANGUAGE = 0x0021;
    /**
     * The settings for the LED support three different modes
     */
    public static final int ATTR_LEDSETTINGS = 0x0022;
    /**
     * The number of seconds to wait after unlocking a lock before it automatically locks
     * again. 0=disabled. If set, unlock operations from any source will be timed. For one time
     * unlock with timeout use the specific command.
     */
    public static final int ATTR_AUTORELOCKTIME = 0x0023;
    /**
     * The sound volume on a door lock has three possible settings: silent, low and high volumes
     */
    public static final int ATTR_SOUNDVOLUME = 0x0024;
    /**
     * Shows the current operating mode
     */
    public static final int ATTR_OPERATINGMODE = 0x0025;
    /**
     * This bitmap contains all operating bits of the Operating Mode Attribute supported by
     * the lock. The value of the enumeration in “Operating Mode” defines the related bit to be
     * set, as shown in Table 7-16. All bits supported by a lock shall be set to zero.
     */
    public static final int ATTR_SUPPORTEDOPERATINGMODES = 0x0026;
    /**
     * This attribute represents the default configurations as they are physically set on the
     * device (example: hardware dip switch setting, etc…) and represents the default
     * setting for some of the attributes within this Operational Setting Attribute Set (for
     * example: LED, Auto Lock, Sound Volume, and Operating Mode attributes).
     * <p>
     * This is a read-only attribute and is intended to allow clients to determine what changes
     * may need to be made without having to query all the included attributes. It may be
     * beneficial for the clients to know what the device’s original settings were in the event
     * that the device needs to be restored to factory default settings.
     */
    public static final int ATTR_DEFAULTCONFIGURATIONREGISTER = 0x0027;
    /**
     * Enable/disable local programming on the door lock. The local programming features
     * includes but not limited to adding new user codes, deleting existing user codes, add new
     * schedule, deleting existing schedule on the local door lock interfaces. If this value
     * is set to 0x01 or TRUE then local programming is enabled on the door lock. If it is set to
     * 0x00 or FALSE then local programming is disabled on the door lock. Local programming is
     * enabled by default.
     */
    public static final int ATTR_ENABLELOCALPROGRAMMING = 0x0028;
    /**
     * Enable/disable the ability to lock the door lock with a single touch on the door lock.
     */
    public static final int ATTR_ENABLEONETOUCHLOCKING = 0x0029;
    /**
     * Enable/disable an inside LED that allows the user to see at a glance if the door is locked.
     */
    public static final int ATTR_ENABLEINSIDESTATUSLED = 0x002A;
    /**
     * Enable/disable a button inside the door that is used to put the lock into privacy mode.
     * When the lock is in privacy mode it cannot be manipulated from the outside.
     */
    public static final int ATTR_ENABLEPRIVACYMODEBUTTON = 0x002B;
    /**
     * The number of incorrect codes or RFID presentment attempts a user is allowed to enter
     * before the door will enter a lockout state. The lockout state will be for the duration of
     * UserCodeTemporaryDisableTime.
     */
    public static final int ATTR_WRONGCODEENTRYLIMIT = 0x0030;
    /**
     * The number of seconds that the lock shuts down following wrong code entry. 1-255
     * seconds. Device can shutdown to lock user out for specified amount of time. (Makes it
     * difficult to try and guess a PIN for the device.)
     */
    public static final int ATTR_USERCODETEMPORARYDISABLETIME = 0x0031;
    /**
     * Boolean set to True if it is ok for the door lock server to send PINs over the air. This
     * attribute determines the behavior of the server’s TX operation. If it is false, then it
     * is not ok for the device to send PIN in any messages over the air.
     * <p>
     * The PIN field within any door lock cluster message shall keep the first octet unchanged
     * and masks the actual code by replacing with 0xFF. For example (PIN "1234" ): If the
     * attribute value is True, 0x04 0x31 0x32 0x33 0x34 shall be used in the PIN field in any door
     * lock cluster message payload. If the attribute value is False, 0x04 0xFF 0xFF 0xFF 0xFF
     * shall be used.
     */
    public static final int ATTR_SENDPINOVERTHEAIR = 0x0032;
    /**
     * Boolean set to True if the door lock server requires that an optional PINs be included in
     * the payload of RF lock operation events like Lock, Unlock and Toggle in order to
     * function.
     */
    public static final int ATTR_REQUIREPINFORRFOPERATION = 0x0033;
    /**
     * Door locks may sometimes wish to implement a higher level of security within the
     * application protocol in additional to the default network security. For instance a
     * door lock may wish to use additional APS security for cluster transactions. This
     * protects the door lock against being controlled by any other devices which have access
     * to the network key.
     * <p>
     * The Security Level attribute allows the door lock manufacturer to indicate what level
     * of security the doorlock requires.
     */
    public static final int ATTR_ZIGBEESECURITYLEVEL = 0x0034;
    /**
     * The alarm mask is used to turn on/off alarms for particular functions. Alarms for an
     * alarm group are enabled if the associated alarm mask bit is set. Each bit represents a
     * group of alarms. Entire alarm groups can be turned on or off by setting or clearing the
     * associated bit in the alarm mask.
     */
    public static final int ATTR_ALARMMASK = 0x0040;
    /**
     * Event mask used to turn on and off the transmission of keypad operation events. This mask
     * DOES NOT apply to the storing of events in the report table.
     */
    public static final int ATTR_KEYPADOPERATIONEVENTMASK = 0x0041;
    /**
     * Event mask used to turn on and off the transmission of RF operation events. This mask DOES
     * NOT apply to the storing of events in the report table.
     */
    public static final int ATTR_RFOPERATIONEVENTMASK = 0x0042;
    /**
     * Event mask used to turn on and off manual operation events. This mask DOES NOT apply to the
     * storing of events in the report table.
     */
    public static final int ATTR_MANUALOPERATIONEVENTMASK = 0x0043;
    /**
     * Event mask used to turn on and off RFID operation events. This mask DOES NOT apply to the
     * storing of events in the report table.
     */
    public static final int ATTR_RFIDOPERATIONEVENTMASK = 0x0044;
    /**
     * Event mask used to turn on and off keypad programming events. This mask DOES NOT apply to
     * the storing of events in the report table.
     */
    public static final int ATTR_KEYPADPROGRAMMINGEVENTMASK = 0x0045;
    /**
     * Event mask used to turn on and off RF programming events. This mask DOES NOT apply to the
     * storing of events in the report table.
     */
    public static final int ATTR_RFPROGRAMMINGEVENTMASK = 0x0046;
    /**
     * Event mask used to turn on and off RFID programming events. This mask DOES NOT apply to the
     * storing of events in the report table.
     */
    public static final int ATTR_RFIDPROGRAMMINGEVENTMASK = 0x0047;

    @Override
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        Map<Integer, ZclAttribute> attributeMap = super.initializeClientAttributes();

        return attributeMap;
    }

    @Override
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        Map<Integer, ZclAttribute> attributeMap = super.initializeServerAttributes();

        attributeMap.put(ATTR_LOCKSTATE, new ZclAttribute(this, ATTR_LOCKSTATE, "Lock State", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_LOCKTYPE, new ZclAttribute(this, ATTR_LOCKTYPE, "Lock Type", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_ACTUATORENABLED, new ZclAttribute(this, ATTR_ACTUATORENABLED, "Actuator Enabled", ZclDataType.BOOLEAN, true, true, false, false));
        attributeMap.put(ATTR_DOORSTATE, new ZclAttribute(this, ATTR_DOORSTATE, "Door State", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_DOOROPENEVENTS, new ZclAttribute(this, ATTR_DOOROPENEVENTS, "Door Open Events", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_DOORCLOSEDEVENTS, new ZclAttribute(this, ATTR_DOORCLOSEDEVENTS, "Door Closed Events", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_OPENPERIOD, new ZclAttribute(this, ATTR_OPENPERIOD, "Open Period", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_NUMLOCKRECORDSSUPPORTED, new ZclAttribute(this, ATTR_NUMLOCKRECORDSSUPPORTED, "Num Lock Records Supported", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NUMTOTALUSERSSUPPORTED, new ZclAttribute(this, ATTR_NUMTOTALUSERSSUPPORTED, "Num Total Users Supported", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NUMPINUSERSSUPPORTED, new ZclAttribute(this, ATTR_NUMPINUSERSSUPPORTED, "Num PIN Users Supported", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NUMRFIDUSERSSUPPORTED, new ZclAttribute(this, ATTR_NUMRFIDUSERSSUPPORTED, "Num RFID Users Supported", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NUMWEEKDAYSCHEDULESSUPPORTEDPERUSER, new ZclAttribute(this, ATTR_NUMWEEKDAYSCHEDULESSUPPORTEDPERUSER, "Num Weekday Schedules Supported Per User", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NUMYEARDAYSCHEDULESSUPPORTEDPERUSER, new ZclAttribute(this, ATTR_NUMYEARDAYSCHEDULESSUPPORTEDPERUSER, "Num Yearday Schedules Supported Per User", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NUMHOLIDAYSCHEDULESSUPPORTEDPERUSER, new ZclAttribute(this, ATTR_NUMHOLIDAYSCHEDULESSUPPORTEDPERUSER, "Num Holiday Schedules Supported Per User", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MAXPINLENGTH, new ZclAttribute(this, ATTR_MAXPINLENGTH, "Max PIN Length", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MINPINLENGTH, new ZclAttribute(this, ATTR_MINPINLENGTH, "Min PIN Length", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MAXRFIDCODELENGTH, new ZclAttribute(this, ATTR_MAXRFIDCODELENGTH, "Max RFID Code Length", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MINRFIDCODELENGTH, new ZclAttribute(this, ATTR_MINRFIDCODELENGTH, "Min RFID Code Length", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ENABLELOGGING, new ZclAttribute(this, ATTR_ENABLELOGGING, "Enable Logging", ZclDataType.BOOLEAN, false, true, true, true));
        attributeMap.put(ATTR_LANGUAGE, new ZclAttribute(this, ATTR_LANGUAGE, "Language", ZclDataType.CHARACTER_STRING, false, true, true, true));
        attributeMap.put(ATTR_LEDSETTINGS, new ZclAttribute(this, ATTR_LEDSETTINGS, "LED Settings", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_AUTORELOCKTIME, new ZclAttribute(this, ATTR_AUTORELOCKTIME, "Auto Relock Time", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_SOUNDVOLUME, new ZclAttribute(this, ATTR_SOUNDVOLUME, "Sound Volume", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_OPERATINGMODE, new ZclAttribute(this, ATTR_OPERATINGMODE, "Operating Mode", ZclDataType.ENUMERATION_8_BIT, false, true, true, true));
        attributeMap.put(ATTR_SUPPORTEDOPERATINGMODES, new ZclAttribute(this, ATTR_SUPPORTEDOPERATINGMODES, "Supported Operating Modes", ZclDataType.BITMAP_16_BIT, true, true, false, false));
        attributeMap.put(ATTR_DEFAULTCONFIGURATIONREGISTER, new ZclAttribute(this, ATTR_DEFAULTCONFIGURATIONREGISTER, "Default Configuration Register", ZclDataType.BITMAP_16_BIT, true, true, false, false));
        attributeMap.put(ATTR_ENABLELOCALPROGRAMMING, new ZclAttribute(this, ATTR_ENABLELOCALPROGRAMMING, "Enable Local Programming", ZclDataType.BOOLEAN, false, true, true, true));
        attributeMap.put(ATTR_ENABLEONETOUCHLOCKING, new ZclAttribute(this, ATTR_ENABLEONETOUCHLOCKING, "Enable One Touch Locking", ZclDataType.BOOLEAN, false, true, true, true));
        attributeMap.put(ATTR_ENABLEINSIDESTATUSLED, new ZclAttribute(this, ATTR_ENABLEINSIDESTATUSLED, "Enable Inside Status Led", ZclDataType.BOOLEAN, false, true, true, true));
        attributeMap.put(ATTR_ENABLEPRIVACYMODEBUTTON, new ZclAttribute(this, ATTR_ENABLEPRIVACYMODEBUTTON, "Enable Privacy Mode Button", ZclDataType.BOOLEAN, false, true, true, true));
        attributeMap.put(ATTR_WRONGCODEENTRYLIMIT, new ZclAttribute(this, ATTR_WRONGCODEENTRYLIMIT, "Wrong Code Entry Limit", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_USERCODETEMPORARYDISABLETIME, new ZclAttribute(this, ATTR_USERCODETEMPORARYDISABLETIME, "User Code Temporary Disable Time", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_SENDPINOVERTHEAIR, new ZclAttribute(this, ATTR_SENDPINOVERTHEAIR, "Send PIN Over The Air", ZclDataType.BOOLEAN, false, true, true, true));
        attributeMap.put(ATTR_REQUIREPINFORRFOPERATION, new ZclAttribute(this, ATTR_REQUIREPINFORRFOPERATION, "Require PIN For RF Operation", ZclDataType.BOOLEAN, false, true, true, true));
        attributeMap.put(ATTR_ZIGBEESECURITYLEVEL, new ZclAttribute(this, ATTR_ZIGBEESECURITYLEVEL, "ZigBee Security Level", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_ALARMMASK, new ZclAttribute(this, ATTR_ALARMMASK, "Alarm Mask", ZclDataType.BITMAP_16_BIT, false, true, true, true));
        attributeMap.put(ATTR_KEYPADOPERATIONEVENTMASK, new ZclAttribute(this, ATTR_KEYPADOPERATIONEVENTMASK, "Keypad Operation Event Mask", ZclDataType.BITMAP_16_BIT, false, true, true, true));
        attributeMap.put(ATTR_RFOPERATIONEVENTMASK, new ZclAttribute(this, ATTR_RFOPERATIONEVENTMASK, "RF Operation Event Mask", ZclDataType.BITMAP_16_BIT, false, true, true, true));
        attributeMap.put(ATTR_MANUALOPERATIONEVENTMASK, new ZclAttribute(this, ATTR_MANUALOPERATIONEVENTMASK, "Manual Operation Event Mask", ZclDataType.BITMAP_16_BIT, false, true, true, true));
        attributeMap.put(ATTR_RFIDOPERATIONEVENTMASK, new ZclAttribute(this, ATTR_RFIDOPERATIONEVENTMASK, "RFID Operation Event Mask", ZclDataType.BITMAP_16_BIT, false, true, true, true));
        attributeMap.put(ATTR_KEYPADPROGRAMMINGEVENTMASK, new ZclAttribute(this, ATTR_KEYPADPROGRAMMINGEVENTMASK, "Keypad Programming Event Mask", ZclDataType.BITMAP_16_BIT, false, true, true, true));
        attributeMap.put(ATTR_RFPROGRAMMINGEVENTMASK, new ZclAttribute(this, ATTR_RFPROGRAMMINGEVENTMASK, "RF Programming Event Mask", ZclDataType.BITMAP_16_BIT, false, true, true, true));
        attributeMap.put(ATTR_RFIDPROGRAMMINGEVENTMASK, new ZclAttribute(this, ATTR_RFIDPROGRAMMINGEVENTMASK, "RFID Programming Event Mask", ZclDataType.BITMAP_16_BIT, false, true, true, true));

        return attributeMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeServerCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();

        commandMap.put(0x0000, LockDoorResponse.class);
        commandMap.put(0x0001, UnlockDoorResponse.class);
        commandMap.put(0x0002, ToggleResponse.class);
        commandMap.put(0x0003, UnlockWithTimeoutResponse.class);
        commandMap.put(0x0004, GetLogRecordResponse.class);
        commandMap.put(0x0005, SetPinCodeResponse.class);
        commandMap.put(0x0006, GetPinCodeResponse.class);
        commandMap.put(0x0007, ClearPinCodeResponse.class);
        commandMap.put(0x0008, ClearAllPinCodesResponse.class);
        commandMap.put(0x0009, SetUserStatusResponse.class);
        commandMap.put(0x000A, GetUserStatusResponse.class);
        commandMap.put(0x000B, SetWeekDayScheduleResponse.class);
        commandMap.put(0x000C, GetWeekDayScheduleResponse.class);
        commandMap.put(0x000D, ClearWeekDayScheduleResponse.class);
        commandMap.put(0x000E, SetYearDayScheduleResponse.class);
        commandMap.put(0x000F, SetYearDayScheduleResponse.class);
        commandMap.put(0x0010, ClearYearDayScheduleResponse.class);
        commandMap.put(0x0011, SetHolidayScheduleResponse.class);
        commandMap.put(0x0012, GetHolidayScheduleResponse.class);
        commandMap.put(0x0013, ClearHolidayScheduleResponse.class);
        commandMap.put(0x0014, SetUserTypeResponse.class);
        commandMap.put(0x0015, GetUserTypeResponse.class);
        commandMap.put(0x0016, SetRfidCodeResponse.class);
        commandMap.put(0x0017, GetRfidCodeResponse.class);
        commandMap.put(0x0018, ClearRfidCodeResponse.class);
        commandMap.put(0x0019, ClearAllRfidCodesResponse.class);
        commandMap.put(0x0020, OperationEventNotification.class);
        commandMap.put(0x0021, ProgrammingEventNotification.class);

        return commandMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();

        commandMap.put(0x0000, LockDoorCommand.class);
        commandMap.put(0x0001, UnlockDoorCommand.class);
        commandMap.put(0x0002, Toggle.class);
        commandMap.put(0x0003, UnlockWithTimeout.class);
        commandMap.put(0x0004, GetLogRecord.class);
        commandMap.put(0x0005, SetPinCode.class);
        commandMap.put(0x0006, GetPinCode.class);
        commandMap.put(0x0007, ClearPinCode.class);
        commandMap.put(0x0008, ClearAllPinCodes.class);
        commandMap.put(0x0009, SetUserStatus.class);
        commandMap.put(0x000A, GetUserStatus.class);
        commandMap.put(0x000B, SetWeekDaySchedule.class);
        commandMap.put(0x000C, GetWeekDaySchedule.class);
        commandMap.put(0x000D, ClearWeekDaySchedule.class);
        commandMap.put(0x000E, SetYearDaySchedule.class);
        commandMap.put(0x000F, GetYearDaySchedule.class);
        commandMap.put(0x0010, ClearYearDaySchedule.class);
        commandMap.put(0x0011, SetHolidaySchedule.class);
        commandMap.put(0x0012, GetHolidaySchedule.class);
        commandMap.put(0x0013, ClearHolidaySchedule.class);
        commandMap.put(0x0014, SetUserType.class);
        commandMap.put(0x0015, GetUserType.class);
        commandMap.put(0x0016, SetRfidCode.class);
        commandMap.put(0x0017, GetRfidCode.class);
        commandMap.put(0x0018, ClearRfidCode.class);
        commandMap.put(0x0019, ClearAllRfidCodes.class);

        return commandMap;
    }

    /**
     * Default constructor to create a Door Lock cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclDoorLockCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Sends a {@link ZclDoorLockCommand} and returns the {@link Future} to the result which will complete when the remote
     * device response is received, or the request times out.
     *
     * @param command the {@link ZclDoorLockCommand} to send
     * @return the command result future
     */
    public Future<CommandResult> sendCommand(ZclDoorLockCommand command) {
        return super.sendCommand(command);
    }

    /**
     * Sends a response to the command. This method sets all the common elements of the response based on the command -
     * eg transactionId, direction, address...
     *
     * @param command the {@link ZclDoorLockCommand} to which the response is being sent
     * @param response the {@link ZclDoorLockCommand} to send
     */
    public Future<CommandResult> sendResponse(ZclDoorLockCommand command, ZclDoorLockCommand response) {
        return super.sendResponse(command, response);
    }

    /**
     * Get the <i>Lock State</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * Provides the current lock state
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getLockStateAsync() {
        return read(serverAttributes.get(ATTR_LOCKSTATE));
    }

    /**
     * Synchronously get the <i>Lock State</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * Provides the current lock state
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getLockState(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_LOCKSTATE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_LOCKSTATE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_LOCKSTATE));
    }

    /**
     * Set reporting for the <i>Lock State</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * Provides the current lock state
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setLockStateReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_LOCKSTATE), minInterval, maxInterval);
    }

    /**
     * Get the <i>Lock Type</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * Defines the type of lock
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getLockTypeAsync() {
        return read(serverAttributes.get(ATTR_LOCKTYPE));
    }

    /**
     * Synchronously get the <i>Lock Type</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * Defines the type of lock
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getLockType(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_LOCKTYPE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_LOCKTYPE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_LOCKTYPE));
    }

    /**
     * Set reporting for the <i>Lock Type</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * Defines the type of lock
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setLockTypeReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_LOCKTYPE), minInterval, maxInterval);
    }

    /**
     * Get the <i>Actuator Enabled</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * Boolean indicating the enabled/disabled state of the lock
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getActuatorEnabledAsync() {
        return read(serverAttributes.get(ATTR_ACTUATORENABLED));
    }

    /**
     * Synchronously get the <i>Actuator Enabled</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * Boolean indicating the enabled/disabled state of the lock
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Boolean} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Boolean getActuatorEnabled(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACTUATORENABLED).isLastValueCurrent(refreshPeriod)) {
            return (Boolean) serverAttributes.get(ATTR_ACTUATORENABLED).getLastValue();
        }

        return (Boolean) readSync(serverAttributes.get(ATTR_ACTUATORENABLED));
    }

    /**
     * Set reporting for the <i>Actuator Enabled</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * Boolean indicating the enabled/disabled state of the lock
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setActuatorEnabledReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_ACTUATORENABLED), minInterval, maxInterval);
    }

    /**
     * Get the <i>Door State</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The current state of the door lock
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDoorStateAsync() {
        return read(serverAttributes.get(ATTR_DOORSTATE));
    }

    /**
     * Synchronously get the <i>Door State</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The current state of the door lock
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getDoorState(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DOORSTATE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DOORSTATE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DOORSTATE));
    }

    /**
     * Set reporting for the <i>Door State</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The current state of the door lock
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setDoorStateReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_DOORSTATE), minInterval, maxInterval);
    }

    /**
     * Set the <i>Door Open Events</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * This attribute holds the number of door open events that have occurred since it was last
     * zeroed.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param doorOpenEvents the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setDoorOpenEvents(final Integer value) {
        return write(serverAttributes.get(ATTR_DOOROPENEVENTS), value);
    }

    /**
     * Get the <i>Door Open Events</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * This attribute holds the number of door open events that have occurred since it was last
     * zeroed.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDoorOpenEventsAsync() {
        return read(serverAttributes.get(ATTR_DOOROPENEVENTS));
    }

    /**
     * Synchronously get the <i>Door Open Events</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * This attribute holds the number of door open events that have occurred since it was last
     * zeroed.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getDoorOpenEvents(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DOOROPENEVENTS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DOOROPENEVENTS).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DOOROPENEVENTS));
    }

    /**
     * Set the <i>Door Closed Events</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * This attribute holds the number of door closed events that have occurred since it was
     * last zeroed.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param doorClosedEvents the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setDoorClosedEvents(final Integer value) {
        return write(serverAttributes.get(ATTR_DOORCLOSEDEVENTS), value);
    }

    /**
     * Get the <i>Door Closed Events</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * This attribute holds the number of door closed events that have occurred since it was
     * last zeroed.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDoorClosedEventsAsync() {
        return read(serverAttributes.get(ATTR_DOORCLOSEDEVENTS));
    }

    /**
     * Synchronously get the <i>Door Closed Events</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * This attribute holds the number of door closed events that have occurred since it was
     * last zeroed.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getDoorClosedEvents(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DOORCLOSEDEVENTS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DOORCLOSEDEVENTS).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DOORCLOSEDEVENTS));
    }

    /**
     * Set the <i>Open Period</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * This attribute holds the number of minutes the door has been open since the last time it
     * transitioned from closed to open.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param openPeriod the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setOpenPeriod(final Integer value) {
        return write(serverAttributes.get(ATTR_OPENPERIOD), value);
    }

    /**
     * Get the <i>Open Period</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * This attribute holds the number of minutes the door has been open since the last time it
     * transitioned from closed to open.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getOpenPeriodAsync() {
        return read(serverAttributes.get(ATTR_OPENPERIOD));
    }

    /**
     * Synchronously get the <i>Open Period</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * This attribute holds the number of minutes the door has been open since the last time it
     * transitioned from closed to open.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getOpenPeriod(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_OPENPERIOD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_OPENPERIOD).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_OPENPERIOD));
    }

    /**
     * Get the <i>Num Lock Records Supported</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The number of available log records.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getNumLockRecordsSupportedAsync() {
        return read(serverAttributes.get(ATTR_NUMLOCKRECORDSSUPPORTED));
    }

    /**
     * Synchronously get the <i>Num Lock Records Supported</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The number of available log records.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getNumLockRecordsSupported(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_NUMLOCKRECORDSSUPPORTED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_NUMLOCKRECORDSSUPPORTED).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_NUMLOCKRECORDSSUPPORTED));
    }

    /**
     * Set reporting for the <i>Num Lock Records Supported</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The number of available log records.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setNumLockRecordsSupportedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_NUMLOCKRECORDSSUPPORTED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Num Total Users Supported</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * Number of total users supported by the lock. This value is equal to the higher one of [# of
     * PIN Users Supported] and [# of RFID Users Supported]
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getNumTotalUsersSupportedAsync() {
        return read(serverAttributes.get(ATTR_NUMTOTALUSERSSUPPORTED));
    }

    /**
     * Synchronously get the <i>Num Total Users Supported</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * Number of total users supported by the lock. This value is equal to the higher one of [# of
     * PIN Users Supported] and [# of RFID Users Supported]
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getNumTotalUsersSupported(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_NUMTOTALUSERSSUPPORTED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_NUMTOTALUSERSSUPPORTED).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_NUMTOTALUSERSSUPPORTED));
    }

    /**
     * Set reporting for the <i>Num Total Users Supported</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * Number of total users supported by the lock. This value is equal to the higher one of [# of
     * PIN Users Supported] and [# of RFID Users Supported]
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setNumTotalUsersSupportedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_NUMTOTALUSERSSUPPORTED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Num PIN Users Supported</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The number of PIN users supported.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getNumPinUsersSupportedAsync() {
        return read(serverAttributes.get(ATTR_NUMPINUSERSSUPPORTED));
    }

    /**
     * Synchronously get the <i>Num PIN Users Supported</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The number of PIN users supported.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getNumPinUsersSupported(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_NUMPINUSERSSUPPORTED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_NUMPINUSERSSUPPORTED).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_NUMPINUSERSSUPPORTED));
    }

    /**
     * Set reporting for the <i>Num PIN Users Supported</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The number of PIN users supported.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setNumPinUsersSupportedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_NUMPINUSERSSUPPORTED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Num RFID Users Supported</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The number of RFID users supported.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getNumRfidUsersSupportedAsync() {
        return read(serverAttributes.get(ATTR_NUMRFIDUSERSSUPPORTED));
    }

    /**
     * Synchronously get the <i>Num RFID Users Supported</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The number of RFID users supported.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getNumRfidUsersSupported(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_NUMRFIDUSERSSUPPORTED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_NUMRFIDUSERSSUPPORTED).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_NUMRFIDUSERSSUPPORTED));
    }

    /**
     * Set reporting for the <i>Num RFID Users Supported</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The number of RFID users supported.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setNumRfidUsersSupportedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_NUMRFIDUSERSSUPPORTED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Num Weekday Schedules Supported Per User</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The number of configurable week day schedule supported per user.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getNumWeekdaySchedulesSupportedPerUserAsync() {
        return read(serverAttributes.get(ATTR_NUMWEEKDAYSCHEDULESSUPPORTEDPERUSER));
    }

    /**
     * Synchronously get the <i>Num Weekday Schedules Supported Per User</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The number of configurable week day schedule supported per user.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getNumWeekdaySchedulesSupportedPerUser(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_NUMWEEKDAYSCHEDULESSUPPORTEDPERUSER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_NUMWEEKDAYSCHEDULESSUPPORTEDPERUSER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_NUMWEEKDAYSCHEDULESSUPPORTEDPERUSER));
    }

    /**
     * Set reporting for the <i>Num Weekday Schedules Supported Per User</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The number of configurable week day schedule supported per user.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setNumWeekdaySchedulesSupportedPerUserReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_NUMWEEKDAYSCHEDULESSUPPORTEDPERUSER), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Num Yearday Schedules Supported Per User</i> attribute [attribute ID <b>0x0015</b>].
     * <p>
     * The number of configurable year day schedule supported per user
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getNumYeardaySchedulesSupportedPerUserAsync() {
        return read(serverAttributes.get(ATTR_NUMYEARDAYSCHEDULESSUPPORTEDPERUSER));
    }

    /**
     * Synchronously get the <i>Num Yearday Schedules Supported Per User</i> attribute [attribute ID <b>0x0015</b>].
     * <p>
     * The number of configurable year day schedule supported per user
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getNumYeardaySchedulesSupportedPerUser(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_NUMYEARDAYSCHEDULESSUPPORTEDPERUSER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_NUMYEARDAYSCHEDULESSUPPORTEDPERUSER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_NUMYEARDAYSCHEDULESSUPPORTEDPERUSER));
    }

    /**
     * Set reporting for the <i>Num Yearday Schedules Supported Per User</i> attribute [attribute ID <b>0x0015</b>].
     * <p>
     * The number of configurable year day schedule supported per user
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setNumYeardaySchedulesSupportedPerUserReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_NUMYEARDAYSCHEDULESSUPPORTEDPERUSER), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Num Holiday Schedules Supported Per User</i> attribute [attribute ID <b>0x0016</b>].
     * <p>
     * The number of holiday schedules supported for the entire door lock device.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getNumHolidaySchedulesSupportedPerUserAsync() {
        return read(serverAttributes.get(ATTR_NUMHOLIDAYSCHEDULESSUPPORTEDPERUSER));
    }

    /**
     * Synchronously get the <i>Num Holiday Schedules Supported Per User</i> attribute [attribute ID <b>0x0016</b>].
     * <p>
     * The number of holiday schedules supported for the entire door lock device.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getNumHolidaySchedulesSupportedPerUser(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_NUMHOLIDAYSCHEDULESSUPPORTEDPERUSER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_NUMHOLIDAYSCHEDULESSUPPORTEDPERUSER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_NUMHOLIDAYSCHEDULESSUPPORTEDPERUSER));
    }

    /**
     * Set reporting for the <i>Num Holiday Schedules Supported Per User</i> attribute [attribute ID <b>0x0016</b>].
     * <p>
     * The number of holiday schedules supported for the entire door lock device.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setNumHolidaySchedulesSupportedPerUserReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_NUMHOLIDAYSCHEDULESSUPPORTEDPERUSER), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Max PIN Length</i> attribute [attribute ID <b>0x0017</b>].
     * <p>
     * An 8 bit value indicates the maximum length in bytes of a PIN Code on this device. The
     * default is set to 8 since most lock manufacturers currently allow PIN Codes of 8 bytes or
     * less.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMaxPinLengthAsync() {
        return read(serverAttributes.get(ATTR_MAXPINLENGTH));
    }

    /**
     * Synchronously get the <i>Max PIN Length</i> attribute [attribute ID <b>0x0017</b>].
     * <p>
     * An 8 bit value indicates the maximum length in bytes of a PIN Code on this device. The
     * default is set to 8 since most lock manufacturers currently allow PIN Codes of 8 bytes or
     * less.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getMaxPinLength(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MAXPINLENGTH).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MAXPINLENGTH).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MAXPINLENGTH));
    }

    /**
     * Set reporting for the <i>Max PIN Length</i> attribute [attribute ID <b>0x0017</b>].
     * <p>
     * An 8 bit value indicates the maximum length in bytes of a PIN Code on this device. The
     * default is set to 8 since most lock manufacturers currently allow PIN Codes of 8 bytes or
     * less.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setMaxPinLengthReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MAXPINLENGTH), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Min PIN Length</i> attribute [attribute ID <b>0x0018</b>].
     * <p>
     * An 8 bit value indicates the minimum length in bytes of a PIN Code on this device. The
     * default is set to 4 since most lock manufacturers do not support PIN Codes that are
     * shorter than 4 bytes.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMinPinLengthAsync() {
        return read(serverAttributes.get(ATTR_MINPINLENGTH));
    }

    /**
     * Synchronously get the <i>Min PIN Length</i> attribute [attribute ID <b>0x0018</b>].
     * <p>
     * An 8 bit value indicates the minimum length in bytes of a PIN Code on this device. The
     * default is set to 4 since most lock manufacturers do not support PIN Codes that are
     * shorter than 4 bytes.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getMinPinLength(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MINPINLENGTH).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MINPINLENGTH).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MINPINLENGTH));
    }

    /**
     * Set reporting for the <i>Min PIN Length</i> attribute [attribute ID <b>0x0018</b>].
     * <p>
     * An 8 bit value indicates the minimum length in bytes of a PIN Code on this device. The
     * default is set to 4 since most lock manufacturers do not support PIN Codes that are
     * shorter than 4 bytes.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setMinPinLengthReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MINPINLENGTH), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Max RFID Code Length</i> attribute [attribute ID <b>0x0019</b>].
     * <p>
     * An 8 bit value indicates the maximum length in bytes of a RFID Code on this device. The
     * value depends on the RFID code range specified by the manufacturer, if media
     * anti-collision identifiers (UID) are used as RFID code, a value of 20 (equals 10 Byte ISO
     * 14443A UID) is recommended.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMaxRfidCodeLengthAsync() {
        return read(serverAttributes.get(ATTR_MAXRFIDCODELENGTH));
    }

    /**
     * Synchronously get the <i>Max RFID Code Length</i> attribute [attribute ID <b>0x0019</b>].
     * <p>
     * An 8 bit value indicates the maximum length in bytes of a RFID Code on this device. The
     * value depends on the RFID code range specified by the manufacturer, if media
     * anti-collision identifiers (UID) are used as RFID code, a value of 20 (equals 10 Byte ISO
     * 14443A UID) is recommended.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getMaxRfidCodeLength(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MAXRFIDCODELENGTH).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MAXRFIDCODELENGTH).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MAXRFIDCODELENGTH));
    }

    /**
     * Set reporting for the <i>Max RFID Code Length</i> attribute [attribute ID <b>0x0019</b>].
     * <p>
     * An 8 bit value indicates the maximum length in bytes of a RFID Code on this device. The
     * value depends on the RFID code range specified by the manufacturer, if media
     * anti-collision identifiers (UID) are used as RFID code, a value of 20 (equals 10 Byte ISO
     * 14443A UID) is recommended.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setMaxRfidCodeLengthReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MAXRFIDCODELENGTH), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Min RFID Code Length</i> attribute [attribute ID <b>0x001A</b>].
     * <p>
     * An 8 bit value indicates the minimum length in bytes of a RFID Code on this device. The
     * value depends on the RFID code range specified by the manufacturer, if media
     * anti-collision identifiers (UID) are used as RFID code, a value of 8 (equals 4 Byte ISO
     * 14443A UID) is recommended.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMinRfidCodeLengthAsync() {
        return read(serverAttributes.get(ATTR_MINRFIDCODELENGTH));
    }

    /**
     * Synchronously get the <i>Min RFID Code Length</i> attribute [attribute ID <b>0x001A</b>].
     * <p>
     * An 8 bit value indicates the minimum length in bytes of a RFID Code on this device. The
     * value depends on the RFID code range specified by the manufacturer, if media
     * anti-collision identifiers (UID) are used as RFID code, a value of 8 (equals 4 Byte ISO
     * 14443A UID) is recommended.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getMinRfidCodeLength(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MINRFIDCODELENGTH).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MINRFIDCODELENGTH).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MINRFIDCODELENGTH));
    }

    /**
     * Set reporting for the <i>Min RFID Code Length</i> attribute [attribute ID <b>0x001A</b>].
     * <p>
     * An 8 bit value indicates the minimum length in bytes of a RFID Code on this device. The
     * value depends on the RFID code range specified by the manufacturer, if media
     * anti-collision identifiers (UID) are used as RFID code, a value of 8 (equals 4 Byte ISO
     * 14443A UID) is recommended.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setMinRfidCodeLengthReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MINRFIDCODELENGTH), minInterval, maxInterval, reportableChange);
    }

    /**
     * Set the <i>Enable Logging</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * Enable/disable event logging. When event logging is enabled, all event messages are
     * stored on the lock for retrieval. Logging events can be but not limited to Tamper Alarm,
     * Lock, Unlock, Autolock, User Code Added, User Code Deleted, Schedule Added, and
     * Schedule Deleted. For a full detail of all the possible alarms and events, please refer
     * to the full list in the Alarm and Event Masks Attribute Set.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param enableLogging the {@link Boolean} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setEnableLogging(final Boolean value) {
        return write(serverAttributes.get(ATTR_ENABLELOGGING), value);
    }

    /**
     * Get the <i>Enable Logging</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * Enable/disable event logging. When event logging is enabled, all event messages are
     * stored on the lock for retrieval. Logging events can be but not limited to Tamper Alarm,
     * Lock, Unlock, Autolock, User Code Added, User Code Deleted, Schedule Added, and
     * Schedule Deleted. For a full detail of all the possible alarms and events, please refer
     * to the full list in the Alarm and Event Masks Attribute Set.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getEnableLoggingAsync() {
        return read(serverAttributes.get(ATTR_ENABLELOGGING));
    }

    /**
     * Synchronously get the <i>Enable Logging</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * Enable/disable event logging. When event logging is enabled, all event messages are
     * stored on the lock for retrieval. Logging events can be but not limited to Tamper Alarm,
     * Lock, Unlock, Autolock, User Code Added, User Code Deleted, Schedule Added, and
     * Schedule Deleted. For a full detail of all the possible alarms and events, please refer
     * to the full list in the Alarm and Event Masks Attribute Set.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Boolean} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Boolean getEnableLogging(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ENABLELOGGING).isLastValueCurrent(refreshPeriod)) {
            return (Boolean) serverAttributes.get(ATTR_ENABLELOGGING).getLastValue();
        }

        return (Boolean) readSync(serverAttributes.get(ATTR_ENABLELOGGING));
    }

    /**
     * Set the <i>Language</i> attribute [attribute ID <b>0x0021</b>].
     * <p>
     * Modifies the language for the on-screen or audible user interface using three bytes
     * from ISO-639-1. It consists of one byte of length and two bytes for the language code. For
     * example if the language is set to English, the value would be "02 65 6E" for the language
     * code "en"
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param language the {@link String} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setLanguage(final String value) {
        return write(serverAttributes.get(ATTR_LANGUAGE), value);
    }

    /**
     * Get the <i>Language</i> attribute [attribute ID <b>0x0021</b>].
     * <p>
     * Modifies the language for the on-screen or audible user interface using three bytes
     * from ISO-639-1. It consists of one byte of length and two bytes for the language code. For
     * example if the language is set to English, the value would be "02 65 6E" for the language
     * code "en"
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getLanguageAsync() {
        return read(serverAttributes.get(ATTR_LANGUAGE));
    }

    /**
     * Synchronously get the <i>Language</i> attribute [attribute ID <b>0x0021</b>].
     * <p>
     * Modifies the language for the on-screen or audible user interface using three bytes
     * from ISO-639-1. It consists of one byte of length and two bytes for the language code. For
     * example if the language is set to English, the value would be "02 65 6E" for the language
     * code "en"
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link String} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public String getLanguage(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_LANGUAGE).isLastValueCurrent(refreshPeriod)) {
            return (String) serverAttributes.get(ATTR_LANGUAGE).getLastValue();
        }

        return (String) readSync(serverAttributes.get(ATTR_LANGUAGE));
    }

    /**
     * Set the <i>LED Settings</i> attribute [attribute ID <b>0x0022</b>].
     * <p>
     * The settings for the LED support three different modes
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param ledSettings the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setLedSettings(final Integer value) {
        return write(serverAttributes.get(ATTR_LEDSETTINGS), value);
    }

    /**
     * Get the <i>LED Settings</i> attribute [attribute ID <b>0x0022</b>].
     * <p>
     * The settings for the LED support three different modes
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getLedSettingsAsync() {
        return read(serverAttributes.get(ATTR_LEDSETTINGS));
    }

    /**
     * Synchronously get the <i>LED Settings</i> attribute [attribute ID <b>0x0022</b>].
     * <p>
     * The settings for the LED support three different modes
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getLedSettings(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_LEDSETTINGS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_LEDSETTINGS).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_LEDSETTINGS));
    }

    /**
     * Set the <i>Auto Relock Time</i> attribute [attribute ID <b>0x0023</b>].
     * <p>
     * The number of seconds to wait after unlocking a lock before it automatically locks
     * again. 0=disabled. If set, unlock operations from any source will be timed. For one time
     * unlock with timeout use the specific command.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param autoRelockTime the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setAutoRelockTime(final Integer value) {
        return write(serverAttributes.get(ATTR_AUTORELOCKTIME), value);
    }

    /**
     * Get the <i>Auto Relock Time</i> attribute [attribute ID <b>0x0023</b>].
     * <p>
     * The number of seconds to wait after unlocking a lock before it automatically locks
     * again. 0=disabled. If set, unlock operations from any source will be timed. For one time
     * unlock with timeout use the specific command.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAutoRelockTimeAsync() {
        return read(serverAttributes.get(ATTR_AUTORELOCKTIME));
    }

    /**
     * Synchronously get the <i>Auto Relock Time</i> attribute [attribute ID <b>0x0023</b>].
     * <p>
     * The number of seconds to wait after unlocking a lock before it automatically locks
     * again. 0=disabled. If set, unlock operations from any source will be timed. For one time
     * unlock with timeout use the specific command.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getAutoRelockTime(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_AUTORELOCKTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_AUTORELOCKTIME).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_AUTORELOCKTIME));
    }

    /**
     * Set the <i>Sound Volume</i> attribute [attribute ID <b>0x0024</b>].
     * <p>
     * The sound volume on a door lock has three possible settings: silent, low and high volumes
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param soundVolume the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setSoundVolume(final Integer value) {
        return write(serverAttributes.get(ATTR_SOUNDVOLUME), value);
    }

    /**
     * Get the <i>Sound Volume</i> attribute [attribute ID <b>0x0024</b>].
     * <p>
     * The sound volume on a door lock has three possible settings: silent, low and high volumes
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getSoundVolumeAsync() {
        return read(serverAttributes.get(ATTR_SOUNDVOLUME));
    }

    /**
     * Synchronously get the <i>Sound Volume</i> attribute [attribute ID <b>0x0024</b>].
     * <p>
     * The sound volume on a door lock has three possible settings: silent, low and high volumes
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getSoundVolume(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_SOUNDVOLUME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_SOUNDVOLUME).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_SOUNDVOLUME));
    }

    /**
     * Set the <i>Operating Mode</i> attribute [attribute ID <b>0x0025</b>].
     * <p>
     * Shows the current operating mode
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param operatingMode the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setOperatingMode(final Integer value) {
        return write(serverAttributes.get(ATTR_OPERATINGMODE), value);
    }

    /**
     * Get the <i>Operating Mode</i> attribute [attribute ID <b>0x0025</b>].
     * <p>
     * Shows the current operating mode
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getOperatingModeAsync() {
        return read(serverAttributes.get(ATTR_OPERATINGMODE));
    }

    /**
     * Synchronously get the <i>Operating Mode</i> attribute [attribute ID <b>0x0025</b>].
     * <p>
     * Shows the current operating mode
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getOperatingMode(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_OPERATINGMODE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_OPERATINGMODE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_OPERATINGMODE));
    }

    /**
     * Get the <i>Supported Operating Modes</i> attribute [attribute ID <b>0x0026</b>].
     * <p>
     * This bitmap contains all operating bits of the Operating Mode Attribute supported by
     * the lock. The value of the enumeration in “Operating Mode” defines the related bit to be
     * set, as shown in Table 7-16. All bits supported by a lock shall be set to zero.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getSupportedOperatingModesAsync() {
        return read(serverAttributes.get(ATTR_SUPPORTEDOPERATINGMODES));
    }

    /**
     * Synchronously get the <i>Supported Operating Modes</i> attribute [attribute ID <b>0x0026</b>].
     * <p>
     * This bitmap contains all operating bits of the Operating Mode Attribute supported by
     * the lock. The value of the enumeration in “Operating Mode” defines the related bit to be
     * set, as shown in Table 7-16. All bits supported by a lock shall be set to zero.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getSupportedOperatingModes(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_SUPPORTEDOPERATINGMODES).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_SUPPORTEDOPERATINGMODES).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_SUPPORTEDOPERATINGMODES));
    }

    /**
     * Set reporting for the <i>Supported Operating Modes</i> attribute [attribute ID <b>0x0026</b>].
     * <p>
     * This bitmap contains all operating bits of the Operating Mode Attribute supported by
     * the lock. The value of the enumeration in “Operating Mode” defines the related bit to be
     * set, as shown in Table 7-16. All bits supported by a lock shall be set to zero.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setSupportedOperatingModesReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_SUPPORTEDOPERATINGMODES), minInterval, maxInterval);
    }

    /**
     * Get the <i>Default Configuration Register</i> attribute [attribute ID <b>0x0027</b>].
     * <p>
     * This attribute represents the default configurations as they are physically set on the
     * device (example: hardware dip switch setting, etc…) and represents the default
     * setting for some of the attributes within this Operational Setting Attribute Set (for
     * example: LED, Auto Lock, Sound Volume, and Operating Mode attributes).
     * <p>
     * This is a read-only attribute and is intended to allow clients to determine what changes
     * may need to be made without having to query all the included attributes. It may be
     * beneficial for the clients to know what the device’s original settings were in the event
     * that the device needs to be restored to factory default settings.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDefaultConfigurationRegisterAsync() {
        return read(serverAttributes.get(ATTR_DEFAULTCONFIGURATIONREGISTER));
    }

    /**
     * Synchronously get the <i>Default Configuration Register</i> attribute [attribute ID <b>0x0027</b>].
     * <p>
     * This attribute represents the default configurations as they are physically set on the
     * device (example: hardware dip switch setting, etc…) and represents the default
     * setting for some of the attributes within this Operational Setting Attribute Set (for
     * example: LED, Auto Lock, Sound Volume, and Operating Mode attributes).
     * <p>
     * This is a read-only attribute and is intended to allow clients to determine what changes
     * may need to be made without having to query all the included attributes. It may be
     * beneficial for the clients to know what the device’s original settings were in the event
     * that the device needs to be restored to factory default settings.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getDefaultConfigurationRegister(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DEFAULTCONFIGURATIONREGISTER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DEFAULTCONFIGURATIONREGISTER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DEFAULTCONFIGURATIONREGISTER));
    }

    /**
     * Set reporting for the <i>Default Configuration Register</i> attribute [attribute ID <b>0x0027</b>].
     * <p>
     * This attribute represents the default configurations as they are physically set on the
     * device (example: hardware dip switch setting, etc…) and represents the default
     * setting for some of the attributes within this Operational Setting Attribute Set (for
     * example: LED, Auto Lock, Sound Volume, and Operating Mode attributes).
     * <p>
     * This is a read-only attribute and is intended to allow clients to determine what changes
     * may need to be made without having to query all the included attributes. It may be
     * beneficial for the clients to know what the device’s original settings were in the event
     * that the device needs to be restored to factory default settings.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setDefaultConfigurationRegisterReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_DEFAULTCONFIGURATIONREGISTER), minInterval, maxInterval);
    }

    /**
     * Set the <i>Enable Local Programming</i> attribute [attribute ID <b>0x0028</b>].
     * <p>
     * Enable/disable local programming on the door lock. The local programming features
     * includes but not limited to adding new user codes, deleting existing user codes, add new
     * schedule, deleting existing schedule on the local door lock interfaces. If this value
     * is set to 0x01 or TRUE then local programming is enabled on the door lock. If it is set to
     * 0x00 or FALSE then local programming is disabled on the door lock. Local programming is
     * enabled by default.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param enableLocalProgramming the {@link Boolean} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setEnableLocalProgramming(final Boolean value) {
        return write(serverAttributes.get(ATTR_ENABLELOCALPROGRAMMING), value);
    }

    /**
     * Get the <i>Enable Local Programming</i> attribute [attribute ID <b>0x0028</b>].
     * <p>
     * Enable/disable local programming on the door lock. The local programming features
     * includes but not limited to adding new user codes, deleting existing user codes, add new
     * schedule, deleting existing schedule on the local door lock interfaces. If this value
     * is set to 0x01 or TRUE then local programming is enabled on the door lock. If it is set to
     * 0x00 or FALSE then local programming is disabled on the door lock. Local programming is
     * enabled by default.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getEnableLocalProgrammingAsync() {
        return read(serverAttributes.get(ATTR_ENABLELOCALPROGRAMMING));
    }

    /**
     * Synchronously get the <i>Enable Local Programming</i> attribute [attribute ID <b>0x0028</b>].
     * <p>
     * Enable/disable local programming on the door lock. The local programming features
     * includes but not limited to adding new user codes, deleting existing user codes, add new
     * schedule, deleting existing schedule on the local door lock interfaces. If this value
     * is set to 0x01 or TRUE then local programming is enabled on the door lock. If it is set to
     * 0x00 or FALSE then local programming is disabled on the door lock. Local programming is
     * enabled by default.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Boolean} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Boolean getEnableLocalProgramming(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ENABLELOCALPROGRAMMING).isLastValueCurrent(refreshPeriod)) {
            return (Boolean) serverAttributes.get(ATTR_ENABLELOCALPROGRAMMING).getLastValue();
        }

        return (Boolean) readSync(serverAttributes.get(ATTR_ENABLELOCALPROGRAMMING));
    }

    /**
     * Set the <i>Enable One Touch Locking</i> attribute [attribute ID <b>0x0029</b>].
     * <p>
     * Enable/disable the ability to lock the door lock with a single touch on the door lock.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param enableOneTouchLocking the {@link Boolean} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setEnableOneTouchLocking(final Boolean value) {
        return write(serverAttributes.get(ATTR_ENABLEONETOUCHLOCKING), value);
    }

    /**
     * Get the <i>Enable One Touch Locking</i> attribute [attribute ID <b>0x0029</b>].
     * <p>
     * Enable/disable the ability to lock the door lock with a single touch on the door lock.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getEnableOneTouchLockingAsync() {
        return read(serverAttributes.get(ATTR_ENABLEONETOUCHLOCKING));
    }

    /**
     * Synchronously get the <i>Enable One Touch Locking</i> attribute [attribute ID <b>0x0029</b>].
     * <p>
     * Enable/disable the ability to lock the door lock with a single touch on the door lock.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Boolean} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Boolean getEnableOneTouchLocking(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ENABLEONETOUCHLOCKING).isLastValueCurrent(refreshPeriod)) {
            return (Boolean) serverAttributes.get(ATTR_ENABLEONETOUCHLOCKING).getLastValue();
        }

        return (Boolean) readSync(serverAttributes.get(ATTR_ENABLEONETOUCHLOCKING));
    }

    /**
     * Set the <i>Enable Inside Status Led</i> attribute [attribute ID <b>0x002A</b>].
     * <p>
     * Enable/disable an inside LED that allows the user to see at a glance if the door is locked.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param enableInsideStatusLed the {@link Boolean} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setEnableInsideStatusLed(final Boolean value) {
        return write(serverAttributes.get(ATTR_ENABLEINSIDESTATUSLED), value);
    }

    /**
     * Get the <i>Enable Inside Status Led</i> attribute [attribute ID <b>0x002A</b>].
     * <p>
     * Enable/disable an inside LED that allows the user to see at a glance if the door is locked.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getEnableInsideStatusLedAsync() {
        return read(serverAttributes.get(ATTR_ENABLEINSIDESTATUSLED));
    }

    /**
     * Synchronously get the <i>Enable Inside Status Led</i> attribute [attribute ID <b>0x002A</b>].
     * <p>
     * Enable/disable an inside LED that allows the user to see at a glance if the door is locked.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Boolean} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Boolean getEnableInsideStatusLed(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ENABLEINSIDESTATUSLED).isLastValueCurrent(refreshPeriod)) {
            return (Boolean) serverAttributes.get(ATTR_ENABLEINSIDESTATUSLED).getLastValue();
        }

        return (Boolean) readSync(serverAttributes.get(ATTR_ENABLEINSIDESTATUSLED));
    }

    /**
     * Set the <i>Enable Privacy Mode Button</i> attribute [attribute ID <b>0x002B</b>].
     * <p>
     * Enable/disable a button inside the door that is used to put the lock into privacy mode.
     * When the lock is in privacy mode it cannot be manipulated from the outside.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param enablePrivacyModeButton the {@link Boolean} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setEnablePrivacyModeButton(final Boolean value) {
        return write(serverAttributes.get(ATTR_ENABLEPRIVACYMODEBUTTON), value);
    }

    /**
     * Get the <i>Enable Privacy Mode Button</i> attribute [attribute ID <b>0x002B</b>].
     * <p>
     * Enable/disable a button inside the door that is used to put the lock into privacy mode.
     * When the lock is in privacy mode it cannot be manipulated from the outside.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getEnablePrivacyModeButtonAsync() {
        return read(serverAttributes.get(ATTR_ENABLEPRIVACYMODEBUTTON));
    }

    /**
     * Synchronously get the <i>Enable Privacy Mode Button</i> attribute [attribute ID <b>0x002B</b>].
     * <p>
     * Enable/disable a button inside the door that is used to put the lock into privacy mode.
     * When the lock is in privacy mode it cannot be manipulated from the outside.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Boolean} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Boolean getEnablePrivacyModeButton(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ENABLEPRIVACYMODEBUTTON).isLastValueCurrent(refreshPeriod)) {
            return (Boolean) serverAttributes.get(ATTR_ENABLEPRIVACYMODEBUTTON).getLastValue();
        }

        return (Boolean) readSync(serverAttributes.get(ATTR_ENABLEPRIVACYMODEBUTTON));
    }

    /**
     * Set the <i>Wrong Code Entry Limit</i> attribute [attribute ID <b>0x0030</b>].
     * <p>
     * The number of incorrect codes or RFID presentment attempts a user is allowed to enter
     * before the door will enter a lockout state. The lockout state will be for the duration of
     * UserCodeTemporaryDisableTime.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param wrongCodeEntryLimit the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setWrongCodeEntryLimit(final Integer value) {
        return write(serverAttributes.get(ATTR_WRONGCODEENTRYLIMIT), value);
    }

    /**
     * Get the <i>Wrong Code Entry Limit</i> attribute [attribute ID <b>0x0030</b>].
     * <p>
     * The number of incorrect codes or RFID presentment attempts a user is allowed to enter
     * before the door will enter a lockout state. The lockout state will be for the duration of
     * UserCodeTemporaryDisableTime.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getWrongCodeEntryLimitAsync() {
        return read(serverAttributes.get(ATTR_WRONGCODEENTRYLIMIT));
    }

    /**
     * Synchronously get the <i>Wrong Code Entry Limit</i> attribute [attribute ID <b>0x0030</b>].
     * <p>
     * The number of incorrect codes or RFID presentment attempts a user is allowed to enter
     * before the door will enter a lockout state. The lockout state will be for the duration of
     * UserCodeTemporaryDisableTime.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getWrongCodeEntryLimit(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_WRONGCODEENTRYLIMIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_WRONGCODEENTRYLIMIT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_WRONGCODEENTRYLIMIT));
    }

    /**
     * Set the <i>User Code Temporary Disable Time</i> attribute [attribute ID <b>0x0031</b>].
     * <p>
     * The number of seconds that the lock shuts down following wrong code entry. 1-255
     * seconds. Device can shutdown to lock user out for specified amount of time. (Makes it
     * difficult to try and guess a PIN for the device.)
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param userCodeTemporaryDisableTime the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setUserCodeTemporaryDisableTime(final Integer value) {
        return write(serverAttributes.get(ATTR_USERCODETEMPORARYDISABLETIME), value);
    }

    /**
     * Get the <i>User Code Temporary Disable Time</i> attribute [attribute ID <b>0x0031</b>].
     * <p>
     * The number of seconds that the lock shuts down following wrong code entry. 1-255
     * seconds. Device can shutdown to lock user out for specified amount of time. (Makes it
     * difficult to try and guess a PIN for the device.)
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getUserCodeTemporaryDisableTimeAsync() {
        return read(serverAttributes.get(ATTR_USERCODETEMPORARYDISABLETIME));
    }

    /**
     * Synchronously get the <i>User Code Temporary Disable Time</i> attribute [attribute ID <b>0x0031</b>].
     * <p>
     * The number of seconds that the lock shuts down following wrong code entry. 1-255
     * seconds. Device can shutdown to lock user out for specified amount of time. (Makes it
     * difficult to try and guess a PIN for the device.)
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getUserCodeTemporaryDisableTime(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_USERCODETEMPORARYDISABLETIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_USERCODETEMPORARYDISABLETIME).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_USERCODETEMPORARYDISABLETIME));
    }

    /**
     * Set the <i>Send PIN Over The Air</i> attribute [attribute ID <b>0x0032</b>].
     * <p>
     * Boolean set to True if it is ok for the door lock server to send PINs over the air. This
     * attribute determines the behavior of the server’s TX operation. If it is false, then it
     * is not ok for the device to send PIN in any messages over the air.
     * <p>
     * The PIN field within any door lock cluster message shall keep the first octet unchanged
     * and masks the actual code by replacing with 0xFF. For example (PIN "1234" ): If the
     * attribute value is True, 0x04 0x31 0x32 0x33 0x34 shall be used in the PIN field in any door
     * lock cluster message payload. If the attribute value is False, 0x04 0xFF 0xFF 0xFF 0xFF
     * shall be used.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param sendPinOverTheAir the {@link Boolean} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setSendPinOverTheAir(final Boolean value) {
        return write(serverAttributes.get(ATTR_SENDPINOVERTHEAIR), value);
    }

    /**
     * Get the <i>Send PIN Over The Air</i> attribute [attribute ID <b>0x0032</b>].
     * <p>
     * Boolean set to True if it is ok for the door lock server to send PINs over the air. This
     * attribute determines the behavior of the server’s TX operation. If it is false, then it
     * is not ok for the device to send PIN in any messages over the air.
     * <p>
     * The PIN field within any door lock cluster message shall keep the first octet unchanged
     * and masks the actual code by replacing with 0xFF. For example (PIN "1234" ): If the
     * attribute value is True, 0x04 0x31 0x32 0x33 0x34 shall be used in the PIN field in any door
     * lock cluster message payload. If the attribute value is False, 0x04 0xFF 0xFF 0xFF 0xFF
     * shall be used.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getSendPinOverTheAirAsync() {
        return read(serverAttributes.get(ATTR_SENDPINOVERTHEAIR));
    }

    /**
     * Synchronously get the <i>Send PIN Over The Air</i> attribute [attribute ID <b>0x0032</b>].
     * <p>
     * Boolean set to True if it is ok for the door lock server to send PINs over the air. This
     * attribute determines the behavior of the server’s TX operation. If it is false, then it
     * is not ok for the device to send PIN in any messages over the air.
     * <p>
     * The PIN field within any door lock cluster message shall keep the first octet unchanged
     * and masks the actual code by replacing with 0xFF. For example (PIN "1234" ): If the
     * attribute value is True, 0x04 0x31 0x32 0x33 0x34 shall be used in the PIN field in any door
     * lock cluster message payload. If the attribute value is False, 0x04 0xFF 0xFF 0xFF 0xFF
     * shall be used.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Boolean} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Boolean getSendPinOverTheAir(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_SENDPINOVERTHEAIR).isLastValueCurrent(refreshPeriod)) {
            return (Boolean) serverAttributes.get(ATTR_SENDPINOVERTHEAIR).getLastValue();
        }

        return (Boolean) readSync(serverAttributes.get(ATTR_SENDPINOVERTHEAIR));
    }

    /**
     * Set the <i>Require PIN For RF Operation</i> attribute [attribute ID <b>0x0033</b>].
     * <p>
     * Boolean set to True if the door lock server requires that an optional PINs be included in
     * the payload of RF lock operation events like Lock, Unlock and Toggle in order to
     * function.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param requirePinForRfOperation the {@link Boolean} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setRequirePinForRfOperation(final Boolean value) {
        return write(serverAttributes.get(ATTR_REQUIREPINFORRFOPERATION), value);
    }

    /**
     * Get the <i>Require PIN For RF Operation</i> attribute [attribute ID <b>0x0033</b>].
     * <p>
     * Boolean set to True if the door lock server requires that an optional PINs be included in
     * the payload of RF lock operation events like Lock, Unlock and Toggle in order to
     * function.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRequirePinForRfOperationAsync() {
        return read(serverAttributes.get(ATTR_REQUIREPINFORRFOPERATION));
    }

    /**
     * Synchronously get the <i>Require PIN For RF Operation</i> attribute [attribute ID <b>0x0033</b>].
     * <p>
     * Boolean set to True if the door lock server requires that an optional PINs be included in
     * the payload of RF lock operation events like Lock, Unlock and Toggle in order to
     * function.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Boolean} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Boolean getRequirePinForRfOperation(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_REQUIREPINFORRFOPERATION).isLastValueCurrent(refreshPeriod)) {
            return (Boolean) serverAttributes.get(ATTR_REQUIREPINFORRFOPERATION).getLastValue();
        }

        return (Boolean) readSync(serverAttributes.get(ATTR_REQUIREPINFORRFOPERATION));
    }

    /**
     * Get the <i>ZigBee Security Level</i> attribute [attribute ID <b>0x0034</b>].
     * <p>
     * Door locks may sometimes wish to implement a higher level of security within the
     * application protocol in additional to the default network security. For instance a
     * door lock may wish to use additional APS security for cluster transactions. This
     * protects the door lock against being controlled by any other devices which have access
     * to the network key.
     * <p>
     * The Security Level attribute allows the door lock manufacturer to indicate what level
     * of security the doorlock requires.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getZigbeeSecurityLevelAsync() {
        return read(serverAttributes.get(ATTR_ZIGBEESECURITYLEVEL));
    }

    /**
     * Synchronously get the <i>ZigBee Security Level</i> attribute [attribute ID <b>0x0034</b>].
     * <p>
     * Door locks may sometimes wish to implement a higher level of security within the
     * application protocol in additional to the default network security. For instance a
     * door lock may wish to use additional APS security for cluster transactions. This
     * protects the door lock against being controlled by any other devices which have access
     * to the network key.
     * <p>
     * The Security Level attribute allows the door lock manufacturer to indicate what level
     * of security the doorlock requires.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getZigbeeSecurityLevel(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ZIGBEESECURITYLEVEL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ZIGBEESECURITYLEVEL).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ZIGBEESECURITYLEVEL));
    }

    /**
     * Set reporting for the <i>ZigBee Security Level</i> attribute [attribute ID <b>0x0034</b>].
     * <p>
     * Door locks may sometimes wish to implement a higher level of security within the
     * application protocol in additional to the default network security. For instance a
     * door lock may wish to use additional APS security for cluster transactions. This
     * protects the door lock against being controlled by any other devices which have access
     * to the network key.
     * <p>
     * The Security Level attribute allows the door lock manufacturer to indicate what level
     * of security the doorlock requires.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setZigbeeSecurityLevelReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_ZIGBEESECURITYLEVEL), minInterval, maxInterval);
    }

    /**
     * Set the <i>Alarm Mask</i> attribute [attribute ID <b>0x0040</b>].
     * <p>
     * The alarm mask is used to turn on/off alarms for particular functions. Alarms for an
     * alarm group are enabled if the associated alarm mask bit is set. Each bit represents a
     * group of alarms. Entire alarm groups can be turned on or off by setting or clearing the
     * associated bit in the alarm mask.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param alarmMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setAlarmMask(final Integer value) {
        return write(serverAttributes.get(ATTR_ALARMMASK), value);
    }

    /**
     * Get the <i>Alarm Mask</i> attribute [attribute ID <b>0x0040</b>].
     * <p>
     * The alarm mask is used to turn on/off alarms for particular functions. Alarms for an
     * alarm group are enabled if the associated alarm mask bit is set. Each bit represents a
     * group of alarms. Entire alarm groups can be turned on or off by setting or clearing the
     * associated bit in the alarm mask.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAlarmMaskAsync() {
        return read(serverAttributes.get(ATTR_ALARMMASK));
    }

    /**
     * Synchronously get the <i>Alarm Mask</i> attribute [attribute ID <b>0x0040</b>].
     * <p>
     * The alarm mask is used to turn on/off alarms for particular functions. Alarms for an
     * alarm group are enabled if the associated alarm mask bit is set. Each bit represents a
     * group of alarms. Entire alarm groups can be turned on or off by setting or clearing the
     * associated bit in the alarm mask.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getAlarmMask(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ALARMMASK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ALARMMASK).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ALARMMASK));
    }

    /**
     * Set the <i>Keypad Operation Event Mask</i> attribute [attribute ID <b>0x0041</b>].
     * <p>
     * Event mask used to turn on and off the transmission of keypad operation events. This mask
     * DOES NOT apply to the storing of events in the report table.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param keypadOperationEventMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setKeypadOperationEventMask(final Integer value) {
        return write(serverAttributes.get(ATTR_KEYPADOPERATIONEVENTMASK), value);
    }

    /**
     * Get the <i>Keypad Operation Event Mask</i> attribute [attribute ID <b>0x0041</b>].
     * <p>
     * Event mask used to turn on and off the transmission of keypad operation events. This mask
     * DOES NOT apply to the storing of events in the report table.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getKeypadOperationEventMaskAsync() {
        return read(serverAttributes.get(ATTR_KEYPADOPERATIONEVENTMASK));
    }

    /**
     * Synchronously get the <i>Keypad Operation Event Mask</i> attribute [attribute ID <b>0x0041</b>].
     * <p>
     * Event mask used to turn on and off the transmission of keypad operation events. This mask
     * DOES NOT apply to the storing of events in the report table.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getKeypadOperationEventMask(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_KEYPADOPERATIONEVENTMASK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_KEYPADOPERATIONEVENTMASK).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_KEYPADOPERATIONEVENTMASK));
    }

    /**
     * Set the <i>RF Operation Event Mask</i> attribute [attribute ID <b>0x0042</b>].
     * <p>
     * Event mask used to turn on and off the transmission of RF operation events. This mask DOES
     * NOT apply to the storing of events in the report table.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param rfOperationEventMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setRfOperationEventMask(final Integer value) {
        return write(serverAttributes.get(ATTR_RFOPERATIONEVENTMASK), value);
    }

    /**
     * Get the <i>RF Operation Event Mask</i> attribute [attribute ID <b>0x0042</b>].
     * <p>
     * Event mask used to turn on and off the transmission of RF operation events. This mask DOES
     * NOT apply to the storing of events in the report table.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRfOperationEventMaskAsync() {
        return read(serverAttributes.get(ATTR_RFOPERATIONEVENTMASK));
    }

    /**
     * Synchronously get the <i>RF Operation Event Mask</i> attribute [attribute ID <b>0x0042</b>].
     * <p>
     * Event mask used to turn on and off the transmission of RF operation events. This mask DOES
     * NOT apply to the storing of events in the report table.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getRfOperationEventMask(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RFOPERATIONEVENTMASK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RFOPERATIONEVENTMASK).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RFOPERATIONEVENTMASK));
    }

    /**
     * Set the <i>Manual Operation Event Mask</i> attribute [attribute ID <b>0x0043</b>].
     * <p>
     * Event mask used to turn on and off manual operation events. This mask DOES NOT apply to the
     * storing of events in the report table.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param manualOperationEventMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setManualOperationEventMask(final Integer value) {
        return write(serverAttributes.get(ATTR_MANUALOPERATIONEVENTMASK), value);
    }

    /**
     * Get the <i>Manual Operation Event Mask</i> attribute [attribute ID <b>0x0043</b>].
     * <p>
     * Event mask used to turn on and off manual operation events. This mask DOES NOT apply to the
     * storing of events in the report table.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getManualOperationEventMaskAsync() {
        return read(serverAttributes.get(ATTR_MANUALOPERATIONEVENTMASK));
    }

    /**
     * Synchronously get the <i>Manual Operation Event Mask</i> attribute [attribute ID <b>0x0043</b>].
     * <p>
     * Event mask used to turn on and off manual operation events. This mask DOES NOT apply to the
     * storing of events in the report table.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getManualOperationEventMask(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MANUALOPERATIONEVENTMASK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MANUALOPERATIONEVENTMASK).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MANUALOPERATIONEVENTMASK));
    }

    /**
     * Set the <i>RFID Operation Event Mask</i> attribute [attribute ID <b>0x0044</b>].
     * <p>
     * Event mask used to turn on and off RFID operation events. This mask DOES NOT apply to the
     * storing of events in the report table.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param rfidOperationEventMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setRfidOperationEventMask(final Integer value) {
        return write(serverAttributes.get(ATTR_RFIDOPERATIONEVENTMASK), value);
    }

    /**
     * Get the <i>RFID Operation Event Mask</i> attribute [attribute ID <b>0x0044</b>].
     * <p>
     * Event mask used to turn on and off RFID operation events. This mask DOES NOT apply to the
     * storing of events in the report table.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRfidOperationEventMaskAsync() {
        return read(serverAttributes.get(ATTR_RFIDOPERATIONEVENTMASK));
    }

    /**
     * Synchronously get the <i>RFID Operation Event Mask</i> attribute [attribute ID <b>0x0044</b>].
     * <p>
     * Event mask used to turn on and off RFID operation events. This mask DOES NOT apply to the
     * storing of events in the report table.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getRfidOperationEventMask(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RFIDOPERATIONEVENTMASK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RFIDOPERATIONEVENTMASK).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RFIDOPERATIONEVENTMASK));
    }

    /**
     * Set the <i>Keypad Programming Event Mask</i> attribute [attribute ID <b>0x0045</b>].
     * <p>
     * Event mask used to turn on and off keypad programming events. This mask DOES NOT apply to
     * the storing of events in the report table.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param keypadProgrammingEventMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setKeypadProgrammingEventMask(final Integer value) {
        return write(serverAttributes.get(ATTR_KEYPADPROGRAMMINGEVENTMASK), value);
    }

    /**
     * Get the <i>Keypad Programming Event Mask</i> attribute [attribute ID <b>0x0045</b>].
     * <p>
     * Event mask used to turn on and off keypad programming events. This mask DOES NOT apply to
     * the storing of events in the report table.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getKeypadProgrammingEventMaskAsync() {
        return read(serverAttributes.get(ATTR_KEYPADPROGRAMMINGEVENTMASK));
    }

    /**
     * Synchronously get the <i>Keypad Programming Event Mask</i> attribute [attribute ID <b>0x0045</b>].
     * <p>
     * Event mask used to turn on and off keypad programming events. This mask DOES NOT apply to
     * the storing of events in the report table.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getKeypadProgrammingEventMask(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_KEYPADPROGRAMMINGEVENTMASK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_KEYPADPROGRAMMINGEVENTMASK).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_KEYPADPROGRAMMINGEVENTMASK));
    }

    /**
     * Set the <i>RF Programming Event Mask</i> attribute [attribute ID <b>0x0046</b>].
     * <p>
     * Event mask used to turn on and off RF programming events. This mask DOES NOT apply to the
     * storing of events in the report table.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param rfProgrammingEventMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setRfProgrammingEventMask(final Integer value) {
        return write(serverAttributes.get(ATTR_RFPROGRAMMINGEVENTMASK), value);
    }

    /**
     * Get the <i>RF Programming Event Mask</i> attribute [attribute ID <b>0x0046</b>].
     * <p>
     * Event mask used to turn on and off RF programming events. This mask DOES NOT apply to the
     * storing of events in the report table.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRfProgrammingEventMaskAsync() {
        return read(serverAttributes.get(ATTR_RFPROGRAMMINGEVENTMASK));
    }

    /**
     * Synchronously get the <i>RF Programming Event Mask</i> attribute [attribute ID <b>0x0046</b>].
     * <p>
     * Event mask used to turn on and off RF programming events. This mask DOES NOT apply to the
     * storing of events in the report table.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getRfProgrammingEventMask(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RFPROGRAMMINGEVENTMASK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RFPROGRAMMINGEVENTMASK).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RFPROGRAMMINGEVENTMASK));
    }

    /**
     * Set the <i>RFID Programming Event Mask</i> attribute [attribute ID <b>0x0047</b>].
     * <p>
     * Event mask used to turn on and off RFID programming events. This mask DOES NOT apply to the
     * storing of events in the report table.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param rfidProgrammingEventMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setRfidProgrammingEventMask(final Integer value) {
        return write(serverAttributes.get(ATTR_RFIDPROGRAMMINGEVENTMASK), value);
    }

    /**
     * Get the <i>RFID Programming Event Mask</i> attribute [attribute ID <b>0x0047</b>].
     * <p>
     * Event mask used to turn on and off RFID programming events. This mask DOES NOT apply to the
     * storing of events in the report table.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRfidProgrammingEventMaskAsync() {
        return read(serverAttributes.get(ATTR_RFIDPROGRAMMINGEVENTMASK));
    }

    /**
     * Synchronously get the <i>RFID Programming Event Mask</i> attribute [attribute ID <b>0x0047</b>].
     * <p>
     * Event mask used to turn on and off RFID programming events. This mask DOES NOT apply to the
     * storing of events in the report table.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getRfidProgrammingEventMask(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RFIDPROGRAMMINGEVENTMASK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RFIDPROGRAMMINGEVENTMASK).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RFIDPROGRAMMINGEVENTMASK));
    }

    /**
     * The Lock Door Command
     * <p>
     * This command causes the lock device to lock the door. As of HA 1.2, this command includes
     * an optional code for the lock. The door lock may require a PIN depending on the value of the
     * [Require PIN for RF Operation attribute]
     *
     * @param pinCode {@link ByteArray} PIN Code
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.lockDoorCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new lockDoorCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> lockDoorCommand(ByteArray pinCode) {
        LockDoorCommand command = new LockDoorCommand();

        // Set the fields
        command.setPinCode(pinCode);

        return sendCommand(command);
    }

    /**
     * The Unlock Door Command
     * <p>
     * This command causes the lock device to unlock the door. As of HA 1.2, this command
     * includes an optional code for the lock. The door lock may require a code depending on the
     * value of the [Require PIN for RF Operation attribute].
     * <p>
     *
     * <p>
     * <b>Note:</b> If the attribute AutoRelockTime is supported the lock will close when the auto relock
     * time has expired
     *
     * @param pinCode {@link ByteArray} PIN Code
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.unlockDoorCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new unlockDoorCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> unlockDoorCommand(ByteArray pinCode) {
        UnlockDoorCommand command = new UnlockDoorCommand();

        // Set the fields
        command.setPinCode(pinCode);

        return sendCommand(command);
    }

    /**
     * The Toggle
     * <p>
     * Request the status of the lock. As of HA 1.2, this command includes an optional code for
     * the lock. The door lock may require a code depending on the value of the [Require PIN for RF
     * Operation attribute]
     *
     * @param pin {@link String} PIN
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.toggle(parameters ...)</code>
     * with <code>cluster.sendCommand(new toggle(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> toggle(String pin) {
        Toggle command = new Toggle();

        // Set the fields
        command.setPin(pin);

        return sendCommand(command);
    }

    /**
     * The Unlock With Timeout
     * <p>
     * This command causes the lock device to unlock the door with a timeout parameter. After
     * the time in seconds specified in the timeout field, the lock device will relock itself
     * automatically. This timeout parameter is only temporary for this message transition
     * only and overrides the default relock time as specified in the [Auto Relock Time
     * attribute] attribute. If the door lock device is not capable of or does not want to
     * support temporary Relock Timeout, it should not support this optional command.
     *
     * @param timeoutInSeconds {@link Integer} Timeout In Seconds
     * @param pin {@link String} PIN
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.unlockWithTimeout(parameters ...)</code>
     * with <code>cluster.sendCommand(new unlockWithTimeout(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> unlockWithTimeout(Integer timeoutInSeconds, String pin) {
        UnlockWithTimeout command = new UnlockWithTimeout();

        // Set the fields
        command.setTimeoutInSeconds(timeoutInSeconds);
        command.setPin(pin);

        return sendCommand(command);
    }

    /**
     * The Get Log Record
     * <p>
     * Request a log record. Log number is between 1 – [Number of Log Records Supported
     * attribute]. If log number 0 is requested then the most recent log entry is returned.
     *
     * @param logIndex {@link Integer} Log Index
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getLogRecord(parameters ...)</code>
     * with <code>cluster.sendCommand(new getLogRecord(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getLogRecord(Integer logIndex) {
        GetLogRecord command = new GetLogRecord();

        // Set the fields
        command.setLogIndex(logIndex);

        return sendCommand(command);
    }

    /**
     * The Set PIN Code
     * <p>
     * Set a PIN into the lock.
     *
     * @param userId {@link Integer} User ID
     * @param userStatus {@link Integer} User Status
     * @param userType {@link Integer} User Type
     * @param pin {@link ByteArray} PIN
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.setPinCode(parameters ...)</code>
     * with <code>cluster.sendCommand(new setPinCode(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> setPinCode(Integer userId, Integer userStatus, Integer userType, ByteArray pin) {
        SetPinCode command = new SetPinCode();

        // Set the fields
        command.setUserId(userId);
        command.setUserStatus(userStatus);
        command.setUserType(userType);
        command.setPin(pin);

        return sendCommand(command);
    }

    /**
     * The Get PIN Code
     * <p>
     * Retrieve a PIN Code. User ID is between 0 - [# of PIN Users Supported attribute].
     *
     * @param userId {@link Integer} User ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getPinCode(parameters ...)</code>
     * with <code>cluster.sendCommand(new getPinCode(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getPinCode(Integer userId) {
        GetPinCode command = new GetPinCode();

        // Set the fields
        command.setUserId(userId);

        return sendCommand(command);
    }

    /**
     * The Clear PIN Code
     * <p>
     * Delete a PIN. User ID is between 0 - [# of PIN Users Supported attribute].
     *
     * @param userId {@link Integer} User ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.clearPinCode(parameters ...)</code>
     * with <code>cluster.sendCommand(new clearPinCode(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> clearPinCode(Integer userId) {
        ClearPinCode command = new ClearPinCode();

        // Set the fields
        command.setUserId(userId);

        return sendCommand(command);
    }

    /**
     * The Clear All PIN Codes
     * <p>
     * Set the status of a user ID. User Status value of 0x00 is not allowed. In order to clear a
     * user id, the Clear ID Command shall be used. For user status value please refer to User
     * Status Value.
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.clearAllPinCodes(parameters ...)</code>
     * with <code>cluster.sendCommand(new clearAllPinCodes(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> clearAllPinCodes() {
        return sendCommand(new ClearAllPinCodes());
    }

    /**
     * The Set User Status
     * <p>
     * Get the status of a user.
     *
     * @param userId {@link Integer} User ID
     * @param userStatus {@link Integer} User Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.setUserStatus(parameters ...)</code>
     * with <code>cluster.sendCommand(new setUserStatus(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> setUserStatus(Integer userId, Integer userStatus) {
        SetUserStatus command = new SetUserStatus();

        // Set the fields
        command.setUserId(userId);
        command.setUserStatus(userStatus);

        return sendCommand(command);
    }

    /**
     * The Get User Status
     * <p>
     * Get the status of a user.
     *
     * @param userId {@link Integer} User ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getUserStatus(parameters ...)</code>
     * with <code>cluster.sendCommand(new getUserStatus(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getUserStatus(Integer userId) {
        GetUserStatus command = new GetUserStatus();

        // Set the fields
        command.setUserId(userId);

        return sendCommand(command);
    }

    /**
     * The Set Week Day Schedule
     * <p>
     * Set a weekly repeating schedule for a specified user.
     *
     * @param scheduleId {@link Integer} Schedule ID
     * @param userId {@link Integer} User ID
     * @param daysMask {@link Integer} Days Mask
     * @param startHour {@link Integer} Start Hour
     * @param startMinute {@link Integer} Start Minute
     * @param endHour {@link Integer} End Hour
     * @param endMinute {@link Integer} End Minute
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.setWeekDaySchedule(parameters ...)</code>
     * with <code>cluster.sendCommand(new setWeekDaySchedule(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> setWeekDaySchedule(Integer scheduleId, Integer userId, Integer daysMask, Integer startHour, Integer startMinute, Integer endHour, Integer endMinute) {
        SetWeekDaySchedule command = new SetWeekDaySchedule();

        // Set the fields
        command.setScheduleId(scheduleId);
        command.setUserId(userId);
        command.setDaysMask(daysMask);
        command.setStartHour(startHour);
        command.setStartMinute(startMinute);
        command.setEndHour(endHour);
        command.setEndMinute(endMinute);

        return sendCommand(command);
    }

    /**
     * The Get Week Day Schedule
     * <p>
     * Retrieve the specific weekly schedule for the specific user.
     *
     * @param scheduleId {@link Integer} Schedule ID
     * @param userId {@link Integer} User ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getWeekDaySchedule(parameters ...)</code>
     * with <code>cluster.sendCommand(new getWeekDaySchedule(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getWeekDaySchedule(Integer scheduleId, Integer userId) {
        GetWeekDaySchedule command = new GetWeekDaySchedule();

        // Set the fields
        command.setScheduleId(scheduleId);
        command.setUserId(userId);

        return sendCommand(command);
    }

    /**
     * The Clear Week Day Schedule
     * <p>
     * Clear the specific weekly schedule for the specific user.
     *
     * @param scheduleId {@link Integer} Schedule ID
     * @param userId {@link Integer} User ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.clearWeekDaySchedule(parameters ...)</code>
     * with <code>cluster.sendCommand(new clearWeekDaySchedule(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> clearWeekDaySchedule(Integer scheduleId, Integer userId) {
        ClearWeekDaySchedule command = new ClearWeekDaySchedule();

        // Set the fields
        command.setScheduleId(scheduleId);
        command.setUserId(userId);

        return sendCommand(command);
    }

    /**
     * The Set Year Day Schedule
     * <p>
     * Set a time-specific schedule ID for a specified user.
     *
     * @param scheduleId {@link Integer} Schedule ID
     * @param userId {@link Integer} User ID
     * @param localStartTime {@link Integer} Local Start Time
     * @param localEndTime {@link Integer} Local End Time
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.setYearDaySchedule(parameters ...)</code>
     * with <code>cluster.sendCommand(new setYearDaySchedule(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> setYearDaySchedule(Integer scheduleId, Integer userId, Integer localStartTime, Integer localEndTime) {
        SetYearDaySchedule command = new SetYearDaySchedule();

        // Set the fields
        command.setScheduleId(scheduleId);
        command.setUserId(userId);
        command.setLocalStartTime(localStartTime);
        command.setLocalEndTime(localEndTime);

        return sendCommand(command);
    }

    /**
     * The Get Year Day Schedule
     * <p>
     * Retrieve the specific year day schedule for the specific user.
     *
     * @param scheduleId {@link Integer} Schedule ID
     * @param userId {@link Integer} User ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getYearDaySchedule(parameters ...)</code>
     * with <code>cluster.sendCommand(new getYearDaySchedule(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getYearDaySchedule(Integer scheduleId, Integer userId) {
        GetYearDaySchedule command = new GetYearDaySchedule();

        // Set the fields
        command.setScheduleId(scheduleId);
        command.setUserId(userId);

        return sendCommand(command);
    }

    /**
     * The Clear Year Day Schedule
     * <p>
     * Clears the specific year day schedule for the specific user.
     *
     * @param scheduleId {@link Integer} Schedule ID
     * @param userId {@link Integer} User ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.clearYearDaySchedule(parameters ...)</code>
     * with <code>cluster.sendCommand(new clearYearDaySchedule(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> clearYearDaySchedule(Integer scheduleId, Integer userId) {
        ClearYearDaySchedule command = new ClearYearDaySchedule();

        // Set the fields
        command.setScheduleId(scheduleId);
        command.setUserId(userId);

        return sendCommand(command);
    }

    /**
     * The Set Holiday Schedule
     * <p>
     * Set a time-specific schedule ID for a specified user.
     *
     * @param holidayScheduleId {@link Integer} Holiday Schedule ID
     * @param localStartTime {@link Integer} Local Start Time
     * @param localEndTime {@link Integer} Local End Time
     * @param operatingMode {@link Integer} Operating Mode
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.setHolidaySchedule(parameters ...)</code>
     * with <code>cluster.sendCommand(new setHolidaySchedule(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> setHolidaySchedule(Integer holidayScheduleId, Integer localStartTime, Integer localEndTime, Integer operatingMode) {
        SetHolidaySchedule command = new SetHolidaySchedule();

        // Set the fields
        command.setHolidayScheduleId(holidayScheduleId);
        command.setLocalStartTime(localStartTime);
        command.setLocalEndTime(localEndTime);
        command.setOperatingMode(operatingMode);

        return sendCommand(command);
    }

    /**
     * The Get Holiday Schedule
     * <p>
     * Get the holiday Schedule by specifying Holiday ID.
     *
     * @param holidayScheduleId {@link Integer} Holiday Schedule ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getHolidaySchedule(parameters ...)</code>
     * with <code>cluster.sendCommand(new getHolidaySchedule(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getHolidaySchedule(Integer holidayScheduleId) {
        GetHolidaySchedule command = new GetHolidaySchedule();

        // Set the fields
        command.setHolidayScheduleId(holidayScheduleId);

        return sendCommand(command);
    }

    /**
     * The Clear Holiday Schedule
     * <p>
     * Clear the holiday Schedule by specifying Holiday ID.
     *
     * @param holidayScheduleId {@link Integer} Holiday Schedule ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.clearHolidaySchedule(parameters ...)</code>
     * with <code>cluster.sendCommand(new clearHolidaySchedule(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> clearHolidaySchedule(Integer holidayScheduleId) {
        ClearHolidaySchedule command = new ClearHolidaySchedule();

        // Set the fields
        command.setHolidayScheduleId(holidayScheduleId);

        return sendCommand(command);
    }

    /**
     * The Set User Type
     * <p>
     * Set the type byte for a specified user.
     *
     * @param userId {@link Integer} User ID
     * @param userType {@link Integer} User Type
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.setUserType(parameters ...)</code>
     * with <code>cluster.sendCommand(new setUserType(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> setUserType(Integer userId, Integer userType) {
        SetUserType command = new SetUserType();

        // Set the fields
        command.setUserId(userId);
        command.setUserType(userType);

        return sendCommand(command);
    }

    /**
     * The Get User Type
     * <p>
     * Retrieve the type byte for a specific user.
     *
     * @param userId {@link Integer} User ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getUserType(parameters ...)</code>
     * with <code>cluster.sendCommand(new getUserType(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getUserType(Integer userId) {
        GetUserType command = new GetUserType();

        // Set the fields
        command.setUserId(userId);

        return sendCommand(command);
    }

    /**
     * The Set RFID Code
     * <p>
     * Set an ID for RFID access into the lock.
     *
     * @param userId {@link Integer} User ID
     * @param userStatus {@link Integer} User Status
     * @param userType {@link Integer} User Type
     * @param rfidCode {@link ByteArray} RFID Code
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.setRfidCode(parameters ...)</code>
     * with <code>cluster.sendCommand(new setRfidCode(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> setRfidCode(Integer userId, Integer userStatus, Integer userType, ByteArray rfidCode) {
        SetRfidCode command = new SetRfidCode();

        // Set the fields
        command.setUserId(userId);
        command.setUserStatus(userStatus);
        command.setUserType(userType);
        command.setRfidCode(rfidCode);

        return sendCommand(command);
    }

    /**
     * The Get RFID Code
     * <p>
     * Retrieve an ID. User ID is between 0 - [# of RFID Users Supported attribute].
     *
     * @param userId {@link Integer} User ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getRfidCode(parameters ...)</code>
     * with <code>cluster.sendCommand(new getRfidCode(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getRfidCode(Integer userId) {
        GetRfidCode command = new GetRfidCode();

        // Set the fields
        command.setUserId(userId);

        return sendCommand(command);
    }

    /**
     * The Clear RFID Code
     * <p>
     * Delete an ID. User ID is between 0 - [# of RFID Users Supported attribute]. If you delete a
     * RFID code and this user didn't have a PIN code, the user status has to be set to "0
     * Available", the user type has to be set to the default value, and all schedules which are
     * supported have to be set to the default values.
     *
     * @param userId {@link Integer} User ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.clearRfidCode(parameters ...)</code>
     * with <code>cluster.sendCommand(new clearRfidCode(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> clearRfidCode(Integer userId) {
        ClearRfidCode command = new ClearRfidCode();

        // Set the fields
        command.setUserId(userId);

        return sendCommand(command);
    }

    /**
     * The Clear All RFID Codes
     * <p>
     * Clear out all RFIDs on the lock. If you delete all RFID codes and this user didn't have a PIN
     * code, the user status has to be set to "0 Available", the user type has to be set to the
     * default value, and all schedules which are supported have to be set to the default
     * values.
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.clearAllRfidCodes(parameters ...)</code>
     * with <code>cluster.sendCommand(new clearAllRfidCodes(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> clearAllRfidCodes() {
        return sendCommand(new ClearAllRfidCodes());
    }

    /**
     * The Lock Door Response
     * <p>
     * This command is sent in response to a Lock command with one status byte payload. The
     * Status field shall be set to SUCCESS or FAILURE.
     * <p>
     * The status byte only indicates if the message has received successfully. To determine
     * the lock and/or door status, the client should query to [Lock State attribute] and [Door
     * State attribute]
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.lockDoorResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new lockDoorResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> lockDoorResponse(Integer status) {
        LockDoorResponse command = new LockDoorResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Unlock Door Response
     * <p>
     * This command is sent in response to a Toggle command with one status byte payload. The
     * Status field shall be set to SUCCESS or FAILURE.
     * <p>
     * The status byte only indicates if the message has received successfully. To determine
     * the lock and/or door status, the client should query to [Lock State attribute] and [Door
     * State attribute].
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.unlockDoorResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new unlockDoorResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> unlockDoorResponse(Integer status) {
        UnlockDoorResponse command = new UnlockDoorResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Toggle Response
     * <p>
     * This command is sent in response to a Toggle command with one status byte payload. The
     * Status field shall be set to SUCCESS or FAILURE.
     * <p>
     * The status byte only indicates if the message has received successfully. To determine
     * the lock and/or door status, the client should query to [Lock State attribute] and [Door
     * State attribute].
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.toggleResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new toggleResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> toggleResponse(Integer status) {
        ToggleResponse command = new ToggleResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Unlock With Timeout Response
     * <p>
     * This command is sent in response to an Unlock with Timeout command with one status byte
     * payload. The Status field shall be set to SUCCESS or FAILURE.
     * <p>
     * The status byte only indicates if the message has received successfully. To determine
     * status, the client should query to [Lock State attribute] and [Door State attribute].
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.unlockWithTimeoutResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new unlockWithTimeoutResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> unlockWithTimeoutResponse(Integer status) {
        UnlockWithTimeoutResponse command = new UnlockWithTimeoutResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Get Log Record Response
     * <p>
     * Returns the specified log record. If an invalid log entry ID was requested, it is set to 0
     * and the most recent log entry will be returned.
     *
     * @param logEntryId {@link Integer} Log Entry ID
     * @param timestamp {@link Integer} Timestamp
     * @param eventType {@link Integer} Event Type
     * @param source {@link Integer} Source
     * @param eventId {@link Integer} Event ID
     * @param userId {@link Integer} User ID
     * @param pin {@link ByteArray} PIN
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getLogRecordResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new getLogRecordResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getLogRecordResponse(Integer logEntryId, Integer timestamp, Integer eventType, Integer source, Integer eventId, Integer userId, ByteArray pin) {
        GetLogRecordResponse command = new GetLogRecordResponse();

        // Set the fields
        command.setLogEntryId(logEntryId);
        command.setTimestamp(timestamp);
        command.setEventType(eventType);
        command.setSource(source);
        command.setEventId(eventId);
        command.setUserId(userId);
        command.setPin(pin);

        return sendCommand(command);
    }

    /**
     * The Set PIN Code Response
     * <p>
     * Returns status of the PIN set command.
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.setPinCodeResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new setPinCodeResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> setPinCodeResponse(Integer status) {
        SetPinCodeResponse command = new SetPinCodeResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Get PIN Code Response
     * <p>
     * Returns the PIN for the specified user ID.
     *
     * @param userId {@link Integer} User ID
     * @param userStatus {@link Integer} User Status
     * @param userType {@link Integer} User Type
     * @param code {@link ByteArray} Code
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getPinCodeResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new getPinCodeResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getPinCodeResponse(Integer userId, Integer userStatus, Integer userType, ByteArray code) {
        GetPinCodeResponse command = new GetPinCodeResponse();

        // Set the fields
        command.setUserId(userId);
        command.setUserStatus(userStatus);
        command.setUserType(userType);
        command.setCode(code);

        return sendCommand(command);
    }

    /**
     * The Clear PIN Code Response
     * <p>
     * Returns pass/fail of the command.
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.clearPinCodeResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new clearPinCodeResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> clearPinCodeResponse(Integer status) {
        ClearPinCodeResponse command = new ClearPinCodeResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Clear All PIN Codes Response
     * <p>
     * Returns pass/fail of the command.
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.clearAllPinCodesResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new clearAllPinCodesResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> clearAllPinCodesResponse(Integer status) {
        ClearAllPinCodesResponse command = new ClearAllPinCodesResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Set User Status Response
     * <p>
     * Returns pass/fail of the command.
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.setUserStatusResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new setUserStatusResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> setUserStatusResponse(Integer status) {
        SetUserStatusResponse command = new SetUserStatusResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Get User Status Response
     * <p>
     * Returns the user status for the specified user ID.
     *
     * @param userId {@link Integer} User ID
     * @param userStatus {@link Integer} User Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getUserStatusResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new getUserStatusResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getUserStatusResponse(Integer userId, Integer userStatus) {
        GetUserStatusResponse command = new GetUserStatusResponse();

        // Set the fields
        command.setUserId(userId);
        command.setUserStatus(userStatus);

        return sendCommand(command);
    }

    /**
     * The Set Week Day Schedule Response
     * <p>
     * Returns pass/fail of the command.
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.setWeekDayScheduleResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new setWeekDayScheduleResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> setWeekDayScheduleResponse(Integer status) {
        SetWeekDayScheduleResponse command = new SetWeekDayScheduleResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Get Week Day Schedule Response
     * <p>
     * Returns the weekly repeating schedule data for the specified schedule ID.
     *
     * @param scheduleId {@link Integer} Schedule ID
     * @param userId {@link Integer} User ID
     * @param status {@link Integer} Status
     * @param daysMask {@link Integer} Days Mask
     * @param startHour {@link Integer} Start Hour
     * @param startMinute {@link Integer} Start Minute
     * @param endHour {@link Integer} End Hour
     * @param endMinute {@link Integer} End Minute
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getWeekDayScheduleResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new getWeekDayScheduleResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getWeekDayScheduleResponse(Integer scheduleId, Integer userId, Integer status, Integer daysMask, Integer startHour, Integer startMinute, Integer endHour, Integer endMinute) {
        GetWeekDayScheduleResponse command = new GetWeekDayScheduleResponse();

        // Set the fields
        command.setScheduleId(scheduleId);
        command.setUserId(userId);
        command.setStatus(status);
        command.setDaysMask(daysMask);
        command.setStartHour(startHour);
        command.setStartMinute(startMinute);
        command.setEndHour(endHour);
        command.setEndMinute(endMinute);

        return sendCommand(command);
    }

    /**
     * The Clear Week Day Schedule Response
     * <p>
     * Returns pass/fail of the command.
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.clearWeekDayScheduleResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new clearWeekDayScheduleResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> clearWeekDayScheduleResponse(Integer status) {
        ClearWeekDayScheduleResponse command = new ClearWeekDayScheduleResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Set Year Day Schedule Response
     * <p>
     * Returns pass/fail of the command.
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.setYearDayScheduleResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new setYearDayScheduleResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> setYearDayScheduleResponse(Integer status) {
        SetYearDayScheduleResponse command = new SetYearDayScheduleResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Set Year Day Schedule Response
     * <p>
     * Returns the weekly repeating schedule data for the specified schedule ID.
     *
     * @param scheduleId {@link Integer} Schedule ID
     * @param userId {@link Integer} User ID
     * @param status {@link Integer} Status
     * @param localStartTime {@link Integer} Local Start Time
     * @param localEndTime {@link Integer} Local End Time
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.setYearDayScheduleResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new setYearDayScheduleResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> setYearDayScheduleResponse(Integer scheduleId, Integer userId, Integer status, Integer localStartTime, Integer localEndTime) {
        SetYearDayScheduleResponse command = new SetYearDayScheduleResponse();

        // Set the fields
        command.setScheduleId(scheduleId);
        command.setUserId(userId);
        command.setStatus(status);
        command.setLocalStartTime(localStartTime);
        command.setLocalEndTime(localEndTime);

        return sendCommand(command);
    }

    /**
     * The Clear Year Day Schedule Response
     * <p>
     * Returns pass/fail of the command.
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.clearYearDayScheduleResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new clearYearDayScheduleResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> clearYearDayScheduleResponse(Integer status) {
        ClearYearDayScheduleResponse command = new ClearYearDayScheduleResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Set Holiday Schedule Response
     * <p>
     * Returns pass/fail of the command.
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.setHolidayScheduleResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new setHolidayScheduleResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> setHolidayScheduleResponse(Integer status) {
        SetHolidayScheduleResponse command = new SetHolidayScheduleResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Get Holiday Schedule Response
     * <p>
     * Returns the Holiday Schedule Entry for the specified Holiday ID.
     *
     * @param holidayScheduleId {@link Integer} Holiday Schedule ID
     * @param status {@link Integer} Status
     * @param localStartTime {@link Integer} Local Start Time
     * @param localEndTime {@link Integer} Local End Time
     * @param operatingMode {@link Integer} Operating Mode
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getHolidayScheduleResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new getHolidayScheduleResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getHolidayScheduleResponse(Integer holidayScheduleId, Integer status, Integer localStartTime, Integer localEndTime, Integer operatingMode) {
        GetHolidayScheduleResponse command = new GetHolidayScheduleResponse();

        // Set the fields
        command.setHolidayScheduleId(holidayScheduleId);
        command.setStatus(status);
        command.setLocalStartTime(localStartTime);
        command.setLocalEndTime(localEndTime);
        command.setOperatingMode(operatingMode);

        return sendCommand(command);
    }

    /**
     * The Clear Holiday Schedule Response
     * <p>
     * Returns pass/fail of the command.
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.clearHolidayScheduleResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new clearHolidayScheduleResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> clearHolidayScheduleResponse(Integer status) {
        ClearHolidayScheduleResponse command = new ClearHolidayScheduleResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Set User Type Response
     * <p>
     * Returns pass/fail of the command.
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.setUserTypeResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new setUserTypeResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> setUserTypeResponse(Integer status) {
        SetUserTypeResponse command = new SetUserTypeResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Get User Type Response
     * <p>
     * Returns pass/fail of the command.
     *
     * @param userId {@link Integer} User ID
     * @param userType {@link Integer} User Type
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getUserTypeResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new getUserTypeResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getUserTypeResponse(Integer userId, Integer userType) {
        GetUserTypeResponse command = new GetUserTypeResponse();

        // Set the fields
        command.setUserId(userId);
        command.setUserType(userType);

        return sendCommand(command);
    }

    /**
     * The Set RFID Code Response
     * <p>
     * Returns pass/fail of the command.
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.setRfidCodeResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new setRfidCodeResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> setRfidCodeResponse(Integer status) {
        SetRfidCodeResponse command = new SetRfidCodeResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Get RFID Code Response
     * <p>
     * Returns pass/fail of the command.
     *
     * @param userId {@link Integer} User ID
     * @param userStatus {@link Integer} User Status
     * @param userType {@link Integer} User Type
     * @param rfidCode {@link ByteArray} RFID Code
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getRfidCodeResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new getRfidCodeResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getRfidCodeResponse(Integer userId, Integer userStatus, Integer userType, ByteArray rfidCode) {
        GetRfidCodeResponse command = new GetRfidCodeResponse();

        // Set the fields
        command.setUserId(userId);
        command.setUserStatus(userStatus);
        command.setUserType(userType);
        command.setRfidCode(rfidCode);

        return sendCommand(command);
    }

    /**
     * The Clear RFID Code Response
     * <p>
     * Returns pass/fail of the command.
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.clearRfidCodeResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new clearRfidCodeResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> clearRfidCodeResponse(Integer status) {
        ClearRfidCodeResponse command = new ClearRfidCodeResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Clear All RFID Codes Response
     * <p>
     * Returns pass/fail of the command.
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.clearAllRfidCodesResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new clearAllRfidCodesResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> clearAllRfidCodesResponse(Integer status) {
        ClearAllRfidCodesResponse command = new ClearAllRfidCodesResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Operation Event Notification
     * <p>
     * The door lock server sends out operation event notification when the event is triggered
     * by the various event sources. The specific operation event will only be sent out if the
     * associated bitmask is enabled in the various attributes in the Event Masks Attribute
     * Set.
     *
     * @param operationEventSource {@link Integer} Operation Event Source
     * @param operationEventCode {@link Integer} Operation Event Code
     * @param userId {@link Integer} User ID
     * @param pin {@link ByteArray} PIN
     * @param localTime {@link Integer} Local Time
     * @param data {@link String} Data
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.operationEventNotification(parameters ...)</code>
     * with <code>cluster.sendCommand(new operationEventNotification(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> operationEventNotification(Integer operationEventSource, Integer operationEventCode, Integer userId, ByteArray pin, Integer localTime, String data) {
        OperationEventNotification command = new OperationEventNotification();

        // Set the fields
        command.setOperationEventSource(operationEventSource);
        command.setOperationEventCode(operationEventCode);
        command.setUserId(userId);
        command.setPin(pin);
        command.setLocalTime(localTime);
        command.setData(data);

        return sendCommand(command);
    }

    /**
     * The Programming Event Notification
     * <p>
     * The door lock server sends out a programming event notification whenever a programming
     * event takes place on the door lock.
     *
     * @param programEventSource {@link Integer} Program Event Source
     * @param programEventCode {@link Integer} Program Event Code
     * @param userId {@link Integer} User ID
     * @param pin {@link ByteArray} PIN
     * @param userType {@link Integer} User Type
     * @param userStatus {@link Integer} User Status
     * @param localTime {@link Integer} Local Time
     * @param data {@link String} Data
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.programmingEventNotification(parameters ...)</code>
     * with <code>cluster.sendCommand(new programmingEventNotification(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> programmingEventNotification(Integer programEventSource, Integer programEventCode, Integer userId, ByteArray pin, Integer userType, Integer userStatus, Integer localTime, String data) {
        ProgrammingEventNotification command = new ProgrammingEventNotification();

        // Set the fields
        command.setProgramEventSource(programEventSource);
        command.setProgramEventCode(programEventCode);
        command.setUserId(userId);
        command.setPin(pin);
        command.setUserType(userType);
        command.setUserStatus(userStatus);
        command.setLocalTime(localTime);
        command.setData(data);

        return sendCommand(command);
    }
}
