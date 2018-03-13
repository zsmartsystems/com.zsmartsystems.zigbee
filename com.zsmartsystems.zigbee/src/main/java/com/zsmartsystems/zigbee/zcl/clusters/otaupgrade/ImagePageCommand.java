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
 * Image Page Command value object class.
 * <p>
 * The support for the command is optional. The client device may choose to request OTA upgrade data
 * in one page size at a time from upgrade server. Using Image Page Request reduces the numbers of
 * requests sent from the client to the upgrade server, compared to using Image Block Request command.
 * In order to conserve battery life a device may use the Image Page Request command. Using the Image
 * Page Request command eliminates the need for the client device to send Image Block Request
 * command for every data block it needs; possibly saving the transmission of hundreds or thousands of
 * messages depending on the image size.
 * <br>
 * The client keeps track of how much data it has received by keeping a cumulative count of each data
 * size it has received in each Image Block Response. Once the count has reach the value of the page size
 * requested, it shall repeat Image Page Requests until it has successfully obtained all pages. Note that the
 * client may choose to switch between using Image Block Request and Image Page Request during the
 * upgrade process. For example, if the client does not receive all data requested in one Image Page
 * Request, the client may choose to request the missing block of data using Image Block Request
 * command, instead of requesting the whole page again.
 * <p>
 * Cluster: <b>OTA Upgrade</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the OTA Upgrade cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class ImagePageCommand extends ZclCommand {
    /**
     * Field control command message field.
     */
    private Integer fieldControl;

    /**
     * Manufacturer code command message field.
     */
    private Integer manufacturerCode;

    /**
     * Image type command message field.
     */
    private Integer imageType;

    /**
     * File version command message field.
     */
    private Integer fileVersion;

    /**
     * File offset command message field.
     */
    private Integer fileOffset;

    /**
     * Maximum data size command message field.
     */
    private Integer maximumDataSize;

    /**
     * Page size command message field.
     */
    private Integer pageSize;

    /**
     * Response spacing command message field.
     */
    private Integer responseSpacing;

    /**
     * Request node address command message field.
     */
    private IeeeAddress requestNodeAddress;

    /**
     * Default constructor.
     */
    public ImagePageCommand() {
        genericCommand = false;
        clusterId = 25;
        commandId = 4;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Field control.
     *
     * @return the Field control
     */
    public Integer getFieldControl() {
        return fieldControl;
    }

    /**
     * Sets Field control.
     *
     * @param fieldControl the Field control
     */
    public void setFieldControl(final Integer fieldControl) {
        this.fieldControl = fieldControl;
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
     * Gets File version.
     *
     * @return the File version
     */
    public Integer getFileVersion() {
        return fileVersion;
    }

    /**
     * Sets File version.
     *
     * @param fileVersion the File version
     */
    public void setFileVersion(final Integer fileVersion) {
        this.fileVersion = fileVersion;
    }

    /**
     * Gets File offset.
     *
     * @return the File offset
     */
    public Integer getFileOffset() {
        return fileOffset;
    }

    /**
     * Sets File offset.
     *
     * @param fileOffset the File offset
     */
    public void setFileOffset(final Integer fileOffset) {
        this.fileOffset = fileOffset;
    }

    /**
     * Gets Maximum data size.
     *
     * @return the Maximum data size
     */
    public Integer getMaximumDataSize() {
        return maximumDataSize;
    }

    /**
     * Sets Maximum data size.
     *
     * @param maximumDataSize the Maximum data size
     */
    public void setMaximumDataSize(final Integer maximumDataSize) {
        this.maximumDataSize = maximumDataSize;
    }

    /**
     * Gets Page size.
     *
     * @return the Page size
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * Sets Page size.
     *
     * @param pageSize the Page size
     */
    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Gets Response spacing.
     *
     * @return the Response spacing
     */
    public Integer getResponseSpacing() {
        return responseSpacing;
    }

    /**
     * Sets Response spacing.
     *
     * @param responseSpacing the Response spacing
     */
    public void setResponseSpacing(final Integer responseSpacing) {
        this.responseSpacing = responseSpacing;
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
