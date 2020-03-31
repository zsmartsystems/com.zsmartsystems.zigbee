/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspAddEndpointRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspAddEndpointResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspAddOrUpdateKeyTableEntryRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspAddOrUpdateKeyTableEntryResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspAddTransientLinkKeyRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspAddTransientLinkKeyResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspAesMmoHashRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspAesMmoHashResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspClearKeyTableRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspClearKeyTableResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspEnergyScanResultHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetCertificate283k1Request;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetCertificate283k1Response;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetCertificateRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetCertificateResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetChildDataRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetChildDataResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetConfigurationValueRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetConfigurationValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetCurrentSecurityStateRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetCurrentSecurityStateResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetEui64Request;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetEui64Response;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetKeyRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetKeyResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetKeyTableEntryRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetKeyTableEntryResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetLibraryStatusRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetLibraryStatusResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetMfgTokenRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetMfgTokenResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNetworkParametersRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNetworkParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNodeIdRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNodeIdResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetParentChildParametersRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetParentChildParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetPolicyRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetPolicyResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetStandaloneBootloaderVersionPlatMicroPhyRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetStandaloneBootloaderVersionPlatMicroPhyResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetTransientKeyTableEntryRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetTransientKeyTableEntryResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetTransientLinkKeyRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetTransientLinkKeyResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetValueRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspLeaveNetworkRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspLeaveNetworkResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkFoundHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkInitRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkInitResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkStateRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkStateResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspReadCountersRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspReadCountersResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspScanCompleteHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetConfigurationValueRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetConfigurationValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetExtendedTimeoutRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetExtendedTimeoutResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetManufacturerCodeRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetManufacturerCodeResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetPolicyRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetPolicyResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetPowerDescriptorRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetPowerDescriptorResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetRadioPowerRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetRadioPowerResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetValueRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspStartScanRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspStartScanResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspVersionRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspVersionResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberAesMmoHashContext;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCertificate283k1Data;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCertificateData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCurrentSecurityState;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyStruct;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberLibraryId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberLibraryStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkParameters;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberTransientKeyData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspConfigId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspDecisionId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspMfgTokenId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspNetworkScanType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspPolicyId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspValueId;
import com.zsmartsystems.zigbee.dongle.ember.internal.EzspFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.EzspProtocolHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.transaction.EzspMultiResponseTransaction;
import com.zsmartsystems.zigbee.dongle.ember.internal.transaction.EzspSingleResponseTransaction;
import com.zsmartsystems.zigbee.dongle.ember.internal.transaction.EzspTransaction;
import com.zsmartsystems.zigbee.security.ZigBeeKey;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;

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
     * The protocol handler used to send and receive EZSP packets
     */
    private final EzspProtocolHandler protocolHandler;

    /**
     * The status value from the last request
     */
    private EmberStatus lastStatus;

    /**
     * Create the NCP instance
     *
     * @param protocolHandler the {@link EzspFrameHandler} used for communicating with the NCP
     */
    public EmberNcp(EzspProtocolHandler protocolHandler) {
        this.protocolHandler = protocolHandler;
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
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspVersionResponse.class));
        EzspVersionResponse response = (EzspVersionResponse) transaction.getResponse();
        if (response == null) {
            logger.debug("No response from ezspVersion command");
            return null;
        }
        logger.debug(response.toString());
        lastStatus = null;

        return response;
    }

    /**
     * Resume network operation after a reboot. The node retains its original type. This should be called on startup
     * whether or not the node was previously part of a network. EMBER_NOT_JOINED is returned if the node is not part of
     * a network.
     *
     * @return {@link EmberStatus} if success or failure
     */
    public EmberStatus networkInit() {
        EzspNetworkInitRequest request = new EzspNetworkInitRequest();
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspNetworkInitResponse.class));
        EzspNetworkInitResponse response = (EzspNetworkInitResponse) transaction.getResponse();
        logger.debug(response.toString());

        return response.getStatus();
    }

    /**
     * Causes the stack to leave the current network. This generates a stackStatusHandler callback to indicate that the
     * network is down. The radio will not be used until after sending a formNetwork or joinNetwork command.
     * <p>
     * Note that the user must wait for the network state to change to {@link EmberNetworkStatus#EMBER_NO_NETWORK}
     * otherwise the state may remain in {@link EmberNetworkStatus#EMBER_LEAVING_NETWORK} and attempts to reinitialise
     * the NCP will result in {@link EmberStatus#EMBER_INVALID_CALL}.
     *
     * @return {@link EmberStatus} if success or failure
     */
    public EmberStatus leaveNetwork() {
        EzspLeaveNetworkRequest request = new EzspLeaveNetworkRequest();
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspLeaveNetworkResponse.class));
        EzspLeaveNetworkResponse response = (EzspLeaveNetworkResponse) transaction.getResponse();
        logger.debug(response.toString());

        return response.getStatus();
    }

    /**
     * Gets the current security state that is being used by a device that is joined in the network.
     *
     * @return the {@link EmberNetworkParameters} or null on error
     */
    public EmberCurrentSecurityState getCurrentSecurityState() {
        EzspGetCurrentSecurityStateRequest request = new EzspGetCurrentSecurityStateRequest();
        EzspTransaction transaction = protocolHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(request, EzspGetCurrentSecurityStateResponse.class));
        EzspGetCurrentSecurityStateResponse response = (EzspGetCurrentSecurityStateResponse) transaction.getResponse();
        logger.debug(response.toString());
        lastStatus = response.getStatus();
        return response.getState();
    }

    /**
     * Gets the current network parameters, or an empty parameters class if there's an error
     *
     * @return {@link EzspGetNetworkParametersResponse}
     */
    public EzspGetNetworkParametersResponse getNetworkParameters() {
        EzspGetNetworkParametersRequest request = new EzspGetNetworkParametersRequest();
        EzspTransaction transaction = protocolHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(request, EzspGetNetworkParametersResponse.class));
        return (EzspGetNetworkParametersResponse) transaction.getResponse();
    }

    /**
     * Returns a value indicating whether the node is joining, joined to, or leaving a network.
     *
     * @return the {@link EmberNetworkStatus}
     */
    public EmberNetworkStatus getNetworkState() {
        EzspNetworkStateRequest request = new EzspNetworkStateRequest();
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspNetworkStateResponse.class));
        EzspNetworkStateResponse response = (EzspNetworkStateResponse) transaction.getResponse();
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
        EzspTransaction transaction = protocolHandler.sendEzspTransaction(
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
        EzspTransaction transaction = protocolHandler
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
        request.setInputClusterList(inputClusters);
        request.setOutputClusterList(outputClusters);
        EzspTransaction transaction = protocolHandler
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
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspReadCountersResponse.class));
        EzspReadCountersResponse response = (EzspReadCountersResponse) transaction.getResponse();
        logger.debug(response.toString());
        lastStatus = null;
        return response.getValues();
    }

    /**
     * Clears the key table on the NCP
     *
     * @return the {@link EmberStatus} or null on error
     */
    public EmberStatus clearKeyTable() {
        EzspClearKeyTableRequest request = new EzspClearKeyTableRequest();
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspClearKeyTableResponse.class));
        EzspClearKeyTableResponse response = (EzspClearKeyTableResponse) transaction.getResponse();
        logger.debug(response.toString());
        lastStatus = response.getStatus();
        return lastStatus;
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
        EzspTransaction transaction = protocolHandler
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
        EzspTransaction transaction = protocolHandler
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

        EzspTransaction transaction = protocolHandler.sendEzspTransaction(
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
     * @return the {@link EzspStatus} of the response
     */
    public EzspStatus setConfiguration(EzspConfigId configId, Integer value) {
        EzspSetConfigurationValueRequest request = new EzspSetConfigurationValueRequest();
        request.setConfigId(configId);
        request.setValue(value);
        logger.debug(request.toString());

        EzspTransaction transaction = protocolHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(request, EzspSetConfigurationValueResponse.class));
        EzspSetConfigurationValueResponse response = (EzspSetConfigurationValueResponse) transaction.getResponse();
        lastStatus = null;
        logger.debug(response.toString());

        return response.getStatus();
    }

    /**
     * Set a policy used by the NCP to make fast decisions.
     *
     * @param policyId the {@link EzspPolicyId} to set
     * @param decisionId the {@link EzspDecisionId} to set to
     * @return the {@link EzspStatus} of the response
     */
    public EzspStatus setPolicy(EzspPolicyId policyId, EzspDecisionId decisionId) {
        EzspSetPolicyRequest setPolicyRequest = new EzspSetPolicyRequest();
        setPolicyRequest.setPolicyId(policyId);
        setPolicyRequest.setDecisionId(decisionId.getKey());
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(setPolicyRequest, EzspSetPolicyResponse.class));
        EzspSetPolicyResponse setPolicyResponse = (EzspSetPolicyResponse) transaction.getResponse();
        lastStatus = null;
        logger.debug(setPolicyResponse.toString());
        if (setPolicyResponse.getStatus() != EzspStatus.EZSP_SUCCESS) {
            logger.debug("Error during setting policy: {}", setPolicyResponse);
        }

        return setPolicyResponse.getStatus();
    }

    /**
     * Set a policy used by the NCP to make fast decisions.
     *
     * @param policyId the {@link EzspPolicyId} to set
     * @param decisionId the decisionId to set to as an integer
     * @return the {@link EzspStatus} of the response
     */
    public EzspStatus setPolicy(EzspPolicyId policyId, int decisionId) {
        EzspSetPolicyRequest setPolicyRequest = new EzspSetPolicyRequest();
        setPolicyRequest.setPolicyId(policyId);
        setPolicyRequest.setDecisionId(decisionId);
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(setPolicyRequest, EzspSetPolicyResponse.class));
        EzspSetPolicyResponse setPolicyResponse = (EzspSetPolicyResponse) transaction.getResponse();
        lastStatus = null;
        logger.debug(setPolicyResponse.toString());
        if (setPolicyResponse.getStatus() != EzspStatus.EZSP_SUCCESS) {
            logger.debug("Error during setting policy: {}", setPolicyResponse);
        }

        return setPolicyResponse.getStatus();
    }

    /**
     * Get a policy used by the NCP to make fast decisions.
     *
     * @param policyId the {@link EzspPolicyId} to set
     * @return the returned {@link EzspDecisionId} if the policy was retrieved successfully or null if there was an
     *         error
     */
    public Integer getPolicy(EzspPolicyId policyId) {
        EzspGetPolicyRequest getPolicyRequest = new EzspGetPolicyRequest();
        getPolicyRequest.setPolicyId(policyId);
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(getPolicyRequest, EzspGetPolicyResponse.class));
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
     * @return the {@link EzspStatus} of the response
     */
    public EzspStatus setValue(EzspValueId valueId, int[] value) {
        EzspSetValueRequest request = new EzspSetValueRequest();
        request.setValueId(valueId);
        request.setValue(value);
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspSetValueResponse.class));
        EzspSetValueResponse response = (EzspSetValueResponse) transaction.getResponse();
        lastStatus = null;
        logger.debug(response.toString());
        if (response.getStatus() != EzspStatus.EZSP_SUCCESS) {
            logger.debug("Error setting value: {}", response);
        }

        return response.getStatus();
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
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspGetValueResponse.class));
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
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspAddTransientLinkKeyResponse.class));
        EzspAddTransientLinkKeyResponse response = (EzspAddTransientLinkKeyResponse) transaction.getResponse();
        lastStatus = response.getStatus();
        logger.debug(response.toString());
        if (response.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error setting transient key: {}", response);
        }

        return response.getStatus();
    }

    /**
     * Gets a transient link key from the NCP
     *
     * @param index the key index to retrieve
     * @return the {@link EmberTransientKeyData} of the response
     */
    public EmberTransientKeyData getTransientLinkKeyIndex(int index) {
        EzspGetTransientKeyTableEntryRequest request = new EzspGetTransientKeyTableEntryRequest();
        request.setIndex(index);
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(
                        new EzspSingleResponseTransaction(request, EzspGetTransientKeyTableEntryResponse.class));
        EzspGetTransientKeyTableEntryResponse response = (EzspGetTransientKeyTableEntryResponse) transaction
                .getResponse();
        logger.debug(response.toString());
        lastStatus = response.getStatus();
        if (lastStatus != EmberStatus.EMBER_SUCCESS) {
            return null;
        }
        return response.getTransientKeyData();
    }

    /**
     * Gets a transient link key from the NCP
     *
     * @param ieeeAddress the {@link IeeeAddress} transient key to retrieve
     * @return the {@link EmberTransientKeyData} of the response
     */
    public EmberTransientKeyData getTransientLinkKey(IeeeAddress ieeeAddress) {
        EzspGetTransientLinkKeyRequest request = new EzspGetTransientLinkKeyRequest();
        request.setEui(ieeeAddress);
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspGetTransientLinkKeyResponse.class));
        EzspGetTransientLinkKeyResponse response = (EzspGetTransientLinkKeyResponse) transaction
                .getResponse();
        logger.debug(response.toString());
        lastStatus = response.getStatus();
        if (lastStatus != EmberStatus.EMBER_SUCCESS) {
            return null;
        }
        return response.getTransientKeyData();
    }

    /**
     * Gets the {@link EmberCertificateData} certificate currently stored in the node.
     * <p>
     * This is the 163k1 certificate used in
     *
     * @return the {@link EmberCertificateData} certificate
     */
    public EmberCertificateData getCertificateData() {
        EzspGetCertificateRequest request = new EzspGetCertificateRequest();
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspGetCertificateResponse.class));
        EzspGetCertificateResponse response = (EzspGetCertificateResponse) transaction.getResponse();
        lastStatus = response.getStatus();
        if (response.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error getting 163k1 certificate: {}", response);
            return null;
        }

        return response.getLocalCert();
    }

    /**
     * Gets the {@link EmberCertificate283k1Data} certificate currently stored in the node
     *
     * @return the {@link EmberCertificate283k1Data} certificate
     */
    public EmberCertificate283k1Data getCertificate283k1Data() {
        EzspGetCertificate283k1Request request = new EzspGetCertificate283k1Request();
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspGetCertificate283k1Response.class));
        EzspGetCertificate283k1Response response = (EzspGetCertificate283k1Response) transaction.getResponse();
        lastStatus = response.getStatus();
        if (response.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error getting 283k1 certificate: {}", response);
            return null;
        }

        return response.getLocalCert();
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
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspAesMmoHashResponse.class));
        EzspAesMmoHashResponse response = (EzspAesMmoHashResponse) transaction.getResponse();
        lastStatus = response.getStatus();
        logger.debug(response.toString());
        if (response.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error performing AES MMO hash: {}", response);
        }

        return response.getReturnContext();
    }

    /**
     * Gets the {@link IeeeAddress} of the local node
     *
     * @return the {@link IeeeAddress} of the local node
     */
    public IeeeAddress getIeeeAddress() {
        EzspGetEui64Request request = new EzspGetEui64Request();
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspGetEui64Response.class));
        EzspGetEui64Response response = (EzspGetEui64Response) transaction.getResponse();
        return response.getEui64();
    }

    /**
     * Gets the 16 bit network node id of the local node
     *
     * @return the network address of the local node or 0xFFFE if the network address is not known
     */
    public int getNwkAddress() {
        EzspGetNodeIdRequest request = new EzspGetNodeIdRequest();
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspGetNodeIdResponse.class));
        EzspGetNodeIdResponse response = (EzspGetNodeIdResponse) transaction.getResponse();
        if (response == null) {
            return 0xFFFE;
        }
        return response.getNodeId();
    }

    /**
     * Sets the radio output power at which a node is operating. Ember radios have discrete power settings. For a list
     * of available power settings, see the technical specification for the RF communication module in your Developer
     * Kit. Note: Care should be taken when using this API on a running network, as it will directly impact the
     * established link qualities neighboring nodes have with the node on which it is called. This can lead to
     * disruption of existing routes and erratic network behavior.
     *
     * @param power Desired radio output power, in dBm.
     * @return the response {@link EmberStatus} of the request
     */
    public EmberStatus setRadioPower(int power) {
        EzspSetRadioPowerRequest request = new EzspSetRadioPowerRequest();
        request.setPower(power);
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspSetRadioPowerResponse.class));
        EzspSetRadioPowerResponse response = (EzspSetRadioPowerResponse) transaction.getResponse();
        return response.getStatus();
    }

    /**
     * Gets the {@link EmberLibraryStatus} of the requested {@link EmberLibraryId}
     *
     * @return the {@link EmberLibraryStatus} of the local node
     */
    public EmberLibraryStatus getLibraryStatus(EmberLibraryId libraryId) {
        EzspGetLibraryStatusRequest request = new EzspGetLibraryStatusRequest();
        request.setLibraryId(libraryId);
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspGetLibraryStatusResponse.class));
        EzspGetLibraryStatusResponse response = (EzspGetLibraryStatusResponse) transaction.getResponse();
        return response.getStatus();
    }

    /**
     * This function updates an existing entry in the key table or adds a new one. It first searches the table for an
     * existing entry that matches the passed EUI64 address. If no entry is found, it searches for the first free entry.
     * If successful, it updates the key data and resets the associated incoming frame counter. If it fails to find an
     * existing entry and no free one exists, it returns a failure.
     *
     * @param address the {@link IeeeAddress}
     * @param key the {@link ZigBeeKey}
     * @param linkKey This indicates whether the key is a Link or a Master Key
     * @return the returned {@link EmberStatus} of the request
     */
    public EmberStatus addOrUpdateKeyTableEntry(IeeeAddress address, ZigBeeKey key, boolean linkKey) {
        EmberKeyData keyData = new EmberKeyData();
        keyData.setContents(key.getValue());

        EzspAddOrUpdateKeyTableEntryRequest request = new EzspAddOrUpdateKeyTableEntryRequest();
        request.setAddress(address);
        request.setKeyData(keyData);
        request.setLinkKey(linkKey);
        EzspTransaction transaction = protocolHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(request, EzspAddOrUpdateKeyTableEntryResponse.class));
        EzspAddOrUpdateKeyTableEntryResponse response = (EzspAddOrUpdateKeyTableEntryResponse) transaction
                .getResponse();
        return response.getStatus();
    }

    /**
     * Sets the power descriptor to the specified value. The power descriptor is a dynamic value, therefore you should
     * call this function whenever the value changes.
     *
     * @param descriptor the descriptor to set as int
     */
    public void setPowerDescriptor(int descriptor) {
        EzspSetPowerDescriptorRequest request = new EzspSetPowerDescriptorRequest();
        request.setDescriptor(descriptor);
        protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspSetPowerDescriptorResponse.class));
    }

    /**
     * Sets the manufacturer code to the specified value. The manufacturer code is one of the fields of the node
     * descriptor.
     *
     * @param code the code to set as int
     */
    public void setManufacturerCode(int code) {
        EzspSetManufacturerCodeRequest request = new EzspSetManufacturerCodeRequest();
        request.setCode(code);
        protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspSetManufacturerCodeResponse.class));
    }

    /**
     * Gets the board name from the manufacturer information block on the NCP
     *
     * @return string containing the board name
     */
    public String getMfgBoardName() {
        int[] response = getMfgToken(EzspMfgTokenId.EZSP_MFG_BOARD_NAME);

        return intArrayToString(response);
    }

    /**
     * Gets the manufacturer name from the manufacturer information block on the NCP
     *
     * @return string containing the manufacturer name
     */
    public String getMfgName() {
        int[] response = getMfgToken(EzspMfgTokenId.EZSP_MFG_STRING);

        return intArrayToString(response);
    }

    /**
     * Gets the custom version from the manufacturer information block on the NCP
     *
     * @return integer containing the custom version
     */
    public int getMfgCustomVersion() {
        int[] response = getMfgToken(EzspMfgTokenId.EZSP_MFG_CUSTOM_VERSION);

        return (response[0] << 8) + response[1];
    }

    /**
     * Gets the install code stored in the NCP memory
     *
     * @return {@link ByteArray} defining the install code. May be empty if no installation code is defined, or null on
     *         error.
     */
    public ByteArray getMfgInstallationCode() {
        int[] response = getMfgToken(EzspMfgTokenId.EZSP_MFG_INSTALLATION_CODE);

        int length = 0;
        int flags = response[0] + (response[1] << 8);
        switch (flags) {
            case 0:
                length = 6;
                break;
            case 2:
                length = 8;
                break;
            case 4:
                length = 12;
                break;
            case 6:
                length = 16;
                break;
            case 65535:
                return new ByteArray(new int[] {});
            default:
                return null;
        }
        response[length + 2] = response[18];
        response[length + 3] = response[19];
        return new ByteArray(response, 2, length + 4);
    }

    /**
     * Perform an active scan. The radio will try to send beacon request on each channel.
     * <p>
     * During the scan, the CSMA/CA mechanism will be used to detect whether the radio channel is free at that moment.
     * If some of the radio channels in the environment are too busy for the device to perform the scan, the NCP returns
     * EMBER_MAC_COMMAND_TRANSMIT_FAILURE. A clearer RF environment might mitigate this issue.
     *
     * @param channelMask the channel mask on which to perform the scan.
     * @param scanDuration Sets the exponent of the number of scan periods, where a scan period is 960 symbols. The scan
     *            will occur for ((2^duration) + 1) scan periods.
     * @return a List of {@link EzspNetworkFoundHandler} on success. If there was an error during the scan, null is
     *         returned.
     */
    public Collection<EzspNetworkFoundHandler> doActiveScan(int channelMask, int scanDuration) {
        EzspStartScanRequest activeScan = new EzspStartScanRequest();
        activeScan.setChannelMask(channelMask);
        activeScan.setDuration(scanDuration);
        activeScan.setScanType(EzspNetworkScanType.EZSP_ACTIVE_SCAN);

        Set<Class<?>> relatedResponses = new HashSet<Class<?>>(
                Arrays.asList(EzspStartScanResponse.class, EzspNetworkFoundHandler.class));
        EzspTransaction transaction = protocolHandler.sendEzspTransaction(
                new EzspMultiResponseTransaction(activeScan, EzspScanCompleteHandler.class, relatedResponses));
        EzspScanCompleteHandler activeScanCompleteResponse = (EzspScanCompleteHandler) transaction.getResponse();
        logger.debug(activeScanCompleteResponse.toString());

        if (activeScanCompleteResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            lastStatus = activeScanCompleteResponse.getStatus();
            logger.debug("Error during active scan: {}", activeScanCompleteResponse);
            return null;
        }

        Map<ExtendedPanId, EzspNetworkFoundHandler> networksFound = new HashMap<>();
        for (EzspFrameResponse response : transaction.getResponses()) {
            if (response instanceof EzspNetworkFoundHandler) {
                EzspNetworkFoundHandler network = (EzspNetworkFoundHandler) response;
                networksFound.put(network.getNetworkFound().getExtendedPanId(), network);
            }
        }

        return networksFound.values();
    }

    /**
     * Performs an energy scan and returns the quietest channel
     *
     * @param channelMask the channel mask on which to perform the scan.
     * @param scanDuration Sets the exponent of the number of scan periods, where a scan period is 960 symbols. The scan
     *            will occur for ((2^duration) + 1) scan periods.
     * @return a List of {@link EzspNetworkFoundHandler} on success. If there was an error during the scan, null is
     *         returned.
     */
    public List<EzspEnergyScanResultHandler> doEnergyScan(int channelMask, int scanDuration) {
        EzspStartScanRequest energyScan = new EzspStartScanRequest();
        energyScan.setChannelMask(channelMask);
        energyScan.setDuration(scanDuration);
        energyScan.setScanType(EzspNetworkScanType.EZSP_ENERGY_SCAN);

        Set<Class<?>> relatedResponses = new HashSet<Class<?>>(
                Arrays.asList(EzspStartScanResponse.class, EzspEnergyScanResultHandler.class));
        EzspTransaction transaction = protocolHandler.sendEzspTransaction(
                new EzspMultiResponseTransaction(energyScan, EzspScanCompleteHandler.class, relatedResponses));

        EzspScanCompleteHandler scanCompleteResponse = (EzspScanCompleteHandler) transaction.getResponse();
        logger.debug(scanCompleteResponse.toString());

        List<EzspEnergyScanResultHandler> channels = new ArrayList<>();
        for (EzspFrameResponse network : transaction.getResponses()) {
            if (network instanceof EzspEnergyScanResultHandler) {
                channels.add((EzspEnergyScanResultHandler) network);
            }
        }

        lastStatus = scanCompleteResponse.getStatus();

        return channels;
    }

    /**
     * Tells the stack whether or not the normal interval between retransmissions of a retried unicast message should be
     * increased by EMBER_INDIRECT_TRANSMISSION_TIMEOUT. The interval needs to be increased when sending to a sleepy
     * node so that the message is not retransmitted until the destination has had time to wake up and poll its parent.
     * The stack will automatically extend the timeout:
     * <ul>
     * <li>For our own sleepy children.
     * <li>When an address response is received from a parent on behalf of its child.
     * <li>When an indirect transaction expiry route error is received.
     * <li>When an end device announcement is received from a sleepy node.
     * </ul>
     *
     * @param remoteEui64 the {@link IeeeAddress} of the remote node
     * @param extendedTimeout true if the node should be set with an extended timeout
     * @return the {@link ZigBeeStatus} of the request
     */
    public ZigBeeStatus setExtendedTimeout(IeeeAddress remoteEui64, boolean extendedTimeout) {
        EzspSetExtendedTimeoutRequest request = new EzspSetExtendedTimeoutRequest();
        request.setRemoteEui64(remoteEui64);
        request.setExtendedTimeout(extendedTimeout);
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspSetExtendedTimeoutResponse.class));
        EzspSetExtendedTimeoutResponse response = (EzspSetExtendedTimeoutResponse) transaction.getResponse();
        return (response == null) ? ZigBeeStatus.FAILURE : ZigBeeStatus.SUCCESS;
    }

    /**
     * Detects if the standalone bootloader is installed, and if so returns the installed version. If not return 0xffff.
     * A returned version of 0x1234 would indicate version 1.2 build 34.
     *
     * @return the bootloader version. A returned version of 0x1234 would indicate version 1.2 build 34.
     */
    public int getBootloaderVersion() {
        EzspGetStandaloneBootloaderVersionPlatMicroPhyRequest request = new EzspGetStandaloneBootloaderVersionPlatMicroPhyRequest();
        EzspTransaction transaction = protocolHandler.sendEzspTransaction(new EzspSingleResponseTransaction(request,
                EzspGetStandaloneBootloaderVersionPlatMicroPhyResponse.class));
        EzspGetStandaloneBootloaderVersionPlatMicroPhyResponse response = (EzspGetStandaloneBootloaderVersionPlatMicroPhyResponse) transaction
                .getResponse();
        return (response == null) ? 0xFFFF : response.getBootloaderVersion();
    }

    private String intArrayToString(int[] payload) {
        int length = payload.length;
        for (int cnt = 0; cnt < length; cnt++) {
            if (payload[cnt] == 0 || payload[cnt] == 255) {
                length = cnt;
            }
        }
        return new String(payload, 0, length);
    }

    private int[] getMfgToken(EzspMfgTokenId tokenId) {
        EzspGetMfgTokenRequest request = new EzspGetMfgTokenRequest();
        request.setTokenId(tokenId);
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspGetMfgTokenResponse.class));
        EzspGetMfgTokenResponse response = (EzspGetMfgTokenResponse) transaction.getResponse();
        return response.getTokenData();
    }
}
