/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;

/**
 * Class to implement the Ember EZSP command <b>changeSourceRouteHandler</b>.
 * <p>
 * Change the source route entry in a source route table.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspChangeSourceRouteHandler extends EzspFrameResponse {
    public static final int FRAME_ID = 0xC4;

    /**
     * The source/neighbor for which the source route entry is changed.
     * <p>
     * EZSP type is <i>EmberNodeId</i> - Java type is {@link int}
     */
    private int newChildId;

    /**
     * The parent of the source/neighbor for which the source route entry is changed.
     * <p>
     * EZSP type is <i>EmberNodeId</i> - Java type is {@link int}
     */
    private int newParentId;

    /**
     * Is the source our child?
     * <p>
     * EZSP type is <i>bool</i> - Java type is {@link boolean}
     */
    private boolean ourChild;

    /**
     * Response and Handler constructor
     */
    public EzspChangeSourceRouteHandler(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        newChildId = deserializer.deserializeUInt16();
        newParentId = deserializer.deserializeUInt16();
        ourChild = deserializer.deserializeBool();
    }

    /**
     * The source/neighbor for which the source route entry is changed.
     * <p>
     * EZSP type is <i>EmberNodeId</i> - Java type is {@link int}
     *
     * @return the current newChildId as {@link int}
     */
    public int getNewChildId() {
        return newChildId;
    }

    /**
     * The source/neighbor for which the source route entry is changed.
     *
     * @param newChildId the newChildId to set as {@link int}
     */
    public void setNewChildId(int newChildId) {
        this.newChildId = newChildId;
    }

    /**
     * The parent of the source/neighbor for which the source route entry is changed.
     * <p>
     * EZSP type is <i>EmberNodeId</i> - Java type is {@link int}
     *
     * @return the current newParentId as {@link int}
     */
    public int getNewParentId() {
        return newParentId;
    }

    /**
     * The parent of the source/neighbor for which the source route entry is changed.
     *
     * @param newParentId the newParentId to set as {@link int}
     */
    public void setNewParentId(int newParentId) {
        this.newParentId = newParentId;
    }

    /**
     * Is the source our child?
     * <p>
     * EZSP type is <i>bool</i> - Java type is {@link boolean}
     *
     * @return the current ourChild as {@link boolean}
     */
    public boolean getOurChild() {
        return ourChild;
    }

    /**
     * Is the source our child?
     *
     * @param ourChild the ourChild to set as {@link boolean}
     */
    public void setOurChild(boolean ourChild) {
        this.ourChild = ourChild;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(131);
        builder.append("EzspChangeSourceRouteHandler [networkId=");
        builder.append(networkId);
        builder.append(", newChildId=");
        builder.append(String.format("%04X", newChildId));
        builder.append(", newParentId=");
        builder.append(String.format("%04X", newParentId));
        builder.append(", ourChild=");
        builder.append(ourChild);
        builder.append(']');
        return builder.toString();
    }
}
