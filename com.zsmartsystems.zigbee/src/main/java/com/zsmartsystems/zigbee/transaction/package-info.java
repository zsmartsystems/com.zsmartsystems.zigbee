/**
 * This package supports ZigBee transactions within the framework.
 * <p>
 * The diagram below shows the general flow from the application sending a transaction to the
 * {@link ZigBeeTransactionManager}, into the {@link ZigBeeTransactionQueue}, and the interaction with the
 * {@link ZigBeeTransportTransmit} and {@link ZigBeeTransportRecieve} classes.
 * <p>
 * <img src="./doc-files/ZigBeeTransactionOverview.png" width="100%">
 * <p>
 * The {@link ZigBeeTransactionManager} manages the overall transaction process. It acts as the central configuration
 * interface for configuring the transaction subsystem, and handles sending and receiving of commands. It gets
 * transactions from the different queues, selecting a queue randomly, and enforces an overall maximum number of
 * transactions that can be outstanding.
 * <p>
 * The {@link ZigBeeTransactionQueue} handles the transactions for a single queue. A queue is established for each node,
 * along with broadcasts and multicast message queues. The {@link ZigBeeTransactionQueue} enforces the maximum number of
 * outstanding messages for this queue, and ensures a minimum delay between each subsequent transaction release. The
 * {@link ZigBeeTransactionQueue} is notified of all completed transactions so it can add failed transactions back in
 * the queue if retries are required.
 * <p>
 * The {@link ZigBeeTransactionProfile} is used to configure the queues.
 */

package com.zsmartsystems.zigbee.transaction;
