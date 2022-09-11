/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;

/**
 * Class to implement the Ember EZSP command <b>setSourceRouteDiscoveryMode</b>.
 * <p>
 * : Sets source route discovery(MTORR) mode to on, off, reschedule.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspSetSourceRouteDiscoveryModeResponse extends EzspFrameResponse {
    public static final int FRAME_ID = 0x5A;

    /**
     * Remaining time(ms) until next MTORR broadcast if the mode is on, MAX_INT32U_VALUE if the
     * mode is off.
     * <p>
     * EZSP type is <i>uint32_t</i> - Java type is {@link int}
     */
    private int remainingTime;

    /**
     * Response and Handler constructor
     */
    public EzspSetSourceRouteDiscoveryModeResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        remainingTime = deserializer.deserializeUInt32();
    }

    /**
     * Remaining time(ms) until next MTORR broadcast if the mode is on, MAX_INT32U_VALUE if the
     * mode is off.
     * <p>
     * EZSP type is <i>uint32_t</i> - Java type is {@link int}
     *
     * @return the current remainingTime as {@link int}
     */
    public int getRemainingTime() {
        return remainingTime;
    }

    /**
     * Remaining time(ms) until next MTORR broadcast if the mode is on, MAX_INT32U_VALUE if the
     * mode is off.
     *
     * @param remainingTime the remainingTime to set as {@link int}
     */
    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(92);
        builder.append("EzspSetSourceRouteDiscoveryModeResponse [networkId=");
        builder.append(networkId);
        builder.append(", remainingTime=");
        builder.append(remainingTime);
        builder.append(']');
        return builder.toString();
    }
}
