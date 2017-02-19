/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspSerializer;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to implement the Ember EZSP command <b>getBindingRemoteNodeId</b>.
 * <p>
 * Returns the node ID for the binding's destination, if the ID is known. If a message is sent
 * using the binding and the destination's ID is not known, the stack will discover the ID by
 * broadcasting a ZDO address request. The application can avoid the need for this discovery by
 * using setBindingRemoteNodeId when it knows the correct ID via some other means. The
 * destination's node ID is forgotten when the binding is changed, when the local node reboots
 * or, much more rarely, when the destination node changes its ID in response to an ID conflict.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspGetBindingRemoteNodeIdRequest extends EzspFrameRequest {
    public static int FRAME_ID = 0x2F;

    /**
     * The index of a binding table entry.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int index;

    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspGetBindingRemoteNodeIdRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
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

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(serializer);

        // Serialize the fields
        serializer.serializeUInt8(index);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EzspGetBindingRemoteNodeIdRequest [index=");
        builder.append(index);
        builder.append("]");
        return builder.toString();
    }
}
