/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.otaupgrade;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Query Next Image Command value object class.
 * <p>
 * Cluster: <b>Ota Upgrade</b>. Command ID 0x01 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Ota Upgrade cluster.
 * <p>
 * Client devices shall send a Query Next Image Request command to the server to see if there is
 * new OTA upgrade image available. ZR devices may send the command after receiving Image
 * Notify command. ZED device shall periodically wake up and send the command to the upgrade
 * server. Client devices query what the next image is, based on their own information. <br> The
 * server takes the client’s information in the command and determines whether it has a
 * suitable image for the particular client. The decision should be based on specific policy
 * that is specific to the upgrade server and outside the scope of this document.. However, a
 * recommended default policy is for the server to send back a response that indicates the
 * availability of an image that matches the manufacturer code, image type, and the highest
 * available file version of that image on the server. However, the server may choose to
 * upgrade, downgrade, or reinstall clients’ image, as its policy dictates. If client’s
 * hardware version is included in the command, the server shall examine the value against the
 * minimum and maximum hardware versions included in the OTA file header.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class QueryNextImageCommand extends ZclOtaUpgradeCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0019;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x01;

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
     * Hardware Version command message field.
     */
    private Integer hardwareVersion;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public QueryNextImageCommand() {
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
     * @param hardwareVersion {@link Integer} Hardware Version
     */
    public QueryNextImageCommand(
            Integer fieldControl,
            Integer manufacturerCode,
            Integer imageType,
            Integer fileVersion,
            Integer hardwareVersion) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.fieldControl = fieldControl;
        this.manufacturerCode = manufacturerCode;
        this.imageType = imageType;
        this.fileVersion = fileVersion;
        this.hardwareVersion = hardwareVersion;
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
     * Gets Hardware Version.
     *
     * @return the Hardware Version
     */
    public Integer getHardwareVersion() {
        return hardwareVersion;
    }

    /**
     * Sets Hardware Version.
     *
     * @param hardwareVersion the Hardware Version
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setHardwareVersion(final Integer hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(fieldControl, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(manufacturerCode, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(imageType, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(fileVersion, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        if ((fieldControl & 0x01) != 0) {
            serializer.serialize(hardwareVersion, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        fieldControl = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        manufacturerCode = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        imageType = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        fileVersion = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        if ((fieldControl & 0x01) != 0) {
            hardwareVersion = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(187);
        builder.append("QueryNextImageCommand [");
        builder.append(super.toString());
        builder.append(", fieldControl=");
        builder.append(fieldControl);
        builder.append(", manufacturerCode=");
        builder.append(manufacturerCode);
        builder.append(", imageType=");
        builder.append(imageType);
        builder.append(", fileVersion=");
        builder.append(fileVersion);
        builder.append(", hardwareVersion=");
        builder.append(hardwareVersion);
        builder.append(']');
        return builder.toString();
    }

}
