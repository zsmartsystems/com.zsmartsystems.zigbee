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
import com.zsmartsystems.zigbee.zcl.clusters.demandresponseandloadcontrol.CancelAllLoadControlEvents;
import com.zsmartsystems.zigbee.zcl.clusters.demandresponseandloadcontrol.CancelLoadControlEvent;
import com.zsmartsystems.zigbee.zcl.clusters.demandresponseandloadcontrol.GetScheduledEvents;
import com.zsmartsystems.zigbee.zcl.clusters.demandresponseandloadcontrol.LoadControlEventCommand;
import com.zsmartsystems.zigbee.zcl.clusters.demandresponseandloadcontrol.ReportEventStatus;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Demand Response And Load Control</b> cluster implementation (<i>Cluster ID 0x0701</i>).
 * <p>
 * This cluster provides an interface to the functionality of Smart Energy Demand Response and
 * Load Control. Devices targeted by this cluster include thermostats and devices that
 * support load control.
 * <p>
 * The ESI is defined as the Server due to its role in acting as the proxy for upstream demand
 * response/load control management systems and subsequent data stores.
 * <p>
 * A server device shall be capable of storing at least two load control events.
 * <p>
 * Events carried using this cluster include a timestamp with the assumption that target
 * devices maintain a real-time clock. If a device does not support a real-time clock, it is
 * assumed the device will ignore all values within the Time field except the “Start Now” value.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-26T20:57:36Z")
public class ZclDemandResponseAndLoadControlCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0701;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Demand Response And Load Control";

    // Attribute constants
    /**
     * The UtilityEnrollmentGroup provides a method for utilities to assign devices to
     * groups. In other words, Utility defined groups provide a mechanism to arbitrarily
     * group together different sets of load control or demand response devices for use as part
     * of a larger utility program. The definition of the groups, implied usage, and their
     * assigned values are dictated by the Utilities and subsequently used at their
     * discretion, therefore outside the scope of this specification. The valid range for
     * this attribute is 0x00 to 0xFF, where 0x00 (the default value) indicates the device is a
     * member of all groups and values 0x01 to 0xFF indicates that the device is member of that
     * specified group.
     */
    public static final int ATTR_UTILITYENROLLMENTGROUP = 0x0000;
    /**
     * The StartRandomizedMinutes represents the maximum number of minutes to be used when
     * randomizing the start of an event. As an example, if StartRandomizedMinutes is set for 3
     * minutes, the device could randomly select 2 minutes (but never greater than the 3
     * minutes) for this event, causing the start of the event to be delayed by two minutes. The
     * valid range for this attribute is 0x00 to 0x3C where 0x00 indicates start event
     * randomization is not performed.
     */
    public static final int ATTR_STARTRANDOMIZATIONMINUTES = 0x0001;
    /**
     * The EndRandomizedMinutes represents the maximum number of minutes to be used when
     * randomizing the end of an event. As an example, if EndRandomizedMinutes is set for 3
     * minutes, the device could randomly select one minute (but never greater than 3 minutes)
     * for this event, causing the end of the event to be delayed by one minute. The valid range
     * for this attribute is 0x00 to 0x3C where 0x00 indicates end event randomization is not
     * performed.
     */
    public static final int ATTR_ENDRANDOMIZATIONMINUTES = 0x0002;
    /**
     * The DeviceClassValue attribute identifies which bits the device will match in the
     * Device Class fields. Please refer to Table D-2, “Device Class Field BitMap/ Encoding”
     * for further details. Although the attribute has a read/write access property, the
     * device is permitted to refuse to change the DeviceClass by setting the status field of
     * the corresponding write attribute status record to NOT_AUTHORIZED.
     * <p>
     * Although, for backwards compatibility, the Type cannot be changed, this 16-bit
     * Integer should be treated as if it were a 16-bit BitMap.
     * <p>
     * Device Class and/or Utility Enrollment Group fields are to be used as filters for
     * deciding to accept or ignore a Load Control Event or a Cancel Load Control Event command.
     * There is no requirement for a device to store or remember the Device Class and/or Utility
     * Enrollment Group once the decision to accept the event has been made. A consequence of
     * this is that devices that accept multiple device classes may have an event created for
     * one device class superseded by an event created for another device class.
     */
    public static final int ATTR_DEVICECLASSVALUE = 0x0003;

    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<>(0);

        return attributeMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeServerCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentHashMap<>(3);

        commandMap.put(0x0000, LoadControlEventCommand.class);
        commandMap.put(0x0001, CancelLoadControlEvent.class);
        commandMap.put(0x0002, CancelAllLoadControlEvents.class);

        return commandMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentHashMap<>(2);

        commandMap.put(0x0000, ReportEventStatus.class);
        commandMap.put(0x0001, GetScheduledEvents.class);

        return commandMap;
    }

    /**
     * Default constructor to create a Demand Response And Load Control cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclDemandResponseAndLoadControlCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Set the <i>Utility Enrollment Group</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The UtilityEnrollmentGroup provides a method for utilities to assign devices to
     * groups. In other words, Utility defined groups provide a mechanism to arbitrarily
     * group together different sets of load control or demand response devices for use as part
     * of a larger utility program. The definition of the groups, implied usage, and their
     * assigned values are dictated by the Utilities and subsequently used at their
     * discretion, therefore outside the scope of this specification. The valid range for
     * this attribute is 0x00 to 0xFF, where 0x00 (the default value) indicates the device is a
     * member of all groups and values 0x01 to 0xFF indicates that the device is member of that
     * specified group.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param utilityEnrollmentGroup the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setUtilityEnrollmentGroup(final Integer value) {
        return write(attributes.get(ATTR_UTILITYENROLLMENTGROUP), value);
    }

    /**
     * Get the <i>Utility Enrollment Group</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The UtilityEnrollmentGroup provides a method for utilities to assign devices to
     * groups. In other words, Utility defined groups provide a mechanism to arbitrarily
     * group together different sets of load control or demand response devices for use as part
     * of a larger utility program. The definition of the groups, implied usage, and their
     * assigned values are dictated by the Utilities and subsequently used at their
     * discretion, therefore outside the scope of this specification. The valid range for
     * this attribute is 0x00 to 0xFF, where 0x00 (the default value) indicates the device is a
     * member of all groups and values 0x01 to 0xFF indicates that the device is member of that
     * specified group.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getUtilityEnrollmentGroupAsync() {
        return read(attributes.get(ATTR_UTILITYENROLLMENTGROUP));
    }

    /**
     * Synchronously get the <i>Utility Enrollment Group</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The UtilityEnrollmentGroup provides a method for utilities to assign devices to
     * groups. In other words, Utility defined groups provide a mechanism to arbitrarily
     * group together different sets of load control or demand response devices for use as part
     * of a larger utility program. The definition of the groups, implied usage, and their
     * assigned values are dictated by the Utilities and subsequently used at their
     * discretion, therefore outside the scope of this specification. The valid range for
     * this attribute is 0x00 to 0xFF, where 0x00 (the default value) indicates the device is a
     * member of all groups and values 0x01 to 0xFF indicates that the device is member of that
     * specified group.
     * <p>
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
    public Integer getUtilityEnrollmentGroup(final long refreshPeriod) {
        if (attributes.get(ATTR_UTILITYENROLLMENTGROUP).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_UTILITYENROLLMENTGROUP).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_UTILITYENROLLMENTGROUP));
    }

    /**
     * Set the <i>Start Randomization Minutes</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The StartRandomizedMinutes represents the maximum number of minutes to be used when
     * randomizing the start of an event. As an example, if StartRandomizedMinutes is set for 3
     * minutes, the device could randomly select 2 minutes (but never greater than the 3
     * minutes) for this event, causing the start of the event to be delayed by two minutes. The
     * valid range for this attribute is 0x00 to 0x3C where 0x00 indicates start event
     * randomization is not performed.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param startRandomizationMinutes the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setStartRandomizationMinutes(final Integer value) {
        return write(attributes.get(ATTR_STARTRANDOMIZATIONMINUTES), value);
    }

    /**
     * Get the <i>Start Randomization Minutes</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The StartRandomizedMinutes represents the maximum number of minutes to be used when
     * randomizing the start of an event. As an example, if StartRandomizedMinutes is set for 3
     * minutes, the device could randomly select 2 minutes (but never greater than the 3
     * minutes) for this event, causing the start of the event to be delayed by two minutes. The
     * valid range for this attribute is 0x00 to 0x3C where 0x00 indicates start event
     * randomization is not performed.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getStartRandomizationMinutesAsync() {
        return read(attributes.get(ATTR_STARTRANDOMIZATIONMINUTES));
    }

    /**
     * Synchronously get the <i>Start Randomization Minutes</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The StartRandomizedMinutes represents the maximum number of minutes to be used when
     * randomizing the start of an event. As an example, if StartRandomizedMinutes is set for 3
     * minutes, the device could randomly select 2 minutes (but never greater than the 3
     * minutes) for this event, causing the start of the event to be delayed by two minutes. The
     * valid range for this attribute is 0x00 to 0x3C where 0x00 indicates start event
     * randomization is not performed.
     * <p>
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
    public Integer getStartRandomizationMinutes(final long refreshPeriod) {
        if (attributes.get(ATTR_STARTRANDOMIZATIONMINUTES).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_STARTRANDOMIZATIONMINUTES).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_STARTRANDOMIZATIONMINUTES));
    }

    /**
     * Set the <i>End Randomization Minutes</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The EndRandomizedMinutes represents the maximum number of minutes to be used when
     * randomizing the end of an event. As an example, if EndRandomizedMinutes is set for 3
     * minutes, the device could randomly select one minute (but never greater than 3 minutes)
     * for this event, causing the end of the event to be delayed by one minute. The valid range
     * for this attribute is 0x00 to 0x3C where 0x00 indicates end event randomization is not
     * performed.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param endRandomizationMinutes the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setEndRandomizationMinutes(final Integer value) {
        return write(attributes.get(ATTR_ENDRANDOMIZATIONMINUTES), value);
    }

    /**
     * Get the <i>End Randomization Minutes</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The EndRandomizedMinutes represents the maximum number of minutes to be used when
     * randomizing the end of an event. As an example, if EndRandomizedMinutes is set for 3
     * minutes, the device could randomly select one minute (but never greater than 3 minutes)
     * for this event, causing the end of the event to be delayed by one minute. The valid range
     * for this attribute is 0x00 to 0x3C where 0x00 indicates end event randomization is not
     * performed.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getEndRandomizationMinutesAsync() {
        return read(attributes.get(ATTR_ENDRANDOMIZATIONMINUTES));
    }

    /**
     * Synchronously get the <i>End Randomization Minutes</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The EndRandomizedMinutes represents the maximum number of minutes to be used when
     * randomizing the end of an event. As an example, if EndRandomizedMinutes is set for 3
     * minutes, the device could randomly select one minute (but never greater than 3 minutes)
     * for this event, causing the end of the event to be delayed by one minute. The valid range
     * for this attribute is 0x00 to 0x3C where 0x00 indicates end event randomization is not
     * performed.
     * <p>
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
    public Integer getEndRandomizationMinutes(final long refreshPeriod) {
        if (attributes.get(ATTR_ENDRANDOMIZATIONMINUTES).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ENDRANDOMIZATIONMINUTES).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ENDRANDOMIZATIONMINUTES));
    }

    /**
     * Set the <i>Device Class Value</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The DeviceClassValue attribute identifies which bits the device will match in the
     * Device Class fields. Please refer to Table D-2, “Device Class Field BitMap/ Encoding”
     * for further details. Although the attribute has a read/write access property, the
     * device is permitted to refuse to change the DeviceClass by setting the status field of
     * the corresponding write attribute status record to NOT_AUTHORIZED.
     * <p>
     * Although, for backwards compatibility, the Type cannot be changed, this 16-bit
     * Integer should be treated as if it were a 16-bit BitMap.
     * <p>
     * Device Class and/or Utility Enrollment Group fields are to be used as filters for
     * deciding to accept or ignore a Load Control Event or a Cancel Load Control Event command.
     * There is no requirement for a device to store or remember the Device Class and/or Utility
     * Enrollment Group once the decision to accept the event has been made. A consequence of
     * this is that devices that accept multiple device classes may have an event created for
     * one device class superseded by an event created for another device class.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param deviceClassValue the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDeviceClassValue(final Integer value) {
        return write(attributes.get(ATTR_DEVICECLASSVALUE), value);
    }

    /**
     * Get the <i>Device Class Value</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The DeviceClassValue attribute identifies which bits the device will match in the
     * Device Class fields. Please refer to Table D-2, “Device Class Field BitMap/ Encoding”
     * for further details. Although the attribute has a read/write access property, the
     * device is permitted to refuse to change the DeviceClass by setting the status field of
     * the corresponding write attribute status record to NOT_AUTHORIZED.
     * <p>
     * Although, for backwards compatibility, the Type cannot be changed, this 16-bit
     * Integer should be treated as if it were a 16-bit BitMap.
     * <p>
     * Device Class and/or Utility Enrollment Group fields are to be used as filters for
     * deciding to accept or ignore a Load Control Event or a Cancel Load Control Event command.
     * There is no requirement for a device to store or remember the Device Class and/or Utility
     * Enrollment Group once the decision to accept the event has been made. A consequence of
     * this is that devices that accept multiple device classes may have an event created for
     * one device class superseded by an event created for another device class.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDeviceClassValueAsync() {
        return read(attributes.get(ATTR_DEVICECLASSVALUE));
    }

    /**
     * Synchronously get the <i>Device Class Value</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The DeviceClassValue attribute identifies which bits the device will match in the
     * Device Class fields. Please refer to Table D-2, “Device Class Field BitMap/ Encoding”
     * for further details. Although the attribute has a read/write access property, the
     * device is permitted to refuse to change the DeviceClass by setting the status field of
     * the corresponding write attribute status record to NOT_AUTHORIZED.
     * <p>
     * Although, for backwards compatibility, the Type cannot be changed, this 16-bit
     * Integer should be treated as if it were a 16-bit BitMap.
     * <p>
     * Device Class and/or Utility Enrollment Group fields are to be used as filters for
     * deciding to accept or ignore a Load Control Event or a Cancel Load Control Event command.
     * There is no requirement for a device to store or remember the Device Class and/or Utility
     * Enrollment Group once the decision to accept the event has been made. A consequence of
     * this is that devices that accept multiple device classes may have an event created for
     * one device class superseded by an event created for another device class.
     * <p>
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
    public Integer getDeviceClassValue(final long refreshPeriod) {
        if (attributes.get(ATTR_DEVICECLASSVALUE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEVICECLASSVALUE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEVICECLASSVALUE));
    }

    /**
     * The Report Event Status
     *
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param eventStatus {@link Integer} Event Status
     * @param eventStatusTime {@link Calendar} Event Status Time
     * @param criticalityLevelApplied {@link Integer} Criticality Level Applied
     * @param coolingTemperatureSetPointApplied {@link Integer} Cooling Temperature Set Point Applied
     * @param heatingTemperatureSetPointApplied {@link Integer} Heating Temperature Set Point Applied
     * @param averageLoadAdjustmentPercentageApplied {@link Integer} Average Load Adjustment Percentage Applied
     * @param dutyCycleApplied {@link Integer} Duty Cycle Applied
     * @param eventControl {@link Integer} Event Control
     * @param signatureType {@link Integer} Signature Type
     * @param signature {@link ByteArray} Signature
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> reportEventStatus(Integer issuerEventId, Integer eventStatus, Calendar eventStatusTime, Integer criticalityLevelApplied, Integer coolingTemperatureSetPointApplied, Integer heatingTemperatureSetPointApplied, Integer averageLoadAdjustmentPercentageApplied, Integer dutyCycleApplied, Integer eventControl, Integer signatureType, ByteArray signature) {
        ReportEventStatus command = new ReportEventStatus();

        // Set the fields
        command.setIssuerEventId(issuerEventId);
        command.setEventStatus(eventStatus);
        command.setEventStatusTime(eventStatusTime);
        command.setCriticalityLevelApplied(criticalityLevelApplied);
        command.setCoolingTemperatureSetPointApplied(coolingTemperatureSetPointApplied);
        command.setHeatingTemperatureSetPointApplied(heatingTemperatureSetPointApplied);
        command.setAverageLoadAdjustmentPercentageApplied(averageLoadAdjustmentPercentageApplied);
        command.setDutyCycleApplied(dutyCycleApplied);
        command.setEventControl(eventControl);
        command.setSignatureType(signatureType);
        command.setSignature(signature);

        return send(command);
    }

    /**
     * The Get Scheduled Events
     * <p>
     * This command is used to request that all scheduled Load Control Events, starting at or
     * after the supplied Start Time, are re-issued to the requesting device. When received by
     * the Server, one or more Load Control Event commands will be sent covering both active and
     * scheduled Load Control Events.
     *
     * @param startTime {@link Calendar} Start Time
     * @param numberOfEvents {@link Integer} Number Of Events
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getScheduledEvents(Calendar startTime, Integer numberOfEvents) {
        GetScheduledEvents command = new GetScheduledEvents();

        // Set the fields
        command.setStartTime(startTime);
        command.setNumberOfEvents(numberOfEvents);

        return send(command);
    }

    /**
     * The Load Control Event Command
     *
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param deviceClass {@link Integer} Device Class
     * @param utilityEnrollmentGroup {@link Integer} Utility Enrollment Group
     * @param startTime {@link Calendar} Start Time
     * @param durationInMinutes {@link Integer} Duration In Minutes
     * @param criticalityLevel {@link Integer} Criticality Level
     * @param coolingTemperatureOffset {@link Integer} Cooling Temperature Offset
     * @param heatingTemperatureOffset {@link Integer} Heating Temperature Offset
     * @param coolingTemperatureSetPoint {@link Integer} Cooling Temperature Set Point
     * @param heatingTemperatureSetPoint {@link Integer} Heating Temperature Set Point
     * @param averageLoadAdjustmentPercentage {@link Integer} Average Load Adjustment Percentage
     * @param dutyCycle {@link Integer} Duty Cycle
     * @param eventControl {@link Integer} Event Control
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> loadControlEventCommand(Integer issuerEventId, Integer deviceClass, Integer utilityEnrollmentGroup, Calendar startTime, Integer durationInMinutes, Integer criticalityLevel, Integer coolingTemperatureOffset, Integer heatingTemperatureOffset, Integer coolingTemperatureSetPoint, Integer heatingTemperatureSetPoint, Integer averageLoadAdjustmentPercentage, Integer dutyCycle, Integer eventControl) {
        LoadControlEventCommand command = new LoadControlEventCommand();

        // Set the fields
        command.setIssuerEventId(issuerEventId);
        command.setDeviceClass(deviceClass);
        command.setUtilityEnrollmentGroup(utilityEnrollmentGroup);
        command.setStartTime(startTime);
        command.setDurationInMinutes(durationInMinutes);
        command.setCriticalityLevel(criticalityLevel);
        command.setCoolingTemperatureOffset(coolingTemperatureOffset);
        command.setHeatingTemperatureOffset(heatingTemperatureOffset);
        command.setCoolingTemperatureSetPoint(coolingTemperatureSetPoint);
        command.setHeatingTemperatureSetPoint(heatingTemperatureSetPoint);
        command.setAverageLoadAdjustmentPercentage(averageLoadAdjustmentPercentage);
        command.setDutyCycle(dutyCycle);
        command.setEventControl(eventControl);

        return send(command);
    }

    /**
     * The Cancel Load Control Event
     *
     * @param issuerEventId {@link Integer} Issuer Event ID
     * @param deviceClass {@link Integer} Device Class
     * @param utilityEnrollmentGroup {@link Integer} Utility Enrollment Group
     * @param cancelControl {@link Integer} Cancel Control
     * @param effectiveTime {@link Calendar} Effective Time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> cancelLoadControlEvent(Integer issuerEventId, Integer deviceClass, Integer utilityEnrollmentGroup, Integer cancelControl, Calendar effectiveTime) {
        CancelLoadControlEvent command = new CancelLoadControlEvent();

        // Set the fields
        command.setIssuerEventId(issuerEventId);
        command.setDeviceClass(deviceClass);
        command.setUtilityEnrollmentGroup(utilityEnrollmentGroup);
        command.setCancelControl(cancelControl);
        command.setEffectiveTime(effectiveTime);

        return send(command);
    }

    /**
     * The Cancel All Load Control Events
     *
     * @param cancelControl {@link Integer} Cancel Control
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> cancelAllLoadControlEvents(Integer cancelControl) {
        CancelAllLoadControlEvents command = new CancelAllLoadControlEvents();

        // Set the fields
        command.setCancelControl(cancelControl);

        return send(command);
    }
}
