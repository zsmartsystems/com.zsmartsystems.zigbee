/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

/**
 * <b>Power configuration</b> cluster implementation (<i>Cluster ID 0x0001</i>).
 * <p>
 * Attributes for determining detailed information about a device’s power source(s),
 * and for configuring under/over voltage alarms.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclPowerConfigurationCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0001;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Power configuration";

    // Attribute constants
    /**
     * The MainsVoltage attribute is 16-bits in length and specifies the actual (measured)
     * RMS voltage (or DC voltage in the case of a DC supply) currently applied to the
     * device, measured in units of 100mV.
     */
    public static final int ATTR_MAINSVOLTAGE = 0x0000;
    /**
     * The MainsFrequency attribute is 8-bits in length and represents the frequency, in
     * Hertz, of the mains as determined by the device as follows:-
     * <p>
     * MainsFrequency = 0.5 x measured frequency
     * <p>
     * Where 2 Hz <= measured frequency <= 506 Hz, corresponding to a
     * <p>
     * MainsFrequency in the range 1 to 0xfd.
     * <p>
     * The maximum resolution this format allows is 2 Hz.
     * The following special values of MainsFrequency apply.
     * <li>0x00 indicates a frequency that is too low to be measured.</li>
     * <li>0xfe indicates a frequency that is too high to be measured.</li>
     * <li>0xff indicates that the frequency could not be measured.</li>
     */
    public static final int ATTR_MAINSFREQUENCY = 0x0001;
    /**
     * The MainsAlarmMask attribute is 8-bits in length and specifies which mains
     * alarms may be generated. A ‘1’ in each bit position enables the alarm.
     */
    public static final int ATTR_MAINSALARMMASK = 0x0010;
    /**
     * The MainsVoltageMinThreshold attribute is 16-bits in length and specifies the
     * lower alarm threshold, measured in units of 100mV, for the MainsVoltage
     * attribute. The value of this attribute shall be less than MainsVoltageMaxThreshold.
     * <p>
     * If the value of MainsVoltage drops below the threshold specified by
     * MainsVoltageMinThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute increases to
     * greater than or equal to MainsVoltageMinThreshold before the timer expires, the
     * device shall stop and reset the timer. If the timer expires, an alarm shall be
     * generated.
     * <p>
     * The Alarm Code field included in the generated alarm shall be 0x00.
     * <p>
     * If this attribute takes the value 0xffff then this alarm shall not be generated.
     */
    public static final int ATTR_MAINSVOLTAGEMINTHRESHOLD = 0x0011;
    /**
     * The MainsVoltageMaxThreshold attribute is 16-bits in length and specifies the
     * upper alarm threshold, measured in units of 100mV, for the MainsVoltage
     * attribute. The value of this attribute shall be greater than
     * MainsVoltageMinThreshold.
     * <p>
     * If the value of MainsVoltage rises above the threshold specified by
     * MainsVoltageMaxThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute drops to lower
     * than or equal to MainsVoltageMaxThreshold before the timer expires, the device
     * shall stop and reset the timer. If the timer expires, an alarm shall be generated.
     * <p>
     * The Alarm Code field included in the generated alarm shall be 0x01.
     * <p>
     * If this attribute takes the value 0xffff then this alarm shall not be generated.
     */
    public static final int ATTR_MAINSVOLTAGEMAXTHRESHOLD = 0x0012;
    /**
     * The MainsVoltageDwellTripPoint attribute is 16-bits in length and specifies the
     * length of time, in seconds that the value of MainsVoltage may exist beyond either
     * of its thresholds before an alarm is generated.
     * <p>
     * If this attribute takes the value 0xffff then the associated alarms shall not be
     * generated.
     */
    public static final int ATTR_MAINSVOLTAGEDWELLTRIPPOINT = 0x0013;
    /**
     * The BatteryVoltage attribute is 8-bits in length and specifies the current actual
     * (measured) battery voltage, in units of 100mV.
     * The value 0xff indicates an invalid or unknown reading.
     */
    public static final int ATTR_BATTERYVOLTAGE = 0x0020;
    /**
     * The BatteryManufacturer attribute is a maximum of 16 bytes in length and
     * specifies the name of the battery manufacturer as a ZigBee character string.
     */
    public static final int ATTR_BATTERYMANUFACTURER = 0x0030;
    /**
     * The BatterySize attribute is an enumeration which specifies the type of battery
     * being used by the device.
     */
    public static final int ATTR_BATTERYSIZE = 0x0031;
    /**
     * The BatteryAHrRating attribute is 16-bits in length and specifies the Ampere-hour
     * rating of the battery, measured in units of 10mAHr.
     */
    public static final int ATTR_BATTERYAHRRATING = 0x0032;
    /**
     * The BatteryQuantity attribute is 8-bits in length and specifies the number of
     * battery cells used to power the device.
     */
    public static final int ATTR_BATTERYQUANTITY = 0x0033;
    /**
     * The BatteryRatedVoltage attribute is 8-bits in length and specifies the rated
     * voltage of the battery being used in the device, measured in units of 100mV.
     */
    public static final int ATTR_BATTERYRATEDVOLTAGE = 0x0034;
    /**
     * The BatteryAlarmMask attribute is 8-bits in length and specifies which battery
     * alarms may be generated.
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

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(14);

        attributeMap.put(ATTR_MAINSVOLTAGE, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_MAINSVOLTAGE, "MainsVoltage", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MAINSFREQUENCY, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_MAINSFREQUENCY, "MainsFrequency", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MAINSALARMMASK, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_MAINSALARMMASK, "MainsAlarmMask", ZclDataType.BITMAP_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_MAINSVOLTAGEMINTHRESHOLD, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_MAINSVOLTAGEMINTHRESHOLD, "MainsVoltageMinThreshold", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_MAINSVOLTAGEMAXTHRESHOLD, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_MAINSVOLTAGEMAXTHRESHOLD, "MainsVoltageMaxThreshold", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_MAINSVOLTAGEDWELLTRIPPOINT, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_MAINSVOLTAGEDWELLTRIPPOINT, "MainsVoltageDwellTripPoint", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYVOLTAGE, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYVOLTAGE, "BatteryVoltage", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BATTERYMANUFACTURER, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYMANUFACTURER, "BatteryManufacturer", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_BATTERYSIZE, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYSIZE, "BatterySize", ZclDataType.ENUMERATION_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_BATTERYAHRRATING, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYAHRRATING, "BatteryAHrRating", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYQUANTITY, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYQUANTITY, "BatteryQuantity", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYRATEDVOLTAGE, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYRATEDVOLTAGE, "BatteryRatedVoltage", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYALARMMASK, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYALARMMASK, "BatteryAlarmMask", ZclDataType.BITMAP_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_BATTERYVOLTAGEMINTHRESHOLD, new ZclAttribute(ZclClusterType.POWER_CONFIGURATION, ATTR_BATTERYVOLTAGEMINTHRESHOLD, "BatteryVoltageMinThreshold", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Power configuration cluster.
     *
     * @param zigbeeManager {@link ZigBeeNetworkManager}
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint}
     */
    public ZclPowerConfigurationCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeManager, zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * Get the <i>MainsVoltage</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The MainsVoltage attribute is 16-bits in length and specifies the actual (measured)
     * RMS voltage (or DC voltage in the case of a DC supply) currently applied to the
     * device, measured in units of 100mV.
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
     * Synchronously get the <i>MainsVoltage</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The MainsVoltage attribute is 16-bits in length and specifies the actual (measured)
     * RMS voltage (or DC voltage in the case of a DC supply) currently applied to the
     * device, measured in units of 100mV.
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
        if(refreshPeriod > 0 && attributes.get(ATTR_MAINSVOLTAGE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_MAINSVOLTAGE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_MAINSVOLTAGE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_MAINSVOLTAGE));
    }

    /**
     * Get the <i>MainsFrequency</i> attribute [attribute ID <b>1</b>].
     * <p>
     * The MainsFrequency attribute is 8-bits in length and represents the frequency, in
     * Hertz, of the mains as determined by the device as follows:-
     * <p>
     * MainsFrequency = 0.5 x measured frequency
     * <p>
     * Where 2 Hz <= measured frequency <= 506 Hz, corresponding to a
     * <p>
     * MainsFrequency in the range 1 to 0xfd.
     * <p>
     * The maximum resolution this format allows is 2 Hz.
     * The following special values of MainsFrequency apply.
     * <li>0x00 indicates a frequency that is too low to be measured.</li>
     * <li>0xfe indicates a frequency that is too high to be measured.</li>
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
     * Synchronously get the <i>MainsFrequency</i> attribute [attribute ID <b>1</b>].
     * <p>
     * The MainsFrequency attribute is 8-bits in length and represents the frequency, in
     * Hertz, of the mains as determined by the device as follows:-
     * <p>
     * MainsFrequency = 0.5 x measured frequency
     * <p>
     * Where 2 Hz <= measured frequency <= 506 Hz, corresponding to a
     * <p>
     * MainsFrequency in the range 1 to 0xfd.
     * <p>
     * The maximum resolution this format allows is 2 Hz.
     * The following special values of MainsFrequency apply.
     * <li>0x00 indicates a frequency that is too low to be measured.</li>
     * <li>0xfe indicates a frequency that is too high to be measured.</li>
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
        if(refreshPeriod > 0 && attributes.get(ATTR_MAINSFREQUENCY).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_MAINSFREQUENCY).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_MAINSFREQUENCY).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_MAINSFREQUENCY));
    }


    /**
     * Set the <i>MainsAlarmMask</i> attribute [attribute ID <b>16</b>].
     * <p>
     * The MainsAlarmMask attribute is 8-bits in length and specifies which mains
     * alarms may be generated. A ‘1’ in each bit position enables the alarm.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param mainsAlarmMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMainsAlarmMask(final Object value) {
        return write(attributes.get(ATTR_MAINSALARMMASK), value);
    }

    /**
     * Get the <i>MainsAlarmMask</i> attribute [attribute ID <b>16</b>].
     * <p>
     * The MainsAlarmMask attribute is 8-bits in length and specifies which mains
     * alarms may be generated. A ‘1’ in each bit position enables the alarm.
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
     * Synchronously get the <i>MainsAlarmMask</i> attribute [attribute ID <b>16</b>].
     * <p>
     * The MainsAlarmMask attribute is 8-bits in length and specifies which mains
     * alarms may be generated. A ‘1’ in each bit position enables the alarm.
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
        if(refreshPeriod > 0 && attributes.get(ATTR_MAINSALARMMASK).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_MAINSALARMMASK).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_MAINSALARMMASK).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_MAINSALARMMASK));
    }


    /**
     * Set the <i>MainsVoltageMinThreshold</i> attribute [attribute ID <b>17</b>].
     * <p>
     * The MainsVoltageMinThreshold attribute is 16-bits in length and specifies the
     * lower alarm threshold, measured in units of 100mV, for the MainsVoltage
     * attribute. The value of this attribute shall be less than MainsVoltageMaxThreshold.
     * <p>
     * If the value of MainsVoltage drops below the threshold specified by
     * MainsVoltageMinThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute increases to
     * greater than or equal to MainsVoltageMinThreshold before the timer expires, the
     * device shall stop and reset the timer. If the timer expires, an alarm shall be
     * generated.
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
    public Future<CommandResult> setMainsVoltageMinThreshold(final Object value) {
        return write(attributes.get(ATTR_MAINSVOLTAGEMINTHRESHOLD), value);
    }

    /**
     * Get the <i>MainsVoltageMinThreshold</i> attribute [attribute ID <b>17</b>].
     * <p>
     * The MainsVoltageMinThreshold attribute is 16-bits in length and specifies the
     * lower alarm threshold, measured in units of 100mV, for the MainsVoltage
     * attribute. The value of this attribute shall be less than MainsVoltageMaxThreshold.
     * <p>
     * If the value of MainsVoltage drops below the threshold specified by
     * MainsVoltageMinThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute increases to
     * greater than or equal to MainsVoltageMinThreshold before the timer expires, the
     * device shall stop and reset the timer. If the timer expires, an alarm shall be
     * generated.
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
     * Synchronously get the <i>MainsVoltageMinThreshold</i> attribute [attribute ID <b>17</b>].
     * <p>
     * The MainsVoltageMinThreshold attribute is 16-bits in length and specifies the
     * lower alarm threshold, measured in units of 100mV, for the MainsVoltage
     * attribute. The value of this attribute shall be less than MainsVoltageMaxThreshold.
     * <p>
     * If the value of MainsVoltage drops below the threshold specified by
     * MainsVoltageMinThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute increases to
     * greater than or equal to MainsVoltageMinThreshold before the timer expires, the
     * device shall stop and reset the timer. If the timer expires, an alarm shall be
     * generated.
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
        if(refreshPeriod > 0 && attributes.get(ATTR_MAINSVOLTAGEMINTHRESHOLD).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_MAINSVOLTAGEMINTHRESHOLD).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_MAINSVOLTAGEMINTHRESHOLD).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_MAINSVOLTAGEMINTHRESHOLD));
    }


    /**
     * Set the <i>MainsVoltageMaxThreshold</i> attribute [attribute ID <b>18</b>].
     * <p>
     * The MainsVoltageMaxThreshold attribute is 16-bits in length and specifies the
     * upper alarm threshold, measured in units of 100mV, for the MainsVoltage
     * attribute. The value of this attribute shall be greater than
     * MainsVoltageMinThreshold.
     * <p>
     * If the value of MainsVoltage rises above the threshold specified by
     * MainsVoltageMaxThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute drops to lower
     * than or equal to MainsVoltageMaxThreshold before the timer expires, the device
     * shall stop and reset the timer. If the timer expires, an alarm shall be generated.
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
    public Future<CommandResult> setMainsVoltageMaxThreshold(final Object value) {
        return write(attributes.get(ATTR_MAINSVOLTAGEMAXTHRESHOLD), value);
    }

    /**
     * Get the <i>MainsVoltageMaxThreshold</i> attribute [attribute ID <b>18</b>].
     * <p>
     * The MainsVoltageMaxThreshold attribute is 16-bits in length and specifies the
     * upper alarm threshold, measured in units of 100mV, for the MainsVoltage
     * attribute. The value of this attribute shall be greater than
     * MainsVoltageMinThreshold.
     * <p>
     * If the value of MainsVoltage rises above the threshold specified by
     * MainsVoltageMaxThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute drops to lower
     * than or equal to MainsVoltageMaxThreshold before the timer expires, the device
     * shall stop and reset the timer. If the timer expires, an alarm shall be generated.
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
     * Synchronously get the <i>MainsVoltageMaxThreshold</i> attribute [attribute ID <b>18</b>].
     * <p>
     * The MainsVoltageMaxThreshold attribute is 16-bits in length and specifies the
     * upper alarm threshold, measured in units of 100mV, for the MainsVoltage
     * attribute. The value of this attribute shall be greater than
     * MainsVoltageMinThreshold.
     * <p>
     * If the value of MainsVoltage rises above the threshold specified by
     * MainsVoltageMaxThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute drops to lower
     * than or equal to MainsVoltageMaxThreshold before the timer expires, the device
     * shall stop and reset the timer. If the timer expires, an alarm shall be generated.
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
        if(refreshPeriod > 0 && attributes.get(ATTR_MAINSVOLTAGEMAXTHRESHOLD).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_MAINSVOLTAGEMAXTHRESHOLD).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_MAINSVOLTAGEMAXTHRESHOLD).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_MAINSVOLTAGEMAXTHRESHOLD));
    }


    /**
     * Set the <i>MainsVoltageDwellTripPoint</i> attribute [attribute ID <b>19</b>].
     * <p>
     * The MainsVoltageDwellTripPoint attribute is 16-bits in length and specifies the
     * length of time, in seconds that the value of MainsVoltage may exist beyond either
     * of its thresholds before an alarm is generated.
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
    public Future<CommandResult> setMainsVoltageDwellTripPoint(final Object value) {
        return write(attributes.get(ATTR_MAINSVOLTAGEDWELLTRIPPOINT), value);
    }

    /**
     * Get the <i>MainsVoltageDwellTripPoint</i> attribute [attribute ID <b>19</b>].
     * <p>
     * The MainsVoltageDwellTripPoint attribute is 16-bits in length and specifies the
     * length of time, in seconds that the value of MainsVoltage may exist beyond either
     * of its thresholds before an alarm is generated.
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
     * Synchronously get the <i>MainsVoltageDwellTripPoint</i> attribute [attribute ID <b>19</b>].
     * <p>
     * The MainsVoltageDwellTripPoint attribute is 16-bits in length and specifies the
     * length of time, in seconds that the value of MainsVoltage may exist beyond either
     * of its thresholds before an alarm is generated.
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
        if(refreshPeriod > 0 && attributes.get(ATTR_MAINSVOLTAGEDWELLTRIPPOINT).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_MAINSVOLTAGEDWELLTRIPPOINT).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_MAINSVOLTAGEDWELLTRIPPOINT).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_MAINSVOLTAGEDWELLTRIPPOINT));
    }

    /**
     * Get the <i>BatteryVoltage</i> attribute [attribute ID <b>32</b>].
     * <p>
     * The BatteryVoltage attribute is 8-bits in length and specifies the current actual
     * (measured) battery voltage, in units of 100mV.
     * The value 0xff indicates an invalid or unknown reading.
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
     * Synchronously get the <i>BatteryVoltage</i> attribute [attribute ID <b>32</b>].
     * <p>
     * The BatteryVoltage attribute is 8-bits in length and specifies the current actual
     * (measured) battery voltage, in units of 100mV.
     * The value 0xff indicates an invalid or unknown reading.
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
        if(refreshPeriod > 0 && attributes.get(ATTR_BATTERYVOLTAGE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_BATTERYVOLTAGE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_BATTERYVOLTAGE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYVOLTAGE));
    }


    /**
     * Set the <i>BatteryManufacturer</i> attribute [attribute ID <b>48</b>].
     * <p>
     * The BatteryManufacturer attribute is a maximum of 16 bytes in length and
     * specifies the name of the battery manufacturer as a ZigBee character string.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param batteryManufacturer the {@link String} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryManufacturer(final Object value) {
        return write(attributes.get(ATTR_BATTERYMANUFACTURER), value);
    }

    /**
     * Get the <i>BatteryManufacturer</i> attribute [attribute ID <b>48</b>].
     * <p>
     * The BatteryManufacturer attribute is a maximum of 16 bytes in length and
     * specifies the name of the battery manufacturer as a ZigBee character string.
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
     * Synchronously get the <i>BatteryManufacturer</i> attribute [attribute ID <b>48</b>].
     * <p>
     * The BatteryManufacturer attribute is a maximum of 16 bytes in length and
     * specifies the name of the battery manufacturer as a ZigBee character string.
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
        if(refreshPeriod > 0 && attributes.get(ATTR_BATTERYMANUFACTURER).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_BATTERYMANUFACTURER).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (String) attributes.get(ATTR_BATTERYMANUFACTURER).getLastValue();
            }
        }

        return (String) readSync(attributes.get(ATTR_BATTERYMANUFACTURER));
    }


    /**
     * Set the <i>BatterySize</i> attribute [attribute ID <b>49</b>].
     * <p>
     * The BatterySize attribute is an enumeration which specifies the type of battery
     * being used by the device.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param batterySize the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatterySize(final Object value) {
        return write(attributes.get(ATTR_BATTERYSIZE), value);
    }

    /**
     * Get the <i>BatterySize</i> attribute [attribute ID <b>49</b>].
     * <p>
     * The BatterySize attribute is an enumeration which specifies the type of battery
     * being used by the device.
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
     * Synchronously get the <i>BatterySize</i> attribute [attribute ID <b>49</b>].
     * <p>
     * The BatterySize attribute is an enumeration which specifies the type of battery
     * being used by the device.
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
        if(refreshPeriod > 0 && attributes.get(ATTR_BATTERYSIZE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_BATTERYSIZE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_BATTERYSIZE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYSIZE));
    }


    /**
     * Set the <i>BatteryAHrRating</i> attribute [attribute ID <b>50</b>].
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
    public Future<CommandResult> setBatteryAHrRating(final Object value) {
        return write(attributes.get(ATTR_BATTERYAHRRATING), value);
    }

    /**
     * Get the <i>BatteryAHrRating</i> attribute [attribute ID <b>50</b>].
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
     * Synchronously get the <i>BatteryAHrRating</i> attribute [attribute ID <b>50</b>].
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
        if(refreshPeriod > 0 && attributes.get(ATTR_BATTERYAHRRATING).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_BATTERYAHRRATING).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_BATTERYAHRRATING).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYAHRRATING));
    }


    /**
     * Set the <i>BatteryQuantity</i> attribute [attribute ID <b>51</b>].
     * <p>
     * The BatteryQuantity attribute is 8-bits in length and specifies the number of
     * battery cells used to power the device.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param batteryQuantity the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryQuantity(final Object value) {
        return write(attributes.get(ATTR_BATTERYQUANTITY), value);
    }

    /**
     * Get the <i>BatteryQuantity</i> attribute [attribute ID <b>51</b>].
     * <p>
     * The BatteryQuantity attribute is 8-bits in length and specifies the number of
     * battery cells used to power the device.
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
     * Synchronously get the <i>BatteryQuantity</i> attribute [attribute ID <b>51</b>].
     * <p>
     * The BatteryQuantity attribute is 8-bits in length and specifies the number of
     * battery cells used to power the device.
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
        if(refreshPeriod > 0 && attributes.get(ATTR_BATTERYQUANTITY).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_BATTERYQUANTITY).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_BATTERYQUANTITY).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYQUANTITY));
    }


    /**
     * Set the <i>BatteryRatedVoltage</i> attribute [attribute ID <b>52</b>].
     * <p>
     * The BatteryRatedVoltage attribute is 8-bits in length and specifies the rated
     * voltage of the battery being used in the device, measured in units of 100mV.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param batteryRatedVoltage the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryRatedVoltage(final Object value) {
        return write(attributes.get(ATTR_BATTERYRATEDVOLTAGE), value);
    }

    /**
     * Get the <i>BatteryRatedVoltage</i> attribute [attribute ID <b>52</b>].
     * <p>
     * The BatteryRatedVoltage attribute is 8-bits in length and specifies the rated
     * voltage of the battery being used in the device, measured in units of 100mV.
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
     * Synchronously get the <i>BatteryRatedVoltage</i> attribute [attribute ID <b>52</b>].
     * <p>
     * The BatteryRatedVoltage attribute is 8-bits in length and specifies the rated
     * voltage of the battery being used in the device, measured in units of 100mV.
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
        if(refreshPeriod > 0 && attributes.get(ATTR_BATTERYRATEDVOLTAGE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_BATTERYRATEDVOLTAGE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_BATTERYRATEDVOLTAGE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYRATEDVOLTAGE));
    }


    /**
     * Set the <i>BatteryAlarmMask</i> attribute [attribute ID <b>53</b>].
     * <p>
     * The BatteryAlarmMask attribute is 8-bits in length and specifies which battery
     * alarms may be generated.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param batteryAlarmMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryAlarmMask(final Object value) {
        return write(attributes.get(ATTR_BATTERYALARMMASK), value);
    }

    /**
     * Get the <i>BatteryAlarmMask</i> attribute [attribute ID <b>53</b>].
     * <p>
     * The BatteryAlarmMask attribute is 8-bits in length and specifies which battery
     * alarms may be generated.
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
     * Synchronously get the <i>BatteryAlarmMask</i> attribute [attribute ID <b>53</b>].
     * <p>
     * The BatteryAlarmMask attribute is 8-bits in length and specifies which battery
     * alarms may be generated.
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
        if(refreshPeriod > 0 && attributes.get(ATTR_BATTERYALARMMASK).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_BATTERYALARMMASK).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_BATTERYALARMMASK).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYALARMMASK));
    }


    /**
     * Set the <i>BatteryVoltageMinThreshold</i> attribute [attribute ID <b>54</b>].
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
    public Future<CommandResult> setBatteryVoltageMinThreshold(final Object value) {
        return write(attributes.get(ATTR_BATTERYVOLTAGEMINTHRESHOLD), value);
    }

    /**
     * Get the <i>BatteryVoltageMinThreshold</i> attribute [attribute ID <b>54</b>].
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
     * Synchronously get the <i>BatteryVoltageMinThreshold</i> attribute [attribute ID <b>54</b>].
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
        if(refreshPeriod > 0 && attributes.get(ATTR_BATTERYVOLTAGEMINTHRESHOLD).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_BATTERYVOLTAGEMINTHRESHOLD).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_BATTERYVOLTAGEMINTHRESHOLD).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_BATTERYVOLTAGEMINTHRESHOLD));
    }
}
