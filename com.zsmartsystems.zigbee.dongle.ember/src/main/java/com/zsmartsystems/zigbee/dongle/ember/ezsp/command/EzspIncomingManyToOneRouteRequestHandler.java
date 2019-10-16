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
 * Class to implement the Ember EZSP command <b>incomingManyToOneRouteRequestHandler</b>.
 * <p>
 * A callback indicating that a many-to-one route to the concentrator with the given short and
 * long id is available for use.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspIncomingManyToOneRouteRequestHandler extends EzspFrameResponse {
    public static final int FRAME_ID = 0x7D;

    /**
     * The short id of the concentrator.
     * <p>
     * EZSP type is <i>EmberNodeId</i> - Java type is {@link int}
     */
    private int source;

    /**
     * The EUI64 of the concentrator.
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     */
    private IeeeAddress longId;

    /**
     * The path cost to the concentrator. The cost may decrease as additional route request packets
     * for this discovery arrive, but the callback is made only once.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int cost;

    /**
     * Response and Handler constructor
     */
    public EzspIncomingManyToOneRouteRequestHandler(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        source = deserializer.deserializeUInt16();
        longId = deserializer.deserializeEmberEui64();
        cost = deserializer.deserializeUInt8();
    }

    /**
     * The short id of the concentrator.
     * <p>
     * EZSP type is <i>EmberNodeId</i> - Java type is {@link int}
     *
     * @return the current source as {@link int}
     */
    public int getSource() {
        return source;
    }

    /**
     * The short id of the concentrator.
     *
     * @param source the source to set as {@link int}
     */
    public void setSource(int source) {
        this.source = source;
    }

    /**
     * The EUI64 of the concentrator.
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     *
     * @return the current longId as {@link IeeeAddress}
     */
    public IeeeAddress getLongId() {
        return longId;
    }

    /**
     * The EUI64 of the concentrator.
     *
     * @param longId the longId to set as {@link IeeeAddress}
     */
    public void setLongId(IeeeAddress longId) {
        this.longId = longId;
    }

    /**
     * The path cost to the concentrator. The cost may decrease as additional route request packets
     * for this discovery arrive, but the callback is made only once.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current cost as {@link int}
     */
    public int getCost() {
        return cost;
    }

    /**
     * The path cost to the concentrator. The cost may decrease as additional route request packets
     * for this discovery arrive, but the callback is made only once.
     *
     * @param cost the cost to set as {@link int}
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(118);
        builder.append("EzspIncomingManyToOneRouteRequestHandler [source=");
        builder.append(String.format("%04X", source));
        builder.append(", longId=");
        builder.append(longId);
        builder.append(", cost=");
        builder.append(cost);
        builder.append(']');
        return builder.toString();
    }
}
