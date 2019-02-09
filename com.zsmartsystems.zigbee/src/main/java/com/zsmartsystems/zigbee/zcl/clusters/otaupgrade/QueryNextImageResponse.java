/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.otaupgrade;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Query Next Image Response value object class.
 * <p>
 * Cluster: <b>Ota Upgrade</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Ota Upgrade cluster.
 * <p>
 * The upgrade server sends a Query Next Image Response with one of the following status:
 * SUCCESS, NO_IMAGE_AVAILABLE or NOT_AUTHORIZED. When a SUCCESS status is sent, it is
 * considered to be the explicit authorization to a device by the upgrade server that the device
 * may upgrade to a specific software image. <br> A status of NO_IMAGE_AVAILABLE indicates
 * that the server is authorized to upgrade the client but it currently does not have the (new)
 * OTA upgrade image available for the client. For all clients (both ZR and ZED)9 , they shall
 * continue sending Query Next Image Requests to the server periodically until an image
 * becomes available. <br> A status of NOT_AUTHORIZED indicates the server is not authorized
 * to upgrade the client. In this case, the client may perform discovery again to find another
 * upgrade server. The client may implement an intelligence to avoid querying the same
 * unauthorized server.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class QueryNextImageResponse extends ZclCommand {
    /**
     * Status command message field.
     */
    private ZclStatus status;

    /**
     * Manufacturer Code command message field.
     */
    private Integer manufacturerCode;

    /**
     * Image Type command message field.
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
    public QueryNextImageResponse() {
        genericCommand = false;
        clusterId = 0x0019;
        commandId = 2;
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
     * Gets Manufacturer Code.
     *
     * @return the Manufacturer Code
     */
    public Integer getManufacturerCode() {
        return manufacturerCode;
    }

    /**
     * Sets Manufacturer Code.
     *
     * @param manufacturerCode the Manufacturer Code
     */
    public void setManufacturerCode(final Integer manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    /**
     * Gets Image Type.
     *
     * @return the Image Type
     */
    public Integer getImageType() {
        return imageType;
    }

    /**
     * Sets Image Type.
     *
     * @param imageType the Image Type
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
        final StringBuilder builder = new StringBuilder(176);
        builder.append("QueryNextImageResponse [");
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
