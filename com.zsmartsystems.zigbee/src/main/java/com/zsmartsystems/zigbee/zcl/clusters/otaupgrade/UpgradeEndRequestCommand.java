/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.otaupgrade;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Upgrade End Request Command value object class.
 * <p>
 * Upon reception all the image data, the client should verify the image to ensure its integrity and validity.
 * If the device requires signed images it shall examine the image and verify the signature as described in
 * section 6.3.9.2. Clients may perform additional manufacturer specific integrity checks to validate the
 * image, for example, CRC check on the actual file data.
 * <br>
 * If the image fails any integrity checks, the client shall send an Upgrade End Request command to the
 * upgrade server with a status of INVALID_IMAGE. In this case, the client may reinitiate the upgrade
 * process in order to obtain a valid OTA upgrade image. The client shall not upgrade to the bad image
 * and shall discard the downloaded image data.
 * <br>
 * If the image passes all integrity checks and the client does not require additional OTA upgrade image
 * file, it shall send back an Upgrade End Request with a status of SUCCESS. However, if the client
 * requires multiple OTA upgrade image files before performing an upgrade, it shall send an Upgrade End
 * Request command with status REQUIRE_MORE_IMAGE. This shall indicate to the server that it
 * cannot yet upgrade the image it received.
 * <br>
 * If the client decides to cancel the download process for any other reasons, it has the option of sending
 * Upgrade End Request with status of ABORT at anytime during the download process. The client shall
 * then try to reinitiate the download process again at a later time.
 * <p>
 * Cluster: <b>OTA Upgrade</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the OTA Upgrade cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class UpgradeEndRequestCommand extends ZclCommand {
    /**
     * Status command message field.
     */
    private Integer status;

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
     * Default constructor.
     */
    public UpgradeEndRequestCommand() {
        genericCommand = false;
        clusterId = 25;
        commandId = 6;
        commandDirection = true;
    }

    /**
     * Gets Status.
     *
     * @return the Status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets Status.
     *
     * @param status the Status
     */
    public void setStatus(final Integer status) {
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

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(status, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(manufacturerCode, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(imageType, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(fileVersion, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        status = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        manufacturerCode = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        imageType = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        fileVersion = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(149);
        builder.append("UpgradeEndRequestCommand [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(", manufacturerCode=");
        builder.append(manufacturerCode);
        builder.append(", imageType=");
        builder.append(imageType);
        builder.append(", fileVersion=");
        builder.append(fileVersion);
        builder.append(']');
        return builder.toString();
    }

}
