/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Power Configuration</b> cluster implementation (<i>Cluster ID 0x0001</i>).
 * <p>
 * Attributes for determining detailed information about a device’s power source(s), and for
 * configuring under/over voltage alarms.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-26T21:33:25Z")
public class ZclPowerConfigurationCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0001;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Power Configuration";

    // Attribute constants
    /**
     * The MainsVoltage attribute is 16-bits in length and specifies the actual (measured)
     * RMS voltage (or DC voltage in the case of a DC supply) currently applied to the device,
     * measured in units of 100mV.
     */
    public static final int ATTR_MAINSVOLTAGE = 0x0000;
    /**
     * The MainsFrequency attribute is 8-bits in length and represents the frequency, in
     * Hertz, of the mains as determined by the device as follows:-
     * <p>
     * MainsFrequency = 0.5 x measured frequency
     * <p>
     * Where 2 Hz <= measured frequency <= 506 Hz, corresponding to a MainsFrequency in the
     * range 1 to 0xfd.
     * <p>
     * The maximum resolution this format allows is 2 Hz. The following special values of
     * MainsFrequency apply. <li>0x00 indicates a frequency that is too low to be
     * measured.</li> <li>0xfe indicates a frequency that is too high to be measured.</li>
     * <li>0xff indicates that the frequency could not be measured.</li>
     */
    public static final int ATTR_MAINSFREQUENCY = 0x0001;
    /**
     * The MainsAlarmMask attribute is 8-bits in length and specifies which mains alarms may
     * be generated. A ‘1’ in each bit position enables the alarm.
     */
    public static final int ATTR_MAINSALARMMASK = 0x0010;
    /**
     * The MainsVoltageMinThreshold attribute is 16-bits in length and specifies the lower
     * alarm threshold, measured in units of 100mV, for the MainsVoltage attribute. The value
     * of this attribute shall be less than MainsVoltageMaxThreshold.
     * <p>
     * If the value of MainsVoltage drops below the threshold specified by
     * MainsVoltageMinThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute increases to
     * greater than or equal to MainsVoltageMinThreshold before the timer expires, the
     * device shall stop and reset the timer. If the timer expires, an alarm shall be generated.
     * <p>
     * The Alarm Code field included in the generated alarm shall be 0x00.
     * <p>
     * If this attribute takes the value 0xffff then this alarm shall not be generated.
     */
    public static final int ATTR_MAINSVOLTAGEMINTHRESHOLD = 0x0011;
    /**
     * The MainsVoltageMaxThreshold attribute is 16-bits in length and specifies the upper
     * alarm threshold, measured in units of 100mV, for the MainsVoltage attribute. The value
     * of this attribute shall be greater than MainsVoltageMinThreshold.
     * <p>
     * If the value of MainsVoltage rises above the threshold specified by
     * MainsVoltageMaxThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute drops to lower
     * than or equal to MainsVoltageMaxThreshold before the timer expires, the device shall
     * stop and reset the timer. If the timer expires, an alarm shall be generated.
     * <p>
     * The Alarm Code field included in the generated alarm shall be 0x01.
     * <p>
     * If this attribute takes the value 0xffff then this alarm shall not be generated.
     */
    public static final int ATTR_MAINSVOLTAGEMAXTHRESHOLD = 0x0012;
    /**
     * The MainsVoltageDwellTripPoint attribute is 16-bits in length and specifies the
     * length of time, in seconds that the value of MainsVoltage may exist beyond either of its
     * thresholds before an alarm is generated.
     * <p>
     * If this attribute takes the value 0xffff then the associated alarms shall not be
     * generated.
     */
    public static final int ATTR_MAINSVOLTAGEDWELLTRIPPOINT = 0x0013;
    /**
     * The BatteryVoltage attribute is 8-bits in length and specifies the current actual
     * (measured) battery voltage, in units of 100mV. The value 0xff indicates an invalid or
     * unknown reading.
     */
    public static final int ATTR_BATTERYVOLTAGE = 0x0020;
    public static final int ATTR_BATTERYPERCENTAGEREMAINING = 0x0021;
    /**
     * The BatteryManufacturer attribute is a maximum of 16 bytes in length and specifies the
     * name of the battery manufacturer as a ZigBee character string.
     */
    public static final int ATTR_BATTERYMANUFACTURER = 0x0030;
    /**
     * The BatterySize attribute is an enumeration which specifies the type of battery being
     * used by the device.
     */
    public static final int ATTR_BATTERYSIZE = 0x0031;
    /**
     * The BatteryAHrRating attribute is 16-bits in length and specifies the Ampere-hour
     * rating of the battery, measured in units of 10mAHr.
     */
    public static final int ATTR_BATTERYAHRRATING = 0x0032;
    /**
     * The BatteryQuantity attribute is 8-bits in length and specifies the number of battery
     * cells used to power the device.
     */
    public static final int ATTR_BATTERYQUANTITY = 0x0033;
    /**
     * The BatteryRatedVoltage attribute is 8-bits in length and specifies the rated voltage
     * of the battery being used in the device, measured in units of 100mV.
     */
    public static final int ATTR_BATTERYRATEDVOLTAGE = 0x0034;
    /**
     * The BatteryAlarmMask attribute is 8-bits in length and specifies which battery alarms
     * may be generated.
     */
    public static final int ATTR_BATTERYALARMMASK = 0x0035;
    /**
     * The BatteryVoltageMinThreshold attribute is 8-bits in length and specifies the low
     * voltage alarm threshold, measured in units of 100mV, for the BatteryVoltage
     * attribute.
     * <p>
     * If the value of BatteryVoltage drops below the threshold specified by
     * BatteryVoltageMinThreshold an alarm shall be generated.
     * <p>
     * The Alarm Code field included in the generated alarm shall be 0x10.
     * <p>
     * If this attribute takes the value 0xff then this alarm shall not be generated.
     */
    public static final int ATTR_BATTERYVOLTAGEMINTHRESHOLD = 0x0036;
    /**
     * Specify the low voltage alarm thresholds, measured in units of 100mV, for the
     * BatteryVoltage attribute.
     */
    public static final int ATTR_BATTERYVOLTAGETHRESHOLD1 = 0x0037;
    /**
     * Specify the low voltage alarm thresholds, measured in units of 100mV, for the
     * BatteryVoltage attribute.
     */
    public static final int ATTR_BATTERYVOLTAGETHRESHOLD2 = 0x0038;
    /**
     * Specify the low voltage alarm thresholds, measured in units of 100mV, for the
     * BatteryVoltage attribute.
     */
    public static final int ATTR_BATTERYVOLTAGETHRESHOLD3 = 0x0039;
    /**
     * Specifies the low battery percentage alarm threshold, measured in percentage (i.e.,
     * zero to 100%), for the BatteryPercentageRemaining attribute.
     */
    public static final int ATTR_BATTERYPERCENTAGEMINTHRESHOLD = 0x003A;
    /**
     * Specifies the low battery percentage alarm threshold, measured in percentage (i.e.,
     * zero to 100%), for the BatteryPercentageRemaining attribute.
     */
    public static final int ATTR_BATTERYPERCENTAGETHRESHOLD1 = 0x003B;
    /**
     * Specifies the low battery percentage alarm threshold, measured in percentage (i.e.,
     * zero to 100%), for the BatteryPercentageRemaining attribute.
     */
    public static final int ATTR_BATTERYPERCENTAGETHRESHOLD2 = 0x003C;
    /**
     * Specifies the low battery percentage alarm threshold, measured in percentage (i.e.,
     * zero to 100%), for the BatteryPercentageRemaining attribute.
     */
    public static final int ATTR_BATTERYPERCENTAGETHRESHOLD3 = 0x003D;
    /**
     * Specifies the current state of the device's battery alarms. This attribute provides a
     * persistent record of a device's battery alarm conditions as well as a mechanism for
     * reporting changes to those conditions, including the elimination of battery alarm
     * states (e.g., when a battery is replaced).
     */
    public static final int ATTR_BATTERYALARMSTATE = 0x003E;

    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<>(23);

        attributeMap.put(ATTR_MAINSVOLTAGE, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_MAINSVOLTAGE, "Mains Voltage", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MAINSFREQUENCY, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_MAINSFREQUENCY, "Mains Frequency", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MAINSALARMMASK, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_MAINSALARMMASK, "Mains Alarm Mask", ZclDataType.BITMAP_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_MAINSVOLTAGEMINTHRESHOLD, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_MAINSVOLTAGEMINTHRESHOLD, "Mains Voltage Min Threshold", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_MAINSVOLTAGEMAXTHRESHOLD, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_MAINSVOLTAGEMAXTHRESHOLD, "Mains Voltage Max Threshold", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_MAINSVOLTAGEDWELLTRIPPOINT, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_MAINSVOLTAGEDWELLTRIPPOINT, "Mains Voltage Dwell Trip Point", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYVOLTAGE, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYVOLTAGE, "Battery Voltage", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BATTERYPERCENTAGEREMAINING, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYPERCENTAGEREMAINING, "Battery Percentage Remaining", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_BATTERYMANUFACTURER, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYMANUFACTURER, "Battery Manufacturer", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_BATTERYSIZE, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYSIZE, "Battery Size", ZclDataType.ENUMERATION_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_BATTERYAHRRATING, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYAHRRATING, "Battery A Hr Rating", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYQUANTITY, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYQUANTITY, "Battery Quantity", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYRATEDVOLTAGE, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYRATEDVOLTAGE, "Battery Rated Voltage", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYALARMMASK, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYALARMMASK, "Battery Alarm Mask", ZclDataType.BITMAP_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_BATTERYVOLTAGEMINTHRESHOLD, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYVOLTAGEMINTHRESHOLD, "Battery Voltage Min Threshold", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYVOLTAGETHRESHOLD1, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYVOLTAGETHRESHOLD1, "Battery Voltage Threshold 1", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYVOLTAGETHRESHOLD2, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYVOLTAGETHRESHOLD2, "Battery Voltage Threshold 2", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYVOLTAGETHRESHOLD3, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYVOLTAGETHRESHOLD3, "Battery Voltage Threshold 3", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYPERCENTAGEMINTHRESHOLD, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYPERCENTAGEMINTHRESHOLD, "Battery Percentage Min Threshold", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYPERCENTAGETHRESHOLD1, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYPERCENTAGETHRESHOLD1, "Battery Percentage Threshold 1", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYPERCENTAGETHRESHOLD2, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYPERCENTAGETHRESHOLD2, "Battery Percentage Threshold 2", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYPERCENTAGETHRESHOLD3, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYPERCENTAGETHRESHOLD3, "Battery Percentage Threshold 3", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYALARMSTATE, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYALARMSTATE, "Battery Alarm State", ZclDataType.BITMAP_32_BIT, false, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Power Configuration cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclPowerConfigurationCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>Mains Voltage</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The MainsVoltage attribute is 16-bits in length and specifies the actual (measured)
     * RMS voltage (or DC voltage in the case of a DC supply) currently applied to the device,
     * measured in units of 100mV.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMainsVoltageAsync() {
        return read(attributes.get(ATTR_MAINSVOLTAGE));
    }

    /**
     * Synchronously get the <i>Mains Voltage</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The MainsVoltage attribute is 16-bits in length and specifies the actual (measured)
     * RMS voltage (or DC voltage in the case of a DC supply) currently applied to the device,
     * measured in units of 100mV.
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
     */
    public Integer getMainsVoltage(final long refreshPeriod) {
        if (attributes.get(ATTR_MAINSVOLTAGE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MAINSVOLTAGE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MAINSVOLTAGE));
    }

    /**
     * Get the <i>Mains Frequency</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The MainsFrequency attribute is 8-bits in length and represents the frequency, in
     * Hertz, of the mains as determined by the device as follows:-
     * <p>
     * MainsFrequency = 0.5 x measured frequency
     * <p>
     * Where 2 Hz <= measured frequency <= 506 Hz, corresponding to a MainsFrequency in the
     * range 1 to 0xfd.
     * <p>
     * The maximum resolution this format allows is 2 Hz. The following special values of
     * MainsFrequency apply. <li>0x00 indicates a frequency that is too low to be
     * measured.</li> <li>0xfe indicates a frequency that is too high to be measured.</li>
     * <li>0xff indicates that the frequency could not be measured.</li>
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMainsFrequencyAsync() {
        return read(attributes.get(ATTR_MAINSFREQUENCY));
    }

    /**
     * Synchronously get the <i>Mains Frequency</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The MainsFrequency attribute is 8-bits in length and represents the frequency, in
     * Hertz, of the mains as determined by the device as follows:-
     * <p>
     * MainsFrequency = 0.5 x measured frequency
     * <p>
     * Where 2 Hz <= measured frequency <= 506 Hz, corresponding to a MainsFrequency in the
     * range 1 to 0xfd.
     * <p>
     * The maximum resolution this format allows is 2 Hz. The following special values of
     * MainsFrequency apply. <li>0x00 indicates a frequency that is too low to be
     * measured.</li> <li>0xfe indicates a frequency that is too high to be measured.</li>
     * <li>0xff indicates that the frequency could not be measured.</li>
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
     */
    public Integer getMainsFrequency(final long refreshPeriod) {
        if (attributes.get(ATTR_MAINSFREQUENCY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MAINSFREQUENCY).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MAINSFREQUENCY));
    }

    /**
     * Set the <i>Mains Alarm Mask</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The MainsAlarmMask attribute is 8-bits in length and specifies which mains alarms may
     * be generated. A ‘1’ in each bit position enables the alarm.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param mainsAlarmMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMainsAlarmMask(final Integer value) {
        return write(attributes.get(ATTR_MAINSALARMMASK), value);
    }

    /**
     * Get the <i>Mains Alarm Mask</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The MainsAlarmMask attribute is 8-bits in length and specifies which mains alarms may
     * be generated. A ‘1’ in each bit position enables the alarm.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMainsAlarmMaskAsync() {
        return read(attributes.get(ATTR_MAINSALARMMASK));
    }

    /**
     * Synchronously get the <i>Mains Alarm Mask</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The MainsAlarmMask attribute is 8-bits in length and specifies which mains alarms may
     * be generated. A ‘1’ in each bit position enables the alarm.
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
     */
    public Integer getMainsAlarmMask(final long refreshPeriod) {
        if (attributes.get(ATTR_MAINSALARMMASK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MAINSALARMMASK).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MAINSALARMMASK));
    }

    /**
     * Set the <i>Mains Voltage Min Threshold</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The MainsVoltageMinThreshold attribute is 16-bits in length and specifies the lower
     * alarm threshold, measured in units of 100mV, for the MainsVoltage attribute. The value
     * of this attribute shall be less than MainsVoltageMaxThreshold.
     * <p>
     * If the value of MainsVoltage drops below the threshold specified by
     * MainsVoltageMinThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute increases to
     * greater than or equal to MainsVoltageMinThreshold before the timer expires, the
     * device shall stop and reset the timer. If the timer expires, an alarm shall be generated.
     * <p>
     * The Alarm Code field included in the generated alarm shall be 0x00.
     * <p>
     * If this attribute takes the value 0xffff then this alarm shall not be generated.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param mainsVoltageMinThreshold the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMainsVoltageMinThreshold(final Integer value) {
        return write(attributes.get(ATTR_MAINSVOLTAGEMINTHRESHOLD), value);
    }

    /**
     * Get the <i>Mains Voltage Min Threshold</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The MainsVoltageMinThreshold attribute is 16-bits in length and specifies the lower
     * alarm threshold, measured in units of 100mV, for the MainsVoltage attribute. The value
     * of this attribute shall be less than MainsVoltageMaxThreshold.
     * <p>
     * If the value of MainsVoltage drops below the threshold specified by
     * MainsVoltageMinThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute increases to
     * greater than or equal to MainsVoltageMinThreshold before the timer expires, the
     * device shall stop and reset the timer. If the timer expires, an alarm shall be generated.
     * <p>
     * The Alarm Code field included in the generated alarm shall be 0x00.
     * <p>
     * If this attribute takes the value 0xffff then this alarm shall not be generated.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMainsVoltageMinThresholdAsync() {
        return read(attributes.get(ATTR_MAINSVOLTAGEMINTHRESHOLD));
    }

    /**
     * Synchronously get the <i>Mains Voltage Min Threshold</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The MainsVoltageMinThreshold attribute is 16-bits in length and specifies the lower
     * alarm threshold, measured in units of 100mV, for the MainsVoltage attribute. The value
     * of this attribute shall be less than MainsVoltageMaxThreshold.
     * <p>
     * If the value of MainsVoltage drops below the threshold specified by
     * MainsVoltageMinThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute increases to
     * greater than or equal to MainsVoltageMinThreshold before the timer expires, the
     * device shall stop and reset the timer. If the timer expires, an alarm shall be generated.
     * <p>
     * The Alarm Code field included in the generated alarm shall be 0x00.
     * <p>
     * If this attribute takes the value 0xffff then this alarm shall not be generated.
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
     */
    public Integer getMainsVoltageMinThreshold(final long refreshPeriod) {
        if (attributes.get(ATTR_MAINSVOLTAGEMINTHRESHOLD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MAINSVOLTAGEMINTHRESHOLD).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MAINSVOLTAGEMINTHRESHOLD));
    }

    /**
     * Set the <i>Mains Voltage Max Threshold</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The MainsVoltageMaxThreshold attribute is 16-bits in length and specifies the upper
     * alarm threshold, measured in units of 100mV, for the MainsVoltage attribute. The value
     * of this attribute shall be greater than MainsVoltageMinThreshold.
     * <p>
     * If the value of MainsVoltage rises above the threshold specified by
     * MainsVoltageMaxThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute drops to lower
     * than or equal to MainsVoltageMaxThreshold before the timer expires, the device shall
     * stop and reset the timer. If the timer expires, an alarm shall be generated.
     * <p>
     * The Alarm Code field included in the generated alarm shall be 0x01.
     * <p>
     * If this attribute takes the value 0xffff then this alarm shall not be generated.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param mainsVoltageMaxThreshold the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMainsVoltageMaxThreshold(final Integer value) {
        return write(attributes.get(ATTR_MAINSVOLTAGEMAXTHRESHOLD), value);
    }

    /**
     * Get the <i>Mains Voltage Max Threshold</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The MainsVoltageMaxThreshold attribute is 16-bits in length and specifies the upper
     * alarm threshold, measured in units of 100mV, for the MainsVoltage attribute. The value
     * of this attribute shall be greater than MainsVoltageMinThreshold.
     * <p>
     * If the value of MainsVoltage rises above the threshold specified by
     * MainsVoltageMaxThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute drops to lower
     * than or equal to MainsVoltageMaxThreshold before the timer expires, the device shall
     * stop and reset the timer. If the timer expires, an alarm shall be generated.
     * <p>
     * The Alarm Code field included in the generated alarm shall be 0x01.
     * <p>
     * If this attribute takes the value 0xffff then this alarm shall not be generated.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMainsVoltageMaxThresholdAsync() {
        return read(attributes.get(ATTR_MAINSVOLTAGEMAXTHRESHOLD));
    }

    /**
     * Synchronously get the <i>Mains Voltage Max Threshold</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The MainsVoltageMaxThreshold attribute is 16-bits in length and specifies the upper
     * alarm threshold, measured in units of 100mV, for the MainsVoltage attribute. The value
     * of this attribute shall be greater than MainsVoltageMinThreshold.
     * <p>
     * If the value of MainsVoltage rises above the threshold specified by
     * MainsVoltageMaxThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute drops to lower
     * than or equal to MainsVoltageMaxThreshold before the timer expires, the device shall
     * stop and reset the timer. If the timer expires, an alarm shall be generated.
     * <p>
     * The Alarm Code field included in the generated alarm shall be 0x01.
     * <p>
     * If this attribute takes the value 0xffff then this alarm shall not be generated.
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
     */
    public Integer getMainsVoltageMaxThreshold(final long refreshPeriod) {
        if (attributes.get(ATTR_MAINSVOLTAGEMAXTHRESHOLD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MAINSVOLTAGEMAXTHRESHOLD).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MAINSVOLTAGEMAXTHRESHOLD));
    }

    /**
     * Set the <i>Mains Voltage Dwell Trip Point</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The MainsVoltageDwellTripPoint attribute is 16-bits in length and specifies the
     * length of time, in seconds that the value of MainsVoltage may exist beyond either of its
     * thresholds before an alarm is generated.
     * <p>
     * If this attribute takes the value 0xffff then the associated alarms shall not be
     * generated.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param mainsVoltageDwellTripPoint the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMainsVoltageDwellTripPoint(final Integer value) {
        return write(attributes.get(ATTR_MAINSVOLTAGEDWELLTRIPPOINT), value);
    }

    /**
     * Get the <i>Mains Voltage Dwell Trip Point</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The MainsVoltageDwellTripPoint attribute is 16-bits in length and specifies the
     * length of time, in seconds that the value of MainsVoltage may exist beyond either of its
     * thresholds before an alarm is generated.
     * <p>
     * If this attribute takes the value 0xffff then the associated alarms shall not be
     * generated.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMainsVoltageDwellTripPointAsync() {
        return read(attributes.get(ATTR_MAINSVOLTAGEDWELLTRIPPOINT));
    }

    /**
     * Synchronously get the <i>Mains Voltage Dwell Trip Point</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The MainsVoltageDwellTripPoint attribute is 16-bits in length and specifies the
     * length of time, in seconds that the value of MainsVoltage may exist beyond either of its
     * thresholds before an alarm is generated.
     * <p>
     * If this attribute takes the value 0xffff then the associated alarms shall not be
     * generated.
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
     */
    public Integer getMainsVoltageDwellTripPoint(final long refreshPeriod) {
        if (attributes.get(ATTR_MAINSVOLTAGEDWELLTRIPPOINT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MAINSVOLTAGEDWELLTRIPPOINT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MAINSVOLTAGEDWELLTRIPPOINT));
    }

    /**
     * Get the <i>Battery Voltage</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * The BatteryVoltage attribute is 8-bits in length and specifies the current actual
     * (measured) battery voltage, in units of 100mV. The value 0xff indicates an invalid or
     * unknown reading.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryVoltageAsync() {
        return read(attributes.get(ATTR_BATTERYVOLTAGE));
    }

    /**
     * Synchronously get the <i>Battery Voltage</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * The BatteryVoltage attribute is 8-bits in length and specifies the current actual
     * (measured) battery voltage, in units of 100mV. The value 0xff indicates an invalid or
     * unknown reading.
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
     */
    public Integer getBatteryVoltage(final long refreshPeriod) {
        if (attributes.get(ATTR_BATTERYVOLTAGE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BATTERYVOLTAGE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYVOLTAGE));
    }

    /**
     * Get the <i>Battery Percentage Remaining</i> attribute [attribute ID <b>0x0021</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryPercentageRemainingAsync() {
        return read(attributes.get(ATTR_BATTERYPERCENTAGEREMAINING));
    }

    /**
     * Synchronously get the <i>Battery Percentage Remaining</i> attribute [attribute ID <b>0x0021</b>].
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
     */
    public Integer getBatteryPercentageRemaining(final long refreshPeriod) {
        if (attributes.get(ATTR_BATTERYPERCENTAGEREMAINING).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BATTERYPERCENTAGEREMAINING).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYPERCENTAGEREMAINING));
    }

    /**
     * Set the <i>Battery Manufacturer</i> attribute [attribute ID <b>0x0030</b>].
     * <p>
     * The BatteryManufacturer attribute is a maximum of 16 bytes in length and specifies the
     * name of the battery manufacturer as a ZigBee character string.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param batteryManufacturer the {@link String} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryManufacturer(final String value) {
        return write(attributes.get(ATTR_BATTERYMANUFACTURER), value);
    }

    /**
     * Get the <i>Battery Manufacturer</i> attribute [attribute ID <b>0x0030</b>].
     * <p>
     * The BatteryManufacturer attribute is a maximum of 16 bytes in length and specifies the
     * name of the battery manufacturer as a ZigBee character string.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryManufacturerAsync() {
        return read(attributes.get(ATTR_BATTERYMANUFACTURER));
    }

    /**
     * Synchronously get the <i>Battery Manufacturer</i> attribute [attribute ID <b>0x0030</b>].
     * <p>
     * The BatteryManufacturer attribute is a maximum of 16 bytes in length and specifies the
     * name of the battery manufacturer as a ZigBee character string.
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
     */
    public String getBatteryManufacturer(final long refreshPeriod) {
        if (attributes.get(ATTR_BATTERYMANUFACTURER).isLastValueCurrent(refreshPeriod)) {
            return (String) attributes.get(ATTR_BATTERYMANUFACTURER).getLastValue();
        }

        return (String) readSync(attributes.get(ATTR_BATTERYMANUFACTURER));
    }

    /**
     * Set the <i>Battery Size</i> attribute [attribute ID <b>0x0031</b>].
     * <p>
     * The BatterySize attribute is an enumeration which specifies the type of battery being
     * used by the device.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param batterySize the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatterySize(final Integer value) {
        return write(attributes.get(ATTR_BATTERYSIZE), value);
    }

    /**
     * Get the <i>Battery Size</i> attribute [attribute ID <b>0x0031</b>].
     * <p>
     * The BatterySize attribute is an enumeration which specifies the type of battery being
     * used by the device.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatterySizeAsync() {
        return read(attributes.get(ATTR_BATTERYSIZE));
    }

    /**
     * Synchronously get the <i>Battery Size</i> attribute [attribute ID <b>0x0031</b>].
     * <p>
     * The BatterySize attribute is an enumeration which specifies the type of battery being
     * used by the device.
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
     */
    public Integer getBatterySize(final long refreshPeriod) {
        if (attributes.get(ATTR_BATTERYSIZE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BATTERYSIZE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYSIZE));
    }

    /**
     * Set the <i>Battery A Hr Rating</i> attribute [attribute ID <b>0x0032</b>].
     * <p>
     * The BatteryAHrRating attribute is 16-bits in length and specifies the Ampere-hour
     * rating of the battery, measured in units of 10mAHr.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param batteryAHrRating the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryAHrRating(final Integer value) {
        return write(attributes.get(ATTR_BATTERYAHRRATING), value);
    }

    /**
     * Get the <i>Battery A Hr Rating</i> attribute [attribute ID <b>0x0032</b>].
     * <p>
     * The BatteryAHrRating attribute is 16-bits in length and specifies the Ampere-hour
     * rating of the battery, measured in units of 10mAHr.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryAHrRatingAsync() {
        return read(attributes.get(ATTR_BATTERYAHRRATING));
    }

    /**
     * Synchronously get the <i>Battery A Hr Rating</i> attribute [attribute ID <b>0x0032</b>].
     * <p>
     * The BatteryAHrRating attribute is 16-bits in length and specifies the Ampere-hour
     * rating of the battery, measured in units of 10mAHr.
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
     */
    public Integer getBatteryAHrRating(final long refreshPeriod) {
        if (attributes.get(ATTR_BATTERYAHRRATING).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BATTERYAHRRATING).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYAHRRATING));
    }

    /**
     * Set the <i>Battery Quantity</i> attribute [attribute ID <b>0x0033</b>].
     * <p>
     * The BatteryQuantity attribute is 8-bits in length and specifies the number of battery
     * cells used to power the device.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param batteryQuantity the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryQuantity(final Integer value) {
        return write(attributes.get(ATTR_BATTERYQUANTITY), value);
    }

    /**
     * Get the <i>Battery Quantity</i> attribute [attribute ID <b>0x0033</b>].
     * <p>
     * The BatteryQuantity attribute is 8-bits in length and specifies the number of battery
     * cells used to power the device.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryQuantityAsync() {
        return read(attributes.get(ATTR_BATTERYQUANTITY));
    }

    /**
     * Synchronously get the <i>Battery Quantity</i> attribute [attribute ID <b>0x0033</b>].
     * <p>
     * The BatteryQuantity attribute is 8-bits in length and specifies the number of battery
     * cells used to power the device.
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
     */
    public Integer getBatteryQuantity(final long refreshPeriod) {
        if (attributes.get(ATTR_BATTERYQUANTITY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BATTERYQUANTITY).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYQUANTITY));
    }

    /**
     * Set the <i>Battery Rated Voltage</i> attribute [attribute ID <b>0x0034</b>].
     * <p>
     * The BatteryRatedVoltage attribute is 8-bits in length and specifies the rated voltage
     * of the battery being used in the device, measured in units of 100mV.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param batteryRatedVoltage the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryRatedVoltage(final Integer value) {
        return write(attributes.get(ATTR_BATTERYRATEDVOLTAGE), value);
    }

    /**
     * Get the <i>Battery Rated Voltage</i> attribute [attribute ID <b>0x0034</b>].
     * <p>
     * The BatteryRatedVoltage attribute is 8-bits in length and specifies the rated voltage
     * of the battery being used in the device, measured in units of 100mV.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryRatedVoltageAsync() {
        return read(attributes.get(ATTR_BATTERYRATEDVOLTAGE));
    }

    /**
     * Synchronously get the <i>Battery Rated Voltage</i> attribute [attribute ID <b>0x0034</b>].
     * <p>
     * The BatteryRatedVoltage attribute is 8-bits in length and specifies the rated voltage
     * of the battery being used in the device, measured in units of 100mV.
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
     */
    public Integer getBatteryRatedVoltage(final long refreshPeriod) {
        if (attributes.get(ATTR_BATTERYRATEDVOLTAGE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BATTERYRATEDVOLTAGE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYRATEDVOLTAGE));
    }

    /**
     * Set the <i>Battery Alarm Mask</i> attribute [attribute ID <b>0x0035</b>].
     * <p>
     * The BatteryAlarmMask attribute is 8-bits in length and specifies which battery alarms
     * may be generated.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param batteryAlarmMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryAlarmMask(final Integer value) {
        return write(attributes.get(ATTR_BATTERYALARMMASK), value);
    }

    /**
     * Get the <i>Battery Alarm Mask</i> attribute [attribute ID <b>0x0035</b>].
     * <p>
     * The BatteryAlarmMask attribute is 8-bits in length and specifies which battery alarms
     * may be generated.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryAlarmMaskAsync() {
        return read(attributes.get(ATTR_BATTERYALARMMASK));
    }

    /**
     * Synchronously get the <i>Battery Alarm Mask</i> attribute [attribute ID <b>0x0035</b>].
     * <p>
     * The BatteryAlarmMask attribute is 8-bits in length and specifies which battery alarms
     * may be generated.
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
     */
    public Integer getBatteryAlarmMask(final long refreshPeriod) {
        if (attributes.get(ATTR_BATTERYALARMMASK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BATTERYALARMMASK).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYALARMMASK));
    }

    /**
     * Set the <i>Battery Voltage Min Threshold</i> attribute [attribute ID <b>0x0036</b>].
     * <p>
     * The BatteryVoltageMinThreshold attribute is 8-bits in length and specifies the low
     * voltage alarm threshold, measured in units of 100mV, for the BatteryVoltage
     * attribute.
     * <p>
     * If the value of BatteryVoltage drops below the threshold specified by
     * BatteryVoltageMinThreshold an alarm shall be generated.
     * <p>
     * The Alarm Code field included in the generated alarm shall be 0x10.
     * <p>
     * If this attribute takes the value 0xff then this alarm shall not be generated.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param batteryVoltageMinThreshold the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryVoltageMinThreshold(final Integer value) {
        return write(attributes.get(ATTR_BATTERYVOLTAGEMINTHRESHOLD), value);
    }

    /**
     * Get the <i>Battery Voltage Min Threshold</i> attribute [attribute ID <b>0x0036</b>].
     * <p>
     * The BatteryVoltageMinThreshold attribute is 8-bits in length and specifies the low
     * voltage alarm threshold, measured in units of 100mV, for the BatteryVoltage
     * attribute.
     * <p>
     * If the value of BatteryVoltage drops below the threshold specified by
     * BatteryVoltageMinThreshold an alarm shall be generated.
     * <p>
     * The Alarm Code field included in the generated alarm shall be 0x10.
     * <p>
     * If this attribute takes the value 0xff then this alarm shall not be generated.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryVoltageMinThresholdAsync() {
        return read(attributes.get(ATTR_BATTERYVOLTAGEMINTHRESHOLD));
    }

    /**
     * Synchronously get the <i>Battery Voltage Min Threshold</i> attribute [attribute ID <b>0x0036</b>].
     * <p>
     * The BatteryVoltageMinThreshold attribute is 8-bits in length and specifies the low
     * voltage alarm threshold, measured in units of 100mV, for the BatteryVoltage
     * attribute.
     * <p>
     * If the value of BatteryVoltage drops below the threshold specified by
     * BatteryVoltageMinThreshold an alarm shall be generated.
     * <p>
     * The Alarm Code field included in the generated alarm shall be 0x10.
     * <p>
     * If this attribute takes the value 0xff then this alarm shall not be generated.
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
     */
    public Integer getBatteryVoltageMinThreshold(final long refreshPeriod) {
        if (attributes.get(ATTR_BATTERYVOLTAGEMINTHRESHOLD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BATTERYVOLTAGEMINTHRESHOLD).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYVOLTAGEMINTHRESHOLD));
    }

    /**
     * Set the <i>Battery Voltage Threshold 1</i> attribute [attribute ID <b>0x0037</b>].
     * <p>
     * Specify the low voltage alarm thresholds, measured in units of 100mV, for the
     * BatteryVoltage attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param batteryVoltageThreshold1 the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryVoltageThreshold1(final Integer value) {
        return write(attributes.get(ATTR_BATTERYVOLTAGETHRESHOLD1), value);
    }

    /**
     * Get the <i>Battery Voltage Threshold 1</i> attribute [attribute ID <b>0x0037</b>].
     * <p>
     * Specify the low voltage alarm thresholds, measured in units of 100mV, for the
     * BatteryVoltage attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryVoltageThreshold1Async() {
        return read(attributes.get(ATTR_BATTERYVOLTAGETHRESHOLD1));
    }

    /**
     * Synchronously get the <i>Battery Voltage Threshold 1</i> attribute [attribute ID <b>0x0037</b>].
     * <p>
     * Specify the low voltage alarm thresholds, measured in units of 100mV, for the
     * BatteryVoltage attribute.
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
     */
    public Integer getBatteryVoltageThreshold1(final long refreshPeriod) {
        if (attributes.get(ATTR_BATTERYVOLTAGETHRESHOLD1).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BATTERYVOLTAGETHRESHOLD1).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYVOLTAGETHRESHOLD1));
    }

    /**
     * Set the <i>Battery Voltage Threshold 2</i> attribute [attribute ID <b>0x0038</b>].
     * <p>
     * Specify the low voltage alarm thresholds, measured in units of 100mV, for the
     * BatteryVoltage attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param batteryVoltageThreshold2 the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryVoltageThreshold2(final Integer value) {
        return write(attributes.get(ATTR_BATTERYVOLTAGETHRESHOLD2), value);
    }

    /**
     * Get the <i>Battery Voltage Threshold 2</i> attribute [attribute ID <b>0x0038</b>].
     * <p>
     * Specify the low voltage alarm thresholds, measured in units of 100mV, for the
     * BatteryVoltage attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryVoltageThreshold2Async() {
        return read(attributes.get(ATTR_BATTERYVOLTAGETHRESHOLD2));
    }

    /**
     * Synchronously get the <i>Battery Voltage Threshold 2</i> attribute [attribute ID <b>0x0038</b>].
     * <p>
     * Specify the low voltage alarm thresholds, measured in units of 100mV, for the
     * BatteryVoltage attribute.
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
     */
    public Integer getBatteryVoltageThreshold2(final long refreshPeriod) {
        if (attributes.get(ATTR_BATTERYVOLTAGETHRESHOLD2).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BATTERYVOLTAGETHRESHOLD2).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYVOLTAGETHRESHOLD2));
    }

    /**
     * Set the <i>Battery Voltage Threshold 3</i> attribute [attribute ID <b>0x0039</b>].
     * <p>
     * Specify the low voltage alarm thresholds, measured in units of 100mV, for the
     * BatteryVoltage attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param batteryVoltageThreshold3 the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryVoltageThreshold3(final Integer value) {
        return write(attributes.get(ATTR_BATTERYVOLTAGETHRESHOLD3), value);
    }

    /**
     * Get the <i>Battery Voltage Threshold 3</i> attribute [attribute ID <b>0x0039</b>].
     * <p>
     * Specify the low voltage alarm thresholds, measured in units of 100mV, for the
     * BatteryVoltage attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryVoltageThreshold3Async() {
        return read(attributes.get(ATTR_BATTERYVOLTAGETHRESHOLD3));
    }

    /**
     * Synchronously get the <i>Battery Voltage Threshold 3</i> attribute [attribute ID <b>0x0039</b>].
     * <p>
     * Specify the low voltage alarm thresholds, measured in units of 100mV, for the
     * BatteryVoltage attribute.
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
     */
    public Integer getBatteryVoltageThreshold3(final long refreshPeriod) {
        if (attributes.get(ATTR_BATTERYVOLTAGETHRESHOLD3).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BATTERYVOLTAGETHRESHOLD3).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYVOLTAGETHRESHOLD3));
    }

    /**
     * Set the <i>Battery Percentage Min Threshold</i> attribute [attribute ID <b>0x003A</b>].
     * <p>
     * Specifies the low battery percentage alarm threshold, measured in percentage (i.e.,
     * zero to 100%), for the BatteryPercentageRemaining attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param batteryPercentageMinThreshold the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryPercentageMinThreshold(final Integer value) {
        return write(attributes.get(ATTR_BATTERYPERCENTAGEMINTHRESHOLD), value);
    }

    /**
     * Get the <i>Battery Percentage Min Threshold</i> attribute [attribute ID <b>0x003A</b>].
     * <p>
     * Specifies the low battery percentage alarm threshold, measured in percentage (i.e.,
     * zero to 100%), for the BatteryPercentageRemaining attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryPercentageMinThresholdAsync() {
        return read(attributes.get(ATTR_BATTERYPERCENTAGEMINTHRESHOLD));
    }

    /**
     * Synchronously get the <i>Battery Percentage Min Threshold</i> attribute [attribute ID <b>0x003A</b>].
     * <p>
     * Specifies the low battery percentage alarm threshold, measured in percentage (i.e.,
     * zero to 100%), for the BatteryPercentageRemaining attribute.
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
     */
    public Integer getBatteryPercentageMinThreshold(final long refreshPeriod) {
        if (attributes.get(ATTR_BATTERYPERCENTAGEMINTHRESHOLD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BATTERYPERCENTAGEMINTHRESHOLD).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYPERCENTAGEMINTHRESHOLD));
    }

    /**
     * Set the <i>Battery Percentage Threshold 1</i> attribute [attribute ID <b>0x003B</b>].
     * <p>
     * Specifies the low battery percentage alarm threshold, measured in percentage (i.e.,
     * zero to 100%), for the BatteryPercentageRemaining attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param batteryPercentageThreshold1 the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryPercentageThreshold1(final Integer value) {
        return write(attributes.get(ATTR_BATTERYPERCENTAGETHRESHOLD1), value);
    }

    /**
     * Get the <i>Battery Percentage Threshold 1</i> attribute [attribute ID <b>0x003B</b>].
     * <p>
     * Specifies the low battery percentage alarm threshold, measured in percentage (i.e.,
     * zero to 100%), for the BatteryPercentageRemaining attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryPercentageThreshold1Async() {
        return read(attributes.get(ATTR_BATTERYPERCENTAGETHRESHOLD1));
    }

    /**
     * Synchronously get the <i>Battery Percentage Threshold 1</i> attribute [attribute ID <b>0x003B</b>].
     * <p>
     * Specifies the low battery percentage alarm threshold, measured in percentage (i.e.,
     * zero to 100%), for the BatteryPercentageRemaining attribute.
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
     */
    public Integer getBatteryPercentageThreshold1(final long refreshPeriod) {
        if (attributes.get(ATTR_BATTERYPERCENTAGETHRESHOLD1).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BATTERYPERCENTAGETHRESHOLD1).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYPERCENTAGETHRESHOLD1));
    }

    /**
     * Set the <i>Battery Percentage Threshold 2</i> attribute [attribute ID <b>0x003C</b>].
     * <p>
     * Specifies the low battery percentage alarm threshold, measured in percentage (i.e.,
     * zero to 100%), for the BatteryPercentageRemaining attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param batteryPercentageThreshold2 the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryPercentageThreshold2(final Integer value) {
        return write(attributes.get(ATTR_BATTERYPERCENTAGETHRESHOLD2), value);
    }

    /**
     * Get the <i>Battery Percentage Threshold 2</i> attribute [attribute ID <b>0x003C</b>].
     * <p>
     * Specifies the low battery percentage alarm threshold, measured in percentage (i.e.,
     * zero to 100%), for the BatteryPercentageRemaining attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryPercentageThreshold2Async() {
        return read(attributes.get(ATTR_BATTERYPERCENTAGETHRESHOLD2));
    }

    /**
     * Synchronously get the <i>Battery Percentage Threshold 2</i> attribute [attribute ID <b>0x003C</b>].
     * <p>
     * Specifies the low battery percentage alarm threshold, measured in percentage (i.e.,
     * zero to 100%), for the BatteryPercentageRemaining attribute.
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
     */
    public Integer getBatteryPercentageThreshold2(final long refreshPeriod) {
        if (attributes.get(ATTR_BATTERYPERCENTAGETHRESHOLD2).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BATTERYPERCENTAGETHRESHOLD2).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYPERCENTAGETHRESHOLD2));
    }

    /**
     * Set the <i>Battery Percentage Threshold 3</i> attribute [attribute ID <b>0x003D</b>].
     * <p>
     * Specifies the low battery percentage alarm threshold, measured in percentage (i.e.,
     * zero to 100%), for the BatteryPercentageRemaining attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param batteryPercentageThreshold3 the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryPercentageThreshold3(final Integer value) {
        return write(attributes.get(ATTR_BATTERYPERCENTAGETHRESHOLD3), value);
    }

    /**
     * Get the <i>Battery Percentage Threshold 3</i> attribute [attribute ID <b>0x003D</b>].
     * <p>
     * Specifies the low battery percentage alarm threshold, measured in percentage (i.e.,
     * zero to 100%), for the BatteryPercentageRemaining attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryPercentageThreshold3Async() {
        return read(attributes.get(ATTR_BATTERYPERCENTAGETHRESHOLD3));
    }

    /**
     * Synchronously get the <i>Battery Percentage Threshold 3</i> attribute [attribute ID <b>0x003D</b>].
     * <p>
     * Specifies the low battery percentage alarm threshold, measured in percentage (i.e.,
     * zero to 100%), for the BatteryPercentageRemaining attribute.
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
     */
    public Integer getBatteryPercentageThreshold3(final long refreshPeriod) {
        if (attributes.get(ATTR_BATTERYPERCENTAGETHRESHOLD3).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BATTERYPERCENTAGETHRESHOLD3).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYPERCENTAGETHRESHOLD3));
    }

    /**
     * Get the <i>Battery Alarm State</i> attribute [attribute ID <b>0x003E</b>].
     * <p>
     * Specifies the current state of the device's battery alarms. This attribute provides a
     * persistent record of a device's battery alarm conditions as well as a mechanism for
     * reporting changes to those conditions, including the elimination of battery alarm
     * states (e.g., when a battery is replaced).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryAlarmStateAsync() {
        return read(attributes.get(ATTR_BATTERYALARMSTATE));
    }

    /**
     * Synchronously get the <i>Battery Alarm State</i> attribute [attribute ID <b>0x003E</b>].
     * <p>
     * Specifies the current state of the device's battery alarms. This attribute provides a
     * persistent record of a device's battery alarm conditions as well as a mechanism for
     * reporting changes to those conditions, including the elimination of battery alarm
     * states (e.g., when a battery is replaced).
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
     */
    public Integer getBatteryAlarmState(final long refreshPeriod) {
        if (attributes.get(ATTR_BATTERYALARMSTATE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BATTERYALARMSTATE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYALARMSTATE));
    }
}
