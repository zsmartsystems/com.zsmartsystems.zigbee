/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 * Class to implement the Ember EZSP command <b>sendManyToOneRouteRequest</b>.
 * <p>
 * Sends a route request packet that creates routes from every node in the network back to this
 * node. This function should be called by an application that wishes to communicate with many
 * nodes, for example, a gateway, central monitor, or controller. A device using this function
 * was referred to as an 'aggregator' in EmberZNet 2.x and earlier, and is referred to as a
 * 'concentrator' in the ZigBee specification and EmberZNet 3. This function enables large
 * scale networks, because the other devices do not have to individually perform
 * bandwidth-intensive route discoveries. Instead, when a remote node sends an APS unicast to
 * a concentrator, its network layer automatically delivers a special route record packet
 * first, which lists the network ids of all the intermediate relays. The concentrator can then
 * use source routing to send outbound APS unicasts. (A source routed message is one in which the
 * entire route is listed in the network layer header.) This allows the concentrator to
 * communicate with thousands of devices without requiring large route tables on neighboring
 * nodes. This function is only available in ZigBee Pro (stack profile 2), and cannot be called
 * on end devices. Any router can be a concentrator (not just the coordinator), and there can be
 * multiple concentrators on a network. Note that a concentrator does not automatically
 * obtain routes to all network nodes after calling this function. Remote applications must
 * first initiate an inbound APS unicast. Many-to-one routes are not repaired automatically.
 * Instead, the concentrator application must call this function to rediscover the routes as
 * necessary, for example, upon failure of a retried APS message. The reason for this is that
 * there is no scalable one-size-fits-all route repair strategy. A common and recommended
 * strategy is for the concentrator application to refresh the routes by calling this function
 * periodically.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspSendManyToOneRouteRequestResponse extends EzspFrameResponse {
    public static final int FRAME_ID = 0x41;

    /**
     * EMBER_SUCCESS if the route request was successfully submitted to the transmit queue, and
     * EMBER_ERR_FATAL otherwise.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     */
    private EmberStatus status;

    /**
     * Response and Handler constructor
     */
    public EzspSendManyToOneRouteRequestResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        status = deserializer.deserializeEmberStatus();
    }

    /**
     * EMBER_SUCCESS if the route request was successfully submitted to the transmit queue, and
     * EMBER_ERR_FATAL otherwise.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     *
     * @return the current status as {@link EmberStatus}
     */
    public EmberStatus getStatus() {
        return status;
    }

    /**
     * EMBER_SUCCESS if the route request was successfully submitted to the transmit queue, and
     * EMBER_ERR_FATAL otherwise.
     *
     * @param status the status to set as {@link EmberStatus}
     */
    public void setStatus(EmberStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(90);
        builder.append("EzspSendManyToOneRouteRequestResponse [networkId=");
        builder.append(networkId);
        builder.append(", status=");
        builder.append(status);
        builder.append(']');
        return builder.toString();
    }
}
