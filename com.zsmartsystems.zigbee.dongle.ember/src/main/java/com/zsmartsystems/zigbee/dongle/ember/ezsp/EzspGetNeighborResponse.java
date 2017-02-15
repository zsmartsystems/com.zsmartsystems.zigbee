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
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNeighborTableEntry;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to implement the Ember EZSP command <b>getNeighbor</b>.
 * <p>
 * Returns the neighbor table entry at the given index. The number of active neighbors can be
 * obtained using the neighborCount command.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspGetNeighborResponse extends EzspFrameResponse {
    private static final Logger logger = LoggerFactory.getLogger(EzspGetNeighborResponse.class);

    /**
     * EMBER_ERR_FATAL if the index is greater or equal to the number of active neighbors, or if the
     * device is an end device. Returns EMBER_SUCCESS otherwise.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     */
    private EmberStatus status;

    /**
     * The contents of the neighbor table entry.
     * <p>
     * EZSP type is <i>EmberNeighborTableEntry</i> - Java type is {@link EmberNeighborTableEntry}
     */
    private EmberNeighborTableEntry value;

    /**
     * Response and Handler constructor
     */
    public EzspGetNeighborResponse(int[] inputBuffer) {
        super(inputBuffer);
    }

    /**
     * EMBER_ERR_FATAL if the index is greater or equal to the number of active neighbors, or if the
     * device is an end device. Returns EMBER_SUCCESS otherwise.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     *
     * @return the current status as {@link EmberStatus}
     */
    public EmberStatus getStatus() {
        return status;
    }

    /**
     * EMBER_ERR_FATAL if the index is greater or equal to the number of active neighbors, or if the
     * device is an end device. Returns EMBER_SUCCESS otherwise.
     *
     * @param status the status to set as {@link EmberStatus}
     */
    public void setStatus(EmberStatus status) {
        this.status = status;
    }

    /**
     * The contents of the neighbor table entry.
     * <p>
     * EZSP type is <i>EmberNeighborTableEntry</i> - Java type is {@link EmberNeighborTableEntry}
     *
     * @return the current value as {@link EmberNeighborTableEntry}
     */
    public EmberNeighborTableEntry getValue() {
        return value;
    }

    /**
     * The contents of the neighbor table entry.
     *
     * @param value the value to set as {@link EmberNeighborTableEntry}
     */
    public void setValue(EmberNeighborTableEntry value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EzspGetNeighborResponse [status=");
        builder.append(status);
        builder.append(", value=");
        builder.append(value);
        builder.append("]");
        return builder.toString();
    }
}
