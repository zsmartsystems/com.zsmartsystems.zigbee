/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspDeserializer;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to implement the Ember EZSP command <b>scanCompleteHandler</b>.
 * <p>
 * Returns the status of the current scan of type EZSP_ENERGY_SCAN or EZSP_ACTIVE_SCAN.
 * EMBER_SUCCESS signals that the scan has completed. Other error conditions signify a failure
 * to scan on the channel specified.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspScanCompleteHandlerResponse extends EzspFrameResponse {
    private static final Logger logger = LoggerFactory.getLogger(EzspScanCompleteHandlerResponse.class);

    /**
     * The channel on which the current error occurred. Undefined for the case of EMBER_SUCCESS.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int channel;

    /**
     * The error condition that occurred on the current channel. Value will be EMBER_SUCCESS when the
     * scan has completed.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     */
    private EmberStatus status;

    /**
     * Response and Handler constructor
     */
    public EzspScanCompleteHandlerResponse(int[] inputBuffer) {
        super(inputBuffer);
    }

    /**
     * The channel on which the current error occurred. Undefined for the case of EMBER_SUCCESS.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current channel as {@link int}
     */
    public int getChannel() {
        return channel;
    }

    /**
     * The channel on which the current error occurred. Undefined for the case of EMBER_SUCCESS.
     *
     * @param channel the channel to set as {@link int}
     */
    public void setChannel(int channel) {
        this.channel = channel;
    }

    /**
     * The error condition that occurred on the current channel. Value will be EMBER_SUCCESS when the
     * scan has completed.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     *
     * @return the current status as {@link EmberStatus}
     */
    public EmberStatus getStatus() {
        return status;
    }

    /**
     * The error condition that occurred on the current channel. Value will be EMBER_SUCCESS when the
     * scan has completed.
     *
     * @param status the status to set as {@link EmberStatus}
     */
    public void setStatus(EmberStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EzspScanCompleteHandlerResponse [channel=");
        builder.append(channel);
        builder.append(", status=");
        builder.append(status);
        builder.append("]");
        return builder.toString();
    }
}
