/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transaction;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclCommand;

/**
 * Transaction class to handle the sending of commands and timeout in the event there is no response.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeTransaction implements ZigBeeCommandListener {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeTransaction.class);

    private final ZigBeeNetworkManager networkManager;
    private ZigBeeTransactionFuture transactionFuture;
    private ZigBeeTransactionMatcher responseMatcher;
    private ZigBeeCommand command;
    private ScheduledFuture<?> timeoutTask;

    private final static int DEFAULT_TIMEOUT_MILLISECONDS = 8000;

    private int timeout = DEFAULT_TIMEOUT_MILLISECONDS;

    /**
     * Transaction constructor
     * 
     * @param networkManager the {@link ZigBeeNetworkManager} to which the transaction is being sent
     */
    public ZigBeeTransaction(ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    /**
     * Sends {@link ZigBeeCommand} command and uses the {@link ZigBeeTransactionMatcher} to match the response.
     * The task will be timed out if there is no response.
     *
     * @param command the {@link ZigBeeCommand}
     * @param responseMatcher the {@link ZigBeeTransactionMatcher}
     * @return the {@link CommandResult} future.
     */
    public Future<CommandResult> sendTransaction(final ZigBeeCommand command,
            final ZigBeeTransactionMatcher responseMatcher) {
        this.command = command;
        this.responseMatcher = responseMatcher;
        synchronized (this.command) {
            transactionFuture = new ZigBeeTransactionFuture();

            // Schedule a task to timeout the transaction
            timeoutTask = networkManager.scheduleTask(new Runnable() {
                @Override
                public void run() {
                    timeoutTransaction();
                }
            }, timeout);

            networkManager.addCommandListener(this);

            int transactionId = networkManager.sendCommand(command);
            if (command instanceof ZclCommand) {
                ((ZclCommand) command).setTransactionId(transactionId);
            }

            return transactionFuture;
        }
    }

    /**
     * @return the current timeout in milliseconds
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * @param timeout the timeout to set in milliseconds
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public void commandReceived(ZigBeeCommand receivedCommand) {
        // Ensure that received command is not processed before command is sent
        // and hence transaction ID for the command set.
        synchronized (command) {
            if (responseMatcher.isTransactionMatch(command, receivedCommand)) {
                logger.debug("Transaction complete: {}", command);

                timeoutTask.cancel(false);

                synchronized (transactionFuture) {
                    transactionFuture.set(new CommandResult(receivedCommand));
                    transactionFuture.notify();
                }
                networkManager.removeCommandListener(this);
            }
        }
    }

    private void timeoutTransaction() {
        logger.debug("Transaction timeout: {}", command);
        synchronized (command) {
            synchronized (transactionFuture) {
                transactionFuture.cancel(false);
                transactionFuture.notify();
            }
            networkManager.removeCommandListener(this);
        }
    }
}
