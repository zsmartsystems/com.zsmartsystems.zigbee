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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.cc2531.network.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_REGISTER;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_REGISTER_SRSP;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclProfileType;

/**
 * This class is a <i>singleton</i> aimed at sharing the <b>the Application Framework Layer</b>
 * status of the <b>ZigBee Base Driver</b> among all the
 * {@link com.zsmartsystems.zigbee.dongle.cc2531.network.ZigBeeEndpoint} registered by it.
 * <p>
 * In particular, this class tracks the <i>Transaction Id</i> and the <i>Active End Point</i>
 * on the hardware providing access to <i>ZigBee Network</i> (currently the <b>Texas Instrument CC2480</b>).
 *
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:francesco.furfari@isti.cnr.it">Francesco Furfari</a>
 * @author Chris Jackson
 */
public class ApplicationFrameworkLayer {

    private final static Object LOCK = new Object();
    private final static Logger logger = LoggerFactory.getLogger(ApplicationFrameworkLayer.class);
    private final static int MAX_CLUSTERS_PER_AF_REGISTER = 16;

    private static ApplicationFrameworkLayer singleton;

    private final HashMap<SenderIdentifier, Short> sender2EndPoint = new HashMap<SenderIdentifier, Short>();
    private final HashMap<Integer, List<Integer>> profile2Cluster = new HashMap<Integer, List<Integer>>();
    private final HashMap<Short, Byte> endPoint2Transaction = new HashMap<Short, Byte>();
    private final HashMap<Integer, ArrayList<ZigBeeEndpoint>> profiles = new HashMap<Integer, ArrayList<ZigBeeEndpoint>>();

    private final ZigBeeNetworkManager driver;

    private byte firstFreeEndPoint;

    class SenderIdentifier {
        private int profileId;
        private int clusterId;

        public SenderIdentifier(int profileId, int clusterId) {
            this.profileId = profileId;
            this.clusterId = clusterId;
        }

        @Override
        public int hashCode() {
            return (profileId << 16) + clusterId;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof SenderIdentifier) {
                SenderIdentifier si = (SenderIdentifier) o;
                return si.profileId == profileId && si.clusterId == clusterId;
            } else {
                return false;
            }
        }
    }

    private ApplicationFrameworkLayer(ZigBeeNetworkManager driver) {
        this.driver = driver;
        firstFreeEndPoint = 1;
    }

    public static ApplicationFrameworkLayer getAFLayer(ZigBeeNetworkManager driver) {
        synchronized (LOCK) {
            if (singleton == null) {
                singleton = new ApplicationFrameworkLayer(driver);
            } else if (singleton.driver != driver) {
                /*
                 * It means that the service implementing the driver has been changed
                 * so we have to create a new ApplicationFrameworkLayer
                 */
                singleton = new ApplicationFrameworkLayer(driver);
            }
            return singleton;
        }
    }

    public short getSendingEndpoint(int profileId, int clusterId) {
        SenderIdentifier si = new SenderIdentifier(ZclProfileType.HOME_AUTOMATION.getId(), (short) clusterId);
        logger.trace("Looking for a registered enpoint among {}", sender2EndPoint.size());
        synchronized (sender2EndPoint) {
            if (sender2EndPoint.containsKey(si)) {
                logger.trace("An endpoint is already registered for <profileId,clusterId>=<{},{}>", si.profileId,
                        si.clusterId);
                return sender2EndPoint.get(si);
            } else {
                logger.info("No endpoint registered for <profileId,clusterId>=<{},{}>", si.profileId, si.clusterId);
                final byte ep = createEndPoint(si, profileId);
                return ep;
            }
        }
    }

    public int getSenderEndpointProfileId(short endpointId, int clusterId) throws IllegalArgumentException {
        for (final Map.Entry<SenderIdentifier, Short> entry : sender2EndPoint.entrySet()) {
            if (endpointId == entry.getValue().shortValue() && clusterId == entry.getKey().clusterId) {
                return entry.getKey().profileId;
            }
        }
        throw new IllegalArgumentException("No sender EndPoint " + endpointId + " with cluster " + clusterId + ".");
    }

    // public short getSendingEndpoint(ZigBeeEndpoint endpoint, ClusterMessage input) {
    // return getSendingEndpoint(endpoint.getProfileId(), input.getId());
    // }

    /**
     * Creates default sending end point.
     */
    public void createDefaultSendingEndPoint() {
        final SenderIdentifier si = new SenderIdentifier(ZclProfileType.HOME_AUTOMATION.getId(),
                (short) ZclClusterType.BASIC.getId());
        final List<ZclClusterType> clusterSet = Arrays.asList(ZclClusterType.BASIC, ZclClusterType.POWER_CONFIGURATION,
                ZclClusterType.DEVICE_TEMPERATURE_CONFIGURATION, ZclClusterType.IDENTIFY, ZclClusterType.GROUPS,
                ZclClusterType.SCENES, ZclClusterType.ON_OFF, ZclClusterType.ON_OFF_SWITCH_CONFIGURATION,
                ZclClusterType.LEVEL_CONTROL, ZclClusterType.ALARMS, ZclClusterType.BINARY_INPUT__BASIC,
                ZclClusterType.TIME, ZclClusterType.ANALOG_INPUT__BASIC, ZclClusterType.COMMISSIONING,
                ZclClusterType.SHADE_CONFIGURATION, ZclClusterType.DOOR_LOCK,
                ZclClusterType.PUMP_CONFIGURATION_AND_CONTROL, ZclClusterType.THERMOSTAT, ZclClusterType.FAN_CONTROL,
                ZclClusterType.THERMOSTAT_USER_INTERFACE_CONFIGURATION, ZclClusterType.COLOR_CONTROL,
                ZclClusterType.PRESSURE_MEASUREMENT, ZclClusterType.ILLUMINANCE_MEASUREMENT,
                ZclClusterType.ILLUMINANCE_LEVEL_SENSING, ZclClusterType.TEMPERATURE_MEASUREMENT,
                ZclClusterType.FLOW_MEASUREMENT, ZclClusterType.RELATIVE_HUMIDITY_MEASUREMENT,
                ZclClusterType.OCCUPANCY_SENSING, ZclClusterType.IAS_ZONE, ZclClusterType.IAS_ACE,
                ZclClusterType.IAS_WD);

        int startIndex = 0;
        int endIndex = 0;

        while (endIndex < clusterSet.size()) {
            final byte endPoint = getFreeEndPoint();

            endIndex = clusterSet.size();

            if ((endIndex - startIndex) > MAX_CLUSTERS_PER_AF_REGISTER) {
                endIndex = startIndex + MAX_CLUSTERS_PER_AF_REGISTER;
            }

            final int[] clusters = new int[endIndex - startIndex];

            int index = 0;
            for (final ZclClusterType cluster : clusterSet.subList(startIndex, endIndex)) {
                clusters[index] = cluster.getId();
                index++;
            }
            startIndex = endIndex;

            final AF_REGISTER_SRSP result = driver
                    .sendAFRegister(new AF_REGISTER(endPoint, si.profileId, (short) 0, (byte) 0, new int[0], clusters));

            if (result.getStatus() != 0) {
                // Default end point creation failed probably due to end point already exists.
                logger.warn("Default end point {} creation failed with status: {} ", endPoint,
                        ZclStatus.getStatus((byte) result.getStatus()));
                return;
            }

            logger.debug("Registered default sending endpoint {} with clusters: {}", endPoint, clusters);
            registerSenderEndPoint(endPoint, si.profileId, clusters);
        }
    }

    private byte createEndPoint(SenderIdentifier si, int receiverProfileId) {
        byte endPoint = getFreeEndPoint();
        logger.trace("Registering a new endpoint for <profileId,clusterId>  <{},{}>", si.profileId, si.clusterId);

        Set<Integer> clusterSet = collectClusterForProfile(receiverProfileId);
        final int[] clusters = new int[clusterSet.size()];

        int index = 0;
        for (final Integer cluster : clusterSet) {
            clusters[index] = cluster;
            index++;
        }

        if (clusters.length > MAX_CLUSTERS_PER_AF_REGISTER) {
            logger.warn(
                    "We found too many clusters to implement for a single endpoint "
                            + "(maxium is {}), so we are filtering out the extra {}",
                    MAX_CLUSTERS_PER_AF_REGISTER, clusters);
            /*
             * Too many cluster to implement for this profile we must use the first
             * (MAX_CLUSTERS_PER_AF_REGISTER - 1) plus the cluster that we are trying to
             * create as last one
             */
            int[] values = new int[MAX_CLUSTERS_PER_AF_REGISTER];
            int i = 0;
            while (i < values.length - 1) {
                values[i] = clusters[i];
            }
            values[i] = si.clusterId;
            logger.warn("Following the list of filtered cluster that we are going to register: {} ", clusters);
        }
        AF_REGISTER_SRSP result;
        int retry = 0;
        do {
            result = driver
                    .sendAFRegister(new AF_REGISTER(endPoint, si.profileId, (short) 0, (byte) 0, new int[0], clusters));
            // FIX We should retry only when Status != 0xb8 ( Z_APS_DUPLICATE_ENTRY )
            if (result.getStatus() != 0) {
                if (retry < 1) {
                    endPoint = getFreeEndPoint();
                } else {
                    /*
                     * //TODO We should provide a workaround for the maximum number of registered EndPoint
                     * For example, with the CC2480 we could reset the dongle
                     */
                    throw new IllegalStateException("Unable create a new Endpoint. AF_REGISTER command failed with "
                            + result.getStatus() + ":" + result.getErrorMsg());
                }
            } else {
                break;
            }
        } while (true);
        final int profileId = si.profileId;

        logger.debug("Registered endpoint {} with clusters: {}", endPoint, clusters);

        registerSenderEndPoint(endPoint, profileId, clusters);
        return endPoint;
    }

    public void registerSenderEndPoint(short endPoint, int profileId, int[] clusters) {
        final List<Integer> list;
        synchronized (profile2Cluster) {
            if (profile2Cluster.containsKey(profileId)) {
                list = profile2Cluster.get(profileId);
            } else {
                list = new ArrayList<Integer>();
                profile2Cluster.put(profileId, list);
            }
        }
        synchronized (sender2EndPoint) {
            for (int i = 0; i < clusters.length; i++) {
                list.add(clusters[i]);
                SenderIdentifier adding = new SenderIdentifier(profileId, clusters[i]);
                if (sender2EndPoint.containsKey(adding)) {
                    if (sender2EndPoint.get(adding).equals(endPoint)) {
                        continue;
                    }
                    logger.warn("Overriding a valid <profileId,clusterId> endpoint with this {}", adding);
                }
                logger.debug("Adding <profileId,clusterId> <{},{}> to sender2EndPoint hashtable", adding.profileId,
                        adding.clusterId);
                sender2EndPoint.put(adding, endPoint);
            }
        }
    }

    private Set<Integer> collectClusterForProfile(int profileId) {
        final HashSet<Integer> clusters = new HashSet<Integer>();
        final Collection<ZigBeeEndpoint> endpoints = getEndpoints(profileId);
        logger.debug("Found {} devices belonging to profile {}", endpoints.size(), profileId);
        for (ZigBeeEndpoint endpoint : endpoints) {
            int[] ids;
            ids = endpoint.getInputClusters();
            logger.debug("Device {} provides the following cluster as input {}", endpoint.getEndpointId(), ids);
            for (int i = 0; i < ids.length; i++) {
                clusters.add(ids[i]);
            }
            ids = endpoint.getOutputClusters();
            logger.debug("Device {} provides the following cluster as input {}", endpoint.getEndpointId(), ids);
            for (int i = 0; i < ids.length; i++) {
                clusters.add(ids[i]);
            }
        }

        final List<Integer> implementedCluster = profile2Cluster.get(profileId);
        if (implementedCluster != null) {
            logger.debug("List of clusters of profile {} already provided by some registered endpoint {}", profileId,
                    implementedCluster);
            clusters.removeAll(implementedCluster);
        } else {
            logger.debug("No previus clusters registered on any endpoint of the dongle for the profile {}", profileId);
        }

        return clusters;
    }

    public synchronized Collection<ZigBeeEndpoint> getEndpoints(int profileId) {
        final ArrayList<ZigBeeEndpoint> result = new ArrayList<ZigBeeEndpoint>();
        final ArrayList<ZigBeeEndpoint> values = profiles.get(profileId);
        if (values == null) {
            logger.warn("No endpoints found implementing the profile={}", profileId);
        } else {
            logger.trace("We found {} implementing the profile={}", values.size(), profileId);
            result.addAll(values);
        }
        return result;
    }

    /**
     * Returns the next transaction ID for the specified endpoint.
     * <p>
     * Transaction IDs are tracked separately for each endpoint.
     *
     * @param endPoint the endpoint number
     * @return byte transaction ID
     */
    public byte getNextTransactionId(short endPoint) {
        if (!endPoint2Transaction.containsKey(endPoint)) {
            endPoint2Transaction.put(endPoint, (byte) 1);
        }
        byte value = endPoint2Transaction.get(endPoint);
        switch (value) {
            case 127: {
                endPoint2Transaction.put(endPoint, (byte) -128);
                return 127;
            }
            default: {
                endPoint2Transaction.put(endPoint, (byte) (value + 1));
                return value;
            }
        }
    }

    private byte getFreeEndPoint() {
        switch (firstFreeEndPoint) {
            case 127: {
                firstFreeEndPoint = -128;
                return 127;
            }
            case -15: {
                throw new IllegalStateException("No more end point free");
            }
            default: {
                firstFreeEndPoint += 1;
                return (byte) (firstFreeEndPoint - 1);
            }
        }
    }

}
