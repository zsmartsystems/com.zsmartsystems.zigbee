/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.appcnf;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameRequest;
import com.zsmartsystems.zigbee.dongle.zstack.api.rpc.ZstackRpcSreqErrorSrsp;
import com.zsmartsystems.zigbee.security.ZigBeeKey;

/**
 * Class to implement the Z-Stack command <b>APP_CNF_BDB_ADD_INSTALLCODE</b>.
 * <p>
 * Add a preconfigured key (plain key or IC) to Trust Center device.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public class ZstackAppCnfBdbAddInstallcodeSreq extends ZstackFrameRequest {

    /**
     * This value specifies the format in which the install code is being added. The following list contains the values corresponding
     * to the supported formats: 0x01 Install Code + CRC 0x02 Key derived from Install Code
     */
    private ZstackInstallCodeFormat installCodeFormat;

    /**
     * Full IEEE address for the device joining the network
     */
    private IeeeAddress ieeeAddress;

    /**
     * 16 Bytes for the Key derived from the IC. 18 Bytes for the Install Code +CRC
     */
    private ZigBeeKey installCode;

    /**
     * Request constructor
     */
    public ZstackAppCnfBdbAddInstallcodeSreq() {
        synchronousCommand = true;
    }

    /**
     * This value specifies the format in which the install code is being added. The following list contains the values corresponding
     * to the supported formats: 0x01 Install Code + CRC 0x02 Key derived from Install Code
     *
     * @return the current installCodeFormat as {@link ZstackInstallCodeFormat}
     */
    public ZstackInstallCodeFormat getInstallCodeFormat() {
        return installCodeFormat;
    }

    /**
     * This value specifies the format in which the install code is being added. The following list contains the values corresponding
     * to the supported formats: 0x01 Install Code + CRC 0x02 Key derived from Install Code
     *
     * @param installCodeFormat the InstallCodeFormat to set as {@link ZstackInstallCodeFormat}
     */
    public void setInstallCodeFormat(ZstackInstallCodeFormat installCodeFormat) {
        this.installCodeFormat = installCodeFormat;
    }

    /**
     * Full IEEE address for the device joining the network
     *
     * @return the current ieeeAddress as {@link IeeeAddress}
     */
    public IeeeAddress getIeeeAddress() {
        return ieeeAddress;
    }

    /**
     * Full IEEE address for the device joining the network
     *
     * @param ieeeAddress the IeeeAddress to set as {@link IeeeAddress}
     */
    public void setIeeeAddress(IeeeAddress ieeeAddress) {
        this.ieeeAddress = ieeeAddress;
    }

    /**
     * 16 Bytes for the Key derived from the IC. 18 Bytes for the Install Code +CRC
     *
     * @return the current installCode as {@link ZigBeeKey}
     */
    public ZigBeeKey getInstallCode() {
        return installCode;
    }

    /**
     * 16 Bytes for the Key derived from the IC. 18 Bytes for the Install Code +CRC
     *
     * @param installCode the InstallCode to set as {@link ZigBeeKey}
     */
    public void setInstallCode(ZigBeeKey installCode) {
        this.installCode = installCode;
    }

    @Override
    public boolean matchSreqError(ZstackRpcSreqErrorSrsp response) {
        return (((response.getReqCmd0() & 0x1F) == ZSTACK_APP_CNF) && (response.getReqCmd1() == 0x02));
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(ZSTACK_SREQ, ZSTACK_APP_CNF, 0x02);

        // Serialize the fields
        serializeUInt8(installCodeFormat.getKey());
        serializeIeeeAddress(ieeeAddress);
        serializeZigBeeKey(installCode);
        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(111);
        builder.append("ZstackAppCnfBdbAddInstallcodeSreq [installCodeFormat=");
        builder.append(installCodeFormat);
        builder.append(", ieeeAddress=");
        builder.append(ieeeAddress);
        builder.append(", installCode=");
        builder.append(installCode);
        builder.append(']');
        return builder.toString();
    }
}
