/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspSerializer;

/**
 * Class to implement the Ember EZSP command <b>setExtendedTimeout</b>.
 * <p>
 * Tells the stack whether or not the normal interval between retransmissions of a retried
 * unicast message should be increased by EMBER_INDIRECT_TRANSMISSION_TIMEOUT. The
 * interval needs to be increased when sending to a sleepy node so that the message is not
 * retransmitted until the destination has had time to wake up and poll its parent. The stack
 * will automatically extend the timeout: - For our own sleepy children. - When an address
 * response is received from a parent on behalf of its child. - When an indirect transaction
 * expiry route error is received. - When an end device announcement is received from a sleepy
 * node.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspSetExtendedTimeoutRequest extends EzspFrameRequest {
    public static int FRAME_ID = 0x7E;

    /**
     * The address of the node for which the timeout is to be set.
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     */
    private IeeeAddress remoteEui64;

    /**
     * true if the retry interval should be increased by EMBER_INDIRECT_TRANSMISSION_TIMEOUT.
     * false if the normal retry interval should be used.
     * <p>
     * EZSP type is <i>bool</i> - Java type is {@link boolean}
     */
    private boolean extendedTimeout;

    /**
     * Serialiser used to seialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspSetExtendedTimeoutRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }

    /**
     * The address of the node for which the timeout is to be set.
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     *
     * @return the current remoteEui64 as {@link IeeeAddress}
     */
    public IeeeAddress getRemoteEui64() {
        return remoteEui64;
    }

    /**
     * The address of the node for which the timeout is to be set.
     *
     * @param remoteEui64 the remoteEui64 to set as {@link IeeeAddress}
     */
    public void setRemoteEui64(IeeeAddress remoteEui64) {
        this.remoteEui64 = remoteEui64;
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
    public int[] serialize() {
        // Serialize the header
        serializeHeader(serializer);

        // Serialize the fields
        serializer.serializeEmberEui64(remoteEui64);
        serializer.serializeBool(extendedTimeout);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EzspSetExtendedTimeoutRequest [remoteEui64=");
        builder.append(remoteEui64);
        builder.append(", extendedTimeout=");
        builder.append(extendedTimeout);
        builder.append("]");
        return builder.toString();
    }
}
