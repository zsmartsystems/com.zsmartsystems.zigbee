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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.cc2531.network.ApplicationFrameworkMessageConsumer;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ApplicationFrameworkMessageProducer;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_INCOMING_MSG;

/**
 * This class register itself as
 * {@link com.zsmartsystems.zigbee.dongle.cc2531.network.ApplicationFrameworkMessageConsumer} to the given
 * {@link com.zsmartsystems.zigbee.dongle.cc2531.network.ApplicationFrameworkMessageProducer}<br>
 * and it wait for a matching {@link com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_INCOMING_MSG}. As soon as the matching
 * {@link com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_INCOMING_MSG}<br>
 * is received or the timeout expires the the object unregister itself from
 * {@link com.zsmartsystems.zigbee.dongle.cc2531.network.ApplicationFrameworkMessageProducer}<br>
 *
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:francesco.furfari@isti.cnr.it">Francesco Furfari</a>
 * @author Chris Jackson
 */
public class WaitForClusterResponse implements ApplicationFrameworkMessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(WaitForClusterResponse.class);

    private int requestTransactionId;
    private short clusterId;
    private long timeout = -1;
    private AF_INCOMING_MSG response = null;
    private ApplicationFrameworkMessageProducer producer;
    private final Thread waiter;

    /**
     * @param timeout the maximum number of milliseconds to wait for. The value -1 means unlimited waiting time.
     */
    public WaitForClusterResponse(final ApplicationFrameworkMessageProducer producer,
            final int clusterMessageTransactionId, final short id, final long timeout, final Thread thread) {

        synchronized (this) {
            this.producer = producer;
            this.producer.addAFMessageConsumer(this);
            this.timeout = timeout;
            this.waiter = thread;
            response = null;
            requestTransactionId = clusterMessageTransactionId;
            clusterId = id;
        }
    }

    /**
     * @param timeout the maximum number of milliseconds to wait for. The value -1 means unlimited waiting time.
     */
    public WaitForClusterResponse(final ApplicationFrameworkMessageProducer producer,
            final int clusterMessageTransactionId, final short id, final long timeout) {

        this(producer, clusterMessageTransactionId, id, timeout, Thread.currentThread());
    }

    @Override
    public boolean consume(AF_INCOMING_MSG msg) {
        if (msg.getClusterId() != clusterId) {
            logger.trace("AF_INCOMING_MSG ignored by waiter due to cluster mismatch {} != {}", msg.getClusterId(),
                    clusterId);
            return false;
        }
        byte responseTransactionId = (byte) msg.getData()[1];
        if (responseTransactionId != requestTransactionId) {
            logger.trace("AF_INCOMING_MSG ignored by waiter due to transaction ID mismatch {} != {}", msg.getTransId(),
                    requestTransactionId);
            return false;
        }
        logger.trace("Consuming message with ClusterId: {} TransactionId: {} for thread {}/{}", new Object[] {
                msg.getClusterId(), responseTransactionId, waiter.getName(), waiter.getClass().getName() });
        synchronized (this) {
            response = msg;
            notify();
        }
        // We wait for a cluster at the time
        producer.removeAFMessageConsumer(this);
        return true;
    }

    /**
     * Wait until an {@link com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_INCOMING_MSG} arrives from the <i>ZigBee
     * Network</i>.
     * The message has to match the specified parameters and has to arrive before a timout otherwise <code>null</code>
     * will be returned.
     *
     * @return {@link com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_INCOMING_MSG} that match the filter used with the
     *         constructor
     *         {@link #WaitForClusterResponse(byte, short)}
     */
    public AF_INCOMING_MSG getResponse() {
        final long wakeUpTime = System.currentTimeMillis() + timeout;
        AF_INCOMING_MSG msg;

        synchronized (this) {
            while (response == null && (timeout > 0 && wakeUpTime > System.currentTimeMillis())) {
                try {
                    wait(wakeUpTime - System.currentTimeMillis());
                } catch (InterruptedException ignored) {
                }
            }
            msg = response;
            response = null;
            producer.removeAFMessageConsumer(this);
        }
        return msg;
    }

}
