/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.otaupgrade;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.IeeeAddress;

/**
 * Query Specific File Command value object class.
 * <p>
 * Client devices shall send a Query Specific File Request command to the server to request for a file that
 * is specific and unique to it. Such file could contain non-firmware data such as security credential
 * (needed for upgrading from Smart Energy 1.1 to Smart Energy 2.0), configuration or log. When the
 * device decides to send the Query Specific File Request command is manufacturer specific. However,
 * one example is during upgrading from SE 1.1 to 2.0 where the client may have already obtained new
 * SE 2.0 image and now needs new SE 2.0 security credential data.
 * <p>
 * Cluster: <b>OTA Upgrade</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the OTA Upgrade cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class QuerySpecificFileCommand extends ZclCommand {
    /**
     * Request node address command message field.
     */
    private IeeeAddress requestNodeAddress;

    /**
     * Manufacturer code command message field.
     */
    private Integer manufacturerCode;

    /**
     * Image type command message field.
     */
    private Integer imageType;

    /**
     * File Version command message field.
     */
    private Integer fileVersion;

    /**
     * Zigbee Stack Version command message field.
     */
    private Integer zigbeeStackVersion;

    /**
     * Default constructor.
     */
    public QuerySpecificFileCommand() {
        genericCommand = false;
        clusterId = 25;
        commandId = 8;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Request node address.
     *
     * @return the Request node address
     */
    public IeeeAddress getRequestNodeAddress() {
        return requestNodeAddress;
    }

    /**
     * Sets Request node address.
     *
     * @param requestNodeAddress the Request node address
     */
    public void setRequestNodeAddress(final IeeeAddress requestNodeAddress) {
        this.requestNodeAddress = requestNodeAddress;
    }

    /**
     * Gets Manufacturer code.
     *
     * @return the Manufacturer code
     */
    public Integer getManufacturerCode() {
        return manufacturerCode;
    }

    /**
     * Sets Manufacturer code.
     *
     * @param manufacturerCode the Manufacturer code
     */
    public void setManufacturerCode(final Integer manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    /**
     * Gets Image type.
     *
     * @return the Image type
     */
    public Integer getImageType() {
        return imageType;
    }

    /**
     * Sets Image type.
     *
     * @param imageType the Image type
     */
    public void setImageType(final Integer imageType) {
        this.imageType = imageType;
    }

    /**
     * Gets File Version.
     *
     * @return the File Version
     */
    public Integer getFileVersion() {
        return fileVersion;
    }

    /**
     * Sets File Version.
     *
     * @param fileVersion the File Version
     */
    public void setFileVersion(final Integer fileVersion) {
        this.fileVersion = fileVersion;
    }

    /**
     * Gets Zigbee Stack Version.
     *
     * @return the Zigbee Stack Version
     */
    public Integer getZigbeeStackVersion() {
        return zigbeeStackVersion;
    }

    /**
     * Sets Zigbee Stack Version.
     *
     * @param zigbeeStackVersion the Zigbee Stack Version
     */
    public void setZigbeeStackVersion(final Integer zigbeeStackVersion) {
        this.zigbeeStackVersion = zigbeeStackVersion;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(requestNodeAddress, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(manufacturerCode, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(imageType, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(fileVersion, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(zigbeeStackVersion, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        requestNodeAddress = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        manufacturerCode = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        imageType = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        fileVersion = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        zigbeeStackVersion = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(199);
        builder.append("QuerySpecificFileCommand [");
        builder.append(super.toString());
        builder.append(", requestNodeAddress=");
        builder.append(requestNodeAddress);
        builder.append(", manufacturerCode=");
        builder.append(manufacturerCode);
        builder.append(", imageType=");
        builder.append(imageType);
        builder.append(", fileVersion=");
        builder.append(fileVersion);
        builder.append(", zigbeeStackVersion=");
        builder.append(zigbeeStackVersion);
        builder.append(']');
        return builder.toString();
    }

}
