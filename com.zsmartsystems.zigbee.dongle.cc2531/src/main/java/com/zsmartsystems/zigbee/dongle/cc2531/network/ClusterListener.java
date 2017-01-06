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

package com.zsmartsystems.zigbee.dongle.cc2531.network;

/**
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:francesco.furfari@isti.cnr.it">Francesco Furfari</a>
 */
public interface ClusterListener {
    /**
     * Set the {@link ClusterListener} of this ClusterListener.<br>
     * A <code>null</code> values means no filtering
     *
     * @param filter the {@link ClusterListener} to associates to this ClusterListener.<br>
     *            <code>null</code> to disable the filtering
     * @since 0.4.0
     */
    void setClusterFilter(ClusterFilter filter);

    /**
     * Return the {@link ClusterListener} associated to this ClusterListener.<br>
     * A <code>null</code> values means no filtering
     *
     * @return the {@link ClusterListener} associated to this ClusterListener
     * @since 0.4.0
     */
    ClusterFilter getClusterFilter();

    /**
     * The callback invoked by the ZigBee Base Driver to announce for a new {@link ClusterMessage}
     *
     * @param endpoint reference to the {@link ZigBeeEndpoint} receiving the {@link ClusterMessage}
     * @param clusterMessage reference to the received {@link ClusterMessage}
     */
    void handleCluster(ZigBeeEndpoint endpoint, ClusterMessage clusterMessage);
}
