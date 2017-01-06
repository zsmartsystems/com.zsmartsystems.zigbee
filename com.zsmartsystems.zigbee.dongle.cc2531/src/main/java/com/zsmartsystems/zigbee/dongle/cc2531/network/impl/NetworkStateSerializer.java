/**
 * Copyright 2013 Tommi S.E. Laukkanen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zsmartsystems.zigbee.dongle.cc2531.network.impl;

import org.codehaus.jackson.map.ObjectMapper;

import com.zsmartsystems.zigbee.dongle.cc2531.network.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ZigBeeNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * NetworkStateSerializer serializes and deserializes the ZigBeeNetworkState.
 * @author <a href="mailto:tommi.s.e.laukkanen@gmail.com">Tommi S.E. Laukkanen</a>
 */
public class NetworkStateSerializer {

    /**
     * Serializes the network state.
     * <p>
     * Produces a json {@link String} containing the current state of the network.
     * 
     * @param zigBeeNetwork the {@link ZigBeeNetwork}
     * @return the serialized network state as json {@link String}.
     */
    public String serialize(final ZigBeeNetwork zigBeeNetwork) {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping();
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        try {
            final HashMap<ZigBeeNode, HashMap<Integer, ZigBeeEndpoint>> devices = new HashMap<ZigBeeNode, HashMap<Integer, ZigBeeEndpoint>>(zigBeeNetwork.getDevices());

            final List<ZigBeeEndpoint> endpoints = new ArrayList<ZigBeeEndpoint>();
            for (final ZigBeeNode node : devices.keySet()) {
                for (final ZigBeeEndpoint endpoint : devices.get(node).values()) {
                    endpoints.add(endpoint);
                }
            }

            return objectMapper.writeValueAsString(endpoints);
        } catch (final IOException e) {
            throw new RuntimeException("Error serializing network state.", e);
        }
    }

    /**
     * Deserializes the network state.
     * 
     * @param zigBeeNetworkManager the {@link ZigBeeNetworkManager ZigBee network manager}
     * @param zigBeeNetwork the {@link ZigBeeNetwork ZigBee network}
     * @param networkStateString the network state as {@link String}
     */
    @SuppressWarnings("unchecked")
    public void deserialize(final ZigBeeNetworkManager zigBeeNetworkManager, final ZigBeeNetwork zigBeeNetwork, final String networkStateString) {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping();
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        final List<ZigBeeEndpoint> endpoints;
        try {
            endpoints = objectMapper.readValue(networkStateString, ArrayList.class);
        } catch (final IOException e) {
            throw new RuntimeException("Error serializing network state.", e);
        }
        for (final ZigBeeEndpoint endpoint : endpoints) {
            ZigBeeNodeImpl existingNode = zigBeeNetwork.getNode(endpoint.getNode().getIeeeAddress());
            if (existingNode == null) {
                zigBeeNetwork.addNode((ZigBeeNodeImpl) endpoint.getNode());
            }
            else
            {
                ((ZigBeeEndpointImpl) endpoint).setNode(existingNode);
            }
            ((ZigBeeEndpointImpl) endpoint).setNetworkManager(zigBeeNetworkManager);
            zigBeeNetwork.addEndpoint(endpoint);
        }
    }

}
