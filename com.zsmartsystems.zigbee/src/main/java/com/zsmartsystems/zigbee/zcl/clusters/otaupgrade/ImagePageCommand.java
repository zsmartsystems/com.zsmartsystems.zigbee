/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.otaupgrade;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Image Page Command value object class.
 * <p>
 * Cluster: <b>Ota Upgrade</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Ota Upgrade cluster.
 * <p>
 * The support for the command is optional. The client device may choose to request OTA upgrade
 * data in one page size at a time from upgrade server. Using Image Page Request reduces the
 * numbers of requests sent from the client to the upgrade server, compared to using Image Block
 * Request command. In order to conserve battery life a device may use the Image Page Request
 * command. Using the Image Page Request command eliminates the need for the client device to
 * send Image Block Request command for every data block it needs; possibly saving the
 * transmission of hundreds or thousands of messages depending on the image size. <br> The
 * client keeps track of how much data it has received by keeping a cumulative count of each data
 * size it has received in each Image Block Response. Once the count has reach the value of the
 * page size requested, it shall repeat Image Page Requests until it has successfully obtained
 * all pages. Note that the client may choose to switch between using Image Block Request and
 * Image Page Request during the upgrade process. For example, if the client does not receive
 * all data requested in one Image Page Request, the client may choose to request the missing
 * block of data using Image Block Request command, instead of requesting the whole page again.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ImagePageCommand extends ZclCommand {
    /**
     * Field Control command message field.
     */
    private Integer fieldControl;

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
     * Maximum Data Size command message field.
     */
    private Integer maximumDataSize;

    /**
     * Page Size command message field.
     */
    private Integer pageSize;

    /**
     * Response Spacing command message field.
     */
    private Integer responseSpacing;

    /**
     * Request Node Address command message field.
     */
    private IeeeAddress requestNodeAddress;

    /**
     * Default constructor.
     */
    public ImagePageCommand() {
        genericCommand = false;
        clusterId = 0x0019;
        commandId = 4;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Field Control.
     *
     * @return the Field Control
     */
    public Integer getFieldControl() {
        return fieldControl;
    }

    /**
     * Sets Field Control.
     *
     * @param fieldControl the Field Control
     */
    public void setFieldControl(final Integer fieldControl) {
        this.fieldControl = fieldControl;
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
     * Gets Maximum Data Size.
     *
     * @return the Maximum Data Size
     */
    public Integer getMaximumDataSize() {
        return maximumDataSize;
    }

    /**
     * Sets Maximum Data Size.
     *
     * @param maximumDataSize the Maximum Data Size
     */
    public void setMaximumDataSize(final Integer maximumDataSize) {
        this.maximumDataSize = maximumDataSize;
    }

    /**
     * Gets Page Size.
     *
     * @return the Page Size
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * Sets Page Size.
     *
     * @param pageSize the Page Size
     */
    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Gets Response Spacing.
     *
     * @return the Response Spacing
     */
    public Integer getResponseSpacing() {
        return responseSpacing;
    }

    /**
     * Sets Response Spacing.
     *
     * @param responseSpacing the Response Spacing
     */
    public void setResponseSpacing(final Integer responseSpacing) {
        this.responseSpacing = responseSpacing;
    }

    /**
     * Gets Request Node Address.
     *
     * @return the Request Node Address
     */
    public IeeeAddress getRequestNodeAddress() {
        return requestNodeAddress;
    }

    /**
     * Sets Request Node Address.
     *
     * @param requestNodeAddress the Request Node Address
     */
    public void setRequestNodeAddress(final IeeeAddress requestNodeAddress) {
        this.requestNodeAddress = requestNodeAddress;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(fieldControl, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(manufacturerCode, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(imageType, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(fileVersion, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(fileOffset, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(maximumDataSize, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(pageSize, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(responseSpacing, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        if ((fieldControl & 0x01) != 0) {
            serializer.serialize(requestNodeAddress, ZclDataType.IEEE_ADDRESS);
        }
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        fieldControl = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        manufacturerCode = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        imageType = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        fileVersion = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        fileOffset = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        maximumDataSize = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        pageSize = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        responseSpacing = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        if ((fieldControl & 0x01) != 0) {
            requestNodeAddress = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(313);
        builder.append("ImagePageCommand [");
        builder.append(super.toString());
        builder.append(", fieldControl=");
        builder.append(fieldControl);
        builder.append(", manufacturerCode=");
        builder.append(manufacturerCode);
        builder.append(", imageType=");
        builder.append(imageType);
        builder.append(", fileVersion=");
        builder.append(fileVersion);
        builder.append(", fileOffset=");
        builder.append(fileOffset);
        builder.append(", maximumDataSize=");
        builder.append(maximumDataSize);
        builder.append(", pageSize=");
        builder.append(pageSize);
        builder.append(", responseSpacing=");
        builder.append(responseSpacing);
        builder.append(", requestNodeAddress=");
        builder.append(requestNodeAddress);
        builder.append(']');
        return builder.toString();
    }

}
