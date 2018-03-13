/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ResponseStatus;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;

/**
 * AF_DATA_SRSP_EXT command implementation.
 */
public class AF_DATA_SRSP_EXT extends ZToolPacket {
    /**
     * Response status.
     */
    private int status;

    /**
     * Constructor which sets frame data.
     *
     * @param framedata the frame data
     */
    public AF_DATA_SRSP_EXT(int[] framedata) {
        this.status = framedata[0];
        super.buildPacket(new DoubleByte(ZToolCMD.AF_DATA_SRSP_EXT), framedata);
    }

    @Override
    public String toString() {
        return "AF_DATA_SRSP_EXT(Status=" + ResponseStatus.getStatus(status) + ')';
    }

    /**
     * Gets response status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }
}
