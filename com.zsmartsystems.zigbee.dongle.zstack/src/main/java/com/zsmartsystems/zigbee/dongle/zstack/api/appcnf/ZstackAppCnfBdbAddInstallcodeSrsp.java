/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.appcnf;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameResponse;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackResponseCode;
import javax.annotation.Generated;

/**
 * Class to implement the Z-Stack command <b>APP_CNF_BDB_ADD_INSTALLCODE</b>.
 * <p>
 * Add a preconfigured key (plain key or IC) to Trust Center device.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 */

@Generated(value = "com.zsmartsystems.zigbee.dongle.zstack.autocode.CommandGenerator", date = "Sun Mar 26 09:52:48 CEST 2023")
public class ZstackAppCnfBdbAddInstallcodeSrsp extends ZstackFrameResponse {

    /**
     * Status values: 0x00 Success. 0x01 Failure (IC not supported) 0x02 Invalid parameter (bad CRC).
     */
    private ZstackResponseCode status;

    /**
     * Response and Handler constructor
     */
    public ZstackAppCnfBdbAddInstallcodeSrsp(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        synchronousCommand = true;

        // Deserialize the fields
        status = ZstackResponseCode.valueOf(deserializer.deserializeUInt8());
    }

    /**
     * Status values: 0x00 Success. 0x01 Failure (IC not supported) 0x02 Invalid parameter (bad CRC).
     *
     * @return the current status as {@link ZstackResponseCode}
     */
    public ZstackResponseCode getStatus() {
        return status;
    }

    /**
     * Status values: 0x00 Success. 0x01 Failure (IC not supported) 0x02 Invalid parameter (bad CRC).
     *
     * @param status the Status to set as {@link ZstackResponseCode}
     */
    public void setStatus(ZstackResponseCode status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(61);
        builder.append("ZstackAppCnfBdbAddInstallcodeSrsp [status=");
        builder.append(status);
        builder.append(']');
        return builder.toString();
    }
}
