/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspSerializer;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyData;

/**
 * Class to implement the Ember EZSP command <b>becomeTrustCenter</b>.
 * <p>
 * This function causes a coordinator to become the Trust Center when it is operating in a
 * network that is not using one. It will send out an updated Network Key to all devices that will
 * indicate a transition of the network to now use a Trust Center. The Trust Center should also
 * switch all devices to using this new network key with the appropriate API.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspBecomeTrustCenterRequest extends EzspFrameRequest {
    public static int FRAME_ID = 0x77;

    /**
     * The key data for the Updated Network Key.
     * <p>
     * EZSP type is <i>EmberKeyData</i> - Java type is {@link EmberKeyData}
     */
    private EmberKeyData newNetworkKey;

    /**
     * Serialiser used to seialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspBecomeTrustCenterRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }

    /**
     * The key data for the Updated Network Key.
     * <p>
     * EZSP type is <i>EmberKeyData</i> - Java type is {@link EmberKeyData}
     *
     * @return the current newNetworkKey as {@link EmberKeyData}
     */
    public EmberKeyData getNewNetworkKey() {
        return newNetworkKey;
    }

    /**
     * The key data for the Updated Network Key.
     *
     * @param newNetworkKey the newNetworkKey to set as {@link EmberKeyData}
     */
    public void setNewNetworkKey(EmberKeyData newNetworkKey) {
        this.newNetworkKey = newNetworkKey;
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(serializer);

        // Serialize the fields
        serializer.serializeEmberKeyData(newNetworkKey);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EzspBecomeTrustCenterRequest [newNetworkKey=");
        builder.append(newNetworkKey);
        builder.append("]");
        return builder.toString();
    }
}
