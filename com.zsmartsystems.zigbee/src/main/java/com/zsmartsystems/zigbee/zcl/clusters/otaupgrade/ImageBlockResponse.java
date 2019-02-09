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
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Image Block Response value object class.
 * <p>
 * Cluster: <b>Ota Upgrade</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Ota Upgrade cluster.
 * <p>
 * Upon receipt of an Image Block Request command the server shall generate an Image Block
 * Response. If the server is able to retrieve the data for the client and does not wish to change
 * the image download rate, it will respond with a status of SUCCESS and it will include all the
 * fields in the payload. The use of file offset allows the server to send packets with variable
 * data size during the upgrade process. This allows the server to support a case when the
 * network topology of a client may change during the upgrade process, for example, mobile
 * client may move around during the upgrade process. If the client has moved a few hops away, the
 * data size shall be smaller. Moreover, using file offset eliminates the need for data padding
 * since each Image Block Response command may contain different data size. A simple server
 * implementation may choose to only support largest possible data size for the worst-case
 * scenario in order to avoid supporting sending packets with variable data size. <br> The
 * server shall respect the maximum data size value requested by the client and shall not send
 * the data with length greater than that value. The server may send the data with length smaller
 * than the value depending on the network topology of the client. For example, the client may be
 * able to receive 100 bytes of data at once so it sends the request with 100 as maximum data size.
 * But after considering all the security headers (perhaps from both APS and network levels)
 * and source routing overhead (for example, the client is five hops away), the largest
 * possible data size that the server can send to the client shall be smaller than 100 bytes.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ImageBlockResponse extends ZclCommand {
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
     * File Offset command message field.
     */
    private Integer fileOffset;

    /**
     * Image Data command message field.
     */
    private ByteArray imageData;

    /**
     * Default constructor.
     */
    public ImageBlockResponse() {
        genericCommand = false;
        clusterId = 0x0019;
        commandId = 5;
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
     * Gets File Offset.
     *
     * @return the File Offset
     */
    public Integer getFileOffset() {
        return fileOffset;
    }

    /**
     * Sets File Offset.
     *
     * @param fileOffset the File Offset
     */
    public void setFileOffset(final Integer fileOffset) {
        this.fileOffset = fileOffset;
    }

    /**
     * Gets Image Data.
     *
     * @return the Image Data
     */
    public ByteArray getImageData() {
        return imageData;
    }

    /**
     * Sets Image Data.
     *
     * @param imageData the Image Data
     */
    public void setImageData(final ByteArray imageData) {
        this.imageData = imageData;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(status, ZclDataType.ZCL_STATUS);
        serializer.serialize(manufacturerCode, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(imageType, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(fileVersion, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(fileOffset, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(imageData, ZclDataType.BYTE_ARRAY);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        status = (ZclStatus) deserializer.deserialize(ZclDataType.ZCL_STATUS);
        manufacturerCode = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        imageType = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        fileVersion = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        fileOffset = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        imageData = (ByteArray) deserializer.deserialize(ZclDataType.BYTE_ARRAY);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(202);
        builder.append("ImageBlockResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(", manufacturerCode=");
        builder.append(manufacturerCode);
        builder.append(", imageType=");
        builder.append(imageType);
        builder.append(", fileVersion=");
        builder.append(fileVersion);
        builder.append(", fileOffset=");
        builder.append(fileOffset);
        builder.append(", imageData=");
        builder.append(imageData);
        builder.append(']');
        return builder.toString();
    }

}
