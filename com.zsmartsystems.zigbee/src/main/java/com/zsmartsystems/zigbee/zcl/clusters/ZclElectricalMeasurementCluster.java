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
 * <b>Electrical Measurement</b> cluster implementation (<i>Cluster ID 0x0B04</i>).
 * <p>
 * This cluster provides a mechanism for querying data about the electrical properties as measured
 * by the device. This cluster may be implemented on any device type and be implemented on a per-endpoint
 * basis. For example, a power  strip device could represent each outlet on a  different endpoint and
 * report electrical  information for each individual outlet. The only caveat is that if you implement
 * an attribute that has an associated multiplier and divisor, then you must implement the associated
 * multiplier and divisor attributes. For example if you implement DCVoltage, you must also implement
 * DCVoltageMultiplier and DCVoltageDivisor.
 * <p>
 * If you are interested in reading information about the power supply or battery level on the device,
 * please see the Power Configuration cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclElectricalMeasurementCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0B04;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Electrical Measurement";

    // Attribute constants
    /**
     * This attribute indicates a device’s measurement capabilities. This will be indicated by setting
     * the desire measurement bits to 1.
     */
    public static final int ATTR_MEASUREMENTTYPE = 0x0000;
    /**
     * The ACFrequency attribute represents the most recent AC Frequency reading in Hertz (Hz).
     * If the frequency cannot be measured, a value of 0xFFFF is returned.
     */
    public static final int ATTR_ACFREQUENCY = 0x0300;
    /**
     * Active power represents the current demand of active power delivered or received at the
     * premises, in kW. Positive values indicate power delivered to the premises where negative
     * values indicate power received from the premises. In case if device is capable of measuring
     * multi elements or phases then this will be net active power value.
     */
    public static final int ATTR_TOTALACTIVEPOWER = 0x0304;
    /**
     * Reactive power represents the  current demand of reactive power delivered or
     * received at the premises, in kVAr. Positive values indicate power delivered to
     * the premises where negative values indicate power received from the premises. In
     * case if device is capable of measuring multi elements or phases then this will be net reactive
     * power value.
     */
    public static final int ATTR_TOTALREACTIVEPOWER = 0x0305;
    /**
     * Represents the current demand of apparent power, in kVA. In case if device is capable of
     * measuring multi elements or phases then this will be net apparent power value.
     */
    public static final int ATTR_TOTALAPPARENTPOWER = 0x0306;
    /**
     * Represents the  most recent RMS voltage reading in Volts (V). If the RMS voltage cannot be
     * measured, a value of 0xFFFF is returned.
     */
    public static final int ATTR_RMSVOLTAGE = 0x0505;
    /**
     * Represents the most recent RMS current reading in Amps (A). If the power cannot be measured,
     * a value of 0xFFFF is returned.
     */
    public static final int ATTR_RMSCURRENT = 0x0508;
    /**
     * Represents the single phase or Phase A, current demand of active power delivered or received at
     * the premises, in Watts (W). Positive values indicate power delivered to the premises where negative
     * values indicate power received from the premises.
     */
    public static final int ATTR_ACTIVEPOWER = 0x050B;
    /**
     * Provides a value to be multiplied against the InstantaneousCurrent and RMSCurrentattributes.
     * his attribute must be used in conjunction with the ACCurrentDivisorattribute. 0x0000 is an invalid value for this attribute.
     */
    public static final int ATTR_ACCURRENTMULTIPLIER = 0x0602;
    /**
     * Provides  a  value  to  be  divided  against the ACCurrent, InstantaneousCurrent and
     * RMSCurrentattributes. This attribute must be used in conjunction with the ACCurrentMultiplierattribute
     * 0x0000 is an invalid value for this attribute.
     */
    public static final int ATTR_ACCURRENTDIVISOR = 0x0603;
    /**
     * Provides a value to be multiplied against the InstantaneousPower and ActivePowerattributes.
     * This attribute must be used in conjunction with the ACPowerDivisorattribute. 0x0000 is an invalid
     * value for this attribute
     */
    public static final int ATTR_ACPOWERMULTIPLIER = 0x0604;
    /**
     * Provides a value to be divided against the InstantaneousPower and ActivePowerattributes.
     * This  attribute must be used in conjunction with the ACPowerMultiplierattribute. 0x0000 is an
     * invalid value for this attribute.
     */
    public static final int ATTR_ACPOWERDIVISOR = 0x0605;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(12);

        attributeMap.put(ATTR_MEASUREMENTTYPE, new ZclAttribute(ZclClusterType.ELECTRICAL_MEASUREMENT, ATTR_MEASUREMENTTYPE, "MeasurementType", ZclDataType.BITMAP_32_BIT, true, true, false, false));
        attributeMap.put(ATTR_ACFREQUENCY, new ZclAttribute(ZclClusterType.ELECTRICAL_MEASUREMENT, ATTR_ACFREQUENCY, "ACFrequency", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TOTALACTIVEPOWER, new ZclAttribute(ZclClusterType.ELECTRICAL_MEASUREMENT, ATTR_TOTALACTIVEPOWER, "TotalActivePower", ZclDataType.SIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TOTALREACTIVEPOWER, new ZclAttribute(ZclClusterType.ELECTRICAL_MEASUREMENT, ATTR_TOTALREACTIVEPOWER, "TotalReactivePower", ZclDataType.SIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TOTALAPPARENTPOWER, new ZclAttribute(ZclClusterType.ELECTRICAL_MEASUREMENT, ATTR_TOTALAPPARENTPOWER, "TotalApparentPower", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RMSVOLTAGE, new ZclAttribute(ZclClusterType.ELECTRICAL_MEASUREMENT, ATTR_RMSVOLTAGE, "RMSVoltage", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RMSCURRENT, new ZclAttribute(ZclClusterType.ELECTRICAL_MEASUREMENT, ATTR_RMSCURRENT, "RMSCurrent", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ACTIVEPOWER, new ZclAttribute(ZclClusterType.ELECTRICAL_MEASUREMENT, ATTR_ACTIVEPOWER, "ActivePower", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ACCURRENTMULTIPLIER, new ZclAttribute(ZclClusterType.ELECTRICAL_MEASUREMENT, ATTR_ACCURRENTMULTIPLIER, "ACCurrentMultiplier", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ACCURRENTDIVISOR, new ZclAttribute(ZclClusterType.ELECTRICAL_MEASUREMENT, ATTR_ACCURRENTDIVISOR, "ACCurrentDivisor", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ACPOWERMULTIPLIER, new ZclAttribute(ZclClusterType.ELECTRICAL_MEASUREMENT, ATTR_ACPOWERMULTIPLIER, "ACPowerMultiplier", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ACPOWERDIVISOR, new ZclAttribute(ZclClusterType.ELECTRICAL_MEASUREMENT, ATTR_ACPOWERDIVISOR, "ACPowerDivisor", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Electrical Measurement cluster.
     *
     * @param zigbeeManager {@link ZigBeeNetworkManager}
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint}
     */
    public ZclElectricalMeasurementCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeManager, zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * Get the <i>MeasurementType</i> attribute [attribute ID <b>0</b>].
     * <p>
     * This attribute indicates a device’s measurement capabilities. This will be indicated by setting
     * the desire measurement bits to 1.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMeasurementTypeAsync() {
        return read(attributes.get(ATTR_MEASUREMENTTYPE));
    }


    /**
     * Synchronously get the <i>MeasurementType</i> attribute [attribute ID <b>0</b>].
     * <p>
     * This attribute indicates a device’s measurement capabilities. This will be indicated by setting
     * the desire measurement bits to 1.
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
     */
    public Integer getMeasurementType(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_MEASUREMENTTYPE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_MEASUREMENTTYPE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_MEASUREMENTTYPE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_MEASUREMENTTYPE));
    }

    /**
     * Get the <i>ACFrequency</i> attribute [attribute ID <b>768</b>].
     * <p>
     * The ACFrequency attribute represents the most recent AC Frequency reading in Hertz (Hz).
     * If the frequency cannot be measured, a value of 0xFFFF is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getAcFrequencyAsync() {
        return read(attributes.get(ATTR_ACFREQUENCY));
    }


    /**
     * Synchronously get the <i>ACFrequency</i> attribute [attribute ID <b>768</b>].
     * <p>
     * The ACFrequency attribute represents the most recent AC Frequency reading in Hertz (Hz).
     * If the frequency cannot be measured, a value of 0xFFFF is returned.
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
    public Integer getAcFrequency(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_ACFREQUENCY).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_ACFREQUENCY).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_ACFREQUENCY).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_ACFREQUENCY));
    }

    /**
     * Get the <i>TotalActivePower</i> attribute [attribute ID <b>772</b>].
     * <p>
     * Active power represents the current demand of active power delivered or received at the
     * premises, in kW. Positive values indicate power delivered to the premises where negative
     * values indicate power received from the premises. In case if device is capable of measuring
     * multi elements or phases then this will be net active power value.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTotalActivePowerAsync() {
        return read(attributes.get(ATTR_TOTALACTIVEPOWER));
    }


    /**
     * Synchronously get the <i>TotalActivePower</i> attribute [attribute ID <b>772</b>].
     * <p>
     * Active power represents the current demand of active power delivered or received at the
     * premises, in kW. Positive values indicate power delivered to the premises where negative
     * values indicate power received from the premises. In case if device is capable of measuring
     * multi elements or phases then this will be net active power value.
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
    public Integer getTotalActivePower(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_TOTALACTIVEPOWER).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_TOTALACTIVEPOWER).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_TOTALACTIVEPOWER).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_TOTALACTIVEPOWER));
    }

    /**
     * Get the <i>TotalReactivePower</i> attribute [attribute ID <b>773</b>].
     * <p>
     * Reactive power represents the  current demand of reactive power delivered or
     * received at the premises, in kVAr. Positive values indicate power delivered to
     * the premises where negative values indicate power received from the premises. In
     * case if device is capable of measuring multi elements or phases then this will be net reactive
     * power value.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTotalReactivePowerAsync() {
        return read(attributes.get(ATTR_TOTALREACTIVEPOWER));
    }


    /**
     * Synchronously get the <i>TotalReactivePower</i> attribute [attribute ID <b>773</b>].
     * <p>
     * Reactive power represents the  current demand of reactive power delivered or
     * received at the premises, in kVAr. Positive values indicate power delivered to
     * the premises where negative values indicate power received from the premises. In
     * case if device is capable of measuring multi elements or phases then this will be net reactive
     * power value.
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
    public Integer getTotalReactivePower(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_TOTALREACTIVEPOWER).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_TOTALREACTIVEPOWER).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_TOTALREACTIVEPOWER).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_TOTALREACTIVEPOWER));
    }

    /**
     * Get the <i>TotalApparentPower</i> attribute [attribute ID <b>774</b>].
     * <p>
     * Represents the current demand of apparent power, in kVA. In case if device is capable of
     * measuring multi elements or phases then this will be net apparent power value.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTotalApparentPowerAsync() {
        return read(attributes.get(ATTR_TOTALAPPARENTPOWER));
    }


    /**
     * Synchronously get the <i>TotalApparentPower</i> attribute [attribute ID <b>774</b>].
     * <p>
     * Represents the current demand of apparent power, in kVA. In case if device is capable of
     * measuring multi elements or phases then this will be net apparent power value.
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
    public Integer getTotalApparentPower(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_TOTALAPPARENTPOWER).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_TOTALAPPARENTPOWER).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_TOTALAPPARENTPOWER).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_TOTALAPPARENTPOWER));
    }

    /**
     * Get the <i>RMSVoltage</i> attribute [attribute ID <b>1285</b>].
     * <p>
     * Represents the  most recent RMS voltage reading in Volts (V). If the RMS voltage cannot be
     * measured, a value of 0xFFFF is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRmsVoltageAsync() {
        return read(attributes.get(ATTR_RMSVOLTAGE));
    }


    /**
     * Synchronously get the <i>RMSVoltage</i> attribute [attribute ID <b>1285</b>].
     * <p>
     * Represents the  most recent RMS voltage reading in Volts (V). If the RMS voltage cannot be
     * measured, a value of 0xFFFF is returned.
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
    public Integer getRmsVoltage(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_RMSVOLTAGE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_RMSVOLTAGE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_RMSVOLTAGE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_RMSVOLTAGE));
    }

    /**
     * Get the <i>RMSCurrent</i> attribute [attribute ID <b>1288</b>].
     * <p>
     * Represents the most recent RMS current reading in Amps (A). If the power cannot be measured,
     * a value of 0xFFFF is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRmsCurrentAsync() {
        return read(attributes.get(ATTR_RMSCURRENT));
    }


    /**
     * Synchronously get the <i>RMSCurrent</i> attribute [attribute ID <b>1288</b>].
     * <p>
     * Represents the most recent RMS current reading in Amps (A). If the power cannot be measured,
     * a value of 0xFFFF is returned.
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
    public Integer getRmsCurrent(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_RMSCURRENT).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_RMSCURRENT).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_RMSCURRENT).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_RMSCURRENT));
    }

    /**
     * Get the <i>ActivePower</i> attribute [attribute ID <b>1291</b>].
     * <p>
     * Represents the single phase or Phase A, current demand of active power delivered or received at
     * the premises, in Watts (W). Positive values indicate power delivered to the premises where negative
     * values indicate power received from the premises.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getActivePowerAsync() {
        return read(attributes.get(ATTR_ACTIVEPOWER));
    }


    /**
     * Synchronously get the <i>ActivePower</i> attribute [attribute ID <b>1291</b>].
     * <p>
     * Represents the single phase or Phase A, current demand of active power delivered or received at
     * the premises, in Watts (W). Positive values indicate power delivered to the premises where negative
     * values indicate power received from the premises.
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
    public Integer getActivePower(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_ACTIVEPOWER).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_ACTIVEPOWER).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_ACTIVEPOWER).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_ACTIVEPOWER));
    }

    /**
     * Get the <i>ACCurrentMultiplier</i> attribute [attribute ID <b>1538</b>].
     * <p>
     * Provides a value to be multiplied against the InstantaneousCurrent and RMSCurrentattributes.
     * his attribute must be used in conjunction with the ACCurrentDivisorattribute. 0x0000 is an invalid value for this attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getAcCurrentMultiplierAsync() {
        return read(attributes.get(ATTR_ACCURRENTMULTIPLIER));
    }


    /**
     * Synchronously get the <i>ACCurrentMultiplier</i> attribute [attribute ID <b>1538</b>].
     * <p>
     * Provides a value to be multiplied against the InstantaneousCurrent and RMSCurrentattributes.
     * his attribute must be used in conjunction with the ACCurrentDivisorattribute. 0x0000 is an invalid value for this attribute.
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
    public Integer getAcCurrentMultiplier(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_ACCURRENTMULTIPLIER).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_ACCURRENTMULTIPLIER).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_ACCURRENTMULTIPLIER).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_ACCURRENTMULTIPLIER));
    }

    /**
     * Get the <i>ACCurrentDivisor</i> attribute [attribute ID <b>1539</b>].
     * <p>
     * Provides  a  value  to  be  divided  against the ACCurrent, InstantaneousCurrent and
     * RMSCurrentattributes. This attribute must be used in conjunction with the ACCurrentMultiplierattribute
     * 0x0000 is an invalid value for this attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getAcCurrentDivisorAsync() {
        return read(attributes.get(ATTR_ACCURRENTDIVISOR));
    }


    /**
     * Synchronously get the <i>ACCurrentDivisor</i> attribute [attribute ID <b>1539</b>].
     * <p>
     * Provides  a  value  to  be  divided  against the ACCurrent, InstantaneousCurrent and
     * RMSCurrentattributes. This attribute must be used in conjunction with the ACCurrentMultiplierattribute
     * 0x0000 is an invalid value for this attribute.
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
    public Integer getAcCurrentDivisor(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_ACCURRENTDIVISOR).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_ACCURRENTDIVISOR).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_ACCURRENTDIVISOR).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_ACCURRENTDIVISOR));
    }

    /**
     * Get the <i>ACPowerMultiplier</i> attribute [attribute ID <b>1540</b>].
     * <p>
     * Provides a value to be multiplied against the InstantaneousPower and ActivePowerattributes.
     * This attribute must be used in conjunction with the ACPowerDivisorattribute. 0x0000 is an invalid
     * value for this attribute
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getAcPowerMultiplierAsync() {
        return read(attributes.get(ATTR_ACPOWERMULTIPLIER));
    }


    /**
     * Synchronously get the <i>ACPowerMultiplier</i> attribute [attribute ID <b>1540</b>].
     * <p>
     * Provides a value to be multiplied against the InstantaneousPower and ActivePowerattributes.
     * This attribute must be used in conjunction with the ACPowerDivisorattribute. 0x0000 is an invalid
     * value for this attribute
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
    public Integer getAcPowerMultiplier(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_ACPOWERMULTIPLIER).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_ACPOWERMULTIPLIER).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_ACPOWERMULTIPLIER).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_ACPOWERMULTIPLIER));
    }

    /**
     * Get the <i>ACPowerDivisor</i> attribute [attribute ID <b>1541</b>].
     * <p>
     * Provides a value to be divided against the InstantaneousPower and ActivePowerattributes.
     * This  attribute must be used in conjunction with the ACPowerMultiplierattribute. 0x0000 is an
     * invalid value for this attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getAcPowerDivisorAsync() {
        return read(attributes.get(ATTR_ACPOWERDIVISOR));
    }


    /**
     * Synchronously get the <i>ACPowerDivisor</i> attribute [attribute ID <b>1541</b>].
     * <p>
     * Provides a value to be divided against the InstantaneousPower and ActivePowerattributes.
     * This  attribute must be used in conjunction with the ACPowerMultiplierattribute. 0x0000 is an
     * invalid value for this attribute.
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
    public Integer getAcPowerDivisor(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_ACPOWERDIVISOR).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_ACPOWERDIVISOR).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_ACPOWERDIVISOR).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_ACPOWERDIVISOR));
    }
}
