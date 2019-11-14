/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeBroadcastDestination;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeProfileType;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorResponse;

/**
 * Class to respond to the {@link MatchDescriptorRequest}. The matcher will respond to {@link MatchDescriptorRequest}
 * messages received with the appropriate ZigBee profile set in the constructor, and a match against any of the client
 * or server clusters set with the {@link #addClientCluster(int)} and {@link #addServerCluster(int)} methods.
 * <p>
 * Note that this class currently only supports clusters that are not manufacturer-specific.
 *
 * @author Chris Jackson
 */
public class ClusterMatcher implements ZigBeeCommandListener {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(ClusterMatcher.class);

    /**
     * The network manager
     */
    private final ZigBeeNetworkManager networkManager;

    /**
     * The local endpoint ID that we're matching and sending responses to
     */
    private final int localEndpointId;

    /**
     * The profile ID that we're matching
     */
    private final int profileId;

    /**
     * List of client clusters supported by the manager. This is used to respond to the {@link MatchDescriptorRequest}
     */
    private Set<Integer> clientClusters = new CopyOnWriteArraySet<>();

    /**
     * List of client clusters supported by the manager. This is used to respond to the {@link MatchDescriptorRequest}
     */
    private Set<Integer> serverClusters = new CopyOnWriteArraySet<>();

    /**
     * Constructor for the cluster matcher.
     *
     * @param networkManager the {@link ZigBeeNetworkManager} to which this matcher is linked
     * @param localEndpointId the local endpoint ID for this matcher
     * @param profileId the ZigBee profile ID that this matcher will use
     */
    public ClusterMatcher(ZigBeeNetworkManager networkManager, int localEndpointId, int profileId) {
        logger.debug("ClusterMatcher starting for endpoint {} with profile ID {} ({})", localEndpointId,
                String.format("%04X", profileId), ZigBeeProfileType.getByValue(profileId));
        this.networkManager = networkManager;
        this.localEndpointId = localEndpointId;
        this.profileId = profileId;

        networkManager.addCommandListener(this);
    }

    /**
     * Shut down the cluster matcher
     */
    public void shutdown() {
        networkManager.removeCommandListener(this);
    }

    /**
     * Adds a cluster to the list of client clusters we will match
     *
     * @param cluster the client cluster to match
     */
    public void addClientCluster(int cluster) {
        logger.debug("ClusterMatcher adding client cluster {}", String.format("%04X", cluster));
        clientClusters.add(cluster);
    }

    /**
     * Adds a cluster to the list of server clusters we will match
     *
     * @param cluster the server cluster to match
     */
    public void addServerCluster(int cluster) {
        logger.debug("ClusterMatcher adding server cluster {}", String.format("%04X", cluster));
        serverClusters.add(cluster);
    }

    /**
     * Returns true if the requested cluster is supported as a client
     *
     * @param cluster the cluster to test
     * @return true if the requested cluster is supported as a client
     */
    public boolean isClientSupported(int cluster) {
        return clientClusters.contains(cluster);
    }

    /**
     * Returns true if the requested cluster is supported as a server
     *
     * @param cluster the cluster to test
     * @return true if the requested cluster is supported as a server
     */
    public boolean isServerSupported(int cluster) {
        return serverClusters.contains(cluster);
    }

    @Override
    public void commandReceived(ZigBeeCommand command) {
        // If we have local servers matching the request, then we need to respond
        if (command instanceof MatchDescriptorRequest) {
            MatchDescriptorRequest matchRequest = (MatchDescriptorRequest) command;
            logger.debug("{}: ClusterMatcher received request {}", networkManager.getZigBeeExtendedPanId(),
                    matchRequest);
            if (matchRequest.getProfileId() != profileId) {
                logger.debug("{}: ClusterMatcher no match to profileId", networkManager.getZigBeeExtendedPanId());
                return;
            }
            if (matchRequest.getNwkAddrOfInterest() != networkManager.getLocalNwkAddress()
                    && !ZigBeeBroadcastDestination.isBroadcast(matchRequest.getNwkAddrOfInterest())) {
                logger.debug("{}: ClusterMatcher no match to local address", networkManager.getZigBeeExtendedPanId());
                return;
            }

            // We want to match any of our local servers (ie our input clusters) with any
            // requested clusters in the requests cluster list
            if (Collections.disjoint(matchRequest.getInClusterList(), serverClusters)
                    && Collections.disjoint(matchRequest.getOutClusterList(), clientClusters)) {
                logger.debug("{}: ClusterMatcher no match", networkManager.getZigBeeExtendedPanId());
                return;
            }

            MatchDescriptorResponse matchResponse = new MatchDescriptorResponse();
            matchResponse.setStatus(ZdoStatus.SUCCESS);
            List<Integer> matchList = new ArrayList<Integer>();
            matchList.add(localEndpointId);
            matchResponse.setMatchList(matchList);

            matchResponse.setDestinationAddress(command.getSourceAddress());
            matchResponse.setNwkAddrOfInterest(networkManager.getLocalNwkAddress());
            logger.debug("{}: ClusterMatcher sending match {}", networkManager.getZigBeeExtendedPanId(), matchResponse);
            networkManager.sendTransaction(matchResponse);
        }
    }
}
