/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Debt Repayment Log value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command ID 0x0A is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is used to request the contents of the repayment log.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class GetDebtRepaymentLog extends ZclPrepaymentCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0705;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x0A;

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
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GetDebtRepaymentLog() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param latestEndTime {@link Calendar} Latest End Time
     * @param numberOfDebts {@link Integer} Number Of Debts
     * @param debtType {@link Integer} Debt Type
     */
    public GetDebtRepaymentLog(
            Calendar latestEndTime,
            Integer numberOfDebts,
            Integer debtType) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.latestEndTime = latestEndTime;
        this.numberOfDebts = numberOfDebts;
        this.debtType = debtType;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
