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
 * This class represent the filter used by the {@link ZigBeeEndpoint} to identifies<br>
 * if the {@link ClusterListener} has to handle or not the {@link ClusterMessage}.<br>
 * In fact, the {@link ClusterListener#handleCluster(ZigBeeEndpoint, ClusterMessage)} must be invoked<br>
 * if and only if either<br>
 * - the {@link ClusterFilter} returned by {@link ClusterListener#getClusterFilter()} is <code>null</code>, or<br>
 * - the {@link ClusterFilter#match(ClusterMessage)} on the cluster return <code>true</code>
 *
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:francesco.furfari@isti.cnr.it">Francesco Furfari</a>
 */
public interface ClusterFilter {
    /**
     * This method is invoked to check if the {@link ZigBeeEndpoint} to check if {@link ClusterMessage}<br>
     * can be handled by the {@link ClusterListener} associated to this {@link ClusterFilter}
     *
     * @param clusterMessage the {@link ClusterMessage} to match
     * @return true if the {@link ClusterMessage} match the {@link ClusterFilter}
     */
    boolean match(ClusterMessage clusterMessage);
}
