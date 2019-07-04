/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transaction;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.zsmartsystems.zigbee.greenpower.GpCommandResult;

public class ZigBeeGpTransactionFuture implements Future<GpCommandResult> {
    /**
     * The {@link GpCommandResult}
     */
    private GpCommandResult result;

    private boolean cancelled = false;

    // Not final for tests
    private static long TIMEOUT_MILLISECONDS = 12000;

    /**
     * Sets the {@link GpCommandResult}.
     *
     * @param result the {@link GpCommandResult}
     */
    public synchronized void set(final GpCommandResult result) {
        this.result = result;
        notifyAll();
    }

    @Override
    public synchronized boolean cancel(boolean mayInterruptIfRunning) {
        if (result != null || cancelled) {
            return false;
        }
        cancelled = true;
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
    public GpCommandResult get() throws InterruptedException, ExecutionException {
        try {
            return get(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            set(new GpCommandResult());
            return result;
        }
    }

    @Override
    public GpCommandResult get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException {
        synchronized (this) {
            if (result != null) {
                return result;
            }
            unit.timedWait(this, timeout);
            if (result == null) {
                set(new GpCommandResult());
            }
            return result;
        }
    }
}
