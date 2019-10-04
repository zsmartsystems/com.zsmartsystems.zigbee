/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.zcl.clusters.electricalmeasurement.GetMeasurementProfileCommand;
import com.zsmartsystems.zigbee.zcl.clusters.electricalmeasurement.GetMeasurementProfileResponseCommand;
import com.zsmartsystems.zigbee.zcl.clusters.electricalmeasurement.GetProfileInfoCommand;
import com.zsmartsystems.zigbee.zcl.clusters.electricalmeasurement.GetProfileInfoResponseCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Electrical Measurement</b> cluster implementation (<i>Cluster ID 0x0B04</i>).
 * <p>
 * This cluster provides a mechanism for querying data about the electrical properties as
 * measured by the device. This cluster may be implemented on any device type and be implemented
 * on a per-endpoint basis. For example, a power strip device could represent each outlet on a
 * different endpoint and report electrical information for each individual outlet. The only
 * caveat is that if you implement an attribute that has an associated multiplier and divisor,
 * then you must implement the associated multiplier and divisor attributes. For example if
 * you implement DCVoltage, you must also implement DCVoltageMultiplier and
 * DCVoltageDivisor.
 * <p>
 * If you are interested in reading information about the power supply or battery level on the
 * device, please see the Power Configuration cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-10-04T18:21:10Z")
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
     * This attribute indicates a device’s measurement capabilities. This will be indicated
     * by setting the desire measurement bits to 1.
     */
    public static final int ATTR_MEASUREMENTTYPE = 0x0000;
    public static final int ATTR_DCVOLTAGE = 0x0100;
    public static final int ATTR_DCVOLTAGEMIN = 0x0101;
    public static final int ATTR_DCVOLTAGEMAX = 0x0102;
    public static final int ATTR_DCCURRENT = 0x0103;
    public static final int ATTR_DCCURRENTMIN = 0x0104;
    public static final int ATTR_DCCURRENTMAX = 0x0105;
    public static final int ATTR_DCPOWER = 0x0106;
    public static final int ATTR_DCPOWERMIN = 0x0107;
    public static final int ATTR_DCPOWERMAX = 0x0108;
    public static final int ATTR_DCVOLTAGEMULTIPLIER = 0x0200;
    public static final int ATTR_DCVOLTAGEDIVISOR = 0x0201;
    public static final int ATTR_DCCURRENTMULTIPLIER = 0x0202;
    public static final int ATTR_DCCURRENTDIVISOR = 0x0203;
    public static final int ATTR_DCPOWERMULTIPLIER = 0x0204;
    public static final int ATTR_DCPOWERDIVISOR = 0x0205;
    /**
     * The ACFrequency attribute represents the most recent AC Frequency reading in Hertz
     * (Hz). If the frequency cannot be measured, a value of 0xFFFF is returned.
     */
    public static final int ATTR_ACFREQUENCY = 0x0300;
    public static final int ATTR_ACFREQUENCYMIN = 0x0301;
    public static final int ATTR_ACFREQUENCYMAX = 0x0302;
    public static final int ATTR_NEUTRALCURRENT = 0x0303;
    /**
     * Active power represents the current demand of active power delivered or received at the
     * premises, in kW. Positive values indicate power delivered to the premises where
     * negative values indicate power received from the premises. In case if device is capable
     * of measuring multi elements or phases then this will be net active power value.
     */
    public static final int ATTR_TOTALACTIVEPOWER = 0x0304;
    /**
     * Reactive power represents the current demand of reactive power delivered or received
     * at the premises, in kVAr. Positive values indicate power delivered to the premises
     * where negative values indicate power received from the premises. In case if device is
     * capable of measuring multi elements or phases then this will be net reactive power
     * value.
     */
    public static final int ATTR_TOTALREACTIVEPOWER = 0x0305;
    /**
     * Represents the current demand of apparent power, in kVA. In case if device is capable of
     * measuring multi elements or phases then this will be net apparent power value.
     */
    public static final int ATTR_TOTALAPPARENTPOWER = 0x0306;
    public static final int ATTR_MEASURED1STHARMONICCURRENT = 0x0307;
    public static final int ATTR_MEASURED3RDHARMONICCURRENT = 0x0308;
    public static final int ATTR_MEASURED5THHARMONICCURRENT = 0x0309;
    public static final int ATTR_MEASURED7THHARMONICCURRENT = 0x030A;
    public static final int ATTR_MEASURED9THHARMONICCURRENT = 0x030B;
    public static final int ATTR_MEASURED11THHARMONICCURRENT = 0x030C;
    public static final int ATTR_MEASUREDPHASE1STHARMONICCURRENT = 0x030D;
    public static final int ATTR_MEASUREDPHASE3RDHARMONICCURRENT = 0x030E;
    public static final int ATTR_MEASUREDPHASE5THHARMONICCURRENT = 0x030F;
    public static final int ATTR_MEASUREDPHASE7THHARMONICCURRENT = 0x0310;
    public static final int ATTR_MEASUREDPHASE9THHARMONICCURRENT = 0x0311;
    public static final int ATTR_MEASUREDPHASE11THHARMONICCURRENT = 0x0312;
    public static final int ATTR_ACFREQUENCYMULTIPLIER = 0x0400;
    public static final int ATTR_ACFREQUENCYDIVISOR = 0x0401;
    public static final int ATTR_POWERMULTIPLIER = 0x0402;
    public static final int ATTR_POWERDIVISOR = 0x0403;
    public static final int ATTR_HARMONICCURRENTMULTIPLIER = 0x0404;
    public static final int ATTR_PHASEHARMONICCURRENTMULTIPLIER = 0x0405;
    public static final int ATTR_INSTANTANEOUSVOLTAGE = 0x0500;
    public static final int ATTR_INSTANTANEOUSLINECURRENT = 0x0501;
    public static final int ATTR_INSTANTANEOUSACTIVECURRENT = 0x0502;
    public static final int ATTR_INSTANTANEOUSREACTIVECURRENT = 0x0503;
    public static final int ATTR_INSTANTANEOUSPOWER = 0x0504;
    /**
     * Represents the most recent RMS voltage reading in Volts (V). If the RMS voltage cannot be
     * measured, a value of 0xFFFF is returned.
     */
    public static final int ATTR_RMSVOLTAGE = 0x0505;
    public static final int ATTR_RMSVOLTAGEMIN = 0x0506;
    public static final int ATTR_RMSVOLTAGEMAX = 0x0507;
    /**
     * Represents the most recent RMS current reading in Amps (A). If the power cannot be
     * measured, a value of 0xFFFF is returned.
     */
    public static final int ATTR_RMSCURRENT = 0x0508;
    public static final int ATTR_RMSCURRENTMIN = 0x0509;
    public static final int ATTR_RMSCURRENTMAX = 0x050A;
    /**
     * Represents the single phase or Phase A, current demand of active power delivered or
     * received at the premises, in Watts (W). Positive values indicate power delivered to the
     * premises where negative values indicate power received from the premises.
     */
    public static final int ATTR_ACTIVEPOWER = 0x050B;
    public static final int ATTR_ACTIVEPOWERMIN = 0x050C;
    public static final int ATTR_ACTIVEPOWERMAX = 0x050D;
    public static final int ATTR_REACTIVEPOWER = 0x050E;
    public static final int ATTR_APPARENTPOWER = 0x050F;
    public static final int ATTR_POWERFACTOR = 0x0510;
    public static final int ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIOD = 0x0511;
    public static final int ATTR_AVERAGERMSUNDERVOLTAGECOUNTER = 0x0513;
    public static final int ATTR_RMSEXTREMEOVERVOLTAGEPERIOD = 0x0514;
    public static final int ATTR_RMSEXTREMEUNDERVOLTAGEPERIOD = 0x0515;
    public static final int ATTR_RMSVOLTAGESAGPERIOD = 0x0516;
    public static final int ATTR_RMSVOLTAGESWELLPERIOD = 0x0517;
    public static final int ATTR_ACVOLTAGEMULTIPLIER = 0x0600;
    public static final int ATTR_ACVOLTAGEDIVISOR = 0x0601;
    /**
     * Provides a value to be multiplied against the InstantaneousCurrent and
     * RMSCurrentattributes. his attribute must be used in conjunction with the
     * ACCurrentDivisorattribute. 0x0000 is an invalid value for this attribute.
     */
    public static final int ATTR_ACCURRENTMULTIPLIER = 0x0602;
    /**
     * Provides a value to be divided against the ACCurrent, InstantaneousCurrent and
     * RMSCurrentattributes. This attribute must be used in conjunction with the
     * ACCurrentMultiplierattribute 0x0000 is an invalid value for this attribute.
     */
    public static final int ATTR_ACCURRENTDIVISOR = 0x0603;
    /**
     * Provides a value to be multiplied against the InstantaneousPower and
     * ActivePowerattributes. This attribute must be used in conjunction with the
     * ACPowerDivisorattribute. 0x0000 is an invalid value for this attribute
     */
    public static final int ATTR_ACPOWERMULTIPLIER = 0x0604;
    /**
     * Provides a value to be divided against the InstantaneousPower and
     * ActivePowerattributes. This attribute must be used in conjunction with the
     * ACPowerMultiplierattribute. 0x0000 is an invalid value for this attribute.
     */
    public static final int ATTR_ACPOWERDIVISOR = 0x0605;
    public static final int ATTR_OVERLOADALARMSMASK = 0x0700;
    public static final int ATTR_VOLTAGEOVERLOAD = 0x0701;
    public static final int ATTR_CURRENTOVERLOAD = 0x0702;
    public static final int ATTR_ACOVERLOADALARMSMASK = 0x0800;
    public static final int ATTR_ACVOLTAGEOVERLOAD = 0x0801;
    public static final int ATTR_ACCURRENTOVERLOAD = 0x0802;
    public static final int ATTR_ACACTIVEPOWEROVERLOAD = 0x0803;
    public static final int ATTR_ACREACTIVEPOWEROVERLOAD = 0x0804;
    public static final int ATTR_AVERAGERMSOVERVOLTAGE = 0x0805;
    public static final int ATTR_AVERAGERMSUNDERVOLTAGE = 0x0806;
    public static final int ATTR_RMSEXTREMEOVERVOLTAGE = 0x0807;
    public static final int ATTR_RMSEXTREMEUNDERVOLTAGE = 0x0808;
    public static final int ATTR_RMSVOLTAGESAG = 0x0809;
    public static final int ATTR_RMSVOLTAGESWELL = 0x080A;
    public static final int ATTR_LINECURRENTPHASEB = 0x0901;
    public static final int ATTR_ACTIVECURRENTPHASEB = 0x0902;
    public static final int ATTR_REACTIVECURRENTPHASEB = 0x0903;
    public static final int ATTR_RMSVOLTAGEPHASEB = 0x0905;
    public static final int ATTR_RMSVOLTAGEMINPHASEB = 0x0906;
    public static final int ATTR_RMSVOLTAGEMAXPHASEB = 0x0907;
    public static final int ATTR_RMSCURRENTPHASEB = 0x0908;
    public static final int ATTR_RMSCURRENTMINPHASEB = 0x0909;
    public static final int ATTR_RMSCURRENTMAXPHASEB = 0x090A;
    public static final int ATTR_ACTIVEPOWERPHASEB = 0x090B;
    public static final int ATTR_ACTIVEPOWERMINPHASEB = 0x090C;
    public static final int ATTR_ACTIVEPOWERMAXPHASEB = 0x090D;
    public static final int ATTR_REACTIVEPOWERPHASEB = 0x090E;
    public static final int ATTR_APPARENTPOWERPHASEB = 0x090F;
    public static final int ATTR_POWERFACTORPHASEB = 0x0910;
    public static final int ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIODPHASEB = 0x0911;
    public static final int ATTR_AVERAGERMSOVERVOLTAGECOUNTERPHASEB = 0x0912;
    public static final int ATTR_AVERAGERMSUNDERVOLTAGECOUNTERPHASEB = 0x0913;
    public static final int ATTR_RMSEXTREMEOVERVOLTAGEPERIODPHASEB = 0x0914;
    public static final int ATTR_RMSEXTREMEUNDERVOLTAGEPERIODPHASEB = 0x0915;
    public static final int ATTR_RMSVOLTAGESAGPERIODPHASEB = 0x0916;
    public static final int ATTR_RMSVOLTAGESWELLPERIODPHASEB = 0x0917;
    public static final int ATTR_LINECURRENTPHASEC = 0x0A01;
    public static final int ATTR_ACTIVECURRENTPHASEC = 0x0A02;
    public static final int ATTR_REACTIVECURRENTPHASEC = 0x0A03;
    public static final int ATTR_RMSVOLTAGEPHASEC = 0x0A05;
    public static final int ATTR_RMSVOLTAGEMINPHASEC = 0x0A06;
    public static final int ATTR_RMSVOLTAGEMAXPHASEC = 0x0A07;
    public static final int ATTR_RMSCURRENTPHASEC = 0x0A08;
    public static final int ATTR_RMSCURRENTMINPHASEC = 0x0A09;
    public static final int ATTR_RMSCURRENTMAXPHASEC = 0x0A0A;
    public static final int ATTR_ACTIVEPOWERPHASEC = 0x0A0B;
    public static final int ATTR_ACTIVEPOWERMINPHASEC = 0x0A0C;
    public static final int ATTR_ACTIVEPOWERMAXPHASEC = 0x0A0D;
    public static final int ATTR_REACTIVEPOWERPHASEC = 0x0A0E;
    public static final int ATTR_APPARENTPOWERPHASEC = 0x0A0F;
    public static final int ATTR_POWERFACTORPHASEC = 0x0A10;
    public static final int ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIODPHASEC = 0x0A11;
    public static final int ATTR_AVERAGERMSOVERVOLTAGECOUNTERPHASEC = 0x0A12;
    public static final int ATTR_AVERAGERMSUNDERVOLTAGECOUNTERPHASEC = 0x0A13;
    public static final int ATTR_RMSEXTREMEOVERVOLTAGEPERIODPHASEC = 0x0A14;
    public static final int ATTR_RMSEXTREMEUNDERVOLTAGEPERIODPHASEC = 0x0A15;
    public static final int ATTR_RMSVOLTAGESAGPERIODPHASEC = 0x0A16;
    public static final int ATTR_RMSVOLTAGESWELLPERIODPHASEC = 0x0A17;

    @Override
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentSkipListMap<>();

        return attributeMap;
    }

    @Override
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentSkipListMap<>();

        attributeMap.put(ATTR_MEASUREMENTTYPE, new ZclAttribute(this, ATTR_MEASUREMENTTYPE, "Measurement Type", ZclDataType.BITMAP_32_BIT, true, true, false, false));
        attributeMap.put(ATTR_DCVOLTAGE, new ZclAttribute(this, ATTR_DCVOLTAGE, "DC Voltage", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DCVOLTAGEMIN, new ZclAttribute(this, ATTR_DCVOLTAGEMIN, "DC Voltage Min", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DCVOLTAGEMAX, new ZclAttribute(this, ATTR_DCVOLTAGEMAX, "DC Voltage Max", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DCCURRENT, new ZclAttribute(this, ATTR_DCCURRENT, "DC Current", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DCCURRENTMIN, new ZclAttribute(this, ATTR_DCCURRENTMIN, "DC Current Min", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DCCURRENTMAX, new ZclAttribute(this, ATTR_DCCURRENTMAX, "DC Current Max", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DCPOWER, new ZclAttribute(this, ATTR_DCPOWER, "DC Power", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DCPOWERMIN, new ZclAttribute(this, ATTR_DCPOWERMIN, "DC Power Min", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DCPOWERMAX, new ZclAttribute(this, ATTR_DCPOWERMAX, "DC Power Max", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DCVOLTAGEMULTIPLIER, new ZclAttribute(this, ATTR_DCVOLTAGEMULTIPLIER, "DC Voltage Multiplier", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DCVOLTAGEDIVISOR, new ZclAttribute(this, ATTR_DCVOLTAGEDIVISOR, "DC Voltage Divisor", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DCCURRENTMULTIPLIER, new ZclAttribute(this, ATTR_DCCURRENTMULTIPLIER, "DC Current Multiplier", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DCCURRENTDIVISOR, new ZclAttribute(this, ATTR_DCCURRENTDIVISOR, "DC Current Divisor", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DCPOWERMULTIPLIER, new ZclAttribute(this, ATTR_DCPOWERMULTIPLIER, "DC Power Multiplier", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DCPOWERDIVISOR, new ZclAttribute(this, ATTR_DCPOWERDIVISOR, "DC Power Divisor", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ACFREQUENCY, new ZclAttribute(this, ATTR_ACFREQUENCY, "AC Frequency", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ACFREQUENCYMIN, new ZclAttribute(this, ATTR_ACFREQUENCYMIN, "AC Frequency Min", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ACFREQUENCYMAX, new ZclAttribute(this, ATTR_ACFREQUENCYMAX, "AC Frequency Max", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NEUTRALCURRENT, new ZclAttribute(this, ATTR_NEUTRALCURRENT, "Neutral Current", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_TOTALACTIVEPOWER, new ZclAttribute(this, ATTR_TOTALACTIVEPOWER, "Total Active Power", ZclDataType.SIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TOTALREACTIVEPOWER, new ZclAttribute(this, ATTR_TOTALREACTIVEPOWER, "Total Reactive Power", ZclDataType.SIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TOTALAPPARENTPOWER, new ZclAttribute(this, ATTR_TOTALAPPARENTPOWER, "Total Apparent Power", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MEASURED1STHARMONICCURRENT, new ZclAttribute(this, ATTR_MEASURED1STHARMONICCURRENT, "Measured 1st Harmonic Current", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MEASURED3RDHARMONICCURRENT, new ZclAttribute(this, ATTR_MEASURED3RDHARMONICCURRENT, "Measured 3rd Harmonic Current", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MEASURED5THHARMONICCURRENT, new ZclAttribute(this, ATTR_MEASURED5THHARMONICCURRENT, "Measured 5th Harmonic Current", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MEASURED7THHARMONICCURRENT, new ZclAttribute(this, ATTR_MEASURED7THHARMONICCURRENT, "Measured 7th Harmonic Current", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MEASURED9THHARMONICCURRENT, new ZclAttribute(this, ATTR_MEASURED9THHARMONICCURRENT, "Measured 9th Harmonic Current", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MEASURED11THHARMONICCURRENT, new ZclAttribute(this, ATTR_MEASURED11THHARMONICCURRENT, "Measured 11th Harmonic Current", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MEASUREDPHASE1STHARMONICCURRENT, new ZclAttribute(this, ATTR_MEASUREDPHASE1STHARMONICCURRENT, "Measured Phase 1st Harmonic Current", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MEASUREDPHASE3RDHARMONICCURRENT, new ZclAttribute(this, ATTR_MEASUREDPHASE3RDHARMONICCURRENT, "Measured Phase 3rd Harmonic Current", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MEASUREDPHASE5THHARMONICCURRENT, new ZclAttribute(this, ATTR_MEASUREDPHASE5THHARMONICCURRENT, "Measured Phase 5th Harmonic Current", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MEASUREDPHASE7THHARMONICCURRENT, new ZclAttribute(this, ATTR_MEASUREDPHASE7THHARMONICCURRENT, "Measured Phase 7th Harmonic Current", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MEASUREDPHASE9THHARMONICCURRENT, new ZclAttribute(this, ATTR_MEASUREDPHASE9THHARMONICCURRENT, "Measured Phase 9th Harmonic Current", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MEASUREDPHASE11THHARMONICCURRENT, new ZclAttribute(this, ATTR_MEASUREDPHASE11THHARMONICCURRENT, "Measured Phase 11th Harmonic Current", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ACFREQUENCYMULTIPLIER, new ZclAttribute(this, ATTR_ACFREQUENCYMULTIPLIER, "AC Frequency Multiplier", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ACFREQUENCYDIVISOR, new ZclAttribute(this, ATTR_ACFREQUENCYDIVISOR, "AC Frequency Divisor", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_POWERMULTIPLIER, new ZclAttribute(this, ATTR_POWERMULTIPLIER, "Power Multiplier", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_POWERDIVISOR, new ZclAttribute(this, ATTR_POWERDIVISOR, "Power Divisor", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_HARMONICCURRENTMULTIPLIER, new ZclAttribute(this, ATTR_HARMONICCURRENTMULTIPLIER, "Harmonic Current Multiplier", ZclDataType.SIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PHASEHARMONICCURRENTMULTIPLIER, new ZclAttribute(this, ATTR_PHASEHARMONICCURRENTMULTIPLIER, "Phase Harmonic Current Multiplier", ZclDataType.SIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_INSTANTANEOUSVOLTAGE, new ZclAttribute(this, ATTR_INSTANTANEOUSVOLTAGE, "Instantaneous Voltage", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_INSTANTANEOUSLINECURRENT, new ZclAttribute(this, ATTR_INSTANTANEOUSLINECURRENT, "Instantaneous Line Current", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_INSTANTANEOUSACTIVECURRENT, new ZclAttribute(this, ATTR_INSTANTANEOUSACTIVECURRENT, "Instantaneous Active Current", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_INSTANTANEOUSREACTIVECURRENT, new ZclAttribute(this, ATTR_INSTANTANEOUSREACTIVECURRENT, "Instantaneous Reactive Current", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_INSTANTANEOUSPOWER, new ZclAttribute(this, ATTR_INSTANTANEOUSPOWER, "Instantaneous Power", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSVOLTAGE, new ZclAttribute(this, ATTR_RMSVOLTAGE, "RMS Voltage", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RMSVOLTAGEMIN, new ZclAttribute(this, ATTR_RMSVOLTAGEMIN, "RMS Voltage Min", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSVOLTAGEMAX, new ZclAttribute(this, ATTR_RMSVOLTAGEMAX, "RMS Voltage Max", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSCURRENT, new ZclAttribute(this, ATTR_RMSCURRENT, "RMS Current", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RMSCURRENTMIN, new ZclAttribute(this, ATTR_RMSCURRENTMIN, "RMS Current Min", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSCURRENTMAX, new ZclAttribute(this, ATTR_RMSCURRENTMAX, "RMS Current Max", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ACTIVEPOWER, new ZclAttribute(this, ATTR_ACTIVEPOWER, "Active Power", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ACTIVEPOWERMIN, new ZclAttribute(this, ATTR_ACTIVEPOWERMIN, "Active Power Min", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ACTIVEPOWERMAX, new ZclAttribute(this, ATTR_ACTIVEPOWERMAX, "Active Power Max", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_REACTIVEPOWER, new ZclAttribute(this, ATTR_REACTIVEPOWER, "Reactive Power", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APPARENTPOWER, new ZclAttribute(this, ATTR_APPARENTPOWER, "Apparent Power", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_POWERFACTOR, new ZclAttribute(this, ATTR_POWERFACTOR, "Power Factor", ZclDataType.SIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIOD, new ZclAttribute(this, ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIOD, "Average RMS Voltage Measurement Period", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_AVERAGERMSUNDERVOLTAGECOUNTER, new ZclAttribute(this, ATTR_AVERAGERMSUNDERVOLTAGECOUNTER, "Average RMS Under Voltage Counter", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_RMSEXTREMEOVERVOLTAGEPERIOD, new ZclAttribute(this, ATTR_RMSEXTREMEOVERVOLTAGEPERIOD, "RMS Extreme Over Voltage Period", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_RMSEXTREMEUNDERVOLTAGEPERIOD, new ZclAttribute(this, ATTR_RMSEXTREMEUNDERVOLTAGEPERIOD, "RMS Extreme Under Voltage Period", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_RMSVOLTAGESAGPERIOD, new ZclAttribute(this, ATTR_RMSVOLTAGESAGPERIOD, "RMS Voltage Sag Period", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_RMSVOLTAGESWELLPERIOD, new ZclAttribute(this, ATTR_RMSVOLTAGESWELLPERIOD, "RMS Voltage Swell Period", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_ACVOLTAGEMULTIPLIER, new ZclAttribute(this, ATTR_ACVOLTAGEMULTIPLIER, "AC Voltage Multiplier", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ACVOLTAGEDIVISOR, new ZclAttribute(this, ATTR_ACVOLTAGEDIVISOR, "AC Voltage Divisor", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ACCURRENTMULTIPLIER, new ZclAttribute(this, ATTR_ACCURRENTMULTIPLIER, "AC Current Multiplier", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ACCURRENTDIVISOR, new ZclAttribute(this, ATTR_ACCURRENTDIVISOR, "AC Current Divisor", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ACPOWERMULTIPLIER, new ZclAttribute(this, ATTR_ACPOWERMULTIPLIER, "AC Power Multiplier", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ACPOWERDIVISOR, new ZclAttribute(this, ATTR_ACPOWERDIVISOR, "AC Power Divisor", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_OVERLOADALARMSMASK, new ZclAttribute(this, ATTR_OVERLOADALARMSMASK, "Overload Alarms Mask", ZclDataType.BITMAP_8_BIT, false, true, true, true));
        attributeMap.put(ATTR_VOLTAGEOVERLOAD, new ZclAttribute(this, ATTR_VOLTAGEOVERLOAD, "Voltage Overload", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_CURRENTOVERLOAD, new ZclAttribute(this, ATTR_CURRENTOVERLOAD, "Current Overload", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ACOVERLOADALARMSMASK, new ZclAttribute(this, ATTR_ACOVERLOADALARMSMASK, "AC Overload Alarms Mask", ZclDataType.BITMAP_16_BIT, false, true, true, true));
        attributeMap.put(ATTR_ACVOLTAGEOVERLOAD, new ZclAttribute(this, ATTR_ACVOLTAGEOVERLOAD, "AC Voltage Overload", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ACCURRENTOVERLOAD, new ZclAttribute(this, ATTR_ACCURRENTOVERLOAD, "AC Current Overload", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ACACTIVEPOWEROVERLOAD, new ZclAttribute(this, ATTR_ACACTIVEPOWEROVERLOAD, "AC Active Power Overload", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ACREACTIVEPOWEROVERLOAD, new ZclAttribute(this, ATTR_ACREACTIVEPOWEROVERLOAD, "AC Reactive Power Overload", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_AVERAGERMSOVERVOLTAGE, new ZclAttribute(this, ATTR_AVERAGERMSOVERVOLTAGE, "Average RMS Over Voltage", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_AVERAGERMSUNDERVOLTAGE, new ZclAttribute(this, ATTR_AVERAGERMSUNDERVOLTAGE, "Average RMS Under Voltage", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSEXTREMEOVERVOLTAGE, new ZclAttribute(this, ATTR_RMSEXTREMEOVERVOLTAGE, "RMS Extreme Over Voltage", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSEXTREMEUNDERVOLTAGE, new ZclAttribute(this, ATTR_RMSEXTREMEUNDERVOLTAGE, "RMS Extreme Under Voltage", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSVOLTAGESAG, new ZclAttribute(this, ATTR_RMSVOLTAGESAG, "RMS Voltage Sag", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSVOLTAGESWELL, new ZclAttribute(this, ATTR_RMSVOLTAGESWELL, "RMS Voltage Swell", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_LINECURRENTPHASEB, new ZclAttribute(this, ATTR_LINECURRENTPHASEB, "Line Current Phase B", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ACTIVECURRENTPHASEB, new ZclAttribute(this, ATTR_ACTIVECURRENTPHASEB, "Active Current Phase B", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_REACTIVECURRENTPHASEB, new ZclAttribute(this, ATTR_REACTIVECURRENTPHASEB, "Reactive Current Phase B", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSVOLTAGEPHASEB, new ZclAttribute(this, ATTR_RMSVOLTAGEPHASEB, "RMS Voltage Phase B", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSVOLTAGEMINPHASEB, new ZclAttribute(this, ATTR_RMSVOLTAGEMINPHASEB, "RMS Voltage Min Phase B", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSVOLTAGEMAXPHASEB, new ZclAttribute(this, ATTR_RMSVOLTAGEMAXPHASEB, "RMS Voltage Max Phase B", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSCURRENTPHASEB, new ZclAttribute(this, ATTR_RMSCURRENTPHASEB, "RMS Current Phase B", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSCURRENTMINPHASEB, new ZclAttribute(this, ATTR_RMSCURRENTMINPHASEB, "RMS Current Min Phase B", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSCURRENTMAXPHASEB, new ZclAttribute(this, ATTR_RMSCURRENTMAXPHASEB, "RMS Current Max Phase B", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ACTIVEPOWERPHASEB, new ZclAttribute(this, ATTR_ACTIVEPOWERPHASEB, "Active Power Phase B", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ACTIVEPOWERMINPHASEB, new ZclAttribute(this, ATTR_ACTIVEPOWERMINPHASEB, "Active Power Min Phase B", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ACTIVEPOWERMAXPHASEB, new ZclAttribute(this, ATTR_ACTIVEPOWERMAXPHASEB, "Active Power Max Phase B", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_REACTIVEPOWERPHASEB, new ZclAttribute(this, ATTR_REACTIVEPOWERPHASEB, "Reactive Power Phase B", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APPARENTPOWERPHASEB, new ZclAttribute(this, ATTR_APPARENTPOWERPHASEB, "Apparent Power Phase B", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_POWERFACTORPHASEB, new ZclAttribute(this, ATTR_POWERFACTORPHASEB, "Power Factor Phase B", ZclDataType.SIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIODPHASEB, new ZclAttribute(this, ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIODPHASEB, "Average RMS Voltage Measurement Period Phase B", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_AVERAGERMSOVERVOLTAGECOUNTERPHASEB, new ZclAttribute(this, ATTR_AVERAGERMSOVERVOLTAGECOUNTERPHASEB, "Average RMS Over Voltage Counter Phase B", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_AVERAGERMSUNDERVOLTAGECOUNTERPHASEB, new ZclAttribute(this, ATTR_AVERAGERMSUNDERVOLTAGECOUNTERPHASEB, "Average RMS Under Voltage Counter Phase B", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSEXTREMEOVERVOLTAGEPERIODPHASEB, new ZclAttribute(this, ATTR_RMSEXTREMEOVERVOLTAGEPERIODPHASEB, "RMS Extreme Over Voltage Period Phase B", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSEXTREMEUNDERVOLTAGEPERIODPHASEB, new ZclAttribute(this, ATTR_RMSEXTREMEUNDERVOLTAGEPERIODPHASEB, "RMS Extreme Under Voltage Period Phase B", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSVOLTAGESAGPERIODPHASEB, new ZclAttribute(this, ATTR_RMSVOLTAGESAGPERIODPHASEB, "RMS Voltage Sag Period Phase B", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSVOLTAGESWELLPERIODPHASEB, new ZclAttribute(this, ATTR_RMSVOLTAGESWELLPERIODPHASEB, "RMS Voltage Swell Period Phase B", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_LINECURRENTPHASEC, new ZclAttribute(this, ATTR_LINECURRENTPHASEC, "Line Current Phase C", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ACTIVECURRENTPHASEC, new ZclAttribute(this, ATTR_ACTIVECURRENTPHASEC, "Active Current Phase C", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_REACTIVECURRENTPHASEC, new ZclAttribute(this, ATTR_REACTIVECURRENTPHASEC, "Reactive Current Phase C", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSVOLTAGEPHASEC, new ZclAttribute(this, ATTR_RMSVOLTAGEPHASEC, "RMS Voltage Phase C", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSVOLTAGEMINPHASEC, new ZclAttribute(this, ATTR_RMSVOLTAGEMINPHASEC, "RMS Voltage Min Phase C", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSVOLTAGEMAXPHASEC, new ZclAttribute(this, ATTR_RMSVOLTAGEMAXPHASEC, "RMS Voltage Max Phase C", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSCURRENTPHASEC, new ZclAttribute(this, ATTR_RMSCURRENTPHASEC, "RMS Current Phase C", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSCURRENTMINPHASEC, new ZclAttribute(this, ATTR_RMSCURRENTMINPHASEC, "RMS Current Min Phase C", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSCURRENTMAXPHASEC, new ZclAttribute(this, ATTR_RMSCURRENTMAXPHASEC, "RMS Current Max Phase C", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ACTIVEPOWERPHASEC, new ZclAttribute(this, ATTR_ACTIVEPOWERPHASEC, "Active Power Phase C", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ACTIVEPOWERMINPHASEC, new ZclAttribute(this, ATTR_ACTIVEPOWERMINPHASEC, "Active Power Min Phase C", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ACTIVEPOWERMAXPHASEC, new ZclAttribute(this, ATTR_ACTIVEPOWERMAXPHASEC, "Active Power Max Phase C", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_REACTIVEPOWERPHASEC, new ZclAttribute(this, ATTR_REACTIVEPOWERPHASEC, "Reactive Power Phase C", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APPARENTPOWERPHASEC, new ZclAttribute(this, ATTR_APPARENTPOWERPHASEC, "Apparent Power Phase C", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_POWERFACTORPHASEC, new ZclAttribute(this, ATTR_POWERFACTORPHASEC, "Power Factor Phase C", ZclDataType.SIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIODPHASEC, new ZclAttribute(this, ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIODPHASEC, "Average RMS Voltage Measurement Period Phase C", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_AVERAGERMSOVERVOLTAGECOUNTERPHASEC, new ZclAttribute(this, ATTR_AVERAGERMSOVERVOLTAGECOUNTERPHASEC, "Average RMS Over Voltage Counter Phase C", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_AVERAGERMSUNDERVOLTAGECOUNTERPHASEC, new ZclAttribute(this, ATTR_AVERAGERMSUNDERVOLTAGECOUNTERPHASEC, "Average RMS Under Voltage Counter Phase C", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSEXTREMEOVERVOLTAGEPERIODPHASEC, new ZclAttribute(this, ATTR_RMSEXTREMEOVERVOLTAGEPERIODPHASEC, "RMS Extreme Over Voltage Period Phase C", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSEXTREMEUNDERVOLTAGEPERIODPHASEC, new ZclAttribute(this, ATTR_RMSEXTREMEUNDERVOLTAGEPERIODPHASEC, "RMS Extreme Under Voltage Period Phase C", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSVOLTAGESAGPERIODPHASEC, new ZclAttribute(this, ATTR_RMSVOLTAGESAGPERIODPHASEC, "RMS Voltage Sag Period Phase C", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RMSVOLTAGESWELLPERIODPHASEC, new ZclAttribute(this, ATTR_RMSVOLTAGESWELLPERIODPHASEC, "RMS Voltage Swell Period Phase C", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));

        return attributeMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeServerCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();

        commandMap.put(0x0000, GetProfileInfoResponseCommand.class);
        commandMap.put(0x0001, GetMeasurementProfileResponseCommand.class);

        return commandMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();

        commandMap.put(0x0000, GetProfileInfoCommand.class);
        commandMap.put(0x0001, GetMeasurementProfileCommand.class);

        return commandMap;
    }

    /**
     * Default constructor to create a Electrical Measurement cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclElectricalMeasurementCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>Measurement Type</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * This attribute indicates a device’s measurement capabilities. This will be indicated
     * by setting the desire measurement bits to 1.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMeasurementTypeAsync() {
        return read(serverAttributes.get(ATTR_MEASUREMENTTYPE));
    }

    /**
     * Synchronously get the <i>Measurement Type</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * This attribute indicates a device’s measurement capabilities. This will be indicated
     * by setting the desire measurement bits to 1.
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
    public Integer getMeasurementType(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MEASUREMENTTYPE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MEASUREMENTTYPE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MEASUREMENTTYPE));
    }

    /**
     * Set reporting for the <i>Measurement Type</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * This attribute indicates a device’s measurement capabilities. This will be indicated
     * by setting the desire measurement bits to 1.
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
    public Future<CommandResult> setMeasurementTypeReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_MEASUREMENTTYPE), minInterval, maxInterval);
    }

    /**
     * Get the <i>DC Voltage</i> attribute [attribute ID <b>0x0100</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDcVoltageAsync() {
        return read(serverAttributes.get(ATTR_DCVOLTAGE));
    }

    /**
     * Synchronously get the <i>DC Voltage</i> attribute [attribute ID <b>0x0100</b>].
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
    public Integer getDcVoltage(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DCVOLTAGE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DCVOLTAGE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DCVOLTAGE));
    }

    /**
     * Set reporting for the <i>DC Voltage</i> attribute [attribute ID <b>0x0100</b>].
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
    public Future<CommandResult> setDcVoltageReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_DCVOLTAGE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>DC Voltage Min</i> attribute [attribute ID <b>0x0101</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDcVoltageMinAsync() {
        return read(serverAttributes.get(ATTR_DCVOLTAGEMIN));
    }

    /**
     * Synchronously get the <i>DC Voltage Min</i> attribute [attribute ID <b>0x0101</b>].
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
    public Integer getDcVoltageMin(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DCVOLTAGEMIN).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DCVOLTAGEMIN).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DCVOLTAGEMIN));
    }

    /**
     * Set reporting for the <i>DC Voltage Min</i> attribute [attribute ID <b>0x0101</b>].
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
    public Future<CommandResult> setDcVoltageMinReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_DCVOLTAGEMIN), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>DC Voltage Max</i> attribute [attribute ID <b>0x0102</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDcVoltageMaxAsync() {
        return read(serverAttributes.get(ATTR_DCVOLTAGEMAX));
    }

    /**
     * Synchronously get the <i>DC Voltage Max</i> attribute [attribute ID <b>0x0102</b>].
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
    public Integer getDcVoltageMax(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DCVOLTAGEMAX).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DCVOLTAGEMAX).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DCVOLTAGEMAX));
    }

    /**
     * Set reporting for the <i>DC Voltage Max</i> attribute [attribute ID <b>0x0102</b>].
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
    public Future<CommandResult> setDcVoltageMaxReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_DCVOLTAGEMAX), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>DC Current</i> attribute [attribute ID <b>0x0103</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDcCurrentAsync() {
        return read(serverAttributes.get(ATTR_DCCURRENT));
    }

    /**
     * Synchronously get the <i>DC Current</i> attribute [attribute ID <b>0x0103</b>].
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
    public Integer getDcCurrent(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DCCURRENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DCCURRENT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DCCURRENT));
    }

    /**
     * Set reporting for the <i>DC Current</i> attribute [attribute ID <b>0x0103</b>].
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
    public Future<CommandResult> setDcCurrentReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_DCCURRENT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>DC Current Min</i> attribute [attribute ID <b>0x0104</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDcCurrentMinAsync() {
        return read(serverAttributes.get(ATTR_DCCURRENTMIN));
    }

    /**
     * Synchronously get the <i>DC Current Min</i> attribute [attribute ID <b>0x0104</b>].
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
    public Integer getDcCurrentMin(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DCCURRENTMIN).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DCCURRENTMIN).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DCCURRENTMIN));
    }

    /**
     * Set reporting for the <i>DC Current Min</i> attribute [attribute ID <b>0x0104</b>].
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
    public Future<CommandResult> setDcCurrentMinReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_DCCURRENTMIN), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>DC Current Max</i> attribute [attribute ID <b>0x0105</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDcCurrentMaxAsync() {
        return read(serverAttributes.get(ATTR_DCCURRENTMAX));
    }

    /**
     * Synchronously get the <i>DC Current Max</i> attribute [attribute ID <b>0x0105</b>].
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
    public Integer getDcCurrentMax(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DCCURRENTMAX).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DCCURRENTMAX).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DCCURRENTMAX));
    }

    /**
     * Set reporting for the <i>DC Current Max</i> attribute [attribute ID <b>0x0105</b>].
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
    public Future<CommandResult> setDcCurrentMaxReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_DCCURRENTMAX), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>DC Power</i> attribute [attribute ID <b>0x0106</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDcPowerAsync() {
        return read(serverAttributes.get(ATTR_DCPOWER));
    }

    /**
     * Synchronously get the <i>DC Power</i> attribute [attribute ID <b>0x0106</b>].
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
    public Integer getDcPower(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DCPOWER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DCPOWER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DCPOWER));
    }

    /**
     * Set reporting for the <i>DC Power</i> attribute [attribute ID <b>0x0106</b>].
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
    public Future<CommandResult> setDcPowerReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_DCPOWER), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>DC Power Min</i> attribute [attribute ID <b>0x0107</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDcPowerMinAsync() {
        return read(serverAttributes.get(ATTR_DCPOWERMIN));
    }

    /**
     * Synchronously get the <i>DC Power Min</i> attribute [attribute ID <b>0x0107</b>].
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
    public Integer getDcPowerMin(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DCPOWERMIN).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DCPOWERMIN).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DCPOWERMIN));
    }

    /**
     * Set reporting for the <i>DC Power Min</i> attribute [attribute ID <b>0x0107</b>].
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
    public Future<CommandResult> setDcPowerMinReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_DCPOWERMIN), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>DC Power Max</i> attribute [attribute ID <b>0x0108</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDcPowerMaxAsync() {
        return read(serverAttributes.get(ATTR_DCPOWERMAX));
    }

    /**
     * Synchronously get the <i>DC Power Max</i> attribute [attribute ID <b>0x0108</b>].
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
    public Integer getDcPowerMax(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DCPOWERMAX).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DCPOWERMAX).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DCPOWERMAX));
    }

    /**
     * Set reporting for the <i>DC Power Max</i> attribute [attribute ID <b>0x0108</b>].
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
    public Future<CommandResult> setDcPowerMaxReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_DCPOWERMAX), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>DC Voltage Multiplier</i> attribute [attribute ID <b>0x0200</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDcVoltageMultiplierAsync() {
        return read(serverAttributes.get(ATTR_DCVOLTAGEMULTIPLIER));
    }

    /**
     * Synchronously get the <i>DC Voltage Multiplier</i> attribute [attribute ID <b>0x0200</b>].
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
    public Integer getDcVoltageMultiplier(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DCVOLTAGEMULTIPLIER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DCVOLTAGEMULTIPLIER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DCVOLTAGEMULTIPLIER));
    }

    /**
     * Set reporting for the <i>DC Voltage Multiplier</i> attribute [attribute ID <b>0x0200</b>].
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
    public Future<CommandResult> setDcVoltageMultiplierReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_DCVOLTAGEMULTIPLIER), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>DC Voltage Divisor</i> attribute [attribute ID <b>0x0201</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDcVoltageDivisorAsync() {
        return read(serverAttributes.get(ATTR_DCVOLTAGEDIVISOR));
    }

    /**
     * Synchronously get the <i>DC Voltage Divisor</i> attribute [attribute ID <b>0x0201</b>].
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
    public Integer getDcVoltageDivisor(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DCVOLTAGEDIVISOR).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DCVOLTAGEDIVISOR).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DCVOLTAGEDIVISOR));
    }

    /**
     * Set reporting for the <i>DC Voltage Divisor</i> attribute [attribute ID <b>0x0201</b>].
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
    public Future<CommandResult> setDcVoltageDivisorReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_DCVOLTAGEDIVISOR), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>DC Current Multiplier</i> attribute [attribute ID <b>0x0202</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDcCurrentMultiplierAsync() {
        return read(serverAttributes.get(ATTR_DCCURRENTMULTIPLIER));
    }

    /**
     * Synchronously get the <i>DC Current Multiplier</i> attribute [attribute ID <b>0x0202</b>].
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
    public Integer getDcCurrentMultiplier(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DCCURRENTMULTIPLIER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DCCURRENTMULTIPLIER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DCCURRENTMULTIPLIER));
    }

    /**
     * Set reporting for the <i>DC Current Multiplier</i> attribute [attribute ID <b>0x0202</b>].
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
    public Future<CommandResult> setDcCurrentMultiplierReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_DCCURRENTMULTIPLIER), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>DC Current Divisor</i> attribute [attribute ID <b>0x0203</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDcCurrentDivisorAsync() {
        return read(serverAttributes.get(ATTR_DCCURRENTDIVISOR));
    }

    /**
     * Synchronously get the <i>DC Current Divisor</i> attribute [attribute ID <b>0x0203</b>].
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
    public Integer getDcCurrentDivisor(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DCCURRENTDIVISOR).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DCCURRENTDIVISOR).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DCCURRENTDIVISOR));
    }

    /**
     * Set reporting for the <i>DC Current Divisor</i> attribute [attribute ID <b>0x0203</b>].
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
    public Future<CommandResult> setDcCurrentDivisorReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_DCCURRENTDIVISOR), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>DC Power Multiplier</i> attribute [attribute ID <b>0x0204</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDcPowerMultiplierAsync() {
        return read(serverAttributes.get(ATTR_DCPOWERMULTIPLIER));
    }

    /**
     * Synchronously get the <i>DC Power Multiplier</i> attribute [attribute ID <b>0x0204</b>].
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
    public Integer getDcPowerMultiplier(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DCPOWERMULTIPLIER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DCPOWERMULTIPLIER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DCPOWERMULTIPLIER));
    }

    /**
     * Set reporting for the <i>DC Power Multiplier</i> attribute [attribute ID <b>0x0204</b>].
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
    public Future<CommandResult> setDcPowerMultiplierReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_DCPOWERMULTIPLIER), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>DC Power Divisor</i> attribute [attribute ID <b>0x0205</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDcPowerDivisorAsync() {
        return read(serverAttributes.get(ATTR_DCPOWERDIVISOR));
    }

    /**
     * Synchronously get the <i>DC Power Divisor</i> attribute [attribute ID <b>0x0205</b>].
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
    public Integer getDcPowerDivisor(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DCPOWERDIVISOR).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DCPOWERDIVISOR).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DCPOWERDIVISOR));
    }

    /**
     * Set reporting for the <i>DC Power Divisor</i> attribute [attribute ID <b>0x0205</b>].
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
    public Future<CommandResult> setDcPowerDivisorReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_DCPOWERDIVISOR), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>AC Frequency</i> attribute [attribute ID <b>0x0300</b>].
     * <p>
     * The ACFrequency attribute represents the most recent AC Frequency reading in Hertz
     * (Hz). If the frequency cannot be measured, a value of 0xFFFF is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcFrequencyAsync() {
        return read(serverAttributes.get(ATTR_ACFREQUENCY));
    }

    /**
     * Synchronously get the <i>AC Frequency</i> attribute [attribute ID <b>0x0300</b>].
     * <p>
     * The ACFrequency attribute represents the most recent AC Frequency reading in Hertz
     * (Hz). If the frequency cannot be measured, a value of 0xFFFF is returned.
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
    public Integer getAcFrequency(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACFREQUENCY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACFREQUENCY).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACFREQUENCY));
    }

    /**
     * Get the <i>AC Frequency Min</i> attribute [attribute ID <b>0x0301</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcFrequencyMinAsync() {
        return read(serverAttributes.get(ATTR_ACFREQUENCYMIN));
    }

    /**
     * Synchronously get the <i>AC Frequency Min</i> attribute [attribute ID <b>0x0301</b>].
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
    public Integer getAcFrequencyMin(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACFREQUENCYMIN).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACFREQUENCYMIN).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACFREQUENCYMIN));
    }

    /**
     * Set reporting for the <i>AC Frequency Min</i> attribute [attribute ID <b>0x0301</b>].
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
    public Future<CommandResult> setAcFrequencyMinReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_ACFREQUENCYMIN), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>AC Frequency Max</i> attribute [attribute ID <b>0x0302</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcFrequencyMaxAsync() {
        return read(serverAttributes.get(ATTR_ACFREQUENCYMAX));
    }

    /**
     * Synchronously get the <i>AC Frequency Max</i> attribute [attribute ID <b>0x0302</b>].
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
    public Integer getAcFrequencyMax(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACFREQUENCYMAX).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACFREQUENCYMAX).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACFREQUENCYMAX));
    }

    /**
     * Set reporting for the <i>AC Frequency Max</i> attribute [attribute ID <b>0x0302</b>].
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
    public Future<CommandResult> setAcFrequencyMaxReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_ACFREQUENCYMAX), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Neutral Current</i> attribute [attribute ID <b>0x0303</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getNeutralCurrentAsync() {
        return read(serverAttributes.get(ATTR_NEUTRALCURRENT));
    }

    /**
     * Synchronously get the <i>Neutral Current</i> attribute [attribute ID <b>0x0303</b>].
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
    public Integer getNeutralCurrent(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_NEUTRALCURRENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_NEUTRALCURRENT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_NEUTRALCURRENT));
    }

    /**
     * Set reporting for the <i>Neutral Current</i> attribute [attribute ID <b>0x0303</b>].
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
    public Future<CommandResult> setNeutralCurrentReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_NEUTRALCURRENT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Total Active Power</i> attribute [attribute ID <b>0x0304</b>].
     * <p>
     * Active power represents the current demand of active power delivered or received at the
     * premises, in kW. Positive values indicate power delivered to the premises where
     * negative values indicate power received from the premises. In case if device is capable
     * of measuring multi elements or phases then this will be net active power value.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getTotalActivePowerAsync() {
        return read(serverAttributes.get(ATTR_TOTALACTIVEPOWER));
    }

    /**
     * Synchronously get the <i>Total Active Power</i> attribute [attribute ID <b>0x0304</b>].
     * <p>
     * Active power represents the current demand of active power delivered or received at the
     * premises, in kW. Positive values indicate power delivered to the premises where
     * negative values indicate power received from the premises. In case if device is capable
     * of measuring multi elements or phases then this will be net active power value.
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
    public Integer getTotalActivePower(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_TOTALACTIVEPOWER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_TOTALACTIVEPOWER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_TOTALACTIVEPOWER));
    }

    /**
     * Get the <i>Total Reactive Power</i> attribute [attribute ID <b>0x0305</b>].
     * <p>
     * Reactive power represents the current demand of reactive power delivered or received
     * at the premises, in kVAr. Positive values indicate power delivered to the premises
     * where negative values indicate power received from the premises. In case if device is
     * capable of measuring multi elements or phases then this will be net reactive power
     * value.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getTotalReactivePowerAsync() {
        return read(serverAttributes.get(ATTR_TOTALREACTIVEPOWER));
    }

    /**
     * Synchronously get the <i>Total Reactive Power</i> attribute [attribute ID <b>0x0305</b>].
     * <p>
     * Reactive power represents the current demand of reactive power delivered or received
     * at the premises, in kVAr. Positive values indicate power delivered to the premises
     * where negative values indicate power received from the premises. In case if device is
     * capable of measuring multi elements or phases then this will be net reactive power
     * value.
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
    public Integer getTotalReactivePower(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_TOTALREACTIVEPOWER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_TOTALREACTIVEPOWER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_TOTALREACTIVEPOWER));
    }

    /**
     * Get the <i>Total Apparent Power</i> attribute [attribute ID <b>0x0306</b>].
     * <p>
     * Represents the current demand of apparent power, in kVA. In case if device is capable of
     * measuring multi elements or phases then this will be net apparent power value.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getTotalApparentPowerAsync() {
        return read(serverAttributes.get(ATTR_TOTALAPPARENTPOWER));
    }

    /**
     * Synchronously get the <i>Total Apparent Power</i> attribute [attribute ID <b>0x0306</b>].
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getTotalApparentPower(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_TOTALAPPARENTPOWER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_TOTALAPPARENTPOWER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_TOTALAPPARENTPOWER));
    }

    /**
     * Get the <i>Measured 1st Harmonic Current</i> attribute [attribute ID <b>0x0307</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMeasured1stHarmonicCurrentAsync() {
        return read(serverAttributes.get(ATTR_MEASURED1STHARMONICCURRENT));
    }

    /**
     * Synchronously get the <i>Measured 1st Harmonic Current</i> attribute [attribute ID <b>0x0307</b>].
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
    public Integer getMeasured1stHarmonicCurrent(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MEASURED1STHARMONICCURRENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MEASURED1STHARMONICCURRENT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MEASURED1STHARMONICCURRENT));
    }

    /**
     * Set reporting for the <i>Measured 1st Harmonic Current</i> attribute [attribute ID <b>0x0307</b>].
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
    public Future<CommandResult> setMeasured1stHarmonicCurrentReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MEASURED1STHARMONICCURRENT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Measured 3rd Harmonic Current</i> attribute [attribute ID <b>0x0308</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMeasured3rdHarmonicCurrentAsync() {
        return read(serverAttributes.get(ATTR_MEASURED3RDHARMONICCURRENT));
    }

    /**
     * Synchronously get the <i>Measured 3rd Harmonic Current</i> attribute [attribute ID <b>0x0308</b>].
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
    public Integer getMeasured3rdHarmonicCurrent(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MEASURED3RDHARMONICCURRENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MEASURED3RDHARMONICCURRENT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MEASURED3RDHARMONICCURRENT));
    }

    /**
     * Set reporting for the <i>Measured 3rd Harmonic Current</i> attribute [attribute ID <b>0x0308</b>].
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
    public Future<CommandResult> setMeasured3rdHarmonicCurrentReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MEASURED3RDHARMONICCURRENT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Measured 5th Harmonic Current</i> attribute [attribute ID <b>0x0309</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMeasured5thHarmonicCurrentAsync() {
        return read(serverAttributes.get(ATTR_MEASURED5THHARMONICCURRENT));
    }

    /**
     * Synchronously get the <i>Measured 5th Harmonic Current</i> attribute [attribute ID <b>0x0309</b>].
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
    public Integer getMeasured5thHarmonicCurrent(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MEASURED5THHARMONICCURRENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MEASURED5THHARMONICCURRENT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MEASURED5THHARMONICCURRENT));
    }

    /**
     * Set reporting for the <i>Measured 5th Harmonic Current</i> attribute [attribute ID <b>0x0309</b>].
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
    public Future<CommandResult> setMeasured5thHarmonicCurrentReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MEASURED5THHARMONICCURRENT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Measured 7th Harmonic Current</i> attribute [attribute ID <b>0x030A</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMeasured7thHarmonicCurrentAsync() {
        return read(serverAttributes.get(ATTR_MEASURED7THHARMONICCURRENT));
    }

    /**
     * Synchronously get the <i>Measured 7th Harmonic Current</i> attribute [attribute ID <b>0x030A</b>].
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
    public Integer getMeasured7thHarmonicCurrent(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MEASURED7THHARMONICCURRENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MEASURED7THHARMONICCURRENT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MEASURED7THHARMONICCURRENT));
    }

    /**
     * Set reporting for the <i>Measured 7th Harmonic Current</i> attribute [attribute ID <b>0x030A</b>].
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
    public Future<CommandResult> setMeasured7thHarmonicCurrentReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MEASURED7THHARMONICCURRENT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Measured 9th Harmonic Current</i> attribute [attribute ID <b>0x030B</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMeasured9thHarmonicCurrentAsync() {
        return read(serverAttributes.get(ATTR_MEASURED9THHARMONICCURRENT));
    }

    /**
     * Synchronously get the <i>Measured 9th Harmonic Current</i> attribute [attribute ID <b>0x030B</b>].
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
    public Integer getMeasured9thHarmonicCurrent(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MEASURED9THHARMONICCURRENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MEASURED9THHARMONICCURRENT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MEASURED9THHARMONICCURRENT));
    }

    /**
     * Set reporting for the <i>Measured 9th Harmonic Current</i> attribute [attribute ID <b>0x030B</b>].
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
    public Future<CommandResult> setMeasured9thHarmonicCurrentReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MEASURED9THHARMONICCURRENT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Measured 11th Harmonic Current</i> attribute [attribute ID <b>0x030C</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMeasured11thHarmonicCurrentAsync() {
        return read(serverAttributes.get(ATTR_MEASURED11THHARMONICCURRENT));
    }

    /**
     * Synchronously get the <i>Measured 11th Harmonic Current</i> attribute [attribute ID <b>0x030C</b>].
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
    public Integer getMeasured11thHarmonicCurrent(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MEASURED11THHARMONICCURRENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MEASURED11THHARMONICCURRENT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MEASURED11THHARMONICCURRENT));
    }

    /**
     * Set reporting for the <i>Measured 11th Harmonic Current</i> attribute [attribute ID <b>0x030C</b>].
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
    public Future<CommandResult> setMeasured11thHarmonicCurrentReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MEASURED11THHARMONICCURRENT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Measured Phase 1st Harmonic Current</i> attribute [attribute ID <b>0x030D</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMeasuredPhase1stHarmonicCurrentAsync() {
        return read(serverAttributes.get(ATTR_MEASUREDPHASE1STHARMONICCURRENT));
    }

    /**
     * Synchronously get the <i>Measured Phase 1st Harmonic Current</i> attribute [attribute ID <b>0x030D</b>].
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
    public Integer getMeasuredPhase1stHarmonicCurrent(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MEASUREDPHASE1STHARMONICCURRENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MEASUREDPHASE1STHARMONICCURRENT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MEASUREDPHASE1STHARMONICCURRENT));
    }

    /**
     * Set reporting for the <i>Measured Phase 1st Harmonic Current</i> attribute [attribute ID <b>0x030D</b>].
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
    public Future<CommandResult> setMeasuredPhase1stHarmonicCurrentReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MEASUREDPHASE1STHARMONICCURRENT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Measured Phase 3rd Harmonic Current</i> attribute [attribute ID <b>0x030E</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMeasuredPhase3rdHarmonicCurrentAsync() {
        return read(serverAttributes.get(ATTR_MEASUREDPHASE3RDHARMONICCURRENT));
    }

    /**
     * Synchronously get the <i>Measured Phase 3rd Harmonic Current</i> attribute [attribute ID <b>0x030E</b>].
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
    public Integer getMeasuredPhase3rdHarmonicCurrent(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MEASUREDPHASE3RDHARMONICCURRENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MEASUREDPHASE3RDHARMONICCURRENT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MEASUREDPHASE3RDHARMONICCURRENT));
    }

    /**
     * Set reporting for the <i>Measured Phase 3rd Harmonic Current</i> attribute [attribute ID <b>0x030E</b>].
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
    public Future<CommandResult> setMeasuredPhase3rdHarmonicCurrentReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MEASUREDPHASE3RDHARMONICCURRENT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Measured Phase 5th Harmonic Current</i> attribute [attribute ID <b>0x030F</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMeasuredPhase5thHarmonicCurrentAsync() {
        return read(serverAttributes.get(ATTR_MEASUREDPHASE5THHARMONICCURRENT));
    }

    /**
     * Synchronously get the <i>Measured Phase 5th Harmonic Current</i> attribute [attribute ID <b>0x030F</b>].
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
    public Integer getMeasuredPhase5thHarmonicCurrent(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MEASUREDPHASE5THHARMONICCURRENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MEASUREDPHASE5THHARMONICCURRENT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MEASUREDPHASE5THHARMONICCURRENT));
    }

    /**
     * Set reporting for the <i>Measured Phase 5th Harmonic Current</i> attribute [attribute ID <b>0x030F</b>].
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
    public Future<CommandResult> setMeasuredPhase5thHarmonicCurrentReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MEASUREDPHASE5THHARMONICCURRENT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Measured Phase 7th Harmonic Current</i> attribute [attribute ID <b>0x0310</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMeasuredPhase7thHarmonicCurrentAsync() {
        return read(serverAttributes.get(ATTR_MEASUREDPHASE7THHARMONICCURRENT));
    }

    /**
     * Synchronously get the <i>Measured Phase 7th Harmonic Current</i> attribute [attribute ID <b>0x0310</b>].
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
    public Integer getMeasuredPhase7thHarmonicCurrent(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MEASUREDPHASE7THHARMONICCURRENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MEASUREDPHASE7THHARMONICCURRENT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MEASUREDPHASE7THHARMONICCURRENT));
    }

    /**
     * Set reporting for the <i>Measured Phase 7th Harmonic Current</i> attribute [attribute ID <b>0x0310</b>].
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
    public Future<CommandResult> setMeasuredPhase7thHarmonicCurrentReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MEASUREDPHASE7THHARMONICCURRENT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Measured Phase 9th Harmonic Current</i> attribute [attribute ID <b>0x0311</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMeasuredPhase9thHarmonicCurrentAsync() {
        return read(serverAttributes.get(ATTR_MEASUREDPHASE9THHARMONICCURRENT));
    }

    /**
     * Synchronously get the <i>Measured Phase 9th Harmonic Current</i> attribute [attribute ID <b>0x0311</b>].
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
    public Integer getMeasuredPhase9thHarmonicCurrent(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MEASUREDPHASE9THHARMONICCURRENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MEASUREDPHASE9THHARMONICCURRENT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MEASUREDPHASE9THHARMONICCURRENT));
    }

    /**
     * Set reporting for the <i>Measured Phase 9th Harmonic Current</i> attribute [attribute ID <b>0x0311</b>].
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
    public Future<CommandResult> setMeasuredPhase9thHarmonicCurrentReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MEASUREDPHASE9THHARMONICCURRENT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Measured Phase 11th Harmonic Current</i> attribute [attribute ID <b>0x0312</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMeasuredPhase11thHarmonicCurrentAsync() {
        return read(serverAttributes.get(ATTR_MEASUREDPHASE11THHARMONICCURRENT));
    }

    /**
     * Synchronously get the <i>Measured Phase 11th Harmonic Current</i> attribute [attribute ID <b>0x0312</b>].
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
    public Integer getMeasuredPhase11thHarmonicCurrent(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MEASUREDPHASE11THHARMONICCURRENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MEASUREDPHASE11THHARMONICCURRENT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MEASUREDPHASE11THHARMONICCURRENT));
    }

    /**
     * Set reporting for the <i>Measured Phase 11th Harmonic Current</i> attribute [attribute ID <b>0x0312</b>].
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
    public Future<CommandResult> setMeasuredPhase11thHarmonicCurrentReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MEASUREDPHASE11THHARMONICCURRENT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>AC Frequency Multiplier</i> attribute [attribute ID <b>0x0400</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcFrequencyMultiplierAsync() {
        return read(serverAttributes.get(ATTR_ACFREQUENCYMULTIPLIER));
    }

    /**
     * Synchronously get the <i>AC Frequency Multiplier</i> attribute [attribute ID <b>0x0400</b>].
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
    public Integer getAcFrequencyMultiplier(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACFREQUENCYMULTIPLIER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACFREQUENCYMULTIPLIER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACFREQUENCYMULTIPLIER));
    }

    /**
     * Set reporting for the <i>AC Frequency Multiplier</i> attribute [attribute ID <b>0x0400</b>].
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
    public Future<CommandResult> setAcFrequencyMultiplierReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_ACFREQUENCYMULTIPLIER), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>AC Frequency Divisor</i> attribute [attribute ID <b>0x0401</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcFrequencyDivisorAsync() {
        return read(serverAttributes.get(ATTR_ACFREQUENCYDIVISOR));
    }

    /**
     * Synchronously get the <i>AC Frequency Divisor</i> attribute [attribute ID <b>0x0401</b>].
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
    public Integer getAcFrequencyDivisor(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACFREQUENCYDIVISOR).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACFREQUENCYDIVISOR).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACFREQUENCYDIVISOR));
    }

    /**
     * Set reporting for the <i>AC Frequency Divisor</i> attribute [attribute ID <b>0x0401</b>].
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
    public Future<CommandResult> setAcFrequencyDivisorReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_ACFREQUENCYDIVISOR), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Power Multiplier</i> attribute [attribute ID <b>0x0402</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPowerMultiplierAsync() {
        return read(serverAttributes.get(ATTR_POWERMULTIPLIER));
    }

    /**
     * Synchronously get the <i>Power Multiplier</i> attribute [attribute ID <b>0x0402</b>].
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
    public Integer getPowerMultiplier(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_POWERMULTIPLIER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_POWERMULTIPLIER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_POWERMULTIPLIER));
    }

    /**
     * Set reporting for the <i>Power Multiplier</i> attribute [attribute ID <b>0x0402</b>].
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
    public Future<CommandResult> setPowerMultiplierReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_POWERMULTIPLIER), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Power Divisor</i> attribute [attribute ID <b>0x0403</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPowerDivisorAsync() {
        return read(serverAttributes.get(ATTR_POWERDIVISOR));
    }

    /**
     * Synchronously get the <i>Power Divisor</i> attribute [attribute ID <b>0x0403</b>].
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
    public Integer getPowerDivisor(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_POWERDIVISOR).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_POWERDIVISOR).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_POWERDIVISOR));
    }

    /**
     * Set reporting for the <i>Power Divisor</i> attribute [attribute ID <b>0x0403</b>].
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
    public Future<CommandResult> setPowerDivisorReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_POWERDIVISOR), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Harmonic Current Multiplier</i> attribute [attribute ID <b>0x0404</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getHarmonicCurrentMultiplierAsync() {
        return read(serverAttributes.get(ATTR_HARMONICCURRENTMULTIPLIER));
    }

    /**
     * Synchronously get the <i>Harmonic Current Multiplier</i> attribute [attribute ID <b>0x0404</b>].
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
    public Integer getHarmonicCurrentMultiplier(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_HARMONICCURRENTMULTIPLIER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_HARMONICCURRENTMULTIPLIER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_HARMONICCURRENTMULTIPLIER));
    }

    /**
     * Set reporting for the <i>Harmonic Current Multiplier</i> attribute [attribute ID <b>0x0404</b>].
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
    public Future<CommandResult> setHarmonicCurrentMultiplierReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_HARMONICCURRENTMULTIPLIER), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Phase Harmonic Current Multiplier</i> attribute [attribute ID <b>0x0405</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPhaseHarmonicCurrentMultiplierAsync() {
        return read(serverAttributes.get(ATTR_PHASEHARMONICCURRENTMULTIPLIER));
    }

    /**
     * Synchronously get the <i>Phase Harmonic Current Multiplier</i> attribute [attribute ID <b>0x0405</b>].
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
    public Integer getPhaseHarmonicCurrentMultiplier(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_PHASEHARMONICCURRENTMULTIPLIER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_PHASEHARMONICCURRENTMULTIPLIER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_PHASEHARMONICCURRENTMULTIPLIER));
    }

    /**
     * Set reporting for the <i>Phase Harmonic Current Multiplier</i> attribute [attribute ID <b>0x0405</b>].
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
    public Future<CommandResult> setPhaseHarmonicCurrentMultiplierReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_PHASEHARMONICCURRENTMULTIPLIER), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Instantaneous Voltage</i> attribute [attribute ID <b>0x0500</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getInstantaneousVoltageAsync() {
        return read(serverAttributes.get(ATTR_INSTANTANEOUSVOLTAGE));
    }

    /**
     * Synchronously get the <i>Instantaneous Voltage</i> attribute [attribute ID <b>0x0500</b>].
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
    public Integer getInstantaneousVoltage(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_INSTANTANEOUSVOLTAGE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_INSTANTANEOUSVOLTAGE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_INSTANTANEOUSVOLTAGE));
    }

    /**
     * Set reporting for the <i>Instantaneous Voltage</i> attribute [attribute ID <b>0x0500</b>].
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
    public Future<CommandResult> setInstantaneousVoltageReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_INSTANTANEOUSVOLTAGE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Instantaneous Line Current</i> attribute [attribute ID <b>0x0501</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getInstantaneousLineCurrentAsync() {
        return read(serverAttributes.get(ATTR_INSTANTANEOUSLINECURRENT));
    }

    /**
     * Synchronously get the <i>Instantaneous Line Current</i> attribute [attribute ID <b>0x0501</b>].
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
    public Integer getInstantaneousLineCurrent(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_INSTANTANEOUSLINECURRENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_INSTANTANEOUSLINECURRENT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_INSTANTANEOUSLINECURRENT));
    }

    /**
     * Set reporting for the <i>Instantaneous Line Current</i> attribute [attribute ID <b>0x0501</b>].
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
    public Future<CommandResult> setInstantaneousLineCurrentReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_INSTANTANEOUSLINECURRENT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Instantaneous Active Current</i> attribute [attribute ID <b>0x0502</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getInstantaneousActiveCurrentAsync() {
        return read(serverAttributes.get(ATTR_INSTANTANEOUSACTIVECURRENT));
    }

    /**
     * Synchronously get the <i>Instantaneous Active Current</i> attribute [attribute ID <b>0x0502</b>].
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
    public Integer getInstantaneousActiveCurrent(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_INSTANTANEOUSACTIVECURRENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_INSTANTANEOUSACTIVECURRENT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_INSTANTANEOUSACTIVECURRENT));
    }

    /**
     * Set reporting for the <i>Instantaneous Active Current</i> attribute [attribute ID <b>0x0502</b>].
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
    public Future<CommandResult> setInstantaneousActiveCurrentReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_INSTANTANEOUSACTIVECURRENT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Instantaneous Reactive Current</i> attribute [attribute ID <b>0x0503</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getInstantaneousReactiveCurrentAsync() {
        return read(serverAttributes.get(ATTR_INSTANTANEOUSREACTIVECURRENT));
    }

    /**
     * Synchronously get the <i>Instantaneous Reactive Current</i> attribute [attribute ID <b>0x0503</b>].
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
    public Integer getInstantaneousReactiveCurrent(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_INSTANTANEOUSREACTIVECURRENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_INSTANTANEOUSREACTIVECURRENT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_INSTANTANEOUSREACTIVECURRENT));
    }

    /**
     * Set reporting for the <i>Instantaneous Reactive Current</i> attribute [attribute ID <b>0x0503</b>].
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
    public Future<CommandResult> setInstantaneousReactiveCurrentReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_INSTANTANEOUSREACTIVECURRENT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Instantaneous Power</i> attribute [attribute ID <b>0x0504</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getInstantaneousPowerAsync() {
        return read(serverAttributes.get(ATTR_INSTANTANEOUSPOWER));
    }

    /**
     * Synchronously get the <i>Instantaneous Power</i> attribute [attribute ID <b>0x0504</b>].
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
    public Integer getInstantaneousPower(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_INSTANTANEOUSPOWER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_INSTANTANEOUSPOWER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_INSTANTANEOUSPOWER));
    }

    /**
     * Set reporting for the <i>Instantaneous Power</i> attribute [attribute ID <b>0x0504</b>].
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
    public Future<CommandResult> setInstantaneousPowerReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_INSTANTANEOUSPOWER), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Voltage</i> attribute [attribute ID <b>0x0505</b>].
     * <p>
     * Represents the most recent RMS voltage reading in Volts (V). If the RMS voltage cannot be
     * measured, a value of 0xFFFF is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsVoltageAsync() {
        return read(serverAttributes.get(ATTR_RMSVOLTAGE));
    }

    /**
     * Synchronously get the <i>RMS Voltage</i> attribute [attribute ID <b>0x0505</b>].
     * <p>
     * Represents the most recent RMS voltage reading in Volts (V). If the RMS voltage cannot be
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getRmsVoltage(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSVOLTAGE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSVOLTAGE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSVOLTAGE));
    }

    /**
     * Get the <i>RMS Voltage Min</i> attribute [attribute ID <b>0x0506</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsVoltageMinAsync() {
        return read(serverAttributes.get(ATTR_RMSVOLTAGEMIN));
    }

    /**
     * Synchronously get the <i>RMS Voltage Min</i> attribute [attribute ID <b>0x0506</b>].
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
    public Integer getRmsVoltageMin(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSVOLTAGEMIN).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSVOLTAGEMIN).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSVOLTAGEMIN));
    }

    /**
     * Set reporting for the <i>RMS Voltage Min</i> attribute [attribute ID <b>0x0506</b>].
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
    public Future<CommandResult> setRmsVoltageMinReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSVOLTAGEMIN), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Voltage Max</i> attribute [attribute ID <b>0x0507</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsVoltageMaxAsync() {
        return read(serverAttributes.get(ATTR_RMSVOLTAGEMAX));
    }

    /**
     * Synchronously get the <i>RMS Voltage Max</i> attribute [attribute ID <b>0x0507</b>].
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
    public Integer getRmsVoltageMax(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSVOLTAGEMAX).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSVOLTAGEMAX).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSVOLTAGEMAX));
    }

    /**
     * Set reporting for the <i>RMS Voltage Max</i> attribute [attribute ID <b>0x0507</b>].
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
    public Future<CommandResult> setRmsVoltageMaxReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSVOLTAGEMAX), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Current</i> attribute [attribute ID <b>0x0508</b>].
     * <p>
     * Represents the most recent RMS current reading in Amps (A). If the power cannot be
     * measured, a value of 0xFFFF is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsCurrentAsync() {
        return read(serverAttributes.get(ATTR_RMSCURRENT));
    }

    /**
     * Synchronously get the <i>RMS Current</i> attribute [attribute ID <b>0x0508</b>].
     * <p>
     * Represents the most recent RMS current reading in Amps (A). If the power cannot be
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getRmsCurrent(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSCURRENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSCURRENT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSCURRENT));
    }

    /**
     * Get the <i>RMS Current Min</i> attribute [attribute ID <b>0x0509</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsCurrentMinAsync() {
        return read(serverAttributes.get(ATTR_RMSCURRENTMIN));
    }

    /**
     * Synchronously get the <i>RMS Current Min</i> attribute [attribute ID <b>0x0509</b>].
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
    public Integer getRmsCurrentMin(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSCURRENTMIN).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSCURRENTMIN).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSCURRENTMIN));
    }

    /**
     * Set reporting for the <i>RMS Current Min</i> attribute [attribute ID <b>0x0509</b>].
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
    public Future<CommandResult> setRmsCurrentMinReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSCURRENTMIN), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Current Max</i> attribute [attribute ID <b>0x050A</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsCurrentMaxAsync() {
        return read(serverAttributes.get(ATTR_RMSCURRENTMAX));
    }

    /**
     * Synchronously get the <i>RMS Current Max</i> attribute [attribute ID <b>0x050A</b>].
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
    public Integer getRmsCurrentMax(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSCURRENTMAX).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSCURRENTMAX).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSCURRENTMAX));
    }

    /**
     * Set reporting for the <i>RMS Current Max</i> attribute [attribute ID <b>0x050A</b>].
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
    public Future<CommandResult> setRmsCurrentMaxReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSCURRENTMAX), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Active Power</i> attribute [attribute ID <b>0x050B</b>].
     * <p>
     * Represents the single phase or Phase A, current demand of active power delivered or
     * received at the premises, in Watts (W). Positive values indicate power delivered to the
     * premises where negative values indicate power received from the premises.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getActivePowerAsync() {
        return read(serverAttributes.get(ATTR_ACTIVEPOWER));
    }

    /**
     * Synchronously get the <i>Active Power</i> attribute [attribute ID <b>0x050B</b>].
     * <p>
     * Represents the single phase or Phase A, current demand of active power delivered or
     * received at the premises, in Watts (W). Positive values indicate power delivered to the
     * premises where negative values indicate power received from the premises.
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
    public Integer getActivePower(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACTIVEPOWER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACTIVEPOWER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACTIVEPOWER));
    }

    /**
     * Get the <i>Active Power Min</i> attribute [attribute ID <b>0x050C</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getActivePowerMinAsync() {
        return read(serverAttributes.get(ATTR_ACTIVEPOWERMIN));
    }

    /**
     * Synchronously get the <i>Active Power Min</i> attribute [attribute ID <b>0x050C</b>].
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
    public Integer getActivePowerMin(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACTIVEPOWERMIN).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACTIVEPOWERMIN).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACTIVEPOWERMIN));
    }

    /**
     * Set reporting for the <i>Active Power Min</i> attribute [attribute ID <b>0x050C</b>].
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
    public Future<CommandResult> setActivePowerMinReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_ACTIVEPOWERMIN), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Active Power Max</i> attribute [attribute ID <b>0x050D</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getActivePowerMaxAsync() {
        return read(serverAttributes.get(ATTR_ACTIVEPOWERMAX));
    }

    /**
     * Synchronously get the <i>Active Power Max</i> attribute [attribute ID <b>0x050D</b>].
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
    public Integer getActivePowerMax(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACTIVEPOWERMAX).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACTIVEPOWERMAX).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACTIVEPOWERMAX));
    }

    /**
     * Set reporting for the <i>Active Power Max</i> attribute [attribute ID <b>0x050D</b>].
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
    public Future<CommandResult> setActivePowerMaxReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_ACTIVEPOWERMAX), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Reactive Power</i> attribute [attribute ID <b>0x050E</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getReactivePowerAsync() {
        return read(serverAttributes.get(ATTR_REACTIVEPOWER));
    }

    /**
     * Synchronously get the <i>Reactive Power</i> attribute [attribute ID <b>0x050E</b>].
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
    public Integer getReactivePower(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_REACTIVEPOWER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_REACTIVEPOWER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_REACTIVEPOWER));
    }

    /**
     * Set reporting for the <i>Reactive Power</i> attribute [attribute ID <b>0x050E</b>].
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
    public Future<CommandResult> setReactivePowerReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_REACTIVEPOWER), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Apparent Power</i> attribute [attribute ID <b>0x050F</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getApparentPowerAsync() {
        return read(serverAttributes.get(ATTR_APPARENTPOWER));
    }

    /**
     * Synchronously get the <i>Apparent Power</i> attribute [attribute ID <b>0x050F</b>].
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
    public Integer getApparentPower(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_APPARENTPOWER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_APPARENTPOWER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_APPARENTPOWER));
    }

    /**
     * Set reporting for the <i>Apparent Power</i> attribute [attribute ID <b>0x050F</b>].
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
    public Future<CommandResult> setApparentPowerReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_APPARENTPOWER), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Power Factor</i> attribute [attribute ID <b>0x0510</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPowerFactorAsync() {
        return read(serverAttributes.get(ATTR_POWERFACTOR));
    }

    /**
     * Synchronously get the <i>Power Factor</i> attribute [attribute ID <b>0x0510</b>].
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
    public Integer getPowerFactor(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_POWERFACTOR).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_POWERFACTOR).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_POWERFACTOR));
    }

    /**
     * Set reporting for the <i>Power Factor</i> attribute [attribute ID <b>0x0510</b>].
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
    public Future<CommandResult> setPowerFactorReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_POWERFACTOR), minInterval, maxInterval, reportableChange);
    }

    /**
     * Set the <i>Average RMS Voltage Measurement Period</i> attribute [attribute ID <b>0x0511</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param averageRmsVoltageMeasurementPeriod the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setAverageRmsVoltageMeasurementPeriod(final Integer value) {
        return write(serverAttributes.get(ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIOD), value);
    }

    /**
     * Get the <i>Average RMS Voltage Measurement Period</i> attribute [attribute ID <b>0x0511</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAverageRmsVoltageMeasurementPeriodAsync() {
        return read(serverAttributes.get(ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIOD));
    }

    /**
     * Synchronously get the <i>Average RMS Voltage Measurement Period</i> attribute [attribute ID <b>0x0511</b>].
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
    public Integer getAverageRmsVoltageMeasurementPeriod(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIOD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIOD).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIOD));
    }

    /**
     * Set the <i>Average RMS Under Voltage Counter</i> attribute [attribute ID <b>0x0513</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param averageRmsUnderVoltageCounter the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setAverageRmsUnderVoltageCounter(final Integer value) {
        return write(serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGECOUNTER), value);
    }

    /**
     * Get the <i>Average RMS Under Voltage Counter</i> attribute [attribute ID <b>0x0513</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAverageRmsUnderVoltageCounterAsync() {
        return read(serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGECOUNTER));
    }

    /**
     * Synchronously get the <i>Average RMS Under Voltage Counter</i> attribute [attribute ID <b>0x0513</b>].
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
    public Integer getAverageRmsUnderVoltageCounter(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGECOUNTER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGECOUNTER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGECOUNTER));
    }

    /**
     * Set the <i>RMS Extreme Over Voltage Period</i> attribute [attribute ID <b>0x0514</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param rmsExtremeOverVoltagePeriod the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setRmsExtremeOverVoltagePeriod(final Integer value) {
        return write(serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGEPERIOD), value);
    }

    /**
     * Get the <i>RMS Extreme Over Voltage Period</i> attribute [attribute ID <b>0x0514</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsExtremeOverVoltagePeriodAsync() {
        return read(serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGEPERIOD));
    }

    /**
     * Synchronously get the <i>RMS Extreme Over Voltage Period</i> attribute [attribute ID <b>0x0514</b>].
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
    public Integer getRmsExtremeOverVoltagePeriod(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGEPERIOD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGEPERIOD).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGEPERIOD));
    }

    /**
     * Set the <i>RMS Extreme Under Voltage Period</i> attribute [attribute ID <b>0x0515</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param rmsExtremeUnderVoltagePeriod the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setRmsExtremeUnderVoltagePeriod(final Integer value) {
        return write(serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGEPERIOD), value);
    }

    /**
     * Get the <i>RMS Extreme Under Voltage Period</i> attribute [attribute ID <b>0x0515</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsExtremeUnderVoltagePeriodAsync() {
        return read(serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGEPERIOD));
    }

    /**
     * Synchronously get the <i>RMS Extreme Under Voltage Period</i> attribute [attribute ID <b>0x0515</b>].
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
    public Integer getRmsExtremeUnderVoltagePeriod(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGEPERIOD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGEPERIOD).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGEPERIOD));
    }

    /**
     * Set the <i>RMS Voltage Sag Period</i> attribute [attribute ID <b>0x0516</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param rmsVoltageSagPeriod the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setRmsVoltageSagPeriod(final Integer value) {
        return write(serverAttributes.get(ATTR_RMSVOLTAGESAGPERIOD), value);
    }

    /**
     * Get the <i>RMS Voltage Sag Period</i> attribute [attribute ID <b>0x0516</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsVoltageSagPeriodAsync() {
        return read(serverAttributes.get(ATTR_RMSVOLTAGESAGPERIOD));
    }

    /**
     * Synchronously get the <i>RMS Voltage Sag Period</i> attribute [attribute ID <b>0x0516</b>].
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
    public Integer getRmsVoltageSagPeriod(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSVOLTAGESAGPERIOD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSVOLTAGESAGPERIOD).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSVOLTAGESAGPERIOD));
    }

    /**
     * Set the <i>RMS Voltage Swell Period</i> attribute [attribute ID <b>0x0517</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param rmsVoltageSwellPeriod the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setRmsVoltageSwellPeriod(final Integer value) {
        return write(serverAttributes.get(ATTR_RMSVOLTAGESWELLPERIOD), value);
    }

    /**
     * Get the <i>RMS Voltage Swell Period</i> attribute [attribute ID <b>0x0517</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsVoltageSwellPeriodAsync() {
        return read(serverAttributes.get(ATTR_RMSVOLTAGESWELLPERIOD));
    }

    /**
     * Synchronously get the <i>RMS Voltage Swell Period</i> attribute [attribute ID <b>0x0517</b>].
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
    public Integer getRmsVoltageSwellPeriod(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSVOLTAGESWELLPERIOD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSVOLTAGESWELLPERIOD).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSVOLTAGESWELLPERIOD));
    }

    /**
     * Get the <i>AC Voltage Multiplier</i> attribute [attribute ID <b>0x0600</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcVoltageMultiplierAsync() {
        return read(serverAttributes.get(ATTR_ACVOLTAGEMULTIPLIER));
    }

    /**
     * Synchronously get the <i>AC Voltage Multiplier</i> attribute [attribute ID <b>0x0600</b>].
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
    public Integer getAcVoltageMultiplier(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACVOLTAGEMULTIPLIER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACVOLTAGEMULTIPLIER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACVOLTAGEMULTIPLIER));
    }

    /**
     * Get the <i>AC Voltage Divisor</i> attribute [attribute ID <b>0x0601</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcVoltageDivisorAsync() {
        return read(serverAttributes.get(ATTR_ACVOLTAGEDIVISOR));
    }

    /**
     * Synchronously get the <i>AC Voltage Divisor</i> attribute [attribute ID <b>0x0601</b>].
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
    public Integer getAcVoltageDivisor(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACVOLTAGEDIVISOR).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACVOLTAGEDIVISOR).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACVOLTAGEDIVISOR));
    }

    /**
     * Get the <i>AC Current Multiplier</i> attribute [attribute ID <b>0x0602</b>].
     * <p>
     * Provides a value to be multiplied against the InstantaneousCurrent and
     * RMSCurrentattributes. his attribute must be used in conjunction with the
     * ACCurrentDivisorattribute. 0x0000 is an invalid value for this attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcCurrentMultiplierAsync() {
        return read(serverAttributes.get(ATTR_ACCURRENTMULTIPLIER));
    }

    /**
     * Synchronously get the <i>AC Current Multiplier</i> attribute [attribute ID <b>0x0602</b>].
     * <p>
     * Provides a value to be multiplied against the InstantaneousCurrent and
     * RMSCurrentattributes. his attribute must be used in conjunction with the
     * ACCurrentDivisorattribute. 0x0000 is an invalid value for this attribute.
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
    public Integer getAcCurrentMultiplier(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACCURRENTMULTIPLIER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACCURRENTMULTIPLIER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACCURRENTMULTIPLIER));
    }

    /**
     * Get the <i>AC Current Divisor</i> attribute [attribute ID <b>0x0603</b>].
     * <p>
     * Provides a value to be divided against the ACCurrent, InstantaneousCurrent and
     * RMSCurrentattributes. This attribute must be used in conjunction with the
     * ACCurrentMultiplierattribute 0x0000 is an invalid value for this attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcCurrentDivisorAsync() {
        return read(serverAttributes.get(ATTR_ACCURRENTDIVISOR));
    }

    /**
     * Synchronously get the <i>AC Current Divisor</i> attribute [attribute ID <b>0x0603</b>].
     * <p>
     * Provides a value to be divided against the ACCurrent, InstantaneousCurrent and
     * RMSCurrentattributes. This attribute must be used in conjunction with the
     * ACCurrentMultiplierattribute 0x0000 is an invalid value for this attribute.
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
    public Integer getAcCurrentDivisor(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACCURRENTDIVISOR).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACCURRENTDIVISOR).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACCURRENTDIVISOR));
    }

    /**
     * Get the <i>AC Power Multiplier</i> attribute [attribute ID <b>0x0604</b>].
     * <p>
     * Provides a value to be multiplied against the InstantaneousPower and
     * ActivePowerattributes. This attribute must be used in conjunction with the
     * ACPowerDivisorattribute. 0x0000 is an invalid value for this attribute
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcPowerMultiplierAsync() {
        return read(serverAttributes.get(ATTR_ACPOWERMULTIPLIER));
    }

    /**
     * Synchronously get the <i>AC Power Multiplier</i> attribute [attribute ID <b>0x0604</b>].
     * <p>
     * Provides a value to be multiplied against the InstantaneousPower and
     * ActivePowerattributes. This attribute must be used in conjunction with the
     * ACPowerDivisorattribute. 0x0000 is an invalid value for this attribute
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
    public Integer getAcPowerMultiplier(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACPOWERMULTIPLIER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACPOWERMULTIPLIER).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACPOWERMULTIPLIER));
    }

    /**
     * Get the <i>AC Power Divisor</i> attribute [attribute ID <b>0x0605</b>].
     * <p>
     * Provides a value to be divided against the InstantaneousPower and
     * ActivePowerattributes. This attribute must be used in conjunction with the
     * ACPowerMultiplierattribute. 0x0000 is an invalid value for this attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcPowerDivisorAsync() {
        return read(serverAttributes.get(ATTR_ACPOWERDIVISOR));
    }

    /**
     * Synchronously get the <i>AC Power Divisor</i> attribute [attribute ID <b>0x0605</b>].
     * <p>
     * Provides a value to be divided against the InstantaneousPower and
     * ActivePowerattributes. This attribute must be used in conjunction with the
     * ACPowerMultiplierattribute. 0x0000 is an invalid value for this attribute.
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
    public Integer getAcPowerDivisor(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACPOWERDIVISOR).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACPOWERDIVISOR).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACPOWERDIVISOR));
    }

    /**
     * Set the <i>Overload Alarms Mask</i> attribute [attribute ID <b>0x0700</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param overloadAlarmsMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setOverloadAlarmsMask(final Integer value) {
        return write(serverAttributes.get(ATTR_OVERLOADALARMSMASK), value);
    }

    /**
     * Get the <i>Overload Alarms Mask</i> attribute [attribute ID <b>0x0700</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getOverloadAlarmsMaskAsync() {
        return read(serverAttributes.get(ATTR_OVERLOADALARMSMASK));
    }

    /**
     * Synchronously get the <i>Overload Alarms Mask</i> attribute [attribute ID <b>0x0700</b>].
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
    public Integer getOverloadAlarmsMask(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_OVERLOADALARMSMASK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_OVERLOADALARMSMASK).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_OVERLOADALARMSMASK));
    }

    /**
     * Get the <i>Voltage Overload</i> attribute [attribute ID <b>0x0701</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getVoltageOverloadAsync() {
        return read(serverAttributes.get(ATTR_VOLTAGEOVERLOAD));
    }

    /**
     * Synchronously get the <i>Voltage Overload</i> attribute [attribute ID <b>0x0701</b>].
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
    public Integer getVoltageOverload(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_VOLTAGEOVERLOAD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_VOLTAGEOVERLOAD).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_VOLTAGEOVERLOAD));
    }

    /**
     * Set reporting for the <i>Voltage Overload</i> attribute [attribute ID <b>0x0701</b>].
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
    public Future<CommandResult> setVoltageOverloadReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_VOLTAGEOVERLOAD), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Current Overload</i> attribute [attribute ID <b>0x0702</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getCurrentOverloadAsync() {
        return read(serverAttributes.get(ATTR_CURRENTOVERLOAD));
    }

    /**
     * Synchronously get the <i>Current Overload</i> attribute [attribute ID <b>0x0702</b>].
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
    public Integer getCurrentOverload(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_CURRENTOVERLOAD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_CURRENTOVERLOAD).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_CURRENTOVERLOAD));
    }

    /**
     * Set reporting for the <i>Current Overload</i> attribute [attribute ID <b>0x0702</b>].
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
    public Future<CommandResult> setCurrentOverloadReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_CURRENTOVERLOAD), minInterval, maxInterval, reportableChange);
    }

    /**
     * Set the <i>AC Overload Alarms Mask</i> attribute [attribute ID <b>0x0800</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param acOverloadAlarmsMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setAcOverloadAlarmsMask(final Integer value) {
        return write(serverAttributes.get(ATTR_ACOVERLOADALARMSMASK), value);
    }

    /**
     * Get the <i>AC Overload Alarms Mask</i> attribute [attribute ID <b>0x0800</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcOverloadAlarmsMaskAsync() {
        return read(serverAttributes.get(ATTR_ACOVERLOADALARMSMASK));
    }

    /**
     * Synchronously get the <i>AC Overload Alarms Mask</i> attribute [attribute ID <b>0x0800</b>].
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
    public Integer getAcOverloadAlarmsMask(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACOVERLOADALARMSMASK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACOVERLOADALARMSMASK).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACOVERLOADALARMSMASK));
    }

    /**
     * Get the <i>AC Voltage Overload</i> attribute [attribute ID <b>0x0801</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcVoltageOverloadAsync() {
        return read(serverAttributes.get(ATTR_ACVOLTAGEOVERLOAD));
    }

    /**
     * Synchronously get the <i>AC Voltage Overload</i> attribute [attribute ID <b>0x0801</b>].
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
    public Integer getAcVoltageOverload(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACVOLTAGEOVERLOAD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACVOLTAGEOVERLOAD).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACVOLTAGEOVERLOAD));
    }

    /**
     * Set reporting for the <i>AC Voltage Overload</i> attribute [attribute ID <b>0x0801</b>].
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
    public Future<CommandResult> setAcVoltageOverloadReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_ACVOLTAGEOVERLOAD), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>AC Current Overload</i> attribute [attribute ID <b>0x0802</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcCurrentOverloadAsync() {
        return read(serverAttributes.get(ATTR_ACCURRENTOVERLOAD));
    }

    /**
     * Synchronously get the <i>AC Current Overload</i> attribute [attribute ID <b>0x0802</b>].
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
    public Integer getAcCurrentOverload(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACCURRENTOVERLOAD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACCURRENTOVERLOAD).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACCURRENTOVERLOAD));
    }

    /**
     * Set reporting for the <i>AC Current Overload</i> attribute [attribute ID <b>0x0802</b>].
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
    public Future<CommandResult> setAcCurrentOverloadReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_ACCURRENTOVERLOAD), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>AC Active Power Overload</i> attribute [attribute ID <b>0x0803</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcActivePowerOverloadAsync() {
        return read(serverAttributes.get(ATTR_ACACTIVEPOWEROVERLOAD));
    }

    /**
     * Synchronously get the <i>AC Active Power Overload</i> attribute [attribute ID <b>0x0803</b>].
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
    public Integer getAcActivePowerOverload(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACACTIVEPOWEROVERLOAD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACACTIVEPOWEROVERLOAD).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACACTIVEPOWEROVERLOAD));
    }

    /**
     * Set reporting for the <i>AC Active Power Overload</i> attribute [attribute ID <b>0x0803</b>].
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
    public Future<CommandResult> setAcActivePowerOverloadReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_ACACTIVEPOWEROVERLOAD), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>AC Reactive Power Overload</i> attribute [attribute ID <b>0x0804</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAcReactivePowerOverloadAsync() {
        return read(serverAttributes.get(ATTR_ACREACTIVEPOWEROVERLOAD));
    }

    /**
     * Synchronously get the <i>AC Reactive Power Overload</i> attribute [attribute ID <b>0x0804</b>].
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
    public Integer getAcReactivePowerOverload(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACREACTIVEPOWEROVERLOAD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACREACTIVEPOWEROVERLOAD).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACREACTIVEPOWEROVERLOAD));
    }

    /**
     * Set reporting for the <i>AC Reactive Power Overload</i> attribute [attribute ID <b>0x0804</b>].
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
    public Future<CommandResult> setAcReactivePowerOverloadReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_ACREACTIVEPOWEROVERLOAD), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Average RMS Over Voltage</i> attribute [attribute ID <b>0x0805</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAverageRmsOverVoltageAsync() {
        return read(serverAttributes.get(ATTR_AVERAGERMSOVERVOLTAGE));
    }

    /**
     * Synchronously get the <i>Average RMS Over Voltage</i> attribute [attribute ID <b>0x0805</b>].
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
    public Integer getAverageRmsOverVoltage(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_AVERAGERMSOVERVOLTAGE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_AVERAGERMSOVERVOLTAGE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_AVERAGERMSOVERVOLTAGE));
    }

    /**
     * Set reporting for the <i>Average RMS Over Voltage</i> attribute [attribute ID <b>0x0805</b>].
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
    public Future<CommandResult> setAverageRmsOverVoltageReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_AVERAGERMSOVERVOLTAGE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Average RMS Under Voltage</i> attribute [attribute ID <b>0x0806</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAverageRmsUnderVoltageAsync() {
        return read(serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGE));
    }

    /**
     * Synchronously get the <i>Average RMS Under Voltage</i> attribute [attribute ID <b>0x0806</b>].
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
    public Integer getAverageRmsUnderVoltage(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGE));
    }

    /**
     * Set reporting for the <i>Average RMS Under Voltage</i> attribute [attribute ID <b>0x0806</b>].
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
    public Future<CommandResult> setAverageRmsUnderVoltageReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Extreme Over Voltage</i> attribute [attribute ID <b>0x0807</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsExtremeOverVoltageAsync() {
        return read(serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGE));
    }

    /**
     * Synchronously get the <i>RMS Extreme Over Voltage</i> attribute [attribute ID <b>0x0807</b>].
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
    public Integer getRmsExtremeOverVoltage(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGE));
    }

    /**
     * Set reporting for the <i>RMS Extreme Over Voltage</i> attribute [attribute ID <b>0x0807</b>].
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
    public Future<CommandResult> setRmsExtremeOverVoltageReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Extreme Under Voltage</i> attribute [attribute ID <b>0x0808</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsExtremeUnderVoltageAsync() {
        return read(serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGE));
    }

    /**
     * Synchronously get the <i>RMS Extreme Under Voltage</i> attribute [attribute ID <b>0x0808</b>].
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
    public Integer getRmsExtremeUnderVoltage(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGE));
    }

    /**
     * Set reporting for the <i>RMS Extreme Under Voltage</i> attribute [attribute ID <b>0x0808</b>].
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
    public Future<CommandResult> setRmsExtremeUnderVoltageReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Voltage Sag</i> attribute [attribute ID <b>0x0809</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsVoltageSagAsync() {
        return read(serverAttributes.get(ATTR_RMSVOLTAGESAG));
    }

    /**
     * Synchronously get the <i>RMS Voltage Sag</i> attribute [attribute ID <b>0x0809</b>].
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
    public Integer getRmsVoltageSag(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSVOLTAGESAG).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSVOLTAGESAG).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSVOLTAGESAG));
    }

    /**
     * Set reporting for the <i>RMS Voltage Sag</i> attribute [attribute ID <b>0x0809</b>].
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
    public Future<CommandResult> setRmsVoltageSagReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSVOLTAGESAG), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Voltage Swell</i> attribute [attribute ID <b>0x080A</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsVoltageSwellAsync() {
        return read(serverAttributes.get(ATTR_RMSVOLTAGESWELL));
    }

    /**
     * Synchronously get the <i>RMS Voltage Swell</i> attribute [attribute ID <b>0x080A</b>].
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
    public Integer getRmsVoltageSwell(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSVOLTAGESWELL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSVOLTAGESWELL).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSVOLTAGESWELL));
    }

    /**
     * Set reporting for the <i>RMS Voltage Swell</i> attribute [attribute ID <b>0x080A</b>].
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
    public Future<CommandResult> setRmsVoltageSwellReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSVOLTAGESWELL), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Line Current Phase B</i> attribute [attribute ID <b>0x0901</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getLineCurrentPhaseBAsync() {
        return read(serverAttributes.get(ATTR_LINECURRENTPHASEB));
    }

    /**
     * Synchronously get the <i>Line Current Phase B</i> attribute [attribute ID <b>0x0901</b>].
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
    public Integer getLineCurrentPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_LINECURRENTPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_LINECURRENTPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_LINECURRENTPHASEB));
    }

    /**
     * Set reporting for the <i>Line Current Phase B</i> attribute [attribute ID <b>0x0901</b>].
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
    public Future<CommandResult> setLineCurrentPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_LINECURRENTPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Active Current Phase B</i> attribute [attribute ID <b>0x0902</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getActiveCurrentPhaseBAsync() {
        return read(serverAttributes.get(ATTR_ACTIVECURRENTPHASEB));
    }

    /**
     * Synchronously get the <i>Active Current Phase B</i> attribute [attribute ID <b>0x0902</b>].
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
    public Integer getActiveCurrentPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACTIVECURRENTPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACTIVECURRENTPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACTIVECURRENTPHASEB));
    }

    /**
     * Set reporting for the <i>Active Current Phase B</i> attribute [attribute ID <b>0x0902</b>].
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
    public Future<CommandResult> setActiveCurrentPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_ACTIVECURRENTPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Reactive Current Phase B</i> attribute [attribute ID <b>0x0903</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getReactiveCurrentPhaseBAsync() {
        return read(serverAttributes.get(ATTR_REACTIVECURRENTPHASEB));
    }

    /**
     * Synchronously get the <i>Reactive Current Phase B</i> attribute [attribute ID <b>0x0903</b>].
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
    public Integer getReactiveCurrentPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_REACTIVECURRENTPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_REACTIVECURRENTPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_REACTIVECURRENTPHASEB));
    }

    /**
     * Set reporting for the <i>Reactive Current Phase B</i> attribute [attribute ID <b>0x0903</b>].
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
    public Future<CommandResult> setReactiveCurrentPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_REACTIVECURRENTPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Voltage Phase B</i> attribute [attribute ID <b>0x0905</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsVoltagePhaseBAsync() {
        return read(serverAttributes.get(ATTR_RMSVOLTAGEPHASEB));
    }

    /**
     * Synchronously get the <i>RMS Voltage Phase B</i> attribute [attribute ID <b>0x0905</b>].
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
    public Integer getRmsVoltagePhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSVOLTAGEPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSVOLTAGEPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSVOLTAGEPHASEB));
    }

    /**
     * Set reporting for the <i>RMS Voltage Phase B</i> attribute [attribute ID <b>0x0905</b>].
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
    public Future<CommandResult> setRmsVoltagePhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSVOLTAGEPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Voltage Min Phase B</i> attribute [attribute ID <b>0x0906</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsVoltageMinPhaseBAsync() {
        return read(serverAttributes.get(ATTR_RMSVOLTAGEMINPHASEB));
    }

    /**
     * Synchronously get the <i>RMS Voltage Min Phase B</i> attribute [attribute ID <b>0x0906</b>].
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
    public Integer getRmsVoltageMinPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSVOLTAGEMINPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSVOLTAGEMINPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSVOLTAGEMINPHASEB));
    }

    /**
     * Set reporting for the <i>RMS Voltage Min Phase B</i> attribute [attribute ID <b>0x0906</b>].
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
    public Future<CommandResult> setRmsVoltageMinPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSVOLTAGEMINPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Voltage Max Phase B</i> attribute [attribute ID <b>0x0907</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsVoltageMaxPhaseBAsync() {
        return read(serverAttributes.get(ATTR_RMSVOLTAGEMAXPHASEB));
    }

    /**
     * Synchronously get the <i>RMS Voltage Max Phase B</i> attribute [attribute ID <b>0x0907</b>].
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
    public Integer getRmsVoltageMaxPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSVOLTAGEMAXPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSVOLTAGEMAXPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSVOLTAGEMAXPHASEB));
    }

    /**
     * Set reporting for the <i>RMS Voltage Max Phase B</i> attribute [attribute ID <b>0x0907</b>].
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
    public Future<CommandResult> setRmsVoltageMaxPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSVOLTAGEMAXPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Current Phase B</i> attribute [attribute ID <b>0x0908</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsCurrentPhaseBAsync() {
        return read(serverAttributes.get(ATTR_RMSCURRENTPHASEB));
    }

    /**
     * Synchronously get the <i>RMS Current Phase B</i> attribute [attribute ID <b>0x0908</b>].
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
    public Integer getRmsCurrentPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSCURRENTPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSCURRENTPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSCURRENTPHASEB));
    }

    /**
     * Set reporting for the <i>RMS Current Phase B</i> attribute [attribute ID <b>0x0908</b>].
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
    public Future<CommandResult> setRmsCurrentPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSCURRENTPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Current Min Phase B</i> attribute [attribute ID <b>0x0909</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsCurrentMinPhaseBAsync() {
        return read(serverAttributes.get(ATTR_RMSCURRENTMINPHASEB));
    }

    /**
     * Synchronously get the <i>RMS Current Min Phase B</i> attribute [attribute ID <b>0x0909</b>].
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
    public Integer getRmsCurrentMinPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSCURRENTMINPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSCURRENTMINPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSCURRENTMINPHASEB));
    }

    /**
     * Set reporting for the <i>RMS Current Min Phase B</i> attribute [attribute ID <b>0x0909</b>].
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
    public Future<CommandResult> setRmsCurrentMinPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSCURRENTMINPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Current Max Phase B</i> attribute [attribute ID <b>0x090A</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsCurrentMaxPhaseBAsync() {
        return read(serverAttributes.get(ATTR_RMSCURRENTMAXPHASEB));
    }

    /**
     * Synchronously get the <i>RMS Current Max Phase B</i> attribute [attribute ID <b>0x090A</b>].
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
    public Integer getRmsCurrentMaxPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSCURRENTMAXPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSCURRENTMAXPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSCURRENTMAXPHASEB));
    }

    /**
     * Set reporting for the <i>RMS Current Max Phase B</i> attribute [attribute ID <b>0x090A</b>].
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
    public Future<CommandResult> setRmsCurrentMaxPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSCURRENTMAXPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Active Power Phase B</i> attribute [attribute ID <b>0x090B</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getActivePowerPhaseBAsync() {
        return read(serverAttributes.get(ATTR_ACTIVEPOWERPHASEB));
    }

    /**
     * Synchronously get the <i>Active Power Phase B</i> attribute [attribute ID <b>0x090B</b>].
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
    public Integer getActivePowerPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACTIVEPOWERPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACTIVEPOWERPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACTIVEPOWERPHASEB));
    }

    /**
     * Set reporting for the <i>Active Power Phase B</i> attribute [attribute ID <b>0x090B</b>].
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
    public Future<CommandResult> setActivePowerPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_ACTIVEPOWERPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Active Power Min Phase B</i> attribute [attribute ID <b>0x090C</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getActivePowerMinPhaseBAsync() {
        return read(serverAttributes.get(ATTR_ACTIVEPOWERMINPHASEB));
    }

    /**
     * Synchronously get the <i>Active Power Min Phase B</i> attribute [attribute ID <b>0x090C</b>].
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
    public Integer getActivePowerMinPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACTIVEPOWERMINPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACTIVEPOWERMINPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACTIVEPOWERMINPHASEB));
    }

    /**
     * Set reporting for the <i>Active Power Min Phase B</i> attribute [attribute ID <b>0x090C</b>].
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
    public Future<CommandResult> setActivePowerMinPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_ACTIVEPOWERMINPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Active Power Max Phase B</i> attribute [attribute ID <b>0x090D</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getActivePowerMaxPhaseBAsync() {
        return read(serverAttributes.get(ATTR_ACTIVEPOWERMAXPHASEB));
    }

    /**
     * Synchronously get the <i>Active Power Max Phase B</i> attribute [attribute ID <b>0x090D</b>].
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
    public Integer getActivePowerMaxPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACTIVEPOWERMAXPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACTIVEPOWERMAXPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACTIVEPOWERMAXPHASEB));
    }

    /**
     * Set reporting for the <i>Active Power Max Phase B</i> attribute [attribute ID <b>0x090D</b>].
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
    public Future<CommandResult> setActivePowerMaxPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_ACTIVEPOWERMAXPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Reactive Power Phase B</i> attribute [attribute ID <b>0x090E</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getReactivePowerPhaseBAsync() {
        return read(serverAttributes.get(ATTR_REACTIVEPOWERPHASEB));
    }

    /**
     * Synchronously get the <i>Reactive Power Phase B</i> attribute [attribute ID <b>0x090E</b>].
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
    public Integer getReactivePowerPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_REACTIVEPOWERPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_REACTIVEPOWERPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_REACTIVEPOWERPHASEB));
    }

    /**
     * Set reporting for the <i>Reactive Power Phase B</i> attribute [attribute ID <b>0x090E</b>].
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
    public Future<CommandResult> setReactivePowerPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_REACTIVEPOWERPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Apparent Power Phase B</i> attribute [attribute ID <b>0x090F</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getApparentPowerPhaseBAsync() {
        return read(serverAttributes.get(ATTR_APPARENTPOWERPHASEB));
    }

    /**
     * Synchronously get the <i>Apparent Power Phase B</i> attribute [attribute ID <b>0x090F</b>].
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
    public Integer getApparentPowerPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_APPARENTPOWERPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_APPARENTPOWERPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_APPARENTPOWERPHASEB));
    }

    /**
     * Set reporting for the <i>Apparent Power Phase B</i> attribute [attribute ID <b>0x090F</b>].
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
    public Future<CommandResult> setApparentPowerPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_APPARENTPOWERPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Power Factor Phase B</i> attribute [attribute ID <b>0x0910</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPowerFactorPhaseBAsync() {
        return read(serverAttributes.get(ATTR_POWERFACTORPHASEB));
    }

    /**
     * Synchronously get the <i>Power Factor Phase B</i> attribute [attribute ID <b>0x0910</b>].
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
    public Integer getPowerFactorPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_POWERFACTORPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_POWERFACTORPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_POWERFACTORPHASEB));
    }

    /**
     * Set reporting for the <i>Power Factor Phase B</i> attribute [attribute ID <b>0x0910</b>].
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
    public Future<CommandResult> setPowerFactorPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_POWERFACTORPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Average RMS Voltage Measurement Period Phase B</i> attribute [attribute ID <b>0x0911</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAverageRmsVoltageMeasurementPeriodPhaseBAsync() {
        return read(serverAttributes.get(ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIODPHASEB));
    }

    /**
     * Synchronously get the <i>Average RMS Voltage Measurement Period Phase B</i> attribute [attribute ID <b>0x0911</b>].
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
    public Integer getAverageRmsVoltageMeasurementPeriodPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIODPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIODPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIODPHASEB));
    }

    /**
     * Set reporting for the <i>Average RMS Voltage Measurement Period Phase B</i> attribute [attribute ID <b>0x0911</b>].
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
    public Future<CommandResult> setAverageRmsVoltageMeasurementPeriodPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIODPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Average RMS Over Voltage Counter Phase B</i> attribute [attribute ID <b>0x0912</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAverageRmsOverVoltageCounterPhaseBAsync() {
        return read(serverAttributes.get(ATTR_AVERAGERMSOVERVOLTAGECOUNTERPHASEB));
    }

    /**
     * Synchronously get the <i>Average RMS Over Voltage Counter Phase B</i> attribute [attribute ID <b>0x0912</b>].
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
    public Integer getAverageRmsOverVoltageCounterPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_AVERAGERMSOVERVOLTAGECOUNTERPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_AVERAGERMSOVERVOLTAGECOUNTERPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_AVERAGERMSOVERVOLTAGECOUNTERPHASEB));
    }

    /**
     * Set reporting for the <i>Average RMS Over Voltage Counter Phase B</i> attribute [attribute ID <b>0x0912</b>].
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
    public Future<CommandResult> setAverageRmsOverVoltageCounterPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_AVERAGERMSOVERVOLTAGECOUNTERPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Average RMS Under Voltage Counter Phase B</i> attribute [attribute ID <b>0x0913</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAverageRmsUnderVoltageCounterPhaseBAsync() {
        return read(serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGECOUNTERPHASEB));
    }

    /**
     * Synchronously get the <i>Average RMS Under Voltage Counter Phase B</i> attribute [attribute ID <b>0x0913</b>].
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
    public Integer getAverageRmsUnderVoltageCounterPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGECOUNTERPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGECOUNTERPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGECOUNTERPHASEB));
    }

    /**
     * Set reporting for the <i>Average RMS Under Voltage Counter Phase B</i> attribute [attribute ID <b>0x0913</b>].
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
    public Future<CommandResult> setAverageRmsUnderVoltageCounterPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGECOUNTERPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Extreme Over Voltage Period Phase B</i> attribute [attribute ID <b>0x0914</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsExtremeOverVoltagePeriodPhaseBAsync() {
        return read(serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGEPERIODPHASEB));
    }

    /**
     * Synchronously get the <i>RMS Extreme Over Voltage Period Phase B</i> attribute [attribute ID <b>0x0914</b>].
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
    public Integer getRmsExtremeOverVoltagePeriodPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGEPERIODPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGEPERIODPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGEPERIODPHASEB));
    }

    /**
     * Set reporting for the <i>RMS Extreme Over Voltage Period Phase B</i> attribute [attribute ID <b>0x0914</b>].
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
    public Future<CommandResult> setRmsExtremeOverVoltagePeriodPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGEPERIODPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Extreme Under Voltage Period Phase B</i> attribute [attribute ID <b>0x0915</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsExtremeUnderVoltagePeriodPhaseBAsync() {
        return read(serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGEPERIODPHASEB));
    }

    /**
     * Synchronously get the <i>RMS Extreme Under Voltage Period Phase B</i> attribute [attribute ID <b>0x0915</b>].
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
    public Integer getRmsExtremeUnderVoltagePeriodPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGEPERIODPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGEPERIODPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGEPERIODPHASEB));
    }

    /**
     * Set reporting for the <i>RMS Extreme Under Voltage Period Phase B</i> attribute [attribute ID <b>0x0915</b>].
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
    public Future<CommandResult> setRmsExtremeUnderVoltagePeriodPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGEPERIODPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Voltage Sag Period Phase B</i> attribute [attribute ID <b>0x0916</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsVoltageSagPeriodPhaseBAsync() {
        return read(serverAttributes.get(ATTR_RMSVOLTAGESAGPERIODPHASEB));
    }

    /**
     * Synchronously get the <i>RMS Voltage Sag Period Phase B</i> attribute [attribute ID <b>0x0916</b>].
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
    public Integer getRmsVoltageSagPeriodPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSVOLTAGESAGPERIODPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSVOLTAGESAGPERIODPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSVOLTAGESAGPERIODPHASEB));
    }

    /**
     * Set reporting for the <i>RMS Voltage Sag Period Phase B</i> attribute [attribute ID <b>0x0916</b>].
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
    public Future<CommandResult> setRmsVoltageSagPeriodPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSVOLTAGESAGPERIODPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Voltage Swell Period Phase B</i> attribute [attribute ID <b>0x0917</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsVoltageSwellPeriodPhaseBAsync() {
        return read(serverAttributes.get(ATTR_RMSVOLTAGESWELLPERIODPHASEB));
    }

    /**
     * Synchronously get the <i>RMS Voltage Swell Period Phase B</i> attribute [attribute ID <b>0x0917</b>].
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
    public Integer getRmsVoltageSwellPeriodPhaseB(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSVOLTAGESWELLPERIODPHASEB).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSVOLTAGESWELLPERIODPHASEB).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSVOLTAGESWELLPERIODPHASEB));
    }

    /**
     * Set reporting for the <i>RMS Voltage Swell Period Phase B</i> attribute [attribute ID <b>0x0917</b>].
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
    public Future<CommandResult> setRmsVoltageSwellPeriodPhaseBReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSVOLTAGESWELLPERIODPHASEB), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Line Current Phase C</i> attribute [attribute ID <b>0x0A01</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getLineCurrentPhaseCAsync() {
        return read(serverAttributes.get(ATTR_LINECURRENTPHASEC));
    }

    /**
     * Synchronously get the <i>Line Current Phase C</i> attribute [attribute ID <b>0x0A01</b>].
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
    public Integer getLineCurrentPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_LINECURRENTPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_LINECURRENTPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_LINECURRENTPHASEC));
    }

    /**
     * Set reporting for the <i>Line Current Phase C</i> attribute [attribute ID <b>0x0A01</b>].
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
    public Future<CommandResult> setLineCurrentPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_LINECURRENTPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Active Current Phase C</i> attribute [attribute ID <b>0x0A02</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getActiveCurrentPhaseCAsync() {
        return read(serverAttributes.get(ATTR_ACTIVECURRENTPHASEC));
    }

    /**
     * Synchronously get the <i>Active Current Phase C</i> attribute [attribute ID <b>0x0A02</b>].
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
    public Integer getActiveCurrentPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACTIVECURRENTPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACTIVECURRENTPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACTIVECURRENTPHASEC));
    }

    /**
     * Set reporting for the <i>Active Current Phase C</i> attribute [attribute ID <b>0x0A02</b>].
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
    public Future<CommandResult> setActiveCurrentPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_ACTIVECURRENTPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Reactive Current Phase C</i> attribute [attribute ID <b>0x0A03</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getReactiveCurrentPhaseCAsync() {
        return read(serverAttributes.get(ATTR_REACTIVECURRENTPHASEC));
    }

    /**
     * Synchronously get the <i>Reactive Current Phase C</i> attribute [attribute ID <b>0x0A03</b>].
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
    public Integer getReactiveCurrentPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_REACTIVECURRENTPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_REACTIVECURRENTPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_REACTIVECURRENTPHASEC));
    }

    /**
     * Set reporting for the <i>Reactive Current Phase C</i> attribute [attribute ID <b>0x0A03</b>].
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
    public Future<CommandResult> setReactiveCurrentPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_REACTIVECURRENTPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Voltage Phase C</i> attribute [attribute ID <b>0x0A05</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsVoltagePhaseCAsync() {
        return read(serverAttributes.get(ATTR_RMSVOLTAGEPHASEC));
    }

    /**
     * Synchronously get the <i>RMS Voltage Phase C</i> attribute [attribute ID <b>0x0A05</b>].
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
    public Integer getRmsVoltagePhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSVOLTAGEPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSVOLTAGEPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSVOLTAGEPHASEC));
    }

    /**
     * Set reporting for the <i>RMS Voltage Phase C</i> attribute [attribute ID <b>0x0A05</b>].
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
    public Future<CommandResult> setRmsVoltagePhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSVOLTAGEPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Voltage Min Phase C</i> attribute [attribute ID <b>0x0A06</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsVoltageMinPhaseCAsync() {
        return read(serverAttributes.get(ATTR_RMSVOLTAGEMINPHASEC));
    }

    /**
     * Synchronously get the <i>RMS Voltage Min Phase C</i> attribute [attribute ID <b>0x0A06</b>].
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
    public Integer getRmsVoltageMinPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSVOLTAGEMINPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSVOLTAGEMINPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSVOLTAGEMINPHASEC));
    }

    /**
     * Set reporting for the <i>RMS Voltage Min Phase C</i> attribute [attribute ID <b>0x0A06</b>].
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
    public Future<CommandResult> setRmsVoltageMinPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSVOLTAGEMINPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Voltage Max Phase C</i> attribute [attribute ID <b>0x0A07</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsVoltageMaxPhaseCAsync() {
        return read(serverAttributes.get(ATTR_RMSVOLTAGEMAXPHASEC));
    }

    /**
     * Synchronously get the <i>RMS Voltage Max Phase C</i> attribute [attribute ID <b>0x0A07</b>].
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
    public Integer getRmsVoltageMaxPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSVOLTAGEMAXPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSVOLTAGEMAXPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSVOLTAGEMAXPHASEC));
    }

    /**
     * Set reporting for the <i>RMS Voltage Max Phase C</i> attribute [attribute ID <b>0x0A07</b>].
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
    public Future<CommandResult> setRmsVoltageMaxPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSVOLTAGEMAXPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Current Phase C</i> attribute [attribute ID <b>0x0A08</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsCurrentPhaseCAsync() {
        return read(serverAttributes.get(ATTR_RMSCURRENTPHASEC));
    }

    /**
     * Synchronously get the <i>RMS Current Phase C</i> attribute [attribute ID <b>0x0A08</b>].
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
    public Integer getRmsCurrentPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSCURRENTPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSCURRENTPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSCURRENTPHASEC));
    }

    /**
     * Set reporting for the <i>RMS Current Phase C</i> attribute [attribute ID <b>0x0A08</b>].
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
    public Future<CommandResult> setRmsCurrentPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSCURRENTPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Current Min Phase C</i> attribute [attribute ID <b>0x0A09</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsCurrentMinPhaseCAsync() {
        return read(serverAttributes.get(ATTR_RMSCURRENTMINPHASEC));
    }

    /**
     * Synchronously get the <i>RMS Current Min Phase C</i> attribute [attribute ID <b>0x0A09</b>].
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
    public Integer getRmsCurrentMinPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSCURRENTMINPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSCURRENTMINPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSCURRENTMINPHASEC));
    }

    /**
     * Set reporting for the <i>RMS Current Min Phase C</i> attribute [attribute ID <b>0x0A09</b>].
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
    public Future<CommandResult> setRmsCurrentMinPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSCURRENTMINPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Current Max Phase C</i> attribute [attribute ID <b>0x0A0A</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsCurrentMaxPhaseCAsync() {
        return read(serverAttributes.get(ATTR_RMSCURRENTMAXPHASEC));
    }

    /**
     * Synchronously get the <i>RMS Current Max Phase C</i> attribute [attribute ID <b>0x0A0A</b>].
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
    public Integer getRmsCurrentMaxPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSCURRENTMAXPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSCURRENTMAXPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSCURRENTMAXPHASEC));
    }

    /**
     * Set reporting for the <i>RMS Current Max Phase C</i> attribute [attribute ID <b>0x0A0A</b>].
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
    public Future<CommandResult> setRmsCurrentMaxPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSCURRENTMAXPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Active Power Phase C</i> attribute [attribute ID <b>0x0A0B</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getActivePowerPhaseCAsync() {
        return read(serverAttributes.get(ATTR_ACTIVEPOWERPHASEC));
    }

    /**
     * Synchronously get the <i>Active Power Phase C</i> attribute [attribute ID <b>0x0A0B</b>].
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
    public Integer getActivePowerPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACTIVEPOWERPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACTIVEPOWERPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACTIVEPOWERPHASEC));
    }

    /**
     * Set reporting for the <i>Active Power Phase C</i> attribute [attribute ID <b>0x0A0B</b>].
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
    public Future<CommandResult> setActivePowerPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_ACTIVEPOWERPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Active Power Min Phase C</i> attribute [attribute ID <b>0x0A0C</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getActivePowerMinPhaseCAsync() {
        return read(serverAttributes.get(ATTR_ACTIVEPOWERMINPHASEC));
    }

    /**
     * Synchronously get the <i>Active Power Min Phase C</i> attribute [attribute ID <b>0x0A0C</b>].
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
    public Integer getActivePowerMinPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACTIVEPOWERMINPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACTIVEPOWERMINPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACTIVEPOWERMINPHASEC));
    }

    /**
     * Set reporting for the <i>Active Power Min Phase C</i> attribute [attribute ID <b>0x0A0C</b>].
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
    public Future<CommandResult> setActivePowerMinPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_ACTIVEPOWERMINPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Active Power Max Phase C</i> attribute [attribute ID <b>0x0A0D</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getActivePowerMaxPhaseCAsync() {
        return read(serverAttributes.get(ATTR_ACTIVEPOWERMAXPHASEC));
    }

    /**
     * Synchronously get the <i>Active Power Max Phase C</i> attribute [attribute ID <b>0x0A0D</b>].
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
    public Integer getActivePowerMaxPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ACTIVEPOWERMAXPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ACTIVEPOWERMAXPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ACTIVEPOWERMAXPHASEC));
    }

    /**
     * Set reporting for the <i>Active Power Max Phase C</i> attribute [attribute ID <b>0x0A0D</b>].
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
    public Future<CommandResult> setActivePowerMaxPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_ACTIVEPOWERMAXPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Reactive Power Phase C</i> attribute [attribute ID <b>0x0A0E</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getReactivePowerPhaseCAsync() {
        return read(serverAttributes.get(ATTR_REACTIVEPOWERPHASEC));
    }

    /**
     * Synchronously get the <i>Reactive Power Phase C</i> attribute [attribute ID <b>0x0A0E</b>].
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
    public Integer getReactivePowerPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_REACTIVEPOWERPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_REACTIVEPOWERPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_REACTIVEPOWERPHASEC));
    }

    /**
     * Set reporting for the <i>Reactive Power Phase C</i> attribute [attribute ID <b>0x0A0E</b>].
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
    public Future<CommandResult> setReactivePowerPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_REACTIVEPOWERPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Apparent Power Phase C</i> attribute [attribute ID <b>0x0A0F</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getApparentPowerPhaseCAsync() {
        return read(serverAttributes.get(ATTR_APPARENTPOWERPHASEC));
    }

    /**
     * Synchronously get the <i>Apparent Power Phase C</i> attribute [attribute ID <b>0x0A0F</b>].
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
    public Integer getApparentPowerPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_APPARENTPOWERPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_APPARENTPOWERPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_APPARENTPOWERPHASEC));
    }

    /**
     * Set reporting for the <i>Apparent Power Phase C</i> attribute [attribute ID <b>0x0A0F</b>].
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
    public Future<CommandResult> setApparentPowerPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_APPARENTPOWERPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Power Factor Phase C</i> attribute [attribute ID <b>0x0A10</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPowerFactorPhaseCAsync() {
        return read(serverAttributes.get(ATTR_POWERFACTORPHASEC));
    }

    /**
     * Synchronously get the <i>Power Factor Phase C</i> attribute [attribute ID <b>0x0A10</b>].
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
    public Integer getPowerFactorPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_POWERFACTORPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_POWERFACTORPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_POWERFACTORPHASEC));
    }

    /**
     * Set reporting for the <i>Power Factor Phase C</i> attribute [attribute ID <b>0x0A10</b>].
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
    public Future<CommandResult> setPowerFactorPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_POWERFACTORPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Average RMS Voltage Measurement Period Phase C</i> attribute [attribute ID <b>0x0A11</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAverageRmsVoltageMeasurementPeriodPhaseCAsync() {
        return read(serverAttributes.get(ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIODPHASEC));
    }

    /**
     * Synchronously get the <i>Average RMS Voltage Measurement Period Phase C</i> attribute [attribute ID <b>0x0A11</b>].
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
    public Integer getAverageRmsVoltageMeasurementPeriodPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIODPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIODPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIODPHASEC));
    }

    /**
     * Set reporting for the <i>Average RMS Voltage Measurement Period Phase C</i> attribute [attribute ID <b>0x0A11</b>].
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
    public Future<CommandResult> setAverageRmsVoltageMeasurementPeriodPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_AVERAGERMSVOLTAGEMEASUREMENTPERIODPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Average RMS Over Voltage Counter Phase C</i> attribute [attribute ID <b>0x0A12</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAverageRmsOverVoltageCounterPhaseCAsync() {
        return read(serverAttributes.get(ATTR_AVERAGERMSOVERVOLTAGECOUNTERPHASEC));
    }

    /**
     * Synchronously get the <i>Average RMS Over Voltage Counter Phase C</i> attribute [attribute ID <b>0x0A12</b>].
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
    public Integer getAverageRmsOverVoltageCounterPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_AVERAGERMSOVERVOLTAGECOUNTERPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_AVERAGERMSOVERVOLTAGECOUNTERPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_AVERAGERMSOVERVOLTAGECOUNTERPHASEC));
    }

    /**
     * Set reporting for the <i>Average RMS Over Voltage Counter Phase C</i> attribute [attribute ID <b>0x0A12</b>].
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
    public Future<CommandResult> setAverageRmsOverVoltageCounterPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_AVERAGERMSOVERVOLTAGECOUNTERPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Average RMS Under Voltage Counter Phase C</i> attribute [attribute ID <b>0x0A13</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getAverageRmsUnderVoltageCounterPhaseCAsync() {
        return read(serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGECOUNTERPHASEC));
    }

    /**
     * Synchronously get the <i>Average RMS Under Voltage Counter Phase C</i> attribute [attribute ID <b>0x0A13</b>].
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
    public Integer getAverageRmsUnderVoltageCounterPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGECOUNTERPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGECOUNTERPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGECOUNTERPHASEC));
    }

    /**
     * Set reporting for the <i>Average RMS Under Voltage Counter Phase C</i> attribute [attribute ID <b>0x0A13</b>].
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
    public Future<CommandResult> setAverageRmsUnderVoltageCounterPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_AVERAGERMSUNDERVOLTAGECOUNTERPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Extreme Over Voltage Period Phase C</i> attribute [attribute ID <b>0x0A14</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsExtremeOverVoltagePeriodPhaseCAsync() {
        return read(serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGEPERIODPHASEC));
    }

    /**
     * Synchronously get the <i>RMS Extreme Over Voltage Period Phase C</i> attribute [attribute ID <b>0x0A14</b>].
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
    public Integer getRmsExtremeOverVoltagePeriodPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGEPERIODPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGEPERIODPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGEPERIODPHASEC));
    }

    /**
     * Set reporting for the <i>RMS Extreme Over Voltage Period Phase C</i> attribute [attribute ID <b>0x0A14</b>].
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
    public Future<CommandResult> setRmsExtremeOverVoltagePeriodPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSEXTREMEOVERVOLTAGEPERIODPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Extreme Under Voltage Period Phase C</i> attribute [attribute ID <b>0x0A15</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsExtremeUnderVoltagePeriodPhaseCAsync() {
        return read(serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGEPERIODPHASEC));
    }

    /**
     * Synchronously get the <i>RMS Extreme Under Voltage Period Phase C</i> attribute [attribute ID <b>0x0A15</b>].
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
    public Integer getRmsExtremeUnderVoltagePeriodPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGEPERIODPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGEPERIODPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGEPERIODPHASEC));
    }

    /**
     * Set reporting for the <i>RMS Extreme Under Voltage Period Phase C</i> attribute [attribute ID <b>0x0A15</b>].
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
    public Future<CommandResult> setRmsExtremeUnderVoltagePeriodPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSEXTREMEUNDERVOLTAGEPERIODPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Voltage Sag Period Phase C</i> attribute [attribute ID <b>0x0A16</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsVoltageSagPeriodPhaseCAsync() {
        return read(serverAttributes.get(ATTR_RMSVOLTAGESAGPERIODPHASEC));
    }

    /**
     * Synchronously get the <i>RMS Voltage Sag Period Phase C</i> attribute [attribute ID <b>0x0A16</b>].
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
    public Integer getRmsVoltageSagPeriodPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSVOLTAGESAGPERIODPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSVOLTAGESAGPERIODPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSVOLTAGESAGPERIODPHASEC));
    }

    /**
     * Set reporting for the <i>RMS Voltage Sag Period Phase C</i> attribute [attribute ID <b>0x0A16</b>].
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
    public Future<CommandResult> setRmsVoltageSagPeriodPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSVOLTAGESAGPERIODPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RMS Voltage Swell Period Phase C</i> attribute [attribute ID <b>0x0A17</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRmsVoltageSwellPeriodPhaseCAsync() {
        return read(serverAttributes.get(ATTR_RMSVOLTAGESWELLPERIODPHASEC));
    }

    /**
     * Synchronously get the <i>RMS Voltage Swell Period Phase C</i> attribute [attribute ID <b>0x0A17</b>].
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
    public Integer getRmsVoltageSwellPeriodPhaseC(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_RMSVOLTAGESWELLPERIODPHASEC).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_RMSVOLTAGESWELLPERIODPHASEC).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_RMSVOLTAGESWELLPERIODPHASEC));
    }

    /**
     * Set reporting for the <i>RMS Voltage Swell Period Phase C</i> attribute [attribute ID <b>0x0A17</b>].
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
    public Future<CommandResult> setRmsVoltageSwellPeriodPhaseCReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_RMSVOLTAGESWELLPERIODPHASEC), minInterval, maxInterval, reportableChange);
    }

    /**
     * The Get Profile Info Command
     * <p>
     * Retrieves the power profiling information from the electrical measurement server.
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getProfileInfoCommand() {
        return send(new GetProfileInfoCommand());
    }

    /**
     * The Get Measurement Profile Command
     * <p>
     * Retrieves an electricity measurement profile from the electricity measurement
     * server for a specific attribute ID requested.
     *
     * @param attributeId {@link Integer} Attribute ID
     * @param startTime {@link Integer} Start Time
     * @param numberOfIntervals {@link Integer} Number Of Intervals
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMeasurementProfileCommand(Integer attributeId, Integer startTime, Integer numberOfIntervals) {
        GetMeasurementProfileCommand command = new GetMeasurementProfileCommand();

        // Set the fields
        command.setAttributeId(attributeId);
        command.setStartTime(startTime);
        command.setNumberOfIntervals(numberOfIntervals);

        return send(command);
    }

    /**
     * The Get Profile Info Response Command
     * <p>
     * Returns the power profiling information requested in the GetProfileInfo command. The
     * power profiling information consists of a list of attributes which are profiled along
     * with the period used to profile them.
     *
     * @param profileCount {@link Integer} Profile Count
     * @param profileIntervalPeriod {@link Integer} Profile Interval Period
     * @param maxNumberOfIntervals {@link Integer} Max Number Of Intervals
     * @param listOfAttributes {@link Integer} List Of Attributes
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getProfileInfoResponseCommand(Integer profileCount, Integer profileIntervalPeriod, Integer maxNumberOfIntervals, Integer listOfAttributes) {
        GetProfileInfoResponseCommand command = new GetProfileInfoResponseCommand();

        // Set the fields
        command.setProfileCount(profileCount);
        command.setProfileIntervalPeriod(profileIntervalPeriod);
        command.setMaxNumberOfIntervals(maxNumberOfIntervals);
        command.setListOfAttributes(listOfAttributes);

        return send(command);
    }

    /**
     * The Get Measurement Profile Response Command
     * <p>
     * Returns the electricity measurement profile. The electricity measurement profile
     * includes information regarding the amount of time used to capture data related to the
     * flow of electricity as well as the intervals thes
     *
     * @param startTime {@link Integer} Start Time
     * @param status {@link Integer} Status
     * @param profileIntervalPeriod {@link Integer} Profile Interval Period
     * @param numberOfIntervalsDelivered {@link Integer} Number Of Intervals Delivered
     * @param attributeId {@link Integer} Attribute ID
     * @param intervals {@link Integer} Intervals
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMeasurementProfileResponseCommand(Integer startTime, Integer status, Integer profileIntervalPeriod, Integer numberOfIntervalsDelivered, Integer attributeId, Integer intervals) {
        GetMeasurementProfileResponseCommand command = new GetMeasurementProfileResponseCommand();

        // Set the fields
        command.setStartTime(startTime);
        command.setStatus(status);
        command.setProfileIntervalPeriod(profileIntervalPeriod);
        command.setNumberOfIntervalsDelivered(numberOfIntervalsDelivered);
        command.setAttributeId(attributeId);
        command.setIntervals(intervals);

        return send(command);
    }
}
