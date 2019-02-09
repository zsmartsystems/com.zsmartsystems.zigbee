/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.price;

import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Credit Payment Command value object class.
 * <p>
 * Cluster: <b>Price</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Price cluster.
 * <p>
 * This command initiates PublishCreditPayment commands for the requested credit payment
 * information.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetCreditPaymentCommand extends ZclCommand {
    /**
     * Latest End Time command message field.
     * <p>
     * UTCTime stamp indicating the latest CreditPaymentDate of records to be returned by the
     * corresponding PublishCreditPayment commands. The first returned
     * PublishCreditPayment command shall be the most recent record with its
     * CreditPaymentDate equal to or older than the Latest End Time provided.
     */
    private Calendar latestEndTime;

    /**
     * Number Of Records command message field.
     * <p>
     * An 8-bit integer that represents the maximum number of PublishCreditPayment commands
     * that the CLIENT is willing to receive in response to this command. A value of 0 would
     * indicate all available PublishCreditPayment commands shall be returned. If more than
     * one record is requested, the PublishCreditPayment commands should be returned with
     * descending ordered CreditPaymentDate. If fewer records are available than are being
     * requested, only those available are returned.
     */
    private Integer numberOfRecords;

    /**
     * Default constructor.
     */
    public GetCreditPaymentCommand() {
        genericCommand = false;
        clusterId = 0x0700;
        commandId = 14;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Latest End Time.
     * <p>
     * UTCTime stamp indicating the latest CreditPaymentDate of records to be returned by the
     * corresponding PublishCreditPayment commands. The first returned
     * PublishCreditPayment command shall be the most recent record with its
     * CreditPaymentDate equal to or older than the Latest End Time provided.
     *
     * @return the Latest End Time
     */
    public Calendar getLatestEndTime() {
        return latestEndTime;
    }

    /**
     * Sets Latest End Time.
     * <p>
     * UTCTime stamp indicating the latest CreditPaymentDate of records to be returned by the
     * corresponding PublishCreditPayment commands. The first returned
     * PublishCreditPayment command shall be the most recent record with its
     * CreditPaymentDate equal to or older than the Latest End Time provided.
     *
     * @param latestEndTime the Latest End Time
     */
    public void setLatestEndTime(final Calendar latestEndTime) {
        this.latestEndTime = latestEndTime;
    }

    /**
     * Gets Number Of Records.
     * <p>
     * An 8-bit integer that represents the maximum number of PublishCreditPayment commands
     * that the CLIENT is willing to receive in response to this command. A value of 0 would
     * indicate all available PublishCreditPayment commands shall be returned. If more than
     * one record is requested, the PublishCreditPayment commands should be returned with
     * descending ordered CreditPaymentDate. If fewer records are available than are being
     * requested, only those available are returned.
     *
     * @return the Number Of Records
     */
    public Integer getNumberOfRecords() {
        return numberOfRecords;
    }

    /**
     * Sets Number Of Records.
     * <p>
     * An 8-bit integer that represents the maximum number of PublishCreditPayment commands
     * that the CLIENT is willing to receive in response to this command. A value of 0 would
     * indicate all available PublishCreditPayment commands shall be returned. If more than
     * one record is requested, the PublishCreditPayment commands should be returned with
     * descending ordered CreditPaymentDate. If fewer records are available than are being
     * requested, only those available are returned.
     *
     * @param numberOfRecords the Number Of Records
     */
    public void setNumberOfRecords(final Integer numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(latestEndTime, ZclDataType.UTCTIME);
        serializer.serialize(numberOfRecords, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        latestEndTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        numberOfRecords = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(94);
        builder.append("GetCreditPaymentCommand [");
        builder.append(super.toString());
        builder.append(", latestEndTime=");
        builder.append(latestEndTime);
        builder.append(", numberOfRecords=");
        builder.append(numberOfRecords);
        builder.append(']');
        return builder.toString();
    }

}
