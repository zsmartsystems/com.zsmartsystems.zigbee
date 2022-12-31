/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.prepayment;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Set Low Credit Warning Level value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command ID 0x09 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is sent from client to a Prepayment server to set the warning level for low
 * credit.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class SetLowCreditWarningLevel extends ZclPrepaymentCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0705;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x09;

    /**
     * Low Credit Warning Level command message field.
     */
    private Integer lowCreditWarningLevel;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public SetLowCreditWarningLevel() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param lowCreditWarningLevel {@link Integer} Low Credit Warning Level
     */
    public SetLowCreditWarningLevel(
            Integer lowCreditWarningLevel) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.lowCreditWarningLevel = lowCreditWarningLevel;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
