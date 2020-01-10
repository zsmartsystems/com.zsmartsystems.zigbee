/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class to implement the Telegesis command <b>Set Output Clusters</b>.
 * <p>
 * Sets the list of output clusters supported by endpoint 2. Up to 12 clusters can be set
 * <p>
 * This class provides methods for processing Telegesis AT API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class TelegesisSetOutputClustersCommand extends TelegesisFrame implements TelegesisCommand {
    /**
     * Command field
     */
    private List<Integer> clusterList = new ArrayList<Integer>();

    /**
     *
     * @param cluster list the clusterList to add to the {@link Set} as {@link Integer}
     */
    public void addClusterList(Integer clusterList) {
        this.clusterList.add(clusterList);
    }

    /**
     *
     * @param clusterList the clusterList to remove to the {@link Set} as {@link Integer}
     */
    public void removeClusterList(Integer clusterList) {
        this.clusterList.remove(clusterList);
    }

    /**
     *
     * @param clusterList the clusterList to set to the {@link Set} as {@link Integer}
     */
    public void setClusterList(Collection<Integer> clusterList) {
        this.clusterList.addAll(clusterList);
    }

    @Override
    public int[] serialize() {
        // Serialize the command fields
        serializeCommand("ATS4C=");
        boolean firstClusterList = true;
        for (Integer value : clusterList) {
            if (!firstClusterList) {
                serializeDelimiter(',');
            }
            firstClusterList = false;
            serializeInt16(value);
        }

        return getPayload();
    }

    @Override
    public boolean deserialize(int[] data) {
        // Handle standard status responses (ie. OK / ERROR)
        if (handleIncomingStatus(data)) {
            return true;
        }

        initialiseDeserializer(data);


        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(213);
        // First present the command parameters...
        // Then the responses later if they are available
        builder.append("TelegesisSetOutputClustersCommand [clusterList=");
        builder.append(clusterList);
        if (status != null) {
            builder.append(", status=");
            builder.append(status);
        }
        builder.append(']');
        return builder.toString();
    }
}
