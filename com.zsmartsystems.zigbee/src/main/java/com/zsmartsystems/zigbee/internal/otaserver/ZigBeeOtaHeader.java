/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal.otaserver;

import java.math.BigInteger;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeStackType;
import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;

/**
 * This class implements the Over The Air update server file header.
 * <p>
 * The OTA file format is composed of a header followed by a number of sub-elements. The header
 * describes general information about the file such as version, the manufacturer that created it, and the
 * device it is intended for. Sub-elements in the file may contain upgrade data for the embedded device,
 * certificates, configuration data, log messages, or other manufacturer specific pieces. Below is an
 * example file.
 * <p>
 * The OTA Upgrade image file name should contain the following information at the beginning of the
 * name with each field separated by a dash (“-“): manufacturer code, image type and file version. The
 * value of each field stated should be in hexadecimal number and in capital letter. Each manufacturer
 * may append more information to the name as seen fit to make the name more specific. The OTA
 * Upgrade file extension should be “.zigbee”.
 *
 * @author Chris Jackson
 */
class ZigBeeOtaHeader {
    /**
     * The value is a unique 4-byte value that is included at the beginning of all ZigBee OTA upgrade image
     * files in order to quickly identify and distinguish the file as being a ZigBee OTA cluster upgrade file,
     * without having to examine the whole file content. This helps distinguishing the file from other file
     * types on disk. The value is defined to be “0x0BEEF11E”.
     */
    private BigInteger fileIdentifier;

    /**
     * The value enumerates the version of the header and provides compatibility information. The value is
     * composed of a major and minor version number (one byte each). The high byte (or the most significant
     * byte) represents the major version and the low byte (or the least significant byte) represents the minor
     * version number. A change to the minor version means the OTA upgrade file format is still backward
     * compatible, while a change to the major version suggests incompatibility.
     * The current OTA header version shall be 0x0100 with major version of “01” and minor version of
     * “00”
     */
    private Integer headerVersion;

    /**
     * This value indicates full length of the OTA header in bytes, including the OTA upgrade file identifier,
     * OTA header length itself to any optional fields. The value insulates existing software against new
     * fields that may be added to the header. If new header fields added are not compatible with current
     * running software, the implementations should process all fields they understand and then skip over any
     * remaining bytes in the header to process the image or signing certificate. The value of the header
     * length depends on the value of the OTA header field control, which dictates which optional OTA
     * header fields are included.
     */
    private Integer headerLength;

    /**
     * This is the ZigBee assigned identifier for each member company. When used during the OTA upgrade
     * process, manufacturer code value of 0xffff has a special meaning of a wild card. The value has a
     * ‘match all’ effect. OTA server may send a command with wild card value for manufacturer code to
     * match all client devices from all manufacturers.
     */
    private Integer manufacturerCode;

    /**
     * The manufacturer should assign an appropriate and unique image type value to each of its devices in
     * order to distinguish the products. This is a manufacturer specific value. However, the OTA Upgrade
     * cluster has reserved the last 64 values of image type value to indicate specific file types such as security
     * credential, log, and configuration. When a client wants to request one of these specific file types, it
     * shall use one of the reserved image type values instead of its own (manufacturer specific) value when
     * requesting the image via Query Next Image Request command.
     * Image type value of 0xffff has a special meaning of a wild card. The value has a ‘match all’ effect.
     */
    private Integer imageType;

    /**
     * For firmware image, the file version represents the release and build number of the image’s application
     * and stack. The application release and build numbers are manufacturer specific, however, each
     * manufacturer should obtain stack release and build numbers from their stack vendor. OTA Upgrade
     * cluster makes the recommendation below regarding how the file version should be defined, in an
     * attempt to make it easy for humans and upgrade servers to determine which versions are newer than
     * others. The upgrade server should use this version value to compare with the one received from the
     * client.
     */
    private BigInteger fileVersion;

    /**
     * This information indicates the ZigBee stack version that is used by the application. This provides the
     * upgrade server an ability to coordinate the distribution of images to devices when the upgrades will
     * cause a major jump that usually breaks the over-the-air compatibility, for example, from ZigBee Pro to
     * upcoming ZigBee IP.
     */
    private ZigBeeStackType stackVersion;

    /**
     * This is a manufacturer specific string that may be used to store other necessary information as seen
     * fit by each manufacturer. The idea is to have a human readable string that can prove helpful during
     * development cycle. The string is defined to occupy 32 bytes of space in the OTA header
     */
    private String headerString;

    /**
     * The value represents the total image size in bytes. This is the total of data in bytes that shall be
     * 561 transferred over-the-air from the server to the client. In most cases, the total image size of an OTA
     * 562 upgrade image file is the sum of the OTA header and the actual file data (along with its tag) lengths. If
     * 563 the image is a signed image and contains a certificate of the signer, then the Total image size shall also
     * 564 include the signer certificate and the signature (along with their tags) in bytes.
     */
    private BigInteger imageSize;

    /**
     * This information indicates security credential version type, such as SE1.0 or SE2.0 that the client is
     * required to have, before it shall install the image. One use case for this is so that after the client has
     * downloaded a new image from the server, it should check if the value of security credential version
     * allows for running the image. If the client’s existing security credential version does not match or is
     * outdated from what specified in the OTA header, it should obtain new security credentials before
     * upgrading to running the new image.
     */
    private Integer securityCredentialVersion;

    /**
     * If Device Specific File bit is set, it indicates that this OTA file contains security credential/certificate
     * data or other type of information that is specific to a particular device. Hence, the upgrade file
     * destination field (in OTA header) should also be set to indicate the IEEE address of the client device
     * that this file is meant for.
     */
    private IeeeAddress destination;

    /**
     * The value represents the earliest hardware platform version this image should be used on.
     */
    private Integer minimumHardware;

    /**
     * The value represents the latest hardware platform this image should be used on. The field is defined
     * the same as the Minimum Hardware Version.
     */
    private Integer maximumHardware;

    private final int FIELD_CTL_SECURITY_CREDENTIAL = 0x01;
    private final int FIELD_CTL_DEVICE_SPECIFIC = 0x02;
    private final int FIELD_CTL_HARDWARE_VERSIONS = 0x04;

    /**
     * Serialise the contents of the structure.
     *
     * @param serializer the {@link ZigBeeSerializer} used to serialize
     */
    public void serialize(ZigBeeDeserializer deserializer) {
    }

    /**
     * Deserialise the contents of the structure.
     *
     * @param deserializer the {@link ZigBeeDeserializer} used to deserialize
     */
    public void deserialize(ZigBeeDeserializer deserializer) {
    }

}
