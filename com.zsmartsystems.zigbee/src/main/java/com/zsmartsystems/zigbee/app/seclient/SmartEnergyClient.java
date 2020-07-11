/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.seclient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeBroadcastDestination;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkState;
import com.zsmartsystems.zigbee.ZigBeeNetworkStateListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeProfileType;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.ZigBeeNetworkExtension;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeProvider;
import com.zsmartsystems.zigbee.security.ZigBeeCryptoSuites;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclKeyEstablishmentCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclMeteringCluster;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressRequest;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressResponse;
import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.command.NodeDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.NodeDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.command.SimpleDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.SimpleDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.LogicalType;
import com.zsmartsystems.zigbee.zdo.field.SimpleDescriptor;

/**
 * Implements the smart energy extension. A {@link ZclKeyEstablishmentClient} and {@link ZclKeyEstablishmentServer} will
 * be created for each remote server and client respectively.
 * <p>
 * The client will wait for the network to be joined, then search for the key establishment cluster and perform the KE
 * protocol. It will then search for and SEP nodes and add them to the {@link ZigBeeNetworkManager}.
 * <p>
 * The client performs the following functions -:
 * <p>
 * <ul>
 * <li>Searches for the Key Establishment Server using the ZDO {@link MatchDescriptorRequest}.
 * <li>Performs the key establishment using {@link ZclKeyEstablishmentClient}. Note that the
 * {@link ZclKeyEstablishmentClient} is instantiated at all times to handle any CBKE messages from the server even when
 * no CBKE is in progress. The {@link ZclKeyEstablishmentClient} is instantiated when the node is added and the client
 * then uses that when the CBKE is started.
 * <li>Searches for metering servers ({@link ZclMeteringCluster} using the ZDO {@link MatchDescriptorRequest} and adds
 * them to the {@link ZigBeeNetworkManager}.
 * <li>Applies the APS security requirement to all SEP clusters that require this.
 * </ul>
 * <p>
 * Once the client is established on the SE network, it will periodically poll the trust centre using an APS encrypted
 * request, and it should expect an APS encrypted response. If there are 3 consecutive requests that fail, the client
 * will perform a search for the trust centre [TBC].
 * <p>
 * SEP 1.2b uses the Keep Alive cluster [TBC]
 * <p>
 * <img src="./doc-files/SmartEnergyClient.png" width="50%">
 *
 * @author Chris Jackson
 *
 */
public class SmartEnergyClient implements ZigBeeNetworkExtension, ZigBeeCommandListener, ZigBeeNetworkNodeListener,
        ZigBeeNetworkStateListener {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(SmartEnergyClient.class);

    /**
     * The {@link ZigBeeNetworkManager} to which this network belongs
     */
    private ZigBeeNetworkManager networkManager;

    /**
     * A list of ESI servers on the local network
     */
    private Map<IeeeAddress, Long> serverList;

    /**
     * The Key Establishment Establishment Endpoint on the Trust Centre
     */
    private Integer trustCenterKeyEstablishmentEndpoint;

    /**
     * The Keep Alive Endpoint on the Trust Centre
     */
    private Integer trustCenterKeepAliveEndpoint;

    /**
     * The {@link ZigBeeCbkeProvider} provides the security algorithms required for the Certificate Based Key Exchange.
     */
    private ZigBeeCbkeProvider cbkeProvider;

    private ScheduledFuture<?> timer;

    private SmartEnergyClientState seState = SmartEnergyClientState.IDLE;

    private ZigBeeSepClientStatus currentStatus = ZigBeeSepClientStatus.DISCONNECTED;

    /**
     * The number of milliseconds to wait for an "immediate" timer to run
     */
    private static final int TIMER_IMMEDIATE = 1000;

    /**
     * The number of attempts to each stage in the process before alerting the user that we failed
     */
    private static final int SEP_RETRIES = 3;

    /**
     * Period to wait between retries of the same stage
     */
    private static final int SEP_RETRY_PERIOD = 30000;

    /**
     * If Key Establishment fails, this is the amount of time to wait before retrying
     */
    private static final int TIMER_RESTART_KEY_ESTABLISHMENT = 30000;

    private static final int KEEPALIVE_RETRIES = 3;
    private static final int KEEPALIVE_TIMER_DEFAULT = 600000;
    private static final int KEEPALIVE_TIMER_MINIMUM = 300000;
    private static final int KEEPALIVE_TIMER_MAXIMUM = 1200000;

    private int retryCounter = 0;

    private Map<ZigBeeEndpointAddress, ZclKeyEstablishmentClient> cbkeClientRegistry = new HashMap<>();
    private Map<ZigBeeEndpointAddress, ZclKeyEstablishmentServer> cbkeServerRegistry = new HashMap<>();

    private int keepaliveCounter = 0;
    private int keepaliveTimeout = KEEPALIVE_TIMER_DEFAULT;
    private Calendar lastKeepAliveTime = null;

    /**
     * Used to fix the crypto suite. May be set by the application to limit the crypto suite to a known value rather
     * than let the system choose the highest usable suite.
     */
    private ZigBeeCryptoSuites forceCryptoSuite = ZigBeeCryptoSuites.ECC_163K1;

    private boolean extensionStarted = false;

    private final static Set<ZclClusterType> secureClusters = new HashSet<>();

    static {
        secureClusters.add(ZclClusterType.TIME);
        secureClusters.add(ZclClusterType.COMMISSIONING);
        secureClusters.add(ZclClusterType.OTA_UPGRADE);
        secureClusters.add(ZclClusterType.PRICE);
        secureClusters.add(ZclClusterType.DEMAND_RESPONSE_AND_LOAD_CONTROL);
        secureClusters.add(ZclClusterType.METERING);
        secureClusters.add(ZclClusterType.MESSAGING);
        secureClusters.add(ZclClusterType.SMART_ENERGY_TUNNELING);
        secureClusters.add(ZclClusterType.PREPAYMENT);
    }

    /**
     * A list of listeners to receive status callbacks
     */
    private Collection<SmartEnergyStatusCallback> statusListeners = Collections
            .unmodifiableCollection(new ArrayList<SmartEnergyStatusCallback>());

    /**
     * Constructs the Smart Energy Client.
     *
     * @param cbkeProvider the {@link ZigBeeCbkeProvider} that provides the CBKE security algorithms
     */
    public SmartEnergyClient(ZigBeeCbkeProvider cbkeProvider) {
        this.cbkeProvider = cbkeProvider;
    }

    @Override
    public ZigBeeStatus extensionInitialize(ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public ZigBeeStatus extensionStartup() {
        if (extensionStarted) {
            logger.debug("SEP Extension: Already started");
            return ZigBeeStatus.INVALID_STATE;
        }
        if (cbkeProvider == null) {
            logger.debug("SEP Extension: Unable to start as CBKE Provider is not set");
            return ZigBeeStatus.FAILURE;
        }
        logger.debug("SEP Extension: Starting");

        networkManager.addNetworkStateListener(this);
        networkManager.addCommandListener(this);

        // Loop through existing/known nodes to set SEP security requirements
        for (ZigBeeNode node : networkManager.getNodes()) {
            setProfileSecurity(node);
        }
        networkManager.addNetworkNodeListener(this);
        extensionStarted = true;
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public void extensionShutdown() {
        timerCancel();
        networkManager.removeNetworkStateListener(this);
        networkManager.removeCommandListener(this);
        networkManager.removeNetworkNodeListener(this);

        for (ZclKeyEstablishmentClient cbkeClient : cbkeClientRegistry.values()) {
            cbkeClient.shutdown();
        }
        cbkeClientRegistry.clear();
        logger.debug("SEP Extension: Shutdown");
    }

    /**
     * Gets the current {@link ZigBeeCbkeProvider}
     *
     * @return the current {@link ZigBeeCbkeProvider}
     */
    public ZigBeeCbkeProvider getCbkeProvider() {
        return cbkeProvider;
    }

    /**
     * Set the crypto suite to a fixed value. May be set by the application to limit the crypto suite to a known value
     * rather than let the system choose the highest usable suite.
     * <p>
     * Once the crypto suite has been manually selected the system will not request the supported suites on the remote
     * device. If the remote device does not support the manually selected crypto suite, then the CBKE will fail.
     *
     * @param requestedCryptoSuite the {@link ZigBeeCryptoSuites} to use
     * @return true if the {@link ZigBeeCryptoSuites} was set
     */
    public boolean setCryptoSuite(ZigBeeCryptoSuites requestedCryptoSuite) {
        forceCryptoSuite = requestedCryptoSuite;
        return true;
    }

    /**
     * Add a listener to receive status callbacks on the SEP status.
     *
     * @param listener the {@link SmartEnergyStatusCallback} to receive the status
     */
    public void addListener(final SmartEnergyStatusCallback listener) {
        if (listener == null) {
            return;
        }
        synchronized (this) {
            final Set<SmartEnergyStatusCallback> modifiedListeners = new HashSet<>(statusListeners);
            modifiedListeners.add(listener);
            statusListeners = Collections.unmodifiableCollection(modifiedListeners);
        }
    }

    /**
     * Remove a listener from receiving status callbacks on the SEP status.
     *
     * @param listener the {@link SmartEnergyStatusCallback} to stop receiving status callbacks
     */
    public void removeListener(final SmartEnergyStatusCallback listener) {
        synchronized (this) {
            final Set<SmartEnergyStatusCallback> modifiedListeners = new HashSet<>(statusListeners);
            modifiedListeners.remove(listener);
            statusListeners = Collections.unmodifiableCollection(modifiedListeners);
        }
    }

    /**
     * Sets the current keep alive retry interval in milliseconds.
     * This will not allow the timer to be set outside of the period recommended by
     * the standard (5 to 20 minutes).
     *
     * @param timeout the keep alive retry period in milliseconds
     */
    public void setKeepAlivePeriod(int timeout) {
        if (timeout < KEEPALIVE_TIMER_MINIMUM || timeout > KEEPALIVE_TIMER_MAXIMUM) {
            return;
        }
        keepaliveTimeout = timeout;

        // Restart the keep-alive timer if we're in KEEP_ALIVE state
        if (seState == SmartEnergyClientState.KEEP_ALIVE) {
            timerStart(keepaliveTimeout);
        }
    }

    /**
     * Returns the time of the last successful keep-alive poll
     *
     * @return the time of the last successful keep-alive poll, or null if there have been no successful responses
     */
    public Calendar getLastKeepAlive() {
        return lastKeepAliveTime;
    }

    /**
     * Manually start a CBKE update with the specified endpoint.
     *
     * @param endpoint the endpoint with which to perform the CBKE.
     * @return the {@link ZigBeeStatus}
     */
    public ZigBeeStatus startKeyExchange(ZigBeeEndpointAddress endpoint) {
        ZclKeyEstablishmentClient keClient = cbkeClientRegistry.get(endpoint);
        if (keClient == null) {
            logger.debug("Unable to find CBKE Client for endpoint {}", endpoint);
            return ZigBeeStatus.FAILURE;
        }
        keClient.start();

        logger.debug("Manually starting CBKE Client for endpoint {}", endpoint);
        return ZigBeeStatus.SUCCESS;
    }

    private void updateClientState(SmartEnergyClientState newState) {
        logger.debug("SEP Extension: SE State updated from {} to {}.", seState, newState);
        if (seState != newState) {
            retryCounter = 0;
        }
        seState = newState;

        final ZigBeeSepClientStatus updatedStatus;
        switch (newState) {
            case DISCOVER_TRUST_CENTRE:
            case DISCOVER_KEY_ESTABLISHMENT_CLUSTER:
            case DISCOVER_METERING_SERVERS:
            case PERFORM_KEY_ESTABLISHMENT:
                timerStart(TIMER_IMMEDIATE);
                updatedStatus = ZigBeeSepClientStatus.INITIALIZING;
                break;
            case DISCOVER_KEEP_ALIVE:
                timerStart(TIMER_IMMEDIATE);
                updatedStatus = ZigBeeSepClientStatus.INITIALIZING;
                break;
            case DISCOVER_KEEP_ALIVE_TIMEOUT:
                updatedStatus = ZigBeeSepClientStatus.INITIALIZING;
                break;
            case KEEP_ALIVE:
                timerStart(keepaliveTimeout);
                updatedStatus = ZigBeeSepClientStatus.CONNECTED;
                break;
            case IDLE:
                updatedStatus = ZigBeeSepClientStatus.DISCONNECTED;
                break;
            case FATAL:
                updatedStatus = ZigBeeSepClientStatus.FATAL_ERROR;
                break;
            default:
                updatedStatus = null;
                break;
        }

        // Only notify of changes
        if (currentStatus == updatedStatus) {
            return;
        }
        currentStatus = updatedStatus;
        logger.debug("SEP Extension: Status updated to {}.", updatedStatus);

        // If we're not still initialising, then cancel the timer
        if (updatedStatus != ZigBeeSepClientStatus.INITIALIZING) {
            timerCancel();
        }

        synchronized (this) {
            // Notify the listeners
            for (final SmartEnergyStatusCallback statusListener : statusListeners) {
                networkManager.getNotificationService().execute(new Runnable() {
                    @Override
                    public void run() {
                        statusListener.sepStatusUpdate(updatedStatus);
                    }
                });
            }
        }
    }

    private void discoveryStart() {
        logger.debug("SEP Extension: Startup");

        // Check if we are the trust centre
        if (networkManager.getLocalNwkAddress() == 0x0000) {
            logger.debug("SEP Extension: Local node is TC - aborting discovery");
            return;
        }

        // Reset the retry counter as the user asked us to start again.
        retryCounter = 0;

        // Check if we are already authorised and don't perform key establishment again
        ZigBeeNode trustCentre = networkManager.getNode(0);
        logger.debug("SEP Extension: Trust Centre={}", trustCentre);
        if (trustCentre != null && cbkeProvider.isAuthorised(trustCentre.getIeeeAddress())) {
            logger.debug("SEP Extension: Startup TC is authorised");
            setProfileSecurity(trustCentre);

            // Check if we know the metering cluster
            boolean meteringFound = false;
            for (ZigBeeEndpoint endpoint : trustCentre.getEndpoints()) {
                meteringFound |= (endpoint.getInputCluster(ZclMeteringCluster.CLUSTER_ID) != null);
            }
            if (trustCentre.getLogicalType() == LogicalType.UNKNOWN) {
                updateClientState(SmartEnergyClientState.DISCOVER_TRUST_CENTRE);
            } else if (meteringFound) {
                updateClientState(SmartEnergyClientState.DISCOVER_KEEP_ALIVE);
            } else {
                updateClientState(SmartEnergyClientState.DISCOVER_METERING_SERVERS);
            }
        } else {
            // Start the SEP discovery
            if (trustCentre == null) {
                updateClientState(SmartEnergyClientState.DISCOVER_TRUST_CENTRE);
            } else {
                updateClientState(SmartEnergyClientState.DISCOVER_KEY_ESTABLISHMENT_CLUSTER);
            }
        }
        logger.debug("SEP Extension: Starting discovery at {}", seState);
    }

    private void discoveryComplete() {
        logger.debug("SEP Extension: Discovery complete");

        timerCancel();
        updateClientState(SmartEnergyClientState.KEEP_ALIVE);
    }

    private void discoveryStop() {
        logger.debug("SEP Extension: Discovery stopped at state {} after {} retries", seState, retryCounter);
        timerCancel();

        for (ZclKeyEstablishmentClient keClient : cbkeClientRegistry.values()) {
            keClient.stop();
        }
        updateClientState(SmartEnergyClientState.IDLE);
    }

    private void timerStart(int milliseconds) {
        logger.debug("SEP Extension: SEP discovery timer start in {}ms at state {}", milliseconds, seState);
        timerCancel();
        timer = networkManager.scheduleTask(new Runnable() {
            @Override
            public void run() {
                logger.debug("SEP Extension: SEP discovery running task {}, attempt {}", seState, retryCounter);
                if (retryCounter++ > SEP_RETRIES) {
                    logger.debug("SEP Extension: SEP discovery terminated - too many retries");
                    discoveryStop();
                    return;
                }
                logger.debug("SEP Extension: SEP discovery running task {}, attempt {}", seState, retryCounter);

                switch (seState) {
                    case DISCOVER_TRUST_CENTRE:
                        if (networkManager.getNode(0) != null
                                && networkManager.getNode(0).getLogicalType() != LogicalType.UNKNOWN) {
                            logger.debug("SEP Extension: Trust centre already known");
                            updateClientState(SmartEnergyClientState.DISCOVER_KEY_ESTABLISHMENT_CLUSTER);
                            timerStart(TIMER_IMMEDIATE);
                            break;
                        }
                        ZigBeeNode trustCentre = networkManager.getNode(0);
                        if (trustCentre == null) {
                            IeeeAddress ieeeAddress = requestIeeeAddress(0);
                            if (ieeeAddress == null) {
                                logger.debug("SEP Extension: SEP discovery did not find TC IEEE address");
                                timerStart(SEP_RETRY_PERIOD);
                                break;
                            }
                            logger.debug("SEP Extension: SEP discovery found TC IEEE address - {}", ieeeAddress);

                            trustCentre = new ZigBeeNode(networkManager, ieeeAddress);
                            trustCentre.setNetworkAddress(0);
                        }
                        try {
                            if (requestNodeDescriptor(trustCentre)) {
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            logger.debug("SEP Extension: Exception getting node descriptor for address - {}",
                                    trustCentre.getIeeeAddress());
                        }
                        networkManager.updateNode(trustCentre);

                        updateClientState(SmartEnergyClientState.DISCOVER_KEY_ESTABLISHMENT_CLUSTER);
                        timerStart(TIMER_IMMEDIATE);
                        break;
                    case DISCOVER_KEY_ESTABLISHMENT_CLUSTER:
                        discoverKeyEstablishmentServer();
                        // Timer restarted in discoverServices
                        break;
                    case PERFORM_KEY_ESTABLISHMENT:
                        performKeyEstablishment();
                        // Timer restarted in keyEstablishmentCallback
                        break;
                    case DISCOVER_METERING_SERVERS:
                        discoverMeteringServers();
                        // Timer restarted in discoverServices
                        break;
                    case DISCOVER_KEEP_ALIVE:
                        discoverKeepAlive();
                        // Timer restarted in discoverServices
                        break;
                    case DISCOVER_KEEP_ALIVE_TIMEOUT:
                        discoverKeepAliveTimeout();
                        // Timer restarted in updateClientState
                        break;
                    case KEEP_ALIVE:
                        keepalivePoll();
                        timerStart(keepaliveTimeout);
                        break;
                    default:
                        break;
                }
            }
        }, milliseconds);
    }

    private void timerCancel() {
        if (timer != null) {
            timer.cancel(true);
            timer = null;
        }
    }

    /**
     * Discovery the cluster specified in clusterId
     *
     * @param destination the destination address to search for
     * @param clusterId the cluster ID to search for
     */
    private void discoverServices(Integer destination, int clusterId) {
        List<Integer> clusterList = new ArrayList<Integer>();
        clusterList.add(clusterId);

        MatchDescriptorRequest matchRequest = new MatchDescriptorRequest(
                destination,
                ZigBeeProfileType.ZIGBEE_SMART_ENERGY.getKey(),
                clusterList,
                Collections.emptyList());
        matchRequest.setDestinationAddress(new ZigBeeEndpointAddress(destination));

        networkManager.sendTransaction(matchRequest);
        timerStart(SEP_RETRY_PERIOD);
    }

    /**
     * Searches for the key establishment server on the trust centre (in the input cluster list)
     */
    private void discoverKeyEstablishmentServer() {
        logger.debug("SEP Extension: Discovery searching for Key Establishment Server");
        discoverServices(0, ZclKeyEstablishmentCluster.CLUSTER_ID);
    }

    private void performKeyEstablishment() {
        logger.debug("SEP Extension: Discovery starting key establishment");
        ZigBeeNode trustCentre = networkManager.getNode(0);
        if (trustCentre == null) {
            logger.error("SEP Extension: SEP key establishment Trust Centre not found in network nodes list");
            updateClientState(SmartEnergyClientState.DISCOVER_TRUST_CENTRE);
            return;
        }

        if (trustCenterKeyEstablishmentEndpoint == null) {
            logger.debug("SEP Extension: SEP key establishment endpoint not known");
            updateClientState(SmartEnergyClientState.DISCOVER_TRUST_CENTRE);
            return;
        }

        ZigBeeEndpoint endpoint = trustCentre.getEndpoint(trustCenterKeyEstablishmentEndpoint);
        if (endpoint == null) {
            logger.error("SEP Extension: KeyEstablishment endpoint not found in trust centre");
            updateClientState(SmartEnergyClientState.DISCOVER_TRUST_CENTRE);
            return;
        }

        ZclKeyEstablishmentCluster keCluster = (ZclKeyEstablishmentCluster) endpoint
                .getInputCluster(ZclKeyEstablishmentCluster.CLUSTER_ID);
        if (keCluster == null) {
            logger.error("SEP Extension: KeyEstablishment cluster not found in endpoint");
            updateClientState(SmartEnergyClientState.DISCOVER_KEY_ESTABLISHMENT_CLUSTER);
            return;
        }

        // Don't perform CBKE if we're already authorised
        if (cbkeProvider.isAuthorised(trustCentre.getIeeeAddress())) {
            logger.error("SEP Extension: Already authorised with {}", trustCentre.getIeeeAddress());
            updateClientState(SmartEnergyClientState.DISCOVER_METERING_SERVERS);
            return;
        }

        ZclKeyEstablishmentClient keClient = cbkeClientRegistry.get(endpoint.getEndpointAddress());
        if (keClient == null) {
            logger.error("SEP Extension: KeyEstablishment client not found in registry");
            updateClientState(SmartEnergyClientState.DISCOVER_KEY_ESTABLISHMENT_CLUSTER);
            return;
        }
        keClient.setCbkeProvider(cbkeProvider);
        if (forceCryptoSuite != null) {
            keClient.setCryptoSuite(forceCryptoSuite);
        }

        // Note. No timer has been started. This will be restarted when the CBKE terminates.
        keClient.start();
    }

    /**
     * Get node descriptor
     *
     * @return true if the message was processed ok
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private boolean requestNodeDescriptor(ZigBeeNode node) throws InterruptedException, ExecutionException {
        final NodeDescriptorRequest nodeDescriptorRequest = new NodeDescriptorRequest(node.getNetworkAddress());
        nodeDescriptorRequest.setDestinationAddress(new ZigBeeEndpointAddress(node.getNetworkAddress()));

        CommandResult response = networkManager.sendTransaction(nodeDescriptorRequest, nodeDescriptorRequest).get();
        final NodeDescriptorResponse nodeDescriptorResponse = (NodeDescriptorResponse) response.getResponse();
        logger.debug("{}: SEP Extension: NodeDescriptorResponse returned {}", node.getIeeeAddress(),
                nodeDescriptorResponse);
        if (nodeDescriptorResponse == null) {
            return false;
        }

        if (nodeDescriptorResponse.getStatus() == ZdoStatus.SUCCESS) {
            node.setNodeDescriptor(nodeDescriptorResponse.getNodeDescriptor());

            return true;
        }

        return false;
    }

    /**
     * Get the simple descriptor for an endpoint and create the {@link ZigBeeEndpoint}
     *
     * @param endpoint the {@link ZigBeeEndpoint} to request the descriptor
     * @return the {@link ZigBeeStatus}
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private ZigBeeStatus requestSimpleDescriptor(final ZigBeeEndpoint endpoint)
            throws InterruptedException, ExecutionException {
        final SimpleDescriptorRequest simpleDescriptorRequest = new SimpleDescriptorRequest(
                endpoint.getParentNode().getNetworkAddress(),
                endpoint.getEndpointId());
        simpleDescriptorRequest
                .setDestinationAddress(new ZigBeeEndpointAddress(endpoint.getParentNode().getNetworkAddress()));

        CommandResult response = networkManager.sendTransaction(simpleDescriptorRequest, simpleDescriptorRequest).get();

        final SimpleDescriptorResponse simpleDescriptorResponse = (SimpleDescriptorResponse) response.getResponse();
        logger.debug("{}: SEP Extension: Endpoint {} SimpleDescriptorResponse returned {}", endpoint.getIeeeAddress(),
                endpoint.getEndpointId(), simpleDescriptorResponse);
        if (simpleDescriptorResponse == null) {
            return ZigBeeStatus.FAILURE;
        }

        if (simpleDescriptorResponse.getStatus() == ZdoStatus.SUCCESS) {
            SimpleDescriptor simpleDescriptor = simpleDescriptorResponse.getSimpleDescriptor();
            endpoint.setProfileId(simpleDescriptor.getProfileId());
            endpoint.setDeviceId(simpleDescriptor.getDeviceId());
            endpoint.setDeviceVersion(simpleDescriptor.getDeviceVersion());
            endpoint.setInputClusterIds(simpleDescriptor.getInputClusterList());
            endpoint.setOutputClusterIds(simpleDescriptor.getOutputClusterList());

            return ZigBeeStatus.SUCCESS;
        }

        return ZigBeeStatus.FAILURE;
    }

    /**
     * Searches for any metering servers on the network
     */
    private void discoverMeteringServers() {
        logger.debug("SEP Extension: SEP discovery searching for Metering servers");
        discoverServices(ZigBeeBroadcastDestination.BROADCAST_RX_ON.getKey(), ZclMeteringCluster.CLUSTER_ID);
    }

    /**
     * Searches for the keep alive cluster on the trust centre
     */
    private void discoverKeepAlive() {
        logger.debug("SEP Extension: SEP discovery searching for keep alive cluster");
        updateClientState(SmartEnergyClientState.DISCOVER_KEEP_ALIVE_TIMEOUT);
    }

    /**
     * Completes the search for the keep alive cluster on the trust centre. This is called when there is no response to
     * the service discovery and we use the old system.
     */
    private void discoverKeepAliveTimeout() {
        logger.debug("SEP Extension: SEP discovery timeout searching for keep alive cluster");
        updateClientState(SmartEnergyClientState.KEEP_ALIVE);
    }

    private void handleMatchDescriptorResponse(MatchDescriptorResponse response) {
        // In all events, we ignore the frame if status isn't success, or there's no endpoints
        if (response.getStatus() != ZdoStatus.SUCCESS || response.getMatchList().size() == 0) {
            return;
        }

        logger.debug("SEP Extension: Processing MatchDescriptor in state {}: {}", seState, response);
        ZigBeeNode trustCentre;

        switch (seState) {
            case DISCOVER_KEY_ESTABLISHMENT_CLUSTER:
                // Check this is from the Trust Centre
                if (response.getSourceAddress().getAddress() != 0) {
                    return;
                }

                trustCentre = networkManager.getNode(0);
                if (trustCentre == null) {
                    logger.debug("SEP Extension: Trust Centre not found in network nodes list");

                    updateClientState(SmartEnergyClientState.DISCOVER_TRUST_CENTRE);
                    return;
                }

                // Updates need to go into a new object to ensure change detection works when we update the node
                ZigBeeNode updatedTrustCentre = new ZigBeeNode(networkManager, trustCentre.getIeeeAddress());

                // Key Establishment is a global cluster so just use the first endpoint
                trustCenterKeyEstablishmentEndpoint = response.getMatchList().get(0);
                logger.debug("SEP Extension: SEP discovery is using endpoint {} for KeyEstablishment",
                        trustCenterKeyEstablishmentEndpoint);

                ZigBeeEndpoint keEndpoint = new ZigBeeEndpoint(trustCentre, trustCenterKeyEstablishmentEndpoint);
                keEndpoint.setProfileId(ZigBeeProfileType.ZIGBEE_SMART_ENERGY.getKey());
                updatedTrustCentre.addEndpoint(keEndpoint);

                ZclKeyEstablishmentCluster keCluster = new ZclKeyEstablishmentCluster(keEndpoint);
                keEndpoint.addInputCluster(keCluster);

                networkManager.updateNode(updatedTrustCentre);

                // Advance the state machine
                updateClientState(SmartEnergyClientState.PERFORM_KEY_ESTABLISHMENT);
                break;

            case DISCOVER_METERING_SERVERS:
                ZigBeeNode node = networkManager.getNode(response.getSourceAddress().getAddress());
                if (node == null) {
                    logger.debug("{}: SEP Extension: SEP discovery node is unknown - getting IEEE address.",
                            response.getSourceAddress().getAddress());
                    // This node is unknown to us - get the long address
                    IeeeAddress ieeeAddress = requestIeeeAddress(response.getSourceAddress().getAddress());
                    node = new ZigBeeNode(networkManager, ieeeAddress);
                    networkManager.updateNode(node);
                }

                // Check if this node is authorised to communicate securely
                if (!cbkeProvider.isAuthorised(node.getIeeeAddress())) {
                    logger.debug("{}: SEP Extension: SEP discovery node is not authorised", node.getIeeeAddress());
                    return;
                }

                ZigBeeNode updatedNode = new ZigBeeNode(networkManager, node.getIeeeAddress(),
                        node.getNetworkAddress());
                for (Integer endpointId : response.getMatchList()) {
                    ZigBeeEndpoint endpoint = new ZigBeeEndpoint(updatedNode, endpointId);
                    logger.debug("{}: SEP Extension: Endpoint {} Metering endpoint being added/updated",
                            node.getIeeeAddress(), endpoint.getEndpointAddress());

                    try {
                        requestSimpleDescriptor(endpoint);
                    } catch (InterruptedException | ExecutionException e) {
                        logger.debug("{}: SEP Extension: Endpoint {} Exception getting simple descriptor",
                                node.getIeeeAddress(), endpoint.getEndpointAddress());
                    }
                    updatedNode.addEndpoint(endpoint);
                }
                setProfileSecurity(updatedNode);
                networkManager.updateNode(updatedNode);

                discoveryComplete();
                break;

            case DISCOVER_KEEP_ALIVE:
                // Check this is from the Trust Centre
                if (response.getSourceAddress().getAddress() != 0) {
                    return;
                }
                // Keep Alive is a global cluster so just use the first endpoint
                trustCenterKeepAliveEndpoint = response.getMatchList().get(0);
                logger.debug("SEP Extension: SEP discovery is using endpoint {} for KeepAlive",
                        trustCenterKeepAliveEndpoint);

                // Advance the state machine
                updateClientState(SmartEnergyClientState.KEEP_ALIVE);
                break;

            default:
                break;
        }
    }

    /**
     * Callback from the {@link KeyEstablishmentClient} when the key establishment completes or terminates.
     * <p>
     * If the returnState is {@link ZigBeeStatus#FATAL_ERROR} the application should leave the network.
     *
     * @param returnState a {@link ZigBeeStatus} indicating if the key establishment completed successfully
     * @param waitTime a time in seconds to wait before retrying any key establishment if the key establishment failed
     */
    protected void keyEstablishmentCallback(ZigBeeStatus returnState, Integer waitTime) {
        logger.debug("SEP Extension: keyEstablishmentCallback state={}", returnState);

        if (returnState == ZigBeeStatus.FATAL_ERROR) {
            updateClientState(SmartEnergyClientState.FATAL);
            return;
        } else {
            updateClientState(returnState == ZigBeeStatus.SUCCESS ? SmartEnergyClientState.DISCOVER_METERING_SERVERS
                    : SmartEnergyClientState.PERFORM_KEY_ESTABLISHMENT);
        }

        int time = TIMER_IMMEDIATE;
        if (returnState == ZigBeeStatus.FAILURE) {
            if (waitTime == 0) {
                time = TIMER_RESTART_KEY_ESTABLISHMENT;
            } else {
                time = (waitTime * 1000);
            }
            logger.debug("SEP Extension: keyEstablishmentCallback next timer {}ms", time);
            timerStart(time);
        } else {
            setProfileSecurity(networkManager.getNode(0));
        }
    }

    @Override
    public void commandReceived(ZigBeeCommand command) {
        if (command instanceof MatchDescriptorResponse) {
            handleMatchDescriptorResponse((MatchDescriptorResponse) command);
        }
    }

    @Override
    public void networkStateUpdated(ZigBeeNetworkState state) {
        switch (state) {
            case ONLINE:
                // Network has come online - start a scan
                discoveryStart();
                break;
            case OFFLINE:
                discoveryStop();
                break;
            default:
                break;
        }
    }

    /**
     * Returns the smart energy discovery state.
     *
     * @return the current {@link SmartEnergyClientState}
     */
    public SmartEnergyClientState getDiscoveryState() {
        return seState;
    }

    /**
     * Get Node IEEE address
     *
     * @param networkAddress the network address of the node
     * @return true if the message was processed ok
     */
    private IeeeAddress requestIeeeAddress(final int networkAddress) {
        try {
            // Request simple response, start index for associated list is 0
            final IeeeAddressRequest ieeeAddressRequest = new IeeeAddressRequest(
                    networkAddress,
                    0,
                    0);
            ieeeAddressRequest.setDestinationAddress(new ZigBeeEndpointAddress(networkAddress));
            CommandResult response = networkManager.sendTransaction(ieeeAddressRequest, ieeeAddressRequest).get();
            if (response.isError()) {
                logger.debug("SEP Extension: IeeeAddressRequest returned null");
                return null;
            }

            final IeeeAddressResponse ieeeAddressResponse = response.getResponse();
            logger.debug("SEP Extension: IeeeAddressRequest returned {}", ieeeAddressResponse);
            if (ieeeAddressResponse != null && ieeeAddressResponse.getStatus() == ZdoStatus.SUCCESS) {
                return ieeeAddressResponse.getIeeeAddrRemoteDev();
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.debug("SEP Extension: IeeeAddressRequest error ", e);
        }

        logger.debug("SEP Extension: IeeeAddressRequest is null");
        return null;
    }

    /**
     * Enable APS security on all clusters that require this within the Smart Energy Profile.
     *
     * @param node the {@link ZigBeeNode} on which to enforce SEP security requirements
     */
    private void setProfileSecurity(ZigBeeNode node) {
        logger.debug("{}: SEP Extension: Setting profile security", node.getIeeeAddress());
        if (!cbkeProvider.isAuthorised(node.getIeeeAddress())) {
            logger.debug("{}: SEP Extension: Node is not authorised", node.getIeeeAddress());
            return;
        }
        logger.debug("{}: SEP Extension: Node is authorised", node.getIeeeAddress());

        for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
            if (endpoint.getProfileId() != ZigBeeProfileType.ZIGBEE_SMART_ENERGY.getKey()) {
                logger.debug("{}: SEP Extension: Endpoint {} is not SmartEnergy [{}]", node.getIeeeAddress(),
                        endpoint.getEndpointId(), String.format("%04X", endpoint.getProfileId()));
                continue;
            }

            for (ZclClusterType clusterType : secureClusters) {
                ZclCluster cluster = endpoint.getInputCluster(clusterType.getId());
                if (cluster == null) {
                    continue;
                }

                logger.debug("{}: SEP Extension: Endpoint {} Setting profile security for input cluster {}",
                        node.getIeeeAddress(), endpoint.getEndpointId(), clusterType);
                cluster.setApsSecurityRequired(true);
            }

            for (ZclClusterType clusterType : secureClusters) {
                ZclCluster cluster = endpoint.getOutputCluster(clusterType.getId());
                if (cluster == null) {
                    continue;
                }

                logger.debug("{}: SEP Extension: Endpoint {} Setting profile security for output cluster {}",
                        node.getIeeeAddress(), endpoint.getEndpointId(), clusterType);
                cluster.setApsSecurityRequired(true);
            }
        }
    }

    /**
     * Perform the keep-alive poll.
     */
    private void keepalivePoll() {
        logger.debug("SEP Extension: Performing TC keep-alive poll");

        ZigBeeNode node = networkManager.getNode(0);
        if (node == null) {
            logger.debug("SEP Extension: Cant find TC to perform keep-alive poll");
            return;
        }

        ZigBeeEndpoint endpoint = node.getEndpoint(trustCenterKeyEstablishmentEndpoint);
        ZclKeyEstablishmentCluster keCluster = (ZclKeyEstablishmentCluster) endpoint
                .getInputCluster(ZclKeyEstablishmentCluster.CLUSTER_ID);
        if (keCluster == null) {
            logger.error("SEP Extension: KeyEstablishment cluster not found in endpoint for TC keep-alive poll");
            return;
        }

        ZclAttribute keSuiteAttribute = keCluster
                .getAttribute(ZclKeyEstablishmentCluster.ATTR_SERVERKEYESTABLISHMENTSUITE);
        boolean success = keSuiteAttribute.readValue(0) != null;
        if (success) {
            keepaliveCounter = 0;
            lastKeepAliveTime = Calendar.getInstance();
        } else {
            keepaliveCounter++;
        }
        logger.debug("SEP Extension: TC keep-alive poll {}successful. Retries={}", success ? "" : "un",
                keepaliveCounter);
    }

    @Override
    public void nodeAdded(ZigBeeNode node) {
        logger.debug("SEP Extension: Adding node {} [{}]", node.getIeeeAddress(),
                String.format("%04X", node.getNetworkAddress()));
        for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
            if (cbkeClientRegistry.get(endpoint.getEndpointAddress()) != null) {
                logger.debug("SEP Extension: CBKE client handler for node {} endpoint {} already set",
                        node.getIeeeAddress(), endpoint.getEndpointId());
            } else {
                ZclKeyEstablishmentCluster keCluster = (ZclKeyEstablishmentCluster) endpoint
                        .getInputCluster(ZclKeyEstablishmentCluster.CLUSTER_ID);
                if (keCluster != null) {
                    logger.debug("SEP Extension: Adding CBKE client handler to node {} endpoint {}",
                            node.getIeeeAddress(), endpoint.getEndpointId());
                    ZclKeyEstablishmentClient keClient = new ZclKeyEstablishmentClient(node.getIeeeAddress(), this,
                            keCluster);
                    keClient.setCbkeProvider(cbkeProvider);
                    keClient.setCryptoSuite(forceCryptoSuite);
                    cbkeClientRegistry.put(endpoint.getEndpointAddress(), keClient);
                }
            }

            if (cbkeServerRegistry.get(endpoint.getEndpointAddress()) != null) {
                logger.debug("SEP Extension: CBKE server handler for node {} endpoint {} already set",
                        node.getIeeeAddress(), endpoint.getEndpointId());
            } else {
                ZclKeyEstablishmentCluster keCluster = (ZclKeyEstablishmentCluster) endpoint
                        .getOutputCluster(ZclKeyEstablishmentCluster.CLUSTER_ID);
                if (keCluster != null) {
                    logger.debug("SEP Extension: Adding CBKE server handler to node {} endpoint {}",
                            node.getIeeeAddress(), endpoint.getEndpointId());
                    ZclAttribute attribute = keCluster
                            .getLocalAttribute(ZclKeyEstablishmentCluster.ATTR_SERVERKEYESTABLISHMENTSUITE);
                    if (attribute == null) {
                        logger.debug("{}: Unable to get ATTR_SERVERKEYESTABLISHMENTSUITE");
                    } else {
                        attribute.setValue(1);
                        attribute.setImplemented(true);
                    }

                    ZclKeyEstablishmentServer keServer = new ZclKeyEstablishmentServer(node.getIeeeAddress(),
                            keCluster);
                    keServer.setCbkeProvider(cbkeProvider);
                    cbkeServerRegistry.put(endpoint.getEndpointAddress(), keServer);
                }
            }
        }

        setProfileSecurity(node);
    }

    @Override
    public void nodeUpdated(ZigBeeNode node) {
        nodeAdded(node);
    }
}
