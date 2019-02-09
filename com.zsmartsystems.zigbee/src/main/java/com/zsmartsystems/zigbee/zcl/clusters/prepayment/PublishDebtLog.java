/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.prepayment;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.DebtPayload;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Publish Debt Log value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is used to send the contents of the Repayment Log.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class PublishDebtLog extends ZclCommand {
    /**
     * Command Index command message field.
     */
    private Integer commandIndex;

    /**
     * Total Number Of Commands command message field.
     */
    private Integer totalNumberOfCommands;

    /**
     * Debt Payload command message field.
     */
    private DebtPayload debtPayload;

    /**
     * Default constructor.
     */
    public PublishDebtLog() {
        genericCommand = false;
        clusterId = 0x0705;
        commandId = 6;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Command Index.
     *
     * @return the Command Index
     */
    public Integer getCommandIndex() {
        return commandIndex;
    }

    /**
     * Sets Command Index.
     *
     * @param commandIndex the Command Index
     */
    public void setCommandIndex(final Integer commandIndex) {
        this.commandIndex = commandIndex;
    }

    /**
     * Gets Total Number Of Commands.
     *
     * @return the Total Number Of Commands
     */
    public Integer getTotalNumberOfCommands() {
        return totalNumberOfCommands;
    }

    /**
     * Sets Total Number Of Commands.
     *
     * @param totalNumberOfCommands the Total Number Of Commands
     */
    public void setTotalNumberOfCommands(final Integer totalNumberOfCommands) {
        this.totalNumberOfCommands = totalNumberOfCommands;
    }

    /**
     * Gets Debt Payload.
     *
     * @return the Debt Payload
     */
    public DebtPayload getDebtPayload() {
        return debtPayload;
    }

    /**
     * Sets Debt Payload.
     *
     * @param debtPayload the Debt Payload
     */
    public void setDebtPayload(final DebtPayload debtPayload) {
        this.debtPayload = debtPayload;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(commandIndex, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(totalNumberOfCommands, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        debtPayload.serialize(serializer);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        commandIndex = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        totalNumberOfCommands = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        debtPayload = new DebtPayload();
        debtPayload.deserialize(deserializer);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(121);
        builder.append("PublishDebtLog [");
        builder.append(super.toString());
        builder.append(", commandIndex=");
        builder.append(commandIndex);
        builder.append(", totalNumberOfCommands=");
        builder.append(totalNumberOfCommands);
        builder.append(", debtPayload=");
        builder.append(debtPayload);
        builder.append(']');
        return builder.toString();
    }

}
