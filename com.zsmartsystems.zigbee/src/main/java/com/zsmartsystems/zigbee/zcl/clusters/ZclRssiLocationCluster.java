package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.AnchorNodeAnnounceCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.CompactLocationDataNotificationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.DeviceConfigurationResponse;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.GetDeviceConfigurationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.GetLocationDataCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.LocationDataNotificationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.LocationDataResponse;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.ReportRssiMeasurementsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.RequestOwnLocationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.RssiPingCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.RssiRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.RssiResponse;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.SendPingsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.SetAbsoluteLocationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.SetDeviceConfigurationCommand;
import com.zsmartsystems.zigbee.zcl.field.*;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * <b>RSSI Location</b> cluster implementation (<i>Cluster ID 0x000B</i>).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclRssiLocationCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0x000B;

    // Cluster Name
    public static final String CLUSTER_NAME = "RSSI Location";

    // Attribute constants
    public static final int ATTR_LOCATIONTYPE = 0x0000;
    public static final int ATTR_LOCATIONMETHOD = 0x0001;
    public static final int ATTR_LOCATIONAGE = 0x0002;
    public static final int ATTR_QUALITYMEASURE = 0x0003;
    public static final int ATTR_NUMBEROFDEVICES = 0x0004;
    public static final int ATTR_COORDINATE1 = 0x0010;
    public static final int ATTR_COORDINATE2 = 0x0011;
    public static final int ATTR_COORDINATE3 = 0x0012;
    public static final int ATTR_POWER = 0x0013;
    public static final int ATTR_PATHLOSSEXPONENT = 0x0014;
    public static final int ATTR_REPORTINGPERIOD = 0x0015;
    public static final int ATTR_CALCULATIONPERIOD = 0x0016;
    public static final int ATTR_NUMBERRSSIMEASUREMENTS = 0x0017;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>(13);

        attributeMap.put(ATTR_LOCATIONTYPE, new ZclAttribute(0, "LocationType", ZclDataType.DATA_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_LOCATIONMETHOD, new ZclAttribute(1, "LocationMethod", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_LOCATIONAGE, new ZclAttribute(2, "LocationAge", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_QUALITYMEASURE, new ZclAttribute(3, "QualityMeasure", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_NUMBEROFDEVICES, new ZclAttribute(4, "NumberOfDevices", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_COORDINATE1, new ZclAttribute(16, "Coordinate1", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, true, false));
        attributeMap.put(ATTR_COORDINATE2, new ZclAttribute(17, "Coordinate2", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, true, false));
        attributeMap.put(ATTR_COORDINATE3, new ZclAttribute(18, "Coordinate3", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_POWER, new ZclAttribute(19, "Power", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, true, false));
        attributeMap.put(ATTR_PATHLOSSEXPONENT, new ZclAttribute(20, "PathLossExponent", ZclDataType.SIGNED_16_BIT_INTEGER, true, true, true, false));
        attributeMap.put(ATTR_REPORTINGPERIOD, new ZclAttribute(21, "ReportingPeriod", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_CALCULATIONPERIOD, new ZclAttribute(22, "CalculationPeriod", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_NUMBERRSSIMEASUREMENTS, new ZclAttribute(23, "NumberRSSIMeasurements", ZclDataType.SIGNED_16_BIT_INTEGER, false, true, true, false));

        return attributeMap;
    }

    /**
     * Default constructor.
     */
    public ZclRssiLocationCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {
        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * <p>
     * Get the <i>LocationType</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The LocationType attribute is 8 bits long and is divided into bit fields.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLocationTypeAsync() {
        return read(attributes.get(ATTR_LOCATIONTYPE));
    }


    /**
     * <p>
     * Synchronously get the <i>LocationType</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The LocationType attribute is 8 bits long and is divided into bit fields.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getLocationType() {
        return (Integer) readSync(attributes.get(ATTR_LOCATIONTYPE));
    }

    /**
     * <p>
     * Get the <i>LocationMethod</i> attribute [attribute ID <b>1</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLocationMethodAsync() {
        return read(attributes.get(ATTR_LOCATIONMETHOD));
    }


    /**
     * <p>
     * Synchronously get the <i>LocationMethod</i> attribute [attribute ID <b>1</b>].
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getLocationMethod() {
        return (Integer) readSync(attributes.get(ATTR_LOCATIONMETHOD));
    }

    /**
     * <p>
     * Get the <i>LocationAge</i> attribute [attribute ID <b>2</b>].
     * <p>
     * <br>
     * The LocationAge attribute indicates the amount of time, measured in seconds, that
     * has transpired since the location information was last calculated. This attribute is
     * not valid if the Absolute bit of the LocationType attribute is set to one.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLocationAgeAsync() {
        return read(attributes.get(ATTR_LOCATIONAGE));
    }


    /**
     * <p>
     * Synchronously get the <i>LocationAge</i> attribute [attribute ID <b>2</b>].
     * <p>
     * <br>
     * The LocationAge attribute indicates the amount of time, measured in seconds, that
     * has transpired since the location information was last calculated. This attribute is
     * not valid if the Absolute bit of the LocationType attribute is set to one.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getLocationAge() {
        return (Integer) readSync(attributes.get(ATTR_LOCATIONAGE));
    }

    /**
     * <p>
     * Get the <i>QualityMeasure</i> attribute [attribute ID <b>3</b>].
     * <p>
     * <br>
     * The QualityMeasure attribute is a measure of confidence in the corresponding
     * location information. The higher the value, the more confident the transmitting
     * device is in the location information. A value of 0x64 indicates complete (100%)
     * confidence and a value of 0x00 indicates zero confidence. (Note: no fixed
     * confidence metric is mandated – the metric may be application and manufacturer
     * dependent).
     * <br>
     * This field is not valid if the Absolute bit of the LocationType attribute is set to one.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getQualityMeasureAsync() {
        return read(attributes.get(ATTR_QUALITYMEASURE));
    }


    /**
     * <p>
     * Synchronously get the <i>QualityMeasure</i> attribute [attribute ID <b>3</b>].
     * <p>
     * <br>
     * The QualityMeasure attribute is a measure of confidence in the corresponding
     * location information. The higher the value, the more confident the transmitting
     * device is in the location information. A value of 0x64 indicates complete (100%)
     * confidence and a value of 0x00 indicates zero confidence. (Note: no fixed
     * confidence metric is mandated – the metric may be application and manufacturer
     * dependent).
     * <br>
     * This field is not valid if the Absolute bit of the LocationType attribute is set to one.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getQualityMeasure() {
        return (Integer) readSync(attributes.get(ATTR_QUALITYMEASURE));
    }

    /**
     * <p>
     * Get the <i>NumberOfDevices</i> attribute [attribute ID <b>4</b>].
     * <p>
     * <br>
     * The NumberOfDevices attribute is the number of devices whose location data
     * were used to calculate the last location value. This attribute is related to the
     * QualityMeasure attribute.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getNumberOfDevicesAsync() {
        return read(attributes.get(ATTR_NUMBEROFDEVICES));
    }


    /**
     * <p>
     * Synchronously get the <i>NumberOfDevices</i> attribute [attribute ID <b>4</b>].
     * <p>
     * <br>
     * The NumberOfDevices attribute is the number of devices whose location data
     * were used to calculate the last location value. This attribute is related to the
     * QualityMeasure attribute.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getNumberOfDevices() {
        return (Integer) readSync(attributes.get(ATTR_NUMBEROFDEVICES));
    }


    /**
     * <p>
     * Set the <i>Coordinate1</i> attribute [attribute ID <b>16</b>].
     * <p>
     * <br>
     * The Coordinate1, Coordinate2 and Coordinate3 attributes are signed 16-bit
     * integers, and represent orthogonal linear coordinates x, y, z in meters as follows.
     * <br>
     * x = Coordinate1 / 10, y = Coordinate2 / 10, z = Coordinate3 / 10
     * <br>
     * The range of x is -3276.7 to 3276.7 meters, corresponding to Coordinate1
     * between 0x8001 and 0x7fff. The same range applies to y and z. A value of
     * 0x8000 for any of the coordinates indicates that the coordinate is unknown.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param coordinate1 the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCoordinate1(final Object value) {
        return write(attributes.get(ATTR_COORDINATE1), value);
    }

    /**
     * <p>
     * Get the <i>Coordinate1</i> attribute [attribute ID <b>16</b>].
     * <p>
     * <br>
     * The Coordinate1, Coordinate2 and Coordinate3 attributes are signed 16-bit
     * integers, and represent orthogonal linear coordinates x, y, z in meters as follows.
     * <br>
     * x = Coordinate1 / 10, y = Coordinate2 / 10, z = Coordinate3 / 10
     * <br>
     * The range of x is -3276.7 to 3276.7 meters, corresponding to Coordinate1
     * between 0x8001 and 0x7fff. The same range applies to y and z. A value of
     * 0x8000 for any of the coordinates indicates that the coordinate is unknown.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCoordinate1Async() {
        return read(attributes.get(ATTR_COORDINATE1));
    }


    /**
     * <p>
     * Synchronously get the <i>Coordinate1</i> attribute [attribute ID <b>16</b>].
     * <p>
     * <br>
     * The Coordinate1, Coordinate2 and Coordinate3 attributes are signed 16-bit
     * integers, and represent orthogonal linear coordinates x, y, z in meters as follows.
     * <br>
     * x = Coordinate1 / 10, y = Coordinate2 / 10, z = Coordinate3 / 10
     * <br>
     * The range of x is -3276.7 to 3276.7 meters, corresponding to Coordinate1
     * between 0x8001 and 0x7fff. The same range applies to y and z. A value of
     * 0x8000 for any of the coordinates indicates that the coordinate is unknown.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getCoordinate1() {
        return (Integer) readSync(attributes.get(ATTR_COORDINATE1));
    }


    /**
     * <p>
     * Set the <i>Coordinate2</i> attribute [attribute ID <b>17</b>].
     * <p>
     * <br>
     * The Coordinate1, Coordinate2 and Coordinate3 attributes are signed 16-bit
     * integers, and represent orthogonal linear coordinates x, y, z in meters as follows.
     * <br>
     * x = Coordinate1 / 10, y = Coordinate2 / 10, z = Coordinate3 / 10
     * <br>
     * The range of x is -3276.7 to 3276.7 meters, corresponding to Coordinate1
     * between 0x8001 and 0x7fff. The same range applies to y and z. A value of
     * 0x8000 for any of the coordinates indicates that the coordinate is unknown.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param coordinate2 the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCoordinate2(final Object value) {
        return write(attributes.get(ATTR_COORDINATE2), value);
    }

    /**
     * <p>
     * Get the <i>Coordinate2</i> attribute [attribute ID <b>17</b>].
     * <p>
     * <br>
     * The Coordinate1, Coordinate2 and Coordinate3 attributes are signed 16-bit
     * integers, and represent orthogonal linear coordinates x, y, z in meters as follows.
     * <br>
     * x = Coordinate1 / 10, y = Coordinate2 / 10, z = Coordinate3 / 10
     * <br>
     * The range of x is -3276.7 to 3276.7 meters, corresponding to Coordinate1
     * between 0x8001 and 0x7fff. The same range applies to y and z. A value of
     * 0x8000 for any of the coordinates indicates that the coordinate is unknown.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCoordinate2Async() {
        return read(attributes.get(ATTR_COORDINATE2));
    }


    /**
     * <p>
     * Synchronously get the <i>Coordinate2</i> attribute [attribute ID <b>17</b>].
     * <p>
     * <br>
     * The Coordinate1, Coordinate2 and Coordinate3 attributes are signed 16-bit
     * integers, and represent orthogonal linear coordinates x, y, z in meters as follows.
     * <br>
     * x = Coordinate1 / 10, y = Coordinate2 / 10, z = Coordinate3 / 10
     * <br>
     * The range of x is -3276.7 to 3276.7 meters, corresponding to Coordinate1
     * between 0x8001 and 0x7fff. The same range applies to y and z. A value of
     * 0x8000 for any of the coordinates indicates that the coordinate is unknown.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getCoordinate2() {
        return (Integer) readSync(attributes.get(ATTR_COORDINATE2));
    }


    /**
     * <p>
     * Set the <i>Coordinate3</i> attribute [attribute ID <b>18</b>].
     * <p>
     * <br>
     * The Coordinate1, Coordinate2 and Coordinate3 attributes are signed 16-bit
     * integers, and represent orthogonal linear coordinates x, y, z in meters as follows.
     * <br>
     * x = Coordinate1 / 10, y = Coordinate2 / 10, z = Coordinate3 / 10
     * <br>
     * The range of x is -3276.7 to 3276.7 meters, corresponding to Coordinate1
     * between 0x8001 and 0x7fff. The same range applies to y and z. A value of
     * 0x8000 for any of the coordinates indicates that the coordinate is unknown.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param coordinate3 the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCoordinate3(final Object value) {
        return write(attributes.get(ATTR_COORDINATE3), value);
    }

    /**
     * <p>
     * Get the <i>Coordinate3</i> attribute [attribute ID <b>18</b>].
     * <p>
     * <br>
     * The Coordinate1, Coordinate2 and Coordinate3 attributes are signed 16-bit
     * integers, and represent orthogonal linear coordinates x, y, z in meters as follows.
     * <br>
     * x = Coordinate1 / 10, y = Coordinate2 / 10, z = Coordinate3 / 10
     * <br>
     * The range of x is -3276.7 to 3276.7 meters, corresponding to Coordinate1
     * between 0x8001 and 0x7fff. The same range applies to y and z. A value of
     * 0x8000 for any of the coordinates indicates that the coordinate is unknown.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCoordinate3Async() {
        return read(attributes.get(ATTR_COORDINATE3));
    }


    /**
     * <p>
     * Synchronously get the <i>Coordinate3</i> attribute [attribute ID <b>18</b>].
     * <p>
     * <br>
     * The Coordinate1, Coordinate2 and Coordinate3 attributes are signed 16-bit
     * integers, and represent orthogonal linear coordinates x, y, z in meters as follows.
     * <br>
     * x = Coordinate1 / 10, y = Coordinate2 / 10, z = Coordinate3 / 10
     * <br>
     * The range of x is -3276.7 to 3276.7 meters, corresponding to Coordinate1
     * between 0x8001 and 0x7fff. The same range applies to y and z. A value of
     * 0x8000 for any of the coordinates indicates that the coordinate is unknown.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getCoordinate3() {
        return (Integer) readSync(attributes.get(ATTR_COORDINATE3));
    }


    /**
     * <p>
     * Set the <i>Power</i> attribute [attribute ID <b>19</b>].
     * <p>
     * <br>
     * The Power attribute specifies the value of the average power P0, measured in
     * dBm, received at a reference distance of one meter from the transmitter.
     * <br>
     * P0 = Power / 100
     * <br>
     * A value of 0x8000 indicates that Power is unknown.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param power the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPower(final Object value) {
        return write(attributes.get(ATTR_POWER), value);
    }

    /**
     * <p>
     * Get the <i>Power</i> attribute [attribute ID <b>19</b>].
     * <p>
     * <br>
     * The Power attribute specifies the value of the average power P0, measured in
     * dBm, received at a reference distance of one meter from the transmitter.
     * <br>
     * P0 = Power / 100
     * <br>
     * A value of 0x8000 indicates that Power is unknown.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPowerAsync() {
        return read(attributes.get(ATTR_POWER));
    }


    /**
     * <p>
     * Synchronously get the <i>Power</i> attribute [attribute ID <b>19</b>].
     * <p>
     * <br>
     * The Power attribute specifies the value of the average power P0, measured in
     * dBm, received at a reference distance of one meter from the transmitter.
     * <br>
     * P0 = Power / 100
     * <br>
     * A value of 0x8000 indicates that Power is unknown.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getPower() {
        return (Integer) readSync(attributes.get(ATTR_POWER));
    }


    /**
     * <p>
     * Set the <i>PathLossExponent</i> attribute [attribute ID <b>20</b>].
     * <p>
     * <br>
     * The PathLossExponent attribute specifies the value of the Path Loss Exponent n,
     * an exponent that describes the rate at which the signal power decays with
     * increasing distance from the transmitter.
     * <br>
     * n = PathLossExponent / 100
     * <br>
     * A value of 0xffff indicates that PathLossExponent is unknown.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param pathLossExponent the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPathLossExponent(final Object value) {
        return write(attributes.get(ATTR_PATHLOSSEXPONENT), value);
    }

    /**
     * <p>
     * Get the <i>PathLossExponent</i> attribute [attribute ID <b>20</b>].
     * <p>
     * <br>
     * The PathLossExponent attribute specifies the value of the Path Loss Exponent n,
     * an exponent that describes the rate at which the signal power decays with
     * increasing distance from the transmitter.
     * <br>
     * n = PathLossExponent / 100
     * <br>
     * A value of 0xffff indicates that PathLossExponent is unknown.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPathLossExponentAsync() {
        return read(attributes.get(ATTR_PATHLOSSEXPONENT));
    }


    /**
     * <p>
     * Synchronously get the <i>PathLossExponent</i> attribute [attribute ID <b>20</b>].
     * <p>
     * <br>
     * The PathLossExponent attribute specifies the value of the Path Loss Exponent n,
     * an exponent that describes the rate at which the signal power decays with
     * increasing distance from the transmitter.
     * <br>
     * n = PathLossExponent / 100
     * <br>
     * A value of 0xffff indicates that PathLossExponent is unknown.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getPathLossExponent() {
        return (Integer) readSync(attributes.get(ATTR_PATHLOSSEXPONENT));
    }


    /**
     * <p>
     * Set the <i>ReportingPeriod</i> attribute [attribute ID <b>21</b>].
     * <p>
     * <br>
     * The ReportingPeriod attribute specifies the time in seconds between successive
     * reports of the device's location by means of the Location Data Notification
     * command. The minimum value this attribute can take is specified by the profile in
     * use. If ReportingPeriod is zero, the device does not automatically report its
     * location. Note that location information can always be polled at any time.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param reportingPeriod the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setReportingPeriod(final Object value) {
        return write(attributes.get(ATTR_REPORTINGPERIOD), value);
    }

    /**
     * <p>
     * Get the <i>ReportingPeriod</i> attribute [attribute ID <b>21</b>].
     * <p>
     * <br>
     * The ReportingPeriod attribute specifies the time in seconds between successive
     * reports of the device's location by means of the Location Data Notification
     * command. The minimum value this attribute can take is specified by the profile in
     * use. If ReportingPeriod is zero, the device does not automatically report its
     * location. Note that location information can always be polled at any time.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getReportingPeriodAsync() {
        return read(attributes.get(ATTR_REPORTINGPERIOD));
    }


    /**
     * <p>
     * Synchronously get the <i>ReportingPeriod</i> attribute [attribute ID <b>21</b>].
     * <p>
     * <br>
     * The ReportingPeriod attribute specifies the time in seconds between successive
     * reports of the device's location by means of the Location Data Notification
     * command. The minimum value this attribute can take is specified by the profile in
     * use. If ReportingPeriod is zero, the device does not automatically report its
     * location. Note that location information can always be polled at any time.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getReportingPeriod() {
        return (Integer) readSync(attributes.get(ATTR_REPORTINGPERIOD));
    }


    /**
     * <p>
     * Set the <i>CalculationPeriod</i> attribute [attribute ID <b>22</b>].
     * <p>
     * <br>
     * The CalculationPeriod attribute specifies the time in seconds between successive
     * calculations of the device's location. If CalculationPeriod is less than the
     * physically possible minimum period that the calculation can be performed, the
     * calculation will be repeated as frequently as possible.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param calculationPeriod the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCalculationPeriod(final Object value) {
        return write(attributes.get(ATTR_CALCULATIONPERIOD), value);
    }

    /**
     * <p>
     * Get the <i>CalculationPeriod</i> attribute [attribute ID <b>22</b>].
     * <p>
     * <br>
     * The CalculationPeriod attribute specifies the time in seconds between successive
     * calculations of the device's location. If CalculationPeriod is less than the
     * physically possible minimum period that the calculation can be performed, the
     * calculation will be repeated as frequently as possible.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCalculationPeriodAsync() {
        return read(attributes.get(ATTR_CALCULATIONPERIOD));
    }


    /**
     * <p>
     * Synchronously get the <i>CalculationPeriod</i> attribute [attribute ID <b>22</b>].
     * <p>
     * <br>
     * The CalculationPeriod attribute specifies the time in seconds between successive
     * calculations of the device's location. If CalculationPeriod is less than the
     * physically possible minimum period that the calculation can be performed, the
     * calculation will be repeated as frequently as possible.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getCalculationPeriod() {
        return (Integer) readSync(attributes.get(ATTR_CALCULATIONPERIOD));
    }


    /**
     * <p>
     * Set the <i>NumberRSSIMeasurements</i> attribute [attribute ID <b>23</b>].
     * <p>
     * <br>
     * The NumberRSSIMeasurements attribute specifies the number of RSSI
     * measurements to be used to generate one location estimate. The measurements are
     * averaged to improve accuracy. NumberRSSIMeasurements must be greater than or
     * equal to 1.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param numberRssiMeasurements the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setNumberRssiMeasurements(final Object value) {
        return write(attributes.get(ATTR_NUMBERRSSIMEASUREMENTS), value);
    }

    /**
     * <p>
     * Get the <i>NumberRSSIMeasurements</i> attribute [attribute ID <b>23</b>].
     * <p>
     * <br>
     * The NumberRSSIMeasurements attribute specifies the number of RSSI
     * measurements to be used to generate one location estimate. The measurements are
     * averaged to improve accuracy. NumberRSSIMeasurements must be greater than or
     * equal to 1.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getNumberRssiMeasurementsAsync() {
        return read(attributes.get(ATTR_NUMBERRSSIMEASUREMENTS));
    }


    /**
     * <p>
     * Synchronously get the <i>NumberRSSIMeasurements</i> attribute [attribute ID <b>23</b>].
     * <p>
     * <br>
     * The NumberRSSIMeasurements attribute specifies the number of RSSI
     * measurements to be used to generate one location estimate. The measurements are
     * averaged to improve accuracy. NumberRSSIMeasurements must be greater than or
     * equal to 1.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getNumberRssiMeasurements() {
        return (Integer) readSync(attributes.get(ATTR_NUMBERRSSIMEASUREMENTS));
    }

    /**
     * The Set Absolute Location Command
     *
     * @param coordinate1 {@link Integer} Coordinate 1
     * @param coordinate2 {@link Integer} Coordinate 2
     * @param coordinate3 {@link Integer} Coordinate 3
     * @param power {@link Integer} Power
     * @param pathLossExponent {@link Integer} Path Loss Exponent
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setAbsoluteLocationCommand(Integer coordinate1, Integer coordinate2, Integer coordinate3, Integer power, Integer pathLossExponent) {
        SetAbsoluteLocationCommand command = new SetAbsoluteLocationCommand();

        // Set the fields
        command.setCoordinate1(coordinate1);
        command.setCoordinate2(coordinate2);
        command.setCoordinate3(coordinate3);
        command.setPower(power);
        command.setPathLossExponent(pathLossExponent);

        return send(command);
    }

    /**
     * The Set Device Configuration Command
     *
     * @param power {@link Integer} Power
     * @param pathLossExponent {@link Integer} Path Loss Exponent
     * @param calculationPeriod {@link Integer} Calculation Period
     * @param numberRssiMeasurements {@link Integer} Number RSSI Measurements
     * @param reportingPeriod {@link Integer} Reporting Period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDeviceConfigurationCommand(Integer power, Integer pathLossExponent, Integer calculationPeriod, Integer numberRssiMeasurements, Integer reportingPeriod) {
        SetDeviceConfigurationCommand command = new SetDeviceConfigurationCommand();

        // Set the fields
        command.setPower(power);
        command.setPathLossExponent(pathLossExponent);
        command.setCalculationPeriod(calculationPeriod);
        command.setNumberRssiMeasurements(numberRssiMeasurements);
        command.setReportingPeriod(reportingPeriod);

        return send(command);
    }

    /**
     * The Get Device Configuration Command
     *
     * @param targetAddress {@link Long} Target Address
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDeviceConfigurationCommand(Long targetAddress) {
        GetDeviceConfigurationCommand command = new GetDeviceConfigurationCommand();

        // Set the fields
        command.setTargetAddress(targetAddress);

        return send(command);
    }

    /**
     * The Get Location Data Command
     *
     * @param header {@link Integer} Header
     * @param numberResponses {@link Integer} Number Responses
     * @param targetAddress {@link Long} Target Address
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLocationDataCommand(Integer header, Integer numberResponses, Long targetAddress) {
        GetLocationDataCommand command = new GetLocationDataCommand();

        // Set the fields
        command.setHeader(header);
        command.setNumberResponses(numberResponses);
        command.setTargetAddress(targetAddress);

        return send(command);
    }

    /**
     * The RSSI Response
     *
     * @param replyingDevice {@link Long} Replying Device
     * @param coordinate1 {@link Integer} Coordinate 1
     * @param coordinate2 {@link Integer} Coordinate 2
     * @param coordinate3 {@link Integer} Coordinate 3
     * @param rssi {@link Integer} RSSI
     * @param numberRssiMeasurements {@link Integer} Number RSSI Measurements
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> rssiResponse(Long replyingDevice, Integer coordinate1, Integer coordinate2, Integer coordinate3, Integer rssi, Integer numberRssiMeasurements) {
        RssiResponse command = new RssiResponse();

        // Set the fields
        command.setReplyingDevice(replyingDevice);
        command.setCoordinate1(coordinate1);
        command.setCoordinate2(coordinate2);
        command.setCoordinate3(coordinate3);
        command.setRssi(rssi);
        command.setNumberRssiMeasurements(numberRssiMeasurements);

        return send(command);
    }

    /**
     * The Send Pings Command
     *
     * @param targetAddress {@link Long} Target Address
     * @param numberRssiMeasurements {@link Integer} Number RSSI Measurements
     * @param calculationPeriod {@link Integer} Calculation Period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> sendPingsCommand(Long targetAddress, Integer numberRssiMeasurements, Integer calculationPeriod) {
        SendPingsCommand command = new SendPingsCommand();

        // Set the fields
        command.setTargetAddress(targetAddress);
        command.setNumberRssiMeasurements(numberRssiMeasurements);
        command.setCalculationPeriod(calculationPeriod);

        return send(command);
    }

    /**
     * The Anchor Node Announce Command
     *
     * @param anchorNodeAddress {@link Long} Anchor Node Address
     * @param coordinate1 {@link Integer} Coordinate 1
     * @param coordinate2 {@link Integer} Coordinate 2
     * @param coordinate3 {@link Integer} Coordinate 3
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> anchorNodeAnnounceCommand(Long anchorNodeAddress, Integer coordinate1, Integer coordinate2, Integer coordinate3) {
        AnchorNodeAnnounceCommand command = new AnchorNodeAnnounceCommand();

        // Set the fields
        command.setAnchorNodeAddress(anchorNodeAddress);
        command.setCoordinate1(coordinate1);
        command.setCoordinate2(coordinate2);
        command.setCoordinate3(coordinate3);

        return send(command);
    }

    /**
     * The Device Configuration Response
     *
     * @param status {@link Integer} Status
     * @param power {@link Integer} Power
     * @param pathLossExponent {@link Integer} Path Loss Exponent
     * @param calculationPeriod {@link Integer} Calculation Period
     * @param numberRssiMeasurements {@link Integer} Number RSSI Measurements
     * @param reportingPeriod {@link Integer} Reporting Period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> deviceConfigurationResponse(Integer status, Integer power, Integer pathLossExponent, Integer calculationPeriod, Integer numberRssiMeasurements, Integer reportingPeriod) {
        DeviceConfigurationResponse command = new DeviceConfigurationResponse();

        // Set the fields
        command.setStatus(status);
        command.setPower(power);
        command.setPathLossExponent(pathLossExponent);
        command.setCalculationPeriod(calculationPeriod);
        command.setNumberRssiMeasurements(numberRssiMeasurements);
        command.setReportingPeriod(reportingPeriod);

        return send(command);
    }

    /**
     * The Location Data Response
     *
     * @param status {@link Integer} Status
     * @param locationType {@link Integer} Location Type
     * @param coordinate1 {@link Integer} Coordinate 1
     * @param coordinate2 {@link Integer} Coordinate 2
     * @param coordinate3 {@link Integer} Coordinate 3
     * @param power {@link Integer} Power
     * @param pathLossExponent {@link Integer} Path Loss Exponent
     * @param locationMethod {@link Integer} Location Method
     * @param qualityMeasure {@link Integer} Quality Measure
     * @param locationAge {@link Integer} Location Age
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> locationDataResponse(Integer status, Integer locationType, Integer coordinate1, Integer coordinate2, Integer coordinate3, Integer power, Integer pathLossExponent, Integer locationMethod, Integer qualityMeasure, Integer locationAge) {
        LocationDataResponse command = new LocationDataResponse();

        // Set the fields
        command.setStatus(status);
        command.setLocationType(locationType);
        command.setCoordinate1(coordinate1);
        command.setCoordinate2(coordinate2);
        command.setCoordinate3(coordinate3);
        command.setPower(power);
        command.setPathLossExponent(pathLossExponent);
        command.setLocationMethod(locationMethod);
        command.setQualityMeasure(qualityMeasure);
        command.setLocationAge(locationAge);

        return send(command);
    }

    /**
     * The Location Data Notification Command
     *
     * @param locationType {@link Integer} Location Type
     * @param coordinate1 {@link Integer} Coordinate 1
     * @param coordinate2 {@link Integer} Coordinate 2
     * @param coordinate3 {@link Integer} Coordinate 3
     * @param power {@link Integer} Power
     * @param pathLossExponent {@link Integer} Path Loss Exponent
     * @param locationMethod {@link Integer} Location Method
     * @param qualityMeasure {@link Integer} Quality Measure
     * @param locationAge {@link Integer} Location Age
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> locationDataNotificationCommand(Integer locationType, Integer coordinate1, Integer coordinate2, Integer coordinate3, Integer power, Integer pathLossExponent, Integer locationMethod, Integer qualityMeasure, Integer locationAge) {
        LocationDataNotificationCommand command = new LocationDataNotificationCommand();

        // Set the fields
        command.setLocationType(locationType);
        command.setCoordinate1(coordinate1);
        command.setCoordinate2(coordinate2);
        command.setCoordinate3(coordinate3);
        command.setPower(power);
        command.setPathLossExponent(pathLossExponent);
        command.setLocationMethod(locationMethod);
        command.setQualityMeasure(qualityMeasure);
        command.setLocationAge(locationAge);

        return send(command);
    }

    /**
     * The Compact Location Data Notification Command
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> compactLocationDataNotificationCommand() {
        CompactLocationDataNotificationCommand command = new CompactLocationDataNotificationCommand();

        return send(command);
    }

    /**
     * The RSSI Ping Command
     *
     * @param locationType {@link Integer} Location Type
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> rssiPingCommand(Integer locationType) {
        RssiPingCommand command = new RssiPingCommand();

        // Set the fields
        command.setLocationType(locationType);

        return send(command);
    }

    /**
     * The RSSI Request Command
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> rssiRequestCommand() {
        RssiRequestCommand command = new RssiRequestCommand();

        return send(command);
    }

    /**
     * The Report RSSI Measurements Command
     *
     * @param reportingAddress {@link Long} Reporting Address
     * @param numberOfNeighbors {@link Integer} Number of Neighbors
     * @param neighborsInformation {@link List<NeighborInformation>} Neighbors Information
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> reportRssiMeasurementsCommand(Long reportingAddress, Integer numberOfNeighbors, List<NeighborInformation> neighborsInformation) {
        ReportRssiMeasurementsCommand command = new ReportRssiMeasurementsCommand();

        // Set the fields
        command.setReportingAddress(reportingAddress);
        command.setNumberOfNeighbors(numberOfNeighbors);
        command.setNeighborsInformation(neighborsInformation);

        return send(command);
    }

    /**
     * The Request Own Location Command
     *
     * @param requestingAddress {@link Long} Requesting Address
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> requestOwnLocationCommand(Long requestingAddress) {
        RequestOwnLocationCommand command = new RequestOwnLocationCommand();

        // Set the fields
        command.setRequestingAddress(requestingAddress);

        return send(command);
    }

    /**
     * Add a binding for this cluster to the local node
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> bind() {
        return bind();
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0: // SET_ABSOLUTE_LOCATION_COMMAND
                return new SetAbsoluteLocationCommand();
            case 1: // SET_DEVICE_CONFIGURATION_COMMAND
                return new SetDeviceConfigurationCommand();
            case 2: // GET_DEVICE_CONFIGURATION_COMMAND
                return new GetDeviceConfigurationCommand();
            case 3: // GET_LOCATION_DATA_COMMAND
                return new GetLocationDataCommand();
            case 4: // RSSI_RESPONSE
                return new RssiResponse();
            case 5: // SEND_PINGS_COMMAND
                return new SendPingsCommand();
            case 6: // ANCHOR_NODE_ANNOUNCE_COMMAND
                return new AnchorNodeAnnounceCommand();
            default:
                return null;
        }
    }

    @Override
    public ZclCommand getResponseFromId(int commandId) {
        switch (commandId) {
            case 0: // DEVICE_CONFIGURATION_RESPONSE
                return new DeviceConfigurationResponse();
            case 1: // LOCATION_DATA_RESPONSE
                return new LocationDataResponse();
            case 2: // LOCATION_DATA_NOTIFICATION_COMMAND
                return new LocationDataNotificationCommand();
            case 3: // COMPACT_LOCATION_DATA_NOTIFICATION_COMMAND
                return new CompactLocationDataNotificationCommand();
            case 4: // RSSI_PING_COMMAND
                return new RssiPingCommand();
            case 5: // RSSI_REQUEST_COMMAND
                return new RssiRequestCommand();
            case 6: // REPORT_RSSI_MEASUREMENTS_COMMAND
                return new ReportRssiMeasurementsCommand();
            case 7: // REQUEST_OWN_LOCATION_COMMAND
                return new RequestOwnLocationCommand();
            default:
                return null;
        }
    }
}
