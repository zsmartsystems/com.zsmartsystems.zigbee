/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.util;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameResponse;
import com.zsmartsystems.zigbee.security.ZigBeeKey;

/**
 * Class to implement the Z-Stack command <b>UTIL_GET_NV_INFO</b>.
 * <p>
 * This command is used to read a block of parameters from non-volatile storage of the target device.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public class ZstackUtilGetNvInfoSrsp extends ZstackFrameResponse {

    /**
     * A value of zero indicates success. Failure is indicated by a non-zero value, representing a bit mask of each item that failed to be
     * retrieved from NV memory. Bit0 is used for the first item (IEEEAddress), bit1 for the second item (ScanChannels), and so forth.
     * Data values for failed items are returned as one or more bytes of 0xFF, the typical value read from erased NV memory.
     */
    private int status;

    /**
     * IEEE address of the device.
     */
    private IeeeAddress ieeeAddress;

    /**
     * This represents a bit-mask of channels to be scanned when starting the device.
     */
    private int scanChannels;

    /**
     * Specifies the Pan Id to start or join. Set to 0xFFFF to select a PAN after scanning.
     */
    private int panId;

    /**
     * This specifies the network messaging security level, zero disables security.
     */
    private int securityLevel;

    /**
     * This specifies the pre-configured security key.
     */
    private ZigBeeKey preConfigKey;

    /**
     * Response and Handler constructor
     */
    public ZstackUtilGetNvInfoSrsp(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        synchronousCommand = true;

        // Deserialize the fields
        status = deserializer.deserializeUInt8();
        ieeeAddress = deserializer.deserializeIeeeAddress();
        scanChannels = deserializer.deserializeUInt32();
        panId = deserializer.deserializeUInt16();
        securityLevel = deserializer.deserializeUInt8();
        preConfigKey = deserializer.deserializeZigBeeKey();
    }

    /**
     * A value of zero indicates success. Failure is indicated by a non-zero value, representing a bit mask of each item that failed to be
     * retrieved from NV memory. Bit0 is used for the first item (IEEEAddress), bit1 for the second item (ScanChannels), and so forth.
     * Data values for failed items are returned as one or more bytes of 0xFF, the typical value read from erased NV memory.
     *
     * @return the current status as {@link int}
     */
    public int getStatus() {
        return status;
    }

    /**
     * A value of zero indicates success. Failure is indicated by a non-zero value, representing a bit mask of each item that failed to be
     * retrieved from NV memory. Bit0 is used for the first item (IEEEAddress), bit1 for the second item (ScanChannels), and so forth.
     * Data values for failed items are returned as one or more bytes of 0xFF, the typical value read from erased NV memory.
     *
     * @param status the Status to set as {@link int}
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * IEEE address of the device.
     *
     * @return the current ieeeAddress as {@link IeeeAddress}
     */
    public IeeeAddress getIeeeAddress() {
        return ieeeAddress;
    }

    /**
     * IEEE address of the device.
     *
     * @param ieeeAddress the IeeeAddress to set as {@link IeeeAddress}
     */
    public void setIeeeAddress(IeeeAddress ieeeAddress) {
        this.ieeeAddress = ieeeAddress;
    }

    /**
     * This represents a bit-mask of channels to be scanned when starting the device.
     *
     * @return the current scanChannels as {@link int}
     */
    public int getScanChannels() {
        return scanChannels;
    }

    /**
     * This represents a bit-mask of channels to be scanned when starting the device.
     *
     * @param scanChannels the ScanChannels to set as {@link int}
     */
    public void setScanChannels(int scanChannels) {
        this.scanChannels = scanChannels;
    }

    /**
     * Specifies the Pan Id to start or join. Set to 0xFFFF to select a PAN after scanning.
     *
     * @return the current panId as {@link int}
     */
    public int getPanId() {
        return panId;
    }

    /**
     * Specifies the Pan Id to start or join. Set to 0xFFFF to select a PAN after scanning.
     *
     * @param panId the PanId to set as {@link int}
     */
    public void setPanId(int panId) {
        this.panId = panId;
    }

    /**
     * This specifies the network messaging security level, zero disables security.
     *
     * @return the current securityLevel as {@link int}
     */
    public int getSecurityLevel() {
        return securityLevel;
    }

    /**
     * This specifies the network messaging security level, zero disables security.
     *
     * @param securityLevel the SecurityLevel to set as {@link int}
     */
    public void setSecurityLevel(int securityLevel) {
        this.securityLevel = securityLevel;
    }

    /**
     * This specifies the pre-configured security key.
     *
     * @return the current preConfigKey as {@link ZigBeeKey}
     */
    public ZigBeeKey getPreConfigKey() {
        return preConfigKey;
    }

    /**
     * This specifies the pre-configured security key.
     *
     * @param preConfigKey the PreConfigKey to set as {@link ZigBeeKey}
     */
    public void setPreConfigKey(ZigBeeKey preConfigKey) {
        this.preConfigKey = preConfigKey;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(176);
        builder.append("ZstackUtilGetNvInfoSrsp [status=");
        builder.append(status);
        builder.append(", ieeeAddress=");
        builder.append(ieeeAddress);
        builder.append(", scanChannels=");
        builder.append(scanChannels);
        builder.append(", panId=");
        builder.append(String.format("%04X", panId));
        builder.append(", securityLevel=");
        builder.append(securityLevel);
        builder.append(", preConfigKey=");
        builder.append(preConfigKey);
        builder.append(']');
        return builder.toString();
    }
}
