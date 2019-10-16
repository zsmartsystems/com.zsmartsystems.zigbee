/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;

/**
 * Class to implement the Ember EZSP command <b>getParentChildParameters</b>.
 * <p>
 * Returns information about the children of the local node and the parent of the local node.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspGetParentChildParametersResponse extends EzspFrameResponse {
    public static final int FRAME_ID = 0x29;

    /**
     * The number of children the node currently has.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int childCount;

    /**
     * The parent's EUI64. The value is undefined for nodes without parents (coordinators and
     * nodes that are not joined to a network)
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     */
    private IeeeAddress parentEui64;

    /**
     * The parent's node ID. The value is undefined for nodes without parents (coordinators and
     * nodes that are not joined to a network).
     * <p>
     * EZSP type is <i>EmberNodeId</i> - Java type is {@link int}
     */
    private int parentNodeId;

    /**
     * Response and Handler constructor
     */
    public EzspGetParentChildParametersResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        childCount = deserializer.deserializeUInt8();
        parentEui64 = deserializer.deserializeEmberEui64();
        parentNodeId = deserializer.deserializeUInt16();
    }

    /**
     * The number of children the node currently has.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current childCount as {@link int}
     */
    public int getChildCount() {
        return childCount;
    }

    /**
     * The number of children the node currently has.
     *
     * @param childCount the childCount to set as {@link int}
     */
    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    /**
     * The parent's EUI64. The value is undefined for nodes without parents (coordinators and
     * nodes that are not joined to a network)
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     *
     * @return the current parentEui64 as {@link IeeeAddress}
     */
    public IeeeAddress getParentEui64() {
        return parentEui64;
    }

    /**
     * The parent's EUI64. The value is undefined for nodes without parents (coordinators and
     * nodes that are not joined to a network)
     *
     * @param parentEui64 the parentEui64 to set as {@link IeeeAddress}
     */
    public void setParentEui64(IeeeAddress parentEui64) {
        this.parentEui64 = parentEui64;
    }

    /**
     * The parent's node ID. The value is undefined for nodes without parents (coordinators and
     * nodes that are not joined to a network).
     * <p>
     * EZSP type is <i>EmberNodeId</i> - Java type is {@link int}
     *
     * @return the current parentNodeId as {@link int}
     */
    public int getParentNodeId() {
        return parentNodeId;
    }

    /**
     * The parent's node ID. The value is undefined for nodes without parents (coordinators and
     * nodes that are not joined to a network).
     *
     * @param parentNodeId the parentNodeId to set as {@link int}
     */
    public void setParentNodeId(int parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(114);
        builder.append("EzspGetParentChildParametersResponse [childCount=");
        builder.append(childCount);
        builder.append(", parentEui64=");
        builder.append(parentEui64);
        builder.append(", parentNodeId=");
        builder.append(String.format("%04X", parentNodeId));
        builder.append(']');
        return builder.toString();
    }
}
