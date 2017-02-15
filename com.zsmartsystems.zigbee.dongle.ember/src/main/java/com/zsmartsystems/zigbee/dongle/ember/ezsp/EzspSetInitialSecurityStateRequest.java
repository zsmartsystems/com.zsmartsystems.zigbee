/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspSerializer;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberInitialSecurityState;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to implement the Ember EZSP command <b>setInitialSecurityState</b>.
 * <p>
 * Sets the security state that will be used by the device when it forms or joins the network. This
 * call should not be used when restoring saved network state via networkInit as this will result
 * in a loss of security data and will cause communication problems when the device re-enters the
 * network.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspSetInitialSecurityStateRequest extends EzspFrameRequest {
    private static final Logger logger = LoggerFactory.getLogger(EzspSetInitialSecurityStateRequest.class);

    /**
     * The security configuration to be set.
     * <p>
     * EZSP type is <i>EmberInitialSecurityState</i> - Java type is {@link EmberInitialSecurityState}
     */
    private EmberInitialSecurityState state;

    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspSetInitialSecurityStateRequest() {
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
        serializer.serializeEmberInitialSecurityState(state);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EzspSetInitialSecurityStateRequest [state=");
        builder.append(state);
        builder.append("]");
        return builder.toString();
    }
}
