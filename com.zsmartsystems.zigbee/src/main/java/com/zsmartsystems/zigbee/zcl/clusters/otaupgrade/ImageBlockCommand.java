/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.otaupgrade;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Image Block Command value object class.
 * <p>
 * Cluster: <b>Ota Upgrade</b>. Command ID 0x03 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Ota Upgrade cluster.
 * <p>
 * The client device requests the image data at its leisure by sending Image Block Request
 * command to the upgrade server. The client knows the total number of request commands it needs
 * to send from the image size value received in Query Next Image Response command. <br> The
 * client repeats Image Block Requests until it has successfully obtained all data.
 * Manufacturer code, image type and file version are included in all further queries
 * regarding that image. The information eliminates the need for the server to remember which
 * OTA Upgrade Image is being used for each download process. <br> If the client supports the
 * BlockRequestDelay attribute it shall include the value of the attribute as the
 * BlockRequestDelay field of the Image Block Request message. The client shall ensure that it
 * delays at least BlockRequestDelay milliseconds after the previous Image Block Request was
 * sent before sending the next Image Block Request message. A client may delay its next Image
 * Block Requests longer than its BlockRequestDelay attribute.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class ImageBlockCommand extends ZclOtaUpgradeCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0019;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x03;

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
     * Request Node Address command message field.
     */
    private IeeeAddress requestNodeAddress;

    /**
     * Block Request Delay command message field.
     */
    private Integer blockRequestDelay;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public ImageBlockCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param fieldControl {@link Integer} Field Control
     * @param manufacturerCode {@link Integer} Manufacturer Code
     * @param imageType {@link Integer} Image Type
     * @param fileVersion {@link Integer} File Version
     * @param fileOffset {@link Integer} File Offset
     * @param maximumDataSize {@link Integer} Maximum Data Size
     * @param requestNodeAddress {@link IeeeAddress} Request Node Address
     * @param blockRequestDelay {@link Integer} Block Request Delay
     */
    public ImageBlockCommand(
            Integer fieldControl,
            Integer manufacturerCode,
            Integer imageType,
            Integer fileVersion,
            Integer fileOffset,
            Integer maximumDataSize,
            IeeeAddress requestNodeAddress,
            Integer blockRequestDelay) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.fieldControl = fieldControl;
        this.manufacturerCode = manufacturerCode;
        this.imageType = imageType;
        this.fileVersion = fileVersion;
        this.fileOffset = fileOffset;
        this.maximumDataSize = maximumDataSize;
        this.requestNodeAddress = requestNodeAddress;
        this.blockRequestDelay = blockRequestDelay;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setMaximumDataSize(final Integer maximumDataSize) {
        this.maximumDataSize = maximumDataSize;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setRequestNodeAddress(final IeeeAddress requestNodeAddress) {
        this.requestNodeAddress = requestNodeAddress;
    }

    /**
     * Gets Block Request Delay.
     *
     * @return the Block Request Delay
     */
    public Integer getBlockRequestDelay() {
        return blockRequestDelay;
    }

    /**
     * Sets Block Request Delay.
     *
     * @param blockRequestDelay the Block Request Delay
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setBlockRequestDelay(final Integer blockRequestDelay) {
        this.blockRequestDelay = blockRequestDelay;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(fieldControl, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(manufacturerCode, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(imageType, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(fileVersion, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(fileOffset, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(maximumDataSize, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        if ((fieldControl & 0x01) != 0) {
            serializer.serialize(requestNodeAddress, ZclDataType.IEEE_ADDRESS);
        }
        if ((fieldControl & 0x02) != 0) {
            serializer.serialize(blockRequestDelay, ZclDataType.UNSIGNED_16_BIT_INTEGER);
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
        if ((fieldControl & 0x01) != 0) {
            requestNodeAddress = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        }
        if ((fieldControl & 0x02) != 0) {
            blockRequestDelay = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(288);
        builder.append("ImageBlockCommand [");
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
        builder.append(", requestNodeAddress=");
        builder.append(requestNodeAddress);
        builder.append(", blockRequestDelay=");
        builder.append(blockRequestDelay);
        builder.append(']');
        return builder.toString();
    }

}
