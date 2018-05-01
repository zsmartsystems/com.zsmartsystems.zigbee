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

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeKey;
import com.zsmartsystems.zigbee.dongle.ember.internal.ash.AshFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspAddEndpointRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspAddEndpointResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspAddTransientLinkKeyRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspAddTransientLinkKeyResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspAesMmoHashRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspAesMmoHashResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetChildDataRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetChildDataResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetConfigurationValueRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetConfigurationValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetCurrentSecurityStateRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetCurrentSecurityStateResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetKeyRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetKeyResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetKeyTableEntryRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetKeyTableEntryResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetNetworkParametersRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetNetworkParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetParentChildParametersRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetParentChildParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetPolicyRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetPolicyResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetValueRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspNetworkStateRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspNetworkStateResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspReadCountersRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspReadCountersResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetConfigurationValueRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetConfigurationValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetPolicyRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetPolicyResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetValueRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspVersionRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspVersionResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberAesMmoHashContext;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberCurrentSecurityState;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberKeyData;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberKeyStruct;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberKeyType;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberNetworkParameters;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberNetworkStatus;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EzspConfigId;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EzspDecisionId;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EzspPolicyId;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EzspStatus;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EzspValueId;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.transaction.EzspSingleResponseTransaction;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.transaction.EzspTransaction;

/**
 * This class provides utility methods for accessing the Ember NCP.
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
     * The status value from the last request
     */
    private EmberStatus lastStatus;

    /**
     * Create the NCP instance
     *
     * @param ashHandler the {@link AshFrameHandler} used for communicating with the NCP
     */
    public EmberNcp(AshFrameHandler ashHandler) {
        this.ashHandler = ashHandler;
    }

    /**
     * Returns the {@link EmberStatus} from the last request. If the request did not provide a status, null is returned.
     *
     * @return {@link EmberStatus}
     */
    public EmberStatus getLastStatus() {
        return lastStatus;
    }

    /**
     * The command allows the Host to specify the desired EZSP version and must be sent before any other command. The
     * response provides information about the firmware running on the NCP.
     *
     * @param desiredVersion the requested version we support
     * @return the {@link EzspVersionResponse}
     */
    public EzspVersionResponse getVersion(int desiredVersion) {
        EzspVersionRequest request = new EzspVersionRequest();
        request.setDesiredProtocolVersion(EzspFrame.getEzspVersion());
        EzspTransaction transaction = ashHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspVersionResponse.class));
        EzspVersionResponse response = (EzspVersionResponse) transaction.getResponse();
        logger.debug(response.toString());
        lastStatus = null;

        return response;
    }

    /**
     * Gets the current security state that is being used by a device that is joined in the network.
     *
     * @return the {@link EmberNetworkParameters} or null on error
     */
    public EmberCurrentSecurityState getCurrentSecurityState() {
        EzspGetCurrentSecurityStateRequest request = new EzspGetCurrentSecurityStateRequest();
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(request,
                EzspGetCurrentSecurityStateResponse.class);
        ashHandler.sendEzspTransaction(transaction);
        EzspGetCurrentSecurityStateResponse response = (EzspGetCurrentSecurityStateResponse) transaction.getResponse();
        logger.debug(response.toString());
        lastStatus = response.getStatus();
        if (response.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error during retrieval of security parameters: {}", response);
            return null;
        }
        return response.getState();
    }

    /**
     * Gets the current network parameters, or an empty parameters class if there's an error
     *
     * @return {@link EmberNetworkParameters}
     */
    public EmberNetworkParameters getNetworkParameters() {
        EzspGetNetworkParametersRequest request = new EzspGetNetworkParametersRequest();
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(request,
                EzspGetNetworkParametersResponse.class);
        ashHandler.sendEzspTransaction(transaction);
        EzspGetNetworkParametersResponse response = (EzspGetNetworkParametersResponse) transaction.getResponse();
        logger.debug(response.toString());
        lastStatus = response.getStatus();
        if (response.getStatus() != EmberStatus.EMBER_SUCCESS && response.getStatus() != EmberStatus.EMBER_NOT_JOINED) {
            logger.debug("Error during retrieval of network parameters: {}", response);
            return new EmberNetworkParameters();
        }

        return response.getParameters();
    }

    /**
     * Returns a value indicating whether the node is joining, joined to, or leaving a network.
     *
     * @return the {@link EmberNetworkStatus}
     */
    public EmberNetworkStatus getNetworkState() {
        EzspNetworkStateRequest request = new EzspNetworkStateRequest();
        EzspTransaction transaction = ashHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspNetworkStateResponse.class));
        EzspNetworkStateResponse response = (EzspNetworkStateResponse) transaction.getResponse();
        logger.debug(response.toString());
        lastStatus = null;

        return response.getStatus();
    }

    /**
     * Returns information about the children of the local node and the parent of the local node.
     *
     * @return the {@link EzspGetParentChildParametersResponse}
     */
    public EzspGetParentChildParametersResponse getChildParameters() {
        EzspGetParentChildParametersRequest request = new EzspGetParentChildParametersRequest();
        EzspTransaction transaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(request, EzspGetParentChildParametersResponse.class));
        EzspGetParentChildParametersResponse response = (EzspGetParentChildParametersResponse) transaction
                .getResponse();
        lastStatus = null;

        return response;
    }

    /**
     * Returns information about a child of the local node.
     *
     * @param childId the ID of the child to get information on
     * @return the {@link EzspGetChildDataResponse} of the requested childId or null on error
     */
    public EzspGetChildDataResponse getChildInformation(int childId) {
        EzspGetChildDataRequest request = new EzspGetChildDataRequest();
        request.setIndex(childId);
        EzspTransaction transaction = ashHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspGetChildDataResponse.class));
        EzspGetChildDataResponse response = (EzspGetChildDataResponse) transaction.getResponse();
        logger.debug(response.toString());
        lastStatus = response.getStatus();
        if (lastStatus != EmberStatus.EMBER_SUCCESS) {
            return null;
        }

        return response;
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
        EzspAddEndpointRequest request = new EzspAddEndpointRequest();
        request.setEndpoint(endpointId);
        request.setDeviceId(deviceId);
        request.setProfileId(profileId);
        request.setInputClusterList(new int[] { 0 });
        request.setOutputClusterList(new int[] { 0 });
        EzspTransaction transaction = ashHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspAddEndpointResponse.class));
        EzspAddEndpointResponse response = (EzspAddEndpointResponse) transaction.getResponse();

        logger.debug(response.toString());
        lastStatus = null;

        return response.getStatus();
    }

    /**
     * Retrieves Ember counters. See the EmberCounterType enumeration for the counter types.
     *
     * @return the array of counters
     */
    public int[] getCounters() {
        EzspReadCountersRequest request = new EzspReadCountersRequest();
        EzspTransaction transaction = ashHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspReadCountersResponse.class));
        EzspReadCountersResponse response = (EzspReadCountersResponse) transaction.getResponse();
        logger.debug(response.toString());
        lastStatus = null;
        return response.getValues();
    }

    /**
     * Gets a Security Key based on the passed key type.
     *
     * @param keyType the {@link EmberKeyType} of the key to get
     * @return the {@link EmberKeyStruct} or null on error
     */
    public EmberKeyStruct getKey(EmberKeyType keyType) {
        EzspGetKeyRequest request = new EzspGetKeyRequest();
        request.setKeyType(keyType);
        EzspTransaction transaction = ashHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspGetKeyResponse.class));
        EzspGetKeyResponse response = (EzspGetKeyResponse) transaction.getResponse();
        logger.debug(response.toString());
        lastStatus = response.getStatus();
        if (lastStatus != EmberStatus.EMBER_SUCCESS) {
            return null;
        }
        return response.getKeyStruct();
    }

    /**
     * Gets a Security Key based on the passed key type.
     *
     * @param index the index of the key to get
     * @return the {@link EmberKeyStruct} of the requested key or null on error
     */
    public EmberKeyStruct getKeyTableEntry(int index) {
        EzspGetKeyTableEntryRequest request = new EzspGetKeyTableEntryRequest();
        request.setIndex(index);
        EzspTransaction transaction = ashHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspGetKeyTableEntryResponse.class));
        EzspGetKeyTableEntryResponse response = (EzspGetKeyTableEntryResponse) transaction.getResponse();
        logger.debug(response.toString());
        lastStatus = response.getStatus();
        if (lastStatus != EmberStatus.EMBER_SUCCESS) {
            return null;
        }
        return response.getKeyStruct();
    }

    /**
     * Get a configuration value
     *
     * @param configId the {@link EzspConfigId} to set
     * @return the configuration value as {@link Integer} or null on error
     */
    public Integer getConfiguration(EzspConfigId configId) {
        EzspGetConfigurationValueRequest request = new EzspGetConfigurationValueRequest();
        request.setConfigId(configId);

        EzspTransaction transaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(request, EzspGetConfigurationValueResponse.class));
        EzspGetConfigurationValueResponse response = (EzspGetConfigurationValueResponse) transaction.getResponse();
        lastStatus = null;
        logger.debug(response.toString());

        if (response.getStatus() != EzspStatus.EZSP_SUCCESS) {
            return null;
        }

        return response.getValue();
    }

    /**
     * Set a configuration value
     *
     * @param configId the {@link EzspConfigId} to set
     * @param value the value to set
     * @return true if the configuration returns success
     */
    public boolean setConfiguration(EzspConfigId configId, Integer value) {
        EzspSetConfigurationValueRequest request = new EzspSetConfigurationValueRequest();
        request.setConfigId(configId);
        request.setValue(value);
        logger.debug(request.toString());

        EzspTransaction transaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(request, EzspSetConfigurationValueResponse.class));
        EzspSetConfigurationValueResponse response = (EzspSetConfigurationValueResponse) transaction.getResponse();
        lastStatus = null;
        logger.debug(response.toString());

        return response.getStatus() == EzspStatus.EZSP_SUCCESS;
    }

    /**
     * Set a policy used by the NCP to make fast decisions.
     *
     * @param policyId the {@link EzspPolicyId} to set
     * @param decisionId the {@link EzspDecisionId} to set to
     * @return true if the policy setting was successful
     */
    public boolean setPolicy(EzspPolicyId policyId, EzspDecisionId decisionId) {
        EzspSetPolicyRequest setPolicyRequest = new EzspSetPolicyRequest();
        setPolicyRequest.setPolicyId(policyId);
        setPolicyRequest.setDecisionId(decisionId);
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(setPolicyRequest,
                EzspSetPolicyResponse.class);
        ashHandler.sendEzspTransaction(transaction);
        EzspSetPolicyResponse setPolicyResponse = (EzspSetPolicyResponse) transaction.getResponse();
        lastStatus = null;
        logger.debug(setPolicyResponse.toString());
        if (setPolicyResponse.getStatus() != EzspStatus.EZSP_SUCCESS) {
            logger.debug("Error during setting policy: {}", setPolicyResponse);
            return false;
        }

        return true;
    }

    /**
     * Get a policy used by the NCP to make fast decisions.
     *
     * @param policyId the {@link EzspPolicyId} to set
     * @return the returned {@link EzspDecisionId} if the policy was retrieved successfully or null if there was an
     *         error
     */
    public EzspDecisionId getPolicy(EzspPolicyId policyId) {
        EzspGetPolicyRequest getPolicyRequest = new EzspGetPolicyRequest();
        getPolicyRequest.setPolicyId(policyId);
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(getPolicyRequest,
                EzspGetPolicyResponse.class);
        ashHandler.sendEzspTransaction(transaction);
        EzspGetPolicyResponse getPolicyResponse = (EzspGetPolicyResponse) transaction.getResponse();
        lastStatus = null;
        logger.debug(getPolicyResponse.toString());
        if (getPolicyResponse.getStatus() != EzspStatus.EZSP_SUCCESS) {
            logger.debug("Error getting policy: {}", getPolicyResponse);
            return null;
        }

        return getPolicyResponse.getDecisionId();
    }

    /**
     * Set a memory value used by the NCP.
     *
     * @param valueId the {@link EzspValueId} to set
     * @param value the value to set to
     * @return true if the value setting was successful
     */
    public boolean setValue(EzspValueId valueId, int[] value) {
        EzspSetValueRequest request = new EzspSetValueRequest();
        request.setValueId(valueId);
        request.setValue(value);
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(request,
                EzspSetValueResponse.class);
        ashHandler.sendEzspTransaction(transaction);
        EzspSetValueResponse response = (EzspSetValueResponse) transaction.getResponse();
        lastStatus = null;
        logger.debug(response.toString());
        if (response.getStatus() != EzspStatus.EZSP_SUCCESS) {
            logger.debug("Error setting value: {}", response);
            return false;
        }

        return true;
    }

    /**
     * Get a memory value from the NCP
     *
     * @param valueId the {@link EzspValueId} to set
     * @return the returned value as int[]
     */
    public int[] getValue(EzspValueId valueId) {
        EzspGetValueRequest request = new EzspGetValueRequest();
        request.setValueId(valueId);
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(request,
                EzspGetValueResponse.class);
        ashHandler.sendEzspTransaction(transaction);
        EzspGetValueResponse response = (EzspGetValueResponse) transaction.getResponse();
        lastStatus = null;
        logger.debug(response.toString());
        if (response.getStatus() != EzspStatus.EZSP_SUCCESS) {
            logger.debug("Error getting value: {}", response);
            return null;
        }

        return response.getValue();
    }

    /**
     * Adds a transient link key to the NCP
     *
     * @param partner the {@link IeeeAddress} to set
     * @param transientKey the {@link ZigBeeKey} to set
     * @return the {@link EmberStatus} of the response
     */
    public EmberStatus addTransientLinkKey(IeeeAddress partner, ZigBeeKey transientKey) {
        EmberKeyData emberKey = new EmberKeyData();
        emberKey.setContents(transientKey.getValue());
        EzspAddTransientLinkKeyRequest request = new EzspAddTransientLinkKeyRequest();
        request.setPartner(partner);
        request.setTransientKey(emberKey);
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(request,
                EzspAddTransientLinkKeyResponse.class);
        ashHandler.sendEzspTransaction(transaction);
        EzspAddTransientLinkKeyResponse response = (EzspAddTransientLinkKeyResponse) transaction.getResponse();
        lastStatus = response.getStatus();
        logger.debug(response.toString());
        if (response.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error setting transient key: {}", response);
        }

        return response.getStatus();
    }

    /**
     * This routine processes the passed chunk of data and updates the hash context based on it. If the 'finalize'
     * parameter is not set, then the length of the data passed in must be a multiple of 16. If the 'finalize' parameter
     * is set then the length can be any value up 1-16, and the final hash value will be calculated.
     *
     * @param code the integer array to hash
     * @return the resulting {@link EmberAesMmoHashContext}
     */
    public EmberAesMmoHashContext mmoHash(int[] code) {
        EmberAesMmoHashContext hashContext = new EmberAesMmoHashContext();
        hashContext.setResult(new int[16]);
        hashContext.setLength(0);
        EzspAesMmoHashRequest request = new EzspAesMmoHashRequest();
        request.setContext(hashContext);
        request.setData(code);
        request.setFinalize(true);
        request.setLength(code.length);
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(request,
                EzspAesMmoHashResponse.class);
        ashHandler.sendEzspTransaction(transaction);
        EzspAesMmoHashResponse response = (EzspAesMmoHashResponse) transaction.getResponse();
        lastStatus = response.getStatus();
        logger.debug(response.toString());
        if (response.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error performing AES MMO hash: {}", response);
        }

        return response.getReturnContext();
    }

}
