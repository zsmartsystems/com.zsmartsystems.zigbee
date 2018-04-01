/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.ember.internal.ash.AshFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspAddEndpointRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspAddEndpointResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetChildDataRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetChildDataResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetCurrentSecurityStateRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetCurrentSecurityStateResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetNetworkParametersRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetNetworkParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetParentChildParametersRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetParentChildParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspNetworkStateRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspNetworkStateResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspVersionRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspVersionResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberCurrentSecurityState;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberNetworkParameters;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberNetworkStatus;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EzspStatus;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.transaction.EzspSingleResponseTransaction;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.transaction.EzspTransaction;

/**
 * This class provides utility methods for accessing the Ember NCP
 *
 * @author Chris Jackson - Initial contribution
 *
 */
public class EmberNcp {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(EmberNcp.class);

    /**
     * The ASH protocol handler used to send and receive EZSP packets
     */
    private AshFrameHandler ashHandler;

    /**
     * Create the NCP instance
     *
     * @param ashHandler the {@link AshFrameHandler} used for communicating with the NCP
     */
    public EmberNcp(AshFrameHandler ashHandler) {
        this.ashHandler = ashHandler;
    }

    /**
     * The command allows the Host to specify the desired EZSP version and must be sent before any other command. The
     * response provides information about the firmware running on the NCP.
     *
     * @param desiredVersion the requested version we support
     * @return the {@link EzspVersionResponse}
     */
    public EzspVersionResponse getVersion(int desiredVersion) {
        EzspVersionRequest version = new EzspVersionRequest();
        version.setDesiredProtocolVersion(EzspFrame.getEzspVersion());
        EzspTransaction versionTransaction = ashHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(version, EzspVersionResponse.class));
        EzspVersionResponse versionResponse = (EzspVersionResponse) versionTransaction.getResponse();
        logger.debug(versionResponse.toString());

        return versionResponse;
    }

    /**
     * Gets the current security state that is being used by a device that is joined in the network.
     *
     * @return the {@link EmberNetworkParameters} or null on error
     */
    public EmberCurrentSecurityState getCurrentSecurityState() {
        EzspGetCurrentSecurityStateRequest networkParms = new EzspGetCurrentSecurityStateRequest();
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(networkParms,
                EzspGetCurrentSecurityStateResponse.class);
        ashHandler.sendEzspTransaction(transaction);
        EzspGetCurrentSecurityStateResponse currentSecurityStateResponse = (EzspGetCurrentSecurityStateResponse) transaction
                .getResponse();
        logger.debug(currentSecurityStateResponse.toString());
        if (currentSecurityStateResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error during retrieval of security parameters: {}", currentSecurityStateResponse);
            return null;
        }
        return currentSecurityStateResponse.getState();
    }

    /**
     * Gets the current network parameters, or an empty parameters class if there's an error
     *
     * @return {@link EmberNetworkParameters}
     */
    public EmberNetworkParameters getNetworkParameters() {
        EzspGetNetworkParametersRequest networkParms = new EzspGetNetworkParametersRequest();
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(networkParms,
                EzspGetNetworkParametersResponse.class);
        ashHandler.sendEzspTransaction(transaction);
        EzspGetNetworkParametersResponse getNetworkParametersResponse = (EzspGetNetworkParametersResponse) transaction
                .getResponse();
        logger.debug(getNetworkParametersResponse.toString());
        if (getNetworkParametersResponse.getStatus() != EmberStatus.EMBER_SUCCESS
                && getNetworkParametersResponse.getStatus() != EmberStatus.EMBER_NOT_JOINED) {
            logger.debug("Error during retrieval of network parameters: {}", getNetworkParametersResponse);
            return new EmberNetworkParameters();
        }

        return getNetworkParametersResponse.getParameters();
    }

    /**
     * Returns a value indicating whether the node is joining, joined to, or leaving a network.
     *
     * @return the {@link EmberNetworkStatus}
     */
    public EmberNetworkStatus getNetworkState() {
        EzspNetworkStateRequest networkStateRequest = new EzspNetworkStateRequest();
        EzspTransaction networkStateTransaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(networkStateRequest, EzspNetworkStateResponse.class));
        EzspNetworkStateResponse networkStateResponse = (EzspNetworkStateResponse) networkStateTransaction
                .getResponse();
        logger.debug(networkStateResponse.toString());

        return networkStateResponse.getStatus();
    }

    /**
     * Returns information about the children of the local node and the parent of the local node.
     *
     * @return the {@link EzspGetParentChildParametersResponse}
     */
    public EzspGetParentChildParametersResponse getChildParameters() {
        EzspGetParentChildParametersRequest childParametersRequest = new EzspGetParentChildParametersRequest();
        EzspTransaction childParametersTransaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(childParametersRequest, EzspGetParentChildParametersResponse.class));
        EzspGetParentChildParametersResponse childParametersResponse = (EzspGetParentChildParametersResponse) childParametersTransaction
                .getResponse();

        return childParametersResponse;
    }

    /**
     * Returns information about a child of the local node.
     *
     * @param childId the ID of the child to get information on
     * @return the {@link EzspGetChildDataResponse} of the requested childId
     */
    public EzspGetChildDataResponse getChildInformation(int childId) {
        EzspGetChildDataRequest childDataRequest = new EzspGetChildDataRequest();
        childDataRequest.setIndex(childId);
        EzspTransaction childDataTransaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(childDataRequest, EzspGetChildDataResponse.class));
        EzspGetChildDataResponse childDataResponse = (EzspGetChildDataResponse) childDataTransaction.getResponse();
        logger.debug(childDataResponse.toString());

        return childDataResponse;
    }

    /**
     * Configures endpoint information on the NCP. The NCP does not remember these settings after a reset. Endpoints can
     * be added by the Host after the NCP has reset. Once the status of the stack changes to EMBER_NETWORK_UP, endpoints
     * can no longer be added and this command will respond with EZSP_ERROR_INVALID_CALL.
     *
     * @param endpointId the endpoint number to add
     * @param deviceId the device id for the endpoint
     * @param profileId the profile id
     * @param inputClusters an array of input clusters supported by the endpoint
     * @param outputClusters an array of output clusters supported by the endpoint
     * @return the {@link EzspStatus} of the response
     */
    public EzspStatus addEndpoint(int endpointId, int deviceId, int profileId, int[] inputClusters,
            int[] outputClusters) {
        EzspAddEndpointRequest addEndpoint = new EzspAddEndpointRequest();
        addEndpoint.setEndpoint(endpointId);
        addEndpoint.setDeviceId(deviceId);
        addEndpoint.setProfileId(profileId);
        addEndpoint.setInputClusterList(new int[] { 0 });
        addEndpoint.setOutputClusterList(new int[] { 0 });
        EzspTransaction addEndpointTransaction = ashHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(addEndpoint, EzspAddEndpointResponse.class));
        EzspAddEndpointResponse addEndpointResponse = (EzspAddEndpointResponse) addEndpointTransaction.getResponse();

        logger.debug(addEndpointResponse.toString());

        return addEndpointResponse.getStatus();
    }
}
