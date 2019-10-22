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
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorResponse;

/**
 * Class to respond to the {@link MatchDescriptorRequest}.
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
    private ZigBeeNetworkManager networkManager;

    /**
     * List of clusters supported by the manager. This is used to respond to the {@link MatchDescriptorRequest}
     */
    private Set<Integer> clusters = new CopyOnWriteArraySet<>();

    /**
     * Constructor
     *
     * @param networkManager the {@link ZigBeeNetworkManager} to which this matcher is linked
     */
    public ClusterMatcher(ZigBeeNetworkManager networkManager) {
        logger.debug("ClusterMatcher starting");
        this.networkManager = networkManager;

        networkManager.addCommandListener(this);
    }

    /**
     * Adds a cluster to the list of clusters we will match
     *
     * @param cluster the cluster to match
     */
    public void addCluster(int cluster) {
        logger.debug("ClusterMatcher adding cluster {}", cluster);
        clusters.add(cluster);
    }

    @Override
    public void commandReceived(ZigBeeCommand command) {
        // If we have local servers matching the request, then we need to respond
        if (command instanceof MatchDescriptorRequest) {
            MatchDescriptorRequest matchRequest = (MatchDescriptorRequest) command;
            logger.debug("{}: ClusterMatcher received request {}", networkManager.getZigBeeExtendedPanId(),
                    matchRequest);
            if (matchRequest.getProfileId() != 0x104) {
                // TODO: Do we need to restrict the profile? Remove this check?
                return;
            }
            if (matchRequest.getNwkAddrOfInterest() != networkManager.getLocalNwkAddress()
                    && !ZigBeeBroadcastDestination.isBroadcast(matchRequest.getNwkAddrOfInterest())) {
                return;
            }

            // We want to match any of our local servers (ie our input clusters) with any
            // requested clusters in the requests cluster list
            if (Collections.disjoint(matchRequest.getInClusterList(), clusters)
                    && Collections.disjoint(matchRequest.getOutClusterList(), clusters)) {
                logger.debug("{}: ClusterMatcher no match", networkManager.getZigBeeExtendedPanId());
                return;
            }

            MatchDescriptorResponse matchResponse = new MatchDescriptorResponse();
            matchResponse.setStatus(ZdoStatus.SUCCESS);
            List<Integer> matchList = new ArrayList<Integer>();
            matchList.add(1);
            matchResponse.setMatchList(matchList);

            matchResponse.setDestinationAddress(command.getSourceAddress());
            matchResponse.setNwkAddrOfInterest(networkManager.getLocalNwkAddress());
            logger.debug("{}: ClusterMatcher sending match {}", networkManager.getZigBeeExtendedPanId(), matchResponse);
            networkManager.sendTransaction(matchResponse);
        }
    }
}
