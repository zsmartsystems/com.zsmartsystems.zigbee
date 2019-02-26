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
import com.zsmartsystems.zigbee.zcl.clusters.price.BlockThresholdSubPayload;
import com.zsmartsystems.zigbee.zcl.clusters.price.CancelTariffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.CppEventResponse;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetBillingPeriodCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetBlockPeriodCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetBlockThresholdsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetCalorificValueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetCo2ValueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetConsolidatedBillCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetConversionFactorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetCreditPaymentCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetCurrencyConversionCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetCurrentPriceCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetPriceMatrixCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetScheduledPricesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetTariffCancellationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetTariffInformationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetTierLabelsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PriceAcknowledgementCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PriceMatrixSubPayload;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishBillingPeriodCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishBlockPeriodCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishBlockThresholdsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishCalorificValueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishCo2ValueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishConsolidatedBillCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishConversionFactorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishCppEventCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishCreditPaymentCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishCurrencyConversionCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishPriceCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishPriceMatrixCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishTariffInformationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishTierLabelsCommand;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Price</b> cluster implementation (<i>Cluster ID 0x0700</i>).
 * <p>
 * The Price Cluster provides the mechanism for communicating Gas, Energy, or Water pricing
 * information within the premises. This pricing information is distributed to the ESI from
 * either the utilities or from regional energy providers. The ESI conveys the information
 * (via the Price Cluster mechanisms) to other Smart Energy devices.
 * <p>
 * Events carried using this cluster include a timestamp with the assumption that target
 * devices maintain a real time clock. Devices can acquire and synchronize their internal
 * clocks via the ZCL Time server. If a device does not support a real time clock it is assumed that
 * the device will interpret and utilize the “Start Now” value within the Time field.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-26T20:57:36Z")
public class ZclPriceCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0700;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Price";

    // Attribute constants
    /**
     * The PriceIncreaseRandomizeMinutes attribute represents the maximum amount of time
     * to be used when randomizing the response to a price increase. Note that although the
     * granularity of the attribute is in minutes, it is recommended the granularity of the
     * randomization used within a responding device be in seconds or smaller. If a device
     * responds to a price increase it must choose a random amount of time, in seconds or
     * smaller, between 0 and PriceIncreaseRandomizeMinutes minutes. The device must
     * implement that random amount of time before or after the price change. How and if a device
     * will respond to a price increase is up to the manufacturer. Whether to respond before or
     * after the price increase is also up to the manufacturer.
     * <p>
     * As an example, a water heater with a PriceIncreaseRandomizeMinutes set to 6 could
     * choose to lower its set point 315 seconds (but not more than 360 seconds) before the price
     * increases.
     * <p>
     * The valid range for this attribute is 0x00 to 0x3C.
     * <p>
     * If PriceIncreaseRandomizeMinutes or PriceDecreaseRandomizeMinutes attributes
     * are not supported by the CLIENT, then it should use the default values for the attributes
     * as specified in the Price CLIENT Cluster Attribute table.
     */
    public static final int ATTR_PRICEINCREASERANDOMIZEMINUTES = 0x0000;
    /**
     * The PriceDecreaseRandomizeMinutes attribute represents the maximum number of
     * minutes to be used when randomizing the response to a price decrease. Note that although
     * the granularity of the attribute is in minutes, it is recommended the granularity of the
     * randomization used within a responding device be in seconds or smaller. If a device
     * responds to a price decrease it must choose a random amount of time, in seconds or
     * smaller, between 0 and PriceDecreaseRandomizeMinutes minutes and implement that
     * random amount of time before or after the price change. How and if a device will respond to
     * a price decrease is up to the manufacturer. Whether to respond before or after the price
     * increase is also up to the manufacturer.
     * <p>
     * As an example, a dishwasher with a PriceDecreaseRandomizeMinutes set to 15 could
     * choose to start its wash cycle 723 seconds (but not more than 900 seconds) after the price
     * decreases. The valid range for this attribute is 0x00 to 0x3C.
     */
    public static final int ATTR_PRICEDECREASERANDOMIZEMINUTES = 0x0001;
    /**
     * CommodityType provides a label for identifying the type of pricing client present. The
     * attribute is an enumerated value representing the commodity. The defined values are
     * represented by the non-mirrored values (0-127) in the MeteringDeviceType attribute
     * enumerations.
     */
    public static final int ATTR_COMMODITYTYPECLIENT = 0x0002;
    public static final int ATTR_TIER1PRICELABEL = 0x0001;
    public static final int ATTR_TIER2PRICELABEL = 0x0002;
    public static final int ATTR_TIER3PRICELABEL = 0x0003;
    public static final int ATTR_TIER4PRICELABEL = 0x0004;
    public static final int ATTR_TIER5PRICELABEL = 0x0005;
    public static final int ATTR_TIER6PRICELABEL = 0x0006;
    public static final int ATTR_TIER7PRICELABEL = 0x0007;
    public static final int ATTR_TIER8PRICELABEL = 0x0008;
    public static final int ATTR_TIER9PRICELABEL = 0x0009;
    public static final int ATTR_TIER10PRICELABEL = 0x000A;
    public static final int ATTR_TIER11PRICELABEL = 0x000B;
    public static final int ATTR_TIER12PRICELABEL = 0x000C;
    public static final int ATTR_TIER13PRICELABEL = 0x000D;
    public static final int ATTR_TIER14PRICELABEL = 0x000E;
    public static final int ATTR_TIER15PRICELABEL = 0x000F;
    public static final int ATTR_TIER16PRICELABEL = 0x0010;
    public static final int ATTR_TIER17PRICELABEL = 0x0011;
    public static final int ATTR_TIER18PRICELABEL = 0x0012;
    public static final int ATTR_TIER19PRICELABEL = 0x0013;
    public static final int ATTR_TIER20PRICELABEL = 0x0014;
    public static final int ATTR_TIER21PRICELABEL = 0x0015;
    public static final int ATTR_TIER22PRICELABEL = 0x0016;
    public static final int ATTR_TIER23PRICELABEL = 0x0017;
    public static final int ATTR_TIER24PRICELABEL = 0x0018;
    public static final int ATTR_TIER25PRICELABEL = 0x0019;
    public static final int ATTR_TIER26PRICELABEL = 0x001A;
    public static final int ATTR_TIER27PRICELABEL = 0x001B;
    public static final int ATTR_TIER28PRICELABEL = 0x001C;
    public static final int ATTR_TIER29PRICELABEL = 0x001D;
    public static final int ATTR_TIER30PRICELABEL = 0x001E;
    public static final int ATTR_TIER31PRICELABEL = 0x001F;
    public static final int ATTR_TIER32PRICELABEL = 0x0020;
    public static final int ATTR_TIER33PRICELABEL = 0x0021;
    public static final int ATTR_TIER34PRICELABEL = 0x0022;
    public static final int ATTR_TIER35PRICELABEL = 0x0023;
    public static final int ATTR_TIER36PRICELABEL = 0x0024;
    public static final int ATTR_TIER37PRICELABEL = 0x0025;
    public static final int ATTR_TIER38PRICELABEL = 0x0026;
    public static final int ATTR_TIER39PRICELABEL = 0x0027;
    public static final int ATTR_TIER40PRICELABEL = 0x0028;
    public static final int ATTR_TIER41PRICELABEL = 0x0029;
    public static final int ATTR_TIER42PRICELABEL = 0x002A;
    public static final int ATTR_TIER43PRICELABEL = 0x002B;
    public static final int ATTR_TIER44PRICELABEL = 0x002C;
    public static final int ATTR_TIER45PRICELABEL = 0x002D;
    public static final int ATTR_TIER46PRICELABEL = 0x002E;
    public static final int ATTR_TIER47PRICELABEL = 0x002F;
    public static final int ATTR_TIER48PRICELABEL = 0x0030;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_BLOCK1THRESHOLD = 0x0101;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_BLOCK2THRESHOLD = 0x0102;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_BLOCK3THRESHOLD = 0x0103;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_BLOCK4THRESHOLD = 0x0104;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_BLOCK5THRESHOLD = 0x0105;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_BLOCK6THRESHOLD = 0x0106;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_BLOCK7THRESHOLD = 0x0107;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_BLOCK8THRESHOLD = 0x0108;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_BLOCK9THRESHOLD = 0x0109;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_BLOCK10THRESHOLD = 0x010A;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_BLOCK11THRESHOLD = 0x010B;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_BLOCK12THRESHOLD = 0x010C;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_BLOCK13THRESHOLD = 0x010D;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_BLOCK14THRESHOLD = 0x010E;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_BLOCK15THRESHOLD = 0x010F;
    /**
     * Where a single set of thresholds is used, the BlockThresholdCount attribute indicates
     * the number of applicable BlockNThresholds. Where more than one set of thresholds is
     * used, each set will be accompanied by an appropriate TierNBlockThresholdCount
     * attribute.
     */
    public static final int ATTR_BLOCKTHRESHOLDCOUNT = 0x010F;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER1BLOCK1THRESHOLD = 0x0111;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER1BLOCK2THRESHOLD = 0x0112;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER1BLOCK3THRESHOLD = 0x0113;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER1BLOCK4THRESHOLD = 0x0114;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER1BLOCK5THRESHOLD = 0x0115;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER1BLOCK6THRESHOLD = 0x0116;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER1BLOCK7THRESHOLD = 0x0117;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER1BLOCK8THRESHOLD = 0x0118;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER1BLOCK9THRESHOLD = 0x0119;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER1BLOCK10THRESHOLD = 0x011A;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER1BLOCK11THRESHOLD = 0x011B;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER1BLOCK12THRESHOLD = 0x011C;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER1BLOCK13THRESHOLD = 0x011D;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER1BLOCK14THRESHOLD = 0x011E;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER1BLOCK15THRESHOLD = 0x011F;
    /**
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     */
    public static final int ATTR_TIER1BLOCKTHRESHOLDCOUNT = 0x011F;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER2BLOCK1THRESHOLD = 0x0121;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER2BLOCK2THRESHOLD = 0x0122;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER2BLOCK3THRESHOLD = 0x0123;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER2BLOCK4THRESHOLD = 0x0124;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER2BLOCK5THRESHOLD = 0x0125;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER2BLOCK6THRESHOLD = 0x0126;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER2BLOCK7THRESHOLD = 0x0127;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER2BLOCK8THRESHOLD = 0x0128;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER2BLOCK9THRESHOLD = 0x0129;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER2BLOCK10THRESHOLD = 0x012A;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER2BLOCK11THRESHOLD = 0x012B;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER2BLOCK12THRESHOLD = 0x012C;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER2BLOCK13THRESHOLD = 0x012D;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER2BLOCK14THRESHOLD = 0x012E;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER2BLOCK15THRESHOLD = 0x012F;
    /**
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     */
    public static final int ATTR_TIER2BLOCKTHRESHOLDCOUNT = 0x012F;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER3BLOCK1THRESHOLD = 0x0131;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER3BLOCK2THRESHOLD = 0x0132;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER3BLOCK3THRESHOLD = 0x0133;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER3BLOCK4THRESHOLD = 0x0134;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER3BLOCK5THRESHOLD = 0x0135;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER3BLOCK6THRESHOLD = 0x0136;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER3BLOCK7THRESHOLD = 0x0137;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER3BLOCK8THRESHOLD = 0x0138;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER3BLOCK9THRESHOLD = 0x0139;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER3BLOCK10THRESHOLD = 0x013A;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER3BLOCK11THRESHOLD = 0x013B;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER3BLOCK12THRESHOLD = 0x013C;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER3BLOCK13THRESHOLD = 0x013D;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER3BLOCK14THRESHOLD = 0x013E;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER3BLOCK15THRESHOLD = 0x013F;
    /**
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     */
    public static final int ATTR_TIER3BLOCKTHRESHOLDCOUNT = 0x013F;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER4BLOCK1THRESHOLD = 0x0141;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER4BLOCK2THRESHOLD = 0x0142;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER4BLOCK3THRESHOLD = 0x0143;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER4BLOCK4THRESHOLD = 0x0144;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER4BLOCK5THRESHOLD = 0x0145;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER4BLOCK6THRESHOLD = 0x0146;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER4BLOCK7THRESHOLD = 0x0147;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER4BLOCK8THRESHOLD = 0x0148;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER4BLOCK9THRESHOLD = 0x0149;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER4BLOCK10THRESHOLD = 0x014A;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER4BLOCK11THRESHOLD = 0x014B;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER4BLOCK12THRESHOLD = 0x014C;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER4BLOCK13THRESHOLD = 0x014D;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER4BLOCK14THRESHOLD = 0x014E;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER4BLOCK15THRESHOLD = 0x014F;
    /**
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     */
    public static final int ATTR_TIER4BLOCKTHRESHOLDCOUNT = 0x014F;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER5BLOCK1THRESHOLD = 0x0151;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER5BLOCK2THRESHOLD = 0x0152;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER5BLOCK3THRESHOLD = 0x0153;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER5BLOCK4THRESHOLD = 0x0154;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER5BLOCK5THRESHOLD = 0x0155;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER5BLOCK6THRESHOLD = 0x0156;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER5BLOCK7THRESHOLD = 0x0157;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER5BLOCK8THRESHOLD = 0x0158;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER5BLOCK9THRESHOLD = 0x0159;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER5BLOCK10THRESHOLD = 0x015A;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER5BLOCK11THRESHOLD = 0x015B;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER5BLOCK12THRESHOLD = 0x015C;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER5BLOCK13THRESHOLD = 0x015D;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER5BLOCK14THRESHOLD = 0x015E;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER5BLOCK15THRESHOLD = 0x015F;
    /**
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     */
    public static final int ATTR_TIER5BLOCKTHRESHOLDCOUNT = 0x015F;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER6BLOCK1THRESHOLD = 0x0161;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER6BLOCK2THRESHOLD = 0x0162;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER6BLOCK3THRESHOLD = 0x0163;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER6BLOCK4THRESHOLD = 0x0164;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER6BLOCK5THRESHOLD = 0x0165;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER6BLOCK6THRESHOLD = 0x0166;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER6BLOCK7THRESHOLD = 0x0167;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER6BLOCK8THRESHOLD = 0x0168;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER6BLOCK9THRESHOLD = 0x0169;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER6BLOCK10THRESHOLD = 0x016A;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER6BLOCK11THRESHOLD = 0x016B;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER6BLOCK12THRESHOLD = 0x016C;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER6BLOCK13THRESHOLD = 0x016D;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER6BLOCK14THRESHOLD = 0x016E;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER6BLOCK15THRESHOLD = 0x016F;
    /**
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     */
    public static final int ATTR_TIER6BLOCKTHRESHOLDCOUNT = 0x016F;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER7BLOCK1THRESHOLD = 0x0171;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER7BLOCK2THRESHOLD = 0x0172;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER7BLOCK3THRESHOLD = 0x0173;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER7BLOCK4THRESHOLD = 0x0174;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER7BLOCK5THRESHOLD = 0x0175;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER7BLOCK6THRESHOLD = 0x0176;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER7BLOCK7THRESHOLD = 0x0177;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER7BLOCK8THRESHOLD = 0x0178;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER7BLOCK9THRESHOLD = 0x0179;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER7BLOCK10THRESHOLD = 0x017A;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER7BLOCK11THRESHOLD = 0x017B;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER7BLOCK12THRESHOLD = 0x017C;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER7BLOCK13THRESHOLD = 0x017D;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER7BLOCK14THRESHOLD = 0x017E;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER7BLOCK15THRESHOLD = 0x017F;
    /**
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     */
    public static final int ATTR_TIER7BLOCKTHRESHOLDCOUNT = 0x017F;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER8BLOCK1THRESHOLD = 0x0181;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER8BLOCK2THRESHOLD = 0x0182;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER8BLOCK3THRESHOLD = 0x0183;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER8BLOCK4THRESHOLD = 0x0184;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER8BLOCK5THRESHOLD = 0x0185;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER8BLOCK6THRESHOLD = 0x0186;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER8BLOCK7THRESHOLD = 0x0187;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER8BLOCK8THRESHOLD = 0x0188;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER8BLOCK9THRESHOLD = 0x0189;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER8BLOCK10THRESHOLD = 0x018A;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER8BLOCK11THRESHOLD = 0x018B;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER8BLOCK12THRESHOLD = 0x018C;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER8BLOCK13THRESHOLD = 0x018D;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER8BLOCK14THRESHOLD = 0x018E;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER8BLOCK15THRESHOLD = 0x018F;
    /**
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     */
    public static final int ATTR_TIER8BLOCKTHRESHOLDCOUNT = 0x018F;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER9BLOCK1THRESHOLD = 0x0191;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER9BLOCK2THRESHOLD = 0x0192;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER9BLOCK3THRESHOLD = 0x0193;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER9BLOCK4THRESHOLD = 0x0194;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER9BLOCK5THRESHOLD = 0x0195;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER9BLOCK6THRESHOLD = 0x0196;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER9BLOCK7THRESHOLD = 0x0197;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER9BLOCK8THRESHOLD = 0x0198;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER9BLOCK9THRESHOLD = 0x0199;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER9BLOCK10THRESHOLD = 0x019A;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER9BLOCK11THRESHOLD = 0x019B;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER9BLOCK12THRESHOLD = 0x019C;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER9BLOCK13THRESHOLD = 0x019D;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER9BLOCK14THRESHOLD = 0x019E;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER9BLOCK15THRESHOLD = 0x019F;
    /**
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     */
    public static final int ATTR_TIER9BLOCKTHRESHOLDCOUNT = 0x019F;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER10BLOCK1THRESHOLD = 0x01A1;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER10BLOCK2THRESHOLD = 0x01A2;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER10BLOCK3THRESHOLD = 0x01A3;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER10BLOCK4THRESHOLD = 0x01A4;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER10BLOCK5THRESHOLD = 0x01A5;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER10BLOCK6THRESHOLD = 0x01A6;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER10BLOCK7THRESHOLD = 0x01A7;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER10BLOCK8THRESHOLD = 0x01A8;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER10BLOCK9THRESHOLD = 0x01A9;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER10BLOCK10THRESHOLD = 0x01AA;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER10BLOCK11THRESHOLD = 0x01AB;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER10BLOCK12THRESHOLD = 0x01AC;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER10BLOCK13THRESHOLD = 0x01AD;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER10BLOCK14THRESHOLD = 0x01AE;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER10BLOCK15THRESHOLD = 0x01AF;
    /**
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     */
    public static final int ATTR_TIER10BLOCKTHRESHOLDCOUNT = 0x01AF;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER11BLOCK1THRESHOLD = 0x01B1;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER11BLOCK2THRESHOLD = 0x01B2;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER11BLOCK3THRESHOLD = 0x01B3;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER11BLOCK4THRESHOLD = 0x01B4;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER11BLOCK5THRESHOLD = 0x01B5;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER11BLOCK6THRESHOLD = 0x01B6;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER11BLOCK7THRESHOLD = 0x01B7;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER11BLOCK8THRESHOLD = 0x01B8;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER11BLOCK9THRESHOLD = 0x01B9;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER11BLOCK10THRESHOLD = 0x01BA;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER11BLOCK11THRESHOLD = 0x01BB;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER11BLOCK12THRESHOLD = 0x01BC;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER11BLOCK13THRESHOLD = 0x01BD;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER11BLOCK14THRESHOLD = 0x01BE;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER11BLOCK15THRESHOLD = 0x01BF;
    /**
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     */
    public static final int ATTR_TIER11BLOCKTHRESHOLDCOUNT = 0x01BF;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER12BLOCK1THRESHOLD = 0x01C1;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER12BLOCK2THRESHOLD = 0x01C2;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER12BLOCK3THRESHOLD = 0x01C3;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER12BLOCK4THRESHOLD = 0x01C4;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER12BLOCK5THRESHOLD = 0x01C5;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER12BLOCK6THRESHOLD = 0x01C6;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER12BLOCK7THRESHOLD = 0x01C7;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER12BLOCK8THRESHOLD = 0x01C8;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER12BLOCK9THRESHOLD = 0x01C9;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER12BLOCK10THRESHOLD = 0x01CA;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER12BLOCK11THRESHOLD = 0x01CB;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER12BLOCK12THRESHOLD = 0x01CC;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER12BLOCK13THRESHOLD = 0x01CD;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER12BLOCK14THRESHOLD = 0x01CE;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER12BLOCK15THRESHOLD = 0x01CF;
    /**
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     */
    public static final int ATTR_TIER12BLOCKTHRESHOLDCOUNT = 0x01CF;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER13BLOCK1THRESHOLD = 0x01D1;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER13BLOCK2THRESHOLD = 0x01D2;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER13BLOCK3THRESHOLD = 0x01D3;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER13BLOCK4THRESHOLD = 0x01D4;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER13BLOCK5THRESHOLD = 0x01D5;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER13BLOCK6THRESHOLD = 0x01D6;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER13BLOCK7THRESHOLD = 0x01D7;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER13BLOCK8THRESHOLD = 0x01D8;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER13BLOCK9THRESHOLD = 0x01D9;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER13BLOCK10THRESHOLD = 0x01DA;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER13BLOCK11THRESHOLD = 0x01DB;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER13BLOCK12THRESHOLD = 0x01DC;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER13BLOCK13THRESHOLD = 0x01DD;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER13BLOCK14THRESHOLD = 0x01DE;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER13BLOCK15THRESHOLD = 0x01DF;
    /**
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     */
    public static final int ATTR_TIER13BLOCKTHRESHOLDCOUNT = 0x01DF;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER14BLOCK1THRESHOLD = 0x01E1;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER14BLOCK2THRESHOLD = 0x01E2;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER14BLOCK3THRESHOLD = 0x01E3;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER14BLOCK4THRESHOLD = 0x01E4;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER14BLOCK5THRESHOLD = 0x01E5;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER14BLOCK6THRESHOLD = 0x01E6;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER14BLOCK7THRESHOLD = 0x01E7;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER14BLOCK8THRESHOLD = 0x01E8;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER14BLOCK9THRESHOLD = 0x01E9;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER14BLOCK10THRESHOLD = 0x01EA;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER14BLOCK11THRESHOLD = 0x01EB;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER14BLOCK12THRESHOLD = 0x01EC;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER14BLOCK13THRESHOLD = 0x01ED;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER14BLOCK14THRESHOLD = 0x01EE;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER14BLOCK15THRESHOLD = 0x01EF;
    /**
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     */
    public static final int ATTR_TIER14BLOCKTHRESHOLDCOUNT = 0x01EF;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER15BLOCK1THRESHOLD = 0x01F1;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER15BLOCK2THRESHOLD = 0x01F2;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER15BLOCK3THRESHOLD = 0x01F3;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER15BLOCK4THRESHOLD = 0x01F4;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER15BLOCK5THRESHOLD = 0x01F5;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER15BLOCK6THRESHOLD = 0x01F6;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER15BLOCK7THRESHOLD = 0x01F7;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER15BLOCK8THRESHOLD = 0x01F8;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER15BLOCK9THRESHOLD = 0x01F9;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER15BLOCK10THRESHOLD = 0x01FA;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER15BLOCK11THRESHOLD = 0x01FB;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER15BLOCK12THRESHOLD = 0x01FC;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER15BLOCK13THRESHOLD = 0x01FD;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER15BLOCK14THRESHOLD = 0x01FE;
    /**
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     */
    public static final int ATTR_TIER15BLOCK15THRESHOLD = 0x01FF;
    /**
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     */
    public static final int ATTR_TIER15BLOCKTHRESHOLDCOUNT = 0x01FF;
    /**
     * The StartofBlockPeriod attribute represents the start time of the current block
     * tariff period. A change indicates that a new Block Period is in effect.
     */
    public static final int ATTR_STARTOFBLOCKPERIOD = 0x0200;
    /**
     * The BlockPeriodDuration attribute represents the current block tariff period
     * duration in units defined by the BlockPeriodDurationType attribute. A change
     * indicates that only the duration of the current Block Period has been modified. A client
     * device shall expect a new Block Period following the expiration of the new duration.
     */
    public static final int ATTR_BLOCKPERIODDURATION = 0x0201;
    /**
     * ThresholdMultiplier provides a value to be multiplied against Threshold attributes.
     * If present, this attribute must be applied to all Block Threshold values to derive
     * values that can be compared against the CurrentBlockPeriodConsumptionDelivered
     * attribute within the Metering cluster. This attribute must be used in conjunction with
     * the ThresholdDivisor attribute. An attribute value of zero shall result in a unitary
     * multiplier (0x000001).
     */
    public static final int ATTR_THRESHOLDMULTIPLIER = 0x0202;
    /**
     * ThresholdDivisor provides a value to divide the result of applying the
     * ThresholdMultiplier attribute to Block Threshold values to derive values that can be
     * compared against the CurrentBlockPeriodConsumptionDelivered attribute within the
     * Metering cluster. This attribute must be used in conjunction with the
     * ThresholdMultiplier attribute. An attribute value of zero shall result in a unitary
     * divisor (0x000001).
     */
    public static final int ATTR_THRESHOLDDIVISOR = 0x0203;
    /**
     * The BlockPeriodDurationType attribute indicates the timebase used for the
     * BlockPeriodDuration attribute. A default value of 0x00 (Minutes) shall be assumed if
     * this attribute is not present.
     */
    public static final int ATTR_BLOCKPERIODDURATIONTYPE = 0x0204;
    /**
     * CommodityType provides a label for identifying the type of pricing CLIENT present. The
     * attribute is an enumerated value representing the commodity.
     */
    public static final int ATTR_COMMODITYTYPESERVER = 0x0300;
    /**
     * The value of the Standing Charge is a daily fixed charge associated with supplying the
     * commodity, measured in base unit of Currency with the decimal point located as
     * indicated by the Trailing Digits field of a Publish Price command or
     * PriceTrailingDigit attribute. A value of 0xFFFFFFFF indicates attribute not used.
     */
    public static final int ATTR_STANDINGCHARGE = 0x0301;
    public static final int ATTR_CONVERSIONFACTOR = 0x0302;
    public static final int ATTR_CONVERSIONFACTORTRAILINGDIGIT = 0x0303;
    public static final int ATTR_CALORIFICVALUE = 0x0304;
    public static final int ATTR_CALORIFICVALUEUNIT = 0x0305;
    public static final int ATTR_CALORIFICVALUETRAILINGDIGIT = 0x0306;
    public static final int ATTR_NOTIERBLOCK1PRICE = 0x0401;
    public static final int ATTR_NOTIERBLOCK2PRICE = 0x0402;
    public static final int ATTR_NOTIERBLOCK3PRICE = 0x0403;
    public static final int ATTR_NOTIERBLOCK4PRICE = 0x0404;
    public static final int ATTR_NOTIERBLOCK5PRICE = 0x0405;
    public static final int ATTR_NOTIERBLOCK6PRICE = 0x0406;
    public static final int ATTR_NOTIERBLOCK7PRICE = 0x0407;
    public static final int ATTR_NOTIERBLOCK8PRICE = 0x0408;
    public static final int ATTR_NOTIERBLOCK9PRICE = 0x0409;
    public static final int ATTR_NOTIERBLOCK10PRICE = 0x040A;
    public static final int ATTR_NOTIERBLOCK11PRICE = 0x040B;
    public static final int ATTR_NOTIERBLOCK12PRICE = 0x040C;
    public static final int ATTR_NOTIERBLOCK13PRICE = 0x040D;
    public static final int ATTR_NOTIERBLOCK14PRICE = 0x040E;
    public static final int ATTR_NOTIERBLOCK15PRICE = 0x040F;
    public static final int ATTR_NOTIERBLOCK16PRICE = 0x0410;
    public static final int ATTR_TIER1BLOCK1PRICE = 0x0411;
    public static final int ATTR_TIER1BLOCK2PRICE = 0x0412;
    public static final int ATTR_TIER1BLOCK3PRICE = 0x0413;
    public static final int ATTR_TIER1BLOCK4PRICE = 0x0414;
    public static final int ATTR_TIER1BLOCK5PRICE = 0x0415;
    public static final int ATTR_TIER1BLOCK6PRICE = 0x0416;
    public static final int ATTR_TIER1BLOCK7PRICE = 0x0417;
    public static final int ATTR_TIER1BLOCK8PRICE = 0x0418;
    public static final int ATTR_TIER1BLOCK9PRICE = 0x0419;
    public static final int ATTR_TIER1BLOCK10PRICE = 0x041A;
    public static final int ATTR_TIER1BLOCK11PRICE = 0x041B;
    public static final int ATTR_TIER1BLOCK12PRICE = 0x041C;
    public static final int ATTR_TIER1BLOCK13PRICE = 0x041D;
    public static final int ATTR_TIER1BLOCK14PRICE = 0x041E;
    public static final int ATTR_TIER1BLOCK15PRICE = 0x041F;
    public static final int ATTR_TIER1BLOCK16PRICE = 0x0420;
    public static final int ATTR_TIER2BLOCK1PRICE = 0x0421;
    public static final int ATTR_TIER2BLOCK2PRICE = 0x0422;
    public static final int ATTR_TIER2BLOCK3PRICE = 0x0423;
    public static final int ATTR_TIER2BLOCK4PRICE = 0x0424;
    public static final int ATTR_TIER2BLOCK5PRICE = 0x0425;
    public static final int ATTR_TIER2BLOCK6PRICE = 0x0426;
    public static final int ATTR_TIER2BLOCK7PRICE = 0x0427;
    public static final int ATTR_TIER2BLOCK8PRICE = 0x0428;
    public static final int ATTR_TIER2BLOCK9PRICE = 0x0429;
    public static final int ATTR_TIER2BLOCK10PRICE = 0x042A;
    public static final int ATTR_TIER2BLOCK11PRICE = 0x042B;
    public static final int ATTR_TIER2BLOCK12PRICE = 0x042C;
    public static final int ATTR_TIER2BLOCK13PRICE = 0x042D;
    public static final int ATTR_TIER2BLOCK14PRICE = 0x042E;
    public static final int ATTR_TIER2BLOCK15PRICE = 0x042F;
    public static final int ATTR_TIER2BLOCK16PRICE = 0x0430;
    public static final int ATTR_TIER3BLOCK1PRICE = 0x0431;
    public static final int ATTR_TIER3BLOCK2PRICE = 0x0432;
    public static final int ATTR_TIER3BLOCK3PRICE = 0x0433;
    public static final int ATTR_TIER3BLOCK4PRICE = 0x0434;
    public static final int ATTR_TIER3BLOCK5PRICE = 0x0435;
    public static final int ATTR_TIER3BLOCK6PRICE = 0x0436;
    public static final int ATTR_TIER3BLOCK7PRICE = 0x0437;
    public static final int ATTR_TIER3BLOCK8PRICE = 0x0438;
    public static final int ATTR_TIER3BLOCK9PRICE = 0x0439;
    public static final int ATTR_TIER3BLOCK10PRICE = 0x043A;
    public static final int ATTR_TIER3BLOCK11PRICE = 0x043B;
    public static final int ATTR_TIER3BLOCK12PRICE = 0x043C;
    public static final int ATTR_TIER3BLOCK13PRICE = 0x043D;
    public static final int ATTR_TIER3BLOCK14PRICE = 0x043E;
    public static final int ATTR_TIER3BLOCK15PRICE = 0x043F;
    public static final int ATTR_TIER3BLOCK16PRICE = 0x0440;
    public static final int ATTR_TIER4BLOCK1PRICE = 0x0441;
    public static final int ATTR_TIER4BLOCK2PRICE = 0x0442;
    public static final int ATTR_TIER4BLOCK3PRICE = 0x0443;
    public static final int ATTR_TIER4BLOCK4PRICE = 0x0444;
    public static final int ATTR_TIER4BLOCK5PRICE = 0x0445;
    public static final int ATTR_TIER4BLOCK6PRICE = 0x0446;
    public static final int ATTR_TIER4BLOCK7PRICE = 0x0447;
    public static final int ATTR_TIER4BLOCK8PRICE = 0x0448;
    public static final int ATTR_TIER4BLOCK9PRICE = 0x0449;
    public static final int ATTR_TIER4BLOCK10PRICE = 0x044A;
    public static final int ATTR_TIER4BLOCK11PRICE = 0x044B;
    public static final int ATTR_TIER4BLOCK12PRICE = 0x044C;
    public static final int ATTR_TIER4BLOCK13PRICE = 0x044D;
    public static final int ATTR_TIER4BLOCK14PRICE = 0x044E;
    public static final int ATTR_TIER4BLOCK15PRICE = 0x044F;
    public static final int ATTR_TIER4BLOCK16PRICE = 0x0450;
    public static final int ATTR_TIER5BLOCK1PRICE = 0x0451;
    public static final int ATTR_TIER5BLOCK2PRICE = 0x0452;
    public static final int ATTR_TIER5BLOCK3PRICE = 0x0453;
    public static final int ATTR_TIER5BLOCK4PRICE = 0x0454;
    public static final int ATTR_TIER5BLOCK5PRICE = 0x0455;
    public static final int ATTR_TIER5BLOCK6PRICE = 0x0456;
    public static final int ATTR_TIER5BLOCK7PRICE = 0x0457;
    public static final int ATTR_TIER5BLOCK8PRICE = 0x0458;
    public static final int ATTR_TIER5BLOCK9PRICE = 0x0459;
    public static final int ATTR_TIER5BLOCK10PRICE = 0x045A;
    public static final int ATTR_TIER5BLOCK11PRICE = 0x045B;
    public static final int ATTR_TIER5BLOCK12PRICE = 0x045C;
    public static final int ATTR_TIER5BLOCK13PRICE = 0x045D;
    public static final int ATTR_TIER5BLOCK14PRICE = 0x045E;
    public static final int ATTR_TIER5BLOCK15PRICE = 0x045F;
    public static final int ATTR_TIER5BLOCK16PRICE = 0x0460;
    public static final int ATTR_TIER6BLOCK1PRICE = 0x0461;
    public static final int ATTR_TIER6BLOCK2PRICE = 0x0462;
    public static final int ATTR_TIER6BLOCK3PRICE = 0x0463;
    public static final int ATTR_TIER6BLOCK4PRICE = 0x0464;
    public static final int ATTR_TIER6BLOCK5PRICE = 0x0465;
    public static final int ATTR_TIER6BLOCK6PRICE = 0x0466;
    public static final int ATTR_TIER6BLOCK7PRICE = 0x0467;
    public static final int ATTR_TIER6BLOCK8PRICE = 0x0468;
    public static final int ATTR_TIER6BLOCK9PRICE = 0x0469;
    public static final int ATTR_TIER6BLOCK10PRICE = 0x046A;
    public static final int ATTR_TIER6BLOCK11PRICE = 0x046B;
    public static final int ATTR_TIER6BLOCK12PRICE = 0x046C;
    public static final int ATTR_TIER6BLOCK13PRICE = 0x046D;
    public static final int ATTR_TIER6BLOCK14PRICE = 0x046E;
    public static final int ATTR_TIER6BLOCK15PRICE = 0x046F;
    public static final int ATTR_TIER6BLOCK16PRICE = 0x0470;
    public static final int ATTR_TIER7BLOCK1PRICE = 0x0471;
    public static final int ATTR_TIER7BLOCK2PRICE = 0x0472;
    public static final int ATTR_TIER7BLOCK3PRICE = 0x0473;
    public static final int ATTR_TIER7BLOCK4PRICE = 0x0474;
    public static final int ATTR_TIER7BLOCK5PRICE = 0x0475;
    public static final int ATTR_TIER7BLOCK6PRICE = 0x0476;
    public static final int ATTR_TIER7BLOCK7PRICE = 0x0477;
    public static final int ATTR_TIER7BLOCK8PRICE = 0x0478;
    public static final int ATTR_TIER7BLOCK9PRICE = 0x0479;
    public static final int ATTR_TIER7BLOCK10PRICE = 0x047A;
    public static final int ATTR_TIER7BLOCK11PRICE = 0x047B;
    public static final int ATTR_TIER7BLOCK12PRICE = 0x047C;
    public static final int ATTR_TIER7BLOCK13PRICE = 0x047D;
    public static final int ATTR_TIER7BLOCK14PRICE = 0x047E;
    public static final int ATTR_TIER7BLOCK15PRICE = 0x047F;
    public static final int ATTR_TIER7BLOCK16PRICE = 0x0480;
    public static final int ATTR_TIER8BLOCK1PRICE = 0x0481;
    public static final int ATTR_TIER8BLOCK2PRICE = 0x0482;
    public static final int ATTR_TIER8BLOCK3PRICE = 0x0483;
    public static final int ATTR_TIER8BLOCK4PRICE = 0x0484;
    public static final int ATTR_TIER8BLOCK5PRICE = 0x0485;
    public static final int ATTR_TIER8BLOCK6PRICE = 0x0486;
    public static final int ATTR_TIER8BLOCK7PRICE = 0x0487;
    public static final int ATTR_TIER8BLOCK8PRICE = 0x0488;
    public static final int ATTR_TIER8BLOCK9PRICE = 0x0489;
    public static final int ATTR_TIER8BLOCK10PRICE = 0x048A;
    public static final int ATTR_TIER8BLOCK11PRICE = 0x048B;
    public static final int ATTR_TIER8BLOCK12PRICE = 0x048C;
    public static final int ATTR_TIER8BLOCK13PRICE = 0x048D;
    public static final int ATTR_TIER8BLOCK14PRICE = 0x048E;
    public static final int ATTR_TIER8BLOCK15PRICE = 0x048F;
    public static final int ATTR_TIER8BLOCK16PRICE = 0x0490;
    public static final int ATTR_TIER9BLOCK1PRICE = 0x0491;
    public static final int ATTR_TIER9BLOCK2PRICE = 0x0492;
    public static final int ATTR_TIER9BLOCK3PRICE = 0x0493;
    public static final int ATTR_TIER9BLOCK4PRICE = 0x0494;
    public static final int ATTR_TIER9BLOCK5PRICE = 0x0495;
    public static final int ATTR_TIER9BLOCK6PRICE = 0x0496;
    public static final int ATTR_TIER9BLOCK7PRICE = 0x0497;
    public static final int ATTR_TIER9BLOCK8PRICE = 0x0498;
    public static final int ATTR_TIER9BLOCK9PRICE = 0x0499;
    public static final int ATTR_TIER9BLOCK10PRICE = 0x049A;
    public static final int ATTR_TIER9BLOCK11PRICE = 0x049B;
    public static final int ATTR_TIER9BLOCK12PRICE = 0x049C;
    public static final int ATTR_TIER9BLOCK13PRICE = 0x049D;
    public static final int ATTR_TIER9BLOCK14PRICE = 0x049E;
    public static final int ATTR_TIER9BLOCK15PRICE = 0x049F;
    public static final int ATTR_TIER9BLOCK16PRICE = 0x04A0;
    public static final int ATTR_TIER10BLOCK1PRICE = 0x04A1;
    public static final int ATTR_TIER10BLOCK2PRICE = 0x04A2;
    public static final int ATTR_TIER10BLOCK3PRICE = 0x04A3;
    public static final int ATTR_TIER10BLOCK4PRICE = 0x04A4;
    public static final int ATTR_TIER10BLOCK5PRICE = 0x04A5;
    public static final int ATTR_TIER10BLOCK6PRICE = 0x04A6;
    public static final int ATTR_TIER10BLOCK7PRICE = 0x04A7;
    public static final int ATTR_TIER10BLOCK8PRICE = 0x04A8;
    public static final int ATTR_TIER10BLOCK9PRICE = 0x04A9;
    public static final int ATTR_TIER10BLOCK10PRICE = 0x04AA;
    public static final int ATTR_TIER10BLOCK11PRICE = 0x04AB;
    public static final int ATTR_TIER10BLOCK12PRICE = 0x04AC;
    public static final int ATTR_TIER10BLOCK13PRICE = 0x04AD;
    public static final int ATTR_TIER10BLOCK14PRICE = 0x04AE;
    public static final int ATTR_TIER10BLOCK15PRICE = 0x04AF;
    public static final int ATTR_TIER10BLOCK16PRICE = 0x04B0;
    public static final int ATTR_TIER11BLOCK1PRICE = 0x04B1;
    public static final int ATTR_TIER11BLOCK2PRICE = 0x04B2;
    public static final int ATTR_TIER11BLOCK3PRICE = 0x04B3;
    public static final int ATTR_TIER11BLOCK4PRICE = 0x04B4;
    public static final int ATTR_TIER11BLOCK5PRICE = 0x04B5;
    public static final int ATTR_TIER11BLOCK6PRICE = 0x04B6;
    public static final int ATTR_TIER11BLOCK7PRICE = 0x04B7;
    public static final int ATTR_TIER11BLOCK8PRICE = 0x04B8;
    public static final int ATTR_TIER11BLOCK9PRICE = 0x04B9;
    public static final int ATTR_TIER11BLOCK10PRICE = 0x04BA;
    public static final int ATTR_TIER11BLOCK11PRICE = 0x04BB;
    public static final int ATTR_TIER11BLOCK12PRICE = 0x04BC;
    public static final int ATTR_TIER11BLOCK13PRICE = 0x04BD;
    public static final int ATTR_TIER11BLOCK14PRICE = 0x04BE;
    public static final int ATTR_TIER11BLOCK15PRICE = 0x04BF;
    public static final int ATTR_TIER11BLOCK16PRICE = 0x04C0;
    public static final int ATTR_TIER12BLOCK1PRICE = 0x04C1;
    public static final int ATTR_TIER12BLOCK2PRICE = 0x04C2;
    public static final int ATTR_TIER12BLOCK3PRICE = 0x04C3;
    public static final int ATTR_TIER12BLOCK4PRICE = 0x04C4;
    public static final int ATTR_TIER12BLOCK5PRICE = 0x04C5;
    public static final int ATTR_TIER12BLOCK6PRICE = 0x04C6;
    public static final int ATTR_TIER12BLOCK7PRICE = 0x04C7;
    public static final int ATTR_TIER12BLOCK8PRICE = 0x04C8;
    public static final int ATTR_TIER12BLOCK9PRICE = 0x04C9;
    public static final int ATTR_TIER12BLOCK10PRICE = 0x04CA;
    public static final int ATTR_TIER12BLOCK11PRICE = 0x04CB;
    public static final int ATTR_TIER12BLOCK12PRICE = 0x04CC;
    public static final int ATTR_TIER12BLOCK13PRICE = 0x04CD;
    public static final int ATTR_TIER12BLOCK14PRICE = 0x04CE;
    public static final int ATTR_TIER12BLOCK15PRICE = 0x04CF;
    public static final int ATTR_TIER12BLOCK16PRICE = 0x04D0;
    public static final int ATTR_TIER13BLOCK1PRICE = 0x04D1;
    public static final int ATTR_TIER13BLOCK2PRICE = 0x04D2;
    public static final int ATTR_TIER13BLOCK3PRICE = 0x04D3;
    public static final int ATTR_TIER13BLOCK4PRICE = 0x04D4;
    public static final int ATTR_TIER13BLOCK5PRICE = 0x04D5;
    public static final int ATTR_TIER13BLOCK6PRICE = 0x04D6;
    public static final int ATTR_TIER13BLOCK7PRICE = 0x04D7;
    public static final int ATTR_TIER13BLOCK8PRICE = 0x04D8;
    public static final int ATTR_TIER13BLOCK9PRICE = 0x04D9;
    public static final int ATTR_TIER13BLOCK10PRICE = 0x04DA;
    public static final int ATTR_TIER13BLOCK11PRICE = 0x04DB;
    public static final int ATTR_TIER13BLOCK12PRICE = 0x04DC;
    public static final int ATTR_TIER13BLOCK13PRICE = 0x04DD;
    public static final int ATTR_TIER13BLOCK14PRICE = 0x04DE;
    public static final int ATTR_TIER13BLOCK15PRICE = 0x04DF;
    public static final int ATTR_TIER13BLOCK16PRICE = 0x04E0;
    public static final int ATTR_TIER14BLOCK1PRICE = 0x04E1;
    public static final int ATTR_TIER14BLOCK2PRICE = 0x04E2;
    public static final int ATTR_TIER14BLOCK3PRICE = 0x04E3;
    public static final int ATTR_TIER14BLOCK4PRICE = 0x04E4;
    public static final int ATTR_TIER14BLOCK5PRICE = 0x04E5;
    public static final int ATTR_TIER14BLOCK6PRICE = 0x04E6;
    public static final int ATTR_TIER14BLOCK7PRICE = 0x04E7;
    public static final int ATTR_TIER14BLOCK8PRICE = 0x04E8;
    public static final int ATTR_TIER14BLOCK9PRICE = 0x04E9;
    public static final int ATTR_TIER14BLOCK10PRICE = 0x04EA;
    public static final int ATTR_TIER14BLOCK11PRICE = 0x04EB;
    public static final int ATTR_TIER14BLOCK12PRICE = 0x04EC;
    public static final int ATTR_TIER14BLOCK13PRICE = 0x04ED;
    public static final int ATTR_TIER14BLOCK14PRICE = 0x04EE;
    public static final int ATTR_TIER14BLOCK15PRICE = 0x04EF;
    public static final int ATTR_TIER14BLOCK16PRICE = 0x04F0;
    public static final int ATTR_TIER15BLOCK1PRICE = 0x04F1;
    public static final int ATTR_TIER15BLOCK2PRICE = 0x04F2;
    public static final int ATTR_TIER15BLOCK3PRICE = 0x04F3;
    public static final int ATTR_TIER15BLOCK4PRICE = 0x04F4;
    public static final int ATTR_TIER15BLOCK5PRICE = 0x04F5;
    public static final int ATTR_TIER15BLOCK6PRICE = 0x04F6;
    public static final int ATTR_TIER15BLOCK7PRICE = 0x04F7;
    public static final int ATTR_TIER15BLOCK8PRICE = 0x04F8;
    public static final int ATTR_TIER15BLOCK9PRICE = 0x04F9;
    public static final int ATTR_TIER15BLOCK10PRICE = 0x04FA;
    public static final int ATTR_TIER15BLOCK11PRICE = 0x04FB;
    public static final int ATTR_TIER15BLOCK12PRICE = 0x04FC;
    public static final int ATTR_TIER15BLOCK13PRICE = 0x04FD;
    public static final int ATTR_TIER15BLOCK14PRICE = 0x04FE;
    public static final int ATTR_TIER15BLOCK15PRICE = 0x04FF;
    public static final int ATTR_TIER15BLOCK16PRICE = 0x0500;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER16 = 0x051F;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER17 = 0x0520;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER18 = 0x0521;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER19 = 0x0522;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER20 = 0x0523;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER21 = 0x0524;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER22 = 0x0525;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER23 = 0x0526;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER24 = 0x0527;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER25 = 0x0528;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER26 = 0x0529;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER27 = 0x052A;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER28 = 0x052B;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER29 = 0x052C;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER30 = 0x052D;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER31 = 0x052E;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER32 = 0x052F;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER33 = 0x0530;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER34 = 0x0531;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER35 = 0x0532;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER36 = 0x0533;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER37 = 0x0534;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER38 = 0x0535;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER39 = 0x0536;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER40 = 0x0537;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER41 = 0x0538;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER42 = 0x0539;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER43 = 0x053A;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER44 = 0x053B;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER45 = 0x053C;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER46 = 0x053D;
    /**
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     */
    public static final int ATTR_PRICETIER47 = 0x053E;
    /**
     * Attribute CPP1 Price represents the price of Energy, Gas, or Water delivered to the
     * premises (i.e. delivered to the customer from the utility) while Critical Peak Pricing
     * ‘CPP1’ is being applied.
     */
    public static final int ATTR_CPP1PRICE = 0x05FE;
    /**
     * Attribute CPP2 Price represents the price of Energy, Gas, or Water delivered to the
     * premises (i.e. delivered to the customer from the utility) while Critical Peak Pricing
     * ‘CPP2’ is being applied.
     */
    public static final int ATTR_CPP2PRICE = 0x05FF;
    public static final int ATTR_TARIFFLABEL = 0x0610;
    public static final int ATTR_NUMBEROFPRICETIERSINUSE = 0x0611;
    public static final int ATTR_NUMBEROFBLOCKTHRESHOLDSINUSE = 0x0612;
    public static final int ATTR_TIERBLOCKMODE = 0x0613;
    public static final int ATTR_UNITOFMEASURE = 0x0615;
    public static final int ATTR_CURRENCY = 0x0616;
    public static final int ATTR_PRICETRAILINGDIGIT = 0x0617;
    /**
     * An 8 bit enumeration identifying the resolution period for Block Tariff
     */
    public static final int ATTR_TARIFFRESOLUTIONPERIOD = 0x0619;
    /**
     * Used to calculate the amount of carbon dioxide (CO2) produced from energy use. Natural
     * gas has a conversion factor of about 0.185, e.g. 1,000 kWh of gas used is responsible for
     * the production of 185kg CO2 (0.185 x 1000 kWh). The CO2 attribute represents the current
     * active value
     */
    public static final int ATTR_CO2 = 0x0620;
    /**
     * This attribute is an 8-bit enumeration which defines the unit for the CO2 attribute. The
     * values and descriptions for this attribute are listed in Table D-83 below. The CO2Unit
     * attribute represents the current active value.
     */
    public static final int ATTR_CO2UNIT = 0x0621;
    public static final int ATTR_CO2TRAILINGDIGIT = 0x0622;
    /**
     * The CurrentBillingPeriodStart attribute represents the start time of the current
     * billing period.
     */
    public static final int ATTR_CURRENTBILLINGPERIODSTART = 0x0700;
    /**
     * The CurrentBillingPeriodDuration attribute represents the current billing period
     * duration in minutes.
     */
    public static final int ATTR_CURRENTBILLINGPERIODDURATION = 0x0701;
    /**
     * The LastBillingPeriodStart attribute represents the start time of the last billing
     * period.
     */
    public static final int ATTR_LASTBILLINGPERIODSTART = 0x0702;
    /**
     * The LastBillingPeriodDuration attribute is the duration of the last billing period in
     * minutes (start to end of last billing period).
     */
    public static final int ATTR_LASTBILLINGPERIODDURATION = 0x0703;
    /**
     * The LastBillingPeriodConsolidatedBill attribute is an amount for the cost of the
     * energy supplied from the date of the LastBillingPeriodStart attribute and until the
     * duration of the LastBillingPeriodDuration attribute expires, measured in base unit
     * of Currency with the decimal point located as indicated by the Trailing Digits
     * attribute.
     */
    public static final int ATTR_LASTBILLINGPERIODCONSOLIDATEDBILL = 0x0704;
    /**
     * The CreditPaymentDueDate attribute indicates the date and time when the next credit
     * payment is due to be paid by the consumer to the supplier.
     */
    public static final int ATTR_CREDITPAYMENTDUEDATE = 0x0800;
    /**
     * The CreditPaymentStatus attribute indicates the current status of the last payment.
     */
    public static final int ATTR_CREDITPAYMENTSTATUS = 0x0801;
    /**
     * This is the total of the consolidated bill amounts accumulated since the last payment.
     */
    public static final int ATTR_CREDITPAYMENTOVERDUEAMOUNT = 0x0802;
    /**
     * The PaymentDiscount attribute indicates the discount that the energy supplier has
     * applied to the consolidated bill.
     */
    public static final int ATTR_PAYMENTDISCOUNT = 0x080A;
    /**
     * The PaymentDiscountPeriod attribute indicates the period for which this discount
     * shall be applied for.
     */
    public static final int ATTR_PAYMENTDISCOUNTPERIOD = 0x080B;
    public static final int ATTR_CREDITCARDPAYMENT1 = 0x0810;
    public static final int ATTR_CREDITCARDPAYMENTDATE1 = 0x0811;
    public static final int ATTR_CREDITCARDPAYMENTREF1 = 0x0812;
    public static final int ATTR_CREDITCARDPAYMENT2 = 0x0820;
    public static final int ATTR_CREDITCARDPAYMENTDATE2 = 0x0821;
    public static final int ATTR_CREDITCARDPAYMENTREF2 = 0x0822;
    public static final int ATTR_CREDITCARDPAYMENT3 = 0x0830;
    public static final int ATTR_CREDITCARDPAYMENTDATE3 = 0x0831;
    public static final int ATTR_CREDITCARDPAYMENTREF3 = 0x0832;
    public static final int ATTR_CREDITCARDPAYMENT4 = 0x0840;
    public static final int ATTR_CREDITCARDPAYMENTDATE4 = 0x0841;
    public static final int ATTR_CREDITCARDPAYMENTREF4 = 0x0842;
    public static final int ATTR_CREDITCARDPAYMENT5 = 0x0850;
    public static final int ATTR_CREDITCARDPAYMENTDATE5 = 0x0851;
    public static final int ATTR_CREDITCARDPAYMENTREF5 = 0x0852;
    public static final int ATTR_RECEIVEDTIER1PRICELABEL = 0x8001;
    public static final int ATTR_RECEIVEDTIER2PRICELABEL = 0x8002;
    public static final int ATTR_RECEIVEDTIER3PRICELABEL = 0x8003;
    public static final int ATTR_RECEIVEDTIER4PRICELABEL = 0x8004;
    public static final int ATTR_RECEIVEDTIER5PRICELABEL = 0x8005;
    public static final int ATTR_RECEIVEDTIER6PRICELABEL = 0x8006;
    public static final int ATTR_RECEIVEDTIER7PRICELABEL = 0x8007;
    public static final int ATTR_RECEIVEDTIER8PRICELABEL = 0x8008;
    public static final int ATTR_RECEIVEDTIER9PRICELABEL = 0x8009;
    public static final int ATTR_RECEIVEDTIER10PRICELABEL = 0x800A;
    public static final int ATTR_RECEIVEDTIER11PRICELABEL = 0x800B;
    public static final int ATTR_RECEIVEDTIER12PRICELABEL = 0x800C;
    public static final int ATTR_RECEIVEDTIER13PRICELABEL = 0x800D;
    public static final int ATTR_RECEIVEDTIER14PRICELABEL = 0x800E;
    public static final int ATTR_RECEIVEDTIER15PRICELABEL = 0x800F;
    public static final int ATTR_RECEIVEDTIER16PRICELABEL = 0x8010;
    public static final int ATTR_RECEIVEDTIER17PRICELABEL = 0x8011;
    public static final int ATTR_RECEIVEDTIER18PRICELABEL = 0x8012;
    public static final int ATTR_RECEIVEDTIER19PRICELABEL = 0x8013;
    public static final int ATTR_RECEIVEDTIER20PRICELABEL = 0x8014;
    public static final int ATTR_RECEIVEDTIER21PRICELABEL = 0x8015;
    public static final int ATTR_RECEIVEDTIER22PRICELABEL = 0x8016;
    public static final int ATTR_RECEIVEDTIER23PRICELABEL = 0x8017;
    public static final int ATTR_RECEIVEDTIER24PRICELABEL = 0x8018;
    public static final int ATTR_RECEIVEDTIER25PRICELABEL = 0x8019;
    public static final int ATTR_RECEIVEDTIER26PRICELABEL = 0x801A;
    public static final int ATTR_RECEIVEDTIER27PRICELABEL = 0x801B;
    public static final int ATTR_RECEIVEDTIER28PRICELABEL = 0x801C;
    public static final int ATTR_RECEIVEDTIER29PRICELABEL = 0x801D;
    public static final int ATTR_RECEIVEDTIER30PRICELABEL = 0x801E;
    public static final int ATTR_RECEIVEDTIER31PRICELABEL = 0x801F;
    public static final int ATTR_RECEIVEDTIER32PRICELABEL = 0x8020;
    public static final int ATTR_RECEIVEDTIER33PRICELABEL = 0x8021;
    public static final int ATTR_RECEIVEDTIER34PRICELABEL = 0x8022;
    public static final int ATTR_RECEIVEDTIER35PRICELABEL = 0x8023;
    public static final int ATTR_RECEIVEDTIER36PRICELABEL = 0x8024;
    public static final int ATTR_RECEIVEDTIER37PRICELABEL = 0x8025;
    public static final int ATTR_RECEIVEDTIER38PRICELABEL = 0x8026;
    public static final int ATTR_RECEIVEDTIER39PRICELABEL = 0x8027;
    public static final int ATTR_RECEIVEDTIER40PRICELABEL = 0x8028;
    public static final int ATTR_RECEIVEDTIER41PRICELABEL = 0x8029;
    public static final int ATTR_RECEIVEDTIER42PRICELABEL = 0x802A;
    public static final int ATTR_RECEIVEDTIER43PRICELABEL = 0x802B;
    public static final int ATTR_RECEIVEDTIER44PRICELABEL = 0x802C;
    public static final int ATTR_RECEIVEDTIER45PRICELABEL = 0x802D;
    public static final int ATTR_RECEIVEDTIER46PRICELABEL = 0x802E;
    public static final int ATTR_RECEIVEDTIER47PRICELABEL = 0x802F;
    public static final int ATTR_RECEIVEDTIER48PRICELABEL = 0x8030;
    public static final int ATTR_RECEIVEDBLOCK1THRESHOLD = 0x8101;
    public static final int ATTR_RECEIVEDBLOCK2THRESHOLD = 0x8102;
    public static final int ATTR_RECEIVEDBLOCK3THRESHOLD = 0x8103;
    public static final int ATTR_RECEIVEDBLOCK4THRESHOLD = 0x8104;
    public static final int ATTR_RECEIVEDBLOCK5THRESHOLD = 0x8105;
    public static final int ATTR_RECEIVEDBLOCK6THRESHOLD = 0x8106;
    public static final int ATTR_RECEIVEDBLOCK7THRESHOLD = 0x8107;
    public static final int ATTR_RECEIVEDBLOCK8THRESHOLD = 0x8108;
    public static final int ATTR_RECEIVEDBLOCK9THRESHOLD = 0x8109;
    public static final int ATTR_RECEIVEDBLOCK10THRESHOLD = 0x810A;
    public static final int ATTR_RECEIVEDBLOCK11THRESHOLD = 0x810B;
    public static final int ATTR_RECEIVEDBLOCK12THRESHOLD = 0x810C;
    public static final int ATTR_RECEIVEDBLOCK13THRESHOLD = 0x810D;
    public static final int ATTR_RECEIVEDBLOCK14THRESHOLD = 0x810E;
    public static final int ATTR_RECEIVEDBLOCK15THRESHOLD = 0x810F;
    public static final int ATTR_RECEIVEDBLOCK16THRESHOLD = 0x8110;
    public static final int ATTR_RECEIVEDSTARTOFBLOCKPERIOD = 0x8200;
    public static final int ATTR_RECEIVEDBLOCKPERIODDURATION = 0x8201;
    public static final int ATTR_RECEIVEDTHRESHOLDMULTIPLIER = 0x8202;
    public static final int ATTR_RECEIVEDTHRESHOLDDIVISOR = 0x8203;
    public static final int ATTR_RXNOTIERBLOCK1PRICE = 0x8401;
    public static final int ATTR_RXNOTIERBLOCK2PRICE = 0x8402;
    public static final int ATTR_RXNOTIERBLOCK3PRICE = 0x8403;
    public static final int ATTR_RXNOTIERBLOCK4PRICE = 0x8404;
    public static final int ATTR_RXNOTIERBLOCK5PRICE = 0x8405;
    public static final int ATTR_RXNOTIERBLOCK6PRICE = 0x8406;
    public static final int ATTR_RXNOTIERBLOCK7PRICE = 0x8407;
    public static final int ATTR_RXNOTIERBLOCK8PRICE = 0x8408;
    public static final int ATTR_RXNOTIERBLOCK9PRICE = 0x8409;
    public static final int ATTR_RXNOTIERBLOCK10PRICE = 0x840A;
    public static final int ATTR_RXNOTIERBLOCK11PRICE = 0x840B;
    public static final int ATTR_RXNOTIERBLOCK12PRICE = 0x840C;
    public static final int ATTR_RXNOTIERBLOCK13PRICE = 0x840D;
    public static final int ATTR_RXNOTIERBLOCK14PRICE = 0x840E;
    public static final int ATTR_RXNOTIERBLOCK15PRICE = 0x840F;
    public static final int ATTR_RXNOTIERBLOCK16PRICE = 0x8410;
    public static final int ATTR_RXTIER1BLOCK1PRICE = 0x8411;
    public static final int ATTR_RXTIER1BLOCK2PRICE = 0x8412;
    public static final int ATTR_RXTIER1BLOCK3PRICE = 0x8413;
    public static final int ATTR_RXTIER1BLOCK4PRICE = 0x8414;
    public static final int ATTR_RXTIER1BLOCK5PRICE = 0x8415;
    public static final int ATTR_RXTIER1BLOCK6PRICE = 0x8416;
    public static final int ATTR_RXTIER1BLOCK7PRICE = 0x8417;
    public static final int ATTR_RXTIER1BLOCK8PRICE = 0x8418;
    public static final int ATTR_RXTIER1BLOCK9PRICE = 0x8419;
    public static final int ATTR_RXTIER1BLOCK10PRICE = 0x841A;
    public static final int ATTR_RXTIER1BLOCK11PRICE = 0x841B;
    public static final int ATTR_RXTIER1BLOCK12PRICE = 0x841C;
    public static final int ATTR_RXTIER1BLOCK13PRICE = 0x841D;
    public static final int ATTR_RXTIER1BLOCK14PRICE = 0x841E;
    public static final int ATTR_RXTIER1BLOCK15PRICE = 0x841F;
    public static final int ATTR_RXTIER1BLOCK16PRICE = 0x8420;
    public static final int ATTR_RXTIER2BLOCK1PRICE = 0x8421;
    public static final int ATTR_RXTIER2BLOCK2PRICE = 0x8422;
    public static final int ATTR_RXTIER2BLOCK3PRICE = 0x8423;
    public static final int ATTR_RXTIER2BLOCK4PRICE = 0x8424;
    public static final int ATTR_RXTIER2BLOCK5PRICE = 0x8425;
    public static final int ATTR_RXTIER2BLOCK6PRICE = 0x8426;
    public static final int ATTR_RXTIER2BLOCK7PRICE = 0x8427;
    public static final int ATTR_RXTIER2BLOCK8PRICE = 0x8428;
    public static final int ATTR_RXTIER2BLOCK9PRICE = 0x8429;
    public static final int ATTR_RXTIER2BLOCK10PRICE = 0x842A;
    public static final int ATTR_RXTIER2BLOCK11PRICE = 0x842B;
    public static final int ATTR_RXTIER2BLOCK12PRICE = 0x842C;
    public static final int ATTR_RXTIER2BLOCK13PRICE = 0x842D;
    public static final int ATTR_RXTIER2BLOCK14PRICE = 0x842E;
    public static final int ATTR_RXTIER2BLOCK15PRICE = 0x842F;
    public static final int ATTR_RXTIER2BLOCK16PRICE = 0x8430;
    public static final int ATTR_RXTIER3BLOCK1PRICE = 0x8431;
    public static final int ATTR_RXTIER3BLOCK2PRICE = 0x8432;
    public static final int ATTR_RXTIER3BLOCK3PRICE = 0x8433;
    public static final int ATTR_RXTIER3BLOCK4PRICE = 0x8434;
    public static final int ATTR_RXTIER3BLOCK5PRICE = 0x8435;
    public static final int ATTR_RXTIER3BLOCK6PRICE = 0x8436;
    public static final int ATTR_RXTIER3BLOCK7PRICE = 0x8437;
    public static final int ATTR_RXTIER3BLOCK8PRICE = 0x8438;
    public static final int ATTR_RXTIER3BLOCK9PRICE = 0x8439;
    public static final int ATTR_RXTIER3BLOCK10PRICE = 0x843A;
    public static final int ATTR_RXTIER3BLOCK11PRICE = 0x843B;
    public static final int ATTR_RXTIER3BLOCK12PRICE = 0x843C;
    public static final int ATTR_RXTIER3BLOCK13PRICE = 0x843D;
    public static final int ATTR_RXTIER3BLOCK14PRICE = 0x843E;
    public static final int ATTR_RXTIER3BLOCK15PRICE = 0x843F;
    public static final int ATTR_RXTIER3BLOCK16PRICE = 0x8440;
    public static final int ATTR_RXTIER4BLOCK1PRICE = 0x8441;
    public static final int ATTR_RXTIER4BLOCK2PRICE = 0x8442;
    public static final int ATTR_RXTIER4BLOCK3PRICE = 0x8443;
    public static final int ATTR_RXTIER4BLOCK4PRICE = 0x8444;
    public static final int ATTR_RXTIER4BLOCK5PRICE = 0x8445;
    public static final int ATTR_RXTIER4BLOCK6PRICE = 0x8446;
    public static final int ATTR_RXTIER4BLOCK7PRICE = 0x8447;
    public static final int ATTR_RXTIER4BLOCK8PRICE = 0x8448;
    public static final int ATTR_RXTIER4BLOCK9PRICE = 0x8449;
    public static final int ATTR_RXTIER4BLOCK10PRICE = 0x844A;
    public static final int ATTR_RXTIER4BLOCK11PRICE = 0x844B;
    public static final int ATTR_RXTIER4BLOCK12PRICE = 0x844C;
    public static final int ATTR_RXTIER4BLOCK13PRICE = 0x844D;
    public static final int ATTR_RXTIER4BLOCK14PRICE = 0x844E;
    public static final int ATTR_RXTIER4BLOCK15PRICE = 0x844F;
    public static final int ATTR_RXTIER4BLOCK16PRICE = 0x8450;
    public static final int ATTR_RXTIER5BLOCK1PRICE = 0x8451;
    public static final int ATTR_RXTIER5BLOCK2PRICE = 0x8452;
    public static final int ATTR_RXTIER5BLOCK3PRICE = 0x8453;
    public static final int ATTR_RXTIER5BLOCK4PRICE = 0x8454;
    public static final int ATTR_RXTIER5BLOCK5PRICE = 0x8455;
    public static final int ATTR_RXTIER5BLOCK6PRICE = 0x8456;
    public static final int ATTR_RXTIER5BLOCK7PRICE = 0x8457;
    public static final int ATTR_RXTIER5BLOCK8PRICE = 0x8458;
    public static final int ATTR_RXTIER5BLOCK9PRICE = 0x8459;
    public static final int ATTR_RXTIER5BLOCK10PRICE = 0x845A;
    public static final int ATTR_RXTIER5BLOCK11PRICE = 0x845B;
    public static final int ATTR_RXTIER5BLOCK12PRICE = 0x845C;
    public static final int ATTR_RXTIER5BLOCK13PRICE = 0x845D;
    public static final int ATTR_RXTIER5BLOCK14PRICE = 0x845E;
    public static final int ATTR_RXTIER5BLOCK15PRICE = 0x845F;
    public static final int ATTR_RXTIER5BLOCK16PRICE = 0x8460;
    public static final int ATTR_RXTIER6BLOCK1PRICE = 0x8461;
    public static final int ATTR_RXTIER6BLOCK2PRICE = 0x8462;
    public static final int ATTR_RXTIER6BLOCK3PRICE = 0x8463;
    public static final int ATTR_RXTIER6BLOCK4PRICE = 0x8464;
    public static final int ATTR_RXTIER6BLOCK5PRICE = 0x8465;
    public static final int ATTR_RXTIER6BLOCK6PRICE = 0x8466;
    public static final int ATTR_RXTIER6BLOCK7PRICE = 0x8467;
    public static final int ATTR_RXTIER6BLOCK8PRICE = 0x8468;
    public static final int ATTR_RXTIER6BLOCK9PRICE = 0x8469;
    public static final int ATTR_RXTIER6BLOCK10PRICE = 0x846A;
    public static final int ATTR_RXTIER6BLOCK11PRICE = 0x846B;
    public static final int ATTR_RXTIER6BLOCK12PRICE = 0x846C;
    public static final int ATTR_RXTIER6BLOCK13PRICE = 0x846D;
    public static final int ATTR_RXTIER6BLOCK14PRICE = 0x846E;
    public static final int ATTR_RXTIER6BLOCK15PRICE = 0x846F;
    public static final int ATTR_RXTIER6BLOCK16PRICE = 0x8470;
    public static final int ATTR_RXTIER7BLOCK1PRICE = 0x8471;
    public static final int ATTR_RXTIER7BLOCK2PRICE = 0x8472;
    public static final int ATTR_RXTIER7BLOCK3PRICE = 0x8473;
    public static final int ATTR_RXTIER7BLOCK4PRICE = 0x8474;
    public static final int ATTR_RXTIER7BLOCK5PRICE = 0x8475;
    public static final int ATTR_RXTIER7BLOCK6PRICE = 0x8476;
    public static final int ATTR_RXTIER7BLOCK7PRICE = 0x8477;
    public static final int ATTR_RXTIER7BLOCK8PRICE = 0x8478;
    public static final int ATTR_RXTIER7BLOCK9PRICE = 0x8479;
    public static final int ATTR_RXTIER7BLOCK10PRICE = 0x847A;
    public static final int ATTR_RXTIER7BLOCK11PRICE = 0x847B;
    public static final int ATTR_RXTIER7BLOCK12PRICE = 0x847C;
    public static final int ATTR_RXTIER7BLOCK13PRICE = 0x847D;
    public static final int ATTR_RXTIER7BLOCK14PRICE = 0x847E;
    public static final int ATTR_RXTIER7BLOCK15PRICE = 0x847F;
    public static final int ATTR_RXTIER7BLOCK16PRICE = 0x8480;
    public static final int ATTR_RXTIER8BLOCK1PRICE = 0x8481;
    public static final int ATTR_RXTIER8BLOCK2PRICE = 0x8482;
    public static final int ATTR_RXTIER8BLOCK3PRICE = 0x8483;
    public static final int ATTR_RXTIER8BLOCK4PRICE = 0x8484;
    public static final int ATTR_RXTIER8BLOCK5PRICE = 0x8485;
    public static final int ATTR_RXTIER8BLOCK6PRICE = 0x8486;
    public static final int ATTR_RXTIER8BLOCK7PRICE = 0x8487;
    public static final int ATTR_RXTIER8BLOCK8PRICE = 0x8488;
    public static final int ATTR_RXTIER8BLOCK9PRICE = 0x8489;
    public static final int ATTR_RXTIER8BLOCK10PRICE = 0x848A;
    public static final int ATTR_RXTIER8BLOCK11PRICE = 0x848B;
    public static final int ATTR_RXTIER8BLOCK12PRICE = 0x848C;
    public static final int ATTR_RXTIER8BLOCK13PRICE = 0x848D;
    public static final int ATTR_RXTIER8BLOCK14PRICE = 0x848E;
    public static final int ATTR_RXTIER8BLOCK15PRICE = 0x848F;
    public static final int ATTR_RXTIER8BLOCK16PRICE = 0x8490;
    public static final int ATTR_RXTIER9BLOCK1PRICE = 0x8491;
    public static final int ATTR_RXTIER9BLOCK2PRICE = 0x8492;
    public static final int ATTR_RXTIER9BLOCK3PRICE = 0x8493;
    public static final int ATTR_RXTIER9BLOCK4PRICE = 0x8494;
    public static final int ATTR_RXTIER9BLOCK5PRICE = 0x8495;
    public static final int ATTR_RXTIER9BLOCK6PRICE = 0x8496;
    public static final int ATTR_RXTIER9BLOCK7PRICE = 0x8497;
    public static final int ATTR_RXTIER9BLOCK8PRICE = 0x8498;
    public static final int ATTR_RXTIER9BLOCK9PRICE = 0x8499;
    public static final int ATTR_RXTIER9BLOCK10PRICE = 0x849A;
    public static final int ATTR_RXTIER9BLOCK11PRICE = 0x849B;
    public static final int ATTR_RXTIER9BLOCK12PRICE = 0x849C;
    public static final int ATTR_RXTIER9BLOCK13PRICE = 0x849D;
    public static final int ATTR_RXTIER9BLOCK14PRICE = 0x849E;
    public static final int ATTR_RXTIER9BLOCK15PRICE = 0x849F;
    public static final int ATTR_RXTIER9BLOCK16PRICE = 0x84A0;
    public static final int ATTR_RXTIER10BLOCK1PRICE = 0x84A1;
    public static final int ATTR_RXTIER10BLOCK2PRICE = 0x84A2;
    public static final int ATTR_RXTIER10BLOCK3PRICE = 0x84A3;
    public static final int ATTR_RXTIER10BLOCK4PRICE = 0x84A4;
    public static final int ATTR_RXTIER10BLOCK5PRICE = 0x84A5;
    public static final int ATTR_RXTIER10BLOCK6PRICE = 0x84A6;
    public static final int ATTR_RXTIER10BLOCK7PRICE = 0x84A7;
    public static final int ATTR_RXTIER10BLOCK8PRICE = 0x84A8;
    public static final int ATTR_RXTIER10BLOCK9PRICE = 0x84A9;
    public static final int ATTR_RXTIER10BLOCK10PRICE = 0x84AA;
    public static final int ATTR_RXTIER10BLOCK11PRICE = 0x84AB;
    public static final int ATTR_RXTIER10BLOCK12PRICE = 0x84AC;
    public static final int ATTR_RXTIER10BLOCK13PRICE = 0x84AD;
    public static final int ATTR_RXTIER10BLOCK14PRICE = 0x84AE;
    public static final int ATTR_RXTIER10BLOCK15PRICE = 0x84AF;
    public static final int ATTR_RXTIER10BLOCK16PRICE = 0x84B0;
    public static final int ATTR_RXTIER11BLOCK1PRICE = 0x84B1;
    public static final int ATTR_RXTIER11BLOCK2PRICE = 0x84B2;
    public static final int ATTR_RXTIER11BLOCK3PRICE = 0x84B3;
    public static final int ATTR_RXTIER11BLOCK4PRICE = 0x84B4;
    public static final int ATTR_RXTIER11BLOCK5PRICE = 0x84B5;
    public static final int ATTR_RXTIER11BLOCK6PRICE = 0x84B6;
    public static final int ATTR_RXTIER11BLOCK7PRICE = 0x84B7;
    public static final int ATTR_RXTIER11BLOCK8PRICE = 0x84B8;
    public static final int ATTR_RXTIER11BLOCK9PRICE = 0x84B9;
    public static final int ATTR_RXTIER11BLOCK10PRICE = 0x84BA;
    public static final int ATTR_RXTIER11BLOCK11PRICE = 0x84BB;
    public static final int ATTR_RXTIER11BLOCK12PRICE = 0x84BC;
    public static final int ATTR_RXTIER11BLOCK13PRICE = 0x84BD;
    public static final int ATTR_RXTIER11BLOCK14PRICE = 0x84BE;
    public static final int ATTR_RXTIER11BLOCK15PRICE = 0x84BF;
    public static final int ATTR_RXTIER11BLOCK16PRICE = 0x84C0;
    public static final int ATTR_RXTIER12BLOCK1PRICE = 0x84C1;
    public static final int ATTR_RXTIER12BLOCK2PRICE = 0x84C2;
    public static final int ATTR_RXTIER12BLOCK3PRICE = 0x84C3;
    public static final int ATTR_RXTIER12BLOCK4PRICE = 0x84C4;
    public static final int ATTR_RXTIER12BLOCK5PRICE = 0x84C5;
    public static final int ATTR_RXTIER12BLOCK6PRICE = 0x84C6;
    public static final int ATTR_RXTIER12BLOCK7PRICE = 0x84C7;
    public static final int ATTR_RXTIER12BLOCK8PRICE = 0x84C8;
    public static final int ATTR_RXTIER12BLOCK9PRICE = 0x84C9;
    public static final int ATTR_RXTIER12BLOCK10PRICE = 0x84CA;
    public static final int ATTR_RXTIER12BLOCK11PRICE = 0x84CB;
    public static final int ATTR_RXTIER12BLOCK12PRICE = 0x84CC;
    public static final int ATTR_RXTIER12BLOCK13PRICE = 0x84CD;
    public static final int ATTR_RXTIER12BLOCK14PRICE = 0x84CE;
    public static final int ATTR_RXTIER12BLOCK15PRICE = 0x84CF;
    public static final int ATTR_RXTIER12BLOCK16PRICE = 0x84D0;
    public static final int ATTR_RXTIER13BLOCK1PRICE = 0x84D1;
    public static final int ATTR_RXTIER13BLOCK2PRICE = 0x84D2;
    public static final int ATTR_RXTIER13BLOCK3PRICE = 0x84D3;
    public static final int ATTR_RXTIER13BLOCK4PRICE = 0x84D4;
    public static final int ATTR_RXTIER13BLOCK5PRICE = 0x84D5;
    public static final int ATTR_RXTIER13BLOCK6PRICE = 0x84D6;
    public static final int ATTR_RXTIER13BLOCK7PRICE = 0x84D7;
    public static final int ATTR_RXTIER13BLOCK8PRICE = 0x84D8;
    public static final int ATTR_RXTIER13BLOCK9PRICE = 0x84D9;
    public static final int ATTR_RXTIER13BLOCK10PRICE = 0x84DA;
    public static final int ATTR_RXTIER13BLOCK11PRICE = 0x84DB;
    public static final int ATTR_RXTIER13BLOCK12PRICE = 0x84DC;
    public static final int ATTR_RXTIER13BLOCK13PRICE = 0x84DD;
    public static final int ATTR_RXTIER13BLOCK14PRICE = 0x84DE;
    public static final int ATTR_RXTIER13BLOCK15PRICE = 0x84DF;
    public static final int ATTR_RXTIER13BLOCK16PRICE = 0x84E0;
    public static final int ATTR_RXTIER14BLOCK1PRICE = 0x84E1;
    public static final int ATTR_RXTIER14BLOCK2PRICE = 0x84E2;
    public static final int ATTR_RXTIER14BLOCK3PRICE = 0x84E3;
    public static final int ATTR_RXTIER14BLOCK4PRICE = 0x84E4;
    public static final int ATTR_RXTIER14BLOCK5PRICE = 0x84E5;
    public static final int ATTR_RXTIER14BLOCK6PRICE = 0x84E6;
    public static final int ATTR_RXTIER14BLOCK7PRICE = 0x84E7;
    public static final int ATTR_RXTIER14BLOCK8PRICE = 0x84E8;
    public static final int ATTR_RXTIER14BLOCK9PRICE = 0x84E9;
    public static final int ATTR_RXTIER14BLOCK10PRICE = 0x84EA;
    public static final int ATTR_RXTIER14BLOCK11PRICE = 0x84EB;
    public static final int ATTR_RXTIER14BLOCK12PRICE = 0x84EC;
    public static final int ATTR_RXTIER14BLOCK13PRICE = 0x84ED;
    public static final int ATTR_RXTIER14BLOCK14PRICE = 0x84EE;
    public static final int ATTR_RXTIER14BLOCK15PRICE = 0x84EF;
    public static final int ATTR_RXTIER14BLOCK16PRICE = 0x84F0;
    public static final int ATTR_RXTIER15BLOCK1PRICE = 0x84F1;
    public static final int ATTR_RXTIER15BLOCK2PRICE = 0x84F2;
    public static final int ATTR_RXTIER15BLOCK3PRICE = 0x84F3;
    public static final int ATTR_RXTIER15BLOCK4PRICE = 0x84F4;
    public static final int ATTR_RXTIER15BLOCK5PRICE = 0x84F5;
    public static final int ATTR_RXTIER15BLOCK6PRICE = 0x84F6;
    public static final int ATTR_RXTIER15BLOCK7PRICE = 0x84F7;
    public static final int ATTR_RXTIER15BLOCK8PRICE = 0x84F8;
    public static final int ATTR_RXTIER15BLOCK9PRICE = 0x84F9;
    public static final int ATTR_RXTIER15BLOCK10PRICE = 0x84FA;
    public static final int ATTR_RXTIER15BLOCK11PRICE = 0x84FB;
    public static final int ATTR_RXTIER15BLOCK12PRICE = 0x84FC;
    public static final int ATTR_RXTIER15BLOCK13PRICE = 0x84FD;
    public static final int ATTR_RXTIER15BLOCK14PRICE = 0x84FE;
    public static final int ATTR_RXTIER15BLOCK15PRICE = 0x84FF;
    public static final int ATTR_RXTIER15BLOCK16PRICE = 0x8500;
    public static final int ATTR_RECEIVEDPRICETIER16 = 0x851F;
    public static final int ATTR_RECEIVEDPRICETIER17 = 0x8520;
    public static final int ATTR_RECEIVEDPRICETIER18 = 0x8521;
    public static final int ATTR_RECEIVEDPRICETIER19 = 0x8522;
    public static final int ATTR_RECEIVEDPRICETIER20 = 0x8523;
    public static final int ATTR_RECEIVEDPRICETIER21 = 0x8524;
    public static final int ATTR_RECEIVEDPRICETIER22 = 0x8525;
    public static final int ATTR_RECEIVEDPRICETIER23 = 0x8526;
    public static final int ATTR_RECEIVEDPRICETIER24 = 0x8527;
    public static final int ATTR_RECEIVEDPRICETIER25 = 0x8528;
    public static final int ATTR_RECEIVEDPRICETIER26 = 0x8529;
    public static final int ATTR_RECEIVEDPRICETIER27 = 0x852A;
    public static final int ATTR_RECEIVEDPRICETIER28 = 0x852B;
    public static final int ATTR_RECEIVEDPRICETIER29 = 0x852C;
    public static final int ATTR_RECEIVEDPRICETIER30 = 0x852D;
    public static final int ATTR_RECEIVEDPRICETIER31 = 0x852E;
    public static final int ATTR_RECEIVEDPRICETIER32 = 0x852F;
    public static final int ATTR_RECEIVEDPRICETIER33 = 0x8530;
    public static final int ATTR_RECEIVEDPRICETIER34 = 0x8531;
    public static final int ATTR_RECEIVEDPRICETIER35 = 0x8532;
    public static final int ATTR_RECEIVEDPRICETIER36 = 0x8533;
    public static final int ATTR_RECEIVEDPRICETIER37 = 0x8534;
    public static final int ATTR_RECEIVEDPRICETIER38 = 0x8535;
    public static final int ATTR_RECEIVEDPRICETIER39 = 0x8536;
    public static final int ATTR_RECEIVEDPRICETIER40 = 0x8537;
    public static final int ATTR_RECEIVEDPRICETIER41 = 0x8538;
    public static final int ATTR_RECEIVEDPRICETIER42 = 0x8539;
    public static final int ATTR_RECEIVEDPRICETIER43 = 0x853A;
    public static final int ATTR_RECEIVEDPRICETIER44 = 0x853B;
    public static final int ATTR_RECEIVEDPRICETIER45 = 0x853C;
    public static final int ATTR_RECEIVEDPRICETIER46 = 0x853D;
    public static final int ATTR_RECEIVEDPRICETIER47 = 0x853E;
    public static final int ATTR_RECEIVEDPRICETIER48 = 0x853F;
    public static final int ATTR_RECEIVEDPRICETIER49 = 0x8540;
    public static final int ATTR_RECEIVEDPRICETIER50 = 0x8541;
    public static final int ATTR_RECEIVEDPRICETIER51 = 0x8542;
    public static final int ATTR_RECEIVEDPRICETIER52 = 0x8543;
    public static final int ATTR_RECEIVEDPRICETIER53 = 0x8544;
    public static final int ATTR_RECEIVEDPRICETIER54 = 0x8545;
    public static final int ATTR_RECEIVEDPRICETIER55 = 0x8546;
    public static final int ATTR_RECEIVEDPRICETIER56 = 0x8547;
    public static final int ATTR_RECEIVEDPRICETIER57 = 0x8548;
    public static final int ATTR_RECEIVEDPRICETIER58 = 0x8549;
    public static final int ATTR_RECEIVEDPRICETIER59 = 0x854A;
    public static final int ATTR_RECEIVEDPRICETIER60 = 0x854B;
    public static final int ATTR_RECEIVEDPRICETIER61 = 0x854C;
    public static final int ATTR_RECEIVEDPRICETIER62 = 0x854D;
    public static final int ATTR_RECEIVEDPRICETIER63 = 0x854E;
    public static final int ATTR_RECEIVEDTARIFFLABEL = 0x8610;
    public static final int ATTR_RECEIVEDNUMBEROFPRICETIERSINUSE = 0x8611;
    public static final int ATTR_RECEIVEDNUMBEROFBLOCKTHRESHOLDSINUSE = 0x8612;
    public static final int ATTR_RECEIVEDTIERBLOCKMODE = 0x8613;
    public static final int ATTR_RECEIVEDCO2 = 0x8625;
    public static final int ATTR_RECEIVEDCO2UNIT = 0x8626;
    public static final int ATTR_RECEIVEDCO2TRAILINGDIGIT = 0x8627;
    public static final int ATTR_RECEIVEDCURRENTBILLINGPERIODSTART = 0x8700;
    public static final int ATTR_RECEIVEDCURRENTBILLINGPERIODDURATION = 0x8701;
    public static final int ATTR_RECEIVEDLASTBILLINGPERIODSTART = 0x8702;
    public static final int ATTR_RECEIVEDLASTBILLINGPERIODDURATION = 0x8703;
    public static final int ATTR_RECEIVEDLASTBILLINGPERIODCONSOLIDATEDBILL = 0x8704;

    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<>(135);

        attributeMap.put(ATTR_TIER1PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1PRICELABEL, "Tier 1 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER2PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2PRICELABEL, "Tier 2 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER3PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3PRICELABEL, "Tier 3 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER4PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4PRICELABEL, "Tier 4 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER5PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5PRICELABEL, "Tier 5 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER6PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6PRICELABEL, "Tier 6 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER7PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7PRICELABEL, "Tier 7 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER8PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8PRICELABEL, "Tier 8 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER9PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9PRICELABEL, "Tier 9 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER10PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10PRICELABEL, "Tier 10 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER11PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11PRICELABEL, "Tier 11 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER12PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12PRICELABEL, "Tier 12 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER13PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13PRICELABEL, "Tier 13 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER14PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14PRICELABEL, "Tier 14 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER15PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15PRICELABEL, "Tier 15 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER16PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER16PRICELABEL, "Tier 16 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER17PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER17PRICELABEL, "Tier 17 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER18PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER18PRICELABEL, "Tier 18 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER19PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER19PRICELABEL, "Tier 19 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER20PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER20PRICELABEL, "Tier 20 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER21PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER21PRICELABEL, "Tier 21 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER22PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER22PRICELABEL, "Tier 22 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER23PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER23PRICELABEL, "Tier 23 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER24PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER24PRICELABEL, "Tier 24 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER25PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER25PRICELABEL, "Tier 25 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER26PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER26PRICELABEL, "Tier 26 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER27PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER27PRICELABEL, "Tier 27 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER28PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER28PRICELABEL, "Tier 28 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER29PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER29PRICELABEL, "Tier 29 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER30PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER30PRICELABEL, "Tier 30 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER31PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER31PRICELABEL, "Tier 31 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER32PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER32PRICELABEL, "Tier 32 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER33PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER33PRICELABEL, "Tier 33 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER34PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER34PRICELABEL, "Tier 34 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER35PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER35PRICELABEL, "Tier 35 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER36PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER36PRICELABEL, "Tier 36 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER37PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER37PRICELABEL, "Tier 37 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER38PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER38PRICELABEL, "Tier 38 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER39PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER39PRICELABEL, "Tier 39 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER40PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER40PRICELABEL, "Tier 40 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER41PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER41PRICELABEL, "Tier 41 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER42PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER42PRICELABEL, "Tier 42 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER43PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER43PRICELABEL, "Tier 43 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER44PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER44PRICELABEL, "Tier 44 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER45PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER45PRICELABEL, "Tier 45 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER46PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER46PRICELABEL, "Tier 46 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER47PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER47PRICELABEL, "Tier 47 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_TIER48PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER48PRICELABEL, "Tier 48 Price Label", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_BLOCK1THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_BLOCK1THRESHOLD, "Block 1 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BLOCK2THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_BLOCK2THRESHOLD, "Block 2 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BLOCK3THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_BLOCK3THRESHOLD, "Block 3 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BLOCK4THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_BLOCK4THRESHOLD, "Block 4 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BLOCK5THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_BLOCK5THRESHOLD, "Block 5 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BLOCK6THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_BLOCK6THRESHOLD, "Block 6 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BLOCK7THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_BLOCK7THRESHOLD, "Block 7 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BLOCK8THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_BLOCK8THRESHOLD, "Block 8 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BLOCK9THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_BLOCK9THRESHOLD, "Block 9 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BLOCK10THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_BLOCK10THRESHOLD, "Block 10 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BLOCK11THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_BLOCK11THRESHOLD, "Block 11 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BLOCK12THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_BLOCK12THRESHOLD, "Block 12 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BLOCK13THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_BLOCK13THRESHOLD, "Block 13 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BLOCK14THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_BLOCK14THRESHOLD, "Block 14 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BLOCK15THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_BLOCK15THRESHOLD, "Block 15 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BLOCKTHRESHOLDCOUNT, new ZclAttribute(ZclClusterType.PRICE, ATTR_BLOCKTHRESHOLDCOUNT, "Block Threshold Count", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK1THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK1THRESHOLD, "Tier 1 Block 1 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK2THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK2THRESHOLD, "Tier 1 Block 2 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK3THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK3THRESHOLD, "Tier 1 Block 3 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK4THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK4THRESHOLD, "Tier 1 Block 4 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK5THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK5THRESHOLD, "Tier 1 Block 5 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK6THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK6THRESHOLD, "Tier 1 Block 6 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK7THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK7THRESHOLD, "Tier 1 Block 7 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK8THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK8THRESHOLD, "Tier 1 Block 8 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK9THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK9THRESHOLD, "Tier 1 Block 9 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK10THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK10THRESHOLD, "Tier 1 Block 10 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK11THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK11THRESHOLD, "Tier 1 Block 11 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK12THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK12THRESHOLD, "Tier 1 Block 12 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK13THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK13THRESHOLD, "Tier 1 Block 13 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK14THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK14THRESHOLD, "Tier 1 Block 14 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK15THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK15THRESHOLD, "Tier 1 Block 15 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCKTHRESHOLDCOUNT, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCKTHRESHOLDCOUNT, "Tier 1 Block Threshold Count", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK1THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK1THRESHOLD, "Tier 2 Block 1 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK2THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK2THRESHOLD, "Tier 2 Block 2 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK3THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK3THRESHOLD, "Tier 2 Block 3 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK4THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK4THRESHOLD, "Tier 2 Block 4 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK5THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK5THRESHOLD, "Tier 2 Block 5 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK6THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK6THRESHOLD, "Tier 2 Block 6 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK7THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK7THRESHOLD, "Tier 2 Block 7 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK8THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK8THRESHOLD, "Tier 2 Block 8 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK9THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK9THRESHOLD, "Tier 2 Block 9 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK10THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK10THRESHOLD, "Tier 2 Block 10 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK11THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK11THRESHOLD, "Tier 2 Block 11 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK12THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK12THRESHOLD, "Tier 2 Block 12 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK13THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK13THRESHOLD, "Tier 2 Block 13 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK14THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK14THRESHOLD, "Tier 2 Block 14 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK15THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK15THRESHOLD, "Tier 2 Block 15 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCKTHRESHOLDCOUNT, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCKTHRESHOLDCOUNT, "Tier 2 Block Threshold Count", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK1THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK1THRESHOLD, "Tier 3 Block 1 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK2THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK2THRESHOLD, "Tier 3 Block 2 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK3THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK3THRESHOLD, "Tier 3 Block 3 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK4THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK4THRESHOLD, "Tier 3 Block 4 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK5THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK5THRESHOLD, "Tier 3 Block 5 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK6THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK6THRESHOLD, "Tier 3 Block 6 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK7THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK7THRESHOLD, "Tier 3 Block 7 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK8THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK8THRESHOLD, "Tier 3 Block 8 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK9THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK9THRESHOLD, "Tier 3 Block 9 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK10THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK10THRESHOLD, "Tier 3 Block 10 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK11THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK11THRESHOLD, "Tier 3 Block 11 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK12THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK12THRESHOLD, "Tier 3 Block 12 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK13THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK13THRESHOLD, "Tier 3 Block 13 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK14THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK14THRESHOLD, "Tier 3 Block 14 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK15THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK15THRESHOLD, "Tier 3 Block 15 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCKTHRESHOLDCOUNT, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCKTHRESHOLDCOUNT, "Tier 3 Block Threshold Count", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK1THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK1THRESHOLD, "Tier 4 Block 1 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK2THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK2THRESHOLD, "Tier 4 Block 2 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK3THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK3THRESHOLD, "Tier 4 Block 3 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK4THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK4THRESHOLD, "Tier 4 Block 4 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK5THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK5THRESHOLD, "Tier 4 Block 5 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK6THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK6THRESHOLD, "Tier 4 Block 6 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK7THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK7THRESHOLD, "Tier 4 Block 7 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK8THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK8THRESHOLD, "Tier 4 Block 8 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK9THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK9THRESHOLD, "Tier 4 Block 9 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK10THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK10THRESHOLD, "Tier 4 Block 10 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK11THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK11THRESHOLD, "Tier 4 Block 11 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK12THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK12THRESHOLD, "Tier 4 Block 12 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK13THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK13THRESHOLD, "Tier 4 Block 13 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK14THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK14THRESHOLD, "Tier 4 Block 14 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK15THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK15THRESHOLD, "Tier 4 Block 15 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCKTHRESHOLDCOUNT, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCKTHRESHOLDCOUNT, "Tier 4 Block Threshold Count", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK1THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK1THRESHOLD, "Tier 5 Block 1 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK2THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK2THRESHOLD, "Tier 5 Block 2 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK3THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK3THRESHOLD, "Tier 5 Block 3 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK4THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK4THRESHOLD, "Tier 5 Block 4 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK5THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK5THRESHOLD, "Tier 5 Block 5 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK6THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK6THRESHOLD, "Tier 5 Block 6 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK7THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK7THRESHOLD, "Tier 5 Block 7 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK8THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK8THRESHOLD, "Tier 5 Block 8 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK9THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK9THRESHOLD, "Tier 5 Block 9 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK10THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK10THRESHOLD, "Tier 5 Block 10 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK11THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK11THRESHOLD, "Tier 5 Block 11 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK12THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK12THRESHOLD, "Tier 5 Block 12 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK13THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK13THRESHOLD, "Tier 5 Block 13 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK14THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK14THRESHOLD, "Tier 5 Block 14 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK15THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK15THRESHOLD, "Tier 5 Block 15 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCKTHRESHOLDCOUNT, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCKTHRESHOLDCOUNT, "Tier 5 Block Threshold Count", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK1THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK1THRESHOLD, "Tier 6 Block 1 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK2THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK2THRESHOLD, "Tier 6 Block 2 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK3THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK3THRESHOLD, "Tier 6 Block 3 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK4THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK4THRESHOLD, "Tier 6 Block 4 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK5THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK5THRESHOLD, "Tier 6 Block 5 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK6THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK6THRESHOLD, "Tier 6 Block 6 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK7THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK7THRESHOLD, "Tier 6 Block 7 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK8THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK8THRESHOLD, "Tier 6 Block 8 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK9THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK9THRESHOLD, "Tier 6 Block 9 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK10THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK10THRESHOLD, "Tier 6 Block 10 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK11THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK11THRESHOLD, "Tier 6 Block 11 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK12THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK12THRESHOLD, "Tier 6 Block 12 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK13THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK13THRESHOLD, "Tier 6 Block 13 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK14THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK14THRESHOLD, "Tier 6 Block 14 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK15THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK15THRESHOLD, "Tier 6 Block 15 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCKTHRESHOLDCOUNT, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCKTHRESHOLDCOUNT, "Tier 6 Block Threshold Count", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK1THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK1THRESHOLD, "Tier 7 Block 1 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK2THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK2THRESHOLD, "Tier 7 Block 2 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK3THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK3THRESHOLD, "Tier 7 Block 3 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK4THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK4THRESHOLD, "Tier 7 Block 4 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK5THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK5THRESHOLD, "Tier 7 Block 5 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK6THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK6THRESHOLD, "Tier 7 Block 6 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK7THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK7THRESHOLD, "Tier 7 Block 7 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK8THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK8THRESHOLD, "Tier 7 Block 8 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK9THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK9THRESHOLD, "Tier 7 Block 9 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK10THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK10THRESHOLD, "Tier 7 Block 10 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK11THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK11THRESHOLD, "Tier 7 Block 11 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK12THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK12THRESHOLD, "Tier 7 Block 12 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK13THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK13THRESHOLD, "Tier 7 Block 13 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK14THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK14THRESHOLD, "Tier 7 Block 14 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK15THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK15THRESHOLD, "Tier 7 Block 15 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCKTHRESHOLDCOUNT, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCKTHRESHOLDCOUNT, "Tier 7 Block Threshold Count", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK1THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK1THRESHOLD, "Tier 8 Block 1 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK2THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK2THRESHOLD, "Tier 8 Block 2 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK3THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK3THRESHOLD, "Tier 8 Block 3 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK4THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK4THRESHOLD, "Tier 8 Block 4 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK5THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK5THRESHOLD, "Tier 8 Block 5 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK6THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK6THRESHOLD, "Tier 8 Block 6 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK7THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK7THRESHOLD, "Tier 8 Block 7 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK8THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK8THRESHOLD, "Tier 8 Block 8 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK9THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK9THRESHOLD, "Tier 8 Block 9 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK10THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK10THRESHOLD, "Tier 8 Block 10 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK11THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK11THRESHOLD, "Tier 8 Block 11 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK12THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK12THRESHOLD, "Tier 8 Block 12 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK13THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK13THRESHOLD, "Tier 8 Block 13 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK14THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK14THRESHOLD, "Tier 8 Block 14 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK15THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK15THRESHOLD, "Tier 8 Block 15 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCKTHRESHOLDCOUNT, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCKTHRESHOLDCOUNT, "Tier 8 Block Threshold Count", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK1THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK1THRESHOLD, "Tier 9 Block 1 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK2THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK2THRESHOLD, "Tier 9 Block 2 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK3THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK3THRESHOLD, "Tier 9 Block 3 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK4THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK4THRESHOLD, "Tier 9 Block 4 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK5THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK5THRESHOLD, "Tier 9 Block 5 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK6THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK6THRESHOLD, "Tier 9 Block 6 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK7THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK7THRESHOLD, "Tier 9 Block 7 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK8THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK8THRESHOLD, "Tier 9 Block 8 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK9THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK9THRESHOLD, "Tier 9 Block 9 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK10THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK10THRESHOLD, "Tier 9 Block 10 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK11THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK11THRESHOLD, "Tier 9 Block 11 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK12THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK12THRESHOLD, "Tier 9 Block 12 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK13THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK13THRESHOLD, "Tier 9 Block 13 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK14THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK14THRESHOLD, "Tier 9 Block 14 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK15THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK15THRESHOLD, "Tier 9 Block 15 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCKTHRESHOLDCOUNT, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCKTHRESHOLDCOUNT, "Tier 9 Block Threshold Count", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK1THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK1THRESHOLD, "Tier 10 Block 1 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK2THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK2THRESHOLD, "Tier 10 Block 2 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK3THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK3THRESHOLD, "Tier 10 Block 3 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK4THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK4THRESHOLD, "Tier 10 Block 4 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK5THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK5THRESHOLD, "Tier 10 Block 5 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK6THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK6THRESHOLD, "Tier 10 Block 6 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK7THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK7THRESHOLD, "Tier 10 Block 7 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK8THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK8THRESHOLD, "Tier 10 Block 8 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK9THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK9THRESHOLD, "Tier 10 Block 9 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK10THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK10THRESHOLD, "Tier 10 Block 10 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK11THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK11THRESHOLD, "Tier 10 Block 11 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK12THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK12THRESHOLD, "Tier 10 Block 12 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK13THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK13THRESHOLD, "Tier 10 Block 13 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK14THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK14THRESHOLD, "Tier 10 Block 14 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK15THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK15THRESHOLD, "Tier 10 Block 15 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCKTHRESHOLDCOUNT, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCKTHRESHOLDCOUNT, "Tier 10 Block Threshold Count", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK1THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK1THRESHOLD, "Tier 11 Block 1 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK2THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK2THRESHOLD, "Tier 11 Block 2 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK3THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK3THRESHOLD, "Tier 11 Block 3 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK4THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK4THRESHOLD, "Tier 11 Block 4 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK5THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK5THRESHOLD, "Tier 11 Block 5 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK6THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK6THRESHOLD, "Tier 11 Block 6 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK7THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK7THRESHOLD, "Tier 11 Block 7 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK8THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK8THRESHOLD, "Tier 11 Block 8 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK9THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK9THRESHOLD, "Tier 11 Block 9 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK10THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK10THRESHOLD, "Tier 11 Block 10 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK11THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK11THRESHOLD, "Tier 11 Block 11 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK12THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK12THRESHOLD, "Tier 11 Block 12 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK13THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK13THRESHOLD, "Tier 11 Block 13 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK14THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK14THRESHOLD, "Tier 11 Block 14 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK15THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK15THRESHOLD, "Tier 11 Block 15 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCKTHRESHOLDCOUNT, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCKTHRESHOLDCOUNT, "Tier 11 Block Threshold Count", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK1THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK1THRESHOLD, "Tier 12 Block 1 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK2THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK2THRESHOLD, "Tier 12 Block 2 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK3THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK3THRESHOLD, "Tier 12 Block 3 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK4THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK4THRESHOLD, "Tier 12 Block 4 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK5THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK5THRESHOLD, "Tier 12 Block 5 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK6THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK6THRESHOLD, "Tier 12 Block 6 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK7THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK7THRESHOLD, "Tier 12 Block 7 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK8THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK8THRESHOLD, "Tier 12 Block 8 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK9THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK9THRESHOLD, "Tier 12 Block 9 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK10THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK10THRESHOLD, "Tier 12 Block 10 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK11THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK11THRESHOLD, "Tier 12 Block 11 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK12THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK12THRESHOLD, "Tier 12 Block 12 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK13THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK13THRESHOLD, "Tier 12 Block 13 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK14THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK14THRESHOLD, "Tier 12 Block 14 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK15THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK15THRESHOLD, "Tier 12 Block 15 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCKTHRESHOLDCOUNT, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCKTHRESHOLDCOUNT, "Tier 12 Block Threshold Count", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK1THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK1THRESHOLD, "Tier 13 Block 1 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK2THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK2THRESHOLD, "Tier 13 Block 2 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK3THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK3THRESHOLD, "Tier 13 Block 3 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK4THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK4THRESHOLD, "Tier 13 Block 4 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK5THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK5THRESHOLD, "Tier 13 Block 5 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK6THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK6THRESHOLD, "Tier 13 Block 6 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK7THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK7THRESHOLD, "Tier 13 Block 7 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK8THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK8THRESHOLD, "Tier 13 Block 8 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK9THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK9THRESHOLD, "Tier 13 Block 9 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK10THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK10THRESHOLD, "Tier 13 Block 10 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK11THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK11THRESHOLD, "Tier 13 Block 11 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK12THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK12THRESHOLD, "Tier 13 Block 12 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK13THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK13THRESHOLD, "Tier 13 Block 13 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK14THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK14THRESHOLD, "Tier 13 Block 14 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK15THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK15THRESHOLD, "Tier 13 Block 15 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCKTHRESHOLDCOUNT, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCKTHRESHOLDCOUNT, "Tier 13 Block Threshold Count", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK1THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK1THRESHOLD, "Tier 14 Block 1 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK2THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK2THRESHOLD, "Tier 14 Block 2 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK3THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK3THRESHOLD, "Tier 14 Block 3 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK4THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK4THRESHOLD, "Tier 14 Block 4 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK5THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK5THRESHOLD, "Tier 14 Block 5 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK6THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK6THRESHOLD, "Tier 14 Block 6 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK7THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK7THRESHOLD, "Tier 14 Block 7 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK8THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK8THRESHOLD, "Tier 14 Block 8 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK9THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK9THRESHOLD, "Tier 14 Block 9 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK10THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK10THRESHOLD, "Tier 14 Block 10 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK11THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK11THRESHOLD, "Tier 14 Block 11 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK12THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK12THRESHOLD, "Tier 14 Block 12 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK13THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK13THRESHOLD, "Tier 14 Block 13 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK14THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK14THRESHOLD, "Tier 14 Block 14 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK15THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK15THRESHOLD, "Tier 14 Block 15 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCKTHRESHOLDCOUNT, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCKTHRESHOLDCOUNT, "Tier 14 Block Threshold Count", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK1THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK1THRESHOLD, "Tier 15 Block 1 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK2THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK2THRESHOLD, "Tier 15 Block 2 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK3THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK3THRESHOLD, "Tier 15 Block 3 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK4THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK4THRESHOLD, "Tier 15 Block 4 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK5THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK5THRESHOLD, "Tier 15 Block 5 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK6THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK6THRESHOLD, "Tier 15 Block 6 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK7THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK7THRESHOLD, "Tier 15 Block 7 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK8THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK8THRESHOLD, "Tier 15 Block 8 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK9THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK9THRESHOLD, "Tier 15 Block 9 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK10THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK10THRESHOLD, "Tier 15 Block 10 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK11THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK11THRESHOLD, "Tier 15 Block 11 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK12THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK12THRESHOLD, "Tier 15 Block 12 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK13THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK13THRESHOLD, "Tier 15 Block 13 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK14THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK14THRESHOLD, "Tier 15 Block 14 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK15THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK15THRESHOLD, "Tier 15 Block 15 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCKTHRESHOLDCOUNT, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCKTHRESHOLDCOUNT, "Tier 15 Block Threshold Count", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_STARTOFBLOCKPERIOD, new ZclAttribute(ZclClusterType.PRICE, ATTR_STARTOFBLOCKPERIOD, "Start of Block Period", ZclDataType.UTCTIME, false, true, false, false));
        attributeMap.put(ATTR_BLOCKPERIODDURATION, new ZclAttribute(ZclClusterType.PRICE, ATTR_BLOCKPERIODDURATION, "Block Period Duration", ZclDataType.UNSIGNED_24_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_THRESHOLDMULTIPLIER, new ZclAttribute(ZclClusterType.PRICE, ATTR_THRESHOLDMULTIPLIER, "Threshold Multiplier", ZclDataType.UNSIGNED_24_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_THRESHOLDDIVISOR, new ZclAttribute(ZclClusterType.PRICE, ATTR_THRESHOLDDIVISOR, "Threshold Divisor", ZclDataType.UNSIGNED_24_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BLOCKPERIODDURATIONTYPE, new ZclAttribute(ZclClusterType.PRICE, ATTR_BLOCKPERIODDURATIONTYPE, "Block Period Duration Type", ZclDataType.BITMAP_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_COMMODITYTYPESERVER, new ZclAttribute(ZclClusterType.PRICE, ATTR_COMMODITYTYPESERVER, "Commodity Type Server", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_STANDINGCHARGE, new ZclAttribute(ZclClusterType.PRICE, ATTR_STANDINGCHARGE, "Standing Charge", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_CONVERSIONFACTOR, new ZclAttribute(ZclClusterType.PRICE, ATTR_CONVERSIONFACTOR, "Conversion Factor", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_CONVERSIONFACTORTRAILINGDIGIT, new ZclAttribute(ZclClusterType.PRICE, ATTR_CONVERSIONFACTORTRAILINGDIGIT, "Conversion Factor Trailing Digit", ZclDataType.BITMAP_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_CALORIFICVALUE, new ZclAttribute(ZclClusterType.PRICE, ATTR_CALORIFICVALUE, "Calorific Value", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_CALORIFICVALUEUNIT, new ZclAttribute(ZclClusterType.PRICE, ATTR_CALORIFICVALUEUNIT, "Calorific Value Unit", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_CALORIFICVALUETRAILINGDIGIT, new ZclAttribute(ZclClusterType.PRICE, ATTR_CALORIFICVALUETRAILINGDIGIT, "Calorific Value Trailing Digit", ZclDataType.BITMAP_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_NOTIERBLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_NOTIERBLOCK1PRICE, "No Tier Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_NOTIERBLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_NOTIERBLOCK2PRICE, "No Tier Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_NOTIERBLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_NOTIERBLOCK3PRICE, "No Tier Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_NOTIERBLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_NOTIERBLOCK4PRICE, "No Tier Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_NOTIERBLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_NOTIERBLOCK5PRICE, "No Tier Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_NOTIERBLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_NOTIERBLOCK6PRICE, "No Tier Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_NOTIERBLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_NOTIERBLOCK7PRICE, "No Tier Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_NOTIERBLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_NOTIERBLOCK8PRICE, "No Tier Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_NOTIERBLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_NOTIERBLOCK9PRICE, "No Tier Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_NOTIERBLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_NOTIERBLOCK10PRICE, "No Tier Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_NOTIERBLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_NOTIERBLOCK11PRICE, "No Tier Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_NOTIERBLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_NOTIERBLOCK12PRICE, "No Tier Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_NOTIERBLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_NOTIERBLOCK13PRICE, "No Tier Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_NOTIERBLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_NOTIERBLOCK14PRICE, "No Tier Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_NOTIERBLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_NOTIERBLOCK15PRICE, "No Tier Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_NOTIERBLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_NOTIERBLOCK16PRICE, "No Tier Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK1PRICE, "Tier 1 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK2PRICE, "Tier 1 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK3PRICE, "Tier 1 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK4PRICE, "Tier 1 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK5PRICE, "Tier 1 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK6PRICE, "Tier 1 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK7PRICE, "Tier 1 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK8PRICE, "Tier 1 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK9PRICE, "Tier 1 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK10PRICE, "Tier 1 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK11PRICE, "Tier 1 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK12PRICE, "Tier 1 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK13PRICE, "Tier 1 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK14PRICE, "Tier 1 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK15PRICE, "Tier 1 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER1BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER1BLOCK16PRICE, "Tier 1 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK1PRICE, "Tier 2 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK2PRICE, "Tier 2 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK3PRICE, "Tier 2 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK4PRICE, "Tier 2 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK5PRICE, "Tier 2 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK6PRICE, "Tier 2 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK7PRICE, "Tier 2 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK8PRICE, "Tier 2 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK9PRICE, "Tier 2 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK10PRICE, "Tier 2 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK11PRICE, "Tier 2 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK12PRICE, "Tier 2 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK13PRICE, "Tier 2 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK14PRICE, "Tier 2 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK15PRICE, "Tier 2 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER2BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER2BLOCK16PRICE, "Tier 2 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK1PRICE, "Tier 3 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK2PRICE, "Tier 3 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK3PRICE, "Tier 3 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK4PRICE, "Tier 3 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK5PRICE, "Tier 3 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK6PRICE, "Tier 3 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK7PRICE, "Tier 3 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK8PRICE, "Tier 3 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK9PRICE, "Tier 3 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK10PRICE, "Tier 3 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK11PRICE, "Tier 3 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK12PRICE, "Tier 3 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK13PRICE, "Tier 3 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK14PRICE, "Tier 3 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK15PRICE, "Tier 3 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER3BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER3BLOCK16PRICE, "Tier 3 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK1PRICE, "Tier 4 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK2PRICE, "Tier 4 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK3PRICE, "Tier 4 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK4PRICE, "Tier 4 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK5PRICE, "Tier 4 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK6PRICE, "Tier 4 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK7PRICE, "Tier 4 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK8PRICE, "Tier 4 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK9PRICE, "Tier 4 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK10PRICE, "Tier 4 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK11PRICE, "Tier 4 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK12PRICE, "Tier 4 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK13PRICE, "Tier 4 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK14PRICE, "Tier 4 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK15PRICE, "Tier 4 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER4BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER4BLOCK16PRICE, "Tier 4 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK1PRICE, "Tier 5 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK2PRICE, "Tier 5 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK3PRICE, "Tier 5 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK4PRICE, "Tier 5 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK5PRICE, "Tier 5 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK6PRICE, "Tier 5 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK7PRICE, "Tier 5 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK8PRICE, "Tier 5 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK9PRICE, "Tier 5 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK10PRICE, "Tier 5 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK11PRICE, "Tier 5 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK12PRICE, "Tier 5 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK13PRICE, "Tier 5 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK14PRICE, "Tier 5 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK15PRICE, "Tier 5 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER5BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER5BLOCK16PRICE, "Tier 5 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK1PRICE, "Tier 6 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK2PRICE, "Tier 6 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK3PRICE, "Tier 6 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK4PRICE, "Tier 6 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK5PRICE, "Tier 6 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK6PRICE, "Tier 6 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK7PRICE, "Tier 6 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK8PRICE, "Tier 6 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK9PRICE, "Tier 6 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK10PRICE, "Tier 6 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK11PRICE, "Tier 6 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK12PRICE, "Tier 6 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK13PRICE, "Tier 6 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK14PRICE, "Tier 6 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK15PRICE, "Tier 6 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER6BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER6BLOCK16PRICE, "Tier 6 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK1PRICE, "Tier 7 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK2PRICE, "Tier 7 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK3PRICE, "Tier 7 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK4PRICE, "Tier 7 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK5PRICE, "Tier 7 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK6PRICE, "Tier 7 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK7PRICE, "Tier 7 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK8PRICE, "Tier 7 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK9PRICE, "Tier 7 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK10PRICE, "Tier 7 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK11PRICE, "Tier 7 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK12PRICE, "Tier 7 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK13PRICE, "Tier 7 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK14PRICE, "Tier 7 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK15PRICE, "Tier 7 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER7BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER7BLOCK16PRICE, "Tier 7 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK1PRICE, "Tier 8 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK2PRICE, "Tier 8 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK3PRICE, "Tier 8 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK4PRICE, "Tier 8 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK5PRICE, "Tier 8 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK6PRICE, "Tier 8 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK7PRICE, "Tier 8 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK8PRICE, "Tier 8 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK9PRICE, "Tier 8 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK10PRICE, "Tier 8 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK11PRICE, "Tier 8 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK12PRICE, "Tier 8 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK13PRICE, "Tier 8 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK14PRICE, "Tier 8 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK15PRICE, "Tier 8 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER8BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER8BLOCK16PRICE, "Tier 8 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK1PRICE, "Tier 9 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK2PRICE, "Tier 9 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK3PRICE, "Tier 9 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK4PRICE, "Tier 9 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK5PRICE, "Tier 9 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK6PRICE, "Tier 9 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK7PRICE, "Tier 9 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK8PRICE, "Tier 9 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK9PRICE, "Tier 9 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK10PRICE, "Tier 9 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK11PRICE, "Tier 9 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK12PRICE, "Tier 9 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK13PRICE, "Tier 9 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK14PRICE, "Tier 9 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK15PRICE, "Tier 9 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER9BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER9BLOCK16PRICE, "Tier 9 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK1PRICE, "Tier 10 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK2PRICE, "Tier 10 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK3PRICE, "Tier 10 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK4PRICE, "Tier 10 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK5PRICE, "Tier 10 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK6PRICE, "Tier 10 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK7PRICE, "Tier 10 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK8PRICE, "Tier 10 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK9PRICE, "Tier 10 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK10PRICE, "Tier 10 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK11PRICE, "Tier 10 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK12PRICE, "Tier 10 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK13PRICE, "Tier 10 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK14PRICE, "Tier 10 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK15PRICE, "Tier 10 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER10BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER10BLOCK16PRICE, "Tier 10 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK1PRICE, "Tier 11 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK2PRICE, "Tier 11 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK3PRICE, "Tier 11 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK4PRICE, "Tier 11 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK5PRICE, "Tier 11 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK6PRICE, "Tier 11 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK7PRICE, "Tier 11 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK8PRICE, "Tier 11 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK9PRICE, "Tier 11 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK10PRICE, "Tier 11 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK11PRICE, "Tier 11 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK12PRICE, "Tier 11 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK13PRICE, "Tier 11 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK14PRICE, "Tier 11 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK15PRICE, "Tier 11 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER11BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER11BLOCK16PRICE, "Tier 11 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK1PRICE, "Tier 12 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK2PRICE, "Tier 12 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK3PRICE, "Tier 12 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK4PRICE, "Tier 12 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK5PRICE, "Tier 12 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK6PRICE, "Tier 12 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK7PRICE, "Tier 12 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK8PRICE, "Tier 12 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK9PRICE, "Tier 12 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK10PRICE, "Tier 12 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK11PRICE, "Tier 12 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK12PRICE, "Tier 12 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK13PRICE, "Tier 12 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK14PRICE, "Tier 12 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK15PRICE, "Tier 12 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER12BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER12BLOCK16PRICE, "Tier 12 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK1PRICE, "Tier 13 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK2PRICE, "Tier 13 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK3PRICE, "Tier 13 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK4PRICE, "Tier 13 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK5PRICE, "Tier 13 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK6PRICE, "Tier 13 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK7PRICE, "Tier 13 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK8PRICE, "Tier 13 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK9PRICE, "Tier 13 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK10PRICE, "Tier 13 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK11PRICE, "Tier 13 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK12PRICE, "Tier 13 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK13PRICE, "Tier 13 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK14PRICE, "Tier 13 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK15PRICE, "Tier 13 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER13BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER13BLOCK16PRICE, "Tier 13 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK1PRICE, "Tier 14 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK2PRICE, "Tier 14 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK3PRICE, "Tier 14 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK4PRICE, "Tier 14 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK5PRICE, "Tier 14 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK6PRICE, "Tier 14 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK7PRICE, "Tier 14 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK8PRICE, "Tier 14 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK9PRICE, "Tier 14 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK10PRICE, "Tier 14 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK11PRICE, "Tier 14 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK12PRICE, "Tier 14 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK13PRICE, "Tier 14 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK14PRICE, "Tier 14 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK15PRICE, "Tier 14 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER14BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER14BLOCK16PRICE, "Tier 14 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK1PRICE, "Tier 15 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK2PRICE, "Tier 15 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK3PRICE, "Tier 15 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK4PRICE, "Tier 15 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK5PRICE, "Tier 15 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK6PRICE, "Tier 15 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK7PRICE, "Tier 15 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK8PRICE, "Tier 15 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK9PRICE, "Tier 15 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK10PRICE, "Tier 15 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK11PRICE, "Tier 15 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK12PRICE, "Tier 15 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK13PRICE, "Tier 15 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK14PRICE, "Tier 15 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK15PRICE, "Tier 15 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIER15BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIER15BLOCK16PRICE, "Tier 15 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER16, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER16, "Price Tier 16", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER17, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER17, "Price Tier 17", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER18, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER18, "Price Tier 18", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER19, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER19, "Price Tier 19", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER20, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER20, "Price Tier 20", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER21, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER21, "Price Tier 21", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER22, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER22, "Price Tier 22", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER23, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER23, "Price Tier 23", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER24, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER24, "Price Tier 24", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER25, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER25, "Price Tier 25", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER26, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER26, "Price Tier 26", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER27, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER27, "Price Tier 27", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER28, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER28, "Price Tier 28", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER29, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER29, "Price Tier 29", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER30, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER30, "Price Tier 30", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER31, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER31, "Price Tier 31", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER32, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER32, "Price Tier 32", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER33, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER33, "Price Tier 33", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER34, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER34, "Price Tier 34", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER35, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER35, "Price Tier 35", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER36, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER36, "Price Tier 36", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER37, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER37, "Price Tier 37", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER38, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER38, "Price Tier 38", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER39, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER39, "Price Tier 39", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER40, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER40, "Price Tier 40", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER41, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER41, "Price Tier 41", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER42, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER42, "Price Tier 42", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER43, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER43, "Price Tier 43", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER44, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER44, "Price Tier 44", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER45, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER45, "Price Tier 45", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER46, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER46, "Price Tier 46", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETIER47, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETIER47, "Price Tier 47", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_CPP1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_CPP1PRICE, "Cpp 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_CPP2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_CPP2PRICE, "Cpp 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TARIFFLABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_TARIFFLABEL, "Tariff Label", ZclDataType.CHARACTER_STRING, false, true, false, false));
        attributeMap.put(ATTR_NUMBEROFPRICETIERSINUSE, new ZclAttribute(ZclClusterType.PRICE, ATTR_NUMBEROFPRICETIERSINUSE, "Numberof Price Tiers In Use", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_NUMBEROFBLOCKTHRESHOLDSINUSE, new ZclAttribute(ZclClusterType.PRICE, ATTR_NUMBEROFBLOCKTHRESHOLDSINUSE, "Numberof Block Thresholds In Use", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_TIERBLOCKMODE, new ZclAttribute(ZclClusterType.PRICE, ATTR_TIERBLOCKMODE, "Tier Block Mode", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_UNITOFMEASURE, new ZclAttribute(ZclClusterType.PRICE, ATTR_UNITOFMEASURE, "Unit Of Measure", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_CURRENCY, new ZclAttribute(ZclClusterType.PRICE, ATTR_CURRENCY, "Currency", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PRICETRAILINGDIGIT, new ZclAttribute(ZclClusterType.PRICE, ATTR_PRICETRAILINGDIGIT, "Price Trailing Digit", ZclDataType.BITMAP_16_BIT, false, true, false, false));
        attributeMap.put(ATTR_TARIFFRESOLUTIONPERIOD, new ZclAttribute(ZclClusterType.PRICE, ATTR_TARIFFRESOLUTIONPERIOD, "Tariff Resolution Period", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_CO2, new ZclAttribute(ZclClusterType.PRICE, ATTR_CO2, "CO2", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_CO2UNIT, new ZclAttribute(ZclClusterType.PRICE, ATTR_CO2UNIT, "CO2 Unit", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_CO2TRAILINGDIGIT, new ZclAttribute(ZclClusterType.PRICE, ATTR_CO2TRAILINGDIGIT, "CO2 Trailing Digit", ZclDataType.BITMAP_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_CURRENTBILLINGPERIODSTART, new ZclAttribute(ZclClusterType.PRICE, ATTR_CURRENTBILLINGPERIODSTART, "Current Billing Period Start", ZclDataType.UTCTIME, false, true, false, false));
        attributeMap.put(ATTR_CURRENTBILLINGPERIODDURATION, new ZclAttribute(ZclClusterType.PRICE, ATTR_CURRENTBILLINGPERIODDURATION, "Current Billing Period Duration", ZclDataType.UNSIGNED_24_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_LASTBILLINGPERIODSTART, new ZclAttribute(ZclClusterType.PRICE, ATTR_LASTBILLINGPERIODSTART, "Last Billing Period Start", ZclDataType.UTCTIME, false, true, false, false));
        attributeMap.put(ATTR_LASTBILLINGPERIODDURATION, new ZclAttribute(ZclClusterType.PRICE, ATTR_LASTBILLINGPERIODDURATION, "Last Billing Period Duration", ZclDataType.UNSIGNED_24_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_LASTBILLINGPERIODCONSOLIDATEDBILL, new ZclAttribute(ZclClusterType.PRICE, ATTR_LASTBILLINGPERIODCONSOLIDATEDBILL, "Last Billing Period Consolidated Bill", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_CREDITPAYMENTDUEDATE, new ZclAttribute(ZclClusterType.PRICE, ATTR_CREDITPAYMENTDUEDATE, "Credit Payment Due Date", ZclDataType.UTCTIME, false, true, false, false));
        attributeMap.put(ATTR_CREDITPAYMENTSTATUS, new ZclAttribute(ZclClusterType.PRICE, ATTR_CREDITPAYMENTSTATUS, "Credit Payment Status", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_CREDITPAYMENTOVERDUEAMOUNT, new ZclAttribute(ZclClusterType.PRICE, ATTR_CREDITPAYMENTOVERDUEAMOUNT, "Credit Payment Over Due Amount", ZclDataType.SIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PAYMENTDISCOUNT, new ZclAttribute(ZclClusterType.PRICE, ATTR_PAYMENTDISCOUNT, "Payment Discount", ZclDataType.SIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PAYMENTDISCOUNTPERIOD, new ZclAttribute(ZclClusterType.PRICE, ATTR_PAYMENTDISCOUNTPERIOD, "Payment Discount Period", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_CREDITCARDPAYMENT1, new ZclAttribute(ZclClusterType.PRICE, ATTR_CREDITCARDPAYMENT1, "Credit Card Payment 1", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_CREDITCARDPAYMENTDATE1, new ZclAttribute(ZclClusterType.PRICE, ATTR_CREDITCARDPAYMENTDATE1, "Credit Card Payment Date 1", ZclDataType.UTCTIME, false, true, false, false));
        attributeMap.put(ATTR_CREDITCARDPAYMENTREF1, new ZclAttribute(ZclClusterType.PRICE, ATTR_CREDITCARDPAYMENTREF1, "Credit Card Payment Ref 1", ZclDataType.OCTET_STRING, false, true, false, false));
        attributeMap.put(ATTR_CREDITCARDPAYMENT2, new ZclAttribute(ZclClusterType.PRICE, ATTR_CREDITCARDPAYMENT2, "Credit Card Payment 2", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_CREDITCARDPAYMENTDATE2, new ZclAttribute(ZclClusterType.PRICE, ATTR_CREDITCARDPAYMENTDATE2, "Credit Card Payment Date 2", ZclDataType.UTCTIME, false, true, false, false));
        attributeMap.put(ATTR_CREDITCARDPAYMENTREF2, new ZclAttribute(ZclClusterType.PRICE, ATTR_CREDITCARDPAYMENTREF2, "Credit Card Payment Ref 2", ZclDataType.OCTET_STRING, false, true, false, false));
        attributeMap.put(ATTR_CREDITCARDPAYMENT3, new ZclAttribute(ZclClusterType.PRICE, ATTR_CREDITCARDPAYMENT3, "Credit Card Payment 3", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_CREDITCARDPAYMENTDATE3, new ZclAttribute(ZclClusterType.PRICE, ATTR_CREDITCARDPAYMENTDATE3, "Credit Card Payment Date 3", ZclDataType.UTCTIME, false, true, false, false));
        attributeMap.put(ATTR_CREDITCARDPAYMENTREF3, new ZclAttribute(ZclClusterType.PRICE, ATTR_CREDITCARDPAYMENTREF3, "Credit Card Payment Ref 3", ZclDataType.OCTET_STRING, false, true, false, false));
        attributeMap.put(ATTR_CREDITCARDPAYMENT4, new ZclAttribute(ZclClusterType.PRICE, ATTR_CREDITCARDPAYMENT4, "Credit Card Payment 4", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_CREDITCARDPAYMENTDATE4, new ZclAttribute(ZclClusterType.PRICE, ATTR_CREDITCARDPAYMENTDATE4, "Credit Card Payment Date 4", ZclDataType.UTCTIME, false, true, false, false));
        attributeMap.put(ATTR_CREDITCARDPAYMENTREF4, new ZclAttribute(ZclClusterType.PRICE, ATTR_CREDITCARDPAYMENTREF4, "Credit Card Payment Ref 4", ZclDataType.OCTET_STRING, false, true, false, false));
        attributeMap.put(ATTR_CREDITCARDPAYMENT5, new ZclAttribute(ZclClusterType.PRICE, ATTR_CREDITCARDPAYMENT5, "Credit Card Payment 5", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_CREDITCARDPAYMENTDATE5, new ZclAttribute(ZclClusterType.PRICE, ATTR_CREDITCARDPAYMENTDATE5, "Credit Card Payment Date 5", ZclDataType.UTCTIME, false, true, false, false));
        attributeMap.put(ATTR_CREDITCARDPAYMENTREF5, new ZclAttribute(ZclClusterType.PRICE, ATTR_CREDITCARDPAYMENTREF5, "Credit Card Payment Ref 5", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER1PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER1PRICELABEL, "Received Tier 1 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER2PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER2PRICELABEL, "Received Tier 2 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER3PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER3PRICELABEL, "Received Tier 3 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER4PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER4PRICELABEL, "Received Tier 4 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER5PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER5PRICELABEL, "Received Tier 5 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER6PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER6PRICELABEL, "Received Tier 6 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER7PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER7PRICELABEL, "Received Tier 7 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER8PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER8PRICELABEL, "Received Tier 8 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER9PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER9PRICELABEL, "Received Tier 9 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER10PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER10PRICELABEL, "Received Tier 10 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER11PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER11PRICELABEL, "Received Tier 11 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER12PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER12PRICELABEL, "Received Tier 12 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER13PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER13PRICELABEL, "Received Tier 13 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER14PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER14PRICELABEL, "Received Tier 14 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER15PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER15PRICELABEL, "Received Tier 15 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER16PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER16PRICELABEL, "Received Tier 16 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER17PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER17PRICELABEL, "Received Tier 17 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER18PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER18PRICELABEL, "Received Tier 18 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER19PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER19PRICELABEL, "Received Tier 19 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER20PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER20PRICELABEL, "Received Tier 20 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER21PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER21PRICELABEL, "Received Tier 21 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER22PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER22PRICELABEL, "Received Tier 22 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER23PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER23PRICELABEL, "Received Tier 23 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER24PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER24PRICELABEL, "Received Tier 24 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER25PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER25PRICELABEL, "Received Tier 25 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER26PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER26PRICELABEL, "Received Tier 26 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER27PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER27PRICELABEL, "Received Tier 27 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER28PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER28PRICELABEL, "Received Tier 28 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER29PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER29PRICELABEL, "Received Tier 29 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER30PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER30PRICELABEL, "Received Tier 30 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER31PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER31PRICELABEL, "Received Tier 31 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER32PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER32PRICELABEL, "Received Tier 32 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER33PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER33PRICELABEL, "Received Tier 33 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER34PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER34PRICELABEL, "Received Tier 34 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER35PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER35PRICELABEL, "Received Tier 35 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER36PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER36PRICELABEL, "Received Tier 36 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER37PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER37PRICELABEL, "Received Tier 37 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER38PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER38PRICELABEL, "Received Tier 38 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER39PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER39PRICELABEL, "Received Tier 39 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER40PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER40PRICELABEL, "Received Tier 40 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER41PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER41PRICELABEL, "Received Tier 41 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER42PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER42PRICELABEL, "Received Tier 42 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER43PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER43PRICELABEL, "Received Tier 43 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER44PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER44PRICELABEL, "Received Tier 44 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER45PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER45PRICELABEL, "Received Tier 45 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER46PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER46PRICELABEL, "Received Tier 46 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER47PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER47PRICELABEL, "Received Tier 47 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDTIER48PRICELABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIER48PRICELABEL, "Received Tier 48 Price Label", ZclDataType.OCTET_STRING, false, true, true, false));
        attributeMap.put(ATTR_RECEIVEDBLOCK1THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDBLOCK1THRESHOLD, "Received Block 1 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDBLOCK2THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDBLOCK2THRESHOLD, "Received Block 2 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDBLOCK3THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDBLOCK3THRESHOLD, "Received Block 3 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDBLOCK4THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDBLOCK4THRESHOLD, "Received Block 4 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDBLOCK5THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDBLOCK5THRESHOLD, "Received Block 5 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDBLOCK6THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDBLOCK6THRESHOLD, "Received Block 6 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDBLOCK7THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDBLOCK7THRESHOLD, "Received Block 7 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDBLOCK8THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDBLOCK8THRESHOLD, "Received Block 8 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDBLOCK9THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDBLOCK9THRESHOLD, "Received Block 9 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDBLOCK10THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDBLOCK10THRESHOLD, "Received Block 10 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDBLOCK11THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDBLOCK11THRESHOLD, "Received Block 11 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDBLOCK12THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDBLOCK12THRESHOLD, "Received Block 12 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDBLOCK13THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDBLOCK13THRESHOLD, "Received Block 13 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDBLOCK14THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDBLOCK14THRESHOLD, "Received Block 14 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDBLOCK15THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDBLOCK15THRESHOLD, "Received Block 15 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDBLOCK16THRESHOLD, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDBLOCK16THRESHOLD, "Received Block 16 Threshold", ZclDataType.UNSIGNED_48_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDSTARTOFBLOCKPERIOD, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDSTARTOFBLOCKPERIOD, "Received Start Of Block Period", ZclDataType.UTCTIME, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDBLOCKPERIODDURATION, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDBLOCKPERIODDURATION, "Received Block Period Duration", ZclDataType.UNSIGNED_24_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDTHRESHOLDMULTIPLIER, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTHRESHOLDMULTIPLIER, "Received Threshold Multiplier", ZclDataType.UNSIGNED_24_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDTHRESHOLDDIVISOR, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTHRESHOLDDIVISOR, "Received Threshold Divisor", ZclDataType.UNSIGNED_24_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXNOTIERBLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXNOTIERBLOCK1PRICE, "Rx No Tier Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXNOTIERBLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXNOTIERBLOCK2PRICE, "Rx No Tier Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXNOTIERBLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXNOTIERBLOCK3PRICE, "Rx No Tier Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXNOTIERBLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXNOTIERBLOCK4PRICE, "Rx No Tier Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXNOTIERBLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXNOTIERBLOCK5PRICE, "Rx No Tier Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXNOTIERBLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXNOTIERBLOCK6PRICE, "Rx No Tier Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXNOTIERBLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXNOTIERBLOCK7PRICE, "Rx No Tier Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXNOTIERBLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXNOTIERBLOCK8PRICE, "Rx No Tier Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXNOTIERBLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXNOTIERBLOCK9PRICE, "Rx No Tier Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXNOTIERBLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXNOTIERBLOCK10PRICE, "Rx No Tier Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXNOTIERBLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXNOTIERBLOCK11PRICE, "Rx No Tier Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXNOTIERBLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXNOTIERBLOCK12PRICE, "Rx No Tier Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXNOTIERBLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXNOTIERBLOCK13PRICE, "Rx No Tier Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXNOTIERBLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXNOTIERBLOCK14PRICE, "Rx No Tier Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXNOTIERBLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXNOTIERBLOCK15PRICE, "Rx No Tier Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXNOTIERBLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXNOTIERBLOCK16PRICE, "Rx No Tier Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER1BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER1BLOCK1PRICE, "Rx Tier 1 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER1BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER1BLOCK2PRICE, "Rx Tier 1 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER1BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER1BLOCK3PRICE, "Rx Tier 1 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER1BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER1BLOCK4PRICE, "Rx Tier 1 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER1BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER1BLOCK5PRICE, "Rx Tier 1 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER1BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER1BLOCK6PRICE, "Rx Tier 1 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER1BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER1BLOCK7PRICE, "Rx Tier 1 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER1BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER1BLOCK8PRICE, "Rx Tier 1 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER1BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER1BLOCK9PRICE, "Rx Tier 1 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER1BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER1BLOCK10PRICE, "Rx Tier 1 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER1BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER1BLOCK11PRICE, "Rx Tier 1 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER1BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER1BLOCK12PRICE, "Rx Tier 1 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER1BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER1BLOCK13PRICE, "Rx Tier 1 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER1BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER1BLOCK14PRICE, "Rx Tier 1 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER1BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER1BLOCK15PRICE, "Rx Tier 1 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER1BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER1BLOCK16PRICE, "Rx Tier 1 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER2BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER2BLOCK1PRICE, "Rx Tier 2 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER2BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER2BLOCK2PRICE, "Rx Tier 2 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER2BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER2BLOCK3PRICE, "Rx Tier 2 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER2BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER2BLOCK4PRICE, "Rx Tier 2 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER2BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER2BLOCK5PRICE, "Rx Tier 2 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER2BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER2BLOCK6PRICE, "Rx Tier 2 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER2BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER2BLOCK7PRICE, "Rx Tier 2 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER2BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER2BLOCK8PRICE, "Rx Tier 2 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER2BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER2BLOCK9PRICE, "Rx Tier 2 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER2BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER2BLOCK10PRICE, "Rx Tier 2 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER2BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER2BLOCK11PRICE, "Rx Tier 2 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER2BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER2BLOCK12PRICE, "Rx Tier 2 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER2BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER2BLOCK13PRICE, "Rx Tier 2 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER2BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER2BLOCK14PRICE, "Rx Tier 2 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER2BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER2BLOCK15PRICE, "Rx Tier 2 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER2BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER2BLOCK16PRICE, "Rx Tier 2 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER3BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER3BLOCK1PRICE, "Rx Tier 3 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER3BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER3BLOCK2PRICE, "Rx Tier 3 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER3BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER3BLOCK3PRICE, "Rx Tier 3 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER3BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER3BLOCK4PRICE, "Rx Tier 3 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER3BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER3BLOCK5PRICE, "Rx Tier 3 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER3BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER3BLOCK6PRICE, "Rx Tier 3 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER3BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER3BLOCK7PRICE, "Rx Tier 3 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER3BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER3BLOCK8PRICE, "Rx Tier 3 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER3BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER3BLOCK9PRICE, "Rx Tier 3 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER3BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER3BLOCK10PRICE, "Rx Tier 3 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER3BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER3BLOCK11PRICE, "Rx Tier 3 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER3BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER3BLOCK12PRICE, "Rx Tier 3 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER3BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER3BLOCK13PRICE, "Rx Tier 3 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER3BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER3BLOCK14PRICE, "Rx Tier 3 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER3BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER3BLOCK15PRICE, "Rx Tier 3 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER3BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER3BLOCK16PRICE, "Rx Tier 3 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER4BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER4BLOCK1PRICE, "Rx Tier 4 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER4BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER4BLOCK2PRICE, "Rx Tier 4 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER4BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER4BLOCK3PRICE, "Rx Tier 4 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER4BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER4BLOCK4PRICE, "Rx Tier 4 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER4BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER4BLOCK5PRICE, "Rx Tier 4 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER4BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER4BLOCK6PRICE, "Rx Tier 4 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER4BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER4BLOCK7PRICE, "Rx Tier 4 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER4BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER4BLOCK8PRICE, "Rx Tier 4 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER4BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER4BLOCK9PRICE, "Rx Tier 4 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER4BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER4BLOCK10PRICE, "Rx Tier 4 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER4BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER4BLOCK11PRICE, "Rx Tier 4 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER4BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER4BLOCK12PRICE, "Rx Tier 4 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER4BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER4BLOCK13PRICE, "Rx Tier 4 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER4BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER4BLOCK14PRICE, "Rx Tier 4 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER4BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER4BLOCK15PRICE, "Rx Tier 4 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER4BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER4BLOCK16PRICE, "Rx Tier 4 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER5BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER5BLOCK1PRICE, "Rx Tier 5 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER5BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER5BLOCK2PRICE, "Rx Tier 5 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER5BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER5BLOCK3PRICE, "Rx Tier 5 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER5BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER5BLOCK4PRICE, "Rx Tier 5 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER5BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER5BLOCK5PRICE, "Rx Tier 5 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER5BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER5BLOCK6PRICE, "Rx Tier 5 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER5BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER5BLOCK7PRICE, "Rx Tier 5 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER5BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER5BLOCK8PRICE, "Rx Tier 5 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER5BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER5BLOCK9PRICE, "Rx Tier 5 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER5BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER5BLOCK10PRICE, "Rx Tier 5 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER5BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER5BLOCK11PRICE, "Rx Tier 5 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER5BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER5BLOCK12PRICE, "Rx Tier 5 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER5BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER5BLOCK13PRICE, "Rx Tier 5 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER5BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER5BLOCK14PRICE, "Rx Tier 5 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER5BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER5BLOCK15PRICE, "Rx Tier 5 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER5BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER5BLOCK16PRICE, "Rx Tier 5 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER6BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER6BLOCK1PRICE, "Rx Tier 6 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER6BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER6BLOCK2PRICE, "Rx Tier 6 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER6BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER6BLOCK3PRICE, "Rx Tier 6 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER6BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER6BLOCK4PRICE, "Rx Tier 6 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER6BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER6BLOCK5PRICE, "Rx Tier 6 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER6BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER6BLOCK6PRICE, "Rx Tier 6 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER6BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER6BLOCK7PRICE, "Rx Tier 6 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER6BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER6BLOCK8PRICE, "Rx Tier 6 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER6BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER6BLOCK9PRICE, "Rx Tier 6 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER6BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER6BLOCK10PRICE, "Rx Tier 6 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER6BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER6BLOCK11PRICE, "Rx Tier 6 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER6BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER6BLOCK12PRICE, "Rx Tier 6 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER6BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER6BLOCK13PRICE, "Rx Tier 6 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER6BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER6BLOCK14PRICE, "Rx Tier 6 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER6BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER6BLOCK15PRICE, "Rx Tier 6 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER6BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER6BLOCK16PRICE, "Rx Tier 6 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER7BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER7BLOCK1PRICE, "Rx Tier 7 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER7BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER7BLOCK2PRICE, "Rx Tier 7 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER7BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER7BLOCK3PRICE, "Rx Tier 7 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER7BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER7BLOCK4PRICE, "Rx Tier 7 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER7BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER7BLOCK5PRICE, "Rx Tier 7 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER7BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER7BLOCK6PRICE, "Rx Tier 7 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER7BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER7BLOCK7PRICE, "Rx Tier 7 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER7BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER7BLOCK8PRICE, "Rx Tier 7 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER7BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER7BLOCK9PRICE, "Rx Tier 7 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER7BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER7BLOCK10PRICE, "Rx Tier 7 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER7BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER7BLOCK11PRICE, "Rx Tier 7 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER7BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER7BLOCK12PRICE, "Rx Tier 7 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER7BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER7BLOCK13PRICE, "Rx Tier 7 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER7BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER7BLOCK14PRICE, "Rx Tier 7 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER7BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER7BLOCK15PRICE, "Rx Tier 7 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER7BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER7BLOCK16PRICE, "Rx Tier 7 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER8BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER8BLOCK1PRICE, "Rx Tier 8 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER8BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER8BLOCK2PRICE, "Rx Tier 8 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER8BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER8BLOCK3PRICE, "Rx Tier 8 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER8BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER8BLOCK4PRICE, "Rx Tier 8 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER8BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER8BLOCK5PRICE, "Rx Tier 8 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER8BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER8BLOCK6PRICE, "Rx Tier 8 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER8BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER8BLOCK7PRICE, "Rx Tier 8 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER8BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER8BLOCK8PRICE, "Rx Tier 8 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER8BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER8BLOCK9PRICE, "Rx Tier 8 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER8BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER8BLOCK10PRICE, "Rx Tier 8 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER8BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER8BLOCK11PRICE, "Rx Tier 8 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER8BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER8BLOCK12PRICE, "Rx Tier 8 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER8BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER8BLOCK13PRICE, "Rx Tier 8 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER8BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER8BLOCK14PRICE, "Rx Tier 8 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER8BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER8BLOCK15PRICE, "Rx Tier 8 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER8BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER8BLOCK16PRICE, "Rx Tier 8 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER9BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER9BLOCK1PRICE, "Rx Tier 9 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER9BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER9BLOCK2PRICE, "Rx Tier 9 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER9BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER9BLOCK3PRICE, "Rx Tier 9 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER9BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER9BLOCK4PRICE, "Rx Tier 9 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER9BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER9BLOCK5PRICE, "Rx Tier 9 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER9BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER9BLOCK6PRICE, "Rx Tier 9 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER9BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER9BLOCK7PRICE, "Rx Tier 9 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER9BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER9BLOCK8PRICE, "Rx Tier 9 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER9BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER9BLOCK9PRICE, "Rx Tier 9 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER9BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER9BLOCK10PRICE, "Rx Tier 9 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER9BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER9BLOCK11PRICE, "Rx Tier 9 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER9BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER9BLOCK12PRICE, "Rx Tier 9 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER9BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER9BLOCK13PRICE, "Rx Tier 9 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER9BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER9BLOCK14PRICE, "Rx Tier 9 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER9BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER9BLOCK15PRICE, "Rx Tier 9 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER9BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER9BLOCK16PRICE, "Rx Tier 9 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER10BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER10BLOCK1PRICE, "Rx Tier 10 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER10BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER10BLOCK2PRICE, "Rx Tier 10 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER10BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER10BLOCK3PRICE, "Rx Tier 10 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER10BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER10BLOCK4PRICE, "Rx Tier 10 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER10BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER10BLOCK5PRICE, "Rx Tier 10 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER10BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER10BLOCK6PRICE, "Rx Tier 10 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER10BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER10BLOCK7PRICE, "Rx Tier 10 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER10BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER10BLOCK8PRICE, "Rx Tier 10 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER10BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER10BLOCK9PRICE, "Rx Tier 10 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER10BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER10BLOCK10PRICE, "Rx Tier 10 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER10BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER10BLOCK11PRICE, "Rx Tier 10 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER10BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER10BLOCK12PRICE, "Rx Tier 10 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER10BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER10BLOCK13PRICE, "Rx Tier 10 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER10BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER10BLOCK14PRICE, "Rx Tier 10 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER10BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER10BLOCK15PRICE, "Rx Tier 10 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER10BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER10BLOCK16PRICE, "Rx Tier 10 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER11BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER11BLOCK1PRICE, "Rx Tier 11 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER11BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER11BLOCK2PRICE, "Rx Tier 11 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER11BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER11BLOCK3PRICE, "Rx Tier 11 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER11BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER11BLOCK4PRICE, "Rx Tier 11 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER11BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER11BLOCK5PRICE, "Rx Tier 11 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER11BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER11BLOCK6PRICE, "Rx Tier 11 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER11BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER11BLOCK7PRICE, "Rx Tier 11 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER11BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER11BLOCK8PRICE, "Rx Tier 11 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER11BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER11BLOCK9PRICE, "Rx Tier 11 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER11BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER11BLOCK10PRICE, "Rx Tier 11 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER11BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER11BLOCK11PRICE, "Rx Tier 11 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER11BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER11BLOCK12PRICE, "Rx Tier 11 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER11BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER11BLOCK13PRICE, "Rx Tier 11 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER11BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER11BLOCK14PRICE, "Rx Tier 11 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER11BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER11BLOCK15PRICE, "Rx Tier 11 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER11BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER11BLOCK16PRICE, "Rx Tier 11 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER12BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER12BLOCK1PRICE, "Rx Tier 12 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER12BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER12BLOCK2PRICE, "Rx Tier 12 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER12BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER12BLOCK3PRICE, "Rx Tier 12 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER12BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER12BLOCK4PRICE, "Rx Tier 12 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER12BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER12BLOCK5PRICE, "Rx Tier 12 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER12BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER12BLOCK6PRICE, "Rx Tier 12 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER12BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER12BLOCK7PRICE, "Rx Tier 12 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER12BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER12BLOCK8PRICE, "Rx Tier 12 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER12BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER12BLOCK9PRICE, "Rx Tier 12 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER12BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER12BLOCK10PRICE, "Rx Tier 12 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER12BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER12BLOCK11PRICE, "Rx Tier 12 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER12BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER12BLOCK12PRICE, "Rx Tier 12 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER12BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER12BLOCK13PRICE, "Rx Tier 12 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER12BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER12BLOCK14PRICE, "Rx Tier 12 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER12BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER12BLOCK15PRICE, "Rx Tier 12 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER12BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER12BLOCK16PRICE, "Rx Tier 12 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER13BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER13BLOCK1PRICE, "Rx Tier 13 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER13BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER13BLOCK2PRICE, "Rx Tier 13 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER13BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER13BLOCK3PRICE, "Rx Tier 13 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER13BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER13BLOCK4PRICE, "Rx Tier 13 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER13BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER13BLOCK5PRICE, "Rx Tier 13 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER13BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER13BLOCK6PRICE, "Rx Tier 13 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER13BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER13BLOCK7PRICE, "Rx Tier 13 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER13BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER13BLOCK8PRICE, "Rx Tier 13 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER13BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER13BLOCK9PRICE, "Rx Tier 13 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER13BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER13BLOCK10PRICE, "Rx Tier 13 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER13BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER13BLOCK11PRICE, "Rx Tier 13 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER13BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER13BLOCK12PRICE, "Rx Tier 13 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER13BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER13BLOCK13PRICE, "Rx Tier 13 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER13BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER13BLOCK14PRICE, "Rx Tier 13 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER13BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER13BLOCK15PRICE, "Rx Tier 13 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER13BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER13BLOCK16PRICE, "Rx Tier 13 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER14BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER14BLOCK1PRICE, "Rx Tier 14 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER14BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER14BLOCK2PRICE, "Rx Tier 14 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER14BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER14BLOCK3PRICE, "Rx Tier 14 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER14BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER14BLOCK4PRICE, "Rx Tier 14 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER14BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER14BLOCK5PRICE, "Rx Tier 14 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER14BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER14BLOCK6PRICE, "Rx Tier 14 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER14BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER14BLOCK7PRICE, "Rx Tier 14 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER14BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER14BLOCK8PRICE, "Rx Tier 14 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER14BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER14BLOCK9PRICE, "Rx Tier 14 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER14BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER14BLOCK10PRICE, "Rx Tier 14 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER14BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER14BLOCK11PRICE, "Rx Tier 14 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER14BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER14BLOCK12PRICE, "Rx Tier 14 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER14BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER14BLOCK13PRICE, "Rx Tier 14 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER14BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER14BLOCK14PRICE, "Rx Tier 14 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER14BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER14BLOCK15PRICE, "Rx Tier 14 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER14BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER14BLOCK16PRICE, "Rx Tier 14 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER15BLOCK1PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER15BLOCK1PRICE, "Rx Tier 15 Block 1 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER15BLOCK2PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER15BLOCK2PRICE, "Rx Tier 15 Block 2 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER15BLOCK3PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER15BLOCK3PRICE, "Rx Tier 15 Block 3 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER15BLOCK4PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER15BLOCK4PRICE, "Rx Tier 15 Block 4 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER15BLOCK5PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER15BLOCK5PRICE, "Rx Tier 15 Block 5 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER15BLOCK6PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER15BLOCK6PRICE, "Rx Tier 15 Block 6 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER15BLOCK7PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER15BLOCK7PRICE, "Rx Tier 15 Block 7 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER15BLOCK8PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER15BLOCK8PRICE, "Rx Tier 15 Block 8 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER15BLOCK9PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER15BLOCK9PRICE, "Rx Tier 15 Block 9 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER15BLOCK10PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER15BLOCK10PRICE, "Rx Tier 15 Block 10 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER15BLOCK11PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER15BLOCK11PRICE, "Rx Tier 15 Block 11 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER15BLOCK12PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER15BLOCK12PRICE, "Rx Tier 15 Block 12 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER15BLOCK13PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER15BLOCK13PRICE, "Rx Tier 15 Block 13 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER15BLOCK14PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER15BLOCK14PRICE, "Rx Tier 15 Block 14 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER15BLOCK15PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER15BLOCK15PRICE, "Rx Tier 15 Block 15 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RXTIER15BLOCK16PRICE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RXTIER15BLOCK16PRICE, "Rx Tier 15 Block 16 Price", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER16, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER16, "Received Price Tier 16", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER17, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER17, "Received Price Tier 17", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER18, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER18, "Received Price Tier 18", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER19, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER19, "Received Price Tier 19", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER20, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER20, "Received Price Tier 20", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER21, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER21, "Received Price Tier 21", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER22, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER22, "Received Price Tier 22", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER23, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER23, "Received Price Tier 23", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER24, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER24, "Received Price Tier 24", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER25, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER25, "Received Price Tier 25", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER26, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER26, "Received Price Tier 26", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER27, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER27, "Received Price Tier 27", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER28, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER28, "Received Price Tier 28", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER29, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER29, "Received Price Tier 29", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER30, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER30, "Received Price Tier 30", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER31, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER31, "Received Price Tier 31", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER32, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER32, "Received Price Tier 32", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER33, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER33, "Received Price Tier 33", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER34, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER34, "Received Price Tier 34", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER35, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER35, "Received Price Tier 35", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER36, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER36, "Received Price Tier 36", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER37, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER37, "Received Price Tier 37", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER38, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER38, "Received Price Tier 38", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER39, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER39, "Received Price Tier 39", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER40, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER40, "Received Price Tier 40", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER41, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER41, "Received Price Tier 41", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER42, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER42, "Received Price Tier 42", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER43, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER43, "Received Price Tier 43", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER44, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER44, "Received Price Tier 44", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER45, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER45, "Received Price Tier 45", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER46, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER46, "Received Price Tier 46", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER47, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER47, "Received Price Tier 47", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER48, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER48, "Received Price Tier 48", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER49, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER49, "Received Price Tier 49", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER50, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER50, "Received Price Tier 50", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER51, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER51, "Received Price Tier 51", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER52, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER52, "Received Price Tier 52", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER53, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER53, "Received Price Tier 53", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER54, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER54, "Received Price Tier 54", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER55, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER55, "Received Price Tier 55", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER56, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER56, "Received Price Tier 56", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER57, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER57, "Received Price Tier 57", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER58, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER58, "Received Price Tier 58", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER59, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER59, "Received Price Tier 59", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER60, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER60, "Received Price Tier 60", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER61, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER61, "Received Price Tier 61", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER62, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER62, "Received Price Tier 62", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDPRICETIER63, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDPRICETIER63, "Received Price Tier 63", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDTARIFFLABEL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTARIFFLABEL, "Received Tariff Label", ZclDataType.OCTET_STRING, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDNUMBEROFPRICETIERSINUSE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDNUMBEROFPRICETIERSINUSE, "Received Number Of Price Tiers In Use", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDNUMBEROFBLOCKTHRESHOLDSINUSE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDNUMBEROFBLOCKTHRESHOLDSINUSE, "Received Number Of Block Thresholds In Use", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDTIERBLOCKMODE, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDTIERBLOCKMODE, "Received Tier Block Mode", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDCO2, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDCO2, "Received CO2", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDCO2UNIT, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDCO2UNIT, "Received CO2 Unit", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDCO2TRAILINGDIGIT, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDCO2TRAILINGDIGIT, "Received CO2 Trailing Digit", ZclDataType.BITMAP_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDCURRENTBILLINGPERIODSTART, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDCURRENTBILLINGPERIODSTART, "Received Current Billing Period Start", ZclDataType.UTCTIME, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDCURRENTBILLINGPERIODDURATION, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDCURRENTBILLINGPERIODDURATION, "Received Current Billing Period Duration", ZclDataType.UNSIGNED_24_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDLASTBILLINGPERIODSTART, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDLASTBILLINGPERIODSTART, "Received Last Billing Period Start", ZclDataType.UTCTIME, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDLASTBILLINGPERIODDURATION, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDLASTBILLINGPERIODDURATION, "Received Last Billing Period Duration", ZclDataType.UNSIGNED_24_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_RECEIVEDLASTBILLINGPERIODCONSOLIDATEDBILL, new ZclAttribute(ZclClusterType.PRICE, ATTR_RECEIVEDLASTBILLINGPERIODCONSOLIDATEDBILL, "Received Last Billing Period Consolidated Bill", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));

        return attributeMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeServerCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentHashMap<>(15);

        commandMap.put(0x0000, PublishPriceCommand.class);
        commandMap.put(0x0001, PublishBlockPeriodCommand.class);
        commandMap.put(0x0002, PublishConversionFactorCommand.class);
        commandMap.put(0x0003, PublishCalorificValueCommand.class);
        commandMap.put(0x0004, PublishTariffInformationCommand.class);
        commandMap.put(0x0005, PublishPriceMatrixCommand.class);
        commandMap.put(0x0006, PublishBlockThresholdsCommand.class);
        commandMap.put(0x0007, PublishCo2ValueCommand.class);
        commandMap.put(0x0008, PublishTierLabelsCommand.class);
        commandMap.put(0x0009, PublishBillingPeriodCommand.class);
        commandMap.put(0x000A, PublishConsolidatedBillCommand.class);
        commandMap.put(0x000B, PublishCppEventCommand.class);
        commandMap.put(0x000C, PublishCreditPaymentCommand.class);
        commandMap.put(0x000D, PublishCurrencyConversionCommand.class);
        commandMap.put(0x000E, CancelTariffCommand.class);

        return commandMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentHashMap<>(17);

        commandMap.put(0x0000, GetCurrentPriceCommand.class);
        commandMap.put(0x0001, GetScheduledPricesCommand.class);
        commandMap.put(0x0002, PriceAcknowledgementCommand.class);
        commandMap.put(0x0003, GetBlockPeriodCommand.class);
        commandMap.put(0x0004, GetConversionFactorCommand.class);
        commandMap.put(0x0005, GetCalorificValueCommand.class);
        commandMap.put(0x0006, GetTariffInformationCommand.class);
        commandMap.put(0x0007, GetPriceMatrixCommand.class);
        commandMap.put(0x0008, GetBlockThresholdsCommand.class);
        commandMap.put(0x0009, GetCo2ValueCommand.class);
        commandMap.put(0x000A, GetTierLabelsCommand.class);
        commandMap.put(0x000B, GetBillingPeriodCommand.class);
        commandMap.put(0x000C, GetConsolidatedBillCommand.class);
        commandMap.put(0x000D, CppEventResponse.class);
        commandMap.put(0x000E, GetCreditPaymentCommand.class);
        commandMap.put(0x000F, GetCurrencyConversionCommand.class);
        commandMap.put(0x0010, GetTariffCancellationCommand.class);

        return commandMap;
    }

    /**
     * Default constructor to create a Price cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclPriceCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Set the <i>Price Increase Randomize Minutes</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The PriceIncreaseRandomizeMinutes attribute represents the maximum amount of time
     * to be used when randomizing the response to a price increase. Note that although the
     * granularity of the attribute is in minutes, it is recommended the granularity of the
     * randomization used within a responding device be in seconds or smaller. If a device
     * responds to a price increase it must choose a random amount of time, in seconds or
     * smaller, between 0 and PriceIncreaseRandomizeMinutes minutes. The device must
     * implement that random amount of time before or after the price change. How and if a device
     * will respond to a price increase is up to the manufacturer. Whether to respond before or
     * after the price increase is also up to the manufacturer.
     * <p>
     * As an example, a water heater with a PriceIncreaseRandomizeMinutes set to 6 could
     * choose to lower its set point 315 seconds (but not more than 360 seconds) before the price
     * increases.
     * <p>
     * The valid range for this attribute is 0x00 to 0x3C.
     * <p>
     * If PriceIncreaseRandomizeMinutes or PriceDecreaseRandomizeMinutes attributes
     * are not supported by the CLIENT, then it should use the default values for the attributes
     * as specified in the Price CLIENT Cluster Attribute table.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param priceIncreaseRandomizeMinutes the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPriceIncreaseRandomizeMinutes(final Integer value) {
        return write(attributes.get(ATTR_PRICEINCREASERANDOMIZEMINUTES), value);
    }

    /**
     * Get the <i>Price Increase Randomize Minutes</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The PriceIncreaseRandomizeMinutes attribute represents the maximum amount of time
     * to be used when randomizing the response to a price increase. Note that although the
     * granularity of the attribute is in minutes, it is recommended the granularity of the
     * randomization used within a responding device be in seconds or smaller. If a device
     * responds to a price increase it must choose a random amount of time, in seconds or
     * smaller, between 0 and PriceIncreaseRandomizeMinutes minutes. The device must
     * implement that random amount of time before or after the price change. How and if a device
     * will respond to a price increase is up to the manufacturer. Whether to respond before or
     * after the price increase is also up to the manufacturer.
     * <p>
     * As an example, a water heater with a PriceIncreaseRandomizeMinutes set to 6 could
     * choose to lower its set point 315 seconds (but not more than 360 seconds) before the price
     * increases.
     * <p>
     * The valid range for this attribute is 0x00 to 0x3C.
     * <p>
     * If PriceIncreaseRandomizeMinutes or PriceDecreaseRandomizeMinutes attributes
     * are not supported by the CLIENT, then it should use the default values for the attributes
     * as specified in the Price CLIENT Cluster Attribute table.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPriceIncreaseRandomizeMinutesAsync() {
        return read(attributes.get(ATTR_PRICEINCREASERANDOMIZEMINUTES));
    }

    /**
     * Synchronously get the <i>Price Increase Randomize Minutes</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The PriceIncreaseRandomizeMinutes attribute represents the maximum amount of time
     * to be used when randomizing the response to a price increase. Note that although the
     * granularity of the attribute is in minutes, it is recommended the granularity of the
     * randomization used within a responding device be in seconds or smaller. If a device
     * responds to a price increase it must choose a random amount of time, in seconds or
     * smaller, between 0 and PriceIncreaseRandomizeMinutes minutes. The device must
     * implement that random amount of time before or after the price change. How and if a device
     * will respond to a price increase is up to the manufacturer. Whether to respond before or
     * after the price increase is also up to the manufacturer.
     * <p>
     * As an example, a water heater with a PriceIncreaseRandomizeMinutes set to 6 could
     * choose to lower its set point 315 seconds (but not more than 360 seconds) before the price
     * increases.
     * <p>
     * The valid range for this attribute is 0x00 to 0x3C.
     * <p>
     * If PriceIncreaseRandomizeMinutes or PriceDecreaseRandomizeMinutes attributes
     * are not supported by the CLIENT, then it should use the default values for the attributes
     * as specified in the Price CLIENT Cluster Attribute table.
     * <p>
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
    public Integer getPriceIncreaseRandomizeMinutes(final long refreshPeriod) {
        if (attributes.get(ATTR_PRICEINCREASERANDOMIZEMINUTES).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PRICEINCREASERANDOMIZEMINUTES).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PRICEINCREASERANDOMIZEMINUTES));
    }

    /**
     * Set the <i>Price Decrease Randomize Minutes</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The PriceDecreaseRandomizeMinutes attribute represents the maximum number of
     * minutes to be used when randomizing the response to a price decrease. Note that although
     * the granularity of the attribute is in minutes, it is recommended the granularity of the
     * randomization used within a responding device be in seconds or smaller. If a device
     * responds to a price decrease it must choose a random amount of time, in seconds or
     * smaller, between 0 and PriceDecreaseRandomizeMinutes minutes and implement that
     * random amount of time before or after the price change. How and if a device will respond to
     * a price decrease is up to the manufacturer. Whether to respond before or after the price
     * increase is also up to the manufacturer.
     * <p>
     * As an example, a dishwasher with a PriceDecreaseRandomizeMinutes set to 15 could
     * choose to start its wash cycle 723 seconds (but not more than 900 seconds) after the price
     * decreases. The valid range for this attribute is 0x00 to 0x3C.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param priceDecreaseRandomizeMinutes the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPriceDecreaseRandomizeMinutes(final Integer value) {
        return write(attributes.get(ATTR_PRICEDECREASERANDOMIZEMINUTES), value);
    }

    /**
     * Get the <i>Price Decrease Randomize Minutes</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The PriceDecreaseRandomizeMinutes attribute represents the maximum number of
     * minutes to be used when randomizing the response to a price decrease. Note that although
     * the granularity of the attribute is in minutes, it is recommended the granularity of the
     * randomization used within a responding device be in seconds or smaller. If a device
     * responds to a price decrease it must choose a random amount of time, in seconds or
     * smaller, between 0 and PriceDecreaseRandomizeMinutes minutes and implement that
     * random amount of time before or after the price change. How and if a device will respond to
     * a price decrease is up to the manufacturer. Whether to respond before or after the price
     * increase is also up to the manufacturer.
     * <p>
     * As an example, a dishwasher with a PriceDecreaseRandomizeMinutes set to 15 could
     * choose to start its wash cycle 723 seconds (but not more than 900 seconds) after the price
     * decreases. The valid range for this attribute is 0x00 to 0x3C.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPriceDecreaseRandomizeMinutesAsync() {
        return read(attributes.get(ATTR_PRICEDECREASERANDOMIZEMINUTES));
    }

    /**
     * Synchronously get the <i>Price Decrease Randomize Minutes</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The PriceDecreaseRandomizeMinutes attribute represents the maximum number of
     * minutes to be used when randomizing the response to a price decrease. Note that although
     * the granularity of the attribute is in minutes, it is recommended the granularity of the
     * randomization used within a responding device be in seconds or smaller. If a device
     * responds to a price decrease it must choose a random amount of time, in seconds or
     * smaller, between 0 and PriceDecreaseRandomizeMinutes minutes and implement that
     * random amount of time before or after the price change. How and if a device will respond to
     * a price decrease is up to the manufacturer. Whether to respond before or after the price
     * increase is also up to the manufacturer.
     * <p>
     * As an example, a dishwasher with a PriceDecreaseRandomizeMinutes set to 15 could
     * choose to start its wash cycle 723 seconds (but not more than 900 seconds) after the price
     * decreases. The valid range for this attribute is 0x00 to 0x3C.
     * <p>
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
    public Integer getPriceDecreaseRandomizeMinutes(final long refreshPeriod) {
        if (attributes.get(ATTR_PRICEDECREASERANDOMIZEMINUTES).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PRICEDECREASERANDOMIZEMINUTES).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PRICEDECREASERANDOMIZEMINUTES));
    }

    /**
     * Set the <i>Commodity Type Client</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * CommodityType provides a label for identifying the type of pricing client present. The
     * attribute is an enumerated value representing the commodity. The defined values are
     * represented by the non-mirrored values (0-127) in the MeteringDeviceType attribute
     * enumerations.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param commodityTypeClient the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCommodityTypeClient(final Integer value) {
        return write(attributes.get(ATTR_COMMODITYTYPECLIENT), value);
    }

    /**
     * Get the <i>Commodity Type Client</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * CommodityType provides a label for identifying the type of pricing client present. The
     * attribute is an enumerated value representing the commodity. The defined values are
     * represented by the non-mirrored values (0-127) in the MeteringDeviceType attribute
     * enumerations.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCommodityTypeClientAsync() {
        return read(attributes.get(ATTR_COMMODITYTYPECLIENT));
    }

    /**
     * Synchronously get the <i>Commodity Type Client</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * CommodityType provides a label for identifying the type of pricing client present. The
     * attribute is an enumerated value representing the commodity. The defined values are
     * represented by the non-mirrored values (0-127) in the MeteringDeviceType attribute
     * enumerations.
     * <p>
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
    public Integer getCommodityTypeClient(final long refreshPeriod) {
        if (attributes.get(ATTR_COMMODITYTYPECLIENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_COMMODITYTYPECLIENT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_COMMODITYTYPECLIENT));
    }

    /**
     * Set the <i>Tier {{count}} Price Label</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 48)
     * @param tier{{count}}PriceLabel the {@link String} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTierPriceLabel(final int arrayOffset, final String value) {
        return write(attributes.get(ATTR_TIER1PRICELABEL + arrayOffset), value);
    }

    /**
     * Get the <i>Tier {{count}} Price Label</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 48)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTierPriceLabelAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 48) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER1PRICELABEL + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier {{count}} Price Label</i> attribute [attribute ID <b>0x0000</b>].
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 48)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link String} attribute value, or null on error
     */
    public String getTierPriceLabel(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER1PRICELABEL + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (String) attributes.get(ATTR_TIER1PRICELABEL + arrayOffset).getLastValue();
        }

        return (String) readSync(attributes.get(ATTR_TIER1PRICELABEL + arrayOffset));
    }

    /**
     * Get the <i>Block {{count}} Threshold</i> attribute [attribute ID <b>0x0100</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBlockThresholdAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 15) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Synchronously get the <i>Block {{count}} Threshold</i> attribute [attribute ID <b>0x0100</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getBlockThreshold(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_BLOCK1THRESHOLD + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BLOCK1THRESHOLD + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Get the <i>Block Threshold Count</i> attribute [attribute ID <b>0x010F</b>].
     * <p>
     * Where a single set of thresholds is used, the BlockThresholdCount attribute indicates
     * the number of applicable BlockNThresholds. Where more than one set of thresholds is
     * used, each set will be accompanied by an appropriate TierNBlockThresholdCount
     * attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBlockThresholdCountAsync() {
        return read(attributes.get(ATTR_BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Synchronously get the <i>Block Threshold Count</i> attribute [attribute ID <b>0x010F</b>].
     * <p>
     * Where a single set of thresholds is used, the BlockThresholdCount attribute indicates
     * the number of applicable BlockNThresholds. Where more than one set of thresholds is
     * used, each set will be accompanied by an appropriate TierNBlockThresholdCount
     * attribute.
     * <p>
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
    public Integer getBlockThresholdCount(final long refreshPeriod) {
        if (attributes.get(ATTR_BLOCKTHRESHOLDCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BLOCKTHRESHOLDCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Get the <i>Tier 1 Block {{count}} Threshold</i> attribute [attribute ID <b>0x0110</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier1BlockThresholdAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 15) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER1BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 1 Block {{count}} Threshold</i> attribute [attribute ID <b>0x0110</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier1BlockThreshold(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER1BLOCK1THRESHOLD + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER1BLOCK1THRESHOLD + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER1BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Get the <i>Tier 1 Block Threshold Count</i> attribute [attribute ID <b>0x011F</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier1BlockThresholdCountAsync() {
        return read(attributes.get(ATTR_TIER1BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Synchronously get the <i>Tier 1 Block Threshold Count</i> attribute [attribute ID <b>0x011F</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
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
    public Integer getTier1BlockThresholdCount(final long refreshPeriod) {
        if (attributes.get(ATTR_TIER1BLOCKTHRESHOLDCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER1BLOCKTHRESHOLDCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER1BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Get the <i>Tier 2 Block {{count}} Threshold</i> attribute [attribute ID <b>0x0120</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier2BlockThresholdAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 15) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER2BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 2 Block {{count}} Threshold</i> attribute [attribute ID <b>0x0120</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier2BlockThreshold(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER2BLOCK1THRESHOLD + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER2BLOCK1THRESHOLD + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER2BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Get the <i>Tier 2 Block Threshold Count</i> attribute [attribute ID <b>0x012F</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier2BlockThresholdCountAsync() {
        return read(attributes.get(ATTR_TIER2BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Synchronously get the <i>Tier 2 Block Threshold Count</i> attribute [attribute ID <b>0x012F</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
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
    public Integer getTier2BlockThresholdCount(final long refreshPeriod) {
        if (attributes.get(ATTR_TIER2BLOCKTHRESHOLDCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER2BLOCKTHRESHOLDCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER2BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Get the <i>Tier 3 Block {{count}} Threshold</i> attribute [attribute ID <b>0x0130</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier3BlockThresholdAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 15) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER3BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 3 Block {{count}} Threshold</i> attribute [attribute ID <b>0x0130</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier3BlockThreshold(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER3BLOCK1THRESHOLD + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER3BLOCK1THRESHOLD + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER3BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Get the <i>Tier 3 Block Threshold Count</i> attribute [attribute ID <b>0x013F</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier3BlockThresholdCountAsync() {
        return read(attributes.get(ATTR_TIER3BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Synchronously get the <i>Tier 3 Block Threshold Count</i> attribute [attribute ID <b>0x013F</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
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
    public Integer getTier3BlockThresholdCount(final long refreshPeriod) {
        if (attributes.get(ATTR_TIER3BLOCKTHRESHOLDCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER3BLOCKTHRESHOLDCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER3BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Get the <i>Tier 4 Block {{count}} Threshold</i> attribute [attribute ID <b>0x0140</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier4BlockThresholdAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 15) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER4BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 4 Block {{count}} Threshold</i> attribute [attribute ID <b>0x0140</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier4BlockThreshold(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER4BLOCK1THRESHOLD + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER4BLOCK1THRESHOLD + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER4BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Get the <i>Tier 4 Block Threshold Count</i> attribute [attribute ID <b>0x014F</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier4BlockThresholdCountAsync() {
        return read(attributes.get(ATTR_TIER4BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Synchronously get the <i>Tier 4 Block Threshold Count</i> attribute [attribute ID <b>0x014F</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
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
    public Integer getTier4BlockThresholdCount(final long refreshPeriod) {
        if (attributes.get(ATTR_TIER4BLOCKTHRESHOLDCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER4BLOCKTHRESHOLDCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER4BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Get the <i>Tier 5 Block {{count}} Threshold</i> attribute [attribute ID <b>0x0150</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier5BlockThresholdAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 15) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER5BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 5 Block {{count}} Threshold</i> attribute [attribute ID <b>0x0150</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier5BlockThreshold(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER5BLOCK1THRESHOLD + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER5BLOCK1THRESHOLD + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER5BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Get the <i>Tier 5 Block Threshold Count</i> attribute [attribute ID <b>0x015F</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier5BlockThresholdCountAsync() {
        return read(attributes.get(ATTR_TIER5BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Synchronously get the <i>Tier 5 Block Threshold Count</i> attribute [attribute ID <b>0x015F</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
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
    public Integer getTier5BlockThresholdCount(final long refreshPeriod) {
        if (attributes.get(ATTR_TIER5BLOCKTHRESHOLDCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER5BLOCKTHRESHOLDCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER5BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Get the <i>Tier 6 Block {{count}} Threshold</i> attribute [attribute ID <b>0x0160</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier6BlockThresholdAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 15) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER6BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 6 Block {{count}} Threshold</i> attribute [attribute ID <b>0x0160</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier6BlockThreshold(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER6BLOCK1THRESHOLD + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER6BLOCK1THRESHOLD + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER6BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Get the <i>Tier 6 Block Threshold Count</i> attribute [attribute ID <b>0x016F</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier6BlockThresholdCountAsync() {
        return read(attributes.get(ATTR_TIER6BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Synchronously get the <i>Tier 6 Block Threshold Count</i> attribute [attribute ID <b>0x016F</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
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
    public Integer getTier6BlockThresholdCount(final long refreshPeriod) {
        if (attributes.get(ATTR_TIER6BLOCKTHRESHOLDCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER6BLOCKTHRESHOLDCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER6BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Get the <i>Tier 7 Block {{count}} Threshold</i> attribute [attribute ID <b>0x0170</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier7BlockThresholdAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 15) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER7BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 7 Block {{count}} Threshold</i> attribute [attribute ID <b>0x0170</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier7BlockThreshold(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER7BLOCK1THRESHOLD + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER7BLOCK1THRESHOLD + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER7BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Get the <i>Tier 7 Block Threshold Count</i> attribute [attribute ID <b>0x017F</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier7BlockThresholdCountAsync() {
        return read(attributes.get(ATTR_TIER7BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Synchronously get the <i>Tier 7 Block Threshold Count</i> attribute [attribute ID <b>0x017F</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
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
    public Integer getTier7BlockThresholdCount(final long refreshPeriod) {
        if (attributes.get(ATTR_TIER7BLOCKTHRESHOLDCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER7BLOCKTHRESHOLDCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER7BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Get the <i>Tier 8 Block {{count}} Threshold</i> attribute [attribute ID <b>0x0180</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier8BlockThresholdAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 15) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER8BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 8 Block {{count}} Threshold</i> attribute [attribute ID <b>0x0180</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier8BlockThreshold(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER8BLOCK1THRESHOLD + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER8BLOCK1THRESHOLD + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER8BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Get the <i>Tier 8 Block Threshold Count</i> attribute [attribute ID <b>0x018F</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier8BlockThresholdCountAsync() {
        return read(attributes.get(ATTR_TIER8BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Synchronously get the <i>Tier 8 Block Threshold Count</i> attribute [attribute ID <b>0x018F</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
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
    public Integer getTier8BlockThresholdCount(final long refreshPeriod) {
        if (attributes.get(ATTR_TIER8BLOCKTHRESHOLDCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER8BLOCKTHRESHOLDCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER8BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Get the <i>Tier 9 Block {{count}} Threshold</i> attribute [attribute ID <b>0x0190</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier9BlockThresholdAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 15) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER9BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 9 Block {{count}} Threshold</i> attribute [attribute ID <b>0x0190</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier9BlockThreshold(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER9BLOCK1THRESHOLD + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER9BLOCK1THRESHOLD + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER9BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Get the <i>Tier 9 Block Threshold Count</i> attribute [attribute ID <b>0x019F</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier9BlockThresholdCountAsync() {
        return read(attributes.get(ATTR_TIER9BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Synchronously get the <i>Tier 9 Block Threshold Count</i> attribute [attribute ID <b>0x019F</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
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
    public Integer getTier9BlockThresholdCount(final long refreshPeriod) {
        if (attributes.get(ATTR_TIER9BLOCKTHRESHOLDCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER9BLOCKTHRESHOLDCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER9BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Get the <i>Tier 10 Block {{count}} Threshold</i> attribute [attribute ID <b>0x01A0</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier10BlockThresholdAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 15) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER10BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 10 Block {{count}} Threshold</i> attribute [attribute ID <b>0x01A0</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier10BlockThreshold(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER10BLOCK1THRESHOLD + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER10BLOCK1THRESHOLD + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER10BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Get the <i>Tier 10 Block Threshold Count</i> attribute [attribute ID <b>0x01AF</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier10BlockThresholdCountAsync() {
        return read(attributes.get(ATTR_TIER10BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Synchronously get the <i>Tier 10 Block Threshold Count</i> attribute [attribute ID <b>0x01AF</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
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
    public Integer getTier10BlockThresholdCount(final long refreshPeriod) {
        if (attributes.get(ATTR_TIER10BLOCKTHRESHOLDCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER10BLOCKTHRESHOLDCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER10BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Get the <i>Tier 11 Block {{count}} Threshold</i> attribute [attribute ID <b>0x01B0</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier11BlockThresholdAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 15) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER11BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 11 Block {{count}} Threshold</i> attribute [attribute ID <b>0x01B0</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier11BlockThreshold(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER11BLOCK1THRESHOLD + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER11BLOCK1THRESHOLD + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER11BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Get the <i>Tier 11 Block Threshold Count</i> attribute [attribute ID <b>0x01BF</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier11BlockThresholdCountAsync() {
        return read(attributes.get(ATTR_TIER11BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Synchronously get the <i>Tier 11 Block Threshold Count</i> attribute [attribute ID <b>0x01BF</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
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
    public Integer getTier11BlockThresholdCount(final long refreshPeriod) {
        if (attributes.get(ATTR_TIER11BLOCKTHRESHOLDCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER11BLOCKTHRESHOLDCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER11BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Get the <i>Tier 12 Block {{count}} Threshold</i> attribute [attribute ID <b>0x01C0</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier12BlockThresholdAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 15) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER12BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 12 Block {{count}} Threshold</i> attribute [attribute ID <b>0x01C0</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier12BlockThreshold(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER12BLOCK1THRESHOLD + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER12BLOCK1THRESHOLD + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER12BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Get the <i>Tier 12 Block Threshold Count</i> attribute [attribute ID <b>0x01CF</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier12BlockThresholdCountAsync() {
        return read(attributes.get(ATTR_TIER12BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Synchronously get the <i>Tier 12 Block Threshold Count</i> attribute [attribute ID <b>0x01CF</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
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
    public Integer getTier12BlockThresholdCount(final long refreshPeriod) {
        if (attributes.get(ATTR_TIER12BLOCKTHRESHOLDCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER12BLOCKTHRESHOLDCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER12BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Get the <i>Tier 13 Block {{count}} Threshold</i> attribute [attribute ID <b>0x01D0</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier13BlockThresholdAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 15) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER13BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 13 Block {{count}} Threshold</i> attribute [attribute ID <b>0x01D0</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier13BlockThreshold(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER13BLOCK1THRESHOLD + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER13BLOCK1THRESHOLD + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER13BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Get the <i>Tier 13 Block Threshold Count</i> attribute [attribute ID <b>0x01DF</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier13BlockThresholdCountAsync() {
        return read(attributes.get(ATTR_TIER13BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Synchronously get the <i>Tier 13 Block Threshold Count</i> attribute [attribute ID <b>0x01DF</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
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
    public Integer getTier13BlockThresholdCount(final long refreshPeriod) {
        if (attributes.get(ATTR_TIER13BLOCKTHRESHOLDCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER13BLOCKTHRESHOLDCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER13BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Get the <i>Tier 14 Block {{count}} Threshold</i> attribute [attribute ID <b>0x01E0</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier14BlockThresholdAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 15) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER14BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 14 Block {{count}} Threshold</i> attribute [attribute ID <b>0x01E0</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier14BlockThreshold(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER14BLOCK1THRESHOLD + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER14BLOCK1THRESHOLD + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER14BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Get the <i>Tier 14 Block Threshold Count</i> attribute [attribute ID <b>0x01EF</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier14BlockThresholdCountAsync() {
        return read(attributes.get(ATTR_TIER14BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Synchronously get the <i>Tier 14 Block Threshold Count</i> attribute [attribute ID <b>0x01EF</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
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
    public Integer getTier14BlockThresholdCount(final long refreshPeriod) {
        if (attributes.get(ATTR_TIER14BLOCKTHRESHOLDCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER14BLOCKTHRESHOLDCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER14BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Get the <i>Tier 15 Block {{count}} Threshold</i> attribute [attribute ID <b>0x01F0</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier15BlockThresholdAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 15) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER15BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 15 Block {{count}} Threshold</i> attribute [attribute ID <b>0x01F0</b>].
     * <p>
     * Attributes Block1Threshold through Block15Threshold represent the block threshold
     * values for a given period (typically the billing cycle). These values may be updated by
     * the utility on a seasonal or annual basis. The thresholds are established such that
     * crossing the threshold of energy consumption for the present block activates the next
     * higher block, which can affect the energy rate in a positive or negative manner. The
     * values are absolute and always increasing. The values represent the threshold at the
     * end of a block. The Unit of Measure will be based on the
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 15)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier15BlockThreshold(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER15BLOCK1THRESHOLD + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER15BLOCK1THRESHOLD + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER15BLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Get the <i>Tier 15 Block Threshold Count</i> attribute [attribute ID <b>0x01FF</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier15BlockThresholdCountAsync() {
        return read(attributes.get(ATTR_TIER15BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Synchronously get the <i>Tier 15 Block Threshold Count</i> attribute [attribute ID <b>0x01FF</b>].
     * <p>
     * The TierNBlockThresholdCount attributes hold the number of block thresholds
     * applicable to a given tier. These attributes are used in the case when a combination
     * (TOU/Hybrid) tariff has a separate set of thresholds for each TOU tier. Unused
     * TierNBlockThresholdCount attributes shall be set to zero.
     * <p>
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
    public Integer getTier15BlockThresholdCount(final long refreshPeriod) {
        if (attributes.get(ATTR_TIER15BLOCKTHRESHOLDCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER15BLOCKTHRESHOLDCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER15BLOCKTHRESHOLDCOUNT));
    }

    /**
     * Get the <i>Start of Block Period</i> attribute [attribute ID <b>0x0200</b>].
     * <p>
     * The StartofBlockPeriod attribute represents the start time of the current block
     * tariff period. A change indicates that a new Block Period is in effect.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getStartOfBlockPeriodAsync() {
        return read(attributes.get(ATTR_STARTOFBLOCKPERIOD));
    }

    /**
     * Synchronously get the <i>Start of Block Period</i> attribute [attribute ID <b>0x0200</b>].
     * <p>
     * The StartofBlockPeriod attribute represents the start time of the current block
     * tariff period. A change indicates that a new Block Period is in effect.
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getStartOfBlockPeriod(final long refreshPeriod) {
        if (attributes.get(ATTR_STARTOFBLOCKPERIOD).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_STARTOFBLOCKPERIOD).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_STARTOFBLOCKPERIOD));
    }

    /**
     * Get the <i>Block Period Duration</i> attribute [attribute ID <b>0x0201</b>].
     * <p>
     * The BlockPeriodDuration attribute represents the current block tariff period
     * duration in units defined by the BlockPeriodDurationType attribute. A change
     * indicates that only the duration of the current Block Period has been modified. A client
     * device shall expect a new Block Period following the expiration of the new duration.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBlockPeriodDurationAsync() {
        return read(attributes.get(ATTR_BLOCKPERIODDURATION));
    }

    /**
     * Synchronously get the <i>Block Period Duration</i> attribute [attribute ID <b>0x0201</b>].
     * <p>
     * The BlockPeriodDuration attribute represents the current block tariff period
     * duration in units defined by the BlockPeriodDurationType attribute. A change
     * indicates that only the duration of the current Block Period has been modified. A client
     * device shall expect a new Block Period following the expiration of the new duration.
     * <p>
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
    public Integer getBlockPeriodDuration(final long refreshPeriod) {
        if (attributes.get(ATTR_BLOCKPERIODDURATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BLOCKPERIODDURATION).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BLOCKPERIODDURATION));
    }

    /**
     * Get the <i>Threshold Multiplier</i> attribute [attribute ID <b>0x0202</b>].
     * <p>
     * ThresholdMultiplier provides a value to be multiplied against Threshold attributes.
     * If present, this attribute must be applied to all Block Threshold values to derive
     * values that can be compared against the CurrentBlockPeriodConsumptionDelivered
     * attribute within the Metering cluster. This attribute must be used in conjunction with
     * the ThresholdDivisor attribute. An attribute value of zero shall result in a unitary
     * multiplier (0x000001).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getThresholdMultiplierAsync() {
        return read(attributes.get(ATTR_THRESHOLDMULTIPLIER));
    }

    /**
     * Synchronously get the <i>Threshold Multiplier</i> attribute [attribute ID <b>0x0202</b>].
     * <p>
     * ThresholdMultiplier provides a value to be multiplied against Threshold attributes.
     * If present, this attribute must be applied to all Block Threshold values to derive
     * values that can be compared against the CurrentBlockPeriodConsumptionDelivered
     * attribute within the Metering cluster. This attribute must be used in conjunction with
     * the ThresholdDivisor attribute. An attribute value of zero shall result in a unitary
     * multiplier (0x000001).
     * <p>
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
    public Integer getThresholdMultiplier(final long refreshPeriod) {
        if (attributes.get(ATTR_THRESHOLDMULTIPLIER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_THRESHOLDMULTIPLIER).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_THRESHOLDMULTIPLIER));
    }

    /**
     * Get the <i>Threshold Divisor</i> attribute [attribute ID <b>0x0203</b>].
     * <p>
     * ThresholdDivisor provides a value to divide the result of applying the
     * ThresholdMultiplier attribute to Block Threshold values to derive values that can be
     * compared against the CurrentBlockPeriodConsumptionDelivered attribute within the
     * Metering cluster. This attribute must be used in conjunction with the
     * ThresholdMultiplier attribute. An attribute value of zero shall result in a unitary
     * divisor (0x000001).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getThresholdDivisorAsync() {
        return read(attributes.get(ATTR_THRESHOLDDIVISOR));
    }

    /**
     * Synchronously get the <i>Threshold Divisor</i> attribute [attribute ID <b>0x0203</b>].
     * <p>
     * ThresholdDivisor provides a value to divide the result of applying the
     * ThresholdMultiplier attribute to Block Threshold values to derive values that can be
     * compared against the CurrentBlockPeriodConsumptionDelivered attribute within the
     * Metering cluster. This attribute must be used in conjunction with the
     * ThresholdMultiplier attribute. An attribute value of zero shall result in a unitary
     * divisor (0x000001).
     * <p>
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
    public Integer getThresholdDivisor(final long refreshPeriod) {
        if (attributes.get(ATTR_THRESHOLDDIVISOR).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_THRESHOLDDIVISOR).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_THRESHOLDDIVISOR));
    }

    /**
     * Get the <i>Block Period Duration Type</i> attribute [attribute ID <b>0x0204</b>].
     * <p>
     * The BlockPeriodDurationType attribute indicates the timebase used for the
     * BlockPeriodDuration attribute. A default value of 0x00 (Minutes) shall be assumed if
     * this attribute is not present.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBlockPeriodDurationTypeAsync() {
        return read(attributes.get(ATTR_BLOCKPERIODDURATIONTYPE));
    }

    /**
     * Synchronously get the <i>Block Period Duration Type</i> attribute [attribute ID <b>0x0204</b>].
     * <p>
     * The BlockPeriodDurationType attribute indicates the timebase used for the
     * BlockPeriodDuration attribute. A default value of 0x00 (Minutes) shall be assumed if
     * this attribute is not present.
     * <p>
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
    public Integer getBlockPeriodDurationType(final long refreshPeriod) {
        if (attributes.get(ATTR_BLOCKPERIODDURATIONTYPE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_BLOCKPERIODDURATIONTYPE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_BLOCKPERIODDURATIONTYPE));
    }

    /**
     * Get the <i>Commodity Type Server</i> attribute [attribute ID <b>0x0300</b>].
     * <p>
     * CommodityType provides a label for identifying the type of pricing CLIENT present. The
     * attribute is an enumerated value representing the commodity.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCommodityTypeServerAsync() {
        return read(attributes.get(ATTR_COMMODITYTYPESERVER));
    }

    /**
     * Synchronously get the <i>Commodity Type Server</i> attribute [attribute ID <b>0x0300</b>].
     * <p>
     * CommodityType provides a label for identifying the type of pricing CLIENT present. The
     * attribute is an enumerated value representing the commodity.
     * <p>
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
    public Integer getCommodityTypeServer(final long refreshPeriod) {
        if (attributes.get(ATTR_COMMODITYTYPESERVER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_COMMODITYTYPESERVER).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_COMMODITYTYPESERVER));
    }

    /**
     * Get the <i>Standing Charge</i> attribute [attribute ID <b>0x0301</b>].
     * <p>
     * The value of the Standing Charge is a daily fixed charge associated with supplying the
     * commodity, measured in base unit of Currency with the decimal point located as
     * indicated by the Trailing Digits field of a Publish Price command or
     * PriceTrailingDigit attribute. A value of 0xFFFFFFFF indicates attribute not used.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getStandingChargeAsync() {
        return read(attributes.get(ATTR_STANDINGCHARGE));
    }

    /**
     * Synchronously get the <i>Standing Charge</i> attribute [attribute ID <b>0x0301</b>].
     * <p>
     * The value of the Standing Charge is a daily fixed charge associated with supplying the
     * commodity, measured in base unit of Currency with the decimal point located as
     * indicated by the Trailing Digits field of a Publish Price command or
     * PriceTrailingDigit attribute. A value of 0xFFFFFFFF indicates attribute not used.
     * <p>
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
    public Integer getStandingCharge(final long refreshPeriod) {
        if (attributes.get(ATTR_STANDINGCHARGE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_STANDINGCHARGE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_STANDINGCHARGE));
    }

    /**
     * Get the <i>Conversion Factor</i> attribute [attribute ID <b>0x0302</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getConversionFactorAsync() {
        return read(attributes.get(ATTR_CONVERSIONFACTOR));
    }

    /**
     * Synchronously get the <i>Conversion Factor</i> attribute [attribute ID <b>0x0302</b>].
     * <p>
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
    public Integer getConversionFactor(final long refreshPeriod) {
        if (attributes.get(ATTR_CONVERSIONFACTOR).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CONVERSIONFACTOR).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CONVERSIONFACTOR));
    }

    /**
     * Get the <i>Conversion Factor Trailing Digit</i> attribute [attribute ID <b>0x0303</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getConversionFactorTrailingDigitAsync() {
        return read(attributes.get(ATTR_CONVERSIONFACTORTRAILINGDIGIT));
    }

    /**
     * Synchronously get the <i>Conversion Factor Trailing Digit</i> attribute [attribute ID <b>0x0303</b>].
     * <p>
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
    public Integer getConversionFactorTrailingDigit(final long refreshPeriod) {
        if (attributes.get(ATTR_CONVERSIONFACTORTRAILINGDIGIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CONVERSIONFACTORTRAILINGDIGIT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CONVERSIONFACTORTRAILINGDIGIT));
    }

    /**
     * Get the <i>Calorific Value</i> attribute [attribute ID <b>0x0304</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCalorificValueAsync() {
        return read(attributes.get(ATTR_CALORIFICVALUE));
    }

    /**
     * Synchronously get the <i>Calorific Value</i> attribute [attribute ID <b>0x0304</b>].
     * <p>
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
    public Integer getCalorificValue(final long refreshPeriod) {
        if (attributes.get(ATTR_CALORIFICVALUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CALORIFICVALUE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CALORIFICVALUE));
    }

    /**
     * Get the <i>Calorific Value Unit</i> attribute [attribute ID <b>0x0305</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCalorificValueUnitAsync() {
        return read(attributes.get(ATTR_CALORIFICVALUEUNIT));
    }

    /**
     * Synchronously get the <i>Calorific Value Unit</i> attribute [attribute ID <b>0x0305</b>].
     * <p>
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
    public Integer getCalorificValueUnit(final long refreshPeriod) {
        if (attributes.get(ATTR_CALORIFICVALUEUNIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CALORIFICVALUEUNIT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CALORIFICVALUEUNIT));
    }

    /**
     * Get the <i>Calorific Value Trailing Digit</i> attribute [attribute ID <b>0x0306</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCalorificValueTrailingDigitAsync() {
        return read(attributes.get(ATTR_CALORIFICVALUETRAILINGDIGIT));
    }

    /**
     * Synchronously get the <i>Calorific Value Trailing Digit</i> attribute [attribute ID <b>0x0306</b>].
     * <p>
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
    public Integer getCalorificValueTrailingDigit(final long refreshPeriod) {
        if (attributes.get(ATTR_CALORIFICVALUETRAILINGDIGIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CALORIFICVALUETRAILINGDIGIT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CALORIFICVALUETRAILINGDIGIT));
    }

    /**
     * Get the <i>No Tier Block {{count}} Price</i> attribute [attribute ID <b>0x0400</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getNoTierBlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_NOTIERBLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>No Tier Block {{count}} Price</i> attribute [attribute ID <b>0x0400</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getNoTierBlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_NOTIERBLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_NOTIERBLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_NOTIERBLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Tier 1 Block {{count}} Price</i> attribute [attribute ID <b>0x0410</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier1BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER1BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 1 Block {{count}} Price</i> attribute [attribute ID <b>0x0410</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier1BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER1BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER1BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER1BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Tier 2 Block {{count}} Price</i> attribute [attribute ID <b>0x0420</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier2BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER2BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 2 Block {{count}} Price</i> attribute [attribute ID <b>0x0420</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier2BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER2BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER2BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER2BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Tier 3 Block {{count}} Price</i> attribute [attribute ID <b>0x0430</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier3BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER3BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 3 Block {{count}} Price</i> attribute [attribute ID <b>0x0430</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier3BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER3BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER3BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER3BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Tier 4 Block {{count}} Price</i> attribute [attribute ID <b>0x0440</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier4BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER4BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 4 Block {{count}} Price</i> attribute [attribute ID <b>0x0440</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier4BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER4BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER4BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER4BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Tier 5 Block {{count}} Price</i> attribute [attribute ID <b>0x0450</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier5BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER5BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 5 Block {{count}} Price</i> attribute [attribute ID <b>0x0450</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier5BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER5BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER5BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER5BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Tier 6 Block {{count}} Price</i> attribute [attribute ID <b>0x0460</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier6BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER6BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 6 Block {{count}} Price</i> attribute [attribute ID <b>0x0460</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier6BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER6BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER6BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER6BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Tier 7 Block {{count}} Price</i> attribute [attribute ID <b>0x0470</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier7BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER7BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 7 Block {{count}} Price</i> attribute [attribute ID <b>0x0470</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier7BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER7BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER7BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER7BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Tier 8 Block {{count}} Price</i> attribute [attribute ID <b>0x0480</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier8BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER8BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 8 Block {{count}} Price</i> attribute [attribute ID <b>0x0480</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier8BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER8BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER8BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER8BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Tier 9 Block {{count}} Price</i> attribute [attribute ID <b>0x0490</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier9BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER9BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 9 Block {{count}} Price</i> attribute [attribute ID <b>0x0490</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier9BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER9BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER9BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER9BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Tier 10 Block {{count}} Price</i> attribute [attribute ID <b>0x04A0</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier10BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER10BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 10 Block {{count}} Price</i> attribute [attribute ID <b>0x04A0</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier10BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER10BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER10BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER10BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Tier 11 Block {{count}} Price</i> attribute [attribute ID <b>0x04B0</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier11BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER11BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 11 Block {{count}} Price</i> attribute [attribute ID <b>0x04B0</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier11BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER11BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER11BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER11BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Tier 12 Block {{count}} Price</i> attribute [attribute ID <b>0x04C0</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier12BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER12BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 12 Block {{count}} Price</i> attribute [attribute ID <b>0x04C0</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier12BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER12BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER12BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER12BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Tier 13 Block {{count}} Price</i> attribute [attribute ID <b>0x04D0</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier13BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER13BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 13 Block {{count}} Price</i> attribute [attribute ID <b>0x04D0</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier13BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER13BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER13BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER13BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Tier 14 Block {{count}} Price</i> attribute [attribute ID <b>0x04E0</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier14BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER14BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 14 Block {{count}} Price</i> attribute [attribute ID <b>0x04E0</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier14BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER14BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER14BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER14BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Tier 15 Block {{count}} Price</i> attribute [attribute ID <b>0x04F0</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTier15BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_TIER15BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Tier 15 Block {{count}} Price</i> attribute [attribute ID <b>0x04F0</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTier15BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_TIER15BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIER15BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIER15BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Price Tier {{count}}</i> attribute [attribute ID <b>0x050F</b>].
     * <p>
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (16 < arrayOffset < 47)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPriceTierAsync(final int arrayOffset) {
        if (arrayOffset < 16 || arrayOffset > 47) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_PRICETIER16 + arrayOffset));
    }

    /**
     * Synchronously get the <i>Price Tier {{count}}</i> attribute [attribute ID <b>0x050F</b>].
     * <p>
     * Attributes PriceTier16 through PriceTier48 represent the price of Energy, Gas, or
     * Water delivered to the premises (i.e. delivered to the customer from the utility) at a
     * specific price tier.
     * <p>
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
     * @param arrayOffset attribute array offset (16 < arrayOffset < 47)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getPriceTier(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_PRICETIER16 + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PRICETIER16 + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PRICETIER16 + arrayOffset));
    }

    /**
     * Get the <i>Cpp 1 Price</i> attribute [attribute ID <b>0x05FE</b>].
     * <p>
     * Attribute CPP1 Price represents the price of Energy, Gas, or Water delivered to the
     * premises (i.e. delivered to the customer from the utility) while Critical Peak Pricing
     * ‘CPP1’ is being applied.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCpp1PriceAsync() {
        return read(attributes.get(ATTR_CPP1PRICE));
    }

    /**
     * Synchronously get the <i>Cpp 1 Price</i> attribute [attribute ID <b>0x05FE</b>].
     * <p>
     * Attribute CPP1 Price represents the price of Energy, Gas, or Water delivered to the
     * premises (i.e. delivered to the customer from the utility) while Critical Peak Pricing
     * ‘CPP1’ is being applied.
     * <p>
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
    public Integer getCpp1Price(final long refreshPeriod) {
        if (attributes.get(ATTR_CPP1PRICE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CPP1PRICE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CPP1PRICE));
    }

    /**
     * Get the <i>Cpp 2 Price</i> attribute [attribute ID <b>0x05FF</b>].
     * <p>
     * Attribute CPP2 Price represents the price of Energy, Gas, or Water delivered to the
     * premises (i.e. delivered to the customer from the utility) while Critical Peak Pricing
     * ‘CPP2’ is being applied.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCpp2PriceAsync() {
        return read(attributes.get(ATTR_CPP2PRICE));
    }

    /**
     * Synchronously get the <i>Cpp 2 Price</i> attribute [attribute ID <b>0x05FF</b>].
     * <p>
     * Attribute CPP2 Price represents the price of Energy, Gas, or Water delivered to the
     * premises (i.e. delivered to the customer from the utility) while Critical Peak Pricing
     * ‘CPP2’ is being applied.
     * <p>
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
    public Integer getCpp2Price(final long refreshPeriod) {
        if (attributes.get(ATTR_CPP2PRICE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CPP2PRICE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CPP2PRICE));
    }

    /**
     * Get the <i>Tariff Label</i> attribute [attribute ID <b>0x0610</b>].
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTariffLabelAsync() {
        return read(attributes.get(ATTR_TARIFFLABEL));
    }

    /**
     * Synchronously get the <i>Tariff Label</i> attribute [attribute ID <b>0x0610</b>].
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
    public String getTariffLabel(final long refreshPeriod) {
        if (attributes.get(ATTR_TARIFFLABEL).isLastValueCurrent(refreshPeriod)) {
            return (String) attributes.get(ATTR_TARIFFLABEL).getLastValue();
        }

        return (String) readSync(attributes.get(ATTR_TARIFFLABEL));
    }

    /**
     * Get the <i>Numberof Price Tiers In Use</i> attribute [attribute ID <b>0x0611</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getNumberofPriceTiersInUseAsync() {
        return read(attributes.get(ATTR_NUMBEROFPRICETIERSINUSE));
    }

    /**
     * Synchronously get the <i>Numberof Price Tiers In Use</i> attribute [attribute ID <b>0x0611</b>].
     * <p>
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
    public Integer getNumberofPriceTiersInUse(final long refreshPeriod) {
        if (attributes.get(ATTR_NUMBEROFPRICETIERSINUSE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_NUMBEROFPRICETIERSINUSE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_NUMBEROFPRICETIERSINUSE));
    }

    /**
     * Get the <i>Numberof Block Thresholds In Use</i> attribute [attribute ID <b>0x0612</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getNumberofBlockThresholdsInUseAsync() {
        return read(attributes.get(ATTR_NUMBEROFBLOCKTHRESHOLDSINUSE));
    }

    /**
     * Synchronously get the <i>Numberof Block Thresholds In Use</i> attribute [attribute ID <b>0x0612</b>].
     * <p>
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
    public Integer getNumberofBlockThresholdsInUse(final long refreshPeriod) {
        if (attributes.get(ATTR_NUMBEROFBLOCKTHRESHOLDSINUSE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_NUMBEROFBLOCKTHRESHOLDSINUSE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_NUMBEROFBLOCKTHRESHOLDSINUSE));
    }

    /**
     * Get the <i>Tier Block Mode</i> attribute [attribute ID <b>0x0613</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTierBlockModeAsync() {
        return read(attributes.get(ATTR_TIERBLOCKMODE));
    }

    /**
     * Synchronously get the <i>Tier Block Mode</i> attribute [attribute ID <b>0x0613</b>].
     * <p>
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
    public Integer getTierBlockMode(final long refreshPeriod) {
        if (attributes.get(ATTR_TIERBLOCKMODE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIERBLOCKMODE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIERBLOCKMODE));
    }

    /**
     * Get the <i>Unit Of Measure</i> attribute [attribute ID <b>0x0615</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getUnitOfMeasureAsync() {
        return read(attributes.get(ATTR_UNITOFMEASURE));
    }

    /**
     * Synchronously get the <i>Unit Of Measure</i> attribute [attribute ID <b>0x0615</b>].
     * <p>
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
    public Integer getUnitOfMeasure(final long refreshPeriod) {
        if (attributes.get(ATTR_UNITOFMEASURE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_UNITOFMEASURE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_UNITOFMEASURE));
    }

    /**
     * Get the <i>Currency</i> attribute [attribute ID <b>0x0616</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrencyAsync() {
        return read(attributes.get(ATTR_CURRENCY));
    }

    /**
     * Synchronously get the <i>Currency</i> attribute [attribute ID <b>0x0616</b>].
     * <p>
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
    public Integer getCurrency(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENCY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENCY).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENCY));
    }

    /**
     * Get the <i>Price Trailing Digit</i> attribute [attribute ID <b>0x0617</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPriceTrailingDigitAsync() {
        return read(attributes.get(ATTR_PRICETRAILINGDIGIT));
    }

    /**
     * Synchronously get the <i>Price Trailing Digit</i> attribute [attribute ID <b>0x0617</b>].
     * <p>
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
    public Integer getPriceTrailingDigit(final long refreshPeriod) {
        if (attributes.get(ATTR_PRICETRAILINGDIGIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PRICETRAILINGDIGIT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PRICETRAILINGDIGIT));
    }

    /**
     * Get the <i>Tariff Resolution Period</i> attribute [attribute ID <b>0x0619</b>].
     * <p>
     * An 8 bit enumeration identifying the resolution period for Block Tariff
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTariffResolutionPeriodAsync() {
        return read(attributes.get(ATTR_TARIFFRESOLUTIONPERIOD));
    }

    /**
     * Synchronously get the <i>Tariff Resolution Period</i> attribute [attribute ID <b>0x0619</b>].
     * <p>
     * An 8 bit enumeration identifying the resolution period for Block Tariff
     * <p>
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
    public Integer getTariffResolutionPeriod(final long refreshPeriod) {
        if (attributes.get(ATTR_TARIFFRESOLUTIONPERIOD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TARIFFRESOLUTIONPERIOD).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TARIFFRESOLUTIONPERIOD));
    }

    /**
     * Get the <i>CO2</i> attribute [attribute ID <b>0x0620</b>].
     * <p>
     * Used to calculate the amount of carbon dioxide (CO2) produced from energy use. Natural
     * gas has a conversion factor of about 0.185, e.g. 1,000 kWh of gas used is responsible for
     * the production of 185kg CO2 (0.185 x 1000 kWh). The CO2 attribute represents the current
     * active value
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCo2Async() {
        return read(attributes.get(ATTR_CO2));
    }

    /**
     * Synchronously get the <i>CO2</i> attribute [attribute ID <b>0x0620</b>].
     * <p>
     * Used to calculate the amount of carbon dioxide (CO2) produced from energy use. Natural
     * gas has a conversion factor of about 0.185, e.g. 1,000 kWh of gas used is responsible for
     * the production of 185kg CO2 (0.185 x 1000 kWh). The CO2 attribute represents the current
     * active value
     * <p>
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
    public Integer getCo2(final long refreshPeriod) {
        if (attributes.get(ATTR_CO2).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CO2).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CO2));
    }

    /**
     * Get the <i>CO2 Unit</i> attribute [attribute ID <b>0x0621</b>].
     * <p>
     * This attribute is an 8-bit enumeration which defines the unit for the CO2 attribute. The
     * values and descriptions for this attribute are listed in Table D-83 below. The CO2Unit
     * attribute represents the current active value.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCo2UnitAsync() {
        return read(attributes.get(ATTR_CO2UNIT));
    }

    /**
     * Synchronously get the <i>CO2 Unit</i> attribute [attribute ID <b>0x0621</b>].
     * <p>
     * This attribute is an 8-bit enumeration which defines the unit for the CO2 attribute. The
     * values and descriptions for this attribute are listed in Table D-83 below. The CO2Unit
     * attribute represents the current active value.
     * <p>
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
    public Integer getCo2Unit(final long refreshPeriod) {
        if (attributes.get(ATTR_CO2UNIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CO2UNIT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CO2UNIT));
    }

    /**
     * Get the <i>CO2 Trailing Digit</i> attribute [attribute ID <b>0x0622</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCo2TrailingDigitAsync() {
        return read(attributes.get(ATTR_CO2TRAILINGDIGIT));
    }

    /**
     * Synchronously get the <i>CO2 Trailing Digit</i> attribute [attribute ID <b>0x0622</b>].
     * <p>
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
    public Integer getCo2TrailingDigit(final long refreshPeriod) {
        if (attributes.get(ATTR_CO2TRAILINGDIGIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CO2TRAILINGDIGIT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CO2TRAILINGDIGIT));
    }

    /**
     * Get the <i>Current Billing Period Start</i> attribute [attribute ID <b>0x0700</b>].
     * <p>
     * The CurrentBillingPeriodStart attribute represents the start time of the current
     * billing period.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentBillingPeriodStartAsync() {
        return read(attributes.get(ATTR_CURRENTBILLINGPERIODSTART));
    }

    /**
     * Synchronously get the <i>Current Billing Period Start</i> attribute [attribute ID <b>0x0700</b>].
     * <p>
     * The CurrentBillingPeriodStart attribute represents the start time of the current
     * billing period.
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getCurrentBillingPeriodStart(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENTBILLINGPERIODSTART).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_CURRENTBILLINGPERIODSTART).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_CURRENTBILLINGPERIODSTART));
    }

    /**
     * Get the <i>Current Billing Period Duration</i> attribute [attribute ID <b>0x0701</b>].
     * <p>
     * The CurrentBillingPeriodDuration attribute represents the current billing period
     * duration in minutes.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentBillingPeriodDurationAsync() {
        return read(attributes.get(ATTR_CURRENTBILLINGPERIODDURATION));
    }

    /**
     * Synchronously get the <i>Current Billing Period Duration</i> attribute [attribute ID <b>0x0701</b>].
     * <p>
     * The CurrentBillingPeriodDuration attribute represents the current billing period
     * duration in minutes.
     * <p>
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
    public Integer getCurrentBillingPeriodDuration(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENTBILLINGPERIODDURATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENTBILLINGPERIODDURATION).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTBILLINGPERIODDURATION));
    }

    /**
     * Get the <i>Last Billing Period Start</i> attribute [attribute ID <b>0x0702</b>].
     * <p>
     * The LastBillingPeriodStart attribute represents the start time of the last billing
     * period.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLastBillingPeriodStartAsync() {
        return read(attributes.get(ATTR_LASTBILLINGPERIODSTART));
    }

    /**
     * Synchronously get the <i>Last Billing Period Start</i> attribute [attribute ID <b>0x0702</b>].
     * <p>
     * The LastBillingPeriodStart attribute represents the start time of the last billing
     * period.
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getLastBillingPeriodStart(final long refreshPeriod) {
        if (attributes.get(ATTR_LASTBILLINGPERIODSTART).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_LASTBILLINGPERIODSTART).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_LASTBILLINGPERIODSTART));
    }

    /**
     * Get the <i>Last Billing Period Duration</i> attribute [attribute ID <b>0x0703</b>].
     * <p>
     * The LastBillingPeriodDuration attribute is the duration of the last billing period in
     * minutes (start to end of last billing period).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLastBillingPeriodDurationAsync() {
        return read(attributes.get(ATTR_LASTBILLINGPERIODDURATION));
    }

    /**
     * Synchronously get the <i>Last Billing Period Duration</i> attribute [attribute ID <b>0x0703</b>].
     * <p>
     * The LastBillingPeriodDuration attribute is the duration of the last billing period in
     * minutes (start to end of last billing period).
     * <p>
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
    public Integer getLastBillingPeriodDuration(final long refreshPeriod) {
        if (attributes.get(ATTR_LASTBILLINGPERIODDURATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_LASTBILLINGPERIODDURATION).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_LASTBILLINGPERIODDURATION));
    }

    /**
     * Get the <i>Last Billing Period Consolidated Bill</i> attribute [attribute ID <b>0x0704</b>].
     * <p>
     * The LastBillingPeriodConsolidatedBill attribute is an amount for the cost of the
     * energy supplied from the date of the LastBillingPeriodStart attribute and until the
     * duration of the LastBillingPeriodDuration attribute expires, measured in base unit
     * of Currency with the decimal point located as indicated by the Trailing Digits
     * attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLastBillingPeriodConsolidatedBillAsync() {
        return read(attributes.get(ATTR_LASTBILLINGPERIODCONSOLIDATEDBILL));
    }

    /**
     * Synchronously get the <i>Last Billing Period Consolidated Bill</i> attribute [attribute ID <b>0x0704</b>].
     * <p>
     * The LastBillingPeriodConsolidatedBill attribute is an amount for the cost of the
     * energy supplied from the date of the LastBillingPeriodStart attribute and until the
     * duration of the LastBillingPeriodDuration attribute expires, measured in base unit
     * of Currency with the decimal point located as indicated by the Trailing Digits
     * attribute.
     * <p>
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
    public Integer getLastBillingPeriodConsolidatedBill(final long refreshPeriod) {
        if (attributes.get(ATTR_LASTBILLINGPERIODCONSOLIDATEDBILL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_LASTBILLINGPERIODCONSOLIDATEDBILL).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_LASTBILLINGPERIODCONSOLIDATEDBILL));
    }

    /**
     * Get the <i>Credit Payment Due Date</i> attribute [attribute ID <b>0x0800</b>].
     * <p>
     * The CreditPaymentDueDate attribute indicates the date and time when the next credit
     * payment is due to be paid by the consumer to the supplier.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditPaymentDueDateAsync() {
        return read(attributes.get(ATTR_CREDITPAYMENTDUEDATE));
    }

    /**
     * Synchronously get the <i>Credit Payment Due Date</i> attribute [attribute ID <b>0x0800</b>].
     * <p>
     * The CreditPaymentDueDate attribute indicates the date and time when the next credit
     * payment is due to be paid by the consumer to the supplier.
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getCreditPaymentDueDate(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITPAYMENTDUEDATE).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_CREDITPAYMENTDUEDATE).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_CREDITPAYMENTDUEDATE));
    }

    /**
     * Get the <i>Credit Payment Status</i> attribute [attribute ID <b>0x0801</b>].
     * <p>
     * The CreditPaymentStatus attribute indicates the current status of the last payment.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditPaymentStatusAsync() {
        return read(attributes.get(ATTR_CREDITPAYMENTSTATUS));
    }

    /**
     * Synchronously get the <i>Credit Payment Status</i> attribute [attribute ID <b>0x0801</b>].
     * <p>
     * The CreditPaymentStatus attribute indicates the current status of the last payment.
     * <p>
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
    public Integer getCreditPaymentStatus(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITPAYMENTSTATUS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CREDITPAYMENTSTATUS).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CREDITPAYMENTSTATUS));
    }

    /**
     * Get the <i>Credit Payment Over Due Amount</i> attribute [attribute ID <b>0x0802</b>].
     * <p>
     * This is the total of the consolidated bill amounts accumulated since the last payment.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditPaymentOverDueAmountAsync() {
        return read(attributes.get(ATTR_CREDITPAYMENTOVERDUEAMOUNT));
    }

    /**
     * Synchronously get the <i>Credit Payment Over Due Amount</i> attribute [attribute ID <b>0x0802</b>].
     * <p>
     * This is the total of the consolidated bill amounts accumulated since the last payment.
     * <p>
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
    public Integer getCreditPaymentOverDueAmount(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITPAYMENTOVERDUEAMOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CREDITPAYMENTOVERDUEAMOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CREDITPAYMENTOVERDUEAMOUNT));
    }

    /**
     * Get the <i>Payment Discount</i> attribute [attribute ID <b>0x080A</b>].
     * <p>
     * The PaymentDiscount attribute indicates the discount that the energy supplier has
     * applied to the consolidated bill.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPaymentDiscountAsync() {
        return read(attributes.get(ATTR_PAYMENTDISCOUNT));
    }

    /**
     * Synchronously get the <i>Payment Discount</i> attribute [attribute ID <b>0x080A</b>].
     * <p>
     * The PaymentDiscount attribute indicates the discount that the energy supplier has
     * applied to the consolidated bill.
     * <p>
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
    public Integer getPaymentDiscount(final long refreshPeriod) {
        if (attributes.get(ATTR_PAYMENTDISCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PAYMENTDISCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PAYMENTDISCOUNT));
    }

    /**
     * Get the <i>Payment Discount Period</i> attribute [attribute ID <b>0x080B</b>].
     * <p>
     * The PaymentDiscountPeriod attribute indicates the period for which this discount
     * shall be applied for.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPaymentDiscountPeriodAsync() {
        return read(attributes.get(ATTR_PAYMENTDISCOUNTPERIOD));
    }

    /**
     * Synchronously get the <i>Payment Discount Period</i> attribute [attribute ID <b>0x080B</b>].
     * <p>
     * The PaymentDiscountPeriod attribute indicates the period for which this discount
     * shall be applied for.
     * <p>
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
    public Integer getPaymentDiscountPeriod(final long refreshPeriod) {
        if (attributes.get(ATTR_PAYMENTDISCOUNTPERIOD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PAYMENTDISCOUNTPERIOD).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PAYMENTDISCOUNTPERIOD));
    }

    /**
     * Get the <i>Credit Card Payment 1</i> attribute [attribute ID <b>0x0810</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditCardPayment1Async() {
        return read(attributes.get(ATTR_CREDITCARDPAYMENT1));
    }

    /**
     * Synchronously get the <i>Credit Card Payment 1</i> attribute [attribute ID <b>0x0810</b>].
     * <p>
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
    public Integer getCreditCardPayment1(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITCARDPAYMENT1).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CREDITCARDPAYMENT1).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CREDITCARDPAYMENT1));
    }

    /**
     * Get the <i>Credit Card Payment Date 1</i> attribute [attribute ID <b>0x0811</b>].
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditCardPaymentDate1Async() {
        return read(attributes.get(ATTR_CREDITCARDPAYMENTDATE1));
    }

    /**
     * Synchronously get the <i>Credit Card Payment Date 1</i> attribute [attribute ID <b>0x0811</b>].
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getCreditCardPaymentDate1(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITCARDPAYMENTDATE1).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_CREDITCARDPAYMENTDATE1).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_CREDITCARDPAYMENTDATE1));
    }

    /**
     * Get the <i>Credit Card Payment Ref 1</i> attribute [attribute ID <b>0x0812</b>].
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditCardPaymentRef1Async() {
        return read(attributes.get(ATTR_CREDITCARDPAYMENTREF1));
    }

    /**
     * Synchronously get the <i>Credit Card Payment Ref 1</i> attribute [attribute ID <b>0x0812</b>].
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
    public ByteArray getCreditCardPaymentRef1(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITCARDPAYMENTREF1).isLastValueCurrent(refreshPeriod)) {
            return (ByteArray) attributes.get(ATTR_CREDITCARDPAYMENTREF1).getLastValue();
        }

        return (ByteArray) readSync(attributes.get(ATTR_CREDITCARDPAYMENTREF1));
    }

    /**
     * Get the <i>Credit Card Payment 2</i> attribute [attribute ID <b>0x0820</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditCardPayment2Async() {
        return read(attributes.get(ATTR_CREDITCARDPAYMENT2));
    }

    /**
     * Synchronously get the <i>Credit Card Payment 2</i> attribute [attribute ID <b>0x0820</b>].
     * <p>
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
    public Integer getCreditCardPayment2(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITCARDPAYMENT2).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CREDITCARDPAYMENT2).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CREDITCARDPAYMENT2));
    }

    /**
     * Get the <i>Credit Card Payment Date 2</i> attribute [attribute ID <b>0x0821</b>].
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditCardPaymentDate2Async() {
        return read(attributes.get(ATTR_CREDITCARDPAYMENTDATE2));
    }

    /**
     * Synchronously get the <i>Credit Card Payment Date 2</i> attribute [attribute ID <b>0x0821</b>].
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getCreditCardPaymentDate2(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITCARDPAYMENTDATE2).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_CREDITCARDPAYMENTDATE2).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_CREDITCARDPAYMENTDATE2));
    }

    /**
     * Get the <i>Credit Card Payment Ref 2</i> attribute [attribute ID <b>0x0822</b>].
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditCardPaymentRef2Async() {
        return read(attributes.get(ATTR_CREDITCARDPAYMENTREF2));
    }

    /**
     * Synchronously get the <i>Credit Card Payment Ref 2</i> attribute [attribute ID <b>0x0822</b>].
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
    public ByteArray getCreditCardPaymentRef2(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITCARDPAYMENTREF2).isLastValueCurrent(refreshPeriod)) {
            return (ByteArray) attributes.get(ATTR_CREDITCARDPAYMENTREF2).getLastValue();
        }

        return (ByteArray) readSync(attributes.get(ATTR_CREDITCARDPAYMENTREF2));
    }

    /**
     * Get the <i>Credit Card Payment 3</i> attribute [attribute ID <b>0x0830</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditCardPayment3Async() {
        return read(attributes.get(ATTR_CREDITCARDPAYMENT3));
    }

    /**
     * Synchronously get the <i>Credit Card Payment 3</i> attribute [attribute ID <b>0x0830</b>].
     * <p>
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
    public Integer getCreditCardPayment3(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITCARDPAYMENT3).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CREDITCARDPAYMENT3).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CREDITCARDPAYMENT3));
    }

    /**
     * Get the <i>Credit Card Payment Date 3</i> attribute [attribute ID <b>0x0831</b>].
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditCardPaymentDate3Async() {
        return read(attributes.get(ATTR_CREDITCARDPAYMENTDATE3));
    }

    /**
     * Synchronously get the <i>Credit Card Payment Date 3</i> attribute [attribute ID <b>0x0831</b>].
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getCreditCardPaymentDate3(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITCARDPAYMENTDATE3).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_CREDITCARDPAYMENTDATE3).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_CREDITCARDPAYMENTDATE3));
    }

    /**
     * Get the <i>Credit Card Payment Ref 3</i> attribute [attribute ID <b>0x0832</b>].
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditCardPaymentRef3Async() {
        return read(attributes.get(ATTR_CREDITCARDPAYMENTREF3));
    }

    /**
     * Synchronously get the <i>Credit Card Payment Ref 3</i> attribute [attribute ID <b>0x0832</b>].
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
    public ByteArray getCreditCardPaymentRef3(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITCARDPAYMENTREF3).isLastValueCurrent(refreshPeriod)) {
            return (ByteArray) attributes.get(ATTR_CREDITCARDPAYMENTREF3).getLastValue();
        }

        return (ByteArray) readSync(attributes.get(ATTR_CREDITCARDPAYMENTREF3));
    }

    /**
     * Get the <i>Credit Card Payment 4</i> attribute [attribute ID <b>0x0840</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditCardPayment4Async() {
        return read(attributes.get(ATTR_CREDITCARDPAYMENT4));
    }

    /**
     * Synchronously get the <i>Credit Card Payment 4</i> attribute [attribute ID <b>0x0840</b>].
     * <p>
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
    public Integer getCreditCardPayment4(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITCARDPAYMENT4).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CREDITCARDPAYMENT4).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CREDITCARDPAYMENT4));
    }

    /**
     * Get the <i>Credit Card Payment Date 4</i> attribute [attribute ID <b>0x0841</b>].
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditCardPaymentDate4Async() {
        return read(attributes.get(ATTR_CREDITCARDPAYMENTDATE4));
    }

    /**
     * Synchronously get the <i>Credit Card Payment Date 4</i> attribute [attribute ID <b>0x0841</b>].
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getCreditCardPaymentDate4(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITCARDPAYMENTDATE4).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_CREDITCARDPAYMENTDATE4).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_CREDITCARDPAYMENTDATE4));
    }

    /**
     * Get the <i>Credit Card Payment Ref 4</i> attribute [attribute ID <b>0x0842</b>].
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditCardPaymentRef4Async() {
        return read(attributes.get(ATTR_CREDITCARDPAYMENTREF4));
    }

    /**
     * Synchronously get the <i>Credit Card Payment Ref 4</i> attribute [attribute ID <b>0x0842</b>].
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
    public ByteArray getCreditCardPaymentRef4(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITCARDPAYMENTREF4).isLastValueCurrent(refreshPeriod)) {
            return (ByteArray) attributes.get(ATTR_CREDITCARDPAYMENTREF4).getLastValue();
        }

        return (ByteArray) readSync(attributes.get(ATTR_CREDITCARDPAYMENTREF4));
    }

    /**
     * Get the <i>Credit Card Payment 5</i> attribute [attribute ID <b>0x0850</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditCardPayment5Async() {
        return read(attributes.get(ATTR_CREDITCARDPAYMENT5));
    }

    /**
     * Synchronously get the <i>Credit Card Payment 5</i> attribute [attribute ID <b>0x0850</b>].
     * <p>
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
    public Integer getCreditCardPayment5(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITCARDPAYMENT5).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CREDITCARDPAYMENT5).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CREDITCARDPAYMENT5));
    }

    /**
     * Get the <i>Credit Card Payment Date 5</i> attribute [attribute ID <b>0x0851</b>].
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditCardPaymentDate5Async() {
        return read(attributes.get(ATTR_CREDITCARDPAYMENTDATE5));
    }

    /**
     * Synchronously get the <i>Credit Card Payment Date 5</i> attribute [attribute ID <b>0x0851</b>].
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getCreditCardPaymentDate5(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITCARDPAYMENTDATE5).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_CREDITCARDPAYMENTDATE5).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_CREDITCARDPAYMENTDATE5));
    }

    /**
     * Set the <i>Credit Card Payment Ref 5</i> attribute [attribute ID <b>0x0852</b>].
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param creditCardPaymentRef5 the {@link ByteArray} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCreditCardPaymentRef5(final ByteArray value) {
        return write(attributes.get(ATTR_CREDITCARDPAYMENTREF5), value);
    }

    /**
     * Get the <i>Credit Card Payment Ref 5</i> attribute [attribute ID <b>0x0852</b>].
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditCardPaymentRef5Async() {
        return read(attributes.get(ATTR_CREDITCARDPAYMENTREF5));
    }

    /**
     * Synchronously get the <i>Credit Card Payment Ref 5</i> attribute [attribute ID <b>0x0852</b>].
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
    public ByteArray getCreditCardPaymentRef5(final long refreshPeriod) {
        if (attributes.get(ATTR_CREDITCARDPAYMENTREF5).isLastValueCurrent(refreshPeriod)) {
            return (ByteArray) attributes.get(ATTR_CREDITCARDPAYMENTREF5).getLastValue();
        }

        return (ByteArray) readSync(attributes.get(ATTR_CREDITCARDPAYMENTREF5));
    }

    /**
     * Set the <i>Received Tier {{count}} Price Label</i> attribute [attribute ID <b>0x8000</b>].
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 48)
     * @param receivedTier{{count}}PriceLabel the {@link ByteArray} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setReceivedTierPriceLabel(final int arrayOffset, final ByteArray value) {
        return write(attributes.get(ATTR_RECEIVEDTIER1PRICELABEL + arrayOffset), value);
    }

    /**
     * Get the <i>Received Tier {{count}} Price Label</i> attribute [attribute ID <b>0x8000</b>].
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 48)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReceivedTierPriceLabelAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 48) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_RECEIVEDTIER1PRICELABEL + arrayOffset));
    }

    /**
     * Synchronously get the <i>Received Tier {{count}} Price Label</i> attribute [attribute ID <b>0x8000</b>].
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 48)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link ByteArray} attribute value, or null on error
     */
    public ByteArray getReceivedTierPriceLabel(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_RECEIVEDTIER1PRICELABEL + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (ByteArray) attributes.get(ATTR_RECEIVEDTIER1PRICELABEL + arrayOffset).getLastValue();
        }

        return (ByteArray) readSync(attributes.get(ATTR_RECEIVEDTIER1PRICELABEL + arrayOffset));
    }

    /**
     * Get the <i>Received Block {{count}} Threshold</i> attribute [attribute ID <b>0x8100</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReceivedBlockThresholdAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_RECEIVEDBLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Synchronously get the <i>Received Block {{count}} Threshold</i> attribute [attribute ID <b>0x8100</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getReceivedBlockThreshold(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_RECEIVEDBLOCK1THRESHOLD + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RECEIVEDBLOCK1THRESHOLD + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RECEIVEDBLOCK1THRESHOLD + arrayOffset));
    }

    /**
     * Get the <i>Received Start Of Block Period</i> attribute [attribute ID <b>0x8200</b>].
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReceivedStartOfBlockPeriodAsync() {
        return read(attributes.get(ATTR_RECEIVEDSTARTOFBLOCKPERIOD));
    }

    /**
     * Synchronously get the <i>Received Start Of Block Period</i> attribute [attribute ID <b>0x8200</b>].
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getReceivedStartOfBlockPeriod(final long refreshPeriod) {
        if (attributes.get(ATTR_RECEIVEDSTARTOFBLOCKPERIOD).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_RECEIVEDSTARTOFBLOCKPERIOD).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_RECEIVEDSTARTOFBLOCKPERIOD));
    }

    /**
     * Get the <i>Received Block Period Duration</i> attribute [attribute ID <b>0x8201</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReceivedBlockPeriodDurationAsync() {
        return read(attributes.get(ATTR_RECEIVEDBLOCKPERIODDURATION));
    }

    /**
     * Synchronously get the <i>Received Block Period Duration</i> attribute [attribute ID <b>0x8201</b>].
     * <p>
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
    public Integer getReceivedBlockPeriodDuration(final long refreshPeriod) {
        if (attributes.get(ATTR_RECEIVEDBLOCKPERIODDURATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RECEIVEDBLOCKPERIODDURATION).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RECEIVEDBLOCKPERIODDURATION));
    }

    /**
     * Get the <i>Received Threshold Multiplier</i> attribute [attribute ID <b>0x8202</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReceivedThresholdMultiplierAsync() {
        return read(attributes.get(ATTR_RECEIVEDTHRESHOLDMULTIPLIER));
    }

    /**
     * Synchronously get the <i>Received Threshold Multiplier</i> attribute [attribute ID <b>0x8202</b>].
     * <p>
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
    public Integer getReceivedThresholdMultiplier(final long refreshPeriod) {
        if (attributes.get(ATTR_RECEIVEDTHRESHOLDMULTIPLIER).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RECEIVEDTHRESHOLDMULTIPLIER).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RECEIVEDTHRESHOLDMULTIPLIER));
    }

    /**
     * Get the <i>Received Threshold Divisor</i> attribute [attribute ID <b>0x8203</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReceivedThresholdDivisorAsync() {
        return read(attributes.get(ATTR_RECEIVEDTHRESHOLDDIVISOR));
    }

    /**
     * Synchronously get the <i>Received Threshold Divisor</i> attribute [attribute ID <b>0x8203</b>].
     * <p>
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
    public Integer getReceivedThresholdDivisor(final long refreshPeriod) {
        if (attributes.get(ATTR_RECEIVEDTHRESHOLDDIVISOR).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RECEIVEDTHRESHOLDDIVISOR).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RECEIVEDTHRESHOLDDIVISOR));
    }

    /**
     * Get the <i>Rx No Tier Block {{count}} Price</i> attribute [attribute ID <b>0x8400</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRxNoTierBlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_RXNOTIERBLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Rx No Tier Block {{count}} Price</i> attribute [attribute ID <b>0x8400</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getRxNoTierBlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_RXNOTIERBLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RXNOTIERBLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RXNOTIERBLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Rx Tier 1 Block {{count}} Price</i> attribute [attribute ID <b>0x8410</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRxTier1BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_RXTIER1BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Rx Tier 1 Block {{count}} Price</i> attribute [attribute ID <b>0x8410</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getRxTier1BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_RXTIER1BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RXTIER1BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RXTIER1BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Rx Tier 2 Block {{count}} Price</i> attribute [attribute ID <b>0x8420</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRxTier2BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_RXTIER2BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Rx Tier 2 Block {{count}} Price</i> attribute [attribute ID <b>0x8420</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getRxTier2BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_RXTIER2BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RXTIER2BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RXTIER2BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Rx Tier 3 Block {{count}} Price</i> attribute [attribute ID <b>0x8430</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRxTier3BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_RXTIER3BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Rx Tier 3 Block {{count}} Price</i> attribute [attribute ID <b>0x8430</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getRxTier3BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_RXTIER3BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RXTIER3BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RXTIER3BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Rx Tier 4 Block {{count}} Price</i> attribute [attribute ID <b>0x8440</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRxTier4BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_RXTIER4BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Rx Tier 4 Block {{count}} Price</i> attribute [attribute ID <b>0x8440</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getRxTier4BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_RXTIER4BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RXTIER4BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RXTIER4BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Rx Tier 5 Block {{count}} Price</i> attribute [attribute ID <b>0x8450</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRxTier5BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_RXTIER5BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Rx Tier 5 Block {{count}} Price</i> attribute [attribute ID <b>0x8450</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getRxTier5BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_RXTIER5BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RXTIER5BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RXTIER5BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Rx Tier 6 Block {{count}} Price</i> attribute [attribute ID <b>0x8460</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRxTier6BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_RXTIER6BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Rx Tier 6 Block {{count}} Price</i> attribute [attribute ID <b>0x8460</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getRxTier6BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_RXTIER6BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RXTIER6BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RXTIER6BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Rx Tier 7 Block {{count}} Price</i> attribute [attribute ID <b>0x8470</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRxTier7BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_RXTIER7BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Rx Tier 7 Block {{count}} Price</i> attribute [attribute ID <b>0x8470</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getRxTier7BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_RXTIER7BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RXTIER7BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RXTIER7BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Rx Tier 8 Block {{count}} Price</i> attribute [attribute ID <b>0x8480</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRxTier8BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_RXTIER8BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Rx Tier 8 Block {{count}} Price</i> attribute [attribute ID <b>0x8480</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getRxTier8BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_RXTIER8BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RXTIER8BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RXTIER8BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Rx Tier 9 Block {{count}} Price</i> attribute [attribute ID <b>0x8490</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRxTier9BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_RXTIER9BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Rx Tier 9 Block {{count}} Price</i> attribute [attribute ID <b>0x8490</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getRxTier9BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_RXTIER9BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RXTIER9BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RXTIER9BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Rx Tier 10 Block {{count}} Price</i> attribute [attribute ID <b>0x84A0</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRxTier10BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_RXTIER10BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Rx Tier 10 Block {{count}} Price</i> attribute [attribute ID <b>0x84A0</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getRxTier10BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_RXTIER10BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RXTIER10BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RXTIER10BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Rx Tier 11 Block {{count}} Price</i> attribute [attribute ID <b>0x84B0</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRxTier11BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_RXTIER11BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Rx Tier 11 Block {{count}} Price</i> attribute [attribute ID <b>0x84B0</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getRxTier11BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_RXTIER11BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RXTIER11BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RXTIER11BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Rx Tier 12 Block {{count}} Price</i> attribute [attribute ID <b>0x84C0</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRxTier12BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_RXTIER12BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Rx Tier 12 Block {{count}} Price</i> attribute [attribute ID <b>0x84C0</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getRxTier12BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_RXTIER12BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RXTIER12BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RXTIER12BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Rx Tier 13 Block {{count}} Price</i> attribute [attribute ID <b>0x84D0</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRxTier13BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_RXTIER13BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Rx Tier 13 Block {{count}} Price</i> attribute [attribute ID <b>0x84D0</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getRxTier13BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_RXTIER13BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RXTIER13BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RXTIER13BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Rx Tier 14 Block {{count}} Price</i> attribute [attribute ID <b>0x84E0</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRxTier14BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_RXTIER14BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Rx Tier 14 Block {{count}} Price</i> attribute [attribute ID <b>0x84E0</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getRxTier14BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_RXTIER14BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RXTIER14BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RXTIER14BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Rx Tier 15 Block {{count}} Price</i> attribute [attribute ID <b>0x84F0</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRxTier15BlockPriceAsync(final int arrayOffset) {
        if (arrayOffset < 1 || arrayOffset > 16) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_RXTIER15BLOCK1PRICE + arrayOffset));
    }

    /**
     * Synchronously get the <i>Rx Tier 15 Block {{count}} Price</i> attribute [attribute ID <b>0x84F0</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (1 < arrayOffset < 16)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getRxTier15BlockPrice(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_RXTIER15BLOCK1PRICE + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RXTIER15BLOCK1PRICE + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RXTIER15BLOCK1PRICE + arrayOffset));
    }

    /**
     * Get the <i>Received Price Tier {{count}}</i> attribute [attribute ID <b>0x850F</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param arrayOffset attribute array offset (16 < arrayOffset < 63)
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReceivedPriceTierAsync(final int arrayOffset) {
        if (arrayOffset < 16 || arrayOffset > 63) {
            throw new IllegalArgumentException("arrayOffset out of bounds");
        }

        return read(attributes.get(ATTR_RECEIVEDPRICETIER16 + arrayOffset));
    }

    /**
     * Synchronously get the <i>Received Price Tier {{count}}</i> attribute [attribute ID <b>0x850F</b>].
     * <p>
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
     * @param arrayOffset attribute array offset (16 < arrayOffset < 63)
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getReceivedPriceTier(final int arrayOffset, final long refreshPeriod) {
        if (attributes.get(ATTR_RECEIVEDPRICETIER16 + arrayOffset).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RECEIVEDPRICETIER16 + arrayOffset).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RECEIVEDPRICETIER16 + arrayOffset));
    }

    /**
     * Get the <i>Received Tariff Label</i> attribute [attribute ID <b>0x8610</b>].
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReceivedTariffLabelAsync() {
        return read(attributes.get(ATTR_RECEIVEDTARIFFLABEL));
    }

    /**
     * Synchronously get the <i>Received Tariff Label</i> attribute [attribute ID <b>0x8610</b>].
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
    public ByteArray getReceivedTariffLabel(final long refreshPeriod) {
        if (attributes.get(ATTR_RECEIVEDTARIFFLABEL).isLastValueCurrent(refreshPeriod)) {
            return (ByteArray) attributes.get(ATTR_RECEIVEDTARIFFLABEL).getLastValue();
        }

        return (ByteArray) readSync(attributes.get(ATTR_RECEIVEDTARIFFLABEL));
    }

    /**
     * Get the <i>Received Number Of Price Tiers In Use</i> attribute [attribute ID <b>0x8611</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReceivedNumberOfPriceTiersInUseAsync() {
        return read(attributes.get(ATTR_RECEIVEDNUMBEROFPRICETIERSINUSE));
    }

    /**
     * Synchronously get the <i>Received Number Of Price Tiers In Use</i> attribute [attribute ID <b>0x8611</b>].
     * <p>
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
    public Integer getReceivedNumberOfPriceTiersInUse(final long refreshPeriod) {
        if (attributes.get(ATTR_RECEIVEDNUMBEROFPRICETIERSINUSE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RECEIVEDNUMBEROFPRICETIERSINUSE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RECEIVEDNUMBEROFPRICETIERSINUSE));
    }

    /**
     * Get the <i>Received Number Of Block Thresholds In Use</i> attribute [attribute ID <b>0x8612</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReceivedNumberOfBlockThresholdsInUseAsync() {
        return read(attributes.get(ATTR_RECEIVEDNUMBEROFBLOCKTHRESHOLDSINUSE));
    }

    /**
     * Synchronously get the <i>Received Number Of Block Thresholds In Use</i> attribute [attribute ID <b>0x8612</b>].
     * <p>
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
    public Integer getReceivedNumberOfBlockThresholdsInUse(final long refreshPeriod) {
        if (attributes.get(ATTR_RECEIVEDNUMBEROFBLOCKTHRESHOLDSINUSE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RECEIVEDNUMBEROFBLOCKTHRESHOLDSINUSE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RECEIVEDNUMBEROFBLOCKTHRESHOLDSINUSE));
    }

    /**
     * Get the <i>Received Tier Block Mode</i> attribute [attribute ID <b>0x8613</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReceivedTierBlockModeAsync() {
        return read(attributes.get(ATTR_RECEIVEDTIERBLOCKMODE));
    }

    /**
     * Synchronously get the <i>Received Tier Block Mode</i> attribute [attribute ID <b>0x8613</b>].
     * <p>
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
    public Integer getReceivedTierBlockMode(final long refreshPeriod) {
        if (attributes.get(ATTR_RECEIVEDTIERBLOCKMODE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RECEIVEDTIERBLOCKMODE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RECEIVEDTIERBLOCKMODE));
    }

    /**
     * Get the <i>Received CO2</i> attribute [attribute ID <b>0x8625</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReceivedCo2Async() {
        return read(attributes.get(ATTR_RECEIVEDCO2));
    }

    /**
     * Synchronously get the <i>Received CO2</i> attribute [attribute ID <b>0x8625</b>].
     * <p>
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
    public Integer getReceivedCo2(final long refreshPeriod) {
        if (attributes.get(ATTR_RECEIVEDCO2).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RECEIVEDCO2).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RECEIVEDCO2));
    }

    /**
     * Get the <i>Received CO2 Unit</i> attribute [attribute ID <b>0x8626</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReceivedCo2UnitAsync() {
        return read(attributes.get(ATTR_RECEIVEDCO2UNIT));
    }

    /**
     * Synchronously get the <i>Received CO2 Unit</i> attribute [attribute ID <b>0x8626</b>].
     * <p>
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
    public Integer getReceivedCo2Unit(final long refreshPeriod) {
        if (attributes.get(ATTR_RECEIVEDCO2UNIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RECEIVEDCO2UNIT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RECEIVEDCO2UNIT));
    }

    /**
     * Get the <i>Received CO2 Trailing Digit</i> attribute [attribute ID <b>0x8627</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReceivedCo2TrailingDigitAsync() {
        return read(attributes.get(ATTR_RECEIVEDCO2TRAILINGDIGIT));
    }

    /**
     * Synchronously get the <i>Received CO2 Trailing Digit</i> attribute [attribute ID <b>0x8627</b>].
     * <p>
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
    public Integer getReceivedCo2TrailingDigit(final long refreshPeriod) {
        if (attributes.get(ATTR_RECEIVEDCO2TRAILINGDIGIT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RECEIVEDCO2TRAILINGDIGIT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RECEIVEDCO2TRAILINGDIGIT));
    }

    /**
     * Get the <i>Received Current Billing Period Start</i> attribute [attribute ID <b>0x8700</b>].
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReceivedCurrentBillingPeriodStartAsync() {
        return read(attributes.get(ATTR_RECEIVEDCURRENTBILLINGPERIODSTART));
    }

    /**
     * Synchronously get the <i>Received Current Billing Period Start</i> attribute [attribute ID <b>0x8700</b>].
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getReceivedCurrentBillingPeriodStart(final long refreshPeriod) {
        if (attributes.get(ATTR_RECEIVEDCURRENTBILLINGPERIODSTART).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_RECEIVEDCURRENTBILLINGPERIODSTART).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_RECEIVEDCURRENTBILLINGPERIODSTART));
    }

    /**
     * Get the <i>Received Current Billing Period Duration</i> attribute [attribute ID <b>0x8701</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReceivedCurrentBillingPeriodDurationAsync() {
        return read(attributes.get(ATTR_RECEIVEDCURRENTBILLINGPERIODDURATION));
    }

    /**
     * Synchronously get the <i>Received Current Billing Period Duration</i> attribute [attribute ID <b>0x8701</b>].
     * <p>
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
    public Integer getReceivedCurrentBillingPeriodDuration(final long refreshPeriod) {
        if (attributes.get(ATTR_RECEIVEDCURRENTBILLINGPERIODDURATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RECEIVEDCURRENTBILLINGPERIODDURATION).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RECEIVEDCURRENTBILLINGPERIODDURATION));
    }

    /**
     * Get the <i>Received Last Billing Period Start</i> attribute [attribute ID <b>0x8702</b>].
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReceivedLastBillingPeriodStartAsync() {
        return read(attributes.get(ATTR_RECEIVEDLASTBILLINGPERIODSTART));
    }

    /**
     * Synchronously get the <i>Received Last Billing Period Start</i> attribute [attribute ID <b>0x8702</b>].
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getReceivedLastBillingPeriodStart(final long refreshPeriod) {
        if (attributes.get(ATTR_RECEIVEDLASTBILLINGPERIODSTART).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_RECEIVEDLASTBILLINGPERIODSTART).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_RECEIVEDLASTBILLINGPERIODSTART));
    }

    /**
     * Get the <i>Received Last Billing Period Duration</i> attribute [attribute ID <b>0x8703</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReceivedLastBillingPeriodDurationAsync() {
        return read(attributes.get(ATTR_RECEIVEDLASTBILLINGPERIODDURATION));
    }

    /**
     * Synchronously get the <i>Received Last Billing Period Duration</i> attribute [attribute ID <b>0x8703</b>].
     * <p>
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
    public Integer getReceivedLastBillingPeriodDuration(final long refreshPeriod) {
        if (attributes.get(ATTR_RECEIVEDLASTBILLINGPERIODDURATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RECEIVEDLASTBILLINGPERIODDURATION).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RECEIVEDLASTBILLINGPERIODDURATION));
    }

    /**
     * Get the <i>Received Last Billing Period Consolidated Bill</i> attribute [attribute ID <b>0x8704</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReceivedLastBillingPeriodConsolidatedBillAsync() {
        return read(attributes.get(ATTR_RECEIVEDLASTBILLINGPERIODCONSOLIDATEDBILL));
    }

    /**
     * Synchronously get the <i>Received Last Billing Period Consolidated Bill</i> attribute [attribute ID <b>0x8704</b>].
     * <p>
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
    public Integer getReceivedLastBillingPeriodConsolidatedBill(final long refreshPeriod) {
        if (attributes.get(ATTR_RECEIVEDLASTBILLINGPERIODCONSOLIDATEDBILL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RECEIVEDLASTBILLINGPERIODCONSOLIDATEDBILL).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RECEIVEDLASTBILLINGPERIODCONSOLIDATEDBILL));
    }

    /**
     * The Get Current Price Command
     * <p>
     * This command initiates a PublishPrice command for the current time. On receipt of this
     * command, the device shall send a PublishPrice command for the currently scheduled
     * time.
     *
     * @param commandOptions {@link Integer} Command Options
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentPriceCommand(Integer commandOptions) {
        GetCurrentPriceCommand command = new GetCurrentPriceCommand();

        // Set the fields
        command.setCommandOptions(commandOptions);

        return send(command);
    }

    /**
     * The Get Scheduled Prices Command
     * <p>
     * This command initiates a PublishPrice command for available price events. A server
     * device shall be capable of storing five price events at a minimum On receipt of this
     * command, the device shall send a PublishPrice command for the currently scheduled
     * time.
     *
     * @param startTime {@link Calendar} Start Time
     * @param numberOfEvents {@link Integer} Number Of Events
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getScheduledPricesCommand(Calendar startTime, Integer numberOfEvents) {
        GetScheduledPricesCommand command = new GetScheduledPricesCommand();

        // Set the fields
        command.setStartTime(startTime);
        command.setNumberOfEvents(numberOfEvents);

        return send(command);
    }

    /**
     * The Price Acknowledgement Command
     * <p>
     * The PriceAcknowledgement command provides the ability to acknowledge a previously
     * sent PublishPrice command. It is mandatory for 1.1 and later devices. For SE 1.0
     * devices, the command is optional.
     *
     * @param providerId {@link Integer} Provider ID
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param priceAckTime {@link Calendar} Price Ack Time
     * @param control {@link Integer} Control
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> priceAcknowledgementCommand(Integer providerId, Integer issuerEventId, Calendar priceAckTime, Integer control) {
        PriceAcknowledgementCommand command = new PriceAcknowledgementCommand();

        // Set the fields
        command.setProviderId(providerId);
        command.setIssuerEventId(issuerEventId);
        command.setPriceAckTime(priceAckTime);
        command.setControl(control);

        return send(command);
    }

    /**
     * The Get Block Period Command
     * <p>
     * This command initiates a PublishBlockPeriod command for the currently scheduled
     * block periods. A server device shall be capable of storing at least two commands, the
     * current period and a period to be activated in the near future. <br> A ZCL Default
     * response with status NOT_FOUND shall be returned if there are no events available.
     *
     * @param startTime {@link Calendar} Start Time
     * @param numberOfEvents {@link Integer} Number Of Events
     * @param tariffType {@link Integer} Tariff Type
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBlockPeriodCommand(Calendar startTime, Integer numberOfEvents, Integer tariffType) {
        GetBlockPeriodCommand command = new GetBlockPeriodCommand();

        // Set the fields
        command.setStartTime(startTime);
        command.setNumberOfEvents(numberOfEvents);
        command.setTariffType(tariffType);

        return send(command);
    }

    /**
     * The Get Conversion Factor Command
     * <p>
     * This command initiates a PublishConversionFactor command(s) for scheduled
     * conversion factor updates. A server device shall be capable of storing at least two
     * instances, the current and (if available) next instance to be activated in the future.
     * <br> A ZCL Default response with status NOT_FOUND shall be returned if there are no
     * conversion factor updates available
     *
     * @param earliestStartTime {@link Calendar} Earliest Start Time
     * @param minIssuerEventId {@link Integer} Min . Issuer Event ID
     * @param numberOfCommands {@link Integer} Number Of Commands
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getConversionFactorCommand(Calendar earliestStartTime, Integer minIssuerEventId, Integer numberOfCommands) {
        GetConversionFactorCommand command = new GetConversionFactorCommand();

        // Set the fields
        command.setEarliestStartTime(earliestStartTime);
        command.setMinIssuerEventId(minIssuerEventId);
        command.setNumberOfCommands(numberOfCommands);

        return send(command);
    }

    /**
     * The Get Calorific Value Command
     * <p>
     * This command initiates a PublishCalorificValue command(s) for scheduled calorific
     * value updates. A server device shall be capable of storing at least two instances, the
     * current and (if available) next instance to be activated in the future. <br> A ZCL
     * Default response with status NOT_FOUND shall be returned if there are no conversion
     * factor updates available
     *
     * @param earliestStartTime {@link Calendar} Earliest Start Time
     * @param minIssuerEventId {@link Integer} Min . Issuer Event ID
     * @param numberOfCommands {@link Integer} Number Of Commands
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCalorificValueCommand(Calendar earliestStartTime, Integer minIssuerEventId, Integer numberOfCommands) {
        GetCalorificValueCommand command = new GetCalorificValueCommand();

        // Set the fields
        command.setEarliestStartTime(earliestStartTime);
        command.setMinIssuerEventId(minIssuerEventId);
        command.setNumberOfCommands(numberOfCommands);

        return send(command);
    }

    /**
     * The Get Tariff Information Command
     * <p>
     * This command initiates PublishTariffInformation command(s) for scheduled tariff
     * updates. A server device shall be capable of storing at least two instances, current and
     * the next instance to be activated in the future. <br> One or more
     * PublishTariffInformation commands are sent in response to this command. To obtain the
     * complete tariff details, further GetPriceMatrix and GetBlockThesholds commands
     * must be sent using the start time and IssuerTariffID obtained from the appropriate
     * PublishTariffInformation command.
     *
     * @param earliestStartTime {@link Calendar} Earliest Start Time
     * @param minIssuerEventId {@link Integer} Min . Issuer Event ID
     * @param numberOfCommands {@link Integer} Number Of Commands
     * @param tariffType {@link Integer} Tariff Type
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTariffInformationCommand(Calendar earliestStartTime, Integer minIssuerEventId, Integer numberOfCommands, Integer tariffType) {
        GetTariffInformationCommand command = new GetTariffInformationCommand();

        // Set the fields
        command.setEarliestStartTime(earliestStartTime);
        command.setMinIssuerEventId(minIssuerEventId);
        command.setNumberOfCommands(numberOfCommands);
        command.setTariffType(tariffType);

        return send(command);
    }

    /**
     * The Get Price Matrix Command
     * <p>
     * This command initiates a PublishPriceMatrix command for the scheduled Price Matrix
     * updates. A server device shall be capable of storing at least two instances, current and
     * next instance to be activated in the future. <br> A ZCL Default response with status
     * NOT_FOUND shall be returned if there are no Price Matrix updates available.
     *
     * @param issuerTariffId {@link Integer} Issuer Tariff ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPriceMatrixCommand(Integer issuerTariffId) {
        GetPriceMatrixCommand command = new GetPriceMatrixCommand();

        // Set the fields
        command.setIssuerTariffId(issuerTariffId);

        return send(command);
    }

    /**
     * The Get Block Thresholds Command
     * <p>
     * This command initiates a PublishBlockThreshold command for the scheduled Block
     * Threshold updates. A server device shall be capable of storing at least two instances,
     * current and next instance to be activated in the future. <br> A ZCL Default response with
     * status NOT_FOUND shall be returned if there are no Price Matrix updates available.
     *
     * @param issuerTariffId {@link Integer} Issuer Tariff ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBlockThresholdsCommand(Integer issuerTariffId) {
        GetBlockThresholdsCommand command = new GetBlockThresholdsCommand();

        // Set the fields
        command.setIssuerTariffId(issuerTariffId);

        return send(command);
    }

    /**
     * The Get CO2 Value Command
     * <p>
     * This command initiates PublishCO2Value command(s) for scheduled CO2 conversion
     * factor updates. A server device shall be capable of storing at least two instances,
     * current and (if available) next instance to be activated in the future.
     *
     * @param earliestStartTime {@link Calendar} Earliest Start Time
     * @param minIssuerEventId {@link Integer} Min . Issuer Event ID
     * @param numberOfCommands {@link Integer} Number Of Commands
     * @param tariffType {@link Integer} Tariff Type
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCo2ValueCommand(Calendar earliestStartTime, Integer minIssuerEventId, Integer numberOfCommands, Integer tariffType) {
        GetCo2ValueCommand command = new GetCo2ValueCommand();

        // Set the fields
        command.setEarliestStartTime(earliestStartTime);
        command.setMinIssuerEventId(minIssuerEventId);
        command.setNumberOfCommands(numberOfCommands);
        command.setTariffType(tariffType);

        return send(command);
    }

    /**
     * The Get Tier Labels Command
     * <p>
     * This command allows a CLIENT to retrieve the tier labels associated with a given tariff;
     * this command initiates a PublishTierLabels command from the server.
     *
     * @param issuerTariffId {@link Integer} Issuer Tariff ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTierLabelsCommand(Integer issuerTariffId) {
        GetTierLabelsCommand command = new GetTierLabelsCommand();

        // Set the fields
        command.setIssuerTariffId(issuerTariffId);

        return send(command);
    }

    /**
     * The Get Billing Period Command
     * <p>
     * This command initiates one or more PublishBillingPeriod commands for currently
     * scheduled billing periods.
     *
     * @param earliestStartTime {@link Calendar} Earliest Start Time
     * @param minIssuerEventId {@link Integer} Min . Issuer Event ID
     * @param numberOfCommands {@link Integer} Number Of Commands
     * @param tariffType {@link Integer} Tariff Type
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBillingPeriodCommand(Calendar earliestStartTime, Integer minIssuerEventId, Integer numberOfCommands, Integer tariffType) {
        GetBillingPeriodCommand command = new GetBillingPeriodCommand();

        // Set the fields
        command.setEarliestStartTime(earliestStartTime);
        command.setMinIssuerEventId(minIssuerEventId);
        command.setNumberOfCommands(numberOfCommands);
        command.setTariffType(tariffType);

        return send(command);
    }

    /**
     * The Get Consolidated Bill Command
     * <p>
     * This command initiates one or more PublishConsolidatedBill commands with the
     * requested billing information.
     *
     * @param earliestStartTime {@link Calendar} Earliest Start Time
     * @param minIssuerEventId {@link Integer} Min . Issuer Event ID
     * @param numberOfCommands {@link Integer} Number Of Commands
     * @param tariffType {@link Integer} Tariff Type
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getConsolidatedBillCommand(Calendar earliestStartTime, Integer minIssuerEventId, Integer numberOfCommands, Integer tariffType) {
        GetConsolidatedBillCommand command = new GetConsolidatedBillCommand();

        // Set the fields
        command.setEarliestStartTime(earliestStartTime);
        command.setMinIssuerEventId(minIssuerEventId);
        command.setNumberOfCommands(numberOfCommands);
        command.setTariffType(tariffType);

        return send(command);
    }

    /**
     * The Cpp Event Response
     * <p>
     * The CPPEventResponse command is sent from a CLIENT (IHD) to the ESI to notify it of a
     * Critical Peak Pricing event authorization.
     *
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param cppAuth {@link Integer} Cpp Auth
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> cppEventResponse(Integer issuerEventId, Integer cppAuth) {
        CppEventResponse command = new CppEventResponse();

        // Set the fields
        command.setIssuerEventId(issuerEventId);
        command.setCppAuth(cppAuth);

        return send(command);
    }

    /**
     * The Get Credit Payment Command
     * <p>
     * This command initiates PublishCreditPayment commands for the requested credit
     * payment information.
     *
     * @param latestEndTime {@link Calendar} Latest End Time
     * @param numberOfRecords {@link Integer} Number Of Records
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCreditPaymentCommand(Calendar latestEndTime, Integer numberOfRecords) {
        GetCreditPaymentCommand command = new GetCreditPaymentCommand();

        // Set the fields
        command.setLatestEndTime(latestEndTime);
        command.setNumberOfRecords(numberOfRecords);

        return send(command);
    }

    /**
     * The Get Currency Conversion Command
     * <p>
     * This command initiates a PublishCurrencyConversion command for the currency
     * conversion factor updates. A server shall be capable of storing both the old and the new
     * currencies. <br> A ZCL Default response with status NOT_FOUND shall be returned if
     * there are no currency conversion factor updates available
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrencyConversionCommand() {
        return send(new GetCurrencyConversionCommand());
    }

    /**
     * The Get Tariff Cancellation Command
     * <p>
     * This command initiates the return of the last CancelTariff command held on the
     * associated server. <br> A ZCL Default response with status NOT_FOUND shall be returned
     * if there is no CancelTariff command available.
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTariffCancellationCommand() {
        return send(new GetTariffCancellationCommand());
    }

    /**
     * The Publish Price Command
     * <p>
     * The Publish Price command is generated in response to receiving a Get Current Price
     * command, in response to a Get Scheduled Prices command, and when an update to the pricing
     * information is available from the commodity provider, either before or when a TOU price
     * becomes active. Additionally the Publish Price Command is generated when Block
     * Pricing is in effect. When a Get Current Price or Get Scheduled Prices command is
     * received over a ZigBee Smart Energy network, the Publish Price command should be sent
     * unicast to the requester. In the case of an update to the pricing information from the
     * commodity provider, the Publish Price command should be unicast to all individually
     * registered devices implementing the Price Cluster on the ZigBee Smart Energy network.
     * <br> Devices capable of receiving this command must be capable of storing and
     * supporting at least two pricing information instances, the current active price and
     * the next price. By supporting at least two pricing information instances, receiving
     * devices will allow the Publish Price command generator to publish the next pricing
     * information during the current pricing period. <br> Nested and overlapping Publish
     * Price commands are not allowed. The current active price will be replaced if new price
     * information is received by the ESI. In the case of overlapping events, the event with the
     * newer Issuer Event ID takes priority over all nested and overlapping events. All
     * existing events that overlap, even partially, should be removed. The only exception to
     * this is that if an event with a newer Issuer Event ID overlaps with the end of the current
     * active price but is not yet active, the active price is not deleted but its duration is
     * modified to 0xFFFF (until changed) so that the active price ends when the new event
     * begins.
     *
     * @param providerId {@link Integer} Provider ID
     * @param rateLabel {@link ByteArray} Rate Label
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param currentTime {@link Calendar} Current Time
     * @param unitOfMeasure {@link Integer} Unit Of Measure
     * @param currency {@link Integer} Currency
     * @param priceTrailingDigitAndTier {@link Integer} Price Trailing Digit And Tier
     * @param numberOfPriceTiers {@link Integer} Number Of Price Tiers
     * @param startTime {@link Calendar} Start Time
     * @param duration {@link Integer} Duration
     * @param price {@link Integer} Price
     * @param priceRatio {@link Integer} Price Ratio
     * @param generationPrice {@link Integer} Generation Price
     * @param generationPriceRatio {@link Integer} Generation Price Ratio
     * @param alternateCostDelivered {@link Integer} Alternate Cost Delivered
     * @param alternateCostUnit {@link Integer} Alternate Cost Unit
     * @param alternateCostTrailingDigit {@link Integer} Alternate Cost Trailing Digit
     * @param numberOfBlockThresholds {@link Integer} Number Of Block Thresholds
     * @param priceControl {@link Integer} Price Control
     * @param numberOfGenerationTiers {@link Integer} Number Of Generation Tiers
     * @param generationTier {@link Integer} Generation Tier
     * @param extendedNumberOfPriceTiers {@link Integer} Extended Number Of Price Tiers
     * @param extendedPriceTier {@link Integer} Extended Price Tier
     * @param extendedRegisterTier {@link Integer} Extended Register Tier
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> publishPriceCommand(Integer providerId, ByteArray rateLabel, Integer issuerEventId, Calendar currentTime, Integer unitOfMeasure, Integer currency, Integer priceTrailingDigitAndTier, Integer numberOfPriceTiers, Calendar startTime, Integer duration, Integer price, Integer priceRatio, Integer generationPrice, Integer generationPriceRatio, Integer alternateCostDelivered, Integer alternateCostUnit, Integer alternateCostTrailingDigit, Integer numberOfBlockThresholds, Integer priceControl, Integer numberOfGenerationTiers, Integer generationTier, Integer extendedNumberOfPriceTiers, Integer extendedPriceTier, Integer extendedRegisterTier) {
        PublishPriceCommand command = new PublishPriceCommand();

        // Set the fields
        command.setProviderId(providerId);
        command.setRateLabel(rateLabel);
        command.setIssuerEventId(issuerEventId);
        command.setCurrentTime(currentTime);
        command.setUnitOfMeasure(unitOfMeasure);
        command.setCurrency(currency);
        command.setPriceTrailingDigitAndTier(priceTrailingDigitAndTier);
        command.setNumberOfPriceTiers(numberOfPriceTiers);
        command.setStartTime(startTime);
        command.setDuration(duration);
        command.setPrice(price);
        command.setPriceRatio(priceRatio);
        command.setGenerationPrice(generationPrice);
        command.setGenerationPriceRatio(generationPriceRatio);
        command.setAlternateCostDelivered(alternateCostDelivered);
        command.setAlternateCostUnit(alternateCostUnit);
        command.setAlternateCostTrailingDigit(alternateCostTrailingDigit);
        command.setNumberOfBlockThresholds(numberOfBlockThresholds);
        command.setPriceControl(priceControl);
        command.setNumberOfGenerationTiers(numberOfGenerationTiers);
        command.setGenerationTier(generationTier);
        command.setExtendedNumberOfPriceTiers(extendedNumberOfPriceTiers);
        command.setExtendedPriceTier(extendedPriceTier);
        command.setExtendedRegisterTier(extendedRegisterTier);

        return send(command);
    }

    /**
     * The Publish Block Period Command
     * <p>
     * The Publish Block Period command is generated in response to receiving a Get Block
     * Period(s) command or when an update to the block tariff schedule is available from the
     * commodity provider. When the Get Block Period(s) command is received over the ZigBee
     * Smart Energy network, the Publish Block Period command(s) should be sent unicast to the
     * requestor. In the case of an update to the block tariff schedule from the commodity
     * provider, the Publish Block Period command should be unicast to all individually
     * registered devices implementing the Price Cluster on the ZigBee Smart Energy network.
     * <br> Devices capable of receiving this command must be capable of storing and
     * supporting two block periods, the current active block and the next block. By
     * supporting two block periods, receiving devices will allow the Publish Block Period
     * command generator to publish the next block information during the current block
     * period.
     *
     * @param providerId {@link Integer} Provider ID
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param blockPeriodStartTime {@link Calendar} Block Period Start Time
     * @param blockPeriodDuration {@link Integer} Block Period Duration
     * @param blockPeriodControl {@link Integer} Block Period Control
     * @param blockPeriodDurationType {@link Integer} Block Period Duration Type
     * @param tariffType {@link Integer} Tariff Type
     * @param tariffResolutionPeriod {@link Integer} Tariff Resolution Period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> publishBlockPeriodCommand(Integer providerId, Integer issuerEventId, Calendar blockPeriodStartTime, Integer blockPeriodDuration, Integer blockPeriodControl, Integer blockPeriodDurationType, Integer tariffType, Integer tariffResolutionPeriod) {
        PublishBlockPeriodCommand command = new PublishBlockPeriodCommand();

        // Set the fields
        command.setProviderId(providerId);
        command.setIssuerEventId(issuerEventId);
        command.setBlockPeriodStartTime(blockPeriodStartTime);
        command.setBlockPeriodDuration(blockPeriodDuration);
        command.setBlockPeriodControl(blockPeriodControl);
        command.setBlockPeriodDurationType(blockPeriodDurationType);
        command.setTariffType(tariffType);
        command.setTariffResolutionPeriod(tariffResolutionPeriod);

        return send(command);
    }

    /**
     * The Publish Conversion Factor Command
     * <p>
     * The PublishConversionFactor command is sent in response to a GetConversionFactor
     * command or if a new conversion factor is available. Clients shall be capable of storing
     * at least two instances of the Conversion Factor, the currently active one and the next
     * one.
     *
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param startTime {@link Calendar} Start Time
     * @param conversionFactor {@link Integer} Conversion Factor
     * @param conversionFactorTrailingDigit {@link Integer} Conversion Factor Trailing Digit
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> publishConversionFactorCommand(Integer issuerEventId, Calendar startTime, Integer conversionFactor, Integer conversionFactorTrailingDigit) {
        PublishConversionFactorCommand command = new PublishConversionFactorCommand();

        // Set the fields
        command.setIssuerEventId(issuerEventId);
        command.setStartTime(startTime);
        command.setConversionFactor(conversionFactor);
        command.setConversionFactorTrailingDigit(conversionFactorTrailingDigit);

        return send(command);
    }

    /**
     * The Publish Calorific Value Command
     * <p>
     * The PublishCalorificValue command is sent in response to a GetCalorificValue command
     * or if a new calorific value is available. Clients shall be capable of storing at least two
     * instances of the Calorific Value, the currently active one and the next one.
     *
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param startTime {@link Calendar} Start Time
     * @param calorificValue {@link Integer} Calorific Value
     * @param calorificValueUnit {@link Integer} Calorific Value Unit
     * @param calorificValueTrailingDigit {@link Integer} Calorific Value Trailing Digit
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> publishCalorificValueCommand(Integer issuerEventId, Calendar startTime, Integer calorificValue, Integer calorificValueUnit, Integer calorificValueTrailingDigit) {
        PublishCalorificValueCommand command = new PublishCalorificValueCommand();

        // Set the fields
        command.setIssuerEventId(issuerEventId);
        command.setStartTime(startTime);
        command.setCalorificValue(calorificValue);
        command.setCalorificValueUnit(calorificValueUnit);
        command.setCalorificValueTrailingDigit(calorificValueTrailingDigit);

        return send(command);
    }

    /**
     * The Publish Tariff Information Command
     * <p>
     * The PublishTariffInformation command is sent in response to a GetTariffInformation
     * command or if new tariff information is available (including Price Matrix and Block
     * Thresholds). Clients should be capable of storing at least two instances of the Tariff
     * Information, the currently active and the next one. Note that there may be separate
     * tariff information for consumption delivered and received. <br> Note that the payload
     * for this command could be up to 61 bytes in length, therefore fragmentation may be
     * required. <br> If the CLIENT is unable to store this PublishTariffInformation
     * command, the device should respond using a ZCL Default Response with a status of
     * INSUFFICIENT_SPACE.
     *
     * @param providerId {@link Integer} Provider ID
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param issuerTariffId {@link Integer} Issuer Tariff ID
     * @param startTime {@link Calendar} Start Time
     * @param tariffType {@link Integer} Tariff Type
     * @param tariffLabel {@link ByteArray} Tariff Label
     * @param numberOfPriceTiers {@link Integer} Number Of Price Tiers
     * @param numberOfBlockThresholds {@link Integer} Number Of Block Thresholds
     * @param unitOfMeasure {@link Integer} Unit Of Measure
     * @param currency {@link Integer} Currency
     * @param priceTrailingDigit {@link Integer} Price Trailing Digit
     * @param standingCharge {@link Integer} Standing Charge
     * @param tierBlockMode {@link Integer} Tier Block Mode
     * @param blockThresholdMultiplier {@link Integer} Block Threshold Multiplier
     * @param blockThresholdDivisor {@link Integer} Block Threshold Divisor
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> publishTariffInformationCommand(Integer providerId, Integer issuerEventId, Integer issuerTariffId, Calendar startTime, Integer tariffType, ByteArray tariffLabel, Integer numberOfPriceTiers, Integer numberOfBlockThresholds, Integer unitOfMeasure, Integer currency, Integer priceTrailingDigit, Integer standingCharge, Integer tierBlockMode, Integer blockThresholdMultiplier, Integer blockThresholdDivisor) {
        PublishTariffInformationCommand command = new PublishTariffInformationCommand();

        // Set the fields
        command.setProviderId(providerId);
        command.setIssuerEventId(issuerEventId);
        command.setIssuerTariffId(issuerTariffId);
        command.setStartTime(startTime);
        command.setTariffType(tariffType);
        command.setTariffLabel(tariffLabel);
        command.setNumberOfPriceTiers(numberOfPriceTiers);
        command.setNumberOfBlockThresholds(numberOfBlockThresholds);
        command.setUnitOfMeasure(unitOfMeasure);
        command.setCurrency(currency);
        command.setPriceTrailingDigit(priceTrailingDigit);
        command.setStandingCharge(standingCharge);
        command.setTierBlockMode(tierBlockMode);
        command.setBlockThresholdMultiplier(blockThresholdMultiplier);
        command.setBlockThresholdDivisor(blockThresholdDivisor);

        return send(command);
    }

    /**
     * The Publish Price Matrix Command
     * <p>
     * The PublishPriceMatrix command is used to publish the Block Price Information Set (up
     * to 15 tiers x 15 blocks) and the Extended Price Information Set (up to 48 tiers). The
     * PublishPriceMatrix command is sent in response to a GetPriceMatrix command. Clients
     * should be capable of storing at least two instances of the Price Matrix, the currently
     * active and the next one. There may be a separate Price Matrix for consumption delivered
     * and received; in this case, each Price Matrix will be identified by a different
     * IssuerTariffId value. The Price server shall send only the number of tiers and blocks as
     * defined in the corresponding PublishTariffInformation command
     * (NumberofPriceTiersinUse, NumberofBlockThresholdsinUse+1) <br> The maximum
     * application payload may not be sufficient to transfer all Price Matrix elements in one
     * command. Therefore the ESI may send as many PublishPriceMatrix commands as needed. In
     * this case the first command shall have CommandIndex set to 0, the second to 1 and so on; all
     * associated commands shall use the same value of Issuer Event ID. Note that, in this case,
     * it is the client’s responsibility to ensure that it receives all associated
     * PublishPriceMatrix commands before any of the payloads can be used.
     *
     * @param providerId {@link Integer} Provider ID
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param startTime {@link Calendar} Start Time
     * @param issuerTariffId {@link Integer} Issuer Tariff ID
     * @param commandIndex {@link Integer} Command Index
     * @param totalNumberOfCommands {@link Integer} Total Number Of Commands
     * @param subPayloadControl {@link Integer} Sub Payload Control
     * @param priceMatrixSubPayload {@link PriceMatrixSubPayload} Price Matrix Sub Payload
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> publishPriceMatrixCommand(Integer providerId, Integer issuerEventId, Calendar startTime, Integer issuerTariffId, Integer commandIndex, Integer totalNumberOfCommands, Integer subPayloadControl, PriceMatrixSubPayload priceMatrixSubPayload) {
        PublishPriceMatrixCommand command = new PublishPriceMatrixCommand();

        // Set the fields
        command.setProviderId(providerId);
        command.setIssuerEventId(issuerEventId);
        command.setStartTime(startTime);
        command.setIssuerTariffId(issuerTariffId);
        command.setCommandIndex(commandIndex);
        command.setTotalNumberOfCommands(totalNumberOfCommands);
        command.setSubPayloadControl(subPayloadControl);
        command.setPriceMatrixSubPayload(priceMatrixSubPayload);

        return send(command);
    }

    /**
     * The Publish Block Thresholds Command
     * <p>
     * The PublishBlockThresholds command is sent in response to a GetBlockThresholds
     * command. Clients should be capable of storing at least two instances of the Block
     * Thresholds, the currently active and the next one. <br> There may be a separate set of
     * Block Thresholds for consumption delivered and received; in this case, each set of
     * Block Thresholds will be identified by a different IssuerTariffId value. <br> The
     * price server shall send only the number of block thresholds in use
     * (NumberofBlockThresholdsInUse) as defined in the PublishTariffInformation
     * command. <br> The maximum application payload may not be sufficient to transfer all
     * thresholds in one command. In this case the Price server may send two consecutive
     * PublishBlockThreshold commands (CommandIndex set to 0 and 1 respectively); both
     * commands shall use the same value of Issuer Event ID. Note that, in this case, it is the
     * client’s responsibility to ensure that it receives all associated
     * PublishBlockThreshold commands before any of the payloads can be used.
     *
     * @param providerId {@link Integer} Provider ID
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param startTime {@link Calendar} Start Time
     * @param issuerTariffId {@link Integer} Issuer Tariff ID
     * @param commandIndex {@link Integer} Command Index
     * @param totalNumberOfCommands {@link Integer} Total Number Of Commands
     * @param subPayloadControl {@link Integer} Sub Payload Control
     * @param blockThresholdSubPayload {@link BlockThresholdSubPayload} Block Threshold Sub Payload
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> publishBlockThresholdsCommand(Integer providerId, Integer issuerEventId, Calendar startTime, Integer issuerTariffId, Integer commandIndex, Integer totalNumberOfCommands, Integer subPayloadControl, BlockThresholdSubPayload blockThresholdSubPayload) {
        PublishBlockThresholdsCommand command = new PublishBlockThresholdsCommand();

        // Set the fields
        command.setProviderId(providerId);
        command.setIssuerEventId(issuerEventId);
        command.setStartTime(startTime);
        command.setIssuerTariffId(issuerTariffId);
        command.setCommandIndex(commandIndex);
        command.setTotalNumberOfCommands(totalNumberOfCommands);
        command.setSubPayloadControl(subPayloadControl);
        command.setBlockThresholdSubPayload(blockThresholdSubPayload);

        return send(command);
    }

    /**
     * The Publish CO2 Value Command
     * <p>
     * The PublishCO2Value command is sent in response to a GetCO2Value command or if a new CO2
     * conversion factor is available. Clients should be capable of storing at least two
     * instances of the CO2 conversion factor, the currently active and the next one.
     *
     * @param providerId {@link Integer} Provider ID
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param startTime {@link Calendar} Start Time
     * @param tariffType {@link Integer} Tariff Type
     * @param co2Value {@link Integer} CO2 Value
     * @param co2ValueUnit {@link Integer} CO2 Value Unit
     * @param co2ValueTrailingDigit {@link Integer} CO2 Value Trailing Digit
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> publishCo2ValueCommand(Integer providerId, Integer issuerEventId, Calendar startTime, Integer tariffType, Integer co2Value, Integer co2ValueUnit, Integer co2ValueTrailingDigit) {
        PublishCo2ValueCommand command = new PublishCo2ValueCommand();

        // Set the fields
        command.setProviderId(providerId);
        command.setIssuerEventId(issuerEventId);
        command.setStartTime(startTime);
        command.setTariffType(tariffType);
        command.setCo2Value(co2Value);
        command.setCo2ValueUnit(co2ValueUnit);
        command.setCo2ValueTrailingDigit(co2ValueTrailingDigit);

        return send(command);
    }

    /**
     * The Publish Tier Labels Command
     * <p>
     * The PublishTierLabels command is generated in response to receiving a GetTierLabels
     * command or when there is a tier label change
     *
     * @param providerId {@link Integer} Provider ID
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param issuerTariffId {@link Integer} Issuer Tariff ID
     * @param commandIndex {@link Integer} Command Index
     * @param totalNumberOfCommands {@link Integer} Total Number Of Commands
     * @param numberOfLabels {@link Integer} Number Of Labels
     * @param tierId {@link Integer} Tier ID
     * @param tierLabel {@link ByteArray} Tier Label
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> publishTierLabelsCommand(Integer providerId, Integer issuerEventId, Integer issuerTariffId, Integer commandIndex, Integer totalNumberOfCommands, Integer numberOfLabels, Integer tierId, ByteArray tierLabel) {
        PublishTierLabelsCommand command = new PublishTierLabelsCommand();

        // Set the fields
        command.setProviderId(providerId);
        command.setIssuerEventId(issuerEventId);
        command.setIssuerTariffId(issuerTariffId);
        command.setCommandIndex(commandIndex);
        command.setTotalNumberOfCommands(totalNumberOfCommands);
        command.setNumberOfLabels(numberOfLabels);
        command.setTierId(tierId);
        command.setTierLabel(tierLabel);

        return send(command);
    }

    /**
     * The Publish Billing Period Command
     * <p>
     * The PublishBillingPeriod command is generated in response to receiving a
     * GetBillingPeriod(s) command or when an update to the Billing schedule is available
     * from the commodity supplier. Nested and overlapping PublishBillingPeriod commands
     * are not allowed. In the case of overlapping billing periods, the period with the newer
     * IssuerEventID takes priority over all nested and overlapping periods. All existing
     * periods that overlap, even partially, should be removed. Note however that there may be
     * separate billing schedules for consumption delivered and received.
     *
     * @param providerId {@link Integer} Provider ID
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param billingPeriodStartTime {@link Calendar} Billing Period Start Time
     * @param billingPeriodDuration {@link Integer} Billing Period Duration
     * @param billingPeriodDurationType {@link Integer} Billing Period Duration Type
     * @param tariffType {@link Integer} Tariff Type
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> publishBillingPeriodCommand(Integer providerId, Integer issuerEventId, Calendar billingPeriodStartTime, Integer billingPeriodDuration, Integer billingPeriodDurationType, Integer tariffType) {
        PublishBillingPeriodCommand command = new PublishBillingPeriodCommand();

        // Set the fields
        command.setProviderId(providerId);
        command.setIssuerEventId(issuerEventId);
        command.setBillingPeriodStartTime(billingPeriodStartTime);
        command.setBillingPeriodDuration(billingPeriodDuration);
        command.setBillingPeriodDurationType(billingPeriodDurationType);
        command.setTariffType(tariffType);

        return send(command);
    }

    /**
     * The Publish Consolidated Bill Command
     * <p>
     * The PublishConsolidatedBill command is used to make consolidated billing
     * information from previous billing periods available to other end devices. This
     * command is issued in response to a GetConsolidatedBill command or if new billing
     * information is available. Nested and overlapping PublishConsolidatedBill commands
     * are not allowed. In the case of overlapping consolidated bills, the bill with the newer
     * IssuerEventID takes priority over all nested and overlapping bills. All existing
     * bills that overlap, even partially, should be removed. <br> Note however that there may
     * be separate consolidated bills for consumption delivered and received.
     *
     * @param providerId {@link Integer} Provider ID
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param billingPeriodStartTime {@link Calendar} Billing Period Start Time
     * @param billingPeriodDuration {@link Integer} Billing Period Duration
     * @param billingPeriodDurationType {@link Integer} Billing Period Duration Type
     * @param tariffType {@link Integer} Tariff Type
     * @param consolidatedBill {@link Integer} Consolidated Bill
     * @param currency {@link Integer} Currency
     * @param billTrailingDigit {@link Integer} Bill Trailing Digit
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> publishConsolidatedBillCommand(Integer providerId, Integer issuerEventId, Calendar billingPeriodStartTime, Integer billingPeriodDuration, Integer billingPeriodDurationType, Integer tariffType, Integer consolidatedBill, Integer currency, Integer billTrailingDigit) {
        PublishConsolidatedBillCommand command = new PublishConsolidatedBillCommand();

        // Set the fields
        command.setProviderId(providerId);
        command.setIssuerEventId(issuerEventId);
        command.setBillingPeriodStartTime(billingPeriodStartTime);
        command.setBillingPeriodDuration(billingPeriodDuration);
        command.setBillingPeriodDurationType(billingPeriodDurationType);
        command.setTariffType(tariffType);
        command.setConsolidatedBill(consolidatedBill);
        command.setCurrency(currency);
        command.setBillTrailingDigit(billTrailingDigit);

        return send(command);
    }

    /**
     * The Publish Cpp Event Command
     * <p>
     * The PublishCPPEvent command is sent from an ESI to its Price clients to notify them of a
     * Critical Peak Pricing (CPP) event. <br> When the PublishCPPEvent command is received,
     * the IHD or Meter shall act in one of two ways: 1. It shall notify the consumer that there is a
     * CPP event that requires acknowledgment. The acknowledgement shall be either to accept
     * the CPPEvent or reject the CPPEvent (in which case it shall send the CPPEventResponse
     * command, with the CPPAuth parameter set to Accepted or Rejected). It is recommended
     * that the CPP event is ignored until a consumer either accepts or rejects the event. 2. The
     * CPPAuth parameter is set to “Forced”, in which case the CPPEvent has been accepted.
     *
     * @param providerId {@link Integer} Provider ID
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param startTime {@link Calendar} Start Time
     * @param durationInMinutes {@link Integer} Duration In Minutes
     * @param tariffType {@link Integer} Tariff Type
     * @param cppPriceTier {@link Integer} Cpp Price Tier
     * @param cppAuth {@link Integer} Cpp Auth
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> publishCppEventCommand(Integer providerId, Integer issuerEventId, Calendar startTime, Integer durationInMinutes, Integer tariffType, Integer cppPriceTier, Integer cppAuth) {
        PublishCppEventCommand command = new PublishCppEventCommand();

        // Set the fields
        command.setProviderId(providerId);
        command.setIssuerEventId(issuerEventId);
        command.setStartTime(startTime);
        command.setDurationInMinutes(durationInMinutes);
        command.setTariffType(tariffType);
        command.setCppPriceTier(cppPriceTier);
        command.setCppAuth(cppAuth);

        return send(command);
    }

    /**
     * The Publish Credit Payment Command
     * <p>
     * The PublishCreditPayment command is used to update the credit payment information
     * when available. <br> Nested and overlapping PublishCreditPayment commands are not
     * allowed. In the case of overlapping credit payments, the payment with the newer Issuer
     * Event ID takes priority over all nested and overlapping payments. All existing
     * payments that overlap, even partially, should be removed.
     *
     * @param providerId {@link Integer} Provider ID
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param creditPaymentDueDate {@link Calendar} Credit Payment Due Date
     * @param creditPaymentOverdueAmount {@link Integer} Credit Payment Overdue Amount
     * @param creditPaymentStatus {@link Integer} Credit Payment Status
     * @param creditPayment {@link Integer} Credit Payment
     * @param creditPaymentDate {@link Calendar} Credit Payment Date
     * @param creditPaymentRef {@link ByteArray} Credit Payment Ref
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> publishCreditPaymentCommand(Integer providerId, Integer issuerEventId, Calendar creditPaymentDueDate, Integer creditPaymentOverdueAmount, Integer creditPaymentStatus, Integer creditPayment, Calendar creditPaymentDate, ByteArray creditPaymentRef) {
        PublishCreditPaymentCommand command = new PublishCreditPaymentCommand();

        // Set the fields
        command.setProviderId(providerId);
        command.setIssuerEventId(issuerEventId);
        command.setCreditPaymentDueDate(creditPaymentDueDate);
        command.setCreditPaymentOverdueAmount(creditPaymentOverdueAmount);
        command.setCreditPaymentStatus(creditPaymentStatus);
        command.setCreditPayment(creditPayment);
        command.setCreditPaymentDate(creditPaymentDate);
        command.setCreditPaymentRef(creditPaymentRef);

        return send(command);
    }

    /**
     * The Publish Currency Conversion Command
     * <p>
     * The PublishCurrencyConversion command is sent in response to a
     * GetCurrencyConversion command or when a new currency becomes available.
     *
     * @param providerId {@link Integer} Provider ID
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param startTime {@link Calendar} Start Time
     * @param oldCurrency {@link Integer} Old Currency
     * @param newCurrency {@link Integer} New Currency
     * @param conversionFactor {@link Integer} Conversion Factor
     * @param conversionFactorTrailingDigit {@link Integer} Conversion Factor Trailing Digit
     * @param currencyChangeControlFlags {@link Integer} Currency Change Control Flags
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> publishCurrencyConversionCommand(Integer providerId, Integer issuerEventId, Calendar startTime, Integer oldCurrency, Integer newCurrency, Integer conversionFactor, Integer conversionFactorTrailingDigit, Integer currencyChangeControlFlags) {
        PublishCurrencyConversionCommand command = new PublishCurrencyConversionCommand();

        // Set the fields
        command.setProviderId(providerId);
        command.setIssuerEventId(issuerEventId);
        command.setStartTime(startTime);
        command.setOldCurrency(oldCurrency);
        command.setNewCurrency(newCurrency);
        command.setConversionFactor(conversionFactor);
        command.setConversionFactorTrailingDigit(conversionFactorTrailingDigit);
        command.setCurrencyChangeControlFlags(currencyChangeControlFlags);

        return send(command);
    }

    /**
     * The Cancel Tariff Command
     * <p>
     * The CancelTariff command indicates that all data associated with a particular tariff
     * instance should be discarded. <br> In markets where permanently active price
     * information is required for billing purposes, it is recommended that
     * replacement/superseding PublishTariffInformation, PublishPriceMatrix,
     * PublishBlockThresholds and PublishTierLabels commands are used in place of a
     * CancelTariff command.
     *
     * @param providerId {@link Integer} Provider ID
     * @param issuerTariffId {@link Integer} Issuer Tariff ID
     * @param tariffType {@link Integer} Tariff Type
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> cancelTariffCommand(Integer providerId, Integer issuerTariffId, Integer tariffType) {
        CancelTariffCommand command = new CancelTariffCommand();

        // Set the fields
        command.setProviderId(providerId);
        command.setIssuerTariffId(issuerTariffId);
        command.setTariffType(tariffType);

        return send(command);
    }
}
