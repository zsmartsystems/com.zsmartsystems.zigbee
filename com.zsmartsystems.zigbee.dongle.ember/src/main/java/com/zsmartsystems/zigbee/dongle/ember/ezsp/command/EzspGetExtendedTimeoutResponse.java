/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspDeserializer;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to implement the Ember EZSP command <b>getExtendedTimeout</b>.
 * <p>
 * Indicates whether or not the stack will extend the normal interval between retransmissions
 * of a retried unicast message by EMBER_INDIRECT_TRANSMISSION_TIMEOUT.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspGetExtendedTimeoutResponse extends EzspFrameResponse {
    public static int FRAME_ID = 0x7F;

    /**
     * true if the retry interval should be increased by EMBER_INDIRECT_TRANSMISSION_TIMEOUT.
     * false if the normal retry interval should be used.
     * <p>
     * EZSP type is <i>bool</i> - Java type is {@link boolean}
     */
    private boolean extendedTimeout;

    /**
     * Response and Handler constructor
     */
    public EzspGetExtendedTimeoutResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        extendedTimeout = deserializer.deserializeBool();
    }

    /**
     * true if the retry interval should be increased by EMBER_INDIRECT_TRANSMISSION_TIMEOUT.
     * false if the normal retry interval should be used.
     * <p>
     * EZSP type is <i>bool</i> - Java type is {@link boolean}
     *
     * @return the current extendedTimeout as {@link boolean}
     */
    public boolean getExtendedTimeout() {
        return extendedTimeout;
    }

    /**
     * true if the retry interval should be increased by EMBER_INDIRECT_TRANSMISSION_TIMEOUT.
     * false if the normal retry interval should be used.
     *
     * @param extendedTimeout the extendedTimeout to set as {@link boolean}
     */
    public void setExtendedTimeout(boolean extendedTimeout) {
        this.extendedTimeout = extendedTimeout;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EzspGetExtendedTimeoutResponse [extendedTimeout=");
        builder.append(extendedTimeout);
        builder.append("]");
        return builder.toString();
    }
}
