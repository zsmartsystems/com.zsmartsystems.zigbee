/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberBeaconIterator;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 * Class to implement the Ember EZSP command <b>getFirstBeacon</b>.
 * <p>
 * Returns the first beacon in the cache. Beacons are stored in cache after issuing an active
 * scan.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspGetFirstBeaconResponse extends EzspFrameResponse {
    public static final int FRAME_ID = 0x3D;

    /**
     * EMBER_SUCCESS if first beacon found, EMBER_BAD_ARGUMENT if input parameters are invalid,
     * EMBER_INVALID_CALL if no beacons stored, EMBER_ERR_FATAL if no first beacon found.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     */
    private EmberStatus status;

    /**
     * The iterator to use when returning the first beacon. This argument must not be NULL.
     * <p>
     * EZSP type is <i>EmberBeaconIterator</i> - Java type is {@link EmberBeaconIterator}
     */
    private EmberBeaconIterator beaconIterator;

    /**
     * Response and Handler constructor
     */
    public EzspGetFirstBeaconResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        status = deserializer.deserializeEmberStatus();
        beaconIterator = deserializer.deserializeEmberBeaconIterator();
    }

    /**
     * EMBER_SUCCESS if first beacon found, EMBER_BAD_ARGUMENT if input parameters are invalid,
     * EMBER_INVALID_CALL if no beacons stored, EMBER_ERR_FATAL if no first beacon found.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     *
     * @return the current status as {@link EmberStatus}
     */
    public EmberStatus getStatus() {
        return status;
    }

    /**
     * EMBER_SUCCESS if first beacon found, EMBER_BAD_ARGUMENT if input parameters are invalid,
     * EMBER_INVALID_CALL if no beacons stored, EMBER_ERR_FATAL if no first beacon found.
     *
     * @param status the status to set as {@link EmberStatus}
     */
    public void setStatus(EmberStatus status) {
        this.status = status;
    }

    /**
     * The iterator to use when returning the first beacon. This argument must not be NULL.
     * <p>
     * EZSP type is <i>EmberBeaconIterator</i> - Java type is {@link EmberBeaconIterator}
     *
     * @return the current beaconIterator as {@link EmberBeaconIterator}
     */
    public EmberBeaconIterator getBeaconIterator() {
        return beaconIterator;
    }

    /**
     * The iterator to use when returning the first beacon. This argument must not be NULL.
     *
     * @param beaconIterator the beaconIterator to set as {@link EmberBeaconIterator}
     */
    public void setBeaconIterator(EmberBeaconIterator beaconIterator) {
        this.beaconIterator = beaconIterator;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(104);
        builder.append("EzspGetFirstBeaconResponse [networkId=");
        builder.append(networkId);
        builder.append(", status=");
        builder.append(status);
        builder.append(", beaconIterator=");
        builder.append(beaconIterator);
        builder.append(']');
        return builder.toString();
    }
}
