/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.prepayment;

import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Select Available Emergency Credit value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is sent to the Metering Device to activate the use of any Emergency Credit
 * available on the Metering Device.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class SelectAvailableEmergencyCredit extends ZclCommand {
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
     */
    public SelectAvailableEmergencyCredit() {
        genericCommand = false;
        clusterId = 0x0705;
        commandId = 0;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
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
     */
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
     */
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
     */
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
     */
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
