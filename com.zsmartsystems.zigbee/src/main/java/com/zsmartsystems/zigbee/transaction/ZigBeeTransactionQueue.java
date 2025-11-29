/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transaction;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransaction.TransactionState;

/**
 * This class manages a queue of transactions. The queue provides a number of options when managing the transactions in
 * its queue -:
 * <ul>
 * <li>Priority: Each transaction may have an assigned priority. Higher priority transactions will be sent before lower
 * priority to provide a responsive system.
 * <li>Maximum number of transactions outstanding: This allows the maximum number of outstanding transactions sent from
 * the queue (and normally therefore, to a single device) to be limited. This may be used to manage broadcasts, where
 * only a limited number of broadcasts should be outstanding within a 7.6 second period, or it may be used to avoid
 * overloading a device with limited buffering.
 * <li>Duplicates: Duplicate commands may be removed, leaving only the most recent command in the queue.
 * <li>Inter-transaction delay: This may be used to set the minimum delay between any two transactions being sent. This
 * may be required to manage slow devices.
 * </ul>
 * <p>
 * Normally a queue will be established for each device, or possibly for each type of transaction (eg broadcasts).
 * <p>
 * The manager provides support for retransmission of failed transactions.
 * <p>
 * A {@link ZigBeeTransaction} is added to the queue via the {@link #addToQueue(ZigBeeTransaction)} method. At this
 * point, the duplicate check is performed so as not to add the transaction if it is the same as one that is already in
 * the queue.
 * <p>
 * A {@link ZigBeeTransaction} is removed from the queue via the {@link #getTransaction()} method. This will be
 * periodically called by the {@link TransactionManager} when it is ready to send a transaction (accounting for other
 * queues, and other constraints within the system). During this call, the queue manager will ensure all other
 * constraints have been respected (maximum outstanding transactions, time between transactions, ...) and will return
 * null if it is not able to send a transaction.
 * <p>
 * The queue will monitor the state of each transaction it releases so that it can handle retries if needed. This is
 * managed through the {@link #transactionComplete(ZigBeeTransaction, boolean)} callback from the
 * {@link ZigBeeTransactionManager}.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeTransactionQueue {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeTransactionQueue.class);

    private final Deque<ZigBeeTransaction> queue = new ConcurrentLinkedDeque<>();

    /**
     * The time at which the queue may release the next frame. This is used by the inter-transaction delay pacing
     * algorithm.
     */
    private long nextReleaseTime = 0;

    /**
     * Queue name for logging
     */
    private final String queueName;

    /**
     * The type of queue - true if the device associated with the queue is a sleepy device
     */
    private boolean sleepy = false;

    /**
     * The number of transactions currently outstanding from this queue
     */
    private final Set<ZigBeeTransaction> outstandingTransactions = new HashSet<>();

    /**
     * The {@link IeeeAddress} of the device for which this queue has transactions ({@code null} in case of a default,
     * broadcast, or multicast queue)
     */
    private final IeeeAddress deviceIeeeAdress;

    /**
     * The profile for this queue
     */
    private ZigBeeTransactionProfile profile = new ZigBeeTransactionProfile();

    /**
     * Flag to remember if we have shut down this queue
     */
    private boolean isShutdown = false;

    /**
     * Constructs a {@link ZigBeeTransactionQueue}
     *
     * @param queueName a queue name - used for logging to differentiate multiple queues
     */
    protected ZigBeeTransactionQueue(String queueName) {
        this.queueName = queueName;
        this.deviceIeeeAdress = null;
    }

    /**
     * Constructs a {@link ZigBeeTransactionQueue}
     *
     * @param queueName a queue name - used for logging to differentiate multiple queues
     * @param deviceIeeeAddress - the {@link IEEEAddress} of the device for which this queue holds transactions
     */
    protected ZigBeeTransactionQueue(String queueName, IeeeAddress deviceIeeeAddress) {
        this.queueName = queueName;
        this.deviceIeeeAdress = deviceIeeeAddress;
    }

    /**
     * Shuts down the queue and releases all resources
     */
    protected void shutdown() {
        logger.debug("{}: Queue shutdown", queueName);
        isShutdown = true;

        // Cancel any queued transactions
        while (!queue.isEmpty()) {
            ZigBeeTransaction transaction = queue.poll();
            if (transaction == null) {
                break;
            }
            transaction.cancel();
        }

        // We don't cancel outstanding transactions here as the transaction manager is doing that
        outstandingTransactions.clear();
        queue.clear();
    }

    /**
     * Set the {@link ZigBeeTransactionProfile} for this queue
     *
     * @param profile the {@link ZigBeeTransactionProfile} for this queue
     */
    public void setProfile(ZigBeeTransactionProfile profile) {
        this.profile = profile;
        logger.debug("{}: Set profile to {}", queueName, profile);
    }

    /**
     * Get the {@link ZigBeeTransactionProfile} for this queue
     *
     * return the {@link ZigBeeTransactionProfile} for this queue
     */
    public ZigBeeTransactionProfile getProfile() {
        return profile;
    }

    /**
     * Sets the queue for a sleepy or non-sleepy queue
     *
     * @param sleepy true if this is a queue for a sleepy device
     * @return the previous state of the sleepy flag
     */
    public boolean setSleepy(boolean sleepy) {
        if (this.sleepy == sleepy) {
            return sleepy;
        }

        logger.debug("{}: Updated sleepy state from {} to {}", queueName, this.sleepy, sleepy);
        this.sleepy = sleepy;

        return !this.sleepy;
    }

    /**
     * Gets the type of queue - either a sleepy or non-sleepy queue
     *
     * @return true if this is a queue for a sleepy device
     */
    public boolean isSleepy() {
        return sleepy;
    }

    /**
     * Gets the {@link IeeeAddress} of the device for which this queue has transactions
     *
     * @return the IEEE address or {@code null} if this queue is a default, broadcast or multicast queue
     */
    public IeeeAddress getDeviceIeeeAdress() {
        return deviceIeeeAdress;
    }

    /**
     * Adds a {@link ZigBeeTransaction} to the queue, returning a {@link CommandResult} Future that will be fulfilled
     * once the transaction completes.
     * Once the queue has been shutdown with {@link #shutdown()} no further transactions will be accepted and this
     * method will return null.
     *
     * @param transaction {@link ZigBeeTransaction}
     * @return the Future {@link CommandResult} for the transaction. Will return null if the queue has been shut down
     */
    protected Future<CommandResult> addToQueue(ZigBeeTransaction transaction) {
        if (isShutdown) {
            return null;
        }
        if (transaction.getFuture() == null) {
            transaction.setFuture(new ZigBeeTransactionFuture(transaction));
        }

        transaction.setIeeeAddress(deviceIeeeAdress);

        // Is this the first time this transaction has been added to the queue or is this a retry
        if (transaction.getSendCnt() == 0) {
            // Set the time the transaction is queued - for statistics and monitoring
            transaction.setQueueTime();
            // First time sending this transaction - add to the end of the queue
            queue.add(transaction);
        } else {
            // This is a retry - prioritise this transaction and add to the head of the queue
            transaction.resetTransaction();
            queue.push(transaction);
        }
        logger.debug("{}: Added transaction to queue, len={}, transaction={}", queueName, queue.size(), transaction);

        return transaction.getFuture();
    }

    /**
     * Get a transaction from the queue. This method will return the next transaction required to be sent, based on
     * priority and any other internal selection mechanism. If no transaction is available to be sent, or if the queue
     * manager does not want to send a transaction at this time (e.g. due to inter-transaction delays), it will return
     * null.
     * <p>
     * It is assumed that the transaction is sent immediately as the queue will start any timers relating to transaction
     * delays at this point.
     *
     * @return the {@link ZigBeeTransaction} to send, or null if no transaction is available.
     */
    protected ZigBeeTransaction getTransaction() {
        if (queue.isEmpty() || nextReleaseTime > System.currentTimeMillis()
                || outstandingTransactions.size() >= profile.getMaxOutstandingTransactions() || isShutdown) {
            return null;
        }
        ZigBeeTransaction transaction = queue.poll();
        outstandingTransactions.add(transaction);
        nextReleaseTime = System.currentTimeMillis() + profile.getInterTransactionDelay();

        return transaction;
    }

    /**
     * Returns true if there are currently no transactions waiting to be sent
     *
     * @return true if the queue is empty
     */
    protected boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Gets the number of transactions waiting to be sent
     *
     * @return the number of transactions currently in the queue
     */
    protected int size() {
        return queue.size();
    }

    /**
     * Gets the number of milliseconds before the queue can release the next transaction.
     *
     * @return number of milliseconds before the next transaction can be released
     */
    protected long getNextReleaseTime() {
        long delay = nextReleaseTime - System.currentTimeMillis();
        return delay < 0 ? 0 : delay;
    }

    /**
     * Notification that a previously released transaction has been completed.
     * <p>
     * This is called from the transaction manager when the transaction completes (either successfully, or
     * unsuccessfully).
     * <p>
     * If the transaction has {@link TransactionState#FAILED}, it will be requeued if it has not exceeded the allowable
     * number of retries.
     * <p>
     * If the transaction has {@link TransactionState#CANCELLED}, it will not be requeued.
     *
     * @param transaction the {@link ZigBeeTransaction} that is complete
     * @param state the {@link TransactionState} of the transaction on completion
     */
    protected void transactionComplete(ZigBeeTransaction transaction, TransactionState state) {
        if (isShutdown) {
            transaction.cancel();
            return;
        }

        if (!outstandingTransactions.remove(transaction)) {
            logger.debug("{}: transactionComplete but not outstanding, state={}, outstanding={}", queueName, state,
                    outstandingTransactions.size());
            transaction.cancel();
            return;
        }
        logger.debug("{}: transactionComplete, state={}, outstanding={}", queueName, state,
                outstandingTransactions.size());

        if (state == TransactionState.FAILED) {
            if (transaction.getSendCnt() < profile.getMaxRetries()) {
                // Transaction failed - requeue
                addToQueue(transaction);
            } else {
                logger.debug("{}: transactionComplete exceeded max retries {}", queueName, transaction.getSendCnt());
                transaction.cancel();
            }
        }
    }

    /**
     * Rewrites all transactions in the queue to have the network address of the passed node as destination address.
     *
     * @param newAddress the new address where the transactions should be send to
     */
    protected void rewriteDestinationAddresses(Integer newAddress) {
        LinkedList<ZigBeeTransaction> transactions = new LinkedList<>();

        synchronized (queue) {
            ZigBeeTransaction transaction = null;
            while ((transaction = queue.poll()) != null) {
                if (!Objects.equals(transaction.getDestinationAddress().getAddress(), newAddress)) {
                    logger.debug("Rewriting transaction destination address from {} to {} in transaction={}",
                            transaction.getDestinationAddress().getAddress(), newAddress, transaction);
                    transaction.getDestinationAddress().setAddress(newAddress);
                }
                transactions.add(transaction);
            }

            for (ZigBeeTransaction trans : transactions) {
                queue.add(trans);
            }
        }
    }

    @Override
    public String toString() {
        return "ZigBeeTransactionQueue [queueName=" + queueName + ", deviceIeeeAddress=" + deviceIeeeAdress
                + ", sleepy=" + sleepy + ", queued=" + queue.size() + ", outstandingTransactions="
                + outstandingTransactions.size() + ", profile="
                + profile
                + "]";
    }
}
