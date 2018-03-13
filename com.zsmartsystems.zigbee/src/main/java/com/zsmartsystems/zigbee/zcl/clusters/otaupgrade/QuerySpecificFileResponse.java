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
import com.zsmartsystems.zigbee.zcl.ZclStatus;

/**
 * Query Specific File Response value object class.
 * <p>
 * The server sends Query Specific File Response after receiving Query Specific File Request from a
 * client. The server shall determine whether it first supports the Query Specific File Request command.
 * Then it shall determine whether it has the specific file being requested by the client using all the
 * information included in the request. The upgrade server sends a Query Specific File Response with
 * one of the following status: SUCCESS, NO_IMAGE_AVAILABLE or NOT_AUTHORIZED.
 * <br>
 * A status of NO_IMAGE_AVAILABLE indicates that the server currently does not have the device
 * specific file available for the client. A status of NOT_AUTHORIZED indicates the server is not
 * authorized to send the file to the client.
 * <p>
 * Cluster: <b>OTA Upgrade</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the OTA Upgrade cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class QuerySpecificFileResponse extends ZclCommand {
    /**
     * Status command message field.
     */
    private ZclStatus status;

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
     * Image Size command message field.
     */
    private Integer imageSize;

    /**
     * Default constructor.
     */
    public QuerySpecificFileResponse() {
        genericCommand = false;
        clusterId = 25;
        commandId = 9;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Status.
     *
     * @return the Status
     */
    public ZclStatus getStatus() {
        return status;
    }

    /**
     * Sets Status.
     *
     * @param status the Status
     */
    public void setStatus(final ZclStatus status) {
        this.status = status;
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
     * Gets Image Size.
     *
     * @return the Image Size
     */
    public Integer getImageSize() {
        return imageSize;
    }

    /**
     * Sets Image Size.
     *
     * @param imageSize the Image Size
     */
    public void setImageSize(final Integer imageSize) {
        this.imageSize = imageSize;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(status, ZclDataType.ZCL_STATUS);
        if (status == ZclStatus.SUCCESS) {
            serializer.serialize(manufacturerCode, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
        if (status == ZclStatus.SUCCESS) {
            serializer.serialize(imageType, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
        if (status == ZclStatus.SUCCESS) {
            serializer.serialize(fileVersion, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        }
        if (status == ZclStatus.SUCCESS) {
            serializer.serialize(imageSize, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        }
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        status = (ZclStatus) deserializer.deserialize(ZclDataType.ZCL_STATUS);
        if (status == ZclStatus.SUCCESS) {
            manufacturerCode = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
        if (status == ZclStatus.SUCCESS) {
            imageType = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
        if (status == ZclStatus.SUCCESS) {
            fileVersion = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        }
        if (status == ZclStatus.SUCCESS) {
            imageSize = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(179);
        builder.append("QuerySpecificFileResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(", manufacturerCode=");
        builder.append(manufacturerCode);
        builder.append(", imageType=");
        builder.append(imageType);
        builder.append(", fileVersion=");
        builder.append(fileVersion);
        builder.append(", imageSize=");
        builder.append(imageSize);
        builder.append(']');
        return builder.toString();
    }

}
