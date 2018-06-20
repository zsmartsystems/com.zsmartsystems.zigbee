/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transaction;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.zsmartsystems.zigbee.CommandResult;

/**
 * Future implementation for asynchronous transactions.
 *
 * @author Chris Jackson
 */
public class ZigBeeTransactionFuture implements Future<CommandResult> {
    /**
     * The result.
     */
    private CommandResult result;

    private boolean cancelled = false;

    private final static int TIMEOUT_MILLISECONDS = 10000;

    /**
     * Sets the {@link CommandResult}.
     *
     * @param result the {@link CommandResult}
     */
    public synchronized void set(final CommandResult result) {
        this.result = result;
        notify();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        if (result != null || cancelled) {
            return false;
        }
        cancelled = true;
        notify();
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
    public CommandResult get() throws InterruptedException, ExecutionException {
        try {
            return get(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            return null;
        }
    }

    @Override
    public CommandResult get(long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {
        synchronized (this) {
            if (result != null) {
                return result;
            }
            unit.timedWait(this, timeout);
            if (result == null) {
                set(new CommandResult());
            }
            return result;
        }
    }
}
