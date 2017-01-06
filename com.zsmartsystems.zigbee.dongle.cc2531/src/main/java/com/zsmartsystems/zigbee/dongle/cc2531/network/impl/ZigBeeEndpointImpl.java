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
import java.util.Arrays;
import java.util.HashSet;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.cc2531.network.ApplicationFrameworkMessageConsumer;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ApplicationFrameworkMessageListener;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ApplicationFrameworkMessageProducer;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ClusterFilter;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ClusterListener;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ClusterMessage;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ZigBeeNode;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ResponseStatus;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_DATA_CONFIRM;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_DATA_REQUEST;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_INCOMING_MSG;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_BIND_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_BIND_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_SIMPLE_DESC_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_SIMPLE_DESC_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_UNBIND_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_UNBIND_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.IEEEAddress;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.Integers;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ThreadUtils;

/**
 * This is the implementation of a ZigBee Endpoint.
 *
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:francesco.furfari@isti.cnr.it">Francesco Furfari</a>
 * @author Chris Jackson
 */
public class ZigBeeEndpointImpl
        implements ZigBeeEndpoint, ApplicationFrameworkMessageListener, ApplicationFrameworkMessageProducer {
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ZigBeeEndpointImpl.class);

    /**
     * The cluster message response timeout value millis.
     */
    private static final long CLUSTER_MESSAGE_RESPONSE_TIME_MILLIS = 8000;

    /**
     * The network manager.
     */
    @JsonIgnore
    private ZigBeeNetworkManager networkManager;

    /**
     * The ZigBee node this EndPoint belongs to.
     */
    private ZigBeeNode node;

    /**
     * The device ID.
     */
    private int deviceTypeId;
    /**
     * The profile ID.
     */
    private int profileId;
    /**
     * The device version.
     */
    private short deviceVersion;
    /**
     * The end point.
     */
    private short endPointAddress;
    /**
     * Input clusters.
     */
    private int[] inputClusters;
    /**
     * Output clusters.
     */
    private int[] outputClusters;

    /**
     * Aggregate EndPoint ID.
     */
    private String endpointId = null;

    /**
     * The bound clusters.
     */
    @JsonIgnore
    private final HashSet<Integer> boundCluster = new HashSet<Integer>();

    /**
     * The clusters.
     */
    @JsonIgnore
    private final HashSet<ClusterListener> listeners = new HashSet<ClusterListener>();

    /**
     * The application framework message consumers.
     */
    @JsonIgnore
    private final HashSet<ApplicationFrameworkMessageConsumer> consumers = new HashSet<ApplicationFrameworkMessageConsumer>();

    /**
     * Constructor which sets Endpoint base information.
     *
     * @param node the node
     * @param profileId the profile ID
     * @param deviceId the device ID
     * @param deviceVersion the device version
     * @param endPoint the endpoint
     * @param inputs the input clusters
     * @param outputs the output clusters
     */
    public ZigBeeEndpointImpl(final ZigBeeNode node, int profileId, int deviceId, short deviceVersion, short endPoint,
            int[] inputs, int[] outputs) {
        this.node = node;
        this.deviceTypeId = deviceId;
        this.deviceVersion = deviceVersion;
        this.endPointAddress = endPoint;
        this.inputClusters = inputs;
        this.outputClusters = outputs;
        this.profileId = profileId;

        buildEndpointId();
    }

    /**
     * Default constructor.
     */
    public ZigBeeEndpointImpl() {
    }

    public ZigBeeEndpointImpl(final ZigBeeNetworkManager zigBeeNetworkManager, final ZigBeeNode n, short ep)
            throws ZigBeeNetworkManagerException {
        if (zigBeeNetworkManager == null || n == null) {
            logger.error("Creating {} with some nulls parameters {}",
                    new Object[] { ZigBeeEndpoint.class, zigBeeNetworkManager, n, ep });
            throw new NullPointerException(
                    "Cannot create a device with a null ZigBeeNetworkManager or a null ZigBeeNode");
        }
        networkManager = zigBeeNetworkManager;
        endPointAddress = ep;

        final ZDO_SIMPLE_DESC_RSP result = doRetrieveSimpleDescription(n);
        short[] ins = result.getInputClustersList();
        inputClusters = new int[ins.length];
        for (int i = 0; i < ins.length; i++) {
            inputClusters[i] = ins[i];
        }
        Arrays.sort(inputClusters);
        short[] outs = result.getOutputClustersList();
        outputClusters = new int[outs.length];
        for (int i = 0; i < outs.length; i++) {
            outputClusters[i] = outs[i];
        }
        Arrays.sort(outputClusters);

        deviceTypeId = result.getDeviceId() & 0xFFFF;
        profileId = result.getProfileId() & 0xFFFF;
        deviceVersion = result.getDeviceVersion();

        node = n;

        buildEndpointId();
    }

    private void buildEndpointId() {
        final StringBuffer sb_uuid = new StringBuffer().append(node.getIeeeAddress()).append("/")
                .append(endPointAddress);
        endpointId = sb_uuid.toString();
    }

    /**
     * Sets node.
     *
     * @param node the node
     */
    public void setNode(ZigBeeNode node) {
        this.node = node;
    }

    private ZDO_SIMPLE_DESC_RSP doRetrieveSimpleDescription(ZigBeeNode n) throws ZigBeeNetworkManagerException {
        // TODO Move into ZigBeeNetworkManager?!?!?
        final int nwk = n.getNetworkAddress();
        int i = 0;
        ZDO_SIMPLE_DESC_RSP result = null;

        while (i < 3) {
            logger.debug("Inspecting node {} / end point {}.", n, endPointAddress);

            result = networkManager
                    .sendZDOSimpleDescriptionRequest(new ZDO_SIMPLE_DESC_REQ((short) nwk, endPointAddress));
            if (result == null) {
                // long waiting = (long) (Math.random() * (double)
                // Activator.getCurrentConfiguration().getMessageRetryDelay())
                final long waiting = 1000;
                ThreadUtils.waitNonPreemptive(waiting);
                i++;
                logger.debug("Inspecting ZigBee EndPoint <#{},{}> failed at attempt {}. "
                        + "Waiting for {}ms before retrying", nwk, endPointAddress, i, waiting);

            } else {
                break;
            }
        }

        if (result == null) {
            logger.error("Unable to receive a ZDO_SIMPLE_DESC_RSP for endpoint {} on node {}", nwk, endPointAddress);
            throw new ZigBeeNetworkManagerException("Unable to receive a ZDO_SIMPLE_DESC_RSP from endpoint");
        }

        return result;
    }

    @Override
    public int getDeviceTypeId() {
        return deviceTypeId;
    }

    @Override
    public short getDeviceVersion() {
        return deviceVersion;
    }

    @Override
    public String getEndpointId() {
        return endpointId;
    }

    @Override
    @JsonIgnore
    public int getNetworkAddress() {
        return node.getNetworkAddress();
    }

    @Override
    @JsonIgnore
    public String getIeeeAddress() {
        return node.getIeeeAddress();
    }

    @Override
    public short getEndPointAddress() {
        return endPointAddress;
    }

    @Override
    public int[] getInputClusters() {
        return inputClusters;
    }

    @Override
    public int[] getOutputClusters() {
        return outputClusters;
    }

    @Override
    public int getProfileId() {
        return profileId;
    }

    @Override
    public ZigBeeNode getNode() {
        return node;
    }

    public void setDeviceTypeId(int deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public void setDeviceVersion(short deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    public void setEndPointAddress(short endPointAddress) {
        this.endPointAddress = endPointAddress;
    }

    public void setEndpointId(String endpointId) {
        this.endpointId = endpointId;
    }

    public void setInputClusters(int[] inputClusters) {
        this.inputClusters = inputClusters;
    }

    public void setNetworkManager(ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    public void setOutputClusters(int[] outputClusters) {
        this.outputClusters = outputClusters;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    @Override
    public void send(ClusterMessage input) throws ZigBeeNetworkManagerException {
        synchronized (networkManager) {
            final ApplicationFrameworkLayer af = ApplicationFrameworkLayer.getAFLayer(networkManager);
            final short sender = af.getSendingEndpoint(this, input);
            final byte transaction = af.getNextTransactionId(sender);
            final int[] msg = input.getClusterMsg();

            // TODO Create radius and options according to the current configuration
            AF_DATA_CONFIRM response = networkManager
                    .sendAFDataRequest(new AF_DATA_REQUEST((short) node.getNetworkAddress(), (byte) endPointAddress,
                            sender, input.getId(), transaction, (byte) 0 /* options */, (byte) 0 /* radius */, msg));

            if (response == null) {
                throw new ZigBeeNetworkManagerException(
                        "Unable to send cluster on the ZigBee network due to general error");
            } else if (response.getStatus() != 0) {
                throw new ZigBeeNetworkManagerException(
                        "Unable to send cluster on the ZigBee network:" + response.getErrorMsg());
            }
        }
    }

    @Override
    public ClusterMessage invoke(ClusterMessage input) throws ZigBeeNetworkManagerException {
        synchronized (networkManager) {
            final ApplicationFrameworkLayer af = ApplicationFrameworkLayer.getAFLayer(networkManager);
            final short sender = af.getSendingEndpoint(this, input);

            final int clusterMessageTransactionId = input.getTransactionId();
            final byte afTransactionId = af.getNextTransactionId(sender);
            final int[] msg = input.getClusterMsg();

            addAFMessageListener();

            // Registering the waiter before sending the message, so that they will be captured
            WaitForClusterResponse waiter = new WaitForClusterResponse(this, clusterMessageTransactionId, input.getId(),
                    CLUSTER_MESSAGE_RESPONSE_TIME_MILLIS);

            logger.trace("---> SENDING cluster message with af transaction ID: " + afTransactionId
                    + " and  cluster message transaction ID: " + +clusterMessageTransactionId + " TO: "
                    + node.getNetworkAddress() + " with" + " byte 0 "
                    + Integers.getByteAsInteger(node.getNetworkAddress(), 0) + " byte 1 "
                    + Integers.getByteAsInteger(node.getNetworkAddress(), 1) + " byte 2 "
                    + Integers.getByteAsInteger(node.getNetworkAddress(), 2) + " byte 3 "
                    + Integers.getByteAsInteger(node.getNetworkAddress(), 3) + " from end point: " + sender
                    + " to end point: " + endPointAddress);
            // TODO Create radius and options according to the current configuration
            AF_DATA_CONFIRM response = networkManager
                    .sendAFDataRequest(new AF_DATA_REQUEST(node.getNetworkAddress(), endPointAddress, sender,
                            input.getId(), afTransactionId, (byte) (0) /* options */, (byte) 0 /* radius */, msg));

            if (response == null) {
                removeAFMessageListener();
                throw new ZigBeeNetworkManagerException(
                        "Unable to send cluster on the ZigBee network due to general error - is the device sleeping?");
            } else if (response.getStatus() != 0) {
                removeAFMessageListener();
                final ResponseStatus responseStatus = ResponseStatus.getStatus(response.getStatus());

                /*
                 * if (responseStatus == ResponseStatus.Z_MAC_NO_ACK) {
                 * logger.info("Removing unresponsive device: " + getIeeeAddress());
                 * ApplicationFrameworkLayer.getAFLayer(networkManager).getZigBeeNetwork().removeNode(this.getNode());
                 * }
                 */

                throw new ZigBeeNetworkManagerException("Unable to send cluster on the ZigBee network due to: "
                        + responseStatus + " (" + response.getErrorMsg() + ")");
            } else {
                // FIX Can't be singleton because the invoke method can be invoked by multiple-thread
                AF_INCOMING_MSG incoming = waiter.getResponse();
                removeAFMessageListener();
                if (incoming == null) {
                    throw new ZigBeeBasedriverTimeOutException();
                }
                ClusterMessage result = new ClusterMessageImpl(incoming.getData(), incoming.getClusterId());
                return result;
            }
        }
    }

    @Override
    public boolean providesInputCluster(int id) {
        for (int i = 0; i < inputClusters.length; i++) {
            if (inputClusters[i] == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean providesOutputCluster(int id) {
        for (int i = 0; i < outputClusters.length; i++) {
            if (outputClusters[i] == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean bindTo(ZigBeeEndpoint endpoint, int clusterId) throws ZigBeeNetworkManagerException {
        logger.info("Binding from endpoint {} to {} for cluster {}",
                new Object[] { getEndpointId(), endpoint.getEndpointId(), Integer.valueOf(clusterId) });

        final ZDO_BIND_RSP response = networkManager.sendZDOBind(new ZDO_BIND_REQ((short) getNode().getNetworkAddress(),
                (short) clusterId, IEEEAddress.fromColonNotation(getNode().getIeeeAddress()), (byte) endPointAddress,
                IEEEAddress.fromColonNotation(endpoint.getNode().getIeeeAddress()),
                (byte) endpoint.getEndPointAddress()));
        if (response == null || response.Status != 0) {
            logger.warn("ZDO_BIND_REQ failed due to {}, unable to bind from endpoint {} to {} for cluster {}",
                    new Object[] { ResponseStatus.getStatus(response.Status), getEndpointId(), endpoint.getEndpointId(),
                            new Integer(clusterId) });
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unbindFrom(ZigBeeEndpoint endpoint, int clusterId) throws ZigBeeNetworkManagerException {
        logger.info("Un-binding from endpoint {} to {} for cluster {}",
                new Object[] { getEndpointId(), endpoint.getEndpointId(), Integer.valueOf(clusterId) });

        final ZDO_UNBIND_RSP response = networkManager
                .sendZDOUnbind(new ZDO_UNBIND_REQ((short) getNode().getNetworkAddress(), (short) clusterId,
                        IEEEAddress.fromColonNotation(getNode().getIeeeAddress()), (byte) endPointAddress,
                        IEEEAddress.fromColonNotation(endpoint.getNode().getIeeeAddress()),
                        (byte) endpoint.getEndPointAddress()));
        if (response == null || response.Status != 0) {
            logger.warn("ZDO_BIND_REQ failed, unable to un-bind from endpoint {} to {} for cluster {}",
                    new Object[] { getEndpointId(), endpoint.getEndpointId(), new Integer(clusterId) });
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean bindToLocal(int clusterId) throws ZigBeeNetworkManagerException {
        if (boundCluster.contains(clusterId)) {
            logger.debug("Cluster already bound");
            return true;
        }

        short dstEP = ApplicationFrameworkLayer.getAFLayer(networkManager).getSendingEndpoint(this.getProfileId(),
                clusterId);

        logger.info("Binding from endpoint {} to {} for cluster {}", new Object[] { getEndpointId(),
                IEEEAddress.toString(networkManager.getIeeeAddress()) + "/" + dstEP, Integer.valueOf(clusterId) });

        final ZDO_BIND_RSP response = networkManager.sendZDOBind(new ZDO_BIND_REQ((short) getNode().getNetworkAddress(),
                (short) clusterId, IEEEAddress.fromColonNotation(getNode().getIeeeAddress()), (byte) endPointAddress,
                networkManager.getIeeeAddress(), (byte) dstEP));
        if (response == null || response.Status != 0) {
            logger.warn("ZDO_BIND_REQ failed due to {}, unable to bind from endpoint {} to {} for cluster {}",
                    new Object[] { response == null ? "Timeout" : ResponseStatus.getStatus(response.Status),
                            getEndpointId(), IEEEAddress.toString(networkManager.getIeeeAddress()) + "/" + dstEP,
                            new Integer(clusterId) });
            return false;
        }
        boundCluster.add(clusterId);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unbindFromLocal(int clusterId) throws ZigBeeNetworkManagerException {
        logger.info("Unbinding from cluster {} of endpoint {}", clusterId, getEndpointId());
        if (!boundCluster.contains(clusterId)) {
            logger.warn("Cluster already unbound");
            return true;
        }

        short dstEP = ApplicationFrameworkLayer.getAFLayer(networkManager).getSendingEndpoint(this.getProfileId(),
                clusterId);

        final ZDO_UNBIND_RSP response = networkManager
                .sendZDOUnbind(new ZDO_UNBIND_REQ((short) getNode().getNetworkAddress(), (short) clusterId,
                        IEEEAddress.fromColonNotation(getNode().getIeeeAddress()), (byte) endPointAddress,
                        networkManager.getIeeeAddress(), (byte) dstEP));
        if (response == null || response.Status != 0) {
            logger.warn("ZDO_BIND_REQ failed, unable to unbind");
            return false;
        }
        boundCluster.remove(clusterId);
        return true;
    }

    private void addAFMessageListener() {
        if (listeners.isEmpty() && consumers.isEmpty()) {
            logger.trace("Registered {} as {}", this, ApplicationFrameworkMessageListener.class.getName());
            networkManager.addAFMessageListener(this);
        } else {
            logger.trace("Skipped registration of {} as {}", this, ApplicationFrameworkMessageListener.class.getName());
            logger.trace("Skipped registration due to: listeners.isEmpty() = {} or consumers.size() = {}",
                    listeners.isEmpty(), consumers.size());
        }
    }

    private void removeAFMessageListener() {
        if (listeners.isEmpty() && consumers.isEmpty()) {
            logger.trace("Unregistered {} as {}", this, ApplicationFrameworkMessageListener.class.getName());
            networkManager.removeAFMessageListener(this);
        } else {
            logger.trace("Skipped unregistration of {} as {}", this,
                    ApplicationFrameworkMessageListener.class.getName());
            logger.trace("Skipped unregistration due to: listeners.isEmpty() = {}  or consumers.size() = {}",
                    listeners.isEmpty(), consumers.size());
        }
    }

    @Override
    public boolean addClusterListener(ClusterListener listener) {
        addAFMessageListener();

        return listeners.add(listener);
    }

    @Override
    public boolean removeClusterListener(ClusterListener listener) {
        boolean result = listeners.remove(listener);
        removeAFMessageListener();
        return result;
    }

    private void notifyClusterListener(ClusterMessage c) {
        ArrayList<ClusterListener> localCopy;
        synchronized (listeners) {
            localCopy = new ArrayList<ClusterListener>(listeners);
        }
        if (!localCopy.isEmpty()) {
            logger.trace("Notifying {} ClusterListener of {}", localCopy.size(), c.toString());

            for (ClusterListener listner : localCopy) {
                try {
                    final ClusterFilter filter = listner.getClusterFilter();
                    if (filter == null) {
                        listner.handleCluster(this, c);
                    } else if (filter.match(c)) {
                        listner.handleCluster(this, c);
                    }
                } catch (Throwable t) {
                    logger.error("Error during dispatching of Cluster <{},{}>", c.getId(), c.getClusterMsg());
                    logger.error("Error caused by:", t);
                }
            }
        }
    }

    @Override
    public boolean notify(AF_INCOMING_MSG msg) {
        if (msg.isError()) {
            return false;
        }
        logger.trace("AF_INCOMING_MSG arrived for {} message is {}", endpointId, msg);
        ArrayList<ApplicationFrameworkMessageConsumer> localConsumers;
        synchronized (consumers) {
            localConsumers = new ArrayList<ApplicationFrameworkMessageConsumer>(consumers);
        }
        logger.trace("Notifying {} ApplicationFrameworkMessageConsumer", localConsumers.size());
        for (ApplicationFrameworkMessageConsumer consumer : localConsumers) {
            if (consumer.consume(msg)) {
                logger.trace("AF_INCOMING_MSG consumed by {}", consumer.getClass().getName());
                return true;
            } else {
                logger.trace("AF_INCOMING_MSG ignored by {}", consumer.getClass().getName());
                return false;
            }
        }

        if (msg.getSrcAddr() != node.getNetworkAddress()) {
            return false;
        }
        if (msg.getSrcEndpoint() != endPointAddress) {
            return false;
        }
        logger.trace("AF_INCOMING_MSG consumed by {}", endpointId);
        notifyClusterListener(new ClusterMessageImpl(msg.getData(), msg.getClusterId()));
        return true;
    }

    @Override
    public boolean addAFMessageConsumer(ApplicationFrameworkMessageConsumer consumer) {
        synchronized (consumers) {
            return consumers.add(consumer);
        }
    }

    @Override
    public boolean removeAFMessageConsumer(ApplicationFrameworkMessageConsumer consumer) {
        synchronized (consumers) {
            return consumers.remove(consumer);
        }
    }

    /**
     * @since 0.4.0
     */
    @Override
    public String toString() {
        return getEndpointId();
    }

}
