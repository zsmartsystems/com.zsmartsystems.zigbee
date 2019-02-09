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
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Set Low Credit Warning Level value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is sent from client to a Prepayment server to set the warning level for low
 * credit.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class SetLowCreditWarningLevel extends ZclCommand {
    /**
     * Low Credit Warning Level command message field.
     */
    private Integer lowCreditWarningLevel;

    /**
     * Default constructor.
     */
    public SetLowCreditWarningLevel() {
        genericCommand = false;
        clusterId = 0x0705;
        commandId = 9;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Low Credit Warning Level.
     *
     * @return the Low Credit Warning Level
     */
    public Integer getLowCreditWarningLevel() {
        return lowCreditWarningLevel;
    }

    /**
     * Sets Low Credit Warning Level.
     *
     * @param lowCreditWarningLevel the Low Credit Warning Level
     */
    public void setLowCreditWarningLevel(final Integer lowCreditWarningLevel) {
        this.lowCreditWarningLevel = lowCreditWarningLevel;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(lowCreditWarningLevel, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        lowCreditWarningLevel = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(68);
        builder.append("SetLowCreditWarningLevel [");
        builder.append(super.toString());
        builder.append(", lowCreditWarningLevel=");
        builder.append(lowCreditWarningLevel);
        builder.append(']');
        return builder.toString();
    }

}
