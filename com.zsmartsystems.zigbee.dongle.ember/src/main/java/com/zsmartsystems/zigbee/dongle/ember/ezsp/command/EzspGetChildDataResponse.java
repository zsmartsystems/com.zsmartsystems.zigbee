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
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspDeserializer;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNodeType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 * Class to implement the Ember EZSP command <b>getChildData</b>.
 * <p>
 * Returns information about a child of the local node.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspGetChildDataResponse extends EzspFrameResponse {
    public static int FRAME_ID = 0x4A;

    /**
     * EMBER_SUCCESS if there is a child at index. EMBER_NOT_JOINED if there is no child at index.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     */
    private EmberStatus status;

    /**
     * The node ID of the child.
     * <p>
     * EZSP type is <i>EmberNodeId</i> - Java type is {@link int}
     */
    private int childId;

    /**
     * The EUI64 of the child
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     */
    private IeeeAddress childEui64;

    /**
     * The EmberNodeType value for the child.
     * <p>
     * EZSP type is <i>EmberNodeType</i> - Java type is {@link EmberNodeType}
     */
    private EmberNodeType childType;

    /**
     * Response and Handler constructor
     */
    public EzspGetChildDataResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        status = deserializer.deserializeEmberStatus();
        childId = deserializer.deserializeUInt16();
        childEui64 = deserializer.deserializeEmberEui64();
        childType = deserializer.deserializeEmberNodeType();
    }

    /**
     * EMBER_SUCCESS if there is a child at index. EMBER_NOT_JOINED if there is no child at index.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     *
     * @return the current status as {@link EmberStatus}
     */
    public EmberStatus getStatus() {
        return status;
    }

    /**
     * EMBER_SUCCESS if there is a child at index. EMBER_NOT_JOINED if there is no child at index.
     *
     * @param status the status to set as {@link EmberStatus}
     */
    public void setStatus(EmberStatus status) {
        this.status = status;
    }

    /**
     * The node ID of the child.
     * <p>
     * EZSP type is <i>EmberNodeId</i> - Java type is {@link int}
     *
     * @return the current childId as {@link int}
     */
    public int getChildId() {
        return childId;
    }

    /**
     * The node ID of the child.
     *
     * @param childId the childId to set as {@link int}
     */
    public void setChildId(int childId) {
        this.childId = childId;
    }

    /**
     * The EUI64 of the child
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     *
     * @return the current childEui64 as {@link IeeeAddress}
     */
    public IeeeAddress getChildEui64() {
        return childEui64;
    }

    /**
     * The EUI64 of the child
     *
     * @param childEui64 the childEui64 to set as {@link IeeeAddress}
     */
    public void setChildEui64(IeeeAddress childEui64) {
        this.childEui64 = childEui64;
    }

    /**
     * The EmberNodeType value for the child.
     * <p>
     * EZSP type is <i>EmberNodeType</i> - Java type is {@link EmberNodeType}
     *
     * @return the current childType as {@link EmberNodeType}
     */
    public EmberNodeType getChildType() {
        return childType;
    }

    /**
     * The EmberNodeType value for the child.
     *
     * @param childType the childType to set as {@link EmberNodeType}
     */
    public void setChildType(EmberNodeType childType) {
        this.childType = childType;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EzspGetChildDataResponse [status=");
        builder.append(status);
        builder.append(", childId=");
        builder.append(childId);
        builder.append(", childEui64=");
        builder.append(childEui64);
        builder.append(", childType=");
        builder.append(childType);
        builder.append("]");
        return builder.toString();
    }
}
