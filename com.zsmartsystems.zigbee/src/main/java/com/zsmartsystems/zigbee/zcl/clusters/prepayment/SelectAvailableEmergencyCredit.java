/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.prepayment;

import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Select Available Emergency Credit value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command ID 0x00 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is sent to the Metering Device to activate the use of any Emergency Credit
 * available on the Metering Device.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class SelectAvailableEmergencyCredit extends ZclPrepaymentCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0705;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x00;

    /**
     * Command Issue Date Time command message field.
     */
    private Calendar commandIssueDateTime;

    /**
     * Originating Device command message field.
     */
    private Integer originatingDevice;

    /**
     * Site ID command message field.
     */
    private ByteArray siteId;

    /**
     * Meter Serial Number command message field.
     */
    private ByteArray meterSerialNumber;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public SelectAvailableEmergencyCredit() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param commandIssueDateTime {@link Calendar} Command Issue Date Time
     * @param originatingDevice {@link Integer} Originating Device
     * @param siteId {@link ByteArray} Site ID
     * @param meterSerialNumber {@link ByteArray} Meter Serial Number
     */
    public SelectAvailableEmergencyCredit(
            Calendar commandIssueDateTime,
            Integer originatingDevice,
            ByteArray siteId,
            ByteArray meterSerialNumber) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.commandIssueDateTime = commandIssueDateTime;
        this.originatingDevice = originatingDevice;
        this.siteId = siteId;
        this.meterSerialNumber = meterSerialNumber;
    }

    /**
     * Gets Command Issue Date Time.
     *
     * @return the Command Issue Date Time
     */
    public Calendar getCommandIssueDateTime() {
        return commandIssueDateTime;
    }

    /**
     * Sets Command Issue Date Time.
     *
     * @param commandIssueDateTime the Command Issue Date Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setCommandIssueDateTime(final Calendar commandIssueDateTime) {
        this.commandIssueDateTime = commandIssueDateTime;
    }

    /**
     * Gets Originating Device.
     *
     * @return the Originating Device
     */
    public Integer getOriginatingDevice() {
        return originatingDevice;
    }

    /**
     * Sets Originating Device.
     *
     * @param originatingDevice the Originating Device
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setOriginatingDevice(final Integer originatingDevice) {
        this.originatingDevice = originatingDevice;
    }

    /**
     * Gets Site ID.
     *
     * @return the Site ID
     */
    public ByteArray getSiteId() {
        return siteId;
    }

    /**
     * Sets Site ID.
     *
     * @param siteId the Site ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSiteId(final ByteArray siteId) {
        this.siteId = siteId;
    }

    /**
     * Gets Meter Serial Number.
     *
     * @return the Meter Serial Number
     */
    public ByteArray getMeterSerialNumber() {
        return meterSerialNumber;
    }

    /**
     * Sets Meter Serial Number.
     *
     * @param meterSerialNumber the Meter Serial Number
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setMeterSerialNumber(final ByteArray meterSerialNumber) {
        this.meterSerialNumber = meterSerialNumber;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(commandIssueDateTime, ZclDataType.UTCTIME);
        serializer.serialize(originatingDevice, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(siteId, ZclDataType.OCTET_STRING);
        serializer.serialize(meterSerialNumber, ZclDataType.OCTET_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        commandIssueDateTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        originatingDevice = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        siteId = (ByteArray) deserializer.deserialize(ZclDataType.OCTET_STRING);
        meterSerialNumber = (ByteArray) deserializer.deserialize(ZclDataType.OCTET_STRING);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(173);
        builder.append("SelectAvailableEmergencyCredit [");
        builder.append(super.toString());
        builder.append(", commandIssueDateTime=");
        builder.append(commandIssueDateTime);
        builder.append(", originatingDevice=");
        builder.append(originatingDevice);
        builder.append(", siteId=");
        builder.append(siteId);
        builder.append(", meterSerialNumber=");
        builder.append(meterSerialNumber);
        builder.append(']');
        return builder.toString();
    }

}
