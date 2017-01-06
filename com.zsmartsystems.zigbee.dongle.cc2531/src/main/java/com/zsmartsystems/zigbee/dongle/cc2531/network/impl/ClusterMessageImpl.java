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

import com.zsmartsystems.zigbee.dongle.cc2531.network.ClusterMessage;

/**
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:francesco.furfari@isti.cnr.it">Francesco Furfari</a>
 * @author Chris Jackson
 */
public class ClusterMessageImpl implements ClusterMessage {

    private final int[] msg;
    private final short id;

    public ClusterMessageImpl(int[] msg, short id) {
        this.msg = msg;
        this.id = id;
    }

    @Override
    public int[] getClusterMsg() {
        return msg;
    }

    @Override
    public short getId() {
        return id;
    }

    @Override
    public int getTransactionId() {
        return msg[3];
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("[ ");
        for (int b : msg) {
            builder.append(String.format("%02X ", b));
        }
        builder.append("]");
        return builder.toString();
    }

}
