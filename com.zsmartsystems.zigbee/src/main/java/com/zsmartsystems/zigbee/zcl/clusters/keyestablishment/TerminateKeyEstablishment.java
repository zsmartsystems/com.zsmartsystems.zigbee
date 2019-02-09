/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.keyestablishment;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Terminate Key Establishment value object class.
 * <p>
 * Cluster: <b>Key Establishment</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Key Establishment cluster.
 * <p>
 * The Terminate Key Establishment command may be sent by either the initiator or responder to
 * indicate a failure in the key establishment exchange.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class TerminateKeyEstablishment extends ZclCommand {
    /**
     * Status Code command message field.
     */
    private Integer statusCode;

    /**
     * Wait Time command message field.
     * <p>
     * This value indicates the minimum amount of time in seconds the initiator device should
     * wait before trying to initiate key establishment again. The valid range is 0x00 to 0xFE.
     */
    private Integer waitTime;

    /**
     * Key Establishment Suite command message field.
     * <p>
     * This value will be set the value of the KeyEstablishmentSuite attribute. It indicates
     * the list of key exchange methods that the device supports.
     */
    private Integer keyEstablishmentSuite;

    /**
     * Default constructor.
     */
    public TerminateKeyEstablishment() {
        genericCommand = false;
        clusterId = 0x0800;
        commandId = 3;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Status Code.
     *
     * @return the Status Code
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * Sets Status Code.
     *
     * @param statusCode the Status Code
     */
    public void setStatusCode(final Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Gets Wait Time.
     * <p>
     * This value indicates the minimum amount of time in seconds the initiator device should
     * wait before trying to initiate key establishment again. The valid range is 0x00 to 0xFE.
     *
     * @return the Wait Time
     */
    public Integer getWaitTime() {
        return waitTime;
    }

    /**
     * Sets Wait Time.
     * <p>
     * This value indicates the minimum amount of time in seconds the initiator device should
     * wait before trying to initiate key establishment again. The valid range is 0x00 to 0xFE.
     *
     * @param waitTime the Wait Time
     */
    public void setWaitTime(final Integer waitTime) {
        this.waitTime = waitTime;
    }

    /**
     * Gets Key Establishment Suite.
     * <p>
     * This value will be set the value of the KeyEstablishmentSuite attribute. It indicates
     * the list of key exchange methods that the device supports.
     *
     * @return the Key Establishment Suite
     */
    public Integer getKeyEstablishmentSuite() {
        return keyEstablishmentSuite;
    }

    /**
     * Sets Key Establishment Suite.
     * <p>
     * This value will be set the value of the KeyEstablishmentSuite attribute. It indicates
     * the list of key exchange methods that the device supports.
     *
     * @param keyEstablishmentSuite the Key Establishment Suite
     */
    public void setKeyEstablishmentSuite(final Integer keyEstablishmentSuite) {
        this.keyEstablishmentSuite = keyEstablishmentSuite;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(statusCode, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(waitTime, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(keyEstablishmentSuite, ZclDataType.BITMAP_16_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        statusCode = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        waitTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        keyEstablishmentSuite = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(127);
        builder.append("TerminateKeyEstablishment [");
        builder.append(super.toString());
        builder.append(", statusCode=");
        builder.append(statusCode);
        builder.append(", waitTime=");
        builder.append(waitTime);
        builder.append(", keyEstablishmentSuite=");
        builder.append(keyEstablishmentSuite);
        builder.append(']');
        return builder.toString();
    }

}
