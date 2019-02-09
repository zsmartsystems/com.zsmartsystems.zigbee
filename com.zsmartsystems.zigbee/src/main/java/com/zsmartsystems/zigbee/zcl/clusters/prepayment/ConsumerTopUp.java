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
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Consumer Top Up value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: The ConsumerTopUp command is used by the IPD and the ESI as a method of applying credit
 * top up values to the prepayment meter.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ConsumerTopUp extends ZclCommand {
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
     */
    public ConsumerTopUp() {
        genericCommand = false;
        clusterId = 0x0705;
        commandId = 4;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
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
     */
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
