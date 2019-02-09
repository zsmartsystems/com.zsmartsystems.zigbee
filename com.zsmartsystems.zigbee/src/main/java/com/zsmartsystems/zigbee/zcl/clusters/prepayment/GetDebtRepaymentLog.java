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
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Debt Repayment Log value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is used to request the contents of the repayment log.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetDebtRepaymentLog extends ZclCommand {
    /**
     * Latest End Time command message field.
     */
    private Calendar latestEndTime;

    /**
     * Number Of Debts command message field.
     */
    private Integer numberOfDebts;

    /**
     * Debt Type command message field.
     */
    private Integer debtType;

    /**
     * Default constructor.
     */
    public GetDebtRepaymentLog() {
        genericCommand = false;
        clusterId = 0x0705;
        commandId = 10;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Latest End Time.
     *
     * @return the Latest End Time
     */
    public Calendar getLatestEndTime() {
        return latestEndTime;
    }

    /**
     * Sets Latest End Time.
     *
     * @param latestEndTime the Latest End Time
     */
    public void setLatestEndTime(final Calendar latestEndTime) {
        this.latestEndTime = latestEndTime;
    }

    /**
     * Gets Number Of Debts.
     *
     * @return the Number Of Debts
     */
    public Integer getNumberOfDebts() {
        return numberOfDebts;
    }

    /**
     * Sets Number Of Debts.
     *
     * @param numberOfDebts the Number Of Debts
     */
    public void setNumberOfDebts(final Integer numberOfDebts) {
        this.numberOfDebts = numberOfDebts;
    }

    /**
     * Gets Debt Type.
     *
     * @return the Debt Type
     */
    public Integer getDebtType() {
        return debtType;
    }

    /**
     * Sets Debt Type.
     *
     * @param debtType the Debt Type
     */
    public void setDebtType(final Integer debtType) {
        this.debtType = debtType;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(latestEndTime, ZclDataType.UTCTIME);
        serializer.serialize(numberOfDebts, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(debtType, ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        latestEndTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        numberOfDebts = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        debtType = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(116);
        builder.append("GetDebtRepaymentLog [");
        builder.append(super.toString());
        builder.append(", latestEndTime=");
        builder.append(latestEndTime);
        builder.append(", numberOfDebts=");
        builder.append(numberOfDebts);
        builder.append(", debtType=");
        builder.append(debtType);
        builder.append(']');
        return builder.toString();
    }

}
