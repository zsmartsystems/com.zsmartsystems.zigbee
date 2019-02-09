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
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Upgrade End Response value object class.
 * <p>
 * Cluster: <b>Ota Upgrade</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Ota Upgrade cluster.
 * <p>
 * When an upgrade server receives an Upgrade End Request command with a status of
 * INVALID_IMAGE, REQUIRE_MORE_IMAGE, or ABORT, no additional processing shall be done in
 * its part. If the upgrade server receives an Upgrade End Request command with a status of
 * SUCCESS, it shall generate an Upgrade End Response with the manufacturer code and image type
 * received in the Upgrade End Request along with the times indicating when the device should
 * upgrade to the new image. <br> The server may send an unsolicited Upgrade End Response
 * command to the client. This may be used for example if the server wants to synchronize the
 * upgrade on multiple clients simultaneously. For client devices, the upgrade server may
 * unicast or broadcast Upgrade End Response command indicating a single client device or
 * multiple client devices shall switch to using their new images. The command may not be
 * reliably received by sleepy devices if it is sent unsolicited.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class UpgradeEndResponse extends ZclCommand {
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
     * Current Time command message field.
     */
    private Integer currentTime;

    /**
     * Upgrade Time command message field.
     */
    private Integer upgradeTime;

    /**
     * Default constructor.
     */
    public UpgradeEndResponse() {
        genericCommand = false;
        clusterId = 0x0019;
        commandId = 7;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
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
     * Gets Current Time.
     *
     * @return the Current Time
     */
    public Integer getCurrentTime() {
        return currentTime;
    }

    /**
     * Sets Current Time.
     *
     * @param currentTime the Current Time
     */
    public void setCurrentTime(final Integer currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * Gets Upgrade Time.
     *
     * @return the Upgrade Time
     */
    public Integer getUpgradeTime() {
        return upgradeTime;
    }

    /**
     * Sets Upgrade Time.
     *
     * @param upgradeTime the Upgrade Time
     */
    public void setUpgradeTime(final Integer upgradeTime) {
        this.upgradeTime = upgradeTime;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(manufacturerCode, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(imageType, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(fileVersion, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(currentTime, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(upgradeTime, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        manufacturerCode = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        imageType = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        fileVersion = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        currentTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        upgradeTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(179);
        builder.append("UpgradeEndResponse [");
        builder.append(super.toString());
        builder.append(", manufacturerCode=");
        builder.append(manufacturerCode);
        builder.append(", imageType=");
        builder.append(imageType);
        builder.append(", fileVersion=");
        builder.append(fileVersion);
        builder.append(", currentTime=");
        builder.append(currentTime);
        builder.append(", upgradeTime=");
        builder.append(upgradeTime);
        builder.append(']');
        return builder.toString();
    }

}
