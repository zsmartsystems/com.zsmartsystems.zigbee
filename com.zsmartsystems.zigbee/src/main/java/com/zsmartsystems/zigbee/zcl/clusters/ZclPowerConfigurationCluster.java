package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * <b>Power configuration</b> cluster implementation (<i>Cluster ID 0x0001</i>).
 * <p>
 * Attributes for determining detailed information about a device’s power source(s),
 * and for configuring under/over voltage alarms.
 * </p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclPowerConfigurationCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0x0001;

    // Cluster Name
    public static final String CLUSTER_NAME = "Power configuration";

    // Attribute constants
    public static final int ATTR_MAINSVOLTAGE = 0x0000;
    public static final int ATTR_MAINSFREQUENCY = 0x0001;
    public static final int ATTR_MAINSALARMMASK = 0x0010;
    public static final int ATTR_MAINSVOLTAGEMINTHRESHOLD = 0x0011;
    public static final int ATTR_MAINSVOLTAGEMAXTHRESHOLD = 0x0012;
    public static final int ATTR_MAINSVOLTAGEDWELLTRIPPOINT = 0x0013;
    public static final int ATTR_BATTERYVOLTAGE = 0x0020;
    public static final int ATTR_BATTERYMANUFACTURER = 0x0030;
    public static final int ATTR_BATTERYSIZE = 0x0031;
    public static final int ATTR_BATTERYAHRRATING = 0x0032;
    public static final int ATTR_BATTERYQUANTITY = 0x0033;
    public static final int ATTR_BATTERYRATEDVOLTAGE = 0x0034;
    public static final int ATTR_BATTERYALARMMASK = 0x0035;
    public static final int ATTR_BATTERYVOLTAGEMINTHRESHOLD = 0x0036;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>(14);

        attributeMap.put(ATTR_MAINSVOLTAGE, new ZclAttribute(0, "MainsVoltage", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MAINSFREQUENCY, new ZclAttribute(1, "MainsFrequency", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MAINSALARMMASK, new ZclAttribute(16, "MainsAlarmMask", ZclDataType.BITMAP_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_MAINSVOLTAGEMINTHRESHOLD, new ZclAttribute(17, "MainsVoltageMinThreshold", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_MAINSVOLTAGEMAXTHRESHOLD, new ZclAttribute(18, "MainsVoltageMaxThreshold", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_MAINSVOLTAGEDWELLTRIPPOINT, new ZclAttribute(19, "MainsVoltageDwellTripPoint", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYVOLTAGE, new ZclAttribute(32, "BatteryVoltage", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BATTERYMANUFACTURER, new ZclAttribute(48, "BatteryManufacturer", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_BATTERYSIZE, new ZclAttribute(49, "BatterySize", ZclDataType.ENUMERATION_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_BATTERYAHRRATING, new ZclAttribute(50, "BatteryAHrRating", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYQUANTITY, new ZclAttribute(51, "BatteryQuantity", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYRATEDVOLTAGE, new ZclAttribute(52, "BatteryRatedVoltage", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BATTERYALARMMASK, new ZclAttribute(53, "BatteryAlarmMask", ZclDataType.BITMAP_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_BATTERYVOLTAGEMINTHRESHOLD, new ZclAttribute(54, "BatteryVoltageMinThreshold", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));

        return attributeMap;
    }

    /**
     * Default constructor.
     */
    public ZclPowerConfigurationCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {
        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * <p>
     * Get the <i>MainsVoltage</i> attribute.
     * <p>
     * <p>
     * The MainsVoltage attribute is 16-bits in length and specifies the actual (measured)
     * RMS voltage (or DC voltage in the case of a DC supply) currently applied to the
     * device, measured in units of 100mV.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMainsVoltageAsync() {
        return read(attributes.get(ATTR_MAINSVOLTAGE));
    }


    /**
     * <p>
     * Synchronously get the <i>MainsVoltage</i> attribute.
     * <p>
     * <p>
     * The MainsVoltage attribute is 16-bits in length and specifies the actual (measured)
     * RMS voltage (or DC voltage in the case of a DC supply) currently applied to the
     * device, measured in units of 100mV.
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMainsVoltage() {
        return (Integer) readSync(attributes.get(ATTR_MAINSVOLTAGE));
    }

    /**
     * <p>
     * Get the <i>MainsFrequency</i> attribute.
     * <p>
     * <p>
     * <br>
     * The MainsFrequency attribute is 8-bits in length and represents the frequency, in
     * Hertz, of the mains as determined by the device as follows:-
     * <br>
     * MainsFrequency = 0.5 x measured frequency
     * <br>
     * Where 2 Hz <= measured frequency <= 506 Hz, corresponding to a
     * <br>
     * MainsFrequency in the range 1 to 0xfd.
     * <br>
     * The maximum resolution this format allows is 2 Hz.
     * The following special values of MainsFrequency apply.
     * <li>0x00 indicates a frequency that is too low to be measured.</li>
     * <li>0xfe indicates a frequency that is too high to be measured.</li>
     * <li>0xff indicates that the frequency could not be measured.</li>
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMainsFrequencyAsync() {
        return read(attributes.get(ATTR_MAINSFREQUENCY));
    }


    /**
     * <p>
     * Synchronously get the <i>MainsFrequency</i> attribute.
     * <p>
     * <p>
     * <br>
     * The MainsFrequency attribute is 8-bits in length and represents the frequency, in
     * Hertz, of the mains as determined by the device as follows:-
     * <br>
     * MainsFrequency = 0.5 x measured frequency
     * <br>
     * Where 2 Hz <= measured frequency <= 506 Hz, corresponding to a
     * <br>
     * MainsFrequency in the range 1 to 0xfd.
     * <br>
     * The maximum resolution this format allows is 2 Hz.
     * The following special values of MainsFrequency apply.
     * <li>0x00 indicates a frequency that is too low to be measured.</li>
     * <li>0xfe indicates a frequency that is too high to be measured.</li>
     * <li>0xff indicates that the frequency could not be measured.</li>
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMainsFrequency() {
        return (Integer) readSync(attributes.get(ATTR_MAINSFREQUENCY));
    }


    /**
     * <p>
     * Set the <i>MainsAlarmMask</i> attribute.
     * <p>
     * <p>
     * <br>
     * The MainsAlarmMask attribute is 8-bits in length and specifies which mains
     * alarms may be generated. A ‘1’ in each bit position enables the alarm.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @param mainsAlarmMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMainsAlarmMask(final Object value) {
        return write(attributes.get(ATTR_MAINSALARMMASK), value);
    }

    /**
     * <p>
     * Get the <i>MainsAlarmMask</i> attribute.
     * <p>
     * <p>
     * <br>
     * The MainsAlarmMask attribute is 8-bits in length and specifies which mains
     * alarms may be generated. A ‘1’ in each bit position enables the alarm.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMainsAlarmMaskAsync() {
        return read(attributes.get(ATTR_MAINSALARMMASK));
    }


    /**
     * <p>
     * Synchronously get the <i>MainsAlarmMask</i> attribute.
     * <p>
     * <p>
     * <br>
     * The MainsAlarmMask attribute is 8-bits in length and specifies which mains
     * alarms may be generated. A ‘1’ in each bit position enables the alarm.
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMainsAlarmMask() {
        return (Integer) readSync(attributes.get(ATTR_MAINSALARMMASK));
    }


    /**
     * <p>
     * Set the <i>MainsVoltageMinThreshold</i> attribute.
     * <p>
     * <p>
     * <br>
     * The MainsVoltageMinThreshold attribute is 16-bits in length and specifies the
     * lower alarm threshold, measured in units of 100mV, for the MainsVoltage
     * attribute. The value of this attribute shall be less than MainsVoltageMaxThreshold.
     * <br>
     * If the value of MainsVoltage drops below the threshold specified by
     * MainsVoltageMinThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute increases to
     * greater than or equal to MainsVoltageMinThreshold before the timer expires, the
     * device shall stop and reset the timer. If the timer expires, an alarm shall be
     * generated.
     * <br>
     * The Alarm Code field (see 3.11.2.3.1) included in the generated alarm shall be
     * 0x00.
     * <br>
     * If this attribute takes the value 0xffff then this alarm shall not be generated.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @param mainsVoltageMinThreshold the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMainsVoltageMinThreshold(final Object value) {
        return write(attributes.get(ATTR_MAINSVOLTAGEMINTHRESHOLD), value);
    }

    /**
     * <p>
     * Get the <i>MainsVoltageMinThreshold</i> attribute.
     * <p>
     * <p>
     * <br>
     * The MainsVoltageMinThreshold attribute is 16-bits in length and specifies the
     * lower alarm threshold, measured in units of 100mV, for the MainsVoltage
     * attribute. The value of this attribute shall be less than MainsVoltageMaxThreshold.
     * <br>
     * If the value of MainsVoltage drops below the threshold specified by
     * MainsVoltageMinThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute increases to
     * greater than or equal to MainsVoltageMinThreshold before the timer expires, the
     * device shall stop and reset the timer. If the timer expires, an alarm shall be
     * generated.
     * <br>
     * The Alarm Code field (see 3.11.2.3.1) included in the generated alarm shall be
     * 0x00.
     * <br>
     * If this attribute takes the value 0xffff then this alarm shall not be generated.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMainsVoltageMinThresholdAsync() {
        return read(attributes.get(ATTR_MAINSVOLTAGEMINTHRESHOLD));
    }


    /**
     * <p>
     * Synchronously get the <i>MainsVoltageMinThreshold</i> attribute.
     * <p>
     * <p>
     * <br>
     * The MainsVoltageMinThreshold attribute is 16-bits in length and specifies the
     * lower alarm threshold, measured in units of 100mV, for the MainsVoltage
     * attribute. The value of this attribute shall be less than MainsVoltageMaxThreshold.
     * <br>
     * If the value of MainsVoltage drops below the threshold specified by
     * MainsVoltageMinThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute increases to
     * greater than or equal to MainsVoltageMinThreshold before the timer expires, the
     * device shall stop and reset the timer. If the timer expires, an alarm shall be
     * generated.
     * <br>
     * The Alarm Code field (see 3.11.2.3.1) included in the generated alarm shall be
     * 0x00.
     * <br>
     * If this attribute takes the value 0xffff then this alarm shall not be generated.
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMainsVoltageMinThreshold() {
        return (Integer) readSync(attributes.get(ATTR_MAINSVOLTAGEMINTHRESHOLD));
    }


    /**
     * <p>
     * Set the <i>MainsVoltageMaxThreshold</i> attribute.
     * <p>
     * <p>
     * <br>
     * The MainsVoltageMaxThreshold attribute is 16-bits in length and specifies the
     * upper alarm threshold, measured in units of 100mV, for the MainsVoltage
     * attribute. The value of this attribute shall be greater than
     * MainsVoltageMinThreshold.
     * <br>
     * If the value of MainsVoltage rises above the threshold specified by
     * MainsVoltageMaxThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute drops to lower
     * than or equal to MainsVoltageMaxThreshold before the timer expires, the device
     * shall stop and reset the timer. If the timer expires, an alarm shall be generated.
     * <br>
     * The Alarm Code field (see 3.11.2.3.1) included in the generated alarm shall be
     * 0x01.
     * <br>
     * If this attribute takes the value 0xffff then this alarm shall not be generated.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @param mainsVoltageMaxThreshold the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMainsVoltageMaxThreshold(final Object value) {
        return write(attributes.get(ATTR_MAINSVOLTAGEMAXTHRESHOLD), value);
    }

    /**
     * <p>
     * Get the <i>MainsVoltageMaxThreshold</i> attribute.
     * <p>
     * <p>
     * <br>
     * The MainsVoltageMaxThreshold attribute is 16-bits in length and specifies the
     * upper alarm threshold, measured in units of 100mV, for the MainsVoltage
     * attribute. The value of this attribute shall be greater than
     * MainsVoltageMinThreshold.
     * <br>
     * If the value of MainsVoltage rises above the threshold specified by
     * MainsVoltageMaxThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute drops to lower
     * than or equal to MainsVoltageMaxThreshold before the timer expires, the device
     * shall stop and reset the timer. If the timer expires, an alarm shall be generated.
     * <br>
     * The Alarm Code field (see 3.11.2.3.1) included in the generated alarm shall be
     * 0x01.
     * <br>
     * If this attribute takes the value 0xffff then this alarm shall not be generated.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMainsVoltageMaxThresholdAsync() {
        return read(attributes.get(ATTR_MAINSVOLTAGEMAXTHRESHOLD));
    }


    /**
     * <p>
     * Synchronously get the <i>MainsVoltageMaxThreshold</i> attribute.
     * <p>
     * <p>
     * <br>
     * The MainsVoltageMaxThreshold attribute is 16-bits in length and specifies the
     * upper alarm threshold, measured in units of 100mV, for the MainsVoltage
     * attribute. The value of this attribute shall be greater than
     * MainsVoltageMinThreshold.
     * <br>
     * If the value of MainsVoltage rises above the threshold specified by
     * MainsVoltageMaxThreshold, the device shall start a timer to expire after
     * MainsVoltageDwellTripPoint seconds. If the value of this attribute drops to lower
     * than or equal to MainsVoltageMaxThreshold before the timer expires, the device
     * shall stop and reset the timer. If the timer expires, an alarm shall be generated.
     * <br>
     * The Alarm Code field (see 3.11.2.3.1) included in the generated alarm shall be
     * 0x01.
     * <br>
     * If this attribute takes the value 0xffff then this alarm shall not be generated.
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMainsVoltageMaxThreshold() {
        return (Integer) readSync(attributes.get(ATTR_MAINSVOLTAGEMAXTHRESHOLD));
    }


    /**
     * <p>
     * Set the <i>MainsVoltageDwellTripPoint</i> attribute.
     * <p>
     * <p>
     * <br>
     * The MainsVoltageDwellTripPoint attribute is 16-bits in length and specifies the
     * length of time, in seconds that the value of MainsVoltage may exist beyond either
     * of its thresholds before an alarm is generated.
     * <br>
     * If this attribute takes the value 0xffff then the associated alarms shall not be
     * generated.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @param mainsVoltageDwellTripPoint the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMainsVoltageDwellTripPoint(final Object value) {
        return write(attributes.get(ATTR_MAINSVOLTAGEDWELLTRIPPOINT), value);
    }

    /**
     * <p>
     * Get the <i>MainsVoltageDwellTripPoint</i> attribute.
     * <p>
     * <p>
     * <br>
     * The MainsVoltageDwellTripPoint attribute is 16-bits in length and specifies the
     * length of time, in seconds that the value of MainsVoltage may exist beyond either
     * of its thresholds before an alarm is generated.
     * <br>
     * If this attribute takes the value 0xffff then the associated alarms shall not be
     * generated.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMainsVoltageDwellTripPointAsync() {
        return read(attributes.get(ATTR_MAINSVOLTAGEDWELLTRIPPOINT));
    }


    /**
     * <p>
     * Synchronously get the <i>MainsVoltageDwellTripPoint</i> attribute.
     * <p>
     * <p>
     * <br>
     * The MainsVoltageDwellTripPoint attribute is 16-bits in length and specifies the
     * length of time, in seconds that the value of MainsVoltage may exist beyond either
     * of its thresholds before an alarm is generated.
     * <br>
     * If this attribute takes the value 0xffff then the associated alarms shall not be
     * generated.
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMainsVoltageDwellTripPoint() {
        return (Integer) readSync(attributes.get(ATTR_MAINSVOLTAGEDWELLTRIPPOINT));
    }

    /**
     * <p>
     * Get the <i>BatteryVoltage</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryVoltage attribute is 8-bits in length and specifies the current actual
     * (measured) battery voltage, in units of 100mV.
     * The value 0xff indicates an invalid or unknown reading.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryVoltageAsync() {
        return read(attributes.get(ATTR_BATTERYVOLTAGE));
    }


    /**
     * <p>
     * Synchronously get the <i>BatteryVoltage</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryVoltage attribute is 8-bits in length and specifies the current actual
     * (measured) battery voltage, in units of 100mV.
     * The value 0xff indicates an invalid or unknown reading.
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getBatteryVoltage() {
        return (Integer) readSync(attributes.get(ATTR_BATTERYVOLTAGE));
    }


    /**
     * <p>
     * Set the <i>BatteryManufacturer</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryManufacturer attribute is a maximum of 16 bytes in length and
     * specifies the name of the battery manufacturer as a ZigBee character string.
     * </p>
     * <p>
     * The attribute is of type {@link String}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @param batteryManufacturer the {@link String} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryManufacturer(final Object value) {
        return write(attributes.get(ATTR_BATTERYMANUFACTURER), value);
    }

    /**
     * <p>
     * Get the <i>BatteryManufacturer</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryManufacturer attribute is a maximum of 16 bytes in length and
     * specifies the name of the battery manufacturer as a ZigBee character string.
     * </p>
     * <p>
     * The attribute is of type {@link String}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryManufacturerAsync() {
        return read(attributes.get(ATTR_BATTERYMANUFACTURER));
    }


    /**
     * <p>
     * Synchronously get the <i>BatteryManufacturer</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryManufacturer attribute is a maximum of 16 bytes in length and
     * specifies the name of the battery manufacturer as a ZigBee character string.
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link String}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link String} attribute value, or null on error
     */
    public String getBatteryManufacturer() {
        return (String) readSync(attributes.get(ATTR_BATTERYMANUFACTURER));
    }


    /**
     * <p>
     * Set the <i>BatterySize</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatterySize attribute is an enumeration which specifies the type of battery
     * being used by the device.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @param batterySize the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatterySize(final Object value) {
        return write(attributes.get(ATTR_BATTERYSIZE), value);
    }

    /**
     * <p>
     * Get the <i>BatterySize</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatterySize attribute is an enumeration which specifies the type of battery
     * being used by the device.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatterySizeAsync() {
        return read(attributes.get(ATTR_BATTERYSIZE));
    }


    /**
     * <p>
     * Synchronously get the <i>BatterySize</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatterySize attribute is an enumeration which specifies the type of battery
     * being used by the device.
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getBatterySize() {
        return (Integer) readSync(attributes.get(ATTR_BATTERYSIZE));
    }


    /**
     * <p>
     * Set the <i>BatteryAHrRating</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryAHrRating attribute is 16-bits in length and specifies the Ampere-hour
     * rating of the battery, measured in units of 10mAHr.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @param batteryAHrRating the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryAHrRating(final Object value) {
        return write(attributes.get(ATTR_BATTERYAHRRATING), value);
    }

    /**
     * <p>
     * Get the <i>BatteryAHrRating</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryAHrRating attribute is 16-bits in length and specifies the Ampere-hour
     * rating of the battery, measured in units of 10mAHr.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryAHrRatingAsync() {
        return read(attributes.get(ATTR_BATTERYAHRRATING));
    }


    /**
     * <p>
     * Synchronously get the <i>BatteryAHrRating</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryAHrRating attribute is 16-bits in length and specifies the Ampere-hour
     * rating of the battery, measured in units of 10mAHr.
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getBatteryAHrRating() {
        return (Integer) readSync(attributes.get(ATTR_BATTERYAHRRATING));
    }


    /**
     * <p>
     * Set the <i>BatteryQuantity</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryQuantity attribute is 8-bits in length and specifies the number of
     * battery cells used to power the device.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @param batteryQuantity the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryQuantity(final Object value) {
        return write(attributes.get(ATTR_BATTERYQUANTITY), value);
    }

    /**
     * <p>
     * Get the <i>BatteryQuantity</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryQuantity attribute is 8-bits in length and specifies the number of
     * battery cells used to power the device.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryQuantityAsync() {
        return read(attributes.get(ATTR_BATTERYQUANTITY));
    }


    /**
     * <p>
     * Synchronously get the <i>BatteryQuantity</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryQuantity attribute is 8-bits in length and specifies the number of
     * battery cells used to power the device.
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getBatteryQuantity() {
        return (Integer) readSync(attributes.get(ATTR_BATTERYQUANTITY));
    }


    /**
     * <p>
     * Set the <i>BatteryRatedVoltage</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryRatedVoltage attribute is 8-bits in length and specifies the rated
     * voltage of the battery being used in the device, measured in units of 100mV.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @param batteryRatedVoltage the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryRatedVoltage(final Object value) {
        return write(attributes.get(ATTR_BATTERYRATEDVOLTAGE), value);
    }

    /**
     * <p>
     * Get the <i>BatteryRatedVoltage</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryRatedVoltage attribute is 8-bits in length and specifies the rated
     * voltage of the battery being used in the device, measured in units of 100mV.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryRatedVoltageAsync() {
        return read(attributes.get(ATTR_BATTERYRATEDVOLTAGE));
    }


    /**
     * <p>
     * Synchronously get the <i>BatteryRatedVoltage</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryRatedVoltage attribute is 8-bits in length and specifies the rated
     * voltage of the battery being used in the device, measured in units of 100mV.
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getBatteryRatedVoltage() {
        return (Integer) readSync(attributes.get(ATTR_BATTERYRATEDVOLTAGE));
    }


    /**
     * <p>
     * Set the <i>BatteryAlarmMask</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryAlarmMask attribute is 8-bits in length and specifies which battery
     * alarms may be generated.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @param batteryAlarmMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryAlarmMask(final Object value) {
        return write(attributes.get(ATTR_BATTERYALARMMASK), value);
    }

    /**
     * <p>
     * Get the <i>BatteryAlarmMask</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryAlarmMask attribute is 8-bits in length and specifies which battery
     * alarms may be generated.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryAlarmMaskAsync() {
        return read(attributes.get(ATTR_BATTERYALARMMASK));
    }


    /**
     * <p>
     * Synchronously get the <i>BatteryAlarmMask</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryAlarmMask attribute is 8-bits in length and specifies which battery
     * alarms may be generated.
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getBatteryAlarmMask() {
        return (Integer) readSync(attributes.get(ATTR_BATTERYALARMMASK));
    }


    /**
     * <p>
     * Set the <i>BatteryVoltageMinThreshold</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryVoltageMinThreshold attribute is 8-bits in length and specifies the low
     * voltage alarm threshold, measured in units of 100mV, for the BatteryVoltage
     * attribute.
     * <br>
     * If the value of BatteryVoltage drops below the threshold specified by
     * BatteryVoltageMinThreshold an alarm shall be generated.
     * <br>
     * The Alarm Code field included in the generated alarm shall be 0x10.
     * <br>
     * If this attribute takes the value 0xff then this alarm shall not be generated.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @param batteryVoltageMinThreshold the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBatteryVoltageMinThreshold(final Object value) {
        return write(attributes.get(ATTR_BATTERYVOLTAGEMINTHRESHOLD), value);
    }

    /**
     * <p>
     * Get the <i>BatteryVoltageMinThreshold</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryVoltageMinThreshold attribute is 8-bits in length and specifies the low
     * voltage alarm threshold, measured in units of 100mV, for the BatteryVoltage
     * attribute.
     * <br>
     * If the value of BatteryVoltage drops below the threshold specified by
     * BatteryVoltageMinThreshold an alarm shall be generated.
     * <br>
     * The Alarm Code field included in the generated alarm shall be 0x10.
     * <br>
     * If this attribute takes the value 0xff then this alarm shall not be generated.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBatteryVoltageMinThresholdAsync() {
        return read(attributes.get(ATTR_BATTERYVOLTAGEMINTHRESHOLD));
    }


    /**
     * <p>
     * Synchronously get the <i>BatteryVoltageMinThreshold</i> attribute.
     * <p>
     * <p>
     * <br>
     * The BatteryVoltageMinThreshold attribute is 8-bits in length and specifies the low
     * voltage alarm threshold, measured in units of 100mV, for the BatteryVoltage
     * attribute.
     * <br>
     * If the value of BatteryVoltage drops below the threshold specified by
     * BatteryVoltageMinThreshold an alarm shall be generated.
     * <br>
     * The Alarm Code field included in the generated alarm shall be 0x10.
     * <br>
     * If this attribute takes the value 0xff then this alarm shall not be generated.
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     * </p>
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getBatteryVoltageMinThreshold() {
        return (Integer) readSync(attributes.get(ATTR_BATTERYVOLTAGEMINTHRESHOLD));
    }

    /**
     * Add a binding for this cluster to the local node
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> bind() {
        return bind();
    }
}
