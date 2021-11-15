/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyStatus;

/**
 * Class to implement the Ember EZSP command <b>zigbeeKeyEstablishmentHandler</b>.
 * <p>
 * This is a callback that indicates the success or failure of an attempt to establish a key with a
 * partner device.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspZigbeeKeyEstablishmentHandler extends EzspFrameResponse {
    public static final int FRAME_ID = 0x9B;

    /**
     * This is the IEEE address of the partner that the device successfully established a key with.
     * This value is all zeros on a failure.
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     */
    private IeeeAddress partner;

    /**
     * This is the status indicating what was established or why the key establishment failed.
     * <p>
     * EZSP type is <i>EmberKeyStatus</i> - Java type is {@link EmberKeyStatus}
     */
    private EmberKeyStatus status;

    /**
     * Response and Handler constructor
     */
    public EzspZigbeeKeyEstablishmentHandler(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        partner = deserializer.deserializeEmberEui64();
        status = deserializer.deserializeEmberKeyStatus();
    }

    /**
     * This is the IEEE address of the partner that the device successfully established a key with.
     * This value is all zeros on a failure.
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     *
     * @return the current partner as {@link IeeeAddress}
     */
    public IeeeAddress getPartner() {
        return partner;
    }

    /**
     * This is the IEEE address of the partner that the device successfully established a key with.
     * This value is all zeros on a failure.
     *
     * @param partner the partner to set as {@link IeeeAddress}
     */
    public void setPartner(IeeeAddress partner) {
        this.partner = partner;
    }

    /**
     * This is the status indicating what was established or why the key establishment failed.
     * <p>
     * EZSP type is <i>EmberKeyStatus</i> - Java type is {@link EmberKeyStatus}
     *
     * @return the current status as {@link EmberKeyStatus}
     */
    public EmberKeyStatus getStatus() {
        return status;
    }

    /**
     * This is the status indicating what was established or why the key establishment failed.
     *
     * @param status the status to set as {@link EmberKeyStatus}
     */
    public void setStatus(EmberKeyStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(111);
        builder.append("EzspZigbeeKeyEstablishmentHandler [networkId=");
        builder.append(networkId);
        builder.append(", partner=");
        builder.append(partner);
        builder.append(", status=");
        builder.append(status);
        builder.append(']');
        return builder.toString();
    }
}
