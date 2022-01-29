/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transaction;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeStatus;

/**
 * Future implementation for asynchronous transactions. Multiple threads may listen for the completion of the
 * transaction.
 *
 * @author Chris Jackson
 */
public class ZigBeeTransactionFuture implements Future<CommandResult> {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeTransactionFuture.class);

    /**
     * The {@link CommandResult}
     */
    private CommandResult result;

    private final ZigBeeTransaction transaction;

    private boolean cancelled = false;

    /**
     * We set the timeout to 5 minutes to be long enough to allow the transaction manager to complete its queuing and
     * eventual retries
     */
    // Not final for tests
    private static long TIMEOUT_MINUTES = 5;

    /**
     * Constructor
     *
     * @param transaction the {@link ZigBeTransaction} linked to this future
     */
    public ZigBeeTransactionFuture(ZigBeeTransaction transaction) {
        this.transaction = transaction;
    }

    /**
     * Sets the {@link CommandResult}.
     *
     * @param result the {@link CommandResult}
     */
    public synchronized void set(final CommandResult result) {
        this.result = result;
        notifyAll();
    }

    @Override
    public synchronized boolean cancel(boolean mayInterruptIfRunning) {
        if (result != null || cancelled) {
            return false;
        }

        // cancelled must be set to true before cancelling the transaction
        // as this method will otherwise be called recursively
        cancelled = true;
        transaction.cancel();
        notifyAll();
        return true;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public synchronized boolean isDone() {
        return cancelled | result != null;
    }

    @Override
    public CommandResult get() throws ExecutionException {
        long start = System.currentTimeMillis();
        try {
            return get(TIMEOUT_MINUTES, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            logger.debug("TransactionFuture interrupted after {}ms: {}", System.currentTimeMillis() - start,
                    transaction);
            set(new CommandResult(ZigBeeStatus.FAILURE, null));
            cancel(true);
            return result;
        }
    }

    @Override
    public CommandResult get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException {
        synchronized (this) {
            if (result != null) {
                return result;
            }
            unit.timedWait(this, timeout);
            if (result == null) {
                set(new CommandResult(ZigBeeStatus.FAILURE, null));
                cancel(true);
            }
            return result;
        }
    }

    @Override
    public String toString() {
        return "ZigBeeTransactionFuture [cancelled=" + cancelled + ", transaction=" + transaction + ", result=" + result
                + "]";
    }
}
