/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorResponse;

/**
 * Class to respond to the {@link MatchDescriptorRequest}
 *
 * @author Chris Jackson
 *
 */
public class ClusterMatcher implements ZigBeeCommandListener {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeNode.class);

    /**
     * The network manager
     */
    private ZigBeeNetworkManager networkManager;

    /**
     * List of clusters supported by the manager. This is used to respond to the {@link MatchDescriptorRequest}
     */
    private List<Integer> clusters = new CopyOnWriteArrayList<Integer>();

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
        logger.debug("ClusterMatcher adding cluster {}", ZclClusterType.getValueById(cluster));
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
            matchResponse.setNwkAddrOfInterest(matchRequest.getNwkAddrOfInterest());
            logger.debug("{}: ClusterMatcher sending match {}", networkManager.getZigBeeExtendedPanId(), matchResponse);
            networkManager.sendCommand(matchResponse);
        }
    }
}
