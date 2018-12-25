/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transaction;

/**
 * A class that configures the transaction properties to be used by a transaction queue.
 * <ul>
 * <li>Outstanding transactions
 * <li>Inter-transaction delay
 * <li>Retries
 * <li>Duplicate removal
 * </ul>
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeTransactionProfile {
    private final int RETRIES = 3;
    private final int TRANSACTIONS = 2;
    private final int DELAY = 50;

    /**
     * The maximum number of outstanding transactions the queue will have outstanding.
     */
    private int maxOutstandingTransactions = 1;

    /**
     * The minimum time that will be enforced between sending each packet.
     */
    private int interTransactionDelay = 0;

    /**
     * The maximum number of retries that may be attempted
     */
    private int maxRetries = 3;

    /**
     * Constructs a {@link ZigBeeTransactionProfile}
     */
    public ZigBeeTransactionProfile() {
        this.maxRetries = RETRIES;
        this.maxOutstandingTransactions = TRANSACTIONS;
        this.interTransactionDelay = DELAY;
    }

    /**
     * Constructs a {@link ZigBeeTransactionProfile}
     *
     * @param maxRetries the maximum number of retries that will be performed
     * @param maxOutstandingTransactions the maximum number of simultaneous transactions the queue will release
     * @param interTransactionDelay the minimum delay between transactions
     * @param duplicateRemoval true if duplicate transactions should be removed
     */
    public ZigBeeTransactionProfile(int maxRetries, int maxOutstandingTransactions, int interTransactionDelay,
            boolean duplicateRemoval) {
        this.maxRetries = maxRetries;
        this.maxOutstandingTransactions = maxOutstandingTransactions;
        this.interTransactionDelay = interTransactionDelay;
    }

    /**
     * Gets the maximum number of transactions the queue will release at once.
     *
     * @return the maximum number of transactions the queue is allowed to have outstanding
     */
    public int getMaxOutstandingTransactions() {
        return maxOutstandingTransactions;
    }

    /**
     * Sets the maximum number of transactions the queue will release at once.
     *
     * @param outstandingTransactions the maximum number of transactions the queue is allowed to have outstanding
     */
    public void setMaxOutstandingTransactions(int outstandingTransactions) {
        this.maxOutstandingTransactions = outstandingTransactions;
    }

    /**
     * Gets the minimum period between each subsequent command that the queue will release.
     *
     * @return the minimum required delay between each subsequent transaction that can be sent
     */
    public int getInterTransactionDelay() {
        return interTransactionDelay;
    }

    /**
     * Sets the minimum period between each subsequent command that the queue will release.
     *
     * @param interTransactionDelay the minimum required delay between each subsequent transaction that can be sent
     */
    public void setInterTransactionDelay(int interTransactionDelay) {
        this.interTransactionDelay = interTransactionDelay;
    }

    /**
     * Gets the maximum number of retries attempts the queue will make on a single transaction before it is marked as
     * FAILED.
     *
     * @return the maximum number of retries allowed per transaction
     */
    public int getMaxRetries() {
        return maxRetries;
    }

    /**
     * Sets the maximum number of retries attempts the queue will make on a single transaction before it is marked as
     * FAILED.
     *
     * @param maxRetries the maximum number of retries allowed per transaction
     */
    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }
}
