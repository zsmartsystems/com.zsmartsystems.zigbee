/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;

/**
 * Class to implement the Ember EZSP command <b>getEui64</b>.
 * <p>
 * Returns the EUI64 ID of the local node.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspGetEui64Response extends EzspFrameResponse {
    public static final int FRAME_ID = 0x26;

    /**
     * The 64-bit ID.
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     */
    private IeeeAddress eui64;

    /**
     * Response and Handler constructor
     */
    public EzspGetEui64Response(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        eui64 = deserializer.deserializeEmberEui64();
    }

    /**
     * The 64-bit ID.
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     *
     * @return the current eui64 as {@link IeeeAddress}
     */
    public IeeeAddress getEui64() {
        return eui64;
    }

    /**
     * The 64-bit ID.
     *
     * @param eui64 the eui64 to set as {@link IeeeAddress}
     */
    public void setEui64(IeeeAddress eui64) {
        this.eui64 = eui64;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(73);
        builder.append("EzspGetEui64Response [networkId=");
        builder.append(networkId);
        builder.append(", eui64=");
        builder.append(eui64);
        builder.append(']');
        return builder.toString();
    }
}
