/*
   Copyright 2008-2013 CNR-ISTI, http://isti.cnr.it
   Institute of Information Science and Technologies
   of the Italian National Research Council


   See the NOTICE file distributed with this work for additional
   information regarding copyright ownership

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.zsmartsystems.zigbee.dongle.cc2531.network.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.cc2531.network.EndpointListener;
import com.zsmartsystems.zigbee.dongle.cc2531.network.NodeListener;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ZigBeeDiscoveryMonitor;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ZigBeeNode;

/**
 * This class represents the ZigBee network. It tracks the {@line ZigBeeNode nodes} and
 * {@link ZigBeeEndpoint endpoints} and provides notification to the upper layers when
 * something on the network changes.
 *
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:francesco.furfari@isti.cnr.it">Francesco Furfari</a>
 * @author <a href="mailto:chris@cd-jackson.com">Chris Jackson</a>
 */
public class ZigBeeNetwork {

    private static final Logger logger = LoggerFactory.getLogger(ZigBeeNetwork.class);

    private final Hashtable<String, ZigBeeNodeImpl> nodes = new Hashtable<String, ZigBeeNodeImpl>();
    private final Hashtable<ZigBeeNode, HashMap<Integer, ZigBeeEndpoint>> devices = new Hashtable<ZigBeeNode, HashMap<Integer, ZigBeeEndpoint>>();

    private final HashMap<Integer, ArrayList<ZigBeeEndpoint>> profiles = new HashMap<Integer, ArrayList<ZigBeeEndpoint>>();

    private final List<ZigBeeDiscoveryMonitor> discoveryMonitors = new ArrayList<ZigBeeDiscoveryMonitor>();

    private final List<NodeListener> nodeListeners = new ArrayList<NodeListener>();
    private final List<EndpointListener> endpointListeners = new ArrayList<EndpointListener>();

    /**
     * Gets Node, EndPoint map.
     *
     * @return {@link HashTable} of devices and their endpoints
     */
    public Map<ZigBeeNode, HashMap<Integer, ZigBeeEndpoint>> getDevices() {
        return devices;
    }

    /**
     * Gets the list of nodes
     * 
     * @return {@link HashTable} of nodes on the network
     */
    public Map<String, ZigBeeNodeImpl> getNodes() {
        return nodes;
    }

    /**
     * <b>NOT IN USE, the Driver does not define a logic for unregister devices</b>
     * <p>
     * This method is required for implementing issues:
     * <ul>
     * <li><a href="http://zb4osgi.aaloa.org/redmine/issues/35">Blacklisting of device (#35)</a></li>
     * <li><a href="http://zb4osgi.aaloa.org/redmine/issues/64">Base Driver should monitor the health status of device
     * (#64)</a></li>
     * </ul>
     *
     * @param node {@link ZigBeeNode}
     * @return true if the node was removed
     */
    public synchronized boolean removeNode(ZigBeeNode node) {
        final String ieee = node.getIeeeAddress();

        // Check that we have a node with the specified address
        if (!nodes.containsKey(ieee)) {
            return false;
        }

        // Remove the endpoints for this node
        HashMap<Integer, ZigBeeEndpoint> toRemove = devices.get(node);
        if (toRemove != null) {
            Iterator<ZigBeeEndpoint> it = toRemove.values().iterator();
            while (it.hasNext()) {
                final ZigBeeEndpoint device = it.next();
                if (device != null) {
                    notifyEndpointRemoved(device);
                    it.remove();
                    removeEndpointFromProfiles(device);
                }
            }
        }

        // Notify the listeners
        notifyNodeRemoved(node);

        // Remove the node
        nodes.remove(ieee);
        return true;
    }

    public synchronized boolean addNode(ZigBeeNodeImpl node) {
        final String ieee = node.getIeeeAddress();

        if (nodes.containsKey(ieee)) {
            logger.debug("Node {} already present on the network", node);
            return false;
        }

        logger.debug("Adding node {} to the network", node);
        nodes.put(ieee, node);
        devices.put(node, new HashMap<Integer, ZigBeeEndpoint>());
        notifyNodeAdded(node);
        return true;
    }

    public synchronized boolean removeEndpoint(ZigBeeEndpoint endpoint) {
        notifyEndpointRemoved(endpoint);

        final String ieee = endpoint.getNode().getIeeeAddress();

        ZigBeeNode node;
        node = nodes.get(ieee);
        if (node == null) {
            logger.error("Trying to remove a device but containing node {} does not exists", node);
            return false;
        }

        final HashMap<Integer, ZigBeeEndpoint> endPoints = devices.get(node);
        endPoints.remove(endpoint.getEndPointAddress());
        removeEndpointFromProfiles(endpoint);

        return true;
    }

    public synchronized boolean addEndpoint(ZigBeeEndpoint endpoint) {
        final ZigBeeNode deviceNode = endpoint.getNode();
        final String ieee = deviceNode.getIeeeAddress();
        final short endPoint = endpoint.getEndPointAddress();
        logger.trace("Adding device {} on node {} / end point {}.", endpoint.getDeviceTypeId(), endpoint.getNode(),
                endPoint);
        final ZigBeeNode node = nodes.get(ieee);
        if (node == null) {
            logger.debug("No node {} found");
            return false;
        } else if (node.getNetworkAddress() != deviceNode.getNetworkAddress()) {
            logger.debug("Node ieee collision, stored is {} and new one is {}", node, deviceNode);
            return false;
        }

        HashMap<Integer, ZigBeeEndpoint> endPoints = devices.get(node);
        if (endPoints.containsKey(endPoint)) {
            logger.debug("Endpoint {} on node {} already registered", endPoint, node);
            return false;
        }
        endPoints.put((int) endPoint, endpoint);

        final int profileId = endpoint.getProfileId();
        ArrayList<ZigBeeEndpoint> list;
        list = profiles.get(profileId);
        if (list == null) {
            list = new ArrayList<ZigBeeEndpoint>();
            profiles.put(profileId, list);
        }
        list.add(endpoint);

        notifyEndpointAdded(endpoint);
        return true;
    }

    private synchronized boolean removeEndpointFromProfiles(final ZigBeeEndpoint device) {
        final int profileId = device.getProfileId();
        ArrayList<ZigBeeEndpoint> list = profiles.get(profileId);
        if (list == null) {
            logger.error("Trying to remove a device from a given profile but the profile doesn't exist");
            // XXX It should never happen, we should throw an IllegalStateException
            return true;
        }
        // XXX It following method must always return true, otherwise we should throw an IllegalStateException
        if (!list.remove(device)) {
            logger.error("Device to remove not found in the given profile");
        }

        return true;
    }

    public synchronized Collection<ZigBeeEndpoint> getEndpoints(int profileId) {
        final ArrayList<ZigBeeEndpoint> result = new ArrayList<ZigBeeEndpoint>();
        final ArrayList<ZigBeeEndpoint> values = profiles.get(profileId);
        if (values == null) {
            logger.warn("No endpoints found implementing the profile={}", profileId);
        } else {
            logger.trace("We found {} implementing the profile={}", values.size(), profileId);
            result.addAll(values);
        }
        return result;
    }

    /**
     * Checks a node to see if it contains the specified endpoint
     * 
     * @param ieee the IEEE address of the node
     * @param endPoint the endpoint number
     * @return true if the endpoint exists within the node
     */
    public boolean containsEndpoint(String ieee, short endPoint) {
        final ZigBeeNode node = nodes.get(ieee);
        if (node == null) {
            return false;
        }
        /*
         * If node is not null it means that we found the same network node because
         * IEEE address must be unique for each network node
         */
        final HashMap<Integer, ZigBeeEndpoint> endPoints = devices.get(node);
        if (endPoints == null) {
            return false;
        }
        return endPoints.containsKey(endPoint);
    }

    /**
     * Gets the {@link ZigBeeNode} for the specified address
     * 
     * @param ieeeAddress
     * @return the {@link ZigBeeNode} or null if the node was not found
     */
    public ZigBeeNodeImpl getNode(String ieeeAddress) {
        return nodes.get(ieeeAddress);
    }

    /**
     * Returns a list of all the endpoints within the specified {@link ZigBeeNode}
     * 
     * @param node the {@link ZigBeeNode}
     * @return a {@link List} of {@link ZigBeeEndpoint endpoints}
     */
    public List<ZigBeeEndpoint> getEndpoints(final ZigBeeNode node) {
        return new ArrayList(devices.get(node).values());
    }

    /**
     * Adds discovery monitor.
     *
     * @param discoveryMonitor the discovery monitor
     */
    public void addDiscoveryMonitor(final ZigBeeDiscoveryMonitor discoveryMonitor) {
        synchronized (discoveryMonitors) {
            this.discoveryMonitors.add(discoveryMonitor);
        }
    }

    /**
     * Removes discovery monitor.
     *
     * @param discoveryMonitor the discovery monitor
     */
    public void removeDiscoveryMonitor(final ZigBeeDiscoveryMonitor discoveryMonitor) {
        synchronized (discoveryMonitors) {
            this.discoveryMonitors.remove(discoveryMonitor);
        }
    }

    /**
     * Notifies discovery listeners that node has been browsed.
     *
     * @param node the node
     */
    public void notifyDiscoveryBrowsed(ZigBeeNode node) {
        synchronized (discoveryMonitors) {
            for (final ZigBeeDiscoveryMonitor discoveryMonitor : discoveryMonitors) {
                discoveryMonitor.browsedNode(node);
            }
        }
    }

    /**
     * Notifies discovery listeners that node announcement has been received.
     *
     * @param node the node
     */
    public void notifyDiscoveryAnnounced(ZigBeeNode node) {
        synchronized (discoveryMonitors) {
            for (final ZigBeeDiscoveryMonitor discoveryMonitor : discoveryMonitors) {
                discoveryMonitor.announcedNode(node);
            }
        }
    }

    /**
     * Add an endpoint listener. The listener will be called when endpoints are added
     * removed or updated.
     * 
     * @param endpointListener
     */
    public void addEndpointListener(final EndpointListener endpointListener) {
        synchronized (endpointListeners) {
            endpointListeners.add(endpointListener);
        }
    }

    /**
     * Remove a previously registered endpoint listener.
     * 
     * @param endpointListener
     */
    public void removeEndpointListener(final EndpointListener endpointListener) {
        synchronized (endpointListeners) {
            endpointListeners.remove(endpointListener);
        }
    }

    public void notifyEndpointAdded(final ZigBeeEndpoint endpoint) {
        synchronized (endpointListeners) {
            for (final EndpointListener endpointListener : endpointListeners) {
                endpointListener.endpointAdded(endpoint);
            }
        }
    }

    public void notifyEndpointUpdated(final ZigBeeEndpoint endpoint) {
        synchronized (endpointListeners) {
            for (final EndpointListener endpointListener : endpointListeners) {
                endpointListener.endpointUpdated(endpoint);
            }
        }
    }

    public void notifyEndpointRemoved(final ZigBeeEndpoint endpoint) {
        synchronized (endpointListeners) {
            for (final EndpointListener endpointListener : endpointListeners) {
                endpointListener.endpointRemoved(endpoint);
            }
        }
    }

    /**
     * Add an node listener. The listener will be called when a node is added
     * removed or updated.
     * 
     * @param nodeListener {@link NodeListener}
     */
    public void addNodeListener(final NodeListener nodeListener) {
        synchronized (nodeListeners) {
            nodeListeners.add(nodeListener);
        }
    }

    /**
     * Remove a previously registered an node listener.
     * 
     * @param nodeListener {@link NodeListener}
     */
    public void removeNodeListener(final NodeListener nodeListener) {
        synchronized (nodeListeners) {
            nodeListeners.remove(nodeListener);
        }
    }

    /**
     * Notifies node listeners that a node has been added to the list of nodes.
     * This is called when the node is first found, and before endpoints have been added
     * or descriptors scanned.
     *
     * @param node the {@link ZigBeeNode}
     */
    public void notifyNodeAdded(final ZigBeeNode node) {
        synchronized (discoveryMonitors) {
            for (final NodeListener nodeListener : nodeListeners) {
                nodeListener.nodeAdded(node);
            }
        }
    }

    /**
     * Notifies node listeners that node discovery has been completed.
     * This is called after all endpoints have been added.
     *
     * @param node the {@link ZigBeeNode}
     */
    public void notifyNodeDiscovered(final ZigBeeNode node) {
        synchronized (nodeListeners) {
            for (final NodeListener nodeListener : nodeListeners) {
                nodeListener.nodeDiscovered(node);
            }
        }
    }

    /**
     * Notifies node listeners that a node has been updated within the list of nodes.
     *
     * @param node the {@link ZigBeeNode}
     */
    public void notifyNodeUpdated(ZigBeeNodeImpl node) {
        synchronized (nodeListeners) {
            for (final NodeListener nodeListener : nodeListeners) {
                nodeListener.nodeUpdated(node);
            }
        }
    }

    /**
     * Notifies node listeners that a node has been removed from the list of nodes.
     *
     * @param node the {@link ZigBeeNode}
     */
    public void notifyNodeRemoved(final ZigBeeNode node) {
        synchronized (nodeListeners) {
            for (final NodeListener nodeListener : nodeListeners) {
                nodeListener.nodeRemoved(node);
            }
        }
    }
}
