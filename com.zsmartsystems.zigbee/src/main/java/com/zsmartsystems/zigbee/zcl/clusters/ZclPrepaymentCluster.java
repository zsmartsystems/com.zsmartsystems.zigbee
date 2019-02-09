/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.ChangeDebt;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.ChangePaymentMode;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.ChangePaymentModeResponse;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.ConsumerTopUp;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.ConsumerTopUpResponse;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.CreditAdjustment;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.DebtPayload;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.EmergencyCreditSetup;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.GetDebtRepaymentLog;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.GetPrepaySnapshot;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.GetTopUpLog;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.PublishDebtLog;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.PublishPrepaySnapshot;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.PublishTopUpLog;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.SelectAvailableEmergencyCredit;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.SetLowCreditWarningLevel;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.SetMaximumCreditLimit;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.SetOverallDebtCap;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.TopUpPayload;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Prepayment</b> cluster implementation (<i>Cluster ID 0x0705</i>).
 * <p>
 * The Prepayment Cluster provides the facility to pass messages relating to the accounting
 * functionality of a meter between devices on the HAN. It allows for the implementation of a
 * system conforming to the set of standards relating to Payment Electricity Meters (IEC
 * 62055) and also for the case where the accounting function is remote from the meter.
 * Prepayment is used in situations where the supply of a service may be interrupted or enabled
 * under the control of the meter or system in relation to a payment tariff. The accounting
 * process may be within the meter or elsewhere in the system. The amount of available credit is
 * decremented as the service is consumed and is incremented through payments made by the
 * consumer. Such a system allows the consumer to better manage their energy consumption and
 * reduces the risk of bad debt owing to the supplier.
 * <p>
 * In the case where the accounting process resides within the meter, credit updates are sent to
 * the meter from the ESI. Such messages are out of scope of this cluster. The cluster allows
 * credit status to be made available to other devices on the HAN for example to enable the
 * consumers to view their status on an IHD. It also allows them to select emergency credit if
 * running low and also, where local markets allow, restoring their supply remotely from
 * within the HAN.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ZclPrepaymentCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0705;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Prepayment";

    // Attribute constants
    /**
     * ADDME
     */
    public static final int ATTR_PAYMENTCONTROLCONFIGURATION = 0x0000;
    /**
     * ADDME
     */
    public static final int ATTR_CREDITREMAINING = 0x0001;
    /**
     * ADDME
     */
    public static final int ATTR_EMERGENCYCREDITREMAINING = 0x0002;
    /**
     * ADDME
     */
    public static final int ATTR_CREDITSTATUS = 0x0003;
    /**
     * ADDME
     */
    public static final int ATTR_CREDITREMAININGTIMESTAMP = 0x0004;
    /**
     * ADDME
     */
    public static final int ATTR_ACCUMULATEDDEBT = 0x0005;
    /**
     * ADDME
     */
    public static final int ATTR_OVERALLDEBTCAP = 0x0006;
    /**
     * ADDME
     */
    public static final int ATTR_EMERGENCYCREDITLIMITALLOWANCE = 0x0010;
    /**
     * ADDME
     */
    public static final int ATTR_EMERGENCYCREDITTHRESHOLD = 0x0011;
    /**
     * ADDME
     */
    public static final int ATTR_TOTALCREDITADDED = 0x0020;
    /**
     * ADDME
     */
    public static final int ATTR_MAXCREDITLIMIT = 0x0021;
    /**
     * ADDME
     */
    public static final int ATTR_MAXCREDITPERTOPUP = 0x0022;
    /**
     * ADDME
     */
    public static final int ATTR_FRIENDLYCREDITWARNING = 0x0030;
    /**
     * ADDME
     */
    public static final int ATTR_LOWCREDITWARNINGLEVEL = 0x0031;
    /**
     * ADDME
     */
    public static final int ATTR_IHDLOWCREDITWARNINGLEVEL = 0x0032;
    /**
     * ADDME
     */
    public static final int ATTR_INTERRUPTSUSPENDTIME = 0x0033;
    /**
     * ADDME
     */
    public static final int ATTR_REMAININGFRIENDLYCREDITTIME = 0x0034;
    /**
     * ADDME
     */
    public static final int ATTR_NEXTFRIENDLYCREDITPERIOD = 0x0035;
    /**
     * ADDME
     */
    public static final int ATTR_CUTOFFVALUE = 0x0040;
    /**
     * ADDME
     */
    public static final int ATTR_TOKENCARRIERID = 0x0080;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPDATETIME1 = 0x0100;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPAMOUNT1 = 0x0101;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPORIGINATINGDEVICE1 = 0x0102;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPCODE1 = 0x0103;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPDATETIME2 = 0x0110;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPAMOUNT2 = 0x0111;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPORIGINATINGDEVICE2 = 0x0112;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPCODE2 = 0x0113;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPDATETIME3 = 0x0120;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPAMOUNT3 = 0x0121;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPORIGINATINGDEVICE3 = 0x0122;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPCODE3 = 0x0123;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPDATETIME4 = 0x0130;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPAMOUNT4 = 0x0131;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPORIGINATINGDEVICE4 = 0x0132;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPCODE4 = 0x0133;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPDATETIME5 = 0x0140;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPAMOUNT5 = 0x0141;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPORIGINATINGDEVICE5 = 0x0142;
    /**
     * ADDME
     */
    public static final int ATTR_TOPUPCODE5 = 0x0143;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTLABEL1 = 0x0210;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTAMOUNT1 = 0x0211;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTRECOVERYMETHOD1 = 0x0212;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTRECOVERYSTARTTIME1 = 0x0213;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTRECOVERYCOLLECTIONTIME1 = 0x0214;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTRECOVERYFREQUENCY1 = 0x0216;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTRECOVERYAMOUNT1 = 0x0217;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTRECOVERYTOPUPPERCENTAGE1 = 0x0219;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTLABEL2 = 0x0220;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTAMOUNT2 = 0x0221;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTRECOVERYMETHOD2 = 0x0222;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTRECOVERYSTARTTIME2 = 0x0223;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTRECOVERYCOLLECTIONTIME2 = 0x0224;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTRECOVERYFREQUENCY2 = 0x0226;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTRECOVERYAMOUNT2 = 0x0227;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTRECOVERYTOPUPPERCENTAGE2 = 0x0229;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTLABEL3 = 0x0230;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTAMOUNT3 = 0x0231;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTRECOVERYMETHOD3 = 0x0232;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTRECOVERYSTARTTIME3 = 0x0233;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTRECOVERYCOLLECTIONTIME3 = 0x0234;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTRECOVERYFREQUENCY3 = 0x0236;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTRECOVERYAMOUNT3 = 0x0237;
    /**
     * ADDME
     */
    public static final int ATTR_DEBTRECOVERYTOPUPPERCENTAGE3 = 0x0239;
    /**
     * ADDME
     */
    public static final int ATTR_PREPAYMENTALARMSTATUS = 0x0400;
    /**
     * ADDME
     */
    public static final int ATTR_PREPAYGENERICALARMMASK = 0x0401;
    /**
     * ADDME
     */
    public static final int ATTR_PREPAYSWITCHALARMMASK = 0x0402;
    /**
     * ADDME
     */
    public static final int ATTR_PREPAYEVENTALARMMASK = 0x0403;
    /**
     * ADDME
     */
    public static final int ATTR_HISTORICALCOSTCONSUMPTIONFORMATTING = 0x0500;
    /**
     * ADDME
     */
    public static final int ATTR_CONSUMPTIONUNITOFMEASUREMENT = 0x0501;
    /**
     * ADDME
     */
    public static final int ATTR_CURRENCYSCALINGFACTOR = 0x0502;
    /**
     * ADDME
     */
    public static final int ATTR_CURRENCY = 0x0503;
    /**
     * ADDME
     */
    public static final int ATTR_CURRENTDAYCOSTCONSUMPTIONDELIVERED = 0x051C;
    /**
     * ADDME
     */
    public static final int ATTR_CURRENTDAYCOSTCONSUMPTIONRECEIVED = 0x051D;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSDAYCOSTCONSUMPTIONDELIVERED = 0x051E;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSDAYCOSTCONSUMPTIONRECEIVED = 0x051F;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSDAY2COSTCONSUMPTIONDELIVERED = 0x0520;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSDAY2COSTCONSUMPTIONRECEIVED = 0x0521;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSDAY3COSTCONSUMPTIONDELIVERED = 0x0522;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSDAY3COSTCONSUMPTIONRECEIVED = 0x0523;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSDAY4COSTCONSUMPTIONDELIVERED = 0x0524;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSDAY4COSTCONSUMPTIONRECEIVED = 0x0525;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSDAY5COSTCONSUMPTIONDELIVERED = 0x0526;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSDAY5COSTCONSUMPTIONRECEIVED = 0x0527;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSDAY6COSTCONSUMPTIONDELIVERED = 0x0528;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSDAY6COSTCONSUMPTIONRECEIVED = 0x0529;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSDAY7COSTCONSUMPTIONDELIVERED = 0x052A;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSDAY7COSTCONSUMPTIONRECEIVED = 0x052B;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSDAY8COSTCONSUMPTIONDELIVERED = 0x052C;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSDAY8COSTCONSUMPTIONRECEIVED = 0x052D;
    /**
     * ADDME
     */
    public static final int ATTR_CURRENTWEEKCOSTCONSUMPTIONDELIVERED = 0x0530;
    /**
     * ADDME
     */
    public static final int ATTR_CURRENTWEEKCOSTCONSUMPTIONRECEIVED = 0x0531;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSWEEKCOSTCONSUMPTIONDELIVERED = 0x0532;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSWEEKCOSTCONSUMPTIONRECEIVED = 0x0533;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSWEEK2COSTCONSUMPTIONDELIVERED = 0x0534;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSWEEK2COSTCONSUMPTIONRECEIVED = 0x0535;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSWEEK3COSTCONSUMPTIONDELIVERED = 0x0536;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSWEEK3COSTCONSUMPTIONRECEIVED = 0x0537;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSWEEK4COSTCONSUMPTIONDELIVERED = 0x0538;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSWEEK4COSTCONSUMPTIONRECEIVED = 0x0539;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSWEEK5COSTCONSUMPTIONDELIVERED = 0x053A;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSWEEK5COSTCONSUMPTIONRECEIVED = 0x053B;
    /**
     * ADDME
     */
    public static final int ATTR_CURRENTMONTHCOSTCONSUMPTIONDELIVERED = 0x0540;
    /**
     * ADDME
     */
    public static final int ATTR_CURRENTMONTHCOSTCONSUMPTIONRECEIVED = 0x0541;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTHCOSTCONSUMPTIONDELIVERED = 0x0542;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTHCOSTCONSUMPTIONRECEIVED = 0x0543;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH2COSTCONSUMPTIONDELIVERED = 0x0544;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH2COSTCONSUMPTIONRECEIVED = 0x0545;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH3COSTCONSUMPTIONDELIVERED = 0x0546;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH3COSTCONSUMPTIONRECEIVED = 0x0547;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH4COSTCONSUMPTIONDELIVERED = 0x0548;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH4COSTCONSUMPTIONRECEIVED = 0x0549;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH5COSTCONSUMPTIONDELIVERED = 0x054A;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH5COSTCONSUMPTIONRECEIVED = 0x054B;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH6COSTCONSUMPTIONDELIVERED = 0x054C;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH6COSTCONSUMPTIONRECEIVED = 0x054D;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH7COSTCONSUMPTIONDELIVERED = 0x054E;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH7COSTCONSUMPTIONRECEIVED = 0x054F;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH8COSTCONSUMPTIONDELIVERED = 0x0550;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH8COSTCONSUMPTIONRECEIVED = 0x0551;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH9COSTCONSUMPTIONDELIVERED = 0x0552;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH9COSTCONSUMPTIONRECEIVED = 0x0553;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH10COSTCONSUMPTIONDELIVERED = 0x0554;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH10COSTCONSUMPTIONRECEIVED = 0x0555;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH11COSTCONSUMPTIONDELIVERED = 0x0556;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH11COSTCONSUMPTIONRECEIVED = 0x0557;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH12COSTCONSUMPTIONDELIVERED = 0x0558;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH12COSTCONSUMPTIONRECEIVED = 0x0559;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH13COSTCONSUMPTIONDELIVERED = 0x055A;
    /**
     * ADDME
     */
    public static final int ATTR_PREVIOUSMONTH13COSTCONSUMPTIONRECEIVED = 0x055B;
    /**
     * ADDME
     */
    public static final int ATTR_HISTORICALFREEZETIME = 0x055C;

    // Attribute initialisation
    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(131);

        attributeMap.put(ATTR_PAYMENTCONTROLCONFIGURATION, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PAYMENTCONTROLCONFIGURATION, "Payment Control Configuration", ZclDataType.BITMAP_16_BIT, true, true, false, false));
        attributeMap.put(ATTR_CREDITREMAINING, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_CREDITREMAINING, "Credit Remaining", ZclDataType.SIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_EMERGENCYCREDITREMAINING, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_EMERGENCYCREDITREMAINING, "Emergency Credit Remaining", ZclDataType.SIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_CREDITSTATUS, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_CREDITSTATUS, "Credit Status", ZclDataType.BITMAP_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_CREDITREMAININGTIMESTAMP, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_CREDITREMAININGTIMESTAMP, "Credit Remaining Timestamp", ZclDataType.UTCTIME, true, true, false, false));
        attributeMap.put(ATTR_ACCUMULATEDDEBT, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_ACCUMULATEDDEBT, "Accumulated Debt", ZclDataType.SIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_OVERALLDEBTCAP, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_OVERALLDEBTCAP, "Overall Debt Cap", ZclDataType.SIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_EMERGENCYCREDITLIMITALLOWANCE, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_EMERGENCYCREDITLIMITALLOWANCE, "Emergency Credit Limit Allowance", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_EMERGENCYCREDITTHRESHOLD, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_EMERGENCYCREDITTHRESHOLD, "Emergency Credit Threshold", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_TOTALCREDITADDED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOTALCREDITADDED, "Total Credit Added", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MAXCREDITLIMIT, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_MAXCREDITLIMIT, "Max Credit Limit", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MAXCREDITPERTOPUP, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_MAXCREDITPERTOPUP, "Max Credit Per Top Up", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_FRIENDLYCREDITWARNING, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_FRIENDLYCREDITWARNING, "Friendly Credit Warning", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_LOWCREDITWARNINGLEVEL, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_LOWCREDITWARNINGLEVEL, "Low Credit Warning Level", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_IHDLOWCREDITWARNINGLEVEL, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_IHDLOWCREDITWARNINGLEVEL, "Ihd Low Credit Warning Level", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_INTERRUPTSUSPENDTIME, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_INTERRUPTSUSPENDTIME, "Interrupt Suspend Time", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_REMAININGFRIENDLYCREDITTIME, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_REMAININGFRIENDLYCREDITTIME, "Remaining Friendly Credit Time", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NEXTFRIENDLYCREDITPERIOD, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_NEXTFRIENDLYCREDITPERIOD, "Next Friendly Credit Period", ZclDataType.UTCTIME, true, true, false, false));
        attributeMap.put(ATTR_CUTOFFVALUE, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_CUTOFFVALUE, "Cut Off Value", ZclDataType.SIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_TOKENCARRIERID, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOKENCARRIERID, "Token Carrier ID", ZclDataType.OCTET_STRING, false, true, true, true));
        attributeMap.put(ATTR_TOPUPDATETIME1, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPDATETIME1, "Top Up Date / time #1", ZclDataType.UTCTIME, true, true, false, false));
        attributeMap.put(ATTR_TOPUPAMOUNT1, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPAMOUNT1, "Top Up Amount #1", ZclDataType.SIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_TOPUPORIGINATINGDEVICE1, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPORIGINATINGDEVICE1, "Top Up Originating Device #1", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_TOPUPCODE1, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPCODE1, "Top Up Code #1", ZclDataType.OCTET_STRING, true, true, false, false));
        attributeMap.put(ATTR_TOPUPDATETIME2, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPDATETIME2, "Top Up Date /time #2", ZclDataType.UTCTIME, true, true, false, false));
        attributeMap.put(ATTR_TOPUPAMOUNT2, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPAMOUNT2, "Top Up Amount #2", ZclDataType.SIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_TOPUPORIGINATINGDEVICE2, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPORIGINATINGDEVICE2, "Top Up Originating Device #2", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_TOPUPCODE2, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPCODE2, "Top Up Code #2", ZclDataType.OCTET_STRING, true, true, false, false));
        attributeMap.put(ATTR_TOPUPDATETIME3, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPDATETIME3, "Top Up Date /time #3", ZclDataType.UTCTIME, true, true, false, false));
        attributeMap.put(ATTR_TOPUPAMOUNT3, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPAMOUNT3, "Top Up Amount #3", ZclDataType.SIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_TOPUPORIGINATINGDEVICE3, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPORIGINATINGDEVICE3, "Top Up Originating Device #3", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_TOPUPCODE3, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPCODE3, "Top Up Code #3", ZclDataType.OCTET_STRING, true, true, false, false));
        attributeMap.put(ATTR_TOPUPDATETIME4, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPDATETIME4, "Top Up Date /time #4", ZclDataType.UTCTIME, true, true, false, false));
        attributeMap.put(ATTR_TOPUPAMOUNT4, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPAMOUNT4, "Top Up Amount #4", ZclDataType.SIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_TOPUPORIGINATINGDEVICE4, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPORIGINATINGDEVICE4, "Top Up Originating Device #4", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_TOPUPCODE4, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPCODE4, "Top Up Code #4", ZclDataType.OCTET_STRING, true, true, false, false));
        attributeMap.put(ATTR_TOPUPDATETIME5, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPDATETIME5, "Top Up Date /time #5", ZclDataType.UTCTIME, true, true, false, false));
        attributeMap.put(ATTR_TOPUPAMOUNT5, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPAMOUNT5, "Top Up Amount #5", ZclDataType.SIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_TOPUPORIGINATINGDEVICE5, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPORIGINATINGDEVICE5, "Top Up Originating Device #5", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_TOPUPCODE5, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_TOPUPCODE5, "Top Up Code #5", ZclDataType.OCTET_STRING, true, true, false, false));
        attributeMap.put(ATTR_DEBTLABEL1, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTLABEL1, "Debt Label 1", ZclDataType.OCTET_STRING, true, true, false, false));
        attributeMap.put(ATTR_DEBTAMOUNT1, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTAMOUNT1, "Debt Amount 1", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DEBTRECOVERYMETHOD1, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTRECOVERYMETHOD1, "Debt Recovery Method 1", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_DEBTRECOVERYSTARTTIME1, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTRECOVERYSTARTTIME1, "Debt Recovery Start Time 1", ZclDataType.UTCTIME, true, true, false, false));
        attributeMap.put(ATTR_DEBTRECOVERYCOLLECTIONTIME1, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTRECOVERYCOLLECTIONTIME1, "Debt Recovery Collection Time 1", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DEBTRECOVERYFREQUENCY1, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTRECOVERYFREQUENCY1, "Debt Recovery Frequency 1", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_DEBTRECOVERYAMOUNT1, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTRECOVERYAMOUNT1, "Debt Recovery Amount 1", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DEBTRECOVERYTOPUPPERCENTAGE1, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTRECOVERYTOPUPPERCENTAGE1, "Debt Recovery Top Up Percentage 1", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DEBTLABEL2, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTLABEL2, "Debt Label 2", ZclDataType.OCTET_STRING, true, true, false, false));
        attributeMap.put(ATTR_DEBTAMOUNT2, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTAMOUNT2, "Debt Amount 2", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DEBTRECOVERYMETHOD2, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTRECOVERYMETHOD2, "Debt Recovery Method 2", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_DEBTRECOVERYSTARTTIME2, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTRECOVERYSTARTTIME2, "Debt Recovery Start Time 2", ZclDataType.UTCTIME, true, true, false, false));
        attributeMap.put(ATTR_DEBTRECOVERYCOLLECTIONTIME2, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTRECOVERYCOLLECTIONTIME2, "Debt Recovery Collection Time 2", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DEBTRECOVERYFREQUENCY2, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTRECOVERYFREQUENCY2, "Debt Recovery Frequency 2", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_DEBTRECOVERYAMOUNT2, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTRECOVERYAMOUNT2, "Debt Recovery Amount 2", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DEBTRECOVERYTOPUPPERCENTAGE2, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTRECOVERYTOPUPPERCENTAGE2, "Debt Recovery Top Up Percentage 2", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DEBTLABEL3, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTLABEL3, "Debt Label 3", ZclDataType.OCTET_STRING, true, true, false, false));
        attributeMap.put(ATTR_DEBTAMOUNT3, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTAMOUNT3, "Debt Amount 3", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DEBTRECOVERYMETHOD3, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTRECOVERYMETHOD3, "Debt Recovery Method 3", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_DEBTRECOVERYSTARTTIME3, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTRECOVERYSTARTTIME3, "Debt Recovery Start Time 3", ZclDataType.UTCTIME, true, true, false, false));
        attributeMap.put(ATTR_DEBTRECOVERYCOLLECTIONTIME3, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTRECOVERYCOLLECTIONTIME3, "Debt Recovery Collection Time 3", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DEBTRECOVERYFREQUENCY3, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTRECOVERYFREQUENCY3, "Debt Recovery Frequency 3", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_DEBTRECOVERYAMOUNT3, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTRECOVERYAMOUNT3, "Debt Recovery Amount 3", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DEBTRECOVERYTOPUPPERCENTAGE3, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_DEBTRECOVERYTOPUPPERCENTAGE3, "Debt Recovery Top Up Percentage 3", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREPAYMENTALARMSTATUS, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREPAYMENTALARMSTATUS, "Prepayment Alarm Status", ZclDataType.BITMAP_16_BIT, false, true, true, true));
        attributeMap.put(ATTR_PREPAYGENERICALARMMASK, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREPAYGENERICALARMMASK, "Prepay Generic Alarm Mask", ZclDataType.BITMAP_16_BIT, false, true, true, true));
        attributeMap.put(ATTR_PREPAYSWITCHALARMMASK, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREPAYSWITCHALARMMASK, "Prepay Switch Alarm Mask", ZclDataType.BITMAP_16_BIT, false, true, true, true));
        attributeMap.put(ATTR_PREPAYEVENTALARMMASK, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREPAYEVENTALARMMASK, "Prepay Event Alarm Mask", ZclDataType.BITMAP_16_BIT, false, true, true, true));
        attributeMap.put(ATTR_HISTORICALCOSTCONSUMPTIONFORMATTING, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_HISTORICALCOSTCONSUMPTIONFORMATTING, "Historical Cost Consumption Formatting", ZclDataType.BITMAP_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_CONSUMPTIONUNITOFMEASUREMENT, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_CONSUMPTIONUNITOFMEASUREMENT, "Consumption Unit Of Measurement", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_CURRENCYSCALINGFACTOR, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_CURRENCYSCALINGFACTOR, "Currency Scaling Factor", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_CURRENCY, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_CURRENCY, "Currency", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_CURRENTDAYCOSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_CURRENTDAYCOSTCONSUMPTIONDELIVERED, "Current Day Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_CURRENTDAYCOSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_CURRENTDAYCOSTCONSUMPTIONRECEIVED, "Current Day Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSDAYCOSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSDAYCOSTCONSUMPTIONDELIVERED, "Previous Day Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSDAYCOSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSDAYCOSTCONSUMPTIONRECEIVED, "Previous Day Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSDAY2COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSDAY2COSTCONSUMPTIONDELIVERED, "Previous Day 2 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSDAY2COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSDAY2COSTCONSUMPTIONRECEIVED, "Previous Day 2 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSDAY3COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSDAY3COSTCONSUMPTIONDELIVERED, "Previous Day 3 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSDAY3COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSDAY3COSTCONSUMPTIONRECEIVED, "Previous Day 3 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSDAY4COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSDAY4COSTCONSUMPTIONDELIVERED, "Previous Day 4 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSDAY4COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSDAY4COSTCONSUMPTIONRECEIVED, "Previous Day 4 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSDAY5COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSDAY5COSTCONSUMPTIONDELIVERED, "Previous Day 5 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSDAY5COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSDAY5COSTCONSUMPTIONRECEIVED, "Previous Day 5 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSDAY6COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSDAY6COSTCONSUMPTIONDELIVERED, "Previous Day 6 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSDAY6COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSDAY6COSTCONSUMPTIONRECEIVED, "Previous Day 6 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSDAY7COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSDAY7COSTCONSUMPTIONDELIVERED, "Previous Day 7 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSDAY7COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSDAY7COSTCONSUMPTIONRECEIVED, "Previous Day 7 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSDAY8COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSDAY8COSTCONSUMPTIONDELIVERED, "Previous Day 8 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSDAY8COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSDAY8COSTCONSUMPTIONRECEIVED, "Previous Day 8 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_CURRENTWEEKCOSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_CURRENTWEEKCOSTCONSUMPTIONDELIVERED, "Current Week Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_CURRENTWEEKCOSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_CURRENTWEEKCOSTCONSUMPTIONRECEIVED, "Current Week Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSWEEKCOSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSWEEKCOSTCONSUMPTIONDELIVERED, "Previous Week Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSWEEKCOSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSWEEKCOSTCONSUMPTIONRECEIVED, "Previous Week Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSWEEK2COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSWEEK2COSTCONSUMPTIONDELIVERED, "Previous Week 2 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSWEEK2COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSWEEK2COSTCONSUMPTIONRECEIVED, "Previous Week 2 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSWEEK3COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSWEEK3COSTCONSUMPTIONDELIVERED, "Previous Week 3 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSWEEK3COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSWEEK3COSTCONSUMPTIONRECEIVED, "Previous Week 3 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSWEEK4COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSWEEK4COSTCONSUMPTIONDELIVERED, "Previous Week 4 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSWEEK4COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSWEEK4COSTCONSUMPTIONRECEIVED, "Previous Week 4 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSWEEK5COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSWEEK5COSTCONSUMPTIONDELIVERED, "Previous Week 5 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSWEEK5COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSWEEK5COSTCONSUMPTIONRECEIVED, "Previous Week 5 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_CURRENTMONTHCOSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_CURRENTMONTHCOSTCONSUMPTIONDELIVERED, "Current Month Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_CURRENTMONTHCOSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_CURRENTMONTHCOSTCONSUMPTIONRECEIVED, "Current Month Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTHCOSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTHCOSTCONSUMPTIONDELIVERED, "Previous Month Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTHCOSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTHCOSTCONSUMPTIONRECEIVED, "Previous Month Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH2COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH2COSTCONSUMPTIONDELIVERED, "Previous Month 2 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH2COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH2COSTCONSUMPTIONRECEIVED, "Previous Month 2 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH3COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH3COSTCONSUMPTIONDELIVERED, "Previous Month 3 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH3COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH3COSTCONSUMPTIONRECEIVED, "Previous Month 3 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH4COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH4COSTCONSUMPTIONDELIVERED, "Previous Month 4 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH4COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH4COSTCONSUMPTIONRECEIVED, "Previous Month 4 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH5COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH5COSTCONSUMPTIONDELIVERED, "Previous Month 5 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH5COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH5COSTCONSUMPTIONRECEIVED, "Previous Month 5 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH6COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH6COSTCONSUMPTIONDELIVERED, "Previous Month 6 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH6COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH6COSTCONSUMPTIONRECEIVED, "Previous Month 6 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH7COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH7COSTCONSUMPTIONDELIVERED, "Previous Month 7 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH7COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH7COSTCONSUMPTIONRECEIVED, "Previous Month 7 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH8COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH8COSTCONSUMPTIONDELIVERED, "Previous Month 8 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH8COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH8COSTCONSUMPTIONRECEIVED, "Previous Month 8 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH9COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH9COSTCONSUMPTIONDELIVERED, "Previous Month 9 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH9COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH9COSTCONSUMPTIONRECEIVED, "Previous Month 9 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH10COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH10COSTCONSUMPTIONDELIVERED, "Previous Month 10 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH10COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH10COSTCONSUMPTIONRECEIVED, "Previous Month 10 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH11COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH11COSTCONSUMPTIONDELIVERED, "Previous Month 11 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH11COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH11COSTCONSUMPTIONRECEIVED, "Previous Month 11 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH12COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH12COSTCONSUMPTIONDELIVERED, "Previous Month 12 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH12COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH12COSTCONSUMPTIONRECEIVED, "Previous Month 12 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH13COSTCONSUMPTIONDELIVERED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH13COSTCONSUMPTIONDELIVERED, "Previous Month 13 Cost Consumption Delivered", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PREVIOUSMONTH13COSTCONSUMPTIONRECEIVED, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_PREVIOUSMONTH13COSTCONSUMPTIONRECEIVED, "Previous Month 13 Cost Consumption Received", ZclDataType.UNSIGNED_48_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_HISTORICALFREEZETIME, new ZclAttribute(ZclClusterType.PREPAYMENT, ATTR_HISTORICALFREEZETIME, "Historical Freeze Time", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Prepayment cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclPrepaymentCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>Payment Control Configuration</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPaymentControlConfigurationAsync() {
        return read(attributes.get(ATTR_PAYMENTCONTROLCONFIGURATION));
    }

    /**
     * Synchronously get the <i>Payment Control Configuration</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * ADDME
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
    public Integer getPaymentControlConfiguration(final long refreshPeriod) {
        if (attributes.get(ATTR_PAYMENTCONTROLCONFIGURATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PAYMENTCONTROLCONFIGURATION).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PAYMENTCONTROLCONFIGURATION));
    }

    /**
     * Set reporting for the <i>Payment Control Configuration</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPaymentControlConfigurationReporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_PAYMENTCONTROLCONFIGURATION), minInterval, maxInterval);
    }

    /**
     * Get the <i>Credit Remaining</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditRemainingAsync() {
        return read(attributes.get(ATTR_CREDITREMAINING));
    }

    /**
     * Synchronously get the <i>Credit Remaining</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * ADDME
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
    public Integer getCreditRemaining(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITREMAINING).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CREDITREMAINING).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CREDITREMAINING));
    }

    /**
     * Set reporting for the <i>Credit Remaining</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCreditRemainingReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CREDITREMAINING), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Emergency Credit Remaining</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getEmergencyCreditRemainingAsync() {
        return read(attributes.get(ATTR_EMERGENCYCREDITREMAINING));
    }

    /**
     * Synchronously get the <i>Emergency Credit Remaining</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * ADDME
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
    public Integer getEmergencyCreditRemaining(final long refreshPeriod) {
        if (attributes.get(ATTR_EMERGENCYCREDITREMAINING).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_EMERGENCYCREDITREMAINING).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_EMERGENCYCREDITREMAINING));
    }

    /**
     * Set reporting for the <i>Emergency Credit Remaining</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setEmergencyCreditRemainingReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_EMERGENCYCREDITREMAINING), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Credit Status</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditStatusAsync() {
        return read(attributes.get(ATTR_CREDITSTATUS));
    }

    /**
     * Synchronously get the <i>Credit Status</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * ADDME
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
    public Integer getCreditStatus(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITSTATUS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CREDITSTATUS).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CREDITSTATUS));
    }

    /**
     * Set reporting for the <i>Credit Status</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCreditStatusReporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_CREDITSTATUS), minInterval, maxInterval);
    }

    /**
     * Get the <i>Credit Remaining Timestamp</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditRemainingTimestampAsync() {
        return read(attributes.get(ATTR_CREDITREMAININGTIMESTAMP));
    }

    /**
     * Synchronously get the <i>Credit Remaining Timestamp</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * ADDME
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getCreditRemainingTimestamp(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITREMAININGTIMESTAMP).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_CREDITREMAININGTIMESTAMP).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_CREDITREMAININGTIMESTAMP));
    }

    /**
     * Set reporting for the <i>Credit Remaining Timestamp</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCreditRemainingTimestampReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CREDITREMAININGTIMESTAMP), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Accumulated Debt</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getAccumulatedDebtAsync() {
        return read(attributes.get(ATTR_ACCUMULATEDDEBT));
    }

    /**
     * Synchronously get the <i>Accumulated Debt</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * ADDME
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
    public Integer getAccumulatedDebt(final long refreshPeriod) {
        if (attributes.get(ATTR_ACCUMULATEDDEBT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ACCUMULATEDDEBT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ACCUMULATEDDEBT));
    }

    /**
     * Set reporting for the <i>Accumulated Debt</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setAccumulatedDebtReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_ACCUMULATEDDEBT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Overall Debt Cap</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getOverallDebtCapAsync() {
        return read(attributes.get(ATTR_OVERALLDEBTCAP));
    }

    /**
     * Synchronously get the <i>Overall Debt Cap</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * ADDME
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
    public Integer getOverallDebtCap(final long refreshPeriod) {
        if (attributes.get(ATTR_OVERALLDEBTCAP).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_OVERALLDEBTCAP).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_OVERALLDEBTCAP));
    }

    /**
     * Set reporting for the <i>Overall Debt Cap</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setOverallDebtCapReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_OVERALLDEBTCAP), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Emergency Credit Limit Allowance</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getEmergencyCreditLimitAllowanceAsync() {
        return read(attributes.get(ATTR_EMERGENCYCREDITLIMITALLOWANCE));
    }

    /**
     * Synchronously get the <i>Emergency Credit Limit Allowance</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * ADDME
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
    public Integer getEmergencyCreditLimitAllowance(final long refreshPeriod) {
        if (attributes.get(ATTR_EMERGENCYCREDITLIMITALLOWANCE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_EMERGENCYCREDITLIMITALLOWANCE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_EMERGENCYCREDITLIMITALLOWANCE));
    }

    /**
     * Set reporting for the <i>Emergency Credit Limit Allowance</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setEmergencyCreditLimitAllowanceReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_EMERGENCYCREDITLIMITALLOWANCE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Emergency Credit Threshold</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getEmergencyCreditThresholdAsync() {
        return read(attributes.get(ATTR_EMERGENCYCREDITTHRESHOLD));
    }

    /**
     * Synchronously get the <i>Emergency Credit Threshold</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * ADDME
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
    public Integer getEmergencyCreditThreshold(final long refreshPeriod) {
        if (attributes.get(ATTR_EMERGENCYCREDITTHRESHOLD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_EMERGENCYCREDITTHRESHOLD).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_EMERGENCYCREDITTHRESHOLD));
    }

    /**
     * Set reporting for the <i>Emergency Credit Threshold</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setEmergencyCreditThresholdReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_EMERGENCYCREDITTHRESHOLD), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Total Credit Added</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTotalCreditAddedAsync() {
        return read(attributes.get(ATTR_TOTALCREDITADDED));
    }

    /**
     * Synchronously get the <i>Total Credit Added</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * ADDME
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
    public Integer getTotalCreditAdded(final long refreshPeriod) {
        if (attributes.get(ATTR_TOTALCREDITADDED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TOTALCREDITADDED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TOTALCREDITADDED));
    }

    /**
     * Set reporting for the <i>Total Credit Added</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTotalCreditAddedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_TOTALCREDITADDED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Max Credit Limit</i> attribute [attribute ID <b>0x0021</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMaxCreditLimitAsync() {
        return read(attributes.get(ATTR_MAXCREDITLIMIT));
    }

    /**
     * Synchronously get the <i>Max Credit Limit</i> attribute [attribute ID <b>0x0021</b>].
     * <p>
     * ADDME
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
    public Integer getMaxCreditLimit(final long refreshPeriod) {
        if (attributes.get(ATTR_MAXCREDITLIMIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MAXCREDITLIMIT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MAXCREDITLIMIT));
    }

    /**
     * Set reporting for the <i>Max Credit Limit</i> attribute [attribute ID <b>0x0021</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMaxCreditLimitReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_MAXCREDITLIMIT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Max Credit Per Top Up</i> attribute [attribute ID <b>0x0022</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMaxCreditPerTopUpAsync() {
        return read(attributes.get(ATTR_MAXCREDITPERTOPUP));
    }

    /**
     * Synchronously get the <i>Max Credit Per Top Up</i> attribute [attribute ID <b>0x0022</b>].
     * <p>
     * ADDME
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
    public Integer getMaxCreditPerTopUp(final long refreshPeriod) {
        if (attributes.get(ATTR_MAXCREDITPERTOPUP).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MAXCREDITPERTOPUP).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MAXCREDITPERTOPUP));
    }

    /**
     * Set reporting for the <i>Max Credit Per Top Up</i> attribute [attribute ID <b>0x0022</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMaxCreditPerTopUpReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_MAXCREDITPERTOPUP), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Friendly Credit Warning</i> attribute [attribute ID <b>0x0030</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getFriendlyCreditWarningAsync() {
        return read(attributes.get(ATTR_FRIENDLYCREDITWARNING));
    }

    /**
     * Synchronously get the <i>Friendly Credit Warning</i> attribute [attribute ID <b>0x0030</b>].
     * <p>
     * ADDME
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
    public Integer getFriendlyCreditWarning(final long refreshPeriod) {
        if (attributes.get(ATTR_FRIENDLYCREDITWARNING).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_FRIENDLYCREDITWARNING).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_FRIENDLYCREDITWARNING));
    }

    /**
     * Set reporting for the <i>Friendly Credit Warning</i> attribute [attribute ID <b>0x0030</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setFriendlyCreditWarningReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_FRIENDLYCREDITWARNING), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Low Credit Warning Level</i> attribute [attribute ID <b>0x0031</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLowCreditWarningLevelAsync() {
        return read(attributes.get(ATTR_LOWCREDITWARNINGLEVEL));
    }

    /**
     * Synchronously get the <i>Low Credit Warning Level</i> attribute [attribute ID <b>0x0031</b>].
     * <p>
     * ADDME
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
    public Integer getLowCreditWarningLevel(final long refreshPeriod) {
        if (attributes.get(ATTR_LOWCREDITWARNINGLEVEL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_LOWCREDITWARNINGLEVEL).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_LOWCREDITWARNINGLEVEL));
    }

    /**
     * Set reporting for the <i>Low Credit Warning Level</i> attribute [attribute ID <b>0x0031</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setLowCreditWarningLevelReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_LOWCREDITWARNINGLEVEL), minInterval, maxInterval, reportableChange);
    }

    /**
     * Set the <i>Ihd Low Credit Warning Level</i> attribute [attribute ID <b>0x0032</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param ihdLowCreditWarningLevel the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setIhdLowCreditWarningLevel(final Integer value) {
        return write(attributes.get(ATTR_IHDLOWCREDITWARNINGLEVEL), value);
    }

    /**
     * Get the <i>Ihd Low Credit Warning Level</i> attribute [attribute ID <b>0x0032</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getIhdLowCreditWarningLevelAsync() {
        return read(attributes.get(ATTR_IHDLOWCREDITWARNINGLEVEL));
    }

    /**
     * Synchronously get the <i>Ihd Low Credit Warning Level</i> attribute [attribute ID <b>0x0032</b>].
     * <p>
     * ADDME
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
    public Integer getIhdLowCreditWarningLevel(final long refreshPeriod) {
        if (attributes.get(ATTR_IHDLOWCREDITWARNINGLEVEL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_IHDLOWCREDITWARNINGLEVEL).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_IHDLOWCREDITWARNINGLEVEL));
    }

    /**
     * Get the <i>Interrupt Suspend Time</i> attribute [attribute ID <b>0x0033</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getInterruptSuspendTimeAsync() {
        return read(attributes.get(ATTR_INTERRUPTSUSPENDTIME));
    }

    /**
     * Synchronously get the <i>Interrupt Suspend Time</i> attribute [attribute ID <b>0x0033</b>].
     * <p>
     * ADDME
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
    public Integer getInterruptSuspendTime(final long refreshPeriod) {
        if (attributes.get(ATTR_INTERRUPTSUSPENDTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_INTERRUPTSUSPENDTIME).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_INTERRUPTSUSPENDTIME));
    }

    /**
     * Set reporting for the <i>Interrupt Suspend Time</i> attribute [attribute ID <b>0x0033</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setInterruptSuspendTimeReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_INTERRUPTSUSPENDTIME), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Remaining Friendly Credit Time</i> attribute [attribute ID <b>0x0034</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRemainingFriendlyCreditTimeAsync() {
        return read(attributes.get(ATTR_REMAININGFRIENDLYCREDITTIME));
    }

    /**
     * Synchronously get the <i>Remaining Friendly Credit Time</i> attribute [attribute ID <b>0x0034</b>].
     * <p>
     * ADDME
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
    public Integer getRemainingFriendlyCreditTime(final long refreshPeriod) {
        if (attributes.get(ATTR_REMAININGFRIENDLYCREDITTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_REMAININGFRIENDLYCREDITTIME).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_REMAININGFRIENDLYCREDITTIME));
    }

    /**
     * Set reporting for the <i>Remaining Friendly Credit Time</i> attribute [attribute ID <b>0x0034</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setRemainingFriendlyCreditTimeReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_REMAININGFRIENDLYCREDITTIME), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Next Friendly Credit Period</i> attribute [attribute ID <b>0x0035</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getNextFriendlyCreditPeriodAsync() {
        return read(attributes.get(ATTR_NEXTFRIENDLYCREDITPERIOD));
    }

    /**
     * Synchronously get the <i>Next Friendly Credit Period</i> attribute [attribute ID <b>0x0035</b>].
     * <p>
     * ADDME
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getNextFriendlyCreditPeriod(final long refreshPeriod) {
        if (attributes.get(ATTR_NEXTFRIENDLYCREDITPERIOD).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_NEXTFRIENDLYCREDITPERIOD).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_NEXTFRIENDLYCREDITPERIOD));
    }

    /**
     * Set reporting for the <i>Next Friendly Credit Period</i> attribute [attribute ID <b>0x0035</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setNextFriendlyCreditPeriodReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_NEXTFRIENDLYCREDITPERIOD), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Cut Off Value</i> attribute [attribute ID <b>0x0040</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCutOffValueAsync() {
        return read(attributes.get(ATTR_CUTOFFVALUE));
    }

    /**
     * Synchronously get the <i>Cut Off Value</i> attribute [attribute ID <b>0x0040</b>].
     * <p>
     * ADDME
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
    public Integer getCutOffValue(final long refreshPeriod) {
        if (attributes.get(ATTR_CUTOFFVALUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CUTOFFVALUE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CUTOFFVALUE));
    }

    /**
     * Set reporting for the <i>Cut Off Value</i> attribute [attribute ID <b>0x0040</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCutOffValueReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CUTOFFVALUE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Set the <i>Token Carrier ID</i> attribute [attribute ID <b>0x0080</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param tokenCarrierId the {@link ByteArray} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTokenCarrierId(final ByteArray value) {
        return write(attributes.get(ATTR_TOKENCARRIERID), value);
    }

    /**
     * Get the <i>Token Carrier ID</i> attribute [attribute ID <b>0x0080</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTokenCarrierIdAsync() {
        return read(attributes.get(ATTR_TOKENCARRIERID));
    }

    /**
     * Synchronously get the <i>Token Carrier ID</i> attribute [attribute ID <b>0x0080</b>].
     * <p>
     * ADDME
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link ByteArray} attribute value, or null on error
     */
    public ByteArray getTokenCarrierId(final long refreshPeriod) {
        if (attributes.get(ATTR_TOKENCARRIERID).isLastValueCurrent(refreshPeriod)) {
            return (ByteArray) attributes.get(ATTR_TOKENCARRIERID).getLastValue();
        }

        return (ByteArray) readSync(attributes.get(ATTR_TOKENCARRIERID));
    }

    /**
     * Get the <i>Top Up Date / time #1</i> attribute [attribute ID <b>0x0100</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpDateTime1Async() {
        return read(attributes.get(ATTR_TOPUPDATETIME1));
    }

    /**
     * Synchronously get the <i>Top Up Date / time #1</i> attribute [attribute ID <b>0x0100</b>].
     * <p>
     * ADDME
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getTopUpDateTime1(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPDATETIME1).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_TOPUPDATETIME1).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_TOPUPDATETIME1));
    }

    /**
     * Set reporting for the <i>Top Up Date / time #1</i> attribute [attribute ID <b>0x0100</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpDateTime1Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_TOPUPDATETIME1), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Top Up Amount #1</i> attribute [attribute ID <b>0x0101</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpAmount1Async() {
        return read(attributes.get(ATTR_TOPUPAMOUNT1));
    }

    /**
     * Synchronously get the <i>Top Up Amount #1</i> attribute [attribute ID <b>0x0101</b>].
     * <p>
     * ADDME
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
    public Integer getTopUpAmount1(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPAMOUNT1).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TOPUPAMOUNT1).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TOPUPAMOUNT1));
    }

    /**
     * Set reporting for the <i>Top Up Amount #1</i> attribute [attribute ID <b>0x0101</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpAmount1Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_TOPUPAMOUNT1), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Top Up Originating Device #1</i> attribute [attribute ID <b>0x0102</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpOriginatingDevice1Async() {
        return read(attributes.get(ATTR_TOPUPORIGINATINGDEVICE1));
    }

    /**
     * Synchronously get the <i>Top Up Originating Device #1</i> attribute [attribute ID <b>0x0102</b>].
     * <p>
     * ADDME
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
    public Integer getTopUpOriginatingDevice1(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPORIGINATINGDEVICE1).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TOPUPORIGINATINGDEVICE1).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TOPUPORIGINATINGDEVICE1));
    }

    /**
     * Set reporting for the <i>Top Up Originating Device #1</i> attribute [attribute ID <b>0x0102</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpOriginatingDevice1Reporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_TOPUPORIGINATINGDEVICE1), minInterval, maxInterval);
    }

    /**
     * Get the <i>Top Up Code #1</i> attribute [attribute ID <b>0x0103</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpCode1Async() {
        return read(attributes.get(ATTR_TOPUPCODE1));
    }

    /**
     * Synchronously get the <i>Top Up Code #1</i> attribute [attribute ID <b>0x0103</b>].
     * <p>
     * ADDME
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link ByteArray} attribute value, or null on error
     */
    public ByteArray getTopUpCode1(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPCODE1).isLastValueCurrent(refreshPeriod)) {
            return (ByteArray) attributes.get(ATTR_TOPUPCODE1).getLastValue();
        }

        return (ByteArray) readSync(attributes.get(ATTR_TOPUPCODE1));
    }

    /**
     * Set reporting for the <i>Top Up Code #1</i> attribute [attribute ID <b>0x0103</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpCode1Reporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_TOPUPCODE1), minInterval, maxInterval);
    }

    /**
     * Get the <i>Top Up Date /time #2</i> attribute [attribute ID <b>0x0110</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpDateTime2Async() {
        return read(attributes.get(ATTR_TOPUPDATETIME2));
    }

    /**
     * Synchronously get the <i>Top Up Date /time #2</i> attribute [attribute ID <b>0x0110</b>].
     * <p>
     * ADDME
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getTopUpDateTime2(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPDATETIME2).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_TOPUPDATETIME2).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_TOPUPDATETIME2));
    }

    /**
     * Set reporting for the <i>Top Up Date /time #2</i> attribute [attribute ID <b>0x0110</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpDateTime2Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_TOPUPDATETIME2), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Top Up Amount #2</i> attribute [attribute ID <b>0x0111</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpAmount2Async() {
        return read(attributes.get(ATTR_TOPUPAMOUNT2));
    }

    /**
     * Synchronously get the <i>Top Up Amount #2</i> attribute [attribute ID <b>0x0111</b>].
     * <p>
     * ADDME
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
    public Integer getTopUpAmount2(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPAMOUNT2).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TOPUPAMOUNT2).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TOPUPAMOUNT2));
    }

    /**
     * Set reporting for the <i>Top Up Amount #2</i> attribute [attribute ID <b>0x0111</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpAmount2Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_TOPUPAMOUNT2), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Top Up Originating Device #2</i> attribute [attribute ID <b>0x0112</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpOriginatingDevice2Async() {
        return read(attributes.get(ATTR_TOPUPORIGINATINGDEVICE2));
    }

    /**
     * Synchronously get the <i>Top Up Originating Device #2</i> attribute [attribute ID <b>0x0112</b>].
     * <p>
     * ADDME
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
    public Integer getTopUpOriginatingDevice2(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPORIGINATINGDEVICE2).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TOPUPORIGINATINGDEVICE2).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TOPUPORIGINATINGDEVICE2));
    }

    /**
     * Set reporting for the <i>Top Up Originating Device #2</i> attribute [attribute ID <b>0x0112</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpOriginatingDevice2Reporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_TOPUPORIGINATINGDEVICE2), minInterval, maxInterval);
    }

    /**
     * Get the <i>Top Up Code #2</i> attribute [attribute ID <b>0x0113</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpCode2Async() {
        return read(attributes.get(ATTR_TOPUPCODE2));
    }

    /**
     * Synchronously get the <i>Top Up Code #2</i> attribute [attribute ID <b>0x0113</b>].
     * <p>
     * ADDME
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link ByteArray} attribute value, or null on error
     */
    public ByteArray getTopUpCode2(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPCODE2).isLastValueCurrent(refreshPeriod)) {
            return (ByteArray) attributes.get(ATTR_TOPUPCODE2).getLastValue();
        }

        return (ByteArray) readSync(attributes.get(ATTR_TOPUPCODE2));
    }

    /**
     * Set reporting for the <i>Top Up Code #2</i> attribute [attribute ID <b>0x0113</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpCode2Reporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_TOPUPCODE2), minInterval, maxInterval);
    }

    /**
     * Get the <i>Top Up Date /time #3</i> attribute [attribute ID <b>0x0120</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpDateTime3Async() {
        return read(attributes.get(ATTR_TOPUPDATETIME3));
    }

    /**
     * Synchronously get the <i>Top Up Date /time #3</i> attribute [attribute ID <b>0x0120</b>].
     * <p>
     * ADDME
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getTopUpDateTime3(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPDATETIME3).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_TOPUPDATETIME3).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_TOPUPDATETIME3));
    }

    /**
     * Set reporting for the <i>Top Up Date /time #3</i> attribute [attribute ID <b>0x0120</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpDateTime3Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_TOPUPDATETIME3), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Top Up Amount #3</i> attribute [attribute ID <b>0x0121</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpAmount3Async() {
        return read(attributes.get(ATTR_TOPUPAMOUNT3));
    }

    /**
     * Synchronously get the <i>Top Up Amount #3</i> attribute [attribute ID <b>0x0121</b>].
     * <p>
     * ADDME
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
    public Integer getTopUpAmount3(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPAMOUNT3).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TOPUPAMOUNT3).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TOPUPAMOUNT3));
    }

    /**
     * Set reporting for the <i>Top Up Amount #3</i> attribute [attribute ID <b>0x0121</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpAmount3Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_TOPUPAMOUNT3), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Top Up Originating Device #3</i> attribute [attribute ID <b>0x0122</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpOriginatingDevice3Async() {
        return read(attributes.get(ATTR_TOPUPORIGINATINGDEVICE3));
    }

    /**
     * Synchronously get the <i>Top Up Originating Device #3</i> attribute [attribute ID <b>0x0122</b>].
     * <p>
     * ADDME
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
    public Integer getTopUpOriginatingDevice3(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPORIGINATINGDEVICE3).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TOPUPORIGINATINGDEVICE3).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TOPUPORIGINATINGDEVICE3));
    }

    /**
     * Set reporting for the <i>Top Up Originating Device #3</i> attribute [attribute ID <b>0x0122</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpOriginatingDevice3Reporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_TOPUPORIGINATINGDEVICE3), minInterval, maxInterval);
    }

    /**
     * Get the <i>Top Up Code #3</i> attribute [attribute ID <b>0x0123</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpCode3Async() {
        return read(attributes.get(ATTR_TOPUPCODE3));
    }

    /**
     * Synchronously get the <i>Top Up Code #3</i> attribute [attribute ID <b>0x0123</b>].
     * <p>
     * ADDME
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link ByteArray} attribute value, or null on error
     */
    public ByteArray getTopUpCode3(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPCODE3).isLastValueCurrent(refreshPeriod)) {
            return (ByteArray) attributes.get(ATTR_TOPUPCODE3).getLastValue();
        }

        return (ByteArray) readSync(attributes.get(ATTR_TOPUPCODE3));
    }

    /**
     * Set reporting for the <i>Top Up Code #3</i> attribute [attribute ID <b>0x0123</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpCode3Reporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_TOPUPCODE3), minInterval, maxInterval);
    }

    /**
     * Get the <i>Top Up Date /time #4</i> attribute [attribute ID <b>0x0130</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpDateTime4Async() {
        return read(attributes.get(ATTR_TOPUPDATETIME4));
    }

    /**
     * Synchronously get the <i>Top Up Date /time #4</i> attribute [attribute ID <b>0x0130</b>].
     * <p>
     * ADDME
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getTopUpDateTime4(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPDATETIME4).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_TOPUPDATETIME4).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_TOPUPDATETIME4));
    }

    /**
     * Set reporting for the <i>Top Up Date /time #4</i> attribute [attribute ID <b>0x0130</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpDateTime4Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_TOPUPDATETIME4), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Top Up Amount #4</i> attribute [attribute ID <b>0x0131</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpAmount4Async() {
        return read(attributes.get(ATTR_TOPUPAMOUNT4));
    }

    /**
     * Synchronously get the <i>Top Up Amount #4</i> attribute [attribute ID <b>0x0131</b>].
     * <p>
     * ADDME
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
    public Integer getTopUpAmount4(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPAMOUNT4).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TOPUPAMOUNT4).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TOPUPAMOUNT4));
    }

    /**
     * Set reporting for the <i>Top Up Amount #4</i> attribute [attribute ID <b>0x0131</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpAmount4Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_TOPUPAMOUNT4), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Top Up Originating Device #4</i> attribute [attribute ID <b>0x0132</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpOriginatingDevice4Async() {
        return read(attributes.get(ATTR_TOPUPORIGINATINGDEVICE4));
    }

    /**
     * Synchronously get the <i>Top Up Originating Device #4</i> attribute [attribute ID <b>0x0132</b>].
     * <p>
     * ADDME
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
    public Integer getTopUpOriginatingDevice4(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPORIGINATINGDEVICE4).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TOPUPORIGINATINGDEVICE4).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TOPUPORIGINATINGDEVICE4));
    }

    /**
     * Set reporting for the <i>Top Up Originating Device #4</i> attribute [attribute ID <b>0x0132</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpOriginatingDevice4Reporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_TOPUPORIGINATINGDEVICE4), minInterval, maxInterval);
    }

    /**
     * Get the <i>Top Up Code #4</i> attribute [attribute ID <b>0x0133</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpCode4Async() {
        return read(attributes.get(ATTR_TOPUPCODE4));
    }

    /**
     * Synchronously get the <i>Top Up Code #4</i> attribute [attribute ID <b>0x0133</b>].
     * <p>
     * ADDME
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link ByteArray} attribute value, or null on error
     */
    public ByteArray getTopUpCode4(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPCODE4).isLastValueCurrent(refreshPeriod)) {
            return (ByteArray) attributes.get(ATTR_TOPUPCODE4).getLastValue();
        }

        return (ByteArray) readSync(attributes.get(ATTR_TOPUPCODE4));
    }

    /**
     * Set reporting for the <i>Top Up Code #4</i> attribute [attribute ID <b>0x0133</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpCode4Reporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_TOPUPCODE4), minInterval, maxInterval);
    }

    /**
     * Get the <i>Top Up Date /time #5</i> attribute [attribute ID <b>0x0140</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpDateTime5Async() {
        return read(attributes.get(ATTR_TOPUPDATETIME5));
    }

    /**
     * Synchronously get the <i>Top Up Date /time #5</i> attribute [attribute ID <b>0x0140</b>].
     * <p>
     * ADDME
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getTopUpDateTime5(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPDATETIME5).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_TOPUPDATETIME5).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_TOPUPDATETIME5));
    }

    /**
     * Set reporting for the <i>Top Up Date /time #5</i> attribute [attribute ID <b>0x0140</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpDateTime5Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_TOPUPDATETIME5), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Top Up Amount #5</i> attribute [attribute ID <b>0x0141</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpAmount5Async() {
        return read(attributes.get(ATTR_TOPUPAMOUNT5));
    }

    /**
     * Synchronously get the <i>Top Up Amount #5</i> attribute [attribute ID <b>0x0141</b>].
     * <p>
     * ADDME
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
    public Integer getTopUpAmount5(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPAMOUNT5).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TOPUPAMOUNT5).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TOPUPAMOUNT5));
    }

    /**
     * Set reporting for the <i>Top Up Amount #5</i> attribute [attribute ID <b>0x0141</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpAmount5Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_TOPUPAMOUNT5), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Top Up Originating Device #5</i> attribute [attribute ID <b>0x0142</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpOriginatingDevice5Async() {
        return read(attributes.get(ATTR_TOPUPORIGINATINGDEVICE5));
    }

    /**
     * Synchronously get the <i>Top Up Originating Device #5</i> attribute [attribute ID <b>0x0142</b>].
     * <p>
     * ADDME
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
    public Integer getTopUpOriginatingDevice5(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPORIGINATINGDEVICE5).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TOPUPORIGINATINGDEVICE5).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TOPUPORIGINATINGDEVICE5));
    }

    /**
     * Set reporting for the <i>Top Up Originating Device #5</i> attribute [attribute ID <b>0x0142</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpOriginatingDevice5Reporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_TOPUPORIGINATINGDEVICE5), minInterval, maxInterval);
    }

    /**
     * Get the <i>Top Up Code #5</i> attribute [attribute ID <b>0x0143</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpCode5Async() {
        return read(attributes.get(ATTR_TOPUPCODE5));
    }

    /**
     * Synchronously get the <i>Top Up Code #5</i> attribute [attribute ID <b>0x0143</b>].
     * <p>
     * ADDME
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link ByteArray} attribute value, or null on error
     */
    public ByteArray getTopUpCode5(final long refreshPeriod) {
        if (attributes.get(ATTR_TOPUPCODE5).isLastValueCurrent(refreshPeriod)) {
            return (ByteArray) attributes.get(ATTR_TOPUPCODE5).getLastValue();
        }

        return (ByteArray) readSync(attributes.get(ATTR_TOPUPCODE5));
    }

    /**
     * Set reporting for the <i>Top Up Code #5</i> attribute [attribute ID <b>0x0143</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTopUpCode5Reporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_TOPUPCODE5), minInterval, maxInterval);
    }

    /**
     * Get the <i>Debt Label 1</i> attribute [attribute ID <b>0x0210</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtLabel1Async() {
        return read(attributes.get(ATTR_DEBTLABEL1));
    }

    /**
     * Synchronously get the <i>Debt Label 1</i> attribute [attribute ID <b>0x0210</b>].
     * <p>
     * ADDME
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link ByteArray} attribute value, or null on error
     */
    public ByteArray getDebtLabel1(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTLABEL1).isLastValueCurrent(refreshPeriod)) {
            return (ByteArray) attributes.get(ATTR_DEBTLABEL1).getLastValue();
        }

        return (ByteArray) readSync(attributes.get(ATTR_DEBTLABEL1));
    }

    /**
     * Set reporting for the <i>Debt Label 1</i> attribute [attribute ID <b>0x0210</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtLabel1Reporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_DEBTLABEL1), minInterval, maxInterval);
    }

    /**
     * Get the <i>Debt Amount 1</i> attribute [attribute ID <b>0x0211</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtAmount1Async() {
        return read(attributes.get(ATTR_DEBTAMOUNT1));
    }

    /**
     * Synchronously get the <i>Debt Amount 1</i> attribute [attribute ID <b>0x0211</b>].
     * <p>
     * ADDME
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
    public Integer getDebtAmount1(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTAMOUNT1).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEBTAMOUNT1).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEBTAMOUNT1));
    }

    /**
     * Set reporting for the <i>Debt Amount 1</i> attribute [attribute ID <b>0x0211</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtAmount1Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_DEBTAMOUNT1), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Debt Recovery Method 1</i> attribute [attribute ID <b>0x0212</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtRecoveryMethod1Async() {
        return read(attributes.get(ATTR_DEBTRECOVERYMETHOD1));
    }

    /**
     * Synchronously get the <i>Debt Recovery Method 1</i> attribute [attribute ID <b>0x0212</b>].
     * <p>
     * ADDME
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
    public Integer getDebtRecoveryMethod1(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTRECOVERYMETHOD1).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEBTRECOVERYMETHOD1).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEBTRECOVERYMETHOD1));
    }

    /**
     * Set reporting for the <i>Debt Recovery Method 1</i> attribute [attribute ID <b>0x0212</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtRecoveryMethod1Reporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_DEBTRECOVERYMETHOD1), minInterval, maxInterval);
    }

    /**
     * Get the <i>Debt Recovery Start Time 1</i> attribute [attribute ID <b>0x0213</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtRecoveryStartTime1Async() {
        return read(attributes.get(ATTR_DEBTRECOVERYSTARTTIME1));
    }

    /**
     * Synchronously get the <i>Debt Recovery Start Time 1</i> attribute [attribute ID <b>0x0213</b>].
     * <p>
     * ADDME
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getDebtRecoveryStartTime1(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTRECOVERYSTARTTIME1).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_DEBTRECOVERYSTARTTIME1).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_DEBTRECOVERYSTARTTIME1));
    }

    /**
     * Set reporting for the <i>Debt Recovery Start Time 1</i> attribute [attribute ID <b>0x0213</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtRecoveryStartTime1Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_DEBTRECOVERYSTARTTIME1), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Debt Recovery Collection Time 1</i> attribute [attribute ID <b>0x0214</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtRecoveryCollectionTime1Async() {
        return read(attributes.get(ATTR_DEBTRECOVERYCOLLECTIONTIME1));
    }

    /**
     * Synchronously get the <i>Debt Recovery Collection Time 1</i> attribute [attribute ID <b>0x0214</b>].
     * <p>
     * ADDME
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
    public Integer getDebtRecoveryCollectionTime1(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTRECOVERYCOLLECTIONTIME1).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEBTRECOVERYCOLLECTIONTIME1).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEBTRECOVERYCOLLECTIONTIME1));
    }

    /**
     * Set reporting for the <i>Debt Recovery Collection Time 1</i> attribute [attribute ID <b>0x0214</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtRecoveryCollectionTime1Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_DEBTRECOVERYCOLLECTIONTIME1), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Debt Recovery Frequency 1</i> attribute [attribute ID <b>0x0216</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtRecoveryFrequency1Async() {
        return read(attributes.get(ATTR_DEBTRECOVERYFREQUENCY1));
    }

    /**
     * Synchronously get the <i>Debt Recovery Frequency 1</i> attribute [attribute ID <b>0x0216</b>].
     * <p>
     * ADDME
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
    public Integer getDebtRecoveryFrequency1(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTRECOVERYFREQUENCY1).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEBTRECOVERYFREQUENCY1).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEBTRECOVERYFREQUENCY1));
    }

    /**
     * Set reporting for the <i>Debt Recovery Frequency 1</i> attribute [attribute ID <b>0x0216</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtRecoveryFrequency1Reporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_DEBTRECOVERYFREQUENCY1), minInterval, maxInterval);
    }

    /**
     * Get the <i>Debt Recovery Amount 1</i> attribute [attribute ID <b>0x0217</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtRecoveryAmount1Async() {
        return read(attributes.get(ATTR_DEBTRECOVERYAMOUNT1));
    }

    /**
     * Synchronously get the <i>Debt Recovery Amount 1</i> attribute [attribute ID <b>0x0217</b>].
     * <p>
     * ADDME
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
    public Integer getDebtRecoveryAmount1(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTRECOVERYAMOUNT1).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEBTRECOVERYAMOUNT1).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEBTRECOVERYAMOUNT1));
    }

    /**
     * Set reporting for the <i>Debt Recovery Amount 1</i> attribute [attribute ID <b>0x0217</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtRecoveryAmount1Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_DEBTRECOVERYAMOUNT1), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Debt Recovery Top Up Percentage 1</i> attribute [attribute ID <b>0x0219</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtRecoveryTopUpPercentage1Async() {
        return read(attributes.get(ATTR_DEBTRECOVERYTOPUPPERCENTAGE1));
    }

    /**
     * Synchronously get the <i>Debt Recovery Top Up Percentage 1</i> attribute [attribute ID <b>0x0219</b>].
     * <p>
     * ADDME
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
    public Integer getDebtRecoveryTopUpPercentage1(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTRECOVERYTOPUPPERCENTAGE1).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEBTRECOVERYTOPUPPERCENTAGE1).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEBTRECOVERYTOPUPPERCENTAGE1));
    }

    /**
     * Set reporting for the <i>Debt Recovery Top Up Percentage 1</i> attribute [attribute ID <b>0x0219</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtRecoveryTopUpPercentage1Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_DEBTRECOVERYTOPUPPERCENTAGE1), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Debt Label 2</i> attribute [attribute ID <b>0x0220</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtLabel2Async() {
        return read(attributes.get(ATTR_DEBTLABEL2));
    }

    /**
     * Synchronously get the <i>Debt Label 2</i> attribute [attribute ID <b>0x0220</b>].
     * <p>
     * ADDME
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link ByteArray} attribute value, or null on error
     */
    public ByteArray getDebtLabel2(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTLABEL2).isLastValueCurrent(refreshPeriod)) {
            return (ByteArray) attributes.get(ATTR_DEBTLABEL2).getLastValue();
        }

        return (ByteArray) readSync(attributes.get(ATTR_DEBTLABEL2));
    }

    /**
     * Set reporting for the <i>Debt Label 2</i> attribute [attribute ID <b>0x0220</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtLabel2Reporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_DEBTLABEL2), minInterval, maxInterval);
    }

    /**
     * Get the <i>Debt Amount 2</i> attribute [attribute ID <b>0x0221</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtAmount2Async() {
        return read(attributes.get(ATTR_DEBTAMOUNT2));
    }

    /**
     * Synchronously get the <i>Debt Amount 2</i> attribute [attribute ID <b>0x0221</b>].
     * <p>
     * ADDME
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
    public Integer getDebtAmount2(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTAMOUNT2).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEBTAMOUNT2).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEBTAMOUNT2));
    }

    /**
     * Set reporting for the <i>Debt Amount 2</i> attribute [attribute ID <b>0x0221</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtAmount2Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_DEBTAMOUNT2), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Debt Recovery Method 2</i> attribute [attribute ID <b>0x0222</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtRecoveryMethod2Async() {
        return read(attributes.get(ATTR_DEBTRECOVERYMETHOD2));
    }

    /**
     * Synchronously get the <i>Debt Recovery Method 2</i> attribute [attribute ID <b>0x0222</b>].
     * <p>
     * ADDME
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
    public Integer getDebtRecoveryMethod2(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTRECOVERYMETHOD2).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEBTRECOVERYMETHOD2).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEBTRECOVERYMETHOD2));
    }

    /**
     * Set reporting for the <i>Debt Recovery Method 2</i> attribute [attribute ID <b>0x0222</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtRecoveryMethod2Reporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_DEBTRECOVERYMETHOD2), minInterval, maxInterval);
    }

    /**
     * Get the <i>Debt Recovery Start Time 2</i> attribute [attribute ID <b>0x0223</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtRecoveryStartTime2Async() {
        return read(attributes.get(ATTR_DEBTRECOVERYSTARTTIME2));
    }

    /**
     * Synchronously get the <i>Debt Recovery Start Time 2</i> attribute [attribute ID <b>0x0223</b>].
     * <p>
     * ADDME
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getDebtRecoveryStartTime2(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTRECOVERYSTARTTIME2).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_DEBTRECOVERYSTARTTIME2).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_DEBTRECOVERYSTARTTIME2));
    }

    /**
     * Set reporting for the <i>Debt Recovery Start Time 2</i> attribute [attribute ID <b>0x0223</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtRecoveryStartTime2Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_DEBTRECOVERYSTARTTIME2), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Debt Recovery Collection Time 2</i> attribute [attribute ID <b>0x0224</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtRecoveryCollectionTime2Async() {
        return read(attributes.get(ATTR_DEBTRECOVERYCOLLECTIONTIME2));
    }

    /**
     * Synchronously get the <i>Debt Recovery Collection Time 2</i> attribute [attribute ID <b>0x0224</b>].
     * <p>
     * ADDME
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
    public Integer getDebtRecoveryCollectionTime2(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTRECOVERYCOLLECTIONTIME2).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEBTRECOVERYCOLLECTIONTIME2).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEBTRECOVERYCOLLECTIONTIME2));
    }

    /**
     * Set reporting for the <i>Debt Recovery Collection Time 2</i> attribute [attribute ID <b>0x0224</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtRecoveryCollectionTime2Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_DEBTRECOVERYCOLLECTIONTIME2), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Debt Recovery Frequency 2</i> attribute [attribute ID <b>0x0226</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtRecoveryFrequency2Async() {
        return read(attributes.get(ATTR_DEBTRECOVERYFREQUENCY2));
    }

    /**
     * Synchronously get the <i>Debt Recovery Frequency 2</i> attribute [attribute ID <b>0x0226</b>].
     * <p>
     * ADDME
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
    public Integer getDebtRecoveryFrequency2(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTRECOVERYFREQUENCY2).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEBTRECOVERYFREQUENCY2).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEBTRECOVERYFREQUENCY2));
    }

    /**
     * Set reporting for the <i>Debt Recovery Frequency 2</i> attribute [attribute ID <b>0x0226</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtRecoveryFrequency2Reporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_DEBTRECOVERYFREQUENCY2), minInterval, maxInterval);
    }

    /**
     * Get the <i>Debt Recovery Amount 2</i> attribute [attribute ID <b>0x0227</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtRecoveryAmount2Async() {
        return read(attributes.get(ATTR_DEBTRECOVERYAMOUNT2));
    }

    /**
     * Synchronously get the <i>Debt Recovery Amount 2</i> attribute [attribute ID <b>0x0227</b>].
     * <p>
     * ADDME
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
    public Integer getDebtRecoveryAmount2(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTRECOVERYAMOUNT2).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEBTRECOVERYAMOUNT2).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEBTRECOVERYAMOUNT2));
    }

    /**
     * Set reporting for the <i>Debt Recovery Amount 2</i> attribute [attribute ID <b>0x0227</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtRecoveryAmount2Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_DEBTRECOVERYAMOUNT2), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Debt Recovery Top Up Percentage 2</i> attribute [attribute ID <b>0x0229</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtRecoveryTopUpPercentage2Async() {
        return read(attributes.get(ATTR_DEBTRECOVERYTOPUPPERCENTAGE2));
    }

    /**
     * Synchronously get the <i>Debt Recovery Top Up Percentage 2</i> attribute [attribute ID <b>0x0229</b>].
     * <p>
     * ADDME
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
    public Integer getDebtRecoveryTopUpPercentage2(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTRECOVERYTOPUPPERCENTAGE2).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEBTRECOVERYTOPUPPERCENTAGE2).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEBTRECOVERYTOPUPPERCENTAGE2));
    }

    /**
     * Set reporting for the <i>Debt Recovery Top Up Percentage 2</i> attribute [attribute ID <b>0x0229</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtRecoveryTopUpPercentage2Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_DEBTRECOVERYTOPUPPERCENTAGE2), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Debt Label 3</i> attribute [attribute ID <b>0x0230</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtLabel3Async() {
        return read(attributes.get(ATTR_DEBTLABEL3));
    }

    /**
     * Synchronously get the <i>Debt Label 3</i> attribute [attribute ID <b>0x0230</b>].
     * <p>
     * ADDME
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link ByteArray} attribute value, or null on error
     */
    public ByteArray getDebtLabel3(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTLABEL3).isLastValueCurrent(refreshPeriod)) {
            return (ByteArray) attributes.get(ATTR_DEBTLABEL3).getLastValue();
        }

        return (ByteArray) readSync(attributes.get(ATTR_DEBTLABEL3));
    }

    /**
     * Set reporting for the <i>Debt Label 3</i> attribute [attribute ID <b>0x0230</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtLabel3Reporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_DEBTLABEL3), minInterval, maxInterval);
    }

    /**
     * Get the <i>Debt Amount 3</i> attribute [attribute ID <b>0x0231</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtAmount3Async() {
        return read(attributes.get(ATTR_DEBTAMOUNT3));
    }

    /**
     * Synchronously get the <i>Debt Amount 3</i> attribute [attribute ID <b>0x0231</b>].
     * <p>
     * ADDME
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
    public Integer getDebtAmount3(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTAMOUNT3).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEBTAMOUNT3).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEBTAMOUNT3));
    }

    /**
     * Set reporting for the <i>Debt Amount 3</i> attribute [attribute ID <b>0x0231</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtAmount3Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_DEBTAMOUNT3), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Debt Recovery Method 3</i> attribute [attribute ID <b>0x0232</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtRecoveryMethod3Async() {
        return read(attributes.get(ATTR_DEBTRECOVERYMETHOD3));
    }

    /**
     * Synchronously get the <i>Debt Recovery Method 3</i> attribute [attribute ID <b>0x0232</b>].
     * <p>
     * ADDME
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
    public Integer getDebtRecoveryMethod3(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTRECOVERYMETHOD3).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEBTRECOVERYMETHOD3).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEBTRECOVERYMETHOD3));
    }

    /**
     * Set reporting for the <i>Debt Recovery Method 3</i> attribute [attribute ID <b>0x0232</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtRecoveryMethod3Reporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_DEBTRECOVERYMETHOD3), minInterval, maxInterval);
    }

    /**
     * Get the <i>Debt Recovery Start Time 3</i> attribute [attribute ID <b>0x0233</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtRecoveryStartTime3Async() {
        return read(attributes.get(ATTR_DEBTRECOVERYSTARTTIME3));
    }

    /**
     * Synchronously get the <i>Debt Recovery Start Time 3</i> attribute [attribute ID <b>0x0233</b>].
     * <p>
     * ADDME
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getDebtRecoveryStartTime3(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTRECOVERYSTARTTIME3).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_DEBTRECOVERYSTARTTIME3).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_DEBTRECOVERYSTARTTIME3));
    }

    /**
     * Set reporting for the <i>Debt Recovery Start Time 3</i> attribute [attribute ID <b>0x0233</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtRecoveryStartTime3Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_DEBTRECOVERYSTARTTIME3), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Debt Recovery Collection Time 3</i> attribute [attribute ID <b>0x0234</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtRecoveryCollectionTime3Async() {
        return read(attributes.get(ATTR_DEBTRECOVERYCOLLECTIONTIME3));
    }

    /**
     * Synchronously get the <i>Debt Recovery Collection Time 3</i> attribute [attribute ID <b>0x0234</b>].
     * <p>
     * ADDME
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
    public Integer getDebtRecoveryCollectionTime3(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTRECOVERYCOLLECTIONTIME3).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEBTRECOVERYCOLLECTIONTIME3).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEBTRECOVERYCOLLECTIONTIME3));
    }

    /**
     * Set reporting for the <i>Debt Recovery Collection Time 3</i> attribute [attribute ID <b>0x0234</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtRecoveryCollectionTime3Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_DEBTRECOVERYCOLLECTIONTIME3), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Debt Recovery Frequency 3</i> attribute [attribute ID <b>0x0236</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtRecoveryFrequency3Async() {
        return read(attributes.get(ATTR_DEBTRECOVERYFREQUENCY3));
    }

    /**
     * Synchronously get the <i>Debt Recovery Frequency 3</i> attribute [attribute ID <b>0x0236</b>].
     * <p>
     * ADDME
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
    public Integer getDebtRecoveryFrequency3(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTRECOVERYFREQUENCY3).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEBTRECOVERYFREQUENCY3).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEBTRECOVERYFREQUENCY3));
    }

    /**
     * Set reporting for the <i>Debt Recovery Frequency 3</i> attribute [attribute ID <b>0x0236</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtRecoveryFrequency3Reporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_DEBTRECOVERYFREQUENCY3), minInterval, maxInterval);
    }

    /**
     * Get the <i>Debt Recovery Amount 3</i> attribute [attribute ID <b>0x0237</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtRecoveryAmount3Async() {
        return read(attributes.get(ATTR_DEBTRECOVERYAMOUNT3));
    }

    /**
     * Synchronously get the <i>Debt Recovery Amount 3</i> attribute [attribute ID <b>0x0237</b>].
     * <p>
     * ADDME
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
    public Integer getDebtRecoveryAmount3(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTRECOVERYAMOUNT3).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEBTRECOVERYAMOUNT3).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEBTRECOVERYAMOUNT3));
    }

    /**
     * Set reporting for the <i>Debt Recovery Amount 3</i> attribute [attribute ID <b>0x0237</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtRecoveryAmount3Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_DEBTRECOVERYAMOUNT3), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Debt Recovery Top Up Percentage 3</i> attribute [attribute ID <b>0x0239</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtRecoveryTopUpPercentage3Async() {
        return read(attributes.get(ATTR_DEBTRECOVERYTOPUPPERCENTAGE3));
    }

    /**
     * Synchronously get the <i>Debt Recovery Top Up Percentage 3</i> attribute [attribute ID <b>0x0239</b>].
     * <p>
     * ADDME
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
    public Integer getDebtRecoveryTopUpPercentage3(final long refreshPeriod) {
        if (attributes.get(ATTR_DEBTRECOVERYTOPUPPERCENTAGE3).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEBTRECOVERYTOPUPPERCENTAGE3).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEBTRECOVERYTOPUPPERCENTAGE3));
    }

    /**
     * Set reporting for the <i>Debt Recovery Top Up Percentage 3</i> attribute [attribute ID <b>0x0239</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDebtRecoveryTopUpPercentage3Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_DEBTRECOVERYTOPUPPERCENTAGE3), minInterval, maxInterval, reportableChange);
    }

    /**
     * Set the <i>Prepayment Alarm Status</i> attribute [attribute ID <b>0x0400</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param prepaymentAlarmStatus the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPrepaymentAlarmStatus(final Integer value) {
        return write(attributes.get(ATTR_PREPAYMENTALARMSTATUS), value);
    }

    /**
     * Get the <i>Prepayment Alarm Status</i> attribute [attribute ID <b>0x0400</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPrepaymentAlarmStatusAsync() {
        return read(attributes.get(ATTR_PREPAYMENTALARMSTATUS));
    }

    /**
     * Synchronously get the <i>Prepayment Alarm Status</i> attribute [attribute ID <b>0x0400</b>].
     * <p>
     * ADDME
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
    public Integer getPrepaymentAlarmStatus(final long refreshPeriod) {
        if (attributes.get(ATTR_PREPAYMENTALARMSTATUS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREPAYMENTALARMSTATUS).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREPAYMENTALARMSTATUS));
    }

    /**
     * Set the <i>Prepay Generic Alarm Mask</i> attribute [attribute ID <b>0x0401</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param prepayGenericAlarmMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPrepayGenericAlarmMask(final Integer value) {
        return write(attributes.get(ATTR_PREPAYGENERICALARMMASK), value);
    }

    /**
     * Get the <i>Prepay Generic Alarm Mask</i> attribute [attribute ID <b>0x0401</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPrepayGenericAlarmMaskAsync() {
        return read(attributes.get(ATTR_PREPAYGENERICALARMMASK));
    }

    /**
     * Synchronously get the <i>Prepay Generic Alarm Mask</i> attribute [attribute ID <b>0x0401</b>].
     * <p>
     * ADDME
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
    public Integer getPrepayGenericAlarmMask(final long refreshPeriod) {
        if (attributes.get(ATTR_PREPAYGENERICALARMMASK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREPAYGENERICALARMMASK).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREPAYGENERICALARMMASK));
    }

    /**
     * Set the <i>Prepay Switch Alarm Mask</i> attribute [attribute ID <b>0x0402</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param prepaySwitchAlarmMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPrepaySwitchAlarmMask(final Integer value) {
        return write(attributes.get(ATTR_PREPAYSWITCHALARMMASK), value);
    }

    /**
     * Get the <i>Prepay Switch Alarm Mask</i> attribute [attribute ID <b>0x0402</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPrepaySwitchAlarmMaskAsync() {
        return read(attributes.get(ATTR_PREPAYSWITCHALARMMASK));
    }

    /**
     * Synchronously get the <i>Prepay Switch Alarm Mask</i> attribute [attribute ID <b>0x0402</b>].
     * <p>
     * ADDME
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
    public Integer getPrepaySwitchAlarmMask(final long refreshPeriod) {
        if (attributes.get(ATTR_PREPAYSWITCHALARMMASK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREPAYSWITCHALARMMASK).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREPAYSWITCHALARMMASK));
    }

    /**
     * Set the <i>Prepay Event Alarm Mask</i> attribute [attribute ID <b>0x0403</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param prepayEventAlarmMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPrepayEventAlarmMask(final Integer value) {
        return write(attributes.get(ATTR_PREPAYEVENTALARMMASK), value);
    }

    /**
     * Get the <i>Prepay Event Alarm Mask</i> attribute [attribute ID <b>0x0403</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPrepayEventAlarmMaskAsync() {
        return read(attributes.get(ATTR_PREPAYEVENTALARMMASK));
    }

    /**
     * Synchronously get the <i>Prepay Event Alarm Mask</i> attribute [attribute ID <b>0x0403</b>].
     * <p>
     * ADDME
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
    public Integer getPrepayEventAlarmMask(final long refreshPeriod) {
        if (attributes.get(ATTR_PREPAYEVENTALARMMASK).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREPAYEVENTALARMMASK).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREPAYEVENTALARMMASK));
    }

    /**
     * Get the <i>Historical Cost Consumption Formatting</i> attribute [attribute ID <b>0x0500</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getHistoricalCostConsumptionFormattingAsync() {
        return read(attributes.get(ATTR_HISTORICALCOSTCONSUMPTIONFORMATTING));
    }

    /**
     * Synchronously get the <i>Historical Cost Consumption Formatting</i> attribute [attribute ID <b>0x0500</b>].
     * <p>
     * ADDME
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
    public Integer getHistoricalCostConsumptionFormatting(final long refreshPeriod) {
        if (attributes.get(ATTR_HISTORICALCOSTCONSUMPTIONFORMATTING).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_HISTORICALCOSTCONSUMPTIONFORMATTING).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_HISTORICALCOSTCONSUMPTIONFORMATTING));
    }

    /**
     * Set reporting for the <i>Historical Cost Consumption Formatting</i> attribute [attribute ID <b>0x0500</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setHistoricalCostConsumptionFormattingReporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_HISTORICALCOSTCONSUMPTIONFORMATTING), minInterval, maxInterval);
    }

    /**
     * Get the <i>Consumption Unit Of Measurement</i> attribute [attribute ID <b>0x0501</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getConsumptionUnitOfMeasurementAsync() {
        return read(attributes.get(ATTR_CONSUMPTIONUNITOFMEASUREMENT));
    }

    /**
     * Synchronously get the <i>Consumption Unit Of Measurement</i> attribute [attribute ID <b>0x0501</b>].
     * <p>
     * ADDME
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
    public Integer getConsumptionUnitOfMeasurement(final long refreshPeriod) {
        if (attributes.get(ATTR_CONSUMPTIONUNITOFMEASUREMENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CONSUMPTIONUNITOFMEASUREMENT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CONSUMPTIONUNITOFMEASUREMENT));
    }

    /**
     * Set reporting for the <i>Consumption Unit Of Measurement</i> attribute [attribute ID <b>0x0501</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setConsumptionUnitOfMeasurementReporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_CONSUMPTIONUNITOFMEASUREMENT), minInterval, maxInterval);
    }

    /**
     * Get the <i>Currency Scaling Factor</i> attribute [attribute ID <b>0x0502</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrencyScalingFactorAsync() {
        return read(attributes.get(ATTR_CURRENCYSCALINGFACTOR));
    }

    /**
     * Synchronously get the <i>Currency Scaling Factor</i> attribute [attribute ID <b>0x0502</b>].
     * <p>
     * ADDME
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
    public Integer getCurrencyScalingFactor(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENCYSCALINGFACTOR).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENCYSCALINGFACTOR).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENCYSCALINGFACTOR));
    }

    /**
     * Set reporting for the <i>Currency Scaling Factor</i> attribute [attribute ID <b>0x0502</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCurrencyScalingFactorReporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_CURRENCYSCALINGFACTOR), minInterval, maxInterval);
    }

    /**
     * Get the <i>Currency</i> attribute [attribute ID <b>0x0503</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrencyAsync() {
        return read(attributes.get(ATTR_CURRENCY));
    }

    /**
     * Synchronously get the <i>Currency</i> attribute [attribute ID <b>0x0503</b>].
     * <p>
     * ADDME
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
    public Integer getCurrency(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENCY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENCY).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENCY));
    }

    /**
     * Set reporting for the <i>Currency</i> attribute [attribute ID <b>0x0503</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCurrencyReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENCY), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Current Day Cost Consumption Delivered</i> attribute [attribute ID <b>0x051C</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentDayCostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_CURRENTDAYCOSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Current Day Cost Consumption Delivered</i> attribute [attribute ID <b>0x051C</b>].
     * <p>
     * ADDME
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
    public Integer getCurrentDayCostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENTDAYCOSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENTDAYCOSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTDAYCOSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Current Day Cost Consumption Delivered</i> attribute [attribute ID <b>0x051C</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCurrentDayCostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTDAYCOSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Current Day Cost Consumption Received</i> attribute [attribute ID <b>0x051D</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentDayCostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_CURRENTDAYCOSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Current Day Cost Consumption Received</i> attribute [attribute ID <b>0x051D</b>].
     * <p>
     * ADDME
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
    public Integer getCurrentDayCostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENTDAYCOSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENTDAYCOSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTDAYCOSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Current Day Cost Consumption Received</i> attribute [attribute ID <b>0x051D</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCurrentDayCostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTDAYCOSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Day Cost Consumption Delivered</i> attribute [attribute ID <b>0x051E</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousDayCostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSDAYCOSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Day Cost Consumption Delivered</i> attribute [attribute ID <b>0x051E</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousDayCostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSDAYCOSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSDAYCOSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSDAYCOSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Day Cost Consumption Delivered</i> attribute [attribute ID <b>0x051E</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousDayCostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSDAYCOSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Day Cost Consumption Received</i> attribute [attribute ID <b>0x051F</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousDayCostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSDAYCOSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Day Cost Consumption Received</i> attribute [attribute ID <b>0x051F</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousDayCostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSDAYCOSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSDAYCOSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSDAYCOSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Day Cost Consumption Received</i> attribute [attribute ID <b>0x051F</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousDayCostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSDAYCOSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Day 2 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0520</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousDay2CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSDAY2COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Day 2 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0520</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousDay2CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSDAY2COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSDAY2COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSDAY2COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Day 2 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0520</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousDay2CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSDAY2COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Day 2 Cost Consumption Received</i> attribute [attribute ID <b>0x0521</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousDay2CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSDAY2COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Day 2 Cost Consumption Received</i> attribute [attribute ID <b>0x0521</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousDay2CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSDAY2COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSDAY2COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSDAY2COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Day 2 Cost Consumption Received</i> attribute [attribute ID <b>0x0521</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousDay2CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSDAY2COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Day 3 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0522</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousDay3CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSDAY3COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Day 3 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0522</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousDay3CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSDAY3COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSDAY3COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSDAY3COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Day 3 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0522</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousDay3CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSDAY3COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Day 3 Cost Consumption Received</i> attribute [attribute ID <b>0x0523</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousDay3CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSDAY3COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Day 3 Cost Consumption Received</i> attribute [attribute ID <b>0x0523</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousDay3CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSDAY3COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSDAY3COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSDAY3COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Day 3 Cost Consumption Received</i> attribute [attribute ID <b>0x0523</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousDay3CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSDAY3COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Day 4 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0524</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousDay4CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSDAY4COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Day 4 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0524</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousDay4CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSDAY4COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSDAY4COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSDAY4COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Day 4 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0524</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousDay4CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSDAY4COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Day 4 Cost Consumption Received</i> attribute [attribute ID <b>0x0525</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousDay4CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSDAY4COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Day 4 Cost Consumption Received</i> attribute [attribute ID <b>0x0525</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousDay4CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSDAY4COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSDAY4COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSDAY4COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Day 4 Cost Consumption Received</i> attribute [attribute ID <b>0x0525</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousDay4CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSDAY4COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Day 5 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0526</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousDay5CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSDAY5COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Day 5 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0526</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousDay5CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSDAY5COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSDAY5COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSDAY5COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Day 5 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0526</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousDay5CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSDAY5COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Day 5 Cost Consumption Received</i> attribute [attribute ID <b>0x0527</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousDay5CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSDAY5COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Day 5 Cost Consumption Received</i> attribute [attribute ID <b>0x0527</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousDay5CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSDAY5COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSDAY5COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSDAY5COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Day 5 Cost Consumption Received</i> attribute [attribute ID <b>0x0527</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousDay5CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSDAY5COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Day 6 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0528</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousDay6CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSDAY6COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Day 6 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0528</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousDay6CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSDAY6COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSDAY6COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSDAY6COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Day 6 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0528</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousDay6CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSDAY6COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Day 6 Cost Consumption Received</i> attribute [attribute ID <b>0x0529</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousDay6CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSDAY6COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Day 6 Cost Consumption Received</i> attribute [attribute ID <b>0x0529</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousDay6CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSDAY6COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSDAY6COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSDAY6COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Day 6 Cost Consumption Received</i> attribute [attribute ID <b>0x0529</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousDay6CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSDAY6COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Day 7 Cost Consumption Delivered</i> attribute [attribute ID <b>0x052A</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousDay7CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSDAY7COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Day 7 Cost Consumption Delivered</i> attribute [attribute ID <b>0x052A</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousDay7CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSDAY7COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSDAY7COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSDAY7COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Day 7 Cost Consumption Delivered</i> attribute [attribute ID <b>0x052A</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousDay7CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSDAY7COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Day 7 Cost Consumption Received</i> attribute [attribute ID <b>0x052B</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousDay7CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSDAY7COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Day 7 Cost Consumption Received</i> attribute [attribute ID <b>0x052B</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousDay7CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSDAY7COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSDAY7COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSDAY7COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Day 7 Cost Consumption Received</i> attribute [attribute ID <b>0x052B</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousDay7CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSDAY7COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Day 8 Cost Consumption Delivered</i> attribute [attribute ID <b>0x052C</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousDay8CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSDAY8COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Day 8 Cost Consumption Delivered</i> attribute [attribute ID <b>0x052C</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousDay8CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSDAY8COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSDAY8COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSDAY8COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Day 8 Cost Consumption Delivered</i> attribute [attribute ID <b>0x052C</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousDay8CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSDAY8COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Day 8 Cost Consumption Received</i> attribute [attribute ID <b>0x052D</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousDay8CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSDAY8COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Day 8 Cost Consumption Received</i> attribute [attribute ID <b>0x052D</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousDay8CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSDAY8COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSDAY8COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSDAY8COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Day 8 Cost Consumption Received</i> attribute [attribute ID <b>0x052D</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousDay8CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSDAY8COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Current Week Cost Consumption Delivered</i> attribute [attribute ID <b>0x0530</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentWeekCostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_CURRENTWEEKCOSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Current Week Cost Consumption Delivered</i> attribute [attribute ID <b>0x0530</b>].
     * <p>
     * ADDME
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
    public Integer getCurrentWeekCostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENTWEEKCOSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENTWEEKCOSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTWEEKCOSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Current Week Cost Consumption Delivered</i> attribute [attribute ID <b>0x0530</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCurrentWeekCostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTWEEKCOSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Current Week Cost Consumption Received</i> attribute [attribute ID <b>0x0531</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentWeekCostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_CURRENTWEEKCOSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Current Week Cost Consumption Received</i> attribute [attribute ID <b>0x0531</b>].
     * <p>
     * ADDME
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
    public Integer getCurrentWeekCostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENTWEEKCOSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENTWEEKCOSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTWEEKCOSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Current Week Cost Consumption Received</i> attribute [attribute ID <b>0x0531</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCurrentWeekCostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTWEEKCOSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Week Cost Consumption Delivered</i> attribute [attribute ID <b>0x0532</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousWeekCostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSWEEKCOSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Week Cost Consumption Delivered</i> attribute [attribute ID <b>0x0532</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousWeekCostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSWEEKCOSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSWEEKCOSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSWEEKCOSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Week Cost Consumption Delivered</i> attribute [attribute ID <b>0x0532</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousWeekCostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSWEEKCOSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Week Cost Consumption Received</i> attribute [attribute ID <b>0x0533</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousWeekCostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSWEEKCOSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Week Cost Consumption Received</i> attribute [attribute ID <b>0x0533</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousWeekCostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSWEEKCOSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSWEEKCOSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSWEEKCOSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Week Cost Consumption Received</i> attribute [attribute ID <b>0x0533</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousWeekCostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSWEEKCOSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Week 2 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0534</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousWeek2CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSWEEK2COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Week 2 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0534</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousWeek2CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSWEEK2COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSWEEK2COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSWEEK2COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Week 2 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0534</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousWeek2CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSWEEK2COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Week 2 Cost Consumption Received</i> attribute [attribute ID <b>0x0535</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousWeek2CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSWEEK2COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Week 2 Cost Consumption Received</i> attribute [attribute ID <b>0x0535</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousWeek2CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSWEEK2COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSWEEK2COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSWEEK2COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Week 2 Cost Consumption Received</i> attribute [attribute ID <b>0x0535</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousWeek2CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSWEEK2COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Week 3 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0536</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousWeek3CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSWEEK3COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Week 3 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0536</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousWeek3CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSWEEK3COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSWEEK3COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSWEEK3COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Week 3 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0536</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousWeek3CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSWEEK3COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Week 3 Cost Consumption Received</i> attribute [attribute ID <b>0x0537</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousWeek3CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSWEEK3COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Week 3 Cost Consumption Received</i> attribute [attribute ID <b>0x0537</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousWeek3CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSWEEK3COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSWEEK3COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSWEEK3COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Week 3 Cost Consumption Received</i> attribute [attribute ID <b>0x0537</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousWeek3CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSWEEK3COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Week 4 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0538</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousWeek4CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSWEEK4COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Week 4 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0538</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousWeek4CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSWEEK4COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSWEEK4COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSWEEK4COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Week 4 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0538</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousWeek4CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSWEEK4COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Week 4 Cost Consumption Received</i> attribute [attribute ID <b>0x0539</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousWeek4CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSWEEK4COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Week 4 Cost Consumption Received</i> attribute [attribute ID <b>0x0539</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousWeek4CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSWEEK4COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSWEEK4COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSWEEK4COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Week 4 Cost Consumption Received</i> attribute [attribute ID <b>0x0539</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousWeek4CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSWEEK4COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Week 5 Cost Consumption Delivered</i> attribute [attribute ID <b>0x053A</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousWeek5CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSWEEK5COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Week 5 Cost Consumption Delivered</i> attribute [attribute ID <b>0x053A</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousWeek5CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSWEEK5COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSWEEK5COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSWEEK5COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Week 5 Cost Consumption Delivered</i> attribute [attribute ID <b>0x053A</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousWeek5CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSWEEK5COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Week 5 Cost Consumption Received</i> attribute [attribute ID <b>0x053B</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousWeek5CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSWEEK5COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Week 5 Cost Consumption Received</i> attribute [attribute ID <b>0x053B</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousWeek5CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSWEEK5COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSWEEK5COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSWEEK5COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Week 5 Cost Consumption Received</i> attribute [attribute ID <b>0x053B</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousWeek5CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSWEEK5COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Current Month Cost Consumption Delivered</i> attribute [attribute ID <b>0x0540</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentMonthCostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_CURRENTMONTHCOSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Current Month Cost Consumption Delivered</i> attribute [attribute ID <b>0x0540</b>].
     * <p>
     * ADDME
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
    public Integer getCurrentMonthCostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENTMONTHCOSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENTMONTHCOSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTMONTHCOSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Current Month Cost Consumption Delivered</i> attribute [attribute ID <b>0x0540</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCurrentMonthCostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTMONTHCOSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Current Month Cost Consumption Received</i> attribute [attribute ID <b>0x0541</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentMonthCostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_CURRENTMONTHCOSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Current Month Cost Consumption Received</i> attribute [attribute ID <b>0x0541</b>].
     * <p>
     * ADDME
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
    public Integer getCurrentMonthCostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENTMONTHCOSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENTMONTHCOSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTMONTHCOSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Current Month Cost Consumption Received</i> attribute [attribute ID <b>0x0541</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCurrentMonthCostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTMONTHCOSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month Cost Consumption Delivered</i> attribute [attribute ID <b>0x0542</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonthCostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTHCOSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Month Cost Consumption Delivered</i> attribute [attribute ID <b>0x0542</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonthCostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTHCOSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTHCOSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTHCOSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Month Cost Consumption Delivered</i> attribute [attribute ID <b>0x0542</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonthCostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTHCOSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month Cost Consumption Received</i> attribute [attribute ID <b>0x0543</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonthCostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTHCOSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Month Cost Consumption Received</i> attribute [attribute ID <b>0x0543</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonthCostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTHCOSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTHCOSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTHCOSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Month Cost Consumption Received</i> attribute [attribute ID <b>0x0543</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonthCostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTHCOSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 2 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0544</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth2CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH2COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Month 2 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0544</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth2CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH2COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH2COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH2COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Month 2 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0544</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth2CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH2COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 2 Cost Consumption Received</i> attribute [attribute ID <b>0x0545</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth2CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH2COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Month 2 Cost Consumption Received</i> attribute [attribute ID <b>0x0545</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth2CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH2COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH2COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH2COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Month 2 Cost Consumption Received</i> attribute [attribute ID <b>0x0545</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth2CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH2COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 3 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0546</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth3CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH3COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Month 3 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0546</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth3CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH3COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH3COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH3COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Month 3 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0546</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth3CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH3COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 3 Cost Consumption Received</i> attribute [attribute ID <b>0x0547</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth3CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH3COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Month 3 Cost Consumption Received</i> attribute [attribute ID <b>0x0547</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth3CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH3COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH3COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH3COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Month 3 Cost Consumption Received</i> attribute [attribute ID <b>0x0547</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth3CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH3COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 4 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0548</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth4CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH4COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Month 4 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0548</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth4CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH4COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH4COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH4COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Month 4 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0548</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth4CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH4COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 4 Cost Consumption Received</i> attribute [attribute ID <b>0x0549</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth4CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH4COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Month 4 Cost Consumption Received</i> attribute [attribute ID <b>0x0549</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth4CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH4COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH4COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH4COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Month 4 Cost Consumption Received</i> attribute [attribute ID <b>0x0549</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth4CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH4COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 5 Cost Consumption Delivered</i> attribute [attribute ID <b>0x054A</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth5CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH5COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Month 5 Cost Consumption Delivered</i> attribute [attribute ID <b>0x054A</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth5CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH5COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH5COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH5COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Month 5 Cost Consumption Delivered</i> attribute [attribute ID <b>0x054A</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth5CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH5COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 5 Cost Consumption Received</i> attribute [attribute ID <b>0x054B</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth5CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH5COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Month 5 Cost Consumption Received</i> attribute [attribute ID <b>0x054B</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth5CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH5COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH5COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH5COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Month 5 Cost Consumption Received</i> attribute [attribute ID <b>0x054B</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth5CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH5COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 6 Cost Consumption Delivered</i> attribute [attribute ID <b>0x054C</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth6CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH6COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Month 6 Cost Consumption Delivered</i> attribute [attribute ID <b>0x054C</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth6CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH6COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH6COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH6COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Month 6 Cost Consumption Delivered</i> attribute [attribute ID <b>0x054C</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth6CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH6COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 6 Cost Consumption Received</i> attribute [attribute ID <b>0x054D</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth6CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH6COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Month 6 Cost Consumption Received</i> attribute [attribute ID <b>0x054D</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth6CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH6COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH6COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH6COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Month 6 Cost Consumption Received</i> attribute [attribute ID <b>0x054D</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth6CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH6COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 7 Cost Consumption Delivered</i> attribute [attribute ID <b>0x054E</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth7CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH7COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Month 7 Cost Consumption Delivered</i> attribute [attribute ID <b>0x054E</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth7CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH7COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH7COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH7COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Month 7 Cost Consumption Delivered</i> attribute [attribute ID <b>0x054E</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth7CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH7COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 7 Cost Consumption Received</i> attribute [attribute ID <b>0x054F</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth7CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH7COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Month 7 Cost Consumption Received</i> attribute [attribute ID <b>0x054F</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth7CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH7COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH7COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH7COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Month 7 Cost Consumption Received</i> attribute [attribute ID <b>0x054F</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth7CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH7COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 8 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0550</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth8CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH8COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Month 8 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0550</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth8CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH8COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH8COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH8COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Month 8 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0550</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth8CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH8COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 8 Cost Consumption Received</i> attribute [attribute ID <b>0x0551</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth8CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH8COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Month 8 Cost Consumption Received</i> attribute [attribute ID <b>0x0551</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth8CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH8COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH8COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH8COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Month 8 Cost Consumption Received</i> attribute [attribute ID <b>0x0551</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth8CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH8COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 9 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0552</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth9CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH9COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Month 9 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0552</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth9CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH9COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH9COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH9COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Month 9 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0552</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth9CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH9COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 9 Cost Consumption Received</i> attribute [attribute ID <b>0x0553</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth9CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH9COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Month 9 Cost Consumption Received</i> attribute [attribute ID <b>0x0553</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth9CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH9COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH9COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH9COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Month 9 Cost Consumption Received</i> attribute [attribute ID <b>0x0553</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth9CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH9COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 10 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0554</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth10CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH10COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Month 10 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0554</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth10CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH10COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH10COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH10COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Month 10 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0554</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth10CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH10COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 10 Cost Consumption Received</i> attribute [attribute ID <b>0x0555</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth10CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH10COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Month 10 Cost Consumption Received</i> attribute [attribute ID <b>0x0555</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth10CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH10COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH10COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH10COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Month 10 Cost Consumption Received</i> attribute [attribute ID <b>0x0555</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth10CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH10COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 11 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0556</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth11CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH11COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Month 11 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0556</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth11CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH11COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH11COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH11COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Month 11 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0556</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth11CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH11COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 11 Cost Consumption Received</i> attribute [attribute ID <b>0x0557</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth11CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH11COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Month 11 Cost Consumption Received</i> attribute [attribute ID <b>0x0557</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth11CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH11COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH11COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH11COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Month 11 Cost Consumption Received</i> attribute [attribute ID <b>0x0557</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth11CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH11COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 12 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0558</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth12CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH12COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Month 12 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0558</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth12CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH12COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH12COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH12COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Month 12 Cost Consumption Delivered</i> attribute [attribute ID <b>0x0558</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth12CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH12COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 12 Cost Consumption Received</i> attribute [attribute ID <b>0x0559</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth12CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH12COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Month 12 Cost Consumption Received</i> attribute [attribute ID <b>0x0559</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth12CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH12COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH12COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH12COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Month 12 Cost Consumption Received</i> attribute [attribute ID <b>0x0559</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth12CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH12COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 13 Cost Consumption Delivered</i> attribute [attribute ID <b>0x055A</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth13CostConsumptionDeliveredAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH13COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Synchronously get the <i>Previous Month 13 Cost Consumption Delivered</i> attribute [attribute ID <b>0x055A</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth13CostConsumptionDelivered(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH13COSTCONSUMPTIONDELIVERED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH13COSTCONSUMPTIONDELIVERED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH13COSTCONSUMPTIONDELIVERED));
    }

    /**
     * Set reporting for the <i>Previous Month 13 Cost Consumption Delivered</i> attribute [attribute ID <b>0x055A</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth13CostConsumptionDeliveredReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH13COSTCONSUMPTIONDELIVERED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Previous Month 13 Cost Consumption Received</i> attribute [attribute ID <b>0x055B</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPreviousMonth13CostConsumptionReceivedAsync() {
        return read(attributes.get(ATTR_PREVIOUSMONTH13COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Synchronously get the <i>Previous Month 13 Cost Consumption Received</i> attribute [attribute ID <b>0x055B</b>].
     * <p>
     * ADDME
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
    public Integer getPreviousMonth13CostConsumptionReceived(final long refreshPeriod) {
        if (attributes.get(ATTR_PREVIOUSMONTH13COSTCONSUMPTIONRECEIVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PREVIOUSMONTH13COSTCONSUMPTIONRECEIVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PREVIOUSMONTH13COSTCONSUMPTIONRECEIVED));
    }

    /**
     * Set reporting for the <i>Previous Month 13 Cost Consumption Received</i> attribute [attribute ID <b>0x055B</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPreviousMonth13CostConsumptionReceivedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PREVIOUSMONTH13COSTCONSUMPTIONRECEIVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Historical Freeze Time</i> attribute [attribute ID <b>0x055C</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getHistoricalFreezeTimeAsync() {
        return read(attributes.get(ATTR_HISTORICALFREEZETIME));
    }

    /**
     * Synchronously get the <i>Historical Freeze Time</i> attribute [attribute ID <b>0x055C</b>].
     * <p>
     * ADDME
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
    public Integer getHistoricalFreezeTime(final long refreshPeriod) {
        if (attributes.get(ATTR_HISTORICALFREEZETIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_HISTORICALFREEZETIME).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_HISTORICALFREEZETIME));
    }

    /**
     * Set reporting for the <i>Historical Freeze Time</i> attribute [attribute ID <b>0x055C</b>].
     * <p>
     * ADDME
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setHistoricalFreezeTimeReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_HISTORICALFREEZETIME), minInterval, maxInterval, reportableChange);
    }

    /**
     * The Select Available Emergency Credit
     * <p>
     * FIXME: This command is sent to the Metering Device to activate the use of any Emergency
     * Credit available on the Metering Device.
     *
     * @param commandIssueDateTime {@link Calendar} Command Issue Date Time
     * @param originatingDevice {@link Integer} Originating Device
     * @param siteId {@link ByteArray} Site ID
     * @param meterSerialNumber {@link ByteArray} Meter Serial Number
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> selectAvailableEmergencyCredit(Calendar commandIssueDateTime, Integer originatingDevice, ByteArray siteId, ByteArray meterSerialNumber) {
        SelectAvailableEmergencyCredit command = new SelectAvailableEmergencyCredit();

        // Set the fields
        command.setCommandIssueDateTime(commandIssueDateTime);
        command.setOriginatingDevice(originatingDevice);
        command.setSiteId(siteId);
        command.setMeterSerialNumber(meterSerialNumber);

        return send(command);
    }

    /**
     * The Change Debt
     * <p>
     * FIXME: The ChangeDebt command is send to the Metering Device to change the fuel or Non
     * fuel debt values.
     *
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param debtLabel {@link ByteArray} Debt Label
     * @param debtAmount {@link Integer} Debt Amount
     * @param debtRecoveryMethod {@link Integer} Debt Recovery Method
     * @param debtAmountType {@link Integer} Debt Amount Type
     * @param debtRecoveryStartTime {@link Calendar} Debt Recovery Start Time
     * @param debtRecoveryCollectionTime {@link Integer} Debt Recovery Collection Time
     * @param debtRecoveryFrequency {@link Integer} Debt Recovery Frequency
     * @param debtRecoveryAmount {@link Integer} Debt Recovery Amount
     * @param debtRecoveryBalancePercentage {@link Integer} Debt Recovery Balance Percentage
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> changeDebt(Integer issuerEventId, ByteArray debtLabel, Integer debtAmount, Integer debtRecoveryMethod, Integer debtAmountType, Calendar debtRecoveryStartTime, Integer debtRecoveryCollectionTime, Integer debtRecoveryFrequency, Integer debtRecoveryAmount, Integer debtRecoveryBalancePercentage) {
        ChangeDebt command = new ChangeDebt();

        // Set the fields
        command.setIssuerEventId(issuerEventId);
        command.setDebtLabel(debtLabel);
        command.setDebtAmount(debtAmount);
        command.setDebtRecoveryMethod(debtRecoveryMethod);
        command.setDebtAmountType(debtAmountType);
        command.setDebtRecoveryStartTime(debtRecoveryStartTime);
        command.setDebtRecoveryCollectionTime(debtRecoveryCollectionTime);
        command.setDebtRecoveryFrequency(debtRecoveryFrequency);
        command.setDebtRecoveryAmount(debtRecoveryAmount);
        command.setDebtRecoveryBalancePercentage(debtRecoveryBalancePercentage);

        return send(command);
    }

    /**
     * The Emergency Credit Setup
     * <p>
     * FIXME: This command is a method to set up the parameters for the emergency credit.
     *
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param startTime {@link Calendar} Start Time
     * @param emergencyCreditLimit {@link Integer} Emergency Credit Limit
     * @param emergencyCreditThreshold {@link Integer} Emergency Credit Threshold
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> emergencyCreditSetup(Integer issuerEventId, Calendar startTime, Integer emergencyCreditLimit, Integer emergencyCreditThreshold) {
        EmergencyCreditSetup command = new EmergencyCreditSetup();

        // Set the fields
        command.setIssuerEventId(issuerEventId);
        command.setStartTime(startTime);
        command.setEmergencyCreditLimit(emergencyCreditLimit);
        command.setEmergencyCreditThreshold(emergencyCreditThreshold);

        return send(command);
    }

    /**
     * The Consumer Top Up
     * <p>
     * FIXME: The ConsumerTopUp command is used by the IPD and the ESI as a method of applying
     * credit top up values to the prepayment meter.
     *
     * @param originatingDevice {@link Integer} Originating Device
     * @param topUpCode {@link ByteArray} Top Up Code
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> consumerTopUp(Integer originatingDevice, ByteArray topUpCode) {
        ConsumerTopUp command = new ConsumerTopUp();

        // Set the fields
        command.setOriginatingDevice(originatingDevice);
        command.setTopUpCode(topUpCode);

        return send(command);
    }

    /**
     * The Credit Adjustment
     * <p>
     * FIXME: The CreditAdjustment command is sent to update the accounting base for the
     * Prepayment meter.
     *
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param startTime {@link Calendar} Start Time
     * @param creditAdjustmentType {@link Integer} Credit Adjustment Type
     * @param creditAdjustmentValue {@link Integer} Credit Adjustment Value
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> creditAdjustment(Integer issuerEventId, Calendar startTime, Integer creditAdjustmentType, Integer creditAdjustmentValue) {
        CreditAdjustment command = new CreditAdjustment();

        // Set the fields
        command.setIssuerEventId(issuerEventId);
        command.setStartTime(startTime);
        command.setCreditAdjustmentType(creditAdjustmentType);
        command.setCreditAdjustmentValue(creditAdjustmentValue);

        return send(command);
    }

    /**
     * The Change Payment Mode
     * <p>
     * FIXME: This command is sent to a Metering Device to instruct it to change its mode of
     * operation. i.e. from Credit to Prepayment.
     *
     * @param providerId {@link Integer} Provider ID
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param implementationDateTime {@link Calendar} Implementation Date Time
     * @param proposedPaymentControlConfiguration {@link Integer} Proposed Payment Control Configuration
     * @param cutOffValue {@link Integer} Cut Off Value
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> changePaymentMode(Integer providerId, Integer issuerEventId, Calendar implementationDateTime, Integer proposedPaymentControlConfiguration, Integer cutOffValue) {
        ChangePaymentMode command = new ChangePaymentMode();

        // Set the fields
        command.setProviderId(providerId);
        command.setIssuerEventId(issuerEventId);
        command.setImplementationDateTime(implementationDateTime);
        command.setProposedPaymentControlConfiguration(proposedPaymentControlConfiguration);
        command.setCutOffValue(cutOffValue);

        return send(command);
    }

    /**
     * The Get Prepay Snapshot
     * <p>
     * FIXME: This command is used to request the cluster server for snapshot data.
     *
     * @param earliestStartTime {@link Calendar} Earliest Start Time
     * @param latestEndTime {@link Calendar} Latest End Time
     * @param snapshotOffset {@link Integer} Snapshot Offset
     * @param snapshotCause {@link Integer} Snapshot Cause
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPrepaySnapshot(Calendar earliestStartTime, Calendar latestEndTime, Integer snapshotOffset, Integer snapshotCause) {
        GetPrepaySnapshot command = new GetPrepaySnapshot();

        // Set the fields
        command.setEarliestStartTime(earliestStartTime);
        command.setLatestEndTime(latestEndTime);
        command.setSnapshotOffset(snapshotOffset);
        command.setSnapshotCause(snapshotCause);

        return send(command);
    }

    /**
     * The Get Top Up Log
     * <p>
     * FIXME: This command is sent to the Metering Device to retrieve the log of Top Up codes
     * received by the meter.
     *
     * @param latestEndTime {@link Calendar} Latest End Time
     * @param numberOfRecords {@link Integer} Number Of Records
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTopUpLog(Calendar latestEndTime, Integer numberOfRecords) {
        GetTopUpLog command = new GetTopUpLog();

        // Set the fields
        command.setLatestEndTime(latestEndTime);
        command.setNumberOfRecords(numberOfRecords);

        return send(command);
    }

    /**
     * The Set Low Credit Warning Level
     * <p>
     * FIXME: This command is sent from client to a Prepayment server to set the warning level
     * for low credit.
     *
     * @param lowCreditWarningLevel {@link Integer} Low Credit Warning Level
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setLowCreditWarningLevel(Integer lowCreditWarningLevel) {
        SetLowCreditWarningLevel command = new SetLowCreditWarningLevel();

        // Set the fields
        command.setLowCreditWarningLevel(lowCreditWarningLevel);

        return send(command);
    }

    /**
     * The Get Debt Repayment Log
     * <p>
     * FIXME: This command is used to request the contents of the repayment log.
     *
     * @param latestEndTime {@link Calendar} Latest End Time
     * @param numberOfDebts {@link Integer} Number Of Debts
     * @param debtType {@link Integer} Debt Type
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDebtRepaymentLog(Calendar latestEndTime, Integer numberOfDebts, Integer debtType) {
        GetDebtRepaymentLog command = new GetDebtRepaymentLog();

        // Set the fields
        command.setLatestEndTime(latestEndTime);
        command.setNumberOfDebts(numberOfDebts);
        command.setDebtType(debtType);

        return send(command);
    }

    /**
     * The Set Maximum Credit Limit
     * <p>
     * FIXME: This command is sent from a client to the Prepayment server to set the maximum
     * credit level allowed in the meter.
     *
     * @param providerId {@link Integer} Provider ID
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param implementationDateTime {@link Calendar} Implementation Date Time
     * @param maximumCreditLevel {@link Integer} Maximum Credit Level
     * @param maximumCreditPerTopUp {@link Integer} Maximum Credit Per Top Up
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMaximumCreditLimit(Integer providerId, Integer issuerEventId, Calendar implementationDateTime, Integer maximumCreditLevel, Integer maximumCreditPerTopUp) {
        SetMaximumCreditLimit command = new SetMaximumCreditLimit();

        // Set the fields
        command.setProviderId(providerId);
        command.setIssuerEventId(issuerEventId);
        command.setImplementationDateTime(implementationDateTime);
        command.setMaximumCreditLevel(maximumCreditLevel);
        command.setMaximumCreditPerTopUp(maximumCreditPerTopUp);

        return send(command);
    }

    /**
     * The Set Overall Debt Cap
     * <p>
     * FIXME: This command is sent from a client to the Prepayment server to set the overall debt
     * cap allowed in the meter.
     *
     * @param providerId {@link Integer} Provider ID
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param implementationDateTime {@link Calendar} Implementation Date Time
     * @param overallDebtCap {@link Integer} Overall Debt Cap
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setOverallDebtCap(Integer providerId, Integer issuerEventId, Calendar implementationDateTime, Integer overallDebtCap) {
        SetOverallDebtCap command = new SetOverallDebtCap();

        // Set the fields
        command.setProviderId(providerId);
        command.setIssuerEventId(issuerEventId);
        command.setImplementationDateTime(implementationDateTime);
        command.setOverallDebtCap(overallDebtCap);

        return send(command);
    }

    /**
     * The Publish Prepay Snapshot
     * <p>
     * FIXME: This command is generated in response to a GetPrepaySnapshot command. It is used
     * to return a single snapshot to the client.
     *
     * @param snapshotId {@link Integer} Snapshot ID
     * @param snapshotTime {@link Calendar} Snapshot Time
     * @param totalSnapshotsFound {@link Integer} Total Snapshots Found
     * @param commandIndex {@link Integer} Command Index
     * @param totalNumberOfCommands {@link Integer} Total Number Of Commands
     * @param snapshotCause {@link Integer} Snapshot Cause
     * @param snapshotPayloadType {@link Integer} Snapshot Payload Type
     * @param snapshotPayload {@link Integer} Snapshot Payload
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> publishPrepaySnapshot(Integer snapshotId, Calendar snapshotTime, Integer totalSnapshotsFound, Integer commandIndex, Integer totalNumberOfCommands, Integer snapshotCause, Integer snapshotPayloadType, Integer snapshotPayload) {
        PublishPrepaySnapshot command = new PublishPrepaySnapshot();

        // Set the fields
        command.setSnapshotId(snapshotId);
        command.setSnapshotTime(snapshotTime);
        command.setTotalSnapshotsFound(totalSnapshotsFound);
        command.setCommandIndex(commandIndex);
        command.setTotalNumberOfCommands(totalNumberOfCommands);
        command.setSnapshotCause(snapshotCause);
        command.setSnapshotPayloadType(snapshotPayloadType);
        command.setSnapshotPayload(snapshotPayload);

        return send(command);
    }

    /**
     * The Change Payment Mode Response
     * <p>
     * FIXME: This command is send in response to the ChangePaymentMode Command.
     *
     * @param friendlyCredit {@link Integer} Friendly Credit
     * @param friendlyCreditCalendarId {@link Integer} Friendly Credit Calendar ID
     * @param emergencyCreditLimit {@link Integer} Emergency Credit Limit
     * @param emergencyCreditThreshold {@link Integer} Emergency Credit Threshold
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> changePaymentModeResponse(Integer friendlyCredit, Integer friendlyCreditCalendarId, Integer emergencyCreditLimit, Integer emergencyCreditThreshold) {
        ChangePaymentModeResponse command = new ChangePaymentModeResponse();

        // Set the fields
        command.setFriendlyCredit(friendlyCredit);
        command.setFriendlyCreditCalendarId(friendlyCreditCalendarId);
        command.setEmergencyCreditLimit(emergencyCreditLimit);
        command.setEmergencyCreditThreshold(emergencyCreditThreshold);

        return send(command);
    }

    /**
     * The Consumer Top Up Response
     * <p>
     * FIXME: This command is send in response to the ConsumerTopUp Command.
     *
     * @param resultType {@link Integer} Result Type
     * @param topUpValue {@link Integer} Top Up Value
     * @param sourceOfTopUp {@link Integer} Source Of Top Up
     * @param creditRemaining {@link Integer} Credit Remaining
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> consumerTopUpResponse(Integer resultType, Integer topUpValue, Integer sourceOfTopUp, Integer creditRemaining) {
        ConsumerTopUpResponse command = new ConsumerTopUpResponse();

        // Set the fields
        command.setResultType(resultType);
        command.setTopUpValue(topUpValue);
        command.setSourceOfTopUp(sourceOfTopUp);
        command.setCreditRemaining(creditRemaining);

        return send(command);
    }

    /**
     * The Publish Top Up Log
     * <p>
     * FIXME: This command is used to send the Top Up Code Log entries to the client.
     *
     * @param commandIndex {@link Integer} Command Index
     * @param totalNumberOfCommands {@link Integer} Total Number Of Commands
     * @param topUpPayload {@link TopUpPayload} Top Up Payload
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> publishTopUpLog(Integer commandIndex, Integer totalNumberOfCommands, TopUpPayload topUpPayload) {
        PublishTopUpLog command = new PublishTopUpLog();

        // Set the fields
        command.setCommandIndex(commandIndex);
        command.setTotalNumberOfCommands(totalNumberOfCommands);
        command.setTopUpPayload(topUpPayload);

        return send(command);
    }

    /**
     * The Publish Debt Log
     * <p>
     * FIXME: This command is used to send the contents of the Repayment Log.
     *
     * @param commandIndex {@link Integer} Command Index
     * @param totalNumberOfCommands {@link Integer} Total Number Of Commands
     * @param debtPayload {@link DebtPayload} Debt Payload
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> publishDebtLog(Integer commandIndex, Integer totalNumberOfCommands, DebtPayload debtPayload) {
        PublishDebtLog command = new PublishDebtLog();

        // Set the fields
        command.setCommandIndex(commandIndex);
        command.setTotalNumberOfCommands(totalNumberOfCommands);
        command.setDebtPayload(debtPayload);

        return send(command);
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0x00: // SELECT_AVAILABLE_EMERGENCY_CREDIT
                return new SelectAvailableEmergencyCredit();
            case 0x02: // CHANGE_DEBT
                return new ChangeDebt();
            case 0x03: // EMERGENCY_CREDIT_SETUP
                return new EmergencyCreditSetup();
            case 0x04: // CONSUMER_TOP_UP
                return new ConsumerTopUp();
            case 0x05: // CREDIT_ADJUSTMENT
                return new CreditAdjustment();
            case 0x06: // CHANGE_PAYMENT_MODE
                return new ChangePaymentMode();
            case 0x07: // GET_PREPAY_SNAPSHOT
                return new GetPrepaySnapshot();
            case 0x08: // GET_TOP_UP_LOG
                return new GetTopUpLog();
            case 0x09: // SET_LOW_CREDIT_WARNING_LEVEL
                return new SetLowCreditWarningLevel();
            case 0x0A: // GET_DEBT_REPAYMENT_LOG
                return new GetDebtRepaymentLog();
            case 0x0B: // SET_MAXIMUM_CREDIT_LIMIT
                return new SetMaximumCreditLimit();
            case 0x0C: // SET_OVERALL_DEBT_CAP
                return new SetOverallDebtCap();
            default:
                return null;
        }
    }

    @Override
    public ZclCommand getResponseFromId(int commandId) {
        switch (commandId) {
            case 0x01: // PUBLISH_PREPAY_SNAPSHOT
                return new PublishPrepaySnapshot();
            case 0x02: // CHANGE_PAYMENT_MODE_RESPONSE
                return new ChangePaymentModeResponse();
            case 0x03: // CONSUMER_TOP_UP_RESPONSE
                return new ConsumerTopUpResponse();
            case 0x05: // PUBLISH_TOP_UP_LOG
                return new PublishTopUpLog();
            case 0x06: // PUBLISH_DEBT_LOG
                return new PublishDebtLog();
            default:
                return null;
        }
    }
}
