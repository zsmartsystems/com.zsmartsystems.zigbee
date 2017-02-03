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

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ApplicationFrameworkMessageConsumer;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ApplicationFrameworkMessageProducer;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_SIMPLE_DESC_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_SIMPLE_DESC_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ThreadUtils;

/**
 * This is the implementation of a ZigBee Endpoint.
 *
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:francesco.furfari@isti.cnr.it">Francesco Furfari</a>
 * @author Chris Jackson
 */
public class ZigBeeEndpoint implements ApplicationFrameworkMessageProducer {
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ZigBeeEndpoint.class);

    /**
     * The network manager.
     */
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
     * The device version
     */
    private int deviceVersion;
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
    public ZigBeeEndpoint(final ZigBeeNode node, int profileId, int deviceId, short deviceVersion, short endPoint,
            int[] inputs, int[] outputs) {
        this.node = node;
        this.deviceTypeId = deviceId;
        this.endPointAddress = endPoint;
        this.deviceVersion = deviceVersion;
        this.inputClusters = inputs;
        this.outputClusters = outputs;
        this.profileId = profileId;

        buildEndpointId();
    }

    /**
     * Default constructor.
     */
    public ZigBeeEndpoint() {
    }

    public ZigBeeEndpoint(final ZigBeeNetworkManager zigBeeNetworkManager, final ZigBeeNode n, short ep)
            throws ZigBeeNetworkManagerException {
        if (zigBeeNetworkManager == null || n == null) {
            logger.error("Creating {} with some nulls parameters {}",
                    new Object[] { ZigBeeEndpoint.class, zigBeeNetworkManager, n, ep });
            throw new IllegalArgumentException(
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
        result.getDeviceVersion();

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

    public void setDeviceTypeId(int deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public int getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceVersion(short deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    public int getDeviceVersion() {
        return deviceVersion;
    }

    public void setEndPointAddress(short endPointAddress) {
        this.endPointAddress = endPointAddress;
    }

    public void setEndpointId(String endpointId) {
        this.endpointId = endpointId;
    }

    public String getEndpointId() {
        return endpointId;
    }

    public void setInputClusters(int[] inputClusters) {
        this.inputClusters = inputClusters;
    }

    public int[] getInputClusters() {
        return inputClusters;
    }

    public int[] getOutputClusters() {
        return outputClusters;
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

    public int getProfileId() {
        return profileId;
    }

    @Override
    public boolean addAFMessageConsumer(ApplicationFrameworkMessageConsumer consumer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeAFMessageConsumer(ApplicationFrameworkMessageConsumer consumer) {
        // TODO Auto-generated method stub
        return false;
    }

}
