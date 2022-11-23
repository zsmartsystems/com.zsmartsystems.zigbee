/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 * Class to implement the Ember EZSP command <b>remoteDeleteBindingHandler</b>.
 * <p>
 * The NCP used the external binding modification policy to decide how to handle a remote delete
 * binding request. The Host cannot change the current decision, but it can change the policy
 * for future decisions using the setPolicy command.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspRemoteDeleteBindingHandler extends EzspFrameResponse {
    public static final int FRAME_ID = 0x32;

    /**
     * The index of a binding table entry.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int index;

    /**
     * EMBER_SUCCESS if the binding was removed from the table and any other status if not.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     */
    private EmberStatus policyDecision;

    /**
     * Response and Handler constructor
     */
    public EzspRemoteDeleteBindingHandler(int ezspVersion, int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(ezspVersion, inputBuffer);

        // Deserialize the fields
        index = deserializer.deserializeUInt8();
        policyDecision = deserializer.deserializeEmberStatus();
    }

    /**
     * The index of a binding table entry.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current index as {@link int}
     */
    public int getIndex() {
        return index;
    }

    /**
     * The index of a binding table entry.
     *
     * @param index the index to set as {@link int}
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * EMBER_SUCCESS if the binding was removed from the table and any other status if not.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     *
     * @return the current policyDecision as {@link EmberStatus}
     */
    public EmberStatus getPolicyDecision() {
        return policyDecision;
    }

    /**
     * EMBER_SUCCESS if the binding was removed from the table and any other status if not.
     *
     * @param policyDecision the policyDecision to set as {@link EmberStatus}
     */
    public void setPolicyDecision(EmberStatus policyDecision) {
        this.policyDecision = policyDecision;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(108);
        builder.append("EzspRemoteDeleteBindingHandler [networkId=");
        builder.append(networkId);
        builder.append(", index=");
        builder.append(index);
        builder.append(", policyDecision=");
        builder.append(policyDecision);
        builder.append(']');
        return builder.toString();
    }
}
