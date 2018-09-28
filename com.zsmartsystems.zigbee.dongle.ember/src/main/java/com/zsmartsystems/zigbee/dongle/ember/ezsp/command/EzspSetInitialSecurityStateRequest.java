/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberInitialSecurityState;
import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspSerializer;

/**
 * Class to implement the Ember EZSP command <b>setInitialSecurityState</b>.
 * <p>
 * Sets the security state that will be used by the device when it forms or joins the network. This
 * call should not be used when restoring saved network state via networkInit as this will
 * result in a loss of security data and will cause communication problems when the device
 * re-enters the network.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspSetInitialSecurityStateRequest extends EzspFrameRequest {
    public static final int FRAME_ID = 0x68;

    /**
     * The security configuration to be set.
     * <p>
     * EZSP type is <i>EmberInitialSecurityState</i> - Java type is {@link EmberInitialSecurityState}
     */
    private EmberInitialSecurityState state;

    /**
     * Serialiser used to serialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspSetInitialSecurityStateRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }

    /**
     * The security configuration to be set.
     * <p>
     * EZSP type is <i>EmberInitialSecurityState</i> - Java type is {@link EmberInitialSecurityState}
     *
     * @return the current state as {@link EmberInitialSecurityState}
     */
    public EmberInitialSecurityState getState() {
        return state;
    }

    /**
     * The security configuration to be set.
     *
     * @param state the state to set as {@link EmberInitialSecurityState}
     */
    public void setState(EmberInitialSecurityState state) {
        this.state = state;
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(serializer);

        // Serialize the fields
        serializer.serializeEmberInitialSecurityState(state);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(62);
        builder.append("EzspSetInitialSecurityStateRequest [state=");
        builder.append(state);
        builder.append(']');
        return builder.toString();
    }
}
