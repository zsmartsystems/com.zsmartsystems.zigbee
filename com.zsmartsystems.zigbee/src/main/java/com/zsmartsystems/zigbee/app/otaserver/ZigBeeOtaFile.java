/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.otaserver;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeStackType;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;

/**
 * Defines a ZigBee Over The Air upgrade file.
 * <p>
 * This class will read the file header, and each tag from the file and provide methods to read this data within the
 * {@link ZclOtaUpgradeServer}.
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
 *
 */
public class ZigBeeOtaFile {

    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeOtaFile.class);

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
    private Integer fileVersion;

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
     * transferred over-the-air from the server to the client. In most cases, the total image size of an OTA
     * upgrade image file is the sum of the OTA header and the actual file data (along with its tag) lengths. If
     * the image is a signed image and contains a certificate of the signer, then the Total image size shall also
     * include the signer certificate and the signature (along with their tags) in bytes.
     */
    private Integer imageSize;

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

    /**
     * The bit mask indicates whether additional information such as Image Signature or Signing Certificate
     * are included as part of the OTA Upgrade Image.
     */
    private int headerFieldControl;

    private final int FIELD_CTL_SECURITY_CREDENTIAL = 0x01;
    private final int FIELD_CTL_DEVICE_SPECIFIC = 0x02;
    private final int FIELD_CTL_HARDWARE_VERSIONS = 0x04;

    private final int FILE_SIGNATURE = 0x0BEEF11E;

    /**
     * The file data
     */
    private byte[] fileData = null;

    /**
     * The file pointer
     */
    private int filePointer = 0;

    /**
     * Create a {@link ZigBeeOtaFile} from a {@link byte[]}
     *
     * @param fileData the byte array containing the OTA file data
     */
    public ZigBeeOtaFile(byte[] fileData) {
        if (fileData == null) {
            throw new IllegalArgumentException("ZigBeeOtaFile data can not be null.");
        }

        this.fileData = fileData;

        readOtaFile();
    }

    /**
     * Create a {@link ZigBeeOtaFile} from a {@link File}. This reads the file and gets all the header items we need to
     * use for the transfer.
     */
    private void readOtaFile() {
        if (fileData == null) {
            throw new IllegalArgumentException("ZigBeeOtaFile data can not be null.");
        }

        //
        // Read the file header

        // Unsigned 32-bit integer, OTA upgrade file identifier
        // The value is a unique 4-byte value that is included at the beginning of all ZigBee OTA upgrade image
        // files in order to quickly identify and distinguish the file as being a ZigBee OTA cluster upgrade file,
        // without having to examine the whole file content. This helps distinguishing the file from other file
        // types on disk. The value is defined to be “0x0BEEF11E”.
        if (readUnsigned32() != FILE_SIGNATURE) {
            throw new IllegalArgumentException("ZigBee OTA file is not a valid format");
        }

        // Unsigned 16-bit integer, OTA Header version
        headerVersion = readUnsigned16();

        // Unsigned 16-bit integer, OTA Header length
        headerLength = readUnsigned16();

        // Unsigned 16-bit integer, OTA Header Field control
        headerFieldControl = readUnsigned16();

        // Unsigned 16-bit integer, Manufacturer code
        manufacturerCode = readUnsigned16();

        // Unsigned 16-bit integer, Image type
        imageType = readUnsigned16();

        // Unsigned 32-bit integer, File version
        fileVersion = readUnsigned32();

        // Unsigned 16-bit integer, ZigBee Stack version
        stackVersion = ZigBeeStackType.getStackType(readUnsigned16());

        // Character string [32], OTA Header string
        byte[] stringBytes = Arrays.copyOfRange(fileData, filePointer, filePointer + 32);
        filePointer += 32;
        for (int cnt = 0; cnt < 32; cnt++) {
            if (stringBytes[cnt] == 0) {
                stringBytes = Arrays.copyOfRange(stringBytes, 0, cnt);
                break;
            }
        }
        try {
            headerString = new String(stringBytes, "ASCII");
        } catch (UnsupportedEncodingException e) {
            headerString = "";
        }

        // Unsigned 32-bit integer, Total Image size (including header)
        imageSize = readUnsigned32();

        if ((headerFieldControl & FIELD_CTL_SECURITY_CREDENTIAL) != 0) {
            // Unsigned 8-bit integer, Security credential version [optional]
            securityCredentialVersion = readUnsigned8();
        }

        if ((headerFieldControl & FIELD_CTL_DEVICE_SPECIFIC) != 0) {
            // IEEE Address, Upgrade file destination [optional]
            int[] addressBytes = new int[8];
            for (int cnt = 0; cnt < 8; cnt++) {
                addressBytes[cnt] = readUnsigned16();
            }
            destination = new IeeeAddress(addressBytes);
        }

        if ((headerFieldControl & FIELD_CTL_HARDWARE_VERSIONS) != 0) {
            // Unsigned 16-bit integer, Minimum hardware version [optional]
            minimumHardware = readUnsigned16();

            // Unsigned 16-bit integer, Maximum hardware version [optional]
            maximumHardware = readUnsigned16();
        }

        //
        // Read the tags
        while ((fileData.length - filePointer) >= 6) {
            // Tag Header -:
            // The tag identifier denotes the type and format of the data contained within the sub-element.
            int tagId = readUnsigned16();
            ZigBeeOtaTagType tagType = ZigBeeOtaTagType.getTagType(tagId);

            // The length dictates the length of the rest of the data within the sub-element in bytes. It does not
            // include the size of the Tag ID or the Length Fields.
            long tagLength = readUnsigned32();

            // Skip over the tag data
            filePointer += tagLength;

            logger.debug("Reading OTA image tag {}[{}] ({} bytes long)", tagType, String.format("%04X", tagId),
                    tagLength);
        }

    }

    /**
     * @return the headerVersion
     */
    public Integer getHeaderVersion() {
        return headerVersion;
    }

    /**
     * @return the headerLength
     */
    public Integer getHeaderLength() {
        return headerLength;
    }

    /**
     * @return the manufacturerCode
     */
    public Integer getManufacturerCode() {
        return manufacturerCode;
    }

    /**
     * @return the imageType
     */
    public Integer getImageType() {
        return imageType;
    }

    /**
     * @return the fileVersion
     */
    public Integer getFileVersion() {
        return fileVersion;
    }

    /**
     * @return the stackVersion
     */
    public ZigBeeStackType getStackVersion() {
        return stackVersion;
    }

    /**
     * @return the headerString
     */
    public String getHeaderString() {
        return headerString;
    }

    /**
     * Gets the overall size of the image including the data and header.
     *
     * @return the imageSize including the data and header
     */
    public Integer getImageSize() {
        return imageSize;
    }

    /**
     * Gets the security credential version. This is an optional field in the OTA file, and if it is not set in the file
     * this will return null.
     *
     * @return the securityCredentialVersion
     */
    public Integer getSecurityCredentialVersion() {
        return securityCredentialVersion;
    }

    /**
     * Gets the destination field. This is an optional field in the OTA file, and if it is not set in the file
     * this will return null.
     *
     * @return the destination
     */
    public IeeeAddress getDestination() {
        return destination;
    }

    /**
     * Gets the minimum hardware version applicable to this file. This is an optional field in the OTA file, and if it
     * is not set in the file this will return null.
     *
     * @return the minimumHardware
     */
    public Integer getMinimumHardware() {
        return minimumHardware;
    }

    /**
     * Gets the maximum hardware version applicable to this file. This is an optional field in the OTA file, and if it
     * is not set in the file this will return null.
     *
     * @return the maximumHardware
     */
    public Integer getMaximumHardware() {
        return maximumHardware;
    }

    /**
     * Gets a block of image data, returning it as a {@link ByteArray}
     *
     * @param fileOffset the file offset
     * @param dataSize the length of data to return. Note that the returned {@link ByteArray} may be shorter than
     *            requested if the end of file is reached.
     * @return the image data in a {@link ByteArray}. Null is returned if fileOffset is greater than or equal to the
     *         image size.
     */
    public ByteArray getImageData(Integer fileOffset, Integer dataSize) {
        if (fileOffset >= imageSize) {
            return null;
        }

        int length = Math.min(dataSize, imageSize - fileOffset);
        return new ByteArray(Arrays.copyOfRange(fileData, fileOffset, fileOffset + length));
    }

    private Integer readUnsigned8() {
        Integer value = Integer.valueOf(fileData[filePointer] & 0xff);
        filePointer += 1;
        return value;
    }

    private Integer readUnsigned16() {
        Integer value = Integer.valueOf((fileData[filePointer] & 0xff) + ((fileData[filePointer + 1] & 0xff) << 8));
        filePointer += 2;
        return value;
    }

    private Integer readUnsigned32() {
        Integer value = Integer.valueOf((fileData[filePointer] & 0xff) + ((fileData[filePointer + 1] & 0xff) << 8)
                + ((fileData[filePointer + 2] & 0xff) << 16) + ((fileData[filePointer + 3] & 0xff) << 24));
        filePointer += 4;
        return value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(170);
        builder.append("ZigBeeOtaFile [headerVersion=");
        builder.append(headerVersion);
        builder.append(", manufacturerCode=");
        builder.append(manufacturerCode);
        builder.append(", imageType=");
        builder.append(String.format("%04X", imageType));
        builder.append(", fileVersion=");
        builder.append(String.format("%08X", fileVersion));
        builder.append(", stackVersion=");
        builder.append(stackVersion);
        builder.append(", headerString=");
        builder.append(headerString);
        builder.append(", imageSize=");
        builder.append(imageSize);
        if ((headerFieldControl & FIELD_CTL_HARDWARE_VERSIONS) != 0) {
            builder.append(", minimumHardware=");
            builder.append(minimumHardware);
            builder.append(", maximumHardware=");
            builder.append(maximumHardware);
        }
        builder.append(']');
        return builder.toString();
    }

}
