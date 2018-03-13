/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.ImageBlockCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.ImageBlockResponse;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.ImageNotifyCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.ImagePageCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.QueryNextImageCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.QueryNextImageResponse;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.QuerySpecificFileCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.QuerySpecificFileResponse;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.UpgradeEndCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.UpgradeEndResponse;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import javax.annotation.Generated;

/**
 * <b>OTA Upgrade</b> cluster implementation (<i>Cluster ID 0x0019</i>).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class ZclOtaUpgradeCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0019;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "OTA Upgrade";

    // Attribute constants
    /**
     * The attribute is used to store the IEEE address of the upgrade server resulted from the discovery of the
     * upgrade server’s identity. If the value is set to a non-zero value and corresponds to an IEEE address of
     * a device that is no longer accessible, a device may choose to discover a new Upgrade Server depending
     * on its own security policies.
     */
    public static final int ATTR_UPGRADESERVERID = 0x0000;
    /**
     * The parameter indicates the current location in the OTA upgrade image. It is essentially the (start of
     * the) address of the image data that is being transferred from the OTA server to the client. The attribute
     * is optional on the client and is made available in a case where the server wants to track the upgrade
     * process of a particular client.
     */
    public static final int ATTR_FILEOFFSET = 0x0001;
    /**
     * The file version of the running firmware image on the device. The information is available for the
     * server to query via ZCL read attribute command. The attribute is optional on the client.
     */
    public static final int ATTR_CURRENTFILEVERSION = 0x0002;
    /**
     * The ZigBee stack version of the running image on the device. The information is available for the
     * server to query via ZCL read attribute command. The attribute is optional on the client.
     */
    public static final int ATTR_CURRENTZIGBEESTACKVERSION = 0x0003;
    /**
     * The file version of the downloaded image on additional memory space on the device. The information
     * is available for the server to query via ZCL read attribute command. The information is useful for the
     * OTA upgrade management, so the server shall ensure that each client has downloaded the correct file
     * version before initiate the upgrade. The attribute is optional on the client.
     */
    public static final int ATTR_DOWNLOADEDFILEVERSION = 0x0004;
    /**
     * The ZigBee stack version of the downloaded image on additional memory space on the device. The
     * information is available for the server to query via ZCL read attribute command. The information is
     * useful for the OTA upgrade management, so the server shall ensure that each client has downloaded the
     * correct ZigBee stack version before initiate the upgrade. The attribute is optional on the client.
     */
    public static final int ATTR_DOWNLOADEDZIGBEESTACKVERSION = 0x0005;
    /**
     * The upgrade status of the client device. The status indicates where the client device is at in terms of the
     * download and upgrade process. The status helps to indicate whether the client has completed the
     * download process and whether it is ready to upgrade to the new image. The status may be queried by
     * the server via ZCL read attribute command. Hence, the server may not be able to reliably query the
     * status of ZED client since the device may have its radio off. The attribute is optional on the client.
     */
    public static final int ATTR_IMAGEUPGRADESTATUS = 0x0006;
    /**
     */
    public static final int ATTR_MANUFACTURERID = 0x0007;
    /**
     */
    public static final int ATTR_IMAGETYPEID = 0x0008;
    /**
     * This attribute acts as a rate limiting feature for the server to slow down the client download and prevent
     * saturating the network with block requests. The attribute lives on the client but can be changed during
     * a download if rate limiting is supported by both devices.
     * This attribute shall reflect the minimum delay between Image Block Request commands generated by
     * the client in milliseconds. The value of this attribute shall be updated when the rate is changed by the
     * server, but should reflect the client default when an upgrade is not in progress or a server does not
     * support this feature.
     * The value is in milliseconds and defaults to 0 (no delay).
     */
    public static final int ATTR_MINIMUMBLOCKREQUESTDELAY = 0x0009;
    /**
     */
    public static final int ATTR_IMAGESTAMP = 0x000A;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(11);

        attributeMap.put(ATTR_UPGRADESERVERID, new ZclAttribute(ZclClusterType.OTA_UPGRADE, ATTR_UPGRADESERVERID, "UpgradeServerID", ZclDataType.IEEE_ADDRESS, true, true, false, false));
        attributeMap.put(ATTR_FILEOFFSET, new ZclAttribute(ZclClusterType.OTA_UPGRADE, ATTR_FILEOFFSET, "FileOffset", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_CURRENTFILEVERSION, new ZclAttribute(ZclClusterType.OTA_UPGRADE, ATTR_CURRENTFILEVERSION, "CurrentFileVersion", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_CURRENTZIGBEESTACKVERSION, new ZclAttribute(ZclClusterType.OTA_UPGRADE, ATTR_CURRENTZIGBEESTACKVERSION, "CurrentZigBeeStackVersion", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_DOWNLOADEDFILEVERSION, new ZclAttribute(ZclClusterType.OTA_UPGRADE, ATTR_DOWNLOADEDFILEVERSION, "DownloadedFileVersion", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_DOWNLOADEDZIGBEESTACKVERSION, new ZclAttribute(ZclClusterType.OTA_UPGRADE, ATTR_DOWNLOADEDZIGBEESTACKVERSION, "DownloadedZigBeeStackVersion", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_IMAGEUPGRADESTATUS, new ZclAttribute(ZclClusterType.OTA_UPGRADE, ATTR_IMAGEUPGRADESTATUS, "ImageUpgradeStatus", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MANUFACTURERID, new ZclAttribute(ZclClusterType.OTA_UPGRADE, ATTR_MANUFACTURERID, "ManufacturerID", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_IMAGETYPEID, new ZclAttribute(ZclClusterType.OTA_UPGRADE, ATTR_IMAGETYPEID, "ImageTypeID", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MINIMUMBLOCKREQUESTDELAY, new ZclAttribute(ZclClusterType.OTA_UPGRADE, ATTR_MINIMUMBLOCKREQUESTDELAY, "MinimumBlockRequestDelay", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_IMAGESTAMP, new ZclAttribute(ZclClusterType.OTA_UPGRADE, ATTR_IMAGESTAMP, "ImageStamp", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a OTA Upgrade cluster.
     *
     * @param zigbeeManager {@link ZigBeeNetworkManager}
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint}
     */
    public ZclOtaUpgradeCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeManager, zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>UpgradeServerID</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The attribute is used to store the IEEE address of the upgrade server resulted from the discovery of the
     * upgrade server’s identity. If the value is set to a non-zero value and corresponds to an IEEE address of
     * a device that is no longer accessible, a device may choose to discover a new Upgrade Server depending
     * on its own security policies.
     * <p>
     * The attribute is of type {@link IeeeAddress}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getUpgradeServerIdAsync() {
        return read(attributes.get(ATTR_UPGRADESERVERID));
    }

    /**
     * Synchronously get the <i>UpgradeServerID</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The attribute is used to store the IEEE address of the upgrade server resulted from the discovery of the
     * upgrade server’s identity. If the value is set to a non-zero value and corresponds to an IEEE address of
     * a device that is no longer accessible, a device may choose to discover a new Upgrade Server depending
     * on its own security policies.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link IeeeAddress}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link IeeeAddress} attribute value, or null on error
     */
    public IeeeAddress getUpgradeServerId(final long refreshPeriod) {
        if (attributes.get(ATTR_UPGRADESERVERID).isLastValueCurrent(refreshPeriod)) {
            return (IeeeAddress) attributes.get(ATTR_UPGRADESERVERID).getLastValue();
        }

        return (IeeeAddress) readSync(attributes.get(ATTR_UPGRADESERVERID));
    }

    /**
     * Get the <i>FileOffset</i> attribute [attribute ID <b>1</b>].
     * <p>
     * The parameter indicates the current location in the OTA upgrade image. It is essentially the (start of
     * the) address of the image data that is being transferred from the OTA server to the client. The attribute
     * is optional on the client and is made available in a case where the server wants to track the upgrade
     * process of a particular client.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getFileOffsetAsync() {
        return read(attributes.get(ATTR_FILEOFFSET));
    }

    /**
     * Synchronously get the <i>FileOffset</i> attribute [attribute ID <b>1</b>].
     * <p>
     * The parameter indicates the current location in the OTA upgrade image. It is essentially the (start of
     * the) address of the image data that is being transferred from the OTA server to the client. The attribute
     * is optional on the client and is made available in a case where the server wants to track the upgrade
     * process of a particular client.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getFileOffset(final long refreshPeriod) {
        if (attributes.get(ATTR_FILEOFFSET).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_FILEOFFSET).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_FILEOFFSET));
    }

    /**
     * Get the <i>CurrentFileVersion</i> attribute [attribute ID <b>2</b>].
     * <p>
     * The file version of the running firmware image on the device. The information is available for the
     * server to query via ZCL read attribute command. The attribute is optional on the client.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentFileVersionAsync() {
        return read(attributes.get(ATTR_CURRENTFILEVERSION));
    }

    /**
     * Synchronously get the <i>CurrentFileVersion</i> attribute [attribute ID <b>2</b>].
     * <p>
     * The file version of the running firmware image on the device. The information is available for the
     * server to query via ZCL read attribute command. The attribute is optional on the client.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getCurrentFileVersion(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENTFILEVERSION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENTFILEVERSION).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTFILEVERSION));
    }

    /**
     * Get the <i>CurrentZigBeeStackVersion</i> attribute [attribute ID <b>3</b>].
     * <p>
     * The ZigBee stack version of the running image on the device. The information is available for the
     * server to query via ZCL read attribute command. The attribute is optional on the client.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentZigBeeStackVersionAsync() {
        return read(attributes.get(ATTR_CURRENTZIGBEESTACKVERSION));
    }

    /**
     * Synchronously get the <i>CurrentZigBeeStackVersion</i> attribute [attribute ID <b>3</b>].
     * <p>
     * The ZigBee stack version of the running image on the device. The information is available for the
     * server to query via ZCL read attribute command. The attribute is optional on the client.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getCurrentZigBeeStackVersion(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENTZIGBEESTACKVERSION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENTZIGBEESTACKVERSION).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTZIGBEESTACKVERSION));
    }

    /**
     * Get the <i>DownloadedFileVersion</i> attribute [attribute ID <b>4</b>].
     * <p>
     * The file version of the downloaded image on additional memory space on the device. The information
     * is available for the server to query via ZCL read attribute command. The information is useful for the
     * OTA upgrade management, so the server shall ensure that each client has downloaded the correct file
     * version before initiate the upgrade. The attribute is optional on the client.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDownloadedFileVersionAsync() {
        return read(attributes.get(ATTR_DOWNLOADEDFILEVERSION));
    }

    /**
     * Synchronously get the <i>DownloadedFileVersion</i> attribute [attribute ID <b>4</b>].
     * <p>
     * The file version of the downloaded image on additional memory space on the device. The information
     * is available for the server to query via ZCL read attribute command. The information is useful for the
     * OTA upgrade management, so the server shall ensure that each client has downloaded the correct file
     * version before initiate the upgrade. The attribute is optional on the client.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getDownloadedFileVersion(final long refreshPeriod) {
        if (attributes.get(ATTR_DOWNLOADEDFILEVERSION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DOWNLOADEDFILEVERSION).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DOWNLOADEDFILEVERSION));
    }

    /**
     * Get the <i>DownloadedZigBeeStackVersion</i> attribute [attribute ID <b>5</b>].
     * <p>
     * The ZigBee stack version of the downloaded image on additional memory space on the device. The
     * information is available for the server to query via ZCL read attribute command. The information is
     * useful for the OTA upgrade management, so the server shall ensure that each client has downloaded the
     * correct ZigBee stack version before initiate the upgrade. The attribute is optional on the client.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDownloadedZigBeeStackVersionAsync() {
        return read(attributes.get(ATTR_DOWNLOADEDZIGBEESTACKVERSION));
    }

    /**
     * Synchronously get the <i>DownloadedZigBeeStackVersion</i> attribute [attribute ID <b>5</b>].
     * <p>
     * The ZigBee stack version of the downloaded image on additional memory space on the device. The
     * information is available for the server to query via ZCL read attribute command. The information is
     * useful for the OTA upgrade management, so the server shall ensure that each client has downloaded the
     * correct ZigBee stack version before initiate the upgrade. The attribute is optional on the client.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getDownloadedZigBeeStackVersion(final long refreshPeriod) {
        if (attributes.get(ATTR_DOWNLOADEDZIGBEESTACKVERSION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DOWNLOADEDZIGBEESTACKVERSION).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DOWNLOADEDZIGBEESTACKVERSION));
    }

    /**
     * Get the <i>ImageUpgradeStatus</i> attribute [attribute ID <b>6</b>].
     * <p>
     * The upgrade status of the client device. The status indicates where the client device is at in terms of the
     * download and upgrade process. The status helps to indicate whether the client has completed the
     * download process and whether it is ready to upgrade to the new image. The status may be queried by
     * the server via ZCL read attribute command. Hence, the server may not be able to reliably query the
     * status of ZED client since the device may have its radio off. The attribute is optional on the client.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getImageUpgradeStatusAsync() {
        return read(attributes.get(ATTR_IMAGEUPGRADESTATUS));
    }

    /**
     * Synchronously get the <i>ImageUpgradeStatus</i> attribute [attribute ID <b>6</b>].
     * <p>
     * The upgrade status of the client device. The status indicates where the client device is at in terms of the
     * download and upgrade process. The status helps to indicate whether the client has completed the
     * download process and whether it is ready to upgrade to the new image. The status may be queried by
     * the server via ZCL read attribute command. Hence, the server may not be able to reliably query the
     * status of ZED client since the device may have its radio off. The attribute is optional on the client.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getImageUpgradeStatus(final long refreshPeriod) {
        if (attributes.get(ATTR_IMAGEUPGRADESTATUS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_IMAGEUPGRADESTATUS).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_IMAGEUPGRADESTATUS));
    }

    /**
     * Get the <i>ManufacturerID</i> attribute [attribute ID <b>7</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getManufacturerIdAsync() {
        return read(attributes.get(ATTR_MANUFACTURERID));
    }

    /**
     * Synchronously get the <i>ManufacturerID</i> attribute [attribute ID <b>7</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getManufacturerId(final long refreshPeriod) {
        if (attributes.get(ATTR_MANUFACTURERID).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MANUFACTURERID).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MANUFACTURERID));
    }

    /**
     * Get the <i>ImageTypeID</i> attribute [attribute ID <b>8</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getImageTypeIdAsync() {
        return read(attributes.get(ATTR_IMAGETYPEID));
    }

    /**
     * Synchronously get the <i>ImageTypeID</i> attribute [attribute ID <b>8</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getImageTypeId(final long refreshPeriod) {
        if (attributes.get(ATTR_IMAGETYPEID).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_IMAGETYPEID).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_IMAGETYPEID));
    }

    /**
     * Get the <i>MinimumBlockRequestDelay</i> attribute [attribute ID <b>9</b>].
     * <p>
     * This attribute acts as a rate limiting feature for the server to slow down the client download and prevent
     * saturating the network with block requests. The attribute lives on the client but can be changed during
     * a download if rate limiting is supported by both devices.
     * This attribute shall reflect the minimum delay between Image Block Request commands generated by
     * the client in milliseconds. The value of this attribute shall be updated when the rate is changed by the
     * server, but should reflect the client default when an upgrade is not in progress or a server does not
     * support this feature.
     * The value is in milliseconds and defaults to 0 (no delay).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMinimumBlockRequestDelayAsync() {
        return read(attributes.get(ATTR_MINIMUMBLOCKREQUESTDELAY));
    }

    /**
     * Synchronously get the <i>MinimumBlockRequestDelay</i> attribute [attribute ID <b>9</b>].
     * <p>
     * This attribute acts as a rate limiting feature for the server to slow down the client download and prevent
     * saturating the network with block requests. The attribute lives on the client but can be changed during
     * a download if rate limiting is supported by both devices.
     * This attribute shall reflect the minimum delay between Image Block Request commands generated by
     * the client in milliseconds. The value of this attribute shall be updated when the rate is changed by the
     * server, but should reflect the client default when an upgrade is not in progress or a server does not
     * support this feature.
     * The value is in milliseconds and defaults to 0 (no delay).
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMinimumBlockRequestDelay(final long refreshPeriod) {
        if (attributes.get(ATTR_MINIMUMBLOCKREQUESTDELAY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MINIMUMBLOCKREQUESTDELAY).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MINIMUMBLOCKREQUESTDELAY));
    }

    /**
     * Get the <i>ImageStamp</i> attribute [attribute ID <b>10</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getImageStampAsync() {
        return read(attributes.get(ATTR_IMAGESTAMP));
    }

    /**
     * Synchronously get the <i>ImageStamp</i> attribute [attribute ID <b>10</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getImageStamp(final long refreshPeriod) {
        if (attributes.get(ATTR_IMAGESTAMP).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_IMAGESTAMP).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_IMAGESTAMP));
    }

    /**
     * The Image Notify Command
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
     *
     * @param payloadType {@link Integer} Payload type
     * @param queryJitter {@link Integer} Query jitter
     * @param manufacturerCode {@link Integer} Manufacturer code
     * @param imageType {@link Integer} Image type
     * @param newFileVersion {@link Integer} New File Version
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> imageNotifyCommand(Integer payloadType, Integer queryJitter, Integer manufacturerCode, Integer imageType, Integer newFileVersion) {
        ImageNotifyCommand command = new ImageNotifyCommand();

        // Set the fields
        command.setPayloadType(payloadType);
        command.setQueryJitter(queryJitter);
        command.setManufacturerCode(manufacturerCode);
        command.setImageType(imageType);
        command.setNewFileVersion(newFileVersion);

        return send(command);
    }

    /**
     * The Query Next Image Command
     * <p>
     * Client devices shall send a Query Next Image Request command to the server to see if there is new
     * OTA upgrade image available. ZR devices may send the command after receiving Image Notify
     * command. ZED device shall periodically wake up and send the command to the upgrade server. Client
     * devices query what the next image is, based on their own information.
     * <br>
     * The server takes the client’s information in the command and determines whether it has a suitable
     * image for the particular client. The decision should be based on specific policy that is specific to the
     * upgrade server and outside the scope of this document.. However, a recommended default policy is for
     * the server to send back a response that indicates the availability of an image that matches the
     * manufacturer code, image type, and the highest available file version of that image on the
     * server. However, the server may choose to upgrade, downgrade, or reinstall clients’ image, as its
     * policy dictates. If client’s hardware version is included in the command, the server shall examine the
     * value against the minimum and maximum hardware versions included in the OTA file header.
     *
     * @param fieldControl {@link Integer} Field control
     * @param manufacturerCode {@link Integer} Manufacturer code
     * @param imageType {@link Integer} Image type
     * @param fileVersion {@link Integer} File version
     * @param hardwareVersion {@link Integer} Hardware version
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> queryNextImageCommand(Integer fieldControl, Integer manufacturerCode, Integer imageType, Integer fileVersion, Integer hardwareVersion) {
        QueryNextImageCommand command = new QueryNextImageCommand();

        // Set the fields
        command.setFieldControl(fieldControl);
        command.setManufacturerCode(manufacturerCode);
        command.setImageType(imageType);
        command.setFileVersion(fileVersion);
        command.setHardwareVersion(hardwareVersion);

        return send(command);
    }

    /**
     * The Image Block Command
     * <p>
     * The client device requests the image data at its leisure by sending Image Block Request command to
     * the upgrade server. The client knows the total number of request commands it needs to send from the
     * image size value received in Query Next Image Response command.
     * <br>
     * The client repeats Image Block Requests until it has successfully obtained all data. Manufacturer code,
     * image type and file version are included in all further queries regarding that image. The information
     * eliminates the need for the server to remember which OTA Upgrade Image is being used for each
     * download process.
     * <br>
     * If the client supports the BlockRequestDelay attribute it shall include the value of the attribute as the
     * BlockRequestDelay field of the Image Block Request message. The client shall ensure that it delays at
     * least BlockRequestDelay milliseconds after the previous Image Block Request was sent before sending
     * the next Image Block Request message. A client may delay its next Image Block Requests longer than
     * its BlockRequestDelay attribute.
     *
     * @param fieldControl {@link Integer} Field control
     * @param manufacturerCode {@link Integer} Manufacturer code
     * @param imageType {@link Integer} Image type
     * @param fileVersion {@link Integer} File version
     * @param fileOffset {@link Integer} File offset
     * @param maximumDataSize {@link Integer} Maximum data size
     * @param requestNodeAddress {@link IeeeAddress} Request node address
     * @param blockRequestDelay {@link Integer} BlockRequestDelay
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> imageBlockCommand(Integer fieldControl, Integer manufacturerCode, Integer imageType, Integer fileVersion, Integer fileOffset, Integer maximumDataSize, IeeeAddress requestNodeAddress, Integer blockRequestDelay) {
        ImageBlockCommand command = new ImageBlockCommand();

        // Set the fields
        command.setFieldControl(fieldControl);
        command.setManufacturerCode(manufacturerCode);
        command.setImageType(imageType);
        command.setFileVersion(fileVersion);
        command.setFileOffset(fileOffset);
        command.setMaximumDataSize(maximumDataSize);
        command.setRequestNodeAddress(requestNodeAddress);
        command.setBlockRequestDelay(blockRequestDelay);

        return send(command);
    }

    /**
     * The Image Page Command
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
     *
     * @param fieldControl {@link Integer} Field control
     * @param manufacturerCode {@link Integer} Manufacturer code
     * @param imageType {@link Integer} Image type
     * @param fileVersion {@link Integer} File version
     * @param fileOffset {@link Integer} File offset
     * @param maximumDataSize {@link Integer} Maximum data size
     * @param pageSize {@link Integer} Page size
     * @param responseSpacing {@link Integer} Response spacing
     * @param requestNodeAddress {@link IeeeAddress} Request node address
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> imagePageCommand(Integer fieldControl, Integer manufacturerCode, Integer imageType, Integer fileVersion, Integer fileOffset, Integer maximumDataSize, Integer pageSize, Integer responseSpacing, IeeeAddress requestNodeAddress) {
        ImagePageCommand command = new ImagePageCommand();

        // Set the fields
        command.setFieldControl(fieldControl);
        command.setManufacturerCode(manufacturerCode);
        command.setImageType(imageType);
        command.setFileVersion(fileVersion);
        command.setFileOffset(fileOffset);
        command.setMaximumDataSize(maximumDataSize);
        command.setPageSize(pageSize);
        command.setResponseSpacing(responseSpacing);
        command.setRequestNodeAddress(requestNodeAddress);

        return send(command);
    }

    /**
     * The Upgrade End Command
     * <p>
     * Upon reception all the image data, the client should verify the image to ensure its integrity and validity.
     * If the device requires signed images it shall examine the image and verify the signature. Clients may perform
     * additional manufacturer specific integrity checks to validate the image, for example, CRC check on the actual file data.
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
     *
     * @param status {@link ZclStatus} Status
     * @param manufacturerCode {@link Integer} Manufacturer code
     * @param imageType {@link Integer} Image type
     * @param fileVersion {@link Integer} File Version
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> upgradeEndCommand(ZclStatus status, Integer manufacturerCode, Integer imageType, Integer fileVersion) {
        UpgradeEndCommand command = new UpgradeEndCommand();

        // Set the fields
        command.setStatus(status);
        command.setManufacturerCode(manufacturerCode);
        command.setImageType(imageType);
        command.setFileVersion(fileVersion);

        return send(command);
    }

    /**
     * The Query Specific File Command
     * <p>
     * Client devices shall send a Query Specific File Request command to the server to request for a file that
     * is specific and unique to it. Such file could contain non-firmware data such as security credential
     * (needed for upgrading from Smart Energy 1.1 to Smart Energy 2.0), configuration or log. When the
     * device decides to send the Query Specific File Request command is manufacturer specific. However,
     * one example is during upgrading from SE 1.1 to 2.0 where the client may have already obtained new
     * SE 2.0 image and now needs new SE 2.0 security credential data.
     *
     * @param requestNodeAddress {@link IeeeAddress} Request node address
     * @param manufacturerCode {@link Integer} Manufacturer code
     * @param imageType {@link Integer} Image type
     * @param fileVersion {@link Integer} File Version
     * @param zigbeeStackVersion {@link Integer} Zigbee Stack Version
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> querySpecificFileCommand(IeeeAddress requestNodeAddress, Integer manufacturerCode, Integer imageType, Integer fileVersion, Integer zigbeeStackVersion) {
        QuerySpecificFileCommand command = new QuerySpecificFileCommand();

        // Set the fields
        command.setRequestNodeAddress(requestNodeAddress);
        command.setManufacturerCode(manufacturerCode);
        command.setImageType(imageType);
        command.setFileVersion(fileVersion);
        command.setZigbeeStackVersion(zigbeeStackVersion);

        return send(command);
    }

    /**
     * The Query Next Image Response
     * <p>
     * The upgrade server sends a Query Next Image Response with one of the following status: SUCCESS,
     * NO_IMAGE_AVAILABLE or NOT_AUTHORIZED. When a SUCCESS status is sent, it is
     * considered to be the explicit authorization to a device by the upgrade server that the device may
     * upgrade to a specific software image.
     * <br>
     * A status of NO_IMAGE_AVAILABLE indicates that the server is authorized to upgrade the client but
     * it currently does not have the (new) OTA upgrade image available for the client. For all clients (both
     * ZR and ZED)9 , they shall continue sending Query Next Image Requests to the server periodically until
     * an image becomes available.
     * <br>
     * A status of NOT_AUTHORIZED indicates the server is not authorized to upgrade the client. In this
     * case, the client may perform discovery again to find another upgrade server. The client may implement
     * an intelligence to avoid querying the same unauthorized server.
     *
     * @param status {@link ZclStatus} Status
     * @param manufacturerCode {@link Integer} Manufacturer code
     * @param imageType {@link Integer} Image type
     * @param fileVersion {@link Integer} File Version
     * @param imageSize {@link Integer} Image Size
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> queryNextImageResponse(ZclStatus status, Integer manufacturerCode, Integer imageType, Integer fileVersion, Integer imageSize) {
        QueryNextImageResponse command = new QueryNextImageResponse();

        // Set the fields
        command.setStatus(status);
        command.setManufacturerCode(manufacturerCode);
        command.setImageType(imageType);
        command.setFileVersion(fileVersion);
        command.setImageSize(imageSize);

        return send(command);
    }

    /**
     * The Image Block Response
     * <p>
     * Upon receipt of an Image Block Request command the server shall generate an Image Block Response.
     * If the server is able to retrieve the data for the client and does not wish to change the image download
     * rate, it will respond with a status of SUCCESS and it will include all the fields in the payload. The use
     * of file offset allows the server to send packets with variable data size during the upgrade process. This
     * allows the server to support a case when the network topology of a client may change during the
     * upgrade process, for example, mobile client may move around during the upgrade process. If the client
     * has moved a few hops away, the data size shall be smaller. Moreover, using file offset eliminates the
     * need for data padding since each Image Block Response command may contain different data size. A
     * simple server implementation may choose to only support largest possible data size for the worst-case
     * scenario in order to avoid supporting sending packets with variable data size.
     * <br>
     * The server shall respect the maximum data size value requested by the client and shall not send the data
     * with length greater than that value. The server may send the data with length smaller than the value
     * depending on the network topology of the client. For example, the client may be able to receive 100
     * bytes of data at once so it sends the request with 100 as maximum data size. But after considering all
     * the security headers (perhaps from both APS and network levels) and source routing overhead (for
     * example, the client is five hops away), the largest possible data size that the server can send to the
     * client shall be smaller than 100 bytes.
     *
     * @param status {@link ZclStatus} Status
     * @param manufacturerCode {@link Integer} Manufacturer code
     * @param imageType {@link Integer} Image type
     * @param fileVersion {@link Integer} File Version
     * @param fileOffset {@link Integer} File offset
     * @param imageData {@link ByteArray} Image Data
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> imageBlockResponse(ZclStatus status, Integer manufacturerCode, Integer imageType, Integer fileVersion, Integer fileOffset, ByteArray imageData) {
        ImageBlockResponse command = new ImageBlockResponse();

        // Set the fields
        command.setStatus(status);
        command.setManufacturerCode(manufacturerCode);
        command.setImageType(imageType);
        command.setFileVersion(fileVersion);
        command.setFileOffset(fileOffset);
        command.setImageData(imageData);

        return send(command);
    }

    /**
     * The Upgrade End Response
     * <p>
     * When an upgrade server receives an Upgrade End Request command with a status of
     * INVALID_IMAGE, REQUIRE_MORE_IMAGE, or ABORT, no additional processing shall be done
     * in its part. If the upgrade server receives an Upgrade End Request command with a status of
     * SUCCESS, it shall generate an Upgrade End Response with the manufacturer code and image type
     * received in the Upgrade End Request along with the times indicating when the device should upgrade
     * to the new image.
     * <br>
     * The server may send an unsolicited Upgrade End Response command to the client. This may be used
     * for example if the server wants to synchronize the upgrade on multiple clients simultaneously. For
     * client devices, the upgrade server may unicast or broadcast Upgrade End Response command
     * indicating a single client device or multiple client devices shall switch to using their new images. The
     * command may not be reliably received by sleepy devices if it is sent unsolicited.
     *
     * @param manufacturerCode {@link Integer} Manufacturer code
     * @param imageType {@link Integer} Image type
     * @param fileVersion {@link Integer} File Version
     * @param currentTime {@link Integer} Current Time
     * @param upgradeTime {@link Integer} Upgrade Time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> upgradeEndResponse(Integer manufacturerCode, Integer imageType, Integer fileVersion, Integer currentTime, Integer upgradeTime) {
        UpgradeEndResponse command = new UpgradeEndResponse();

        // Set the fields
        command.setManufacturerCode(manufacturerCode);
        command.setImageType(imageType);
        command.setFileVersion(fileVersion);
        command.setCurrentTime(currentTime);
        command.setUpgradeTime(upgradeTime);

        return send(command);
    }

    /**
     * The Query Specific File Response
     * <p>
     * The server sends Query Specific File Response after receiving Query Specific File Request from a
     * client. The server shall determine whether it first supports the Query Specific File Request command.
     * Then it shall determine whether it has the specific file being requested by the client using all the
     * information included in the request. The upgrade server sends a Query Specific File Response with
     * one of the following status: SUCCESS, NO_IMAGE_AVAILABLE or NOT_AUTHORIZED.
     * <br>
     * A status of NO_IMAGE_AVAILABLE indicates that the server currently does not have the device
     * specific file available for the client. A status of NOT_AUTHORIZED indicates the server is not
     * authorized to send the file to the client.
     *
     * @param status {@link ZclStatus} Status
     * @param manufacturerCode {@link Integer} Manufacturer code
     * @param imageType {@link Integer} Image type
     * @param fileVersion {@link Integer} File Version
     * @param imageSize {@link Integer} Image Size
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> querySpecificFileResponse(ZclStatus status, Integer manufacturerCode, Integer imageType, Integer fileVersion, Integer imageSize) {
        QuerySpecificFileResponse command = new QuerySpecificFileResponse();

        // Set the fields
        command.setStatus(status);
        command.setManufacturerCode(manufacturerCode);
        command.setImageType(imageType);
        command.setFileVersion(fileVersion);
        command.setImageSize(imageSize);

        return send(command);
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0: // IMAGE_NOTIFY_COMMAND
                return new ImageNotifyCommand();
            case 1: // QUERY_NEXT_IMAGE_COMMAND
                return new QueryNextImageCommand();
            case 3: // IMAGE_BLOCK_COMMAND
                return new ImageBlockCommand();
            case 4: // IMAGE_PAGE_COMMAND
                return new ImagePageCommand();
            case 6: // UPGRADE_END_COMMAND
                return new UpgradeEndCommand();
            case 8: // QUERY_SPECIFIC_FILE_COMMAND
                return new QuerySpecificFileCommand();
            default:
                return null;
        }
    }

    @Override
    public ZclCommand getResponseFromId(int commandId) {
        switch (commandId) {
            case 2: // QUERY_NEXT_IMAGE_RESPONSE
                return new QueryNextImageResponse();
            case 5: // IMAGE_BLOCK_RESPONSE
                return new ImageBlockResponse();
            case 7: // UPGRADE_END_RESPONSE
                return new UpgradeEndResponse();
            case 9: // QUERY_SPECIFIC_FILE_RESPONSE
                return new QuerySpecificFileResponse();
            default:
                return null;
        }
    }
}
