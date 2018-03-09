/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberNetworkParameters;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberNodeType;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberStatus;

/**
 * Class to implement the Ember EZSP command <b>getNetworkParameters</b>.
 * <p>
 * Returns the current network parameters.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspGetNetworkParametersResponse extends EzspFrameResponse {
    public static int FRAME_ID = 0x28;

    /**
     * An EmberStatus value indicating success or the reason for failure.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     */
    private EmberStatus status;

    /**
     * An EmberNodeType value indicating the current node type.
     * <p>
     * EZSP type is <i>EmberNodeType</i> - Java type is {@link EmberNodeType}
     */
    private EmberNodeType nodeType;

    /**
     * The current network parameters.
     * <p>
     * EZSP type is <i>EmberNetworkParameters</i> - Java type is {@link EmberNetworkParameters}
     */
    private EmberNetworkParameters parameters;

    /**
     * Response and Handler constructor
     */
    public EzspGetNetworkParametersResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        status = deserializer.deserializeEmberStatus();
        nodeType = deserializer.deserializeEmberNodeType();
        parameters = deserializer.deserializeEmberNetworkParameters();
    }

    /**
     * An EmberStatus value indicating success or the reason for failure.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     *
     * @return the current status as {@link EmberStatus}
     */
    public EmberStatus getStatus() {
        return status;
    }

    /**
     * An EmberStatus value indicating success or the reason for failure.
     *
     * @param status the status to set as {@link EmberStatus}
     */
    public void setStatus(EmberStatus status) {
        this.status = status;
    }

    /**
     * An EmberNodeType value indicating the current node type.
     * <p>
     * EZSP type is <i>EmberNodeType</i> - Java type is {@link EmberNodeType}
     *
     * @return the current nodeType as {@link EmberNodeType}
     */
    public EmberNodeType getNodeType() {
        return nodeType;
    }

    /**
     * An EmberNodeType value indicating the current node type.
     *
     * @param nodeType the nodeType to set as {@link EmberNodeType}
     */
    public void setNodeType(EmberNodeType nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * The current network parameters.
     * <p>
     * EZSP type is <i>EmberNetworkParameters</i> - Java type is {@link EmberNetworkParameters}
     *
     * @return the current parameters as {@link EmberNetworkParameters}
     */
    public EmberNetworkParameters getParameters() {
        return parameters;
    }

    /**
     * The current network parameters.
     *
     * @param parameters the parameters to set as {@link EmberNetworkParameters}
     */
    public void setParameters(EmberNetworkParameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(110);
        builder.append("EzspGetNetworkParametersResponse [status=");
        builder.append(status);
        builder.append(", nodeType=");
        builder.append(nodeType);
        builder.append(", parameters=");
        builder.append(parameters);
        builder.append(']');
        return builder.toString();
    }
}
