/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Future implementation for asynchronous methods.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class CommandResultFuture implements Future<CommandResult> {

    /**
     * The ZigBee Network Manager
     */
    private ZigBeeNetworkManager networkManager;

    /**
     * The command execution.
     */
    private CommandExecution commandExecution;

    /**
     * The result.
     */
    private CommandResult result;

    /**
     * Constructor which sets the {@link ZigBeeNetworkManager} to which this future belongs.
     *
     * @param {@link ZigBeeNetworkManager} the ZigBee network
     */
    public CommandResultFuture(ZigBeeNetworkManager zigBeeNetworkManager) {
        this.networkManager = zigBeeNetworkManager;
    }

    /**
     * Sets the command execution.
     *
     * @param commandExecution the command execution
     */
    public void setCommandExecution(CommandExecution commandExecution) {
        this.commandExecution = commandExecution;
    }

    /**
     * Sets result.
     *
     * @param result the result
     */
    public void set(final CommandResult result) {
        this.result = result;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCancelled() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isDone() {
        return result != null;
    }

    @Override
    public CommandResult get() throws InterruptedException, ExecutionException {
        try {
            return get(10000, TimeUnit.MILLISECONDS);
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
                networkManager.removeCommandExecution(commandExecution);
            }
            return result;
        }
    }
}
