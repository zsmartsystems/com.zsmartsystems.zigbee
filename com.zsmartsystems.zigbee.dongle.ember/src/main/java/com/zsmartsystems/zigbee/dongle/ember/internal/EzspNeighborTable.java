/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.ember.internal.ash.AshFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetConfigurationValueRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetConfigurationValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetNeighborRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetNeighborResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetRouteTableEntryRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetRouteTableEntryResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspNeighborCountRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspNeighborCountResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EzspConfigId;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EzspStatus;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.transaction.EzspSingleResponseTransaction;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.transaction.EzspTransaction;

/**
 * Class uses the native Ember commands to retrieve the neighbor table
 *
 * @author Chris Jackson
 *
 */
public class EzspNeighborTable {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(EmberNetworkInitialisation.class);

    /**
     * Scheduler to run the service
     */
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private AshFrameHandler ashHandler;

    private int routingEntries;

    /**
     * @param ashHandler the {@link AshFrameHandler} used to communicate with the NCP
     */
    public EzspNeighborTable(AshFrameHandler ashHandler, int updatePeriod) {
        this.ashHandler = ashHandler;

        Runnable neighborUpdateThread = new Runnable() {
            @Override
            public void run() {
                updateNeighbors();
            }
        };

        routingEntries = getConfiguration(EzspConfigId.EZSP_CONFIG_SOURCE_ROUTE_TABLE_SIZE);

        scheduler.scheduleAtFixedRate(neighborUpdateThread, updatePeriod, updatePeriod, TimeUnit.SECONDS);
    }

    private void updateNeighbors() {
        for (int neighbor = 0; neighbor < getNeighborCount(); neighbor++) {
            getNeighbor(neighbor);
        }

        for (int route = 0; route < routingEntries; route++) {
            getRoute(route);
        }
    }

    private int getNeighborCount() {
        // Check if the network is initialised
        EzspNeighborCountRequest neighborCountRequest = new EzspNeighborCountRequest();
        EzspTransaction neighborCountTransaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(neighborCountRequest, EzspNeighborCountResponse.class));
        EzspNeighborCountResponse neighborCountResponse = (EzspNeighborCountResponse) neighborCountTransaction
                .getResponse();
        logger.debug(neighborCountResponse.toString());
        logger.debug("EZSP neighborCountResponse {}", neighborCountResponse);

        return neighborCountResponse.getValue();
    }

    private void getNeighbor(int neighbor) {
        EzspGetNeighborRequest neighborRequest = new EzspGetNeighborRequest();
        neighborRequest.setIndex(neighbor);
        EzspTransaction neighborTransaction = ashHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(neighborRequest, EzspGetNeighborResponse.class));
        EzspGetNeighborResponse neighborResponse = (EzspGetNeighborResponse) neighborTransaction.getResponse();
        logger.debug(neighborResponse.toString());
        logger.debug("EZSP getNetworkResponse {}", neighborResponse);
    }

    private void getRoute(int route) {
        EzspGetRouteTableEntryRequest routeRequest = new EzspGetRouteTableEntryRequest();
        routeRequest.setIndex(route);
        EzspTransaction neighborTransaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(routeRequest, EzspGetRouteTableEntryResponse.class));
        EzspGetRouteTableEntryResponse routeResponse = (EzspGetRouteTableEntryResponse) neighborTransaction
                .getResponse();
        logger.debug(routeResponse.toString());
        logger.debug("EZSP getRouteTableEntry {}", routeResponse);
    }

    /**
     * Get a configuration value
     *
     * @param configId the {@link EzspConfigId} to set
     * @return the configuration value as {@link Integer} or null on error
     */
    private Integer getConfiguration(EzspConfigId configId) {
        EzspGetConfigurationValueRequest configValue = new EzspGetConfigurationValueRequest();
        configValue.setConfigId(configId);

        EzspTransaction configTransaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(configValue, EzspGetConfigurationValueResponse.class));
        EzspGetConfigurationValueResponse configResponse = (EzspGetConfigurationValueResponse) configTransaction
                .getResponse();
        logger.debug(configResponse.toString());

        if (configResponse.getStatus() != EzspStatus.EZSP_SUCCESS) {
            return null;
        }

        return configResponse.getValue();
    }
}
