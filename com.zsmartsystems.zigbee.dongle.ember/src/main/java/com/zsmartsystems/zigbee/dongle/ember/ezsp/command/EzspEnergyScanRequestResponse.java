/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspStatus;

/**
 * Class to implement the Ember EZSP command <b>energyScanRequest</b>.
 * <p>
 * Sends a ZDO energy scan request. This request may only be sent by the current network manager
 * and must be unicast, not broadcast. See ezsp-utils.h for related macros
 * emberSetNetworkManagerRequest() and emberChangeChannelRequest()
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspEnergyScanRequestResponse extends EzspFrameResponse {
    public static int FRAME_ID = 0x9C;

    /**
     * An EmberStatus value indicating success or the reason for failure.
     * <p>
     * EZSP type is <i>EzspStatus</i> - Java type is {@link EzspStatus}
     */
    private EzspStatus status;

    /**
     * Response and Handler constructor
     */
    public EzspEnergyScanRequestResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        status = deserializer.deserializeEzspStatus();
    }

    /**
     * An EmberStatus value indicating success or the reason for failure.
     * <p>
     * EZSP type is <i>EzspStatus</i> - Java type is {@link EzspStatus}
     *
     * @return the current status as {@link EzspStatus}
     */
    public EzspStatus getStatus() {
        return status;
    }

    /**
     * An EmberStatus value indicating success or the reason for failure.
     *
     * @param status the status to set as {@link EzspStatus}
     */
    public void setStatus(EzspStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EzspEnergyScanRequestResponse [status=");
        builder.append(status);
        builder.append("]");
        return builder.toString();
    }
}
