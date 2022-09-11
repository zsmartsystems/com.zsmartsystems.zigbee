/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.prepayment;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Consumer Top Up value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command ID 0x04 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: The ConsumerTopUp command is used by the IPD and the ESI as a method of applying credit
 * top up values to the prepayment meter.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class ConsumerTopUp extends ZclPrepaymentCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0705;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x04;

    /**
     * Originating Device command message field.
     */
    private Integer originatingDevice;

    /**
     * Top Up Code command message field.
     */
    private ByteArray topUpCode;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public ConsumerTopUp() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param originatingDevice {@link Integer} Originating Device
     * @param topUpCode {@link ByteArray} Top Up Code
     */
    public ConsumerTopUp(
            Integer originatingDevice,
            ByteArray topUpCode) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.originatingDevice = originatingDevice;
        this.topUpCode = topUpCode;
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
     * Gets Top Up Code.
     *
     * @return the Top Up Code
     */
    public ByteArray getTopUpCode() {
        return topUpCode;
    }

    /**
     * Sets Top Up Code.
     *
     * @param topUpCode the Top Up Code
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTopUpCode(final ByteArray topUpCode) {
        this.topUpCode = topUpCode;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(originatingDevice, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(topUpCode, ZclDataType.OCTET_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        originatingDevice = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        topUpCode = (ByteArray) deserializer.deserialize(ZclDataType.OCTET_STRING);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(82);
        builder.append("ConsumerTopUp [");
        builder.append(super.toString());
        builder.append(", originatingDevice=");
        builder.append(originatingDevice);
        builder.append(", topUpCode=");
        builder.append(topUpCode);
        builder.append(']');
        return builder.toString();
    }

}
