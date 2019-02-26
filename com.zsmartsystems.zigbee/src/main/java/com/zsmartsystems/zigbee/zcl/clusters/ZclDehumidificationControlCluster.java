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
 * <b>Dehumidification Control</b> cluster implementation (<i>Cluster ID 0x0203</i>).
 * <p>
 * This cluster provides an interface to dehumidification functionality.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-26T21:33:25Z")
public class ZclDehumidificationControlCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0203;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Dehumidification Control";

    // Attribute constants
    /**
     * The RelativeHumidity attribute is an 8-bit value that represents the current relative
     * humidity (in %) measured by a local or remote sensor. The valid range ix 0x00 – 0x64 (0% to
     * 100%).
     */
    public static final int ATTR_RELATIVEHUMIDITY = 0x0000;
    /**
     * The DehumidificationCooling attribute is an 8-bit value that specifies the current
     * dehumidification cooling output (in %). The valid range is 0 to
     * DehumidificationMaxCool.
     */
    public static final int ATTR_DEHUMIDIFICATIONCOOLING = 0x0001;
    /**
     * The RHDehumidificationSetpoint attribute is an 8-bit value that represents the
     * relative humidity (in %) at which dehumidification occurs. The valid range ix 0x1E –
     * 0x64 (30% to 100%).
     */
    public static final int ATTR_RHDEHUMIDIFICATIONSETPOINT = 0x0010;
    /**
     * The RelativeHumidityMode attribute is an 8-bit value that specifies how the
     * RelativeHumidity value is being updated.
     */
    public static final int ATTR_RELATIVEHUMIDITYMODE = 0x0011;
    /**
     * The DehumidificationLockout attribute is an 8-bit value that specifies whether
     * dehumidification is allowed or not.
     */
    public static final int ATTR_DEHUMIDIFICATIONLOCKOUT = 0x0012;
    /**
     * The DehumidificationHysteresis attribute is an 8-bit value that specifies the
     * hysteresis (in %) associated with RelativeHumidity value.
     */
    public static final int ATTR_DEHUMIDIFICATIONHYSTERESIS = 0x0013;
    /**
     * The DehumidificationMaxCool attribute is an 8-bit value that specifies the maximum
     * dehumidification cooling output (in %). The valid range ix 0x14 – 0x64 (20% to 100%).
     */
    public static final int ATTR_DEHUMIDIFICATIONMAXCOOL = 0x0014;
    /**
     * The RelativeHumidityDisplay attribute is an 8-bit value that specifies whether the
     * RelativeHumidity value is displayed to the user or not.
     */
    public static final int ATTR_RELATIVEHUMIDITYDISPLAY = 0x0015;

    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<>(8);

        attributeMap.put(ATTR_RELATIVEHUMIDITY, new ZclAttribute(ZclClusterType.DEHUMIDIFICATION_CONTROL, ATTR_RELATIVEHUMIDITY, "Relative Humidity", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_DEHUMIDIFICATIONCOOLING, new ZclAttribute(ZclClusterType.DEHUMIDIFICATION_CONTROL, ATTR_DEHUMIDIFICATIONCOOLING, "Dehumidification Cooling", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RHDEHUMIDIFICATIONSETPOINT, new ZclAttribute(ZclClusterType.DEHUMIDIFICATION_CONTROL, ATTR_RHDEHUMIDIFICATIONSETPOINT, "Rh Dehumidification Setpoint", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_RELATIVEHUMIDITYMODE, new ZclAttribute(ZclClusterType.DEHUMIDIFICATION_CONTROL, ATTR_RELATIVEHUMIDITYMODE, "Relative Humidity Mode", ZclDataType.ENUMERATION_8_BIT, false, true, true, true));
        attributeMap.put(ATTR_DEHUMIDIFICATIONLOCKOUT, new ZclAttribute(ZclClusterType.DEHUMIDIFICATION_CONTROL, ATTR_DEHUMIDIFICATIONLOCKOUT, "Dehumidification Lockout", ZclDataType.ENUMERATION_8_BIT, false, true, true, true));
        attributeMap.put(ATTR_DEHUMIDIFICATIONHYSTERESIS, new ZclAttribute(ZclClusterType.DEHUMIDIFICATION_CONTROL, ATTR_DEHUMIDIFICATIONHYSTERESIS, "Dehumidification Hysteresis", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_DEHUMIDIFICATIONMAXCOOL, new ZclAttribute(ZclClusterType.DEHUMIDIFICATION_CONTROL, ATTR_DEHUMIDIFICATIONMAXCOOL, "Dehumidification Max Cool", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_RELATIVEHUMIDITYDISPLAY, new ZclAttribute(ZclClusterType.DEHUMIDIFICATION_CONTROL, ATTR_RELATIVEHUMIDITYDISPLAY, "Relative Humidity Display", ZclDataType.ENUMERATION_8_BIT, false, true, true, true));

        return attributeMap;
    }

    /**
     * Default constructor to create a Dehumidification Control cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclDehumidificationControlCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>Relative Humidity</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The RelativeHumidity attribute is an 8-bit value that represents the current relative
     * humidity (in %) measured by a local or remote sensor. The valid range ix 0x00 – 0x64 (0% to
     * 100%).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRelativeHumidityAsync() {
        return read(attributes.get(ATTR_RELATIVEHUMIDITY));
    }

    /**
     * Synchronously get the <i>Relative Humidity</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The RelativeHumidity attribute is an 8-bit value that represents the current relative
     * humidity (in %) measured by a local or remote sensor. The valid range ix 0x00 – 0x64 (0% to
     * 100%).
     * <p>
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
    public Integer getRelativeHumidity(final long refreshPeriod) {
        if (attributes.get(ATTR_RELATIVEHUMIDITY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RELATIVEHUMIDITY).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RELATIVEHUMIDITY));
    }

    /**
     * Set reporting for the <i>Relative Humidity</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The RelativeHumidity attribute is an 8-bit value that represents the current relative
     * humidity (in %) measured by a local or remote sensor. The valid range ix 0x00 – 0x64 (0% to
     * 100%).
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
    public Future<CommandResult> setRelativeHumidityReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_RELATIVEHUMIDITY), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Dehumidification Cooling</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The DehumidificationCooling attribute is an 8-bit value that specifies the current
     * dehumidification cooling output (in %). The valid range is 0 to
     * DehumidificationMaxCool.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDehumidificationCoolingAsync() {
        return read(attributes.get(ATTR_DEHUMIDIFICATIONCOOLING));
    }

    /**
     * Synchronously get the <i>Dehumidification Cooling</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The DehumidificationCooling attribute is an 8-bit value that specifies the current
     * dehumidification cooling output (in %). The valid range is 0 to
     * DehumidificationMaxCool.
     * <p>
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
    public Integer getDehumidificationCooling(final long refreshPeriod) {
        if (attributes.get(ATTR_DEHUMIDIFICATIONCOOLING).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEHUMIDIFICATIONCOOLING).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEHUMIDIFICATIONCOOLING));
    }

    /**
     * Set reporting for the <i>Dehumidification Cooling</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The DehumidificationCooling attribute is an 8-bit value that specifies the current
     * dehumidification cooling output (in %). The valid range is 0 to
     * DehumidificationMaxCool.
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
    public Future<CommandResult> setDehumidificationCoolingReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_DEHUMIDIFICATIONCOOLING), minInterval, maxInterval, reportableChange);
    }

    /**
     * Set the <i>Rh Dehumidification Setpoint</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The RHDehumidificationSetpoint attribute is an 8-bit value that represents the
     * relative humidity (in %) at which dehumidification occurs. The valid range ix 0x1E –
     * 0x64 (30% to 100%).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param rhDehumidificationSetpoint the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setRhDehumidificationSetpoint(final Integer value) {
        return write(attributes.get(ATTR_RHDEHUMIDIFICATIONSETPOINT), value);
    }

    /**
     * Get the <i>Rh Dehumidification Setpoint</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The RHDehumidificationSetpoint attribute is an 8-bit value that represents the
     * relative humidity (in %) at which dehumidification occurs. The valid range ix 0x1E –
     * 0x64 (30% to 100%).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRhDehumidificationSetpointAsync() {
        return read(attributes.get(ATTR_RHDEHUMIDIFICATIONSETPOINT));
    }

    /**
     * Synchronously get the <i>Rh Dehumidification Setpoint</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The RHDehumidificationSetpoint attribute is an 8-bit value that represents the
     * relative humidity (in %) at which dehumidification occurs. The valid range ix 0x1E –
     * 0x64 (30% to 100%).
     * <p>
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
    public Integer getRhDehumidificationSetpoint(final long refreshPeriod) {
        if (attributes.get(ATTR_RHDEHUMIDIFICATIONSETPOINT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RHDEHUMIDIFICATIONSETPOINT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RHDEHUMIDIFICATIONSETPOINT));
    }

    /**
     * Set the <i>Relative Humidity Mode</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The RelativeHumidityMode attribute is an 8-bit value that specifies how the
     * RelativeHumidity value is being updated.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param relativeHumidityMode the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setRelativeHumidityMode(final Integer value) {
        return write(attributes.get(ATTR_RELATIVEHUMIDITYMODE), value);
    }

    /**
     * Get the <i>Relative Humidity Mode</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The RelativeHumidityMode attribute is an 8-bit value that specifies how the
     * RelativeHumidity value is being updated.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRelativeHumidityModeAsync() {
        return read(attributes.get(ATTR_RELATIVEHUMIDITYMODE));
    }

    /**
     * Synchronously get the <i>Relative Humidity Mode</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The RelativeHumidityMode attribute is an 8-bit value that specifies how the
     * RelativeHumidity value is being updated.
     * <p>
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
    public Integer getRelativeHumidityMode(final long refreshPeriod) {
        if (attributes.get(ATTR_RELATIVEHUMIDITYMODE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RELATIVEHUMIDITYMODE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RELATIVEHUMIDITYMODE));
    }

    /**
     * Set the <i>Dehumidification Lockout</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The DehumidificationLockout attribute is an 8-bit value that specifies whether
     * dehumidification is allowed or not.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param dehumidificationLockout the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDehumidificationLockout(final Integer value) {
        return write(attributes.get(ATTR_DEHUMIDIFICATIONLOCKOUT), value);
    }

    /**
     * Get the <i>Dehumidification Lockout</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The DehumidificationLockout attribute is an 8-bit value that specifies whether
     * dehumidification is allowed or not.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDehumidificationLockoutAsync() {
        return read(attributes.get(ATTR_DEHUMIDIFICATIONLOCKOUT));
    }

    /**
     * Synchronously get the <i>Dehumidification Lockout</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The DehumidificationLockout attribute is an 8-bit value that specifies whether
     * dehumidification is allowed or not.
     * <p>
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
    public Integer getDehumidificationLockout(final long refreshPeriod) {
        if (attributes.get(ATTR_DEHUMIDIFICATIONLOCKOUT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEHUMIDIFICATIONLOCKOUT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEHUMIDIFICATIONLOCKOUT));
    }

    /**
     * Set the <i>Dehumidification Hysteresis</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The DehumidificationHysteresis attribute is an 8-bit value that specifies the
     * hysteresis (in %) associated with RelativeHumidity value.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param dehumidificationHysteresis the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDehumidificationHysteresis(final Integer value) {
        return write(attributes.get(ATTR_DEHUMIDIFICATIONHYSTERESIS), value);
    }

    /**
     * Get the <i>Dehumidification Hysteresis</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The DehumidificationHysteresis attribute is an 8-bit value that specifies the
     * hysteresis (in %) associated with RelativeHumidity value.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDehumidificationHysteresisAsync() {
        return read(attributes.get(ATTR_DEHUMIDIFICATIONHYSTERESIS));
    }

    /**
     * Synchronously get the <i>Dehumidification Hysteresis</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The DehumidificationHysteresis attribute is an 8-bit value that specifies the
     * hysteresis (in %) associated with RelativeHumidity value.
     * <p>
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
    public Integer getDehumidificationHysteresis(final long refreshPeriod) {
        if (attributes.get(ATTR_DEHUMIDIFICATIONHYSTERESIS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEHUMIDIFICATIONHYSTERESIS).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEHUMIDIFICATIONHYSTERESIS));
    }

    /**
     * Set the <i>Dehumidification Max Cool</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The DehumidificationMaxCool attribute is an 8-bit value that specifies the maximum
     * dehumidification cooling output (in %). The valid range ix 0x14 – 0x64 (20% to 100%).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param dehumidificationMaxCool the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDehumidificationMaxCool(final Integer value) {
        return write(attributes.get(ATTR_DEHUMIDIFICATIONMAXCOOL), value);
    }

    /**
     * Get the <i>Dehumidification Max Cool</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The DehumidificationMaxCool attribute is an 8-bit value that specifies the maximum
     * dehumidification cooling output (in %). The valid range ix 0x14 – 0x64 (20% to 100%).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDehumidificationMaxCoolAsync() {
        return read(attributes.get(ATTR_DEHUMIDIFICATIONMAXCOOL));
    }

    /**
     * Synchronously get the <i>Dehumidification Max Cool</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The DehumidificationMaxCool attribute is an 8-bit value that specifies the maximum
     * dehumidification cooling output (in %). The valid range ix 0x14 – 0x64 (20% to 100%).
     * <p>
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
    public Integer getDehumidificationMaxCool(final long refreshPeriod) {
        if (attributes.get(ATTR_DEHUMIDIFICATIONMAXCOOL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEHUMIDIFICATIONMAXCOOL).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEHUMIDIFICATIONMAXCOOL));
    }

    /**
     * Set the <i>Relative Humidity Display</i> attribute [attribute ID <b>0x0015</b>].
     * <p>
     * The RelativeHumidityDisplay attribute is an 8-bit value that specifies whether the
     * RelativeHumidity value is displayed to the user or not.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param relativeHumidityDisplay the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setRelativeHumidityDisplay(final Integer value) {
        return write(attributes.get(ATTR_RELATIVEHUMIDITYDISPLAY), value);
    }

    /**
     * Get the <i>Relative Humidity Display</i> attribute [attribute ID <b>0x0015</b>].
     * <p>
     * The RelativeHumidityDisplay attribute is an 8-bit value that specifies whether the
     * RelativeHumidity value is displayed to the user or not.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRelativeHumidityDisplayAsync() {
        return read(attributes.get(ATTR_RELATIVEHUMIDITYDISPLAY));
    }

    /**
     * Synchronously get the <i>Relative Humidity Display</i> attribute [attribute ID <b>0x0015</b>].
     * <p>
     * The RelativeHumidityDisplay attribute is an 8-bit value that specifies whether the
     * RelativeHumidity value is displayed to the user or not.
     * <p>
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
    public Integer getRelativeHumidityDisplay(final long refreshPeriod) {
        if (attributes.get(ATTR_RELATIVEHUMIDITYDISPLAY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RELATIVEHUMIDITYDISPLAY).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RELATIVEHUMIDITYDISPLAY));
    }
}
