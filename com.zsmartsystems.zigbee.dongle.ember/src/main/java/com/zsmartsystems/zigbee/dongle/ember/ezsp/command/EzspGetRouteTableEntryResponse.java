/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberRouteTableEntry;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 * Class to implement the Ember EZSP command <b>getRouteTableEntry</b>.
 * <p>
 * Returns the route table entry at the given index. The route table size can be obtained using
 * the getConfigurationValue command.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspGetRouteTableEntryResponse extends EzspFrameResponse {
    public static final int FRAME_ID = 0x7B;

    /**
     * EMBER_ERR_FATAL if the index is out of range or the device is an end device, and EMBER_SUCCESS
     * otherwise.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     */
    private EmberStatus status;

    /**
     * The contents of the route table entry.
     * <p>
     * EZSP type is <i>EmberRouteTableEntry</i> - Java type is {@link EmberRouteTableEntry}
     */
    private EmberRouteTableEntry value;

    /**
     * Response and Handler constructor
     */
    public EzspGetRouteTableEntryResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        status = deserializer.deserializeEmberStatus();
        value = deserializer.deserializeEmberRouteTableEntry();
    }

    /**
     * EMBER_ERR_FATAL if the index is out of range or the device is an end device, and EMBER_SUCCESS
     * otherwise.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     *
     * @return the current status as {@link EmberStatus}
     */
    public EmberStatus getStatus() {
        return status;
    }

    /**
     * EMBER_ERR_FATAL if the index is out of range or the device is an end device, and EMBER_SUCCESS
     * otherwise.
     *
     * @param status the status to set as {@link EmberStatus}
     */
    public void setStatus(EmberStatus status) {
        this.status = status;
    }

    /**
     * The contents of the route table entry.
     * <p>
     * EZSP type is <i>EmberRouteTableEntry</i> - Java type is {@link EmberRouteTableEntry}
     *
     * @return the current value as {@link EmberRouteTableEntry}
     */
    public EmberRouteTableEntry getValue() {
        return value;
    }

    /**
     * The contents of the route table entry.
     *
     * @param value the value to set as {@link EmberRouteTableEntry}
     */
    public void setValue(EmberRouteTableEntry value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(83);
        builder.append("EzspGetRouteTableEntryResponse [status=");
        builder.append(status);
        builder.append(", value=");
        builder.append(value);
        builder.append(']');
        return builder.toString();
    }
}
