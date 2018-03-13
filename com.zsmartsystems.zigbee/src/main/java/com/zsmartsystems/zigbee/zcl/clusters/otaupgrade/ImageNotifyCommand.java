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

/**
 * Image Notify Command value object class.
 * <p>
 * The purpose of sending Image Notify command is so the server has a way to notify client devices of
 * when the OTA upgrade images are available for them. It eliminates the need for ZR client devices
 * having to check with the server periodically of when the new images are available. However, all client
 * devices still need to send in Query Next Image Request command in order to officially start the OTA
 * upgrade process.
 * <br>
 * For ZR client devices, the upgrade server may send out a unicast, broadcast, or multicast indicating it
 * has the next upgrade image, via an Image Notify command. Since the command may not have APS
 * security (if it is broadcast or multicast), it is considered purely informational and not authoritative.
 * Even in the case of a unicast, ZR shall continue to perform the query process described in later section.
 * <br>
 * When the command is sent with payload type value of zero, it generally means the server wishes to
 * notify all clients disregard of their manufacturers, image types or file versions. Query jitter is needed
 * to protect the server from being flooded with clients’ queries for next image.
 * <p>
 * Cluster: <b>OTA Upgrade</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the OTA Upgrade cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class ImageNotifyCommand extends ZclCommand {
    /**
     * Payload type command message field.
     */
    private Integer payloadType;

    /**
     * Query jitter command message field.
     */
    private Integer queryJitter;

    /**
     * Manufacturer code command message field.
     */
    private Integer manufacturerCode;

    /**
     * Image type command message field.
     */
    private Integer imageType;

    /**
     * New File Version command message field.
     */
    private Integer newFileVersion;

    /**
     * Default constructor.
     */
    public ImageNotifyCommand() {
        genericCommand = false;
        clusterId = 25;
        commandId = 0;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Payload type.
     *
     * @return the Payload type
     */
    public Integer getPayloadType() {
        return payloadType;
    }

    /**
     * Sets Payload type.
     *
     * @param payloadType the Payload type
     */
    public void setPayloadType(final Integer payloadType) {
        this.payloadType = payloadType;
    }

    /**
     * Gets Query jitter.
     *
     * @return the Query jitter
     */
    public Integer getQueryJitter() {
        return queryJitter;
    }

    /**
     * Sets Query jitter.
     *
     * @param queryJitter the Query jitter
     */
    public void setQueryJitter(final Integer queryJitter) {
        this.queryJitter = queryJitter;
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
     * Gets New File Version.
     *
     * @return the New File Version
     */
    public Integer getNewFileVersion() {
        return newFileVersion;
    }

    /**
     * Sets New File Version.
     *
     * @param newFileVersion the New File Version
     */
    public void setNewFileVersion(final Integer newFileVersion) {
        this.newFileVersion = newFileVersion;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(payloadType, ZclDataType.ENUMERATION_8_BIT);
        if (payloadType >= 0) {
            serializer.serialize(queryJitter, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        }
        if (payloadType >= 1) {
            serializer.serialize(manufacturerCode, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
        if (payloadType >= 2) {
            serializer.serialize(imageType, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
        if (payloadType >= 3) {
            serializer.serialize(newFileVersion, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        }
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        payloadType = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        if (payloadType >= 0) {
            queryJitter = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        }
        if (payloadType >= 1) {
            manufacturerCode = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
        if (payloadType >= 2) {
            imageType = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
        if (payloadType >= 3) {
            newFileVersion = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(182);
        builder.append("ImageNotifyCommand [");
        builder.append(super.toString());
        builder.append(", payloadType=");
        builder.append(payloadType);
        builder.append(", queryJitter=");
        builder.append(queryJitter);
        builder.append(", manufacturerCode=");
        builder.append(manufacturerCode);
        builder.append(", imageType=");
        builder.append(imageType);
        builder.append(", newFileVersion=");
        builder.append(newFileVersion);
        builder.append(']');
        return builder.toString();
    }

}
